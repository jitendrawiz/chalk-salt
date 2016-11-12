/*
SQLyog Community v8.82 
MySQL - 5.5.41 : Database - chalkndust
*********************************************************************
*/ 
/*!40101 SET NAMES utf8 */;

/*!40101 SET SQL_MODE=''*/;

/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;
CREATE DATABASE /*!32312 IF NOT EXISTS*/`chalkndust` /*!40100 DEFAULT CHARACTER SET latin1 */;

USE `chalkndust`;

/*Table structure for table `cst_academic_details` */

DROP TABLE IF EXISTS `cst_academic_details`;

CREATE TABLE `cst_academic_details` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `student_class_id` int(11) NOT NULL,
  `percentage` decimal(5,2) DEFAULT NULL,
  `previous_school` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=20 DEFAULT CHARSET=latin1;

/*Data for the table `cst_academic_details` */

insert  into `cst_academic_details`(`id`,`student_class_id`,`percentage`,`previous_school`) values (8,1,'0.00',NULL),(11,3,'0.00',NULL),(12,1,'0.00',NULL),(13,1,'100.00','tagore'),(14,1,'0.00',NULL),(15,2,'0.00',NULL),(16,3,'0.00',NULL),(17,6,'0.00',NULL),(18,2,'50.00','maharshi dayanand'),(19,2,'0.00',NULL);

/*Table structure for table `cst_achievements` */

DROP TABLE IF EXISTS `cst_achievements`;

CREATE TABLE `cst_achievements` (
  `achievement_id` int(11) NOT NULL AUTO_INCREMENT,
  `achievement_uuid` varchar(100) DEFAULT NULL,
  `class_id` int(11) DEFAULT NULL,
  `student_id` int(11) DEFAULT NULL,
  `achievement_description` text,
  `file_name` varchar(100) DEFAULT NULL,
  `created_date` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP,
  `modified_date` timestamp NOT NULL DEFAULT '0000-00-00 00:00:00',
  PRIMARY KEY (`achievement_id`)
) ENGINE=InnoDB AUTO_INCREMENT=28 DEFAULT CHARSET=latin1;

/*Data for the table `cst_achievements` */

insert  into `cst_achievements`(`achievement_id`,`achievement_uuid`,`class_id`,`student_id`,`achievement_description`,`file_name`,`created_date`,`modified_date`) values (23,'b6ab7e5f-cafb-47a0-b478-050ae7a98fa2',1,24,'one of the best student of all time.','download (1).png','2016-10-02 11:59:32','2016-10-02 11:59:32'),(24,'2f3705a1-2696-4a19-8c0a-d1d3168f4083',1,23,'Below is an interactive demo that lets you explore the visual results of the different settings:','profile.jpg','2016-10-05 23:35:26','2016-10-05 23:35:26'),(25,'b3f9ab68-ffa8-44b4-9a2e-9583232445e7',3,27,'sa jkasjdfkjasklfjklajsdfjkadjfkjasdkfjklasjdfkljasdfjasdklfjaklsdjfklajsdfkljasdf','WIN_20160502_143547.JPG','2016-10-06 00:13:18','2016-10-06 00:13:18'),(26,'25d96c48-39ca-4ae1-a308-5ffdd9990c8b',3,22,'ajskldfjklasjfj9ejlajskldfjlkajsdfklasfkljasdklfjasdf','WIN_20151125_232219.JPG','2016-10-06 00:13:38','2016-10-06 00:13:38'),(27,'87f6b217-01e8-4d9c-ba60-089a5b5def39',2,29,'student acchieventement detail aar filos hat','IMG_20150403_132949.JPG','2016-11-06 10:15:26','2016-11-06 10:15:26');

/*Table structure for table `cst_class_subject_mapping` */

DROP TABLE IF EXISTS `cst_class_subject_mapping`;

CREATE TABLE `cst_class_subject_mapping` (
  `class_subject_id` int(11) NOT NULL AUTO_INCREMENT,
  `class_id` int(11) DEFAULT NULL,
  `subject_id` int(11) DEFAULT NULL,
  PRIMARY KEY (`class_subject_id`)
) ENGINE=InnoDB AUTO_INCREMENT=40 DEFAULT CHARSET=latin1;

/*Data for the table `cst_class_subject_mapping` */

insert  into `cst_class_subject_mapping`(`class_subject_id`,`class_id`,`subject_id`) values (1,1,1),(2,1,2),(3,1,3),(4,1,4),(6,2,1),(7,2,2),(8,2,3),(9,2,4),(11,3,1),(12,3,2),(13,3,3),(14,3,4),(16,4,1),(17,4,5),(18,4,6),(21,5,1),(22,5,5),(23,5,6),(26,6,7),(27,7,8),(28,8,9),(29,1,10),(30,2,10),(31,3,10),(32,4,10),(33,5,10),(34,6,10),(35,7,10),(36,8,10),(37,1,9),(38,2,9),(39,3,9);

/*Table structure for table `cst_class_subjects` */

DROP TABLE IF EXISTS `cst_class_subjects`;

CREATE TABLE `cst_class_subjects` (
  `subject_id` int(11) NOT NULL AUTO_INCREMENT,
  `subject_name` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`subject_id`)
) ENGINE=InnoDB AUTO_INCREMENT=11 DEFAULT CHARSET=latin1;

/*Data for the table `cst_class_subjects` */

insert  into `cst_class_subjects`(`subject_id`,`subject_name`) values (1,'Maths'),(2,'English'),(3,'Science'),(4,'Social Studies'),(5,'Physics'),(6,'Chemistry'),(7,'National Defence Academy'),(8,'NATA Coaching'),(9,'Olympiads/NTSE'),(10,'General');

/*Table structure for table `cst_class_type` */

DROP TABLE IF EXISTS `cst_class_type`;

CREATE TABLE `cst_class_type` (
  `class_id` int(11) NOT NULL AUTO_INCREMENT,
  `class_name` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`class_id`),
  UNIQUE KEY `class_id` (`class_id`)
) ENGINE=InnoDB AUTO_INCREMENT=8 DEFAULT CHARSET=latin1;

/*Data for the table `cst_class_type` */

insert  into `cst_class_type`(`class_id`,`class_name`) values (1,'Foundation 8'),(2,'Foundation 9'),(3,'Foundation 10'),(4,'IIT-JEE 11'),(5,'IIT-JEE 12'),(6,'NDA'),(7,'Architecture');

/*Table structure for table `cst_contacts` */

DROP TABLE IF EXISTS `cst_contacts`;

CREATE TABLE `cst_contacts` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `address` varchar(100) DEFAULT NULL,
  `city` varchar(50) DEFAULT NULL,
  `state` varchar(50) DEFAULT NULL,
  `country` varchar(50) DEFAULT NULL,
  `pincode` varchar(10) DEFAULT NULL,
  `mobile` varchar(15) DEFAULT NULL,
  `landline` varchar(15) DEFAULT NULL,
  `fax` varchar(15) DEFAULT NULL,
  `email` varchar(50) DEFAULT NULL,
  `corsAddress` varchar(100) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=28 DEFAULT CHARSET=latin1;

/*Data for the table `cst_contacts` */

insert  into `cst_contacts`(`id`,`address`,`city`,`state`,`country`,`pincode`,`mobile`,`landline`,`fax`,`email`,`corsAddress`) values (16,'house no 380','Faridabad','Haryana','IN','121004','1111111111','12121212121',NULL,'abhishek.kumar627@gmail.com','corsaddress'),(19,'house no 343','Gurgaon','Haryana','IN','12104',NULL,NULL,NULL,'abhishek.kumar627@gmail.com',NULL),(20,'house no 380','Faridabad','Haryana','IN','1212121','8823823232','23232332',NULL,'abhishek.kumar627@gmail.com','text'),(21,'house no 390 good','Faridabad','Haryana','IN','121004','9711877121','68768766343',NULL,'abhishek.kumar67@gmail.com','hjghjhghadfads'),(22,'house no 43','Faridabad','Haryana','IN','2323232',NULL,NULL,NULL,'raman@gmail.com',NULL),(23,'house no 43','Faridabad','Haryana','IN','2323232',NULL,NULL,NULL,'abhishek.kumar627@gmail.com',NULL),(24,'house','Faridabad','Haryana','IN','232323',NULL,NULL,NULL,'abhishek.kumar627@gmail.com',NULL),(25,'sfasd','Faridabad','Haryana','IN','2323232',NULL,NULL,NULL,'abhishek.kumar627@gmail.com',NULL),(26,'ballabgarh','Faridabad','Haryana','IN','121004','834759851','232323',NULL,'abhishek.kumar627@gmail.com','house no 380'),(27,'380','Faridabad','Haryana','IN','121004',NULL,NULL,NULL,'abhishek.kumar627@gmail.com',NULL);

/*Table structure for table `cst_discussion_topic_comments` */

DROP TABLE IF EXISTS `cst_discussion_topic_comments`;

CREATE TABLE `cst_discussion_topic_comments` (
  `discussion_comment_id` int(11) NOT NULL AUTO_INCREMENT,
  `discussion_topic_id` int(11) DEFAULT NULL,
  `general_comments` text,
  `created_date` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP,
  `modified_date` timestamp NULL DEFAULT NULL,
  `user_securUuid` varchar(100) NOT NULL,
  `comment_uuid` varchar(100) NOT NULL,
  PRIMARY KEY (`discussion_comment_id`)
) ENGINE=InnoDB AUTO_INCREMENT=87 DEFAULT CHARSET=latin1;

/*Data for the table `cst_discussion_topic_comments` */

insert  into `cst_discussion_topic_comments`(`discussion_comment_id`,`discussion_topic_id`,`general_comments`,`created_date`,`modified_date`,`user_securUuid`,`comment_uuid`) values (63,88,'new comment added successfully','2016-05-05 23:14:11','2016-06-23 23:39:06','41b0e148-5069-4b00-8d94-735d064e9dde','22afafe7-adbe-4d49-a40a-56ade368990a'),(64,94,'new comment uphjkj','2016-05-05 23:32:38','2016-09-09 20:24:45','41b0e148-5069-4b00-8d94-735d064e9dde','560f257c-9868-4f6d-9052-a18c5a74a611'),(65,90,'yes it is good idea','2016-05-05 23:39:10','2016-05-05 23:39:10','41b0e148-5069-4b00-8d94-735d064e9dde','59a45b1f-e8f3-4692-9ee9-670a77c37686'),(66,88,'jsfasdlfjads hellsfasdfklasd','2016-05-06 00:34:43','2016-05-07 13:34:44','41b0e148-5069-4b00-8d94-735d064e9dde','17843262-9509-426d-ae9d-4f545c7432cd'),(67,88,'how are you sir','2016-05-07 13:35:04','2016-05-07 13:35:04','41b0e148-5069-4b00-8d94-735d064e9dde','deaaf493-2e26-4ebe-8e7a-af296d35f62e'),(69,90,'test comment updated one now','2016-05-07 16:25:34','2016-05-07 16:26:20','34368fb4-6047-419d-bfcd-003b0d1dc809','d8269825-957f-47dc-8fd6-4f1e4ee4e2e9'),(70,91,'yes it is not an updated scandal','2016-07-21 01:13:19','2016-07-21 01:13:38','41b0e148-5069-4b00-8d94-735d064e9dde','e6e05356-ba14-47b6-9920-8667710df18f'),(71,98,'nothing','2016-07-24 17:53:16','2016-07-24 17:53:16','41b0e148-5069-4b00-8d94-735d064e9dde','2340da97-3871-4ae7-a4b0-4c9c92f21b60'),(72,68,'hello how are you','2016-09-22 20:44:57','2016-09-22 20:44:57','41b0e148-5069-4b00-8d94-735d064e9dde','fcd32c0a-6f33-4e27-9c17-65887027642a'),(73,68,'i am fine how are o','2016-09-22 20:45:05','2016-09-22 20:45:05','41b0e148-5069-4b00-8d94-735d064e9dde','a10f8b97-c4d3-4c92-b415-7e799ddc9097'),(74,68,'thanks for your comment','2016-09-22 20:45:12','2016-09-22 20:45:12','41b0e148-5069-4b00-8d94-735d064e9dde','a4b54d7a-03f9-4b53-8b4e-ecf3c77d4f79'),(75,68,'on im not ahere','2016-09-22 20:45:19','2016-09-22 20:45:19','41b0e148-5069-4b00-8d94-735d064e9dde','7e3cb525-e84f-43ca-abb8-1d168a7cec75'),(76,68,'will igt shaisdfasdf','2016-09-22 20:45:25','2016-09-22 20:45:25','41b0e148-5069-4b00-8d94-735d064e9dde','11b1bf43-303f-4232-8cef-ee55243de7ab'),(77,68,'asdflaksdfkla','2016-09-22 20:45:34','2016-09-22 20:45:34','41b0e148-5069-4b00-8d94-735d064e9dde','5f4fd5d1-dd3e-4a16-91ea-2e7e04a82c02'),(78,105,'hjghjg','2016-10-23 16:44:52','2016-10-23 16:44:52','41b0e148-5069-4b00-8d94-735d064e9dde','d96ff332-306c-4122-b23a-9577d282b7f4'),(79,105,'njinj kklj','2016-10-23 16:45:00','2016-11-03 01:54:45','41b0e148-5069-4b00-8d94-735d064e9dde','e6003c28-cf68-4aea-8b5e-87f7f9d37706'),(80,105,'hhjgh','2016-10-23 16:45:08','2016-10-23 16:45:08','41b0e148-5069-4b00-8d94-735d064e9dde','c2e4643a-0317-4a1e-ab81-61aa25601da7'),(81,105,'sddfsfsf','2016-10-23 16:48:57','2016-10-23 16:48:57','41b0e148-5069-4b00-8d94-735d064e9dde','9d27b7a8-e08b-4422-964d-568f48ade4aa'),(82,91,'asdfafd','2016-10-23 16:58:27','2016-10-23 16:58:27','41b0e148-5069-4b00-8d94-735d064e9dde','c56ada3b-9893-4e60-a5e3-24d78c408970'),(83,105,'hghgjhgh','2016-11-03 20:28:22','2016-11-03 20:28:22','41b0e148-5069-4b00-8d94-735d064e9dde','01faa584-64a6-4a2d-b216-c5fa2b063a0a'),(84,105,'jkhkjhlkjll;l;k','2016-11-03 20:28:30','2016-11-03 20:28:30','41b0e148-5069-4b00-8d94-735d064e9dde','e9a538f5-d5dd-4b13-8e73-92e06105b3b8'),(85,104,'hjhkjh','2016-11-03 20:31:56','2016-11-03 20:31:56','41b0e148-5069-4b00-8d94-735d064e9dde','402c75b8-d680-4249-8d80-c984d03e0af4'),(86,93,'test coometsn 1','2016-11-06 10:27:55','2016-11-06 10:27:55','3be2050d-e361-482a-9537-3f9157b5e637','ec44a74a-7ce7-45ee-aa6c-1d6199803d72');

