package com.mysky.system.service.impl;

import java.util.List;
import com.mysky.common.utils.DateUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.mysky.system.mapper.TouristRouteMapper;
import com.mysky.system.domain.TouristRoute;
import com.mysky.system.service.ITouristRouteService;

/**
 * 存储旅游者路线的基本信息Service业务层处理
 * 
 * @author ll
 * @date 2024-12-30
 */
@Service
public class TouristRouteServiceImpl implements ITouristRouteService 
{
    @Autowired
    private TouristRouteMapper touristRouteMapper;

    /**
     * 查询存储旅游者路线的基本信息
     * 
     * @param id 存储旅游者路线的基本信息主键
     * @return 存储旅游者路线的基本信息
     */
    @Override
    public TouristRoute selectTouristRouteById(Long id)
    {
        return touristRouteMapper.selectTouristRouteById(id);
    }

    /**
     * 查询存储旅游者路线的基本信息列表
     * 
     * @param touristRoute 存储旅游者路线的基本信息
     * @return 存储旅游者路线的基本信息
     */
    @Override
    public List<TouristRoute> selectTouristRouteList(TouristRoute touristRoute)
    {
        return touristRouteMapper.selectTouristRouteList(touristRoute);
    }

    /**
     * 新增存储旅游者路线的基本信息
     * 
     * @param touristRoute 存储旅游者路线的基本信息
     * @return 结果
     */
    @Override
    public int insertTouristRoute(TouristRoute touristRoute)
    {
        touristRoute.setCreateTime(DateUtils.getNowDate());
        return touristRouteMapper.insertTouristRoute(touristRoute);
    }

    /**
     * 修改存储旅游者路线的基本信息
     * 
     * @param touristRoute 存储旅游者路线的基本信息
     * @return 结果
     */
    @Override
    public int updateTouristRoute(TouristRoute touristRoute)
    {
        touristRoute.setUpdateTime(DateUtils.getNowDate());
        return touristRouteMapper.updateTouristRoute(touristRoute);
    }

    /**
     * 批量删除存储旅游者路线的基本信息
     * 
     * @param ids 需要删除的存储旅游者路线的基本信息主键
     * @return 结果
     */
    @Override
    public int deleteTouristRouteByIds(Long[] ids)
    {
        return touristRouteMapper.deleteTouristRouteByIds(ids);
    }

    /**
     * 删除存储旅游者路线的基本信息信息
     * 
     * @param id 存储旅游者路线的基本信息主键
     * @return 结果
     */
    @Override
    public int deleteTouristRouteById(Long id)
    {
        return touristRouteMapper.deleteTouristRouteById(id);
    }
}
