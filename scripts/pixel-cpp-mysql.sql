SET NAMES utf8mb4;
SET
FOREIGN_KEY_CHECKS = 0;

drop
database if EXISTS `pixel_ccp`;
create
database if not EXISTS `pixel_ccp`;
use
`pixel_ccp`;

-- ----------------------------
-- Table structure for p_attachment
-- ----------------------------
DROP TABLE IF EXISTS `p_attachment`;
CREATE TABLE `p_attachment`
(
    `id`          bigint unsigned NOT NULL AUTO_INCREMENT,
    `user_id`     bigint unsigned NOT NULL DEFAULT '0',
    `file_size`   bigint unsigned NOT NULL,
    `img_width`   bigint unsigned NOT NULL DEFAULT '0',
    `img_height`  bigint unsigned NOT NULL DEFAULT '0',
    `type`        tinyint unsigned NOT NULL DEFAULT '1' COMMENT '1图片，2视频，3其他附件',
    `content`     varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL DEFAULT '',
    `created_on`  timestamp                                                     NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
    `modified_on` timestamp                                                     NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '修改时间',
    `deleted_on`  timestamp                                                              DEFAULT NULL COMMENT '删除时间',
    `is_del`      tinyint unsigned NOT NULL DEFAULT '0' COMMENT '是否删除 0 为未删除、1 为已删除',
    PRIMARY KEY (`id`) USING BTREE,
    KEY           `idx_user` (`user_id`) USING BTREE
) ENGINE=InnoDB AUTO_INCREMENT=100041 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci COMMENT='附件';

-- ----------------------------
-- Table structure for p_captcha
-- ----------------------------
DROP TABLE IF EXISTS `p_captcha`;
CREATE TABLE `p_captcha`
(
    `id`          bigint unsigned NOT NULL AUTO_INCREMENT COMMENT '验证码ID',
    `phone`       varchar(16) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL DEFAULT '' COMMENT '手机号',
    `captcha`     varchar(16) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL DEFAULT '' COMMENT '验证码',
    `use_times`   int unsigned NOT NULL DEFAULT '0' COMMENT '使用次数',
    `expired_on`  bigint unsigned NOT NULL DEFAULT '0' COMMENT '过期时间',
    `created_on`  timestamp                                                    NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
    `modified_on` timestamp                                                    NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '修改时间',
    `deleted_on`  timestamp                                                             DEFAULT NULL COMMENT '删除时间',
    `is_del`      tinyint unsigned NOT NULL DEFAULT '0' COMMENT '是否删除 0 为未删除、1 为已删除',
    PRIMARY KEY (`id`) USING BTREE,
    KEY           `idx_phone` (`phone`) USING BTREE,
    KEY           `idx_expired_on` (`expired_on`) USING BTREE,
    KEY           `idx_use_times` (`use_times`) USING BTREE
) ENGINE=InnoDB AUTO_INCREMENT=1021 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci COMMENT='手机验证码';

-- ----------------------------
-- Table structure for p_comment
-- ----------------------------
DROP TABLE IF EXISTS `p_comment`;
CREATE TABLE `p_comment`
(
    `id`          bigint unsigned NOT NULL AUTO_INCREMENT COMMENT '评论ID',
    `post_id`     bigint unsigned NOT NULL DEFAULT '0' COMMENT 'POST ID',
    `user_id`     bigint unsigned NOT NULL DEFAULT '0' COMMENT '用户ID',
    `ip`          varchar(64) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL DEFAULT '' COMMENT 'IP地址',
    `ip_loc`      varchar(64) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL DEFAULT '' COMMENT 'IP城市地址',
    `created_on`  timestamp                                                    NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
    `modified_on` timestamp                                                    NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '修改时间',
    `deleted_on`  timestamp                                                             DEFAULT NULL COMMENT '删除时间',
    `is_del`      tinyint unsigned NOT NULL DEFAULT '0' COMMENT '是否删除 0 为未删除、1 为已删除',
    PRIMARY KEY (`id`) USING BTREE,
    KEY           `idx_post` (`post_id`) USING BTREE,
    KEY           `idx_user` (`user_id`) USING BTREE
) ENGINE=InnoDB AUTO_INCREMENT=6001736 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci COMMENT='评论';

