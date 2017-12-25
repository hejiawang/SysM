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

CREATE TABLE `sysm_code_log` (
  `id` varchar(36) NOT NULL COMMENT 'ID主键',
  `date` date NOT NULL COMMENT '日期',
  `content` varchar(1024) NOT NULL COMMENT '日志内容',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
ALTER TABLE `sysm_code_log` COMMENT='系统开发进度日志';



