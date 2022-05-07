DROP SCHEMA IF EXISTS `springdemo`;

CREATE SCHEMA `springdemo`;

use `springdemo`;

SET FOREIGN_KEY_CHECKS = 0;

DROP TABLE IF EXISTS `department`;

CREATE TABLE `department` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `name` varchar(128) DEFAULT NULL,
  
  PRIMARY KEY (`id`),
  
  UNIQUE KEY `NAME_UNIQUE` (`name`)
  
) ENGINE=InnoDB AUTO_INCREMENT=100 DEFAULT CHARSET=latin1;


DROP TABLE IF EXISTS `employee`;

CREATE TABLE `employee` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `first_name` varchar(128) DEFAULT NULL,
  `last_name` varchar(128) DEFAULT NULL,
  `email` varchar(128) DEFAULT NULL,
  `dept_id` int(11) DEFAULT NULL,

  PRIMARY KEY (`id`),

  KEY `FK_DEPT_ID_idx` (`dept_id`),

  CONSTRAINT `FK_DEPT` 
  FOREIGN KEY (`dept_id`) 
  REFERENCES `department` (`id`) 
  
  ON DELETE NO ACTION ON UPDATE NO ACTION
  
) ENGINE=InnoDB AUTO_INCREMENT=1 DEFAULT CHARSET=latin1;

DROP TABLE IF EXISTS `users`;

CREATE TABLE `users` (
  `username` varchar(50) NOT NULL,
  `password` char(68) NOT NULL,
  `enabled` tinyint(1) NOT NULL,
  PRIMARY KEY (`username`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

INSERT INTO `users` 
VALUES 
('mohan','{bcrypt}$2a$12$3lyD6KxtXqSgcEWHet95aeLjv0LaZ8SN96auKm8uN.dNhg/C3s7Ke',1),
('arjun','{bcrypt}$2a$12$qA0OsJcyVrRIWe6RkyJg7uyGQ9t9Hjo8qQiOZKVzn7CzK9WALL92q',1);

#mohan - mohan123
#arjun - arjun123

SET FOREIGN_KEY_CHECKS = 1;