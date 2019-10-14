-- swx-ad 数据库
drop database swx_ad_data;
create database swx_ad_data character set utf8;


use swx_ad_data;
-- 用户表
create table `ad_user`
(
  `id`          bigint(20)   not null auto_increment comment '自增主键',
  `username`    varchar(128) not null default '' comment '用户名',
  `token`       varchar(256) not null default '' comment '给用户生成的token',
  `user_status` tinyint(4)   not null default 0 comment '用户状态',
  `create_time` datetime     not null default '1970-01-01 00:00:00' comment '创建时间',
  `update_time` datetime     not null default '1970-01-01 00:00:00' comment '更新时间',
  primary key (`id`),
  unique key (`username`)
) engine = InnoDB
  auto_increment = 10
  default charset = utf8 comment '用户信息表';


-- 推广计划表
create table `ad_plan`
(
  `id`          bigint(20)  not null auto_increment comment '自增主键',
  `user_id`     bigint(20)  not null default 0 comment '当前记录所属用户',
  `plan_name`   varchar(49) not null comment '推广计划名称',
  `plan_status` tinyint(4)  not null default 0 comment '推广计划状态',
  `start_date`  datetime    not null comment '推广计划开始时间',
  `end_date`    datetime    not null comment '推广计划结束时间',
  `create_time` datetime    not null default '1970-01-01 00:00:00' comment '创建时间',
  `update_time` datetime    not null default '1970-01-01 00:00:00' comment '更新时间',
  primary key (`id`)
) engine = InnoDB
  auto_increment = 10
  default charset = utf8 comment '推广计划表';


-- 推广单元表
create table `ad_unit`
(
  `id`            bigint(20)  not null auto_increment comment '自增主键',
  `plan_id`       tinyint(20) not null default 0 comment '关联推广计划的id',
  `unit_name`     varchar(48) not null comment '推广单元名称',
  `unit_status`   tinyint(4)  not null default 0 comment '推广单元状态',
  `position_type` tinyint(4)  not null default 0 comment '广告位类型（开屏，贴片，中贴，暂停贴，后贴）',
  `budget`        bigint(20)  not null comment '预算',
  `create_time`   datetime    not null default '1970-01-01 00:00:00' comment '创建时间',
  `update_time`   datetime    not null default '1970-01-01 00:00:00' comment '更新时间',
  primary key (`id`)
) engine = InnoDB
  auto_increment = 10
  default charset = utf8 comment '推广单元表';


-- 创意表
CREATE TABLE `ad_creative`
(
  `id`            bigint(20)   NOT NULL AUTO_INCREMENT COMMENT '自增主键',
  `name`          varchar(48)  NOT NULL COMMENT '创意名称',
  `type`          tinyint(4)   NOT NULL DEFAULT '0' COMMENT '物料类型（图片，视频）',
  `material_type` tinyint(4)   NOT NULL DEFAULT 0 COMMENT '物料子类型（图片：bmp, jpg 等等）',
  `height`        int(10)      NOT NULL DEFAULT 0 COMMENT '高度',
  `width`         int(10)      NOT NULL DEFAULT 0 COMMENT '宽度',
  `size`          bigint(20)   NOT NULL DEFAULT 0 COMMENT '物料大小，单位是 KB',
  `duration`      int(10)      NOT NULL DEFAULT 0 COMMENT '持续时长，只有视频才不为',
  `audit_status`  tinyint(4)   NOT NULL DEFAULT 0 COMMENT '审核状态',
  `user_id`       bigint(20)   NOT NULL DEFAULT 0 COMMENT '标记当前记录所属用户',
  `url`           varchar(256) NOT NULL COMMENT '物料地址',
  `create_time`   datetime     not null default '1970-01-01 00:00:00' comment '创建时间',
  `update_time`   datetime     not null default '1970-01-01 00:00:00' comment '更新时间',
  PRIMARY KEY (`id`)
) engine = InnoDB
  auto_increment = 10
  default charset = utf8 comment '创意表';

-- 创意与推广单元关联表
create table `creative_unit`
(
  `id`          bigint(20) NOT NULL AUTO_INCREMENT COMMENT '自增主键',
  `creative_id` bigint(20) not null default 0 comment '创意id',
  `unit_id`     bigint(20) not null default 0 comment '推广单元id',
  primary key (id)
) engine = InnoDB
  auto_increment = 10
  default charset = utf8 comment '创意与推广单元关联表';


--  推广单元关键词 Feature
create table `ad_unit_keyword`
(
  `id`      int(11)     NOT NULL AUTO_INCREMENT COMMENT '自增主键',
  `unit_id` int(11)     not null comment '推广单元id',
  `keyword` varchar(30) not null comment '关键词',
  primary key (`id`)
) engine = InnoDB
  auto_increment = 10
  default charset = utf8 comment '推广单元关键词';


--  推广单元兴趣 Feature
create table `ad_unit_it`
(
  `id`      int(11)     NOT NULL AUTO_INCREMENT COMMENT '自增主键',
  `unit_id` int(11)     not null comment '推广单元id',
  `it_tag`  varchar(30) not null comment '兴趣标签',
  primary key (`id`)
) engine = InnoDB
  auto_increment = 10
  default charset = utf8 comment ' 推广单元兴趣';


--  推广单元地域 Feature
create table `ad_unit_district`
(
  `id`       int(11)     NOT NULL AUTO_INCREMENT COMMENT '自增主键',
  `unit_id`  int(11)     not null comment '推广单元id',
  `province` varchar(30) not null comment '省',
  `city`     varchar(30) not null comment '市',
  primary key (`id`)
) engine = InnoDB
  auto_increment = 10
  default charset = utf8 comment '推广单元地域';



