/*
 Navicat Premium Data Transfer

 Source Server         : localhost
 Source Server Type    : MySQL
 Source Server Version : 80028
 Source Host           : localhost:3306
 Source Schema         : zhxy

 Target Server Type    : MySQL
 Target Server Version : 80028
 File Encoding         : 65001

 Date: 25/03/2023 16:00:37
*/

SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
-- Table structure for tb_admin
-- ----------------------------
DROP TABLE IF EXISTS `tb_admin`;
CREATE TABLE `tb_admin` (
  `id` int NOT NULL AUTO_INCREMENT,
  `name` varchar(15) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL,
  `gender` char(1) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL,
  `password` varchar(100) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL,
  `email` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL,
  `telephone` varchar(12) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL,
  `address` varchar(100) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL,
  `portrait_path` varchar(200) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL,
  PRIMARY KEY (`id`) USING BTREE
) ENGINE=InnoDB AUTO_INCREMENT=158 DEFAULT CHARSET=utf8mb3 ROW_FORMAT=DYNAMIC;

-- ----------------------------
-- Records of tb_admin
-- ----------------------------
BEGIN;
INSERT INTO `tb_admin` (`id`, `name`, `gender`, `password`, `email`, `telephone`, `address`, `portrait_path`) VALUES (157, 'admin', '男', '96e79218965eb72c92a549dd5a330112', 'admin@126.com', '13613866678', '中国', 'upload/2fb0b1d29410444b919b28767f403a84IMG_9312.JPG');
COMMIT;

-- ----------------------------
-- Table structure for tb_clazz
-- ----------------------------
DROP TABLE IF EXISTS `tb_clazz`;
CREATE TABLE `tb_clazz` (
  `id` int NOT NULL AUTO_INCREMENT,
  `name` varchar(15) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL,
  `number` int DEFAULT NULL,
  `introducation` varchar(200) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL,
  `headmaster` varchar(15) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL,
  `email` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL,
  `telephone` varchar(12) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL,
  `grade_name` varchar(15) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL,
  PRIMARY KEY (`id`) USING BTREE
) ENGINE=InnoDB AUTO_INCREMENT=16 DEFAULT CHARSET=utf8mb3 ROW_FORMAT=DYNAMIC;

-- ----------------------------
-- Records of tb_clazz
-- ----------------------------
BEGIN;
INSERT INTO `tb_clazz` (`id`, `name`, `number`, `introducation`, `headmaster`, `email`, `telephone`, `grade_name`) VALUES (10, '21硕1班', 52, '6系2021级研究生班级', '王炳哲', 'wangbingzhe@qq.com', '18888888888', '研究生二年级');
INSERT INTO `tb_clazz` (`id`, `name`, `number`, `introducation`, `headmaster`, `email`, `telephone`, `grade_name`) VALUES (11, '21硕3班', 43, '6系2021级研究生班级', '王炳哲', 'wangbingzhe@qq.com', '18888888888', '研究生二年级');
INSERT INTO `tb_clazz` (`id`, `name`, `number`, `introducation`, `headmaster`, `email`, `telephone`, `grade_name`) VALUES (12, '21硕2班', 48, '6系2021级研究生班级', '王炳哲', 'wangbingzhe@qq.com', '18888888888', '研究生二年级');
INSERT INTO `tb_clazz` (`id`, `name`, `number`, `introducation`, `headmaster`, `email`, `telephone`, `grade_name`) VALUES (13, '20硕1班', 42, '6系2020级研究生班级', '王蕾', 'wanglei@qq.com', '16666666666', '研究生二年级');
INSERT INTO `tb_clazz` (`id`, `name`, `number`, `introducation`, `headmaster`, `email`, `telephone`, `grade_name`) VALUES (14, '20硕2班', 51, '6系2020级研究生班级', '王蕾', 'wanglei@qq.com', '16666666666', '研究生二年级');
INSERT INTO `tb_clazz` (`id`, `name`, `number`, `introducation`, `headmaster`, `email`, `telephone`, `grade_name`) VALUES (15, '20硕3班', 47, '6系2020级研究生班级', '王蕾', 'wanglei@qq.com', '16666666666', '研究生二年级');
COMMIT;

