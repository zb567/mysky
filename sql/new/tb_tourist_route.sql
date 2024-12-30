# tb_tourist_route (存储旅游者的路线基本信息)

# 存储每个旅游者路线的基本信息，例如起点城市、终点城市、路线名称等。
# 每条记录代表一条路线。


-- 删除表（如果已存在）
DROP TABLE IF EXISTS `tb_tourist_route`;

-- 创建表
CREATE TABLE `tb_tourist_route` (
                                    `id` BIGINT NOT NULL AUTO_INCREMENT COMMENT '路线唯一标识',
                                    `name` VARCHAR(255) NOT NULL COMMENT '路线名称',
                                    `start_city` VARCHAR(255) NOT NULL COMMENT '起点城市名称',
                                    `end_city` VARCHAR(255) NOT NULL COMMENT '终点城市名称',
                                    `start_point_id` BIGINT NOT NULL COMMENT '起点景点ID',
                                    `start_point_name` VARCHAR(255) NOT NULL COMMENT '起点景点名称',
                                    `end_point_id` BIGINT NOT NULL COMMENT '终点景点ID',
                                    `end_point_name` VARCHAR(255) NOT NULL COMMENT '终点景点名称',
                                    `total_distance` DECIMAL(10, 2) NOT NULL COMMENT '路线总距离（公里）',
                                    `estimated_time` TIME NOT NULL COMMENT '预计旅行时间',
                                    `create_time` TIMESTAMP NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
                                    `update_time` TIMESTAMP NULL DEFAULT NULL ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
                                    PRIMARY KEY (`id`),
                                    FOREIGN KEY (`start_point_id`) REFERENCES `tb_tourist_spot`(`id`) ON DELETE CASCADE ON UPDATE CASCADE,
                                    FOREIGN KEY (`end_point_id`) REFERENCES `tb_tourist_spot`(`id`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB AUTO_INCREMENT=1 DEFAULT CHARSET=utf8mb4 COMMENT='存储旅游者路线的基本信息';

-- 插入数据
INSERT INTO `tb_tourist_route`
(`name`, `start_city`, `end_city`, `start_point_id`, `start_point_name`, `end_point_id`, `end_point_name`, `total_distance`, `estimated_time`)
VALUES
    ('北京到纽约经典游', '北京', '纽约', 1, '长城', 3, '自由女神像', 11500.50, '12:30:00'),
    ('巴黎到纽约文化之旅', '巴黎', '纽约', 2, '埃菲尔铁塔', 3, '自由女神像', 5836.30, '10:00:00'),
    ('纽约到北京经典游', '纽约', '北京', 3, '自由女神像', 1, '长城', 11500.50, '15:20:00');


ALTER TABLE tb_tourist_route
MODIFY COLUMN estimated_time TIME NULL;

