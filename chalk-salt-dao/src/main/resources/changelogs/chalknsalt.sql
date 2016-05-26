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
) ENGINE=InnoDB AUTO_INCREMENT=12 DEFAULT CHARSET=latin1;

/*Data for the table `cst_academic_details` */

insert  into `cst_academic_details`(`id`,`student_class_id`,`percentage`,`previous_school`) values (1,1,'43.54','new schooll'),(8,1,'0.00',NULL),(9,1,'0.00',NULL),(10,2,'0.00',NULL),(11,3,'0.00',NULL);

/*Table structure for table `cst_class_subject_mapping` */

DROP TABLE IF EXISTS `cst_class_subject_mapping`;

CREATE TABLE `cst_class_subject_mapping` (
  `class_subject_id` int(11) NOT NULL AUTO_INCREMENT,
  `class_id` int(11) DEFAULT NULL,
  `subject_id` int(11) DEFAULT NULL,
  PRIMARY KEY (`class_subject_id`)
) ENGINE=InnoDB AUTO_INCREMENT=29 DEFAULT CHARSET=latin1;

/*Data for the table `cst_class_subject_mapping` */

insert  into `cst_class_subject_mapping`(`class_subject_id`,`class_id`,`subject_id`) values (1,1,1),(2,1,2),(3,1,3),(4,1,4),(6,2,1),(7,2,2),(8,2,3),(9,2,4),(11,3,1),(12,3,2),(13,3,3),(14,3,4),(16,4,1),(17,4,5),(18,4,6),(21,5,1),(22,5,5),(23,5,6),(26,6,7),(27,7,8),(28,8,9),(29,1,10),(30,2,10),(31,3,10),(32,4,10),(33,5,10),(34,6,10),(35,7,10),(36,8,10);

/*Table structure for table `cst_class_subjects` */

DROP TABLE IF EXISTS `cst_class_subjects`;

CREATE TABLE `cst_class_subjects` (
  `subject_id` int(11) NOT NULL AUTO_INCREMENT,
  `subject_name` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`subject_id`)
) ENGINE=InnoDB AUTO_INCREMENT=10 DEFAULT CHARSET=latin1;

/*Data for the table `cst_class_subjects` */

insert  into `cst_class_subjects`(`subject_id`,`subject_name`) values (1,'Maths'),(2,'English'),(3,'Science'),(4,'Social Studies'),(5,'Physics'),(6,'Chemistry'),(7,'National Defence Academy'),(8,'NATA Coaching'),(9,'Olympiads/NTSE'),(10,'General');
/*Table structure for table `cst_class_type` */

DROP TABLE IF EXISTS `cst_class_type`;

CREATE TABLE `cst_class_type` (
  `class_id` int(11) NOT NULL AUTO_INCREMENT,
  `class_name` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`class_id`),
  UNIQUE KEY `class_id` (`class_id`)
) ENGINE=InnoDB AUTO_INCREMENT=9 DEFAULT CHARSET=latin1;

/*Data for the table `cst_class_type` */

insert  into `cst_class_type`(`class_id`,`class_name`) values (1,'Foundation 8'),(2,'Foundation 9'),(3,'Foundation 10'),(4,'IIT-JEE 11'),(5,'IIT-JEE 12'),(6,'NDA'),(7,'Architecture'),(8,'Olympiads/NTSE');

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
) ENGINE=InnoDB AUTO_INCREMENT=20 DEFAULT CHARSET=latin1;

/*Data for the table `cst_contacts` */

insert  into `cst_contacts`(`id`,`address`,`city`,`state`,`country`,`pincode`,`mobile`,`landline`,`fax`,`email`,`corsAddress`) values (2,'103, Ambapark Colony address','Ajmer','Rajasthan','India','305001','232323232','12121212121',NULL,'tadfa@gmail.com','new address'),(16,'house no 380','Faridabad','Haryana','IN','121004','1111111111','12121212121',NULL,'abhishek.kumar627@gmail.com','corsaddress'),(17,'House No 380','Faridabad','Haryana','GE','121004',NULL,NULL,NULL,'abhishek.kumar627@gmail.com',NULL),(18,'delhi','Faridabad','Haryana','IN','121004',NULL,NULL,NULL,'snjkmr568@gmail.com',NULL),(19,'house no 343','Gurgaon','Haryana','IN','12104',NULL,NULL,NULL,'abhishek.kumar627@gmail.com',NULL);

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
) ENGINE=InnoDB AUTO_INCREMENT=51 DEFAULT CHARSET=latin1;

