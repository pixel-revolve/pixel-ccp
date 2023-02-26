/*
 Navicat Premium Data Transfer

 Source Server         : pixel_ccp
 Source Server Type    : MySQL
 Source Server Version : 80030 (8.0.30)
 Source Host           : localhost:3306
 Source Schema         : pixel_ccp

 Target Server Type    : MySQL
 Target Server Version : 80030 (8.0.30)
 File Encoding         : 65001

 Date: 26/02/2023 16:53:52
*/

SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
-- Table structure for p_attachment
-- ----------------------------
DROP TABLE IF EXISTS `p_attachment`;
CREATE TABLE `p_attachment`  (
                                 `id` bigint UNSIGNED NOT NULL AUTO_INCREMENT,
                                 `user_id` bigint UNSIGNED NOT NULL DEFAULT 0,
                                 `file_size` bigint UNSIGNED NOT NULL,
                                 `img_width` bigint UNSIGNED NOT NULL DEFAULT 0,
                                 `img_height` bigint UNSIGNED NOT NULL DEFAULT 0,
                                 `type` tinyint UNSIGNED NOT NULL DEFAULT 1 COMMENT '1图片，2视频，3其他附件',
                                 `content` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL DEFAULT '',
                                 `created_on` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
                                 `modified_on` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '修改时间',
                                 `deleted_on` timestamp NULL DEFAULT NULL COMMENT '删除时间',
                                 `is_del` tinyint UNSIGNED NOT NULL DEFAULT 0 COMMENT '是否删除 0 为未删除、1 为已删除',
                                 PRIMARY KEY (`id`) USING BTREE,
                                 INDEX `idx_user`(`user_id` ASC) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 100041 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci COMMENT = '附件' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of p_attachment
-- ----------------------------

-- ----------------------------
-- Table structure for p_captcha
-- ----------------------------
DROP TABLE IF EXISTS `p_captcha`;
CREATE TABLE `p_captcha`  (
                              `id` bigint UNSIGNED NOT NULL AUTO_INCREMENT COMMENT '验证码ID',
                              `phone` varchar(16) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL DEFAULT '' COMMENT '手机号',
                              `captcha` varchar(16) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL DEFAULT '' COMMENT '验证码',
                              `use_times` int UNSIGNED NOT NULL DEFAULT 0 COMMENT '使用次数',
                              `expired_on` bigint UNSIGNED NOT NULL DEFAULT 0 COMMENT '过期时间',
                              `created_on` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
                              `modified_on` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '修改时间',
                              `deleted_on` timestamp NULL DEFAULT NULL COMMENT '删除时间',
                              `is_del` tinyint UNSIGNED NOT NULL DEFAULT 0 COMMENT '是否删除 0 为未删除、1 为已删除',
                              PRIMARY KEY (`id`) USING BTREE,
                              INDEX `idx_phone`(`phone` ASC) USING BTREE,
                              INDEX `idx_expired_on`(`expired_on` ASC) USING BTREE,
                              INDEX `idx_use_times`(`use_times` ASC) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 1021 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci COMMENT = '手机验证码' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of p_captcha
-- ----------------------------

-- ----------------------------
-- Table structure for p_comment
-- ----------------------------
DROP TABLE IF EXISTS `p_comment`;
CREATE TABLE `p_comment`  (
                              `id` bigint UNSIGNED NOT NULL AUTO_INCREMENT COMMENT '评论ID',
                              `post_id` bigint UNSIGNED NOT NULL DEFAULT 0 COMMENT 'POST ID',
                              `user_id` bigint UNSIGNED NOT NULL DEFAULT 0 COMMENT '用户ID',
                              `ip` varchar(64) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL DEFAULT '' COMMENT 'IP地址',
                              `ip_loc` varchar(64) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL DEFAULT '' COMMENT 'IP城市地址',
                              `created_on` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
                              `modified_on` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '修改时间',
                              `deleted_on` timestamp NULL DEFAULT NULL COMMENT '删除时间',
                              `is_del` tinyint UNSIGNED NOT NULL DEFAULT 0 COMMENT '是否删除 0 为未删除、1 为已删除',
                              PRIMARY KEY (`id`) USING BTREE,
                              INDEX `idx_post`(`post_id` ASC) USING BTREE,
                              INDEX `idx_user`(`user_id` ASC) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 2162685444101 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci COMMENT = '评论' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of p_comment
-- ----------------------------
INSERT INTO `p_comment` VALUES (2162685444100, 230606983174, 204085702657, '', '', '2023-01-17 22:39:22', '2023-01-17 22:39:22', NULL, 0);

-- ----------------------------
-- Table structure for p_comment_content
-- ----------------------------
DROP TABLE IF EXISTS `p_comment_content`;
CREATE TABLE `p_comment_content`  (
                                      `id` bigint UNSIGNED NOT NULL AUTO_INCREMENT COMMENT '内容ID',
                                      `comment_id` bigint UNSIGNED NOT NULL DEFAULT 0 COMMENT '评论ID',
                                      `user_id` bigint UNSIGNED NOT NULL DEFAULT 0 COMMENT '用户ID',
                                      `content` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL DEFAULT '' COMMENT '内容',
                                      `type` tinyint UNSIGNED NOT NULL DEFAULT 2 COMMENT '类型，1标题，2文字段落，3图片地址，4视频地址，5语音地址，6链接地址',
                                      `sort` bigint UNSIGNED NOT NULL DEFAULT 100 COMMENT '排序，越小越靠前',
                                      `created_on` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
                                      `modified_on` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '修改时间',
                                      `deleted_on` timestamp NULL DEFAULT NULL COMMENT '删除时间',
                                      `is_del` tinyint UNSIGNED NOT NULL DEFAULT 0 COMMENT '是否删除 0 为未删除、1 为已删除',
                                      PRIMARY KEY (`id`) USING BTREE,
                                      INDEX `idx_reply`(`comment_id` ASC) USING BTREE,
                                      INDEX `idx_user`(`user_id` ASC) USING BTREE,
                                      INDEX `idx_type`(`type` ASC) USING BTREE,
                                      INDEX `idx_sort`(`sort` ASC) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 2162685509637 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci COMMENT = '评论内容' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of p_comment_content
-- ----------------------------
INSERT INTO `p_comment_content` VALUES (2162685444099, 2162685444100, 204085702657, '《反方向的钟》，是周杰伦实验性的一首R&B作品。里边主要涉及到他对中文如何适配西方R&B及嘻哈音乐的探索与发现。', 2, 100, '2023-01-17 22:39:22', '2023-01-17 22:39:22', NULL, 0);
INSERT INTO `p_comment_content` VALUES (2162685509636, 2162685444100, 204085702657, 'https://www.youtube.com/watch?v=fuM1aVCGR8c', 6, 101, '2023-01-17 22:39:22', '2023-01-17 22:39:22', NULL, 0);

-- ----------------------------
-- Table structure for p_comment_reply
-- ----------------------------
DROP TABLE IF EXISTS `p_comment_reply`;
CREATE TABLE `p_comment_reply`  (
                                    `id` bigint UNSIGNED NOT NULL AUTO_INCREMENT COMMENT '回复ID',
                                    `comment_id` bigint UNSIGNED NOT NULL DEFAULT 0 COMMENT '评论ID',
                                    `user_id` bigint UNSIGNED NOT NULL DEFAULT 0 COMMENT '用户ID',
                                    `at_user_id` bigint UNSIGNED NOT NULL DEFAULT 0 COMMENT '@用户ID',
                                    `content` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL DEFAULT '' COMMENT '内容',
                                    `ip` varchar(64) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL DEFAULT '' COMMENT 'IP地址',
                                    `ip_loc` varchar(64) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL DEFAULT '' COMMENT 'IP城市地址',
                                    `created_on` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
                                    `modified_on` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '修改时间',
                                    `deleted_on` timestamp NULL DEFAULT NULL COMMENT '删除时间',
                                    `is_del` tinyint UNSIGNED NOT NULL DEFAULT 0 COMMENT '是否删除 0 为未删除、1 为已删除',
                                    PRIMARY KEY (`id`) USING BTREE,
                                    INDEX `idx_comment`(`comment_id` ASC) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 2162687213570 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci COMMENT = '评论回复' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of p_comment_reply
-- ----------------------------
INSERT INTO `p_comment_reply` VALUES (2162687213569, 2162685444100, 52483444737, 204085702657, '别的不说，那段rap学的脑壳疼', '', '', '2023-01-17 22:39:48', '2023-01-17 22:39:48', NULL, 0);

-- ----------------------------
-- Table structure for p_follow
-- ----------------------------
DROP TABLE IF EXISTS `p_follow`;
CREATE TABLE `p_follow`  (
                             `id` bigint UNSIGNED NOT NULL AUTO_INCREMENT COMMENT '主键',
                             `user_id` bigint UNSIGNED NOT NULL COMMENT '用户id',
                             `follow_user_id` bigint UNSIGNED NOT NULL COMMENT '关联的用户id',
                             `created_on` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
                             `modified_on` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '修改时间',
                             `deleted_on` timestamp NULL DEFAULT NULL COMMENT '删除时间',
                             `is_del` tinyint UNSIGNED NOT NULL DEFAULT 0 COMMENT '是否删除 0 为未删除、1 为已删除',
                             PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 1629685010778824707 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci COMMENT = '关注' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of p_follow
-- ----------------------------
INSERT INTO `p_follow` VALUES (1629500679995678721, 52483444737, 204085702657, '2023-02-25 23:16:51', '2023-02-25 23:16:51', NULL, 0);
INSERT INTO `p_follow` VALUES (1629684097292980226, 204085702657, 2383742042114, '2023-02-26 11:25:41', '2023-02-26 11:25:41', NULL, 0);
INSERT INTO `p_follow` VALUES (1629685010778824706, 52483444737, 2383742042114, '2023-02-26 11:29:19', '2023-02-26 11:29:19', NULL, 0);

-- ----------------------------
-- Table structure for p_message
-- ----------------------------
DROP TABLE IF EXISTS `p_message`;
CREATE TABLE `p_message`  (
                              `id` bigint UNSIGNED NOT NULL AUTO_INCREMENT COMMENT '消息通知ID',
                              `sender_user_id` bigint UNSIGNED NOT NULL DEFAULT 0 COMMENT '发送方用户ID',
                              `receiver_user_id` bigint UNSIGNED NOT NULL DEFAULT 0 COMMENT '接收方用户ID',
                              `type` tinyint UNSIGNED NOT NULL DEFAULT 1 COMMENT '通知类型，1动态，2评论，3回复，4私信，99系统通知',
                              `brief` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL DEFAULT '' COMMENT '摘要说明',
                              `content` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL DEFAULT '' COMMENT '详细内容',
                              `post_id` bigint UNSIGNED NOT NULL DEFAULT 0 COMMENT '动态ID',
                              `comment_id` bigint UNSIGNED NOT NULL DEFAULT 0 COMMENT '评论ID',
                              `reply_id` bigint UNSIGNED NOT NULL DEFAULT 0 COMMENT '回复ID',
                              `is_read` tinyint UNSIGNED NOT NULL DEFAULT 0 COMMENT '是否已读',
                              `created_on` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
                              `modified_on` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '修改时间',
                              `deleted_on` timestamp NULL DEFAULT NULL COMMENT '删除时间',
                              `is_del` tinyint UNSIGNED NOT NULL DEFAULT 0 COMMENT '是否删除 0 为未删除、1 为已删除',
                              PRIMARY KEY (`id`) USING BTREE,
                              INDEX `idx_receiver`(`receiver_user_id` ASC) USING BTREE,
                              INDEX `idx_is_read`(`is_read` ASC) USING BTREE,
                              INDEX `idx_type`(`type` ASC) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 16000033 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci COMMENT = '消息通知' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of p_message
-- ----------------------------

-- ----------------------------
-- Table structure for p_post
-- ----------------------------
DROP TABLE IF EXISTS `p_post`;
CREATE TABLE `p_post`  (
                           `id` bigint UNSIGNED NOT NULL AUTO_INCREMENT COMMENT '主题ID',
                           `user_id` bigint UNSIGNED NOT NULL DEFAULT 0 COMMENT '用户ID',
                           `comment_count` bigint UNSIGNED NOT NULL DEFAULT 0 COMMENT '评论数',
                           `collection_count` bigint UNSIGNED NOT NULL DEFAULT 0 COMMENT '收藏数',
                           `upvote_count` bigint UNSIGNED NOT NULL DEFAULT 0 COMMENT '点赞数',
                           `watch_count` bigint UNSIGNED NOT NULL DEFAULT 0 COMMENT '查看数',
                           `summary` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL DEFAULT '' COMMENT '摘要',
                           `visibility` tinyint UNSIGNED NOT NULL DEFAULT 0 COMMENT '可见性 0公开 1私密 2好友可见',
                           `is_top` tinyint UNSIGNED NOT NULL DEFAULT 0 COMMENT '是否置顶',
                           `is_essence` tinyint UNSIGNED NOT NULL DEFAULT 0 COMMENT '是否精华',
                           `is_lock` tinyint UNSIGNED NOT NULL DEFAULT 0 COMMENT '是否锁定',
                           `latest_replied_on` bigint UNSIGNED NOT NULL DEFAULT 0 COMMENT '最新回复时间',
                           `tags` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL DEFAULT '' COMMENT '标签',
                           `attachment_price` bigint UNSIGNED NOT NULL DEFAULT 0 COMMENT '附件价格(分)',
                           `ip` varchar(64) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL DEFAULT '' COMMENT 'IP地址',
                           `ip_loc` varchar(64) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL DEFAULT '' COMMENT 'IP城市地址',
                           `created_on` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
                           `modified_on` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '修改时间',
                           `deleted_on` timestamp NULL DEFAULT NULL COMMENT '删除时间',
                           `is_del` tinyint UNSIGNED NOT NULL DEFAULT 0 COMMENT '是否删除 0 为未删除、1 为已删除',
                           PRIMARY KEY (`id`) USING BTREE,
                           INDEX `idx_user`(`user_id` ASC) USING BTREE,
                           INDEX `idx_visibility`(`visibility` ASC) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 2387784105986 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci COMMENT = '冒泡/文章' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of p_post
-- ----------------------------
INSERT INTO `p_post` VALUES (101757964293, 204085702657, 0, 0, 0, 0, '晴天', 0, 0, 0, 0, 0, '音乐', 0, '', '', '2022-11-26 11:38:48', '2022-11-26 11:38:48', NULL, 0);
INSERT INTO `p_post` VALUES (230606983174, 204085702657, 0, 0, 0, 0, '反方向的钟', 0, 0, 0, 0, 0, '音乐', 0, '', '', '2022-11-26 11:39:18', '2022-11-26 11:39:18', NULL, 0);
INSERT INTO `p_post` VALUES (423880511495, 204085702657, 0, 0, 0, 0, '流沙', 0, 0, 0, 0, 0, '音乐', 0, '', '', '2022-11-26 11:40:03', '2022-11-26 11:40:03', NULL, 0);
INSERT INTO `p_post` VALUES (531982073865, 204085702657, 0, 0, 0, 0, '厉不厉害你坤哥', 0, 0, 0, 0, 0, '搞笑', 0, '', '', '2022-11-26 11:44:21', '2022-11-26 11:44:21', NULL, 0);
INSERT INTO `p_post` VALUES (936367067137, 204085702657, 0, 0, 0, 0, '比特币大涨', 0, 0, 0, 0, 0, '区块链', 0, '', '', '2022-11-26 11:26:32', '2022-11-26 11:28:45', NULL, 0);
INSERT INTO `p_post` VALUES (981498880004, 204085702657, 0, 0, 0, 0, '龙卷风', 0, 0, 0, 0, 0, '音乐', 0, '', '', '2022-11-26 11:38:20', '2022-11-26 11:38:20', NULL, 0);
INSERT INTO `p_post` VALUES (986521227272, 204085702657, 0, 0, 0, 0, '泰拉瑞亚新手教程', 0, 0, 0, 0, 0, '游戏', 0, '', '', '2022-11-26 11:42:14', '2022-11-26 11:42:14', NULL, 0);
INSERT INTO `p_post` VALUES (2387784105985, 2383742042114, 0, 0, 0, 0, 'MySQL索引', 0, 0, 0, 0, 0, 'MySQL', 0, '', '', '2023-02-26 16:44:54', '2023-02-26 16:44:54', NULL, 0);

-- ----------------------------
-- Table structure for p_post_attachment_bill
-- ----------------------------
DROP TABLE IF EXISTS `p_post_attachment_bill`;
CREATE TABLE `p_post_attachment_bill`  (
                                           `id` bigint UNSIGNED NOT NULL AUTO_INCREMENT COMMENT '购买记录ID',
                                           `post_id` bigint UNSIGNED NOT NULL DEFAULT 0 COMMENT 'POST ID',
                                           `user_id` bigint UNSIGNED NOT NULL DEFAULT 0 COMMENT '用户ID',
                                           `paid_amount` bigint UNSIGNED NOT NULL DEFAULT 0 COMMENT '支付金额',
                                           `created_on` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
                                           `modified_on` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '修改时间',
                                           `deleted_on` timestamp NULL DEFAULT NULL COMMENT '删除时间',
                                           `is_del` tinyint UNSIGNED NOT NULL DEFAULT 0 COMMENT '是否删除 0 为未删除、1 为已删除',
                                           PRIMARY KEY (`id`) USING BTREE,
                                           INDEX `idx_post`(`post_id` ASC) USING BTREE,
                                           INDEX `idx_user`(`user_id` ASC) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 5000002 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci COMMENT = '冒泡/文章附件账单' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of p_post_attachment_bill
-- ----------------------------

-- ----------------------------
-- Table structure for p_post_collection
-- ----------------------------
DROP TABLE IF EXISTS `p_post_collection`;
CREATE TABLE `p_post_collection`  (
                                      `id` bigint UNSIGNED NOT NULL AUTO_INCREMENT COMMENT '收藏ID',
                                      `post_id` bigint UNSIGNED NOT NULL DEFAULT 0 COMMENT 'POST ID',
                                      `user_id` bigint UNSIGNED NOT NULL DEFAULT 0 COMMENT '用户ID',
                                      `created_on` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
                                      `modified_on` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '修改时间',
                                      `deleted_on` timestamp NULL DEFAULT NULL COMMENT '删除时间',
                                      `is_del` tinyint UNSIGNED NOT NULL DEFAULT 0 COMMENT '是否删除 0 为未删除、1 为已删除',
                                      PRIMARY KEY (`id`) USING BTREE,
                                      INDEX `idx_post`(`post_id` ASC) USING BTREE,
                                      INDEX `idx_user`(`user_id` ASC) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 6000012 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci COMMENT = '冒泡/文章收藏' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of p_post_collection
-- ----------------------------

-- ----------------------------
-- Table structure for p_post_content
-- ----------------------------
DROP TABLE IF EXISTS `p_post_content`;
CREATE TABLE `p_post_content`  (
                                   `id` bigint UNSIGNED NOT NULL AUTO_INCREMENT COMMENT '内容ID',
                                   `post_id` bigint UNSIGNED NOT NULL DEFAULT 0 COMMENT 'POST ID',
                                   `user_id` bigint UNSIGNED NOT NULL DEFAULT 0 COMMENT '用户ID',
                                   `content` varchar(2000) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL DEFAULT '' COMMENT '内容',
                                   `type` tinyint UNSIGNED NOT NULL DEFAULT 2 COMMENT '类型，1标题，2文字段落，3图片地址，4视频地址，5语音地址，6链接地址，7附件资源，8收费资源',
                                   `sort` int UNSIGNED NOT NULL DEFAULT 100 COMMENT '排序，越小越靠前',
                                   `created_on` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
                                   `modified_on` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '修改时间',
                                   `deleted_on` timestamp NULL DEFAULT NULL COMMENT '删除时间',
                                   `is_del` tinyint UNSIGNED NOT NULL DEFAULT 0 COMMENT '是否删除 0 为未删除、1 为已删除',
                                   PRIMARY KEY (`id`) USING BTREE,
                                   INDEX `idx_post`(`post_id` ASC) USING BTREE,
                                   INDEX `idx_user`(`user_id` ASC) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 9112796585987 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci COMMENT = '冒泡/文章内容' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of p_post_content
-- ----------------------------
INSERT INTO `p_post_content` VALUES (2387784105985, 2387784105985, 2383742042114, 'MySQL索引是一种数据库技术，它可以帮助您快速访问数据库中的特定信息。索引是一个特殊的数据结构，它可以帮助MySQL快速找到所需的行。\nMySQL索引可以使用不同的数据类型，如整数、字符串和日期。MySQL也可以使用多列索引来跨多个字段进行快速访问。此外，MySQL还允许使用全文索引来对文本字段进行快速访问。\nMySQL索引可以大大减少数据库中的读写时间，并且可以显著地减少服务器上的CPU使用量。此外，MySQL还允许在不同表之间创建外键（foreign key）来实现表之间的关联性。\n总之，MySQL索引是一项重要的数据库功能，它可以帮助我们快速、准确地获取所需信息。', 2, 100, '2023-02-26 16:44:54', '2023-02-26 16:44:54', NULL, 0);
INSERT INTO `p_post_content` VALUES (2387784105986, 2387784105985, 2383742042114, 'https://search.bilibili.com?keyword=MySQL索引', 6, 101, '2023-02-26 16:44:54', '2023-02-26 16:44:54', NULL, 0);
INSERT INTO `p_post_content` VALUES (2387784105987, 2387784105985, 2383742042114, 'https://pixel-ccp.oss-cn-hangzhou.aliyuncs.com/avatar/2022/11/26/16-39-12/770df013-dfb7-4725-938e-9fc1f1c14704.jpg', 3, 102, '2023-02-26 16:44:54', '2023-02-26 16:44:54', NULL, 0);
INSERT INTO `p_post_content` VALUES (6598583967745, 936367067137, 204085702657, '比特币昨日涨幅2.76，亏麻了家人们', 2, 100, '2022-11-26 11:34:22', '2022-11-26 11:34:22', NULL, 0);
INSERT INTO `p_post_content` VALUES (6598609133570, 936367067137, 204085702657, 'https://search.bilibili.com?keyword=比特币', 6, 101, '2022-11-26 11:34:22', '2022-11-26 11:34:22', NULL, 0);
INSERT INTO `p_post_content` VALUES (7596207562753, 981498880004, 204085702657, '爱像一阵风，吹完它就走', 2, 100, '2022-11-26 11:38:20', '2022-11-26 11:38:20', NULL, 0);
INSERT INTO `p_post_content` VALUES (7596207562754, 981498880004, 204085702657, 'https://search.bilibili.com?keyword=龙卷风', 6, 101, '2022-11-26 11:38:20', '2022-11-26 11:38:20', NULL, 0);
INSERT INTO `p_post_content` VALUES (7715485179906, 101757964293, 204085702657, '故事的小黄花，从出生的那天就飘着', 2, 100, '2022-11-26 11:38:48', '2022-11-26 11:38:48', NULL, 0);
INSERT INTO `p_post_content` VALUES (7715485179907, 101757964293, 204085702657, 'https://search.bilibili.com?keyword=晴天', 6, 101, '2022-11-26 11:38:48', '2022-11-26 11:38:48', NULL, 0);
INSERT INTO `p_post_content` VALUES (7838327955458, 230606983174, 204085702657, '穿梭时间的画面的钟，从反方向开始移动', 2, 100, '2022-11-26 11:39:18', '2022-11-26 11:39:18', NULL, 0);
INSERT INTO `p_post_content` VALUES (7838395064322, 230606983174, 204085702657, 'https://search.bilibili.com?keyword=晴天', 6, 101, '2022-11-26 11:39:18', '2022-11-26 11:39:18', NULL, 0);
INSERT INTO `p_post_content` VALUES (8027885330433, 423880511495, 204085702657, '爱情好像流沙~', 2, 100, '2022-11-26 11:40:03', '2022-11-26 11:40:03', NULL, 0);
INSERT INTO `p_post_content` VALUES (8027948244993, 423880511495, 204085702657, 'https://search.bilibili.com?keyword=流沙', 6, 101, '2022-11-26 11:40:03', '2022-11-26 11:40:03', NULL, 0);
INSERT INTO `p_post_content` VALUES (8579536969729, 986521227272, 204085702657, '查一下wiki就好了。我来总结一下。不一定对欢迎来反驳\n1.肉山前只会传播草。范围应该是3\n2肉后会开始传播。石块，冰块，沙子和几种沙子的变种。范围也是3\n3.血腥腐化会把淤泥变成泥土，但是绿叶矿在一定的范围内会组织腐化血腥传播\n4.背景墙不知道~', 2, 100, '2022-11-26 11:42:14', '2022-11-26 11:42:14', NULL, 0);
INSERT INTO `p_post_content` VALUES (8579536969730, 986521227272, 204085702657, 'https://search.bilibili.com?keyword=泰拉瑞亚新手教程', 6, 101, '2022-11-26 11:42:14', '2022-11-26 11:42:14', NULL, 0);
INSERT INTO `p_post_content` VALUES (9112729477121, 531982073865, 204085702657, '只因你太美 oh baby\n你干嘛 哎呦', 2, 100, '2022-11-26 11:44:21', '2022-11-26 11:44:21', NULL, 0);
INSERT INTO `p_post_content` VALUES (9112796585986, 531982073865, 204085702657, 'https://search.bilibili.com?keyword=蔡徐坤', 6, 101, '2022-11-26 11:44:21', '2022-11-26 11:44:21', NULL, 0);

-- ----------------------------
-- Table structure for p_post_star
-- ----------------------------
DROP TABLE IF EXISTS `p_post_star`;
CREATE TABLE `p_post_star`  (
                                `id` bigint UNSIGNED NOT NULL AUTO_INCREMENT COMMENT '收藏ID',
                                `post_id` bigint UNSIGNED NOT NULL DEFAULT 0 COMMENT 'POST ID',
                                `user_id` bigint UNSIGNED NOT NULL DEFAULT 0 COMMENT '用户ID',
                                `created_on` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
                                `modified_on` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '修改时间',
                                `deleted_on` timestamp NULL DEFAULT NULL COMMENT '删除时间',
                                `is_del` tinyint UNSIGNED NOT NULL DEFAULT 0 COMMENT '是否删除 0 为未删除、1 为已删除',
                                PRIMARY KEY (`id`) USING BTREE,
                                INDEX `idx_post`(`post_id` ASC) USING BTREE,
                                INDEX `idx_user`(`user_id` ASC) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 6000028 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci COMMENT = '冒泡/文章点赞' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of p_post_star
-- ----------------------------

-- ----------------------------
-- Table structure for p_sign
-- ----------------------------
DROP TABLE IF EXISTS `p_sign`;
CREATE TABLE `p_sign`  (
                           `id` bigint UNSIGNED NOT NULL AUTO_INCREMENT COMMENT '主键',
                           `user_id` bigint UNSIGNED NOT NULL COMMENT '用户id',
                           `date` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '最新签到日期',
                           `count` bigint UNSIGNED NULL DEFAULT NULL COMMENT '连续签到的天数',
                           PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci ROW_FORMAT = COMPACT;

-- ----------------------------
-- Records of p_sign
-- ----------------------------

-- ----------------------------
-- Table structure for p_tag
-- ----------------------------
DROP TABLE IF EXISTS `p_tag`;
CREATE TABLE `p_tag`  (
                          `id` bigint UNSIGNED NOT NULL AUTO_INCREMENT COMMENT '标签ID',
                          `user_id` bigint UNSIGNED NOT NULL DEFAULT 0 COMMENT '创建者ID',
                          `tag` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT '标签名',
                          `quote_num` bigint UNSIGNED NOT NULL DEFAULT 0 COMMENT '引用数',
                          `created_on` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
                          `modified_on` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '修改时间',
                          `deleted_on` timestamp NULL DEFAULT NULL COMMENT '删除时间',
                          `is_del` tinyint UNSIGNED NOT NULL DEFAULT 0 COMMENT '是否删除 0 为未删除、1 为已删除',
                          PRIMARY KEY (`id`) USING BTREE,
                          UNIQUE INDEX `idx_tag`(`tag` ASC) USING BTREE,
                          INDEX `idx_user`(`user_id` ASC) USING BTREE,
                          INDEX `idx_num`(`quote_num` ASC) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 1629756421308444675 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci COMMENT = '标签' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of p_tag
-- ----------------------------
INSERT INTO `p_tag` VALUES (4269410603010, 204085702657, '区块链', 0, '2022-11-26 10:45:23', '2022-11-26 10:45:23', NULL, 0);
INSERT INTO `p_tag` VALUES (7151829442561, 204085702657, '后端', 0, '2022-11-26 11:36:34', '2022-11-26 11:36:34', NULL, 0);
INSERT INTO `p_tag` VALUES (7175409819649, 204085702657, '前端', 0, '2022-11-26 11:36:39', '2022-11-26 11:36:39', NULL, 0);
INSERT INTO `p_tag` VALUES (7194376462338, 204085702657, '游戏', 0, '2022-11-26 11:36:44', '2022-11-26 11:36:44', NULL, 0);
INSERT INTO `p_tag` VALUES (7242422214657, 204085702657, '搞笑', 0, '2022-11-26 11:36:55', '2022-11-26 11:36:55', NULL, 0);
INSERT INTO `p_tag` VALUES (7273183240193, 204085702657, '音乐', 0, '2022-11-26 11:37:03', '2022-11-26 11:37:03', NULL, 0);
INSERT INTO `p_tag` VALUES (1629756421308444674, 2383742042114, 'MySQL', 0, '2023-02-26 16:13:04', '2023-02-26 16:13:04', NULL, 0);

-- ----------------------------
-- Table structure for p_tag_post
-- ----------------------------
DROP TABLE IF EXISTS `p_tag_post`;
CREATE TABLE `p_tag_post`  (
                               `id` bigint UNSIGNED NOT NULL AUTO_INCREMENT COMMENT '主键',
                               `post_id` bigint UNSIGNED NOT NULL DEFAULT 0 COMMENT '文章ID',
                               `tag_id` bigint UNSIGNED NOT NULL DEFAULT 0 COMMENT '标签ID',
                               `created_on` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
                               `modified_on` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '修改时间',
                               `deleted_on` timestamp NULL DEFAULT NULL COMMENT '删除时间',
                               `is_del` tinyint UNSIGNED NOT NULL DEFAULT 0 COMMENT '是否删除 0 为未删除、1 为已删除',
                               PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 1629764431657340930 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci COMMENT = '标签文章关联表' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of p_tag_post
-- ----------------------------
INSERT INTO `p_tag_post` VALUES (4626015404034, 936367067137, 4269410603010, '2022-11-26 11:26:32', '2022-11-26 11:26:32', NULL, 0);
INSERT INTO `p_tag_post` VALUES (7596207562755, 981498880004, 7273183240193, '2022-11-26 11:38:20', '2022-11-26 11:38:20', NULL, 0);
INSERT INTO `p_tag_post` VALUES (7715552288769, 101757964293, 7273183240193, '2022-11-26 11:38:48', '2022-11-26 11:38:48', NULL, 0);
INSERT INTO `p_tag_post` VALUES (7838457978881, 230606983174, 7273183240193, '2022-11-26 11:39:18', '2022-11-26 11:39:18', NULL, 0);
INSERT INTO `p_tag_post` VALUES (8027948244994, 423880511495, 7273183240193, '2022-11-26 11:40:03', '2022-11-26 11:40:03', NULL, 0);
INSERT INTO `p_tag_post` VALUES (8579536969731, 986521227272, 7194376462338, '2022-11-26 11:42:14', '2022-11-26 11:42:14', NULL, 0);
INSERT INTO `p_tag_post` VALUES (9112796585987, 531982073865, 7242422214657, '2022-11-26 11:44:21', '2022-11-26 11:44:21', NULL, 0);
INSERT INTO `p_tag_post` VALUES (1629756465587712001, 2387659653122, 1629756421308444674, '2023-02-26 16:13:15', '2023-02-26 16:13:15', NULL, 0);
INSERT INTO `p_tag_post` VALUES (1629756741338107906, 2387663912963, 1629756421308444674, '2023-02-26 16:14:21', '2023-02-26 16:14:21', NULL, 0);
INSERT INTO `p_tag_post` VALUES (1629764431657340929, 2387784105985, 1629756421308444674, '2023-02-26 16:44:54', '2023-02-26 16:44:54', NULL, 0);

-- ----------------------------
-- Table structure for p_user
-- ----------------------------
DROP TABLE IF EXISTS `p_user`;
CREATE TABLE `p_user`  (
                           `id` bigint UNSIGNED NOT NULL AUTO_INCREMENT COMMENT '用户ID',
                           `nickname` varchar(32) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL DEFAULT '' COMMENT '昵称',
                           `username` varchar(32) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL DEFAULT '' COMMENT '用户名',
                           `phone` varchar(16) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL DEFAULT '' COMMENT '手机号',
                           `email` varchar(32) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL DEFAULT '' COMMENT '邮箱',
                           `password` varchar(32) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL DEFAULT '' COMMENT 'MD5密码',
                           `salt` varchar(16) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL DEFAULT '' COMMENT '盐值',
                           `status` tinyint UNSIGNED NOT NULL DEFAULT 1 COMMENT '状态，1正常，2停用',
                           `avatar` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL DEFAULT 'http://pixel-revolve.test.upcdn.net/images/avatar/default.png' COMMENT '用户头像',
                           `balance` bigint UNSIGNED NOT NULL DEFAULT 0 COMMENT '用户余额（分）',
                           `is_admin` tinyint UNSIGNED NOT NULL DEFAULT 0 COMMENT '是否管理员',
                           `created_on` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
                           `modified_on` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '修改时间',
                           `deleted_on` timestamp NULL DEFAULT NULL COMMENT '删除时间',
                           `is_del` tinyint UNSIGNED NOT NULL DEFAULT 0 COMMENT '是否删除 0 为未删除、1 为已删除',
                           PRIMARY KEY (`id`) USING BTREE,
                           UNIQUE INDEX `idx_username`(`username` ASC) USING BTREE,
                           UNIQUE INDEX `idx_phone`(`phone` ASC) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 1629503893927251970 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci COMMENT = '用户' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of p_user
-- ----------------------------
INSERT INTO `p_user` VALUES (52483444737, '小粉丝', '120201016330158084', '13838411438', '', '', '', 1, 'http://pixel-revolve.test.upcdn.net/images/avatar/default.png', 0, 0, '2022-11-20 22:01:20', '2023-02-26 10:53:27', NULL, 0);
INSERT INTO `p_user` VALUES (204085702657, '熊二', '120190931746947074', '13770511673', '', '', '', 1, 'http://pixel-revolve.test.upcdn.net/images/avatar/default.png', 0, 0, '2022-11-20 21:22:12', '2023-02-26 11:23:15', NULL, 0);
INSERT INTO `p_user` VALUES (2383742042114, 'pixel-revolve', '2383742042113', '13770909113', '', '', '', 1, 'http://pixel-revolve.test.upcdn.net/images/avatar/default.png', 0, 0, '2023-02-25 23:36:57', '2023-02-25 23:37:49', NULL, 0);
INSERT INTO `p_user` VALUES (2383747153923, 'ovO', '2383747153922', '13770909114', '', '', '', 1, 'http://pixel-revolve.test.upcdn.net/images/avatar/default.png', 0, 0, '2023-02-25 23:38:15', '2023-02-25 23:38:37', NULL, 0);
INSERT INTO `p_user` VALUES (2383758622724, '守望时空33', '2383758622723', '13770909115', '', '', '', 1, 'http://pixel-revolve.test.upcdn.net/images/avatar/default.png', 0, 0, '2023-02-25 23:41:10', '2023-02-25 23:42:23', NULL, 0);
INSERT INTO `p_user` VALUES (2383774154753, '小小小张', '2383774154753', '13770909116', '', '', '', 1, 'http://pixel-revolve.test.upcdn.net/images/avatar/default.png', 0, 0, '2023-02-25 23:45:07', '2023-02-25 23:45:25', NULL, 0);
INSERT INTO `p_user` VALUES (2383778414594, 'nika_yo_nihao', '2383778414594', '13770909117', '', '', '', 1, 'http://pixel-revolve.test.upcdn.net/images/avatar/default.png', 0, 0, '2023-02-25 23:46:12', '2023-02-25 23:46:47', NULL, 0);

-- ----------------------------
-- Table structure for p_wallet_recharge
-- ----------------------------
DROP TABLE IF EXISTS `p_wallet_recharge`;
CREATE TABLE `p_wallet_recharge`  (
                                      `id` bigint UNSIGNED NOT NULL AUTO_INCREMENT COMMENT '充值ID',
                                      `user_id` bigint UNSIGNED NOT NULL DEFAULT 0 COMMENT '用户ID',
                                      `amount` bigint NOT NULL DEFAULT 0 COMMENT '充值金额',
                                      `trade_no` varchar(64) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL DEFAULT '' COMMENT '支付宝订单号',
                                      `trade_status` varchar(32) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL DEFAULT '' COMMENT '交易状态',
                                      `created_on` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
                                      `modified_on` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '修改时间',
                                      `deleted_on` timestamp NULL DEFAULT NULL COMMENT '删除时间',
                                      `is_del` tinyint UNSIGNED NOT NULL DEFAULT 0 COMMENT '是否删除 0 为未删除、1 为已删除',
                                      PRIMARY KEY (`id`) USING BTREE,
                                      INDEX `idx_user`(`user_id` ASC) USING BTREE,
                                      INDEX `idx_trade_no`(`trade_no` ASC) USING BTREE,
                                      INDEX `idx_trade_status`(`trade_status` ASC) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 10023 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci COMMENT = '钱包流水' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of p_wallet_recharge
-- ----------------------------

-- ----------------------------
-- Table structure for p_wallet_statement
-- ----------------------------
DROP TABLE IF EXISTS `p_wallet_statement`;
CREATE TABLE `p_wallet_statement`  (
                                       `id` bigint UNSIGNED NOT NULL AUTO_INCREMENT COMMENT '账单ID',
                                       `user_id` bigint UNSIGNED NOT NULL DEFAULT 0 COMMENT '用户ID',
                                       `change_amount` bigint NOT NULL DEFAULT 0 COMMENT '变动金额',
                                       `balance_snapshot` bigint NOT NULL DEFAULT 0 COMMENT '资金快照',
                                       `reason` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT '变动原因',
                                       `post_id` bigint UNSIGNED NOT NULL DEFAULT 0 COMMENT '关联动态',
                                       `created_on` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
                                       `modified_on` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '修改时间',
                                       `deleted_on` timestamp NULL DEFAULT NULL COMMENT '删除时间',
                                       `is_del` tinyint UNSIGNED NOT NULL DEFAULT 0 COMMENT '是否删除 0 为未删除、1 为已删除',
                                       PRIMARY KEY (`id`) USING BTREE,
                                       INDEX `idx_user`(`user_id` ASC) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 10010 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci COMMENT = '钱包流水' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of p_wallet_statement
-- ----------------------------

SET FOREIGN_KEY_CHECKS = 1;
