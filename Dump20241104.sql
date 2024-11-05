-- MySQL dump 10.13  Distrib 8.0.12, for Win64 (x86_64)
--
-- Host: localhost    Database: maid_booking_system
-- ------------------------------------------------------
-- Server version	8.0.12

/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
 SET NAMES utf8 ;
/*!40103 SET @OLD_TIME_ZONE=@@TIME_ZONE */;
/*!40103 SET TIME_ZONE='+00:00' */;
/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;

--
-- Table structure for table `admin`
--

DROP TABLE IF EXISTS `admin`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
 SET character_set_client = utf8mb4 ;
CREATE TABLE `admin` (
  `admin_id` varchar(50) NOT NULL,
  `f_name` varchar(50) NOT NULL,
  `m_name` varchar(50) DEFAULT NULL,
  `l_name` varchar(50) NOT NULL,
  `contact_number` varchar(10) NOT NULL,
  `address` varchar(100) NOT NULL,
  `email` varchar(50) NOT NULL,
  `is_super_admin` tinyint(1) DEFAULT NULL,
  `user_id` int(11) DEFAULT NULL,
  `crtd_date` date NOT NULL,
  `lst_updt_date` date NOT NULL,
  PRIMARY KEY (`admin_id`),
  KEY `FKq7pdkck9je126wpd9ijw3uwml` (`user_id`),
  CONSTRAINT `FKq7pdkck9je126wpd9ijw3uwml` FOREIGN KEY (`user_id`) REFERENCES `users` (`user_id`),
  CONSTRAINT `fk_admin_user_id` FOREIGN KEY (`user_id`) REFERENCES `users` (`user_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `admin`
--

LOCK TABLES `admin` WRITE;
/*!40000 ALTER TABLE `admin` DISABLE KEYS */;
INSERT INTO `admin` VALUES ('1dbc353a-fc1a-4dca-99bc-1955e431217c','admin5','','admin5','1111111234','admin5','admin5@test.com',0,12,'2023-05-07','2023-05-07'),('4e7a15b7-ef75-46a5-a7d2-05dd8d0b2baa','admin','','admin','1111111111','admin','admin@test.com',0,7,'2023-05-06','2023-05-06'),('9341af57-4685-4493-b3ca-c362bf380c21','admin','','admin','1111111111','admin','admin@test.com',0,8,'2023-05-06','2023-05-06'),('b0aafefd-37eb-4d24-924e-7baa7c6bcf44','admin','admin','admin','1111111111','test','admin@test.com',0,9,'2023-05-07','2023-05-07'),('bf4da0ba-4f66-4423-8ab7-4ff25a499d06','Ishan','','Shukla','9999999999','address_admin','admin@admin.com',1,1,'2023-04-24','2023-04-24'),('c4c3c582-e6eb-45f1-a6ca-00010c4c6476','admin6','','admin6','1111111111','admin6','admin6@admin.com',0,13,'2023-05-08','2023-05-08'),('c8cfd733-edfb-49c4-977b-8faf3f5f8a6d','admin','','admin','1111111111','test','admin@test.com',0,10,'2023-05-07','2023-05-07');
/*!40000 ALTER TABLE `admin` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `customer`
--

DROP TABLE IF EXISTS `customer`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
 SET character_set_client = utf8mb4 ;
CREATE TABLE `customer` (
  `customer_id` varchar(50) NOT NULL,
  `f_name` varchar(50) NOT NULL,
  `m_name` varchar(50) DEFAULT NULL,
  `l_name` varchar(50) NOT NULL,
  `contact_number` varchar(10) NOT NULL,
  `address` varchar(100) NOT NULL,
  `adhar_card_number` varchar(12) NOT NULL,
  `pan_card_number` varchar(10) NOT NULL,
  `email` varchar(50) DEFAULT NULL,
  `is_first_login` tinyint(1) NOT NULL,
  `is_verified` tinyint(1) NOT NULL,
  `rating` decimal(2,1) DEFAULT NULL,
  `user_id` int(11) DEFAULT NULL,
  `crtd_date` date NOT NULL,
  `lst_updt_date` date NOT NULL,
  `lst_updt_by` varchar(50) NOT NULL,
  PRIMARY KEY (`customer_id`),
  KEY `FKra1cb3fu95r1a0m7aksow0nk4` (`user_id`),
  CONSTRAINT `FKra1cb3fu95r1a0m7aksow0nk4` FOREIGN KEY (`user_id`) REFERENCES `users` (`user_id`),
  CONSTRAINT `fk_customer_user_id` FOREIGN KEY (`user_id`) REFERENCES `users` (`user_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `customer`
--

LOCK TABLES `customer` WRITE;
/*!40000 ALTER TABLE `customer` DISABLE KEYS */;
INSERT INTO `customer` VALUES ('379e86f0-6b92-4ce0-949f-8333931d64e3','test1','test1','test1','1111111111','test1','123456781234','BBBBB1111W','test1@test.com',1,1,NULL,3,'2023-05-06','2023-05-24','4e7a15b7-ef75-46a5-a7d2-05dd8d0b2baa'),('386d6b92-147f-41b5-9b31-f0bbed1956c2','test','','test','9999999999','stse','123412341234','BEEPB4343T','test@test.com',1,0,NULL,14,'2023-05-09','2023-05-09','system'),('4a8828c6-871c-4d6e-b226-e71a9e5095e3','test','','test','1111111111','abc','121212121212','BBBBB1111W','test@test.com',1,1,3.5,2,'2023-05-06','2023-05-24','4e7a15b7-ef75-46a5-a7d2-05dd8d0b2baa'),('5c54014f-186e-4a70-924d-b26c36503d70','test2','test2','test2','1111111111','test','111111111111','BBBBB1111W','test2@test.com',1,0,NULL,6,'2023-05-06','2023-05-06','system');
/*!40000 ALTER TABLE `customer` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `maid`
--

DROP TABLE IF EXISTS `maid`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
 SET character_set_client = utf8mb4 ;
CREATE TABLE `maid` (
  `maid_id` varchar(50) NOT NULL,
  `f_name` varchar(50) NOT NULL,
  `m_name` varchar(50) DEFAULT NULL,
  `l_name` varchar(50) NOT NULL,
  `contact_number` varchar(10) NOT NULL,
  `address` varchar(100) NOT NULL,
  `adhar_card_number` varchar(12) NOT NULL,
  `pan_card_number` varchar(10) NOT NULL,
  `email` varchar(50) DEFAULT NULL,
  `is_first_login` tinyint(1) NOT NULL,
  `is_verified` tinyint(1) NOT NULL,
  `rating` decimal(2,1) DEFAULT NULL,
  `user_id` int(11) DEFAULT NULL,
  `crtd_date` date NOT NULL,
  `lst_updt_date` date NOT NULL,
  `lst_updt_by` varchar(50) NOT NULL,
  PRIMARY KEY (`maid_id`),
  KEY `FKj8wja6lnk58t1bme6kjsmlgsw` (`user_id`),
  CONSTRAINT `FKj8wja6lnk58t1bme6kjsmlgsw` FOREIGN KEY (`user_id`) REFERENCES `users` (`user_id`),
  CONSTRAINT `fk_maid_user_id` FOREIGN KEY (`user_id`) REFERENCES `users` (`user_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `maid`
--

LOCK TABLES `maid` WRITE;
/*!40000 ALTER TABLE `maid` DISABLE KEYS */;
INSERT INTO `maid` VALUES ('4acedc28-758e-47e1-a099-58dc4b00516d','maid1','maid1','maid1','1111111111','test','111111111111','BBBBB1111Q','maid1@test.com',1,1,4.2,4,'2023-05-06','2023-05-24','4e7a15b7-ef75-46a5-a7d2-05dd8d0b2baa'),('4b3177f7-9d05-4af0-bd00-b7655ce6ff80','maid2','maid2','maid2','1111111111','test','123456781234','BBBBB1111W','maid2@test.com',1,0,3.6,5,'2023-05-06','2023-05-24','4e7a15b7-ef75-46a5-a7d2-05dd8d0b2baa'),('b6bc461e-f713-4f8a-8723-2d4cd455ca73','maid3','','maid3','1111111111','test','111111111111','BBBBB1111W','maid3@test.com',1,1,2.5,11,'2023-05-07','2023-05-24','4e7a15b7-ef75-46a5-a7d2-05dd8d0b2baa');
/*!40000 ALTER TABLE `maid` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `maid_account_table`
--

DROP TABLE IF EXISTS `maid_account_table`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
 SET character_set_client = utf8mb4 ;
CREATE TABLE `maid_account_table` (
  `id` int(11) NOT NULL,
  `maid_id` varchar(20) NOT NULL,
  `amt` decimal(7,2) NOT NULL,
  PRIMARY KEY (`id`),
  KEY `fk_maid_account_maid_id` (`maid_id`),
  CONSTRAINT `fk_maid_account_maid_id` FOREIGN KEY (`maid_id`) REFERENCES `maid` (`maid_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `maid_account_table`
--

LOCK TABLES `maid_account_table` WRITE;
/*!40000 ALTER TABLE `maid_account_table` DISABLE KEYS */;
/*!40000 ALTER TABLE `maid_account_table` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `request`
--

DROP TABLE IF EXISTS `request`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
 SET character_set_client = utf8mb4 ;
CREATE TABLE `request` (
  `rqst_id` varchar(50) NOT NULL,
  `cust_id` varchar(50) NOT NULL,
  `rqst_type` varchar(20) NOT NULL,
  `rqst_title` varchar(100) NOT NULL,
  `rqst_description` varchar(500) NOT NULL,
  `is_fulfilled` tinyint(1) NOT NULL,
  `is_cancelled` tinyint(1) NOT NULL,
  `status` varchar(20) NOT NULL,
  `is_finalized` tinyint(1) NOT NULL,
  `cust_rating` decimal(2,1) DEFAULT NULL,
  `crtd_date` date NOT NULL,
  `lst_updt_date` date NOT NULL,
  PRIMARY KEY (`rqst_id`),
  KEY `fk_request_cust_id` (`cust_id`),
  CONSTRAINT `fk_request_cust_id` FOREIGN KEY (`cust_id`) REFERENCES `customer` (`customer_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `request`
--

LOCK TABLES `request` WRITE;
/*!40000 ALTER TABLE `request` DISABLE KEYS */;
INSERT INTO `request` VALUES ('0bab479a-96e5-48e8-b26b-cecab3a1ed00','4a8828c6-871c-4d6e-b226-e71a9e5095e3','Event','title1','title1',1,0,'Close',1,4.0,'2023-05-12','2023-05-14'),('1be47516-bd4d-4f0c-97a8-a256843f916e','4a8828c6-871c-4d6e-b226-e71a9e5095e3','Event','event base rqst','event base rqst',1,0,'Close',1,NULL,'2023-05-14','2023-05-14'),('531d97eb-b849-4b96-be7b-0c8d10948ee4','4a8828c6-871c-4d6e-b226-e71a9e5095e3','MonthlyBasis','month basis rqst','month basis rqst',1,0,'Close',1,3.0,'2023-05-14','2023-05-14'),('a2f5254d-4ecc-4f79-982a-f355659fa74a','379e86f0-6b92-4ce0-949f-8333931d64e3','MonthlyBasis','month test1','month test1',1,0,'Close',1,NULL,'2023-05-13','2023-05-24'),('b9afa422-cfb7-48f5-93c0-9679f344f9f0','4a8828c6-871c-4d6e-b226-e71a9e5095e3','MonthlyBasis','task monthly','task monthly',1,0,'Close',1,NULL,'2023-05-13','2023-05-24'),('fc37cdf3-2819-4d65-9da1-fe710543f0a8','4a8828c6-871c-4d6e-b226-e71a9e5095e3','MonthlyBasis','title2','title2',1,0,'Close',1,NULL,'2023-05-12','2023-05-14');
/*!40000 ALTER TABLE `request` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `request_details`
--

DROP TABLE IF EXISTS `request_details`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
 SET character_set_client = utf8mb4 ;
CREATE TABLE `request_details` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `rqst_id` varchar(50) NOT NULL,
  `maid_id` varchar(50) NOT NULL,
  `is_accepted` tinyint(1) NOT NULL,
  `is_rejected` tinyint(1) NOT NULL,
  `is_cust_accepted` tinyint(1) NOT NULL,
  `is_cust_rejected` tinyint(1) NOT NULL,
  `proposed_amt` decimal(7,2) DEFAULT NULL,
  `final_amt` decimal(7,2) DEFAULT NULL,
  `damage_compensation_amt` decimal(7,2) DEFAULT NULL,
  `tip` decimal(7,2) DEFAULT NULL,
  `is_absent` tinyint(1) NOT NULL,
  `maid_rating` decimal(2,1) DEFAULT NULL,
  `lst_updt_date` date DEFAULT NULL,
  `lst_updt_by` varchar(50) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `fk_request_maid_rqst_id` (`rqst_id`),
  KEY `fk_request_maid_maid_id` (`maid_id`),
  CONSTRAINT `fk_request_maid_maid_id` FOREIGN KEY (`maid_id`) REFERENCES `maid` (`maid_id`),
  CONSTRAINT `fk_request_maid_rqst_id` FOREIGN KEY (`rqst_id`) REFERENCES `request` (`rqst_id`)
) ENGINE=InnoDB AUTO_INCREMENT=15 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `request_details`
--

LOCK TABLES `request_details` WRITE;
/*!40000 ALTER TABLE `request_details` DISABLE KEYS */;
INSERT INTO `request_details` VALUES (1,'0bab479a-96e5-48e8-b26b-cecab3a1ed00','4acedc28-758e-47e1-a099-58dc4b00516d',1,0,1,0,NULL,NULL,NULL,NULL,0,3.5,'2023-05-14','4a8828c6-871c-4d6e-b226-e71a9e5095e3'),(2,'0bab479a-96e5-48e8-b26b-cecab3a1ed00','b6bc461e-f713-4f8a-8723-2d4cd455ca73',1,0,1,0,NULL,NULL,NULL,NULL,0,2.5,'2023-05-14','4a8828c6-871c-4d6e-b226-e71a9e5095e3'),(3,'fc37cdf3-2819-4d65-9da1-fe710543f0a8','4acedc28-758e-47e1-a099-58dc4b00516d',1,0,1,0,NULL,NULL,NULL,NULL,0,4.0,'2023-05-14','4a8828c6-871c-4d6e-b226-e71a9e5095e3'),(4,'fc37cdf3-2819-4d65-9da1-fe710543f0a8','4b3177f7-9d05-4af0-bd00-b7655ce6ff80',1,0,0,1,NULL,NULL,NULL,NULL,0,NULL,'2023-05-14','4a8828c6-871c-4d6e-b226-e71a9e5095e3'),(5,'a2f5254d-4ecc-4f79-982a-f355659fa74a','4b3177f7-9d05-4af0-bd00-b7655ce6ff80',1,0,1,0,NULL,NULL,NULL,NULL,0,5.0,'2023-05-24','379e86f0-6b92-4ce0-949f-8333931d64e3'),(6,'a2f5254d-4ecc-4f79-982a-f355659fa74a','4acedc28-758e-47e1-a099-58dc4b00516d',0,1,0,1,NULL,NULL,NULL,NULL,0,NULL,'2023-05-24','379e86f0-6b92-4ce0-949f-8333931d64e3'),(7,'b9afa422-cfb7-48f5-93c0-9679f344f9f0','4acedc28-758e-47e1-a099-58dc4b00516d',1,0,0,1,NULL,NULL,NULL,NULL,0,NULL,'2023-05-24','4a8828c6-871c-4d6e-b226-e71a9e5095e3'),(8,'b9afa422-cfb7-48f5-93c0-9679f344f9f0','4b3177f7-9d05-4af0-bd00-b7655ce6ff80',1,0,1,0,NULL,NULL,NULL,NULL,0,4.0,'2023-05-24','4a8828c6-871c-4d6e-b226-e71a9e5095e3'),(9,'531d97eb-b849-4b96-be7b-0c8d10948ee4','4b3177f7-9d05-4af0-bd00-b7655ce6ff80',1,0,1,0,NULL,NULL,NULL,NULL,0,2.0,'2023-05-14','4a8828c6-871c-4d6e-b226-e71a9e5095e3'),(10,'531d97eb-b849-4b96-be7b-0c8d10948ee4','4acedc28-758e-47e1-a099-58dc4b00516d',0,1,0,1,NULL,NULL,NULL,NULL,0,NULL,'2023-05-14','4a8828c6-871c-4d6e-b226-e71a9e5095e3'),(11,'531d97eb-b849-4b96-be7b-0c8d10948ee4','b6bc461e-f713-4f8a-8723-2d4cd455ca73',1,0,0,1,NULL,NULL,NULL,NULL,0,NULL,'2023-05-14','4a8828c6-871c-4d6e-b226-e71a9e5095e3'),(12,'1be47516-bd4d-4f0c-97a8-a256843f916e','4acedc28-758e-47e1-a099-58dc4b00516d',1,0,1,0,NULL,NULL,NULL,NULL,0,5.0,'2023-05-14','4a8828c6-871c-4d6e-b226-e71a9e5095e3'),(13,'1be47516-bd4d-4f0c-97a8-a256843f916e','4b3177f7-9d05-4af0-bd00-b7655ce6ff80',1,0,1,0,NULL,NULL,NULL,NULL,0,3.5,'2023-05-14','4a8828c6-871c-4d6e-b226-e71a9e5095e3'),(14,'1be47516-bd4d-4f0c-97a8-a256843f916e','b6bc461e-f713-4f8a-8723-2d4cd455ca73',0,1,0,1,NULL,NULL,NULL,NULL,0,NULL,'2023-05-14','4a8828c6-871c-4d6e-b226-e71a9e5095e3');
/*!40000 ALTER TABLE `request_details` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `users`
--

DROP TABLE IF EXISTS `users`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
 SET character_set_client = utf8mb4 ;
CREATE TABLE `users` (
  `user_id` int(11) NOT NULL AUTO_INCREMENT,
  `username` varchar(50) NOT NULL,
  `password` varchar(255) NOT NULL,
  `role` varchar(20) NOT NULL,
  `is_active` tinyint(1) DEFAULT NULL,
  PRIMARY KEY (`user_id`),
  UNIQUE KEY `UKk8d0f2n7n88w1a16yhua64onx` (`username`)
) ENGINE=InnoDB AUTO_INCREMENT=15 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `users`
--

LOCK TABLES `users` WRITE;
/*!40000 ALTER TABLE `users` DISABLE KEYS */;
INSERT INTO `users` VALUES (1,'admin','$2a$10$aytp6vNXgsxw0aWympN7VOQ6B4cUjvN4RBlQqhbYVgpm5rjpU0tEy','SuperAdmin',1),(2,'test','$2a$10$ArljykCd3l2dJENswRqPZ.j9hHEASxINPR1Jj2wXsKPBv1YY0/mT6','Customer',1),(3,'test1','$2a$10$FZlC58B1EWnFuvrmFJoECOKqQS3IUsVMlHLUCBj5eiyJFtVfzvXpm','Customer',1),(4,'maid1','$2a$10$ebJzmxWuJ2azQmSq2utRc.PuxdH5qC/mB2LAs9rDldiqSWgWzUaCW','Maid',1),(5,'maid2','$2a$10$aOvBA/f1iX53Ldr1GVNeTOzgwxf7gqGqh59WLqNMEUy1z4hepvJ8a','Maid',1),(6,'test2','$2a$10$taaE1F2eJpX9wHgH8x3zR.hgAvnXKvsrhUZv6Ieau3Hn28/t4F0NS','Customer',1),(7,'admin1','$2a$10$yrZJkR/yEN1uuyQneVDPHut7hMhY6FzmgqCD7sfZ2GPsdPpRuehgK','Admin',1),(8,'admin2','$2a$10$YDJ4/APGdy5Fe8ufQWMLGuZ9QgJrGyYztnIKtpmC.YI04CTaF3foG','Admin',1),(9,'admin3','$2a$10$O/DnoK491DxWvJqZYZ0gIePQ4FTiLLxbEaG9VRNye6kP5X5dCQgPG','Admin',1),(10,'admin4','$2a$10$JnSIyrP8ldF6kNJG4vsIKOhhdWrmwFJvFtwpXMz5C2BMovZqlZ832','Admin',1),(11,'maid3','$2a$10$v830UqlXx1YU6V5474OTzuq4ykDceuNsumAuAhleBxoh6SOcdjuza','Maid',1),(12,'admin5','$2a$10$cQvRyyqN48lTHOWs9fCH1Od/ua6qbEKhjJE2nSvnhYACH4O6IfKle','Admin',1),(13,'admin6','$2a$10$Y4Bxte7qW9HaXPFDAG1dTeXnDB2CZnOewtt3TAejxkuIz9b/6GPw6','Admin',1),(14,'gb','$2a$10$/0Y75q50m/FwGjO0i7siruOPvyEzIw8uk99Lc3NeCeU83flyKBuPy','Customer',1);
/*!40000 ALTER TABLE `users` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2024-11-04  1:08:32