/*Data for the table `cst_discussion_topic_comments` */

insert  into `cst_discussion_topic_comments`(`discussion_comment_id`,`discussion_topic_id`,`general_comments`,`created_date`,`modified_date`,`user_securUuid`,`comment_uuid`) values (1,1,'How are you going TO CHANGE the class IN a way that\r\n How are you going TO CHANGE the class IN a way that\r\n How are you going TO CHANGE the class IN a way that\r\n How are you going TO CHANGE the class IN a way that\r\n How are you going TO CHANGE the class IN a way that\r\n How are you going TO CHANGE the class IN a way that\r\n How are you going TO CHANGE the class IN a way that','2016-01-30 17:36:11','2016-01-30 17:36:11','4e0b6910-5650-497b-81c5-4d4e96c06d6d','4e0b6910-5650-497b-81c5-4d4e96c06d6d'),(2,1,'new comment to be displayed now','2016-01-30 18:07:41','2016-02-06 17:48:19','b3023d41-494c-471b-ac87-9ea93695b198','b3023d41-494c-471b-ac87-9ea93695b198'),(3,2,'hjkgkjg','2016-01-30 18:07:45','2016-01-30 18:07:45','4e0b6910-5650-497b-81c5-4d4e96c06d6d','1'),(4,2,'dhdghdg','2016-01-30 18:07:49','2016-01-30 18:07:49','4e0b6910-5650-497b-81c5-4d4e96c06d6d','2'),(5,2,'dghdfhhdh','2016-01-30 18:07:51','2016-01-30 18:07:51','4e0b6910-5650-497b-81c5-4d4e96c06d6d','3'),(7,1,'fgfsfgggfs','2016-01-30 18:07:59','2016-01-30 18:07:59','4e0b6910-5650-497b-81c5-4d4e96c06d6d','5'),(9,2,'ffdgfdh','2016-01-30 18:08:16','2016-01-30 18:08:16','b3023d41-494c-471b-ac87-9ea93695b198','7'),(10,4,'hello','2016-02-03 20:18:37','2016-02-03 20:18:37','b3023d41-494c-471b-ac87-9ea93695b198','8'),(11,1,'not null to be update now','2016-02-06 00:19:42','2016-02-06 17:49:04','b3023d41-494c-471b-ac87-9ea93695b198','8df20e96-7bb5-47b5-b2fe-1ecf1266d619'),(12,1,'not null','2016-02-06 00:24:34','2016-02-06 00:24:34','b3023d41-494c-471b-ac87-9ea93695b198','4f05b55b-66d7-4b4a-b1fd-bdde9c5757b1'),(13,1,'new comments here','2016-02-06 00:25:04','2016-02-06 00:25:04','b3023d41-494c-471b-ac87-9ea93695b198','7ea03ef1-633f-4161-81ea-303005d46cde'),(14,1,'newcomment by abhishek','2016-02-06 00:25:32','2016-02-06 00:25:32','b3023d41-494c-471b-ac87-9ea93695b198','e0af559c-f50f-40f2-bdd3-e0dbd797ddb9'),(15,1,'new comment by abhishek','2016-02-06 00:27:22','2016-02-06 00:27:22','4e0b6910-5650-497b-81c5-4d4e96c06d6d','3249d68a-4501-4b6b-a8b6-950cb487cdc8'),(16,1,'new commet by jitendra','2016-02-06 00:27:42','2016-02-06 00:27:42','4e0b6910-5650-497b-81c5-4d4e96c06d6d','6e4508a8-6c9b-4aa7-8404-edd942d96834'),(17,1,'test comment','2016-02-06 00:29:05','2016-02-06 00:29:05','4e0b6910-5650-497b-81c5-4d4e96c06d6d','6dea49aa-419e-441d-a7d5-91c6526a09d3'),(18,1,'new comment by jitendra','2016-02-06 00:29:21','2016-02-06 00:29:21','4e0b6910-5650-497b-81c5-4d4e96c06d6d','f70d5539-d684-41ba-973e-71ced2d9c795'),(19,1,'new com','2016-02-06 00:31:16','2016-02-06 00:31:16','4e0b6910-5650-497b-81c5-4d4e96c06d6d','c9f8992b-6aef-41e5-aaf2-65f9f0493003'),(20,1,'hello','2016-02-06 00:32:03','2016-02-06 00:32:03','4e0b6910-5650-497b-81c5-4d4e96c06d6d','cddb06c6-c51b-49a0-8009-af809e899899'),(21,1,'hello','2016-02-06 00:32:18','2016-02-06 00:32:18','4e0b6910-5650-497b-81c5-4d4e96c06d6d','1349cf09-fc94-4fb4-8426-91866fa51c22'),(22,1,'new comment to be donw','2016-02-06 09:43:04','2016-02-06 09:43:04','4e0b6910-5650-497b-81c5-4d4e96c06d6d','6febc6e8-f241-410a-abd7-9a080a501d76'),(24,4,'topic comments','2016-02-06 11:08:37','2016-02-06 11:08:37','b3023d41-494c-471b-ac87-9ea93695b198','f7ea58ac-9334-4ec0-a6d2-406366c82b84'),(25,1,'new cimmm udpated now','2016-02-06 16:02:11','2016-02-06 17:49:18','b3023d41-494c-471b-ac87-9ea93695b198','bf62e231-5c51-460f-b49b-a7d40720a977'),(26,1,'newcomment by abhishek','2016-02-06 16:38:13','2016-02-06 16:38:13','b3023d41-494c-471b-ac87-9ea93695b198','66a575f4-9d9c-439f-a35f-235524aeff3f'),(28,4,'new comment updated successfully','2016-02-10 20:13:50','2016-02-10 20:14:15','b3023d41-494c-471b-ac87-9ea93695b198','411328bc-8e93-40f2-8524-ac436a3e0215'),(30,4,'new coooooooooooooooooooooooooooooooooo','2016-02-11 21:57:02','2016-02-11 21:57:02','b3023d41-494c-471b-ac87-9ea93695b198','1e9ecfeb-3783-45ff-8166-977a8f152c00'),(31,4,'first comment','2016-02-11 21:57:11','2016-02-11 21:57:11','b3023d41-494c-471b-ac87-9ea93695b198','a73352c8-127a-4176-b222-63a4cb1c1101'),(32,4,'second comment','2016-02-11 21:57:19','2016-02-11 21:57:19','b3023d41-494c-471b-ac87-9ea93695b198','c90071f9-d747-4186-bdc3-d410c3e5948d'),(33,4,'third comment','2016-02-11 21:57:27','2016-02-11 21:57:27','b3023d41-494c-471b-ac87-9ea93695b198','dca8e361-6971-4da8-ae11-9fde5f448ad3'),(34,4,'fourth comment','2016-02-11 21:57:35','2016-02-11 21:57:35','b3023d41-494c-471b-ac87-9ea93695b198','6b07085c-cdf0-4db4-9cfa-3d1deea04780'),(35,4,'firfth','2016-02-11 21:57:41','2016-02-11 21:57:41','b3023d41-494c-471b-ac87-9ea93695b198','5f046d26-2c40-409e-af7c-3cda20bf5ed5'),(36,4,'sidhth','2016-02-11 21:57:49','2016-02-11 21:57:49','b3023d41-494c-471b-ac87-9ea93695b198','00507093-2314-45e8-8e19-7836afdd4375'),(37,4,'safasdfasd','2016-02-11 21:57:55','2016-02-11 21:57:55','b3023d41-494c-471b-ac87-9ea93695b198','37a43330-ad87-4b5f-a922-e7927fa2366b'),(38,4,'adfadsfs','2016-02-11 21:58:01','2016-02-11 21:58:01','b3023d41-494c-471b-ac87-9ea93695b198','2f5644a1-45a1-4c61-9c73-109d2a73fbab'),(39,4,'adfdfasdf','2016-02-11 21:58:09','2016-02-11 21:58:09','b3023d41-494c-471b-ac87-9ea93695b198','91094af9-4c40-4e2c-98ee-e9e69c3aeb12'),(40,4,'new comment udpated','2016-02-12 20:53:57','2016-02-12 20:54:10','b3023d41-494c-471b-ac87-9ea93695b198','9bc2d9a9-de2a-479c-ad40-925fd2edb619'),(41,3,'hello','2016-02-12 20:55:05','2016-02-12 20:55:05','b3023d41-494c-471b-ac87-9ea93695b198','3eab7a6e-1c80-4db9-abc2-ec1c1ca596db');

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
) ENGINE=InnoDB AUTO_INCREMENT=8 DEFAULT CHARSET=latin1;

