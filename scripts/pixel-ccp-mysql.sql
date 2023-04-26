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

 Date: 26/04/2023 11:48:44
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
) ENGINE = InnoDB AUTO_INCREMENT = 100041 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci COMMENT = '附件' ROW_FORMAT = DYNAMIC;

-- ----------------------------
-- Table structure for p_attr
-- ----------------------------
DROP TABLE IF EXISTS `p_attr`;
CREATE TABLE `p_attr`  (
                           `id` bigint UNSIGNED NOT NULL AUTO_INCREMENT COMMENT '主键',
                           `attr_name` varchar(30) CHARACTER SET utf8mb3 COLLATE utf8mb3_general_ci NOT NULL COMMENT '属性名',
                           `search_type` tinyint UNSIGNED NOT NULL DEFAULT 0 COMMENT '是否需要检索：0->不需要；1->需要',
                           `icon` varchar(255) CHARACTER SET utf8mb3 COLLATE utf8mb3_general_ci NULL DEFAULT NULL COMMENT '属性图标',
                           `value_select` varchar(255) CHARACTER SET utf8mb3 COLLATE utf8mb3_general_ci NULL DEFAULT NULL COMMENT '可选值列表[用逗号分隔]',
                           `attr_type` tinyint UNSIGNED NOT NULL DEFAULT 0 COMMENT '属性类型：0->销售属性；1->基本属性；2->既是销售属性又是基本属性',
                           `enable` tinyint UNSIGNED NOT NULL DEFAULT 0 COMMENT '启用状态：0->禁用；1->启用',
                           `category_id` bigint UNSIGNED NOT NULL COMMENT '所属分类',
                           `show_desc` tinyint UNSIGNED NOT NULL DEFAULT 0 COMMENT '快速展示[是否展示在介绍上：0->否；1->是]，在sku中仍然可以调整',
                           `created_on` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
                           `modified_on` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '修改时间',
                           `deleted_on` timestamp NULL DEFAULT NULL COMMENT '删除时间',
                           `is_del` tinyint UNSIGNED NOT NULL DEFAULT 0 COMMENT '是否删除 0 为未删除、1 为已删除',
                           PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8mb3 COLLATE = utf8mb3_general_ci COMMENT = '产品属性' ROW_FORMAT = DYNAMIC;

-- ----------------------------
-- Table structure for p_attr_attrgroup_relation
-- ----------------------------
DROP TABLE IF EXISTS `p_attr_attrgroup_relation`;
CREATE TABLE `p_attr_attrgroup_relation`  (
                                              `id` bigint UNSIGNED NOT NULL AUTO_INCREMENT COMMENT '主键',
                                              `attr_sort` int NOT NULL DEFAULT 0 COMMENT '属性组内排序',
                                              `attr_group_id` bigint UNSIGNED NOT NULL COMMENT '属性组id',
                                              `attr_id` bigint UNSIGNED NOT NULL COMMENT '属性id',
                                              `created_on` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
                                              `modified_on` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '修改时间',
                                              `deleted_on` timestamp NULL DEFAULT NULL COMMENT '删除时间',
                                              `is_del` tinyint UNSIGNED NOT NULL DEFAULT 0 COMMENT '是否删除 0 为未删除、1 为已删除',
                                              PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8mb3 COLLATE = utf8mb3_general_ci ROW_FORMAT = DYNAMIC;

-- ----------------------------
-- Table structure for p_attr_group
-- ----------------------------
DROP TABLE IF EXISTS `p_attr_group`;
CREATE TABLE `p_attr_group`  (
                                 `id` bigint UNSIGNED NOT NULL AUTO_INCREMENT COMMENT '主键',
                                 `attr_group_name` varchar(20) CHARACTER SET utf8mb3 COLLATE utf8mb3_general_ci NOT NULL COMMENT '组名',
                                 `sort` int NOT NULL DEFAULT 0 COMMENT '排序',
                                 `description` varchar(255) CHARACTER SET utf8mb3 COLLATE utf8mb3_general_ci NULL DEFAULT NULL COMMENT '描述',
                                 `icon` varchar(255) CHARACTER SET utf8mb3 COLLATE utf8mb3_general_ci NULL DEFAULT NULL COMMENT '组图标',
                                 `category_id` bigint UNSIGNED NOT NULL COMMENT '所属分类id',
                                 `created_on` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
                                 `modified_on` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '修改时间',
                                 `deleted_on` timestamp NULL DEFAULT NULL COMMENT '删除时间',
                                 `is_del` tinyint UNSIGNED NOT NULL DEFAULT 0 COMMENT '是否删除 0 为未删除、1 为已删除',
                                 PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8mb3 COLLATE = utf8mb3_general_ci COMMENT = '产品属性组' ROW_FORMAT = DYNAMIC;

-- ----------------------------
-- Table structure for p_category
-- ----------------------------
DROP TABLE IF EXISTS `p_category`;
CREATE TABLE `p_category`  (
                               `id` bigint UNSIGNED NOT NULL AUTO_INCREMENT COMMENT '产品类别ID',
                               `parent_id` bigint UNSIGNED NOT NULL DEFAULT 0 COMMENT '上级分类的编号：0表示一级分类',
                               `category_name` varchar(64) CHARACTER SET utf8mb3 COLLATE utf8mb3_general_ci NOT NULL COMMENT '类别名',
                               `sort` int NOT NULL DEFAULT 0 COMMENT '排序',
                               `category_level` int NOT NULL DEFAULT 0 COMMENT '分类级别：0->1级；1->2级',
                               `icon` varchar(255) CHARACTER SET utf8mb3 COLLATE utf8mb3_general_ci NULL DEFAULT NULL COMMENT '图标',
                               `description` varchar(255) CHARACTER SET utf8mb3 COLLATE utf8mb3_general_ci NULL DEFAULT NULL COMMENT '描述',
                               `created_on` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
                               `modified_on` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '修改时间',
                               `deleted_on` timestamp NULL DEFAULT NULL COMMENT '删除时间',
                               `is_del` tinyint UNSIGNED NOT NULL DEFAULT 0 COMMENT '是否删除 0 为未删除、1 为已删除',
                               PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 4 CHARACTER SET = utf8mb3 COLLATE = utf8mb3_general_ci COMMENT = '产品类别' ROW_FORMAT = DYNAMIC;

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
) ENGINE = InnoDB AUTO_INCREMENT = 2162685444101 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci COMMENT = '评论' ROW_FORMAT = DYNAMIC;

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
) ENGINE = InnoDB AUTO_INCREMENT = 2162685509637 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci COMMENT = '评论内容' ROW_FORMAT = DYNAMIC;

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
) ENGINE = InnoDB AUTO_INCREMENT = 2162687213570 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci COMMENT = '评论回复' ROW_FORMAT = DYNAMIC;

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
) ENGINE = InnoDB AUTO_INCREMENT = 1629685010778824707 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci COMMENT = '关注' ROW_FORMAT = DYNAMIC;

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
) ENGINE = InnoDB AUTO_INCREMENT = 16000033 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci COMMENT = '消息通知' ROW_FORMAT = DYNAMIC;

