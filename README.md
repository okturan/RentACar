# Rent-A-Car Application

This project is a simple car rental management application built with Java Swing. It allows administrators to manage car bookings, car inventory, brands, and models through a graphical user interface.

## How to Run

### Clone the Repository:

```sh
git clone <repository-url>
cd <repository-directory>
```

### Configure Database:

Ensure you have a PostgreSQL database set up. Update the database connection details in `Db.java`:

```java
String url = "jdbc:postgresql://localhost:5432/rentacar";
String user = "postgres";
String password = "1234";
```

Use pgadmin to import rentacar.sql to your database.

### Build and Run:

Compile the project and run the `App.java` class.

```sh
javac -d bin src/**/*.java
java -cp bin App
```

## Test Credentials

### Admin Login:

- **Username:** admin
- **Password:** 123

### Employee Login:

- **Username:** employee
- **Password:** 123

## Features

- Car and booking management
- Model and brand management
- User authentication

## Dependencies

- Java
- PostgreSQL
