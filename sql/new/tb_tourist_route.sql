CREATE TABLE `tb_tourist_route` (
                                    `id` BIGINT NOT NULL AUTO_INCREMENT COMMENT '路线唯一标识',
                                    `name` VARCHAR(255) NOT NULL COMMENT '路线名称',
                                    `start_city` VARCHAR(255) NOT NULL COMMENT '起点城市',
                                    `start_province` VARCHAR(255) DEFAULT NULL COMMENT '起点省份',
                                    `start_country` VARCHAR(255) NOT NULL COMMENT '起点国家',
                                    `end_city` VARCHAR(255) NOT NULL COMMENT '终点城市',
                                    `end_province` VARCHAR(255) DEFAULT NULL COMMENT '终点省份',
                                    `end_country` VARCHAR(255) NOT NULL COMMENT '终点国家',
                                    `distance_km` DECIMAL(10, 2) DEFAULT NULL COMMENT '路线总距离（公里）',
                                    `estimated_time_hours` DECIMAL(5, 2) DEFAULT NULL COMMENT '预计旅行时间（小时）',
                                    `create_time` TIMESTAMP DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
                                    `update_time` TIMESTAMP NULL DEFAULT NULL COMMENT '更新时间',
                                    PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='旅游者路线表存储核心信息';
