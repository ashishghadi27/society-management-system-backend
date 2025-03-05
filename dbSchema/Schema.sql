CREATE DATABASE  IF NOT EXISTS `society_management_system` /*!40100 DEFAULT CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci */ /*!80016 DEFAULT ENCRYPTION='N' */;
USE `society_management_system`;
-- MySQL dump 10.13  Distrib 8.0.33, for Win64 (x86_64)
--
-- Host: localhost    Database: society_management_system
-- ------------------------------------------------------
-- Server version	8.0.33

/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!50503 SET NAMES utf8 */;
/*!40103 SET @OLD_TIME_ZONE=@@TIME_ZONE */;
/*!40103 SET TIME_ZONE='+00:00' */;
/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;

--
-- Table structure for table `expense_invoices`
--

DROP TABLE IF EXISTS `expense_invoices`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `expense_invoices` (
  `fid` bigint NOT NULL AUTO_INCREMENT,
  `file_name` varchar(50) NOT NULL,
  `link` varchar(256) DEFAULT NULL,
  `file_type` varchar(10) DEFAULT NULL,
  `uid` bigint NOT NULL,
  `created_at` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP,
  `updated_at` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  PRIMARY KEY (`fid`),
  KEY `uid` (`uid`),
  CONSTRAINT `expense_invoices_ibfk_1` FOREIGN KEY (`uid`) REFERENCES `expense_list` (`uid`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `expense_list`
--

DROP TABLE IF EXISTS `expense_list`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `expense_list` (
  `uid` bigint NOT NULL AUTO_INCREMENT,
  `name` varchar(100) NOT NULL,
  `description` text,
  `amount` double DEFAULT NULL,
  `eid` bigint NOT NULL,
  `created_at` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP,
  `updated_at` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  PRIMARY KEY (`uid`),
  KEY `eid` (`eid`),
  CONSTRAINT `expense_list_ibfk_1` FOREIGN KEY (`eid`) REFERENCES `expenses` (`eid`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `expenses`
--

DROP TABLE IF EXISTS `expenses`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `expenses` (
  `eid` bigint NOT NULL AUTO_INCREMENT,
  `expense_name` varchar(100) NOT NULL,
  `expense_description` text,
  `total_amount` double DEFAULT NULL,
  `sid` bigint NOT NULL,
  `created_at` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP,
  `updated_at` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  PRIMARY KEY (`eid`),
  KEY `sid` (`sid`),
  CONSTRAINT `expenses_ibfk_1` FOREIGN KEY (`sid`) REFERENCES `societies` (`sid`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `maintenance`
--

DROP TABLE IF EXISTS `maintenance`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `maintenance` (
  `m_id` bigint NOT NULL AUTO_INCREMENT,
  `rid` bigint NOT NULL,
  `annual_room_maintenance` double NOT NULL,
  `from_date` timestamp NOT NULL,
  `to_date` timestamp NOT NULL,
  `created_at` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP,
  `updated_at` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  PRIMARY KEY (`m_id`),
  KEY `rid` (`rid`),
  CONSTRAINT `maintenance_ibfk_1` FOREIGN KEY (`rid`) REFERENCES `rooms` (`rid`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `maintenance_payment_details`
--

DROP TABLE IF EXISTS `maintenance_payment_details`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `maintenance_payment_details` (
  `uid` bigint NOT NULL AUTO_INCREMENT,
  `m_id` bigint NOT NULL,
  `mid` bigint NOT NULL,
  `amount` double DEFAULT NULL,
  `payment_mode` varchar(50) DEFAULT 'CASH',
  `created_at` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP,
  `updated_at` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  PRIMARY KEY (`uid`),
  KEY `m_id` (`m_id`),
  KEY `mid` (`mid`),
  CONSTRAINT `maintenance_payment_details_ibfk_1` FOREIGN KEY (`m_id`) REFERENCES `maintenance` (`m_id`),
  CONSTRAINT `maintenance_payment_details_ibfk_2` FOREIGN KEY (`mid`) REFERENCES `members` (`mid`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `meeting_files`
--

DROP TABLE IF EXISTS `meeting_files`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `meeting_files` (
  `fid` bigint NOT NULL AUTO_INCREMENT,
  `file_name` varchar(50) NOT NULL,
  `link` varchar(256) DEFAULT NULL,
  `file_type` varchar(10) DEFAULT NULL,
  `meeting_id` bigint NOT NULL,
  `created_at` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP,
  `updated_at` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  PRIMARY KEY (`fid`),
  KEY `meeting_id` (`meeting_id`),
  CONSTRAINT `meeting_files_ibfk_1` FOREIGN KEY (`meeting_id`) REFERENCES `meetings` (`meeting_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `meeting_minutes`
--

DROP TABLE IF EXISTS `meeting_minutes`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `meeting_minutes` (
  `minute_id` bigint NOT NULL AUTO_INCREMENT,
  `minute_content` text,
  `sort_id` bigint DEFAULT NULL,
  `meeting_id` bigint NOT NULL,
  `created_at` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP,
  `updated_at` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  PRIMARY KEY (`minute_id`),
  KEY `meeting_id` (`meeting_id`),
  CONSTRAINT `meeting_minutes_ibfk_1` FOREIGN KEY (`meeting_id`) REFERENCES `meetings` (`meeting_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `meetings`
--

DROP TABLE IF EXISTS `meetings`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `meetings` (
  `meeting_id` bigint NOT NULL AUTO_INCREMENT,
  `meeting_title` varchar(200) NOT NULL,
  `meeting_agenda` text,
  `meeting_start_datetime` datetime NOT NULL,
  `meeting_end_datetime` datetime NOT NULL,
  `meeting_link` varchar(200) DEFAULT NULL,
  `sid` bigint NOT NULL,
  `created_at` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP,
  `updated_at` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  PRIMARY KEY (`meeting_id`),
  KEY `sid` (`sid`),
  CONSTRAINT `meetings_ibfk_1` FOREIGN KEY (`sid`) REFERENCES `societies` (`sid`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `members`
--

DROP TABLE IF EXISTS `members`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `members` (
  `mid` bigint NOT NULL AUTO_INCREMENT,
  `first_name` varchar(100) NOT NULL,
  `last_name` varchar(100) NOT NULL,
  `email` varchar(100) NOT NULL,
  `hashed_password` varchar(256) NOT NULL,
  `type` varchar(20) NOT NULL DEFAULT 'MEMBER',
  `rid` bigint NOT NULL,
  `approved` tinyint(1) DEFAULT NULL,
  `created_at` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP,
  `updated_at` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  PRIMARY KEY (`mid`),
  UNIQUE KEY `email` (`email`),
  KEY `rid` (`rid`),
  CONSTRAINT `members_ibfk_1` FOREIGN KEY (`rid`) REFERENCES `rooms` (`rid`)
) ENGINE=InnoDB AUTO_INCREMENT=7 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `parking_space_allotment`
--

DROP TABLE IF EXISTS `parking_space_allotment`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `parking_space_allotment` (
  `id` bigint NOT NULL AUTO_INCREMENT,
  `pid` bigint NOT NULL,
  `mid` bigint NOT NULL,
  `created_at` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP,
  `updated_at` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  PRIMARY KEY (`id`),
  KEY `mid` (`mid`),
  KEY `pid` (`pid`),
  CONSTRAINT `parking_space_allotment_ibfk_1` FOREIGN KEY (`mid`) REFERENCES `members` (`mid`),
  CONSTRAINT `parking_space_allotment_ibfk_2` FOREIGN KEY (`pid`) REFERENCES `parking_spaces` (`pid`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `parking_spaces`
--

DROP TABLE IF EXISTS `parking_spaces`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `parking_spaces` (
  `pid` bigint NOT NULL AUTO_INCREMENT,
  `parking_id` varchar(100) NOT NULL,
  `sid` bigint NOT NULL,
  `created_at` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP,
  `updated_at` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  PRIMARY KEY (`pid`),
  KEY `sid` (`sid`),
  CONSTRAINT `parking_spaces_ibfk_1` FOREIGN KEY (`sid`) REFERENCES `societies` (`sid`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `rooms`
--

DROP TABLE IF EXISTS `rooms`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `rooms` (
  `rid` bigint NOT NULL AUTO_INCREMENT,
  `sid` bigint NOT NULL,
  `room_no` varchar(50) NOT NULL,
  `room_size` varchar(50) NOT NULL,
  PRIMARY KEY (`rid`),
  KEY `sid` (`sid`),
  CONSTRAINT `rooms_ibfk_1` FOREIGN KEY (`sid`) REFERENCES `societies` (`sid`)
) ENGINE=InnoDB AUTO_INCREMENT=106 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `societies`
--

DROP TABLE IF EXISTS `societies`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `societies` (
  `sid` bigint NOT NULL AUTO_INCREMENT,
  `name` varchar(256) NOT NULL,
  `address_line1` varchar(256) NOT NULL,
  `address_line2` varchar(256) DEFAULT NULL,
  `plot_number` varchar(20) DEFAULT NULL,
  `profile_pic` varchar(200) DEFAULT NULL,
  `society_fund` double DEFAULT NULL,
  `parking_available` tinyint(1) NOT NULL DEFAULT '0',
  `is_approved` tinyint(1) NOT NULL DEFAULT '0',
  `created_at` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP,
  `updated_at` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  PRIMARY KEY (`sid`)
) ENGINE=InnoDB AUTO_INCREMENT=26 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `society_registration_files`
--

DROP TABLE IF EXISTS `society_registration_files`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `society_registration_files` (
  `fid` bigint NOT NULL AUTO_INCREMENT,
  `file_name` varchar(50) NOT NULL,
  `link` varchar(256) DEFAULT NULL,
  `file_type` varchar(256) DEFAULT NULL,
  `sid` bigint NOT NULL,
  `created_at` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP,
  `updated_at` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  PRIMARY KEY (`fid`),
  KEY `sid` (`sid`),
  CONSTRAINT `society_registration_files_ibfk_1` FOREIGN KEY (`sid`) REFERENCES `societies` (`sid`)
) ENGINE=InnoDB AUTO_INCREMENT=47 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `sub_members`
--

DROP TABLE IF EXISTS `sub_members`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `sub_members` (
  `s_mid` bigint NOT NULL AUTO_INCREMENT,
  `first_name` varchar(100) NOT NULL,
  `last_name` varchar(100) NOT NULL,
  `type` varchar(20) NOT NULL DEFAULT 'FAMILY',
  `mid` bigint NOT NULL,
  `created_at` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP,
  `updated_at` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  PRIMARY KEY (`s_mid`),
  KEY `mid` (`mid`),
  CONSTRAINT `sub_members_ibfk_1` FOREIGN KEY (`mid`) REFERENCES `members` (`mid`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2025-03-05  0:11:31