-- ----------------------------
-- Table structure for p_comment_content
-- ----------------------------
DROP TABLE IF EXISTS `p_comment_content`;
CREATE TABLE `p_comment_content`
(
    `id`          bigint unsigned NOT NULL AUTO_INCREMENT COMMENT '内容ID',
    `comment_id`  bigint unsigned NOT NULL DEFAULT '0' COMMENT '评论ID',
    `user_id`     bigint unsigned NOT NULL DEFAULT '0' COMMENT '用户ID',
    `content`     varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL DEFAULT '' COMMENT '内容',
    `type`        tinyint unsigned NOT NULL DEFAULT '2' COMMENT '类型，1标题，2文字段落，3图片地址，4视频地址，5语音地址，6链接地址',
    `sort`        bigint unsigned NOT NULL DEFAULT '100' COMMENT '排序，越小越靠前',
    `created_on`  timestamp                                                     NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
    `modified_on` timestamp                                                     NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '修改时间',
    `deleted_on`  timestamp                                                              DEFAULT NULL COMMENT '删除时间',
    `is_del`      tinyint unsigned NOT NULL DEFAULT '0' COMMENT '是否删除 0 为未删除、1 为已删除',
    PRIMARY KEY (`id`) USING BTREE,
    KEY           `idx_reply` (`comment_id`) USING BTREE,
    KEY           `idx_user` (`user_id`) USING BTREE,
    KEY           `idx_type` (`type`) USING BTREE,
    KEY           `idx_sort` (`sort`) USING BTREE
) ENGINE=InnoDB AUTO_INCREMENT=11001738 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci COMMENT='评论内容';

-- ----------------------------
-- Table structure for p_comment_reply
-- ----------------------------
DROP TABLE IF EXISTS `p_comment_reply`;
CREATE TABLE `p_comment_reply`
(
    `id`          bigint unsigned NOT NULL AUTO_INCREMENT COMMENT '回复ID',
    `comment_id`  bigint unsigned NOT NULL DEFAULT '0' COMMENT '评论ID',
    `user_id`     bigint unsigned NOT NULL DEFAULT '0' COMMENT '用户ID',
    `at_user_id`  bigint unsigned NOT NULL DEFAULT '0' COMMENT '@用户ID',
    `content`     varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL DEFAULT '' COMMENT '内容',
    `ip`          varchar(64) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci  NOT NULL DEFAULT '' COMMENT 'IP地址',
    `ip_loc`      varchar(64) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci  NOT NULL DEFAULT '' COMMENT 'IP城市地址',
    `created_on`  timestamp                                                     NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
    `modified_on` timestamp                                                     NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '修改时间',
    `deleted_on`  timestamp                                                              DEFAULT NULL COMMENT '删除时间',
    `is_del`      tinyint unsigned NOT NULL DEFAULT '0' COMMENT '是否删除 0 为未删除、1 为已删除',
    PRIMARY KEY (`id`) USING BTREE,
    KEY           `idx_comment` (`comment_id`) USING BTREE
) ENGINE=InnoDB AUTO_INCREMENT=12000015 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci COMMENT='评论回复';

-- ----------------------------
-- Table structure for p_message
-- ----------------------------
DROP TABLE IF EXISTS `p_message`;
CREATE TABLE `p_message`
(
    `id`               bigint unsigned NOT NULL AUTO_INCREMENT COMMENT '消息通知ID',
    `sender_user_id`   bigint unsigned NOT NULL DEFAULT '0' COMMENT '发送方用户ID',
    `receiver_user_id` bigint unsigned NOT NULL DEFAULT '0' COMMENT '接收方用户ID',
    `type`             tinyint unsigned NOT NULL DEFAULT '1' COMMENT '通知类型，1动态，2评论，3回复，4私信，99系统通知',
    `brief`            varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL DEFAULT '' COMMENT '摘要说明',
    `content`          varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL DEFAULT '' COMMENT '详细内容',
    `post_id`          bigint unsigned NOT NULL DEFAULT '0' COMMENT '动态ID',
    `comment_id`       bigint unsigned NOT NULL DEFAULT '0' COMMENT '评论ID',
    `reply_id`         bigint unsigned NOT NULL DEFAULT '0' COMMENT '回复ID',
    `is_read`          tinyint unsigned NOT NULL DEFAULT '0' COMMENT '是否已读',
    `created_on`       timestamp                                                     NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
    `modified_on`      timestamp                                                     NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '修改时间',
    `deleted_on`       timestamp                                                              DEFAULT NULL COMMENT '删除时间',
    `is_del`           tinyint unsigned NOT NULL DEFAULT '0' COMMENT '是否删除 0 为未删除、1 为已删除',
    PRIMARY KEY (`id`) USING BTREE,
    KEY                `idx_receiver` (`receiver_user_id`) USING BTREE,
    KEY                `idx_is_read` (`is_read`) USING BTREE,
    KEY                `idx_type` (`type`) USING BTREE
) ENGINE=InnoDB AUTO_INCREMENT=16000033 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci COMMENT='消息通知';