/*Table structure for table `cst_discussion_topic_comments_history` */

DROP TABLE IF EXISTS `cst_discussion_topic_comments_history`;

CREATE TABLE `cst_discussion_topic_comments_history` (
  `history_id` int(11) NOT NULL AUTO_INCREMENT,
  `discussion_comment_id` int(11) NOT NULL,
  `discussion_topic_id` int(11) DEFAULT NULL,
  `general_comments` text,
  `created_date` timestamp NULL DEFAULT NULL,
  `modified_date` timestamp NULL DEFAULT NULL,
  `user_securUuid` varchar(100) NOT NULL,
  `comment_uuid` varchar(100) NOT NULL,
  `change_date` datetime NOT NULL,
  PRIMARY KEY (`history_id`)
) ENGINE=InnoDB AUTO_INCREMENT=66 DEFAULT CHARSET=latin1;

/*Data for the table `cst_discussion_topic_comments_history` */

insert  into `cst_discussion_topic_comments_history`(`history_id`,`discussion_comment_id`,`discussion_topic_id`,`general_comments`,`created_date`,`modified_date`,`user_securUuid`,`comment_uuid`,`change_date`) values (1,45,4,'new comments updated last','2016-02-15 23:33:34','2016-02-15 23:33:34','b3023d41-494c-471b-ac87-9ea93695b198','4da9ef39-06a0-4b27-a1ac-4e1f69c0c6b0','2016-02-16 22:43:56'),(2,42,4,'new  comment added here by','2016-02-14 11:10:14','2016-02-14 11:11:05','b3023d41-494c-471b-ac87-9ea93695b198','806bee47-7586-4ca1-a75f-922e3ce74bf3','2016-02-16 22:45:44'),(3,50,26,'topic 2 comment 2','2016-02-16 23:00:05','2016-02-16 23:00:05','7c001cf1-0326-4e6d-8d0f-5b46f937afb5','fb47fcbf-9a62-4c99-91fa-da46eedea8ea','2016-02-16 23:01:17'),(4,49,26,'topic 2 comment 1','2016-02-16 22:59:55','2016-02-16 22:59:55','7c001cf1-0326-4e6d-8d0f-5b46f937afb5','2ee181ce-a877-4106-ac07-23193694e220','2016-02-16 23:02:07'),(5,46,25,'comment 1','2016-02-16 22:59:11','2016-02-16 22:59:11','7c001cf1-0326-4e6d-8d0f-5b46f937afb5','7ed922fe-376b-449b-aa98-29c8a0edd9be','2016-02-16 23:04:35'),(6,47,25,'comment 2','2016-02-16 22:59:21','2016-02-16 22:59:21','7c001cf1-0326-4e6d-8d0f-5b46f937afb5','0b2b94fd-6171-4983-84bb-a2b1720a3696','2016-02-16 23:04:35'),(7,48,25,'comment 3','2016-02-16 22:59:39','2016-02-16 22:59:39','7c001cf1-0326-4e6d-8d0f-5b46f937afb5','447aa66b-f50f-47b9-9d9c-b2161e6d9eb4','2016-02-16 23:04:35'),(8,40,4,'new comment udpated','2016-02-12 20:53:57','2016-02-12 20:54:10','b3023d41-494c-471b-ac87-9ea93695b198','9bc2d9a9-de2a-479c-ad40-925fd2edb619','2016-02-18 21:03:52'),(9,39,4,'adfdfasdf','2016-02-11 21:58:09','2016-02-11 21:58:09','b3023d41-494c-471b-ac87-9ea93695b198','91094af9-4c40-4e2c-98ee-e9e69c3aeb12','2016-02-18 21:04:10'),(10,38,4,'adfadsfs','2016-02-11 21:58:01','2016-02-11 21:58:01','b3023d41-494c-471b-ac87-9ea93695b198','2f5644a1-45a1-4c61-9c73-109d2a73fbab','2016-02-18 21:04:32'),(11,37,4,'safasdfasd','2016-02-11 21:57:55','2016-02-11 21:57:55','b3023d41-494c-471b-ac87-9ea93695b198','37a43330-ad87-4b5f-a922-e7927fa2366b','2016-02-18 21:04:48'),(12,36,4,'sidhth','2016-02-11 21:57:49','2016-02-11 21:57:49','b3023d41-494c-471b-ac87-9ea93695b198','00507093-2314-45e8-8e19-7836afdd4375','2016-02-18 21:08:31'),(13,35,4,'firfth','2016-02-11 21:57:41','2016-02-11 21:57:41','b3023d41-494c-471b-ac87-9ea93695b198','5f046d26-2c40-409e-af7c-3cda20bf5ed5','2016-02-18 21:08:49'),(14,34,4,'fourth comment','2016-02-11 21:57:35','2016-02-11 21:57:35','b3023d41-494c-471b-ac87-9ea93695b198','6b07085c-cdf0-4db4-9cfa-3d1deea04780','2016-02-18 21:09:04'),(15,33,4,'third comment','2016-02-11 21:57:27','2016-02-11 21:57:27','b3023d41-494c-471b-ac87-9ea93695b198','dca8e361-6971-4da8-ae11-9fde5f448ad3','2016-02-18 21:10:53'),(16,32,4,'second comment','2016-02-11 21:57:19','2016-02-11 21:57:19','b3023d41-494c-471b-ac87-9ea93695b198','c90071f9-d747-4186-bdc3-d410c3e5948d','2016-02-18 21:11:50'),(17,31,4,'first comment','2016-02-11 21:57:11','2016-02-11 21:57:11','b3023d41-494c-471b-ac87-9ea93695b198','a73352c8-127a-4176-b222-63a4cb1c1101','2016-02-18 21:18:38'),(18,30,4,'new coooooooooooooooooooooooooooooooooo','2016-02-11 21:57:02','2016-02-11 21:57:02','b3023d41-494c-471b-ac87-9ea93695b198','1e9ecfeb-3783-45ff-8166-977a8f152c00','2016-02-18 21:20:34'),(19,28,4,'new comment updated successfully','2016-02-10 20:13:50','2016-02-10 20:14:15','b3023d41-494c-471b-ac87-9ea93695b198','411328bc-8e93-40f2-8524-ac436a3e0215','2016-02-18 21:26:11'),(20,10,4,'hello updated','2016-02-03 20:18:37','2016-02-18 21:36:40','b3023d41-494c-471b-ac87-9ea93695b198','8','2016-02-18 21:50:13'),(21,24,4,'topic comments updated','2016-02-06 11:08:37','2016-02-18 21:36:29','b3023d41-494c-471b-ac87-9ea93695b198','f7ea58ac-9334-4ec0-a6d2-406366c82b84','2016-02-18 21:50:13'),(22,42,4,'new comment added successfully','2016-02-18 21:36:57','2016-02-18 21:36:57','b3023d41-494c-471b-ac87-9ea93695b198','f3e5c035-d00c-4746-918a-834d3fa3ec24','2016-02-18 21:50:13'),(23,1,1,'How are you going TO CHANGE the class IN a way that\r\n How are you going TO CHANGE the class IN a way that\r\n How are you going TO CHANGE the class IN a way that\r\n How are you going TO CHANGE the class IN a way that\r\n How are you going TO CHANGE the class IN a way that\r\n How are you going TO CHANGE the class IN a way that\r\n How are you going TO CHANGE the class IN a way that','2016-01-30 17:36:11','2016-01-30 17:36:11','4e0b6910-5650-497b-81c5-4d4e96c06d6d','4e0b6910-5650-497b-81c5-4d4e96c06d6d','2016-02-19 20:26:23'),(24,3,2,'hjkgkjg','2016-01-30 18:07:45','2016-01-30 18:07:45','4e0b6910-5650-497b-81c5-4d4e96c06d6d','1','2016-02-19 20:26:23'),(25,4,2,'dhdghdg','2016-01-30 18:07:49','2016-01-30 18:07:49','4e0b6910-5650-497b-81c5-4d4e96c06d6d','2','2016-02-19 20:26:23'),(26,5,2,'dghdfhhdh','2016-01-30 18:07:51','2016-01-30 18:07:51','4e0b6910-5650-497b-81c5-4d4e96c06d6d','3','2016-02-19 20:26:23'),(27,7,1,'fgfsfgggfs','2016-01-30 18:07:59','2016-01-30 18:07:59','4e0b6910-5650-497b-81c5-4d4e96c06d6d','5','2016-02-19 20:26:23'),(28,15,1,'new comment by abhishek','2016-02-06 00:27:22','2016-02-06 00:27:22','4e0b6910-5650-497b-81c5-4d4e96c06d6d','3249d68a-4501-4b6b-a8b6-950cb487cdc8','2016-02-19 20:26:23'),(29,16,1,'new commet by jitendra','2016-02-06 00:27:42','2016-02-06 00:27:42','4e0b6910-5650-497b-81c5-4d4e96c06d6d','6e4508a8-6c9b-4aa7-8404-edd942d96834','2016-02-19 20:26:23'),(30,17,1,'test comment','2016-02-06 00:29:05','2016-02-06 00:29:05','4e0b6910-5650-497b-81c5-4d4e96c06d6d','6dea49aa-419e-441d-a7d5-91c6526a09d3','2016-02-19 20:26:23'),(31,18,1,'new comment by jitendra','2016-02-06 00:29:21','2016-02-06 00:29:21','4e0b6910-5650-497b-81c5-4d4e96c06d6d','f70d5539-d684-41ba-973e-71ced2d9c795','2016-02-19 20:26:23'),(32,19,1,'new com','2016-02-06 00:31:16','2016-02-06 00:31:16','4e0b6910-5650-497b-81c5-4d4e96c06d6d','c9f8992b-6aef-41e5-aaf2-65f9f0493003','2016-02-19 20:26:23'),(33,20,1,'hello','2016-02-06 00:32:03','2016-02-06 00:32:03','4e0b6910-5650-497b-81c5-4d4e96c06d6d','cddb06c6-c51b-49a0-8009-af809e899899','2016-02-19 20:26:23'),(34,21,1,'hello','2016-02-06 00:32:18','2016-02-06 00:32:18','4e0b6910-5650-497b-81c5-4d4e96c06d6d','1349cf09-fc94-4fb4-8426-91866fa51c22','2016-02-19 20:26:23'),(35,22,1,'new comment to be donw','2016-02-06 09:43:04','2016-02-06 09:43:04','4e0b6910-5650-497b-81c5-4d4e96c06d6d','6febc6e8-f241-410a-abd7-9a080a501d76','2016-02-19 20:26:23'),(36,43,7,'new coment','2016-02-19 20:25:23','2016-02-19 20:25:23','b3023d41-494c-471b-ac87-9ea93695b198','ce7fda6d-8ebf-4b3b-97ee-f844f1477f43','2016-02-19 20:27:27'),(37,2,1,'new comment to be displayed now','2016-01-30 18:07:41','2016-02-06 17:48:19','b3023d41-494c-471b-ac87-9ea93695b198','b3023d41-494c-471b-ac87-9ea93695b198','2016-02-21 17:01:20'),(38,9,2,'ffdgfdh','2016-01-30 18:08:16','2016-01-30 18:08:16','b3023d41-494c-471b-ac87-9ea93695b198','7','2016-02-21 17:01:20'),(39,11,1,'not null to be update now','2016-02-06 00:19:42','2016-02-06 17:49:04','b3023d41-494c-471b-ac87-9ea93695b198','8df20e96-7bb5-47b5-b2fe-1ecf1266d619','2016-02-21 17:01:20'),(40,12,1,'not null','2016-02-06 00:24:34','2016-02-06 00:24:34','b3023d41-494c-471b-ac87-9ea93695b198','4f05b55b-66d7-4b4a-b1fd-bdde9c5757b1','2016-02-21 17:01:20'),(41,13,1,'new comments here','2016-02-06 00:25:04','2016-02-06 00:25:04','b3023d41-494c-471b-ac87-9ea93695b198','7ea03ef1-633f-4161-81ea-303005d46cde','2016-02-21 17:01:20'),(42,14,1,'newcomment by abhishek','2016-02-06 00:25:32','2016-02-06 00:25:32','b3023d41-494c-471b-ac87-9ea93695b198','e0af559c-f50f-40f2-bdd3-e0dbd797ddb9','2016-02-21 17:01:20'),(43,25,1,'new cimmm udpated now','2016-02-06 16:02:11','2016-02-06 17:49:18','b3023d41-494c-471b-ac87-9ea93695b198','bf62e231-5c51-460f-b49b-a7d40720a977','2016-02-21 17:01:20'),(44,26,1,'newcomment by abhishek','2016-02-06 16:38:13','2016-02-06 16:38:13','b3023d41-494c-471b-ac87-9ea93695b198','66a575f4-9d9c-439f-a35f-235524aeff3f','2016-02-21 17:01:20'),(45,41,3,'hello','2016-02-12 20:55:05','2016-02-12 20:55:05','b3023d41-494c-471b-ac87-9ea93695b198','3eab7a6e-1c80-4db9-abc2-ec1c1ca596db','2016-02-21 17:01:20'),(46,44,7,'new comment','2016-02-19 22:31:17','2016-02-19 22:31:17','b3023d41-494c-471b-ac87-9ea93695b198','8cd2e64c-7188-4245-bc8a-cef92398227b','2016-02-21 17:01:20'),(47,45,7,'totally new comment here','2016-02-19 22:34:24','2016-02-19 22:34:24','b3023d41-494c-471b-ac87-9ea93695b198','e656b8b7-addd-456a-8804-2bec43bae640','2016-02-21 17:01:20'),(48,46,40,'sfndjkhga; nvjk;bndfbn;o   kgjnjkhldfb fdlkxbvnlhfnf; jfjkh fhb;l viglf ub,bhft lgiugukyf kvndu;\nhvkjfhbj;h. bfbn bdfbnlb b lhilib;h hfivbhbvcg vug jbhgdj','2016-02-21 16:47:56','2016-02-21 16:47:56','b3023d41-494c-471b-ac87-9ea93695b198','fa2baea0-6260-4154-bb0e-5b58869826c5','2016-02-21 17:01:20'),(49,58,92,'President Barack Obama is promoting what he deems a series of successes this month in his foreign policy with Iran.\r\n \r\n Last week, 10 U.S. sailors were taken captive by Iran after their patrol boats drifted into Iranian waters. They were released the following day, and the Obama administration hailed the peaceful episode as a product of the warming relationship between the United States and Iran.','2016-02-24 23:09:17','2016-02-24 23:09:17','41b0e148-5069-4b00-8d94-735d064e9dde','61104bfa-dc5a-4f33-80b1-623b14afcf31','2016-05-22 10:30:57'),(50,59,92,'duster','2016-02-24 23:09:24','2016-02-24 23:09:24','41b0e148-5069-4b00-8d94-735d064e9dde','1be088c4-6eec-4d63-8784-9b6cf1762868','2016-05-22 10:30:57'),(51,60,92,'President Barack Obama is promoting what he deems a series of successes this month in his foreign policy with Iran.\r\n \r\n Last week, 10 U.S. sailors were taken captive by Iran after their patrol boats drifted into Iranian waters. They were released the following day, and the Obama administration hailed the peaceful episode as a product of the warming relationship between the United States and Iran.','2016-03-13 12:22:40','2016-03-13 12:37:12','34368fb4-6047-419d-bfcd-003b0d1dc809','368ba873-496b-4b7f-8f0d-a940afd98600','2016-05-22 10:30:57'),(52,61,92,'hello','2016-05-03 23:10:53','2016-05-03 23:10:53','41b0e148-5069-4b00-8d94-735d064e9dde','7fcba79e-58ef-4b95-946d-639d525ad0f7','2016-05-22 10:30:57'),(53,62,92,'how are you sir i am abhishek kumar can you call me by my name that is abhishek kumar','2016-05-03 23:15:16','2016-05-03 23:15:16','41b0e148-5069-4b00-8d94-735d064e9dde','c02c2ffa-e1dd-4f57-ad12-c27218545022','2016-05-22 10:30:57'),(54,47,7,'1','2016-02-24 23:06:18','2016-02-24 23:06:18','41b0e148-5069-4b00-8d94-735d064e9dde','0acb5036-9bfc-4cdb-86e6-8fc023928de9','2016-05-25 23:10:22'),(55,48,7,'new comment 1','2016-02-24 23:06:26','2016-02-24 23:06:26','41b0e148-5069-4b00-8d94-735d064e9dde','4aae1a38-a736-4e34-9063-c3da37245480','2016-05-25 23:10:22'),(56,49,7,'new comment 2','2016-02-24 23:06:34','2016-02-24 23:06:34','41b0e148-5069-4b00-8d94-735d064e9dde','4f8c1919-89e2-4d6f-9ad2-3beeb62792e9','2016-05-25 23:10:22'),(57,50,7,'new comment 3','2016-02-24 23:06:41','2016-02-24 23:06:41','41b0e148-5069-4b00-8d94-735d064e9dde','582c12e7-e51e-48d4-8442-9a8cf10e1699','2016-05-25 23:10:22'),(58,51,7,'new comment 54','2016-02-24 23:07:00','2016-02-24 23:07:00','41b0e148-5069-4b00-8d94-735d064e9dde','7a7a8624-471c-4b05-a8ee-d5a95bba434f','2016-05-25 23:10:22'),(59,52,7,'new comment 4544','2016-02-24 23:07:08','2016-02-24 23:07:08','41b0e148-5069-4b00-8d94-735d064e9dde','c14557d7-4ffa-4021-be2e-b66bca45d61d','2016-05-25 23:10:22'),(60,53,7,'1111','2016-02-24 23:07:20','2016-02-24 23:07:20','41b0e148-5069-4b00-8d94-735d064e9dde','c6635556-d8c6-4fec-babc-de602b7c569c','2016-05-25 23:10:22'),(61,54,7,'new commenadadfadfads','2016-02-24 23:07:32','2016-02-24 23:07:32','41b0e148-5069-4b00-8d94-735d064e9dde','65f62d7e-e1af-403b-87d1-e540dd5d7f41','2016-05-25 23:10:22'),(62,55,7,'old comment','2016-02-24 23:08:02','2016-02-24 23:08:02','41b0e148-5069-4b00-8d94-735d064e9dde','cc1ff2cb-7a65-4d50-a9f5-9cba774322fa','2016-05-25 23:10:22'),(63,56,7,'abortions','2016-02-24 23:08:55','2016-04-01 22:11:15','41b0e148-5069-4b00-8d94-735d064e9dde','bbca1ed2-97ea-4d6f-b122-1b1258796d0b','2016-05-25 23:10:22'),(64,57,7,'hello','2016-02-24 23:09:09','2016-02-24 23:09:09','41b0e148-5069-4b00-8d94-735d064e9dde','effbfe94-ee63-4ac8-b91c-fc006ad3ed01','2016-05-25 23:10:22'),(65,68,88,'lkjljl','2016-05-07 13:38:47','2016-05-07 13:38:47','34368fb4-6047-419d-bfcd-003b0d1dc809','cfec7c9e-e38b-47c0-ace8-ce3e053028b5','2016-06-20 23:42:08');

