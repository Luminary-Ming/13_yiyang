/*
 Navicat Premium Dump SQL

 Source Server         : 本机
 Source Server Type    : MySQL
 Source Server Version : 80100 (8.1.0)
 Source Host           : localhost:3306
 Source Schema         : yiyang

 Target Server Type    : MySQL
 Target Server Version : 80100 (8.1.0)
 File Encoding         : 65001

 Date: 26/09/2024 18:38:07
*/

SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
-- Table structure for bed
-- ----------------------------
DROP TABLE IF EXISTS `bed`;
CREATE TABLE `bed`  (
  `id` int NOT NULL AUTO_INCREMENT COMMENT '主键',
  `num` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL COMMENT '床位编号',
  `brid` int NOT NULL COMMENT '所属楼宇中的房间',
  `status` int NOT NULL COMMENT '是否被占用 （0：占用、1：未占用、2：外出）',
  `is_delete` int NULL DEFAULT 0 COMMENT '是否被弃用   1：弃用   0：使用',
  PRIMARY KEY (`id`) USING BTREE
) COMMENT = '床位表';

-- ----------------------------
-- Table structure for build_room
-- ----------------------------
DROP TABLE IF EXISTS `build_room`;
CREATE TABLE `build_room`  (
  `id` int NOT NULL AUTO_INCREMENT COMMENT '主键',
  `bid` int NOT NULL COMMENT '楼宇id',
  `rid` int NOT NULL COMMENT '房间id',
  `type` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL COMMENT '房间户型',
  `direction` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL COMMENT '房间朝向',
  `img_url` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL COMMENT '房间图片',
  `description` text CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL COMMENT '房间详细描述',
  `is_delete` int NULL DEFAULT NULL COMMENT '是否被弃用   1：弃用   0：使用',
  PRIMARY KEY (`id`) USING BTREE
) COMMENT = '房间与楼宇关系表';

-- ----------------------------
-- Table structure for building
-- ----------------------------
DROP TABLE IF EXISTS `building`;
CREATE TABLE `building`  (
  `id` int NOT NULL AUTO_INCREMENT COMMENT '主键',
  `name` varchar(20) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL COMMENT '楼宇名称',
  `height` int NOT NULL COMMENT '层数',
  `color` varchar(20) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '楼宇颜色',
  `is_delete` int NULL DEFAULT NULL COMMENT '是否被弃用   1：弃用   0：使用',
  `create_time` datetime NULL DEFAULT NULL COMMENT '创建时间',
  PRIMARY KEY (`id`) USING BTREE
) COMMENT = '楼宇表';

-- ----------------------------
-- Table structure for customer
-- ----------------------------
DROP TABLE IF EXISTS `customer`;
CREATE TABLE `customer`  (
  `id` int NOT NULL AUTO_INCREMENT COMMENT '主键',
  `cname` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '入住人员姓名',
  `img_url` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '入住人员照片',
  `gender` varchar(10) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '入住人员性别',
  `age` int NULL DEFAULT NULL COMMENT '入住人员年龄',
  `idcard` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '入住人员身份证',
  `check_in` datetime NULL DEFAULT NULL COMMENT '入住时间',
  `expiration` datetime NULL DEFAULT NULL COMMENT '到期时间',
  `telephone` varchar(20) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '入住人员电话',
  `status` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '入住人员状态',
  `mark` text CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL COMMENT '入住人员备注',
  `bid` int NULL DEFAULT NULL COMMENT '入住人员床位',
  `is_delete` int NULL DEFAULT NULL COMMENT '是否删除',
  PRIMARY KEY (`id`) USING BTREE
) COMMENT = '入住人员表';

-- ----------------------------
-- Table structure for dishes
-- ----------------------------
DROP TABLE IF EXISTS `dishes`;
CREATE TABLE `dishes`  (
  `dish_id` int NOT NULL AUTO_INCREMENT COMMENT '菜品ID',
  `name` varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL COMMENT '菜品名称',
  `calories` int NULL DEFAULT NULL COMMENT '热量',
  `protein` float NULL DEFAULT NULL COMMENT '蛋白质含量(g)',
  `fat` float NULL DEFAULT NULL COMMENT '脂肪含量(g)',
  `carbohydrates` float NULL DEFAULT NULL COMMENT '碳水化合物含量(g)',
  `ingredients` text CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL COMMENT '原料列表(可使用JSON格式)',
  `special_diet` enum('LowSalt','SugarFree','Vegetarian','GlutenFree') CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '适用特殊饮食',
  PRIMARY KEY (`dish_id`) USING BTREE
) COMMENT = '菜品信息表';

