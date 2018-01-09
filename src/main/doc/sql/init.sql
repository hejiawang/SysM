CREATE TABLE `test` (
  `id` varchar(36) NOT NULL,
  `name` varchar(255) DEFAULT NULL,
  `age` int(3) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

CREATE TABLE `test2` (
  `id` varchar(36) NOT NULL,
  `name` varchar(255) DEFAULT NULL,
  `age` int(3) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

CREATE TABLE `customer` (
  `id` varchar(36) NOT NULL,
  `name` varchar(255) DEFAULT NULL,
  `contact` varchar(255) DEFAULT NULL,
  `telephone` varchar(255) DEFAULT NULL,
  `email` varchar(255) DEFAULT NULL,
  `remark` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

DROP TABLE IF EXISTS `sysm_code_log`;
CREATE TABLE `sysm_code_log` (
  `id` varchar(36) NOT NULL COMMENT 'ID主键',
  `date` date NOT NULL COMMENT '日期',
  `content` varchar(1024) NOT NULL COMMENT '日志内容',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
ALTER TABLE `sysm_code_log` COMMENT='系统开发进度日志';

DROP TABLE IF EXISTS `sysm_user_info`;
CREATE TABLE `sysm_user_info` (
  `id` varchar(36) NOT NULL COMMENT 'ID主键',
  `userName` varchar(255) NOT NULL COMMENT '登录名',
	`passWord` varchar(255) NOT NULL COMMENT '密码',
	`salt` varchar(255) NOT NULL COMMENT '密码盐',
	`realName` varchar(255) NOT NULL COMMENT '真是姓名',
	`email` varchar(100) NOT NULL COMMENT '邮箱',
	`telephone` varchar(20) NOT NULL COMMENT '联系方式',
	`birtoday` date NOT NULL COMMENT '出生日期',
	`educational` int(1) NOT NULL COMMENT '教育背景，0无1小学2初中3高中4大专5本科6研究生7博士生',
  `remark` varchar(500) DEFAULT NULL COMMENT '备注',
	`createTime` datetime NOT NULL COMMENT '创建日期',
	`updateTime` datetime NOT NULL COMMENT '修改日期',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='用户信息';





