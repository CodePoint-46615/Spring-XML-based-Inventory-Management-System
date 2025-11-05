
const BASE_URL = 'http://localhost:8083/api'; 

// Add a new product
function addProduct() {
    const product = {
        id: parseInt(document.getElementById('productId').value),
        name: document.getElementById('productName').value,
        category: document.getElementById('productCategory').value,
        price: parseFloat(document.getElementById('productPrice').value),
        quantity: parseInt(document.getElementById('productQuantity').value),
        expiryDate: document.getElementById('productExpiry').value // ISO date string: yyyy-MM-dd 
    };

    fetch(`${BASE_URL}/products`, {
        method: 'POST',
        headers: {'Content-Type': 'application/json'},
        body: JSON.stringify(product)
    })
    .then(response => {
        if (!response.ok) throw new Error('Failed to add product');
        return response.text();
    })
    .then(msg => {
        alert('Product added successfully!');
        clearAddForm();
    })
    .catch(error => alert(error.message));
}


// Find product by ID
function findProduct() {
    const id = document.getElementById('searchId').value;
    fetch(`${BASE_URL}/products/${id}`)
        .then(response => {
            if (!response.ok) throw new Error('Product not found');
            return response.json();
        })
        .then(product => {
            const detail = document.getElementById('productDetail');
            detail.innerHTML = `
                <h3>Product Details</h3>
                <p><strong>ID:</strong> ${product.id}</p>
                <p><strong>Name:</strong> ${product.name}</p>
                <p><strong>Category:</strong> ${product.category}</p>
                <p><strong>Price:</strong> $${product.price}</p>
                <p><strong>Quantity:</strong> ${product.quantity}</p>
                <p><strong>Expiry Date:</strong> ${product.expiryDate || 'N/A'}</p>
            `;
        })
        .catch(error => {
            document.getElementById('productDetail').innerHTML = `<p style="color:red">${error.message}</p>`;
        });
}

// Load all products
function loadAllProducts() {
    fetch(`${BASE_URL}/products`)
        .then(response => response.json())
        .then(products => displayProducts(products))
        .catch(error => alert('Error loading products: ' + error));
}

// Load expiring products
function loadExpiringProducts() {
    fetch(`${BASE_URL}/expiring`)
        .then(response => response.json())
        .then(products => displayProducts(products))
        .catch(error => alert('Error loading expiring products: ' + error));
}

// Load products by category
function loadByCategory() {
    const category = document.getElementById('categoryFilter').value;
    if (!category) {
        loadAllProducts();
        return;
    }
    
    fetch(`${BASE_URL}/category/${category}`)
        .then(response => response.json())
        .then(products => displayProducts(products))
        .catch(error => alert('Error loading products: ' + error));
}

// Display products in table
function displayProducts(products) {
    const tbody = document.getElementById('productList');
    tbody.innerHTML = '';

    if (products.length === 0) {
        tbody.innerHTML = '<tr><td colspan="7">No products found</td></tr>';
        return;
    }

    products.forEach(product => {
        const row = document.createElement('tr');
        
        // Highlight expiring/expired products
        if (product.expiryDate) {
            const expiryDate = new Date(product.expiryDate);
            const today = new Date();
            const daysDiff = Math.floor((expiryDate - today) / (1000 * 60 * 60 * 24));
            
            if (daysDiff < 0) {
                row.classList.add('expired');
            } else if (daysDiff <= 7) {
                row.classList.add('expiring');
            }
        }

        row.innerHTML = `
            <td>${product.id}</td>
            <td>${product.name}</td>
            <td>${product.category}</td>
            <td>${product.price}</td>
            <td>${product.quantity}</td>
            <td>${product.expiryDate || 'N/A'}</td>
            <td>${product.discount ? `Discounted: ${product.discount*100}%` : 'Normal'}</td>
        `;
        tbody.appendChild(row);
    });
}

// Clear add product form
function clearAddForm() {
    document.getElementById('productId').value = '';
    document.getElementById('productName').value = '';
    document.getElementById('productCategory').value = '';
    document.getElementById('productPrice').value = '';
    document.getElementById('productQuantity').value = '';
    document.getElementById('productExpiry').value = '';
}

// Load all products when page loads
window.onload = loadAllProducts;