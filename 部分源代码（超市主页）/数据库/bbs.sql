/*
Navicat MySQL Data Transfer

Source Server         : localhost
Source Server Version : 50617
Source Host           : localhost:3306
Source Database       : bbs

Target Server Type    : MYSQL
Target Server Version : 50617
File Encoding         : 65001

Date: 2020-05-25 15:33:15
*/

SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for authority
-- ----------------------------
DROP TABLE IF EXISTS `authority`;
CREATE TABLE `authority` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `name` varchar(255) COLLATE utf8_bin NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8 COLLATE=utf8_bin;

-- ----------------------------
-- Records of authority
-- ----------------------------
INSERT INTO `authority` VALUES ('1', 'ROLE_ADMIN');
INSERT INTO `authority` VALUES ('2', 'ROLE_USER');

-- ----------------------------
-- Table structure for order_
-- ----------------------------
DROP TABLE IF EXISTS `order_`;
CREATE TABLE `order_` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `onum` int(11) NOT NULL,
  `pay` int(255) DEFAULT '1',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=40 DEFAULT CHARSET=utf8 COLLATE=utf8_bin;

-- ----------------------------
-- Records of order_
-- ----------------------------
INSERT INTO `order_` VALUES ('3', '3', '2');
INSERT INTO `order_` VALUES ('5', '4', '1');
INSERT INTO `order_` VALUES ('6', '6', '1');
INSERT INTO `order_` VALUES ('7', '10', '1');
INSERT INTO `order_` VALUES ('8', '1', '1');
INSERT INTO `order_` VALUES ('9', '3', '2');
INSERT INTO `order_` VALUES ('10', '1', '1');
INSERT INTO `order_` VALUES ('11', '2', '1');
INSERT INTO `order_` VALUES ('12', '2', '1');
INSERT INTO `order_` VALUES ('13', '2', '1');
INSERT INTO `order_` VALUES ('14', '2', '1');
INSERT INTO `order_` VALUES ('15', '2', '1');
INSERT INTO `order_` VALUES ('16', '2', '2');
INSERT INTO `order_` VALUES ('17', '3', '1');
INSERT INTO `order_` VALUES ('18', '3', '1');
INSERT INTO `order_` VALUES ('19', '3', '1');
INSERT INTO `order_` VALUES ('20', '3', '2');
INSERT INTO `order_` VALUES ('21', '3', '1');
INSERT INTO `order_` VALUES ('22', '3', '1');
INSERT INTO `order_` VALUES ('23', '3', '1');
INSERT INTO `order_` VALUES ('24', '3', '1');
INSERT INTO `order_` VALUES ('25', '9', '1');
INSERT INTO `order_` VALUES ('26', '10', '2');
INSERT INTO `order_` VALUES ('27', '3', '2');
INSERT INTO `order_` VALUES ('28', '3', '2');
INSERT INTO `order_` VALUES ('29', '5', '2');
INSERT INTO `order_` VALUES ('31', '7', '1');
INSERT INTO `order_` VALUES ('32', '1', '2');
INSERT INTO `order_` VALUES ('33', '3', '2');
INSERT INTO `order_` VALUES ('34', '10', '2');
INSERT INTO `order_` VALUES ('35', '1', '2');
INSERT INTO `order_` VALUES ('36', '1', '2');
INSERT INTO `order_` VALUES ('37', '1', '2');
INSERT INTO `order_` VALUES ('38', '1', '2');
INSERT INTO `order_` VALUES ('39', '1', '2');

-- ----------------------------
-- Table structure for oup
-- ----------------------------
DROP TABLE IF EXISTS `oup`;
CREATE TABLE `oup` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `uid` int(11) NOT NULL,
  `pid` int(11) NOT NULL,
  `oid` int(11) NOT NULL,
  PRIMARY KEY (`id`),
  KEY `O-U_idx` (`uid`),
  KEY `O-P_idx` (`pid`),
  KEY `O-O_idx` (`oid`),
  CONSTRAINT `O-O` FOREIGN KEY (`oid`) REFERENCES `order_` (`id`),
  CONSTRAINT `O-P` FOREIGN KEY (`pid`) REFERENCES `product` (`id`),
  CONSTRAINT `O-U` FOREIGN KEY (`uid`) REFERENCES `user` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=44 DEFAULT CHARSET=utf8 COLLATE=utf8_bin;

