/*
Navicat MySQL Data Transfer

Source Server         : localhost
Source Server Version : 50617
Source Host           : localhost:3306
Source Database       : biye2

Target Server Type    : MYSQL
Target Server Version : 50617
File Encoding         : 65001

Date: 2020-05-25 15:25:09
*/

SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for books
-- ----------------------------
DROP TABLE IF EXISTS `books`;
CREATE TABLE `books` (
  `bid` int(11) NOT NULL AUTO_INCREMENT,
  `booksname` varchar(255) COLLATE utf8_bin NOT NULL,
  `booksurl` varchar(511) COLLATE utf8_bin NOT NULL,
  `bookszuozhe` varchar(255) COLLATE utf8_bin NOT NULL,
  `booksprice` decimal(10,2) NOT NULL,
  `booksnum` int(11) NOT NULL,
  PRIMARY KEY (`bid`)
) ENGINE=InnoDB AUTO_INCREMENT=15 DEFAULT CHARSET=utf8 COLLATE=utf8_bin;

-- ----------------------------
-- Records of books
-- ----------------------------
INSERT INTO `books` VALUES ('5', '鬼谷子', 'https://img.alicdn.com/imgextra/i2/330935854/O1CN01qIoBey1t7B9N2SX1w_!!330935854.jpg_430x430q90.jpg', '同学B ', '6.66', '2');
INSERT INTO `books` VALUES ('6', '零食 （花生）/斤', 'https://img.alicdn.com/imgextra/https://img.alicdn.com/imgextra/i4/3015869921/O1CN017iq9Gx2N9rt4OKG2E_!!3015869921.jpg_430x430q90.jpg', '同学A', '3.10', '26');
INSERT INTO `books` VALUES ('10', '鞋子A品牌', 'https://img.alicdn.com/imgextra/i4/2201218665486/O1CN018ZmnrK1qOdOjmwIlj_!!2201218665486.jpg_430x430q90.jpg', '男生宿舍A', '99.99', '98');
INSERT INTO `books` VALUES ('11', '行李箱 （可小刀）', 'https://img.alicdn.com/imgextra/i4/3257654815/O1CN01vJQ2pK1lRJdwHpSiA_!!3257654815.jpg_430x430q90.jpg', '女生宿舍B', '75.11', '0');
INSERT INTO `books` VALUES ('12', '\r\n考研英语图书A', 'https://img.alicdn.com/imgextra/i3/2207248942089/O1CN01eZ28Yc1RInyOlLQyZ_!!2207248942089.png_430x430q90.jpg', '同学A', '19.99', '19');
INSERT INTO `books` VALUES ('13', '电脑椅子靠背', 'https://gd2.alicdn.com/imgextra/i2/3889833797/O1CN01uSDVbD1dv4OXbrj9a_!!3889833797.jpg_400x400.jpg', '同学A ', '93.66', '2');
INSERT INTO `books` VALUES ('14', '耳机八成新', 'https://img.alicdn.com/imgextra/i1/2206697676327/O1CN01Mz5NiQ1wboU1vxXV1_!!2206697676327.jpg_430x430q90.jpg', '同学C', '10.21', '1');

-- ----------------------------
-- Table structure for lianjie
-- ----------------------------
DROP TABLE IF EXISTS `lianjie`;
CREATE TABLE `lianjie` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `uid` int(11) DEFAULT NULL,
  `bid` int(11) DEFAULT NULL,
  `oid` int(11) DEFAULT NULL,
  `pay` varchar(255) COLLATE utf8_bin DEFAULT '1',
  PRIMARY KEY (`id`),
  KEY `uid` (`uid`),
  KEY `bid` (`bid`),
  KEY `oid` (`oid`),
  CONSTRAINT `bid` FOREIGN KEY (`bid`) REFERENCES `books` (`bid`),
  CONSTRAINT `oid` FOREIGN KEY (`oid`) REFERENCES `orders` (`oid`),
  CONSTRAINT `uid` FOREIGN KEY (`uid`) REFERENCES `user` (`uid`)
) ENGINE=InnoDB AUTO_INCREMENT=148 DEFAULT CHARSET=utf8 COLLATE=utf8_bin;