-- ----------------------------
-- Table structure for employee
-- ----------------------------
DROP TABLE IF EXISTS `employee`;
CREATE TABLE `employee`  (
  `employee_id` int NOT NULL AUTO_INCREMENT COMMENT '员工ID，自增主键',
  `full_name` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL COMMENT '员工姓名',
  `gender` enum('M','F','O') CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT 'O' COMMENT '性别：M-男性, F-女性, O-其他或未指定',
  `date_of_birth` date NULL DEFAULT NULL COMMENT '出生日期',
  `phone_number` varchar(15) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '联系电话',
  `email` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '电子邮件地址，唯一',
  `address` text CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL COMMENT '家庭住址',
  `hire_date` date NULL DEFAULT NULL COMMENT '入职日期',
  `username` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '登录账号，唯一',
  `password` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '登录密码，建议加密存储',
  PRIMARY KEY (`employee_id`) USING BTREE,
  UNIQUE INDEX `email`(`email` ASC) USING BTREE,
  UNIQUE INDEX `username`(`username` ASC) USING BTREE
) COMMENT = '员工基本信息表';

-- ----------------------------
-- Table structure for employee_role
-- ----------------------------
DROP TABLE IF EXISTS `employee_role`;
CREATE TABLE `employee_role`  (
  `employee_id` bigint NOT NULL COMMENT '员工ID，关联employee表的主键',
  `role_id` bigint NOT NULL COMMENT '角色ID，关联role表的主键',
  PRIMARY KEY (`employee_id`, `role_id`) USING BTREE
) COMMENT = '用于建立员工和角色之间多对多关系的关联表';

-- ----------------------------
-- Table structure for employees_chedule
-- ----------------------------
DROP TABLE IF EXISTS `employees_chedule`;
CREATE TABLE `employees_chedule`  (
  `schedule_id` int NOT NULL AUTO_INCREMENT COMMENT '排班ID，自增主键',
  `employee_id` int NOT NULL COMMENT '员工ID，外键参考Employee表',
  `date` date NOT NULL COMMENT '排班日期',
  `shift` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '班次（早班、中班、晚班等）',
  `assigned_area` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '负责区域或岗位',
  `status` enum('上班','休息','请假') CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT '上班' COMMENT '工作状态',
  PRIMARY KEY (`schedule_id`) USING BTREE
) COMMENT = '员工排班表';

-- ----------------------------
-- Table structure for guardian
-- ----------------------------
DROP TABLE IF EXISTS `guardian`;
CREATE TABLE `guardian`  (
  `id` int NOT NULL AUTO_INCREMENT COMMENT '主键',
  `cid` int NOT NULL COMMENT '入住人id',
  `name` varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL COMMENT '监护人姓名',
  `gender` varchar(2) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL COMMENT '监护人性别',
  `age` int NOT NULL COMMENT '监护人年龄',
  `relation` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL COMMENT '监护人与入住人员关系',
  `idcard` varchar(18) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL COMMENT '监护人身份证',
  `address` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL COMMENT '监护人住址',
  `telephone` varchar(11) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL COMMENT '监护人电话',
  PRIMARY KEY (`id`) USING BTREE
) COMMENT = '监护人表';

-- ----------------------------
-- Table structure for health_profiles
-- ----------------------------
DROP TABLE IF EXISTS `health_profiles`;
CREATE TABLE `health_profiles`  (
  `profile_id` int NOT NULL AUTO_INCREMENT COMMENT '健康档案唯一标识',
  `dietary_restrictions` text CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL COMMENT '饮食限制说明',
  `allergies` text CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL COMMENT '过敏信息',
  `medical_conditions` text CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL COMMENT '医疗状况描述',
  `cid` int NULL DEFAULT NULL COMMENT '关联入住人员ID',
  PRIMARY KEY (`profile_id`) USING BTREE,
  UNIQUE INDEX `resident_id`(`cid` ASC) USING BTREE
) COMMENT = '入住人员健康档案信息';

-- ----------------------------
-- Table structure for ingredients
-- ----------------------------
DROP TABLE IF EXISTS `ingredients`;
CREATE TABLE `ingredients`  (
  `ingredient_id` int NOT NULL AUTO_INCREMENT COMMENT '食材ID',
  `name` varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL COMMENT '食材名称',
  `unit` varchar(20) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL COMMENT '计量单位',
  `unit_price` decimal(10, 2) NOT NULL COMMENT '单价',
  `supplier_id` int NOT NULL COMMENT '供应商ID',
  PRIMARY KEY (`ingredient_id`) USING BTREE
) COMMENT = '食材信息表';

