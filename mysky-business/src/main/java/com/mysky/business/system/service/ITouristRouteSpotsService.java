package com.mysky.business.system.service;

import java.util.List;

import com.mysky.business.system.domain.TouristRouteSpots;

/**
 * 旅游路线中间景点Service接口
 * 
 * @author ll
 * @date 2024-12-30
 */
public interface ITouristRouteSpotsService 
{
    /**
     * 查询旅游路线中间景点
     * 
     * @param id 旅游路线中间景点主键
     * @return 旅游路线中间景点
     */
    public TouristRouteSpots selectTouristRouteSpotsById(Long id);

    /**
     * 查询旅游路线中间景点列表
     * 
     * @param touristRouteSpots 旅游路线中间景点
     * @return 旅游路线中间景点集合
     */
    public List<TouristRouteSpots> selectTouristRouteSpotsList(TouristRouteSpots touristRouteSpots);

    /**
     * 新增旅游路线中间景点
     * 
     * @param touristRouteSpots 旅游路线中间景点
     * @return 结果
     */
    public int insertTouristRouteSpots(TouristRouteSpots touristRouteSpots);

    /**
     * 修改旅游路线中间景点
     * 
     * @param touristRouteSpots 旅游路线中间景点
     * @return 结果
     */
    public int updateTouristRouteSpots(TouristRouteSpots touristRouteSpots);

    /**
     * 批量删除旅游路线中间景点
     * 
     * @param ids 需要删除的旅游路线中间景点主键集合
     * @return 结果
     */
    public int deleteTouristRouteSpotsByIds(Long[] ids);

    /**
     * 删除旅游路线中间景点信息
     * 
     * @param id 旅游路线中间景点主键
     * @return 结果
     */
    public int deleteTouristRouteSpotsById(Long id);
}