-- ----------------------------
-- Records of lianjie
-- ----------------------------
INSERT INTO `lianjie` VALUES ('89', '40', '5', '98', '2');
INSERT INTO `lianjie` VALUES ('106', '19', '5', '115', '1');
INSERT INTO `lianjie` VALUES ('142', '19', '5', '151', '1');
INSERT INTO `lianjie` VALUES ('143', '19', '6', '152', '1');
INSERT INTO `lianjie` VALUES ('144', '40', '5', '153', '1');
INSERT INTO `lianjie` VALUES ('145', '19', '6', '154', '1');
INSERT INTO `lianjie` VALUES ('146', '19', '6', '155', '1');
INSERT INTO `lianjie` VALUES ('147', '19', '5', '156', '1');

-- ----------------------------
-- Table structure for orders
-- ----------------------------
DROP TABLE IF EXISTS `orders`;
CREATE TABLE `orders` (
  `oid` int(11) NOT NULL AUTO_INCREMENT,
  `onum` int(11) DEFAULT NULL,
  `pay` int(11) DEFAULT '1',
  PRIMARY KEY (`oid`)
) ENGINE=InnoDB AUTO_INCREMENT=158 DEFAULT CHARSET=utf8 COLLATE=utf8_bin;

-- ----------------------------
-- Records of orders
-- ----------------------------
INSERT INTO `orders` VALUES ('98', '3', '1');
INSERT INTO `orders` VALUES ('115', '1', '1');
INSERT INTO `orders` VALUES ('151', '20', '1');
INSERT INTO `orders` VALUES ('152', '20', '1');
INSERT INTO `orders` VALUES ('153', '20', '1');
INSERT INTO `orders` VALUES ('154', '10', '1');
INSERT INTO `orders` VALUES ('155', '10', '1');
INSERT INTO `orders` VALUES ('156', '1', '2');
INSERT INTO `orders` VALUES ('157', '55', '2');

-- ----------------------------
-- Table structure for user
-- ----------------------------
DROP TABLE IF EXISTS `user`;
CREATE TABLE `user` (
  `uid` int(11) NOT NULL AUTO_INCREMENT,
  `username` varchar(255) COLLATE utf8_bin DEFAULT NULL,
  `password` varchar(255) COLLATE utf8_bin DEFAULT NULL,
  `phone` varchar(255) COLLATE utf8_bin DEFAULT NULL,
  `role` varchar(255) COLLATE utf8_bin DEFAULT NULL,
  PRIMARY KEY (`uid`)
) ENGINE=InnoDB AUTO_INCREMENT=43 DEFAULT CHARSET=utf8 COLLATE=utf8_bin;

-- ----------------------------
-- Records of user
-- ----------------------------
INSERT INTO `user` VALUES ('19', 'admin', '666', '666', 'ADMIN');
INSERT INTO `user` VALUES ('26', 'yyy', 'yyy', 'yyy', 'USER');
INSERT INTO `user` VALUES ('29', 'Q', '$2a$10$3qvs8RxNn0X.Cfp4fziJUOv4YLLjW9Hvj/giWS3mU140mbdL8eFNW', 'Q', 'USER');
INSERT INTO `user` VALUES ('30', 'Z', '$2a$10$pTf9wRdF7/PeuSYqoJ4zseM6oddbWg25ip61Jj4dCqSi4g/N.Hn2G', 'Z', 'USER');
INSERT INTO `user` VALUES ('32', '777', '$2a$10$zwBobOuj90KgbYr4EhYRGe7vS0kJYLRL37RfzATgkiVxjjKbMk0aK', '888', 'USER');
INSERT INTO `user` VALUES ('33', 'S', '$2a$10$OsvYUloDXr5vxZboGhqLyuDIvKvGRdaqsgJb5xDuj9/wDNxHHWOfW', 'S', 'ADMIN');
INSERT INTO `user` VALUES ('35', 'C', '$2a$10$X4J0Kzf5OKmxOKZVzKva5uaCXcZGl127dTJksjkEK.Et.tONPKbCG', 'C', 'USER');
INSERT INTO `user` VALUES ('40', 'K', '$2a$10$nR/4MyYbVauh2mLcNzw.uuApH.5cfqoG7NIbIo/Q5dWorl0RMUqrq', 'K', 'USER');
INSERT INTO `user` VALUES ('41', 'GG', '$2a$10$XM0Wo7.mTzXFs5dmubHGPehIgquIBLIuC/0KBLJvvjmF50Z3LR9AO', 'GG', 'USER');
INSERT INTO `user` VALUES ('42', 'ppp', '$2a$10$IbGz3r5OOJf7V3oCio/z.O3KT2V6HAFKbgBExi5P8saUfHijzxrxq', 'ppp', 'USER');