-- ----------------------------
-- Table structure for p_post
-- ----------------------------
DROP TABLE IF EXISTS `p_post`;
CREATE TABLE `p_post`
(
    `id`                bigint unsigned NOT NULL AUTO_INCREMENT COMMENT '主题ID',
    `user_id`           bigint unsigned NOT NULL DEFAULT '0' COMMENT '用户ID',
    `comment_count`     bigint unsigned NOT NULL DEFAULT '0' COMMENT '评论数',
    `collection_count`  bigint unsigned NOT NULL DEFAULT '0' COMMENT '收藏数',
    `upvote_count`      bigint unsigned NOT NULL DEFAULT '0' COMMENT '点赞数',
    `watch_count`      bigint unsigned NOT NULL DEFAULT '0' COMMENT '查看数',
    `summary`       varchar(255)  NOT NULL DEFAULT '' COMMENT '摘要',
    `visibility`        tinyint unsigned NOT NULL DEFAULT '0' COMMENT '可见性 0公开 1私密 2好友可见',
    `is_top`            tinyint unsigned NOT NULL DEFAULT '0' COMMENT '是否置顶',
    `is_essence`        tinyint unsigned NOT NULL DEFAULT '0' COMMENT '是否精华',
    `is_lock`           tinyint unsigned NOT NULL DEFAULT '0' COMMENT '是否锁定',
    `latest_replied_on` bigint unsigned NOT NULL DEFAULT '0' COMMENT '最新回复时间',
    `tags`              varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL DEFAULT '' COMMENT '标签',
    `attachment_price`  bigint unsigned NOT NULL DEFAULT '0' COMMENT '附件价格(分)',
    `ip`                varchar(64) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci  NOT NULL DEFAULT '' COMMENT 'IP地址',
    `ip_loc`            varchar(64) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci  NOT NULL DEFAULT '' COMMENT 'IP城市地址',
    `created_on`        timestamp                                                     NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
    `modified_on`       timestamp                                                     NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '修改时间',
    `deleted_on`        timestamp                                                              DEFAULT NULL COMMENT '删除时间',
    `is_del`            tinyint unsigned NOT NULL DEFAULT '0' COMMENT '是否删除 0 为未删除、1 为已删除',
    PRIMARY KEY (`id`) USING BTREE,
    KEY                 `idx_user` (`user_id`) USING BTREE,
    KEY                 `idx_visibility` (`visibility`) USING BTREE
) ENGINE=InnoDB AUTO_INCREMENT=1080017989 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci COMMENT='冒泡/文章';