/*Table structure for table `cst_discussion_topics` */

DROP TABLE IF EXISTS `cst_discussion_topics`;

CREATE TABLE `cst_discussion_topics` (
  `discussion_topic_id` int(11) NOT NULL AUTO_INCREMENT,
  `class_id` int(11) DEFAULT NULL,
  `subject_id` int(11) DEFAULT NULL,
  `topic_title` text,
  `topic_description` text,
  `created_date` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP,
  `modified_date` timestamp NULL DEFAULT NULL,
  `secur_uuid` varchar(100) NOT NULL,
  `topic_image` varchar(100) DEFAULT NULL,
  PRIMARY KEY (`discussion_topic_id`)
) ENGINE=InnoDB AUTO_INCREMENT=106 DEFAULT CHARSET=latin1;

/*Data for the table `cst_discussion_topics` */

insert  into `cst_discussion_topics`(`discussion_topic_id`,`class_id`,`subject_id`,`topic_title`,`topic_description`,`created_date`,`modified_date`,`secur_uuid`,`topic_image`) values (2,1,3,'Human Body','Define Human Body','2016-01-24 00:00:00','2016-01-24 00:00:00','b16d8432-e5ea-42d6-8521-0ab33326ca8c',NULL),(3,1,2,'Financial Aspects','Indian Economy is based on poor people and farmers. So they need to be powerful.','2016-01-30 18:07:09','2016-01-30 18:07:09','5a0fcf08-a2eb-457f-8390-ecf8ec8fef72',NULL),(8,1,1,'tpooo','updated desc dadf','2016-02-03 21:05:51','2016-06-20 23:33:31','fea4bbf2-1353-4a90-ad83-9634be84c542',NULL),(9,1,1,'nnenlkdf','nadfad','2016-02-03 21:06:05','2016-02-03 21:06:05','142a95b5-5c45-4a53-a796-bf2d59339c10',NULL),(11,1,1,'new taodfad','kjdfadklfads','2016-02-03 21:06:39','2016-02-03 21:06:39','5cf34a8f-e825-47f3-aff8-b37d58d0c967',NULL),(12,1,1,'new toopsdfasdfas','sdfasdfadsf','2016-02-03 21:08:32','2016-02-08 20:15:46','6fdd54d2-bf3f-43b2-b231-ebdfe81cf658',NULL),(13,1,1,'hhihihiih','lllllll','2016-02-03 21:09:04','2016-02-03 21:09:04','d148cf81-dc6b-4729-b84e-76357f595b66',NULL),(14,1,1,'heloolsooso','ldskfjadfjasdkf','2016-02-03 21:09:20','2016-02-03 21:09:20','17bc4e1c-e3a5-45ec-970d-3c864c975404',NULL),(16,1,1,'new topic jjjjjjjjjjjjjjjjjj','njjjdfdfd','2016-02-08 21:33:16','2016-02-08 21:33:16','fcae3921-b1e4-42a3-8138-10b42ed052d0',NULL),(17,1,1,'11111111111111','23456','2016-02-08 21:43:39','2016-02-08 21:43:39','d5a2c18b-bd68-40ee-9918-9cd81331054e',NULL),(19,2,1,'1 topic','2 desc','2016-02-10 23:23:10','2016-02-10 23:23:10','76331b6c-c6f7-476e-ba16-1a129025c623',NULL),(20,1,1,'adfadsf','adfads','2016-02-15 22:13:04','2016-02-15 22:13:04','13c33fc5-5340-46c1-8d63-0024723037c0',NULL),(21,1,1,'title','hello','2016-02-15 22:26:25','2016-02-15 22:26:25','c8a1a497-43f6-4ada-9643-dcd9c45aab9e',NULL),(22,1,1,'new topic created successfully','new topic created successfully','2016-02-15 22:49:19','2016-02-15 22:49:19','06087ab9-1273-470e-971c-d05d20b48c3d',NULL),(23,1,1,'asdfdasfa','adfadsfa','2016-02-17 21:54:16','2016-02-17 21:54:16','df4c93c7-8cfa-464f-87bd-2f28eb8b2d62',NULL),(24,1,1,'asdfdasfa','adfadsfa','2016-02-17 21:54:45','2016-02-17 21:54:45','304fdb6a-5789-4dfa-bac4-b62c19c89654',NULL),(25,1,1,'asdfa','adfadsf','2016-02-17 22:01:05','2016-02-17 22:01:05','6979604e-01df-4f2d-9d12-baa94e2e4367',NULL),(26,1,1,'new topic','new topic created','2016-02-17 22:13:22','2016-02-17 22:13:22','2b45e417-eced-4194-adb9-a923861b5f11',NULL),(27,1,1,'new topic created','new topic created desc','2016-02-17 22:31:42','2016-02-17 22:31:42','a82410d3-50d8-48e1-886e-b89c465bb9cc',NULL),(28,1,1,'new topic created','new topic created with new one','2016-02-17 22:37:33','2016-02-17 22:37:33','d3436738-d350-4e98-9293-c17a6143b88b',NULL),(29,1,2,'topoic tkoc  f','ksadjfkaldsf','2016-02-17 22:40:04','2016-02-17 22:40:04','b00fa3d8-e7d2-4112-89a5-2cf1b629a0a0',NULL),(30,1,1,'new topic created','new topoic created','2016-02-17 22:41:49','2016-02-17 22:41:49','2e4c1249-fd30-4db7-a04c-dcad3e6edfd9',NULL),(31,1,1,'new topic','topi c c rete','2016-02-17 22:46:12','2016-02-17 22:46:12','797b4234-528a-4964-a366-01fcbc0b53f8',NULL),(32,2,1,'new asdf','asdfkjkadsf','2016-02-17 22:47:44','2016-02-17 22:47:44','50ff45f4-fb65-43d7-b13f-e47f5421f4a9',NULL),(33,1,1,'asdfads','asdfasd','2016-02-17 22:49:25','2016-02-17 22:49:25','2ed8eca7-d660-420e-b04e-e5c485c6c420',NULL),(34,1,1,'ASDFADS','ASDFADS','2016-02-17 22:50:42','2016-02-17 22:50:42','b875f994-4eec-4a5a-9485-2aec3ef3181d',NULL),(35,1,1,'adfads','adsfadsf','2016-02-17 23:01:05','2016-02-17 23:01:05','6afbc27c-4412-4dcd-86af-8f74ec5e8077',NULL),(36,1,1,'asdfasd','adfads','2016-02-17 23:05:33','2016-02-17 23:05:33','a506fbc7-782a-475e-bd53-5fd1ddc8cb5f',NULL),(37,1,1,'test topic','testing topic desc','2016-02-18 20:53:11','2016-02-18 20:53:11','483b988e-31b0-47b9-b162-53735ee2bedd',NULL),(38,1,1,'1111111111111111','343333333333333333','2016-02-19 20:27:00','2016-02-19 20:27:00','df229764-1218-4fda-aef9-7bc6fdd9f004',NULL),(39,1,1,'hi','hello','2016-02-21 13:59:39','2016-02-21 13:59:39','803aebc7-978b-41c2-9ea6-e927ca24a3a4',NULL),(40,1,1,'1. popup for login in starting page.\n2. login profile pic\n3. email info@chalkndust.com\n4. our policies, term and condition','1. popup for login in starting page.\n2. login profile pic\n3. email info@chalkndust.com\n4. our policies, term and condition','2016-02-21 16:46:24','2016-02-21 16:46:24','ea6cb5de-041a-46bf-a94d-723e8de8c4df',NULL),(41,1,1,'Prakash Topic Created Successfully adfa','Prakash Topic Created Successfully adfad','2016-02-26 18:51:34','2016-02-26 18:53:15','1021b584-2830-415d-b7b9-1ad98dcde230',NULL),(67,1,1,'even no','divisible by 2','2016-03-08 20:49:36','2016-03-08 20:49:36','111',NULL),(68,1,3,'water formula','h2o','2016-03-08 22:06:03','2016-03-08 22:06:03','1111',NULL),(69,1,1,'ankur topic','ankur description','2016-03-26 19:06:50','2016-03-26 19:06:50','a6d27d6b-7d82-40e9-b613-e41c78093f0e',NULL),(70,1,2,'asdfasd','asdfasdf','2016-03-26 19:09:00','2016-03-26 19:09:00','80c6f9e6-1ac6-4375-be7f-e10afe567887',NULL),(71,2,2,'asdfasdfas','asdfasdf','2016-03-26 19:09:16','2016-03-26 19:09:16','be7b3796-53b0-4b2b-9bae-343700409084',NULL),(72,1,1,'sdfsd','dsfsdf','2016-03-26 19:09:57','2016-03-26 19:09:57','efe81714-f419-4999-a782-6dc210cae74b',NULL),(73,2,1,'testing','newtesting','2016-03-26 19:10:32','2016-11-06 10:00:00','6cfbf321-e757-4098-85b9-60fbbc8db851',NULL),(74,1,1,'test topic 1232','test topic descripton','2016-03-26 19:12:17','2016-03-26 19:12:17','337c5152-417c-4e6b-a57a-c1c1115f261a',NULL),(75,2,1,'adfadfa','asdfadf','2016-03-26 19:12:41','2016-03-26 19:12:41','65328b4c-0fba-4eef-ace8-bd4b846a76d1',NULL),(76,1,1,'topic desc','topic desc','2016-03-26 19:19:50','2016-03-26 19:19:50','5fbd2f94-8f4a-4790-9a62-9d6acdb7aac6',NULL),(77,1,1,'hello','howe are ou','2016-03-26 19:40:25','2016-03-26 19:40:25','20a9ebb4-f8b5-4a52-90d0-1b9b7de5e474',NULL),(78,1,1,'asdfasdf','asdf','2016-03-26 19:46:38','2016-03-26 19:46:38','e2021367-75b5-4523-b16d-43f31a08df4e',NULL),(79,1,1,'asdfasdf','asdf','2016-03-26 19:47:10','2016-03-26 19:47:10','65a13b5e-1d7c-4907-b060-a4df8ce19866',NULL),(80,1,1,'hello','hower aryou','2016-03-26 19:47:50','2016-03-26 19:47:50','7b9b60eb-9e8c-4a55-b44a-356ce8ad472c',NULL),(81,1,1,'hello','hower aryou','2016-03-26 19:48:17','2016-03-26 19:48:17','e9d8b43d-f443-4228-81f2-bc2495c39e6a',NULL),(82,1,1,'image upload example topic','image upload example topic request.','2016-03-26 20:20:36','2016-03-26 20:20:36','3f29c271-f1b1-430a-97ae-52acb6241061',NULL),(83,1,1,'image uplaod exmapl','image uplaod example','2016-03-26 20:22:15','2016-03-26 20:22:15','9af829a9-2a50-4f9c-8745-b9b0d1977b0b',NULL),(84,1,1,'topic new test','topic new testing one','2016-03-26 20:28:27','2016-03-26 20:28:27','a90c8bff-0ece-42fd-8261-51e046245c98',NULL),(85,1,1,'topic titile new one','topic desc new ttitile one tow','2016-03-26 20:30:49','2016-03-26 20:30:49','e9fe88b0-ffec-4be6-bd4b-45fa3b49bcac',NULL),(86,1,1,'ne w topa sidfdjfaj','ksdjfasdfjalskdf','2016-03-26 20:32:41','2016-03-26 20:32:41','5e5fbc74-6456-499f-9891-270bdcbaa1f1',NULL),(87,1,1,'Who Won the First Democratic Debate?','The Democratic 2016 hopefuls finally hit the debate stage Wednesday amid worries that, in comparison to the Republican debates, the affair would be a bit boring. Five candidates were on stage, but the bulk of the speaking time went to the two leaders: former Secretary of State Hillary Clinton and independent Vermont Sen. Bernie Sanders.\r\nThe other three candidates â€“ former Maryland Gov. Martin O\'Malley, former Sen. Jim Webb and former Rhode Island Gov. (as well as former Republican) Lincoln Chafee â€“ received significantly less time, perhaps reflecting the fact that they are all garnering less than 1 percent in the Real Clear Politics poll average. On social media, it was Sanders who earned the the most mentions, though much of the analysis casts Clinton as the victor.','2016-03-26 20:33:43','2016-03-26 20:33:43','3f584637-02b4-4dd1-b24b-9d47bf2449fc',NULL),(88,1,1,'Should People on the No-Fly List Be Barred From Purchasing Guns?','Even if you cannot board an airplane in the United States, you may still own a firearm, Congress confirmed last week, rejecting measures that would have barred people whose names appear on the federal no-fly list from purchasing guns.\r\nThe firearm safety proposal was thrust into the national spotlight after the terrorist attack in San Bernardino which left 14 people dead and dozens more wounded. The attackers, husband and wife, were reportedly radicalized Muslims who legally acquired two handguns and two assault rifles to carry out the mass shooting.','2016-03-26 20:51:46','2016-03-26 23:00:31','263432d4-e44b-47cf-8ac0-cd8a332d6080','TOPIC_263432d4-e44b-47cf-8ac0-cd8a332d6080.JPG'),(89,1,2,'Was the Fed Right to Raise Interest Rates?','The Federal Reserve on Wednesday raised interest rates for the first time since 2006, bringing its years of zero interest rate policy to an official end. The Fed has maintained historically low interest rates, and engaged in other forms of unconventional monetary policy, since the financial crisis of 2008 and ensuing Great Recession in an attempt to boost growth and employment.','2016-03-26 20:53:24','2016-03-26 20:53:24','e3464f1a-175f-4807-aeeb-f694cb5df01e',NULL),(90,1,1,'Is Single-Payer Health Care a Good Idea?','A major fault line has developed in the 2016 Democratic primary over health care. On one side, former Secretary of State Hillary Clinton wants to build upon the Affordable Care Act, aka Obamacare, claiming that an overhaul of the law would be too difficult and divisive. On the other is independent Sen. Bernie Sanders, who wants to implement a single-payer health care system, like those in Canada or the United Kingdom, under which the government, not private companies, runs and administers health insurance.','2016-03-26 22:08:39','2016-03-26 22:19:47','368b4f56-0434-4d13-8f5a-e7c95ce2d046','TOPIC_368b4f56-0434-4d13-8f5a-e7c95ce2d046.JPG'),(91,1,1,'Is Benghazi a Legitimate Scandal?','This week\'s release of the Michael Bay film \"13 Hours: The Secret Soldiers of Benghazi\" has renewed debate surrounding the deadly 2012 attacks on U.S. facilities in Benghazi, Libya.\r\n\r\nEarly reviews differ: Some critics hailed the film as nonpolitical, saying its Benghazi narrative instead tells a classic war story. Yet others dismissed it as propaganda \"for the Fox News crowd, â€¦ fueled by paranoia and hate.\"','2016-03-26 22:32:45','2016-03-26 22:33:53','40f6c743-850a-4a98-b893-b93ad5202771','TOPIC_40f6c743-850a-4a98-b893-b93ad5202771.JPG'),(93,2,1,'Are the Media to Blame for Donald Trump?','Businessman Donald Trump has certainly milked the media during his 2016 presidential run. According to The New York Times, Trump has earned nearly $2 billion worth of free media coverage of his campaign; second to him, Democratic presidential candidate Hillary Clinton has earned roughly $746 million. Trump\'s stunning media dominance raises questions about the role that the press has played in his rise to becoming the Republican front-runner.','2016-04-03 17:29:05','2016-04-03 17:29:05','436bb8e5-8bbd-49e0-94bd-fa8568e55972',NULL),(94,1,1,'30 april 2016 topic title','30 april 2016 topic desc','2016-04-30 20:31:21','2016-04-30 20:31:21','5693e099-c6fb-40f3-b23f-42a06fdbb084',NULL),(97,1,10,'new topic dated 2  may','new desc dated 2 may','2016-05-02 14:17:41','2016-05-02 14:17:41','212abd40-5358-47d8-9b64-f4e9d8464c04','TOPIC_212abd40-5358-47d8-9b64-f4e9d8464c04.png'),(98,1,2,'7 may','7 th may','2016-05-07 17:04:03','2016-05-07 17:04:03','65a5e630-4bd5-4a2f-a545-1d46be499223','TOPIC_65a5e630-4bd5-4a2f-a545-1d46be499223.png'),(99,NULL,NULL,NULL,NULL,'2016-06-21 20:03:58','2016-06-21 20:03:58','e9eb96b3-93e6-4e87-ab26-8f74209008ca',NULL),(100,NULL,NULL,NULL,NULL,'2016-06-21 20:05:55','2016-06-21 20:05:55','1d9a4af0-2add-4420-ac85-217cfdc71925',NULL),(101,NULL,NULL,NULL,NULL,'2016-06-21 20:06:10','2016-06-21 20:06:10','0832d191-9f16-4107-a93b-79935d278240',NULL),(102,NULL,NULL,NULL,NULL,'2016-06-21 20:19:30','2016-06-21 20:19:30','65483abd-f6dc-4442-bee7-2814c54cfda6',NULL),(103,1,10,'new topic dated 2 may first','new topic dated 2 may first','2016-07-28 00:14:05','2016-07-28 00:14:05','97589f5f-ea30-4aad-8d3e-172f2d9b7b96',NULL),(104,1,1,'new topic created','new topic descasdfasd','2016-07-28 00:14:37','2016-07-28 00:15:07','47fab996-e7a4-4677-82cb-081677f9952f',NULL),(105,1,1,'tesr','bhjhjjkh','2016-09-19 00:07:45','2016-09-19 00:07:45','1a0492ee-c387-4533-96b7-9b257917dc97','TOPIC_1a0492ee-c387-4533-96b7-9b257917dc97.jpg');

