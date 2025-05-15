Inventory Management System

## Project Overview

This Java-based console application offers a comprehensive Inventory Management System with full Create, Read, Update, and Delete (CRUD) functionality. It leverages MySQL as the backend database, integrated via JDBC, enabling persistent and reliable management of inventory data.

## Key Features

* Add new inventory items with detailed attributes
* Retrieve and display all inventory records
* Update existing inventory entries
* Delete items from the inventory
* Search inventory by item name or ID
* Robust input validation and exception handling

## System Requirements

* Java Development Kit (JDK) 8 or higher
* MySQL Server (Community Edition or higher)
* MySQL Connector/J (JDBC driver)
* Basic familiarity with Java programming and MySQL databases

## Installation and Setup

1. **Clone or Download** the repository to your local machine.
2. **Create the Database and Table**
   Execute the provided SQL scripts or manually run the SQL commands to set up the database schema.
3. **Configure Database Connection**
   Update the database credentials in `DBConnection.java` to match your MySQL username and password.
4. **Add MySQL JDBC Driver**
   Include the MySQL Connector/J JAR file in your project’s classpath for database connectivity.
5. **Compile the Source Code**
   Use the appropriate `javac` command with classpath settings to compile the Java files.
6. **Run the Application**
   Execute the `InventoryApp` class to launch the console-based Inventory Management System.

## Usage

After launching, the application provides a simple menu-driven interface for managing inventory items. Follow the input prompts to add, view, update, or delete inventory data easily.

## Additional Notes

This project serves as a practical example of integrating Java applications with relational databases to perform CRUD operations. Feel free to download, modify, and extend this codebase to suit your own needs.
