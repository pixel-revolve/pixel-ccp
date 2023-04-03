-- ----------------------------
-- Records of p_attachment
-- ----------------------------


-- ----------------------------
-- Records of p_comment
-- ----------------------------
INSERT INTO `p_comment` VALUES (2162685444100, 230606983174, 204085702657, '', '', '2023-01-17 22:39:22', '2023-01-17 22:39:22', NULL, 0);


-- ----------------------------
-- Records of p_comment_content
-- ----------------------------
INSERT INTO `p_comment_content` VALUES (2162685444099, 2162685444100, 204085702657, '《反方向的钟》，是周杰伦实验性的一首R&B作品。里边主要涉及到他对中文如何适配西方R&B及嘻哈音乐的探索与发现。', 2, 100, '2023-01-17 22:39:22', '2023-01-17 22:39:22', NULL, 0);
INSERT INTO `p_comment_content` VALUES (2162685509636, 2162685444100, 204085702657, 'https://www.youtube.com/watch?v=fuM1aVCGR8c', 6, 101, '2023-01-17 22:39:22', '2023-01-17 22:39:22', NULL, 0);


-- ----------------------------
-- Records of p_comment_reply
-- ----------------------------
INSERT INTO `p_comment_reply` VALUES (2162687213569, 2162685444100, 52483444737, 204085702657, '别的不说，那段rap学的脑壳疼', '', '', '2023-01-17 22:39:48', '2023-01-17 22:39:48', NULL, 0);


-- ----------------------------
-- Records of p_follow
-- ----------------------------
INSERT INTO `p_follow` VALUES (1629500679995678721, 52483444737, 204085702657, '2023-02-25 23:16:51', '2023-02-25 23:16:51', NULL, 0);
INSERT INTO `p_follow` VALUES (1629684097292980226, 204085702657, 2383742042114, '2023-02-26 11:25:41', '2023-02-26 11:25:41', NULL, 0);
INSERT INTO `p_follow` VALUES (1629685010778824706, 52483444737, 2383742042114, '2023-02-26 11:29:19', '2023-02-26 11:29:19', NULL, 0);


-- ----------------------------
-- Records of p_message
-- ----------------------------


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
-- Records of p_post_collection
-- ----------------------------


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
-- Records of p_post_star
-- ----------------------------

-- ----------------------------
-- Records of p_sign
-- ----------------------------


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
-- Records of p_wallet_recharge
-- ----------------------------

-- ----------------------------
-- Records of p_wallet_statement
-- ----------------------------


-- ----------------------------
-- Records of p_spu_category
-- ----------------------------
INSERT INTO `p_spu_category` VALUES (1, 0, '文章附件', 0, 0, NULL, NULL, '2023-04-01 20:55:42', '2023-04-01 20:55:42', NULL, 0);
INSERT INTO `p_spu_category` VALUES (2, 0, '社区周边', 0, 0, NULL, NULL, '2023-04-01 20:56:14', '2023-04-01 20:56:14', NULL, 0);
INSERT INTO `p_spu_category` VALUES (3, 0, '课程', 0, 0, NULL, NULL, '2023-04-01 20:56:28', '2023-04-01 20:56:28', NULL, 0);