/*Table structure for table `cst_discussion_topics_history` */

DROP TABLE IF EXISTS `cst_discussion_topics_history`;

CREATE TABLE `cst_discussion_topics_history` (
  `history_id` int(11) NOT NULL AUTO_INCREMENT,
  `discussion_topic_id` int(11) NOT NULL,
  `class_id` int(11) DEFAULT NULL,
  `subject_id` int(11) DEFAULT NULL,
  `topic_title` text,
  `topic_description` text,
  `created_date` timestamp NULL DEFAULT NULL,
  `modified_date` timestamp NULL DEFAULT NULL,
  `secur_uuid` varchar(100) NOT NULL,
  `change_date` datetime NOT NULL,
  PRIMARY KEY (`history_id`)
) ENGINE=InnoDB AUTO_INCREMENT=36 DEFAULT CHARSET=latin1;

/*Data for the table `cst_discussion_topics_history` */

insert  into `cst_discussion_topics_history`(`history_id`,`discussion_topic_id`,`class_id`,`subject_id`,`topic_title`,`topic_description`,`created_date`,`modified_date`,`secur_uuid`,`change_date`) values (1,24,6,7,'new topic','new topic','2016-02-15 22:51:51','2016-02-15 22:51:51','b700d223-fcc8-4deb-abea-43066ee0c498','2016-02-16 22:47:05'),(2,26,3,1,'foundation 10 topic 2','foundation 10 topic 2 desc','2016-02-16 22:55:43','2016-02-16 22:55:43','f4b4aafa-af56-42b2-b954-e534002e2d3a','2016-02-16 23:03:13'),(3,25,3,1,'foundation 10 topic','foundation 10 desc','2016-02-16 22:55:14','2016-02-16 22:55:14','5d748330-5b5e-410f-af34-88d0d8467291','2016-02-16 23:04:36'),(4,4,1,1,'first topic n,mnmn','first description','2016-02-01 20:15:11','2016-02-15 22:49:57','f17d3136-c010-4d0b-8ab9-a4eaae1b2f1c','2016-02-18 21:50:13'),(5,6,1,1,'topic 2','desc 2','2016-02-03 21:05:04','2016-02-03 21:05:04','5e337702-5486-4e03-b742-fdf214440d48','2016-02-18 21:51:00'),(6,45,1,1,'even no','divisible by 2','2016-03-08 19:44:02','2016-03-08 19:44:02','','2016-03-08 19:48:52'),(7,46,1,1,'even no','divisible by 2','2016-03-08 19:44:18','2016-03-08 19:44:18','','2016-03-08 19:48:52'),(8,47,1,3,'water formula','h2o','2016-03-08 19:44:21','2016-03-08 19:44:21','','2016-03-08 19:48:53'),(9,48,1,1,'even no','divisible by 2','2016-03-08 19:45:59','2016-03-08 19:45:59','','2016-03-08 19:48:53'),(10,49,1,1,'even no','divisible by 2','2016-03-08 19:49:09','2016-03-08 19:49:09','','2016-03-08 20:47:17'),(11,50,1,3,'water formula','h2o','2016-03-08 19:49:49','2016-03-08 19:49:49','','2016-03-08 20:47:17'),(12,51,1,1,'new topic request','new topic request','2016-03-08 20:21:50','2016-03-08 20:21:50','','2016-03-08 20:47:17'),(13,52,1,2,'new topic issue','new topic desc','2016-03-08 20:45:41','2016-03-08 20:45:41','','2016-03-08 20:47:17'),(14,53,1,3,'Ozone','what is ozone??','2016-03-08 20:45:44','2016-03-08 20:45:44','','2016-03-08 20:47:17'),(15,54,1,2,'8 march topic request','8 march topic description','2016-03-08 20:45:48','2016-03-08 20:45:48','','2016-03-08 20:47:18'),(16,55,1,1,'even no','divisible by 2','2016-03-08 20:45:54','2016-03-08 20:45:54','','2016-03-08 20:47:18'),(17,56,1,3,'water formula','h2o','2016-03-08 20:45:56','2016-03-08 20:45:56','','2016-03-08 20:47:18'),(18,57,1,1,'new topic request','new topic request','2016-03-08 20:45:59','2016-03-08 20:45:59','','2016-03-08 20:47:18'),(19,58,1,2,'new topic issue','new topic desc','2016-03-08 20:46:13','2016-03-08 20:46:13','12121','2016-03-08 20:47:18'),(20,59,1,3,'Ozone','what is ozone??','2016-03-08 20:46:17','2016-03-08 20:46:17','121221','2016-03-08 20:47:18'),(21,60,1,2,'8 march topic request','8 march topic description','2016-03-08 20:46:22','2016-03-08 20:46:22','12121212','2016-03-08 20:47:18'),(22,42,1,1,'one','one','2016-02-26 20:50:40','2016-02-26 20:50:40','670cdfdb-f36f-462b-bb95-f8b9af50b3d2','2016-03-08 20:47:25'),(23,43,1,1,'topic 32','topic description 32','2016-02-26 20:51:00','2016-02-28 12:11:19','5fc1989c-83d7-4801-90e0-103f5af4a537','2016-03-08 20:47:25'),(24,44,1,1,'topic 3','desc 5','2016-02-26 20:51:17','2016-02-26 20:51:17','d0408b58-4715-434c-b63e-6fc8196b4c82','2016-03-08 20:47:25'),(25,61,1,1,'even no','divisible by 2','2016-03-08 20:47:55','2016-03-08 20:47:55','111','2016-03-08 20:49:16'),(26,62,1,3,'water formula','h2o','2016-03-08 20:47:56','2016-03-08 20:47:56','1111','2016-03-08 20:49:16'),(27,63,1,1,'new topic request','new topic request','2016-03-08 20:47:57','2016-03-08 20:47:57','11111','2016-03-08 20:49:16'),(28,64,1,2,'new topic issue','new topic desc','2016-03-08 20:47:59','2016-03-08 20:47:59','12121','2016-03-08 20:49:16'),(29,65,1,3,'Ozone','what is ozone??','2016-03-08 20:48:01','2016-03-08 20:48:01','121221','2016-03-08 20:49:16'),(30,66,1,2,'8 march topic request','8 march topic description','2016-03-08 20:48:04','2016-03-08 20:48:04','12121212','2016-03-08 20:49:16'),(31,96,1,1,'today','today','2016-05-02 13:35:11','2016-05-02 13:35:11','93ed5b3c-c15a-4828-a08a-27a3697ebbbe','2016-05-02 13:36:42'),(32,95,1,1,'today','today','2016-04-30 20:36:25','2016-04-30 20:36:25','93ed5b3c-c15a-4828-a08a-27a3697ebbbe','2016-05-02 13:36:42'),(33,92,1,1,'Has Obama Been Vindicated on Iran?','President Barack Obama is promoting what he deems a series of successes this month in his foreign policy with Iran.\r\n\r\nLast week, 10 U.S. sailors were taken captive by Iran after their patrol boats drifted into Iranian waters. They were released the following day, and the Obama administration hailed the peaceful episode as a product of the warming relationship between the United States and Iran.','2016-03-26 22:49:43','2016-03-26 22:50:06','ba6b2b92-548e-4ab3-994c-a438511e152e','2016-05-22 10:30:59'),(34,7,1,1,'topic 3','desc 3','2016-02-03 21:05:30','2016-05-25 23:09:27','8b4cf773-fa95-4b9d-a47c-49e5340fedeb','2016-05-25 23:10:23'),(35,106,1,1,'new topic 18 sep','new desc','2016-09-19 19:28:49','2016-09-19 19:32:06','1a1cbfcf-c45c-4839-a0d1-88d663a8e863','2016-09-19 19:32:57');

