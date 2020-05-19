DROP TABLE IF EXISTS user__2018;

CREATE TABLE user__2018
(
	id BIGINT(20) NOT NULL COMMENT '主键ID',
	name VARCHAR(30) NULL DEFAULT NULL COMMENT '姓名',
	age INT(11) NULL DEFAULT NULL COMMENT '年龄',
	gender INT(2) NULL DEFAULT NULL COMMENT '性别,0:MALE, 1:FEMALE',
	grade INT(3) NULL DEFAULT NULL COMMENT '年级',
	email VARCHAR(50) NULL DEFAULT NULL COMMENT '邮箱',
	state INT(1) NULL DEFAULT NULL COMMENT '用户状态',
	wallet VARCHAR(3000) NULL DEFAULT NULL COMMENT '钱包',
	other_info VARCHAR(3000) NULL DEFAULT NULL COMMENT '其他信息',
	version INT(8) NULL COMMENT '版本',
	deleted INT(11) NOT NULL DEFAULT 0,
	operator VARCHAR(25) NULL DEFAULT NULL COMMENT '操作者',
	PRIMARY KEY (id)
);

DROP TABLE IF EXISTS user__2019;
CREATE TABLE user__2019
(
	id BIGINT(20) NOT NULL COMMENT '主键ID',
	name VARCHAR(30) NULL DEFAULT NULL COMMENT '姓名',
	age INT(11) NULL DEFAULT NULL COMMENT '年龄',
	gender INT(2) NULL DEFAULT NULL COMMENT '性别,0:MALE, 1:FEMALE',
	grade INT(3) NULL DEFAULT NULL COMMENT '年级',
	email VARCHAR(50) NULL DEFAULT NULL COMMENT '邮箱',
	state INT(1) NULL DEFAULT NULL COMMENT '用户状态',
	wallet VARCHAR(3000) NULL DEFAULT NULL COMMENT '钱包',
	other_info VARCHAR(3000) NULL DEFAULT NULL COMMENT '其他信息',
	version INT(8) NULL COMMENT '版本',
	deleted INT(11) NOT NULL DEFAULT 0,
	operator VARCHAR(25) NULL DEFAULT NULL COMMENT '操作者',
	PRIMARY KEY (id)
);


drop table if exists man;
drop table if exists woman;
drop table if exists child;

create table man
(
    id        bigint(20) not null,
    name      varchar(30),
    lao_po_id bigint(20) not null
);

create table woman
(
    id          bigint(20) not null,
    name        varchar(30),
    lao_gong_id bigint(20) not null
);

create table child
(
    id         bigint(20) not null,
    name       varchar(30),
    lao_han_id bigint(20) not null,
    lao_ma_id bigint(20) not null
);