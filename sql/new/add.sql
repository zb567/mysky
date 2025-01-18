ALTER TABLE `sys_user`
ADD COLUMN `birthday` DATE DEFAULT NULL COMMENT '用户生日';


ALTER TABLE `tb_tourist_route`
ADD COLUMN `route_image` VARCHAR(255) DEFAULT NULL COMMENT '路线图片URL';