/*Table structure for table `cst_guestusers` */

DROP TABLE IF EXISTS `cst_guestusers`;

CREATE TABLE `cst_guestusers` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `username` varchar(100) NOT NULL,
  `email` varchar(200) NOT NULL,
  `mobileno` varchar(20) NOT NULL,
  `create_at` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP,
  `modified_at` datetime DEFAULT NULL,
  `secur_uuid` varchar(100) NOT NULL,
  `class_id` int(11) NOT NULL DEFAULT '1',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=20 DEFAULT CHARSET=latin1;

/*Data for the table `cst_guestusers` */

insert  into `cst_guestusers`(`id`,`username`,`email`,`mobileno`,`create_at`,`modified_at`,`secur_uuid`,`class_id`) values (1,'warren','abhishek.kumar627@gmail.com','343434343','2016-04-16 13:03:07',NULL,'e11f6fb6-d1d3-4457-9166-7ae3eb2d177d',1),(2,'warren','abhishek.kumar627@gmail.com','4545454545','2016-04-16 13:05:40',NULL,'bc8013aa-51c1-4639-8417-07a70288a441',1),(3,'warren','abhishek.kumar627@gmail.com','67676767676','2016-04-16 13:10:08',NULL,'3a9c7b98-f33a-426f-b668-e69ad685d02e',1),(4,'warren','abhishek.kumar627@gmail.com','767676','2016-04-16 14:43:47',NULL,'d110e652-c194-4bb7-a6d0-53eba5c3bc4d',1),(5,'warren','abhishek.kumar627@gmail.com','34343434','2016-04-16 14:46:34',NULL,'8766d50c-cb3e-4879-a877-24675b6264c8',1),(6,'sachin','sachinkumar@gmail.com','23232323','2016-04-16 15:12:13',NULL,'771a83a8-8f09-445e-be64-e9ba86fa2ad8',1),(7,'dhruv','abhi@kumar.com','33434343','2016-04-16 15:24:00',NULL,'125fa598-9438-48ad-8d5e-ace89af73efb',6),(8,'jyoti','jyoti@gmail.com','3434343','2016-04-16 15:28:57',NULL,'c791af91-b40b-43be-8d5b-76f34cfbbd8f',2),(9,'deepak','deepka@m','121212','2016-04-16 15:31:19',NULL,'3e200e44-a6de-4c8a-ac48-f1d3d8f05cd6',3),(10,'abhishek','abhishek.kumar627@gmail.com','9711877691','2016-04-16 22:25:27',NULL,'f37c4314-0b7b-4157-94b0-977a801ceb44',1),(11,'abhishek','abhi@kumar.com','9711877691','2016-04-16 22:37:19',NULL,'689875f7-0ff8-4916-846c-08dad0060cf5',2),(12,'warren','abhishek.kumar627@gmail.com','565656565','2016-04-16 22:39:27',NULL,'8fdbe306-64e9-48e4-a4e6-ec7b648a6638',1),(13,'warren','abhishek.kumar627@gmail.com','34343434343','2016-04-16 22:40:49',NULL,'621623ce-e893-4e15-abfd-81f99c64885d',3),(14,'abhishekkumar','abhishek.kumar627@gmail.com','3434343434','2016-04-16 22:43:15',NULL,'f694daee-e4a9-4f61-a341-e71a7f4f6190',2),(15,'Abhishek','abhi@gmail.com','98761212','2016-04-16 22:51:35',NULL,'13f347c3-859d-4c6e-a264-8bac0d81ab0b',3),(16,'jdd','abhi@gmail.com','353553353','2016-04-16 22:53:19',NULL,'21aa12f2-3611-4b9c-9a42-f4711b405cd8',1),(17,'nenew user','abhi@kumar.com','34444444','2016-04-17 11:33:45',NULL,'99508562-d6ea-4167-a341-7a90a86d85fc',1),(18,'Abhisehk','abhi@gmail.com','343434','2016-04-17 11:54:24',NULL,'a5a8f6dd-1f50-434e-b11c-251f5d2f4d44',1),(19,'abhishek','abhishek.kumar627@gmail.com','971187691','2016-04-17 12:30:44',NULL,'5e1c5a8e-760c-40aa-84df-b08d876db1ae',1);

