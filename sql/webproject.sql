/*
Navicat MySQL Data Transfer

Source Server         : localhost_3306
Source Server Version : 50716
Source Host           : 127.0.0.1:3306
Source Database       : webproject

Target Server Type    : MYSQL
Target Server Version : 50716
File Encoding         : 65001

Date: 2017-09-09 11:37:26
*/

SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for bill
-- ----------------------------
DROP TABLE IF EXISTS `bill`;
CREATE TABLE `bill` (
  `BILL_ID` int(11) NOT NULL,
  `CREATETIME` datetime DEFAULT NULL,
  `CUSTOM_ID` int(11) DEFAULT NULL,
  `TYPE` varchar(20) DEFAULT NULL,
  `HASPAID_AMOUNT` double DEFAULT NULL,
  `TOTAL_AMOUNT` double DEFAULT NULL,
  `STATUS` varchar(10) DEFAULT NULL,
  PRIMARY KEY (`BILL_ID`),
  KEY `BILL2CUSTOM` (`CUSTOM_ID`),
  CONSTRAINT `BILL2CUSTOM` FOREIGN KEY (`CUSTOM_ID`) REFERENCES `custom` (`CUSTOM_ID`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of bill
-- ----------------------------

-- ----------------------------
-- Table structure for charge
-- ----------------------------
DROP TABLE IF EXISTS `charge`;
CREATE TABLE `charge` (
  `CHARGE_ID` int(11) NOT NULL,
  `CUSTOM_ID` int(11) DEFAULT NULL,
  `CHARGE_AMOUNT` double DEFAULT NULL,
  `BILL_ID` int(11) DEFAULT NULL,
  PRIMARY KEY (`CHARGE_ID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of charge
-- ----------------------------

-- ----------------------------
-- Table structure for custom
-- ----------------------------
DROP TABLE IF EXISTS `custom`;
CREATE TABLE `custom` (
  `CUSTOM_ID` int(11) NOT NULL,
  `TEL` varchar(20) NOT NULL,
  `NAME` varchar(20) DEFAULT NULL,
  `POSITION` varchar(30) DEFAULT NULL,
  `BALANCE` double DEFAULT NULL,
  PRIMARY KEY (`CUSTOM_ID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of custom
-- ----------------------------

-- ----------------------------
-- Table structure for er_menu
-- ----------------------------
DROP TABLE IF EXISTS `er_menu`;
CREATE TABLE `er_menu` (
  `MENUID` int(11) NOT NULL AUTO_INCREMENT,
  `REQUEST` varchar(100) DEFAULT NULL,
  `ISLEAF` int(2) DEFAULT NULL,
  `MENUNAME` varchar(20) DEFAULT NULL,
  `PARENT` int(20) DEFAULT NULL,
  `LEVEL` int(11) NOT NULL,
  PRIMARY KEY (`MENUID`),
  KEY `fkey_parent` (`PARENT`),
  CONSTRAINT `fkey_parent` FOREIGN KEY (`PARENT`) REFERENCES `er_menu` (`MENUID`) ON UPDATE CASCADE
) ENGINE=InnoDB AUTO_INCREMENT=100015 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of er_menu
-- ----------------------------
INSERT INTO `er_menu` VALUES ('100000', null, '0', 'oa管理', null, '0');
INSERT INTO `er_menu` VALUES ('100001', null, '0', '系统模块', '100000', '1');
INSERT INTO `er_menu` VALUES ('100002', '/system', '1', '用户管理', '100001', '2');
INSERT INTO `er_menu` VALUES ('100006', '/menu', '1', '菜单管理', '100001', '2');
INSERT INTO `er_menu` VALUES ('100011', '/report', '1', '缴费统计报表', '100000', '1');
INSERT INTO `er_menu` VALUES ('100014', '/authorize', '1', '权限管理', '100001', '2');

-- ----------------------------
-- Table structure for er_role
-- ----------------------------
DROP TABLE IF EXISTS `er_role`;
CREATE TABLE `er_role` (
  `ROLEID` int(11) NOT NULL AUTO_INCREMENT,
  `ROLENAME` varchar(20) DEFAULT NULL,
  PRIMARY KEY (`ROLEID`)
) ENGINE=InnoDB AUTO_INCREMENT=112 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of er_role
-- ----------------------------
INSERT INTO `er_role` VALUES ('101', 'super');
INSERT INTO `er_role` VALUES ('102', 'admin');
INSERT INTO `er_role` VALUES ('110', '营业员');

-- ----------------------------
-- Table structure for er_role_authorize
-- ----------------------------
DROP TABLE IF EXISTS `er_role_authorize`;
CREATE TABLE `er_role_authorize` (
  `AUTHORIZEID` int(10) unsigned NOT NULL AUTO_INCREMENT,
  `ROLEID` int(11) NOT NULL,
  `MENUID` int(11) NOT NULL,
  PRIMARY KEY (`AUTHORIZEID`),
  KEY `fkey_2` (`MENUID`),
  KEY `fkey_1` (`ROLEID`),
  CONSTRAINT `fkey_1` FOREIGN KEY (`ROLEID`) REFERENCES `er_role` (`ROLEID`) ON DELETE CASCADE ON UPDATE CASCADE,
  CONSTRAINT `fkey_2` FOREIGN KEY (`MENUID`) REFERENCES `er_menu` (`MENUID`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB AUTO_INCREMENT=100056 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of er_role_authorize
-- ----------------------------
INSERT INTO `er_role_authorize` VALUES ('100024', '101', '100000');
INSERT INTO `er_role_authorize` VALUES ('100025', '101', '100001');
INSERT INTO `er_role_authorize` VALUES ('100026', '101', '100002');
INSERT INTO `er_role_authorize` VALUES ('100027', '101', '100006');
INSERT INTO `er_role_authorize` VALUES ('100030', '101', '100000');
INSERT INTO `er_role_authorize` VALUES ('100037', '101', '100011');
INSERT INTO `er_role_authorize` VALUES ('100038', '110', '100000');
INSERT INTO `er_role_authorize` VALUES ('100039', '110', '100011');
INSERT INTO `er_role_authorize` VALUES ('100040', '102', '100000');
INSERT INTO `er_role_authorize` VALUES ('100041', '102', '100001');
INSERT INTO `er_role_authorize` VALUES ('100042', '102', '100006');
INSERT INTO `er_role_authorize` VALUES ('100043', '102', '100002');
INSERT INTO `er_role_authorize` VALUES ('100047', '110', '100001');
INSERT INTO `er_role_authorize` VALUES ('100048', '110', '100006');
INSERT INTO `er_role_authorize` VALUES ('100049', '110', '100002');
INSERT INTO `er_role_authorize` VALUES ('100055', '101', '100014');

-- ----------------------------
-- Table structure for er_user
-- ----------------------------
DROP TABLE IF EXISTS `er_user`;
CREATE TABLE `er_user` (
  `NAME` varchar(20) DEFAULT NULL,
  `ACCOUNT` varchar(20) NOT NULL,
  `PASSWORD` varchar(20) NOT NULL,
  `ID` int(11) NOT NULL AUTO_INCREMENT,
  `ROLEID` int(11) DEFAULT '104',
  PRIMARY KEY (`ID`,`ACCOUNT`),
  KEY `ROLEID` (`ROLEID`),
  KEY `ID` (`ID`),
  CONSTRAINT `fkey_role` FOREIGN KEY (`ROLEID`) REFERENCES `er_role` (`ROLEID`) ON DELETE SET NULL ON UPDATE CASCADE
) ENGINE=InnoDB AUTO_INCREMENT=10000018 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of er_user
-- ----------------------------
INSERT INTO `er_user` VALUES ('super', 'super', '111111', '10000001', '101');
INSERT INTO `er_user` VALUES ('黄ti', 'dsd', 'dsd', '10000002', '102');
INSERT INTO `er_user` VALUES ('', 'dsds', 'dsdaa', '10000003', '110');
INSERT INTO `er_user` VALUES ('', 'admin', '1', '10000016', '101');
INSERT INTO `er_user` VALUES ('ddd', 'woaini', 'woaini', '10000017', '110');

-- ----------------------------
-- Table structure for hall
-- ----------------------------
DROP TABLE IF EXISTS `hall`;
CREATE TABLE `hall` (
  `HALL_ID` int(11) NOT NULL,
  `HALL_NAME` varchar(40) DEFAULT NULL,
  PRIMARY KEY (`HALL_ID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of hall
-- ----------------------------

-- ----------------------------
-- Table structure for payment
-- ----------------------------
DROP TABLE IF EXISTS `payment`;
CREATE TABLE `payment` (
  `PAY_ID` int(10) unsigned NOT NULL AUTO_INCREMENT,
  `CUSTOM_ID` int(11) DEFAULT NULL,
  `TIME` datetime DEFAULT NULL,
  `PAY_METHOD` varchar(20) DEFAULT NULL,
  `QD` varchar(20) DEFAULT NULL,
  `EMPLOYEE_ID` int(11) DEFAULT NULL,
  `HALL_ID` int(11) DEFAULT NULL,
  `AMOUNT` double NOT NULL,
  PRIMARY KEY (`PAY_ID`),
  KEY `PAYMENT2USER` (`EMPLOYEE_ID`),
  KEY `PAYMENT2CUSTOM` (`CUSTOM_ID`),
  CONSTRAINT `PAYMENT2CUSTOM` FOREIGN KEY (`CUSTOM_ID`) REFERENCES `custom` (`CUSTOM_ID`) ON DELETE CASCADE ON UPDATE CASCADE,
  CONSTRAINT `PAYMENT2USER` FOREIGN KEY (`EMPLOYEE_ID`) REFERENCES `er_user` (`ID`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of payment
-- ----------------------------