-- ----------------------------
-- Table structure for tb_grade
-- ----------------------------
DROP TABLE IF EXISTS `tb_grade`;
CREATE TABLE `tb_grade` (
  `id` int NOT NULL AUTO_INCREMENT,
  `name` varchar(15) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL DEFAULT '',
  `manager` varchar(15) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL,
  `email` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL,
  `telephone` varchar(12) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL,
  `introducation` varchar(200) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL,
  PRIMARY KEY (`id`,`name`) USING BTREE
) ENGINE=InnoDB AUTO_INCREMENT=14 DEFAULT CHARSET=utf8mb3 ROW_FORMAT=DYNAMIC;

-- ----------------------------
-- Records of tb_grade
-- ----------------------------
BEGIN;
INSERT INTO `tb_grade` (`id`, `name`, `manager`, `email`, `telephone`, `introducation`) VALUES (12, '研究生一年级', '王蕾', 'wanglei@qq.com', '16666666666', '6系2021届研究生');
INSERT INTO `tb_grade` (`id`, `name`, `manager`, `email`, `telephone`, `introducation`) VALUES (13, '研究生二年级', '王炳哲', 'wangbingzhe@qq.com', '18888888888', '6系2021届研究生');
COMMIT;

-- ----------------------------
-- Table structure for tb_student
-- ----------------------------
DROP TABLE IF EXISTS `tb_student`;
CREATE TABLE `tb_student` (
  `id` int NOT NULL AUTO_INCREMENT,
  `sno` varchar(20) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL,
  `name` varchar(15) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL,
  `gender` char(1) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL,
  `password` varchar(100) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL,
  `email` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL,
  `telephone` varchar(12) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL,
  `address` varchar(100) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL,
  `introducation` varchar(200) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL,
  `portrait_path` varchar(200) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL,
  `clazz_name` varchar(15) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL,
  PRIMARY KEY (`id`) USING BTREE
) ENGINE=InnoDB AUTO_INCREMENT=11 DEFAULT CHARSET=utf8mb3 ROW_FORMAT=DYNAMIC;

-- ----------------------------
-- Records of tb_student
-- ----------------------------
BEGIN;
INSERT INTO `tb_student` (`id`, `sno`, `name`, `gender`, `password`, `email`, `telephone`, `address`, `introducation`, `portrait_path`, `clazz_name`) VALUES (10, 'S321067057', '小新', '男', 'e10adc3949ba59abbe56e057f20f883e', '1096463510@qq.com', '18866688866', '哈尔滨市', '无敌可爱', 'upload/1a73d0317f2a4dabbe0ee7c1c54d597bIMG_9248.JPG', '21硕3班');
COMMIT;

-- ----------------------------
-- Table structure for tb_teacher
-- ----------------------------
DROP TABLE IF EXISTS `tb_teacher`;
CREATE TABLE `tb_teacher` (
  `id` int NOT NULL AUTO_INCREMENT,
  `tno` varchar(20) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL,
  `name` varchar(15) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL,
  `gender` char(1) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL,
  `password` varchar(100) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL,
  `email` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL,
  `telephone` varchar(12) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL,
  `address` varchar(100) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL,
  `portrait_path` varchar(200) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL,
  `clazz_name` varchar(15) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL,
  PRIMARY KEY (`id`) USING BTREE
) ENGINE=InnoDB AUTO_INCREMENT=10 DEFAULT CHARSET=utf8mb3 ROW_FORMAT=DYNAMIC;

-- ----------------------------
-- Records of tb_teacher
-- ----------------------------
BEGIN;
INSERT INTO `tb_teacher` (`id`, `tno`, `name`, `gender`, `password`, `email`, `telephone`, `address`, `portrait_path`, `clazz_name`) VALUES (9, 'teacher001', '王炳哲', '男', 'e10adc3949ba59abbe56e057f20f883e', 'wangbingzhe@qq.com', '18888888888', '哈尔滨市', 'upload/abdc5bf980934e5d870b084e1feea12dIMG_9901.JPG', '21硕3班');
COMMIT;

SET FOREIGN_KEY_CHECKS = 1;
