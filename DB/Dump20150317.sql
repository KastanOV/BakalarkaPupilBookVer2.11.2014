-- MySQL dump 10.13  Distrib 5.6.17, for Win64 (x86_64)
--
-- Host: 192.168.56.102    Database: pupilbook
-- ------------------------------------------------------
-- Server version	5.6.19-0ubuntu0.14.04.1

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
-- Table structure for table `attendance`
--

DROP TABLE IF EXISTS `attendance`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `attendance` (
  `idAttendance` int(11) NOT NULL AUTO_INCREMENT,
  `MissingStart` datetime NOT NULL,
  `MissingEnd` datetime DEFAULT NULL,
  `Excussed` tinyint(1) NOT NULL,
  `Users_Login` varchar(255) NOT NULL,
  PRIMARY KEY (`idAttendance`),
  KEY `fk_Attendance_Users1_idx` (`Users_Login`),
  CONSTRAINT `fk_Attendance_Users1` FOREIGN KEY (`Users_Login`) REFERENCES `users` (`Login`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB AUTO_INCREMENT=509 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `attendance`
--

LOCK TABLES `attendance` WRITE;
/*!40000 ALTER TABLE `attendance` DISABLE KEYS */;
INSERT INTO `attendance` VALUES (494,'2015-03-03 21:27:50',NULL,1,'BUK000'),(495,'2015-03-03 21:29:29','2015-03-03 21:30:29',1,'BUK000'),(496,'2015-03-03 21:31:05','2015-03-03 21:43:21',0,'BUK000'),(497,'2015-03-03 21:31:13','2015-03-03 21:37:18',1,'HAM000'),(498,'2015-03-03 21:31:21',NULL,0,'HEJ000'),(499,'2015-03-04 12:49:04','2015-03-04 12:49:09',0,'KAS000'),(500,'2015-03-04 12:49:08',NULL,1,'KAS000'),(501,'2015-03-04 12:49:16',NULL,0,'KAS000'),(502,'2015-03-04 12:49:17',NULL,0,'KAS000'),(503,'2015-03-07 09:34:50',NULL,1,'HAM000'),(504,'2015-03-07 13:09:06',NULL,1,'HAM000'),(505,'2015-03-07 13:21:27',NULL,1,'HAM000'),(506,'2015-03-07 19:00:47',NULL,1,'HAM000'),(507,'2015-03-07 19:19:33',NULL,1,'HAM000'),(508,'2015-03-07 19:23:52',NULL,1,'HAM000');
/*!40000 ALTER TABLE `attendance` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `informations`
--

DROP TABLE IF EXISTS `informations`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `informations` (
  `idinformations` int(11) NOT NULL AUTO_INCREMENT,
  `Description` varchar(100) NOT NULL,
  `InfoForParrents` tinyint(1) NOT NULL,
  `SomeMessage` varchar(2000) NOT NULL,
  `CreateDate` datetime NOT NULL,
  `StudyGroup_idStudyGroup` int(11) DEFAULT NULL,
  `Users_Login` varchar(255) DEFAULT NULL,
  `Teacher_Login` varchar(255) NOT NULL,
  PRIMARY KEY (`idinformations`),
  KEY `fk_informations_StudyGroup1_idx` (`StudyGroup_idStudyGroup`),
  KEY `fk_informations_Users1_idx` (`Users_Login`),
  KEY `Teacher_Login` (`Teacher_Login`),
  CONSTRAINT `fk_informations_StudyGroup1` FOREIGN KEY (`StudyGroup_idStudyGroup`) REFERENCES `studygroup` (`idStudyGroup`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT `fk_informations_Users1` FOREIGN KEY (`Users_Login`) REFERENCES `users` (`Login`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT `informations_ibfk_1` FOREIGN KEY (`Teacher_Login`) REFERENCES `users` (`Login`)
) ENGINE=InnoDB AUTO_INCREMENT=9 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `informations`
--

LOCK TABLES `informations` WRITE;
/*!40000 ALTER TABLE `informations` DISABLE KEYS */;
INSERT INTO `informations` VALUES (4,'Lorem ipsum',0,'Lorem ipsum dolor sit amet, consectetur adipiscing elit, sed do eiusmod tempor incididunt ut labore et dolore magna aliqua. Ut enim ad minim veniam, quis nostrud exercitation ullamco laboris nisi ut aliquip ex ea commodo consequat. Duis aute irure dolor in reprehenderit in voluptate velit esse cillum dolore eu fugiat nulla pariatur. Excepteur sint occaecat cupidatat non proident, sunt in culpa qui officia deserunt mollit anim id est laborum','2015-01-24 12:45:07',1,NULL,'CHL000'),(5,'Poznámka',1,'Ten kokot zase dělal bordel','2015-01-24 12:50:03',NULL,'HOL000','CHL000'),(6,'lorenc',1,'Lorem ipsum dolor sit amet, consectetur adipiscing elit, sed do eiusmod tempor incididunt ut labore et dolore magna aliqua. Ut enim ad minim veniam, quis nostrud exercitation ullamco laboris nisi ut aliquip ex ea commodo consequat. Duis aute irure dolor in reprehenderit in voluptate velit esse cillum dolore eu fugiat nulla pariatur. Excepteur sint occaecat cupidatat non proident, sunt in culpa qui officia deserunt mollit anim id est laborum','2015-01-24 12:57:58',NULL,NULL,'JIR000'),(7,'Test rest serv',0,'blablabla','2015-02-08 16:32:08',NULL,'HOL000','CHL000'),(8,'Poznámka',0,'Nedělej už bordel','2015-02-20 09:27:17',NULL,'KAS000','CHL000');
/*!40000 ALTER TABLE `informations` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `parrentstudent`
--

DROP TABLE IF EXISTS `parrentstudent`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `parrentstudent` (
  `idParrentStudent` int(11) NOT NULL AUTO_INCREMENT,
  `Student_Login` varchar(255) NOT NULL,
  `Parent_Login` varchar(255) NOT NULL,
  PRIMARY KEY (`idParrentStudent`),
  KEY `fk_ParrentStudent_Users1_idx` (`Student_Login`),
  KEY `fk_ParrentStudent_Users2_idx` (`Parent_Login`),
  CONSTRAINT `fk_ParrentStudent_Users1` FOREIGN KEY (`Student_Login`) REFERENCES `users` (`Login`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT `fk_ParrentStudent_Users2` FOREIGN KEY (`Parent_Login`) REFERENCES `users` (`Login`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB AUTO_INCREMENT=27 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `parrentstudent`
--

LOCK TABLES `parrentstudent` WRITE;
/*!40000 ALTER TABLE `parrentstudent` DISABLE KEYS */;
INSERT INTO `parrentstudent` VALUES (1,'ARG000','pARG000'),(6,'HEJ000','pHEJ000'),(7,'BUK000','pBUK000'),(8,'BUZ000','pBUZ000'),(9,'FON000','pFON000'),(10,'HAM000','pHAM000'),(11,'HOL000','pHOL000'),(12,'HOM000','pHOM000'),(13,'HOU000','pHOU000'),(14,'JAG000','pJAG000'),(15,'JEB000','pJEB000'),(16,'KAS000','pKAS000'),(17,'KAS001','pKAS001'),(18,'KAS003','pKAS003'),(19,'KLI000','pKLI000'),(20,'MAU000','pMAU000'),(21,'MUC000','pMUC000'),(22,'RAF000','pRAF000'),(23,'SUP000','pSUP000'),(24,'SUP001','pSUP001'),(25,'SNE000','pSNE000'),(26,'ZAP000','pZAP000');
/*!40000 ALTER TABLE `parrentstudent` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `results`
--

DROP TABLE IF EXISTS `results`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `results` (
  `idResults` int(11) NOT NULL AUTO_INCREMENT,
  `Description` varchar(255) DEFAULT NULL,
  `Score` tinyint(4) NOT NULL,
  `Date` datetime DEFAULT NULL,
  `Teacher_Login` varchar(255) NOT NULL,
  `Student_Login` varchar(255) NOT NULL,
  `StudySubject_idStudySubject` int(11) NOT NULL,
  `SchoolYear_idSchoolYear` int(11) NOT NULL,
  PRIMARY KEY (`idResults`),
  KEY `fk_Results_Users1_idx` (`Teacher_Login`),
  KEY `fk_Results_Users2_idx` (`Student_Login`),
  KEY `fk_Results_StudySubject1_idx` (`StudySubject_idStudySubject`),
  KEY `fk_Results_SchoolYear1_idx` (`SchoolYear_idSchoolYear`),
  CONSTRAINT `fk_Results_SchoolYear1` FOREIGN KEY (`SchoolYear_idSchoolYear`) REFERENCES `schoolyear` (`idSchoolYear`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT `fk_Results_StudySubject1` FOREIGN KEY (`StudySubject_idStudySubject`) REFERENCES `studysubject` (`idStudySubject`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT `fk_Results_Users1` FOREIGN KEY (`Teacher_Login`) REFERENCES `users` (`Login`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT `fk_Results_Users2` FOREIGN KEY (`Student_Login`) REFERENCES `users` (`Login`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB AUTO_INCREMENT=165 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `results`
--

LOCK TABLES `results` WRITE;
/*!40000 ALTER TABLE `results` DISABLE KEYS */;
INSERT INTO `results` VALUES (39,'Diktat',0,'2014-12-08 00:00:00','JIR000','KAS000',2,2),(40,'jirasrj',9,'2014-12-08 00:00:00','JIR000','KAS000',2,2),(41,'vgggc',5,'2014-12-08 00:00:00','JIR000','KAS000',2,2),(42,'Jirásek test',5,'2014-12-08 00:00:00','JIR000','KAS000',2,2),(43,'Trojuhelník',6,'2014-12-08 00:00:00','CHL000','KAS000',1,2),(48,'fhhcg',3,'2014-12-09 00:00:00','CHL000','KAS000',4,2),(50,'Zlomky Sčítání',6,'2014-12-11 21:55:50','CHL000','BUZ000',1,2),(51,'Zlomky Násobení',10,'2014-12-11 21:56:49','CHL000','BUZ000',1,2),(52,'Trojuhelník rovnostranný',2,'2014-12-11 22:01:04','CHL000','BUZ000',1,2),(53,'Trojuhelník rovnostranný',5,'2014-12-11 22:01:16','CHL000','KAS001',1,2),(54,'Trojuhelník rovnostranný',4,'2014-12-11 22:01:22','CHL000','MUC000',1,2),(55,'Trojuhelník rovnostranný',8,'2014-12-11 22:01:26','CHL000','RAF000',1,2),(57,'čtyř uhelník',5,'2014-12-11 22:01:59','CHL000','BUZ000',1,2),(58,'čtyř uhelník',10,'2014-12-11 22:02:10','CHL000','KAS001',1,2),(59,'čtyř uhelník',6,'2014-12-11 22:02:26','CHL000','MUC000',1,2),(60,'čtyř uhelník',6,'2014-12-11 22:02:31','CHL000','RAF000',1,2),(61,'1',1,'2014-12-12 00:00:00','CHL000','HOL000',4,2),(69,'Zlomky',6,'2014-12-12 11:50:34','CHL000','KAS001',1,2),(70,'Zlomky',3,'2014-12-12 11:52:19','CHL000','BUZ000',1,2),(72,'8',8,'2014-12-12 00:00:00','CHL000','BUZ000',5,2),(73,'Zlomky',5,'2014-12-25 22:16:06','CHL000','BUK000',1,2),(74,'Zlomky',4,'2014-12-25 22:24:43','CHL000','SUP000',1,2),(75,'Zlomky',4,'2014-12-25 22:25:47','CHL000','KLI000',1,2),(76,'Zlomky',2,'2014-12-25 22:45:02','CHL000','HOM000',1,2),(79,'Skok přes kozu',9,'2014-12-25 22:54:38','CHL000','KAS001',5,2),(80,'Derivace',5,'2014-12-25 22:54:58','CHL000','KAS001',1,2),(81,'Bradla',8,'2014-12-25 22:55:47','CHL000','KAS001',5,2),(82,'Bradla',2,'2014-12-25 22:56:23','CHL000','HOL000',5,2),(83,'Bradla',10,'2014-12-25 22:56:34','CHL000','RAF000',5,2),(85,'skok přes hlavu',4,'2014-12-26 13:51:06','CHL000','KAS000',5,2),(86,'skok přes berušku',10,'2014-12-26 13:53:17','CHL000','KAS000',5,2),(102,'diktat',5,'2015-01-01 00:00:00','JES000','KAS001',2,2),(103,'Integrál',10,'2015-01-01 01:04:26','CHL000','KAS001',1,2),(104,'Komplexní čísla',3,'2015-01-01 08:59:37','CHL000','KAS001',1,2),(105,'derivace',7,'2015-01-01 00:00:00','CHL000','BUK000',1,2),(106,'integral',9,'2015-01-01 00:00:00','CHL000','BUK000',1,2),(107,'komplexní čísla',10,'2015-01-01 10:13:21','CHL000','BUK000',1,2),(108,'komplexní čísla 2',1,'2015-01-01 10:14:43','CHL000','BUK000',1,2),(110,'bradla',1,'2015-01-01 00:00:00','CHL000','BUK000',5,2),(111,'zlomky',8,'2015-01-13 00:00:00','CHL000','HAM000',1,2),(112,'diktát',10,'2015-01-16 00:00:00','JIR000','KAS000',2,2),(113,'ffdhgg',7,'2015-01-16 00:00:00','JIR000','KAS000',2,2),(114,'Stromy',8,'2015-01-18 17:13:50','CHL000','KAS000',6,2),(115,'Zlomky',10,'2015-01-18 21:22:40','CHL000','FON000',5,2),(116,'Zlomky Sčítání',10,'2015-01-19 11:00:57','CHL000','KAS000',1,2),(117,'Stromy',8,'2015-01-19 11:03:29','CHL000','HOL000',6,2),(118,'Trojuhelník rovnostranný',4,'2015-01-19 16:45:12','CHL000','SUP000',1,2),(119,'Trojuhelník rovnostranný',8,'2015-01-19 16:45:53','CHL000','HOU000',1,2),(120,'Trojuhelník rovnostranný',4,'2015-01-19 16:46:01','CHL000','SUP000',1,2),(121,'Trojuhelník',7,'2015-01-19 16:46:48','CHL000','FON000',1,2),(122,'Trojuhelník',2,'2015-01-19 16:46:53','CHL000','HOL000',1,2),(129,'Zlomky Sčítání',5,'2015-01-19 19:19:35','CHL000','KAS001',1,2),(130,'Zlomky Násobení',6,'2015-01-19 19:19:47','CHL000','KAS001',1,2),(131,'Zlomky Násobení',7,'2015-01-19 19:44:57','CHL000','KAS000',1,2),(133,'Stromy',5,'2015-01-19 20:22:14','CHL000','RAF000',6,2),(135,'Trojuhelník',1,'2015-01-20 13:01:48','CHL000','KAS001',1,2),(136,'Trojuhelník',7,'2015-01-24 00:15:13','CHL000','BUK000',1,2),(137,'Atom',8,'2015-01-24 11:33:07','CHL000','MUC000',4,2),(138,'Trojuhelník',10,'2015-02-07 23:42:31','CHL000','ARG000',1,2),(139,'Zlomky Sčítání',7,'2015-02-07 23:42:38','CHL000','ARG000',1,2),(140,'Trojuhelník rovnostranný',3,'2015-02-07 23:42:46','CHL000','ARG000',1,2),(141,'Zlomky Násobení',6,'2015-02-07 23:43:01','CHL000','ARG000',1,2),(142,'Derivace',3,'2015-02-07 23:43:08','CHL000','ARG000',1,2),(143,'Skok přes kozu',10,'2015-02-07 23:43:24','CHL000','ARG000',5,2),(144,'Stromy',6,'2015-02-07 23:43:42','CHL000','ARG000',6,2),(145,'Trojuhelník rovnostranný',10,'2015-02-20 09:26:56','CHL000','KAS000',1,2),(146,'Trojuhelník rovnostranný',3,'2015-02-20 09:39:20','CHL000','ARG000',2,2),(147,'Skok přes kozu',4,'2015-02-28 11:20:06','CHL000','KLI000',5,2),(148,'skok přes hlavu',9,'2015-02-28 11:21:57','CHL000','KAS001',5,2),(149,'skok přes berušku',1,'2015-02-28 11:22:48','CHL000','KAS001',5,2),(150,'hgfggg',6,'2015-03-03 00:00:00','CHL000','HAM000',1,2),(151,'Zlomky Sčítání',8,'2015-03-03 12:21:18','CHL000','HAM000',1,2),(152,'ggfzhg',3,'2015-03-03 00:00:00','CHL000','HAM000',1,2),(153,'hcfhgg',7,'2015-03-03 00:00:00','CHL000','HAM000',1,2),(154,'Zlomky Násobení',9,'2015-03-07 09:27:35','CHL000','HAM000',1,2),(155,'Trojuhelník',10,'2015-03-07 09:27:44','CHL000','HAM000',1,2),(156,'Trojuhelník rovnostranný',10,'2015-03-07 09:27:53','CHL000','HAM000',1,2),(157,'čtyř uhelník',10,'2015-03-07 09:28:00','CHL000','HAM000',1,2),(158,'Derivace',3,'2015-03-07 09:28:08','CHL000','HAM000',1,2),(159,'Integrál',6,'2015-03-07 09:28:15','CHL000','HAM000',1,2),(160,'Skok přes kozu',9,'2015-03-07 09:28:31','CHL000','HAM000',5,2),(161,'Bradla',6,'2015-03-07 09:28:39','CHL000','HAM000',5,2),(162,'skok přes hlavu',10,'2015-03-07 09:28:47','CHL000','HAM000',5,2),(163,'Diktát',3,'2015-03-07 09:29:04','CHL000','HAM000',2,2),(164,'Nauka  stromech',8,'2015-03-07 09:29:27','CHL000','HAM000',6,2);
/*!40000 ALTER TABLE `results` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `schoolyear`
--

DROP TABLE IF EXISTS `schoolyear`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `schoolyear` (
  `idSchoolYear` int(11) NOT NULL AUTO_INCREMENT,
  `name` varchar(20) NOT NULL,
  `isactualyear` tinyint(1) NOT NULL,
  `StartDate` date NOT NULL,
  `EndDate` date NOT NULL,
  PRIMARY KEY (`idSchoolYear`)
) ENGINE=InnoDB AUTO_INCREMENT=7 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `schoolyear`
--

LOCK TABLES `schoolyear` WRITE;
/*!40000 ALTER TABLE `schoolyear` DISABLE KEYS */;
INSERT INTO `schoolyear` VALUES (1,'2012/2013',0,'2012-09-01','2013-06-30'),(2,'2013/2014',1,'2013-09-01','2014-06-12'),(3,'2014/2015',0,'2015-08-01','2016-06-30'),(4,'2015/2016',0,'2015-10-01','2016-05-18'),(5,'2018/2019',0,'2015-01-13','2015-01-27'),(6,'1862/5236',0,'2015-02-11','2015-02-28');
/*!40000 ALTER TABLE `schoolyear` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `sheduleitem`
--

DROP TABLE IF EXISTS `sheduleitem`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `sheduleitem` (
  `idSheduleItem` int(11) NOT NULL AUTO_INCREMENT,
  `day` tinyint(3) NOT NULL,
  `hour` tinyint(3) NOT NULL,
  `StudyGroup_idStudyGroup` int(11) NOT NULL,
  `Users_Login` varchar(255) DEFAULT NULL,
  `StudySubject_idStudySubject` int(11) DEFAULT NULL,
  PRIMARY KEY (`idSheduleItem`),
  KEY `fk_SheduleItem_StudyGroup1_idx` (`StudyGroup_idStudyGroup`),
  KEY `fk_SheduleItem_Users1_idx` (`Users_Login`),
  KEY `fk_SheduleItem_StudySubject1_idx` (`StudySubject_idStudySubject`),
  CONSTRAINT `fk_SheduleItem_StudyGroup1` FOREIGN KEY (`StudyGroup_idStudyGroup`) REFERENCES `studygroup` (`idStudyGroup`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT `fk_SheduleItem_StudySubject1` FOREIGN KEY (`StudySubject_idStudySubject`) REFERENCES `studysubject` (`idStudySubject`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT `fk_SheduleItem_Users1` FOREIGN KEY (`Users_Login`) REFERENCES `users` (`Login`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB AUTO_INCREMENT=441 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `sheduleitem`
--

LOCK TABLES `sheduleitem` WRITE;
/*!40000 ALTER TABLE `sheduleitem` DISABLE KEYS */;
INSERT INTO `sheduleitem` VALUES (1,0,0,1,'CHL000',1),(2,1,0,1,'CHL000',1),(3,2,0,1,'CHL000',5),(4,3,0,1,'CHL000',1),(5,4,0,1,'CHL000',1),(6,0,1,1,'JIR000',2),(7,1,1,1,'JIR000',2),(8,2,1,1,'JIR000',2),(9,3,1,1,'JIR000',2),(10,4,1,1,'JIR000',2),(11,0,2,1,'VEL000',4),(12,1,2,1,'VEL000',4),(13,2,2,1,'VEL000',6),(14,3,2,1,'VEL000',6),(15,4,2,1,'VEL000',6),(16,0,3,1,'FIC000',1),(17,1,3,1,'CHL000',2),(18,2,3,1,'CHL000',6),(19,3,3,1,NULL,NULL),(20,4,3,1,'CHL000',1),(21,0,4,1,'FIC000',2),(22,1,4,1,NULL,NULL),(23,2,4,1,NULL,NULL),(24,3,4,1,NULL,NULL),(25,4,4,1,NULL,NULL),(26,0,5,1,NULL,NULL),(27,1,5,1,NULL,NULL),(28,2,5,1,NULL,NULL),(29,3,5,1,NULL,NULL),(30,4,5,1,NULL,NULL),(31,0,6,1,NULL,NULL),(32,1,6,1,NULL,NULL),(33,2,6,1,NULL,NULL),(34,3,6,1,NULL,NULL),(35,4,6,1,NULL,NULL),(36,0,7,1,NULL,NULL),(37,1,7,1,NULL,NULL),(38,2,7,1,NULL,NULL),(39,3,7,1,NULL,NULL),(40,4,7,1,NULL,NULL),(61,0,0,2,'JIR000',2),(62,1,0,2,'JES000',2),(63,2,0,2,'JIR000',2),(64,3,0,2,'JIR000',2),(65,4,0,2,'JIR000',2),(66,0,1,2,'VEL000',6),(67,1,1,2,'VEL000',6),(68,2,1,2,'VEL000',6),(69,3,1,2,'VEL000',5),(70,4,1,2,'VEL000',5),(71,0,2,2,'CHL000',5),(72,1,2,2,'JES000',2),(73,2,2,2,'CHL000',4),(74,3,2,2,'CHL000',1),(75,4,2,2,'CHL000',1),(76,0,3,2,'JIR000',2),(77,1,3,2,'JES000',6),(78,2,3,2,'JES000',4),(79,3,3,2,'JES000',2),(80,4,3,2,'JAC000',2),(81,0,4,2,'JIR000',2),(82,1,4,2,NULL,NULL),(83,2,4,2,NULL,NULL),(84,3,4,2,NULL,NULL),(85,4,4,2,'CHL000',6),(86,0,5,2,NULL,NULL),(87,1,5,2,NULL,NULL),(88,2,5,2,NULL,NULL),(89,3,5,2,NULL,NULL),(90,4,5,2,NULL,NULL),(91,0,6,2,NULL,NULL),(92,1,6,2,NULL,NULL),(93,2,6,2,NULL,NULL),(94,3,6,2,NULL,NULL),(95,4,6,2,NULL,NULL),(96,0,7,2,NULL,NULL),(97,1,7,2,NULL,NULL),(98,2,7,2,NULL,NULL),(99,3,7,2,NULL,NULL),(100,4,7,2,NULL,NULL),(161,0,0,4,NULL,NULL),(162,1,0,4,NULL,NULL),(163,2,0,4,'KES000',1),(164,3,0,4,NULL,NULL),(165,4,0,4,NULL,NULL),(166,0,1,4,NULL,NULL),(167,1,1,4,NULL,NULL),(168,2,1,4,'JIR000',2),(169,3,1,4,NULL,NULL),(170,4,1,4,NULL,NULL),(171,0,2,4,NULL,NULL),(172,1,2,4,'FIC000',2),(173,2,2,4,NULL,NULL),(174,3,2,4,NULL,NULL),(175,4,2,4,NULL,NULL),(176,0,3,4,NULL,NULL),(177,1,3,4,NULL,NULL),(178,2,3,4,NULL,NULL),(179,3,3,4,NULL,NULL),(180,4,3,4,NULL,NULL),(181,0,4,4,NULL,NULL),(182,1,4,4,NULL,NULL),(183,2,4,4,NULL,NULL),(184,3,4,4,'CHL000',2),(185,4,4,4,NULL,NULL),(186,0,5,4,NULL,NULL),(187,1,5,4,NULL,NULL),(188,2,5,4,NULL,NULL),(189,3,5,4,NULL,NULL),(190,4,5,4,NULL,NULL),(191,0,6,4,NULL,NULL),(192,1,6,4,NULL,NULL),(193,2,6,4,NULL,NULL),(194,3,6,4,NULL,NULL),(195,4,6,4,NULL,NULL),(196,0,7,4,NULL,NULL),(197,1,7,4,NULL,NULL),(198,2,7,4,NULL,NULL),(199,3,7,4,NULL,NULL),(200,4,7,4,NULL,NULL),(201,0,0,5,NULL,NULL),(202,1,0,5,'KES000',2),(203,2,0,5,NULL,NULL),(204,3,0,5,NULL,NULL),(205,4,0,5,NULL,NULL),(206,0,1,5,NULL,NULL),(207,1,1,5,'FIC000',3),(208,2,1,5,NULL,NULL),(209,3,1,5,NULL,NULL),(210,4,1,5,NULL,NULL),(211,0,2,5,NULL,NULL),(212,1,2,5,'JIR000',4),(213,2,2,5,NULL,NULL),(214,3,2,5,NULL,NULL),(215,4,2,5,NULL,NULL),(216,0,3,5,NULL,NULL),(217,1,3,5,NULL,NULL),(218,2,3,5,NULL,NULL),(219,3,3,5,NULL,NULL),(220,4,3,5,NULL,NULL),(221,0,4,5,NULL,NULL),(222,1,4,5,NULL,NULL),(223,2,4,5,NULL,NULL),(224,3,4,5,NULL,NULL),(225,4,4,5,NULL,NULL),(226,0,5,5,NULL,NULL),(227,1,5,5,NULL,NULL),(228,2,5,5,NULL,NULL),(229,3,5,5,NULL,NULL),(230,4,5,5,NULL,NULL),(231,0,6,5,NULL,NULL),(232,1,6,5,NULL,NULL),(233,2,6,5,NULL,NULL),(234,3,6,5,NULL,NULL),(235,4,6,5,NULL,NULL),(236,0,7,5,NULL,NULL),(237,1,7,5,NULL,NULL),(238,2,7,5,NULL,NULL),(239,3,7,5,NULL,NULL),(240,4,7,5,NULL,NULL),(241,0,0,6,NULL,NULL),(242,1,0,6,NULL,NULL),(243,2,0,6,NULL,NULL),(244,3,0,6,NULL,NULL),(245,4,0,6,NULL,NULL),(246,0,1,6,NULL,NULL),(247,1,1,6,NULL,NULL),(248,2,1,6,NULL,NULL),(249,3,1,6,NULL,NULL),(250,4,1,6,NULL,NULL),(251,0,2,6,NULL,NULL),(252,1,2,6,NULL,NULL),(253,2,2,6,NULL,NULL),(254,3,2,6,NULL,NULL),(255,4,2,6,NULL,NULL),(256,0,3,6,NULL,NULL),(257,1,3,6,NULL,NULL),(258,2,3,6,NULL,NULL),(259,3,3,6,NULL,NULL),(260,4,3,6,NULL,NULL),(261,0,4,6,NULL,NULL),(262,1,4,6,NULL,NULL),(263,2,4,6,NULL,NULL),(264,3,4,6,NULL,NULL),(265,4,4,6,NULL,NULL),(266,0,5,6,NULL,NULL),(267,1,5,6,NULL,NULL),(268,2,5,6,NULL,NULL),(269,3,5,6,NULL,NULL),(270,4,5,6,NULL,NULL),(271,0,6,6,NULL,NULL),(272,1,6,6,NULL,NULL),(273,2,6,6,NULL,NULL),(274,3,6,6,NULL,NULL),(275,4,6,6,NULL,NULL),(276,0,7,6,NULL,NULL),(277,1,7,6,NULL,NULL),(278,2,7,6,NULL,NULL),(279,3,7,6,NULL,NULL),(280,4,7,6,NULL,NULL),(321,0,0,8,NULL,NULL),(322,1,0,8,NULL,NULL),(323,2,0,8,NULL,NULL),(324,3,0,8,NULL,NULL),(325,4,0,8,NULL,NULL),(326,0,1,8,NULL,NULL),(327,1,1,8,NULL,NULL),(328,2,1,8,NULL,NULL),(329,3,1,8,NULL,NULL),(330,4,1,8,NULL,NULL),(331,0,2,8,NULL,NULL),(332,1,2,8,NULL,NULL),(333,2,2,8,NULL,NULL),(334,3,2,8,NULL,NULL),(335,4,2,8,NULL,NULL),(336,0,3,8,NULL,NULL),(337,1,3,8,NULL,NULL),(338,2,3,8,NULL,NULL),(339,3,3,8,NULL,NULL),(340,4,3,8,NULL,NULL),(341,0,4,8,NULL,NULL),(342,1,4,8,NULL,NULL),(343,2,4,8,NULL,NULL),(344,3,4,8,NULL,NULL),(345,4,4,8,NULL,NULL),(346,0,5,8,NULL,NULL),(347,1,5,8,NULL,NULL),(348,2,5,8,NULL,NULL),(349,3,5,8,NULL,NULL),(350,4,5,8,NULL,NULL),(351,0,6,8,NULL,NULL),(352,1,6,8,NULL,NULL),(353,2,6,8,NULL,NULL),(354,3,6,8,NULL,NULL),(355,4,6,8,NULL,NULL),(356,0,7,8,NULL,NULL),(357,1,7,8,NULL,NULL),(358,2,7,8,NULL,NULL),(359,3,7,8,NULL,NULL),(360,4,7,8,NULL,NULL),(361,0,0,9,NULL,NULL),(362,1,0,9,NULL,NULL),(363,2,0,9,NULL,NULL),(364,3,0,9,NULL,NULL),(365,4,0,9,NULL,NULL),(366,0,1,9,NULL,NULL),(367,1,1,9,NULL,NULL),(368,2,1,9,NULL,NULL),(369,3,1,9,NULL,NULL),(370,4,1,9,NULL,NULL),(371,0,2,9,NULL,NULL),(372,1,2,9,NULL,NULL),(373,2,2,9,NULL,NULL),(374,3,2,9,NULL,NULL),(375,4,2,9,NULL,NULL),(376,0,3,9,NULL,NULL),(377,1,3,9,NULL,NULL),(378,2,3,9,NULL,NULL),(379,3,3,9,NULL,NULL),(380,4,3,9,NULL,NULL),(381,0,4,9,NULL,NULL),(382,1,4,9,NULL,NULL),(383,2,4,9,NULL,NULL),(384,3,4,9,NULL,NULL),(385,4,4,9,NULL,NULL),(386,0,5,9,NULL,NULL),(387,1,5,9,NULL,NULL),(388,2,5,9,NULL,NULL),(389,3,5,9,NULL,NULL),(390,4,5,9,NULL,NULL),(391,0,6,9,NULL,NULL),(392,1,6,9,NULL,NULL),(393,2,6,9,NULL,NULL),(394,3,6,9,NULL,NULL),(395,4,6,9,NULL,NULL),(396,0,7,9,NULL,NULL),(397,1,7,9,NULL,NULL),(398,2,7,9,NULL,NULL),(399,3,7,9,NULL,NULL),(400,4,7,9,NULL,NULL),(401,0,0,10,NULL,NULL),(402,1,0,10,NULL,NULL),(403,2,0,10,NULL,NULL),(404,3,0,10,NULL,NULL),(405,4,0,10,NULL,NULL),(406,0,1,10,NULL,NULL),(407,1,1,10,NULL,NULL),(408,2,1,10,NULL,NULL),(409,3,1,10,NULL,NULL),(410,4,1,10,NULL,NULL),(411,0,2,10,NULL,NULL),(412,1,2,10,NULL,NULL),(413,2,2,10,NULL,NULL),(414,3,2,10,NULL,NULL),(415,4,2,10,NULL,NULL),(416,0,3,10,NULL,NULL),(417,1,3,10,NULL,NULL),(418,2,3,10,NULL,NULL),(419,3,3,10,NULL,NULL),(420,4,3,10,NULL,NULL),(421,0,4,10,NULL,NULL),(422,1,4,10,NULL,NULL),(423,2,4,10,NULL,NULL),(424,3,4,10,NULL,NULL),(425,4,4,10,NULL,NULL),(426,0,5,10,NULL,NULL),(427,1,5,10,NULL,NULL),(428,2,5,10,NULL,NULL),(429,3,5,10,NULL,NULL),(430,4,5,10,NULL,NULL),(431,0,6,10,NULL,NULL),(432,1,6,10,NULL,NULL),(433,2,6,10,NULL,NULL),(434,3,6,10,NULL,NULL),(435,4,6,10,NULL,NULL),(436,0,7,10,NULL,NULL),(437,1,7,10,NULL,NULL),(438,2,7,10,NULL,NULL),(439,3,7,10,NULL,NULL),(440,4,7,10,NULL,NULL);
/*!40000 ALTER TABLE `sheduleitem` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `studygroup`
--

DROP TABLE IF EXISTS `studygroup`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `studygroup` (
  `idStudyGroup` int(11) NOT NULL AUTO_INCREMENT,
  `Name` varchar(45) NOT NULL,
  `SchoolYear_idSchoolYear` int(11) NOT NULL,
  PRIMARY KEY (`idStudyGroup`),
  KEY `fk_StudyGroup_SchoolYear_idx` (`SchoolYear_idSchoolYear`),
  CONSTRAINT `fk_StudyGroup_SchoolYear` FOREIGN KEY (`SchoolYear_idSchoolYear`) REFERENCES `schoolyear` (`idSchoolYear`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB AUTO_INCREMENT=11 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `studygroup`
--

LOCK TABLES `studygroup` WRITE;
/*!40000 ALTER TABLE `studygroup` DISABLE KEYS */;
INSERT INTO `studygroup` VALUES (1,'1.A',2),(2,'1.B',2),(4,'5.C',2),(5,'3.C - 3.D',2),(6,'5.F',2),(8,'1.A',1),(9,'2.A',1),(10,'1.C',2);
/*!40000 ALTER TABLE `studygroup` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `studysubject`
--

DROP TABLE IF EXISTS `studysubject`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `studysubject` (
  `idStudySubject` int(11) NOT NULL AUTO_INCREMENT,
  `Name` varchar(45) NOT NULL,
  `ShortName` varchar(5) NOT NULL,
  PRIMARY KEY (`idStudySubject`)
) ENGINE=InnoDB AUTO_INCREMENT=12 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `studysubject`
--

LOCK TABLES `studysubject` WRITE;
/*!40000 ALTER TABLE `studysubject` DISABLE KEYS */;
INSERT INTO `studysubject` VALUES (1,'Matematika','M'),(2,'Český jazyk','ČJ'),(3,'Dějepis','D'),(4,'Fyzika','F'),(5,'Tělocvik','T'),(6,'Přírodověda','Př'),(7,'Občanská Nauka','ON'),(8,'Počítače','Poč'),(9,'Vývoj Internetových Aplikací','VIS'),(10,'Informační systémy pro elektronické podnikání','ISEP'),(11,'Počítačové sítě','POS');
/*!40000 ALTER TABLE `studysubject` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `teacher_subjects`
--

DROP TABLE IF EXISTS `teacher_subjects`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `teacher_subjects` (
  `idTeacher_Subjects` int(11) NOT NULL,
  `Users_Login` varchar(255) NOT NULL,
  `StudySubject_idStudySubject` int(11) NOT NULL,
  PRIMARY KEY (`idTeacher_Subjects`),
  KEY `fk_Teacher_Subjects_Users1_idx` (`Users_Login`),
  KEY `fk_Teacher_Subjects_StudySubject1_idx` (`StudySubject_idStudySubject`),
  CONSTRAINT `fk_Teacher_Subjects_StudySubject1` FOREIGN KEY (`StudySubject_idStudySubject`) REFERENCES `studysubject` (`idStudySubject`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT `fk_Teacher_Subjects_Users1` FOREIGN KEY (`Users_Login`) REFERENCES `users` (`Login`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `teacher_subjects`
--

LOCK TABLES `teacher_subjects` WRITE;
/*!40000 ALTER TABLE `teacher_subjects` DISABLE KEYS */;
/*!40000 ALTER TABLE `teacher_subjects` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `users`
--

DROP TABLE IF EXISTS `users`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `users` (
  `FirstName` varchar(45) NOT NULL,
  `MiddleName` varchar(45) DEFAULT NULL,
  `LastName` varchar(45) NOT NULL,
  `Phone` varchar(15) DEFAULT NULL,
  `Email` varchar(100) DEFAULT NULL,
  `Login` varchar(255) NOT NULL,
  `Password` varchar(255) NOT NULL,
  `BirthDate` date DEFAULT NULL,
  `StudyGroup_idStudyGroup` int(11) DEFAULT NULL,
  `Role` char(1) NOT NULL,
  `deleted` tinyint(1) DEFAULT NULL,
  PRIMARY KEY (`Login`),
  UNIQUE KEY `Login_UNIQUE` (`Login`),
  KEY `fk_Users_StudyGroup1_idx` (`StudyGroup_idStudyGroup`),
  CONSTRAINT `fk_Users_StudyGroup1` FOREIGN KEY (`StudyGroup_idStudyGroup`) REFERENCES `studygroup` (`idStudyGroup`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `users`
--

LOCK TABLES `users` WRITE;
/*!40000 ALTER TABLE `users` DISABLE KEYS */;
INSERT INTO `users` VALUES ('Josef','','Argus','','','ARG000','9dd4e461268c8034f5c8564e155c67a6',NULL,2,'S',0),('Karelos','','Bukvice','','karel.bukvice@seznam.cz','BUK000','9dd4e461268c8034f5c8564e155c67a6','2011-01-22',1,'S',0),('Ondřej','Buznovic','Buzz','739569856','OndrejB@gmail.com','BUZ000','9dd4e461268c8034f5c8564e155c67a6','2012-11-03',8,'S',0),('Jakub','','Chlupatos','','','CHL000','9dd4e461268c8034f5c8564e155c67a6',NULL,NULL,'T',0),('Venca','','Ficher','','','FIC000','9dd4e461268c8034f5c8564e155c67a6',NULL,1,'T',0),('Karel','','Fontána','','','FON000','9dd4e461268c8034f5c8564e155c67a6','2000-09-10',2,'S',0),('Božena','','Hamerská','','','HAM000','9dd4e461268c8034f5c8564e155c67a6','2009-01-22',1,'S',0),('Arnošt','','Hejhouba','','','HEJ000','9dd4e461268c8034f5c8564e155c67a6','2000-02-11',1,'S',0),('Adolf','','Holohlav','','','HOL000','9dd4e461268c8034f5c8564e155c67a6','2010-08-11',10,'S',0),('Evzen','','Homoklada','','test@klada.ho','HOM000','9dd4e461268c8034f5c8564e155c67a6','2012-03-20',1,'S',0),('Eduard','','Houžvička','','','HOU000','9dd4e461268c8034f5c8564e155c67a6','2003-01-15',4,'S',0),('Euro','','Jack','','','JAC000','9dd4e461268c8034f5c8564e155c67a6',NULL,NULL,'T',0),('Jaromír','','Jágr','','','JAG000','9dd4e461268c8034f5c8564e155c67a6','2002-01-16',4,'S',0),('John','','Jebal','','','JEB000','9dd4e461268c8034f5c8564e155c67a6',NULL,NULL,'S',0),('Adéla','','Ještěnevečeřela','','','JES000','9dd4e461268c8034f5c8564e155c67a6',NULL,4,'T',0),('Aloiz','','Jirásek','','','JIR000','9dd4e461268c8034f5c8564e155c67a6','1982-01-06',NULL,'T',0),('Jaroslav','','Kaštura','','','KAS000','9dd4e461268c8034f5c8564e155c67a6',NULL,1,'S',0),('Anita','','Kašturová','','','KAS001','9dd4e461268c8034f5c8564e155c67a6',NULL,2,'S',0),('Renátka','','Kašturová','','','KAS003','9dd4e461268c8034f5c8564e155c67a6',NULL,2,'S',0),('Jaroslav','Kaštan','Kaštura','739247129','kastura.jaroslav@gmail.com','KAS0110','9dd4e461268c8034f5c8564e155c67a6',NULL,NULL,'A',0),('Boris','','Keselý','','','KES000','9dd4e461268c8034f5c8564e155c67a6',NULL,6,'T',0),('Arnošt ','','Klika','','','KLI000','9dd4e461268c8034f5c8564e155c67a6',NULL,2,'S',0),('Adam','','Mauer','','','MAU000','9dd4e461268c8034f5c8564e155c67a6',NULL,1,'S',0),('Alfons','','Mucha','','','MUC000','9dd4e461268c8034f5c8564e155c67a6',NULL,2,'S',0),('Josef','','Argus','','','pARG000','9dd4e461268c8034f5c8564e155c67a6',NULL,1,'P',0),('Karelos','','Bukvice','','karel.bukvice@seznam.cz','pBUK000','9dd4e461268c8034f5c8564e155c67a6','2011-01-22',NULL,'P',0),('Ondřej','Buznovic','Buzz','739569856','OndrejB@gmail.com','pBUZ000','9dd4e461268c8034f5c8564e155c67a6','2012-11-03',NULL,'P',0),('Karel','','Fontána','','','pFON000','9dd4e461268c8034f5c8564e155c67a6','2000-09-10',NULL,'P',0),('Božena','','Hamerská','','','pHAM000','9dd4e461268c8034f5c8564e155c67a6','2009-01-22',1,'P',0),('Arnošt','','Hejhouba','','','pHEJ000','9dd4e461268c8034f5c8564e155c67a6','2000-02-11',NULL,'P',0),('Adolf','','Holohlav','','','pHOL000','9dd4e461268c8034f5c8564e155c67a6','2010-08-11',NULL,'P',0),('Evzen','','Homoklada','','test@klada.ho','pHOM000','9dd4e461268c8034f5c8564e155c67a6','2012-03-20',NULL,'P',0),('Eduard','','Houžvička','','','pHOU000','9dd4e461268c8034f5c8564e155c67a6','2003-01-15',NULL,'P',0),('Jaromír','','Jágr','','','pJAG000','9dd4e461268c8034f5c8564e155c67a6','2002-01-16',NULL,'P',0),('John','','Jebal','','','pJEB000','9dd4e461268c8034f5c8564e155c67a6',NULL,NULL,'P',0),('Jaroslav','','Kaštura','','','pKAS000','9dd4e461268c8034f5c8564e155c67a6',NULL,NULL,'P',0),('Anita','','Kašturová','','','pKAS001','e9fc7a9bd173a90aa288f597836ac38b',NULL,NULL,'P',0),('Renátka','','Kašturová','','','pKAS003','c5da8c9c24e483e5f0a559043dadaebb',NULL,NULL,'P',0),('Arnošt ','','Klika','','','pKLI000','057753b59a47a22dc9003e9b0fd5c50c',NULL,NULL,'P',0),('Adam','','Mauer','','','pMAU000','d9223210503a31a6f6dec8bddb1b0137',NULL,NULL,'P',0),('Alfons','','Mucha','','','pMUC000','d33b39c9ec1e4d01ea0f907e0fbb6da4',NULL,NULL,'P',0),('Zelvak','','Rafaelo','','','pRAF000','48808cef90ec3b65d2923500b43bb8c7',NULL,NULL,'P',0),('Olaf','','Sněhulák','','','pSNE000','14a3f0e874ba79b64a00e1b690bd5a40',NULL,NULL,'P',0),('Jožka','','SuperBoy','','','pSUP000','60a9528d1cbb4aeb3a13066b0294303e',NULL,NULL,'P',0),('Adolf','','SuperBoy','','','pSUP001','a7b521aa8da7065aae64e75c4b78b5bc',NULL,NULL,'P',0),('Tereza','','Zapalačová','','t.zapalacova','pZAP000','2988bd76f9c37a83eb3e62c712e942dc',NULL,NULL,'P',0),('Zelvak','','Rafaelo','','','RAF000','5d66e1836071b236073286acb17a0efc',NULL,2,'S',0),('Olaf','','Sněhulák','','','SNE000','7aa731ce6cf5090a5a07b5555b9c5935',NULL,10,'S',0),('Jožka','','SuperBoy','','','SUP000','32851b29d1a0f7639f922cb39cc45e71',NULL,4,'S',0),('Adolf','','SuperBoy','','','SUP001','a6175059c187426142bab9f6aee5424e',NULL,5,'S',0),('Ondřej','','Veletele','','','VEL000','5e68ca8a651d80d2a005ac825ede6b8c',NULL,4,'T',0),('Tereza','','Zapalačová','','t.zapalacova','ZAP000','1bc9359e43e30b4e6185ce52172e1cd8',NULL,4,'S',0);
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

-- Dump completed on 2015-03-17  7:49:57
