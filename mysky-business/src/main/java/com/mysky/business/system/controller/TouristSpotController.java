package com.mysky.business.system.controller;

import java.util.List;
import javax.servlet.http.HttpServletResponse;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.mysky.common.annotation.Log;
import com.mysky.common.core.controller.BaseController;
import com.mysky.common.core.domain.AjaxResult;
import com.mysky.common.enums.BusinessType;
import com.mysky.business.system.domain.TouristSpot;
import com.mysky.business.system.service.ITouristSpotService;
import com.mysky.common.utils.poi.ExcelUtil;
import com.mysky.common.core.page.TableDataInfo;

/**
 * 旅游地点存储核心信息Controller
 * 
 * @author ll
 * @date 2024-12-30
 */
@RestController
@RequestMapping("/system/spot")
public class TouristSpotController extends BaseController
{
    @Autowired
    private ITouristSpotService touristSpotService;

    /**
     * 查询旅游地点存储核心信息列表
     */
    @PreAuthorize("@ss.hasPermi('system:spot:list')")
    @GetMapping("/list")
    public TableDataInfo list(TouristSpot touristSpot)
    {
        startPage();
        List<TouristSpot> list = touristSpotService.selectTouristSpotList(touristSpot);
        return getDataTable(list);
    }

    /**
     * 导出旅游地点存储核心信息列表
     */
    @PreAuthorize("@ss.hasPermi('system:spot:export')")
    @Log(title = "旅游地点存储核心信息", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    public void export(HttpServletResponse response, TouristSpot touristSpot)
    {
        List<TouristSpot> list = touristSpotService.selectTouristSpotList(touristSpot);
        ExcelUtil<TouristSpot> util = new ExcelUtil<TouristSpot>(TouristSpot.class);
        util.exportExcel(response, list, "旅游地点存储核心信息数据");
    }

    /**
     * 获取旅游地点存储核心信息详细信息
     */
    @PreAuthorize("@ss.hasPermi('system:spot:query')")
    @GetMapping(value = "/{id}")
    public AjaxResult getInfo(@PathVariable("id") Long id)
    {
        return success(touristSpotService.selectTouristSpotById(id));
    }

    /**
     * 新增旅游地点存储核心信息
     */
    @PreAuthorize("@ss.hasPermi('system:spot:add')")
    @Log(title = "旅游地点存储核心信息", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult add(@RequestBody TouristSpot touristSpot)
    {
        return toAjax(touristSpotService.insertTouristSpot(touristSpot));
    }

    /**
     * 修改旅游地点存储核心信息
     */
    @PreAuthorize("@ss.hasPermi('system:spot:edit')")
    @Log(title = "旅游地点存储核心信息", businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult edit(@RequestBody TouristSpot touristSpot)
    {
        return toAjax(touristSpotService.updateTouristSpot(touristSpot));
    }

    /**
     * 删除旅游地点存储核心信息
     */
    @PreAuthorize("@ss.hasPermi('system:spot:remove')")
    @Log(title = "旅游地点存储核心信息", businessType = BusinessType.DELETE)
	@DeleteMapping("/{ids}")
    public AjaxResult remove(@PathVariable Long[] ids)
    {
        return toAjax(touristSpotService.deleteTouristSpotByIds(ids));
    }
}