-- ----------------------------
-- Records of oup
-- ----------------------------
INSERT INTO `oup` VALUES ('3', '10', '2', '3');
INSERT INTO `oup` VALUES ('5', '10', '1', '5');
INSERT INTO `oup` VALUES ('6', '11', '2', '6');
INSERT INTO `oup` VALUES ('8', '10', '3', '7');
INSERT INTO `oup` VALUES ('12', '10', '3', '11');
INSERT INTO `oup` VALUES ('13', '10', '3', '12');
INSERT INTO `oup` VALUES ('14', '10', '3', '13');
INSERT INTO `oup` VALUES ('15', '10', '3', '14');
INSERT INTO `oup` VALUES ('16', '10', '3', '15');
INSERT INTO `oup` VALUES ('17', '10', '3', '10');
INSERT INTO `oup` VALUES ('18', '10', '3', '16');
INSERT INTO `oup` VALUES ('19', '10', '3', '8');
INSERT INTO `oup` VALUES ('20', '10', '3', '9');
INSERT INTO `oup` VALUES ('21', '24', '1', '17');
INSERT INTO `oup` VALUES ('22', '24', '1', '18');
INSERT INTO `oup` VALUES ('23', '24', '1', '19');
INSERT INTO `oup` VALUES ('24', '24', '1', '20');
INSERT INTO `oup` VALUES ('25', '24', '1', '21');
INSERT INTO `oup` VALUES ('26', '44', '1', '22');
INSERT INTO `oup` VALUES ('27', '24', '1', '23');
INSERT INTO `oup` VALUES ('28', '24', '1', '24');
INSERT INTO `oup` VALUES ('29', '24', '1', '25');
INSERT INTO `oup` VALUES ('30', '24', '1', '26');
INSERT INTO `oup` VALUES ('31', '24', '1', '27');
INSERT INTO `oup` VALUES ('32', '24', '1', '28');
INSERT INTO `oup` VALUES ('33', '24', '1', '29');
INSERT INTO `oup` VALUES ('35', '24', '1', '31');
INSERT INTO `oup` VALUES ('36', '24', '3', '32');
INSERT INTO `oup` VALUES ('37', '24', '2', '33');
INSERT INTO `oup` VALUES ('38', '24', '12', '34');
INSERT INTO `oup` VALUES ('39', '24', '3', '35');
INSERT INTO `oup` VALUES ('40', '10', '5', '36');
INSERT INTO `oup` VALUES ('41', '10', '3', '37');
INSERT INTO `oup` VALUES ('42', '10', '2', '38');
INSERT INTO `oup` VALUES ('43', '10', '3', '39');

-- ----------------------------
-- Table structure for product
-- ----------------------------
DROP TABLE IF EXISTS `product`;
CREATE TABLE `product` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `name` varchar(255) COLLATE utf8_bin NOT NULL,
  `num` int(11) NOT NULL,
  `price` double NOT NULL,
  `img` varchar(255) COLLATE utf8_bin NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=18 DEFAULT CHARSET=utf8 COLLATE=utf8_bin;

