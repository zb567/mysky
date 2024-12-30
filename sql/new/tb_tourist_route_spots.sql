# tb_tourist_route_spots(存储路线中的中间景点)

# 存储路线中的中间景点，支持多对多的关系。
# 每条记录表示一条路线中的一个中间景点，按顺序排列。

-- 删除表（如果已存在）
DROP TABLE IF EXISTS `tb_tourist_route_spots`;

-- 创建表
CREATE TABLE `tb_tourist_route_spots` (
                                          `id` BIGINT NOT NULL AUTO_INCREMENT COMMENT '记录唯一标识',
                                          `route_id` BIGINT NOT NULL COMMENT '关联路线ID',
                                          `spot_id` BIGINT NOT NULL COMMENT '景点ID',
                                          `spot_name` VARCHAR(255) NOT NULL COMMENT '中间景点名称',
                                          `sequence` INT NOT NULL COMMENT '景点顺序（从起点开始）',
                                          `create_time` TIMESTAMP NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
                                          `update_time` TIMESTAMP NULL DEFAULT NULL ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
                                          PRIMARY KEY (`id`),
                                          FOREIGN KEY (`route_id`) REFERENCES `tb_tourist_route`(`id`) ON DELETE CASCADE ON UPDATE CASCADE,
                                          FOREIGN KEY (`spot_id`) REFERENCES `tb_tourist_spot`(`id`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB AUTO_INCREMENT=1 DEFAULT CHARSET=utf8mb4 COMMENT='旅游路线中间景点表';

-- 插入数据
INSERT INTO `tb_tourist_route_spots`
(`route_id`, `spot_id`, `spot_name`, `sequence`)
VALUES
    (1, 4, '天安门', 1),
    (1, 5, '中央公园', 2),
    (2, 6, '卢浮宫', 1),
    (2, 7, '第五大道', 2),
    (3, 5, '中央公园', 1),
    (3, 4, '天安门', 2);
