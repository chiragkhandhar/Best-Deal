CREATE DATABASE  IF NOT EXISTS `bestdeal` /*!40100 DEFAULT CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci */ /*!80016 DEFAULT ENCRYPTION='N' */;
USE `bestdeal`;
-- MySQL dump 10.13  Distrib 8.0.20, for Win64 (x86_64)
--
-- Host: localhost    Database: bestdeal
-- ------------------------------------------------------
-- Server version	8.0.21

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
-- Table structure for table `customerorder`
--

DROP TABLE IF EXISTS `customerorder`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `customerorder` (
  `OrderId` int NOT NULL,
  `userID` varchar(11) DEFAULT NULL,
  `userName` varchar(40) NOT NULL,
  `orderName` varchar(40) NOT NULL,
  `productID` varchar(11) DEFAULT NULL,
  `category` varchar(11) DEFAULT NULL,
  `quantity` int DEFAULT NULL,
  `discount` double DEFAULT NULL,
  `shippingCost` double DEFAULT NULL,
  `netTotal` double DEFAULT NULL,
  `orderPrice` double DEFAULT NULL,
  `userAddress` varchar(60) DEFAULT NULL,
  `creditCardNo` varchar(40) DEFAULT NULL,
  `mode` varchar(10) DEFAULT NULL,
  `storeID` varchar(11) DEFAULT NULL,
  `location` varchar(40) DEFAULT NULL,
  `orderDate` varchar(16) DEFAULT NULL,
  `shipDate` varchar(16) DEFAULT NULL,
  PRIMARY KEY (`OrderId`,`userName`,`orderName`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `customerorder`
--

LOCK TABLES `customerorder` WRITE;
/*!40000 ALTER TABLE `customerorder` DISABLE KEYS */;
INSERT INTO `customerorder` VALUES (278199,'1602465920','akash','Apple Watch Series 3 Space Gray Aluminum','sw1','wts',0,10,3,209,229,'400 E 33RD ST, 1603, CHICAGO, IL, 60616-4044','46546446','delivery','store1','','2020-10-11','2020-10-25'),(340939,'1602468478','testuser','LG UHD','tv2','tvs',0,15,3,598,699.99,'400 E 33RD ST, 1603, CHICAGO, IL, 60616-4044','456978951236','delivery','store1','','2020-10-11','2020-10-25'),(558398,'1602467000','testuser','Apple iPhone 11 Pro','ap2','phones',0,15,3,853,999.99,'400 E 33RD ST, 1603, CHICAGO, IL, 60616-4044','456978951236','delivery','store1','','2020-10-11','2020-10-25'),(558398,'1602467000','testuser','LG UHD','tv2','tvs',0,15,3,598,699.99,'400 E 33RD ST, 1603, CHICAGO, IL, 60616-4044','456978951236','delivery','store1','','2020-10-11','2020-10-25'),(753610,'1602341465','chirag','LG UHD','tv2','tvs',0,15,3,598,699.99,'400 E 33rd St, 1603, Chicago, IL, 60616','46464','delivery','store1','','2020-10-11','2020-10-25'),(855709,'1602341465','chirag','Apple iPhone 11 Pro Max','ap3','phones',0,15,0,1012,1190.99,'400 E 33RD ST, 1603, CHICAGO, IL, 60616-4044','46546446','pickup','store3','Wacker Drive, Chicago, IL, 60645','2020-10-11','2020-10-25'),(881228,'1602466033','omar','LG UHD','tv2','tvs',0,15,0,595,699.99,'400 E 33RD ST, 1603, CHICAGO, IL, 60616-4044','1234','pickup','store12','ABC Street, Chicago, IL, 60616','2020-10-11','2020-10-25'),(961361,'1602465920','akash','Apple iPhone 11 Pro','ap2','phones',0,15,3,853,999.99,'400 E 33RD ST, 1603, CHICAGO, IL, 60616-4044','456978951236','delivery','store1','','2020-10-11','2020-10-25'),(961361,'1602465920','akash','Apple Macbook Pro 16\"','al3','laptops',0,0,3,1903,1899.99,'400 E 33RD ST, 1603, CHICAGO, IL, 60616-4044','456978951236','delivery','store1','','2020-10-11','2020-10-25'),(961361,'1602465920','akash','Google - Nest Wifi AC1200','gh1','vas',0,10,3,137,149,'400 E 33RD ST, 1603, CHICAGO, IL, 60616-4044','456978951236','delivery','store1','','2020-10-11','2020-10-25');
/*!40000 ALTER TABLE `customerorder` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2020-10-11 21:34:11
