package com.example.demo;
import org.apache.catalina.Context;
import org.apache.catalina.startup.Tomcat;
import org.springframework.web.context.support.XmlWebApplicationContext;
import org.springframework.web.servlet.DispatcherServlet;


public class DemoApplication {
    public static void main(String[] args) throws Exception {

        // 1. Configure Tomcat
        Tomcat tomcat = new Tomcat();
        tomcat.setPort(8083);

        // 2. Create Spring ApplicationContext
        XmlWebApplicationContext context = new XmlWebApplicationContext();
        context.setConfigLocation("classpath:ApplicationContext.xml");

        // 3. Register DispatcherServlet PROPERLY
        DispatcherServlet servlet = new DispatcherServlet(context);
        Context tomcatCtx = tomcat.addContext("", null);  // Root context
        Tomcat.addServlet(tomcatCtx, "dispatcher", servlet);
        tomcatCtx.addServletMappingDecoded("/*", "dispatcher");  // Map all requests

        // 4. Start Server
        tomcat.start();
        System.out.println("Server running on http://localhost:" + tomcat.getConnector().getPort());
        tomcat.getServer().await();
    }
}