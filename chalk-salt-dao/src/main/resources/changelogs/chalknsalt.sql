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
) ENGINE=InnoDB AUTO_INCREMENT=9 DEFAULT CHARSET=latin1;

/*Data for the table `cst_academic_details` */

insert  into `cst_academic_details`(`id`,`student_class_id`,`percentage`,`previous_school`) values (1,1,'43.54','new schooll'),(8,1,'0.00',NULL);

/*Table structure for table `cst_class_subject_mapping` */

DROP TABLE IF EXISTS `cst_class_subject_mapping`;

CREATE TABLE `cst_class_subject_mapping` (
  `class_subject_id` int(11) NOT NULL AUTO_INCREMENT,
  `class_id` int(11) DEFAULT NULL,
  `subject_id` int(11) DEFAULT NULL,
  PRIMARY KEY (`class_subject_id`)
) ENGINE=InnoDB AUTO_INCREMENT=31 DEFAULT CHARSET=latin1;

/*Data for the table `cst_class_subject_mapping` */

insert  into `cst_class_subject_mapping`(`class_subject_id`,`class_id`,`subject_id`) values (1,1,1),(2,1,2),(3,1,3),(4,1,4),(6,2,1),(7,2,2),(8,2,3),(9,2,4),(11,3,1),(12,3,2),(13,3,3),(14,3,4),(16,4,1),(17,4,5),(18,4,6),(21,5,1),(22,5,5),(23,5,6),(26,6,7),(27,7,8),(28,8,9);

/*Table structure for table `cst_class_subjects` */

DROP TABLE IF EXISTS `cst_class_subjects`;

CREATE TABLE `cst_class_subjects` (
  `subject_id` int(11) NOT NULL AUTO_INCREMENT,
  `subject_name` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`subject_id`)
) ENGINE=InnoDB AUTO_INCREMENT=11 DEFAULT CHARSET=latin1;

/*Data for the table `cst_class_subjects` */

insert  into `cst_class_subjects`(`subject_id`,`subject_name`) values (1,'Maths'),(2,'English'),(3,'Science'),(4,'Social Studies'),(5,'Physics'),(6,'Chemistry'),(7,'National Defence Academy'),(8,'NATA Coaching'),(9,'Olympiads/NTSE');

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
) ENGINE=InnoDB AUTO_INCREMENT=17 DEFAULT CHARSET=latin1;

/*Data for the table `cst_contacts` */

insert  into `cst_contacts`(`id`,`address`,`city`,`state`,`country`,`pincode`,`mobile`,`landline`,`fax`,`email`,`corsAddress`) values (2,'103, Ambapark Colony address','Ajmer','Rajasthan','India','305001','232323232','12121212121',NULL,'tadfa@gmail.com','new address'),(16,'house no 380','Faridabad','Haryana','IN','121004','1111111111','12121212121',NULL,'abhishek.kumar627@gmail.com','corsaddress');

/*Table structure for table `cst_discussion_topic_comments` */

DROP TABLE IF EXISTS `cst_discussion_topic_comments`;

CREATE TABLE `cst_discussion_topic_comments` (
  `discussion_comment_id` int(11) NOT NULL AUTO_INCREMENT,
  `discussion_topic_id` int(11) DEFAULT NULL,
  `general_comments` varchar(500) DEFAULT NULL,
  `created_date` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP,
  `modified_date` timestamp NULL DEFAULT NULL,
  `delete_status` tinyint(1) DEFAULT '1',
  PRIMARY KEY (`discussion_comment_id`)
) ENGINE=InnoDB AUTO_INCREMENT=10 DEFAULT CHARSET=latin1;

/*Data for the table `cst_discussion_topic_comments` */

insert  into `cst_discussion_topic_comments`(`discussion_comment_id`,`discussion_topic_id`,`general_comments`,`created_date`,`modified_date`,`delete_status`) values (1,1,'gfgfg','2016-01-30 17:36:11','2016-01-30 17:36:11',1),(2,1,'gfdgdgd','2016-01-30 18:07:41','2016-01-30 18:07:41',1),(3,2,'hjkgkjg','2016-01-30 18:07:45','2016-01-30 18:07:45',1),(4,2,'dhdghdg','2016-01-30 18:07:49','2016-01-30 18:07:49',1),(5,2,'dghdfhhdh','2016-01-30 18:07:51','2016-01-30 18:07:51',1),(6,3,'fggshdjf','2016-01-30 18:07:57','2016-01-30 18:07:57',1),(7,1,'fgfsfgggfs','2016-01-30 18:07:59','2016-01-30 18:07:59',1),(8,3,'dhgdhdghh','2016-01-30 18:08:08','2016-01-30 18:08:08',1),(9,2,'ffdgfdh','2016-01-30 18:08:16','2016-01-30 18:08:16',1);

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
  PRIMARY KEY (`discussion_topic_id`)
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=latin1;