-- ----------------------------
-- Records of p_post
-- ----------------------------
INSERT INTO `p_post` VALUES (122263936367067137, 1594320204085702657, 0, 0, 0, 0, '比特币大涨', 0, 0, 0, 0, 0, '区块链', 0, '', '', '2022-11-26 11:26:32', '2022-11-26 11:28:45', NULL, 0);
INSERT INTO `p_post` VALUES (122266981498880004, 1594320204085702657, 0, 0, 0, 0, '龙卷风', 0, 0, 0, 0, 0, '音乐', 0, '', '', '2022-11-26 11:38:20', '2022-11-26 11:38:20', NULL, 0);
INSERT INTO `p_post` VALUES (122267101757964293, 1594320204085702657, 0, 0, 0, 0, '晴天', 0, 0, 0, 0, 0, '音乐', 0, '', '', '2022-11-26 11:38:48', '2022-11-26 11:38:48', NULL, 0);
INSERT INTO `p_post` VALUES (122267230606983174, 1594320204085702657, 0, 0, 0, 0, '反方向的钟', 0, 0, 0, 0, 0, '音乐', 0, '', '', '2022-11-26 11:39:18', '2022-11-26 11:39:18', NULL, 0);
INSERT INTO `p_post` VALUES (122267423880511495, 1594320204085702657, 0, 0, 0, 0, '流沙', 0, 0, 0, 0, 0, '音乐', 0, '', '', '2022-11-26 11:40:03', '2022-11-26 11:40:03', NULL, 0);
INSERT INTO `p_post` VALUES (122267986521227272, 1594320204085702657, 0, 0, 0, 0, '泰拉瑞亚新手教程', 0, 0, 0, 0, 0, '游戏', 0, '', '', '2022-11-26 11:42:14', '2022-11-26 11:42:14', NULL, 0);
INSERT INTO `p_post` VALUES (122268531982073865, 1594320204085702657, 0, 0, 0, 0, '厉不厉害你坤哥', 0, 0, 0, 0, 0, '搞笑', 0, '', '', '2022-11-26 11:44:21', '2022-11-26 11:44:21', NULL, 0);
-- ----------------------------
-- Table structure for p_post_attachment_bill
-- ----------------------------
DROP TABLE IF EXISTS `p_post_attachment_bill`;
CREATE TABLE `p_post_attachment_bill`
(
    `id`          bigint unsigned NOT NULL AUTO_INCREMENT COMMENT '购买记录ID',
    `post_id`     bigint unsigned NOT NULL DEFAULT '0' COMMENT 'POST ID',
    `user_id`     bigint unsigned NOT NULL DEFAULT '0' COMMENT '用户ID',
    `paid_amount` bigint unsigned NOT NULL DEFAULT '0' COMMENT '支付金额',
    `created_on`  timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
    `modified_on` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '修改时间',
    `deleted_on`  timestamp          DEFAULT NULL COMMENT '删除时间',
    `is_del`      tinyint unsigned NOT NULL DEFAULT '0' COMMENT '是否删除 0 为未删除、1 为已删除',
    PRIMARY KEY (`id`) USING BTREE,
    KEY           `idx_post` (`post_id`) USING BTREE,
    KEY           `idx_user` (`user_id`) USING BTREE
) ENGINE=InnoDB AUTO_INCREMENT=5000002 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci COMMENT='冒泡/文章附件账单';

-- ----------------------------
-- Table structure for p_post_collection
-- ----------------------------
DROP TABLE IF EXISTS `p_post_collection`;
CREATE TABLE `p_post_collection`
(
    `id`          bigint unsigned NOT NULL AUTO_INCREMENT COMMENT '收藏ID',
    `post_id`     bigint unsigned NOT NULL DEFAULT '0' COMMENT 'POST ID',
    `user_id`     bigint unsigned NOT NULL DEFAULT '0' COMMENT '用户ID',
    `created_on`  timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
    `modified_on` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '修改时间',
    `deleted_on`  timestamp          DEFAULT NULL COMMENT '删除时间',
    `is_del`      tinyint unsigned NOT NULL DEFAULT '0' COMMENT '是否删除 0 为未删除、1 为已删除',
    PRIMARY KEY (`id`) USING BTREE,
    KEY           `idx_post` (`post_id`) USING BTREE,
    KEY           `idx_user` (`user_id`) USING BTREE
) ENGINE=InnoDB AUTO_INCREMENT=6000012 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci COMMENT='冒泡/文章收藏';

