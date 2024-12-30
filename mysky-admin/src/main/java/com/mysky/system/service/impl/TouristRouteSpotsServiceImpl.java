package com.mysky.system.service.impl;

import java.util.List;
import com.mysky.common.utils.DateUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.mysky.system.mapper.TouristRouteSpotsMapper;
import com.mysky.system.domain.TouristRouteSpots;
import com.mysky.system.service.ITouristRouteSpotsService;

/**
 * 旅游路线中间景点Service业务层处理
 * 
 * @author ll
 * @date 2024-12-30
 */
@Service
public class TouristRouteSpotsServiceImpl implements ITouristRouteSpotsService 
{
    @Autowired
    private TouristRouteSpotsMapper touristRouteSpotsMapper;

    /**
     * 查询旅游路线中间景点
     * 
     * @param id 旅游路线中间景点主键
     * @return 旅游路线中间景点
     */
    @Override
    public TouristRouteSpots selectTouristRouteSpotsById(Long id)
    {
        return touristRouteSpotsMapper.selectTouristRouteSpotsById(id);
    }

    /**
     * 查询旅游路线中间景点列表
     * 
     * @param touristRouteSpots 旅游路线中间景点
     * @return 旅游路线中间景点
     */
    @Override
    public List<TouristRouteSpots> selectTouristRouteSpotsList(TouristRouteSpots touristRouteSpots)
    {
        return touristRouteSpotsMapper.selectTouristRouteSpotsList(touristRouteSpots);
    }

    /**
     * 新增旅游路线中间景点
     * 
     * @param touristRouteSpots 旅游路线中间景点
     * @return 结果
     */
    @Override
    public int insertTouristRouteSpots(TouristRouteSpots touristRouteSpots)
    {
        touristRouteSpots.setCreateTime(DateUtils.getNowDate());
        return touristRouteSpotsMapper.insertTouristRouteSpots(touristRouteSpots);
    }

    /**
     * 修改旅游路线中间景点
     * 
     * @param touristRouteSpots 旅游路线中间景点
     * @return 结果
     */
    @Override
    public int updateTouristRouteSpots(TouristRouteSpots touristRouteSpots)
    {
        touristRouteSpots.setUpdateTime(DateUtils.getNowDate());
        return touristRouteSpotsMapper.updateTouristRouteSpots(touristRouteSpots);
    }

    /**
     * 批量删除旅游路线中间景点
     * 
     * @param ids 需要删除的旅游路线中间景点主键
     * @return 结果
     */
    @Override
    public int deleteTouristRouteSpotsByIds(Long[] ids)
    {
        return touristRouteSpotsMapper.deleteTouristRouteSpotsByIds(ids);
    }

    /**
     * 删除旅游路线中间景点信息
     * 
     * @param id 旅游路线中间景点主键
     * @return 结果
     */
    @Override
    public int deleteTouristRouteSpotsById(Long id)
    {
        return touristRouteSpotsMapper.deleteTouristRouteSpotsById(id);
    }
}
