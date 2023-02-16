-- MySQL dump 10.13  Distrib 8.0.30, for Win64 (x86_64)
--
-- Host: localhost    Database: planit
-- ------------------------------------------------------
-- Server version	8.0.30

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
-- Table structure for table `chatting_message`
--

DROP TABLE IF EXISTS `chatting_message`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `chatting_message` (
  `chatting_id` bigint NOT NULL AUTO_INCREMENT,
  `created_at` datetime NOT NULL,
  `updated_date` datetime NOT NULL,
  `constructor` varchar(255) DEFAULT NULL,
  `updater` varchar(255) DEFAULT NULL,
  `message` varchar(255) NOT NULL,
  `member_id` bigint DEFAULT NULL,
  `room_id` bigint DEFAULT NULL,
  PRIMARY KEY (`chatting_id`),
  KEY `FKebjahyugxo6wn530aj3151ipv` (`member_id`),
  KEY `FKbfalr0abm4gyfa0j8umh1n1a2` (`room_id`),
  CONSTRAINT `FKbfalr0abm4gyfa0j8umh1n1a2` FOREIGN KEY (`room_id`) REFERENCES `room` (`room_id`),
  CONSTRAINT `FKebjahyugxo6wn530aj3151ipv` FOREIGN KEY (`member_id`) REFERENCES `member` (`member_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `chatting_message`
--

LOCK TABLES `chatting_message` WRITE;
/*!40000 ALTER TABLE `chatting_message` DISABLE KEYS */;
/*!40000 ALTER TABLE `chatting_message` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2023-02-16 21:19:39