/*Data for the table `cst_discussion_topic_comments_history` */

insert  into `cst_discussion_topic_comments_history`(`history_id`,`discussion_comment_id`,`discussion_topic_id`,`general_comments`,`created_date`,`modified_date`,`user_securUuid`,`comment_uuid`,`change_date`) values (1,45,4,'new comments updated last','2016-02-15 23:33:34','2016-02-15 23:33:34','b3023d41-494c-471b-ac87-9ea93695b198','4da9ef39-06a0-4b27-a1ac-4e1f69c0c6b0','2016-02-16 22:43:56'),(2,42,4,'new  comment added here by','2016-02-14 11:10:14','2016-02-14 11:11:05','b3023d41-494c-471b-ac87-9ea93695b198','806bee47-7586-4ca1-a75f-922e3ce74bf3','2016-02-16 22:45:44'),(3,50,26,'topic 2 comment 2','2016-02-16 23:00:05','2016-02-16 23:00:05','7c001cf1-0326-4e6d-8d0f-5b46f937afb5','fb47fcbf-9a62-4c99-91fa-da46eedea8ea','2016-02-16 23:01:17'),(4,49,26,'topic 2 comment 1','2016-02-16 22:59:55','2016-02-16 22:59:55','7c001cf1-0326-4e6d-8d0f-5b46f937afb5','2ee181ce-a877-4106-ac07-23193694e220','2016-02-16 23:02:07'),(5,46,25,'comment 1','2016-02-16 22:59:11','2016-02-16 22:59:11','7c001cf1-0326-4e6d-8d0f-5b46f937afb5','7ed922fe-376b-449b-aa98-29c8a0edd9be','2016-02-16 23:04:35'),(6,47,25,'comment 2','2016-02-16 22:59:21','2016-02-16 22:59:21','7c001cf1-0326-4e6d-8d0f-5b46f937afb5','0b2b94fd-6171-4983-84bb-a2b1720a3696','2016-02-16 23:04:35'),(7,48,25,'comment 3','2016-02-16 22:59:39','2016-02-16 22:59:39','7c001cf1-0326-4e6d-8d0f-5b46f937afb5','447aa66b-f50f-47b9-9d9c-b2161e6d9eb4','2016-02-16 23:04:35');

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
) ENGINE=InnoDB AUTO_INCREMENT=27 DEFAULT CHARSET=latin1;