-- ----------------------------
-- Table structure for meal_serving_records
-- ----------------------------
DROP TABLE IF EXISTS `meal_serving_records`;
CREATE TABLE `meal_serving_records`  (
  `record_id` int NOT NULL AUTO_INCREMENT COMMENT '用餐记录ID',
  `resident_id` int NOT NULL COMMENT '居民ID',
  `menu_id` int NOT NULL COMMENT '菜单ID',
  `serving_time` time NOT NULL COMMENT '供餐时间',
  `notes` text CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL COMMENT '备注',
  PRIMARY KEY (`record_id`) USING BTREE
) COMMENT = '用餐服务记录';

-- ----------------------------
-- Table structure for menus
-- ----------------------------
DROP TABLE IF EXISTS `menus`;
CREATE TABLE `menus`  (
  `menu_id` int NOT NULL AUTO_INCREMENT COMMENT '菜单ID',
  `meal_type` enum('Breakfast','Lunch','Dinner','Special') CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL COMMENT '餐别',
  `date` date NOT NULL COMMENT '菜单日期',
  `dish_id` int NOT NULL COMMENT '关联菜品ID',
  PRIMARY KEY (`menu_id`) USING BTREE
) COMMENT = '每日菜谱';

-- ----------------------------
-- Table structure for nutritionplans
-- ----------------------------
DROP TABLE IF EXISTS `nutritionplans`;
CREATE TABLE `nutritionplans`  (
  `plan_id` int NOT NULL AUTO_INCREMENT COMMENT '营养计划ID',
  `cid` int NOT NULL COMMENT '关联入住人员ID',
  `start_date` date NOT NULL COMMENT '计划开始日期',
  `end_date` date NOT NULL COMMENT '计划结束日期',
  `daily_calories` int NULL DEFAULT NULL COMMENT '每日推荐热量摄入',
  `special_instructions` text CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL COMMENT '特殊饮食指导',
  PRIMARY KEY (`plan_id`) USING BTREE
) COMMENT = '入住人员个性化营养计划';

-- ----------------------------
-- Table structure for outing_registers
-- ----------------------------
DROP TABLE IF EXISTS `outing_registers`;
CREATE TABLE `outing_registers`  (
  `outing_id` int NOT NULL AUTO_INCREMENT COMMENT '外出登记ID，主键，自增',
  `cid` int NOT NULL COMMENT '入住人员ID，外键关联居民信息表',
  `outing_date` date NOT NULL COMMENT '外出日期',
  `departure_time` time NULL DEFAULT NULL COMMENT '预计离院时间',
  `return_time` datetime NULL DEFAULT NULL COMMENT '预计返院时间',
  `destination` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '外出目的地',
  `purpose` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '外出目的',
  `accompanying_person` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '陪同人员姓名（如有）',
  `accompanying_phone` varchar(15) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '陪同人员联系方式（如有）',
  `status` enum('待审核','已批准','已外出','已返回','取消') CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL DEFAULT '待审核' COMMENT '外出状态',
  `created_at` timestamp NULL DEFAULT CURRENT_TIMESTAMP COMMENT '记录创建时间',
  `updated_at` timestamp NULL DEFAULT NULL ON UPDATE CURRENT_TIMESTAMP COMMENT '记录最后更新时间',
  PRIMARY KEY (`outing_id`) USING BTREE
) COMMENT = '颐养中心入住人员外出登记表';

-- ----------------------------
-- Table structure for purchases
-- ----------------------------
DROP TABLE IF EXISTS `purchases`;
CREATE TABLE `purchases`  (
  `purchase_id` int NOT NULL AUTO_INCREMENT COMMENT '采购记录ID',
  `ingredient_id` int NOT NULL COMMENT '食材ID',
  `quantity` int NOT NULL COMMENT '采购数量',
  `order_date` date NOT NULL COMMENT '下单日期',
  `delivery_date` date NULL DEFAULT NULL COMMENT '预计到货日期',
  `supplier_id` int NOT NULL COMMENT '供应商ID',
  PRIMARY KEY (`purchase_id`) USING BTREE
) COMMENT = '食材采购记录';

-- ----------------------------
-- Table structure for role
-- ----------------------------
DROP TABLE IF EXISTS `role`;
CREATE TABLE `role`  (
  `role_id` int NOT NULL AUTO_INCREMENT COMMENT '角色ID，自增主键',
  `role_name` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL COMMENT '角色名称',
  `description` text CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL COMMENT '角色描述',
  PRIMARY KEY (`role_id`) USING BTREE
) COMMENT = '角色表';

-- ----------------------------
-- Table structure for role_menu
-- ----------------------------
DROP TABLE IF EXISTS `role_menu`;
CREATE TABLE `role_menu`  (
  `role_id` bigint NOT NULL COMMENT '角色ID，关联role表的主键',
  `menu_id` bigint NOT NULL COMMENT '菜单ID，关联sys_menu表的主键',
  PRIMARY KEY (`role_id`, `menu_id`) USING BTREE
) COMMENT = '用于建立角色和菜单之间多对多关系的关联表';

