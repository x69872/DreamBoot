
CREATE TABLE `user` (
  `object_id` varchar(32) COLLATE cp1250_bin NOT NULL COMMENT '主键ID',
  `UAERNAME` varchar(20) COLLATE cp1250_bin NOT NULL COMMENT '用户名',
  `PASSWORD` varchar(16) COLLATE cp1250_bin NOT NULL COMMENT '密码',
  `EMAIL` varchar(30) COLLATE cp1250_bin DEFAULT NULL COMMENT '邮箱',
  `TELEPHONE` decimal(11,0) DEFAULT NULL COMMENT '手机号码',
  `SEX` char(1) COLLATE cp1250_bin NOT NULL COMMENT '性别',
  `creation time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `created_by` varchar(25) COLLATE cp1250_bin NOT NULL COMMENT '创建人',
  `last_updated_time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '最近更新时间',
  `last_updated_by` varchar(25) COLLATE cp1250_bin NOT NULL COMMENT '最近更新人',
  PRIMARY KEY (`object_id`)
);