/*Data for the table `cst_discussion_topics` */

insert  into `cst_discussion_topics`(`discussion_topic_id`,`class_id`,`subject_id`,`topic_title`,`topic_description`,`created_date`,`modified_date`,`secur_uuid`) values (2,1,3,'Human Body','Define Human Body','2016-01-24 00:00:00','2016-01-24 00:00:00','b16d8432-e5ea-42d6-8521-0ab33326ca8c'),(3,1,2,'Financial Aspects','Indian Economy is based on poor people and farmers. So they need to be powerful.','2016-01-30 18:07:09','2016-01-30 18:07:09','5a0fcf08-a2eb-457f-8390-ecf8ec8fef72'),(4,1,1,'first topic n,mnmn','first description','2016-02-01 20:15:11','2016-02-15 22:49:57','f17d3136-c010-4d0b-8ab9-a4eaae1b2f1c'),(6,1,1,'topic 2','desc 2','2016-02-03 21:05:04','2016-02-03 21:05:04','5e337702-5486-4e03-b742-fdf214440d48'),(7,1,1,'topic 3','desc 3','2016-02-03 21:05:30','2016-02-03 21:05:30','8b4cf773-fa95-4b9d-a47c-49e5340fedeb'),(8,1,1,'tpooo','dsofdsf','2016-02-03 21:05:51','2016-02-03 21:05:51','fea4bbf2-1353-4a90-ad83-9634be84c542'),(9,1,1,'nnenlkdf','nadfad','2016-02-03 21:06:05','2016-02-03 21:06:05','142a95b5-5c45-4a53-a796-bf2d59339c10'),(11,1,1,'new taodfad','kjdfadklfads','2016-02-03 21:06:39','2016-02-03 21:06:39','5cf34a8f-e825-47f3-aff8-b37d58d0c967'),(12,1,1,'new toopsdfasdfas','sdfasdfadsf','2016-02-03 21:08:32','2016-02-08 20:15:46','6fdd54d2-bf3f-43b2-b231-ebdfe81cf658'),(13,1,1,'hhihihiih','lllllll','2016-02-03 21:09:04','2016-02-03 21:09:04','d148cf81-dc6b-4729-b84e-76357f595b66'),(14,1,1,'heloolsooso','ldskfjadfjasdkf','2016-02-03 21:09:20','2016-02-03 21:09:20','17bc4e1c-e3a5-45ec-970d-3c864c975404'),(16,1,1,'new topic jjjjjjjjjjjjjjjjjj','njjjdfdfd','2016-02-08 21:33:16','2016-02-08 21:33:16','fcae3921-b1e4-42a3-8138-10b42ed052d0'),(17,1,1,'11111111111111','23456','2016-02-08 21:43:39','2016-02-08 21:43:39','d5a2c18b-bd68-40ee-9918-9cd81331054e'),(19,2,1,'1 topic','2 desc','2016-02-10 23:23:10','2016-02-10 23:23:10','76331b6c-c6f7-476e-ba16-1a129025c623'),(20,1,1,'adfadsf','adfads','2016-02-15 22:13:04','2016-02-15 22:13:04','13c33fc5-5340-46c1-8d63-0024723037c0'),(21,1,1,'title','hello','2016-02-15 22:26:25','2016-02-15 22:26:25','c8a1a497-43f6-4ada-9643-dcd9c45aab9e'),(22,1,1,'new topic created successfully','new topic created successfully','2016-02-15 22:49:19','2016-02-15 22:49:19','06087ab9-1273-470e-971c-d05d20b48c3d');

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
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=latin1;