/*Table structure for table `cst_logins` */

DROP TABLE IF EXISTS `cst_logins`;

CREATE TABLE `cst_logins` (
  `user_id` bigint(20) NOT NULL AUTO_INCREMENT,
  `username` varchar(50) NOT NULL,
  `password` text NOT NULL,
  `active` tinyint(4) NOT NULL DEFAULT '1',
  `disabled_from` datetime DEFAULT NULL,
  PRIMARY KEY (`user_id`)
) ENGINE=InnoDB AUTO_INCREMENT=31 DEFAULT CHARSET=latin1;

/*Data for the table `cst_logins` */

insert  into `cst_logins`(`user_id`,`username`,`password`,`active`,`disabled_from`) values (19,'admin','JDJhJDEyJFFIT1pmZHhlN2tjTW5WUU1VU0hsZHVyU2NRbFFMLjZlM1VzMS9kVnR5MEtvRUpnSjh6alhL',1,NULL),(22,'prakash','JDJhJDEyJDlzOFFiRmR2TXVxa0FRb2Fic3RGTHVKUWxoNHZLUlJuTGNHNk9jMmFJWm1DUGZBTkt3a3Ft',1,NULL),(23,'himanshu','JDJhJDEyJDlIU0RYNVBPRy91ejNNRlJXMnQwR3VCLlE1QURWN2hjVkwwLkZnd0RCLnh4Ykk5N1RqQ3NP',1,NULL),(24,'abhishek','JDJhJDEyJEZnUDh1OUpBVkF1SnBsUVJYUXlEV3U2ejRXUDdlVGlIT1BkWGpBMjg2eDVOdkRsS3VMTy51',1,NULL),(25,'raman','JDJhJDEyJHY2RWVTSlVXeWtEVVNQVzVCRVdvMi42VEhFTHowSXJVR3ZNMVNtRFRKNmY3TnQzL1EvMDlH',1,NULL),(26,'raman12','JDJhJDEyJHMyVEJJRjBhcXZpMDQwTEY0ZjJkUk9wOEVhc2suaFhyNUlFc2haZ0NnQUc5dnNQaklrN3My',1,NULL),(27,'jitendra','JDJhJDEyJGZQOU51NTd1ckFZWXA3d0kxQm9OcU81SGtsb2RKb0hRU3I3aUhub05EMHlERWFvUU5qLnou',1,NULL),(28,'pankaj','JDJhJDEyJGVrV21pMlN1MUJXa1dGWkxjNkFhWmU3ZjRNMDhqRmYwdnhib2JOVUJ4MlZmRGNSTEFKd3hT',1,NULL),(29,'deepak','JDJhJDEyJEZnUDh1OUpBVkF1SnBsUVJYUXlEV3U2ejRXUDdlVGlIT1BkWGpBMjg2eDVOdkRsS3VMTy51',1,NULL),(30,'sachin','JDJhJDEyJHo3dHhHUjA4Vy9GQWRwV2V5RVNFN09qNXc4WGw0NG51VXR2SmJYdUs4bUI3Llg3cDc1dGs2',1,NULL);

/*Table structure for table `cst_notes` */

DROP TABLE IF EXISTS `cst_notes`;

