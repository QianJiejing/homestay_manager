/*
 Navicat Premium Data Transfer

 Source Server         : localhost
 Source Server Type    : MySQL
 Source Server Version : 80032
 Source Host           : localhost:3308
 Source Schema         : homestay_manager

 Target Server Type    : MySQL
 Target Server Version : 80032
 File Encoding         : 65001

 Date: 04/04/2024 14:15:35
*/

SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
-- Table structure for admin
-- ----------------------------
DROP TABLE IF EXISTS `admin`;
CREATE TABLE `admin`  (
  `id` int NOT NULL AUTO_INCREMENT COMMENT 'ID',
  `username` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NULL DEFAULT NULL COMMENT '用户名',
  `password` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NULL DEFAULT NULL COMMENT '密码',
  `name` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NULL DEFAULT NULL COMMENT '姓名',
  `avatar` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NULL DEFAULT NULL COMMENT '头像',
  `role` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NULL DEFAULT NULL COMMENT '角色标识',
  `phone` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NULL DEFAULT NULL COMMENT '电话',
  `email` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NULL DEFAULT NULL COMMENT '邮箱',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 8 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_unicode_ci COMMENT = '管理员' ROW_FORMAT = DYNAMIC;

-- ----------------------------
-- Records of admin
-- ----------------------------
INSERT INTO `admin` VALUES (1, 'admin', '123456', '管理员', 'http://localhost:9090/HomestayAPI/files/1697438073596-avatar.png', 'ADMIN', '13677889921', 'admin@xm.com');
INSERT INTO `admin` VALUES (4, 'admin2', '123456', '小管', 'http://localhost:9090/HomestayAPI/files/1709716136348-cloud.webp', 'ADMIN', '13625252546', 'xiaoguan@xm.com');
INSERT INTO `admin` VALUES (5, 'kunkun', '123456', '坤坤', 'http://localhost:9090/HomestayAPI/files/1709726186556-kunkun.webp', 'ADMIN', '18345626562', 'kunkun@ms.com');

-- ----------------------------
-- Table structure for checkin
-- ----------------------------
DROP TABLE IF EXISTS `checkin`;
CREATE TABLE `checkin`  (
  `id` int NOT NULL AUTO_INCREMENT COMMENT 'ID',
  `order_id` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NULL DEFAULT NULL COMMENT '订单编号',
  `user_id` int NULL DEFAULT NULL COMMENT '用户ID',
  `homestay_id` int NULL DEFAULT NULL COMMENT '民宿ID',
  `type_id` int NULL DEFAULT NULL COMMENT '房型ID',
  `in_time` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NULL DEFAULT NULL COMMENT '入住时间',
  `out_time` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NULL DEFAULT NULL COMMENT '退房时间',
  `room_id` int NULL DEFAULT NULL COMMENT '客房ID',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 37 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_unicode_ci COMMENT = '入住登记表' ROW_FORMAT = DYNAMIC;

-- ----------------------------
-- Records of checkin
-- ----------------------------
INSERT INTO `checkin` VALUES (22, '20240302100205', 15, 15, 18, '2024-02-23', '2024-03-02', 30);
INSERT INTO `checkin` VALUES (23, '20240229004205', 18, 18, 26, '2024-02-16', '2024-03-09', 63);
INSERT INTO `checkin` VALUES (24, '20240229004205', 18, 18, 26, '2024-02-16', '2024-03-09', 64);
INSERT INTO `checkin` VALUES (25, '20240229002124', 20, 15, 16, '2024-01-10', '2024-03-11', 19);
INSERT INTO `checkin` VALUES (26, '20240229002110', 20, 15, 16, '2024-01-04', '2024-03-13', 20);
INSERT INTO `checkin` VALUES (27, '20240229002155', 20, 15, 16, '2024-02-15', '2024-03-20', 23);
INSERT INTO `checkin` VALUES (28, '20240329180408', 18, 21, 34, '2024-03-01', '2024-03-29', 80);
INSERT INTO `checkin` VALUES (36, '20240329221114', 18, 16, 19, '2024-03-29', '2024-03-30', 32);
INSERT INTO `checkin` VALUES (37, '20240330092136', 18, 16, 19, '2024-03-30', NULL, 33);

-- ----------------------------
-- Table structure for collect
-- ----------------------------
DROP TABLE IF EXISTS `collect`;
CREATE TABLE `collect`  (
  `id` int NOT NULL AUTO_INCREMENT COMMENT 'ID',
  `user_id` int NOT NULL COMMENT '用户ID',
  `type_id` int NOT NULL COMMENT '房型ID',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 29 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_unicode_ci COMMENT = '收藏信息表' ROW_FORMAT = DYNAMIC;

-- ----------------------------
-- Records of collect
-- ----------------------------
INSERT INTO `collect` VALUES (13, 19, 23);
INSERT INTO `collect` VALUES (15, 19, 28);
INSERT INTO `collect` VALUES (17, 18, 25);
INSERT INTO `collect` VALUES (18, 17, 21);
INSERT INTO `collect` VALUES (21, 15, 21);
INSERT INTO `collect` VALUES (22, 15, 22);
INSERT INTO `collect` VALUES (23, 15, 18);
INSERT INTO `collect` VALUES (24, 17, 17);
INSERT INTO `collect` VALUES (25, 18, 16);
INSERT INTO `collect` VALUES (26, 17, 24);
INSERT INTO `collect` VALUES (27, 15, 24);
INSERT INTO `collect` VALUES (29, 18, 19);

-- ----------------------------
-- Table structure for comment
-- ----------------------------
DROP TABLE IF EXISTS `comment`;
CREATE TABLE `comment`  (
  `id` int NOT NULL AUTO_INCREMENT COMMENT 'ID',
  `content` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NULL DEFAULT NULL COMMENT '评论内容',
  `time` datetime NULL DEFAULT NULL COMMENT '评论时间',
  `user_id` int NULL DEFAULT NULL COMMENT '评论人ID',
  `type_id` int NULL DEFAULT NULL COMMENT '房型ID',
  `role` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NULL DEFAULT NULL COMMENT '角色标识',
  `parent_id` int NULL DEFAULT NULL COMMENT '父节点ID',
  `homestay_id` int NULL DEFAULT NULL COMMENT '民宿ID',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 85 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_unicode_ci COMMENT = '评论信息表' ROW_FORMAT = DYNAMIC;

-- ----------------------------
-- Records of comment
-- ----------------------------
INSERT INTO `comment` VALUES (18, '我是老六', '2024-02-29 00:47:21', 17, 21, 'USER', 0, 16);
INSERT INTO `comment` VALUES (19, '老六住过都说好', '2024-02-29 00:47:40', 17, 21, 'USER', 0, 16);
INSERT INTO `comment` VALUES (20, '666', '2024-02-29 00:48:04', 17, 21, 'USER', 18, 16);
INSERT INTO `comment` VALUES (21, '住的很舒服', '2024-03-09 10:07:14', 18, 26, 'USER', 0, 18);
INSERT INTO `comment` VALUES (22, '价格便宜', '2024-03-09 10:26:34', 15, 18, 'USER', 0, 15);
INSERT INTO `comment` VALUES (25, '666', '2024-03-12 14:46:04', 17, 21, 'HOMESTAY', 20, 16);
INSERT INTO `comment` VALUES (26, '哈哈哈哈，谢谢', '2024-03-12 15:08:13', 17, 21, 'HOMESTAY', 20, 16);
INSERT INTO `comment` VALUES (27, '是的', '2024-03-12 16:49:36', 17, 21, 'USER', 19, 16);
INSERT INTO `comment` VALUES (28, '住的很舒服', '2024-03-13 00:04:09', 17, 21, 'USER', 0, 16);
INSERT INTO `comment` VALUES (29, '真的', '2024-03-13 00:04:37', 17, 21, 'USER', 28, 16);
INSERT INTO `comment` VALUES (32, '谢谢支持', '2024-03-13 00:24:56', 15, 18, 'HOMESTAY', 22, 15);
INSERT INTO `comment` VALUES (33, '谢谢', '2024-03-13 10:31:18', 18, 26, 'HOMESTAY', 21, 18);
INSERT INTO `comment` VALUES (34, '炒鸡舒服', '2024-03-13 10:38:39', 0, 25, 'HOMESTAY', 0, 18);
INSERT INTO `comment` VALUES (38, '11', '2024-03-14 09:49:08', 17, 24, 'USER', 0, 17);
INSERT INTO `comment` VALUES (39, '222', '2024-03-14 11:14:33', 18, 24, 'USER', 38, 17);
INSERT INTO `comment` VALUES (43, '住的舒服不？', '2024-03-14 12:12:43', 18, 24, 'USER', 0, 17);
INSERT INTO `comment` VALUES (44, '超级舒服双人房，欢迎大家来预订', '2024-03-14 12:29:00', 0, 24, 'HOMESTAY', 0, 17);
INSERT INTO `comment` VALUES (45, '333', '2024-03-14 17:49:43', 17, 24, 'USER', 39, 17);
INSERT INTO `comment` VALUES (47, '444', '2024-03-14 21:45:00', 17, 24, 'USER', 45, 17);
INSERT INTO `comment` VALUES (48, '好的，下次一定', '2024-03-15 12:30:08', 17, 24, 'USER', 44, 17);
INSERT INTO `comment` VALUES (49, '哈哈哈，笑发财了', '2024-03-15 12:31:07', 18, 24, 'USER', 48, 17);
INSERT INTO `comment` VALUES (50, '嘻嘻', '2024-03-15 12:31:24', 17, 24, 'USER', 49, 17);
INSERT INTO `comment` VALUES (52, '555', '2024-03-15 13:25:43', 17, 24, 'HOMESTAY', 47, 17);
INSERT INTO `comment` VALUES (53, '亲，这次一定！', '2024-03-15 13:40:58', 17, 24, 'HOMESTAY', 48, 17);
INSERT INTO `comment` VALUES (55, '777', '2024-03-15 13:43:18', 17, 24, 'HOMESTAY', 38, 17);
INSERT INTO `comment` VALUES (64, 'OK 有机会一定去', '2024-03-19 17:50:28', 17, 24, 'USER', 53, 17);
INSERT INTO `comment` VALUES (66, '888', '2024-03-19 23:12:55', 17, 24, 'USER', 55, 17);
INSERT INTO `comment` VALUES (75, '66', '2024-03-29 18:33:16', 18, 20, 'USER', 0, 16);
INSERT INTO `comment` VALUES (76, '1', '2024-03-29 18:33:20', 18, 20, 'USER', 75, 16);
INSERT INTO `comment` VALUES (85, '看起来环境很好', '2024-03-29 23:54:58', 18, 19, 'USER', 0, 16);

-- ----------------------------
-- Table structure for homestay
-- ----------------------------
DROP TABLE IF EXISTS `homestay`;
CREATE TABLE `homestay`  (
  `id` int NOT NULL AUTO_INCREMENT COMMENT 'ID',
  `username` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NULL DEFAULT NULL COMMENT '民宿账号',
  `password` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NULL DEFAULT NULL COMMENT '密码',
  `name` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NULL DEFAULT NULL COMMENT '民宿名称',
  `price` double(10, 2) NULL DEFAULT NULL COMMENT '价格',
  `phone` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NULL DEFAULT NULL COMMENT '电话',
  `email` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NULL DEFAULT NULL COMMENT '邮箱',
  `url` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NULL DEFAULT NULL COMMENT '官网',
  `description` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NULL DEFAULT NULL COMMENT '介绍',
  `role` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NULL DEFAULT NULL COMMENT '角色',
  `status` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NULL DEFAULT NULL COMMENT '审核状态',
  `avatar` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NULL DEFAULT NULL COMMENT '民宿图片',
  `com_num` int NULL DEFAULT 0 COMMENT '点评数',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 21 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_unicode_ci COMMENT = '民宿信息表' ROW_FORMAT = DYNAMIC;

-- ----------------------------
-- Records of homestay
-- ----------------------------
INSERT INTO `homestay` VALUES (15, '7d', '123456', '7天北路民宿', 120.00, '13526656521', '7d@ms.com', '无', '7天北路民宿，带给你舒适与怡人的体验。在这里，民风淳朴，让你感受到家的温馨。精心设计的房间，宽敞明亮，每一处细节都彰显着用心。在这里，你可以尽情享受悠闲的时光，感受大自然的美好。7天北路民宿，让你的旅程更加难忘。', 'HOMESTAY', '审核通过', 'http://localhost:9090/HomestayAPI/files/1709133701288-7天北路民宿.jpg', 2);
INSERT INTO `homestay` VALUES (16, 'cl', '123456', '从林里野奢民宿', 280.00, '13923654541', 'cl@ms.com', '无', '民宿坐落于从化区天然资源丰富的从林里风景区内，四周被郁郁葱葱的山林环绕，环境幽静宜人，空气清新怡人。', 'HOMESTAY', '审核通过', 'http://localhost:9090/HomestayAPI/files/1709134680673-从林里野奢民宿.jpg', 11);
INSERT INTO `homestay` VALUES (17, 'ms', '123456', '民宿房东（田心村店）', 180.00, '15356552522', 'ms@ms.com', '无', '有人在这里种田采茶，等你诗归。旗下民宿分店：东篱小筑、西禾里、东城里、月亮谷、路见小屋、时光里、渔林里、星星小居、汉田居、粤湖客栈、甜屋', 'HOMESTAY', '审核通过', 'http://localhost:9090/HomestayAPI/files/1709135179260-民宿房东（田心村店）.jpg', 14);
INSERT INTO `homestay` VALUES (18, 'my', '123456', '陌野尘外美宿', 260.00, '18925525626', 'my@ms.com', '无', '民宿位于和睦村，民宿纯白色建筑墙体，极简干净。最吸引人的是民宿自带的水上乐园泳池，边吸氧，边欣赏广袤山野，还可在户外露台，伴着鸟鸣与星空用餐！拥有前后花园，前花园500平，300平草地，后花园（围炉煮茶区域、烧烤区、泡池区、篝火区、舞台区）、独立KTV、独立麻将娱乐室、可提供会议室投影。', 'HOMESTAY', '审核通过', 'http://localhost:9090/HomestayAPI/files/1709135496449-陌野尘外美宿.jpg', 3);
INSERT INTO `homestay` VALUES (19, 'yf', '123456', '一凡鸣舍', 188.00, '15695564532', 'yf@ms.com', '无', '一凡鸣舍——艺术之家', 'HOMESTAY', '待审核', 'http://localhost:9090/HomestayAPI/files/1709809291882-一凡鸣舍.jpg', 0);

-- ----------------------------
-- Table structure for imsingle
-- ----------------------------
DROP TABLE IF EXISTS `imsingle`;
CREATE TABLE `imsingle`  (
  `id` int NOT NULL AUTO_INCREMENT COMMENT 'ID',
  `content` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NULL DEFAULT NULL COMMENT '聊天内容',
  `fromuser` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NULL DEFAULT NULL COMMENT '发送人',
  `fromavatar` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NULL DEFAULT NULL COMMENT '发送人头像',
  `time` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NULL DEFAULT NULL COMMENT '时间',
  `type` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NULL DEFAULT NULL COMMENT '类型',
  `touser` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NULL DEFAULT NULL COMMENT '接收人',
  `toavatar` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NULL DEFAULT NULL COMMENT '接收人头像',
  `readed` int NULL DEFAULT 0 COMMENT '是否已读',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 55 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_unicode_ci COMMENT = '单人聊天表' ROW_FORMAT = DYNAMIC;

-- ----------------------------
-- Records of imsingle
-- ----------------------------
INSERT INTO `imsingle` VALUES (26, '1', 'HOMESTAY_7天北路民宿', 'http://localhost:9090/HomestayAPI/files/1709133701288-7天北路民宿.jpg', '2024-03-06 16:00:20', 'text', 'ADMIN_管理员', 'http://localhost:9090/HomestayAPI/files/1697438073596-avatar.png', 1);
INSERT INTO `imsingle` VALUES (27, '啊', 'ADMIN_管理员', 'http://localhost:9090/HomestayAPI/files/1697438073596-avatar.png', '2024-03-06 16:01:16', 'text', 'HOMESTAY_7天北路民宿', 'http://localhost:9090/HomestayAPI/files/1709133701288-7天北路民宿.jpg', 1);
INSERT INTO `imsingle` VALUES (28, '3', 'ADMIN_管理员', 'http://localhost:9090/HomestayAPI/files/1697438073596-avatar.png', '2024-03-06 16:01:28', 'text', 'HOMESTAY_7天北路民宿', 'http://localhost:9090/HomestayAPI/files/1709133701288-7天北路民宿.jpg', 1);
INSERT INTO `imsingle` VALUES (29, '😀你好啊', 'ADMIN_管理员', 'http://localhost:9090/HomestayAPI/files/1697438073596-avatar.png', '2024-03-06 16:48:42', 'text', 'HOMESTAY_7天北路民宿', 'http://localhost:9090/HomestayAPI/files/1709133701288-7天北路民宿.jpg', 1);
INSERT INTO `imsingle` VALUES (31, '我们的系统终于上线了', 'ADMIN_管理员', 'http://localhost:9090/HomestayAPI/files/1697438073596-avatar.png', '2024-03-06 17:10:14', 'text', 'ADMIN_小管', 'http://localhost:9090/HomestayAPI/files/1709716136348-cloud.webp', 1);
INSERT INTO `imsingle` VALUES (32, '现在有房吗', 'USER_灰大王', 'http://localhost:9090/HomestayAPI/files/1709136959637-huidawang.jpg', '2024-03-06 19:19:14', 'text', 'HOMESTAY_陌野尘外美宿', 'http://localhost:9090/HomestayAPI/files/1709135496449-陌野尘外美宿.jpg', 1);
INSERT INTO `imsingle` VALUES (33, '还有的，灰太狼先生😄', 'HOMESTAY_陌野尘外美宿', 'http://localhost:9090/HomestayAPI/files/1709135496449-陌野尘外美宿.jpg', '2024-03-06 19:21:03', 'text', 'USER_灰大王', 'http://localhost:9090/HomestayAPI/files/1709136959637-huidawang.jpg', 1);
INSERT INTO `imsingle` VALUES (34, '老六，你好，我是灰太狼😁', 'USER_灰大王', 'http://localhost:9090/HomestayAPI/files/1709136959637-huidawang.jpg', '2024-03-06 19:27:14', 'text', 'USER_老六', 'http://localhost:9090/HomestayAPI/files/1709136644473-laoliu.webp', 1);
INSERT INTO `imsingle` VALUES (35, '我坤哥真厉害', 'HOMESTAY_7天北路民宿', 'http://localhost:9090/HomestayAPI/files/1709133701288-7天北路民宿.jpg', '2024-03-06 19:59:34', 'text', 'ADMIN_坤坤', 'http://localhost:9090/HomestayAPI/files/1709726186556-kunkun.webp', 1);
INSERT INTO `imsingle` VALUES (36, '谢谢你夸我💗', 'ADMIN_坤坤', 'http://localhost:9090/HomestayAPI/files/1709726186556-kunkun.webp', '2024-03-06 20:01:37', 'text', 'HOMESTAY_7天北路民宿', 'http://localhost:9090/HomestayAPI/files/1709133701288-7天北路民宿.jpg', 1);
INSERT INTO `imsingle` VALUES (37, '你好，请问双人房还有吗', 'USER_李四', 'http://localhost:9090/HomestayAPI/files/1709133959703-lisi.webp', '2024-03-09 10:27:12', 'text', 'HOMESTAY_7天北路民宿', 'http://localhost:9090/HomestayAPI/files/1709133701288-7天北路民宿.jpg', 1);
INSERT INTO `imsingle` VALUES (38, '还有的，李四先生😊', 'HOMESTAY_7天北路民宿', 'http://localhost:9090/HomestayAPI/files/1709133701288-7天北路民宿.jpg', '2024-03-09 10:27:42', 'text', 'USER_李四', 'http://localhost:9090/HomestayAPI/files/1709133959703-lisi.webp', 1);
INSERT INTO `imsingle` VALUES (39, '你好，你的民宿审核通过了', 'ADMIN_小管', 'http://localhost:9090/HomestayAPI/files/1709716136348-cloud.webp', '2024-03-09 10:28:42', 'text', 'HOMESTAY_7天北路民宿', 'http://localhost:9090/HomestayAPI/files/1709133701288-7天北路民宿.jpg', 1);
INSERT INTO `imsingle` VALUES (40, 'http://localhost:9090/HomestayAPI/files/1710259529097-一凡鸣舍.jpg', 'USER_老六', 'http://localhost:9090/HomestayAPI/files/1709136644473-laoliu.webp', '2024-03-13 00:05:29', 'img', 'USER_灰大王', 'http://localhost:9090/HomestayAPI/files/1709136959637-huidawang.jpg', 1);
INSERT INTO `imsingle` VALUES (41, '😁', 'USER_老六', 'http://localhost:9090/HomestayAPI/files/1709136644473-laoliu.webp', '2024-03-13 00:05:37', 'text', 'USER_灰大王', 'http://localhost:9090/HomestayAPI/files/1709136959637-huidawang.jpg', 1);
INSERT INTO `imsingle` VALUES (42, 'http://localhost:9090/HomestayAPI/files/1710259807643-配置vue.pdf', 'USER_灰大王', 'http://localhost:9090/HomestayAPI/files/1709136959637-huidawang.jpg', '2024-03-13 00:10:07', 'file', 'USER_老六', 'http://localhost:9090/HomestayAPI/files/1709136644473-laoliu.webp', 1);
INSERT INTO `imsingle` VALUES (43, '666', 'USER_灰大王1', 'http://localhost:9090/HomestayAPI/files/1709136959637-huidawang.jpg', '2024-03-13 00:20:21', 'text', 'USER_老六', 'http://localhost:9090/HomestayAPI/files/1709136644473-laoliu.webp', 1);
INSERT INTO `imsingle` VALUES (44, 'http://localhost:9090/HomestayAPI/files/1710260436076-2022夏电气.jpg', 'USER_灰大王1', 'http://localhost:9090/HomestayAPI/files/1709136959637-huidawang.jpg', '2024-03-13 00:20:36', 'img', 'USER_老六', 'http://localhost:9090/HomestayAPI/files/1709136644473-laoliu.webp', 1);
INSERT INTO `imsingle` VALUES (45, 'http://localhost:9090/HomestayAPI/files/1710260448314-基于SSM框架的网上招聘系统设计与实现_殷凤梅.caj', 'USER_灰大王1', 'http://localhost:9090/HomestayAPI/files/1709136959637-huidawang.jpg', '2024-03-13 00:20:48', 'file', 'USER_老六', 'http://localhost:9090/HomestayAPI/files/1709136644473-laoliu.webp', 1);
INSERT INTO `imsingle` VALUES (46, '😉nihao', 'USER_灰大王1', 'http://localhost:9090/HomestayAPI/files/1709136959637-huidawang.jpg', '2024-03-13 00:20:56', 'text', 'USER_老六', 'http://localhost:9090/HomestayAPI/files/1709136644473-laoliu.webp', 1);
INSERT INTO `imsingle` VALUES (47, '系统修复好了吗', 'HOMESTAY_7天北路民宿', 'http://localhost:9090/HomestayAPI/files/1709133701288-7天北路民宿.jpg', '2024-03-21 00:00:29', 'text', 'ADMIN_管理员', 'http://localhost:9090/HomestayAPI/files/1697438073596-avatar.png', 1);
INSERT INTO `imsingle` VALUES (48, '3哥，最近怎么样，系统体验还不错吧？', 'ADMIN_管理员', 'http://localhost:9090/HomestayAPI/files/1697438073596-avatar.png', '2024-03-22 01:22:36', 'text', 'USER_张三', 'http://localhost:9090/HomestayAPI/files/1709136419776-zhangsan.webp', 0);
INSERT INTO `imsingle` VALUES (54, '111', 'USER_灰大王', 'http://localhost:9090/HomestayAPI/files/1709136959637-huidawang.jpg', '2024-03-29 19:42:19', 'text', 'HOMESTAY_从林里野奢民宿', 'http://localhost:9090/HomestayAPI/files/1709134680673-从林里野奢民宿.jpg', 1);
INSERT INTO `imsingle` VALUES (55, '1', 'HOMESTAY_从林里野奢民宿', 'http://localhost:9090/HomestayAPI/files/1709134680673-从林里野奢民宿.jpg', '2024-03-29 19:42:56', 'text', 'USER_灰大王', 'http://localhost:9090/HomestayAPI/files/1709136959637-huidawang.jpg', 1);

-- ----------------------------
-- Table structure for notice
-- ----------------------------
DROP TABLE IF EXISTS `notice`;
CREATE TABLE `notice`  (
  `id` int NOT NULL AUTO_INCREMENT COMMENT 'ID',
  `title` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NULL DEFAULT NULL COMMENT '标题',
  `content` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NULL DEFAULT NULL COMMENT '内容',
  `time` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NULL DEFAULT NULL COMMENT '创建时间',
  `user` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NULL DEFAULT NULL COMMENT '创建人',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 9 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_unicode_ci COMMENT = '公告信息表' ROW_FORMAT = DYNAMIC;

-- ----------------------------
-- Records of notice
-- ----------------------------
INSERT INTO `notice` VALUES (2, '斯是陋室，惟吾德馨', '斯是陋室，惟吾德馨。', '2024-02-29', 'admin');
INSERT INTO `notice` VALUES (3, '在陌生的城市，寻找那家熟悉的民宿', '在陌生的城市，寻找那家熟悉的民宿。舒适的环境，怡人的风景，淳朴的民风，让我们在这里找到家的感觉。', '2024-02-29', 'admin');
INSERT INTO `notice` VALUES (4, '嘿！一起去旅行吧', '嘿！一起去旅行吧！让每一次旅行都成为心灵的洗礼，留下美好的回忆。', '2024-02-28', 'admin');
INSERT INTO `notice` VALUES (5, '还没准备好吗，没关系的，我们说走就走', '还没准备好吗，没关系的，我们说走就走。每一处转角都可能遇见惊喜，每一次停留都充满故事。', '2024-02-28', 'admin');
INSERT INTO `notice` VALUES (6, '你看这里，风景如画，阳光正好', '你看这里，风景如画，阳光正好。\n来吧，一起踏上这场即兴的旅程。', '2024-02-28', 'admin');

-- ----------------------------
-- Table structure for orders
-- ----------------------------
DROP TABLE IF EXISTS `orders`;
CREATE TABLE `orders`  (
  `id` int NOT NULL AUTO_INCREMENT COMMENT 'ID',
  `order_id` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NULL DEFAULT NULL COMMENT '订单编号',
  `user_id` int NOT NULL COMMENT '用户ID',
  `homestay_id` int NULL DEFAULT NULL COMMENT '民宿ID',
  `type_id` int NULL DEFAULT NULL COMMENT '房型ID',
  `time` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NULL DEFAULT NULL COMMENT '预订时间',
  `in_time` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NULL DEFAULT NULL COMMENT '入住时间',
  `out_time` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NULL DEFAULT NULL COMMENT '离开时间',
  `days` bigint NULL DEFAULT NULL COMMENT '入住天数',
  `price` double(10, 2) NULL DEFAULT NULL COMMENT '订单价格',
  `status` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NULL DEFAULT NULL COMMENT '订单状态',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 73 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_unicode_ci COMMENT = '预订信息表' ROW_FORMAT = DYNAMIC;

-- ----------------------------
-- Records of orders
-- ----------------------------
INSERT INTO `orders` VALUES (24, '20240229002110', 20, 15, 16, '2024-02-29 00:21:10', '2024-01-04', '2024-01-06', 2, 440.00, '已退房');
INSERT INTO `orders` VALUES (25, '20240229002124', 20, 15, 16, '2024-02-29 00:21:24', '2024-01-10', '2024-01-12', 2, 440.00, '已退房');
INSERT INTO `orders` VALUES (26, '20240229002155', 20, 15, 16, '2024-02-29 00:21:55', '2024-02-15', '2024-02-18', 3, 660.00, '已退房');
INSERT INTO `orders` VALUES (29, '20240229002317', 19, 16, 19, '2024-02-29 00:23:17', '2024-02-19', '2024-02-21', 2, 560.00, '已退房');
INSERT INTO `orders` VALUES (30, '20240229002426', 19, 17, 23, '2024-02-29 00:24:26', '2024-01-18', '2024-01-21', 3, 540.00, '待入住');
INSERT INTO `orders` VALUES (31, '20240229002439', 19, 17, 23, '2024-02-29 00:24:39', '2024-02-27', '2024-02-28', 1, 180.00, '待入住');
INSERT INTO `orders` VALUES (32, '20240229002521', 19, 18, 28, '2024-02-29 00:25:21', '2024-02-20', '2024-02-21', 1, 330.00, '待入住');
INSERT INTO `orders` VALUES (33, '20240229002529', 19, 18, 28, '2024-02-29 00:25:29', '2024-02-22', '2024-02-24', 2, 660.00, '待入住');
INSERT INTO `orders` VALUES (34, '20240229004153', 18, 18, 26, '2024-02-29 00:41:53', '2024-02-15', '2024-02-16', 1, 420.00, '待入住');
INSERT INTO `orders` VALUES (36, '20240229004205', 18, 18, 26, '2024-02-29 00:42:05', '2024-02-16', '2024-02-18', 2, 840.00, '已退房');
INSERT INTO `orders` VALUES (38, '20240229004343', 18, 18, 25, '2024-02-29 00:43:43', '2024-02-15', '2024-02-16', 1, 350.00, '待入住');
INSERT INTO `orders` VALUES (39, '20240229004347', 18, 18, 25, '2024-02-29 00:43:47', '2024-02-15', '2024-02-17', 2, 700.00, '待入住');
INSERT INTO `orders` VALUES (42, '20240302100205', 15, 15, 18, '2024-03-02 10:02:05', '2024-02-23', '2024-02-25', 2, 360.00, '已退房');
INSERT INTO `orders` VALUES (44, '20240324014502', 17, 17, 24, '2024-03-24 01:45:02', '2024-03-18', '2024-03-19', 1, 240.00, '待入住');
INSERT INTO `orders` VALUES (45, '20240324021617', 18, 15, 18, '2024-03-24 02:16:17', '2024-03-18', '2024-03-19', 1, 180.00, '待入住');
INSERT INTO `orders` VALUES (48, '20240324022117', 18, 15, 18, '2024-03-24 02:21:17', '2024-03-18', '2024-03-19', 1, 180.00, '待入住');
INSERT INTO `orders` VALUES (67, '20240329194244', 18, 16, 19, '2024-03-29 19:42:44', '2024-03-29', '2024-03-30', 1, 280.00, '已退房');
INSERT INTO `orders` VALUES (68, '20240329221114', 18, 16, 19, '2024-03-29 22:11:14', '2024-03-29', '2024-03-30', 1, 280.00, '已退房');
INSERT INTO `orders` VALUES (69, '20240329232142', 18, 16, 19, '2024-03-29 23:21:42', '2024-03-29', '2024-03-30', 1, 280.00, '已退房');
INSERT INTO `orders` VALUES (70, '20240329233237', 18, 16, 19, '2024-03-29 23:32:37', '2024-03-29', '2024-03-30', 1, 280.00, '已退房');
INSERT INTO `orders` VALUES (71, '20240329235518', 18, 16, 19, '2024-03-29 23:55:18', '2024-03-29', '2024-03-30', 1, 280.00, '待入住');
INSERT INTO `orders` VALUES (72, '20240329235602', 18, 16, 20, '2024-03-29 23:56:02', '2024-03-29', '2024-03-30', 1, 320.00, '待入住');
INSERT INTO `orders` VALUES (73, '20240330092136', 18, 16, 19, '2024-03-30 09:21:36', '2024-03-30', '2024-03-31', 1, 280.00, '已入住');

-- ----------------------------
-- Table structure for room
-- ----------------------------
DROP TABLE IF EXISTS `room`;
CREATE TABLE `room`  (
  `id` int NOT NULL AUTO_INCREMENT COMMENT 'ID',
  `name` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NOT NULL COMMENT '房间编号',
  `status` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NULL DEFAULT NULL COMMENT '房间状态',
  `type_id` int NULL DEFAULT NULL COMMENT '分类ID',
  `homestay_id` int NULL DEFAULT NULL COMMENT '民宿ID',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 80 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_unicode_ci COMMENT = '客房信息表' ROW_FORMAT = DYNAMIC;

-- ----------------------------
-- Records of room
-- ----------------------------
INSERT INTO `room` VALUES (19, 'jd001', '空闲', 16, 15);
INSERT INTO `room` VALUES (20, 'jd002', '空闲', 16, 15);
INSERT INTO `room` VALUES (21, 'jd003', '空闲', 16, 15);
INSERT INTO `room` VALUES (22, 'jd004', '空闲', 16, 15);
INSERT INTO `room` VALUES (23, 'jd005', '空闲', 16, 15);
INSERT INTO `room` VALUES (24, 'js001', '空闲', 17, 15);
INSERT INTO `room` VALUES (25, 'js002', '空闲', 17, 15);
INSERT INTO `room` VALUES (26, 'js003', '空闲', 17, 15);
INSERT INTO `room` VALUES (27, 'js004', '空闲', 17, 15);
INSERT INTO `room` VALUES (28, 'js005', '空闲', 17, 15);
INSERT INTO `room` VALUES (29, 'zd001', '空闲', 18, 15);
INSERT INTO `room` VALUES (30, 'zd003', '空闲', 18, 15);
INSERT INTO `room` VALUES (31, 'zd002', '空闲', 18, 15);
INSERT INTO `room` VALUES (32, 'b001', '空闲', 19, 16);
INSERT INTO `room` VALUES (33, 'b002', '占用', 19, 16);
INSERT INTO `room` VALUES (34, 'b003', '空闲', 19, 16);
INSERT INTO `room` VALUES (35, 'b004', '空闲', 19, 16);
INSERT INTO `room` VALUES (36, 'b005', '空闲', 19, 16);
INSERT INTO `room` VALUES (37, 'd001', '占用', 20, 16);
INSERT INTO `room` VALUES (38, 'd002', '空闲', 20, 16);
INSERT INTO `room` VALUES (39, 'd003', '空闲', 20, 16);
INSERT INTO `room` VALUES (40, 'd004', '空闲', 20, 16);
INSERT INTO `room` VALUES (41, 'd005', '空闲', 20, 16);
INSERT INTO `room` VALUES (42, 'h001', '空闲', 21, 16);
INSERT INTO `room` VALUES (43, 'h002', '空闲', 21, 16);
INSERT INTO `room` VALUES (44, 's001', '空闲', 22, 16);
INSERT INTO `room` VALUES (45, 's002', '空闲', 22, 16);
INSERT INTO `room` VALUES (46, 's003', '空闲', 22, 16);
INSERT INTO `room` VALUES (47, 's004', '空闲', 22, 16);
INSERT INTO `room` VALUES (48, 's005', '空闲', 22, 16);
INSERT INTO `room` VALUES (49, 'jds001', '空闲', 23, 17);
INSERT INTO `room` VALUES (50, 'jds002', '空闲', 23, 17);
INSERT INTO `room` VALUES (51, 'jds004', '空闲', 23, 17);
INSERT INTO `room` VALUES (52, 'jds003', '空闲', 23, 17);
INSERT INTO `room` VALUES (53, 'jds005', '空闲', 23, 17);
INSERT INTO `room` VALUES (54, 'jxs001', '空闲', 24, 17);
INSERT INTO `room` VALUES (55, 'jxs002', '空闲', 24, 17);
INSERT INTO `room` VALUES (56, 'jxs003', '空闲', 24, 17);
INSERT INTO `room` VALUES (57, 'jxs004', '空闲', 24, 17);
INSERT INTO `room` VALUES (58, 'jxs005', '空闲', 24, 17);
INSERT INTO `room` VALUES (59, 'jd001', '空闲', 25, 18);
INSERT INTO `room` VALUES (60, 'jd002', '空闲', 25, 18);
INSERT INTO `room` VALUES (61, 'jd003', '空闲', 25, 18);
INSERT INTO `room` VALUES (62, 'jd004', '空闲', 25, 18);
INSERT INTO `room` VALUES (63, 'js001', '空闲', 26, 18);
INSERT INTO `room` VALUES (64, 'js002', '空闲', 26, 18);
INSERT INTO `room` VALUES (65, 'js003', '空闲', 26, 18);
INSERT INTO `room` VALUES (66, 'js004', '空闲', 26, 18);
INSERT INTO `room` VALUES (67, 'pd001', '空闲', 27, 18);
INSERT INTO `room` VALUES (68, 'pd002', '空闲', 27, 18);
INSERT INTO `room` VALUES (69, 'pd005', '空闲', 27, 18);
INSERT INTO `room` VALUES (70, 'pd003', '空闲', 27, 18);
INSERT INTO `room` VALUES (71, 'pd004', '空闲', 27, 18);
INSERT INTO `room` VALUES (72, 'ps001', '空闲', 28, 18);
INSERT INTO `room` VALUES (73, 'ps002', '空闲', 28, 18);
INSERT INTO `room` VALUES (74, 'ps003', '空闲', 28, 18);
INSERT INTO `room` VALUES (75, 'ps004', '空闲', 28, 18);
INSERT INTO `room` VALUES (76, 'ps005', '空闲', 28, 18);
INSERT INTO `room` VALUES (77, 'pd006', '空闲', 27, 18);
INSERT INTO `room` VALUES (78, 'js051', '空闲', 26, 18);

-- ----------------------------
-- Table structure for type
-- ----------------------------
DROP TABLE IF EXISTS `type`;
CREATE TABLE `type`  (
  `id` int NOT NULL AUTO_INCREMENT COMMENT 'ID',
  `name` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NULL DEFAULT NULL COMMENT '分类名称',
  `description` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NULL DEFAULT NULL COMMENT '分类描述',
  `price` double(10, 2) NULL DEFAULT NULL COMMENT '房间价格',
  `num` int NULL DEFAULT 0 COMMENT '剩余间数',
  `homestay_id` int NULL DEFAULT NULL COMMENT '民宿ID',
  `img` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NULL DEFAULT NULL COMMENT '房间图片',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 35 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_unicode_ci COMMENT = '房间分类表' ROW_FORMAT = DYNAMIC;

-- ----------------------------
-- Records of type
-- ----------------------------
INSERT INTO `type` VALUES (16, '精选大床房', '这是精选大床房', 220.00, 5, 15, 'http://localhost:9090/HomestayAPI/files/1709134106151-7天北路民宿_精选大床房.jpg');
INSERT INTO `type` VALUES (17, '精选双人房', '这是精选双人房', 280.00, 5, 15, 'http://localhost:9090/HomestayAPI/files/1709134203997-7天北路民宿_精选双人房.jpg');
INSERT INTO `type` VALUES (18, '自主双人房', '这是自主双人房', 180.00, 3, 15, 'http://localhost:9090/HomestayAPI/files/1709134301095-7天北路民宿_自主大床房.jpg');
INSERT INTO `type` VALUES (19, '标准间', '这是标准间', 280.00, 5, 16, 'http://localhost:9090/HomestayAPI/files/1709134703188-从林里野奢民宿_标准间.jpg');
INSERT INTO `type` VALUES (20, '大床房', '这是大床房', 320.00, 4, 16, 'http://localhost:9090/HomestayAPI/files/1709134770925-从林里野奢民宿_大床房.jpg');
INSERT INTO `type` VALUES (21, '豪华间', '这是豪华间', 480.00, 2, 16, 'http://localhost:9090/HomestayAPI/files/1709134803370-从林里野奢民宿_豪华间.jpg');
INSERT INTO `type` VALUES (22, '双人房', '这是双人房', 400.00, 5, 16, 'http://localhost:9090/HomestayAPI/files/1709134855882-从林里野奢民宿_双人房.jpg');
INSERT INTO `type` VALUES (23, '经典双人房', '这是经典双人房', 180.00, 5, 17, 'http://localhost:9090/HomestayAPI/files/1709135187119-民宿房东（田心店）_经典双人房.jpg');
INSERT INTO `type` VALUES (24, '精选双人房', '这是精选双人房', 240.00, 5, 17, 'http://localhost:9090/HomestayAPI/files/1709135246749-民宿房东（田心店）_精选双人房.jpg');
INSERT INTO `type` VALUES (25, '精选大床房', '这是精选大床房', 350.00, 4, 18, 'http://localhost:9090/HomestayAPI/files/1709135527803-陌野尘外美宿_精选大床房.jpg');
INSERT INTO `type` VALUES (26, '精选双人房', '这是精选双人房', 420.00, 5, 18, 'http://localhost:9090/HomestayAPI/files/1709135613543-陌野尘外美宿_精选双人房.jpg');
INSERT INTO `type` VALUES (27, '普通大床房', '这是普通大床房', 260.00, 6, 18, 'http://localhost:9090/HomestayAPI/files/1709135660694-陌野尘外美宿_普通大床房.jpg');
INSERT INTO `type` VALUES (28, '普通双人房', '这是普通双人房', 330.00, 5, 18, 'http://localhost:9090/HomestayAPI/files/1709135710890-陌野尘外美宿_普通双人房.jpg');

-- ----------------------------
-- Table structure for user
-- ----------------------------
DROP TABLE IF EXISTS `user`;
CREATE TABLE `user`  (
  `id` int NOT NULL AUTO_INCREMENT COMMENT 'ID',
  `username` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NULL DEFAULT NULL COMMENT '用户账号',
  `password` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NULL DEFAULT NULL COMMENT '密码',
  `name` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NULL DEFAULT NULL COMMENT '用户昵称',
  `phone` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NULL DEFAULT NULL COMMENT '电话',
  `email` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NULL DEFAULT NULL COMMENT '邮箱',
  `role` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NULL DEFAULT NULL COMMENT '角色',
  `avatar` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NULL DEFAULT NULL COMMENT '头像',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 25 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_unicode_ci COMMENT = '用户信息表' ROW_FORMAT = DYNAMIC;

-- ----------------------------
-- Records of user
-- ----------------------------
INSERT INTO `user` VALUES (14, 'zhangsan', '123456', '张三', '15265654478', 'zhangsan@ms.com', 'USER', 'http://localhost:9090/HomestayAPI/files/1709136419776-zhangsan.webp');
INSERT INTO `user` VALUES (15, 'lisi', '123456', '李四', '18556550582', 'lisi@ms.com', 'USER', 'http://localhost:9090/HomestayAPI/files/1709133959703-lisi.webp');
INSERT INTO `user` VALUES (16, 'wangwu', '123456', '王武', '14755264654', 'wangwu@ms.com', 'USER', 'http://localhost:9090/HomestayAPI/files/1709136534792-wangwu.webp');
INSERT INTO `user` VALUES (17, 'laoliu', '123456', '老六', '16666565656', 'laoliu@ms.com', 'USER', 'http://localhost:9090/HomestayAPI/files/1709136644473-laoliu.webp');
INSERT INTO `user` VALUES (18, 'huidawang', '123456', '灰大王', '15785532541', 'huidawang@ms.com', 'USER', 'http://localhost:9090/HomestayAPI/files/1709136959637-huidawang.jpg');
INSERT INTO `user` VALUES (19, 'axi', '123456', '阿喜', '18385856471', 'axi@ms.com', 'USER', 'http://localhost:9090/HomestayAPI/files/1709137106858-axi.jpg');
INSERT INTO `user` VALUES (20, 'xiaolan', '123456', '小懒', '17845254741', 'xiaolan@xm.com', 'USER', 'http://localhost:9090/HomestayAPI/files/1709137136518-xiaolan.jpg');

SET FOREIGN_KEY_CHECKS = 1;
