-- MySQL dump 10.13  Distrib 8.0.31, for Win64 (x86_64)
--
-- Host: 127.0.0.1    Database: ry-vue
-- ------------------------------------------------------
-- Server version    8.0.31

/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!50503 SET NAMES utf8mb4 */;

--
-- Table structure for table `tb_tourist_spot`
--

DROP TABLE IF EXISTS `tb_tourist_spot`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `tb_tourist_spot` (
                                   `id` bigint NOT NULL AUTO_INCREMENT COMMENT '旅游地点唯一标识',
                                   `name` varchar(255) NOT NULL COMMENT '旅游地点名称',
                                   `cover_image_url` varchar(512) DEFAULT NULL COMMENT '封面图片 URL',
                                   `business_metrics` json DEFAULT NULL COMMENT '业务数据（JSON格式）',
                                   `top_industries` json DEFAULT NULL COMMENT '主要行业（JSON格式）',
                                   `description` text DEFAULT NULL COMMENT '旅游地点描述',
                                   `category_id` bigint NOT NULL COMMENT '分类ID',
                                   `latitude` decimal(10, 8) DEFAULT NULL COMMENT '纬度',
                                   `longitude` decimal(11, 8) DEFAULT NULL COMMENT '经度',
                                   `city` varchar(255) DEFAULT NULL COMMENT '所属城市',
                                   `province` varchar(255) DEFAULT NULL COMMENT '所属省份',
                                   `country` varchar(255) DEFAULT NULL COMMENT '所属国家',
                                   `rating` decimal(3, 2) DEFAULT NULL COMMENT '用户评分',
                                   `popularity_level` TINYINT DEFAULT NULL COMMENT '小众度等级，1为最小众，5为最主流，可为空',
                                   `create_time` timestamp NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
                                   `update_time` timestamp NULL DEFAULT NULL COMMENT '更新时间',
                                   PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=1 DEFAULT CHARSET=utf8mb4 COMMENT='旅游地点表存储核心信息';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `tb_tourist_spot`
--

LOCK TABLES `tb_tourist_spot` WRITE;
/*!40000 ALTER TABLE `tb_tourist_spot` DISABLE KEYS */;

INSERT INTO `tb_tourist_spot` (`name`, `cover_image_url`, `business_metrics`, `top_industries`, `description`, `category_id`, `latitude`, `longitude`, `city`, `province`, `country`, `rating`, `create_time`, `update_time`)
VALUES
    ('长城', 'https://example.com/images/great_wall_cover.jpg',
     '{"visitors": 1200000, "revenue": 550000}',
     '["旅游", "文物保护"]',
     '中国的古代军事防御建筑，被联合国列为世界文化遗产。',
     1, 40.4319, 116.5704, '北京', '北京市', '中国', 4.9, '2024-05-01 10:00:00', '2024-05-01 10:00:00'),
    ('埃菲尔铁塔', 'https://example.com/images/eiffel_tower_cover.jpg',
     '{"visitors": 2500000, "revenue": 1100000}',
     '["观光", "文化交流"]',
     '法国巴黎的地标建筑，浪漫文化的象征，世界著名旅游胜地。',
     2, 48.8584, 2.2945, '巴黎', '法兰西岛', '法国', 4.8, '2024-05-01 10:01:00', '2024-05-01 10:01:00'),
    ('自由女神像', 'https://example.com/images/statue_of_liberty_cover.jpg',
     '{"visitors": 1800000, "revenue": 800000}',
     '["文化象征", "旅游"]',
     '位于美国纽约，是美国的象征，也是世界文化遗产。',
     3, 40.6892, -74.0445, '纽约', '纽约州', '美国', 4.7, '2024-05-01 10:02:00', '2024-05-01 10:02:00'),
    ('卢浮宫', 'https://example.com/images/louvre_cover.jpg',
     '{"visitors": 3000000, "revenue": 1500000}',
     '["艺术", "文化交流"]',
     '法国巴黎的博物馆，世界上最著名的艺术馆之一。',
     4, 48.8606, 2.3376, '巴黎', '法兰西岛', '法国', 4.9, '2024-05-01 10:03:00', '2024-05-01 10:03:00'),
    ('东方明珠塔', 'https://example.com/images/oriental_pearl_tower_cover.jpg',
     '{"visitors": 2200000, "revenue": 1200000}',
     '["观光", "现代建筑"]',
     '位于中国上海的地标建筑，极具现代感的电视塔。',
     5, 31.2397, 121.4998, '上海', '上海市', '中国', 4.8, '2024-05-01 10:04:00', '2024-05-01 10:04:00');

/*!40000 ALTER TABLE `tb_tourist_spot` ENABLE KEYS */;
UNLOCK TABLES;