/*Data for the table `cst_discussion_topics_history` */

insert  into `cst_discussion_topics_history`(`history_id`,`discussion_topic_id`,`class_id`,`subject_id`,`topic_title`,`topic_description`,`created_date`,`modified_date`,`secur_uuid`,`change_date`) values (1,24,6,7,'new topic','new topic','2016-02-15 22:51:51','2016-02-15 22:51:51','b700d223-fcc8-4deb-abea-43066ee0c498','2016-02-16 22:47:05'),(2,26,3,1,'foundation 10 topic 2','foundation 10 topic 2 desc','2016-02-16 22:55:43','2016-02-16 22:55:43','f4b4aafa-af56-42b2-b954-e534002e2d3a','2016-02-16 23:03:13'),(3,25,3,1,'foundation 10 topic','foundation 10 desc','2016-02-16 22:55:14','2016-02-16 22:55:14','5d748330-5b5e-410f-af34-88d0d8467291','2016-02-16 23:04:36');

/*Table structure for table `cst_logins` */

DROP TABLE IF EXISTS `cst_logins`;

CREATE TABLE `cst_logins` (
  `user_id` bigint(20) NOT NULL AUTO_INCREMENT,
  `username` varchar(50) NOT NULL,
  `password` text NOT NULL,
  `active` tinyint(4) NOT NULL DEFAULT '1',
  `disabled_from` datetime DEFAULT NULL,
  PRIMARY KEY (`user_id`)
) ENGINE=InnoDB AUTO_INCREMENT=23 DEFAULT CHARSET=latin1;

