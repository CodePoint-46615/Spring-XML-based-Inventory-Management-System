# 🧾 Product Inventory Manager

[![Java](https://img.shields.io/badge/Java-17-blue?logo=java)](https://www.oracle.com/java/)
[![Spring Framework](https://img.shields.io/badge/Spring-Framework-green?logo=spring)](https://spring.io/projects/spring-framework)
[![XML Config](https://img.shields.io/badge/Config-XML-lightgrey)](https://docs.spring.io/)
[![Frontend](https://img.shields.io/badge/Frontend-HTML%20%7C%20CSS%20%7C%20JS-orange)]()
[![License](https://img.shields.io/badge/License-MIT-success)](LICENSE)

> A **Java + Spring XML–based Inventory Management System** with a simple **HTML/JS frontend**, allowing CRUD operations on products, dynamic discount computation, and real-time stock visualization.

---

## 🚀 Features

✅ Manage product inventory — add, update, delete, and list all products.  
✅ REST-like API endpoints (tested via Postman).  
✅ Compute **20% discount automatically** for products expiring within 7 days.  
✅ Categorize products by type or usage.  
✅ In-memory product list for fast prototyping.  
✅ Frontend built with **HTML, CSS, and JavaScript** consuming backend APIs.  
✅ Spring configuration handled **entirely through XML** (no Spring Boot).

---
```plaintext
Product-Inventory-Manager/
│
├── backend/
│   ├── src/main/java/com/example/demo/
│   │   ├── api/                # REST-like endpoints (ProductAPI)
│   │   ├── entity/             # Entities (Product, ProductCategory)
│   │   ├── repository/         # In-memory repository logic
│   │   └── service/            # Business service layer
│   ├── src/main/resources/
│   │   ├── ApplicationContext.xml  # Spring XML configuration
│   │   └── application.properties  # Basic app properties
│  

## 🏗️ Project Structure
Product-Inventory-Manager/
│
├── backend/
│ ├── src/main/java/com/example/demo/
│ │ ├── api/ # REST-like endpoints (ProductAPI)
│ │ ├── entity/ # Entities (Product, ProductCategory)
│ │ ├── repository/ # In-memory repository logic
│ │ └── service/ # Business service layer
│ ├── src/main/resources/
│ │ ├── ApplicationContext.xml # Spring XML configuration
│ │ └── application.properties # Basic app properties
│ ├── pom.xml # Maven dependencies
│ └── DemoApplication.java # Main entry point
│
└── frontend/
├── views/
│ └── index.html # Main user interface
├── assets/
│ ├── CSS/index.css # Styles
│ └── JS/index.js # API calls and DOM updates

## ⚙️ Technologies Used

| Layer        | Technologies |
|---------------|--------------|
| **Backend**   | Java 17, Spring Framework (XML Config), Maven |
| **Frontend**  | HTML5, CSS3, JavaScript (Fetch API) |
| **Data**      | In-memory Java `List<Product>` |
| **Testing**   | Postman / REST Client |

### 1️⃣ Prerequisites
- JDK 17 or later  
- Maven 3.x  
- Any IDE (IntelliJ / Eclipse / VS Code)

| Method   | Endpoint         | Description                   |
| -------- | ---------------- | ----------------------------- |
| `GET`    | `/products`      | Retrieve all products         |
| `POST`   | `/products`      | Add a new product             |
| `PUT`    | `/products/{id}` | Update existing product       |
| `DELETE` | `/products/{id}` | Remove product                |
| `POST`   | `/products/bulk` | Add multiple products at once |
