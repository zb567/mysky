package com.mysky.business.system.domain;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import com.mysky.common.annotation.Excel;
import com.mysky.common.core.domain.BaseEntity;

/**
 * 旅游路线中间景点对象 tb_tourist_route_spots
 * 
 * @author ll
 * @date 2024-12-30
 */
public class TouristRouteSpots extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    /** 记录唯一标识 */
    private Long id;

    /** 关联路线ID */
    @Excel(name = "关联路线ID")
    private Long routeId;

    /** 景点ID */
    @Excel(name = "景点ID")
    private Long spotId;

    /** 中间景点名称 */
    @Excel(name = "中间景点名称")
    private String spotName;

    /** 景点顺序（从起点开始） */
    @Excel(name = "景点顺序", readConverterExp = "从=起点开始")
    private Long sequence;

    public void setId(Long id) 
    {
        this.id = id;
    }

    public Long getId() 
    {
        return id;
    }
    public void setRouteId(Long routeId) 
    {
        this.routeId = routeId;
    }

    public Long getRouteId() 
    {
        return routeId;
    }
    public void setSpotId(Long spotId) 
    {
        this.spotId = spotId;
    }

    public Long getSpotId() 
    {
        return spotId;
    }
    public void setSpotName(String spotName) 
    {
        this.spotName = spotName;
    }

    public String getSpotName() 
    {
        return spotName;
    }
    public void setSequence(Long sequence) 
    {
        this.sequence = sequence;
    }

    public Long getSequence() 
    {
        return sequence;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this,ToStringStyle.MULTI_LINE_STYLE)
            .append("id", getId())
            .append("routeId", getRouteId())
            .append("spotId", getSpotId())
            .append("spotName", getSpotName())
            .append("sequence", getSequence())
            .append("createTime", getCreateTime())
            .append("updateTime", getUpdateTime())
            .toString();
    }
}
