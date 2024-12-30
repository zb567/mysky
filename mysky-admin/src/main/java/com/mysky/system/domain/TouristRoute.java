package com.mysky.system.domain;

import java.math.BigDecimal;
import java.util.Date;
import com.fasterxml.jackson.annotation.JsonFormat;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import com.mysky.common.annotation.Excel;
import com.mysky.common.core.domain.BaseEntity;

/**
 * 存储旅游者路线的基本信息对象 tb_tourist_route
 * 
 * @author ll
 * @date 2024-12-30
 */
public class TouristRoute extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    /** 路线唯一标识 */
    private Long id;

    /** 路线名称 */
    @Excel(name = "路线名称")
    private String name;

    /** 起点城市名称 */
    @Excel(name = "起点城市名称")
    private String startCity;

    /** 终点城市名称 */
    @Excel(name = "终点城市名称")
    private String endCity;

    /** 起点景点ID */
    @Excel(name = "起点景点ID")
    private Long startPointId;

    /** 起点景点名称 */
    @Excel(name = "起点景点名称")
    private String startPointName;

    /** 终点景点ID */
    @Excel(name = "终点景点ID")
    private Long endPointId;

    /** 终点景点名称 */
    @Excel(name = "终点景点名称")
    private String endPointName;

    /** 路线总距离（公里） */
    @Excel(name = "路线总距离", readConverterExp = "公=里")
    private BigDecimal totalDistance;

    /** 预计旅行时间 */
    @JsonFormat(pattern = "yyyy-MM-dd")
    @Excel(name = "预计旅行时间", width = 30, dateFormat = "yyyy-MM-dd")
    private Date estimatedTime;

    public void setId(Long id) 
    {
        this.id = id;
    }

    public Long getId() 
    {
        return id;
    }
    public void setName(String name) 
    {
        this.name = name;
    }

    public String getName() 
    {
        return name;
    }
    public void setStartCity(String startCity) 
    {
        this.startCity = startCity;
    }

    public String getStartCity() 
    {
        return startCity;
    }
    public void setEndCity(String endCity) 
    {
        this.endCity = endCity;
    }

    public String getEndCity() 
    {
        return endCity;
    }
    public void setStartPointId(Long startPointId) 
    {
        this.startPointId = startPointId;
    }

    public Long getStartPointId() 
    {
        return startPointId;
    }
    public void setStartPointName(String startPointName) 
    {
        this.startPointName = startPointName;
    }

    public String getStartPointName() 
    {
        return startPointName;
    }
    public void setEndPointId(Long endPointId) 
    {
        this.endPointId = endPointId;
    }

    public Long getEndPointId() 
    {
        return endPointId;
    }
    public void setEndPointName(String endPointName) 
    {
        this.endPointName = endPointName;
    }

    public String getEndPointName() 
    {
        return endPointName;
    }
    public void setTotalDistance(BigDecimal totalDistance) 
    {
        this.totalDistance = totalDistance;
    }

    public BigDecimal getTotalDistance() 
    {
        return totalDistance;
    }
    public void setEstimatedTime(Date estimatedTime) 
    {
        this.estimatedTime = estimatedTime;
    }

    public Date getEstimatedTime() 
    {
        return estimatedTime;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this,ToStringStyle.MULTI_LINE_STYLE)
            .append("id", getId())
            .append("name", getName())
            .append("startCity", getStartCity())
            .append("endCity", getEndCity())
            .append("startPointId", getStartPointId())
            .append("startPointName", getStartPointName())
            .append("endPointId", getEndPointId())
            .append("endPointName", getEndPointName())
            .append("totalDistance", getTotalDistance())
            .append("estimatedTime", getEstimatedTime())
            .append("createTime", getCreateTime())
            .append("updateTime", getUpdateTime())
            .toString();
    }
}
