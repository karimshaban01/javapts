# Java Parcel Transportation System (JavaPTS)

## Overview
The **Java Parcel Transportation System (JavaPTS)** is a Java-based application designed to simplify and streamline parcel transportation management. It uses **MySQL** as the database backend for storing and managing data and integrates **Java Mailer** for sending email notifications. The system is robust, scalable, and user-friendly, providing a complete solution for handling parcel transportation workflows, tracking, and notifications.

## Features
- **Parcel Management**: Add, update, and delete parcel details.
- **User Management**: Manage users, including customers and admins.
- **Real-Time Tracking**: Track parcels in real-time.
- **Email Notifications**: Notify customers and admins via email for parcel updates using Java Mailer.
- **Database Integration**: Stores all data securely in a MySQL database.
- **Reporting**: Generate reports for parcel status, delivery, and user activity.

## Tech Stack
- **Language**: Java (100%)
- **Database**: MySQL
- **Email API**: Java Mailer

## Prerequisites
To run this system, ensure the following software is installed on your machine:
- **Java (JDK 8 or later)**: To compile and run the application.
- **MySQL**: For database management.
- **Java Mail Library**: For handling email notifications.
- **IDE**: Any Java IDE (e.g., IntelliJ IDEA, Eclipse, NetBeans).
- **Maven**: For dependency management (optional but recommended).

## Installation and Setup
Follow these steps to install and set up the JavaPTS application:

### 1. Clone the Repository
```bash
git clone https://github.com/karimshaban01/javapts.git
cd javapts
```

### 2. Configure the Database
1. Create a new database in MySQL:
   ```sql
   CREATE DATABASE javapts_db;
   ```
2. Update the database credentials in the application's configuration file:
   ```java
   // Example: config/DatabaseConfig.java
   public static final String DB_URL = "jdbc:mysql://localhost:3306/javapts_db";
   public static final String DB_USER = "your_username";
   public static final String DB_PASSWORD = "your_password";
   ```

3. Import the database schema:
   - Locate the `javapts_schema.sql` file in the repository.
   - Execute the SQL script to set up the required tables:
     ```bash
     mysql -u your_username -p javapts_db < javapts_schema.sql
     ```

### 3. Configure Java Mailer
1. Update email server settings in the configuration file:
   ```java
   // Example: config/EmailConfig.java
   public static final String SMTP_HOST = "smtp.example.com";
   public static final String SMTP_PORT = "587";
   public static final String EMAIL_USERNAME = "your_email@example.com";
   public static final String EMAIL_PASSWORD = "your_password";
   ```

2. Ensure that the email account has SMTP access enabled (check your email provider's documentation for configuration).

### 4. Compile and Run the Application
1. Compile the Java code:
   ```bash
   javac -d bin src/*.java
   ```
2. Run the application:
   ```bash
   java -cp bin Main
   ```

### 5. Access the Application
- Once the application is running, follow the on-screen instructions to interact with the system.

## File Structure
The repository is organized as follows:
```
javapts/
├── src/                     # Source code
│   ├── Main.java            # Entry point for the application
│   ├── config/              # Configuration files (database, email settings)
│   ├── models/              # Data models for parcels, users, etc.
│   ├── services/            # Business logic and application services
│   ├── utils/               # Utility classes (e.g., email sending, logging)
├── javapts_schema.sql       # Database schema for MySQL
├── README.md                # Documentation
└── LICENSE                  # License information
```

## Usage
### Parcel Management
- Add new parcels with sender and receiver details.
- Update parcel status (e.g., in transit, delivered).
- Delete parcels from the system.

### User Management
- Admins can add, update, or delete users.
- Customers can view their parcel tracking information.

### Notifications
- The system automatically sends email notifications for:
  - Parcel shipment.
  - Parcel delivery.
  - Any status updates.

### Reports
- Generate detailed reports for:
  - Daily parcel movements.
  - Delivery success rates.
  - User activity logs.

## Contribution Guidelines
We welcome contributions to improve this project. Please follow these steps to contribute:
1. Fork the repository.
2. Create a new branch for your feature or bug fix:
   ```bash
   git checkout -b feature-name
   ```
3. Commit your changes:
   ```bash
   git commit -m "Add feature description"
   ```
4. Push your branch to your fork:
   ```bash
   git push origin feature-name
   ```
5. Open a pull request to the main repository.

## License
This project is licensed under the [MIT License](LICENSE).

## Support and Feedback
If you encounter any issues or have suggestions for improvement, feel free to open an issue in the [GitHub Issues](https://github.com/karimshaban01/javapts/issues) section of the repository.

---

Let me know if you need further assistance or additional details added to the documentation!
