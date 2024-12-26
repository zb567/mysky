package com.mysky.system.domain;

import java.math.BigDecimal;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import com.mysky.common.annotation.Excel;
import com.mysky.common.core.domain.BaseEntity;

/**
 * 旅游地点存储核心信息对象 tb_tourist_spot
 * 
 * @author ll
 * @date 2024-12-25
 */
public class TouristSpot extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    /** id */
    private Long id;

    /** 旅游地点名称 */
    @Excel(name = "旅游地点名称")
    private String name;

    /** 封面图片 */
    @Excel(name = "封面图片")
    private String coverImageUrl;

    /** 业务数据 */
    @Excel(name = "业务数据")
    private String businessMetrics;

    /** 主要行业 */
    @Excel(name = "主要行业")
    private String topIndustries;

    /** 旅游地点描述 */
    @Excel(name = "旅游地点描述")
    private String description;

    /** 分类ID */
    @Excel(name = "分类ID")
    private Long categoryId;

    /** 纬度 */
    @Excel(name = "纬度")
    private BigDecimal latitude;

    /** 经度 */
    @Excel(name = "经度")
    private BigDecimal longitude;

    /** 所属城市 */
    @Excel(name = "所属城市")
    private String city;

    /** 所属省份 */
    @Excel(name = "所属省份")
    private String province;

    /** 所属国家 */
    @Excel(name = "所属国家")
    private String country;

    /** 用户评分 */
    @Excel(name = "用户评分")
    private BigDecimal rating;

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
    public void setCoverImageUrl(String coverImageUrl) 
    {
        this.coverImageUrl = coverImageUrl;
    }

    public String getCoverImageUrl() 
    {
        return coverImageUrl;
    }
    public void setBusinessMetrics(String businessMetrics) 
    {
        this.businessMetrics = businessMetrics;
    }

    public String getBusinessMetrics() 
    {
        return businessMetrics;
    }
    public void setTopIndustries(String topIndustries) 
    {
        this.topIndustries = topIndustries;
    }

    public String getTopIndustries() 
    {
        return topIndustries;
    }
    public void setDescription(String description) 
    {
        this.description = description;
    }

    public String getDescription() 
    {
        return description;
    }
    public void setCategoryId(Long categoryId) 
    {
        this.categoryId = categoryId;
    }

    public Long getCategoryId() 
    {
        return categoryId;
    }
    public void setLatitude(BigDecimal latitude) 
    {
        this.latitude = latitude;
    }

    public BigDecimal getLatitude() 
    {
        return latitude;
    }
    public void setLongitude(BigDecimal longitude) 
    {
        this.longitude = longitude;
    }

    public BigDecimal getLongitude() 
    {
        return longitude;
    }
    public void setCity(String city) 
    {
        this.city = city;
    }

    public String getCity() 
    {
        return city;
    }
    public void setProvince(String province) 
    {
        this.province = province;
    }

    public String getProvince() 
    {
        return province;
    }
    public void setCountry(String country) 
    {
        this.country = country;
    }

    public String getCountry() 
    {
        return country;
    }
    public void setRating(BigDecimal rating) 
    {
        this.rating = rating;
    }

    public BigDecimal getRating() 
    {
        return rating;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this,ToStringStyle.MULTI_LINE_STYLE)
            .append("id", getId())
            .append("name", getName())
            .append("coverImageUrl", getCoverImageUrl())
            .append("businessMetrics", getBusinessMetrics())
            .append("topIndustries", getTopIndustries())
            .append("description", getDescription())
            .append("categoryId", getCategoryId())
            .append("latitude", getLatitude())
            .append("longitude", getLongitude())
            .append("city", getCity())
            .append("province", getProvince())
            .append("country", getCountry())
            .append("rating", getRating())
            .append("createTime", getCreateTime())
            .append("updateTime", getUpdateTime())
            .toString();
    }
}
