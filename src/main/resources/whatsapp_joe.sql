-- MySQL dump 10.13  Distrib 5.7.17, for macos10.12 (x86_64)
--
-- Host: localhost    Database: whatsapp
-- ------------------------------------------------------
-- Server version	5.7.17
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
-- Table structure for table `chats`
--
DROP TABLE IF EXISTS `chats`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `chats` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `chat_title` varchar(100) DEFAULT NULL,
  `created_at` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=hp8;
/*!40101 SET character_set_client = @saved_cs_client */;
--
-- Dumping data for table `chats`
--
LOCK TABLES `chats` WRITE;
/*!40000 ALTER TABLE `chats` DISABLE KEYS */;
INSERT INTO `chats` VALUES (1,'ryan and kim','2020-03-30 12:12:47'),(2,'ryan and martin','2020-03-30 12:13:05'),(3,'ryan and caden','2020-03-30 12:13:05'),(4,'cameron and martin','2020-03-30 14:49:42');
/*!40000 ALTER TABLE `chats` ENABLE KEYS */;
UNLOCK TABLES;
--
-- Table structure for table `messages`
--
DROP TABLE IF EXISTS `messages`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `messages` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `message` varchar(250) NOT NULL,
  `date_sent` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP,
  `chat_id` int(11) NOT NULL,
  `user_id` int(11) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=17 DEFAULT CHARSET=hp8;
