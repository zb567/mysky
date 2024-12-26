package com.mysky.system.service.impl;

import java.util.List;
import com.mysky.common.utils.DateUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.mysky.system.mapper.TouristSpotMapper;
import com.mysky.system.domain.TouristSpot;
import com.mysky.system.service.ITouristSpotService;

/**
 * 旅游地点存储核心信息Service业务层处理
 * 
 * @author ll
 * @date 2024-12-25
 */
@Service
public class TouristSpotServiceImpl implements ITouristSpotService 
{
    @Autowired
    private TouristSpotMapper touristSpotMapper;

    /**
     * 查询旅游地点存储核心信息
     * 
     * @param id 旅游地点存储核心信息主键
     * @return 旅游地点存储核心信息
     */
    @Override
    public TouristSpot selectTouristSpotById(Long id)
    {
        return touristSpotMapper.selectTouristSpotById(id);
    }

    /**
     * 查询旅游地点存储核心信息列表
     * 
     * @param touristSpot 旅游地点存储核心信息
     * @return 旅游地点存储核心信息
     */
    @Override
    public List<TouristSpot> selectTouristSpotList(TouristSpot touristSpot)
    {
        return touristSpotMapper.selectTouristSpotList(touristSpot);
    }

    /**
     * 新增旅游地点存储核心信息
     * 
     * @param touristSpot 旅游地点存储核心信息
     * @return 结果
     */
    @Override
    public int insertTouristSpot(TouristSpot touristSpot)
    {
        touristSpot.setCreateTime(DateUtils.getNowDate());
        return touristSpotMapper.insertTouristSpot(touristSpot);
    }

    /**
     * 修改旅游地点存储核心信息
     * 
     * @param touristSpot 旅游地点存储核心信息
     * @return 结果
     */
    @Override
    public int updateTouristSpot(TouristSpot touristSpot)
    {
        touristSpot.setUpdateTime(DateUtils.getNowDate());
        return touristSpotMapper.updateTouristSpot(touristSpot);
    }

    /**
     * 批量删除旅游地点存储核心信息
     * 
     * @param ids 需要删除的旅游地点存储核心信息主键
     * @return 结果
     */
    @Override
    public int deleteTouristSpotByIds(Long[] ids)
    {
        return touristSpotMapper.deleteTouristSpotByIds(ids);
    }

    /**
     * 删除旅游地点存储核心信息信息
     * 
     * @param id 旅游地点存储核心信息主键
     * @return 结果
     */
    @Override
    public int deleteTouristSpotById(Long id)
    {
        return touristSpotMapper.deleteTouristSpotById(id);
    }
}
