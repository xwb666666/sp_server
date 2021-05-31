DROP DATABASE IF EXISTS hc_cloud_system;
CREATE DATABASE IF NOT EXISTS hc_cloud_system DEFAULT CHARSET utf8 COLLATE utf8_general_ci;

USE hc_cloud_system;

/*
 Navicat Premium Data Transfer

 Source Server         : local-vmware
 Source Server Type    : MySQL
 Source Server Version : 50729
 Source Host           : 192.168.11.128:3306
 Source Schema         : hc_cloud_system

 Target Server Type    : MySQL
 Target Server Version : 50729
 File Encoding         : 65001

 Date: 26/09/2020 21:50:46
*/

SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
-- Table structure for guns_ent_comp_app
-- ----------------------------
DROP TABLE IF EXISTS `guns_ent_comp_app`;
CREATE TABLE `guns_ent_comp_app`  (
  `id` bigint(20) NOT NULL COMMENT 'id',
  `company_id` bigint(20) NULL DEFAULT NULL COMMENT '公司id',
  `app_id` bigint(20) NULL DEFAULT NULL COMMENT '应用ID',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_general_ci COMMENT = '企业应用配置' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of guns_ent_comp_app
-- ----------------------------
INSERT INTO `guns_ent_comp_app` VALUES (1192437489311178753, 1192425871256870913, 1174270350789906434);
INSERT INTO `guns_ent_comp_app` VALUES (1192437503336931330, 1192426576034160642, 1174270350789906434);
INSERT INTO `guns_ent_comp_app` VALUES (1192437514397310977, 1192426957166370818, 1174270350789906434);
INSERT INTO `guns_ent_comp_app` VALUES (1192437526036504578, 1192427288931622914, 1174270350789906434);
INSERT INTO `guns_ent_comp_app` VALUES (1192437536610344961, 1192428002504368129, 1174270350789906434);

-- ----------------------------
-- Table structure for guns_ent_company
-- ----------------------------
DROP TABLE IF EXISTS `guns_ent_company`;
CREATE TABLE `guns_ent_company`  (
  `company_id` bigint(20) NOT NULL COMMENT '公司id',
  `name` varchar(100) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '公司名称',
  `short_name` varchar(100) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '公司简称',
  `cp_code` varchar(64) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '公司编码',
  `parent_id` bigint(20) NULL DEFAULT NULL COMMENT '上级id',
  `parent_ids` varchar(1000) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '上级id的集合',
  `url` varchar(100) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '公司门户地址',
  `order_no` decimal(11, 2) NULL DEFAULT NULL COMMENT '排序',
  `description` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '公司简介',
  `status` int(11) NULL DEFAULT NULL COMMENT '状态（1启用，2禁用）',
  `create_user` bigint(20) NULL DEFAULT NULL COMMENT '创建人',
  `create_time` datetime(0) NULL DEFAULT NULL COMMENT '创建时间',
  `update_user` bigint(20) NULL DEFAULT NULL COMMENT '更新人',
  `update_time` datetime(0) NULL DEFAULT NULL COMMENT '更新时间',
  PRIMARY KEY (`company_id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_general_ci COMMENT = '企业信息' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of guns_ent_company
-- ----------------------------
INSERT INTO `guns_ent_company` VALUES (1192425871256870913, '荷兰皇家壳牌石油公司', '荷兰石油公司', 'COMP20191107HLHJQPSYGS', -1, '-1', '荷兰海牙', 2.00, '荷兰皇家壳牌石油公司由荷兰皇家石油与英国的壳牌两家公司合并组成。荷兰皇家石油于1890年创立，并获得荷兰女王特别授权，因此被命名为荷兰皇家壳牌石油公司', 1, 1184285445204418561, '2019-11-07 12:57:37', NULL, NULL);
INSERT INTO `guns_ent_company` VALUES (1192426576034160642, '戴姆勒股份公司', '戴姆勒', 'COMP20191107DMLGFGS', -1, '-1', '德国斯图加特', 3.00, '2019年3月28日，浙江吉利控股集团和戴姆勒股份公司宣布，双方将成立合资公司，在全球范围内联合运营和推动smart品牌转型，致力于将smart打造成为全球领先的高端电动智能汽车品牌', 1, 1184285445204418561, '2019-11-07 13:00:25', NULL, NULL);
INSERT INTO `guns_ent_company` VALUES (1192426957166370818, '鸿海精密工业股份有限公司', '鸿海精密', 'COMP20191107HHJMGYGFYXGS', -1, '-1', '台北县土城市工业区自由街2号', 4.00, '成立于1974年，鸿海科技集团是全球3C（电脑、通讯、消费类电子）代工领域规模最大、成长最快的国际集团，集团旗下公司不仅在台湾、香港、伦敦等证券交易所挂牌交易，更囊括当前台湾最大的企业、捷克前三大出口商、大中华地区最大出口商、2015年美国财富杂志全球企业排名第31名，及全球3C代工服务领域龙头等头衔。', 1, 1184285445204418561, '2019-11-07 13:01:55', NULL, NULL);
INSERT INTO `guns_ent_company` VALUES (1192427288931622914, '花旗集团', '花旗', 'COMP20191107HQJT', -1, '-1', '美国纽约', 5.00, '花旗集团（Citigroup）：1955年纽约花旗银行与纽约第一银行合并，改名为纽约第一花旗银行，1962年改为第一花旗银行，1967年改为花旗公司。总部设在纽约。1998年与旅行者集团合并组建花旗集团，成为世界上最大的金融服务公司。', 1, 1184285445204418561, '2019-11-07 13:03:15', NULL, NULL);
INSERT INTO `guns_ent_company` VALUES (1192428002504368129, '美国康卡斯特电信公司', '康卡斯特', 'COMP20191107MGKKSTDXGS', -1, '-1', '宾夕法尼亚州费城', 6.00, '是美国一家主要有线电视，宽带网络及IP电话服务供应商，总部位于宾夕法尼亚州的费城，拥有2460万有线电视用户，1440万宽带网络用户及560万IP电话用户，是美国最大的有线电视公司。康卡斯特亦是美国第二大互联网服务供应商', 1, 1184285445204418561, '2019-11-07 13:06:05', NULL, NULL);

-- ----------------------------
-- Table structure for guns_ent_dept
-- ----------------------------
DROP TABLE IF EXISTS `guns_ent_dept`;
CREATE TABLE `guns_ent_dept`  (
  `dept_id` bigint(20) NOT NULL COMMENT '部门id',
  `company_id` bigint(20) NULL DEFAULT NULL COMMENT '企业id',
  `dept_name` varchar(64) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '部门名称',
  `dept_short_name` varchar(64) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '部门简称',
  `dept_code` varchar(64) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '部门编码',
  `parent_id` bigint(20) NULL DEFAULT -1 COMMENT '上级部门',
  `parent_ids` varchar(1000) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT '-1' COMMENT '所有上级部门ID',
  `status` int(11) NULL DEFAULT NULL COMMENT '状态（1:启用，2：禁用）',
  `order_no` decimal(11, 2) NULL DEFAULT NULL COMMENT '排序',
  `description` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '部门描述',
  `create_time` datetime(0) NULL DEFAULT NULL COMMENT '创建时间',
  `create_user` bigint(20) NULL DEFAULT NULL COMMENT '创建人',
  `update_time` datetime(0) NULL DEFAULT NULL COMMENT '更新时间',
  `update_user` bigint(20) NULL DEFAULT NULL COMMENT '更新人',
  PRIMARY KEY (`dept_id`) USING BTREE,
  UNIQUE INDEX `deptId_index`(`dept_id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_general_ci COMMENT = '部门表' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of guns_ent_dept
-- ----------------------------
INSERT INTO `guns_ent_dept` VALUES (1192428625308180481, 1192425871256870913, '总务部', '总务部', 'DEPT20191107ZWB', 1192425871256870913, '1192425871256870913', 1, 1.00, NULL, '2019-11-07 13:08:33', 1184285445204418561, NULL, NULL);
INSERT INTO `guns_ent_dept` VALUES (1192428748239036417, 1192425871256870913, '会计部', '会计部', 'DEPT20191107HJB', 1192425871256870913, '1192425871256870913', 1, 2.00, NULL, '2019-11-07 13:09:02', 1184285445204418561, NULL, NULL);
INSERT INTO `guns_ent_dept` VALUES (1192428819550593025, 1192425871256870913, '财务部', '财务部', 'DEPT20191107CWB', 1192425871256870913, '1192425871256870913', 1, 3.00, NULL, '2019-11-07 13:09:19', 1184285445204418561, NULL, NULL);
INSERT INTO `guns_ent_dept` VALUES (1192429230290395137, 1192426576034160642, '业务开发部', '业务开发部', 'DEPT20191107YWKFB', 1192426576034160642, '1192426576034160642', 1, 4.00, NULL, '2019-11-07 13:10:57', 1184285445204418561, NULL, NULL);
INSERT INTO `guns_ent_dept` VALUES (1192429335382876162, 1192426576034160642, '人事部', '人事部', 'DEPT20191107RSB', 1192426576034160642, '1192426576034160642', 1, 5.00, NULL, '2019-11-07 13:11:22', 1184285445204418561, NULL, NULL);
INSERT INTO `guns_ent_dept` VALUES (1192429396128980994, 1192426576034160642, '劳务部', '劳务部', 'DEPT20191107LWB', 1192426576034160642, '1192426576034160642', 1, 6.00, NULL, '2019-11-07 13:11:37', 1184285445204418561, NULL, NULL);
INSERT INTO `guns_ent_dept` VALUES (1192429526785744897, 1192426957166370818, '企划部', '企划部', 'DEPT20191107QHB', 1192426957166370818, '1192426957166370818', 1, 7.00, NULL, '2019-11-07 13:12:08', 1184285445204418561, NULL, NULL);
INSERT INTO `guns_ent_dept` VALUES (1192429636303216641, 1192426957166370818, '管理部', '管理部', 'DEPT20191107GLB', 1192426957166370818, '1192426957166370818', 1, 8.00, NULL, '2019-11-07 13:12:34', 1184285445204418561, NULL, NULL);
INSERT INTO `guns_ent_dept` VALUES (1192429690703339521, 1192426957166370818, '法律部', '法律部', 'DEPT20191107FLB', 1192426957166370818, '1192426957166370818', 1, 9.00, NULL, '2019-11-07 13:12:47', 1184285445204418561, NULL, NULL);
INSERT INTO `guns_ent_dept` VALUES (1192430025102614530, 1192427288931622914, '人力开发部', '人力开发部', 'DEPT20191107RLKFB', 1192427288931622914, '1192427288931622914', 1, 10.00, NULL, '2019-11-07 13:14:07', 1184285445204418561, NULL, NULL);
INSERT INTO `guns_ent_dept` VALUES (1192430088797315073, 1192427288931622914, '教育培训部', '教育培训部', 'DEPT20191107JYPXB', 1192427288931622914, '1192427288931622914', 1, 11.00, NULL, '2019-11-07 13:14:22', 1184285445204418561, NULL, NULL);
INSERT INTO `guns_ent_dept` VALUES (1192430173727776769, 1192427288931622914, '信用部', '信用部', 'DEPT20191107XYB', 1192427288931622914, '1192427288931622914', 1, 12.00, NULL, '2019-11-07 13:14:42', 1184285445204418561, NULL, NULL);
INSERT INTO `guns_ent_dept` VALUES (1192430290115518466, 1192428002504368129, '国际部', '国际部', 'DEPT20191107GJB', 1192428002504368129, '1192428002504368129', 1, 13.00, NULL, '2019-11-07 13:15:10', 1184285445204418561, NULL, NULL);
INSERT INTO `guns_ent_dept` VALUES (1192430354275786755, 1192428002504368129, '采购部', '采购部', 'DEPT20191107CGB', 1192428002504368129, '1192428002504368129', 1, 14.00, NULL, '2019-11-07 13:15:25', 1184285445204418561, NULL, NULL);
INSERT INTO `guns_ent_dept` VALUES (1192430430830223361, 1192428002504368129, '设计部', '设计部', 'DEPT20191107SJB', 1192428002504368129, '1192428002504368129', 1, 15.00, NULL, '2019-11-07 13:15:44', 1184285445204418561, NULL, NULL);

-- ----------------------------
-- Table structure for guns_ent_duty
-- ----------------------------
DROP TABLE IF EXISTS `guns_ent_duty`;
CREATE TABLE `guns_ent_duty`  (
  `duty_id` bigint(20) NOT NULL COMMENT '职务管理ID',
  `duty_code` varchar(64) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '职务唯一业务编码',
  `duty_name` varchar(64) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '职务名称',
  `order_no` decimal(11, 2) NULL DEFAULT NULL COMMENT '排序码',
  `status` int(11) NULL DEFAULT NULL COMMENT '状态(1:启用,2:禁用)',
  `description` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '描述',
  `create_user` bigint(20) NULL DEFAULT NULL COMMENT '创建人',
  `create_time` datetime(0) NULL DEFAULT NULL COMMENT '创建时间',
  `update_user` bigint(20) NULL DEFAULT NULL COMMENT '更新人',
  `update_time` datetime(0) NULL DEFAULT NULL COMMENT '更新时间',
  PRIMARY KEY (`duty_id`) USING BTREE,
  UNIQUE INDEX `duty_manageId_index`(`duty_id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_general_ci COMMENT = '职务管理' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of guns_ent_duty
-- ----------------------------
INSERT INTO `guns_ent_duty` VALUES (1192432207281545219, 'DUTY20191107DSJZX', '董事局主席', 1.00, 1, NULL, 1184285445204418561, '2019-11-07 13:22:47', NULL, NULL);
INSERT INTO `guns_ent_duty` VALUES (1192432287837347843, 'DUTY20191107DSZ', '董事长', 2.00, 1, NULL, 1184285445204418561, '2019-11-07 13:23:06', NULL, NULL);
INSERT INTO `guns_ent_duty` VALUES (1192432340404559875, 'DUTY20191107ZJL', '总经理', 3.00, 1, NULL, 1184285445204418561, '2019-11-07 13:23:19', NULL, NULL);
INSERT INTO `guns_ent_duty` VALUES (1192432395551268867, 'DUTY20191107JL', '副总经理', 4.00, 1, '', 1184285445204418561, '2019-11-07 13:23:32', 1184285445204418561, '2019-11-07 13:25:16');
INSERT INTO `guns_ent_duty` VALUES (1192432495065325570, 'DUTY20191107ZG', '部门经理', 5.00, 1, '', 1184285445204418561, '2019-11-07 13:23:56', 1184285445204418561, '2019-11-07 13:25:35');
INSERT INTO `guns_ent_duty` VALUES (1192432594071871490, 'DUTY20191107BZ', '部门副经理', 7.00, 1, '', 1184285445204418561, '2019-11-07 13:24:19', 1184285445204418561, '2019-11-07 13:25:46');
INSERT INTO `guns_ent_duty` VALUES (1192433024638148610, 'DUTY20191107BMZG', '部门主管', 8.00, 1, '', 1184285445204418561, '2019-11-07 13:26:02', 1184285445204418561, '2019-11-07 13:26:09');
INSERT INTO `guns_ent_duty` VALUES (1192433128765939714, 'DUTY20191107BMFGLD', '部门分管领导', 9.00, 1, NULL, 1184285445204418561, '2019-11-07 13:26:27', NULL, NULL);
INSERT INTO `guns_ent_duty` VALUES (1192433193878315011, 'DUTY20191107ZZ', '组长', 10.00, 1, NULL, 1184285445204418561, '2019-11-07 13:26:42', NULL, NULL);

-- ----------------------------
-- Table structure for guns_ent_user
-- ----------------------------
DROP TABLE IF EXISTS `guns_ent_user`;
CREATE TABLE `guns_ent_user`  (
  `user_id` bigint(20) NOT NULL COMMENT '用户信息id',
  `name` varchar(64) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '姓名',
  `user_code` varchar(64) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '用户编码',
  `last_name` varchar(64) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '姓',
  `first_name` varchar(64) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '名',
  `last_name_pinyin` varchar(64) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '姓（拼音）',
  `first_name_pinyin` varchar(64) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '名（拼音）',
  `sex` char(1) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '性别(M:男   F:女)',
  `birthday` datetime(0) NULL DEFAULT NULL COMMENT '生日',
  `education` varchar(64) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '学历（字典项）',
  `birthplace` varchar(64) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '籍贯',
  `nation` varchar(64) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '民族',
  `id_card` varchar(20) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '身份证号',
  `matrimony` varchar(64) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '婚姻状况（字典项）',
  `political` varchar(64) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '政治面貌（字典项）',
  `email` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '邮箱',
  `phone` varchar(20) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '电话',
  `mobile_phone` varchar(20) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '手机号',
  `avatar_url` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '用户头像地址',
  `order_no` decimal(11, 2) NULL DEFAULT 9999.00 COMMENT '排序码',
  `status` int(11) NULL DEFAULT 1 COMMENT '状态(1:启用  2:禁用)',
  `create_time` datetime(0) NULL DEFAULT NULL COMMENT '创建时间',
  `create_user` bigint(20) NULL DEFAULT NULL COMMENT '创建人',
  `update_time` datetime(0) NULL DEFAULT NULL COMMENT '更新时间',
  `update_user` bigint(20) NULL DEFAULT NULL COMMENT '更新人',
  PRIMARY KEY (`user_id`) USING BTREE,
  UNIQUE INDEX `infoId_index`(`user_id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_general_ci COMMENT = '用户信息' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of guns_ent_user
-- ----------------------------
INSERT INTO `guns_ent_user` VALUES (1, '超级赛亚人', 'SYSTEM_USER_1', '超级', '赛亚人', NULL, NULL, 'M', '2011-10-03 16:00:00', NULL, NULL, NULL, NULL, NULL, NULL, '1225486625@qq.com', '12138987654', NULL, 'https://micro.stylefeng.cn/file/preview?fileId=1190852155242713090', 9999.00, 1, '2019-10-16 01:50:28', -100, '2019-11-03 13:22:37', 1184285445204418561);
INSERT INTO `guns_ent_user` VALUES (1192434377825476609, '柳井正', 'USER20191107LJZM20101107', '柳', '井正', 'liu', 'jingzheng', 'M', '2010-11-07 00:00:00', 'MASTER', '中国', '汉族', '140827199202140016', 'MARRIED', 'MASSES', '223227@163.com', '18622224521', '18622224521', NULL, 10000.00, 1, '2019-11-07 13:31:25', 1184285445204418561, NULL, NULL);
INSERT INTO `guns_ent_user` VALUES (1192434942076805121, '杨惠妍', 'USER20191107YHYF20151004', '杨', '惠妍', 'yang', 'huiyan', 'F', '2015-10-04 00:00:00', 'MASTER', '中国', '汉族', '777766665555444432', 'MARRIED', 'MASSES', '88565432@163.com', '18636326687', '18636326687', NULL, 10001.00, 1, '2019-11-07 13:33:39', 1184285445204418561, NULL, NULL);
INSERT INTO `guns_ent_user` VALUES (1192435298991104001, '何享健', 'USER20191107HXJM20191103', '何', '享健', 'he', 'xiangjian', 'M', '2019-11-03 00:00:00', 'MASTER', '中国', '汉族', '112256789654356784', 'MARRIED', 'PARTY', 'dfe5432@163.com', '18636372765', '18636372765', NULL, 10002.00, 1, '2019-11-07 13:35:04', 1184285445204418561, NULL, NULL);
INSERT INTO `guns_ent_user` VALUES (1192435630169153538, '黄惠祥', 'USER20191107HHXM20101107', '黄', '惠祥', 'huang', 'huixiang', 'M', '2010-11-07 00:00:00', 'MASTER', '中国', '汉族', '3342343233231321453', 'MARRIED', 'MASSES', '45465521@163.com', '18636370098', '18636370098', NULL, 10003.00, 1, '2019-11-07 13:36:23', 1184285445204418561, NULL, NULL);
INSERT INTO `guns_ent_user` VALUES (1192435904405331969, '李文达', 'USER20191107LWDM20151101', '李', '文达', 'li', 'wenda', 'M', '2015-11-01 00:00:00', 'MASTER', '中国', '汉族', '213213344676654545', 'MARRIED', 'MASSES', '45546345@163.com', '18636372709', '18636372709', NULL, 10004.00, 1, '2019-11-07 13:37:29', 1184285445204418561, NULL, NULL);

-- ----------------------------
-- Table structure for guns_ent_user_account
-- ----------------------------
DROP TABLE IF EXISTS `guns_ent_user_account`;
CREATE TABLE `guns_ent_user_account`  (
  `account_id` bigint(20) NOT NULL COMMENT '账号id',
  `account` varchar(100) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '账号(工卡号)',
  `password` varchar(100) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '密码',
  `salt` varchar(6) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '密码盐',
  `user_id` bigint(20) NULL DEFAULT NULL COMMENT '用户信息id',
  `company_id` bigint(20) NULL DEFAULT NULL COMMENT '默认登录公司id',
  `create_time` datetime(0) NULL DEFAULT NULL COMMENT '创建时间',
  `create_user` bigint(20) NULL DEFAULT NULL COMMENT '创建人',
  `update_time` datetime(0) NULL DEFAULT NULL COMMENT '修改时间',
  `update_user` bigint(20) NULL DEFAULT NULL COMMENT '修改人',
  `del_flag` char(1) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '删除标记',
  PRIMARY KEY (`account_id`) USING BTREE,
  UNIQUE INDEX `accountId_index`(`account_id`) USING BTREE,
  INDEX `infoId_index`(`user_id`) USING BTREE,
  INDEX `company_index`(`company_id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_general_ci COMMENT = '登录账号' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of guns_ent_user_account
-- ----------------------------
INSERT INTO `guns_ent_user_account` VALUES (1184285445204418561, 'admin', '5095826806ac6f741a5df27bab842e28', '0v6hv', 1, NULL, '2019-10-16 01:50:27', -100, '2019-11-07 13:27:39', 1184285445204418561, NULL);
INSERT INTO `guns_ent_user_account` VALUES (1192434377959694338, 'ljz', '5095826806ac6f741a5df27bab842e28', '0v6hv', 1192434377825476609, NULL, '2019-11-07 13:31:25', 1184285445204418561, '2019-11-07 13:44:35', 1184285445204418561, 'N');
INSERT INTO `guns_ent_user_account` VALUES (1192434942114553859, 'yhy', '5095826806ac6f741a5df27bab842e28', '0v6hv', 1192434942076805121, NULL, '2019-11-07 13:33:39', 1184285445204418561, NULL, NULL, 'N');
INSERT INTO `guns_ent_user_account` VALUES (1192435299083378690, 'hxj', '5095826806ac6f741a5df27bab842e28', '0v6hv', 1192435298991104001, NULL, '2019-11-07 13:35:04', 1184285445204418561, NULL, NULL, 'N');
INSERT INTO `guns_ent_user_account` VALUES (1192435630265622530, 'hhx', '5095826806ac6f741a5df27bab842e28', '0v6hv', 1192435630169153538, NULL, '2019-11-07 13:36:23', 1184285445204418561, NULL, NULL, 'N');
INSERT INTO `guns_ent_user_account` VALUES (1192435904476635137, 'lwd', '5095826806ac6f741a5df27bab842e28', '0v6hv', 1192435904405331969, NULL, '2019-11-07 13:37:29', 1184285445204418561, NULL, NULL, 'N');

-- ----------------------------
-- Table structure for guns_ent_user_dept
-- ----------------------------
DROP TABLE IF EXISTS `guns_ent_user_dept`;
CREATE TABLE `guns_ent_user_dept`  (
  `user_dept_id` bigint(20) NOT NULL COMMENT '主键',
  `user_id` bigint(20) NOT NULL COMMENT '用户id',
  `duty_id` bigint(20) NOT NULL COMMENT '职务id',
  `company_id` bigint(20) NOT NULL COMMENT '公司id',
  `dept_id` bigint(20) NOT NULL COMMENT '部门id',
  `default_flag` char(1) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL DEFAULT 'N' COMMENT '是否为 默认部门(\'N\'代表否,\'Y\'代表是)',
  PRIMARY KEY (`user_dept_id`) USING BTREE,
  INDEX `duty_index`(`duty_id`) USING BTREE,
  INDEX `infoId_index`(`user_id`) USING BTREE,
  INDEX `deptId_index`(`dept_id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_general_ci COMMENT = '用户部门关联表' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of guns_ent_user_dept
-- ----------------------------
INSERT INTO `guns_ent_user_dept` VALUES (1192434377938722818, 1192434377825476609, 1192432287837347843, 1192425871256870913, 1192428625308180481, 'Y');
INSERT INTO `guns_ent_user_dept` VALUES (1192434942114553858, 1192434942076805121, 1192432340404559875, 1192426576034160642, 1192429230290395137, 'Y');
INSERT INTO `guns_ent_user_dept` VALUES (1192435299058212865, 1192435298991104001, 1192432207281545219, 1192426957166370818, 1192429636303216641, 'Y');
INSERT INTO `guns_ent_user_dept` VALUES (1192435630257233922, 1192435630169153538, 1192432207281545219, 1192427288931622914, 1192430088797315073, 'Y');
INSERT INTO `guns_ent_user_dept` VALUES (1192435904472440833, 1192435904405331969, 1192432207281545219, 1192428002504368129, 1192430354275786755, 'Y');

-- ----------------------------
-- Table structure for guns_fileinfo
-- ----------------------------
DROP TABLE IF EXISTS `guns_fileinfo`;
CREATE TABLE `guns_fileinfo`  (
  `file_id` bigint(20) NOT NULL COMMENT 'infoId',
  `file_name` varchar(64) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '文件名称',
  `file_url` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '文件路径',
  `file_size` decimal(11, 2) NULL DEFAULT NULL COMMENT '文件大小',
  `file_final_name` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '文件存储路径',
  `file_suffix` varchar(64) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '文件后缀',
  `app_code` varchar(64) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '应用编码',
  `app_name` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '应用名称',
  `create_time` datetime(0) NULL DEFAULT NULL COMMENT '创建时间',
  `create_user` bigint(20) NULL DEFAULT NULL COMMENT '创建人',
  `update_time` datetime(0) NULL DEFAULT NULL COMMENT '更新时间',
  `update_user` bigint(20) NULL DEFAULT NULL COMMENT '更新人',
  `del_flag` char(1) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '删除标记（N:未删除，Y:删除）',
  PRIMARY KEY (`file_id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Table structure for guns_sys_app
-- ----------------------------
DROP TABLE IF EXISTS `guns_sys_app`;
CREATE TABLE `guns_sys_app`  (
  `app_id` bigint(20) NOT NULL COMMENT '应用id',
  `app_code` varchar(64) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '应用编码',
  `app_name` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '应用名称',
  `app_icon` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '应用图标地址',
  `app_type` bigint(20) NULL DEFAULT NULL COMMENT '应用类型（字典）',
  `app_jump_address` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '应用跳转地址',
  `description` varchar(1000) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '应用描述',
  `order_no` decimal(11, 2) NULL DEFAULT NULL COMMENT '排序',
  `status` int(11) NULL DEFAULT NULL COMMENT '应用状态(1:启用,2:禁用)',
  `create_user` bigint(20) NULL DEFAULT NULL COMMENT '创建人',
  `create_time` datetime(0) NULL DEFAULT NULL COMMENT '创建时间',
  `update_user` bigint(20) NULL DEFAULT NULL COMMENT '更新人',
  `update_time` datetime(0) NULL DEFAULT NULL COMMENT '更新时间',
  `del_flag` char(1) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '删除标记',
  PRIMARY KEY (`app_id`) USING BTREE,
  UNIQUE INDEX `APP_CODE_UNI`(`app_code`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_general_ci COMMENT = '应用管理' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of guns_sys_app
-- ----------------------------
INSERT INTO `guns_sys_app` VALUES (1174270350789906434, 'guns-cloud-system', '系统管理平台', 'el-icon-user-solid', 1183726300989239311, 'http://localhost:9501/#/home', '基础平台，基础服务', 1.00, 1, 0, '2019-09-18 18:34:03', 1, '2019-12-10 14:16:40', '');
INSERT INTO `guns_sys_app` VALUES (1202565232069730305, 'guns-cloud-auth', '统一认证后台', 'el-icon-s-promotion', 1183726300989239311, 'http://localhost:9502/#/home', '统一认证后台', 2.00, 1, 1184285445204418561, '2019-12-05 12:27:49', 1184285445204418561, '2020-03-22 03:18:09', '');
INSERT INTO `guns_sys_app` VALUES (1208723761432387585, 'guns-cloud-workflow', '工作流管理平台', 'el-icon-document-checked', 1183726300989239311, 'http://localhost:9503/#/home', '工作流程管理平台', 3.00, 1, 1184285445204418561, '2019-12-22 12:19:36', 1184285445204418561, '2020-03-22 03:18:20', '');

-- ----------------------------
-- Table structure for guns_sys_button
-- ----------------------------
DROP TABLE IF EXISTS `guns_sys_button`;
CREATE TABLE `guns_sys_button`  (
  `button_id` bigint(20) NOT NULL COMMENT '按钮id',
  `menu_id` bigint(20) NOT NULL COMMENT '菜单id',
  `res_code` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '资源编码',
  `res_code_str` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '资源编码的字符串(前端需要)',
  `button_name` varchar(32) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '按钮名称',
  `button_code` varchar(32) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '按钮编码',
  `description` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '按钮描述',
  `status` int(1) NOT NULL COMMENT '按钮状态(1:启用 2:禁用)',
  `order_no` decimal(11, 2) NOT NULL COMMENT '按钮排序',
  `create_user` bigint(20) NULL DEFAULT NULL COMMENT '创建人',
  `create_time` datetime(0) NULL DEFAULT NULL COMMENT '创建时间',
  `update_user` bigint(20) NULL DEFAULT NULL COMMENT '修改人',
  `update_time` datetime(0) NULL DEFAULT NULL COMMENT '修改时间',
  PRIMARY KEY (`button_id`) USING BTREE,
  UNIQUE INDEX `button_code`(`button_code`) USING BTREE COMMENT '按钮编码'
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_general_ci COMMENT = '按钮表' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Table structure for guns_sys_database_info
-- ----------------------------
DROP TABLE IF EXISTS `guns_sys_database_info`;
CREATE TABLE `guns_sys_database_info`  (
  `id` bigint(20) NOT NULL COMMENT '主键id',
  `db_name` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '数据库名称（英文名称）',
  `jdbc_driver` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT 'jdbc的驱动类型',
  `user_name` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '数据库连接的账号',
  `password` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '数据库连接密码',
  `jdbc_url` varchar(2000) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT 'jdbc的url',
  `remarks` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '备注，摘要',
  `create_time` datetime(0) NULL DEFAULT NULL COMMENT '创建时间',
  PRIMARY KEY (`id`) USING BTREE,
  UNIQUE INDEX `NAME_UNIQUE`(`db_name`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_general_ci COMMENT = '数据库信息表' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of guns_sys_database_info
-- ----------------------------
INSERT INTO `guns_sys_database_info` VALUES (1309851800081371138, 'master', 'com.mysql.cj.jdbc.Driver', 'root', '123456', 'jdbc:mysql://192.168.11.128:3306/guns_cloud_system?autoReconnect=true&useUnicode=true&characterEncoding=utf8&zeroDateTimeBehavior=convertToNull&useSSL=false&serverTimezone=CTT', '主数据源，项目启动数据源！1111', '2020-09-26 21:46:19');

-- ----------------------------
-- Table structure for guns_sys_dict
-- ----------------------------
DROP TABLE IF EXISTS `guns_sys_dict`;
CREATE TABLE `guns_sys_dict`  (
  `dict_id` bigint(20) NOT NULL COMMENT '字典id',
  `dict_type_code` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '字典类型编码',
  `dict_code` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '字典编码',
  `dict_name` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '字典名称',
  `dict_short_name` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '简称',
  `dict_short_code` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '字典简拼',
  `parent_id` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '上级代码id',
  `status` smallint(6) NOT NULL COMMENT '状态(1:启用,2:禁用)',
  `order_no` decimal(11, 2) NULL DEFAULT NULL COMMENT '排序',
  `create_user` bigint(20) NULL DEFAULT NULL COMMENT '创建人',
  `create_time` datetime(0) NULL DEFAULT NULL COMMENT '创建时间',
  `update_user` bigint(20) NULL DEFAULT NULL COMMENT '更新人',
  `update_time` datetime(0) NULL DEFAULT NULL COMMENT '更新时间',
  `del_flag` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '删除标记',
  PRIMARY KEY (`dict_id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_general_ci COMMENT = '基础字典' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of guns_sys_dict
-- ----------------------------
INSERT INTO `guns_sys_dict` VALUES (1183726300989239311, 'APP_TYPE', 'INSIDE', '内部', '内部', NULL, '-1', 1, 1.00, NULL, NULL, NULL, NULL, 'N');
INSERT INTO `guns_sys_dict` VALUES (1183726300989239411, 'APP_TYPE', 'OUTSIDE', '外部', '外部', NULL, '-1', 1, 1.00, NULL, NULL, NULL, NULL, 'N');
INSERT INTO `guns_sys_dict` VALUES (1183727344871858177, 'EDUCATION', 'REGULAR', '本科', '本科', '', '1183727829133664258', 1, 1.00, -1, '2019-10-14 20:54:10', 1184285445204418561, '2019-11-06 01:29:31', 'N');
INSERT INTO `guns_sys_dict` VALUES (1183727829133664258, 'EDUCATION', 'MASTER', '硕士', '硕士', '', '-1', 1, 1.00, -1, '2019-10-14 20:55:54', 1184285445204418561, '2019-11-03 14:30:48', 'N');
INSERT INTO `guns_sys_dict` VALUES (1183728219208093697, 'MARITAL', 'MARRIED', '已婚', '已婚', NULL, '-1', 1, 1.00, -1, '2019-10-14 20:57:14', NULL, NULL, 'N');
INSERT INTO `guns_sys_dict` VALUES (1183728535072669697, 'MARITAL', 'SPINSTERHOOD', '未婚', '未婚', NULL, '-1', 1, 1.00, -1, '2019-10-14 20:58:56', NULL, NULL, 'N');
INSERT INTO `guns_sys_dict` VALUES (1183729049877463041, 'POLICY', 'PARTY', '党员', '党员', NULL, '-1', 1, 1.00, -1, '2019-10-14 21:00:30', NULL, NULL, 'N');
INSERT INTO `guns_sys_dict` VALUES (1183729632801746946, 'POLICY', 'MASSES', '群众', '群众', NULL, '-1', 1, 1.00, -1, '2019-10-14 21:02:52', NULL, NULL, 'N');

-- ----------------------------
-- Table structure for guns_sys_dict_type
-- ----------------------------
DROP TABLE IF EXISTS `guns_sys_dict_type`;
CREATE TABLE `guns_sys_dict_type`  (
  `dict_type_id` bigint(20) NOT NULL COMMENT '字典类型id',
  `dict_type_class` smallint(1) NULL DEFAULT NULL COMMENT '类型1：业务类型2：系统类型',
  `dict_type_code` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '字典类型编码',
  `dict_type_name` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '字典类型名称',
  `dict_type_desc` varchar(1000) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '字典描述',
  `status` smallint(11) NOT NULL DEFAULT 1 COMMENT '状态1：启用2：禁用',
  `app_code` varchar(32) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '应用编码',
  `order_no` decimal(11, 2) NULL DEFAULT NULL COMMENT '排序',
  `create_user` bigint(11) NULL DEFAULT NULL COMMENT '创建人',
  `create_time` datetime(0) NULL DEFAULT NULL COMMENT '添加时间',
  `update_user` bigint(11) NULL DEFAULT NULL COMMENT '修改人',
  `update_time` datetime(0) NULL DEFAULT NULL COMMENT '修改时间',
  `del_flag` char(1) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '删除标记',
  PRIMARY KEY (`dict_type_id`) USING BTREE,
  UNIQUE INDEX `CODE_UNI`(`dict_type_code`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_general_ci COMMENT = '字典类型表' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of guns_sys_dict_type
-- ----------------------------
INSERT INTO `guns_sys_dict_type` VALUES (1183725058342834178, 1, 'EDUCATION', '学历', '学历', 1, NULL, 1.00, 1, '2019-10-14 20:46:26', 1184285445204418561, '2019-10-18 13:20:57', 'N');
INSERT INTO `guns_sys_dict_type` VALUES (1183725998110265346, 1, 'MARITAL', '婚姻状况', '婚姻状况', 1, NULL, 2.00, 1, '2019-10-14 20:48:20', NULL, NULL, 'N');
INSERT INTO `guns_sys_dict_type` VALUES (1183726300989239298, 1, 'POLICY', '政治面貌', '政治面貌', 1, NULL, 3.00, 1, '2019-10-14 20:49:21', NULL, NULL, 'N');
INSERT INTO `guns_sys_dict_type` VALUES (1183726300989239310, 1, 'APP_TYPE', '应用类型', '应用类型', 1, NULL, 4.00, 1, NULL, NULL, NULL, 'N');

-- ----------------------------
-- Table structure for guns_sys_menu
-- ----------------------------
DROP TABLE IF EXISTS `guns_sys_menu`;
CREATE TABLE `guns_sys_menu`  (
  `menu_id` bigint(20) NOT NULL COMMENT '菜单id',
  `name` varchar(128) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '菜单名称',
  `code` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '菜单编码',
  `pid` bigint(20) NULL DEFAULT NULL COMMENT '菜单父id',
  `icon` varchar(128) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '图标',
  `app_id` bigint(20) NULL DEFAULT NULL COMMENT '应用id',
  `res_code` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '资源code',
  `menu_tips` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '菜单提示',
  `hidden_flag` char(1) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT 'N' COMMENT '是否隐藏',
  `order_no` decimal(11, 2) NULL DEFAULT NULL COMMENT '排序编号',
  `url` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '请求地址',
  `status` int(11) NULL DEFAULT 1 COMMENT '菜单状态(1:启用,2:禁用)',
  `create_user` bigint(20) NULL DEFAULT NULL COMMENT '创建人',
  `create_time` datetime(0) NULL DEFAULT NULL COMMENT '创建时间',
  `update_user` bigint(20) NULL DEFAULT NULL COMMENT '更新人',
  `update_time` datetime(0) NULL DEFAULT NULL COMMENT '更新时间',
  PRIMARY KEY (`menu_id`) USING BTREE,
  UNIQUE INDEX `CODE_UNI`(`code`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_general_ci COMMENT = '菜单' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of guns_sys_menu
-- ----------------------------
INSERT INTO `guns_sys_menu` VALUES (1177096712667971585, '资源权限', 'resourcePermission', -1, 'icon-keystroke', 1174270350789906434, 'guns-cloud-system$authority_menu_flag$authority_menu', '', 'N', 10.00, 'Layout', 1, 1, '2019-09-26 13:45:00', 1184285445204418561, '2019-10-31 14:48:31');
INSERT INTO `guns_sys_menu` VALUES (1177097020781543426, '组织架构', 'orgBuild', -1, 'icon-department1', 1174270350789906434, 'guns-cloud-system$ent_menu_flag$system', '', 'N', 1.00, 'Layout', 1, 1, '2019-09-26 13:46:14', 1184285445204418561, '2019-10-31 15:12:46');
INSERT INTO `guns_sys_menu` VALUES (1177097856710524930, '系统管理', 'systemManage', -1, 'icon-system', 1174270350789906434, 'guns-cloud-system$system_menu_flag$system', '', 'N', 30.00, 'Layout', 1, 1, '2019-09-26 13:49:33', 1184285445204418561, '2019-10-31 13:40:13');
INSERT INTO `guns_sys_menu` VALUES (1177098192292593666, '角色管理', 'roleManage', 1177096712667971585, 'icon-mg-role', 1174270350789906434, 'guns-cloud-system$authority_menu_flag$role_manager', '', 'N', 11.00, 'resourcePermission/roleManage', 1, 1, '2019-09-26 13:50:53', 1184285445204418561, '2019-10-31 13:37:47');
INSERT INTO `guns_sys_menu` VALUES (1177113716217049089, '权限管理', 'permissionManage', 1177096712667971585, 'icon-accountauthority', 1174270350789906434, 'guns-cloud-system$authority_menu_flag$permission_manager', '', 'N', 12.00, 'resourcePermission/permissionManage', 1, 1, '2019-09-26 14:52:34', 1184285445204418561, '2019-10-31 14:48:13');
INSERT INTO `guns_sys_menu` VALUES (1177113931707805697, '菜单管理', 'menuManage', 1177096712667971585, 'icon-menu', 1174270350789906434, 'guns-cloud-system$authority_menu_flag$menu_manager', '', 'N', 13.00, 'resourcePermission/menuManage', 1, 1, '2019-09-26 14:53:26', 1184285445204418561, '2019-10-31 13:39:28');
INSERT INTO `guns_sys_menu` VALUES (1177114084615352322, '资源管理', 'resourceManage', 1177096712667971585, 'icon-urlguanli', 1174270350789906434, 'guns-cloud-system$authority_menu_flag$resource_manager', '', 'N', 14.00, 'resourcePermission/resourceManage', 1, 1, '2019-09-26 14:54:02', 1184285445204418561, '2019-10-31 14:01:15');
INSERT INTO `guns_sys_menu` VALUES (1177114239078985729, '公司管理', 'companyManage', 1177097020781543426, 'icon-company', 1174270350789906434, 'guns-cloud-system$ent_menu_flag$company', '', 'N', 21.00, 'orgBuild/companyManage', 1, 1, '2019-09-26 14:54:39', 1184285445204418561, '2019-10-31 13:36:29');
INSERT INTO `guns_sys_menu` VALUES (1177114395367141378, '部门管理', 'deptManage', 1177097020781543426, 'icon-department', 1174270350789906434, 'guns-cloud-system$ent_menu_flag$dept', '', 'N', 22.00, 'orgBuild/deptManage', 1, 1, '2019-09-26 14:55:16', 1184285445204418561, '2019-10-31 14:49:19');
INSERT INTO `guns_sys_menu` VALUES (1177114554092187649, '人员管理', 'personManage', 1177097020781543426, 'icon-geren', 1174270350789906434, 'guns-cloud-system$ent_menu_flag$personal', '', 'N', 23.00, 'orgBuild/personManage', 1, 1, '2019-09-26 14:55:54', 1184285445204418561, '2019-10-31 13:36:49');
INSERT INTO `guns_sys_menu` VALUES (1183302416396931073, '职位管理', 'dutyManage', 1177097020781543426, 'icon-position', 1174270350789906434, 'guns-cloud-system$ent_menu_flag$duty', '', 'N', 24.00, 'orgBuild/dutyManage', 1, 1, '2019-10-13 08:44:15', 1184285445204418561, '2019-10-31 13:36:11');
INSERT INTO `guns_sys_menu` VALUES (1183303663497687041, '应用管理', 'appManage', 1177097856710524930, 'icon-app', 1174270350789906434, 'guns-cloud-system$system_menu_flag$app_manager', '', 'N', 31.00, 'systemManage/appManage', 1, 1, '2019-10-13 08:49:13', 1184285445204418561, '2019-11-01 15:47:15');
INSERT INTO `guns_sys_menu` VALUES (1183303686608302082, '字典管理', 'dictManage', 1177097856710524930, 'icon-dictionary', 1174270350789906434, 'guns-cloud-system$system_menu_flag$dic_manager', '', 'N', 32.00, 'systemManage/dictManage', 1, 1, '2019-10-13 08:49:18', 1184285445204418561, '2019-10-31 13:35:29');
INSERT INTO `guns_sys_menu` VALUES (1202574470372417537, '单点应用', 'ssoApp', -1, 'icon-login-', 1202565232069730305, 'guns-cloud-auth$auth_manager_menu$sso_app', NULL, 'N', 5.00, 'Layout', 1, 1184285445204418561, '2019-12-05 13:04:31', NULL, NULL);
INSERT INTO `guns_sys_menu` VALUES (1202574608755089409, '客户端管理', 'clientManage', 1202574470372417537, 'icon-kehuduan', 1202565232069730305, 'guns-cloud-auth$auth_manager_menu$client_manager', NULL, 'N', 1.00, 'ssoApp/clientManage', 1, 1184285445204418561, '2019-12-05 13:05:04', NULL, NULL);
INSERT INTO `guns_sys_menu` VALUES (1203630249892405250, '安全审计', 'securityAudit', -1, 'icon-anquan', 1202565232069730305, 'guns-cloud-auth$auth_manager_menu$safety_audit', NULL, 'N', 10.00, 'Layout', 1, 1184285445204418561, '2019-12-08 10:59:49', NULL, NULL);
INSERT INTO `guns_sys_menu` VALUES (1203630386165342210, '登录日志', 'loginLog', 1203630249892405250, 'icon-denglurizhi', 1202565232069730305, 'guns-cloud-auth$auth_manager_menu$login_log', NULL, 'N', 1.00, 'securityAudit/loginLog', 1, 1184285445204418561, '2019-12-08 11:00:21', NULL, NULL);
INSERT INTO `guns_sys_menu` VALUES (1203630508341223425, '会话管理', 'sessionManage', 1203630249892405250, 'icon-zuidahuihua', 1202565232069730305, 'guns-cloud-auth$auth_manager_menu$session_manage', NULL, 'N', 2.00, 'securityAudit/sessionManage', 1, 1184285445204418561, '2019-12-08 11:00:50', NULL, NULL);
INSERT INTO `guns_sys_menu` VALUES (1203630648405811202, '万能密码', 'godKeyPassword', 1203630249892405250, 'icon-mima', 1202565232069730305, 'guns-cloud-auth$auth_manager_menu$god_key', NULL, 'N', 3.00, 'securityAudit/godKeyPassword', 1, 1184285445204418561, '2019-12-08 11:01:24', NULL, NULL);
INSERT INTO `guns_sys_menu` VALUES (1203630856036442114, '资源维护', '/resourceMaintain/resourceMaintainIndex', -1, 'icon-ziyuan', 1202565232069730305, 'guns-cloud-auth$auth_manager_menu$res_maintain', NULL, 'N', 15.00, 'Layout', 1, 1184285445204418561, '2019-12-08 11:02:13', NULL, NULL);
INSERT INTO `guns_sys_menu` VALUES (1203630976043868161, '网关路由列表', '/routingMaintenance/routingMaintenanceIndex', -1, 'icon-luyou', 1202565232069730305, 'guns-cloud-auth$gateway_actuator$route_list', NULL, 'N', 20.00, 'Layout', 1, 1184285445204418561, '2019-12-08 11:02:42', NULL, NULL);
INSERT INTO `guns_sys_menu` VALUES (1210185114223403009, '工作流程', 'workFlow', -1, 'iconjurassic_process', 1208723761432387585, 'guns-cloud-workflow$workflow_menus$workflow', '', 'N', 5.00, 'Layout', 1, 1184285445204418561, '2019-12-26 13:06:30', 1184285445204418561, '2019-12-30 13:00:33');
INSERT INTO `guns_sys_menu` VALUES (1210185224390991874, '模型管理', 'modelManage', 1210185114223403009, 'iconmoxingzhongxin', 1208723761432387585, 'guns-cloud-workflow$workflow_menus$mode_manager', '', 'N', 1.00, '/workFlow/modelManage', 1, 1184285445204418561, '2019-12-26 13:06:56', 1184285445204418561, '2019-12-30 12:59:51');
INSERT INTO `guns_sys_menu` VALUES (1210185320729960450, '流程管理', 'flowManage', 1210185114223403009, 'iconliuchengguanli-', 1208723761432387585, 'guns-cloud-workflow$workflow_menus$flow_manager', '', 'N', 2.00, '/workFlow/flowManage', 1, 1184285445204418561, '2019-12-26 13:07:19', 1184285445204418561, '2019-12-30 12:54:20');
INSERT INTO `guns_sys_menu` VALUES (1210185425918910465, '运行中流程', 'runningFlow', 1210185114223403009, 'iconliucheng', 1208723761432387585, 'guns-cloud-workflow$workflow_menus$running_flow', '', 'N', 3.00, '/workFlow/runningFlow', 1, 1184285445204418561, '2019-12-26 13:07:44', 1184285445204418561, '2019-12-30 12:59:35');
INSERT INTO `guns_sys_menu` VALUES (1210185528234762241, '历史流程', 'historyFlow', 1210185114223403009, 'iconlishishuju', 1208723761432387585, 'guns-cloud-workflow$workflow_menus$history_flow', '', 'N', 4.00, '/workFlow/historyFlow', 1, 1184285445204418561, '2019-12-26 13:08:09', 1184285445204418561, '2019-12-30 13:00:08');
INSERT INTO `guns_sys_menu` VALUES (1210185649328513026, '任务管理', 'taskManage', -1, 'iconrenwuguanli', 1208723761432387585, 'guns-cloud-workflow$workflow_menus$tasks', '', 'N', 10.00, 'Layout', 1, 1184285445204418561, '2019-12-26 13:08:38', 1184285445204418561, '2019-12-30 12:54:08');
INSERT INTO `guns_sys_menu` VALUES (1210185762989957121, '待办任务', 'needToTask', 1210185649328513026, 'icondaiban', 1208723761432387585, 'guns-cloud-workflow$workflow_menus$waitings', '', 'N', 1.00, '/taskManage/needToTask', 1, 1184285445204418561, '2019-12-26 13:09:05', 1184285445204418561, '2019-12-30 12:54:29');
INSERT INTO `guns_sys_menu` VALUES (1210185880921202689, '已办任务', 'doneToTask', 1210185649328513026, 'iconyiban-moren', 1208723761432387585, 'guns-cloud-workflow$workflow_menus$alreadys', '', 'N', 2.00, '/taskManage/doneToTask', 1, 1184285445204418561, '2019-12-26 13:09:33', NULL, NULL);
INSERT INTO `guns_sys_menu` VALUES (1210186226355691521, '申请审批', 'applyApproval', -1, 'iconshenqingshenpi', 1208723761432387585, 'guns-cloud-workflow$workflow_menus$apply_for', '', 'N', 15.00, 'Layout', 1, 1184285445204418561, '2019-12-26 13:10:55', 1184285445204418561, '2019-12-30 12:53:44');
INSERT INTO `guns_sys_menu` VALUES (1210186353912864770, '请假审批', 'leaveApproval', 1210186226355691521, 'iconqingjia1', 1208723761432387585, 'guns-cloud-workflow$workflow_menus$leave', '', 'N', 1.00, '/applyApproval/leaveApproval', 1, 1184285445204418561, '2019-12-26 13:11:26', 1184285445204418561, '2019-12-30 12:53:59');
INSERT INTO `guns_sys_menu` VALUES (1289514958643544065, '系统监控', 'monitors', -1, 'icon-Monitor', 1174270350789906434, 'guns-cloud-system$monitor_menu_flag$sys_monitor', '', 'N', 1200.00, '', 1, 1184285445204418561, '2020-08-01 18:54:59', 1184285445204418561, '2020-08-02 10:36:56');
INSERT INTO `guns_sys_menu` VALUES (1289578122823368706, 'API管理', 'apiManager', -1, 'icon-APIwendang', 1174270350789906434, 'guns-cloud-system$api_menu_flag$api', '', 'N', 990.00, '', 1, 1184285445204418561, '2020-08-01 23:05:58', 1184285445204418561, '2020-08-02 10:35:11');
INSERT INTO `guns_sys_menu` VALUES (1289578713939214338, '接口文档', 'swagger', 1289578122823368706, 'icon-swagger', 1174270350789906434, 'guns-cloud-system$api_menu_flag$swagger', '', 'N', 0.00, '', 1, 1184285445204418561, '2020-08-01 23:08:19', 1184285445204418561, '2020-08-02 10:35:30');
INSERT INTO `guns_sys_menu` VALUES (1289578890288726018, '参数配置', 'configs', -1, 'icon-configuration', 1174270350789906434, 'guns-cloud-system$config_menu_flag$config_menu', '', 'N', 1000.00, '', 1, 1184285445204418561, '2020-08-01 23:09:01', 1184285445204418561, '2020-08-02 10:35:47');
INSERT INTO `guns_sys_menu` VALUES (1289579175685947393, '配置中心', 'nacos', 1289578890288726018, 'icon-ts-config', 1174270350789906434, 'guns-cloud-system$config_menu_flag$configs', '', 'N', 1001.00, '', 1, 1184285445204418561, '2020-08-01 23:10:09', 1184285445204418561, '2020-08-02 10:36:03');
INSERT INTO `guns_sys_menu` VALUES (1289579318623633410, '日志管理', 'logManager', -1, 'icon-rizhi', 1174270350789906434, 'guns-cloud-system$log_menu_flag$logs_menu', '', 'N', 1100.00, 'Layout', 1, 1184285445204418561, '2020-08-01 23:10:43', 1184285445204418561, '2020-08-02 10:36:20');
INSERT INTO `guns_sys_menu` VALUES (1289579421908369410, '业务日志', 'bizLog', 1289579318623633410, 'icon-wj-rz', 1174270350789906434, 'guns-cloud-system$log_menu_flag$biz_menu', '', 'N', 0.00, 'logManager/bizLog', 1, 1184285445204418561, '2020-08-01 23:11:08', 1184285445204418561, '2020-08-02 10:36:29');
INSERT INTO `guns_sys_menu` VALUES (1289579582768316418, '登录日志', 'sysLoginLog', 1289579318623633410, 'icon-system', 1174270350789906434, 'guns-cloud-system$log_menu_flag$login_menu', '', 'N', 0.00, 'logManager/sysLoginLog', 2, 1184285445204418561, '2020-08-01 23:11:46', 1184285445204418561, '2020-08-02 09:23:43');
INSERT INTO `guns_sys_menu` VALUES (1289580068082843649, 'druid监控', 'druid', 1289514958643544065, 'icon-jurassic_data', 1174270350789906434, 'guns-cloud-system$monitor_menu_flag$druid_menu', '', 'N', 0.00, '', 1, 1184285445204418561, '2020-08-01 23:13:42', 1184285445204418561, '2020-08-02 10:37:09');
INSERT INTO `guns_sys_menu` VALUES (1289580148839972865, '硬件监控', 'hardware', 1289514958643544065, 'icon-yingjian', 1174270350789906434, 'guns-cloud-system$monitor_menu_flag$hard_info_menu', '', 'N', 0.00, '', 1, 1184285445204418561, '2020-08-01 23:14:01', 1184285445204418561, '2020-08-02 10:37:19');
INSERT INTO `guns_sys_menu` VALUES (1309678048110501890, '多数据源', 'multiDatasource', -1, 'icon-jurassic_data', 1174270350789906434, 'guns-cloud-system$system_menu_flag$system', '', 'N', 1300.00, 'Layout', 1, 1184285445204418561, '2020-09-26 10:15:54', 1184285445204418561, '2020-09-26 10:39:31');
INSERT INTO `guns_sys_menu` VALUES (1309678143690301441, '数据源管理', 'dsManage', 1309678048110501890, 'icon-jurassic_data', 1174270350789906434, 'guns-cloud-system$system_menu_flag$system', '', 'N', 1.00, 'multiDatasource/dsManage', 1, 1184285445204418561, '2020-09-26 10:16:17', 1184285445204418561, '2020-09-26 10:39:42');
INSERT INTO `guns_sys_menu` VALUES (1309679647784493057, 'SaaS租户', 'tenant', -1, 'icon-department1', 1174270350789906434, 'guns-cloud-system$system_menu_flag$system', '', 'N', 1450.00, 'a', 1, 1184285445204418561, '2020-09-26 10:22:15', 1184285445204418561, '2020-09-26 10:23:22');
INSERT INTO `guns_sys_menu` VALUES (1309679803594498050, '多租户管理', 'tenantManager', 1309679647784493057, 'icon-urlguanli', 1174270350789906434, 'guns-cloud-system$system_menu_flag$system', NULL, 'N', 1.00, 'tenant', 1, 1184285445204418561, '2020-09-26 10:22:53', NULL, NULL);

-- ----------------------------
-- Table structure for guns_sys_op_log
-- ----------------------------
DROP TABLE IF EXISTS `guns_sys_op_log`;
CREATE TABLE `guns_sys_op_log`  (
  `id` bigint(20) NOT NULL COMMENT '主键',
  `name` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '名称',
  `op_type` tinyint(4) NULL DEFAULT NULL COMMENT '操作类型',
  `success` char(1) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '是否执行成功（Y-是，N-否）',
  `message` text CHARACTER SET utf8 COLLATE utf8_bin NULL COMMENT '具体消息',
  `ip` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT 'ip',
  `location` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '地址',
  `browser` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '浏览器',
  `os` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '操作系统',
  `url` varchar(500) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '请求地址',
  `class_name` varchar(500) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '类名称',
  `method_name` varchar(500) CHARACTER SET utf8 COLLATE utf8_bin NULL DEFAULT NULL COMMENT '方法名称',
  `req_method` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '请求方式（GET POST PUT DELETE)',
  `param` text CHARACTER SET utf8 COLLATE utf8_general_ci NULL COMMENT '请求参数',
  `op_time` datetime(0) NULL DEFAULT NULL COMMENT '操作时间',
  `account` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '操作账号',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_general_ci COMMENT = '系统操作日志表' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of guns_sys_op_log
-- ----------------------------
INSERT INTO `guns_sys_op_log` VALUES (1289752691626516482, '获取左侧菜单', 5, 'Y', '成功', '127.0.0.1', '-', 'Chrome', 'Windows 10 or Windows Server 2016', '/menu/getLeftMenuList', 'cn.stylefeng.guns.cloud.system.modular.sys.controller.MenuController', 'getLeftMenuList', 'GET', '\"1174270350789906434\"', '2020-08-02 10:39:39', '1111');

-- ----------------------------
-- Table structure for guns_sys_permission
-- ----------------------------
DROP TABLE IF EXISTS `guns_sys_permission`;
CREATE TABLE `guns_sys_permission`  (
  `permission_id` bigint(20) NOT NULL COMMENT '主键id',
  `permission_name` varchar(128) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '权限名称',
  `permission_desc` varchar(2000) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '权限描述',
  `order_no` decimal(11, 2) NULL DEFAULT NULL COMMENT '排序编号',
  `parent_id` bigint(20) NULL DEFAULT NULL COMMENT '父级id',
  `app_id` bigint(20) NULL DEFAULT NULL COMMENT '应用ID',
  `status` int(11) NULL DEFAULT 1 COMMENT '权限状态(1:启用,2:禁用)',
  `create_user` bigint(32) NULL DEFAULT NULL COMMENT '创建人',
  `create_time` datetime(0) NULL DEFAULT NULL COMMENT '创建时间',
  `update_user` bigint(32) NULL DEFAULT NULL COMMENT '更新人',
  `update_time` datetime(0) NULL DEFAULT NULL COMMENT '更新时间',
  `del_flag` char(1) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT 'N' COMMENT '删除标记(Y:已删除 N:未删除)',
  PRIMARY KEY (`permission_id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_general_ci COMMENT = '权限表' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of guns_sys_permission
-- ----------------------------
INSERT INTO `guns_sys_permission` VALUES (1184285448379506690, '系统管理员权限', NULL, NULL, -1, 1174270350789906434, 1, -100, '2019-10-16 01:50:29', 1184285445204418561, '2020-08-02 10:20:01', 'N');
INSERT INTO `guns_sys_permission` VALUES (1202572387757875202, '统一认证后台管管理员权限', '统一认证后台管理员', NULL, -1, 1202565232069730305, 1, 1184285445204418561, '2019-12-05 12:56:15', 1184285445204418561, '2019-12-25 06:57:58', 'N');
INSERT INTO `guns_sys_permission` VALUES (1210187481379528705, '工作流管理员权限', NULL, NULL, -1, 1208723761432387585, 1, 1184285445204418561, '2019-12-26 13:15:54', NULL, NULL, 'N');

-- ----------------------------
-- Table structure for guns_sys_permission_resource
-- ----------------------------
DROP TABLE IF EXISTS `guns_sys_permission_resource`;
CREATE TABLE `guns_sys_permission_resource`  (
  `permission_resource_id` bigint(20) NOT NULL COMMENT '权限资源id',
  `permission_id` bigint(20) NOT NULL COMMENT '权限ID',
  `resource_id` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '资源ID',
  PRIMARY KEY (`permission_resource_id`, `permission_id`, `resource_id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_general_ci COMMENT = '权限资源表' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of guns_sys_permission_resource
-- ----------------------------
INSERT INTO `guns_sys_permission_resource` VALUES (1241569095380406274, 1202572387757875202, 'guns-cloud-auth$auth_manager_menu$god_key');
INSERT INTO `guns_sys_permission_resource` VALUES (1241569095380406275, 1202572387757875202, 'guns-cloud-auth$auth_manager_menu$session_manage');
INSERT INTO `guns_sys_permission_resource` VALUES (1241569095380406276, 1202572387757875202, 'guns-cloud-auth$auth_manager_menu$sso_app');
INSERT INTO `guns_sys_permission_resource` VALUES (1241569095380406277, 1202572387757875202, 'guns-cloud-auth$auth_manager_menu$safety_audit');
INSERT INTO `guns_sys_permission_resource` VALUES (1241569095380406278, 1202572387757875202, 'guns-cloud-auth$auth_manager_menu$client_manager');
INSERT INTO `guns_sys_permission_resource` VALUES (1241569095380406279, 1202572387757875202, 'guns-cloud-auth$auth_manager_menu$login_log');
INSERT INTO `guns_sys_permission_resource` VALUES (1241569095380406280, 1202572387757875202, 'guns-cloud-auth$auth_manager_menu$res_maintain');
INSERT INTO `guns_sys_permission_resource` VALUES (1241569095380406281, 1202572387757875202, 'guns-cloud-auth$gateway_actuator$delete_route');
INSERT INTO `guns_sys_permission_resource` VALUES (1241569095380406282, 1202572387757875202, 'guns-cloud-auth$gateway_actuator$routefilters');
INSERT INTO `guns_sys_permission_resource` VALUES (1241569095380406283, 1202572387757875202, 'guns-cloud-auth$gateway_actuator$add_route');
INSERT INTO `guns_sys_permission_resource` VALUES (1241569095380406284, 1202572387757875202, 'guns-cloud-auth$gateway_actuator$route_info_by_id');
INSERT INTO `guns_sys_permission_resource` VALUES (1241569095380406285, 1202572387757875202, 'guns-cloud-auth$gateway_actuator$refresh_route');
INSERT INTO `guns_sys_permission_resource` VALUES (1241569095380406286, 1202572387757875202, 'guns-cloud-auth$gateway_actuator$route_list');
INSERT INTO `guns_sys_permission_resource` VALUES (1241569095380406287, 1202572387757875202, 'guns-cloud-auth$gateway_actuator$globalfilters');
INSERT INTO `guns_sys_permission_resource` VALUES (1241569095380406288, 1202572387757875202, 'guns-cloud-auth$god_key_manager$checked_status');
INSERT INTO `guns_sys_permission_resource` VALUES (1241569095380406289, 1202572387757875202, 'guns-cloud-auth$god_key_manager$get_god_key');
INSERT INTO `guns_sys_permission_resource` VALUES (1241569095380406290, 1202572387757875202, 'guns-cloud-auth$session_manager$forced_offline');
INSERT INTO `guns_sys_permission_resource` VALUES (1241569095380406291, 1202572387757875202, 'guns-cloud-auth$session_manager$page_list');
INSERT INTO `guns_sys_permission_resource` VALUES (1241569095380406292, 1202572387757875202, 'guns-cloud-auth$auth_login_log$detail');
INSERT INTO `guns_sys_permission_resource` VALUES (1241569095380406293, 1202572387757875202, 'guns-cloud-auth$auth_login_log$page_list');
INSERT INTO `guns_sys_permission_resource` VALUES (1241569095380406294, 1202572387757875202, 'guns-cloud-auth$res_cache$edit_res_cache');
INSERT INTO `guns_sys_permission_resource` VALUES (1241569095388794882, 1202572387757875202, 'guns-cloud-auth$res_cache$remove_res_cache');
INSERT INTO `guns_sys_permission_resource` VALUES (1241569095388794883, 1202572387757875202, 'guns-cloud-auth$res_cache$add_res_cache');
INSERT INTO `guns_sys_permission_resource` VALUES (1241569095388794884, 1202572387757875202, 'guns-cloud-auth$res_cache$get_http_request_select');
INSERT INTO `guns_sys_permission_resource` VALUES (1241569095388794885, 1202572387757875202, 'guns-cloud-auth$res_cache$get_app_select');
INSERT INTO `guns_sys_permission_resource` VALUES (1241569095388794886, 1202572387757875202, 'guns-cloud-auth$res_cache$get_detail');
INSERT INTO `guns_sys_permission_resource` VALUES (1241569095388794887, 1202572387757875202, 'guns-cloud-auth$res_cache$get_res_module_select');
INSERT INTO `guns_sys_permission_resource` VALUES (1241569095388794888, 1202572387757875202, 'guns-cloud-auth$res_cache$get_res_cache_list');
INSERT INTO `guns_sys_permission_resource` VALUES (1241569095388794889, 1202572387757875202, 'guns-cloud-auth$auth_client$delete');
INSERT INTO `guns_sys_permission_resource` VALUES (1241569095388794890, 1202572387757875202, 'guns-cloud-auth$auth_client$add');
INSERT INTO `guns_sys_permission_resource` VALUES (1241569095388794891, 1202572387757875202, 'guns-cloud-auth$auth_client$edit');
INSERT INTO `guns_sys_permission_resource` VALUES (1241569095388794892, 1202572387757875202, 'guns-cloud-auth$auth_client$detail');
INSERT INTO `guns_sys_permission_resource` VALUES (1241569095388794893, 1202572387757875202, 'guns-cloud-auth$auth_client$generate_key');
INSERT INTO `guns_sys_permission_resource` VALUES (1241569095388794894, 1202572387757875202, 'guns-cloud-auth$auth_client$page_list');
INSERT INTO `guns_sys_permission_resource` VALUES (1241569095388794895, 1202572387757875202, 'guns-cloud-auth$auth_client$test');
INSERT INTO `guns_sys_permission_resource` VALUES (1241569095388794896, 1202572387757875202, 'guns-cloud-auth$auth_client$test2');
INSERT INTO `guns_sys_permission_resource` VALUES (1241569104809201665, 1210187481379528705, 'guns-cloud-workflow$monitor_process$suspend');
INSERT INTO `guns_sys_permission_resource` VALUES (1241569104817590273, 1210187481379528705, 'guns-cloud-workflow$monitor_process$active');
INSERT INTO `guns_sys_permission_resource` VALUES (1241569104817590274, 1210187481379528705, 'guns-cloud-workflow$monitor_process$end');
INSERT INTO `guns_sys_permission_resource` VALUES (1241569104817590275, 1210187481379528705, 'guns-cloud-workflow$monitor_process$end_list');
INSERT INTO `guns_sys_permission_resource` VALUES (1241569104817590276, 1210187481379528705, 'guns-cloud-workflow$monitor_process$run_list');
INSERT INTO `guns_sys_permission_resource` VALUES (1241569104817590277, 1210187481379528705, 'guns-cloud-workflow$monitor_process$change_assignee');
INSERT INTO `guns_sys_permission_resource` VALUES (1241569104817590278, 1210187481379528705, 'guns-cloud-workflow$workflow_menus$tasks');
INSERT INTO `guns_sys_permission_resource` VALUES (1241569104817590279, 1210187481379528705, 'guns-cloud-workflow$workflow_menus$history_flow');
INSERT INTO `guns_sys_permission_resource` VALUES (1241569104817590280, 1210187481379528705, 'guns-cloud-workflow$workflow_menus$workflow');
INSERT INTO `guns_sys_permission_resource` VALUES (1241569104817590281, 1210187481379528705, 'guns-cloud-workflow$workflow_menus$alreadys');
INSERT INTO `guns_sys_permission_resource` VALUES (1241569104817590282, 1210187481379528705, 'guns-cloud-workflow$workflow_menus$waitings');
INSERT INTO `guns_sys_permission_resource` VALUES (1241569104817590283, 1210187481379528705, 'guns-cloud-workflow$workflow_menus$mode_manager');
INSERT INTO `guns_sys_permission_resource` VALUES (1241569104817590284, 1210187481379528705, 'guns-cloud-workflow$workflow_menus$flow_manager');
INSERT INTO `guns_sys_permission_resource` VALUES (1241569104817590285, 1210187481379528705, 'guns-cloud-workflow$workflow_menus$apply_for');
INSERT INTO `guns_sys_permission_resource` VALUES (1241569104817590286, 1210187481379528705, 'guns-cloud-workflow$workflow_menus$leave');
INSERT INTO `guns_sys_permission_resource` VALUES (1241569104817590287, 1210187481379528705, 'guns-cloud-workflow$workflow_menus$running_flow');
INSERT INTO `guns_sys_permission_resource` VALUES (1241569104817590288, 1210187481379528705, 'guns-cloud-workflow$common$user_list_by_role_id');
INSERT INTO `guns_sys_permission_resource` VALUES (1241569104817590289, 1210187481379528705, 'guns-cloud-workflow$common$role_list');
INSERT INTO `guns_sys_permission_resource` VALUES (1241569104817590290, 1210187481379528705, 'guns-cloud-workflow$model$add_item');
INSERT INTO `guns_sys_permission_resource` VALUES (1241569104817590291, 1210187481379528705, 'guns-cloud-workflow$model$delete');
INSERT INTO `guns_sys_permission_resource` VALUES (1241569104817590292, 1210187481379528705, 'guns-cloud-workflow$model$list');
INSERT INTO `guns_sys_permission_resource` VALUES (1241569104817590293, 1210187481379528705, 'guns-cloud-workflow$model$deploy');
INSERT INTO `guns_sys_permission_resource` VALUES (1241569104817590294, 1210187481379528705, 'guns-cloud-workflow$model$pre_view');
INSERT INTO `guns_sys_permission_resource` VALUES (1241569104817590295, 1210187481379528705, 'guns-cloud-workflow$my_leave$batch_delete_by_ids');
INSERT INTO `guns_sys_permission_resource` VALUES (1241569104817590296, 1210187481379528705, 'guns-cloud-workflow$my_leave$delete');
INSERT INTO `guns_sys_permission_resource` VALUES (1241569104817590297, 1210187481379528705, 'guns-cloud-workflow$my_leave$add_item');
INSERT INTO `guns_sys_permission_resource` VALUES (1241569104817590298, 1210187481379528705, 'guns-cloud-workflow$my_leave$detail');
INSERT INTO `guns_sys_permission_resource` VALUES (1241569104817590299, 1210187481379528705, 'guns-cloud-workflow$my_leave$get_leaves');
INSERT INTO `guns_sys_permission_resource` VALUES (1241569104817590300, 1210187481379528705, 'guns-cloud-workflow$my_leave$list');
INSERT INTO `guns_sys_permission_resource` VALUES (1241569104817590301, 1210187481379528705, 'guns-cloud-workflow$task$entrust');
INSERT INTO `guns_sys_permission_resource` VALUES (1241569104817590302, 1210187481379528705, 'guns-cloud-workflow$task$done_task');
INSERT INTO `guns_sys_permission_resource` VALUES (1241569104817590303, 1210187481379528705, 'guns-cloud-workflow$task$view_task_detail');
INSERT INTO `guns_sys_permission_resource` VALUES (1241569104817590304, 1210187481379528705, 'guns-cloud-workflow$task$candidate_list');
INSERT INTO `guns_sys_permission_resource` VALUES (1241569104817590305, 1210187481379528705, 'guns-cloud-workflow$task$done_list');
INSERT INTO `guns_sys_permission_resource` VALUES (1241569104817590306, 1210187481379528705, 'guns-cloud-workflow$task$todo_list');
INSERT INTO `guns_sys_permission_resource` VALUES (1241569104817590307, 1210187481379528705, 'guns-cloud-workflow$deploy_process$delete');
INSERT INTO `guns_sys_permission_resource` VALUES (1241569104817590308, 1210187481379528705, 'guns-cloud-workflow$deploy_process$import_process');
INSERT INTO `guns_sys_permission_resource` VALUES (1241569104817590309, 1210187481379528705, 'guns-cloud-workflow$deploy_process$export');
INSERT INTO `guns_sys_permission_resource` VALUES (1241569104817590310, 1210187481379528705, 'guns-cloud-workflow$deploy_process$download_zip');
INSERT INTO `guns_sys_permission_resource` VALUES (1241569104817590311, 1210187481379528705, 'guns-cloud-workflow$deploy_process$suspend');
INSERT INTO `guns_sys_permission_resource` VALUES (1241569104825978882, 1210187481379528705, 'guns-cloud-workflow$deploy_process$mapping');
INSERT INTO `guns_sys_permission_resource` VALUES (1241569104825978883, 1210187481379528705, 'guns-cloud-workflow$deploy_process$view_pic');
INSERT INTO `guns_sys_permission_resource` VALUES (1241569104825978884, 1210187481379528705, 'guns-cloud-workflow$deploy_process$active');
INSERT INTO `guns_sys_permission_resource` VALUES (1241569104825978885, 1210187481379528705, 'guns-cloud-workflow$deploy_process$list');
INSERT INTO `guns_sys_permission_resource` VALUES (1241569104825978886, 1210187481379528705, 'guns-cloud-workflow$deploy_process$version_list');
INSERT INTO `guns_sys_permission_resource` VALUES (1309851907484913666, 1184285448379506690, 'guns-cloud-auth$auth_client$add');
INSERT INTO `guns_sys_permission_resource` VALUES (1309851907493302273, 1184285448379506690, 'guns-cloud-auth$auth_client$app_select_list');
INSERT INTO `guns_sys_permission_resource` VALUES (1309851907493302274, 1184285448379506690, 'guns-cloud-auth$auth_client$delete');
INSERT INTO `guns_sys_permission_resource` VALUES (1309851907493302275, 1184285448379506690, 'guns-cloud-auth$auth_client$detail');
INSERT INTO `guns_sys_permission_resource` VALUES (1309851907493302276, 1184285448379506690, 'guns-cloud-auth$auth_client$edit');
INSERT INTO `guns_sys_permission_resource` VALUES (1309851907497496578, 1184285448379506690, 'guns-cloud-auth$auth_client$generate_key');
INSERT INTO `guns_sys_permission_resource` VALUES (1309851907497496579, 1184285448379506690, 'guns-cloud-auth$auth_client$page_list');
INSERT INTO `guns_sys_permission_resource` VALUES (1309851907501690882, 1184285448379506690, 'guns-cloud-auth$auth_login_log$detail');
INSERT INTO `guns_sys_permission_resource` VALUES (1309851907501690883, 1184285448379506690, 'guns-cloud-auth$auth_login_log$page_list');
INSERT INTO `guns_sys_permission_resource` VALUES (1309851907501690884, 1184285448379506690, 'guns-cloud-auth$auth_manager_menu$client_manager');
INSERT INTO `guns_sys_permission_resource` VALUES (1309851907501690885, 1184285448379506690, 'guns-cloud-auth$auth_manager_menu$god_key');
INSERT INTO `guns_sys_permission_resource` VALUES (1309851907501690886, 1184285448379506690, 'guns-cloud-auth$auth_manager_menu$login_log');
INSERT INTO `guns_sys_permission_resource` VALUES (1309851907501690887, 1184285448379506690, 'guns-cloud-auth$auth_manager_menu$res_maintain');
INSERT INTO `guns_sys_permission_resource` VALUES (1309851907501690888, 1184285448379506690, 'guns-cloud-auth$auth_manager_menu$safety_audit');
INSERT INTO `guns_sys_permission_resource` VALUES (1309851907501690889, 1184285448379506690, 'guns-cloud-auth$auth_manager_menu$session_manage');
INSERT INTO `guns_sys_permission_resource` VALUES (1309851907501690890, 1184285448379506690, 'guns-cloud-auth$auth_manager_menu$sso_app');
INSERT INTO `guns_sys_permission_resource` VALUES (1309851907510079490, 1184285448379506690, 'guns-cloud-auth$gateway_actuator$add_route');
INSERT INTO `guns_sys_permission_resource` VALUES (1309851907510079491, 1184285448379506690, 'guns-cloud-auth$gateway_actuator$delete_route');
INSERT INTO `guns_sys_permission_resource` VALUES (1309851907510079492, 1184285448379506690, 'guns-cloud-auth$gateway_actuator$globalfilters');
INSERT INTO `guns_sys_permission_resource` VALUES (1309851907510079493, 1184285448379506690, 'guns-cloud-auth$gateway_actuator$refresh_route');
INSERT INTO `guns_sys_permission_resource` VALUES (1309851907510079494, 1184285448379506690, 'guns-cloud-auth$gateway_actuator$routefilters');
INSERT INTO `guns_sys_permission_resource` VALUES (1309851907510079495, 1184285448379506690, 'guns-cloud-auth$gateway_actuator$route_info_by_id');
INSERT INTO `guns_sys_permission_resource` VALUES (1309851907510079496, 1184285448379506690, 'guns-cloud-auth$gateway_actuator$route_list');
INSERT INTO `guns_sys_permission_resource` VALUES (1309851907510079497, 1184285448379506690, 'guns-cloud-auth$god_key_manager$checked_status');
INSERT INTO `guns_sys_permission_resource` VALUES (1309851907518468098, 1184285448379506690, 'guns-cloud-auth$god_key_manager$get_god_key');
INSERT INTO `guns_sys_permission_resource` VALUES (1309851907518468099, 1184285448379506690, 'guns-cloud-auth$res_cache$add_res_cache');
INSERT INTO `guns_sys_permission_resource` VALUES (1309851907518468100, 1184285448379506690, 'guns-cloud-auth$res_cache$edit_res_cache');
INSERT INTO `guns_sys_permission_resource` VALUES (1309851907518468101, 1184285448379506690, 'guns-cloud-auth$res_cache$get_app_select');
INSERT INTO `guns_sys_permission_resource` VALUES (1309851907518468102, 1184285448379506690, 'guns-cloud-auth$res_cache$get_detail');
INSERT INTO `guns_sys_permission_resource` VALUES (1309851907518468103, 1184285448379506690, 'guns-cloud-auth$res_cache$get_http_request_select');
INSERT INTO `guns_sys_permission_resource` VALUES (1309851907522662401, 1184285448379506690, 'guns-cloud-auth$res_cache$get_res_cache_list');
INSERT INTO `guns_sys_permission_resource` VALUES (1309851907522662402, 1184285448379506690, 'guns-cloud-auth$res_cache$get_res_module_select');
INSERT INTO `guns_sys_permission_resource` VALUES (1309851907522662403, 1184285448379506690, 'guns-cloud-auth$res_cache$remove_res_cache');
INSERT INTO `guns_sys_permission_resource` VALUES (1309851907522662404, 1184285448379506690, 'guns-cloud-auth$session_manager$forced_offline');
INSERT INTO `guns_sys_permission_resource` VALUES (1309851907522662405, 1184285448379506690, 'guns-cloud-auth$session_manager$page_list');
INSERT INTO `guns_sys_permission_resource` VALUES (1309851907522662406, 1184285448379506690, 'guns-cloud-system$api_menu_flag$api');
INSERT INTO `guns_sys_permission_resource` VALUES (1309851907522662407, 1184285448379506690, 'guns-cloud-system$api_menu_flag$swagger');
INSERT INTO `guns_sys_permission_resource` VALUES (1309851907526856706, 1184285448379506690, 'guns-cloud-system$app$add');
INSERT INTO `guns_sys_permission_resource` VALUES (1309851907526856707, 1184285448379506690, 'guns-cloud-system$app$change_status');
INSERT INTO `guns_sys_permission_resource` VALUES (1309851907526856708, 1184285448379506690, 'guns-cloud-system$app$delete');
INSERT INTO `guns_sys_permission_resource` VALUES (1309851907526856709, 1184285448379506690, 'guns-cloud-system$app$detail');
INSERT INTO `guns_sys_permission_resource` VALUES (1309851907526856710, 1184285448379506690, 'guns-cloud-system$app$get_app_select');
INSERT INTO `guns_sys_permission_resource` VALUES (1309851907526856711, 1184285448379506690, 'guns-cloud-system$app$get_app_select_by_current_user');
INSERT INTO `guns_sys_permission_resource` VALUES (1309851907526856712, 1184285448379506690, 'guns-cloud-system$app$list');
INSERT INTO `guns_sys_permission_resource` VALUES (1309851907526856713, 1184285448379506690, 'guns-cloud-system$app$uptate');
INSERT INTO `guns_sys_permission_resource` VALUES (1309851907526856714, 1184285448379506690, 'guns-cloud-system$authority_menu_flag$authority_menu');
INSERT INTO `guns_sys_permission_resource` VALUES (1309851907526856715, 1184285448379506690, 'guns-cloud-system$authority_menu_flag$menu_manager');
INSERT INTO `guns_sys_permission_resource` VALUES (1309851907526856716, 1184285448379506690, 'guns-cloud-system$authority_menu_flag$permission_manager');
INSERT INTO `guns_sys_permission_resource` VALUES (1309851907526856717, 1184285448379506690, 'guns-cloud-system$authority_menu_flag$resource_manager');
INSERT INTO `guns_sys_permission_resource` VALUES (1309851907526856718, 1184285448379506690, 'guns-cloud-system$authority_menu_flag$role_manager');
INSERT INTO `guns_sys_permission_resource` VALUES (1309851907535245313, 1184285448379506690, 'guns-cloud-system$config_menu_flag$configs');
INSERT INTO `guns_sys_permission_resource` VALUES (1309851907535245314, 1184285448379506690, 'guns-cloud-system$config_menu_flag$config_menu');
INSERT INTO `guns_sys_permission_resource` VALUES (1309851907535245315, 1184285448379506690, 'guns-cloud-system$database_info$add');
INSERT INTO `guns_sys_permission_resource` VALUES (1309851907535245316, 1184285448379506690, 'guns-cloud-system$database_info$delete');
INSERT INTO `guns_sys_permission_resource` VALUES (1309851907535245317, 1184285448379506690, 'guns-cloud-system$database_info$detail');
INSERT INTO `guns_sys_permission_resource` VALUES (1309851907535245318, 1184285448379506690, 'guns-cloud-system$database_info$edit');
INSERT INTO `guns_sys_permission_resource` VALUES (1309851907535245319, 1184285448379506690, 'guns-cloud-system$database_info$page');
INSERT INTO `guns_sys_permission_resource` VALUES (1309851907535245320, 1184285448379506690, 'guns-cloud-system$dict$add_dict_type');
INSERT INTO `guns_sys_permission_resource` VALUES (1309851907535245321, 1184285448379506690, 'guns-cloud-system$dict$check_code');
INSERT INTO `guns_sys_permission_resource` VALUES (1309851907535245322, 1184285448379506690, 'guns-cloud-system$dict$delete_dict');
INSERT INTO `guns_sys_permission_resource` VALUES (1309851907535245323, 1184285448379506690, 'guns-cloud-system$dict$get_dict_list');
INSERT INTO `guns_sys_permission_resource` VALUES (1309851907535245324, 1184285448379506690, 'guns-cloud-system$dict$get_dict_list_by_type_code');
INSERT INTO `guns_sys_permission_resource` VALUES (1309851907535245325, 1184285448379506690, 'guns-cloud-system$dict$get_dict_page');
INSERT INTO `guns_sys_permission_resource` VALUES (1309851907535245326, 1184285448379506690, 'guns-cloud-system$dict$get_dict_tree_list');
INSERT INTO `guns_sys_permission_resource` VALUES (1309851907535245327, 1184285448379506690, 'guns-cloud-system$dict$get_list_by_type_code_and_PC_ode');
INSERT INTO `guns_sys_permission_resource` VALUES (1309851907543633921, 1184285448379506690, 'guns-cloud-system$dict$update_dict');
INSERT INTO `guns_sys_permission_resource` VALUES (1309851907543633922, 1184285448379506690, 'guns-cloud-system$dict$update_dict_status');
INSERT INTO `guns_sys_permission_resource` VALUES (1309851907543633923, 1184285448379506690, 'guns-cloud-system$dict_type$add_dict_type');
INSERT INTO `guns_sys_permission_resource` VALUES (1309851907543633924, 1184285448379506690, 'guns-cloud-system$dict_type$check_code');
INSERT INTO `guns_sys_permission_resource` VALUES (1309851907543633925, 1184285448379506690, 'guns-cloud-system$dict_type$delete_dict_type');
INSERT INTO `guns_sys_permission_resource` VALUES (1309851907543633926, 1184285448379506690, 'guns-cloud-system$dict_type$get_dict_type_list');
INSERT INTO `guns_sys_permission_resource` VALUES (1309851907543633927, 1184285448379506690, 'guns-cloud-system$dict_type$update_dict_type');
INSERT INTO `guns_sys_permission_resource` VALUES (1309851907543633928, 1184285448379506690, 'guns-cloud-system$dict_type$update_status');
INSERT INTO `guns_sys_permission_resource` VALUES (1309851907543633929, 1184285448379506690, 'guns-cloud-system$ent_company$add');
INSERT INTO `guns_sys_permission_resource` VALUES (1309851907543633930, 1184285448379506690, 'guns-cloud-system$ent_company$change_status');
INSERT INTO `guns_sys_permission_resource` VALUES (1309851907543633931, 1184285448379506690, 'guns-cloud-system$ent_company$delete');
INSERT INTO `guns_sys_permission_resource` VALUES (1309851907543633932, 1184285448379506690, 'guns-cloud-system$ent_company$detail');
INSERT INTO `guns_sys_permission_resource` VALUES (1309851907543633933, 1184285448379506690, 'guns-cloud-system$ent_company$get_app_list');
INSERT INTO `guns_sys_permission_resource` VALUES (1309851907543633934, 1184285448379506690, 'guns-cloud-system$ent_company$query_comp_tree');
INSERT INTO `guns_sys_permission_resource` VALUES (1309851907543633935, 1184285448379506690, 'guns-cloud-system$ent_company$query_list_page');
INSERT INTO `guns_sys_permission_resource` VALUES (1309851907543633936, 1184285448379506690, 'guns-cloud-system$ent_company$save_comp_app');
INSERT INTO `guns_sys_permission_resource` VALUES (1309851907543633937, 1184285448379506690, 'guns-cloud-system$ent_company$update');
INSERT INTO `guns_sys_permission_resource` VALUES (1309851907552022529, 1184285448379506690, 'guns-cloud-system$ent_comp_app$add');
INSERT INTO `guns_sys_permission_resource` VALUES (1309851907552022530, 1184285448379506690, 'guns-cloud-system$ent_comp_app$delete');
INSERT INTO `guns_sys_permission_resource` VALUES (1309851907552022531, 1184285448379506690, 'guns-cloud-system$ent_comp_app$query_detail');
INSERT INTO `guns_sys_permission_resource` VALUES (1309851907552022532, 1184285448379506690, 'guns-cloud-system$ent_comp_app$query_list');
INSERT INTO `guns_sys_permission_resource` VALUES (1309851907552022533, 1184285448379506690, 'guns-cloud-system$ent_comp_app$query_list_page');
INSERT INTO `guns_sys_permission_resource` VALUES (1309851907552022534, 1184285448379506690, 'guns-cloud-system$ent_comp_app$update');
INSERT INTO `guns_sys_permission_resource` VALUES (1309851907552022535, 1184285448379506690, 'guns-cloud-system$ent_dept$add');
INSERT INTO `guns_sys_permission_resource` VALUES (1309851907552022536, 1184285448379506690, 'guns-cloud-system$ent_dept$change_status');
INSERT INTO `guns_sys_permission_resource` VALUES (1309851907556216834, 1184285448379506690, 'guns-cloud-system$ent_dept$delete');
INSERT INTO `guns_sys_permission_resource` VALUES (1309851907556216835, 1184285448379506690, 'guns-cloud-system$ent_dept$detail');
INSERT INTO `guns_sys_permission_resource` VALUES (1309851907556216836, 1184285448379506690, 'guns-cloud-system$ent_dept$query_dept_tree');
INSERT INTO `guns_sys_permission_resource` VALUES (1309851907556216837, 1184285448379506690, 'guns-cloud-system$ent_dept$query_list_page');
INSERT INTO `guns_sys_permission_resource` VALUES (1309851907556216838, 1184285448379506690, 'guns-cloud-system$ent_dept$update');
INSERT INTO `guns_sys_permission_resource` VALUES (1309851907556216839, 1184285448379506690, 'guns-cloud-system$ent_duty$add');
INSERT INTO `guns_sys_permission_resource` VALUES (1309851907556216840, 1184285448379506690, 'guns-cloud-system$ent_duty$change_status');
INSERT INTO `guns_sys_permission_resource` VALUES (1309851907556216841, 1184285448379506690, 'guns-cloud-system$ent_duty$delete');
INSERT INTO `guns_sys_permission_resource` VALUES (1309851907556216842, 1184285448379506690, 'guns-cloud-system$ent_duty$detail');
INSERT INTO `guns_sys_permission_resource` VALUES (1309851907560411138, 1184285448379506690, 'guns-cloud-system$ent_duty$query_list');
INSERT INTO `guns_sys_permission_resource` VALUES (1309851907560411139, 1184285448379506690, 'guns-cloud-system$ent_duty$query_list_page');
INSERT INTO `guns_sys_permission_resource` VALUES (1309851907560411140, 1184285448379506690, 'guns-cloud-system$ent_duty$update');
INSERT INTO `guns_sys_permission_resource` VALUES (1309851907560411141, 1184285448379506690, 'guns-cloud-system$ent_menu_flag$company');
INSERT INTO `guns_sys_permission_resource` VALUES (1309851907560411142, 1184285448379506690, 'guns-cloud-system$ent_menu_flag$dept');
INSERT INTO `guns_sys_permission_resource` VALUES (1309851907560411143, 1184285448379506690, 'guns-cloud-system$ent_menu_flag$duty');
INSERT INTO `guns_sys_permission_resource` VALUES (1309851907560411144, 1184285448379506690, 'guns-cloud-system$ent_menu_flag$personal');
INSERT INTO `guns_sys_permission_resource` VALUES (1309851907560411145, 1184285448379506690, 'guns-cloud-system$ent_menu_flag$system');
INSERT INTO `guns_sys_permission_resource` VALUES (1309851907560411146, 1184285448379506690, 'guns-cloud-system$ent_user$add');
INSERT INTO `guns_sys_permission_resource` VALUES (1309851907560411147, 1184285448379506690, 'guns-cloud-system$ent_user$change_status');
INSERT INTO `guns_sys_permission_resource` VALUES (1309851907564605441, 1184285448379506690, 'guns-cloud-system$ent_user$delete');
INSERT INTO `guns_sys_permission_resource` VALUES (1309851907564605442, 1184285448379506690, 'guns-cloud-system$ent_user$detail');
INSERT INTO `guns_sys_permission_resource` VALUES (1309851907564605443, 1184285448379506690, 'guns-cloud-system$ent_user$get_company_select');
INSERT INTO `guns_sys_permission_resource` VALUES (1309851907564605444, 1184285448379506690, 'guns-cloud-system$ent_user$get_current_user');
INSERT INTO `guns_sys_permission_resource` VALUES (1309851907564605445, 1184285448379506690, 'guns-cloud-system$ent_user$get_dept_select');
INSERT INTO `guns_sys_permission_resource` VALUES (1309851907564605446, 1184285448379506690, 'guns-cloud-system$ent_user$get_duty_select');
INSERT INTO `guns_sys_permission_resource` VALUES (1309851907564605447, 1184285448379506690, 'guns-cloud-system$ent_user$get_roles');
INSERT INTO `guns_sys_permission_resource` VALUES (1309851907564605448, 1184285448379506690, 'guns-cloud-system$ent_user$query_info_by_dict_type');
INSERT INTO `guns_sys_permission_resource` VALUES (1309851907564605449, 1184285448379506690, 'guns-cloud-system$ent_user$query_list_page');
INSERT INTO `guns_sys_permission_resource` VALUES (1309851907564605450, 1184285448379506690, 'guns-cloud-system$ent_user$reset_password');
INSERT INTO `guns_sys_permission_resource` VALUES (1309851907564605451, 1184285448379506690, 'guns-cloud-system$ent_user$save_current_user');
INSERT INTO `guns_sys_permission_resource` VALUES (1309851907564605452, 1184285448379506690, 'guns-cloud-system$ent_user$save_roles');
INSERT INTO `guns_sys_permission_resource` VALUES (1309851907564605453, 1184285448379506690, 'guns-cloud-system$ent_user$update');
INSERT INTO `guns_sys_permission_resource` VALUES (1309851907564605454, 1184285448379506690, 'guns-cloud-system$ent_user$update_password');
INSERT INTO `guns_sys_permission_resource` VALUES (1309851907564605455, 1184285448379506690, 'guns-cloud-system$ent_user_account$add');
INSERT INTO `guns_sys_permission_resource` VALUES (1309851907564605456, 1184285448379506690, 'guns-cloud-system$ent_user_account$delete');
INSERT INTO `guns_sys_permission_resource` VALUES (1309851907564605457, 1184285448379506690, 'guns-cloud-system$ent_user_account$query_detail');
INSERT INTO `guns_sys_permission_resource` VALUES (1309851907564605458, 1184285448379506690, 'guns-cloud-system$ent_user_account$query_list');
INSERT INTO `guns_sys_permission_resource` VALUES (1309851907572994050, 1184285448379506690, 'guns-cloud-system$ent_user_account$query_list_page');
INSERT INTO `guns_sys_permission_resource` VALUES (1309851907572994051, 1184285448379506690, 'guns-cloud-system$ent_user_account$update');
INSERT INTO `guns_sys_permission_resource` VALUES (1309851907572994052, 1184285448379506690, 'guns-cloud-system$ent_user_dept$add');
INSERT INTO `guns_sys_permission_resource` VALUES (1309851907572994053, 1184285448379506690, 'guns-cloud-system$ent_user_dept$delete');
INSERT INTO `guns_sys_permission_resource` VALUES (1309851907572994054, 1184285448379506690, 'guns-cloud-system$ent_user_dept$query_detail');
INSERT INTO `guns_sys_permission_resource` VALUES (1309851907572994055, 1184285448379506690, 'guns-cloud-system$ent_user_dept$query_list');
INSERT INTO `guns_sys_permission_resource` VALUES (1309851907572994056, 1184285448379506690, 'guns-cloud-system$ent_user_dept$query_list_page');
INSERT INTO `guns_sys_permission_resource` VALUES (1309851907572994057, 1184285448379506690, 'guns-cloud-system$ent_user_dept$update');
INSERT INTO `guns_sys_permission_resource` VALUES (1309851907572994058, 1184285448379506690, 'guns-cloud-system$file$file_access');
INSERT INTO `guns_sys_permission_resource` VALUES (1309851907572994059, 1184285448379506690, 'guns-cloud-system$file$preview');
INSERT INTO `guns_sys_permission_resource` VALUES (1309851907572994060, 1184285448379506690, 'guns-cloud-system$file$upload');
INSERT INTO `guns_sys_permission_resource` VALUES (1309851907577188354, 1184285448379506690, 'guns-cloud-system$file_menu_flag$files');
INSERT INTO `guns_sys_permission_resource` VALUES (1309851907577188355, 1184285448379506690, 'guns-cloud-system$log_menu_flag$biz_menu');
INSERT INTO `guns_sys_permission_resource` VALUES (1309851907577188356, 1184285448379506690, 'guns-cloud-system$log_menu_flag$login_menu');
INSERT INTO `guns_sys_permission_resource` VALUES (1309851907577188357, 1184285448379506690, 'guns-cloud-system$log_menu_flag$logs_menu');
INSERT INTO `guns_sys_permission_resource` VALUES (1309851907577188358, 1184285448379506690, 'guns-cloud-system$menu$add_menu');
INSERT INTO `guns_sys_permission_resource` VALUES (1309851907577188359, 1184285448379506690, 'guns-cloud-system$menu$delete_menu');
INSERT INTO `guns_sys_permission_resource` VALUES (1309851907577188360, 1184285448379506690, 'guns-cloud-system$menu$edit_menu');
INSERT INTO `guns_sys_permission_resource` VALUES (1309851907577188361, 1184285448379506690, 'guns-cloud-system$menu$enable_menu');
INSERT INTO `guns_sys_permission_resource` VALUES (1309851907577188362, 1184285448379506690, 'guns-cloud-system$menu$get_left_menu_list');
INSERT INTO `guns_sys_permission_resource` VALUES (1309851907577188363, 1184285448379506690, 'guns-cloud-system$menu$get_menu_detail');
INSERT INTO `guns_sys_permission_resource` VALUES (1309851907577188364, 1184285448379506690, 'guns-cloud-system$menu$get_select_menu_tree_list');
INSERT INTO `guns_sys_permission_resource` VALUES (1309851907581382658, 1184285448379506690, 'guns-cloud-system$menu$list_menu');
INSERT INTO `guns_sys_permission_resource` VALUES (1309851907581382659, 1184285448379506690, 'guns-cloud-system$monitor_menu_flag$druid_menu');
INSERT INTO `guns_sys_permission_resource` VALUES (1309851907581382660, 1184285448379506690, 'guns-cloud-system$monitor_menu_flag$hard_info_menu');
INSERT INTO `guns_sys_permission_resource` VALUES (1309851907581382661, 1184285448379506690, 'guns-cloud-system$monitor_menu_flag$sys_monitor');
INSERT INTO `guns_sys_permission_resource` VALUES (1309851907581382662, 1184285448379506690, 'guns-cloud-system$permission$add_permission');
INSERT INTO `guns_sys_permission_resource` VALUES (1309851907581382663, 1184285448379506690, 'guns-cloud-system$permission$cancel_permission_bind_resource');
INSERT INTO `guns_sys_permission_resource` VALUES (1309851907581382664, 1184285448379506690, 'guns-cloud-system$permission$get_app_button_status');
INSERT INTO `guns_sys_permission_resource` VALUES (1309851907581382665, 1184285448379506690, 'guns-cloud-system$permission$get_permission_list');
INSERT INTO `guns_sys_permission_resource` VALUES (1309851907581382666, 1184285448379506690, 'guns-cloud-system$permission$get_permission_list4_level');
INSERT INTO `guns_sys_permission_resource` VALUES (1309851907581382667, 1184285448379506690, 'guns-cloud-system$permission$get_permission_page_list');
INSERT INTO `guns_sys_permission_resource` VALUES (1309851907581382668, 1184285448379506690, 'guns-cloud-system$permission$get_permission_resource_list');
INSERT INTO `guns_sys_permission_resource` VALUES (1309851907581382669, 1184285448379506690, 'guns-cloud-system$permission$get_permission_resource_list_detail');
INSERT INTO `guns_sys_permission_resource` VALUES (1309851907581382670, 1184285448379506690, 'guns-cloud-system$permission$get_permission_tree');
INSERT INTO `guns_sys_permission_resource` VALUES (1309851907581382671, 1184285448379506690, 'guns-cloud-system$permission$get_resource_list4_permission');
INSERT INTO `guns_sys_permission_resource` VALUES (1309851907581382672, 1184285448379506690, 'guns-cloud-system$permission$permission_bind_resource');
INSERT INTO `guns_sys_permission_resource` VALUES (1309851907581382673, 1184285448379506690, 'guns-cloud-system$permission$remove_permission');
INSERT INTO `guns_sys_permission_resource` VALUES (1309851907581382674, 1184285448379506690, 'guns-cloud-system$permission$set_permission_status');
INSERT INTO `guns_sys_permission_resource` VALUES (1309851907581382675, 1184285448379506690, 'guns-cloud-system$resource$get_resource_select_list');
INSERT INTO `guns_sys_permission_resource` VALUES (1309851907581382676, 1184285448379506690, 'guns-cloud-system$resource$resource_list');
INSERT INTO `guns_sys_permission_resource` VALUES (1309851907581382677, 1184285448379506690, 'guns-cloud-system$role$add_role');
INSERT INTO `guns_sys_permission_resource` VALUES (1309851907581382678, 1184285448379506690, 'guns-cloud-system$role$assign_permission');
INSERT INTO `guns_sys_permission_resource` VALUES (1309851907581382679, 1184285448379506690, 'guns-cloud-system$role$delete_role');
INSERT INTO `guns_sys_permission_resource` VALUES (1309851907581382680, 1184285448379506690, 'guns-cloud-system$role$find_all_role');
INSERT INTO `guns_sys_permission_resource` VALUES (1309851907581382681, 1184285448379506690, 'guns-cloud-system$role$find_permissions');
INSERT INTO `guns_sys_permission_resource` VALUES (1309851907581382682, 1184285448379506690, 'guns-cloud-system$role$find_role_all_permissions');
INSERT INTO `guns_sys_permission_resource` VALUES (1309851907589771265, 1184285448379506690, 'guns-cloud-system$role$find_role_detail');
INSERT INTO `guns_sys_permission_resource` VALUES (1309851907589771266, 1184285448379506690, 'guns-cloud-system$role$find_role_page');
INSERT INTO `guns_sys_permission_resource` VALUES (1309851907589771267, 1184285448379506690, 'guns-cloud-system$role$get_all_res_permissions');
INSERT INTO `guns_sys_permission_resource` VALUES (1309851907589771268, 1184285448379506690, 'guns-cloud-system$role$get_all_res_permission_urls');
INSERT INTO `guns_sys_permission_resource` VALUES (1309851907589771269, 1184285448379506690, 'guns-cloud-system$role$select_role_by_account_id');
INSERT INTO `guns_sys_permission_resource` VALUES (1309851907589771270, 1184285448379506690, 'guns-cloud-system$role$set_status');
INSERT INTO `guns_sys_permission_resource` VALUES (1309851907589771271, 1184285448379506690, 'guns-cloud-system$role$update_role');
INSERT INTO `guns_sys_permission_resource` VALUES (1309851907589771272, 1184285448379506690, 'guns-cloud-system$role$validate_code');
INSERT INTO `guns_sys_permission_resource` VALUES (1309851907589771273, 1184285448379506690, 'guns-cloud-system$role$validate_name');
INSERT INTO `guns_sys_permission_resource` VALUES (1309851907589771274, 1184285448379506690, 'guns-cloud-system$system$get_user_info');
INSERT INTO `guns_sys_permission_resource` VALUES (1309851907589771275, 1184285448379506690, 'guns-cloud-system$system_menu_flag$app_manager');
INSERT INTO `guns_sys_permission_resource` VALUES (1309851907589771276, 1184285448379506690, 'guns-cloud-system$system_menu_flag$dic_manager');
INSERT INTO `guns_sys_permission_resource` VALUES (1309851907593965570, 1184285448379506690, 'guns-cloud-system$system_menu_flag$system');
INSERT INTO `guns_sys_permission_resource` VALUES (1309851907593965571, 1184285448379506690, 'guns-cloud-system$sys_button$add');
INSERT INTO `guns_sys_permission_resource` VALUES (1309851907593965572, 1184285448379506690, 'guns-cloud-system$sys_button$change_status');
INSERT INTO `guns_sys_permission_resource` VALUES (1309851907593965573, 1184285448379506690, 'guns-cloud-system$sys_button$delete');
INSERT INTO `guns_sys_permission_resource` VALUES (1309851907593965574, 1184285448379506690, 'guns-cloud-system$sys_button$get_detail');
INSERT INTO `guns_sys_permission_resource` VALUES (1309851907593965575, 1184285448379506690, 'guns-cloud-system$sys_button$get_resources');
INSERT INTO `guns_sys_permission_resource` VALUES (1309851907593965576, 1184285448379506690, 'guns-cloud-system$sys_button$query_list_page');
INSERT INTO `guns_sys_permission_resource` VALUES (1309851907593965577, 1184285448379506690, 'guns-cloud-system$sys_button$update');
INSERT INTO `guns_sys_permission_resource` VALUES (1309851907593965578, 1184285448379506690, 'guns-cloud-system$sys_log$op_log_delete');
INSERT INTO `guns_sys_permission_resource` VALUES (1309851907593965579, 1184285448379506690, 'guns-cloud-system$sys_log$op_log_page');
INSERT INTO `guns_sys_permission_resource` VALUES (1309851907598159873, 1184285448379506690, 'guns-cloud-system$sys_machine$query');
INSERT INTO `guns_sys_permission_resource` VALUES (1309851907598159874, 1184285448379506690, 'guns-cloud-system$tenant_info$add');
INSERT INTO `guns_sys_permission_resource` VALUES (1309851907598159875, 1184285448379506690, 'guns-cloud-system$tenant_info$delete');
INSERT INTO `guns_sys_permission_resource` VALUES (1309851907598159876, 1184285448379506690, 'guns-cloud-system$tenant_info$detail');
INSERT INTO `guns_sys_permission_resource` VALUES (1309851907598159877, 1184285448379506690, 'guns-cloud-system$tenant_info$edit');
INSERT INTO `guns_sys_permission_resource` VALUES (1309851907598159878, 1184285448379506690, 'guns-cloud-system$tenant_info$list_tenants');
INSERT INTO `guns_sys_permission_resource` VALUES (1309851907598159879, 1184285448379506690, 'guns-cloud-system$tenant_info$page');
INSERT INTO `guns_sys_permission_resource` VALUES (1309851907598159880, 1184285448379506690, 'guns-cloud-workflow$common$role_list');
INSERT INTO `guns_sys_permission_resource` VALUES (1309851907598159881, 1184285448379506690, 'guns-cloud-workflow$common$user_list_by_role_id');
INSERT INTO `guns_sys_permission_resource` VALUES (1309851907598159882, 1184285448379506690, 'guns-cloud-workflow$deploy_process$active');
INSERT INTO `guns_sys_permission_resource` VALUES (1309851907598159883, 1184285448379506690, 'guns-cloud-workflow$deploy_process$delete');
INSERT INTO `guns_sys_permission_resource` VALUES (1309851907598159884, 1184285448379506690, 'guns-cloud-workflow$deploy_process$download_zip');
INSERT INTO `guns_sys_permission_resource` VALUES (1309851907598159885, 1184285448379506690, 'guns-cloud-workflow$deploy_process$export');
INSERT INTO `guns_sys_permission_resource` VALUES (1309851907602354178, 1184285448379506690, 'guns-cloud-workflow$deploy_process$import_process');
INSERT INTO `guns_sys_permission_resource` VALUES (1309851907602354179, 1184285448379506690, 'guns-cloud-workflow$deploy_process$list');
INSERT INTO `guns_sys_permission_resource` VALUES (1309851907602354180, 1184285448379506690, 'guns-cloud-workflow$deploy_process$mapping');
INSERT INTO `guns_sys_permission_resource` VALUES (1309851907602354181, 1184285448379506690, 'guns-cloud-workflow$deploy_process$suspend');
INSERT INTO `guns_sys_permission_resource` VALUES (1309851907602354182, 1184285448379506690, 'guns-cloud-workflow$deploy_process$version_list');
INSERT INTO `guns_sys_permission_resource` VALUES (1309851907602354183, 1184285448379506690, 'guns-cloud-workflow$deploy_process$view_pic');
INSERT INTO `guns_sys_permission_resource` VALUES (1309851907602354184, 1184285448379506690, 'guns-cloud-workflow$model$add_item');
INSERT INTO `guns_sys_permission_resource` VALUES (1309851907602354185, 1184285448379506690, 'guns-cloud-workflow$model$delete');
INSERT INTO `guns_sys_permission_resource` VALUES (1309851907602354186, 1184285448379506690, 'guns-cloud-workflow$model$deploy');
INSERT INTO `guns_sys_permission_resource` VALUES (1309851907602354187, 1184285448379506690, 'guns-cloud-workflow$model$list');
INSERT INTO `guns_sys_permission_resource` VALUES (1309851907602354188, 1184285448379506690, 'guns-cloud-workflow$model$pre_view');
INSERT INTO `guns_sys_permission_resource` VALUES (1309851907602354189, 1184285448379506690, 'guns-cloud-workflow$monitor_process$active');
INSERT INTO `guns_sys_permission_resource` VALUES (1309851907606548482, 1184285448379506690, 'guns-cloud-workflow$monitor_process$change_assignee');
INSERT INTO `guns_sys_permission_resource` VALUES (1309851907606548483, 1184285448379506690, 'guns-cloud-workflow$monitor_process$end');
INSERT INTO `guns_sys_permission_resource` VALUES (1309851907606548484, 1184285448379506690, 'guns-cloud-workflow$monitor_process$end_list');
INSERT INTO `guns_sys_permission_resource` VALUES (1309851907606548485, 1184285448379506690, 'guns-cloud-workflow$monitor_process$run_list');
INSERT INTO `guns_sys_permission_resource` VALUES (1309851907606548486, 1184285448379506690, 'guns-cloud-workflow$monitor_process$suspend');
INSERT INTO `guns_sys_permission_resource` VALUES (1309851907606548487, 1184285448379506690, 'guns-cloud-workflow$my_leave$add_item');
INSERT INTO `guns_sys_permission_resource` VALUES (1309851907606548488, 1184285448379506690, 'guns-cloud-workflow$my_leave$batch_delete_by_ids');
INSERT INTO `guns_sys_permission_resource` VALUES (1309851907606548489, 1184285448379506690, 'guns-cloud-workflow$my_leave$delete');
INSERT INTO `guns_sys_permission_resource` VALUES (1309851907606548490, 1184285448379506690, 'guns-cloud-workflow$my_leave$detail');
INSERT INTO `guns_sys_permission_resource` VALUES (1309851907606548491, 1184285448379506690, 'guns-cloud-workflow$my_leave$get_leaves');
INSERT INTO `guns_sys_permission_resource` VALUES (1309851907606548492, 1184285448379506690, 'guns-cloud-workflow$my_leave$list');
INSERT INTO `guns_sys_permission_resource` VALUES (1309851907606548493, 1184285448379506690, 'guns-cloud-workflow$task$candidate_list');
INSERT INTO `guns_sys_permission_resource` VALUES (1309851907610742785, 1184285448379506690, 'guns-cloud-workflow$task$done_list');
INSERT INTO `guns_sys_permission_resource` VALUES (1309851907610742786, 1184285448379506690, 'guns-cloud-workflow$task$done_task');
INSERT INTO `guns_sys_permission_resource` VALUES (1309851907610742787, 1184285448379506690, 'guns-cloud-workflow$task$entrust');
INSERT INTO `guns_sys_permission_resource` VALUES (1309851907610742788, 1184285448379506690, 'guns-cloud-workflow$task$todo_list');
INSERT INTO `guns_sys_permission_resource` VALUES (1309851907610742789, 1184285448379506690, 'guns-cloud-workflow$task$view_task_detail');
INSERT INTO `guns_sys_permission_resource` VALUES (1309851907610742790, 1184285448379506690, 'guns-cloud-workflow$workflow_menus$alreadys');
INSERT INTO `guns_sys_permission_resource` VALUES (1309851907610742791, 1184285448379506690, 'guns-cloud-workflow$workflow_menus$apply_for');
INSERT INTO `guns_sys_permission_resource` VALUES (1309851907610742792, 1184285448379506690, 'guns-cloud-workflow$workflow_menus$flow_manager');
INSERT INTO `guns_sys_permission_resource` VALUES (1309851907610742793, 1184285448379506690, 'guns-cloud-workflow$workflow_menus$history_flow');
INSERT INTO `guns_sys_permission_resource` VALUES (1309851907610742794, 1184285448379506690, 'guns-cloud-workflow$workflow_menus$leave');
INSERT INTO `guns_sys_permission_resource` VALUES (1309851907610742795, 1184285448379506690, 'guns-cloud-workflow$workflow_menus$mode_manager');
INSERT INTO `guns_sys_permission_resource` VALUES (1309851907610742796, 1184285448379506690, 'guns-cloud-workflow$workflow_menus$running_flow');
INSERT INTO `guns_sys_permission_resource` VALUES (1309851907610742797, 1184285448379506690, 'guns-cloud-workflow$workflow_menus$tasks');
INSERT INTO `guns_sys_permission_resource` VALUES (1309851907610742798, 1184285448379506690, 'guns-cloud-workflow$workflow_menus$waitings');
INSERT INTO `guns_sys_permission_resource` VALUES (1309851907610742799, 1184285448379506690, 'guns-cloud-workflow$workflow_menus$workflow');

-- ----------------------------
-- Table structure for guns_sys_resource
-- ----------------------------
DROP TABLE IF EXISTS `guns_sys_resource`;
CREATE TABLE `guns_sys_resource`  (
  `resource_id` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '资源id',
  `app_code` varchar(64) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '应用编码',
  `project_code` varchar(64) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '项目编码',
  `class_name` varchar(100) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '类名称',
  `method_name` varchar(100) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '方法名称',
  `modular_code` varchar(100) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '资源模块编码',
  `modular_name` varchar(100) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '资源模块名称',
  `code` varchar(200) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '资源编码',
  `name` varchar(100) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '资源名称',
  `ip_address` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '资源初始化的服务器ip地址',
  `url` varchar(100) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '资源url',
  `http_method` varchar(10) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT 'http请求方法',
  `menu_flag` char(1) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '是否是方法(Y:页面 N:api)',
  `required_login_flag` char(1) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '是否需要登录(Y:是 N:否)',
  `required_permission_flag` char(1) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '是否需要鉴权标识(Y:是 N:否)',
  `create_user` bigint(20) NULL DEFAULT NULL COMMENT '创建人',
  `create_time` datetime(0) NULL DEFAULT NULL COMMENT '创建时间',
  `update_user` bigint(20) NULL DEFAULT NULL COMMENT '更新人',
  `update_time` datetime(0) NULL DEFAULT NULL COMMENT '更新时间',
  PRIMARY KEY (`resource_id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_general_ci COMMENT = '资源表' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of guns_sys_resource
-- ----------------------------
INSERT INTO `guns_sys_resource` VALUES ('guns-cloud-auth$auth_client$add', 'guns-cloud-auth', 'guns-cloud-auth', 'AuthClientController', 'add', 'AuthClient', '单点客户端管理', 'guns-cloud-auth$auth_client$add', '客户端新增', '192.168.11.1', '/guns-cloud-auth/authClient/add', 'POST', 'N', 'Y', 'Y', -100, '2020-09-26 10:04:21', NULL, NULL);
INSERT INTO `guns_sys_resource` VALUES ('guns-cloud-auth$auth_client$app_select_list', 'guns-cloud-auth', 'guns-cloud-auth', 'AuthClientController', 'appSelectList', 'AuthClient', '单点客户端管理', 'guns-cloud-auth$auth_client$app_select_list', '获取应用下拉列表', '192.168.11.1', '/guns-cloud-auth/authClient/getAppSelectList', 'GET', 'N', 'Y', 'Y', -100, '2020-09-26 10:04:21', NULL, NULL);
INSERT INTO `guns_sys_resource` VALUES ('guns-cloud-auth$auth_client$delete', 'guns-cloud-auth', 'guns-cloud-auth', 'AuthClientController', 'delete', 'AuthClient', '单点客户端管理', 'guns-cloud-auth$auth_client$delete', '客户端删除', '192.168.11.1', '/guns-cloud-auth/authClient/delete', 'POST', 'N', 'Y', 'Y', -100, '2020-09-26 10:04:21', NULL, NULL);
INSERT INTO `guns_sys_resource` VALUES ('guns-cloud-auth$auth_client$detail', 'guns-cloud-auth', 'guns-cloud-auth', 'AuthClientController', 'detail', 'AuthClient', '单点客户端管理', 'guns-cloud-auth$auth_client$detail', '客户端详情', '192.168.11.1', '/guns-cloud-auth/authClient/detail', 'GET', 'N', 'Y', 'Y', -100, '2020-09-26 10:04:21', NULL, NULL);
INSERT INTO `guns_sys_resource` VALUES ('guns-cloud-auth$auth_client$edit', 'guns-cloud-auth', 'guns-cloud-auth', 'AuthClientController', 'edit', 'AuthClient', '单点客户端管理', 'guns-cloud-auth$auth_client$edit', '客户端编辑', '192.168.11.1', '/guns-cloud-auth/authClient/edit', 'POST', 'N', 'Y', 'Y', -100, '2020-09-26 10:04:21', NULL, NULL);
INSERT INTO `guns_sys_resource` VALUES ('guns-cloud-auth$auth_client$generate_key', 'guns-cloud-auth', 'guns-cloud-auth', 'AuthClientController', 'generateKey', 'AuthClient', '单点客户端管理', 'guns-cloud-auth$auth_client$generate_key', '生成随机密钥', '192.168.11.1', '/guns-cloud-auth/authClient/generateKey', 'GET', 'N', 'Y', 'Y', -100, '2020-09-26 10:04:21', NULL, NULL);
INSERT INTO `guns_sys_resource` VALUES ('guns-cloud-auth$auth_client$page_list', 'guns-cloud-auth', 'guns-cloud-auth', 'AuthClientController', 'pageList', 'AuthClient', '单点客户端管理', 'guns-cloud-auth$auth_client$page_list', '获取单点客户端分页列表', '192.168.11.1', '/guns-cloud-auth/authClient/pageList', 'GET', 'N', 'Y', 'Y', -100, '2020-09-26 10:04:21', NULL, NULL);
INSERT INTO `guns_sys_resource` VALUES ('guns-cloud-auth$auth_login_log$detail', 'guns-cloud-auth', 'guns-cloud-auth', 'AuthLoginLogController', 'detail', 'AuthLoginLog', '登录日志管理', 'guns-cloud-auth$auth_login_log$detail', '日志详情', '192.168.11.1', '/guns-cloud-auth/authLoginLog/detail', 'GET', 'N', 'Y', 'Y', -100, '2020-09-26 10:04:21', NULL, NULL);
INSERT INTO `guns_sys_resource` VALUES ('guns-cloud-auth$auth_login_log$page_list', 'guns-cloud-auth', 'guns-cloud-auth', 'AuthLoginLogController', 'pageList', 'AuthLoginLog', '登录日志管理', 'guns-cloud-auth$auth_login_log$page_list', '查看日志列表（分页）', '192.168.11.1', '/guns-cloud-auth/authLoginLog/pageList', 'GET', 'N', 'Y', 'Y', -100, '2020-09-26 10:04:21', NULL, NULL);
INSERT INTO `guns_sys_resource` VALUES ('guns-cloud-auth$auth_manager_menu$client_manager', 'guns-cloud-auth', 'guns-cloud-auth', 'AuthManagerMenuController', 'clientManager', 'AuthManagerMenu', '权限管理所有菜单标识', 'guns-cloud-auth$auth_manager_menu$client_manager', '客户端管理', '192.168.11.1', '/guns-cloud-auth/authManagerMenuFlag/clientManager', '', 'Y', 'Y', 'Y', -100, '2020-09-26 10:04:12', NULL, NULL);
INSERT INTO `guns_sys_resource` VALUES ('guns-cloud-auth$auth_manager_menu$god_key', 'guns-cloud-auth', 'guns-cloud-auth', 'AuthManagerMenuController', 'godKey', 'AuthManagerMenu', '权限管理所有菜单标识', 'guns-cloud-auth$auth_manager_menu$god_key', '万能密码', '192.168.11.1', '/guns-cloud-auth/authManagerMenuFlag/godKey', '', 'Y', 'Y', 'Y', -100, '2020-09-26 10:04:12', NULL, NULL);
INSERT INTO `guns_sys_resource` VALUES ('guns-cloud-auth$auth_manager_menu$login_log', 'guns-cloud-auth', 'guns-cloud-auth', 'AuthManagerMenuController', 'loginLog', 'AuthManagerMenu', '权限管理所有菜单标识', 'guns-cloud-auth$auth_manager_menu$login_log', '登录日志', '192.168.11.1', '/guns-cloud-auth/authManagerMenuFlag/loginLog', '', 'Y', 'Y', 'Y', -100, '2020-09-26 10:04:12', NULL, NULL);
INSERT INTO `guns_sys_resource` VALUES ('guns-cloud-auth$auth_manager_menu$res_maintain', 'guns-cloud-auth', 'guns-cloud-auth', 'AuthManagerMenuController', 'resMaintain', 'AuthManagerMenu', '权限管理所有菜单标识', 'guns-cloud-auth$auth_manager_menu$res_maintain', '资源维护', '192.168.11.1', '/guns-cloud-auth/authManagerMenuFlag/resMaintain', '', 'Y', 'Y', 'Y', -100, '2020-09-26 10:04:12', NULL, NULL);
INSERT INTO `guns_sys_resource` VALUES ('guns-cloud-auth$auth_manager_menu$safety_audit', 'guns-cloud-auth', 'guns-cloud-auth', 'AuthManagerMenuController', 'safetyAudit', 'AuthManagerMenu', '权限管理所有菜单标识', 'guns-cloud-auth$auth_manager_menu$safety_audit', '安全审计', '192.168.11.1', '/guns-cloud-auth/authManagerMenuFlag/safetyAudit', '', 'Y', 'Y', 'Y', -100, '2020-09-26 10:04:12', NULL, NULL);
INSERT INTO `guns_sys_resource` VALUES ('guns-cloud-auth$auth_manager_menu$session_manage', 'guns-cloud-auth', 'guns-cloud-auth', 'AuthManagerMenuController', 'sessionManage', 'AuthManagerMenu', '权限管理所有菜单标识', 'guns-cloud-auth$auth_manager_menu$session_manage', '会话管理', '192.168.11.1', '/guns-cloud-auth/authManagerMenuFlag/sessionManage', '', 'Y', 'Y', 'Y', -100, '2020-09-26 10:04:12', NULL, NULL);
INSERT INTO `guns_sys_resource` VALUES ('guns-cloud-auth$auth_manager_menu$sso_app', 'guns-cloud-auth', 'guns-cloud-auth', 'AuthManagerMenuController', 'ssoApp', 'AuthManagerMenu', '权限管理所有菜单标识', 'guns-cloud-auth$auth_manager_menu$sso_app', '单点应用', '192.168.11.1', '/guns-cloud-auth/authManagerMenuFlag/ssoApp', '', 'Y', 'Y', 'Y', -100, '2020-09-26 10:04:12', NULL, NULL);
INSERT INTO `guns_sys_resource` VALUES ('guns-cloud-auth$gateway_actuator$add_route', 'guns-cloud-auth', 'guns-cloud-auth', 'GatewayActuatorController', 'addRoute', 'GatewayActuator', '网关信息控制器', 'guns-cloud-auth$gateway_actuator$add_route', '新增路由', '192.168.11.1', '/guns-cloud-auth/gateway/addRoute', 'POST', 'N', 'Y', 'Y', -100, '2020-09-26 10:04:21', NULL, NULL);
INSERT INTO `guns_sys_resource` VALUES ('guns-cloud-auth$gateway_actuator$delete_route', 'guns-cloud-auth', 'guns-cloud-auth', 'GatewayActuatorController', 'deleteRoute', 'GatewayActuator', '网关信息控制器', 'guns-cloud-auth$gateway_actuator$delete_route', '删除指定路由', '192.168.11.1', '/guns-cloud-auth/gateway/deleteRoute', 'GET', 'N', 'Y', 'Y', -100, '2020-09-26 10:04:21', NULL, NULL);
INSERT INTO `guns_sys_resource` VALUES ('guns-cloud-auth$gateway_actuator$globalfilters', 'guns-cloud-auth', 'guns-cloud-auth', 'GatewayActuatorController', 'globalfilters', 'GatewayActuator', '网关信息控制器', 'guns-cloud-auth$gateway_actuator$globalfilters', '过滤器列表', '192.168.11.1', '/guns-cloud-auth/gateway/globalfilters', 'GET', 'N', 'Y', 'Y', -100, '2020-09-26 10:04:21', NULL, NULL);
INSERT INTO `guns_sys_resource` VALUES ('guns-cloud-auth$gateway_actuator$refresh_route', 'guns-cloud-auth', 'guns-cloud-auth', 'GatewayActuatorController', 'refreshRoute', 'GatewayActuator', '网关信息控制器', 'guns-cloud-auth$gateway_actuator$refresh_route', '清空路由缓存', '192.168.11.1', '/guns-cloud-auth/gateway/refreshRoute', 'POST', 'N', 'Y', 'Y', -100, '2020-09-26 10:04:21', NULL, NULL);
INSERT INTO `guns_sys_resource` VALUES ('guns-cloud-auth$gateway_actuator$routefilters', 'guns-cloud-auth', 'guns-cloud-auth', 'GatewayActuatorController', 'routefilters', 'GatewayActuator', '网关信息控制器', 'guns-cloud-auth$gateway_actuator$routefilters', '所有的过滤器工厂列表', '192.168.11.1', '/guns-cloud-auth/gateway/routefilters', 'GET', 'N', 'Y', 'Y', -100, '2020-09-26 10:04:21', NULL, NULL);
INSERT INTO `guns_sys_resource` VALUES ('guns-cloud-auth$gateway_actuator$route_info_by_id', 'guns-cloud-auth', 'guns-cloud-auth', 'GatewayActuatorController', 'routeInfoById', 'GatewayActuator', '网关信息控制器', 'guns-cloud-auth$gateway_actuator$route_info_by_id', '根据id查询路由信息', '192.168.11.1', '/guns-cloud-auth/gateway/routeInfoById', 'GET', 'N', 'Y', 'Y', -100, '2020-09-26 10:04:21', NULL, NULL);
INSERT INTO `guns_sys_resource` VALUES ('guns-cloud-auth$gateway_actuator$route_list', 'guns-cloud-auth', 'guns-cloud-auth', 'GatewayActuatorController', 'routeList', 'GatewayActuator', '网关信息控制器', 'guns-cloud-auth$gateway_actuator$route_list', '网关路由列表', '192.168.11.1', '/guns-cloud-auth/gateway/routes', 'GET', 'Y', 'Y', 'Y', -100, '2020-09-26 10:04:21', NULL, NULL);
INSERT INTO `guns_sys_resource` VALUES ('guns-cloud-auth$god_key_manager$checked_status', 'guns-cloud-auth', 'guns-cloud-auth', 'GodKeyManagerController', 'checkedStatus', 'GodKeyManager', '万能密码', 'guns-cloud-auth$god_key_manager$checked_status', '启用禁用万能密码', '192.168.11.1', '/guns-cloud-auth/godKeyManager/checkedStatus', 'GET', 'N', 'Y', 'Y', -100, '2020-09-26 10:04:21', NULL, NULL);
INSERT INTO `guns_sys_resource` VALUES ('guns-cloud-auth$god_key_manager$get_god_key', 'guns-cloud-auth', 'guns-cloud-auth', 'GodKeyManagerController', 'getGodKey', 'GodKeyManager', '万能密码', 'guns-cloud-auth$god_key_manager$get_god_key', '获取万能密码的值和状态', '192.168.11.1', '/guns-cloud-auth/godKeyManager/getGodKey', 'GET', 'N', 'Y', 'Y', -100, '2020-09-26 10:04:21', NULL, NULL);
INSERT INTO `guns_sys_resource` VALUES ('guns-cloud-auth$res_cache$add_res_cache', 'guns-cloud-auth', 'guns-cloud-auth', 'ResCacheController', 'addResCache', 'ResCache', '资源缓存维护管理', 'guns-cloud-auth$res_cache$add_res_cache', '新增资源缓存', '192.168.11.1', '/guns-cloud-auth/resCacheManager/addResCache', 'POST', 'N', 'Y', 'N', -100, '2020-09-26 10:04:21', NULL, NULL);
INSERT INTO `guns_sys_resource` VALUES ('guns-cloud-auth$res_cache$edit_res_cache', 'guns-cloud-auth', 'guns-cloud-auth', 'ResCacheController', 'editResCache', 'ResCache', '资源缓存维护管理', 'guns-cloud-auth$res_cache$edit_res_cache', '修改资源缓存', '192.168.11.1', '/guns-cloud-auth/resCacheManager/editResCache', 'POST', 'N', 'Y', 'N', -100, '2020-09-26 10:04:21', NULL, NULL);
INSERT INTO `guns_sys_resource` VALUES ('guns-cloud-auth$res_cache$get_app_select', 'guns-cloud-auth', 'guns-cloud-auth', 'ResCacheController', 'getAppSelect', 'ResCache', '资源缓存维护管理', 'guns-cloud-auth$res_cache$get_app_select', '获取应用下拉列表', '192.168.11.1', '/guns-cloud-auth/resCacheManager/getAppSelect', 'GET', 'N', 'Y', 'N', -100, '2020-09-26 10:04:21', NULL, NULL);
INSERT INTO `guns_sys_resource` VALUES ('guns-cloud-auth$res_cache$get_detail', 'guns-cloud-auth', 'guns-cloud-auth', 'ResCacheController', 'getDetail', 'ResCache', '资源缓存维护管理', 'guns-cloud-auth$res_cache$get_detail', '获取缓存资源的详情', '192.168.11.1', '/guns-cloud-auth/resCacheManager/getDetail', 'GET', 'N', 'Y', 'N', -100, '2020-09-26 10:04:21', NULL, NULL);
INSERT INTO `guns_sys_resource` VALUES ('guns-cloud-auth$res_cache$get_http_request_select', 'guns-cloud-auth', 'guns-cloud-auth', 'ResCacheController', 'getHttpRequestSelect', 'ResCache', '资源缓存维护管理', 'guns-cloud-auth$res_cache$get_http_request_select', '获取http请求下拉列表', '192.168.11.1', '/guns-cloud-auth/resCacheManager/getHttpRequestSelect', 'GET', 'N', 'Y', 'N', -100, '2020-09-26 10:04:21', NULL, NULL);
INSERT INTO `guns_sys_resource` VALUES ('guns-cloud-auth$res_cache$get_res_cache_list', 'guns-cloud-auth', 'guns-cloud-auth', 'ResCacheController', 'getResCacheList', 'ResCache', '资源缓存维护管理', 'guns-cloud-auth$res_cache$get_res_cache_list', '获取资源缓存分页', '192.168.11.1', '/guns-cloud-auth/resCacheManager/getResCacheList', 'GET', 'N', 'Y', 'N', -100, '2020-09-26 10:04:21', NULL, NULL);
INSERT INTO `guns_sys_resource` VALUES ('guns-cloud-auth$res_cache$get_res_module_select', 'guns-cloud-auth', 'guns-cloud-auth', 'ResCacheController', 'getResModuleSelect', 'ResCache', '资源缓存维护管理', 'guns-cloud-auth$res_cache$get_res_module_select', '获取资源所属模块下拉列表', '192.168.11.1', '/guns-cloud-auth/resCacheManager/getResModuleSelect', 'GET', 'N', 'Y', 'N', -100, '2020-09-26 10:04:21', NULL, NULL);
INSERT INTO `guns_sys_resource` VALUES ('guns-cloud-auth$res_cache$remove_res_cache', 'guns-cloud-auth', 'guns-cloud-auth', 'ResCacheController', 'removeResCache', 'ResCache', '资源缓存维护管理', 'guns-cloud-auth$res_cache$remove_res_cache', '删除资源缓存', '192.168.11.1', '/guns-cloud-auth/resCacheManager/removeResCache', 'GET', 'N', 'Y', 'N', -100, '2020-09-26 10:04:21', NULL, NULL);
INSERT INTO `guns_sys_resource` VALUES ('guns-cloud-auth$session_manager$forced_offline', 'guns-cloud-auth', 'guns-cloud-auth', 'SessionManagerController', 'forcedOffline', 'SessionManager', '会话管理', 'guns-cloud-auth$session_manager$forced_offline', '强制用户下线', '192.168.11.1', '/guns-cloud-auth/session/forcedOffline', 'GET', 'N', 'Y', 'Y', -100, '2020-09-26 10:04:21', NULL, NULL);
INSERT INTO `guns_sys_resource` VALUES ('guns-cloud-auth$session_manager$page_list', 'guns-cloud-auth', 'guns-cloud-auth', 'SessionManagerController', 'pageList', 'SessionManager', '会话管理', 'guns-cloud-auth$session_manager$page_list', '获取在线用户信息', '192.168.11.1', '/guns-cloud-auth/session/pageList', 'GET', 'N', 'Y', 'Y', -100, '2020-09-26 10:04:21', NULL, NULL);
INSERT INTO `guns_sys_resource` VALUES ('guns-cloud-system$api_menu_flag$api', 'guns-cloud-system', 'guns-cloud-system', 'ApiMenuFlagController', 'api', 'ApiMenuFlag', 'api管理', 'guns-cloud-system$api_menu_flag$api', 'api管理菜单（一级）', '192.168.11.1', '/guns-cloud-system/apiMenu/api', '', 'Y', 'Y', 'Y', -100, '2020-09-26 21:46:28', NULL, NULL);
INSERT INTO `guns_sys_resource` VALUES ('guns-cloud-system$api_menu_flag$swagger', 'guns-cloud-system', 'guns-cloud-system', 'ApiMenuFlagController', 'swagger', 'ApiMenuFlag', 'api管理', 'guns-cloud-system$api_menu_flag$swagger', '接口文档', '192.168.11.1', '/guns-cloud-system/apiMenu/swagger', '', 'Y', 'Y', 'Y', -100, '2020-09-26 21:46:28', NULL, NULL);
INSERT INTO `guns_sys_resource` VALUES ('guns-cloud-system$app$add', 'guns-cloud-system', 'guns-cloud-system', 'AppController', 'add', 'App', '应用平台', 'guns-cloud-system$app$add', '新增应用', '192.168.11.1', '/guns-cloud-system/app/add', 'POST', 'N', 'Y', 'Y', -100, '2020-09-26 21:46:32', NULL, NULL);
INSERT INTO `guns_sys_resource` VALUES ('guns-cloud-system$app$change_status', 'guns-cloud-system', 'guns-cloud-system', 'AppController', 'changeStatus', 'App', '应用平台', 'guns-cloud-system$app$change_status', '修改状态', '192.168.11.1', '/guns-cloud-system/app/changeStatus', 'GET', 'N', 'Y', 'Y', -100, '2020-09-26 21:46:32', NULL, NULL);
INSERT INTO `guns_sys_resource` VALUES ('guns-cloud-system$app$delete', 'guns-cloud-system', 'guns-cloud-system', 'AppController', 'delete', 'App', '应用平台', 'guns-cloud-system$app$delete', '删除应用', '192.168.11.1', '/guns-cloud-system/app/delete', 'GET', 'N', 'Y', 'Y', -100, '2020-09-26 21:46:32', NULL, NULL);
INSERT INTO `guns_sys_resource` VALUES ('guns-cloud-system$app$detail', 'guns-cloud-system', 'guns-cloud-system', 'AppController', 'detail', 'App', '应用平台', 'guns-cloud-system$app$detail', '应用详情', '192.168.11.1', '/guns-cloud-system/app/detail', 'GET', 'N', 'Y', 'Y', -100, '2020-09-26 21:46:32', NULL, NULL);
INSERT INTO `guns_sys_resource` VALUES ('guns-cloud-system$app$get_app_select', 'guns-cloud-system', 'guns-cloud-system', 'AppController', 'getAppSelect', 'App', '应用平台', 'guns-cloud-system$app$get_app_select', '应用下拉列表', '192.168.11.1', '/guns-cloud-system/app/getAppSelect', 'GET', 'N', 'Y', 'Y', -100, '2020-09-26 21:46:32', NULL, NULL);
INSERT INTO `guns_sys_resource` VALUES ('guns-cloud-system$app$get_app_select_by_current_user', 'guns-cloud-system', 'guns-cloud-system', 'AppController', 'getAppSelectByCurrentUser', 'App', '应用平台', 'guns-cloud-system$app$get_app_select_by_current_user', '根据当前登录人获取应用下拉列表', '192.168.11.1', '/guns-cloud-system/app/getAppSelectByCurrentUser', 'GET', 'N', 'Y', 'Y', -100, '2020-09-26 21:46:32', NULL, NULL);
INSERT INTO `guns_sys_resource` VALUES ('guns-cloud-system$app$list', 'guns-cloud-system', 'guns-cloud-system', 'AppController', 'list', 'App', '应用平台', 'guns-cloud-system$app$list', '获取应用列表', '192.168.11.1', '/guns-cloud-system/app/list', 'GET', 'N', 'Y', 'Y', -100, '2020-09-26 21:46:32', NULL, NULL);
INSERT INTO `guns_sys_resource` VALUES ('guns-cloud-system$app$uptate', 'guns-cloud-system', 'guns-cloud-system', 'AppController', 'uptate', 'App', '应用平台', 'guns-cloud-system$app$uptate', '修改应用', '192.168.11.1', '/guns-cloud-system/app/update', 'POST', 'N', 'Y', 'Y', -100, '2020-09-26 21:46:32', NULL, NULL);
INSERT INTO `guns_sys_resource` VALUES ('guns-cloud-system$authority_menu_flag$authority_menu', 'guns-cloud-system', 'guns-cloud-system', 'AuthorityMenuFlagController', 'authorityMenu', 'AuthorityMenuFlag', '资源权限菜单', 'guns-cloud-system$authority_menu_flag$authority_menu', '资源权限菜单', '192.168.11.1', '/guns-cloud-system/authorityMenu/authorityMenu', '', 'Y', 'Y', 'Y', -100, '2020-09-26 21:46:28', NULL, NULL);
INSERT INTO `guns_sys_resource` VALUES ('guns-cloud-system$authority_menu_flag$menu_manager', 'guns-cloud-system', 'guns-cloud-system', 'AuthorityMenuFlagController', 'menuManager', 'AuthorityMenuFlag', '资源权限菜单', 'guns-cloud-system$authority_menu_flag$menu_manager', '菜单管理', '192.168.11.1', '/guns-cloud-system/authorityMenu/menuManager', '', 'Y', 'Y', 'Y', -100, '2020-09-26 21:46:28', NULL, NULL);
INSERT INTO `guns_sys_resource` VALUES ('guns-cloud-system$authority_menu_flag$permission_manager', 'guns-cloud-system', 'guns-cloud-system', 'AuthorityMenuFlagController', 'permissionManager', 'AuthorityMenuFlag', '资源权限菜单', 'guns-cloud-system$authority_menu_flag$permission_manager', '权限管理', '192.168.11.1', '/guns-cloud-system/authorityMenu/permissionManager', '', 'Y', 'Y', 'Y', -100, '2020-09-26 21:46:28', NULL, NULL);
INSERT INTO `guns_sys_resource` VALUES ('guns-cloud-system$authority_menu_flag$resource_manager', 'guns-cloud-system', 'guns-cloud-system', 'AuthorityMenuFlagController', 'resourceManager', 'AuthorityMenuFlag', '资源权限菜单', 'guns-cloud-system$authority_menu_flag$resource_manager', '资源管理', '192.168.11.1', '/guns-cloud-system/authorityMenu/resourceManager', '', 'Y', 'Y', 'Y', -100, '2020-09-26 21:46:28', NULL, NULL);
INSERT INTO `guns_sys_resource` VALUES ('guns-cloud-system$authority_menu_flag$role_manager', 'guns-cloud-system', 'guns-cloud-system', 'AuthorityMenuFlagController', 'roleManager', 'AuthorityMenuFlag', '资源权限菜单', 'guns-cloud-system$authority_menu_flag$role_manager', '角色管理', '192.168.11.1', '/guns-cloud-system/authorityMenu/roleManager', '', 'Y', 'Y', 'Y', -100, '2020-09-26 21:46:28', NULL, NULL);
INSERT INTO `guns_sys_resource` VALUES ('guns-cloud-system$config_menu_flag$configs', 'guns-cloud-system', 'guns-cloud-system', 'ConfigMenuFlagController', 'configs', 'ConfigMenuFlag', '参数配置', 'guns-cloud-system$config_menu_flag$configs', '参数管理', '192.168.11.1', '/guns-cloud-system/configMenu/configs', '', 'Y', 'Y', 'Y', -100, '2020-09-26 21:46:28', NULL, NULL);
INSERT INTO `guns_sys_resource` VALUES ('guns-cloud-system$config_menu_flag$config_menu', 'guns-cloud-system', 'guns-cloud-system', 'ConfigMenuFlagController', 'configMenu', 'ConfigMenuFlag', '参数配置', 'guns-cloud-system$config_menu_flag$config_menu', '参数配置菜单（一级）', '192.168.11.1', '/guns-cloud-system/configMenu/configMenu', '', 'Y', 'Y', 'Y', -100, '2020-09-26 21:46:28', NULL, NULL);
INSERT INTO `guns_sys_resource` VALUES ('guns-cloud-system$database_info$add', 'guns-cloud-system', 'guns-cloud-system', 'DatabaseInfoController', 'add', 'DatabaseInfo', '数据源管理', 'guns-cloud-system$database_info$add', '新增数据源', '192.168.11.1', '/guns-cloud-system/databaseInfo/add', 'POST', 'N', 'Y', 'Y', -100, '2020-09-26 21:46:31', NULL, NULL);
INSERT INTO `guns_sys_resource` VALUES ('guns-cloud-system$database_info$delete', 'guns-cloud-system', 'guns-cloud-system', 'DatabaseInfoController', 'delete', 'DatabaseInfo', '数据源管理', 'guns-cloud-system$database_info$delete', '删除数据源', '192.168.11.1', '/guns-cloud-system/databaseInfo/delete', 'POST', 'N', 'Y', 'Y', -100, '2020-09-26 21:46:31', NULL, NULL);
INSERT INTO `guns_sys_resource` VALUES ('guns-cloud-system$database_info$detail', 'guns-cloud-system', 'guns-cloud-system', 'DatabaseInfoController', 'detail', 'DatabaseInfo', '数据源管理', 'guns-cloud-system$database_info$detail', '数据源详情', '192.168.11.1', '/guns-cloud-system/databaseInfo/detail', 'POST', 'N', 'Y', 'Y', -100, '2020-09-26 21:46:31', NULL, NULL);
INSERT INTO `guns_sys_resource` VALUES ('guns-cloud-system$database_info$edit', 'guns-cloud-system', 'guns-cloud-system', 'DatabaseInfoController', 'edit', 'DatabaseInfo', '数据源管理', 'guns-cloud-system$database_info$edit', '编辑数据源', '192.168.11.1', '/guns-cloud-system/databaseInfo/edit', 'POST', 'N', 'Y', 'Y', -100, '2020-09-26 21:46:31', NULL, NULL);
INSERT INTO `guns_sys_resource` VALUES ('guns-cloud-system$database_info$page', 'guns-cloud-system', 'guns-cloud-system', 'DatabaseInfoController', 'page', 'DatabaseInfo', '数据源管理', 'guns-cloud-system$database_info$page', '查询数据源列表', '192.168.11.1', '/guns-cloud-system/databaseInfo/page', 'GET', 'N', 'Y', 'Y', -100, '2020-09-26 21:46:31', NULL, NULL);
INSERT INTO `guns_sys_resource` VALUES ('guns-cloud-system$dict$add_dict_type', 'guns-cloud-system', 'guns-cloud-system', 'DictController', 'addDictType', 'Dict', '字典管理', 'guns-cloud-system$dict$add_dict_type', '添加字典', '192.168.11.1', '/guns-cloud-system/dict/addDict', 'POST', 'N', 'Y', 'Y', -100, '2020-09-26 21:46:31', NULL, NULL);
INSERT INTO `guns_sys_resource` VALUES ('guns-cloud-system$dict$check_code', 'guns-cloud-system', 'guns-cloud-system', 'DictController', 'checkCode', 'Dict', '字典管理', 'guns-cloud-system$dict$check_code', 'code校验', '192.168.11.1', '/guns-cloud-system/dict/checkCode', 'POST', 'N', 'Y', 'Y', -100, '2020-09-26 21:46:31', NULL, NULL);
INSERT INTO `guns_sys_resource` VALUES ('guns-cloud-system$dict$delete_dict', 'guns-cloud-system', 'guns-cloud-system', 'DictController', 'deleteDict', 'Dict', '字典管理', 'guns-cloud-system$dict$delete_dict', '删除字典', '192.168.11.1', '/guns-cloud-system/dict/deleteDict', 'POST', 'N', 'Y', 'Y', -100, '2020-09-26 21:46:31', NULL, NULL);
INSERT INTO `guns_sys_resource` VALUES ('guns-cloud-system$dict$get_dict_list', 'guns-cloud-system', 'guns-cloud-system', 'DictController', 'getDictList', 'Dict', '字典管理', 'guns-cloud-system$dict$get_dict_list', '获取字典列表', '192.168.11.1', '/guns-cloud-system/dict/getDictList', 'POST', 'N', 'Y', 'Y', -100, '2020-09-26 21:46:31', NULL, NULL);
INSERT INTO `guns_sys_resource` VALUES ('guns-cloud-system$dict$get_dict_list_by_type_code', 'guns-cloud-system', 'guns-cloud-system', 'DictController', 'getDictListByTypeCode', 'Dict', '字典管理', 'guns-cloud-system$dict$get_dict_list_by_type_code', '根据字典类型code获取所有字典', '192.168.11.1', '/guns-cloud-system/dict/getDictListByTypeCode', 'POST', 'N', 'Y', 'Y', -100, '2020-09-26 21:46:31', NULL, NULL);
INSERT INTO `guns_sys_resource` VALUES ('guns-cloud-system$dict$get_dict_page', 'guns-cloud-system', 'guns-cloud-system', 'DictController', 'getDictPage', 'Dict', '字典管理', 'guns-cloud-system$dict$get_dict_page', '获取字典列表', '192.168.11.1', '/guns-cloud-system/dict/getDictPage', 'POST', 'N', 'Y', 'Y', -100, '2020-09-26 21:46:31', NULL, NULL);
INSERT INTO `guns_sys_resource` VALUES ('guns-cloud-system$dict$get_dict_tree_list', 'guns-cloud-system', 'guns-cloud-system', 'DictController', 'getDictTreeList', 'Dict', '字典管理', 'guns-cloud-system$dict$get_dict_tree_list', '获取树形字典列表', '192.168.11.1', '/guns-cloud-system/dict/getDictTreeList', 'POST', 'N', 'Y', 'Y', -100, '2020-09-26 21:46:31', NULL, NULL);
INSERT INTO `guns_sys_resource` VALUES ('guns-cloud-system$dict$get_list_by_type_code_and_PC_ode', 'guns-cloud-system', 'guns-cloud-system', 'DictController', 'getListByTypeCodeAndPCode', 'Dict', '字典管理', 'guns-cloud-system$dict$get_list_by_type_code_and_PC_ode', '根据字典类型code和父编码获取下级字典', '192.168.11.1', '/guns-cloud-system/dict/getListByTypeCodeAndPCode', 'POST', 'N', 'Y', 'Y', -100, '2020-09-26 21:46:31', NULL, NULL);
INSERT INTO `guns_sys_resource` VALUES ('guns-cloud-system$dict$update_dict', 'guns-cloud-system', 'guns-cloud-system', 'DictController', 'updateDict', 'Dict', '字典管理', 'guns-cloud-system$dict$update_dict', '修改字典', '192.168.11.1', '/guns-cloud-system/dict/updateDict', 'POST', 'N', 'Y', 'Y', -100, '2020-09-26 21:46:31', NULL, NULL);
INSERT INTO `guns_sys_resource` VALUES ('guns-cloud-system$dict$update_dict_status', 'guns-cloud-system', 'guns-cloud-system', 'DictController', 'updateDictStatus', 'Dict', '字典管理', 'guns-cloud-system$dict$update_dict_status', '更新字典状态', '192.168.11.1', '/guns-cloud-system/dict/updateDictStatus', 'POST', 'N', 'Y', 'Y', -100, '2020-09-26 21:46:31', NULL, NULL);
INSERT INTO `guns_sys_resource` VALUES ('guns-cloud-system$dict_type$add_dict_type', 'guns-cloud-system', 'guns-cloud-system', 'DictTypeController', 'addDictType', 'DictType', '字典类型管理', 'guns-cloud-system$dict_type$add_dict_type', '添加字典类型', '192.168.11.1', '/guns-cloud-system/dictType/addDictType', 'POST', 'N', 'Y', 'Y', -100, '2020-09-26 21:46:31', NULL, NULL);
INSERT INTO `guns_sys_resource` VALUES ('guns-cloud-system$dict_type$check_code', 'guns-cloud-system', 'guns-cloud-system', 'DictTypeController', 'checkCode', 'DictType', '字典类型管理', 'guns-cloud-system$dict_type$check_code', 'code校验', '192.168.11.1', '/guns-cloud-system/dictType/checkCode', 'POST', 'N', 'Y', 'Y', -100, '2020-09-26 21:46:31', NULL, NULL);
INSERT INTO `guns_sys_resource` VALUES ('guns-cloud-system$dict_type$delete_dict_type', 'guns-cloud-system', 'guns-cloud-system', 'DictTypeController', 'deleteDictType', 'DictType', '字典类型管理', 'guns-cloud-system$dict_type$delete_dict_type', '删除字典类型', '192.168.11.1', '/guns-cloud-system/dictType/deleteDictType', 'POST', 'N', 'Y', 'Y', -100, '2020-09-26 21:46:31', NULL, NULL);
INSERT INTO `guns_sys_resource` VALUES ('guns-cloud-system$dict_type$get_dict_type_list', 'guns-cloud-system', 'guns-cloud-system', 'DictTypeController', 'getDictTypeList', 'DictType', '字典类型管理', 'guns-cloud-system$dict_type$get_dict_type_list', '获取字典类型列表', '192.168.11.1', '/guns-cloud-system/dictType/getDictTypeList', 'POST', 'N', 'Y', 'Y', -100, '2020-09-26 21:46:31', NULL, NULL);
INSERT INTO `guns_sys_resource` VALUES ('guns-cloud-system$dict_type$update_dict_type', 'guns-cloud-system', 'guns-cloud-system', 'DictTypeController', 'updateDictType', 'DictType', '字典类型管理', 'guns-cloud-system$dict_type$update_dict_type', '修改字典类型', '192.168.11.1', '/guns-cloud-system/dictType/updateDictType', 'POST', 'N', 'Y', 'Y', -100, '2020-09-26 21:46:31', NULL, NULL);
INSERT INTO `guns_sys_resource` VALUES ('guns-cloud-system$dict_type$update_status', 'guns-cloud-system', 'guns-cloud-system', 'DictTypeController', 'updateStatus', 'DictType', '字典类型管理', 'guns-cloud-system$dict_type$update_status', '修改字典类型状态', '192.168.11.1', '/guns-cloud-system/dictType/updateStatus', 'POST', 'N', 'Y', 'Y', -100, '2020-09-26 21:46:31', NULL, NULL);
INSERT INTO `guns_sys_resource` VALUES ('guns-cloud-system$ent_company$add', 'guns-cloud-system', 'guns-cloud-system', 'EntCompanyController', 'add', 'EntCompany', '企业信息管理', 'guns-cloud-system$ent_company$add', '添加', '192.168.11.1', '/guns-cloud-system/entCompany/add', 'POST', 'N', 'Y', 'Y', -100, '2020-09-26 21:46:31', NULL, NULL);
INSERT INTO `guns_sys_resource` VALUES ('guns-cloud-system$ent_company$change_status', 'guns-cloud-system', 'guns-cloud-system', 'EntCompanyController', 'changeStatus', 'EntCompany', '企业信息管理', 'guns-cloud-system$ent_company$change_status', '禁用启用', '192.168.11.1', '/guns-cloud-system/entCompany/changeStatus', 'GET', 'N', 'Y', 'Y', -100, '2020-09-26 21:46:31', NULL, NULL);
INSERT INTO `guns_sys_resource` VALUES ('guns-cloud-system$ent_company$delete', 'guns-cloud-system', 'guns-cloud-system', 'EntCompanyController', 'delete', 'EntCompany', '企业信息管理', 'guns-cloud-system$ent_company$delete', '删除', '192.168.11.1', '/guns-cloud-system/entCompany/delete', 'POST', 'N', 'Y', 'Y', -100, '2020-09-26 21:46:31', NULL, NULL);
INSERT INTO `guns_sys_resource` VALUES ('guns-cloud-system$ent_company$detail', 'guns-cloud-system', 'guns-cloud-system', 'EntCompanyController', 'detail', 'EntCompany', '企业信息管理', 'guns-cloud-system$ent_company$detail', '详情', '192.168.11.1', '/guns-cloud-system/entCompany/detail', 'GET', 'N', 'Y', 'Y', -100, '2020-09-26 21:46:31', NULL, NULL);
INSERT INTO `guns_sys_resource` VALUES ('guns-cloud-system$ent_company$get_app_list', 'guns-cloud-system', 'guns-cloud-system', 'EntCompanyController', 'getAppList', 'EntCompany', '企业信息管理', 'guns-cloud-system$ent_company$get_app_list', '获取应用列表', '192.168.11.1', '/guns-cloud-system/entCompany/getAppList', 'GET', 'N', 'Y', 'N', -100, '2020-09-26 21:46:31', NULL, NULL);
INSERT INTO `guns_sys_resource` VALUES ('guns-cloud-system$ent_company$query_comp_tree', 'guns-cloud-system', 'guns-cloud-system', 'EntCompanyController', 'queryCompTree', 'EntCompany', '企业信息管理', 'guns-cloud-system$ent_company$query_comp_tree', '查询公司树', '192.168.11.1', '/guns-cloud-system/entCompany/queryCompTree', 'GET', 'N', 'Y', 'Y', -100, '2020-09-26 21:46:31', NULL, NULL);
INSERT INTO `guns_sys_resource` VALUES ('guns-cloud-system$ent_company$query_list_page', 'guns-cloud-system', 'guns-cloud-system', 'EntCompanyController', 'queryListPage', 'EntCompany', '企业信息管理', 'guns-cloud-system$ent_company$query_list_page', '分页查询列表', '192.168.11.1', '/guns-cloud-system/entCompany/queryListPage', 'GET', 'N', 'Y', 'Y', -100, '2020-09-26 21:46:31', NULL, NULL);
INSERT INTO `guns_sys_resource` VALUES ('guns-cloud-system$ent_company$save_comp_app', 'guns-cloud-system', 'guns-cloud-system', 'EntCompanyController', 'saveCompApp', 'EntCompany', '企业信息管理', 'guns-cloud-system$ent_company$save_comp_app', '保存公司配置的应用', '192.168.11.1', '/guns-cloud-system/entCompany/saveCompApp', 'POST', 'N', 'Y', 'N', -100, '2020-09-26 21:46:31', NULL, NULL);
INSERT INTO `guns_sys_resource` VALUES ('guns-cloud-system$ent_company$update', 'guns-cloud-system', 'guns-cloud-system', 'EntCompanyController', 'update', 'EntCompany', '企业信息管理', 'guns-cloud-system$ent_company$update', '修改', '192.168.11.1', '/guns-cloud-system/entCompany/update', 'POST', 'N', 'Y', 'Y', -100, '2020-09-26 21:46:31', NULL, NULL);
INSERT INTO `guns_sys_resource` VALUES ('guns-cloud-system$ent_comp_app$add', 'guns-cloud-system', 'guns-cloud-system', 'EntCompAppController', 'add', 'EntCompApp', '企业应用配置管理', 'guns-cloud-system$ent_comp_app$add', '添加', '192.168.11.1', '/guns-cloud-system/entCompApp/add', 'POST', 'N', 'Y', 'Y', -100, '2020-09-26 21:46:31', NULL, NULL);
INSERT INTO `guns_sys_resource` VALUES ('guns-cloud-system$ent_comp_app$delete', 'guns-cloud-system', 'guns-cloud-system', 'EntCompAppController', 'delete', 'EntCompApp', '企业应用配置管理', 'guns-cloud-system$ent_comp_app$delete', '删除', '192.168.11.1', '/guns-cloud-system/entCompApp/delete', 'POST', 'N', 'Y', 'Y', -100, '2020-09-26 21:46:31', NULL, NULL);
INSERT INTO `guns_sys_resource` VALUES ('guns-cloud-system$ent_comp_app$query_detail', 'guns-cloud-system', 'guns-cloud-system', 'EntCompAppController', 'queryDetail', 'EntCompApp', '企业应用配置管理', 'guns-cloud-system$ent_comp_app$query_detail', '查询详情', '192.168.11.1', '/guns-cloud-system/entCompApp/queryDetail', 'POST', 'N', 'Y', 'Y', -100, '2020-09-26 21:46:31', NULL, NULL);
INSERT INTO `guns_sys_resource` VALUES ('guns-cloud-system$ent_comp_app$query_list', 'guns-cloud-system', 'guns-cloud-system', 'EntCompAppController', 'queryList', 'EntCompApp', '企业应用配置管理', 'guns-cloud-system$ent_comp_app$query_list', '查询列表', '192.168.11.1', '/guns-cloud-system/entCompApp/queryList', 'POST', 'N', 'Y', 'Y', -100, '2020-09-26 21:46:31', NULL, NULL);
INSERT INTO `guns_sys_resource` VALUES ('guns-cloud-system$ent_comp_app$query_list_page', 'guns-cloud-system', 'guns-cloud-system', 'EntCompAppController', 'queryListPage', 'EntCompApp', '企业应用配置管理', 'guns-cloud-system$ent_comp_app$query_list_page', '分页查询列表', '192.168.11.1', '/guns-cloud-system/entCompApp/queryListPage', 'POST', 'N', 'Y', 'Y', -100, '2020-09-26 21:46:31', NULL, NULL);
INSERT INTO `guns_sys_resource` VALUES ('guns-cloud-system$ent_comp_app$update', 'guns-cloud-system', 'guns-cloud-system', 'EntCompAppController', 'update', 'EntCompApp', '企业应用配置管理', 'guns-cloud-system$ent_comp_app$update', '修改', '192.168.11.1', '/guns-cloud-system/entCompApp/update', 'POST', 'N', 'Y', 'Y', -100, '2020-09-26 21:46:31', NULL, NULL);
INSERT INTO `guns_sys_resource` VALUES ('guns-cloud-system$ent_dept$add', 'guns-cloud-system', 'guns-cloud-system', 'EntDeptController', 'add', 'EntDept', '部门表管理', 'guns-cloud-system$ent_dept$add', '添加', '192.168.11.1', '/guns-cloud-system/entDept/add', 'POST', 'N', 'Y', 'Y', -100, '2020-09-26 21:46:31', NULL, NULL);
INSERT INTO `guns_sys_resource` VALUES ('guns-cloud-system$ent_dept$change_status', 'guns-cloud-system', 'guns-cloud-system', 'EntDeptController', 'changeStatus', 'EntDept', '部门表管理', 'guns-cloud-system$ent_dept$change_status', '禁用启用', '192.168.11.1', '/guns-cloud-system/entDept/changeStatus', 'GET', 'N', 'Y', 'Y', -100, '2020-09-26 21:46:31', NULL, NULL);
INSERT INTO `guns_sys_resource` VALUES ('guns-cloud-system$ent_dept$delete', 'guns-cloud-system', 'guns-cloud-system', 'EntDeptController', 'delete', 'EntDept', '部门表管理', 'guns-cloud-system$ent_dept$delete', '删除', '192.168.11.1', '/guns-cloud-system/entDept/delete', 'POST', 'N', 'Y', 'Y', -100, '2020-09-26 21:46:31', NULL, NULL);
INSERT INTO `guns_sys_resource` VALUES ('guns-cloud-system$ent_dept$detail', 'guns-cloud-system', 'guns-cloud-system', 'EntDeptController', 'detail', 'EntDept', '部门表管理', 'guns-cloud-system$ent_dept$detail', '详情', '192.168.11.1', '/guns-cloud-system/entDept/detail', 'GET', 'N', 'Y', 'Y', -100, '2020-09-26 21:46:31', NULL, NULL);
INSERT INTO `guns_sys_resource` VALUES ('guns-cloud-system$ent_dept$query_dept_tree', 'guns-cloud-system', 'guns-cloud-system', 'EntDeptController', 'queryDeptTree', 'EntDept', '部门表管理', 'guns-cloud-system$ent_dept$query_dept_tree', '获取部门树列表', '192.168.11.1', '/guns-cloud-system/entDept/queryDeptTree', 'GET', 'N', 'Y', 'Y', -100, '2020-09-26 21:46:31', NULL, NULL);
INSERT INTO `guns_sys_resource` VALUES ('guns-cloud-system$ent_dept$query_list_page', 'guns-cloud-system', 'guns-cloud-system', 'EntDeptController', 'queryListPage', 'EntDept', '部门表管理', 'guns-cloud-system$ent_dept$query_list_page', '分页查询列表', '192.168.11.1', '/guns-cloud-system/entDept/queryListPage', 'GET', 'N', 'Y', 'Y', -100, '2020-09-26 21:46:31', NULL, NULL);
INSERT INTO `guns_sys_resource` VALUES ('guns-cloud-system$ent_dept$update', 'guns-cloud-system', 'guns-cloud-system', 'EntDeptController', 'update', 'EntDept', '部门表管理', 'guns-cloud-system$ent_dept$update', '修改', '192.168.11.1', '/guns-cloud-system/entDept/update', 'POST', 'N', 'Y', 'Y', -100, '2020-09-26 21:46:31', NULL, NULL);
INSERT INTO `guns_sys_resource` VALUES ('guns-cloud-system$ent_duty$add', 'guns-cloud-system', 'guns-cloud-system', 'EntDutyController', 'add', 'EntDuty', '职务管理管理', 'guns-cloud-system$ent_duty$add', '添加', '192.168.11.1', '/guns-cloud-system/entDuty/add', 'POST', 'N', 'Y', 'Y', -100, '2020-09-26 21:46:31', NULL, NULL);
INSERT INTO `guns_sys_resource` VALUES ('guns-cloud-system$ent_duty$change_status', 'guns-cloud-system', 'guns-cloud-system', 'EntDutyController', 'changeStatus', 'EntDuty', '职务管理管理', 'guns-cloud-system$ent_duty$change_status', '禁用启用', '192.168.11.1', '/guns-cloud-system/entDuty/changeStatus', 'GET', 'N', 'Y', 'Y', -100, '2020-09-26 21:46:31', NULL, NULL);
INSERT INTO `guns_sys_resource` VALUES ('guns-cloud-system$ent_duty$delete', 'guns-cloud-system', 'guns-cloud-system', 'EntDutyController', 'delete', 'EntDuty', '职务管理管理', 'guns-cloud-system$ent_duty$delete', '删除', '192.168.11.1', '/guns-cloud-system/entDuty/delete', 'POST', 'N', 'Y', 'Y', -100, '2020-09-26 21:46:31', NULL, NULL);
INSERT INTO `guns_sys_resource` VALUES ('guns-cloud-system$ent_duty$detail', 'guns-cloud-system', 'guns-cloud-system', 'EntDutyController', 'detail', 'EntDuty', '职务管理管理', 'guns-cloud-system$ent_duty$detail', '详情', '192.168.11.1', '/guns-cloud-system/entDuty/detail', 'GET', 'N', 'Y', 'Y', -100, '2020-09-26 21:46:31', NULL, NULL);
INSERT INTO `guns_sys_resource` VALUES ('guns-cloud-system$ent_duty$query_list', 'guns-cloud-system', 'guns-cloud-system', 'EntDutyController', 'queryList', 'EntDuty', '职务管理管理', 'guns-cloud-system$ent_duty$query_list', '职务不分页列表', '192.168.11.1', '/guns-cloud-system/entDuty/queryList', 'GET', 'N', 'Y', 'Y', -100, '2020-09-26 21:46:31', NULL, NULL);
INSERT INTO `guns_sys_resource` VALUES ('guns-cloud-system$ent_duty$query_list_page', 'guns-cloud-system', 'guns-cloud-system', 'EntDutyController', 'queryListPage', 'EntDuty', '职务管理管理', 'guns-cloud-system$ent_duty$query_list_page', '分页查询列表', '192.168.11.1', '/guns-cloud-system/entDuty/queryListPage', 'GET', 'N', 'Y', 'Y', -100, '2020-09-26 21:46:31', NULL, NULL);
INSERT INTO `guns_sys_resource` VALUES ('guns-cloud-system$ent_duty$update', 'guns-cloud-system', 'guns-cloud-system', 'EntDutyController', 'update', 'EntDuty', '职务管理管理', 'guns-cloud-system$ent_duty$update', '修改', '192.168.11.1', '/guns-cloud-system/entDuty/update', 'POST', 'N', 'Y', 'Y', -100, '2020-09-26 21:46:31', NULL, NULL);
INSERT INTO `guns_sys_resource` VALUES ('guns-cloud-system$ent_menu_flag$company', 'guns-cloud-system', 'guns-cloud-system', 'EntMenuFlagController', 'company', 'EntMenuFlag', '组织架构', 'guns-cloud-system$ent_menu_flag$company', '公司管理', '192.168.11.1', '/guns-cloud-system/entMenu/company', '', 'Y', 'Y', 'Y', -100, '2020-09-26 21:46:28', NULL, NULL);
INSERT INTO `guns_sys_resource` VALUES ('guns-cloud-system$ent_menu_flag$dept', 'guns-cloud-system', 'guns-cloud-system', 'EntMenuFlagController', 'dept', 'EntMenuFlag', '组织架构', 'guns-cloud-system$ent_menu_flag$dept', '部门管理', '192.168.11.1', '/guns-cloud-system/entMenu/dept', '', 'Y', 'Y', 'Y', -100, '2020-09-26 21:46:28', NULL, NULL);
INSERT INTO `guns_sys_resource` VALUES ('guns-cloud-system$ent_menu_flag$duty', 'guns-cloud-system', 'guns-cloud-system', 'EntMenuFlagController', 'duty', 'EntMenuFlag', '组织架构', 'guns-cloud-system$ent_menu_flag$duty', '职位管理', '192.168.11.1', '/guns-cloud-system/entMenu/duty', '', 'Y', 'Y', 'Y', -100, '2020-09-26 21:46:28', NULL, NULL);
INSERT INTO `guns_sys_resource` VALUES ('guns-cloud-system$ent_menu_flag$personal', 'guns-cloud-system', 'guns-cloud-system', 'EntMenuFlagController', 'personal', 'EntMenuFlag', '组织架构', 'guns-cloud-system$ent_menu_flag$personal', '人员管理', '192.168.11.1', '/guns-cloud-system/entMenu/personal', '', 'Y', 'Y', 'Y', -100, '2020-09-26 21:46:28', NULL, NULL);
INSERT INTO `guns_sys_resource` VALUES ('guns-cloud-system$ent_menu_flag$system', 'guns-cloud-system', 'guns-cloud-system', 'EntMenuFlagController', 'system', 'EntMenuFlag', '组织架构', 'guns-cloud-system$ent_menu_flag$system', '组织架构菜单', '192.168.11.1', '/guns-cloud-system/entMenu/enterprise', '', 'Y', 'Y', 'Y', -100, '2020-09-26 21:46:28', NULL, NULL);
INSERT INTO `guns_sys_resource` VALUES ('guns-cloud-system$ent_user$add', 'guns-cloud-system', 'guns-cloud-system', 'EntUserController', 'add', 'EntUser', '用户信息管理', 'guns-cloud-system$ent_user$add', '添加', '192.168.11.1', '/guns-cloud-system/entUser/add', 'POST', 'N', 'Y', 'Y', -100, '2020-09-26 21:46:32', NULL, NULL);
INSERT INTO `guns_sys_resource` VALUES ('guns-cloud-system$ent_user$change_status', 'guns-cloud-system', 'guns-cloud-system', 'EntUserController', 'changeStatus', 'EntUser', '用户信息管理', 'guns-cloud-system$ent_user$change_status', '禁用启用', '192.168.11.1', '/guns-cloud-system/entUser/changeStatus', 'GET', 'N', 'Y', 'Y', -100, '2020-09-26 21:46:32', NULL, NULL);
INSERT INTO `guns_sys_resource` VALUES ('guns-cloud-system$ent_user$delete', 'guns-cloud-system', 'guns-cloud-system', 'EntUserController', 'delete', 'EntUser', '用户信息管理', 'guns-cloud-system$ent_user$delete', '删除', '192.168.11.1', '/guns-cloud-system/entUser/delete', 'POST', 'N', 'Y', 'Y', -100, '2020-09-26 21:46:32', NULL, NULL);
INSERT INTO `guns_sys_resource` VALUES ('guns-cloud-system$ent_user$detail', 'guns-cloud-system', 'guns-cloud-system', 'EntUserController', 'detail', 'EntUser', '用户信息管理', 'guns-cloud-system$ent_user$detail', '详情', '192.168.11.1', '/guns-cloud-system/entUser/detail', 'GET', 'N', 'Y', 'Y', -100, '2020-09-26 21:46:32', NULL, NULL);
INSERT INTO `guns_sys_resource` VALUES ('guns-cloud-system$ent_user$get_company_select', 'guns-cloud-system', 'guns-cloud-system', 'EntUserController', 'getCompanySelect', 'EntUser', '用户信息管理', 'guns-cloud-system$ent_user$get_company_select', '获取公司下拉列表', '192.168.11.1', '/guns-cloud-system/entUser/getCompanySelect', 'GET', 'N', 'Y', 'N', -100, '2020-09-26 21:46:32', NULL, NULL);
INSERT INTO `guns_sys_resource` VALUES ('guns-cloud-system$ent_user$get_current_user', 'guns-cloud-system', 'guns-cloud-system', 'EntUserController', 'getCurrentUser', 'EntUser', '用户信息管理', 'guns-cloud-system$ent_user$get_current_user', '获取当前登录人信息', '192.168.11.1', '/guns-cloud-system/entUser/getCurrentUser', 'GET', 'N', 'Y', 'N', -100, '2020-09-26 21:46:32', NULL, NULL);
INSERT INTO `guns_sys_resource` VALUES ('guns-cloud-system$ent_user$get_dept_select', 'guns-cloud-system', 'guns-cloud-system', 'EntUserController', 'getDeptSelect', 'EntUser', '用户信息管理', 'guns-cloud-system$ent_user$get_dept_select', '获取部门下拉列表', '192.168.11.1', '/guns-cloud-system/entUser/getDeptSelect', 'GET', 'N', 'Y', 'N', -100, '2020-09-26 21:46:32', NULL, NULL);
INSERT INTO `guns_sys_resource` VALUES ('guns-cloud-system$ent_user$get_duty_select', 'guns-cloud-system', 'guns-cloud-system', 'EntUserController', 'getDutySelect', 'EntUser', '用户信息管理', 'guns-cloud-system$ent_user$get_duty_select', '获取职务下拉列表', '192.168.11.1', '/guns-cloud-system/entUser/getDutySelect', 'GET', 'N', 'Y', 'N', -100, '2020-09-26 21:46:32', NULL, NULL);
INSERT INTO `guns_sys_resource` VALUES ('guns-cloud-system$ent_user$get_roles', 'guns-cloud-system', 'guns-cloud-system', 'EntUserController', 'getRoles', 'EntUser', '用户信息管理', 'guns-cloud-system$ent_user$get_roles', '获取所有的角色信息', '192.168.11.1', '/guns-cloud-system/entUser/getRoles', 'GET', 'N', 'Y', 'N', -100, '2020-09-26 21:46:32', NULL, NULL);
INSERT INTO `guns_sys_resource` VALUES ('guns-cloud-system$ent_user$query_info_by_dict_type', 'guns-cloud-system', 'guns-cloud-system', 'EntUserController', 'queryInfoByDictType', 'EntUser', '用户信息管理', 'guns-cloud-system$ent_user$query_info_by_dict_type', '获取用户在字典表对应的信息', '192.168.11.1', '/guns-cloud-system/entUser/queryInfoByDictType', 'GET', 'N', 'Y', 'Y', -100, '2020-09-26 21:46:32', NULL, NULL);
INSERT INTO `guns_sys_resource` VALUES ('guns-cloud-system$ent_user$query_list_page', 'guns-cloud-system', 'guns-cloud-system', 'EntUserController', 'queryListPage', 'EntUser', '用户信息管理', 'guns-cloud-system$ent_user$query_list_page', '分页查询列表', '192.168.11.1', '/guns-cloud-system/entUser/queryListPage', 'GET', 'N', 'Y', 'Y', -100, '2020-09-26 21:46:32', NULL, NULL);
INSERT INTO `guns_sys_resource` VALUES ('guns-cloud-system$ent_user$reset_password', 'guns-cloud-system', 'guns-cloud-system', 'EntUserController', 'resetPassword', 'EntUser', '用户信息管理', 'guns-cloud-system$ent_user$reset_password', '重置密码', '192.168.11.1', '/guns-cloud-system/entUser/resetPassword', 'GET', 'N', 'Y', 'N', -100, '2020-09-26 21:46:32', NULL, NULL);
INSERT INTO `guns_sys_resource` VALUES ('guns-cloud-system$ent_user$save_current_user', 'guns-cloud-system', 'guns-cloud-system', 'EntUserController', 'saveCurrentUser', 'EntUser', '用户信息管理', 'guns-cloud-system$ent_user$save_current_user', '保存当前登录人的信息', '192.168.11.1', '/guns-cloud-system/entUser/saveCurrentUser', 'POST', 'N', 'Y', 'N', -100, '2020-09-26 21:46:32', NULL, NULL);
INSERT INTO `guns_sys_resource` VALUES ('guns-cloud-system$ent_user$save_roles', 'guns-cloud-system', 'guns-cloud-system', 'EntUserController', 'saveRoles', 'EntUser', '用户信息管理', 'guns-cloud-system$ent_user$save_roles', '保存人员配置好的角色', '192.168.11.1', '/guns-cloud-system/entUser/saveRoles', 'POST', 'N', 'Y', 'N', -100, '2020-09-26 21:46:32', NULL, NULL);
INSERT INTO `guns_sys_resource` VALUES ('guns-cloud-system$ent_user$update', 'guns-cloud-system', 'guns-cloud-system', 'EntUserController', 'update', 'EntUser', '用户信息管理', 'guns-cloud-system$ent_user$update', '修改', '192.168.11.1', '/guns-cloud-system/entUser/update', 'POST', 'N', 'Y', 'Y', -100, '2020-09-26 21:46:32', NULL, NULL);
INSERT INTO `guns_sys_resource` VALUES ('guns-cloud-system$ent_user$update_password', 'guns-cloud-system', 'guns-cloud-system', 'EntUserController', 'updatePassword', 'EntUser', '用户信息管理', 'guns-cloud-system$ent_user$update_password', '修改密码', '192.168.11.1', '/guns-cloud-system/entUser/updatePassword', 'POST', 'N', 'Y', 'N', -100, '2020-09-26 21:46:32', NULL, NULL);
INSERT INTO `guns_sys_resource` VALUES ('guns-cloud-system$ent_user_account$add', 'guns-cloud-system', 'guns-cloud-system', 'EntUserAccountController', 'add', 'EntUserAccount', '登录账号管理', 'guns-cloud-system$ent_user_account$add', '添加', '192.168.11.1', '/guns-cloud-system/entUserAccount/add', 'POST', 'N', 'Y', 'Y', -100, '2020-09-26 21:46:31', NULL, NULL);
INSERT INTO `guns_sys_resource` VALUES ('guns-cloud-system$ent_user_account$delete', 'guns-cloud-system', 'guns-cloud-system', 'EntUserAccountController', 'delete', 'EntUserAccount', '登录账号管理', 'guns-cloud-system$ent_user_account$delete', '删除', '192.168.11.1', '/guns-cloud-system/entUserAccount/delete', 'POST', 'N', 'Y', 'Y', -100, '2020-09-26 21:46:31', NULL, NULL);
INSERT INTO `guns_sys_resource` VALUES ('guns-cloud-system$ent_user_account$query_detail', 'guns-cloud-system', 'guns-cloud-system', 'EntUserAccountController', 'queryDetail', 'EntUserAccount', '登录账号管理', 'guns-cloud-system$ent_user_account$query_detail', '查询详情', '192.168.11.1', '/guns-cloud-system/entUserAccount/queryDetail', 'POST', 'N', 'Y', 'Y', -100, '2020-09-26 21:46:31', NULL, NULL);
INSERT INTO `guns_sys_resource` VALUES ('guns-cloud-system$ent_user_account$query_list', 'guns-cloud-system', 'guns-cloud-system', 'EntUserAccountController', 'queryList', 'EntUserAccount', '登录账号管理', 'guns-cloud-system$ent_user_account$query_list', '查询列表', '192.168.11.1', '/guns-cloud-system/entUserAccount/queryList', 'POST', 'N', 'Y', 'Y', -100, '2020-09-26 21:46:31', NULL, NULL);
INSERT INTO `guns_sys_resource` VALUES ('guns-cloud-system$ent_user_account$query_list_page', 'guns-cloud-system', 'guns-cloud-system', 'EntUserAccountController', 'queryListPage', 'EntUserAccount', '登录账号管理', 'guns-cloud-system$ent_user_account$query_list_page', '分页查询列表', '192.168.11.1', '/guns-cloud-system/entUserAccount/queryListPage', 'POST', 'N', 'Y', 'Y', -100, '2020-09-26 21:46:31', NULL, NULL);
INSERT INTO `guns_sys_resource` VALUES ('guns-cloud-system$ent_user_account$update', 'guns-cloud-system', 'guns-cloud-system', 'EntUserAccountController', 'update', 'EntUserAccount', '登录账号管理', 'guns-cloud-system$ent_user_account$update', '修改', '192.168.11.1', '/guns-cloud-system/entUserAccount/update', 'POST', 'N', 'Y', 'Y', -100, '2020-09-26 21:46:31', NULL, NULL);
INSERT INTO `guns_sys_resource` VALUES ('guns-cloud-system$ent_user_dept$add', 'guns-cloud-system', 'guns-cloud-system', 'EntUserDeptController', 'add', 'EntUserDept', '用户部门关联表管理', 'guns-cloud-system$ent_user_dept$add', '添加', '192.168.11.1', '/guns-cloud-system/entUserDept/add', 'POST', 'N', 'Y', 'Y', -100, '2020-09-26 21:46:32', NULL, NULL);
INSERT INTO `guns_sys_resource` VALUES ('guns-cloud-system$ent_user_dept$delete', 'guns-cloud-system', 'guns-cloud-system', 'EntUserDeptController', 'delete', 'EntUserDept', '用户部门关联表管理', 'guns-cloud-system$ent_user_dept$delete', '删除', '192.168.11.1', '/guns-cloud-system/entUserDept/delete', 'POST', 'N', 'Y', 'Y', -100, '2020-09-26 21:46:32', NULL, NULL);
INSERT INTO `guns_sys_resource` VALUES ('guns-cloud-system$ent_user_dept$query_detail', 'guns-cloud-system', 'guns-cloud-system', 'EntUserDeptController', 'queryDetail', 'EntUserDept', '用户部门关联表管理', 'guns-cloud-system$ent_user_dept$query_detail', '查询详情', '192.168.11.1', '/guns-cloud-system/entUserDept/queryDetail', 'POST', 'N', 'Y', 'Y', -100, '2020-09-26 21:46:32', NULL, NULL);
INSERT INTO `guns_sys_resource` VALUES ('guns-cloud-system$ent_user_dept$query_list', 'guns-cloud-system', 'guns-cloud-system', 'EntUserDeptController', 'queryList', 'EntUserDept', '用户部门关联表管理', 'guns-cloud-system$ent_user_dept$query_list', '查询列表', '192.168.11.1', '/guns-cloud-system/entUserDept/queryList', 'POST', 'N', 'Y', 'Y', -100, '2020-09-26 21:46:32', NULL, NULL);
INSERT INTO `guns_sys_resource` VALUES ('guns-cloud-system$ent_user_dept$query_list_page', 'guns-cloud-system', 'guns-cloud-system', 'EntUserDeptController', 'queryListPage', 'EntUserDept', '用户部门关联表管理', 'guns-cloud-system$ent_user_dept$query_list_page', '分页查询列表', '192.168.11.1', '/guns-cloud-system/entUserDept/queryListPage', 'POST', 'N', 'Y', 'Y', -100, '2020-09-26 21:46:32', NULL, NULL);
INSERT INTO `guns_sys_resource` VALUES ('guns-cloud-system$ent_user_dept$update', 'guns-cloud-system', 'guns-cloud-system', 'EntUserDeptController', 'update', 'EntUserDept', '用户部门关联表管理', 'guns-cloud-system$ent_user_dept$update', '修改', '192.168.11.1', '/guns-cloud-system/entUserDept/update', 'POST', 'N', 'Y', 'Y', -100, '2020-09-26 21:46:32', NULL, NULL);
INSERT INTO `guns_sys_resource` VALUES ('guns-cloud-system$file$file_access', 'guns-cloud-system', 'guns-cloud-system', 'FileController', 'fileAccess', 'File', '文件系统', 'guns-cloud-system$file$file_access', '文件下载', '192.168.11.1', '/guns-cloud-system/download', 'GET', 'N', 'Y', 'N', -100, '2020-09-26 21:46:32', NULL, NULL);
INSERT INTO `guns_sys_resource` VALUES ('guns-cloud-system$file$preview', 'guns-cloud-system', 'guns-cloud-system', 'FileController', 'preview', 'File', '文件系统', 'guns-cloud-system$file$preview', '文件预览', '192.168.11.1', '/guns-cloud-system/preview', 'GET', 'N', 'N', 'N', -100, '2020-09-26 21:46:32', NULL, NULL);
INSERT INTO `guns_sys_resource` VALUES ('guns-cloud-system$file$upload', 'guns-cloud-system', 'guns-cloud-system', 'FileController', 'upload', 'File', '文件系统', 'guns-cloud-system$file$upload', '文件上传', '192.168.11.1', '/guns-cloud-system/upload', 'POST', 'N', 'Y', 'Y', -100, '2020-09-26 21:46:32', NULL, NULL);
INSERT INTO `guns_sys_resource` VALUES ('guns-cloud-system$file_menu_flag$files', 'guns-cloud-system', 'guns-cloud-system', 'FileMenuFlagController', 'files', 'FileMenuFlag', '文件菜单', 'guns-cloud-system$file_menu_flag$files', '文件管理', '192.168.11.1', '/guns-cloud-system/fileMenuFlagController/files', '', 'Y', 'Y', 'Y', -100, '2020-09-26 21:46:28', NULL, NULL);
INSERT INTO `guns_sys_resource` VALUES ('guns-cloud-system$log_menu_flag$biz_menu', 'guns-cloud-system', 'guns-cloud-system', 'LogMenuFlagController', 'bizMenu', 'LogMenuFlag', '日志管理', 'guns-cloud-system$log_menu_flag$biz_menu', '业务日志', '192.168.11.1', '/guns-cloud-system/logMenu/bizMenu', '', 'Y', 'Y', 'Y', -100, '2020-09-26 21:46:28', NULL, NULL);
INSERT INTO `guns_sys_resource` VALUES ('guns-cloud-system$log_menu_flag$login_menu', 'guns-cloud-system', 'guns-cloud-system', 'LogMenuFlagController', 'loginMenu', 'LogMenuFlag', '日志管理', 'guns-cloud-system$log_menu_flag$login_menu', '登录日志', '192.168.11.1', '/guns-cloud-system/logMenu/loginMenu', '', 'Y', 'Y', 'Y', -100, '2020-09-26 21:46:28', NULL, NULL);
INSERT INTO `guns_sys_resource` VALUES ('guns-cloud-system$log_menu_flag$logs_menu', 'guns-cloud-system', 'guns-cloud-system', 'LogMenuFlagController', 'logsMenu', 'LogMenuFlag', '日志管理', 'guns-cloud-system$log_menu_flag$logs_menu', '日志管理菜单（一级）', '192.168.11.1', '/guns-cloud-system/logMenu/logsMenu', '', 'Y', 'Y', 'Y', -100, '2020-09-26 21:46:28', NULL, NULL);
INSERT INTO `guns_sys_resource` VALUES ('guns-cloud-system$menu$add_menu', 'guns-cloud-system', 'guns-cloud-system', 'MenuController', 'addMenu', 'Menu', '菜单管理接口', 'guns-cloud-system$menu$add_menu', '新增菜单', '192.168.11.1', '/guns-cloud-system/menu/add', 'POST', 'N', 'Y', 'Y', -100, '2020-09-26 21:46:32', NULL, NULL);
INSERT INTO `guns_sys_resource` VALUES ('guns-cloud-system$menu$delete_menu', 'guns-cloud-system', 'guns-cloud-system', 'MenuController', 'deleteMenu', 'Menu', '菜单管理接口', 'guns-cloud-system$menu$delete_menu', '删除菜单', '192.168.11.1', '/guns-cloud-system/menu/delete', 'POST', 'N', 'Y', 'Y', -100, '2020-09-26 21:46:32', NULL, NULL);
INSERT INTO `guns_sys_resource` VALUES ('guns-cloud-system$menu$edit_menu', 'guns-cloud-system', 'guns-cloud-system', 'MenuController', 'editMenu', 'Menu', '菜单管理接口', 'guns-cloud-system$menu$edit_menu', '编辑菜单', '192.168.11.1', '/guns-cloud-system/menu/update', 'POST', 'N', 'Y', 'Y', -100, '2020-09-26 21:46:32', NULL, NULL);
INSERT INTO `guns_sys_resource` VALUES ('guns-cloud-system$menu$enable_menu', 'guns-cloud-system', 'guns-cloud-system', 'MenuController', 'enableMenu', 'Menu', '菜单管理接口', 'guns-cloud-system$menu$enable_menu', '启用禁用菜单', '192.168.11.1', '/guns-cloud-system/menu/changeStatus', 'GET', 'N', 'Y', 'Y', -100, '2020-09-26 21:46:32', NULL, NULL);
INSERT INTO `guns_sys_resource` VALUES ('guns-cloud-system$menu$get_left_menu_list', 'guns-cloud-system', 'guns-cloud-system', 'MenuController', 'getLeftMenuList', 'Menu', '菜单管理接口', 'guns-cloud-system$menu$get_left_menu_list', '获取左侧菜单列表', '192.168.11.1', '/guns-cloud-system/menu/getLeftMenuList', 'GET', 'N', 'Y', 'Y', -100, '2020-09-26 21:46:32', NULL, NULL);
INSERT INTO `guns_sys_resource` VALUES ('guns-cloud-system$menu$get_menu_detail', 'guns-cloud-system', 'guns-cloud-system', 'MenuController', 'getMenuDetail', 'Menu', '菜单管理接口', 'guns-cloud-system$menu$get_menu_detail', '获取菜单详情', '192.168.11.1', '/guns-cloud-system/menu/getMenuDetail', 'GET', 'N', 'Y', 'Y', -100, '2020-09-26 21:46:32', NULL, NULL);
INSERT INTO `guns_sys_resource` VALUES ('guns-cloud-system$menu$get_select_menu_tree_list', 'guns-cloud-system', 'guns-cloud-system', 'MenuController', 'getSelectMenuTreeList', 'Menu', '菜单管理接口', 'guns-cloud-system$menu$get_select_menu_tree_list', '获取父级菜单列表', '192.168.11.1', '/guns-cloud-system/menu/getSelectMenuTreeList', 'GET', 'N', 'Y', 'Y', -100, '2020-09-26 21:46:32', NULL, NULL);
INSERT INTO `guns_sys_resource` VALUES ('guns-cloud-system$menu$list_menu', 'guns-cloud-system', 'guns-cloud-system', 'MenuController', 'listMenu', 'Menu', '菜单管理接口', 'guns-cloud-system$menu$list_menu', '获取菜单列表', '192.168.11.1', '/guns-cloud-system/menu/list', 'POST', 'N', 'Y', 'Y', -100, '2020-09-26 21:46:32', NULL, NULL);
INSERT INTO `guns_sys_resource` VALUES ('guns-cloud-system$monitor_menu_flag$druid_menu', 'guns-cloud-system', 'guns-cloud-system', 'MonitorMenuFlagController', 'druidMenu', 'MonitorMenuFlag', '系统监控', 'guns-cloud-system$monitor_menu_flag$druid_menu', 'druid监控', '192.168.11.1', '/guns-cloud-system/systemMonitorMenu/druidMenu', '', 'Y', 'Y', 'Y', -100, '2020-09-26 21:46:28', NULL, NULL);
INSERT INTO `guns_sys_resource` VALUES ('guns-cloud-system$monitor_menu_flag$hard_info_menu', 'guns-cloud-system', 'guns-cloud-system', 'MonitorMenuFlagController', 'hardInfoMenu', 'MonitorMenuFlag', '系统监控', 'guns-cloud-system$monitor_menu_flag$hard_info_menu', '硬件监控', '192.168.11.1', '/guns-cloud-system/systemMonitorMenu/hardInfoMenu', '', 'Y', 'Y', 'Y', -100, '2020-09-26 21:46:28', NULL, NULL);
INSERT INTO `guns_sys_resource` VALUES ('guns-cloud-system$monitor_menu_flag$sys_monitor', 'guns-cloud-system', 'guns-cloud-system', 'MonitorMenuFlagController', 'sysMonitor', 'MonitorMenuFlag', '系统监控', 'guns-cloud-system$monitor_menu_flag$sys_monitor', '系统监控菜单（一级）', '192.168.11.1', '/guns-cloud-system/systemMonitorMenu/sysMonitor', '', 'Y', 'Y', 'Y', -100, '2020-09-26 21:46:28', NULL, NULL);
INSERT INTO `guns_sys_resource` VALUES ('guns-cloud-system$permission$add_permission', 'guns-cloud-system', 'guns-cloud-system', 'PermissionController', 'addPermission', 'Permission', '权限控制', 'guns-cloud-system$permission$add_permission', '新增权限', '192.168.11.1', '/guns-cloud-system/permission/add', 'POST', 'N', 'Y', 'Y', -100, '2020-09-26 21:46:32', NULL, NULL);
INSERT INTO `guns_sys_resource` VALUES ('guns-cloud-system$permission$cancel_permission_bind_resource', 'guns-cloud-system', 'guns-cloud-system', 'PermissionController', 'cancelPermissionBindResource', 'Permission', '权限控制', 'guns-cloud-system$permission$cancel_permission_bind_resource', '解除权限绑定资源', '192.168.11.1', '/guns-cloud-system/permission/cancelPermissionBindResource', 'POST', 'N', 'Y', 'Y', -100, '2020-09-26 21:46:32', NULL, NULL);
INSERT INTO `guns_sys_resource` VALUES ('guns-cloud-system$permission$get_app_button_status', 'guns-cloud-system', 'guns-cloud-system', 'PermissionController', 'getAppButtonStatus', 'Permission', '权限控制', 'guns-cloud-system$permission$get_app_button_status', '获取应用按钮状态', '192.168.11.1', '/guns-cloud-system/permission/getAppButtonStatus', 'GET', 'N', 'Y', 'N', -100, '2020-09-26 21:46:32', NULL, NULL);
INSERT INTO `guns_sys_resource` VALUES ('guns-cloud-system$permission$get_permission_list', 'guns-cloud-system', 'guns-cloud-system', 'PermissionController', 'getPermissionList', 'Permission', '权限控制', 'guns-cloud-system$permission$get_permission_list', '获取所有权限列表', '192.168.11.1', '/guns-cloud-system/permission/getPermissionList', 'GET', 'N', 'Y', 'Y', -100, '2020-09-26 21:46:32', NULL, NULL);
INSERT INTO `guns_sys_resource` VALUES ('guns-cloud-system$permission$get_permission_list4_level', 'guns-cloud-system', 'guns-cloud-system', 'PermissionController', 'getPermissionList4Level', 'Permission', '权限控制', 'guns-cloud-system$permission$get_permission_list4_level', '分级加载权限', '192.168.11.1', '/guns-cloud-system/permission/getPermissionList4Level', 'GET', 'N', 'Y', 'Y', -100, '2020-09-26 21:46:32', NULL, NULL);
INSERT INTO `guns_sys_resource` VALUES ('guns-cloud-system$permission$get_permission_page_list', 'guns-cloud-system', 'guns-cloud-system', 'PermissionController', 'getPermissionPageList', 'Permission', '权限控制', 'guns-cloud-system$permission$get_permission_page_list', '分页获取权限列表', '192.168.11.1', '/guns-cloud-system/permission/getPermissionPageList', 'GET', 'N', 'Y', 'Y', -100, '2020-09-26 21:46:32', NULL, NULL);
INSERT INTO `guns_sys_resource` VALUES ('guns-cloud-system$permission$get_permission_resource_list', 'guns-cloud-system', 'guns-cloud-system', 'PermissionController', 'getPermissionResourceList', 'Permission', '权限控制', 'guns-cloud-system$permission$get_permission_resource_list', '获取权限绑定的资源', '192.168.11.1', '/guns-cloud-system/permission/getPermissionResourceList', 'GET', 'N', 'Y', 'Y', -100, '2020-09-26 21:46:32', NULL, NULL);
INSERT INTO `guns_sys_resource` VALUES ('guns-cloud-system$permission$get_permission_resource_list_detail', 'guns-cloud-system', 'guns-cloud-system', 'PermissionController', 'getPermissionResourceListDetail', 'Permission', '权限控制', 'guns-cloud-system$permission$get_permission_resource_list_detail', '获取权限资源的关系', '192.168.11.1', '/guns-cloud-system/permission/getPermissionResourceRelation', 'GET', 'N', 'Y', 'Y', -100, '2020-09-26 21:46:32', NULL, NULL);
INSERT INTO `guns_sys_resource` VALUES ('guns-cloud-system$permission$get_permission_tree', 'guns-cloud-system', 'guns-cloud-system', 'PermissionController', 'getPermissionTree', 'Permission', '权限控制', 'guns-cloud-system$permission$get_permission_tree', '获取权限树', '192.168.11.1', '/guns-cloud-system/permission/getPermissionTree', 'GET', 'N', 'Y', 'Y', -100, '2020-09-26 21:46:32', NULL, NULL);
INSERT INTO `guns_sys_resource` VALUES ('guns-cloud-system$permission$get_resource_list4_permission', 'guns-cloud-system', 'guns-cloud-system', 'PermissionController', 'getResourceList4Permission', 'Permission', '权限控制', 'guns-cloud-system$permission$get_resource_list4_permission', '获取权限资源列表', '192.168.11.1', '/guns-cloud-system/permission/getResourceList4Permission', 'GET', 'N', 'Y', 'Y', -100, '2020-09-26 21:46:32', NULL, NULL);
INSERT INTO `guns_sys_resource` VALUES ('guns-cloud-system$permission$permission_bind_resource', 'guns-cloud-system', 'guns-cloud-system', 'PermissionController', 'permissionBindResource', 'Permission', '权限控制', 'guns-cloud-system$permission$permission_bind_resource', '权限绑定资源', '192.168.11.1', '/guns-cloud-system/permission/permissionBindResource', 'POST', 'N', 'Y', 'Y', -100, '2020-09-26 21:46:32', NULL, NULL);
INSERT INTO `guns_sys_resource` VALUES ('guns-cloud-system$permission$remove_permission', 'guns-cloud-system', 'guns-cloud-system', 'PermissionController', 'removePermission', 'Permission', '权限控制', 'guns-cloud-system$permission$remove_permission', '权限删除', '192.168.11.1', '/guns-cloud-system/permission/removePermission', 'GET', 'N', 'Y', 'N', -100, '2020-09-26 21:46:32', NULL, NULL);
INSERT INTO `guns_sys_resource` VALUES ('guns-cloud-system$permission$set_permission_status', 'guns-cloud-system', 'guns-cloud-system', 'PermissionController', 'setPermissionStatus', 'Permission', '权限控制', 'guns-cloud-system$permission$set_permission_status', '更改权限状态', '192.168.11.1', '/guns-cloud-system/permission/setPermissionStatus', 'POST', 'N', 'Y', 'Y', -100, '2020-09-26 21:46:32', NULL, NULL);
INSERT INTO `guns_sys_resource` VALUES ('guns-cloud-system$resource$get_resource_select_list', 'guns-cloud-system', 'guns-cloud-system', 'ResourceController', 'getResourceSelectList', 'Resource', '资源管理', 'guns-cloud-system$resource$get_resource_select_list', '获取资源下拉列表', '192.168.11.1', '/guns-cloud-system/resource/getResourceSelectList', 'GET', 'N', 'Y', 'Y', -100, '2020-09-26 21:46:32', NULL, NULL);
INSERT INTO `guns_sys_resource` VALUES ('guns-cloud-system$resource$resource_list', 'guns-cloud-system', 'guns-cloud-system', 'ResourceController', 'resourceList', 'Resource', '资源管理', 'guns-cloud-system$resource$resource_list', '获取资源列表', '192.168.11.1', '/guns-cloud-system/resource/list', 'GET', 'N', 'Y', 'Y', -100, '2020-09-26 21:46:32', NULL, NULL);
INSERT INTO `guns_sys_resource` VALUES ('guns-cloud-system$role$add_role', 'guns-cloud-system', 'guns-cloud-system', 'RoleController', 'addRole', 'Role', '角色管理', 'guns-cloud-system$role$add_role', '添加角色', '192.168.11.1', '/guns-cloud-system/role/add', 'POST', 'N', 'Y', 'Y', -100, '2020-09-26 21:46:32', NULL, NULL);
INSERT INTO `guns_sys_resource` VALUES ('guns-cloud-system$role$assign_permission', 'guns-cloud-system', 'guns-cloud-system', 'RoleController', 'assignPermission', 'Role', '角色管理', 'guns-cloud-system$role$assign_permission', '为角色分配权限', '192.168.11.1', '/guns-cloud-system/role/assign/permission', 'POST', 'N', 'Y', 'Y', -100, '2020-09-26 21:46:32', NULL, NULL);
INSERT INTO `guns_sys_resource` VALUES ('guns-cloud-system$role$delete_role', 'guns-cloud-system', 'guns-cloud-system', 'RoleController', 'deleteRole', 'Role', '角色管理', 'guns-cloud-system$role$delete_role', '删除角色', '192.168.11.1', '/guns-cloud-system/role/delete', 'POST', 'N', 'Y', 'Y', -100, '2020-09-26 21:46:32', NULL, NULL);
INSERT INTO `guns_sys_resource` VALUES ('guns-cloud-system$role$find_all_role', 'guns-cloud-system', 'guns-cloud-system', 'RoleController', 'findAllRole', 'Role', '角色管理', 'guns-cloud-system$role$find_all_role', '获取所有角色列表', '192.168.11.1', '/guns-cloud-system/role/list', 'POST', 'N', 'Y', 'Y', -100, '2020-09-26 21:46:32', NULL, NULL);
INSERT INTO `guns_sys_resource` VALUES ('guns-cloud-system$role$find_permissions', 'guns-cloud-system', 'guns-cloud-system', 'RoleController', 'findPermissions', 'Role', '角色管理', 'guns-cloud-system$role$find_permissions', '查询角色分配的权限', '192.168.11.1', '/guns-cloud-system/role/permissions', 'GET', 'N', 'Y', 'Y', -100, '2020-09-26 21:46:32', NULL, NULL);
INSERT INTO `guns_sys_resource` VALUES ('guns-cloud-system$role$find_role_all_permissions', 'guns-cloud-system', 'guns-cloud-system', 'RoleController', 'findRoleAllPermissions', 'Role', '角色管理', 'guns-cloud-system$role$find_role_all_permissions', '查询角色分配的所有权限', '192.168.11.1', '/guns-cloud-system/role/findRoleAllPermissions', 'POST', 'N', 'Y', 'Y', -100, '2020-09-26 21:46:32', NULL, NULL);
INSERT INTO `guns_sys_resource` VALUES ('guns-cloud-system$role$find_role_detail', 'guns-cloud-system', 'guns-cloud-system', 'RoleController', 'findRoleDetail', 'Role', '角色管理', 'guns-cloud-system$role$find_role_detail', '获取角色详情', '192.168.11.1', '/guns-cloud-system/role/detail', 'POST', 'N', 'Y', 'Y', -100, '2020-09-26 21:46:32', NULL, NULL);
INSERT INTO `guns_sys_resource` VALUES ('guns-cloud-system$role$find_role_page', 'guns-cloud-system', 'guns-cloud-system', 'RoleController', 'findRolePage', 'Role', '角色管理', 'guns-cloud-system$role$find_role_page', '获取角色列表(分页)', '192.168.11.1', '/guns-cloud-system/role/page', 'POST', 'N', 'Y', 'Y', -100, '2020-09-26 21:46:32', NULL, NULL);
INSERT INTO `guns_sys_resource` VALUES ('guns-cloud-system$role$get_all_res_permissions', 'guns-cloud-system', 'guns-cloud-system', 'RoleController', 'getAllResPermissions', 'Role', '角色管理', 'guns-cloud-system$role$get_all_res_permissions', '获取用户所有资源列表', '192.168.11.1', '/guns-cloud-system/role/getAllResPermissions', 'GET', 'N', 'Y', 'Y', -100, '2020-09-26 21:46:32', NULL, NULL);
INSERT INTO `guns_sys_resource` VALUES ('guns-cloud-system$role$get_all_res_permission_urls', 'guns-cloud-system', 'guns-cloud-system', 'RoleController', 'getAllResPermissionUrls', 'Role', '角色管理', 'guns-cloud-system$role$get_all_res_permission_urls', '获取用户所有资源的url列表', '192.168.11.1', '/guns-cloud-system/role/getAllResPermissionUrls', 'GET', 'N', 'Y', 'Y', -100, '2020-09-26 21:46:32', NULL, NULL);
INSERT INTO `guns_sys_resource` VALUES ('guns-cloud-system$role$select_role_by_account_id', 'guns-cloud-system', 'guns-cloud-system', 'RoleController', 'selectRoleByAccountId', 'Role', '角色管理', 'guns-cloud-system$role$select_role_by_account_id', '查询个人角色信息', '192.168.11.1', '/guns-cloud-system/role/selectRoleByAccountId', 'GET', 'N', 'Y', 'Y', -100, '2020-09-26 21:46:32', NULL, NULL);
INSERT INTO `guns_sys_resource` VALUES ('guns-cloud-system$role$set_status', 'guns-cloud-system', 'guns-cloud-system', 'RoleController', 'setStatus', 'Role', '角色管理', 'guns-cloud-system$role$set_status', '设置角色状态', '192.168.11.1', '/guns-cloud-system/role/status', 'POST', 'N', 'Y', 'Y', -100, '2020-09-26 21:46:32', NULL, NULL);
INSERT INTO `guns_sys_resource` VALUES ('guns-cloud-system$role$update_role', 'guns-cloud-system', 'guns-cloud-system', 'RoleController', 'updateRole', 'Role', '角色管理', 'guns-cloud-system$role$update_role', '更新角色', '192.168.11.1', '/guns-cloud-system/role/update', 'POST', 'N', 'Y', 'Y', -100, '2020-09-26 21:46:32', NULL, NULL);
INSERT INTO `guns_sys_resource` VALUES ('guns-cloud-system$role$validate_code', 'guns-cloud-system', 'guns-cloud-system', 'RoleController', 'validateCode', 'Role', '角色管理', 'guns-cloud-system$role$validate_code', '校验角色编码', '192.168.11.1', '/guns-cloud-system/role/validate/code', 'POST', 'N', 'Y', 'Y', -100, '2020-09-26 21:46:32', NULL, NULL);
INSERT INTO `guns_sys_resource` VALUES ('guns-cloud-system$role$validate_name', 'guns-cloud-system', 'guns-cloud-system', 'RoleController', 'validateName', 'Role', '角色管理', 'guns-cloud-system$role$validate_name', '校验角色名称', '192.168.11.1', '/guns-cloud-system/role/validate/name', 'POST', 'N', 'Y', 'Y', -100, '2020-09-26 21:46:32', NULL, NULL);
INSERT INTO `guns_sys_resource` VALUES ('guns-cloud-system$system$get_user_info', 'guns-cloud-system', 'guns-cloud-system', 'SystemController', 'getUserInfo', 'System', '系统信息', 'guns-cloud-system$system$get_user_info', '获取用户基本信息', '192.168.11.1', '/guns-cloud-system/system/getUserInfo', 'GET', 'N', 'Y', 'Y', -100, '2020-09-26 21:46:32', NULL, NULL);
INSERT INTO `guns_sys_resource` VALUES ('guns-cloud-system$system_menu_flag$app_manager', 'guns-cloud-system', 'guns-cloud-system', 'SystemMenuFlagController', 'appManager', 'SystemMenuFlag', '系统菜单', 'guns-cloud-system$system_menu_flag$app_manager', '应用管理', '192.168.11.1', '/guns-cloud-system/systemMenu/appManager', '', 'Y', 'Y', 'Y', -100, '2020-09-26 21:46:28', NULL, NULL);
INSERT INTO `guns_sys_resource` VALUES ('guns-cloud-system$system_menu_flag$dic_manager', 'guns-cloud-system', 'guns-cloud-system', 'SystemMenuFlagController', 'dicManager', 'SystemMenuFlag', '系统菜单', 'guns-cloud-system$system_menu_flag$dic_manager', '字典管理', '192.168.11.1', '/guns-cloud-system/systemMenu/dicManager', '', 'Y', 'Y', 'Y', -100, '2020-09-26 21:46:28', NULL, NULL);
INSERT INTO `guns_sys_resource` VALUES ('guns-cloud-system$system_menu_flag$system', 'guns-cloud-system', 'guns-cloud-system', 'SystemMenuFlagController', 'system', 'SystemMenuFlag', '系统菜单', 'guns-cloud-system$system_menu_flag$system', '系统管理菜单', '192.168.11.1', '/guns-cloud-system/systemMenu/system', '', 'Y', 'Y', 'Y', -100, '2020-09-26 21:46:28', NULL, NULL);
INSERT INTO `guns_sys_resource` VALUES ('guns-cloud-system$sys_button$add', 'guns-cloud-system', 'guns-cloud-system', 'SysButtonController', 'add', 'SysButton', '按钮接口', 'guns-cloud-system$sys_button$add', '添加', '192.168.11.1', '/guns-cloud-system/sysButton/add', 'POST', 'N', 'Y', 'Y', -100, '2020-09-26 21:46:32', NULL, NULL);
INSERT INTO `guns_sys_resource` VALUES ('guns-cloud-system$sys_button$change_status', 'guns-cloud-system', 'guns-cloud-system', 'SysButtonController', 'changeStatus', 'SysButton', '按钮接口', 'guns-cloud-system$sys_button$change_status', '禁用启用', '192.168.11.1', '/guns-cloud-system/sysButton/changeStatus', 'GET', 'N', 'Y', 'Y', -100, '2020-09-26 21:46:32', NULL, NULL);
INSERT INTO `guns_sys_resource` VALUES ('guns-cloud-system$sys_button$delete', 'guns-cloud-system', 'guns-cloud-system', 'SysButtonController', 'delete', 'SysButton', '按钮接口', 'guns-cloud-system$sys_button$delete', '删除', '192.168.11.1', '/guns-cloud-system/sysButton/delete', 'POST', 'N', 'Y', 'Y', -100, '2020-09-26 21:46:32', NULL, NULL);
INSERT INTO `guns_sys_resource` VALUES ('guns-cloud-system$sys_button$get_detail', 'guns-cloud-system', 'guns-cloud-system', 'SysButtonController', 'getDetail', 'SysButton', '按钮接口', 'guns-cloud-system$sys_button$get_detail', '获取按钮详情', '192.168.11.1', '/guns-cloud-system/sysButton/getDetail', 'GET', 'N', 'Y', 'Y', -100, '2020-09-26 21:46:32', NULL, NULL);
INSERT INTO `guns_sys_resource` VALUES ('guns-cloud-system$sys_button$get_resources', 'guns-cloud-system', 'guns-cloud-system', 'SysButtonController', 'getResources', 'SysButton', '按钮接口', 'guns-cloud-system$sys_button$get_resources', '获取资源级联列表', '192.168.11.1', '/guns-cloud-system/sysButton/getResources', 'GET', 'N', 'Y', 'N', -100, '2020-09-26 21:46:32', NULL, NULL);
INSERT INTO `guns_sys_resource` VALUES ('guns-cloud-system$sys_button$query_list_page', 'guns-cloud-system', 'guns-cloud-system', 'SysButtonController', 'queryListPage', 'SysButton', '按钮接口', 'guns-cloud-system$sys_button$query_list_page', '分页查询列表', '192.168.11.1', '/guns-cloud-system/sysButton/queryListPage', 'GET', 'N', 'Y', 'Y', -100, '2020-09-26 21:46:32', NULL, NULL);
INSERT INTO `guns_sys_resource` VALUES ('guns-cloud-system$sys_button$update', 'guns-cloud-system', 'guns-cloud-system', 'SysButtonController', 'update', 'SysButton', '按钮接口', 'guns-cloud-system$sys_button$update', '修改', '192.168.11.1', '/guns-cloud-system/sysButton/update', 'POST', 'N', 'Y', 'Y', -100, '2020-09-26 21:46:32', NULL, NULL);
INSERT INTO `guns_sys_resource` VALUES ('guns-cloud-system$sys_log$op_log_delete', 'guns-cloud-system', 'guns-cloud-system', 'SysLogController', 'opLogDelete', 'SysLog', '日志管理', 'guns-cloud-system$sys_log$op_log_delete', '操作日志_清空', '192.168.11.1', '/guns-cloud-system/sysOpLog/delete', 'POST', 'N', 'Y', 'Y', -100, '2020-09-26 21:46:32', NULL, NULL);
INSERT INTO `guns_sys_resource` VALUES ('guns-cloud-system$sys_log$op_log_page', 'guns-cloud-system', 'guns-cloud-system', 'SysLogController', 'opLogPage', 'SysLog', '日志管理', 'guns-cloud-system$sys_log$op_log_page', '操作日志_查询', '192.168.11.1', '/guns-cloud-system/sysOpLog/page', 'GET', 'N', 'Y', 'Y', -100, '2020-09-26 21:46:32', NULL, NULL);
INSERT INTO `guns_sys_resource` VALUES ('guns-cloud-system$sys_machine$query', 'guns-cloud-system', 'guns-cloud-system', 'SysMachineController', 'query', 'SysMachine', '系统监控', 'guns-cloud-system$sys_machine$query', '系统属性监控', '192.168.11.1', '/guns-cloud-system/sysMachine/query', 'GET', 'N', 'Y', 'N', -100, '2020-09-26 21:46:32', NULL, NULL);
INSERT INTO `guns_sys_resource` VALUES ('guns-cloud-system$tenant_info$add', 'guns-cloud-system', 'guns-cloud-system', 'TenantInfoController', 'add', 'TenantInfo', '多租户管理', 'guns-cloud-system$tenant_info$add', '新增租户', '192.168.11.1', '/guns-cloud-system/tenantInfo/add', 'POST', 'N', 'Y', 'Y', -100, '2020-09-26 21:46:32', NULL, NULL);
INSERT INTO `guns_sys_resource` VALUES ('guns-cloud-system$tenant_info$delete', 'guns-cloud-system', 'guns-cloud-system', 'TenantInfoController', 'delete', 'TenantInfo', '多租户管理', 'guns-cloud-system$tenant_info$delete', '删除租户', '192.168.11.1', '/guns-cloud-system/tenantInfo/delete', 'POST', 'N', 'Y', 'Y', -100, '2020-09-26 21:46:32', NULL, NULL);
INSERT INTO `guns_sys_resource` VALUES ('guns-cloud-system$tenant_info$detail', 'guns-cloud-system', 'guns-cloud-system', 'TenantInfoController', 'detail', 'TenantInfo', '多租户管理', 'guns-cloud-system$tenant_info$detail', '租户详情', '192.168.11.1', '/guns-cloud-system/tenantInfo/detail', 'GET', 'N', 'Y', 'Y', -100, '2020-09-26 21:46:32', NULL, NULL);
INSERT INTO `guns_sys_resource` VALUES ('guns-cloud-system$tenant_info$edit', 'guns-cloud-system', 'guns-cloud-system', 'TenantInfoController', 'edit', 'TenantInfo', '多租户管理', 'guns-cloud-system$tenant_info$edit', '编辑租户', '192.168.11.1', '/guns-cloud-system/tenantInfo/edit', 'POST', 'N', 'Y', 'Y', -100, '2020-09-26 21:46:32', NULL, NULL);
INSERT INTO `guns_sys_resource` VALUES ('guns-cloud-system$tenant_info$list_tenants', 'guns-cloud-system', 'guns-cloud-system', 'TenantInfoController', 'listTenants', 'TenantInfo', '多租户管理', 'guns-cloud-system$tenant_info$list_tenants', '获取租户列表', '192.168.11.1', '/guns-cloud-system/tenantInfo/listTenants', 'GET', 'N', 'Y', 'Y', -100, '2020-09-26 21:46:32', NULL, NULL);
INSERT INTO `guns_sys_resource` VALUES ('guns-cloud-system$tenant_info$page', 'guns-cloud-system', 'guns-cloud-system', 'TenantInfoController', 'page', 'TenantInfo', '多租户管理', 'guns-cloud-system$tenant_info$page', '查询租户列表', '192.168.11.1', '/guns-cloud-system/tenantInfo/page', 'GET', 'N', 'Y', 'Y', -100, '2020-09-26 21:46:32', NULL, NULL);
INSERT INTO `guns_sys_resource` VALUES ('guns-cloud-workflow$common$role_list', 'guns-cloud-workflow', 'guns-cloud-workflow', 'CommonController', 'roleList', 'Common', '工作流人员管理', 'guns-cloud-workflow$common$role_list', '获取角色列表', '192.168.11.1', '/guns-cloud-workflow/common/roleList', 'GET', 'N', 'Y', 'Y', -100, '2020-09-26 10:04:39', NULL, NULL);
INSERT INTO `guns_sys_resource` VALUES ('guns-cloud-workflow$common$user_list_by_role_id', 'guns-cloud-workflow', 'guns-cloud-workflow', 'CommonController', 'userListByRoleId', 'Common', '工作流人员管理', 'guns-cloud-workflow$common$user_list_by_role_id', '获取角色下用户列表（分页）', '192.168.11.1', '/guns-cloud-workflow/common/userListByRoleId', 'GET', 'N', 'Y', 'Y', -100, '2020-09-26 10:04:39', NULL, NULL);
INSERT INTO `guns_sys_resource` VALUES ('guns-cloud-workflow$deploy_process$active', 'guns-cloud-workflow', 'guns-cloud-workflow', 'DeployProcessController', 'active', 'DeployProcess', '流程部署管理接口', 'guns-cloud-workflow$deploy_process$active', '激活部署的流程', '192.168.11.1', '/guns-cloud-workflow/deployProcess/active', 'POST', 'N', 'Y', 'Y', -100, '2020-09-26 10:04:39', NULL, NULL);
INSERT INTO `guns_sys_resource` VALUES ('guns-cloud-workflow$deploy_process$delete', 'guns-cloud-workflow', 'guns-cloud-workflow', 'DeployProcessController', 'delete', 'DeployProcess', '流程部署管理接口', 'guns-cloud-workflow$deploy_process$delete', '删除已部署的流程', '192.168.11.1', '/guns-cloud-workflow/deployProcess/delete', 'POST', 'N', 'Y', 'Y', -100, '2020-09-26 10:04:39', NULL, NULL);
INSERT INTO `guns_sys_resource` VALUES ('guns-cloud-workflow$deploy_process$download_zip', 'guns-cloud-workflow', 'guns-cloud-workflow', 'DeployProcessController', 'downloadZip', 'DeployProcess', '流程部署管理接口', 'guns-cloud-workflow$deploy_process$download_zip', '打包下载已部署流程图', '192.168.11.1', '/guns-cloud-workflow/deployProcess/downloadZip', 'POST', 'N', 'Y', 'Y', -100, '2020-09-26 10:04:39', NULL, NULL);
INSERT INTO `guns_sys_resource` VALUES ('guns-cloud-workflow$deploy_process$export', 'guns-cloud-workflow', 'guns-cloud-workflow', 'DeployProcessController', 'export', 'DeployProcess', '流程部署管理接口', 'guns-cloud-workflow$deploy_process$export', '导出已部署流程图', '192.168.11.1', '/guns-cloud-workflow/deployProcess/export', 'POST', 'N', 'Y', 'Y', -100, '2020-09-26 10:04:39', NULL, NULL);
INSERT INTO `guns_sys_resource` VALUES ('guns-cloud-workflow$deploy_process$import_process', 'guns-cloud-workflow', 'guns-cloud-workflow', 'DeployProcessController', 'importProcess', 'DeployProcess', '流程部署管理接口', 'guns-cloud-workflow$deploy_process$import_process', '导入流程资源', '192.168.11.1', '/guns-cloud-workflow/deployProcess/importProcess', 'POST', 'N', 'Y', 'Y', -100, '2020-09-26 10:04:39', NULL, NULL);
INSERT INTO `guns_sys_resource` VALUES ('guns-cloud-workflow$deploy_process$list', 'guns-cloud-workflow', 'guns-cloud-workflow', 'DeployProcessController', 'list', 'DeployProcess', '流程部署管理接口', 'guns-cloud-workflow$deploy_process$list', '获取流程部署列表(分页)', '192.168.11.1', '/guns-cloud-workflow/deployProcess/list', 'POST', 'N', 'Y', 'Y', -100, '2020-09-26 10:04:39', NULL, NULL);
INSERT INTO `guns_sys_resource` VALUES ('guns-cloud-workflow$deploy_process$mapping', 'guns-cloud-workflow', 'guns-cloud-workflow', 'DeployProcessController', 'mapping', 'DeployProcess', '流程部署管理接口', 'guns-cloud-workflow$deploy_process$mapping', '映射模型', '192.168.11.1', '/guns-cloud-workflow/deployProcess/mapping', 'POST', 'N', 'Y', 'Y', -100, '2020-09-26 10:04:39', NULL, NULL);
INSERT INTO `guns_sys_resource` VALUES ('guns-cloud-workflow$deploy_process$suspend', 'guns-cloud-workflow', 'guns-cloud-workflow', 'DeployProcessController', 'suspend', 'DeployProcess', '流程部署管理接口', 'guns-cloud-workflow$deploy_process$suspend', '挂起部署的流程', '192.168.11.1', '/guns-cloud-workflow/deployProcess/suspend', 'POST', 'N', 'Y', 'Y', -100, '2020-09-26 10:04:39', NULL, NULL);
INSERT INTO `guns_sys_resource` VALUES ('guns-cloud-workflow$deploy_process$version_list', 'guns-cloud-workflow', 'guns-cloud-workflow', 'DeployProcessController', 'versionList', 'DeployProcess', '流程部署管理接口', 'guns-cloud-workflow$deploy_process$version_list', '获取流程部署版本列表(分页)', '192.168.11.1', '/guns-cloud-workflow/deployProcess/versionList', 'POST', 'N', 'Y', 'Y', -100, '2020-09-26 10:04:39', NULL, NULL);
INSERT INTO `guns_sys_resource` VALUES ('guns-cloud-workflow$deploy_process$view_pic', 'guns-cloud-workflow', 'guns-cloud-workflow', 'DeployProcessController', 'viewPic', 'DeployProcess', '流程部署管理接口', 'guns-cloud-workflow$deploy_process$view_pic', '查看已部署流程图', '192.168.11.1', '/guns-cloud-workflow/deployProcess/viewPic', 'POST', 'N', 'Y', 'Y', -100, '2020-09-26 10:04:39', NULL, NULL);
INSERT INTO `guns_sys_resource` VALUES ('guns-cloud-workflow$model$add_item', 'guns-cloud-workflow', 'guns-cloud-workflow', 'ModelController', 'addItem', 'Model', '模型部署管理接口', 'guns-cloud-workflow$model$add_item', '创建模型', '192.168.11.1', '/guns-cloud-workflow/model/addItem', 'POST', 'N', 'Y', 'Y', -100, '2020-09-26 10:04:39', NULL, NULL);
INSERT INTO `guns_sys_resource` VALUES ('guns-cloud-workflow$model$delete', 'guns-cloud-workflow', 'guns-cloud-workflow', 'ModelController', 'delete', 'Model', '模型部署管理接口', 'guns-cloud-workflow$model$delete', '删除模型', '192.168.11.1', '/guns-cloud-workflow/model/delete', 'POST', 'N', 'Y', 'Y', -100, '2020-09-26 10:04:39', NULL, NULL);
INSERT INTO `guns_sys_resource` VALUES ('guns-cloud-workflow$model$deploy', 'guns-cloud-workflow', 'guns-cloud-workflow', 'ModelController', 'deploy', 'Model', '模型部署管理接口', 'guns-cloud-workflow$model$deploy', '部署模型', '192.168.11.1', '/guns-cloud-workflow/model/deploy', 'POST', 'N', 'Y', 'Y', -100, '2020-09-26 10:04:39', NULL, NULL);
INSERT INTO `guns_sys_resource` VALUES ('guns-cloud-workflow$model$list', 'guns-cloud-workflow', 'guns-cloud-workflow', 'ModelController', 'list', 'Model', '模型部署管理接口', 'guns-cloud-workflow$model$list', '获取模型列表(分页)', '192.168.11.1', '/guns-cloud-workflow/model/list', 'POST', 'N', 'Y', 'Y', -100, '2020-09-26 10:04:39', NULL, NULL);
INSERT INTO `guns_sys_resource` VALUES ('guns-cloud-workflow$model$pre_view', 'guns-cloud-workflow', 'guns-cloud-workflow', 'ModelController', 'preView', 'Model', '模型部署管理接口', 'guns-cloud-workflow$model$pre_view', '预览模型xml', '192.168.11.1', '/guns-cloud-workflow/model/preView', 'POST', 'N', 'Y', 'Y', -100, '2020-09-26 10:04:39', NULL, NULL);
INSERT INTO `guns_sys_resource` VALUES ('guns-cloud-workflow$monitor_process$active', 'guns-cloud-workflow', 'guns-cloud-workflow', 'MonitorProcessController', 'active', 'MonitorProcess', '流程监控管理接口', 'guns-cloud-workflow$monitor_process$active', '激活运行中流程', '192.168.11.1', '/guns-cloud-workflow/monitorProcess/active', 'POST', 'N', 'Y', 'Y', -100, '2020-09-26 10:04:39', NULL, NULL);
INSERT INTO `guns_sys_resource` VALUES ('guns-cloud-workflow$monitor_process$change_assignee', 'guns-cloud-workflow', 'guns-cloud-workflow', 'MonitorProcessController', 'changeAssignee', 'MonitorProcess', '流程监控管理接口', 'guns-cloud-workflow$monitor_process$change_assignee', '转办运行中流程', '192.168.11.1', '/guns-cloud-workflow/monitorProcess/changeAssignee', 'POST', 'N', 'Y', 'Y', -100, '2020-09-26 10:04:39', NULL, NULL);
INSERT INTO `guns_sys_resource` VALUES ('guns-cloud-workflow$monitor_process$end', 'guns-cloud-workflow', 'guns-cloud-workflow', 'MonitorProcessController', 'end', 'MonitorProcess', '流程监控管理接口', 'guns-cloud-workflow$monitor_process$end', '结束运行中流程', '192.168.11.1', '/guns-cloud-workflow/monitorProcess/end', 'POST', 'N', 'Y', 'Y', -100, '2020-09-26 10:04:39', NULL, NULL);
INSERT INTO `guns_sys_resource` VALUES ('guns-cloud-workflow$monitor_process$end_list', 'guns-cloud-workflow', 'guns-cloud-workflow', 'MonitorProcessController', 'endList', 'MonitorProcess', '流程监控管理接口', 'guns-cloud-workflow$monitor_process$end_list', '获取已结束流程列表(分页)', '192.168.11.1', '/guns-cloud-workflow/monitorProcess/endList', 'POST', 'N', 'Y', 'Y', -100, '2020-09-26 10:04:39', NULL, NULL);
INSERT INTO `guns_sys_resource` VALUES ('guns-cloud-workflow$monitor_process$run_list', 'guns-cloud-workflow', 'guns-cloud-workflow', 'MonitorProcessController', 'runList', 'MonitorProcess', '流程监控管理接口', 'guns-cloud-workflow$monitor_process$run_list', '获取运行中流程列表(分页)', '192.168.11.1', '/guns-cloud-workflow/monitorProcess/runList', 'POST', 'N', 'Y', 'Y', -100, '2020-09-26 10:04:39', NULL, NULL);
INSERT INTO `guns_sys_resource` VALUES ('guns-cloud-workflow$monitor_process$suspend', 'guns-cloud-workflow', 'guns-cloud-workflow', 'MonitorProcessController', 'suspend', 'MonitorProcess', '流程监控管理接口', 'guns-cloud-workflow$monitor_process$suspend', '挂起运行中流程', '192.168.11.1', '/guns-cloud-workflow/monitorProcess/suspend', 'POST', 'N', 'Y', 'Y', -100, '2020-09-26 10:04:39', NULL, NULL);
INSERT INTO `guns_sys_resource` VALUES ('guns-cloud-workflow$my_leave$add_item', 'guns-cloud-workflow', 'guns-cloud-workflow', 'MyLeaveController', 'addItem', 'MyLeave', '请假申请接口', 'guns-cloud-workflow$my_leave$add_item', '发起请假', '192.168.11.1', '/guns-cloud-workflow/leave/startLeave', 'POST', 'N', 'Y', 'Y', -100, '2020-09-26 10:04:44', NULL, NULL);
INSERT INTO `guns_sys_resource` VALUES ('guns-cloud-workflow$my_leave$batch_delete_by_ids', 'guns-cloud-workflow', 'guns-cloud-workflow', 'MyLeaveController', 'batchDeleteByIds', 'MyLeave', '请假申请接口', 'guns-cloud-workflow$my_leave$batch_delete_by_ids', '删除请假', '192.168.11.1', '/guns-cloud-workflow/leave/batchDelete', 'POST', 'N', 'Y', 'Y', -100, '2020-09-26 10:04:44', NULL, NULL);
INSERT INTO `guns_sys_resource` VALUES ('guns-cloud-workflow$my_leave$delete', 'guns-cloud-workflow', 'guns-cloud-workflow', 'MyLeaveController', 'delete', 'MyLeave', '请假申请接口', 'guns-cloud-workflow$my_leave$delete', '删除请假', '192.168.11.1', '/guns-cloud-workflow/leave/delete', 'POST', 'N', 'Y', 'Y', -100, '2020-09-26 10:04:44', NULL, NULL);
INSERT INTO `guns_sys_resource` VALUES ('guns-cloud-workflow$my_leave$detail', 'guns-cloud-workflow', 'guns-cloud-workflow', 'MyLeaveController', 'detail', 'MyLeave', '请假申请接口', 'guns-cloud-workflow$my_leave$detail', '查看详情接口', '192.168.11.1', '/guns-cloud-workflow/leave/detail', 'POST', 'N', 'Y', 'Y', -100, '2020-09-26 10:04:44', NULL, NULL);
INSERT INTO `guns_sys_resource` VALUES ('guns-cloud-workflow$my_leave$get_leaves', 'guns-cloud-workflow', 'guns-cloud-workflow', 'MyLeaveController', 'getLeaves', 'MyLeave', '请假申请接口', 'guns-cloud-workflow$my_leave$get_leaves', '获取请假类型', '192.168.11.1', '/guns-cloud-workflow/leave/getLeaves', 'POST', 'N', 'Y', 'Y', -100, '2020-09-26 10:04:44', NULL, NULL);
INSERT INTO `guns_sys_resource` VALUES ('guns-cloud-workflow$my_leave$list', 'guns-cloud-workflow', 'guns-cloud-workflow', 'MyLeaveController', 'list', 'MyLeave', '请假申请接口', 'guns-cloud-workflow$my_leave$list', '请假单查询列表', '192.168.11.1', '/guns-cloud-workflow/leave/list', 'POST', 'N', 'Y', 'Y', -100, '2020-09-26 10:04:44', NULL, NULL);
INSERT INTO `guns_sys_resource` VALUES ('guns-cloud-workflow$task$candidate_list', 'guns-cloud-workflow', 'guns-cloud-workflow', 'TaskController', 'candidateList', 'Task', '任务管理接口', 'guns-cloud-workflow$task$candidate_list', '获取候选人列表', '192.168.11.1', '/guns-cloud-workflow/task/candidateList', 'POST', 'N', 'Y', 'Y', -100, '2020-09-26 10:04:44', NULL, NULL);
INSERT INTO `guns_sys_resource` VALUES ('guns-cloud-workflow$task$done_list', 'guns-cloud-workflow', 'guns-cloud-workflow', 'TaskController', 'doneList', 'Task', '任务管理接口', 'guns-cloud-workflow$task$done_list', '获取已办任务列表', '192.168.11.1', '/guns-cloud-workflow/task/doneList', 'POST', 'N', 'Y', 'Y', -100, '2020-09-26 10:04:44', NULL, NULL);
INSERT INTO `guns_sys_resource` VALUES ('guns-cloud-workflow$task$done_task', 'guns-cloud-workflow', 'guns-cloud-workflow', 'TaskController', 'doneTask', 'Task', '任务管理接口', 'guns-cloud-workflow$task$done_task', '审批任务', '192.168.11.1', '/guns-cloud-workflow/task/doneTask', 'POST', 'N', 'Y', 'Y', -100, '2020-09-26 10:04:44', NULL, NULL);
INSERT INTO `guns_sys_resource` VALUES ('guns-cloud-workflow$task$entrust', 'guns-cloud-workflow', 'guns-cloud-workflow', 'TaskController', 'entrust', 'Task', '任务管理接口', 'guns-cloud-workflow$task$entrust', '委托任务', '192.168.11.1', '/guns-cloud-workflow/task/entrust', 'POST', 'N', 'Y', 'Y', -100, '2020-09-26 10:04:44', NULL, NULL);
INSERT INTO `guns_sys_resource` VALUES ('guns-cloud-workflow$task$todo_list', 'guns-cloud-workflow', 'guns-cloud-workflow', 'TaskController', 'todoList', 'Task', '任务管理接口', 'guns-cloud-workflow$task$todo_list', '获取待办任务列表', '192.168.11.1', '/guns-cloud-workflow/task/todoList', 'POST', 'N', 'Y', 'Y', -100, '2020-09-26 10:04:44', NULL, NULL);
INSERT INTO `guns_sys_resource` VALUES ('guns-cloud-workflow$task$view_task_detail', 'guns-cloud-workflow', 'guns-cloud-workflow', 'TaskController', 'viewTaskDetail', 'Task', '任务管理接口', 'guns-cloud-workflow$task$view_task_detail', '获取任务详情', '192.168.11.1', '/guns-cloud-workflow/task/viewTaskDetail', 'POST', 'N', 'Y', 'Y', -100, '2020-09-26 10:04:44', NULL, NULL);
INSERT INTO `guns_sys_resource` VALUES ('guns-cloud-workflow$workflow_menus$alreadys', 'guns-cloud-workflow', 'guns-cloud-workflow', 'WorkflowMenusController', 'alreadys', 'WorkflowMenus', '工作流相关菜单', 'guns-cloud-workflow$workflow_menus$alreadys', '已办任务', '192.168.11.1', '/guns-cloud-workflow/workflowMenus/alreadys', '', 'Y', 'Y', 'Y', -100, '2020-09-26 10:04:34', NULL, NULL);
INSERT INTO `guns_sys_resource` VALUES ('guns-cloud-workflow$workflow_menus$apply_for', 'guns-cloud-workflow', 'guns-cloud-workflow', 'WorkflowMenusController', 'applyFor', 'WorkflowMenus', '工作流相关菜单', 'guns-cloud-workflow$workflow_menus$apply_for', '申请审批', '192.168.11.1', '/guns-cloud-workflow/workflowMenus/applyFor', '', 'Y', 'Y', 'Y', -100, '2020-09-26 10:04:34', NULL, NULL);
INSERT INTO `guns_sys_resource` VALUES ('guns-cloud-workflow$workflow_menus$flow_manager', 'guns-cloud-workflow', 'guns-cloud-workflow', 'WorkflowMenusController', 'flowManager', 'WorkflowMenus', '工作流相关菜单', 'guns-cloud-workflow$workflow_menus$flow_manager', '流程管理', '192.168.11.1', '/guns-cloud-workflow/workflowMenus/flowManager', '', 'Y', 'Y', 'Y', -100, '2020-09-26 10:04:34', NULL, NULL);
INSERT INTO `guns_sys_resource` VALUES ('guns-cloud-workflow$workflow_menus$history_flow', 'guns-cloud-workflow', 'guns-cloud-workflow', 'WorkflowMenusController', 'historyFlow', 'WorkflowMenus', '工作流相关菜单', 'guns-cloud-workflow$workflow_menus$history_flow', '历史流程', '192.168.11.1', '/guns-cloud-workflow/workflowMenus/historyFlow', '', 'Y', 'Y', 'Y', -100, '2020-09-26 10:04:34', NULL, NULL);
INSERT INTO `guns_sys_resource` VALUES ('guns-cloud-workflow$workflow_menus$leave', 'guns-cloud-workflow', 'guns-cloud-workflow', 'WorkflowMenusController', 'leave', 'WorkflowMenus', '工作流相关菜单', 'guns-cloud-workflow$workflow_menus$leave', '请假审批', '192.168.11.1', '/guns-cloud-workflow/workflowMenus/leave', '', 'Y', 'Y', 'Y', -100, '2020-09-26 10:04:34', NULL, NULL);
INSERT INTO `guns_sys_resource` VALUES ('guns-cloud-workflow$workflow_menus$mode_manager', 'guns-cloud-workflow', 'guns-cloud-workflow', 'WorkflowMenusController', 'modeManager', 'WorkflowMenus', '工作流相关菜单', 'guns-cloud-workflow$workflow_menus$mode_manager', '模型管理', '192.168.11.1', '/guns-cloud-workflow/workflowMenus/modeManager', '', 'Y', 'Y', 'Y', -100, '2020-09-26 10:04:34', NULL, NULL);
INSERT INTO `guns_sys_resource` VALUES ('guns-cloud-workflow$workflow_menus$running_flow', 'guns-cloud-workflow', 'guns-cloud-workflow', 'WorkflowMenusController', 'runningFlow', 'WorkflowMenus', '工作流相关菜单', 'guns-cloud-workflow$workflow_menus$running_flow', '运行中流程', '192.168.11.1', '/guns-cloud-workflow/workflowMenus/runningFlow', '', 'Y', 'Y', 'Y', -100, '2020-09-26 10:04:34', NULL, NULL);
INSERT INTO `guns_sys_resource` VALUES ('guns-cloud-workflow$workflow_menus$tasks', 'guns-cloud-workflow', 'guns-cloud-workflow', 'WorkflowMenusController', 'tasks', 'WorkflowMenus', '工作流相关菜单', 'guns-cloud-workflow$workflow_menus$tasks', '任务管理', '192.168.11.1', '/guns-cloud-workflow/workflowMenus/tasks', '', 'Y', 'Y', 'Y', -100, '2020-09-26 10:04:34', NULL, NULL);
INSERT INTO `guns_sys_resource` VALUES ('guns-cloud-workflow$workflow_menus$waitings', 'guns-cloud-workflow', 'guns-cloud-workflow', 'WorkflowMenusController', 'waitings', 'WorkflowMenus', '工作流相关菜单', 'guns-cloud-workflow$workflow_menus$waitings', '待办任务', '192.168.11.1', '/guns-cloud-workflow/workflowMenus/waitings', '', 'Y', 'Y', 'Y', -100, '2020-09-26 10:04:34', NULL, NULL);
INSERT INTO `guns_sys_resource` VALUES ('guns-cloud-workflow$workflow_menus$workflow', 'guns-cloud-workflow', 'guns-cloud-workflow', 'WorkflowMenusController', 'workflow', 'WorkflowMenus', '工作流相关菜单', 'guns-cloud-workflow$workflow_menus$workflow', '工作流程', '192.168.11.1', '/guns-cloud-workflow/workflowMenus/workflow', '', 'Y', 'Y', 'Y', -100, '2020-09-26 10:04:34', NULL, NULL);

-- ----------------------------
-- Table structure for guns_sys_role
-- ----------------------------
DROP TABLE IF EXISTS `guns_sys_role`;
CREATE TABLE `guns_sys_role`  (
  `role_id` bigint(20) NOT NULL COMMENT '角色id',
  `role_code` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '角色编码',
  `role_name` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '角色名称',
  `role_desc` varchar(1000) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '角色描述',
  `app_id` bigint(20) NULL DEFAULT NULL COMMENT '应用id',
  `order_no` decimal(11, 2) NULL DEFAULT NULL COMMENT '排序码',
  `status` int(11) NULL DEFAULT NULL COMMENT '状态(1:启用  2:禁用)',
  `create_time` datetime(0) NULL DEFAULT NULL COMMENT '创建时间',
  `create_user` bigint(20) NULL DEFAULT NULL COMMENT '创建人',
  `update_time` datetime(0) NULL DEFAULT NULL COMMENT '更新时间',
  `update_user` bigint(20) NULL DEFAULT NULL COMMENT '更新人',
  `del_flag` char(1) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT 'N' COMMENT '删除标记(Y:已删除 N:未删除)',
  PRIMARY KEY (`role_id`) USING BTREE,
  UNIQUE INDEX `CODE_UNI`(`role_code`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_general_ci COMMENT = '角色表' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of guns_sys_role
-- ----------------------------
INSERT INTO `guns_sys_role` VALUES (1184285447301570561, 'system_role', '系统管理员角色', '这是系统管理员角色。', 1174270350789906434, 1.00, 1, '2019-10-16 01:50:28', -100, '2019-10-27 02:49:47', 1184285445204418561, 'N');
INSERT INTO `guns_sys_role` VALUES (1202576699124899841, 'systemManageRole', '统一认证后台管理员', NULL, 1202565232069730305, 2.00, 1, '2019-12-05 13:13:23', 1184285445204418561, NULL, NULL, 'N');
INSERT INTO `guns_sys_role` VALUES (1210187704524890114, 'workflow-code', '系统管理员', NULL, 1208723761432387585, 1.00, 1, '2019-12-26 13:16:48', 1184285445204418561, NULL, NULL, 'N');

-- ----------------------------
-- Table structure for guns_sys_role_permission
-- ----------------------------
DROP TABLE IF EXISTS `guns_sys_role_permission`;
CREATE TABLE `guns_sys_role_permission`  (
  `role_permission_id` bigint(20) NOT NULL COMMENT '角色权限id',
  `role_id` bigint(20) NOT NULL COMMENT '角色ID',
  `permission_id` bigint(20) NOT NULL COMMENT '权限ID',
  PRIMARY KEY (`role_permission_id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_general_ci COMMENT = '角色权限表' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of guns_sys_role_permission
-- ----------------------------
INSERT INTO `guns_sys_role_permission` VALUES (1186275533976027137, 1184285447301570561, 1184285448379506690);
INSERT INTO `guns_sys_role_permission` VALUES (1202577202131001345, 1202576699124899841, 1202572387757875202);
INSERT INTO `guns_sys_role_permission` VALUES (1210187721486655490, 1210187704524890114, 1210187481379528705);

-- ----------------------------
-- Table structure for guns_sys_user_role
-- ----------------------------
DROP TABLE IF EXISTS `guns_sys_user_role`;
CREATE TABLE `guns_sys_user_role`  (
  `user_role_id` bigint(20) NOT NULL COMMENT '用户角色id',
  `account_id` bigint(20) NOT NULL COMMENT '账户id',
  `role_id` bigint(20) NOT NULL COMMENT '角色id',
  PRIMARY KEY (`user_role_id`) USING BTREE,
  INDEX `role_id`(`role_id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_general_ci COMMENT = '用户角色表' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of guns_sys_user_role
-- ----------------------------
INSERT INTO `guns_sys_user_role` VALUES (1184285447838441473, 1184285445204418561, 1202576699124899841);
INSERT INTO `guns_sys_user_role` VALUES (1184285447838441474, 1184285445204418561, 1184285447301570561);
INSERT INTO `guns_sys_user_role` VALUES (1210022881778491395, 1184285445204418561, 1210187704524890114);

-- ----------------------------
-- Table structure for sys_tenant_info
-- ----------------------------
DROP TABLE IF EXISTS `sys_tenant_info`;
CREATE TABLE `sys_tenant_info`  (
  `id` bigint(20) NOT NULL COMMENT '主键id',
  `name` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '租户名称',
  `code` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '租户的编码',
  `db_name` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '关联的数据库名称',
  `create_time` datetime(0) NULL DEFAULT NULL COMMENT '创建时间',
  `create_user` bigint(20) NULL DEFAULT NULL COMMENT '创建人',
  `update_time` datetime(0) NULL DEFAULT NULL COMMENT '更新时间',
  `update_user` bigint(20) NULL DEFAULT NULL COMMENT '更新人',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_general_ci COMMENT = '租户表' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of sys_tenant_info
-- ----------------------------
INSERT INTO `sys_tenant_info` VALUES (1301724123547000811, '总公司（管理单位）', 'default', 'master', '2020-09-04 11:29:51', 1265476890672672808, NULL, NULL);
INSERT INTO `sys_tenant_info` VALUES (1309673072097333249, '北京公司', 'beijingqweqweqw', 'guns_tenant_db_beijingqweqweqw', '2020-09-26 09:56:08', -100, NULL, NULL);

SET FOREIGN_KEY_CHECKS = 1;
