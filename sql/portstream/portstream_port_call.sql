CREATE DATABASE  IF NOT EXISTS `portstream` /*!40100 DEFAULT CHARACTER SET utf8 */;
USE `portstream`;
-- MySQL dump 10.13  Distrib 5.7.17, for Win64 (x86_64)
--
-- Host: localhost    Database: portstream
-- ------------------------------------------------------
-- Server version	5.7.21-log

/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8 */;
/*!40103 SET @OLD_TIME_ZONE=@@TIME_ZONE */;
/*!40103 SET TIME_ZONE='+00:00' */;
/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;

--
-- Table structure for table `port_call`
--

DROP TABLE IF EXISTS `port_call`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `port_call` (
  `port_call_id` int(11) NOT NULL,
  `current_port_country` varchar(255) DEFAULT NULL,
  `eta` datetime DEFAULT NULL,
  `destination_port_id` int(11) DEFAULT NULL,
  `origin_port_id` int(11) DEFAULT NULL,
  `previous_origin_port_id` int(11) DEFAULT NULL,
  PRIMARY KEY (`port_call_id`),
  KEY `FKom4iqoa5fx32kh1p618ip1v22` (`destination_port_id`),
  KEY `FKm593hlrcn84trvloydldpikiq` (`origin_port_id`),
  KEY `FKlog9wcivfb3scktiu3v66lwpg` (`previous_origin_port_id`),
  CONSTRAINT `FKlog9wcivfb3scktiu3v66lwpg` FOREIGN KEY (`previous_origin_port_id`) REFERENCES `port` (`port_id`),
  CONSTRAINT `FKm593hlrcn84trvloydldpikiq` FOREIGN KEY (`origin_port_id`) REFERENCES `port` (`port_id`),
  CONSTRAINT `FKom4iqoa5fx32kh1p618ip1v22` FOREIGN KEY (`destination_port_id`) REFERENCES `port` (`port_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2018-10-21 20:05:29