-- ----------------------------
-- Table structure for room
-- ----------------------------
DROP TABLE IF EXISTS `room`;
CREATE TABLE `room`  (
  `id` int NOT NULL AUTO_INCREMENT COMMENT '主键',
  `num` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL COMMENT '房间编号',
  `floor` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL COMMENT '所属楼层',
  `is_delete` int NULL DEFAULT NULL COMMENT '是否被弃用   1：弃用   0：使用',
  PRIMARY KEY (`id`) USING BTREE
) COMMENT = '房间表';

-- ----------------------------
-- Table structure for suppliers
-- ----------------------------
DROP TABLE IF EXISTS `suppliers`;
CREATE TABLE `suppliers`  (
  `supplier_id` int NOT NULL AUTO_INCREMENT COMMENT '供应商ID',
  `name` varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL COMMENT '公司名称',
  `contact_person` varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL COMMENT '联系人姓名',
  `phone` varchar(15) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL COMMENT '联系电话',
  `address` varchar(200) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL COMMENT '地址',
  PRIMARY KEY (`supplier_id`) USING BTREE
) COMMENT = '供应商信息表';

-- ----------------------------
-- Table structure for sys_menu
-- ----------------------------
DROP TABLE IF EXISTS `sys_menu`;
CREATE TABLE `sys_menu`  (
  `menu_id` bigint NOT NULL AUTO_INCREMENT COMMENT '菜单ID，主键，自动递增',
  `menu_name` varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL COMMENT '菜单名称',
  `parent_id` bigint NULL DEFAULT NULL COMMENT '父级菜单ID，null表示一级菜单',
  `url` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '菜单对应的URL路径',
  `icon` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '菜单图标，显示在前端界面中的图标',
  `sort_order` int NULL DEFAULT 0 COMMENT '菜单排序号，用于在前端展示的菜单顺序',
  `status` tinyint NULL DEFAULT 1 COMMENT '菜单状态，0为无效，1为有效',
  `created_at` timestamp NULL DEFAULT CURRENT_TIMESTAMP COMMENT '菜单记录创建时间',
  PRIMARY KEY (`menu_id`) USING BTREE,
  INDEX `parent_id`(`parent_id` ASC) USING BTREE,
  CONSTRAINT `sys_menu_ibfk_1` FOREIGN KEY (`parent_id`) REFERENCES `sys_menu` (`menu_id`) ON DELETE CASCADE ON UPDATE RESTRICT
) COMMENT = '存储系统中所有菜单项的表，包括一级和二级菜单';

-- ----------------------------
-- Table structure for training_record
-- ----------------------------
DROP TABLE IF EXISTS `training_record`;
CREATE TABLE `training_record`  (
  `record_id` int NOT NULL AUTO_INCREMENT COMMENT '培训记录ID，自增主键',
  `employee_id` int NOT NULL COMMENT '员工ID，外键参考Employee表',
  `training_topic` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL COMMENT '培训主题',
  `trainer` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '培训师姓名',
  `training_date` date NOT NULL COMMENT '培训日期',
  `duration_hours` decimal(4, 2) NULL DEFAULT NULL COMMENT '培训时长（小时）',
  `certificate` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '证书编号或通过状态',
  PRIMARY KEY (`record_id`) USING BTREE
) COMMENT = '员工培训记录表';

-- ----------------------------
-- Table structure for visitors
-- ----------------------------
DROP TABLE IF EXISTS `visitors`;
CREATE TABLE `visitors`  (
  `visitor_id` int NOT NULL AUTO_INCREMENT COMMENT '访客ID，主键，自增',
  `name` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL COMMENT '访客姓名',
  `identity_card` varchar(18) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL COMMENT '身份证号，用于身份验证',
  `phone_number` varchar(15) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL COMMENT '联系电话',
  `in_date` datetime NULL DEFAULT NULL COMMENT '到访时间',
  `out_date` datetime NULL DEFAULT NULL COMMENT '离开时间',
  `email` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '电子邮箱地址',
  `emergency_contact_name` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '紧急联系人姓名',
  `emergency_contact_phone` varchar(15) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '紧急联系人电话',
  `vehicle_license_plate` varchar(10) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '车辆车牌号',
  `vehicle_color` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '车辆颜色',
  `vehicle_brand` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '车辆品牌',
  PRIMARY KEY (`visitor_id`) USING BTREE
) COMMENT = '访客信息表，包含基本的访客信息及车辆信息';

SET FOREIGN_KEY_CHECKS = 1;
