-- MySQL dump 10.13  Distrib 5.6.24, for Win64 (x86_64)
--
-- Host: localhost    Database: ticketmaster
-- ------------------------------------------------------
-- Server version	5.6.26-log

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
-- Table structure for table `movie`
--

DROP TABLE IF EXISTS `movie`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `movie` (
  `id` bigint(30) NOT NULL AUTO_INCREMENT,
  `title` varchar(45) NOT NULL,
  `genre` varchar(45) NOT NULL,
  `description` varchar(200) NOT NULL,
  `rating` float DEFAULT NULL,
  `duration` int(11) NOT NULL,
  `isActive` int(11) NOT NULL,
  `uuid` varchar(45) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `movie`
--

LOCK TABLES `movie` WRITE;
/*!40000 ALTER TABLE `movie` DISABLE KEYS */;
INSERT INTO `movie` VALUES (1,'Inception','Drama','Leonardo DiCaprio',9,140,0,''),(2,'Minions','Comedy','funnnnyyyy',9.5,120,1,'');
/*!40000 ALTER TABLE `movie` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `reservation`
--

DROP TABLE IF EXISTS `reservation`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `reservation` (
  `id` bigint(30) NOT NULL AUTO_INCREMENT,
  `idUser` bigint(30) DEFAULT NULL,
  `state` enum('UNCONFIRMED','CONFIRMED','PRINTED') NOT NULL,
  `email` varchar(45) NOT NULL,
  `idSchedule` bigint(30) NOT NULL,
  `uuid` varchar(45) NOT NULL,
  PRIMARY KEY (`id`),
  KEY `idUser_idx` (`idUser`),
  KEY `idSchedule_idx` (`idSchedule`),
  CONSTRAINT `idSchedule` FOREIGN KEY (`idSchedule`) REFERENCES `schedule` (`id`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT `idUser` FOREIGN KEY (`idUser`) REFERENCES `user` (`id`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB AUTO_INCREMENT=7 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `reservation`
--

LOCK TABLES `reservation` WRITE;
/*!40000 ALTER TABLE `reservation` DISABLE KEYS */;
INSERT INTO `reservation` VALUES (1,1,'CONFIRMED','madarus',1,''),(2,2,'PRINTED','aaaa',2,''),(3,NULL,'UNCONFIRMED','aaa',1,''),(4,3,'PRINTED','ddd',1,'rere');
/*!40000 ALTER TABLE `reservation` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `room`
--

DROP TABLE IF EXISTS `room`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `room` (
  `id` bigint(30) NOT NULL AUTO_INCREMENT,
  `name` varchar(45) NOT NULL,
  `noRow` int(11) NOT NULL,
  `noColumn` int(11) NOT NULL,
  `uuid` varchar(45) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `room`
--

LOCK TABLES `room` WRITE;
/*!40000 ALTER TABLE `room` DISABLE KEYS */;
INSERT INTO `room` VALUES (1,'S1',10,10,''),(2,'S2',7,9,''),(3,'S3',11,10,''),(4,'S4',20,20,'');
/*!40000 ALTER TABLE `room` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `schedule`
--

DROP TABLE IF EXISTS `schedule`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `schedule` (
  `id` bigint(30) NOT NULL AUTO_INCREMENT,
  `idRoom` bigint(30) NOT NULL,
  `idMovie` bigint(30) NOT NULL,
  `broadcastDate` datetime NOT NULL,
  `limitDate` date NOT NULL,
  `price` float NOT NULL,
  `uuid` varchar(45) NOT NULL,
  PRIMARY KEY (`id`),
  KEY `idRoom_idx` (`idRoom`),
  KEY `idMovie_idx` (`idMovie`),
  CONSTRAINT `idMovie` FOREIGN KEY (`idMovie`) REFERENCES `movie` (`id`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT `idRoom` FOREIGN KEY (`idRoom`) REFERENCES `room` (`id`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `schedule`
--

LOCK TABLES `schedule` WRITE;
/*!40000 ALTER TABLE `schedule` DISABLE KEYS */;
INSERT INTO `schedule` VALUES (1,1,1,'2015-05-05 17:00:00','2015-08-05',15,''),(2,2,2,'2015-07-05 17:00:00','2015-12-05',20,'');
/*!40000 ALTER TABLE `schedule` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `seat`
--

DROP TABLE IF EXISTS `seat`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `seat` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `idReservation` bigint(30) NOT NULL,
  `seatrow` int(11) NOT NULL,
  `seatcolumn` int(11) NOT NULL,
  `uuid` varchar(45) NOT NULL,
  PRIMARY KEY (`id`),
  KEY `idReservation_idx` (`idReservation`),
  CONSTRAINT `idReservation` FOREIGN KEY (`idReservation`) REFERENCES `reservation` (`id`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB AUTO_INCREMENT=435 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `seat`
--

LOCK TABLES `seat` WRITE;
/*!40000 ALTER TABLE `seat` DISABLE KEYS */;
INSERT INTO `seat` VALUES (409,1,1,1,'12'),(410,2,1,2,'13'),(411,1,2,3,'563604e6-c1f2-45a0-b3f9-21db63cc8eb7'),(425,1,3,5,'9397bf4a-6be1-49ab-93ba-1c6255355aaf'),(426,1,3,6,'4b7903da-50a9-44ca-a594-647eab47b2bc'),(427,1,4,6,'82a952ca-682e-4396-ac9d-401df7a48282'),(428,1,1,6,'d5d84f3a-e77d-44ab-8360-793ca03678b9'),(429,1,2,6,'71035309-14d3-42c1-a694-47ec75e0b5d7'),(430,1,4,5,'e7260feb-a244-452d-9b22-ffd628604e70'),(431,1,7,7,'b3e92e9c-7783-45fb-a0c2-ecfa19f537d0'),(432,1,7,8,'3bd2fe15-3f22-4ff5-af36-650e15f4e0e2'),(433,1,3,7,'cb3eaa14-851a-41a7-9afa-ed6440c863a5'),(434,1,4,7,'6545d893-5cd2-4f89-be2a-976671026f6b');
/*!40000 ALTER TABLE `seat` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `user`
--

DROP TABLE IF EXISTS `user`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `user` (
  `id` bigint(30) NOT NULL AUTO_INCREMENT,
  `firstName` varchar(45) NOT NULL,
  `lastName` varchar(45) NOT NULL,
  `username` varchar(45) NOT NULL,
  `password` varchar(45) NOT NULL,
  `userType` enum('VIEWER','CLIENT','CASHIER','MANAGER','ADMIN') NOT NULL,
  `email` varchar(45) NOT NULL,
  `address` varchar(45) DEFAULT NULL,
  `phoneNumber` varchar(45) DEFAULT NULL,
  `uuid` varchar(45) DEFAULT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `username_UNIQUE` (`username`)
) ENGINE=InnoDB AUTO_INCREMENT=10 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `user`
--

LOCK TABLES `user` WRITE;
/*!40000 ALTER TABLE `user` DISABLE KEYS */;
INSERT INTO `user` VALUES (1,'Madalina','Rus','madarus','mada','CLIENT','madarus','cluj','00000',NULL),(2,'Andrei','Chelu','achelu','andrei','VIEWER','andrei','cluj','00000',NULL),(3,'Lorand','Vaida','lorand','lorand','ADMIN','lorand','clij','00000',NULL),(4,'Noemi','Gaspar','gasparn','noemi','MANAGER','noemi','cluj','0000',NULL),(5,'Vlad','Blaj','vlad','vlad','CASHIER','vlad','cluj','00000',NULL),(6,'Ovidiu','Pop','ovidiu','ovidiu','CLIENT','ovidiu','cluj','00000',NULL),(7,'root','root','root','†�ö³Ø�\"º2¼]=Gà#îþÛ','ADMIN','vlad','ared','123','f4d724f8-fe1e-4df4-a134-c217dfd26258'),(9,'test','test','marcoblaj','†�ö³Ø�\"º2¼]=Gà#îþÛ','CLIENT','vlad','ffff','123','155bc3dd-924f-4da2-8d09-3aecdcdc342a');
/*!40000 ALTER TABLE `user` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2015-09-12 13:28:40