-- ----------------------------
-- Records of p_spu
-- ----------------------------
INSERT INTO `p_spu` VALUES (21, 2, 999, 'Steve Campbell', 'VYv8MGBMM3', 1, 1, 157, 98, 627, 'Q65olhq85s', 'Jbyc5ObKGW', '2008-11-06 22:51:46', '2005-01-18 01:38:27', '2001-06-18 12:09:45', 134);
INSERT INTO `p_spu` VALUES (22, 1, 70, 'Pak Kwok Ming', 'fN2PYDeCIx', 1, 0, 235, 275, 791, 'zrLxiVf40b', 'ue45cOwu2t', '2013-11-10 11:52:19', '2018-08-29 00:39:11', '2019-08-05 17:21:31', 243);
INSERT INTO `p_spu` VALUES (23, 1, 760, 'Zhu Shihan', 'sakcuEH0Tw', 1, 1, 180, 956, 689, '94fo2kv0jT', 'mpEQ1jBjRT', '2021-12-02 12:41:52', '2011-08-08 10:07:06', '2002-03-11 19:50:37', 195);
INSERT INTO `p_spu` VALUES (24, 1, 113, 'Watanabe Hazuki', 'WZCgpawyES', 0, 0, 965, 481, 469, 'evMjNvxvBm', 'VBqfpCiz5I', '2005-08-04 06:44:18', '2006-04-17 06:51:26', '2010-05-13 14:55:22', 191);
INSERT INTO `p_spu` VALUES (25, 0, 432, 'Hayashi Rena', 'q1V5q5PcsD', 1, 0, 262, 493, 6, 'grTD5iPYJP', 'vG7GgfgJXd', '2003-02-08 00:41:57', '2004-05-04 08:22:53', '2009-09-19 01:57:21', 35);
INSERT INTO `p_spu` VALUES (26, 1, 669, 'Zheng Jialun', 'DwcIGCVPSI', 0, 1, 29, 431, 928, 'FSUhwsS3dD', 'WJzpo1E89Z', '2016-11-13 23:20:40', '2014-08-27 15:02:57', '2007-08-02 06:31:02', 153);
INSERT INTO `p_spu` VALUES (27, 1, 288, 'Siu Ling Ling', 'U5TsJjtsuy', 1, 1, 179, 668, 941, 'wF3GQUL7Lo', 'CaSiPkl7es', '2003-03-29 01:00:20', '2004-12-26 16:45:46', '2001-12-17 08:16:54', 90);
INSERT INTO `p_spu` VALUES (28, 0, 594, 'Ishikawa Hazuki', 'ubknjSsY2K', 0, 1, 670, 336, 976, 'QellpjAKQO', 'lDa29XB1yu', '2022-01-16 11:15:24', '2011-10-02 16:52:54', '2009-06-11 04:44:56', 11);
INSERT INTO `p_spu` VALUES (29, 0, 909, 'Matsumoto Ryota', 'uX4S53MNGx', 1, 0, 996, 513, 844, 'K3NqvLZnJl', 'VbUOeH8wtB', '2022-10-27 13:03:13', '2003-03-08 11:57:07', '2018-01-07 10:32:03', 123);
INSERT INTO `p_spu` VALUES (30, 1, 925, 'Xu Jiehong', 'IWts5Sn5XW', 1, 0, 487, 879, 1, 'QumSfT67TG', 'h0PsTWS2uy', '2016-11-28 06:30:47', '2013-03-11 06:51:00', '2005-08-15 02:21:44', 249);
INSERT INTO `p_spu` VALUES (31, 1, 673, 'Barbara Hernandez', 'N345t07OGf', 1, 1, 834, 185, 419, 'OK3pItBBWj', 'WkVlkLZ4Xv', '2007-12-27 22:47:32', '2006-10-29 05:48:43', '2010-10-18 15:46:40', 190);
INSERT INTO `p_spu` VALUES (32, 1, 886, 'Zhao Yuning', 'ASplBYOVdV', 0, 0, 830, 993, 992, '0cFeucwR0I', 'qeKIz2Fea1', '2008-08-13 05:03:06', '2021-12-15 23:00:56', '2012-09-13 07:21:42', 14);
INSERT INTO `p_spu` VALUES (33, 0, 134, 'Yuen Fat', 'jLCV6YITon', 0, 1, 387, 274, 258, '5xIbX38NqP', 'mo4Dxr6hSD', '2016-05-19 20:36:38', '2016-10-22 23:24:23', '2009-09-30 20:42:20', 134);
INSERT INTO `p_spu` VALUES (34, 0, 92, 'Diane Mendoza', 'xfSo3frqHI', 0, 1, 24, 719, 800, 'Ntkw1Svqod', 'KRTq0AC0pv', '2012-12-22 13:18:48', '2008-10-08 00:59:07', '2001-05-06 09:43:06', 172);
INSERT INTO `p_spu` VALUES (35, 2, 728, 'Janet Wells', 'JwmOH0MzkS', 1, 0, 199, 171, 974, 'e3PIa3nxq1', 'QHoEGPigVt', '2016-02-14 21:46:09', '2014-11-06 09:46:37', '2002-01-15 01:06:44', 14);
INSERT INTO `p_spu` VALUES (36, 0, 448, 'Norman Young', 'qJM8F0mbzd', 0, 0, 304, 176, 443, 'YnhNXpbPe3', '1s2CMCYwvA', '2018-10-03 11:32:20', '2020-06-28 00:30:08', '2000-07-07 18:18:18', 138);
INSERT INTO `p_spu` VALUES (37, 1, 171, 'Hasegawa Rin', 'ZJcQczE5jl', 1, 1, 814, 957, 918, 'iuO4cwFNoZ', 'ge02pgO24c', '2017-07-15 00:06:39', '2001-04-16 07:41:38', '2010-11-12 03:57:09', 84);
INSERT INTO `p_spu` VALUES (38, 2, 561, 'Yuan Lan', 'tRogs6mL5U', 0, 1, 452, 135, 447, '9TGl9UNRm4', 'YurubSl3ls', '2014-06-06 13:52:17', '2000-03-02 08:26:37', '2000-05-07 14:36:53', 182);
INSERT INTO `p_spu` VALUES (39, 1, 919, 'Yan Xiaoming', 'nLHihMqOyt', 1, 0, 430, 358, 965, 'n8V14a3VuW', 'g1pQcthxcn', '2017-02-23 13:52:30', '2001-09-22 21:18:53', '2015-10-07 14:34:55', 39);
INSERT INTO `p_spu` VALUES (40, 0, 238, 'Miyamoto Miu', 'ZNQyWwjCPw', 1, 1, 113, 154, 640, '3NU4jfc5kq', 'mzorUGvAIX', '2010-07-05 12:20:47', '2008-08-11 03:35:28', '2010-02-08 03:16:27', 200);