/*Data for the table `cst_logins` */

insert  into `cst_logins`(`user_id`,`username`,`password`,`active`,`disabled_from`) values (1,'jitendra','JDJhJDEyJGVVMTA1T1l4VUpSRkNvenVqYlpHSGV1bVNxWjdqLzBlM3hBUkY5SkhiQzhFNldvODRQTk42',1,NULL),(19,'admin','JDJhJDEyJFFIT1pmZHhlN2tjTW5WUU1VU0hsZHVyU2NRbFFMLjZlM1VzMS9kVnR5MEtvRUpnSjh6alhL',1,NULL),(20,'abhishek','JDJhJDEyJGJlcm13QVZkOUE5UFQ4QklMOGU4RS4ueXI3T2E0eUdUNS9XZnk4d0MzdzB6ekEyZjZGS01D',1,NULL),(21,'sanjay','JDJhJDEyJEpHYUVVZzZGaTBOLmIuTDFZVzZUTWUzc1huOU1HUUNJV0FMaUtyeHBGRmlidnhjSHRvbElP',1,NULL),(22,'prakash','JDJhJDEyJDlzOFFiRmR2TXVxa0FRb2Fic3RGTHVKUWxoNHZLUlJuTGNHNk9jMmFJWm1DUGZBTkt3a3Ft',1,NULL);

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
) ENGINE=InnoDB AUTO_INCREMENT=8 DEFAULT CHARSET=latin1;

/*Data for the table `cst_parents` */

insert  into `cst_parents`(`id`,`father_name`,`mother_name`,`father_email`,`mother_email`,`father_mobile`,`mother_mobile`,`father_office_address`,`mother_office_address`,`father_occupation`,`mother_occupation`) values (1,'Satish Sharma updated333','minakshi sharma333','newsharmaji@gmail.com','abhishek.kumar623337@gmail.com','2123434343','121212121','new address 3434','address2333','Software Engineer l1 l2','House Wife l2333'),(4,'father name','mothername','father@gmail.com','mother@gmail.com','1212121212','3434343434','officeAddress','office Address','occupation','mother occupation'),(5,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL),(6,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL),(7,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL);

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
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=latin1;

/*Data for the table `cst_template` */

insert  into `cst_template`(`id`,`primary_content`,`editable_content`,`subject`,`notification_recipient_type`,`notification_template_key`,`notification_type`,`merge_body_in_template`,`internal`,`recipient_id`,`merge_subject`) values (1,'Dear <b>${firstName}</b>,<br><br><br> Welcome On Chalk N Dust!  <br><br>A <b>Student</b> login has been created for you. <br>Please get in touch with the Chalk N Dust administrator to get your credentials. <br><br>Regards System Admin',NULL,'Chalk N Dust | User Registration',1,'USER_REGISTRATION_SUCCESSFUL',1,NULL,NULL,NULL,NULL);

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
) ENGINE=InnoDB AUTO_INCREMENT=18 DEFAULT CHARSET=latin1;

/*Data for the table `cst_users` */

