
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

DROP DATABASE IF EXISTS product_master;
CREATE DATABASE product_master;
CREATE TABLE product_master.product(
  id INT PRIMARY KEY AUTO_INCREMENT,
  name VARCHAR(50) NOT NULL,
  price DOUBLE(10,2) NOT NULL DEFAULT 0);
INSERT INTO product_master.product (name, price) VALUES('master', '1');


DROP DATABASE IF EXISTS product_slave_alpha;
CREATE DATABASE product_slave_alpha;
CREATE TABLE product_slave_alpha.product(
  id INT PRIMARY KEY AUTO_INCREMENT,
  name VARCHAR(50) NOT NULL,
  price DOUBLE(10,2) NOT NULL DEFAULT 0);
INSERT INTO product_slave_alpha.product (name, price) VALUES('slaveAlpha', '1');

DROP DATABASE IF EXISTS product_slave_beta;
CREATE DATABASE product_slave_beta;
CREATE TABLE product_slave_beta.product(
  id INT PRIMARY KEY AUTO_INCREMENT,
  name VARCHAR(50) NOT NULL,
  price DOUBLE(10,2) NOT NULL DEFAULT 0);
INSERT INTO product_slave_beta.product (name, price) VALUES('slaveBeta', '1');

DROP DATABASE IF EXISTS product_slave_gamma;
CREATE DATABASE product_slave_gamma;
CREATE TABLE product_slave_gamma.product(
  id INT PRIMARY KEY AUTO_INCREMENT,
  name VARCHAR(50) NOT NULL,
  price DOUBLE(10,2) NOT NULL DEFAULT 0);
INSERT INTO product_slave_gamma.product (name, price) VALUES('slaveGamma', '1');