-- ----------------------------
-- Table structure for p_post_content
-- ----------------------------
DROP TABLE IF EXISTS `p_post_content`;
CREATE TABLE `p_post_content`
(
    `id`          bigint unsigned NOT NULL AUTO_INCREMENT COMMENT '内容ID',
    `post_id`     bigint unsigned NOT NULL DEFAULT '0' COMMENT 'POST ID',
    `user_id`     bigint unsigned NOT NULL DEFAULT '0' COMMENT '用户ID',
    `content`     varchar(2000) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL DEFAULT '' COMMENT '内容',
    `type`        tinyint unsigned NOT NULL DEFAULT '2' COMMENT '类型，1标题，2文字段落，3图片地址，4视频地址，5语音地址，6链接地址，7附件资源，8收费资源',
    `sort`        int unsigned NOT NULL DEFAULT '100' COMMENT '排序，越小越靠前',
    `created_on`  timestamp                                                      NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
    `modified_on` timestamp                                                      NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '修改时间',
    `deleted_on`  timestamp                                                               DEFAULT NULL COMMENT '删除时间',
    `is_del`      tinyint unsigned NOT NULL DEFAULT '0' COMMENT '是否删除 0 为未删除、1 为已删除',
    PRIMARY KEY (`id`) USING BTREE,
    KEY           `idx_post` (`post_id`) USING BTREE,
    KEY           `idx_user` (`user_id`) USING BTREE
) ENGINE=InnoDB AUTO_INCREMENT=180022546 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci COMMENT='冒泡/文章内容';

-- ----------------------------
-- Records of p_post_content
-- ----------------------------
INSERT INTO `p_post_content` VALUES (1596346598583967745, 122263936367067137, 1594320204085702657, '比特币昨日涨幅2.76，亏麻了家人们', 2, 100, '2022-11-26 11:34:22', '2022-11-26 11:34:22', NULL, 0);
INSERT INTO `p_post_content` VALUES (1596346598609133570, 122263936367067137, 1594320204085702657, 'https://search.bilibili.com?keyword=比特币', 6, 101, '2022-11-26 11:34:22', '2022-11-26 11:34:22', NULL, 0);
INSERT INTO `p_post_content` VALUES (1596347596207562753, 122266981498880004, 1594320204085702657, '爱像一阵风，吹完它就走', 2, 100, '2022-11-26 11:38:20', '2022-11-26 11:38:20', NULL, 0);
INSERT INTO `p_post_content` VALUES (1596347596207562754, 122266981498880004, 1594320204085702657, 'https://search.bilibili.com?keyword=龙卷风', 6, 101, '2022-11-26 11:38:20', '2022-11-26 11:38:20', NULL, 0);
INSERT INTO `p_post_content` VALUES (1596347715485179906, 122267101757964293, 1594320204085702657, '故事的小黄花，从出生的那天就飘着', 2, 100, '2022-11-26 11:38:48', '2022-11-26 11:38:48', NULL, 0);
INSERT INTO `p_post_content` VALUES (1596347715485179907, 122267101757964293, 1594320204085702657, 'https://search.bilibili.com?keyword=晴天', 6, 101, '2022-11-26 11:38:48', '2022-11-26 11:38:48', NULL, 0);
INSERT INTO `p_post_content` VALUES (1596347838327955458, 122267230606983174, 1594320204085702657, '穿梭时间的画面的钟，从反方向开始移动', 2, 100, '2022-11-26 11:39:18', '2022-11-26 11:39:18', NULL, 0);
INSERT INTO `p_post_content` VALUES (1596347838395064322, 122267230606983174, 1594320204085702657, 'https://search.bilibili.com?keyword=晴天', 6, 101, '2022-11-26 11:39:18', '2022-11-26 11:39:18', NULL, 0);
INSERT INTO `p_post_content` VALUES (1596348027885330433, 122267423880511495, 1594320204085702657, '爱情好像流沙~', 2, 100, '2022-11-26 11:40:03', '2022-11-26 11:40:03', NULL, 0);
INSERT INTO `p_post_content` VALUES (1596348027948244993, 122267423880511495, 1594320204085702657, 'https://search.bilibili.com?keyword=流沙', 6, 101, '2022-11-26 11:40:03', '2022-11-26 11:40:03', NULL, 0);
INSERT INTO `p_post_content` VALUES (1596348579536969729, 122267986521227272, 1594320204085702657, '查一下wiki就好了。我来总结一下。不一定对欢迎来反驳\n1.肉山前只会传播草。范围应该是3\n2肉后会开始传播。石块，冰块，沙子和几种沙子的变种。范围也是3\n3.血腥腐化会把淤泥变成泥土，但是绿叶矿在一定的范围内会组织腐化血腥传播\n4.背景墙不知道~', 2, 100, '2022-11-26 11:42:14', '2022-11-26 11:42:14', NULL, 0);
INSERT INTO `p_post_content` VALUES (1596348579536969730, 122267986521227272, 1594320204085702657, 'https://search.bilibili.com?keyword=泰拉瑞亚新手教程', 6, 101, '2022-11-26 11:42:14', '2022-11-26 11:42:14', NULL, 0);
INSERT INTO `p_post_content` VALUES (1596349112729477121, 122268531982073865, 1594320204085702657, '只因你太美 oh baby\n你干嘛 哎呦', 2, 100, '2022-11-26 11:44:21', '2022-11-26 11:44:21', NULL, 0);
INSERT INTO `p_post_content` VALUES (1596349112796585986, 122268531982073865, 1594320204085702657, 'https://search.bilibili.com?keyword=蔡徐坤', 6, 101, '2022-11-26 11:44:21', '2022-11-26 11:44:21', NULL, 0);