-- ----------------------------
-- Records of product
-- ----------------------------
INSERT INTO `product` VALUES ('1', '杯子', '48', '21.9', 'https://g-search3.alicdn.com/img/bao/uploaded/i4/i4/2765086155/TB2e8KCvFOWBuNjy0FiXXXFxVXa_!!2765086155-0-item_pic.jpg_230x230.jpg_.webp');
INSERT INTO `product` VALUES ('2', '被子', '12', '49', 'https://g-search1.alicdn.com/img/bao/uploaded/i4/i2/2097385502/O1CN01ofzG1N1qVxgmQkyF3_!!0-item_pic.jpg_230x230.jpg_.webp');
INSERT INTO `product` VALUES ('3', '辣条', '4', '17.9', 'https://g-search1.alicdn.com/img/bao/uploaded/i4/i3/2200070681/O1CN013DCfsf1GtwG61Og8T_!!0-item_pic.jpg_230x230.jpg_.webp');
INSERT INTO `product` VALUES ('5', '维他奶', '79', '54.8', 'https://g-search3.alicdn.com/img/bao/uploaded/i4/i4/3261296144/O1CN01cKCjd91vFzxuFTZZh_!!0-item_pic.jpg_230x230.jpg_.webp');
INSERT INTO `product` VALUES ('6', '维他柠檬茶', '88', '53.5', 'https://g-search3.alicdn.com/img/bao/uploaded/i4/i1/3261296144/O1CN01sx1hAM1vFzxuFP4sR_!!0-item_pic.jpg_230x230.jpg_.webp');
INSERT INTO `product` VALUES ('7', '德芙', '70', '23.6', 'https://g-search1.alicdn.com/img/bao/uploaded/i4/i2/2037968561/O1CN01rlnHPD2D6zFKoYNjH_!!0-item_pic.jpg_230x230.jpg_.webp');
INSERT INTO `product` VALUES ('8', '维生素C片', '68', '19.8', 'https://g-search3.alicdn.com/img/bao/uploaded/i4/i1/692420117/O1CN01kLqo5m1CdDKff7cnN_!!103-0-lubanu.jpg_230x230.jpg_.webp');
INSERT INTO `product` VALUES ('9', '维生素D片', '98', '43.9', 'https://g-search1.alicdn.com/img/bao/uploaded/i4/imgextra/i3/110661269/O1CN011WYSkH1LFF5IbQMuZ_!!0-saturn_solar.jpg_230x230.jpg_.webp');
INSERT INTO `product` VALUES ('10', '牙刷', '799', '169', 'https://g-search3.alicdn.com/img/bao/uploaded/i4/i2/1714128138/O1CN01c67zo229zFikl7IVp_!!0-item_pic.jpg_230x230.jpg_.webp');
INSERT INTO `product` VALUES ('11', '扫把', '666', '24', 'https://g-search1.alicdn.com/img/bao/uploaded/i4/imgextra/i4/49685223/O1CN01z8ecR81oSB9qvI0NT_!!0-saturn_solar.jpg_230x230.jpg_.webp');
INSERT INTO `product` VALUES ('12', '键盘', '1102', '399', 'https://g-search1.alicdn.com/img/bao/uploaded/i4/i2/133668489/O1CN01DQEQC32Ca0jzWMcno_!!0-item_pic.jpg_230x230.jpg_.webp');
INSERT INTO `product` VALUES ('13', '鼠标', '1234', '89', 'https://g-search3.alicdn.com/img/bao/uploaded/i4/i4/2863054326/O1CN010oAH1O1hpLurki04y_!!0-item_pic.jpg_230x230.jpg_.webp');
INSERT INTO `product` VALUES ('15', '蒙牛纯牛奶', '23', '29.9', 'https://g-search2.alicdn.com/img/bao/uploaded/i4/i4/725677994/O1CN01E6pzOP28vIge6t01u_!!0-item_pic.jpg_230x230.jpg_.webp');
INSERT INTO `product` VALUES ('17', '德芙红色款', '877', '91.8', 'https://g-search2.alicdn.com/img/bao/uploaded/i4/i3/877104952/O1CN011NEmWt1mS3r53r5u2_!!0-item_pic.jpg_230x230.jpg_.webp');