insert  into `cst_users`(`id`,`user_id`,`first_name`,`middle_name`,`last_name`,`contact_id`,`user_type_id`,`secur_uuid`,`class_id`,`parents_id`,`academic_id`,`created_date`) values (1,1,'jitendra','lastname','middlename',2,0,'4e0b6910-5650-497b-81c5-4d4e96c06d6d',1,1,1,'2016-02-05 14:46:21'),(12,19,'admin','none','kumar',16,NULL,'802d096e-bcd1-4559-b5af-16a93149bbc7',1,4,8,'2016-02-05 14:46:21'),(13,20,'Abhishek','Kumar','Sharma',17,NULL,'b3023d41-494c-471b-ac87-9ea93695b198',1,5,9,'2016-02-05 14:46:21'),(14,21,'sanjay','kumar','mishra',18,NULL,'63ef56e2-3fd7-42d4-b84c-eeee0b7e387a',2,6,10,'2016-02-10 22:14:33'),(15,22,'prakash','singh','bisht',19,NULL,'7c001cf1-0326-4e6d-8d0f-5b46f937afb5',3,7,11,'2016-02-16 22:57:27');

/*Table structure for table `cst_usertypes` */

DROP TABLE IF EXISTS `cst_usertypes`;

CREATE TABLE `cst_usertypes` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `user_type` varchar(50) DEFAULT NULL,
  `description` text,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

