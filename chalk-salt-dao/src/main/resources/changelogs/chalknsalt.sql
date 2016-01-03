/*
SQLyog Community v8.82 
MySQL - 5.5.30 : Database - chalknsalt
*********************************************************************
*/


/*!40101 SET NAMES utf8 */;

/*!40101 SET SQL_MODE=''*/;

/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;
CREATE DATABASE /*!32312 IF NOT EXISTS*/`chalknsalt` /*!40100 DEFAULT CHARACTER SET latin1 */;

USE `chalknsalt`;

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
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=9 DEFAULT CHARSET=latin1;

/*Data for the table `cst_contacts` */

insert  into `cst_contacts`(`id`,`address`,`city`,`state`,`country`,`pincode`,`mobile`,`landline`,`fax`,`email`) values (2,'103, Ambapark Colony','Ajmer','Rajasthan','India','305001','9829944055','01463341541',NULL,'mr.jitendrapareek@gmail.com'),(8,'217, Sector 1','Gurgaon','Rajasthan','India','321004','97853215500','01247821565',NULL,'abhishek.kumar627@gmail.com');

/*Table structure for table `cst_logins` */

DROP TABLE IF EXISTS `cst_logins`;

CREATE TABLE `cst_logins` (
  `user_id` bigint(20) NOT NULL AUTO_INCREMENT,
  `username` varchar(50) NOT NULL,
  `password` text NOT NULL,
  `active` tinyint(4) NOT NULL DEFAULT '1',
  `disabled_from` datetime DEFAULT NULL,
  PRIMARY KEY (`user_id`)
) ENGINE=InnoDB AUTO_INCREMENT=12 DEFAULT CHARSET=latin1;

/*Data for the table `cst_logins` */

insert  into `cst_logins`(`user_id`,`username`,`password`,`active`,`disabled_from`) values (1,'jitendra','JDJhJDEyJGtwZlBsNXRGenZhM05lUUJ1elNVQ3VlRWJVUnFLNTBCbmRzMkhoS1J1TGs1TmQ4OXQ0eFBL',1,NULL),(11,'abhishek','JDJhJDEyJFAxYTZvNU9Yd0tZbDVYUXppM2pTOHUzdEVCUGpKQmNQckFHS1BYei92N28vQkVFQmYyODJL',1,NULL);

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
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=7 DEFAULT CHARSET=latin1;

/*Data for the table `cst_users` */

insert  into `cst_users`(`id`,`user_id`,`first_name`,`middle_name`,`last_name`,`contact_id`,`user_type_id`,`secur_uuid`) values (1,1,'Jitendra','Kumar','Pareek',2,0,'4e0b6910-5650-497b-81c5-4d4e96c06d6d'),(6,11,'abhishek',' Ji','kumar',8,NULL,'5a0fcf08-a2eb-457f-8390-ecf8ec8fef72');

/*Table structure for table `cst_usertypes` */

DROP TABLE IF EXISTS `cst_usertypes`;

CREATE TABLE `cst_usertypes` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `user_type` varchar(50) DEFAULT NULL,
  `description` text,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

CREATE TABLE IF NOT EXISTS `cst_parents` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `father_name` varchar(50) NOT NULL,
  `mother_name` varchar(50) NOT NULL,
  `father_email` varchar(50) NOT NULL,
  `mother_email` varchar(50) NOT NULL,
  `father_mobile` varchar(15) NOT NULL,
  `mother_mobile` varchar(15) NOT NULL,
  `father_office_address` text NOT NULL,
  `mother_office_address` text NOT NULL,
  `father_occupation` varchar(100) NOT NULL,
  `mother_occupation` varchar(100) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1 AUTO_INCREMENT=1 ;

alter table `cst_users` 
   add column `class_id` int(11) NULL after `secur_uuid`

CREATE TABLE `cst_class_type` (
  `class_id` int(11) NOT NULL AUTO_INCREMENT,
  `class_name` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`class_id`),
  UNIQUE KEY `class_id` (`class_id`)
) ENGINE=InnoDB AUTO_INCREMENT=7 DEFAULT CHARSET=latin1;

/*Data for the table `cst_class_type` */

insert  into `cst_class_type`(`class_id`,`class_name`) values (1,'seventh'),(2,'eighth'),(3,'nineth'),(4,'tenth'),(5,'eleventh'),(6,'twelveth');

/*Data for the table `cst_usertypes` */

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

DROP TABLE IF EXISTS `cst_class_subject_mapping`;

CREATE TABLE `cst_class_subject_mapping` (
  `class_subject_id` int(11) NOT NULL AUTO_INCREMENT,
  `class_id` int(11) DEFAULT NULL,
  `subject_id` int(11) DEFAULT NULL,
  PRIMARY KEY (`class_subject_id`)
) ENGINE=InnoDB AUTO_INCREMENT=31 DEFAULT CHARSET=latin1;

/*Data for the table `cst_class_subject_mapping` */

insert  into `cst_class_subject_mapping`(`class_subject_id`,`class_id`,`subject_id`) values (1,1,1),(2,1,2),(3,1,3),(4,1,4),(5,1,5),(6,2,1),(7,2,2),(8,2,3),(9,2,4),(10,2,5),(11,3,1),(12,3,2),(13,3,3),(14,3,4),(15,3,5),(16,4,1),(17,4,2),(18,4,3),(19,4,4),(20,4,5),(21,5,1),(22,5,2),(23,5,6),(24,5,7),(25,5,8),(26,6,1),(27,6,2),(28,6,6),(29,6,7),(30,6,8);

/*Table structure for table `cst_class_subjects` */

DROP TABLE IF EXISTS `cst_class_subjects`;

CREATE TABLE `cst_class_subjects` (
  `subject_id` int(11) NOT NULL AUTO_INCREMENT,
  `subject_name` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`subject_id`)
) ENGINE=InnoDB AUTO_INCREMENT=10 DEFAULT CHARSET=latin1;

/*Data for the table `cst_class_subjects` */

insert  into `cst_class_subjects`(`subject_id`,`subject_name`) values (1,'Maths'),(2,'English'),(3,'Science'),(4,'Sst'),(5,'Hindi'),(6,'Physical'),(7,'Physics'),(8,'Chemistry'),(9,'Biology');