/*!40101 SET character_set_client = @saved_cs_client */;
--
-- Dumping data for table `messages`
--
LOCK TABLES `messages` WRITE;
/*!40000 ALTER TABLE `messages` DISABLE KEYS */;
INSERT INTO `messages` VALUES (1,'hey Martin! Ryan here!','2020-03-30 12:15:18',2,3),(2,'hey Caden! Ryan here!','2020-03-30 12:15:19',3,3),(3,'hey Kim! Ryan here!','2020-03-30 12:15:20',1,3),(4,'how\'s it going?','2020-03-30 12:18:18',1,3),(5,'good! how are you?','2020-03-30 12:18:19',1,4),(6,'martin, you see this?','2020-03-30 12:18:20',2,3),(7,'yup!','2020-03-30 12:18:21',2,5),(8,'caden?','2020-03-30 12:18:22',3,3),(9,'yo!','2020-03-30 12:18:23',3,6),(12,'I\'m great','2020-03-30 13:55:46',1,3),(13,'Ryan testing Wednesday','2020-04-01 18:14:03',1,3),(14,'yo!','2020-04-01 20:07:41',2,3),(15,'not weird','2020-04-02 11:57:42',1,3),(16,'yes weird','2020-04-02 12:12:47',1,3);
/*!40000 ALTER TABLE `messages` ENABLE KEYS */;
UNLOCK TABLES;
--
-- Table structure for table `role`
--
DROP TABLE IF EXISTS `role`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `role` (
  `role_id` int(11) NOT NULL AUTO_INCREMENT,
  `role` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`role_id`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=hp8;
/*!40101 SET character_set_client = @saved_cs_client */;
--
-- Dumping data for table `role`
--
LOCK TABLES `role` WRITE;
/*!40000 ALTER TABLE `role` DISABLE KEYS */;
INSERT INTO `role` VALUES (1,'ADMIN'),(2,'USER');
/*!40000 ALTER TABLE `role` ENABLE KEYS */;
UNLOCK TABLES;
--
-- Table structure for table `user`
--
DROP TABLE IF EXISTS `user`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `user` (
  `user_id` int(11) NOT NULL AUTO_INCREMENT,
  `active` int(11) DEFAULT NULL,
  `email` varchar(255) NOT NULL,
  `first_name` varchar(255) NOT NULL,
  `last_name` varchar(255) NOT NULL,
  `password` varchar(255) NOT NULL,
  `photo_url` varchar(255) DEFAULT NULL,
  `user_name` varchar(255) NOT NULL,
  PRIMARY KEY (`user_id`)
) ENGINE=InnoDB AUTO_INCREMENT=7 DEFAULT CHARSET=hp8;
/*!40101 SET character_set_client = @saved_cs_client */;
--
-- Dumping data for table `user`
--
LOCK TABLES `user` WRITE;
/*!40000 ALTER TABLE `user` DISABLE KEYS */;
INSERT INTO `user` VALUES (3,1,'radesmond@gmail.com','Ryan','Desmond','$2a$10$8/lnjx/M91raa1EnFVS6PeROuX4lTbB6kQkuvEhgF73nDbKKWVJcS','https://media-exp1.licdn.com/dms/image/C4D03AQEpFj3V0mrWew/profile-displayphoto-shrink_200_200/0?e=1585785600&v=beta&t=eQC0MG6al-KPkDmnMiX1d-hC9ZAiwu5sSmHhpMZ7LV4','codingnomads'),(4,1,'kim@codingnomads.co','Kim ','Desmond','$2a$10$9iyaFmXFSgbp.UgKrCmoZemz7n3wJQ6XAGvXCC6k4/W6DxGpvY2x2','https://codingnomads.co/wp-content/uploads/2019/06/KimDesmond_Headshot_circle_200px.png','kimme'),(5,1,'martin@codingnomads.co','Martin','Breuss','$2a$10$8gONIjRKcBgaFJZ5wyH4wO9a0eoe5rAV0U20Z4ueIkpheRUxXicWG','https://files.realpython.com/media/martin_breuss_python_square.efb2b07faf9f.jpg','marty'),(6,1,'caden@codingnomads.co','Caden','Mackenzie','$2a$10$lVdCJeUiaG7KBoXqGi0eP.aqPVz8Y4XkgaQ6VHB4IyTASnX4rm//.','https://f6s-public.s3.amazonaws.com/profiles/2220235_original.jpg','caycay')'caden@codingnomads.co','Caden','Mackenzie','$2a$10$lVdCJeUiaG7KBoXqGi0eP.aqPVz8Y4XkgaQ6VHB4IyTASnX4rm//.','https://f6s-public.s3.amazonaws.com/profiles/2220235_original.jpg','caycay'caden@codingnomads.co','Caden','Mackenzie','$2a$10$lVdCJeUiaG7KBoXqGi0eP.aqPVz8Y4XkgaQ6VHB4IyTASnX4rm//.','https://f6s-public.s3.amazonaws.com/profiles/2220235_original.jpg','caycay')'caden@codingnomads.co','Caden','Mackenzie','$2a$10$lVdCJeUiaG7KBoXqGi0eP.aqPVz8Y4XkgaQ6VHB4IyTASnX4rm//.','https://f6s-public.s3.amazonaws.com/profiles/2220235_original.jpg','caycay');
/*!40000 ALTER TABLE `user` ENABLE KEYS */;
UNLOCK TABLES;
--
-- Table structure for table `user_role`
--
DROP TABLE IF EXISTS `user_role`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `user_role` (
  `user_id` int(11) NOT NULL,
  `role_id` int(11) NOT NULL,
  PRIMARY KEY (`user_id`,`role_id`),
  KEY `FKa68196081fvovjhkek5m97n3y` (`role_id`),
  CONSTRAINT `FK859n2jvi8ivhui0rl0esws6o` FOREIGN KEY (`user_id`) REFERENCES `user` (`user_id`),
  CONSTRAINT `FKa68196081fvovjhkek5m97n3y` FOREIGN KEY (`role_id`) REFERENCES `role` (`role_id`)
) ENGINE=InnoDB DEFAULT CHARSET=hp8;
/*!40101 SET character_set_client = @saved_cs_client */;
--
-- Dumping data for table `user_role`
--
LOCK TABLES `user_role` WRITE;
/*!40000 ALTER TABLE `user_role` DISABLE KEYS */;
INSERT INTO `user_role` VALUES (3,1),(4,1),(5,1),(6,1);
/*!40000 ALTER TABLE `user_role` ENABLE KEYS */;
UNLOCK TABLES;
--
-- Table structure for table `users_chats`
--
DROP TABLE IF EXISTS `users_chats`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `users_chats` (
  `user_id` int(11) NOT NULL,
  `chat_id` int(11) NOT NULL,
  PRIMARY KEY (`user_id`,`chat_id`),
  KEY `user_key_idx` (`user_id`),
  KEY `user_key2_idx` (`user_id`),
  KEY `chat_key2_idx` (`chat_id`)
) ENGINE=InnoDB DEFAULT CHARSET=hp8;
/*!40101 SET character_set_client = @saved_cs_client */;
--
-- Dumping data for table `users_chats`
--
LOCK TABLES `users_chats` WRITE;
/*!40000 ALTER TABLE `users_chats` DISABLE KEYS */;
INSERT INTO `users_chats` VALUES (3,1),(3,2),(3,3),(4,1),(5,2),(6,3);
/*!40000 ALTER TABLE `users_chats` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;
/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;
-- Dump completed on 2020-04-16 16:48:15