CREATE TABLE `cst_notes` (
  `notes_id` int(11) NOT NULL AUTO_INCREMENT,
  `class_id` int(11) NOT NULL,
  `subject_id` int(11) NOT NULL,
  `notes_title` varchar(500) NOT NULL,
  `notes_file_name` varchar(500) NOT NULL,
  `created_date` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP,
  `modified_date` datetime DEFAULT NULL,
  `notes_uuid` varchar(100) NOT NULL,
  PRIMARY KEY (`notes_id`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=latin1;

/*Data for the table `cst_notes` */

insert  into `cst_notes`(`notes_id`,`class_id`,`subject_id`,`notes_title`,`notes_file_name`,`created_date`,`modified_date`,`notes_uuid`) values (1,1,1,'notes 1','DHBVN.pdf','2016-07-02 21:18:44','2016-07-02 21:18:44','fa903dab-89b0-4bdc-9fb5-804b83f2b25f'),(3,2,1,'notes','example.pdf','2016-11-06 10:14:18','2016-11-06 10:14:18','ad2f7511-54f8-4011-b6af-85cf0178074f');

/*Table structure for table `cst_parents` */

DROP TABLE IF EXISTS `cst_parents`;

CREATE TABLE `cst_parents` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `father_name` varchar(50) DEFAULT NULL,
  `mother_name` varchar(50) DEFAULT NULL,
  `father_email` varchar(50) DEFAULT NULL,
  `mother_email` varchar(50) DEFAULT NULL,
  `father_mobile` varchar(15) DEFAULT NULL,
  `mother_mobile` varchar(15) DEFAULT NULL,
  `father_office_address` text,
  `mother_office_address` text,
  `father_occupation` varchar(100) DEFAULT NULL,
  `mother_occupation` varchar(100) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=16 DEFAULT CHARSET=latin1;

/*Data for the table `cst_parents` */

insert  into `cst_parents`(`id`,`father_name`,`mother_name`,`father_email`,`mother_email`,`father_mobile`,`mother_mobile`,`father_office_address`,`mother_office_address`,`father_occupation`,`mother_occupation`) values (4,'father name','mothername','father@gmail.com','mother@gmail.com','1212121212','3434343434','officeAddress','office Address','occupation','mother occupation'),(7,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL),(8,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL),(9,'Mr Surender Kumar','Kanti Devi','surender.kumar6792@gmail.com','kantidevi@gmail.com','9971218252','8376901835','jaypee vasant','ballabgarh','civil engineer','house wife'),(10,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL),(11,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL),(12,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL),(13,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL),(14,'Surender Kumar','kanti devi','surender.kumar6792@gmail.com','kanti.devi@gmail.com','9971218252','223232','delhi','house wife','engineer','house wife'),(15,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL);

/*Table structure for table `cst_questions` */

DROP TABLE IF EXISTS `cst_questions`;

CREATE TABLE `cst_questions` (
  `question_id` bigint(20) NOT NULL AUTO_INCREMENT,
  `class_id` int(11) NOT NULL,
  `subject_id` int(11) NOT NULL,
  `question` text NOT NULL,
  `marks` int(11) DEFAULT NULL,
  `deleted` tinyint(4) DEFAULT '0',
  `created_at` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP,
  `modified_at` datetime DEFAULT NULL,
  `question_uuid` varchar(100) NOT NULL,
  `question_image` varchar(100) DEFAULT NULL,
  `question_type` varchar(100) DEFAULT NULL,
  PRIMARY KEY (`question_id`)
) ENGINE=InnoDB AUTO_INCREMENT=7 DEFAULT CHARSET=latin1;

/*Data for the table `cst_questions` */

insert  into `cst_questions`(`question_id`,`class_id`,`subject_id`,`question`,`marks`,`deleted`,`created_at`,`modified_at`,`question_uuid`,`question_image`,`question_type`) values (1,1,1,'12312ADFADF',NULL,0,'2016-07-15 22:39:45','2016-07-18 21:36:08','d192a8d1-51ab-4a9d-9a60-f35839e8344d',NULL,'Practice Question'),(2,1,1,'question',NULL,0,'2016-07-15 23:15:50','2016-07-17 17:25:27','ca98d346-fd55-41b8-b3b5-6cf033ea76d5','QUESTION_ca98d346-fd55-41b8-b3b5-6cf033ea76d5.jpg','Practice Question'),(3,1,1,'test question',NULL,0,'2016-07-17 17:51:23','2016-07-17 17:52:11','327d1b3a-de24-4afb-abd5-61252390e270',NULL,'Scheduled Exam Question'),(4,1,1,'tek sajjflkajsfdjaj',NULL,0,'2016-09-21 21:39:20',NULL,'01cc9104-c106-4176-8d29-590c8238f503',NULL,'Practice Question'),(5,2,1,'hwo are you sir are you fien',NULL,0,'2016-11-06 10:17:48','2016-11-06 10:26:57','26600320-8784-459e-a2e5-1f592013cd7a','QUESTION_26600320-8784-459e-a2e5-1f592013cd7a.jpg','Practice Question'),(6,2,1,'schedute test question',NULL,0,'2016-11-06 10:31:34','2016-11-06 10:32:03','2f2e3c4f-eac7-4bc4-bc88-73e74f5bdbdf','QUESTION_2f2e3c4f-eac7-4bc4-bc88-73e74f5bdbdf.jpg','Scheduled Exam Question');

/*Table structure for table `cst_questions_old` */

DROP TABLE IF EXISTS `cst_questions_old`;

CREATE TABLE `cst_questions_old` (
  `question_id` bigint(20) NOT NULL AUTO_INCREMENT,
  `class_id` int(11) NOT NULL,
  `subject_id` int(11) NOT NULL,
  `question` text NOT NULL,
  `option1` text NOT NULL,
  `option2` text NOT NULL,
  `option3` text NOT NULL,
  `option4` text NOT NULL,
  `answer` char(1) NOT NULL,
  `marks` int(11) DEFAULT NULL,
  `deleted` tinyint(4) DEFAULT '0',
  `created_at` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP,
  `modified_at` datetime DEFAULT NULL,
  `question_uuid` varchar(100) NOT NULL,
  `question_image` varchar(100) DEFAULT NULL,
  PRIMARY KEY (`question_id`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=latin1;

/*Data for the table `cst_questions_old` */

insert  into `cst_questions_old`(`question_id`,`class_id`,`subject_id`,`question`,`option1`,`option2`,`option3`,`option4`,`answer`,`marks`,`deleted`,`created_at`,`modified_at`,`question_uuid`,`question_image`) values (1,1,1,'what is your name','abhishek','kumar','sharma','bhardwaj','A',NULL,0,'2016-05-14 16:12:57','2016-06-22 00:40:49','1140e27d-5496-417b-9e99-68bd864705a2',NULL),(2,1,1,'nameadagds','1asdfa','2asdf','3asfd','4afd','B',NULL,0,'2016-05-14 16:14:35','2016-06-20 23:27:25','886ee4c8-fe5d-405e-8c72-679eec12e6dc','QUESTION_886ee4c8-fe5d-405e-8c72-679eec12e6dc.JPG'),(3,1,1,'sdfaasdf','1','2','3','4','c',NULL,0,'2016-07-12 00:02:10',NULL,'121',NULL);

/*Table structure for table `cst_questions_options` */

DROP TABLE IF EXISTS `cst_questions_options`;

CREATE TABLE `cst_questions_options` (
  `options_id` int(11) NOT NULL AUTO_INCREMENT,
  `question_id` int(11) NOT NULL,
  `name` varchar(500) NOT NULL,
  `isAnswer` tinyint(1) DEFAULT '0',
  `answer` varchar(1) DEFAULT NULL,
  PRIMARY KEY (`options_id`)
) ENGINE=InnoDB AUTO_INCREMENT=81 DEFAULT CHARSET=latin1;

/*Data for the table `cst_questions_options` */

insert  into `cst_questions_options`(`options_id`,`question_id`,`name`,`isAnswer`,`answer`) values (37,2,'akdfja',1,'A'),(38,2,'kadjfjl',0,NULL),(39,2,'falksdjkj',0,NULL),(40,2,'kfdj',0,NULL),(49,3,'hello',1,'A'),(50,3,'hi',0,NULL),(51,3,'how are you',0,NULL),(52,3,'ksjdf',0,NULL),(53,1,'Aa',0,NULL),(54,1,'Bb',0,NULL),(55,1,'Cc',0,NULL),(56,1,'Dd',1,'D'),(57,4,'aasdf',1,'A'),(58,4,'asdfaj',0,NULL),(59,4,'sdfjjdfjj',0,NULL),(60,4,'fdjjfkd',0,NULL),(69,5,'yes',1,'A'),(70,5,'no',0,NULL),(71,5,'may be',0,NULL),(72,5,'cann not',0,NULL),(77,6,'option a',1,'A'),(78,6,'option b',0,NULL),(79,6,'option c',0,NULL),(80,6,'option d',0,NULL);

/*Table structure for table `cst_schedule_test_master` */

DROP TABLE IF EXISTS `cst_schedule_test_master`;

CREATE TABLE `cst_schedule_test_master` (
  `test_id` bigint(20) NOT NULL AUTO_INCREMENT,
  `test_title` varchar(100) NOT NULL,
  `test_date` date NOT NULL,
  `test_time` time DEFAULT NULL,
  `test_type_uuid` varchar(100) NOT NULL,
  `class_id` int(11) NOT NULL,
  `subject_id` int(11) NOT NULL,
  `created_at` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP,
  `test_uuid` varchar(100) NOT NULL,
  `update_at` datetime DEFAULT NULL,
  PRIMARY KEY (`test_id`)
) ENGINE=InnoDB AUTO_INCREMENT=11 DEFAULT CHARSET=latin1;

/*Data for the table `cst_schedule_test_master` */

insert  into `cst_schedule_test_master`(`test_id`,`test_title`,`test_date`,`test_time`,`test_type_uuid`,`class_id`,`subject_id`,`created_at`,`test_uuid`,`update_at`) values (2,'ASJDFKLAS','2016-09-09','00:08:00','cf92f98c-4684-11e6-beb8-9e71128cae77',1,1,'2016-07-17 21:34:07','8633bc5b-03d1-43ca-b2ae-fe7f60df09b6',NULL),(4,'new updated data','2016-08-23','02:00:00','cf92f98c-4684-11e6-beb8-9e71128cae77',1,1,'2016-07-17 21:37:56','c1d5a899-1807-42ff-8411-c9de4450a437','2016-07-18 22:55:19'),(5,'test title','2916-09-23','22:00:00','cf92f98c-4684-11e6-beb8-9e71128cae77',1,1,'2016-07-19 23:28:02','9cc82810-cb95-45b0-9bde-411f6480af18','2016-09-22 21:32:49'),(7,'test title','2016-08-01','12:22:00','cf92f98c-4684-11e6-beb8-9e71128cae77',1,1,'2016-07-20 22:00:17','5e94a3c6-c31b-4c85-b447-6707b2c95528','2016-07-28 00:22:28'),(8,'Testing test','2016-09-10','02:00:00','cf92f98c-4684-11e6-beb8-9e71128cae77',1,1,'2016-09-09 20:17:29','9c0ae9e3-e64c-4a19-a03f-cafc5f1dd8ed',NULL),(9,'new test','2016-09-22','21:00:00','cf92f98c-4684-11e6-beb8-9e71128cae77',1,1,'2016-09-22 21:32:26','c4fb3b33-7099-499d-b83b-ac8dcbec1f84','2016-09-22 21:33:33'),(10,'testing schedtule test','2016-11-06','10:00:00','cf92f98c-4684-11e6-beb8-9e71128cae77',2,1,'2016-11-06 10:38:14','f1009a06-4109-4849-9287-8e721879b19f','2016-11-06 10:38:37');

/*Table structure for table `cst_student_test` */

DROP TABLE IF EXISTS `cst_student_test`;

CREATE TABLE `cst_student_test` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `student_id` varchar(100) NOT NULL,
  `class_id` int(11) NOT NULL,
  `subject_id` int(11) NOT NULL,
  `test_type_id` varchar(100) DEFAULT NULL,
  `test_date` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP,
  `scheduled_test_uuid` varchar(100) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=12 DEFAULT CHARSET=latin1;

/*Data for the table `cst_student_test` */

insert  into `cst_student_test`(`id`,`student_id`,`class_id`,`subject_id`,`test_type_id`,`test_date`,`scheduled_test_uuid`) values (6,'41b0e148-5069-4b00-8d94-735d064e9dde',1,1,'cf92f98c-4684-11e6-beb8-9e71128cae77','2016-09-09 12:28:16',NULL),(7,'41b0e148-5069-4b00-8d94-735d064e9dde',1,1,'cf92f98c-4684-11e6-beb8-9e71128cae77','2016-09-09 20:21:36',NULL),(8,'41b0e148-5069-4b00-8d94-735d064e9dde',1,1,'cf92f98c-4684-11e6-beb8-9e71128cae77','2016-09-22 22:21:01',NULL),(9,'3be2050d-e361-482a-9537-3f9157b5e637',2,1,'cf92f98c-4684-11e6-beb8-9e71128cae77','2016-11-06 10:40:29',NULL),(10,'61433276-4cb9-49fa-9150-f4222647676b',2,1,'cf92f98c-4684-11e6-beb8-9e71128cae77','2016-11-06 10:55:32',NULL),(11,'3be2050d-e361-482a-9537-3f9157b5e637',2,1,'cf92f98c-4684-11e6-beb8-9e71128cae77','2016-11-06 11:59:58','f1009a06-4109-4849-9287-8e721879b19f');

/*Table structure for table `cst_student_test_answers` */

DROP TABLE IF EXISTS `cst_student_test_answers`;

CREATE TABLE `cst_student_test_answers` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `cst_student_test_id` int(11) NOT NULL,
  `question_id` int(11) NOT NULL,
  `question_option_selected_id` int(11) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=7 DEFAULT CHARSET=latin1;

/*Data for the table `cst_student_test_answers` */

insert  into `cst_student_test_answers`(`id`,`cst_student_test_id`,`question_id`,`question_option_selected_id`) values (1,6,3,50),(2,7,3,49),(3,8,3,50),(4,9,6,77),(5,10,6,77),(6,11,6,78);

/*Table structure for table `cst_system_settings` */

DROP TABLE IF EXISTS `cst_system_settings`;

CREATE TABLE `cst_system_settings` (
  `settings_id` bigint(20) NOT NULL AUTO_INCREMENT,
  `settings_key` varchar(100) NOT NULL,
  `settings_value` varchar(200) NOT NULL,
  `description` text,
  `media_url` varchar(200) DEFAULT NULL,
  PRIMARY KEY (`settings_id`)
) ENGINE=InnoDB AUTO_INCREMENT=6 DEFAULT CHARSET=latin1;

/*Data for the table `cst_system_settings` */

insert  into `cst_system_settings`(`settings_id`,`settings_key`,`settings_value`,`description`,`media_url`) values (1,'PROFILE_PHOTO','G:\\CHALKANDDUST\\ProfilePhoto\\','To store Profile photo',NULL),(2,'TOPIC_IMAGE','G:\\CHALKANDDUST\\TopicPhoto\\','TO save topic image.',NULL),(3,'QUESTION_IMAGE','G:\\CHALKANDDUST\\QuestionPhoto\\','To save question image.',NULL),(4,'NOTES_FILE','G:\\CHALKANDDUST\\Notes\\','To save notes content w.r.t class and subject','G:\\CHALKANDDUST\\Notes\\'),(5,'ACHIEVEMENT_DATA','G:\\CHALKANDDUST\\AchievementData\\','To save student achievement photos',NULL);

/*Table structure for table `cst_template` */

DROP TABLE IF EXISTS `cst_template`;

CREATE TABLE `cst_template` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `primary_content` text CHARACTER SET utf8,
  `editable_content` text CHARACTER SET utf8,
  `subject` varchar(100) CHARACTER SET utf8 DEFAULT NULL,
  `notification_recipient_type` int(11) DEFAULT NULL,
  `notification_template_key` varchar(100) CHARACTER SET utf8 DEFAULT NULL,
  `notification_type` int(11) DEFAULT NULL,
  `merge_body_in_template` tinyint(4) DEFAULT NULL,
  `internal` tinyint(4) DEFAULT NULL,
  `recipient_id` int(11) DEFAULT NULL,
  `merge_subject` tinyint(4) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=latin1;

/*Data for the table `cst_template` */

insert  into `cst_template`(`id`,`primary_content`,`editable_content`,`subject`,`notification_recipient_type`,`notification_template_key`,`notification_type`,`merge_body_in_template`,`internal`,`recipient_id`,`merge_subject`) values (1,'Dear <b>${firstName}</b>,<br><br><br> Welcome On Chalk N Dust!  <br><br>A <b>Student</b> login has been created for you. <br>Please get in touch with the Chalk N Dust administrator to get your credentials. <br><br>Regards System Admin',NULL,'Chalk N Dust | User Registration',1,'USER_REGISTRATION_SUCCESSFUL',1,NULL,NULL,NULL,NULL),(2,'Dear <b>Admin</b>,<br><br><br> You have new Enquiry Request!  <br><br>\r\nEnquiry Details are:<br><br>\r\n<b>Name</b>&nbsp;&nbsp;${enquiryName}<br>\r\n<b>EmailId</b>&nbsp;&nbsp;${email}<br>\r\n<b>Message</b>&nbsp;&nbsp;${enquiryMessage}<br>\r\n<b>Subject</b>&nbsp;&nbsp;${enquirySubject}<br>\r\n<br>Please respond to the query as soon as poosible.\r\n<br><br>\r\nRegards CHALKNDUST',NULL,'New Enquiry',1,'NEW_ENQUIRY',1,NULL,NULL,NULL,NULL),(3,'Dear <b>${firstName}</b>,<br><br><br> Welcome ON Chalk N Dust!  <br><br>A <b>Student</b> PASSWORD   ${password} has been created FOR you. \r\n<br>Please get IN touch WITH the Chalk N Dust administrator TO get your credentials. <br><br>Regards System Admin',NULL,'reset password',NULL,'RESET_PASSWORD',1,NULL,NULL,NULL,NULL);

/*Table structure for table `cst_test_result` */

DROP TABLE IF EXISTS `cst_test_result`;

CREATE TABLE `cst_test_result` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `test_id` int(11) NOT NULL,
  `student_id` int(11) NOT NULL,
  `total_marks` int(11) NOT NULL,
  `result` varchar(40) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

/*Data for the table `cst_test_result` */

/*Table structure for table `cst_test_type` */

DROP TABLE IF EXISTS `cst_test_type`;

CREATE TABLE `cst_test_type` (
  `test_type_id` int(11) NOT NULL AUTO_INCREMENT,
  `test_type_uuid` varchar(100) NOT NULL,
  `test_duration` varchar(100) NOT NULL,
  `no_of_questions` int(11) NOT NULL,
  PRIMARY KEY (`test_type_id`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=latin1;

/*Data for the table `cst_test_type` */

insert  into `cst_test_type`(`test_type_id`,`test_type_uuid`,`test_duration`,`no_of_questions`) values (1,'cf92f98c-4684-11e6-beb8-9e71128cae77','20 Minutes',20),(2,'cf92fe46-4684-11e6-beb8-9e71128cae77','40 Minutes',40),(3,'cf92fc16-4684-11e6-beb8-9e71128cae77','60 Minutes',60);

/*Table structure for table `cst_topic_requests` */

DROP TABLE IF EXISTS `cst_topic_requests`;

CREATE TABLE `cst_topic_requests` (
  `topic_request_id` bigint(20) NOT NULL AUTO_INCREMENT,
  `topic_title` text NOT NULL,
  `topic_description` text NOT NULL,
  `secur_uuid` varchar(100) NOT NULL,
  `approved` tinyint(4) NOT NULL DEFAULT '0',
  `subject_id` int(11) NOT NULL,
  `class_id` int(11) NOT NULL,
  `request_date` datetime NOT NULL,
  `approval_date` datetime DEFAULT NULL,
  `request_securuuid` varchar(100) NOT NULL,
  `topic_image` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`topic_request_id`)
) ENGINE=InnoDB AUTO_INCREMENT=15 DEFAULT CHARSET=latin1;

/*Data for the table `cst_topic_requests` */

insert  into `cst_topic_requests`(`topic_request_id`,`topic_title`,`topic_description`,`secur_uuid`,`approved`,`subject_id`,`class_id`,`request_date`,`approval_date`,`request_securuuid`,`topic_image`) values (1,'even no','divisible by 2','63ef56e2-3fd7-42d4-b84c-eeee0b7e387a',1,1,1,'2016-02-29 17:41:21','2016-03-08 20:49:36','111',NULL),(2,'water formula','h2o','63ef56e2-3fd7-42d4-b84c-eeee0b7e387a',1,3,1,'2016-02-29 17:44:55','2016-03-08 22:06:03','1111',NULL),(3,'new topic request','new topic request','41b0e148-5069-4b00-8d94-735d064e9dde',0,1,1,'2016-02-29 22:04:17','2016-03-08 20:21:50','11111',NULL),(4,'new topic issue','new topic desc','41b0e148-5069-4b00-8d94-735d064e9dde',0,2,1,'2016-02-29 22:07:06','2016-03-08 20:46:13','12121',NULL),(5,'Ozone','what is ozone??','41b0e148-5069-4b00-8d94-735d064e9dde',0,3,1,'2016-02-29 22:43:40','2016-03-08 20:46:17','121221',NULL),(6,'8 march topic request','8 march topic description','41b0e148-5069-4b00-8d94-735d064e9dde',0,2,1,'2016-03-08 19:25:50','2016-03-08 20:46:22','12121212',NULL),(7,'kjkjjkjkjjkjkjk','hjhkhjjkhjhkjhkjhhjhkjhj','41b0e148-5069-4b00-8d94-735d064e9dde',0,1,1,'2016-04-01 21:59:43',NULL,'8421bcd7-22f5-4164-83fc-095ffbc00b27',NULL),(8,'sefsdf','sdfsd','41b0e148-5069-4b00-8d94-735d064e9dde',0,1,1,'2016-04-02 13:35:07',NULL,'4f3222dc-c95c-41cf-9bd1-08019fe9d101',NULL),(9,'30 april 2016 topic title','30 april 2016 topic desc','41b0e148-5069-4b00-8d94-735d064e9dde',0,1,1,'2016-04-30 20:31:20',NULL,'5693e099-c6fb-40f3-b23f-42a06fdbb084','TOPIC_5693e099-c6fb-40f3-b23f-42a06fdbb084.png'),(10,'today','today','41b0e148-5069-4b00-8d94-735d064e9dde',1,1,1,'2016-04-30 20:36:24','2016-05-02 13:35:11','93ed5b3c-c15a-4828-a08a-27a3697ebbbe','TOPIC_93ed5b3c-c15a-4828-a08a-27a3697ebbbe.png'),(11,'aj ka topic','aj ka topic','41b0e148-5069-4b00-8d94-735d064e9dde',0,2,1,'2016-04-30 21:03:29',NULL,'b4f72a2c-62e3-4fe6-ad02-1fcdbe40738d',NULL),(12,'new topic dated 2  may','new desc dated 2 may','41b0e148-5069-4b00-8d94-735d064e9dde',1,10,1,'2016-05-02 14:15:11','2016-05-02 14:17:34','212abd40-5358-47d8-9b64-f4e9d8464c04','TOPIC_212abd40-5358-47d8-9b64-f4e9d8464c04.png'),(13,'new topic dated 2 may first','new topic dated 2 may first','41b0e148-5069-4b00-8d94-735d064e9dde',1,10,1,'2016-05-02 14:16:32','2016-07-28 00:14:05','97589f5f-ea30-4aad-8d3e-172f2d9b7b96',NULL),(14,'7 may','7 th may','41b0e148-5069-4b00-8d94-735d064e9dde',1,2,1,'2016-05-07 17:03:19','2016-05-07 17:04:03','65a5e630-4bd5-4a2f-a545-1d46be499223','TOPIC_65a5e630-4bd5-4a2f-a545-1d46be499223.png');

/*Table structure for table `cst_users` */

DROP TABLE IF EXISTS `cst_users`;

CREATE TABLE `cst_users` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `user_id` bigint(20) NOT NULL,
  `first_name` varchar(100) DEFAULT NULL,
  `middle_name` varchar(100) DEFAULT NULL,
  `last_name` varchar(100) DEFAULT NULL,
  `contact_id` int(11) DEFAULT NULL,
  `user_type_id` int(11) DEFAULT NULL,
  `secur_uuid` varchar(100) DEFAULT NULL,
  `class_id` int(11) DEFAULT NULL,
  `parents_id` int(11) DEFAULT NULL,
  `academic_id` int(11) DEFAULT NULL,
  `created_date` timestamp NULL DEFAULT CURRENT_TIMESTAMP,
  `profile_photo` varchar(100) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=24 DEFAULT CHARSET=latin1;

/*Data for the table `cst_users` */

insert  into `cst_users`(`id`,`user_id`,`first_name`,`middle_name`,`last_name`,`contact_id`,`user_type_id`,`secur_uuid`,`class_id`,`parents_id`,`academic_id`,`created_date`,`profile_photo`) values (12,19,'admin','none','kumar',16,NULL,'802d096e-bcd1-4559-b5af-16a93149bbc7',1,4,8,'2016-02-05 14:46:21',NULL),(15,22,'prakash','singh','bisht',19,NULL,'7c001cf1-0326-4e6d-8d0f-5b46f937afb5',3,7,11,'2016-02-16 22:57:27',NULL),(16,23,'himanshu','kumar','sharma',20,NULL,'34368fb4-6047-419d-bfcd-003b0d1dc809',1,8,12,'2016-02-18 21:54:05','PROFILE_16.png'),(17,24,'abhishek kumar','kumarrr','test',21,NULL,'41b0e148-5069-4b00-8d94-735d064e9dde',1,9,13,'2016-02-21 22:52:29','PROFILE_17.JPG'),(18,25,'raman','kumar','jangid',22,NULL,'59bac1a6-c07e-4f23-a41b-f302170415d9',1,10,14,'2016-05-03 22:23:29',NULL),(19,26,'raman','kumar','jangid',23,NULL,'fd83e8e8-d886-4274-94f6-a0a273192e69',2,11,15,'2016-05-03 22:24:09',NULL),(20,27,'jitendra','kumar','pareek',24,NULL,'d1849dea-93a3-4d56-8f56-95cb01f58557',3,12,16,'2016-05-03 22:28:02',NULL),(21,28,'pankaj','kumar','singh',25,NULL,'021432eb-ea7c-4f17-9199-89f1934a794d',6,13,17,'2016-05-03 22:30:40',NULL),(22,29,'Deepak','Kumar','Sharma',26,NULL,'3be2050d-e361-482a-9537-3f9157b5e637',2,14,18,'2016-11-06 09:45:50','PROFILE_22.JPG'),(23,30,'sachin','kumar','sharma',27,NULL,'61433276-4cb9-49fa-9150-f4222647676b',2,15,19,'2016-11-06 10:45:01',NULL);

/*Table structure for table `cst_usertypes` */

DROP TABLE IF EXISTS `cst_usertypes`;

CREATE TABLE `cst_usertypes` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `user_type` varchar(50) DEFAULT NULL,
  `description` text,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

/*Data for the table `cst_usertypes` */

/*Table structure for table `cst_videos` */

DROP TABLE IF EXISTS `cst_videos`;

CREATE TABLE `cst_videos` (
  `video_id` int(11) NOT NULL AUTO_INCREMENT,
  `video_embedded_link` text,
  `video_title` varchar(128) DEFAULT NULL,
  `video_description` varchar(500) DEFAULT NULL,
  `created_time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP,
  `modified_time` datetime DEFAULT NULL,
  `video_uuid` varchar(100) DEFAULT NULL,
  `class_id` int(11) NOT NULL,
  `subject_id` int(11) NOT NULL,
  PRIMARY KEY (`video_id`)
) ENGINE=InnoDB AUTO_INCREMENT=8 DEFAULT CHARSET=latin1;

/*Data for the table `cst_videos` */

insert  into `cst_videos`(`video_id`,`video_embedded_link`,`video_title`,`video_description`,`created_time`,`modified_time`,`video_uuid`,`class_id`,`subject_id`) values (1,'https://www.youtube.com/watch?v=C5Z8WQv1Wf4','English 2nd Paper Class 8 Article ','English 2nd Paper Class 8 Article ','2016-05-26 23:59:51','2016-05-26 23:59:51','qeurajkfajdkfajdfkaldfjakld',1,1),(4,'https://www.youtube.com/watch?v=dDwNZA2bGs8','2','2','2016-05-27 01:26:08','2016-05-27 01:26:08','55',1,1),(5,'https://www.youtube.com/watch?v=2eliQ_KR8yA','4','4','2016-05-27 01:27:14','2016-05-27 01:27:14','4',1,1),(6,'https://www.youtube.com/watch?v=aWMTj-rejvc','Baby ko base pasand hain',NULL,'2016-06-22 00:11:34','2016-06-22 00:11:09','8bc82973-471e-4399-aed4-05a9df00bf1b',1,1),(7,'https://www.youtube.com/watch?v=1Q8fG0TtVAY','Test Video udpate',NULL,'2016-11-06 10:11:09','2016-11-06 10:11:27','1317d2d8-50d6-42e3-9131-62fa36d2f74c',2,1);

/* Trigger structure for table `cst_discussion_topic_comments` */

DELIMITER $$

/*!50003 DROP TRIGGER*//*!50032 IF EXISTS */ /*!50003 `after_cst_discussion_topic_comments_delete` */$$

/*!50003 CREATE */ /*!50017 DEFINER = 'root'@'localhost' */ /*!50003 TRIGGER `after_cst_discussion_topic_comments_delete` AFTER DELETE ON `cst_discussion_topic_comments` FOR EACH ROW BEGIN
	INSERT INTO `cst_discussion_topic_comments_history`
	SET discussion_comment_id=OLD.discussion_comment_id,	
	discussion_topic_id =OLD.discussion_topic_id,
	general_comments =OLD.general_comments,
	created_date=OLD.created_date,
	modified_date=OLD.modified_date ,
	user_securUuid=OLD.user_securUuid,
	comment_uuid=OLD.comment_uuid,
	change_date =now();
        END */$$


DELIMITER ;

/* Trigger structure for table `cst_discussion_topics` */

DELIMITER $$

/*!50003 DROP TRIGGER*//*!50032 IF EXISTS */ /*!50003 `after_cst_discussion_topics_delete` */$$

/*!50003 CREATE */ /*!50017 DEFINER = 'root'@'localhost' */ /*!50003 TRIGGER `after_cst_discussion_topics_delete` AFTER DELETE ON `cst_discussion_topics` FOR EACH ROW BEGIN
	INSERT INTO `cst_discussion_topics_history`
	SET discussion_topic_id=OLD.discussion_topic_id,
	class_id=OLD.class_id,
	subject_id=OLD.subject_id,
	topic_title=OLD.topic_title,
	topic_description=OLD.topic_description,
	created_date=OLD.created_date,
	modified_date=OLD.modified_date,
	secur_uuid=OLD.secur_uuid,	 
	change_date =NOW();
        END */$$


DELIMITER ;

/* Function  structure for function  `trim_spaces` */

/*!50003 DROP FUNCTION IF EXISTS `trim_spaces` */;
DELIMITER $$

/*!50003 CREATE DEFINER=`root`@`localhost` FUNCTION `trim_spaces`(`dirty_string` text, `trimChar` varchar(1)) RETURNS text CHARSET latin1
BEGIN
  declare cnt,len int(11) ;
  declare clean_string text;
  declare chr,lst varchar(1);
  set len=length(dirty_string);
  set cnt=1;  
  set clean_string='';
 while cnt <= len do
      set  chr=right(left(dirty_string,cnt),1);           
      if  chr <> trimChar OR (chr=trimChar AND lst <> trimChar ) then  
          set  clean_string =concat(clean_string,chr);
      set  lst=chr;     
     end if;
     set cnt=cnt+1;  
  end while;
  return clean_string;
END */$$
DELIMITER ;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;