/*Data for the table `cst_discussion_topics` */

insert  into `cst_discussion_topics`(`discussion_topic_id`,`class_id`,`subject_id`,`topic_title`,`topic_description`,`created_date`,`modified_date`,`secur_uuid`) values (1,1,1,'Test Title','Test Desc','2016-01-24 00:00:00','2016-01-24 01:00:00','4e0b6910-5650-497b-81c5-4d4e96c06d6d'),(2,1,3,'Human Body','Define Human Body','2016-01-24 00:00:00','2016-01-24 00:00:00','b16d8432-e5ea-42d6-8521-0ab33326ca8c'),(3,1,2,'Financial Aspects','Indian Economy is based on poor people and farmers. So they need to be powerful.','2016-01-30 18:07:09','2016-01-30 18:07:09','5a0fcf08-a2eb-457f-8390-ecf8ec8fef72'),(4,1,1,'first topic','first description','2016-02-01 20:15:11','2016-02-01 20:15:11','f17d3136-c010-4d0b-8ab9-a4eaae1b2f1c');

/*Table structure for table `cst_logins` */

DROP TABLE IF EXISTS `cst_logins`;

CREATE TABLE `cst_logins` (
  `user_id` bigint(20) NOT NULL AUTO_INCREMENT,
  `username` varchar(50) NOT NULL,
  `password` text NOT NULL,
  `active` tinyint(4) NOT NULL DEFAULT '1',
  `disabled_from` datetime DEFAULT NULL,
  PRIMARY KEY (`user_id`)
) ENGINE=InnoDB AUTO_INCREMENT=20 DEFAULT CHARSET=latin1;

/*Data for the table `cst_logins` */

insert  into `cst_logins`(`user_id`,`username`,`password`,`active`,`disabled_from`) values (1,'jitendra','JDJhJDEyJGVVMTA1T1l4VUpSRkNvenVqYlpHSGV1bVNxWjdqLzBlM3hBUkY5SkhiQzhFNldvODRQTk42',1,NULL),(19,'admin','JDJhJDEyJFFIT1pmZHhlN2tjTW5WUU1VU0hsZHVyU2NRbFFMLjZlM1VzMS9kVnR5MEtvRUpnSjh6alhL',1,NULL);

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
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=latin1;

/*Data for the table `cst_parents` */

insert  into `cst_parents`(`id`,`father_name`,`mother_name`,`father_email`,`mother_email`,`father_mobile`,`mother_mobile`,`father_office_address`,`mother_office_address`,`father_occupation`,`mother_occupation`) values (1,'Satish Sharma updated333','minakshi sharma333','newsharmaji@gmail.com','abhishek.kumar623337@gmail.com','2123434343','121212121','new address 3434','address2333','Software Engineer l1 l2','House Wife l2333'),(4,'father name','mothername','father@gmail.com','mother@gmail.com','1212121212','3434343434','officeAddress','office Address','occupation','mother occupation');

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
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=13 DEFAULT CHARSET=latin1;

/*Data for the table `cst_users` */

insert  into `cst_users`(`id`,`user_id`,`first_name`,`middle_name`,`last_name`,`contact_id`,`user_type_id`,`secur_uuid`,`class_id`,`parents_id`,`academic_id`) values (1,1,'jitendra','lastname','middlename',2,0,'4e0b6910-5650-497b-81c5-4d4e96c06d6d',1,1,1),(12,19,'admin','none','kumar',16,NULL,'802d096e-bcd1-4559-b5af-16a93149bbc7',1,4,8);

/*Table structure for table `cst_usertypes` */

DROP TABLE IF EXISTS `cst_usertypes`;

CREATE TABLE `cst_usertypes` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `user_type` varchar(50) DEFAULT NULL,
  `description` text,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

/*Data for the table `cst_usertypes` */

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;
