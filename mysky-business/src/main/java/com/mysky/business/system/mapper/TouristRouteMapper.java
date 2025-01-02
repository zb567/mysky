package com.mysky.business.system.mapper;

import java.util.List;

import com.mysky.business.system.domain.TouristRoute;

/**
 * 存储旅游者路线的基本信息Mapper接口
 * 
 * @author ll
 * @date 2024-12-30
 */
public interface TouristRouteMapper 
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
     * 删除存储旅游者路线的基本信息
     * 
     * @param id 存储旅游者路线的基本信息主键
     * @return 结果
     */
    public int deleteTouristRouteById(Long id);

    /**
     * 批量删除存储旅游者路线的基本信息
     * 
     * @param ids 需要删除的数据主键集合
     * @return 结果
     */
    public int deleteTouristRouteByIds(Long[] ids);
}