-- ----------------------------
-- Table structure for p_post_star
-- ----------------------------
DROP TABLE IF EXISTS `p_post_star`;
CREATE TABLE `p_post_star`
(
    `id`          bigint unsigned NOT NULL AUTO_INCREMENT COMMENT '收藏ID',
    `post_id`     bigint unsigned NOT NULL DEFAULT '0' COMMENT 'POST ID',
    `user_id`     bigint unsigned NOT NULL DEFAULT '0' COMMENT '用户ID',
    `created_on`  timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
    `modified_on` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '修改时间',
    `deleted_on`  timestamp          DEFAULT NULL COMMENT '删除时间',
    `is_del`      tinyint unsigned NOT NULL DEFAULT '0' COMMENT '是否删除 0 为未删除、1 为已删除',
    PRIMARY KEY (`id`) USING BTREE,
    KEY           `idx_post` (`post_id`) USING BTREE,
    KEY           `idx_user` (`user_id`) USING BTREE
) ENGINE=InnoDB AUTO_INCREMENT=6000028 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci COMMENT='冒泡/文章点赞';

-- ----------------------------
-- Table structure for p_tag
-- ----------------------------
DROP TABLE IF EXISTS `p_tag`;
CREATE TABLE `p_tag`
(
    `id`          bigint unsigned NOT NULL AUTO_INCREMENT COMMENT '标签ID',
    `user_id`     bigint unsigned NOT NULL DEFAULT '0' COMMENT '创建者ID',
    `tag`         varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT '标签名',
    `quote_num`   bigint unsigned NOT NULL DEFAULT '0' COMMENT '引用数',
    `created_on`  timestamp                                                     NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
    `modified_on` timestamp                                                     NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '修改时间',
    `deleted_on`  timestamp                                                              DEFAULT NULL COMMENT '删除时间',
    `is_del`      tinyint unsigned NOT NULL DEFAULT '0' COMMENT '是否删除 0 为未删除、1 为已删除',
    PRIMARY KEY (`id`) USING BTREE,
    UNIQUE KEY `idx_tag` (`tag`) USING BTREE,
    KEY           `idx_user` (`user_id`) USING BTREE,
    KEY           `idx_num` (`quote_num`) USING BTREE
) ENGINE=InnoDB AUTO_INCREMENT=9000065 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci COMMENT='标签';

-- ----------------------------
-- Records of p_tag
-- ----------------------------
INSERT INTO `p_tag` VALUES (1596334269410603010, 1594320204085702657, '区块链', 0, '2022-11-26 10:45:23', '2022-11-26 10:45:23', NULL, 0);
INSERT INTO `p_tag` VALUES (1596347151829442561, 1594320204085702657, '后端', 0, '2022-11-26 11:36:34', '2022-11-26 11:36:34', NULL, 0);
INSERT INTO `p_tag` VALUES (1596347175409819649, 1594320204085702657, '前端', 0, '2022-11-26 11:36:39', '2022-11-26 11:36:39', NULL, 0);
INSERT INTO `p_tag` VALUES (1596347194376462338, 1594320204085702657, '游戏', 0, '2022-11-26 11:36:44', '2022-11-26 11:36:44', NULL, 0);
INSERT INTO `p_tag` VALUES (1596347242422214657, 1594320204085702657, '搞笑', 0, '2022-11-26 11:36:55', '2022-11-26 11:36:55', NULL, 0);
INSERT INTO `p_tag` VALUES (1596347273183240193, 1594320204085702657, '音乐', 0, '2022-11-26 11:37:03', '2022-11-26 11:37:03', NULL, 0);