-- ----------------------------
-- Table structure for p_order
-- ----------------------------
DROP TABLE IF EXISTS `p_order`;
CREATE TABLE `p_order`  (
                            `id` bigint UNSIGNED NOT NULL AUTO_INCREMENT COMMENT '订单ID',
                            `user_id` bigint UNSIGNED NOT NULL DEFAULT 0 COMMENT '用户ID',
                            `total_amount` bigint UNSIGNED NOT NULL DEFAULT 0 COMMENT '订单总金额',
                            `pay_amount` bigint UNSIGNED NOT NULL DEFAULT 0 COMMENT '应付金额（分）',
                            `freight_amount` bigint UNSIGNED NOT NULL DEFAULT 0 COMMENT '运费金额（分）',
                            `pay_type` tinyint UNSIGNED NOT NULL DEFAULT 1 COMMENT '支付方式：1->像素币；2->支付宝；3->微信； 4->货到付款；',
                            `order_status` tinyint UNSIGNED NOT NULL DEFAULT 0 COMMENT '订单状态：0->待付款；1->已付款；2->已发货；3->已完成；4->已关闭；5->售后中；6->售后完成',
                            `receiver_name` varchar(32) CHARACTER SET utf8mb3 COLLATE utf8mb3_general_ci NULL DEFAULT NULL COMMENT '收货人姓名',
                            `receiver_phone` varchar(32) CHARACTER SET utf8mb3 COLLATE utf8mb3_general_ci NULL DEFAULT NULL COMMENT '收货人电话',
                            `receiver_province` varchar(32) CHARACTER SET utf8mb3 COLLATE utf8mb3_general_ci NULL DEFAULT NULL COMMENT '省份',
                            `receiver_city` varchar(32) CHARACTER SET utf8mb3 COLLATE utf8mb3_general_ci NULL DEFAULT NULL COMMENT '市',
                            `receiver_detail_address` varchar(255) CHARACTER SET utf8mb3 COLLATE utf8mb3_general_ci NULL DEFAULT NULL COMMENT '详细地址',
                            `note` varchar(255) CHARACTER SET utf8mb3 COLLATE utf8mb3_general_ci NULL DEFAULT NULL COMMENT '订单备注',
                            `delivery_time` timestamp NULL DEFAULT NULL COMMENT '发货时间',
                            `receive_time` timestamp NULL DEFAULT NULL COMMENT '确认收货时间',
                            `created_on` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
                            `modified_on` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '修改时间',
                            `deleted_on` timestamp NULL DEFAULT NULL COMMENT '删除时间',
                            `is_del` tinyint UNSIGNED NOT NULL DEFAULT 0 COMMENT '是否删除 0 为未删除、1 为已删除',
                            PRIMARY KEY (`id`) USING BTREE,
                            INDEX `idx_user`(`user_id` ASC) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8mb3 COLLATE = utf8mb3_general_ci COMMENT = '订单' ROW_FORMAT = DYNAMIC;

-- ----------------------------
-- Table structure for p_order_item
-- ----------------------------
DROP TABLE IF EXISTS `p_order_item`;
CREATE TABLE `p_order_item`  (
                                 `id` bigint UNSIGNED NOT NULL AUTO_INCREMENT COMMENT '订单商品ID',
                                 `order_id` bigint UNSIGNED NOT NULL COMMENT '订单ID',
                                 `spu_id` bigint UNSIGNED NOT NULL COMMENT '商品ID',
                                 `spu_name` varchar(255) CHARACTER SET utf8mb3 COLLATE utf8mb3_general_ci NOT NULL COMMENT '商品名',
                                 `spu_pic` varchar(255) CHARACTER SET utf8mb3 COLLATE utf8mb3_general_ci NOT NULL COMMENT '商品图片',
                                 `category_id` bigint UNSIGNED NOT NULL COMMENT '商品分类ID',
                                 `sku_id` bigint UNSIGNED NOT NULL COMMENT '商品skuID',
                                 `sku_pic` varchar(255) CHARACTER SET utf8mb3 COLLATE utf8mb3_general_ci NULL DEFAULT NULL COMMENT '商品展示图',
                                 `sku_name` varchar(255) CHARACTER SET utf8mb3 COLLATE utf8mb3_general_ci NOT NULL COMMENT '商品名字',
                                 `sku_quantity` int NOT NULL DEFAULT 0 COMMENT '商品购买数量',
                                 `sku_price` bigint UNSIGNED NOT NULL DEFAULT 0 COMMENT '商品价格（分）',
                                 `sku_attrs_vals` varchar(500) CHARACTER SET utf8mb3 COLLATE utf8mb3_general_ci NULL DEFAULT NULL COMMENT '商品销售属性组合（JSON）',
                                 `created_on` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
                                 `modified_on` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '修改时间',
                                 `deleted_on` timestamp NULL DEFAULT NULL COMMENT '删除时间',
                                 `is_del` tinyint UNSIGNED NOT NULL DEFAULT 0 COMMENT '是否删除 0 为未删除、1 为已删除',
                                 PRIMARY KEY (`id`) USING BTREE,
                                 INDEX `idx_order`(`order_id` ASC) USING BTREE,
                                 INDEX `idx_spu`(`spu_id` ASC) USING BTREE,
                                 INDEX `idx_category`(`category_id` ASC) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8mb3 COLLATE = utf8mb3_general_ci COMMENT = '订单商品' ROW_FORMAT = DYNAMIC;

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
) ENGINE = InnoDB AUTO_INCREMENT = 2387784105986 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci COMMENT = '冒泡/文章' ROW_FORMAT = DYNAMIC;

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
) ENGINE = InnoDB AUTO_INCREMENT = 6000012 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci COMMENT = '冒泡/文章收藏' ROW_FORMAT = DYNAMIC;

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
) ENGINE = InnoDB AUTO_INCREMENT = 9112796585987 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci COMMENT = '冒泡/文章内容' ROW_FORMAT = DYNAMIC;

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
) ENGINE = InnoDB AUTO_INCREMENT = 6000028 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci COMMENT = '冒泡/文章点赞' ROW_FORMAT = DYNAMIC;

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
-- Table structure for p_sku
-- ----------------------------
DROP TABLE IF EXISTS `p_sku`;
CREATE TABLE `p_sku`  (
                          `id` bigint UNSIGNED NOT NULL AUTO_INCREMENT COMMENT '主键\n',
                          `spu_id` bigint UNSIGNED NOT NULL COMMENT 'spuID',
                          `sku_name` varchar(255) CHARACTER SET utf8mb3 COLLATE utf8mb3_general_ci NOT NULL COMMENT 'sku名称',
                          `sku_desc` varchar(2000) CHARACTER SET utf8mb3 COLLATE utf8mb3_general_ci NULL DEFAULT NULL COMMENT 'sku介绍描述',
                          `sku_title` varchar(255) CHARACTER SET utf8mb3 COLLATE utf8mb3_general_ci NOT NULL COMMENT 'sku标题',
                          `sku_pic` varchar(255) CHARACTER SET utf8mb3 COLLATE utf8mb3_general_ci NULL DEFAULT NULL COMMENT 'sku展示图片',
                          `category_id` bigint UNSIGNED NOT NULL COMMENT '所属分类id',
                          `sku_subtitle` varchar(2000) CHARACTER SET utf8mb3 COLLATE utf8mb3_general_ci NULL DEFAULT NULL COMMENT '副标题',
                          `price` bigint UNSIGNED NOT NULL COMMENT '价格（分）',
                          `sale_count` bigint UNSIGNED NOT NULL DEFAULT 0 COMMENT '销量',
                          `created_on` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
                          `modified_on` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '修改时间',
                          `deleted_on` timestamp NULL DEFAULT NULL COMMENT '删除时间',
                          `is_del` tinyint UNSIGNED NOT NULL DEFAULT 0 COMMENT '是否删除 0 为未删除、1 为已删除',
                          PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8mb3 COLLATE = utf8mb3_general_ci COMMENT = '产品库存表' ROW_FORMAT = DYNAMIC;

-- ----------------------------
-- Table structure for p_sku_sale_attr_value
-- ----------------------------
DROP TABLE IF EXISTS `p_sku_sale_attr_value`;
CREATE TABLE `p_sku_sale_attr_value`  (
                                          `id` bigint UNSIGNED NOT NULL AUTO_INCREMENT COMMENT '主键',
                                          `sku_id` bigint UNSIGNED NOT NULL COMMENT '商品skuID',
                                          `attr_id` bigint UNSIGNED NOT NULL COMMENT '属性ID',
                                          `attr_name` varchar(255) CHARACTER SET utf8mb3 COLLATE utf8mb3_general_ci NOT NULL COMMENT '销售属性名',
                                          `attr_value` varchar(255) CHARACTER SET utf8mb3 COLLATE utf8mb3_general_ci NOT NULL COMMENT '销售属性值',
                                          `attr_sort` int NOT NULL DEFAULT 0 COMMENT '顺序',
                                          `created_on` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
                                          `modified_on` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '修改时间',
                                          `deleted_on` timestamp NULL DEFAULT NULL COMMENT '删除时间',
                                          `is_del` tinyint UNSIGNED NOT NULL DEFAULT 0 COMMENT '是否删除 0 为未删除、1 为已删除',
                                          PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8mb3 COLLATE = utf8mb3_general_ci COMMENT = '产品库存销售属性值' ROW_FORMAT = DYNAMIC;

-- ----------------------------
-- Table structure for p_spu
-- ----------------------------
DROP TABLE IF EXISTS `p_spu`;
CREATE TABLE `p_spu`  (
                          `id` bigint UNSIGNED NOT NULL AUTO_INCREMENT COMMENT '产品ID',
                          `category_id` bigint UNSIGNED NOT NULL COMMENT '产品分类ID',
                          `spu_content_id` bigint UNSIGNED NOT NULL COMMENT '知识附件等产品ID',
                          `spu_name` varchar(255) CHARACTER SET utf8mb3 COLLATE utf8mb3_general_ci NOT NULL COMMENT '商品名',
                          `pic` varchar(255) CHARACTER SET utf8mb3 COLLATE utf8mb3_general_ci NULL DEFAULT NULL COMMENT '商品展示图',
                          `publish_status` tinyint UNSIGNED NOT NULL DEFAULT 0 COMMENT '上架状态：0->下架；1->上架',
                          `new_status` tinyint UNSIGNED NOT NULL DEFAULT 0 COMMENT '新品状态：0->不是新品；1->新品',
                          `sale` int NOT NULL DEFAULT 0 COMMENT '销量',
                          `sort` int NOT NULL DEFAULT 0 COMMENT '排序',
                          `price` bigint UNSIGNED NOT NULL DEFAULT 0 COMMENT '价格（分）',
                          `description` varchar(255) CHARACTER SET utf8mb3 COLLATE utf8mb3_general_ci NULL DEFAULT NULL COMMENT '描述',
                          `unit` varchar(16) CHARACTER SET utf8mb3 COLLATE utf8mb3_general_ci NULL DEFAULT NULL COMMENT '单位',
                          `created_on` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
                          `modified_on` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '修改时间',
                          `deleted_on` timestamp NULL DEFAULT NULL COMMENT '删除时间',
                          `is_del` tinyint UNSIGNED NOT NULL DEFAULT 0 COMMENT '是否删除 0 为未删除、1 为已删除',
                          PRIMARY KEY (`id`) USING BTREE,
                          INDEX `idx_category`(`category_id` ASC) USING BTREE,
                          INDEX `idx_spu_content`(`spu_content_id` ASC) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 41 CHARACTER SET = utf8mb3 COLLATE = utf8mb3_general_ci COMMENT = '产品表' ROW_FORMAT = DYNAMIC;

-- ----------------------------
-- Table structure for p_spu_attr_value
-- ----------------------------
DROP TABLE IF EXISTS `p_spu_attr_value`;
CREATE TABLE `p_spu_attr_value`  (
                                     `id` bigint UNSIGNED NOT NULL AUTO_INCREMENT COMMENT '主键',
                                     `spu_id` bigint UNSIGNED NOT NULL COMMENT '商品ID',
                                     `attr_id` bigint UNSIGNED NOT NULL COMMENT '属性ID',
                                     `attr_value` varchar(255) CHARACTER SET utf8mb3 COLLATE utf8mb3_general_ci NOT NULL COMMENT '属性值',
                                     `attr_name` varchar(255) CHARACTER SET utf8mb3 COLLATE utf8mb3_general_ci NOT NULL COMMENT '属性名',
                                     `quick_show` tinyint UNSIGNED NOT NULL DEFAULT 0 COMMENT '快速展示[是否展示在介绍上：0->否；1->是]',
                                     `created_on` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
                                     `modified_on` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '修改时间',
                                     `deleted_on` timestamp NULL DEFAULT NULL COMMENT '删除时间',
                                     `is_del` tinyint UNSIGNED NOT NULL DEFAULT 0 COMMENT '是否删除 0 为未删除、1 为已删除',
                                     PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8mb3 COLLATE = utf8mb3_general_ci COMMENT = '产品属性值' ROW_FORMAT = DYNAMIC;

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
) ENGINE = InnoDB AUTO_INCREMENT = 1629756421308444675 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci COMMENT = '标签' ROW_FORMAT = DYNAMIC;

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
) ENGINE = InnoDB AUTO_INCREMENT = 1629764431657340930 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci COMMENT = '标签文章关联表' ROW_FORMAT = DYNAMIC;

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
) ENGINE = InnoDB AUTO_INCREMENT = 1629503893927251970 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci COMMENT = '用户' ROW_FORMAT = DYNAMIC;

-- ----------------------------
-- Table structure for p_user_receive_address
-- ----------------------------
DROP TABLE IF EXISTS `p_user_receive_address`;
CREATE TABLE `p_user_receive_address`  (
                                           `id` bigint UNSIGNED NOT NULL AUTO_INCREMENT COMMENT '主键',
                                           `user_id` bigint UNSIGNED NOT NULL COMMENT '用户ID',
                                           `name` varchar(32) CHARACTER SET utf8mb3 COLLATE utf8mb3_general_ci NOT NULL COMMENT '收货人姓名',
                                           `phone` varchar(32) CHARACTER SET utf8mb3 COLLATE utf8mb3_general_ci NOT NULL COMMENT '电话',
                                           `city` varchar(32) CHARACTER SET utf8mb3 COLLATE utf8mb3_general_ci NOT NULL COMMENT '城市',
                                           `province` varchar(32) CHARACTER SET utf8mb3 COLLATE utf8mb3_general_ci NULL DEFAULT NULL COMMENT '省份',
                                           `detail_address` varchar(255) CHARACTER SET utf8mb3 COLLATE utf8mb3_general_ci NOT NULL COMMENT '详细地址（街道）',
                                           `areacode` varchar(20) CHARACTER SET utf8mb3 COLLATE utf8mb3_general_ci NOT NULL COMMENT '区域编码',
                                           `default_status` tinyint UNSIGNED NOT NULL DEFAULT 0 COMMENT '是否默认',
                                           `created_on` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
                                           `modified_on` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '修改时间',
                                           `deleted_on` timestamp NULL DEFAULT NULL COMMENT '删除时间',
                                           `is_del` tinyint UNSIGNED NOT NULL DEFAULT 0 COMMENT '是否删除 0 为未删除、1 为已删除',
                                           PRIMARY KEY (`id`) USING BTREE,
                                           UNIQUE INDEX `idx_user`(`user_id` ASC) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8mb3 COLLATE = utf8mb3_general_ci COMMENT = '用户收货地址表\n' ROW_FORMAT = DYNAMIC;

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
) ENGINE = InnoDB AUTO_INCREMENT = 10023 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci COMMENT = '钱包充值' ROW_FORMAT = DYNAMIC;

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
                                       `order_id` bigint UNSIGNED NOT NULL DEFAULT 0 COMMENT '关联订单',
                                       `created_on` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
                                       `modified_on` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '修改时间',
                                       `deleted_on` timestamp NULL DEFAULT NULL COMMENT '删除时间',
                                       `is_del` tinyint UNSIGNED NOT NULL DEFAULT 0 COMMENT '是否删除 0 为未删除、1 为已删除',
                                       PRIMARY KEY (`id`) USING BTREE,
                                       INDEX `idx_user`(`user_id` ASC) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 10010 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci COMMENT = '钱包流水' ROW_FORMAT = DYNAMIC;

-- ----------------------------
-- Table structure for p_ware
-- ----------------------------
DROP TABLE IF EXISTS `p_ware`;
CREATE TABLE `p_ware`  (
                           `id` bigint UNSIGNED NOT NULL AUTO_INCREMENT COMMENT '仓库ID',
                           `ware_name` varchar(255) CHARACTER SET utf8mb3 COLLATE utf8mb3_general_ci NOT NULL COMMENT '仓库名',
                           `address` varchar(255) CHARACTER SET utf8mb3 COLLATE utf8mb3_general_ci NULL DEFAULT NULL COMMENT '仓库地址',
                           `areacode` varchar(20) CHARACTER SET utf8mb3 COLLATE utf8mb3_general_ci NULL DEFAULT NULL COMMENT '区域编码',
                           `created_on` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
                           `modified_on` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '修改时间',
                           `deleted_on` timestamp NULL DEFAULT NULL COMMENT '删除时间',
                           `is_del` tinyint UNSIGNED NOT NULL DEFAULT 0 COMMENT '是否删除 0 为未删除、1 为已删除',
                           PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8mb3 COLLATE = utf8mb3_general_ci COMMENT = '仓库' ROW_FORMAT = DYNAMIC;

-- ----------------------------
-- Table structure for p_ware_order_task
-- ----------------------------
DROP TABLE IF EXISTS `p_ware_order_task`;
CREATE TABLE `p_ware_order_task`  (
                                      `id` bigint UNSIGNED NOT NULL AUTO_INCREMENT,
                                      `order_id` bigint UNSIGNED NOT NULL COMMENT '订单ID',
                                      `receiver_name` varchar(32) CHARACTER SET utf8mb3 COLLATE utf8mb3_general_ci NOT NULL COMMENT '收货人姓名',
                                      `receiver_phone` varchar(32) CHARACTER SET utf8mb3 COLLATE utf8mb3_general_ci NOT NULL COMMENT '收货人电话',
                                      `receiver_province` varchar(32) CHARACTER SET utf8mb3 COLLATE utf8mb3_general_ci NOT NULL COMMENT '省份',
                                      `receiver_city` varchar(32) CHARACTER SET utf8mb3 COLLATE utf8mb3_general_ci NOT NULL COMMENT '市',
                                      `receiver_detail_address` varchar(255) CHARACTER SET utf8mb3 COLLATE utf8mb3_general_ci NOT NULL COMMENT '详细地址',
                                      `note` varchar(255) CHARACTER SET utf8mb3 COLLATE utf8mb3_general_ci NULL DEFAULT NULL COMMENT '订单备注',
                                      `payment_way` tinyint UNSIGNED NOT NULL DEFAULT 0 COMMENT '付款方式：1->在线付款 2->货到付款',
                                      `task_status` tinyint UNSIGNED NOT NULL DEFAULT 0 COMMENT '任务状态',
                                      `task_comment` varchar(255) CHARACTER SET utf8mb3 COLLATE utf8mb3_general_ci NULL DEFAULT NULL COMMENT '工作单备注',
                                      `ware_id` bigint UNSIGNED NOT NULL COMMENT '仓库ID',
                                      `created_on` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
                                      `modified_on` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '修改时间',
                                      `deleted_on` timestamp NULL DEFAULT NULL COMMENT '删除时间',
                                      `is_del` tinyint UNSIGNED NOT NULL DEFAULT 0 COMMENT '是否删除 0 为未删除、1 为已删除',
                                      PRIMARY KEY (`id`) USING BTREE,
                                      INDEX `idx_order`(`order_id` ASC) USING BTREE,
                                      INDEX `idx_ware`(`ware_id` ASC) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8mb3 COLLATE = utf8mb3_general_ci COMMENT = '库存工作单' ROW_FORMAT = DYNAMIC;

-- ----------------------------
-- Table structure for p_ware_order_task_detail
-- ----------------------------
DROP TABLE IF EXISTS `p_ware_order_task_detail`;
CREATE TABLE `p_ware_order_task_detail`  (
                                             `id` bigint UNSIGNED NOT NULL AUTO_INCREMENT COMMENT '主键',
                                             `sku_id` bigint UNSIGNED NOT NULL COMMENT '库存ID',
                                             `sku_num` int NOT NULL DEFAULT 0 COMMENT '购买个数',
                                             `task_id` bigint UNSIGNED NOT NULL COMMENT '工作单ID',
                                             `ware_id` bigint UNSIGNED NOT NULL COMMENT '仓库ID',
                                             `lock_status` tinyint UNSIGNED NOT NULL DEFAULT 0 COMMENT '锁定状态：1->已锁定 2->已解锁 3->扣减',
                                             `created_on` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
                                             `modified_on` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '修改时间',
                                             `deleted_on` timestamp NULL DEFAULT NULL COMMENT '删除时间',
                                             `is_del` tinyint UNSIGNED NOT NULL DEFAULT 0 COMMENT '是否删除 0 为未删除、1 为已删除',
                                             PRIMARY KEY (`id`) USING BTREE,
                                             INDEX `idx_sku`(`sku_id` ASC) USING BTREE,
                                             INDEX `idx_task`(`task_id` ASC) USING BTREE,
                                             INDEX `idx_ware`(`ware_id` ASC) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8mb3 COLLATE = utf8mb3_general_ci COMMENT = '库存工作单细节' ROW_FORMAT = DYNAMIC;

-- ----------------------------
-- Table structure for p_ware_sku
-- ----------------------------
DROP TABLE IF EXISTS `p_ware_sku`;
CREATE TABLE `p_ware_sku`  (
                               `id` bigint UNSIGNED NOT NULL AUTO_INCREMENT COMMENT '产品库存ID',
                               `sku_id` bigint UNSIGNED NOT NULL COMMENT 'skuID',
                               `ware_id` bigint UNSIGNED NOT NULL COMMENT '仓库ID',
                               `stock` int NULL DEFAULT NULL COMMENT '库存数',
                               `stock_locked` int NULL DEFAULT NULL COMMENT '锁定库存',
                               `created_on` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
                               `modified_on` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '修改时间',
                               `deleted_on` timestamp NULL DEFAULT NULL COMMENT '删除时间',
                               `is_del` tinyint UNSIGNED NOT NULL DEFAULT 0 COMMENT '是否删除 0 为未删除、1 为已删除',
                               PRIMARY KEY (`id`) USING BTREE,
                               INDEX `idx_spu`(`sku_id` ASC) USING BTREE,
                               INDEX `idx_ware`(`ware_id` ASC) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8mb3 COLLATE = utf8mb3_general_ci COMMENT = '产品库存' ROW_FORMAT = DYNAMIC;

SET FOREIGN_KEY_CHECKS = 1;
