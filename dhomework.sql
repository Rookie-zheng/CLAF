/*
Navicat MySQL Data Transfer

Source Server         : localhost_3306
Source Server Version : 50728
Source Host           : localhost:3306
Source Database       : dhomework

Target Server Type    : MYSQL
Target Server Version : 50728
File Encoding         : 65001

Date: 2019-12-29 02:38:18
*/

SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for t_input
-- ----------------------------
DROP TABLE IF EXISTS `t_input`;
CREATE TABLE `t_input` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `create_time` datetime DEFAULT NULL,
  `price` varchar(255) DEFAULT NULL,
  `remark` varchar(255) DEFAULT NULL,
  `update_time` datetime DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=29 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of t_input
-- ----------------------------
INSERT INTO `t_input` VALUES ('24', '2019-12-29 00:03:28', '0', '初始化', '2019-12-29 00:03:48');
INSERT INTO `t_input` VALUES ('27', '2019-12-29 00:31:17', '432432.0', '工资', null);
INSERT INTO `t_input` VALUES ('28', '2020-12-29 01:44:23', '110000.0', '股票生者', null);

-- ----------------------------
-- Table structure for t_output
-- ----------------------------
DROP TABLE IF EXISTS `t_output`;
CREATE TABLE `t_output` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `create_time` datetime(6) DEFAULT NULL,
  `price` float NOT NULL,
  `remark` varchar(255) DEFAULT NULL,
  `update_time` datetime(6) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=12 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of t_output
-- ----------------------------
INSERT INTO `t_output` VALUES ('2', '2019-12-28 17:54:24.000000', '0', '初始化', '2019-12-28 17:54:34.000000');
INSERT INTO `t_output` VALUES ('11', '2019-12-29 00:31:26.000000', '11', '吃饭', null);

-- ----------------------------
-- Table structure for t_record
-- ----------------------------
DROP TABLE IF EXISTS `t_record`;
CREATE TABLE `t_record` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `create_time` datetime(6) DEFAULT NULL,
  `sum` float NOT NULL,
  `update_time` datetime(6) DEFAULT NULL,
  `input_sum` float NOT NULL,
  `input_time` datetime(6) DEFAULT NULL,
  `output_sum` float NOT NULL,
  `output_time` datetime(6) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=16 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of t_record
-- ----------------------------
INSERT INTO `t_record` VALUES ('13', '2020-01-01 00:31:23.000000', '432432', null, '432432', '2019-12-29 00:31:17.000000', '0', null);
INSERT INTO `t_record` VALUES ('14', '2019-12-29 00:31:33.334000', '432421', null, '0', null, '11', '2019-12-29 00:31:26.000000');
INSERT INTO `t_record` VALUES ('15', '2019-12-29 01:44:48.414000', '542421', null, '110000', '2020-12-29 01:44:23.000000', '0', null);

-- ----------------------------
-- Table structure for t_user
-- ----------------------------
DROP TABLE IF EXISTS `t_user`;
CREATE TABLE `t_user` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `create_time` datetime(6) DEFAULT NULL,
  `email` varchar(255) DEFAULT NULL,
  `nickname` varchar(255) DEFAULT NULL,
  `password` varchar(255) DEFAULT NULL,
  `update_time` datetime(6) DEFAULT NULL,
  `username` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of t_user
-- ----------------------------
INSERT INTO `t_user` VALUES ('1', '2019-12-27 16:06:20.000000', null, 'zheng', '12345678', '2019-12-27 16:06:35.000000', 'zheng');
