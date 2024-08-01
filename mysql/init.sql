-- MySQL dump 10.13  Distrib 8.0.36, for Win64 (x86_64)
--
-- Host: localhost    Database: h2t_coffee
-- ------------------------------------------------------
-- Server version	8.0.36

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
-- Table structure for table `address`
--

DROP TABLE IF EXISTS `address`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `address` (
  `id` bigint NOT NULL AUTO_INCREMENT,
  `city` varchar(255) DEFAULT NULL,
  `district` varchar(255) DEFAULT NULL,
  `is_default` bit(1) DEFAULT NULL,
  `phone_number` varchar(255) DEFAULT NULL,
  `recipient_name` varchar(255) DEFAULT NULL,
  `street` varchar(255) DEFAULT NULL,
  `ward` varchar(255) DEFAULT NULL,
  `user_id` bigint DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `FK6i66ijb8twgcqtetl8eeeed6v` (`user_id`),
  CONSTRAINT `FK6i66ijb8twgcqtetl8eeeed6v` FOREIGN KEY (`user_id`) REFERENCES `users` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=14 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `address`
--

LOCK TABLES `address` WRITE;
/*!40000 ALTER TABLE `address` DISABLE KEYS */;
INSERT INTO `address` VALUES (1,'Thành phố Hồ Chí Minh','',_binary '','0368996868','nguyen van a','72 đường Nguyễn Trãi','',2),(2,'Tỉnh Cao Bằng','Huyện Bảo Lâm',_binary '','0908989878','Đào Trang','số 68 đường Thanh Niên','Xã Đức Hạnh',3),(3,'Thành phố Hà Nội','Quận Ba Đình',_binary '\0','0359476688','Hoàng Entertainment','24 Phan Đình Phùng','Phường Trúc Bạch',4),(4,'Thành phố Hà Nội','Quận Hoàn Kiếm',_binary '\0','0898668990','Nguyễn Hiền Tiến','25 Hàng Bồ','Phường Hàng Bồ',5),(5,'Thành phố Hà Nội','Quận Hoàn Kiếm',_binary '\0','0896608808','Nguyễn Khách Hàng','54 Chương Dương Độ','Phường Chương Dương',6),(6,'Thành phố Hà Nội','Quận Long Biên',_binary '','0896608808','Nguyễn Khách Hàng','54 Chương Dương Độ','Phường Ngọc Thụy',5),(7,'Thành phố Hà Nội','Quận Tây Hồ',_binary '\0','0896608808','demo','54 Chương Dương Độ','Phường Phú Thượng',4),(8,'Thành phố Hà Nội','Quận Hoàn Kiếm',_binary '','0896608808','Nguyễn Hiền Tiến','54 Chương Dương Độ','Phường Đồng Xuân',4),(9,'Thành phố Hà Nội','Quận Tây Hồ',_binary '\0','0896608808','demo','54 Chương Dương Độ','Phường Nhật Tân',5),(10,'Thành phố Hà Nội','Quận Hoàn Kiếm',_binary '\0','0896608808','nguyen van a','54 Chương Dương Độ','Phường Đồng Xuân',5),(11,'Thành phố Hà Nội','Quận Hoàn Kiếm',_binary '\0','0896608808','nguyen van ba','54 Chương Dương Độ','Phường Hàng Bồ',5),(12,'Thành phố Hà Nội','Quận Ba Đình',_binary '','0896608808','Nguễn Hiền Tiến','54 Chương Dương Độ','Phường Cống Vị',6),(13,'Thành phố Hà Nội','Quận Hoàn Kiếm',_binary '\0','0896608808','nguyen van a','54 Chương Dương Độ','Phường Chương Dương',6);
/*!40000 ALTER TABLE `address` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `bill`
--

DROP TABLE IF EXISTS `bill`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `bill` (
  `id` bigint NOT NULL AUTO_INCREMENT,
  `bill_date` datetime(6) DEFAULT NULL,
  `payment_info` varchar(255) DEFAULT NULL,
  `order_id` bigint DEFAULT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `UK_cl7s1gvkl6vmk79h07vx5did9` (`order_id`),
  CONSTRAINT `FKk4iu99d2pp9qbgqq35u6kpyif` FOREIGN KEY (`order_id`) REFERENCES `orders` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `bill`
--

LOCK TABLES `bill` WRITE;
/*!40000 ALTER TABLE `bill` DISABLE KEYS */;
/*!40000 ALTER TABLE `bill` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `cart`
--

DROP TABLE IF EXISTS `cart`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `cart` (
  `id` bigint NOT NULL AUTO_INCREMENT,
  `total_item` int NOT NULL,
  `total_price` int NOT NULL,
  `total_sale_price` int NOT NULL,
  `user_id` bigint NOT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `UK_9emlp6m95v5er2bcqkjsw48he` (`user_id`),
  CONSTRAINT `FKg5uhi8vpsuy0lgloxk2h4w5o6` FOREIGN KEY (`user_id`) REFERENCES `users` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=7 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `cart`
--

LOCK TABLES `cart` WRITE;
/*!40000 ALTER TABLE `cart` DISABLE KEYS */;
INSERT INTO `cart` VALUES (1,3,170000,155000,2),(2,3,129000,112000,3),(3,3,199000,184000,4),(4,3,165000,149998,5),(5,4,244000,224000,6),(6,0,0,0,7);
/*!40000 ALTER TABLE `cart` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `cart_item`
--

DROP TABLE IF EXISTS `cart_item`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `cart_item` (
  `id` bigint NOT NULL AUTO_INCREMENT,
  `price` int NOT NULL,
  `price_sale` int NOT NULL,
  `quantity` int NOT NULL,
  `user_id` bigint DEFAULT NULL,
  `cart_id` bigint DEFAULT NULL,
  `product_id` bigint DEFAULT NULL,
  `size_option_id` bigint DEFAULT NULL,
  `topping_option_id` bigint DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `FK1uobyhgl1wvgt1jpccia8xxs3` (`cart_id`),
  KEY `FKjcyd5wv4igqnw413rgxbfu4nv` (`product_id`),
  KEY `FKla1wfri0ut95vjvrb0ckcs58x` (`size_option_id`),
  KEY `FKl8i16f72ohc7amsjhwtiwrgsp` (`topping_option_id`),
  CONSTRAINT `FK1uobyhgl1wvgt1jpccia8xxs3` FOREIGN KEY (`cart_id`) REFERENCES `cart` (`id`),
  CONSTRAINT `FKjcyd5wv4igqnw413rgxbfu4nv` FOREIGN KEY (`product_id`) REFERENCES `product` (`id`),
  CONSTRAINT `FKl8i16f72ohc7amsjhwtiwrgsp` FOREIGN KEY (`topping_option_id`) REFERENCES `topping_option` (`id`),
  CONSTRAINT `FKla1wfri0ut95vjvrb0ckcs58x` FOREIGN KEY (`size_option_id`) REFERENCES `size_option` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=38 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `cart_item`
--

LOCK TABLES `cart_item` WRITE;
/*!40000 ALTER TABLE `cart_item` DISABLE KEYS */;
INSERT INTO `cart_item` VALUES (1,130000,120000,2,2,1,11,26,15),(2,40000,35000,1,2,1,7,19,9),(3,52000,47000,1,2,1,2,6,2),(4,40000,35000,1,2,1,6,17,7),(19,40000,35000,1,3,2,7,19,8),(32,55000,50000,1,5,4,5,14,29),(33,110000,99998,2,5,4,15,35,20),(36,124000,114000,2,6,5,12,28,16),(37,65000,60000,1,6,5,13,30,18);
/*!40000 ALTER TABLE `cart_item` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `category`
--

DROP TABLE IF EXISTS `category`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `category` (
  `id` bigint NOT NULL AUTO_INCREMENT,
  `name` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `UK_46ccwnsi9409t36lurvtyljak` (`name`)
) ENGINE=InnoDB AUTO_INCREMENT=6 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `category`
--

LOCK TABLES `category` WRITE;
/*!40000 ALTER TABLE `category` DISABLE KEYS */;
INSERT INTO `category` VALUES (1,'Cafe'),(5,'Đồ ăn vặt'),(3,'Matcha'),(2,'Trà'),(4,'Trà Sữa');
/*!40000 ALTER TABLE `category` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `geolocation`
--

DROP TABLE IF EXISTS `geolocation`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `geolocation` (
  `id` int NOT NULL AUTO_INCREMENT,
  `name` varchar(255) DEFAULT NULL,
  `parent_id` int NOT NULL,
  `slug` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `geolocation`
--

LOCK TABLES `geolocation` WRITE;
/*!40000 ALTER TABLE `geolocation` DISABLE KEYS */;
/*!40000 ALTER TABLE `geolocation` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `notifications`
--

DROP TABLE IF EXISTS `notifications`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `notifications` (
  `id` bigint NOT NULL AUTO_INCREMENT,
  `count` int NOT NULL,
  `is_read` bit(1) NOT NULL,
  `message` varchar(255) DEFAULT NULL,
  `timestamp` datetime(6) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=45 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `notifications`
--

LOCK TABLES `notifications` WRITE;
/*!40000 ALTER TABLE `notifications` DISABLE KEYS */;
INSERT INTO `notifications` VALUES (34,0,_binary '','Bạn có đơn hàng mới','2024-05-05 18:32:39.638230'),(35,0,_binary '\0','Bạn có đơn hàng mới','2024-05-07 15:43:52.434801'),(36,0,_binary '\0','Bạn có đơn hàng mới','2024-05-14 22:52:30.103129'),(37,0,_binary '\0','Bạn có đơn hàng mới','2024-05-14 23:01:13.682575'),(38,0,_binary '\0','Bạn có đơn hàng mới','2024-05-14 23:06:59.421643'),(39,0,_binary '\0','Bạn có đơn hàng mới','2024-05-14 23:11:25.805404'),(40,0,_binary '\0','Bạn có đơn hàng mới','2024-05-14 23:16:54.675934'),(41,0,_binary '\0','Bạn có đơn hàng mới','2024-05-14 23:18:31.346838'),(42,0,_binary '\0','Bạn có đơn hàng mới','2024-05-14 23:27:53.117916'),(43,0,_binary '\0','Bạn có đơn hàng mới','2024-05-14 23:32:25.602017'),(44,0,_binary '\0','Bạn có đơn hàng mới','2024-05-14 23:45:11.125375');
/*!40000 ALTER TABLE `notifications` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `order_item`
--

DROP TABLE IF EXISTS `order_item`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `order_item` (
  `id` bigint NOT NULL AUTO_INCREMENT,
  `price` int NOT NULL,
  `price_sale` int NOT NULL,
  `quantity` int NOT NULL,
  `user_id` bigint DEFAULT NULL,
  `order_id` bigint DEFAULT NULL,
  `product_id` bigint DEFAULT NULL,
  `size_option_id` bigint DEFAULT NULL,
  `topping_option_id` bigint DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `FKt4dc2r9nbvbujrljv3e23iibt` (`order_id`),
  KEY `FK551losx9j75ss5d6bfsqvijna` (`product_id`),
  KEY `FK3pk14jrw5u922r4fujoqyxhub` (`size_option_id`),
  KEY `FK9fvdn111nvr0fiejlk88ha50c` (`topping_option_id`),
  CONSTRAINT `FK3pk14jrw5u922r4fujoqyxhub` FOREIGN KEY (`size_option_id`) REFERENCES `size_option` (`id`),
  CONSTRAINT `FK551losx9j75ss5d6bfsqvijna` FOREIGN KEY (`product_id`) REFERENCES `product` (`id`),
  CONSTRAINT `FK9fvdn111nvr0fiejlk88ha50c` FOREIGN KEY (`topping_option_id`) REFERENCES `topping_option` (`id`),
  CONSTRAINT `FKt4dc2r9nbvbujrljv3e23iibt` FOREIGN KEY (`order_id`) REFERENCES `orders` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=51 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `order_item`
--

LOCK TABLES `order_item` WRITE;
/*!40000 ALTER TABLE `order_item` DISABLE KEYS */;
INSERT INTO `order_item` VALUES (1,65000,60000,2,2,1,11,26,15),(2,65000,60000,2,2,2,11,26,15),(3,130000,120000,2,3,3,10,25,13),(4,40000,35000,1,3,3,7,19,9),(5,60000,55000,1,3,4,17,40,28),(6,94000,84000,2,3,5,2,5,2),(7,114000,104000,2,3,6,1,2,1),(8,108000,94000,2,3,7,4,12,4),(9,70000,65000,1,3,8,14,33,19),(10,60000,54999,1,3,9,15,36,21),(11,60000,55000,1,3,10,16,38,23),(12,70000,65000,1,4,11,13,31,18),(13,70000,65000,1,4,12,11,27,15),(14,110000,100000,2,4,13,8,21,11),(15,120000,110000,2,4,14,17,40,27),(16,65000,60000,1,5,15,10,25,14),(17,65000,60000,1,5,16,10,25,14),(18,55000,50000,1,5,17,16,37,23),(19,60000,54999,1,5,18,15,36,21),(20,54000,47000,1,5,19,4,12,4),(21,40000,35000,1,6,20,7,19,9),(22,60000,55000,1,6,21,16,38,24),(23,57000,52000,1,6,22,1,2,1),(24,75000,70000,1,6,23,14,34,19),(25,52000,47000,1,6,24,3,9,3),(26,70000,65000,1,6,25,11,27,15),(27,47000,42000,1,6,26,2,5,2),(28,55000,50000,1,6,27,8,21,11),(29,35000,30000,1,6,28,6,16,7),(30,70000,65000,1,6,29,11,27,15),(31,47000,42000,1,6,30,3,8,3),(32,52000,47000,1,6,31,3,9,3),(33,40000,35000,1,6,32,7,19,9),(34,70000,65000,1,6,33,11,27,15),(35,52000,47000,1,6,34,2,6,2),(36,40000,35000,1,6,35,7,19,9),(37,110000,100000,1,6,36,19,45,NULL),(38,52000,45000,1,4,37,4,12,NULL),(39,110000,100000,1,4,38,19,45,NULL),(40,52000,47000,1,5,39,3,9,3),(41,94000,84000,2,NULL,40,2,5,2),(42,57000,52000,1,NULL,41,1,2,1),(43,65000,60000,1,NULL,42,13,30,18),(44,55000,50000,1,NULL,43,9,23,12),(45,65000,60000,1,NULL,44,13,30,18),(46,55000,50000,1,5,45,5,14,29),(47,110000,99998,2,5,45,15,35,20),(48,70000,65000,1,NULL,46,11,27,15),(49,67000,62000,1,NULL,47,13,31,17),(50,60000,54999,1,6,48,15,36,21);
/*!40000 ALTER TABLE `order_item` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `orders`
--

DROP TABLE IF EXISTS `orders`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `orders` (
  `id` bigint NOT NULL AUTO_INCREMENT,
  `create_at` datetime(6) DEFAULT NULL,
  `delivery_date_time` datetime(6) DEFAULT NULL,
  `note` varchar(255) DEFAULT NULL,
  `order_id` varchar(255) DEFAULT NULL,
  `status` enum('PLACED','CONFIRMED','SHIPPED','DELIVERED','CANCELLED') DEFAULT NULL,
  `total_price` int NOT NULL,
  `total_sale_price` int NOT NULL,
  `update_status_at` datetime(6) DEFAULT NULL,
  `payment_id` bigint DEFAULT NULL,
  `shipping_address_id` bigint DEFAULT NULL,
  `user_id` bigint DEFAULT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `UK_haujdjk1ohmeixjhnhslchrp1` (`payment_id`),
  KEY `FKh0uue95ltjysfmkqb5abgk7tj` (`shipping_address_id`),
  KEY `FK32ql8ubntj5uh44ph9659tiih` (`user_id`),
  CONSTRAINT `FK32ql8ubntj5uh44ph9659tiih` FOREIGN KEY (`user_id`) REFERENCES `users` (`id`),
  CONSTRAINT `FKag8ppnkjvx255gj7lm3m18wkj` FOREIGN KEY (`payment_id`) REFERENCES `payment` (`id`),
  CONSTRAINT `FKh0uue95ltjysfmkqb5abgk7tj` FOREIGN KEY (`shipping_address_id`) REFERENCES `address` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=49 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `orders`
--

LOCK TABLES `orders` WRITE;
/*!40000 ALTER TABLE `orders` DISABLE KEYS */;
INSERT INTO `orders` VALUES (1,'2024-05-04 15:31:18.779506',NULL,'giao nhanh thì được bo','240504H2TBJFLZP','CONFIRMED',65000,60000,'2024-05-04 20:47:47.411623',NULL,NULL,2),(2,'2024-05-04 15:32:20.382940','2024-05-04 15:35:10.595938','','240504H2TXHGDTB','SHIPPED',65000,60000,'2024-05-04 20:45:38.308594',NULL,1,2),(3,'2024-05-04 20:40:01.268794','2024-05-04 20:48:41.000964','ship nhanh nhá','240504H2THAPJOI','DELIVERED',170000,155000,'2024-05-05 18:12:14.845277',NULL,2,3),(4,'2024-05-04 21:20:01.701715','2024-05-06 21:19:30.439949','không có gì để nói','240504H2THSPHGW','DELIVERED',60000,55000,'2024-05-06 21:19:30.439949',NULL,2,3),(5,'2024-05-04 21:59:10.350452',NULL,'không cho đá','240504H2TTTVPGG','CONFIRMED',94000,84000,'2024-05-04 22:12:32.492504',NULL,2,3),(6,'2024-05-04 22:13:35.647824','2024-05-05 17:55:18.585543','ca cao đá nhưng đừng cho đá, ít sữa thôi','240504H2TFKFCYO','DELIVERED',114000,104000,'2024-05-05 17:57:24.892198',NULL,2,3),(7,'2024-05-04 22:28:48.209966','2024-05-07 01:26:45.060903','không cho đá, pha ngọt vào :))','240504H2TFECJQZ','DELIVERED',108000,94000,'2024-05-07 01:26:45.060903',NULL,2,3),(8,'2024-05-04 22:32:29.277818','2024-05-07 00:18:54.760766','không cần đá','240504H2TLKDDUO','DELIVERED',70000,65000,'2024-05-07 00:48:43.182133',NULL,2,3),(9,'2024-05-04 22:36:49.787708',NULL,'','240504H2TNTTNRY','CONFIRMED',60000,54999,'2024-05-04 22:50:38.562859',NULL,2,3),(10,'2024-05-04 22:38:01.889021',NULL,'nhìn ngon nên mua','240504H2TKHKKVF','CONFIRMED',60000,55000,'2024-05-04 22:50:34.189420',NULL,2,3),(11,'2024-05-04 22:50:06.281490','2024-05-05 18:25:26.050454','nhìn ngon thực sự','240504H2THJEJRG','DELIVERED',70000,65000,'2024-05-05 18:25:26.050454',NULL,3,4),(12,'2024-05-04 22:56:33.493147','2024-05-05 19:03:15.781905','','240504H2TEWLSDU','DELIVERED',70000,65000,'2024-05-05 19:03:15.781905',NULL,3,4),(13,'2024-05-04 22:56:59.339893',NULL,'ngon ','240504H2TTBNQWF','PLACED',110000,100000,'2024-05-04 22:56:59.339893',NULL,3,4),(14,'2024-05-04 23:02:46.601147',NULL,'','240504H2TAZMLKR','PLACED',120000,110000,'2024-05-04 23:02:46.601147',NULL,3,4),(15,'2024-05-04 23:07:21.533007',NULL,'','240504H2TWFDOCQ','PLACED',65000,60000,'2024-05-04 23:07:21.533007',NULL,4,5),(16,'2024-05-04 23:20:42.902679','2024-05-07 15:44:50.337480','Ngon','240504H2TBMMTDU','DELIVERED',65000,60000,'2024-05-07 15:46:22.498464',NULL,4,5),(17,'2024-05-04 23:22:29.375898',NULL,'ngon','240504H2TUWVTFV','CONFIRMED',55000,50000,'2024-05-07 13:58:38.838015',NULL,4,5),(18,'2024-05-04 23:42:05.427903','2024-05-06 21:17:31.020546','','240504H2TJNHPAE','DELIVERED',60000,54999,'2024-05-06 21:17:31.020546',NULL,4,5),(19,'2024-05-04 23:42:52.695884','2024-05-06 21:17:22.600452','','240504H2TVRNTEP','DELIVERED',54000,47000,'2024-05-06 21:17:22.600452',NULL,4,5),(20,'2024-05-04 23:49:05.978891','2024-05-05 14:59:45.088268','ngon','240504H2TECFQED','DELIVERED',40000,35000,'2024-05-05 15:03:53.655612',NULL,5,6),(21,'2024-05-04 23:51:24.247908','2024-05-05 14:52:43.763549','','240504H2TAEPFNS','DELIVERED',60000,55000,'2024-05-05 17:42:45.332143',NULL,5,6),(22,'2024-05-05 12:15:43.185526',NULL,'','240505H2TRVRUPT','CANCELLED',57000,52000,'2024-05-06 21:15:46.440928',NULL,NULL,6),(23,'2024-05-05 12:21:58.461329',NULL,'','240505H2TRVPKPY','CONFIRMED',75000,70000,'2024-05-05 13:17:14.196104',NULL,5,6),(24,'2024-05-05 12:27:50.784194',NULL,'','240505H2TDBEPFV','CONFIRMED',52000,47000,'2024-05-05 13:17:04.846692',NULL,5,6),(25,'2024-05-05 12:32:19.325270',NULL,'','240505H2THPCUJD','CONFIRMED',70000,65000,'2024-05-05 13:17:00.826355',NULL,5,6),(26,'2024-05-05 12:33:57.747066',NULL,'','240505H2THXLBHA','CONFIRMED',47000,42000,'2024-05-05 13:16:57.004347',NULL,5,6),(27,'2024-05-05 12:38:25.111746',NULL,'thử thông báo','240505H2TQBJQXP','CONFIRMED',55000,50000,'2024-05-05 13:16:53.217389',NULL,5,6),(28,'2024-05-05 12:46:42.815296',NULL,'','240505H2TITVHAT','CONFIRMED',35000,30000,'2024-05-05 13:16:49.348140',NULL,5,6),(29,'2024-05-05 13:18:19.961997',NULL,'','240505H2TYLSQRE','PLACED',70000,65000,'2024-05-05 13:18:19.961997',NULL,5,6),(30,'2024-05-05 13:29:47.187570',NULL,'','240505H2TYZBAXR','PLACED',47000,42000,'2024-05-05 13:29:47.187570',NULL,5,6),(31,'2024-05-05 13:38:56.243058','2024-05-05 14:43:06.053364','','240505H2TWCJKBB','DELIVERED',52000,47000,'2024-05-05 17:50:11.277424',NULL,5,6),(32,'2024-05-05 13:46:29.130527',NULL,'','240505H2TDUVOJQ','PLACED',40000,35000,'2024-05-05 13:46:29.130527',NULL,5,6),(33,'2024-05-05 14:03:04.493024','2024-05-05 14:41:55.438334','','240505H2TGRYKZH','DELIVERED',70000,65000,'2024-05-05 16:54:37.890749',NULL,5,6),(34,'2024-05-05 14:04:43.947333','2024-05-05 14:41:37.040786','','240505H2TSEMMED','DELIVERED',52000,47000,'2024-05-05 17:48:54.782816',NULL,5,6),(35,'2024-05-05 14:45:16.727460',NULL,'','240505H2TGGMDXR','PLACED',40000,35000,'2024-05-05 14:45:16.727460',NULL,5,6),(36,'2024-05-05 16:43:50.723414',NULL,'','240505H2TJYIAGU','PLACED',110000,100000,'2024-05-05 16:43:50.723414',NULL,5,6),(37,'2024-05-05 18:31:01.494421',NULL,'làm cốc bạc xỉu cho tỉnh người','240505H2TGIEVDJ','PLACED',52000,45000,'2024-05-05 18:31:01.494421',NULL,3,4),(38,'2024-05-05 18:32:39.630533','2024-05-07 13:57:46.314590','mua nửa cân','240505H2TJEYZZP','DELIVERED',110000,100000,'2024-05-14 22:53:20.282910',NULL,3,4),(39,'2024-05-07 15:43:52.403148',NULL,'ship ngay nhé','240507H2TLJSTKS','SHIPPED',52000,47000,'2024-05-14 23:44:34.314046',NULL,6,5),(40,'2024-05-14 22:52:30.072123',NULL,'giao nhanh nhé','240514H2TJQTAFI','PLACED',94000,84000,'2024-05-14 22:52:30.072123',NULL,7,4),(41,'2024-05-14 23:01:13.676574',NULL,'giao nhanh nhé','240514H2TIQSXVZ','PLACED',57000,52000,'2024-05-14 23:01:13.676574',NULL,8,4),(42,'2024-05-14 23:06:59.414641',NULL,'giao nhanh nhé','240514H2TMJJRUC','PLACED',65000,60000,'2024-05-14 23:06:59.414641',NULL,9,5),(43,'2024-05-14 23:11:25.800403',NULL,'giao nhanh nhé','240514H2TQQVLSF','PLACED',55000,50000,'2024-05-14 23:11:25.800403',NULL,10,5),(44,'2024-05-14 23:16:54.667933',NULL,'giao nhanh nhé','240514H2TDTNFPZ','PLACED',65000,60000,'2024-05-14 23:16:54.667933',NULL,11,5),(45,'2024-05-14 23:18:31.322832',NULL,'ngon','240514H2TDCKKOX','PLACED',165000,149998,'2024-05-14 23:18:31.322832',NULL,4,5),(46,'2024-05-14 23:27:53.109915',NULL,'giao nhanh nhé','240514H2TCVLKLA','CONFIRMED',70000,65000,'2024-05-14 23:44:06.970397',NULL,12,6),(47,'2024-05-14 23:32:25.595015','2024-05-14 23:42:13.937844','giao nhanh nhé','240514H2TDCCJBM','DELIVERED',67000,62000,'2024-05-14 23:42:57.060097',NULL,13,6),(48,'2024-05-14 23:45:11.118374',NULL,'test realtime','240514H2TRDRMXH','PLACED',60000,54999,'2024-05-14 23:45:11.118374',NULL,12,6);
/*!40000 ALTER TABLE `orders` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `payment`
--

DROP TABLE IF EXISTS `payment`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `payment` (
  `id` bigint NOT NULL AUTO_INCREMENT,
  `amount` int NOT NULL,
  `payment_date_time` datetime(6) DEFAULT NULL,
  `payment_details` varchar(255) DEFAULT NULL,
  `payment_method` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `payment`
--

LOCK TABLES `payment` WRITE;
/*!40000 ALTER TABLE `payment` DISABLE KEYS */;
/*!40000 ALTER TABLE `payment` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `product`
--

DROP TABLE IF EXISTS `product`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `product` (
  `id` bigint NOT NULL AUTO_INCREMENT,
  `average_rating` double NOT NULL,
  `description` varchar(255) DEFAULT NULL,
  `name` varchar(255) DEFAULT NULL,
  `price` int NOT NULL,
  `sale_price` int NOT NULL,
  `category_id` bigint DEFAULT NULL,
  `sold` int NOT NULL,
  PRIMARY KEY (`id`),
  KEY `FK1mtsbur82frn64de7balymq9s` (`category_id`),
  CONSTRAINT `FK1mtsbur82frn64de7balymq9s` FOREIGN KEY (`category_id`) REFERENCES `category` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=22 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `product`
--

LOCK TABLES `product` WRITE;
/*!40000 ALTER TABLE `product` DISABLE KEYS */;
INSERT INTO `product` VALUES (1,2,'Ngon hơn Tiến nhé Tiến','Cacao (đá)',50000,45000,1,16),(2,5,'đắng vãiii luôn ạ cần thêm nhiều sữa nhé khách ơiii','Đen đá',40000,35000,1,3),(3,4,'ngon hơn nyc của bạnnn .Sờiii ơii','Nâu Đá',40000,35000,1,1),(4,0,'ngon hơn Đoàn Huy Hoàng ẹeee','Bạc xỉu',42000,35000,1,3),(5,0,'Trà đào thơm ngon đây','Trà Đào',45000,40000,2,0),(6,0,'Thanh máttt','Trà Chanh',30000,25000,2,0),(7,2.5,'Đồ uống mà các bạn nữ gọi nhiều nhất nè','Trà Quất',30000,25000,2,3),(8,0,'trà ổi hồng thơm ngon đêy','Trà Ổi Hồng',45000,40000,2,0),(9,0,'trà dâu tuyệt vời ','Trà Dâu',45000,40000,2,0),(10,3,'thưởng thức trà vải mát lạnh tại h2t coffee','Trà Vải',55000,50000,2,3),(11,4.5,'capuchino','Capuchino',60000,55000,1,5),(12,0,'Macchiato','Macchiato',60000,55000,1,0),(13,5,'Matcha latte','Matcha Latte',60000,55000,3,4),(14,5,'matcha đá xay','Matcha Đá Xay',65000,60000,3,1),(15,0,'Trà sữa trân châu đường đen','Trân Châu Đường Đen',50000,44999,4,1),(16,4,'trà sữa socola','Trà sữa socola',50000,45000,4,2),(17,0,'trà sữa dâu','Trà sữa dâu',50000,45000,4,1),(18,0,'em người yêu khá bảnh hay còn gọi là hoa mặt trời nhé','Hướng Dương',25000,20000,5,0),(19,5,'khô bò ăn ít thôi ăn nhiều sớm die','Khô bò',60000,50000,5,1),(20,0,'khô gà cũng giống khô bò','Khô gà',50000,45000,5,0),(21,0,'ngon mà','Trà sữa ngon',37000,20000,4,0);
/*!40000 ALTER TABLE `product` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `product_image`
--

DROP TABLE IF EXISTS `product_image`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `product_image` (
  `id` bigint NOT NULL AUTO_INCREMENT,
  `image_url` varchar(255) DEFAULT NULL,
  `product_id` bigint DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `FK6oo0cvcdtb6qmwsga468uuukk` (`product_id`),
  CONSTRAINT `FK6oo0cvcdtb6qmwsga468uuukk` FOREIGN KEY (`product_id`) REFERENCES `product` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=83 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `product_image`
--

LOCK TABLES `product_image` WRITE;
/*!40000 ALTER TABLE `product_image` DISABLE KEYS */;
INSERT INTO `product_image` VALUES (1,'http://res.cloudinary.com/ddnepfewc/image/upload/v1714659344/tsm4huntc9yhrehzobtf.png',1),(2,'http://res.cloudinary.com/ddnepfewc/image/upload/v1714659436/vjddwjyddkk3mbaltokj.png',1),(3,'http://res.cloudinary.com/ddnepfewc/image/upload/v1714659480/ggzyrwvprzmlvrwx7h2y.png',1),(4,'http://res.cloudinary.com/ddnepfewc/image/upload/v1714659576/iiuc8bqgu1pjam4d8zop.png',1),(5,'http://res.cloudinary.com/ddnepfewc/image/upload/v1714660303/tgvflcysibbah0s0vq6a.png',2),(6,'http://res.cloudinary.com/ddnepfewc/image/upload/v1714660314/gweyekkw9gz1pesjmbnw.png',2),(7,'http://res.cloudinary.com/ddnepfewc/image/upload/v1714660320/ytbovg0nohqqonwstif3.png',2),(8,'http://res.cloudinary.com/ddnepfewc/image/upload/v1714660327/ephyifyaqzwciuv25dph.png',2),(9,'http://res.cloudinary.com/ddnepfewc/image/upload/v1714661321/fb0eh8m8fskmqe4cz6jt.png',3),(10,'http://res.cloudinary.com/ddnepfewc/image/upload/v1714661336/gyfi2xzrooumvzbkicua.png',3),(11,'http://res.cloudinary.com/ddnepfewc/image/upload/v1714661348/oowcsczt2b5bq7ilhf7t.png',3),(12,'http://res.cloudinary.com/ddnepfewc/image/upload/v1714661355/jm5dw4weemzowjqb1p8v.png',3),(13,'http://res.cloudinary.com/ddnepfewc/image/upload/v1714662271/v53fvi5sk8jgjuvpbmkg.png',4),(14,'http://res.cloudinary.com/ddnepfewc/image/upload/v1714662281/knvgljocqvye4cjf1r6u.png',4),(15,'http://res.cloudinary.com/ddnepfewc/image/upload/v1714662294/buacwhajlrostlbpzaun.png',4),(16,'http://res.cloudinary.com/ddnepfewc/image/upload/v1714662305/dvad259prf2irhubnnne.png',4),(17,'http://res.cloudinary.com/ddnepfewc/image/upload/v1714663357/ykyv4sh6wdzovqizt055.png',5),(18,'http://res.cloudinary.com/ddnepfewc/image/upload/v1714663365/q6uxs5bndahpxltziai2.png',5),(19,'http://res.cloudinary.com/ddnepfewc/image/upload/v1714663370/cmdud74eabpl0xagqt0s.png',5),(20,'http://res.cloudinary.com/ddnepfewc/image/upload/v1714663380/hekbzpbnbnhdlro7fack.png',5),(21,'http://res.cloudinary.com/ddnepfewc/image/upload/v1714664084/nik0eakfh1qeu5sr4jkd.png',6),(22,'http://res.cloudinary.com/ddnepfewc/image/upload/v1714664090/hwfqzxuy3j8ojxkypmgg.png',6),(23,'http://res.cloudinary.com/ddnepfewc/image/upload/v1714664096/s6hlyvprjaalq6pa3qsl.png',6),(24,'http://res.cloudinary.com/ddnepfewc/image/upload/v1714664103/xor45q4zrm1v2q6gmpwm.png',6),(25,'http://res.cloudinary.com/ddnepfewc/image/upload/v1714702134/v38anyrbvmkjfjfiutuv.png',7),(26,'http://res.cloudinary.com/ddnepfewc/image/upload/v1714702140/lhkakxithqotxv79xo1n.png',7),(27,'http://res.cloudinary.com/ddnepfewc/image/upload/v1714702146/nxcjyxhraknlznl2vf2w.png',7),(28,'http://res.cloudinary.com/ddnepfewc/image/upload/v1714702151/ragrlyrv19z22vvny05p.png',7),(29,'http://res.cloudinary.com/ddnepfewc/image/upload/v1714702701/qwplfydm5u4njjynegjx.png',8),(30,'http://res.cloudinary.com/ddnepfewc/image/upload/v1714702707/bdps6w8k3cmbyq0vyaj5.png',8),(31,'http://res.cloudinary.com/ddnepfewc/image/upload/v1714702712/ge3dxzg0p8mnhi1wykct.png',8),(32,'http://res.cloudinary.com/ddnepfewc/image/upload/v1714702717/tahvxs0cqb6vzbolzp0c.png',8),(33,'http://res.cloudinary.com/ddnepfewc/image/upload/v1714703386/q7tvf0uyacbopciszkyf.png',9),(34,'http://res.cloudinary.com/ddnepfewc/image/upload/v1714703393/zndxnftjdme4e4fzeoek.png',9),(35,'http://res.cloudinary.com/ddnepfewc/image/upload/v1714703398/said9smo69bo8110udji.png',9),(36,'http://res.cloudinary.com/ddnepfewc/image/upload/v1714703404/ripe5av4ewaan34myeej.png',9),(37,'http://res.cloudinary.com/ddnepfewc/image/upload/v1714733274/tckszzw3atqxuviw1yry.png',10),(38,'http://res.cloudinary.com/ddnepfewc/image/upload/v1714733284/yuixdusytsocsb5iqyby.png',10),(39,'http://res.cloudinary.com/ddnepfewc/image/upload/v1714733292/slzm32htgf6g840bnfyk.png',10),(40,'http://res.cloudinary.com/ddnepfewc/image/upload/v1714733299/f0slbkliziquuaf7vknj.png',10),(41,'http://res.cloudinary.com/ddnepfewc/image/upload/v1714734029/vzgopt0x7dl7oepot3uc.png',11),(42,'http://res.cloudinary.com/ddnepfewc/image/upload/v1714734036/qhcqrgyu0fvfry4entwy.png',11),(43,'http://res.cloudinary.com/ddnepfewc/image/upload/v1714734055/c0jg2tfy1nlvv0gvuxwr.png',11),(44,'http://res.cloudinary.com/ddnepfewc/image/upload/v1714735336/gjbo2qocgscbe9awdor9.png',12),(45,'http://res.cloudinary.com/ddnepfewc/image/upload/v1714735342/fp3llpkzg14s4topbjpa.png',12),(46,'http://res.cloudinary.com/ddnepfewc/image/upload/v1714735349/s92mmfhhoxo7cmgzjwf3.png',12),(47,'http://res.cloudinary.com/ddnepfewc/image/upload/v1714735358/t9xpfqxkylf3necmfoos.png',12),(48,'http://res.cloudinary.com/ddnepfewc/image/upload/v1714735875/jyufumvwgp9i4ojo7v0r.png',13),(49,'http://res.cloudinary.com/ddnepfewc/image/upload/v1714735893/wzbjsqxipexitucxpzqg.png',13),(50,'http://res.cloudinary.com/ddnepfewc/image/upload/v1714735898/d0pd0xhrphk3ckzroqkp.png',13),(51,'http://res.cloudinary.com/ddnepfewc/image/upload/v1714735913/fuflnwstvcfxkc4mphyb.png',13),(52,'http://res.cloudinary.com/ddnepfewc/image/upload/v1714737076/qkigiflsfakxkcy6luaf.png',14),(53,'http://res.cloudinary.com/ddnepfewc/image/upload/v1714737095/ru3xdvb7q2avhypzmbzd.png',14),(54,'http://res.cloudinary.com/ddnepfewc/image/upload/v1714737101/bv2pigybpgkgnoqhjgua.png',14),(55,'http://res.cloudinary.com/ddnepfewc/image/upload/v1714737107/cxqypq8j64tqb9lafi1z.png',14),(56,'http://res.cloudinary.com/ddnepfewc/image/upload/v1714806998/bqbrb4ozaiif5kprj3xc.png',15),(57,'http://res.cloudinary.com/ddnepfewc/image/upload/v1714738210/mpwectpcuwiw9vbcv7wo.png',15),(58,'http://res.cloudinary.com/ddnepfewc/image/upload/v1714738215/j8ylxqdiijkx1dxtlxns.png',15),(59,'http://res.cloudinary.com/ddnepfewc/image/upload/v1714738222/tdr7zcgiqicyysedisnn.png',15),(60,'http://res.cloudinary.com/ddnepfewc/image/upload/v1714738760/x5jpapi8n3ktydqz1287.png',16),(61,'http://res.cloudinary.com/ddnepfewc/image/upload/v1714738765/lpryqsvokiqne0nz2adi.png',16),(62,'http://res.cloudinary.com/ddnepfewc/image/upload/v1714738770/k2vy3vfvcxklw4khassi.png',16),(63,'http://res.cloudinary.com/ddnepfewc/image/upload/v1714738775/llgslmejluo8u0mwdipz.png',16),(64,'http://res.cloudinary.com/ddnepfewc/image/upload/v1714752390/fbcpj3g6dss9mmeqou9w.png',17),(65,'http://res.cloudinary.com/ddnepfewc/image/upload/v1714752402/yctnkcqsj7do9bzb25nr.png',17),(66,'http://res.cloudinary.com/ddnepfewc/image/upload/v1714752471/a05nojgzwwj87mv2cven.png',17),(67,'http://res.cloudinary.com/ddnepfewc/image/upload/v1714752478/fksfpgqhbchmqdtn18q3.png',17),(68,'http://res.cloudinary.com/ddnepfewc/image/upload/v1714752874/x9hfquqqhyk0frejyr5v.png',18),(69,'http://res.cloudinary.com/ddnepfewc/image/upload/v1714752883/ohg6fgyouxnkeaxmcjei.png',18),(70,'http://res.cloudinary.com/ddnepfewc/image/upload/v1714752892/icibu6wqjrg3volupqwy.png',18),(71,'http://res.cloudinary.com/ddnepfewc/image/upload/v1714752898/swiqmxadddnctrrfxlz5.png',18),(72,'http://res.cloudinary.com/ddnepfewc/image/upload/v1714753214/cj0nuncj8bymvamtjseh.png',19),(73,'http://res.cloudinary.com/ddnepfewc/image/upload/v1714753232/hvr6kfvuv8saskukfwlm.png',19),(74,'http://res.cloudinary.com/ddnepfewc/image/upload/v1714753239/jtn5uy73uxj84keosnsj.png',19),(75,'http://res.cloudinary.com/ddnepfewc/image/upload/v1714753249/f0rqhhvaonp8mmkyzgu.png',19),(76,'http://res.cloudinary.com/ddnepfewc/image/upload/v1714753860/qnhlrzmdyemhdd6kapiw.png',20),(77,'http://res.cloudinary.com/ddnepfewc/image/upload/v1714753916/roqsdctmav8g8bi7dyxr.png',20),(78,'http://res.cloudinary.com/ddnepfewc/image/upload/v1714753928/o2upwvlb1p5mwsvckutl.png',20),(79,'http://res.cloudinary.com/ddnepfewc/image/upload/v1714753941/cvs3bkyowpbc9ccqu9ni.png',20),(82,'http://res.cloudinary.com/ddnepfewc/image/upload/v1715704742/lut9zsh9mjhgfxmrcuhx.png',21);
/*!40000 ALTER TABLE `product_image` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `re_view_product`
--

DROP TABLE IF EXISTS `re_view_product`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `re_view_product` (
  `id` bigint NOT NULL AUTO_INCREMENT,
  `comment` varchar(255) DEFAULT NULL,
  `rating` int NOT NULL,
  `product_id` bigint DEFAULT NULL,
  `user_id` bigint DEFAULT NULL,
  `create_at` datetime(6) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `FKg1cbkk8dh1tbr3o63w3pieubv` (`product_id`),
  KEY `FK6vs896qqle7movnmmq5ro6bt8` (`user_id`),
  CONSTRAINT `FK6vs896qqle7movnmmq5ro6bt8` FOREIGN KEY (`user_id`) REFERENCES `users` (`id`),
  CONSTRAINT `FKg1cbkk8dh1tbr3o63w3pieubv` FOREIGN KEY (`product_id`) REFERENCES `product` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=14 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `re_view_product`
--

LOCK TABLES `re_view_product` WRITE;
/*!40000 ALTER TABLE `re_view_product` DISABLE KEYS */;
INSERT INTO `re_view_product` VALUES (1,'ngon nhưng đắt',4,11,2,'2024-05-04 15:35:29.965142'),(2,'tốt',5,7,6,'2024-05-05 15:03:53.606913'),(3,'5 sao',5,11,6,'2024-05-05 16:54:37.890749'),(4,'Không như quảng cáo',4,16,6,'2024-05-05 17:42:45.332143'),(5,'ngon',5,2,6,'2024-05-05 17:48:54.748036'),(6,'Khá Tốt',4,3,6,'2024-05-05 17:50:11.277424'),(7,'Bỏ đá ra còn được nửa cốc',2,1,3,'2024-05-05 17:57:24.887197'),(8,'Không có gì để nói',5,10,3,'2024-05-05 18:12:14.832205'),(9,'',0,7,3,'2024-05-05 18:12:14.839277'),(10,'Khá ngon',5,14,3,'2024-05-07 00:48:43.050946'),(11,'đồ uống không ngon',1,10,5,'2024-05-07 15:46:22.439927'),(12,'Khá ngon',5,19,4,'2024-05-14 22:53:20.260906'),(13,'ngon',5,13,6,'2024-05-14 23:42:57.052095');
/*!40000 ALTER TABLE `re_view_product` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `size_option`
--

DROP TABLE IF EXISTS `size_option`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `size_option` (
  `id` bigint NOT NULL AUTO_INCREMENT,
  `name` varchar(255) DEFAULT NULL,
  `price` int NOT NULL,
  `product_id` bigint DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `FKhr1whwu2x0e3lpiytqsng543i` (`product_id`),
  CONSTRAINT `FKhr1whwu2x0e3lpiytqsng543i` FOREIGN KEY (`product_id`) REFERENCES `product` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=50 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `size_option`
--

LOCK TABLES `size_option` WRITE;
/*!40000 ALTER TABLE `size_option` DISABLE KEYS */;
INSERT INTO `size_option` VALUES (1,'S',0,1),(2,'M',5000,1),(3,'L',10000,1),(4,'S',0,2),(5,'M',5000,2),(6,'L',10000,2),(7,'S',0,3),(8,'M',5000,3),(9,'L',10000,3),(11,'M',5000,4),(12,'L',10000,4),(13,'S',0,5),(14,'M',5000,5),(15,'L',10000,5),(16,'M',0,6),(17,'L',5000,6),(18,'M',0,7),(19,'L',5000,7),(20,'M',0,8),(21,'L',5000,8),(22,'M',0,9),(23,'L',5000,9),(24,'M',0,10),(25,'L',5000,10),(26,'S',0,11),(27,'M',5000,11),(28,'S',0,12),(29,'M',5000,12),(30,'S',0,13),(31,'M',5000,13),(32,'L',10000,13),(33,'M',0,14),(34,'L',5000,14),(35,'M',0,15),(36,'L',5000,15),(37,'M',0,16),(38,'L',5000,16),(39,'M',0,17),(40,'L',5000,17),(41,'S',0,4),(42,'Vị Truyền Thống',0,18),(43,'Vị Dừa',5000,18),(44,'200g',0,19),(45,'500g',50000,19),(46,'200g',0,20),(47,'500g',40000,20),(48,'M',0,21),(49,'L',4000,21);
/*!40000 ALTER TABLE `size_option` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `topping_option`
--

DROP TABLE IF EXISTS `topping_option`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `topping_option` (
  `id` bigint NOT NULL AUTO_INCREMENT,
  `name` varchar(255) DEFAULT NULL,
  `price` int NOT NULL,
  `product_id` bigint DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `FKl23gkm5c1786sfkfr7vw3h2q6` (`product_id`),
  CONSTRAINT `FKl23gkm5c1786sfkfr7vw3h2q6` FOREIGN KEY (`product_id`) REFERENCES `product` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=31 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `topping_option`
--

LOCK TABLES `topping_option` WRITE;
/*!40000 ALTER TABLE `topping_option` DISABLE KEYS */;
INSERT INTO `topping_option` VALUES (1,'Sữa',2000,1),(2,'Sữa',2000,2),(3,'Sữa',2000,3),(4,'Sữa',2000,4),(5,'Thạch ',5000,5),(6,'Thạch',5000,6),(7,'Lô hội',5000,6),(8,'Thạch',5000,7),(9,'Lô Hội',5000,7),(10,'Thạch',5000,8),(11,'Lô hội',5000,8),(12,'Thạch',5000,9),(13,'Thạch',5000,10),(14,'Lô hội',5000,10),(15,'Sữa đặc',5000,11),(16,'Sữa đặc',2000,12),(17,'Sữa',2000,13),(18,'Thạch',5000,13),(19,'Thạch',5000,14),(20,'Pupding trứng',5000,15),(21,'Trân Châu ',5000,15),(22,'Thạch',5000,15),(23,'Pupding socola',5000,16),(24,'Thạch',5000,16),(25,'Trân châu',5000,16),(26,'Thạch',5000,17),(27,'Trân châu',5000,17),(28,'Pupding trứng',5000,17),(29,'Đào miếng',5000,5),(30,'Sữa',5000,21);
/*!40000 ALTER TABLE `topping_option` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `user_roles`
--

DROP TABLE IF EXISTS `user_roles`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `user_roles` (
  `user_id` bigint NOT NULL,
  `roles` enum('USER','ADMIN') DEFAULT NULL,
  KEY `FKhfh9dx7w3ubf1co1vdev94g3f` (`user_id`),
  CONSTRAINT `FKhfh9dx7w3ubf1co1vdev94g3f` FOREIGN KEY (`user_id`) REFERENCES `users` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `user_roles`
--

LOCK TABLES `user_roles` WRITE;
/*!40000 ALTER TABLE `user_roles` DISABLE KEYS */;
INSERT INTO `user_roles` VALUES (1,'ADMIN'),(2,'USER'),(3,'USER'),(4,'USER'),(5,'USER'),(6,'USER'),(7,'USER');
/*!40000 ALTER TABLE `user_roles` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `users`
--

DROP TABLE IF EXISTS `users`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `users` (
  `id` bigint NOT NULL AUTO_INCREMENT,
  `email` varchar(255) DEFAULT NULL,
  `first_name` varchar(255) DEFAULT NULL,
  `last_name` varchar(255) DEFAULT NULL,
  `password` varchar(255) NOT NULL,
  `phone_number` varchar(255) DEFAULT NULL,
  `username` varchar(255) NOT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `UK_r43af9ap4edm43mmtq01oddj6` (`username`)
) ENGINE=InnoDB AUTO_INCREMENT=8 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `users`
--

LOCK TABLES `users` WRITE;
/*!40000 ALTER TABLE `users` DISABLE KEYS */;
INSERT INTO `users` VALUES (1,'admin@gmail.com','admin','promax','$2a$10$OGwdrtITpvj1R/DgYGwT6e/IUKP.g.8NjXWjhFiUcM0b13o.X.9rK',NULL,'admin'),(2,'markson2268@gmail.com',NULL,NULL,'$2a$10$PGHrnWQdQhYxPqUJWtigDuOZ8yLfygL5RJmiUdJQk9WU38fIaRG6S',NULL,'tien001'),(3,'trang@gmail.com',NULL,NULL,'$2a$10$V9SIRvgfZDX22ujcfQ4pxOgaFhMZzgEjdF1.nt.gG4dNVAAgu0j3e',NULL,'trang002'),(4,'hoang@gmail.com',NULL,NULL,'$2a$10$hOZlIRTjQuOpaPwBvYmFoO.oKjKYfoyRm/LbbTqI4ZbTUVkHxcwVq',NULL,'hoang003'),(5,'tien113@gmail.com',NULL,NULL,'$2a$10$zrL6OXuElcaLujVadoDkCefNTFX7uYW5/1gl5bPA71DkZUmFPysQG',NULL,'tien113'),(6,'tien004@gmail.com',NULL,NULL,'$2a$10$3i3cjSVnmTblno44Hpy93.TJOFvbG5ApIrlQ6ou.NC0/E2PkIrHmC',NULL,'khachhang'),(7,'demo@gmail.com',NULL,NULL,'$2a$10$ZqEkZbXKLrrj0L5SEQpdUONo9BucGJotbBku0LQLjVJiI885ftt8i',NULL,'demo');
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

-- Dump completed on 2024-07-31 21:41:37
