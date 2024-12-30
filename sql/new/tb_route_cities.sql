CREATE TABLE `tb_route_cities` (
    `id` BIGINT NOT NULL AUTO_INCREMENT COMMENT '记录唯一标识',
    `route_id` BIGINT NOT NULL COMMENT '关联路线ID',
    `city_id` BIGINT NOT NULL COMMENT '中间城市ID，关联城市表',
    `sequence` INT NOT NULL COMMENT '城市顺序（从起点开始）',
    PRIMARY KEY (`id`),
    FOREIGN KEY (`route_id`) REFERENCES `tb_tourist_route`(`id`) ON DELETE CASCADE,
    FOREIGN KEY (`city_id`) REFERENCES `tb_city`(`id`) ON DELETE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='路线中间城市表，存储城市ID列表';