-- ----------------------------
-- Table structure for p_tag_post
-- ----------------------------
DROP TABLE IF EXISTS `p_tag_post`;
CREATE TABLE `p_tag_post`(
     `id`          bigint unsigned NOT NULL AUTO_INCREMENT COMMENT '主键',
     `post_id`     bigint unsigned NOT NULL DEFAULT '0' COMMENT '文章ID',
     `tag_id`     bigint unsigned NOT NULL DEFAULT '0' COMMENT '标签ID',
     `created_on`  timestamp                                                     NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
     `modified_on` timestamp                                                     NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '修改时间',
     `deleted_on`  timestamp                                                              DEFAULT NULL COMMENT '删除时间',
     `is_del`      tinyint unsigned NOT NULL DEFAULT '0' COMMENT '是否删除 0 为未删除、1 为已删除',
     PRIMARY KEY (`id`) USING BTREE
)ENGINE=InnoDB AUTO_INCREMENT=10000065 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci COMMENT='标签文章关联表';


-- ----------------------------
-- Records of p_tag_post
-- ----------------------------
INSERT INTO `p_tag_post` VALUES (1596344626015404034, 122263936367067137, 1596334269410603010, '2022-11-26 11:26:32', '2022-11-26 11:26:32', NULL, 0);
INSERT INTO `p_tag_post` VALUES (1596347596207562755, 122266981498880004, 1596347273183240193, '2022-11-26 11:38:20', '2022-11-26 11:38:20', NULL, 0);
INSERT INTO `p_tag_post` VALUES (1596347715552288769, 122267101757964293, 1596347273183240193, '2022-11-26 11:38:48', '2022-11-26 11:38:48', NULL, 0);
INSERT INTO `p_tag_post` VALUES (1596347838457978881, 122267230606983174, 1596347273183240193, '2022-11-26 11:39:18', '2022-11-26 11:39:18', NULL, 0);
INSERT INTO `p_tag_post` VALUES (1596348027948244994, 122267423880511495, 1596347273183240193, '2022-11-26 11:40:03', '2022-11-26 11:40:03', NULL, 0);
INSERT INTO `p_tag_post` VALUES (1596348579536969731, 122267986521227272, 1596347194376462338, '2022-11-26 11:42:14', '2022-11-26 11:42:14', NULL, 0);
INSERT INTO `p_tag_post` VALUES (1596349112796585987, 122268531982073865, 1596347242422214657, '2022-11-26 11:44:21', '2022-11-26 11:44:21', NULL, 0);
-- ----------------------------
-- Table structure for p_user
-- ----------------------------
DROP TABLE IF EXISTS `p_user`;
CREATE TABLE `p_user`
(
    `id`          bigint unsigned NOT NULL AUTO_INCREMENT COMMENT '用户ID',
    `nickname`    varchar(32) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci  NOT NULL DEFAULT '' COMMENT '昵称',
    `username`    varchar(32) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci  NOT NULL DEFAULT '' COMMENT '用户名',
    `phone`       varchar(16) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci  NOT NULL DEFAULT '' COMMENT '手机号',
    `email`       varchar(32) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci  NOT NULL DEFAULT '' COMMENT '邮箱',
    `password`    varchar(32) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci  NOT NULL DEFAULT '' COMMENT 'MD5密码',
    `salt`        varchar(16) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci  NOT NULL DEFAULT '' COMMENT '盐值',
    `status`      tinyint unsigned NOT NULL DEFAULT '1' COMMENT '状态，1正常，2停用',
    `avatar`      varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL DEFAULT '' COMMENT '用户头像',
    `balance`     bigint unsigned NOT NULL DEFAULT 0 COMMENT '用户余额（分）',
    `is_admin`    tinyint unsigned NOT NULL DEFAULT '0' COMMENT '是否管理员',
    `created_on`  timestamp                                                     NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
    `modified_on` timestamp                                                     NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '修改时间',
    `deleted_on`  timestamp                                                              DEFAULT NULL COMMENT '删除时间',
    `is_del`      tinyint unsigned NOT NULL DEFAULT '0' COMMENT '是否删除 0 为未删除、1 为已删除',
    PRIMARY KEY (`id`) USING BTREE,
    UNIQUE KEY `idx_username` (`username`) USING BTREE,
    UNIQUE KEY `idx_phone` (`phone`) USING BTREE
) ENGINE=InnoDB AUTO_INCREMENT=100058 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci COMMENT='用户';

