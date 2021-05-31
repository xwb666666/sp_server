DROP DATABASE IF EXISTS guns_cloud_auth;
CREATE DATABASE IF NOT EXISTS guns_cloud_auth DEFAULT CHARSET utf8 COLLATE utf8_general_ci;

USE guns_cloud_auth;

/*
 Navicat MySQL Data Transfer

 Source Server         : 106.14.125.51
 Source Server Type    : MySQL
 Source Server Version : 50727
 Source Host           : 106.14.125.51:3306
 Source Schema         : guns_cloud_auth

 Target Server Type    : MySQL
 Target Server Version : 50727
 File Encoding         : 65001

 Date: 11/12/2019 22:27:59
*/

SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
-- Table structure for guns_auth_cert_account
-- ----------------------------
DROP TABLE IF EXISTS `guns_auth_cert_account`;
CREATE TABLE `guns_auth_cert_account`  (
  `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '主键id',
  `account` varchar(100) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL COMMENT '账号(工卡号)',
  `second_check` int(11) DEFAULT NULL COMMENT '二次认证标识（0：禁用；1：开启）',
  `create_time` datetime(0) DEFAULT NULL COMMENT '创建时间',
  `update_time` datetime(0) DEFAULT NULL COMMENT '修改时间',
  `create_user` bigint(20) DEFAULT NULL COMMENT '创建人',
  `update_user` bigint(20) DEFAULT NULL COMMENT '修改人',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 1 CHARACTER SET = utf8 COLLATE = utf8_general_ci COMMENT = '账号二次认证表' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Table structure for guns_auth_client
-- ----------------------------
DROP TABLE IF EXISTS `guns_auth_client`;
CREATE TABLE `guns_auth_client`  (
  `client_id` bigint(20) NOT NULL COMMENT '客户端id',
  `app_id` bigint(20) NOT NULL COMMENT 'appId应用（企业表appId）',
  `name` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '客户端名称',
  `sso_url` varchar(1000) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '客户端接受sso回调的地址',
  `client_type` int(1) NULL DEFAULT NULL COMMENT '客户端类型 1-WEB浏览器，2-APP手机',
  `login_type` int(1) NOT NULL COMMENT '登录类型: 1-应用自定义页面, 2-统一的登录页面',
  `private_key` varchar(60) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '颁发和解析token用的私钥',
  `sign_private_key` varchar(60) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '签名私钥',
  `data_private_key` varchar(60) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '数据私钥',
  `token_exp` int(11) NULL DEFAULT NULL COMMENT 'token过期时间，单位是秒',
  `refresh_token_exp` int(11) NULL DEFAULT NULL COMMENT '刷新token过期时间，单位是秒',
  `login_out_url` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '退出登录的url',
  `sort` int(11) NULL DEFAULT NULL COMMENT '排序',
  `create_time` datetime(0) NULL DEFAULT NULL COMMENT '创建时间',
  `create_user` bigint(20) NULL DEFAULT NULL COMMENT '创建人',
  `update_time` datetime(0) NULL DEFAULT NULL COMMENT '更新时间',
  `update_user` bigint(20) NULL DEFAULT NULL COMMENT '更新人',
  `del_flag` char(1) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL DEFAULT 'N' COMMENT '是否删除：Y-被删除，N-未删除',
  PRIMARY KEY (`client_id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_general_ci COMMENT = '应用表' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of guns_auth_client
-- ----------------------------
INSERT INTO `guns_auth_client` VALUES (1, 1174270350789906434, '系统管理-正式', 'https://micro.stylefeng.cn/#/home', 1, 1, 'HMk60Cww2Z4toC8kS2HDa9j1ObJgGV8a', '123456', '123456', 7200, 7200, '', NULL, '2019-09-16 18:01:00', NULL, '2020-02-08 14:09:03', -100, 'N');
INSERT INTO `guns_auth_client` VALUES (3, 1202565232069730305, '认证中心-正式', 'http://ac.stylefeng.cn/#/home', 1, 1, 'HMk60Cww2Z4toC8kS2HDa9j1ObJgGV8a', '123456', '123456', 7200, 7200, '', NULL, NULL, NULL, '2020-02-08 14:09:09', -100, 'N');
INSERT INTO `guns_auth_client` VALUES (4, 1208723761432387585, '工作流-正式', 'http://workflow.stylefeng.cn/#/home', 1, 1, 'HMk60Cww2Z4toC8kS2HDa9j1ObJgGV8a', '123456', '123456', 7200, 7200, '', NULL, NULL, NULL, '2020-02-08 14:09:22', -100, 'N');
INSERT INTO `guns_auth_client` VALUES (10001, 1174270350789906434, '系统管理-本机联调', 'http://localhost:9501/#/home', 1, 1, 'HMk60Cww2Z4toC8kS2HDa9j1ObJgGV8a', '123456', '123456', 7200, 7200, '', NULL, NULL, NULL, '2020-02-08 14:09:14', -100, 'N');
INSERT INTO `guns_auth_client` VALUES (10002, 1202565232069730305, '认证中心-本机联调', 'http://localhost:9502/#/home', 1, 1, 'HMk60Cww2Z4toC8kS2HDa9j1ObJgGV8a', '123456', '123456', 7200, 7200, '', NULL, NULL, NULL, '2020-02-08 14:09:17', -100, 'N');
INSERT INTO `guns_auth_client` VALUES (10003, 1208723761432387585, '工作流-本机联调', 'http://localhost:9503/#/home', 1, 1, 'HMk60Cww2Z4toC8kS2HDa9j1ObJgGV8a', '123456', '123456', 7200, 7200, '', NULL, NULL, NULL, '2020-02-08 14:09:22', -100, 'N');

-- ----------------------------
-- Table structure for guns_auth_login_log
-- ----------------------------
DROP TABLE IF EXISTS `guns_auth_login_log`;
CREATE TABLE `guns_auth_login_log`  (
  `login_log_id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '主键',
  `account` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL COMMENT '账号',
  `name` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL COMMENT '姓名',
  `company_id` bigint(20) DEFAULT NULL COMMENT '公司id',
  `client_id` bigint(20) NOT NULL COMMENT '应用客户端id',
  `operation` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL COMMENT '操作内容',
  `ip_address` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL COMMENT '登录ip',
  `local_address` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL COMMENT '登录地点',
  `login_time` datetime(0) DEFAULT NULL COMMENT '登陆时间',
  PRIMARY KEY (`login_log_id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 3100 CHARACTER SET = utf8 COLLATE = utf8_general_ci COMMENT = '登录日志表' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of guns_auth_login_log
-- ----------------------------


SET FOREIGN_KEY_CHECKS = 1;
