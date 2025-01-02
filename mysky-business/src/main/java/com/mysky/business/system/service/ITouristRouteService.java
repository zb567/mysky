package com.mysky.business.system.service;

import java.util.List;

import com.mysky.business.system.domain.TouristRoute;

/**
 * 存储旅游者路线的基本信息Service接口
 * 
 * @author ll
 * @date 2024-12-30
 */
public interface ITouristRouteService 
{
    /**
     * 查询存储旅游者路线的基本信息
     * 
     * @param id 存储旅游者路线的基本信息主键
     * @return 存储旅游者路线的基本信息
     */
    public TouristRoute selectTouristRouteById(Long id);

    /**
     * 查询存储旅游者路线的基本信息列表
     * 
     * @param touristRoute 存储旅游者路线的基本信息
     * @return 存储旅游者路线的基本信息集合
     */
    public List<TouristRoute> selectTouristRouteList(TouristRoute touristRoute);

    /**
     * 新增存储旅游者路线的基本信息
     * 
     * @param touristRoute 存储旅游者路线的基本信息
     * @return 结果
     */
    public int insertTouristRoute(TouristRoute touristRoute);

    /**
     * 修改存储旅游者路线的基本信息
     * 
     * @param touristRoute 存储旅游者路线的基本信息
     * @return 结果
     */
    public int updateTouristRoute(TouristRoute touristRoute);

    /**
     * 批量删除存储旅游者路线的基本信息
     * 
     * @param ids 需要删除的存储旅游者路线的基本信息主键集合
     * @return 结果
     */
    public int deleteTouristRouteByIds(Long[] ids);

    /**
     * 删除存储旅游者路线的基本信息信息
     * 
     * @param id 存储旅游者路线的基本信息主键
     * @return 结果
     */
    public int deleteTouristRouteById(Long id);
}
