package com.mysky.business.system.service;

import java.util.List;

import com.mysky.business.system.domain.TouristSpot;

/**
 * 旅游地点存储核心信息Service接口
 * 
 * @author ll
 * @date 2024-12-30
 */
public interface ITouristSpotService 
{
    /**
     * 查询旅游地点存储核心信息
     * 
     * @param id 旅游地点存储核心信息主键
     * @return 旅游地点存储核心信息
     */
    public TouristSpot selectTouristSpotById(Long id);

    /**
     * 查询旅游地点存储核心信息列表
     * 
     * @param touristSpot 旅游地点存储核心信息
     * @return 旅游地点存储核心信息集合
     */
    public List<TouristSpot> selectTouristSpotList(TouristSpot touristSpot);

    /**
     * 新增旅游地点存储核心信息
     * 
     * @param touristSpot 旅游地点存储核心信息
     * @return 结果
     */
    public int insertTouristSpot(TouristSpot touristSpot);

    /**
     * 修改旅游地点存储核心信息
     * 
     * @param touristSpot 旅游地点存储核心信息
     * @return 结果
     */
    public int updateTouristSpot(TouristSpot touristSpot);

    /**
     * 批量删除旅游地点存储核心信息
     * 
     * @param ids 需要删除的旅游地点存储核心信息主键集合
     * @return 结果
     */
    public int deleteTouristSpotByIds(Long[] ids);

    /**
     * 删除旅游地点存储核心信息信息
     * 
     * @param id 旅游地点存储核心信息主键
     * @return 结果
     */
    public int deleteTouristSpotById(Long id);
}