/*Data for the table `cst_usertypes` */

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
  PRIMARY KEY (`topic_request_id`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=latin1;

/*Data for the table `cst_topic_requests` */

insert  into `cst_topic_requests`(`topic_request_id`,`topic_title`,`topic_description`,`secur_uuid`,`approved`,`subject_id`,`class_id`,`request_date`,`approval_date`) values (1,'even no','divisible by 2','4e0b6910-5650-497b-81c5-4d4e96c06d6d',0,1,1,'2016-02-29 17:41:21',NULL),(2,'water formula','h2o','4e0b6910-5650-497b-81c5-4d4e96c06d6d',0,3,1,'2016-02-29 17:44:55',NULL);

/*Table structure for table `cst_questions` */

DROP TABLE IF EXISTS `cst_questions`;

CREATE TABLE `cst_questions` (
  `question_id` bigint(20) NOT NULL AUTO_INCREMENT,
  `class_id` int(11) NOT NULL,
  `subject_id` int(11) NOT NULL,
  `question` text NOT NULL,
  `option1` text NOT NULL,
  `option2` text NOT NULL,
  `option3` text NOT NULL,
  `option4` text NOT NULL,
  `answer` int(11) NOT NULL,
  `marks` int(11) DEFAULT NULL,
  `deleted` tinyint(4) DEFAULT '0',
  `created_at` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP,
  `modified_at` datetime DEFAULT NULL,
  `question_uuid` varchar(100) NOT NULL,
  PRIMARY KEY (`question_id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

/*Data for the table `cst_questions` */

/*Table structure for table `cst_student_test` */

DROP TABLE IF EXISTS `cst_student_test`;

CREATE TABLE `cst_student_test` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `student_id` int(11) NOT NULL,
  `question_id` int(11) NOT NULL,
  `test_id` int(11) NOT NULL,
  `answer` int(11) DEFAULT NULL,
  `updt` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

/*Data for the table `cst_student_test` */

/*Table structure for table `cst_system_settings` */

DROP TABLE IF EXISTS `cst_system_settings`;

CREATE TABLE `cst_system_settings` (
  `settings_id` bigint(20) NOT NULL AUTO_INCREMENT,
  `settings_key` varchar(100) NOT NULL,
  `settings_value` varchar(200) NOT NULL,
  `description` text,
  PRIMARY KEY (`settings_id`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=latin1;

/*Data for the table `cst_system_settings` */

insert  into `cst_system_settings`(`settings_id`,`settings_key`,`settings_value`,`description`) values (1,'PROFILE_PHOTO','G:\\CHALKANDDUST\\ProfilePhoto\\','To store Profile photo'),(2,'TOPIC_IMAGE','G:\\CHALKANDDUST\\TopicPhoto\\','TO save topic image.');

/*Table structure for table `cst_test_master` */

DROP TABLE IF EXISTS `cst_test_master`;

CREATE TABLE `cst_test_master` (
  `test_id` bigint(20) NOT NULL AUTO_INCREMENT,
  `test_title` varchar(100) NOT NULL,
  `test_description` text,
  `test_date` date NOT NULL,
  `test_time` time DEFAULT NULL,
  `test_type_uuid` varchar(100) NOT NULL,
  `marks` int(11) NOT NULL,
  `class_id` int(11) NOT NULL,
  `subject_id` int(11) NOT NULL,
  `created_at` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP,
  `test_uuid` varchar(100) NOT NULL,
  PRIMARY KEY (`test_id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

/*Data for the table `cst_test_master` */

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
  `test_duration` int(11) NOT NULL,
  `no_of_questions` int(11) NOT NULL,
  PRIMARY KEY (`test_type_id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;


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

DELIMITER $$

USE `chalkndust`$$

DROP FUNCTION IF EXISTS `trim_spaces`$$

CREATE DEFINER=`root`@`localhost` FUNCTION `trim_spaces`(`dirty_string` TEXT, `trimChar` VARCHAR(1)) RETURNS TEXT CHARSET latin1
BEGIN
  DECLARE cnt,len INT(11) ;
  DECLARE clean_string TEXT;
  DECLARE chr,lst VARCHAR(1);
  SET len=LENGTH(dirty_string);
  SET cnt=1;  
  SET clean_string='';
 WHILE cnt <= len DO
      SET  chr=RIGHT(LEFT(dirty_string,cnt),1);           
      IF  chr <> trimChar OR (chr=trimChar AND lst <> trimChar ) THEN  
          SET  clean_string =CONCAT(clean_string,chr);
      SET  lst=chr;     
     END IF;
     SET cnt=cnt+1;  
  END WHILE;
  RETURN clean_string;
END$$

DELIMITER ;

ALTER TABLE `chalkndust`.`cst_topic_requests`     ADD COLUMN `request_securuuid` VARCHAR(100) NOT NULL AFTER `approval_date`;
ALTER TABLE `chalkndust`.`cst_topic_requests`     ADD COLUMN `topic_image` VARCHAR(255) NULL AFTER `request_securuuid`;

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
) ENGINE=InnoDB AUTO_INCREMENT=1 DEFAULT CHARSET=latin1;

ALTER TABLE `chalkndust`.`cst_questions`     CHANGE `answer` `answer` CHAR(1) NOT NULL;
ALTER TABLE `chalkndust`.`cst_questions`     ADD COLUMN `question_image` VARCHAR(100) NULL AFTER `question_uuid`;
INSERT INTO `chalkndust`.`cst_system_settings`(`settings_id`,`settings_key`,`settings_value`,`description`) VALUES ( NULL,'QUESTION_IMAGE','G:\\CHALKANDDUST\\QuestionPhoto\\','To save question image.');


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
) ENGINE=InnoDB AUTO_INCREMENT=6 DEFAULT CHARSET=latin1;

/*Data for the table `cst_videos` */

insert  into `cst_videos`(`video_id`,`video_embedded_link`,`video_title`,`video_description`,`created_time`,`modified_time`,`video_uuid`,`class_id`,`subject_id`) values (1,'https://www.youtube.com/watch?v=C5Z8WQv1Wf4','English 2nd Paper Class 8 Article ','English 2nd Paper Class 8 Article ','2016-05-26 23:59:51','2016-05-26 23:59:51','qeurajkfajdkfajdfkaldfjakld',1,1),(2,'https://www.youtube.com/watch?v=t7buUbo_1Qs','English 2nd Paper Class 8 Article ','English 2nd Paper Class 8 Article ','2016-05-27 00:54:52','2016-05-27 00:54:52','adfadfadfadfadsfadsfasdfdsfa',1,1),(3,'https://www.youtube.com/watch?v=hgumbCaV2g4','we','we','2016-05-27 01:24:24','2016-05-27 01:24:24','fgsrtdgsrdssasdhdfhsdg',1,1),(4,'https://www.youtube.com/watch?v=dDwNZA2bGs8','2','2','2016-05-27 01:26:08','2016-05-27 01:26:08','55',1,1),(5,'https://www.youtube.com/watch?v=2eliQ_KR8yA','4','4','2016-05-27 01:27:14','2016-05-27 01:27:14','4',1,1);

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;
