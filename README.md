# Rent-A-Car Application

This project is a simple car rental management application built with Java Swing. It allows administrators to manage car bookings, car inventory, brands, and models through a graphical user interface.

## How to Run

### Clone the Repository:

```sh
git clone <repository-url>
cd <repository-directory>
```

### Configure Database:

Ensure you have a PostgreSQL database set up. Import `rentacar.sql`, create a dedicated local database role, and keep its password outside the repository.

```sh
cp .env.example .env
# Fill in RENTACAR_DB_PASSWORD and adjust the URL/user if needed.
set -a
source .env
set +a
```

The application requires `RENTACAR_DB_URL`, `RENTACAR_DB_USER`, and `RENTACAR_DB_PASSWORD`. IntelliJ run configurations can instead set `rentacar.db.url`, `rentacar.db.user`, and `rentacar.db.password` as Java system properties. Neither `.env` nor IDE run configurations should be committed.

### Build and Run:

Compile the project with Java 14 or newer and run the `App` class. The following commands target macOS/Linux:

```sh
mkdir -p bin
find src -name '*.java' -print0 | xargs -0 javac --release 14 -cp postgresql-42.7.3.jar -d bin
java -cp "bin:postgresql-42.7.3.jar" App
```

## Local Demo Credentials

The sample database dump includes these convenience accounts for local evaluation only:

### Admin Login:

- **Username:** admin
- **Password:** 123

### Employee Login:

- **Username:** employee
- **Password:** 123

Do not expose the sample database to a network or reuse these passwords. Replace or remove the demo accounts before using the application outside a local development environment.

## Features

- Car and booking management
- Model and brand management
- User authentication

## Dependencies

- Java 14 or newer
- PostgreSQL
