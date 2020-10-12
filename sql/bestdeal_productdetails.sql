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
-- Table structure for table `productdetails`
--

DROP TABLE IF EXISTS `productdetails`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `productdetails` (
  `productType` varchar(20) DEFAULT NULL,
  `ID` varchar(20) NOT NULL,
  `productName` varchar(70) DEFAULT NULL,
  `productPrice` double DEFAULT NULL,
  `productImage` varchar(50) DEFAULT NULL,
  `productManufacturer` varchar(40) DEFAULT NULL,
  `productCondition` varchar(40) DEFAULT NULL,
  `productDiscount` double DEFAULT NULL,
  `description` varchar(60) DEFAULT NULL,
  PRIMARY KEY (`ID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `productdetails`
--

LOCK TABLES `productdetails` WRITE;
/*!40000 ALTER TABLE `productdetails` DISABLE KEYS */;
INSERT INTO `productdetails` VALUES ('laptop','al1','Apple Macbook Air',899.99,'apple_macbook_air.jpg','Apple','New',0,'This is a dummy description.'),('laptop','al2','Apple Macbook Pro',1299.99,'apple_macbook_pro.jpg','Apple','New',0,'This is a dummy description.'),('laptop','al3','Apple Macbook Pro 16\"',1899.99,'apple_macbook_pro_16.jpg','Apple','New',0,'This is a dummy description.'),('va','amz1','Amazon - Echo Dot (3rd Gen) - Smart Speaker with Alexa - Charcoal',49.99,'echo-dot.jpg','Amazon','New',12,'This is a dummy description.'),('va','amz2','Amazon - Echo Studio Smart Speaker with Alexa - Charcoal',199.99,'echo-studio.jpg','Amazon','New',12,'This is a dummy description.'),('va','amz3','Amazon - Echo Dot Kids Edition Smart Speaker with Alexa - Rainbow',69.99,'echo-kids.jpg','Amazon','New',12,'This is a dummy description.'),('phone','ap1','Apple iPhone 11',699.99,'iphone11.jpg','Apple','New',15,'This is a dummy description.'),('phone','ap2','Apple iPhone 11 Pro',999.99,'iphone11-pro.jpg','Apple','New',15,'This is a dummy description.'),('phone','ap3','Apple iPhone 11 Pro Max',1190.99,'iphone11-pro-max.jpg','Apple','New',15,'This is a dummy description.'),('laptop','dl1','Dell Inspiron 6546',699.99,'dell_inspiron.jpg','Dell','New',25,'This is a dummy description.'),('laptop','dl2','Dell Inspiron Fold',799.99,'dell_inspiron_fold.jpg','Dell','New',25,'This is a dummy description.'),('wt','fw1','Samsung - Galaxy Fit Activity Tracker + Heart Rate - Black',99.99,'samsung1.jpg','fitnesswatch','New',50,'This is a dummy description.'),('wt','fw2','Fitbit - Versa 2 Smartwatch 40mm Aluminum - Stone/Mist Gray',179.99,'fitbit1.jpg','fitnesswatch','New',1,'This is a dummy description.'),('wt','fw3','Garmin - Fēnix 6X Pro Solar GPS/GLONASS/Galileo Watch - Black',999.99,'garmin1.jpg','fitnesswatch','New',0,'This is a dummy description.'),('va','gh1','Google - Nest Wifi AC1200',149,'google-nest-wifi.jpg','Google','New',10,'This is a dummy description.'),('va','gh2','Google - Nest Wifi AC2200',269,'google-nest-wifi2.jpg','Google','New',10,'This is a dummy description.'),('phone','gp1','Google Pixel 2XL',599.99,'google-pixel2xl.jpg','Google','New',10,'This is a dummy description.'),('phone','gp2','Google Pixel 4',399.99,'google-pixel4.jpg','Google','new',10,'This is a dummy description.'),('phone','gp3','Google Pixel 4XL',599.99,'google-pixel4xl.jpg','Google','new',10,'This is a dummy description.'),('wt','hp1','Bose - Noise Cancelling Headphones 700 - Triple Midnight',379,'bose1.jpg','headphone','New',12,'This is a dummy description.'),('laptop','ml1','Microsoft Surface Ultima',899.99,'microsoft_surface.jpg','Microsoft','New',15,'This is a dummy description.'),('accessory','nintendo_sh','Nintendo Racing Controller',89.99,'Wii2.jpg','Nintendo','New',40,'This is a dummy description.'),('accessory','nintendo_wc','Nintendo Controller',89.99,'Wii1.jpg','Nintendo','New',40,'This is a dummy description.'),('accessory','ps3_wc','PS Gaming HeadPhones',89.99,'Turtle Beach Headset.jpg','Sony','New',40,'This is a dummy description.'),('accessory','ps4_wc','PS Controller',89.99,'XBOX controller.jpg','Sony','New',40,'This is a dummy description.'),('wt','pt1','Verizon - Smart Locator - Cool Gray',99.99,'verizon1.jpg','pettracker','New',10,'This is a dummy description.'),('phone','sp1','Samsung Galaxy Note 20 -5G',990.99,'samsung-galaxy-note20-5g.jpg','Samsung','New',12,'This is a dummy description.'),('phone','sp2','Samsung Galaxy S20 -5G',799.99,'samsung-galaxy-s20-5g.jpg','Samsung','New',12,'This is a dummy description.'),('phone','sp3','Samsung Galaxy ZFlip',599.99,'samsung-galaxy-zFlip.jpg','Samsung','New',12,'This is a dummy description.'),('soundsystem','ss1','Yamaha Turbi 2500',349.99,'yamaha-turbi.jpg','Yamaha','New',25,'This is a dummy description.'),('soundsystem','ss2','Bose Lifestyle 600',550.99,'bose-lifestyle600.jpg','Bose','Refurbished',15,'This is a dummy description.'),('soundsystem','ss3','Bose Lifestyle 650',650.99,'bose-lifestyle650.jpg','Bose','New',15,'This is a dummy description.'),('soundsystem','ss4','Polka Audio Black Tone',399.99,'polkaudio-blackstone.jpg','PolkaAudio','New',5,'This is a dummy description.'),('soundsystem','ss5','Bose 5.1',450.99,'bose-51.jpg','Bose','New',15,'This is a dummy description.'),('wt','sw1','Apple Watch Series 3 Space Gray Aluminum',229,'apple1.jpg','smartwatch','New',10,'This is a dummy description.'),('wt','sw2','Fitbit Versa 3 Black',329.95,'fitbit2.jpg','smartwatch','New',10,'This is a dummy description.'),('wt','sw3','Apple Watch Edition (GPS + Cellular) 42mm Gray Ceramic Case',1329,'apple2.jpg','smartwatch','New',10,'This is a dummy description.'),('tv','tv1','LG Nanocell',499.99,'lg_nanocell.jpg','LG','New',10,'This is a dummy description.'),('tv','tv2','LG UHD',699.99,'lg_UHD.jpg','LG','New',15,'This is a dummy description.'),('tv','tv3','Samsung Crystal UHD',549.99,'samsung_crystalUHD.jpg','Samsung','New',12,'This is a dummy description.'),('tv','tv4','Samsung QLED',549.99,'samsung_QLED.jpg','Samsung','New',20,'This is a dummy description.'),('tv','tv5','Samsung UHD',749.99,'samsung_UHD.jpg','Samsung','New',13,'This is a dummy description.'),('tv','tv6','Sony X1',849.99,'sony_x1.jpg','Sony','New',35,'This is a dummy description.'),('tv','tv7','NEWPRODUCT',1000,'sony_x1.jpg','NEWPRODUCT','NEWPRODUCT',50,'This is a dummy description 1232.'),('wt','vr1','Oculus - Rift S PC-Powered VR Gaming Headset - Black',399,'oculus1.jpg','vr','New',5,'This is a dummy description.'),('accessory','xbox360_kg','Charger',89.99,'charger.jpg','Microsoft','New',40,'This is a dummy description.'),('accessory','xbox360_mp','Steering',89.99,'steering.jpg','Microsoft','New',40,'This is a dummy description.'),('accessory','xbox360_rs','messengerkit',89.99,'messengerkit.jpg','Microsoft','New',40,'This is a dummy description.'),('accessory','xbox360_sh','XBOX Wireless Adapter',89.99,'xbox360_wa.png','Microsoft','New',40,'This is a dummy description.'),('accessory','xbox360_vb','Remote',89.99,'remote.jpg','Microsoft','New',40,'This is a dummy description.'),('accessory','xbox360_wc','XBOX Speeding Wheel',89.99,'XBOX360-SpeedWheel.jpg','Microsoft','New',40,'This is a dummy description.'),('accessory','xboxone_kg','Keypad',89.99,'keypad.jpg','Microsoft','New',40,'This is a dummy description.'),('accessory','xboxone_mc','Wireless ',89.99,'wirelessadapter.jpg','Microsoft','New',40,'This is a dummy description.'),('accessory','xboxone_mp','Wireless adpter',89.99,'wirelessadapter.jpg','Microsoft','New',40,'This is a dummy description.'),('accessory','xboxone_rs','Starterkit',89.99,'starterkit.jpeg','Microsoft','New',40,'This is a dummy description.'),('accessory','xboxone_sh','Turtle Beach Headset',89.99,'Turtle Beach Headset.jpg','Microsoft','New',40,'This is a dummy description.'),('accessory','xboxone_vb','joystick',89.99,'joystick.jpg','Microsoft','New',40,'This is a dummy description.'),('accessory','xboxone_wc','Controller',89.99,'XBOX controller.jpg','Microsoft','New',40,'This is a dummy description.');
/*!40000 ALTER TABLE `productdetails` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2020-10-11 21:34:10
