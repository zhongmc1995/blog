/*
Navicat MySQL Data Transfer

Source Server         : 本地
Source Server Version : 50550
Source Host           : localhost:3306
Source Database       : blog

Target Server Type    : MYSQL
Target Server Version : 50550
File Encoding         : 65001

Date: 2017-04-10 20:52:25
*/

SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for tbl_themes
-- ----------------------------
DROP TABLE IF EXISTS `tbl_themes`;
CREATE TABLE `tbl_themes` (
  `id` int(10) NOT NULL,
  `themename` varchar(100) DEFAULT NULL,
  `themesdesc` varchar(500) DEFAULT NULL,
  `status` char(1) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of tbl_themes
-- ----------------------------
INSERT INTO `tbl_themes` VALUES ('1', 'default', '默认主题', '1');
INSERT INTO `tbl_themes` VALUES ('2', 'simple', '简洁主题', '0');
