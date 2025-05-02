/*M!999999\- enable the sandbox mode */ 
-- MariaDB dump 10.19  Distrib 10.11.11-MariaDB, for debian-linux-gnu (x86_64)
--
-- Host: localhost    Database: dsa_ga
-- ------------------------------------------------------
-- Server version	10.11.11-MariaDB-0+deb12u1

/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;
/*!40103 SET @OLD_TIME_ZONE=@@TIME_ZONE */;
/*!40103 SET TIME_ZONE='+00:00' */;
/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;

--
-- Table structure for table `Admin_Logs`
--

DROP TABLE IF EXISTS `Admin_Logs`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8mb4 */;
CREATE TABLE `Admin_Logs` (
  `log_id` int(11) NOT NULL AUTO_INCREMENT,
  `admin_id` int(11) DEFAULT NULL,
  `action` text NOT NULL,
  `timestamp` timestamp NULL DEFAULT current_timestamp(),
  `affected_parcel_id` int(11) DEFAULT NULL,
  `notes` text DEFAULT NULL,
  PRIMARY KEY (`log_id`),
  KEY `admin_id` (`admin_id`),
  KEY `affected_parcel_id` (`affected_parcel_id`),
  CONSTRAINT `Admin_Logs_ibfk_1` FOREIGN KEY (`admin_id`) REFERENCES `Users` (`user_id`),
  CONSTRAINT `Admin_Logs_ibfk_2` FOREIGN KEY (`affected_parcel_id`) REFERENCES `Parcels` (`parcel_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `Admin_Logs`
--

LOCK TABLES `Admin_Logs` WRITE;
/*!40000 ALTER TABLE `Admin_Logs` DISABLE KEYS */;
/*!40000 ALTER TABLE `Admin_Logs` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `Notifications`
--

DROP TABLE IF EXISTS `Notifications`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8mb4 */;
CREATE TABLE `Notifications` (
  `notification_id` int(11) NOT NULL AUTO_INCREMENT,
  `user_id` int(11) DEFAULT NULL,
  `parcel_id` int(11) DEFAULT NULL,
  `notification_type` enum('SMS','Email','App') NOT NULL,
  `message` text DEFAULT NULL,
  `sent_time` timestamp NULL DEFAULT current_timestamp(),
  `status` enum('Pending','Sent','Failed') NOT NULL,
  PRIMARY KEY (`notification_id`),
  KEY `user_id` (`user_id`),
  KEY `parcel_id` (`parcel_id`),
  CONSTRAINT `Notifications_ibfk_1` FOREIGN KEY (`user_id`) REFERENCES `Users` (`user_id`),
  CONSTRAINT `Notifications_ibfk_2` FOREIGN KEY (`parcel_id`) REFERENCES `Parcels` (`parcel_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `Notifications`
--

LOCK TABLES `Notifications` WRITE;
/*!40000 ALTER TABLE `Notifications` DISABLE KEYS */;
/*!40000 ALTER TABLE `Notifications` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `Parcel_Tracking`
--

DROP TABLE IF EXISTS `Parcel_Tracking`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8mb4 */;
CREATE TABLE `Parcel_Tracking` (
  `tracking_id` int(11) NOT NULL AUTO_INCREMENT,
  `parcel_id` int(11) DEFAULT NULL,
  `checkpoint_location` varchar(255) DEFAULT NULL,
  `status_update_time` timestamp NULL DEFAULT current_timestamp(),
  `status` enum('At Warehouse','Out for Delivery','In Transit','Delivered') NOT NULL,
  `courier_id` int(11) DEFAULT NULL,
  PRIMARY KEY (`tracking_id`),
  KEY `parcel_id` (`parcel_id`),
  KEY `courier_id` (`courier_id`),
  CONSTRAINT `Parcel_Tracking_ibfk_1` FOREIGN KEY (`parcel_id`) REFERENCES `Parcels` (`parcel_id`),
  CONSTRAINT `Parcel_Tracking_ibfk_2` FOREIGN KEY (`courier_id`) REFERENCES `Users` (`user_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `Parcel_Tracking`
--

LOCK TABLES `Parcel_Tracking` WRITE;
/*!40000 ALTER TABLE `Parcel_Tracking` DISABLE KEYS */;
/*!40000 ALTER TABLE `Parcel_Tracking` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `Parcels`
--

DROP TABLE IF EXISTS `Parcels`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8mb4 */;
CREATE TABLE `Parcels` (
  `parcel_id` int(11) NOT NULL AUTO_INCREMENT,
  `sender_id` int(11) DEFAULT NULL,
  `recipient_id` int(11) DEFAULT NULL,
  `dimensions` varchar(255) DEFAULT NULL,
  `pickup_address` text DEFAULT NULL,
  `delivery_address` text DEFAULT NULL,
  `status` enum('Registered','In Transit','Delivered','Failed','Returned') NOT NULL,
  `estimated_arrival_time` datetime DEFAULT NULL,
  `actual_arrival_time` datetime DEFAULT NULL,
  `created_at` timestamp NULL DEFAULT current_timestamp(),
  `updated_at` timestamp NULL DEFAULT current_timestamp() ON UPDATE current_timestamp(),
  PRIMARY KEY (`parcel_id`),
  KEY `sender_id` (`sender_id`),
  KEY `recipient_id` (`recipient_id`),
  CONSTRAINT `Parcels_ibfk_1` FOREIGN KEY (`sender_id`) REFERENCES `Users` (`user_id`),
  CONSTRAINT `Parcels_ibfk_2` FOREIGN KEY (`recipient_id`) REFERENCES `Users` (`user_id`)
) ENGINE=InnoDB AUTO_INCREMENT=41 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `Parcels`
--

LOCK TABLES `Parcels` WRITE;
/*!40000 ALTER TABLE `Parcels` DISABLE KEYS */;
INSERT INTO `Parcels` VALUES
(1,1,2,'','ARUSHA','MWANZA','In Transit','2025-04-23 12:00:00','2025-04-23 12:00:00','2025-04-24 03:42:30','2025-04-24 03:42:30'),
(40,1,2,'','Arusha','kigoma','Delivered','2025-04-23 12:00:00','2025-04-23 12:00:00','2025-05-02 09:34:32','2025-05-02 09:34:32');
/*!40000 ALTER TABLE `Parcels` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `Roles`
--

DROP TABLE IF EXISTS `Roles`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8mb4 */;
CREATE TABLE `Roles` (
  `role_id` int(11) NOT NULL AUTO_INCREMENT,
  `role_name` enum('Sender','Recipient','Admin','Courier','Warehouse Staff') NOT NULL,
  `permissions` text DEFAULT NULL,
  PRIMARY KEY (`role_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `Roles`
--

LOCK TABLES `Roles` WRITE;
/*!40000 ALTER TABLE `Roles` DISABLE KEYS */;
/*!40000 ALTER TABLE `Roles` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `Users`
--

DROP TABLE IF EXISTS `Users`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8mb4 */;
CREATE TABLE `Users` (
  `user_id` int(11) NOT NULL AUTO_INCREMENT,
  `first_name` varchar(100) NOT NULL,
  `last_name` varchar(100) NOT NULL,
  `email` varchar(255) NOT NULL,
  `password` varchar(255) NOT NULL,
  `role` enum('Sender','Recipient','Courier','Warehouse Staff','Admin') NOT NULL,
  `phone_number` varchar(20) DEFAULT NULL,
  `address` text DEFAULT NULL,
  `created_at` timestamp NULL DEFAULT current_timestamp(),
  `updated_at` timestamp NULL DEFAULT current_timestamp() ON UPDATE current_timestamp(),
  PRIMARY KEY (`user_id`),
  UNIQUE KEY `email` (`email`)
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `Users`
--

LOCK TABLES `Users` WRITE;
/*!40000 ALTER TABLE `Users` DISABLE KEYS */;
INSERT INTO `Users` VALUES
(1,'Karim','Shaban','karimxhaban@gmail.com','Karim@01','Admin','255785817222','ARUSHA','2025-04-23 17:17:54','2025-04-23 17:17:54'),
(2,'','OMARY','omary@gmail.com','1234567890','Admin','255785817222','MWANZA','2025-04-23 17:50:25','2025-04-23 17:50:25'),
(3,'','IGNASS MAJALIWA','iggy@gmail.com','1234567890','Sender','0719837206','DODOMA','2025-04-23 17:54:40','2025-04-23 17:54:40'),
(4,'','MACRICE','mc@gmail.com','1234567890','Recipient','0628370174','MBEYA','2025-04-23 18:01:47','2025-04-23 18:01:47');
/*!40000 ALTER TABLE `Users` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `Warehouses`
--

DROP TABLE IF EXISTS `Warehouses`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8mb4 */;
CREATE TABLE `Warehouses` (
  `warehouse_id` int(11) NOT NULL AUTO_INCREMENT,
  `warehouse_name` varchar(255) DEFAULT NULL,
  `location` text DEFAULT NULL,
  `capacity` int(11) DEFAULT NULL,
  `staff_count` int(11) DEFAULT NULL,
  PRIMARY KEY (`warehouse_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `Warehouses`
--

LOCK TABLES `Warehouses` WRITE;
/*!40000 ALTER TABLE `Warehouses` DISABLE KEYS */;
/*!40000 ALTER TABLE `Warehouses` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2025-05-02 15:06:50
