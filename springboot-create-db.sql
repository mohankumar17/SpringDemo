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

INSERT INTO `department`
VALUES
(102,'HR'),
(101,'Ops'),
(100,'Tech');

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

INSERT INTO `employee`
VALUES
(1,'Paul','Brandon','paul.brandon@gmail.com',102),
(2,'Tine','Nailor','tina.nailor@gmail.com',102),
(3,'Ketan','Shah','ketan.shah@gmail.com',101),
(4,'Rahul','Raj','rahul.raj@gmail.com',101),
(5,'Gunjal','Kumar','gunjal.kumar@gmail.com',100),
(6,'Sasi','Venna','sasi.venna@gmail.com',100),
(7,'John','Feddrick','john.feddrick@gmail.com',100);

DROP TABLE IF EXISTS `users`;

CREATE TABLE `users` (
  `username` varchar(50) NOT NULL,
  `password` char(68) NOT NULL,
  `enabled` tinyint(1) NOT NULL,
  PRIMARY KEY (`username`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

INSERT INTO `users` 
VALUES 
('mohan','$2a$12$3lyD6KxtXqSgcEWHet95aeLjv0LaZ8SN96auKm8uN.dNhg/C3s7Ke',1),
('arjun','$2a$12$qA0OsJcyVrRIWe6RkyJg7uyGQ9t9Hjo8qQiOZKVzn7CzK9WALL92q',1);

#mohan - mohan123
#arjun - arjun123

DROP TABLE IF EXISTS `authorities`;
CREATE TABLE `authorities` (
  `username` varchar(50) NOT NULL,
  `authority` varchar(50) NOT NULL,
  UNIQUE KEY `authorities_idx_1` (`username`,`authority`),
  CONSTRAINT `authorities_ibfk_1` FOREIGN KEY (`username`) REFERENCES `users` (`username`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;


INSERT INTO `authorities` 
VALUES 
('mohan','ROLE_EMPLOYEE'),
('mohan','ROLE_ADMIN'),
('arjun','ROLE_EMPLOYEE');

SET FOREIGN_KEY_CHECKS = 1;