-- ----------------------------
-- Table structure for user
-- ----------------------------
DROP TABLE IF EXISTS `user`;
CREATE TABLE `user` (
  `id` int(16) NOT NULL AUTO_INCREMENT,
  `username` varchar(255) COLLATE utf8_bin NOT NULL,
  `password` varchar(255) COLLATE utf8_bin NOT NULL,
  `name` varchar(45) COLLATE utf8_bin DEFAULT NULL,
  `sex` varchar(45) COLLATE utf8_bin DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=50 DEFAULT CHARSET=utf8 COLLATE=utf8_bin;

-- ----------------------------
-- Records of user
-- ----------------------------
INSERT INTO `user` VALUES ('10', 'admin', '$2aQQQ', null, null);
INSERT INTO `user` VALUES ('11', 'EEE', '$2a$10$nbYuTXSZtzxV4mLzgZMeJeI9AvRZ8KiiJ4naxctCgQBZSr99tCpWm', null, null);
INSERT INTO `user` VALUES ('24', 'SDSA', '$2a$10$ug/fDpNPhgpW6qGE3QYZV.zFRczVCFhOZGOXoNgCH7mjLYGTD5HFi', '77777', '男');
INSERT INTO `user` VALUES ('28', '7', '$2a$10$nVUqUnYJj4Y3bciApoalGugU3Ck6LWz4YGxN/fcFmygaJ/mmZWv7u', null, null);
INSERT INTO `user` VALUES ('29', 'WWL', '$2a$10$VlA2nhjZPWeFIiIf9d4blulBzgAFOc8Tpm46R3A/IveI6ZXyh3JdC', null, null);
INSERT INTO `user` VALUES ('30', 'a', '$2a$10$xdBODA2xjOuBMzq3a3wjpumyj.Y4TiYYd/61ri25KH7/NABcuhQ6S', null, null);
INSERT INTO `user` VALUES ('31', 'user', '$2a$10$a9IKTO/A6L4nJHI3A/xH0Oi0PJ2Qk9byXFh7Vc/lGOnupNfDunLwK', null, null);
INSERT INTO `user` VALUES ('32', '1065721365', '$2a$10$ZXxhQrK0QGQ.3qZUW.b8r.phz2cyx2lLrAaM/sFZu9nsBKmFzEtI6', null, null);
INSERT INTO `user` VALUES ('33', '153353', '$2a$10$Re1F.dI2liUlAgt0YiHWaO/tVC5kxopkwxHojWnYV591NICkVDrDW', null, null);
INSERT INTO `user` VALUES ('34', 'aaa', '$2a$10$FDPw9hEXF3wDItjHEdxyoOWq7wQ755J0Allyf2JjMoQnP9tEp9ewW', null, null);
INSERT INTO `user` VALUES ('35', '123', '$2a$10$Hl.8RNUVNunYrF9HrcX1R.R0yMjfXHVUUir8eU1nISr/GRpcCbQLa', null, null);
INSERT INTO `user` VALUES ('36', '11', '$2a$10$oLL8hcdXtMuuEkXQ3BKjQuDZMC9oQWazx0bIvhEjQf8MTb48G8yfC', null, null);
INSERT INTO `user` VALUES ('37', '111', '$2a$10$NC2HKVLBqqXauQdM2M1ro.jmknovHJ2FwhnDpmKVvQ21WZ3aFbF1m', null, null);
INSERT INTO `user` VALUES ('38', '1234', '$2a$10$XHCLih.Q4z0ToE9y/qYZ6.SrCYGTSVTZdn0PWiJK3xRFfmrUvjDfu', null, null);
INSERT INTO `user` VALUES ('39', '11111', '$2a$10$1pF0VwdLEitVfMcHx5P1FuNKzCz8GhAzqOdEDCvxnc5yIy1gHcWkC', null, null);
INSERT INTO `user` VALUES ('40', 'z', '$2a$10$8BZPkENOZ7EzYhDWxafmWObryOr7j3yBatrep5zwN/AlIr8/UR.76', null, null);
INSERT INTO `user` VALUES ('41', '10', '$2a$10$Dw5zZ7Wdv1ClYoG2/zv/tug2H6s.2P3e0OtSQha1AtmW4I93RoT2.', null, null);
INSERT INTO `user` VALUES ('42', 'c', '$2a$10$9WwOsY.EOcI9H.0JHXBdeOALHp3RuarVyGhbhB673UrwIQ26ZcbRS', null, null);
INSERT INTO `user` VALUES ('43', 'h', '$2a$10$kN2Hilb8uj1YtMQlIJDrReQCLUL7sFarb0IPj3gf9J3k9JCG2leEm', null, null);
INSERT INTO `user` VALUES ('44', 'hua', '$2a$10$4WgayrrB88R8dPWxyPIoAeflHq4VX8w4kdpVZvwJs.yeVgyCnZyqi', 'huaqifan', 'male');
INSERT INTO `user` VALUES ('45', 'jj', '$2a$10$6ES9O5ZU2KcS6n94DWAjT.QT.ZnB6Zh/lt9.CocNgFqNfT1rHWhJe', 'jjj', '男');
INSERT INTO `user` VALUES ('48', 'III', '$2a$10$y3Tu1muaRwqrv3FEqn9uzu430aD/Ivupb6OPnW7K2y8vnJI8zwtyq', null, null);
INSERT INTO `user` VALUES ('49', 'TTT', '$2a$10$M2dAZ6hwk8T6GIz9ZF0ls.s3I44k2vFrqX0nl.umB.61Vtu2didii', '', null);

-- ----------------------------
-- Table structure for userauthority
-- ----------------------------
DROP TABLE IF EXISTS `userauthority`;
CREATE TABLE `userauthority` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `userid` int(11) NOT NULL,
  `authorityid` int(11) NOT NULL,
  PRIMARY KEY (`id`),
  KEY `uid_idx` (`userid`),
  KEY `aid_idx` (`authorityid`),
  CONSTRAINT `aid` FOREIGN KEY (`authorityid`) REFERENCES `authority` (`id`),
  CONSTRAINT `uid` FOREIGN KEY (`userid`) REFERENCES `user` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=59 DEFAULT CHARSET=utf8 COLLATE=utf8_bin;

-- ----------------------------
-- Records of userauthority
-- ----------------------------
INSERT INTO `userauthority` VALUES ('19', '24', '1');
INSERT INTO `userauthority` VALUES ('26', '11', '1');
INSERT INTO `userauthority` VALUES ('29', '29', '1');
INSERT INTO `userauthority` VALUES ('30', '30', '1');
INSERT INTO `userauthority` VALUES ('31', '31', '1');
INSERT INTO `userauthority` VALUES ('32', '32', '1');
INSERT INTO `userauthority` VALUES ('33', '33', '1');
INSERT INTO `userauthority` VALUES ('34', '34', '1');
INSERT INTO `userauthority` VALUES ('35', '35', '1');
INSERT INTO `userauthority` VALUES ('36', '36', '1');
INSERT INTO `userauthority` VALUES ('38', '38', '1');
INSERT INTO `userauthority` VALUES ('39', '39', '1');
INSERT INTO `userauthority` VALUES ('40', '40', '1');
INSERT INTO `userauthority` VALUES ('41', '37', '1');
INSERT INTO `userauthority` VALUES ('42', '41', '1');
INSERT INTO `userauthority` VALUES ('43', '42', '1');
INSERT INTO `userauthority` VALUES ('44', '43', '1');
INSERT INTO `userauthority` VALUES ('46', '28', '1');
INSERT INTO `userauthority` VALUES ('49', '44', '1');
INSERT INTO `userauthority` VALUES ('50', '45', '1');
INSERT INTO `userauthority` VALUES ('54', '24', '1');
INSERT INTO `userauthority` VALUES ('55', '24', '1');
INSERT INTO `userauthority` VALUES ('56', '10', '2');
INSERT INTO `userauthority` VALUES ('57', '48', '1');
INSERT INTO `userauthority` VALUES ('58', '49', '2');