-- ----------------------------
-- Records of p_user
-- ----------------------------
INSERT INTO `p_user` VALUES (1594320204085702657, 'user_d19zgsiw51', '120190931746947074', '13770511673', '', '', '', 1, '', 0, 0, '2022-11-20 21:22:12', '2022-11-20 21:22:12', NULL, 0);
INSERT INTO `p_user` VALUES (1594330052483444737, 'user_gytbcm5r9i', '120201016330158084', '13838411438', '', '', '', 1, '', 0, 0, '2022-11-20 22:01:20', '2022-11-20 22:01:20', NULL, 0);

-- ----------------------------
-- Table structure for p_wallet_recharge
-- ----------------------------
DROP TABLE IF EXISTS `p_wallet_recharge`;
CREATE TABLE `p_wallet_recharge`
(
    `id`           bigint unsigned NOT NULL AUTO_INCREMENT COMMENT '充值ID',
    `user_id`      bigint unsigned NOT NULL DEFAULT '0' COMMENT '用户ID',
    `amount`       bigint                                                       NOT NULL DEFAULT '0' COMMENT '充值金额',
    `trade_no`     varchar(64) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL DEFAULT '' COMMENT '支付宝订单号',
    `trade_status` varchar(32) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL DEFAULT '' COMMENT '交易状态',
    `created_on`   timestamp                                                    NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
    `modified_on`  timestamp                                                    NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '修改时间',
    `deleted_on`   timestamp                                                             DEFAULT NULL COMMENT '删除时间',
    `is_del`       tinyint unsigned NOT NULL DEFAULT '0' COMMENT '是否删除 0 为未删除、1 为已删除',
    PRIMARY KEY (`id`) USING BTREE,
    KEY            `idx_user` (`user_id`) USING BTREE,
    KEY            `idx_trade_no` (`trade_no`) USING BTREE,
    KEY            `idx_trade_status` (`trade_status`) USING BTREE
) ENGINE=InnoDB AUTO_INCREMENT=10023 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci COMMENT='钱包流水';

-- ----------------------------
-- Table structure for p_wallet_statement
-- ----------------------------
DROP TABLE IF EXISTS `p_wallet_statement`;
CREATE TABLE `p_wallet_statement`
(
    `id`               bigint unsigned NOT NULL AUTO_INCREMENT COMMENT '账单ID',
    `user_id`          bigint unsigned NOT NULL DEFAULT '0' COMMENT '用户ID',
    `change_amount`    bigint                                                        NOT NULL DEFAULT '0' COMMENT '变动金额',
    `balance_snapshot` bigint                                                        NOT NULL DEFAULT '0' COMMENT '资金快照',
    `reason`           varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT '变动原因',
    `post_id`          bigint unsigned NOT NULL DEFAULT '0' COMMENT '关联动态',
    `created_on`       timestamp                                                     NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
    `modified_on`      timestamp                                                     NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '修改时间',
    `deleted_on`       timestamp                                                              DEFAULT NULL COMMENT '删除时间',
    `is_del`           tinyint unsigned NOT NULL DEFAULT '0' COMMENT '是否删除 0 为未删除、1 为已删除',
    PRIMARY KEY (`id`) USING BTREE,
    KEY                `idx_user` (`user_id`) USING BTREE
) ENGINE=InnoDB AUTO_INCREMENT=10010 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci COMMENT='钱包流水';

-- ----------------------------
-- Table structure for p_sign
-- ----------------------------
DROP TABLE IF EXISTS `p_sign`;
CREATE TABLE `p_sign`
(
    `id`      bigint unsigned NOT NULL AUTO_INCREMENT COMMENT '主键',
    `user_id` bigint unsigned NOT NULL COMMENT '用户id',
    `date`    varchar(255) COLLATE utf8mb4_general_ci DEFAULT NULL COMMENT '最新签到日期',
    `count`   bigint unsigned DEFAULT NULL COMMENT '连续签到的天数',
    PRIMARY KEY (`id`) USING BTREE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci ROW_FORMAT=COMPACT;

SET
FOREIGN_KEY_CHECKS = 1;