CREATE DATABASE IF NOT EXISTS spring_cloud_and_docker2 default charset utf8 COLLATE utf8_general_ci

USE spring_cloud_and_docker2;

SET FOREIGN_KEY_CHECKS=0;

DROP TABLE IF EXISTS `user`;
CREATE TABLE `user` (
  `id` int(11) NOT NULL,
  `username` varchar(40) DEFAULT NULL,
  `name` varchar(20) DEFAULT NULL,
  `age` int(3) DEFAULT NULL,
  `balance` decimal(10,2) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

INSERT INTO `user` VALUES ('1', 'account1', '张三', '20', '100.00');
INSERT INTO `user` VALUES ('2', 'account2', '李四', '28', '180.00');
INSERT INTO `user` VALUES ('3', 'account3', '王五', '32', '280.00');




