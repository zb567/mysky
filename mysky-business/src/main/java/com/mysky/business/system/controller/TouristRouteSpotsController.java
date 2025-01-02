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
import com.mysky.business.system.domain.TouristRouteSpots;
import com.mysky.business.system.service.ITouristRouteSpotsService;
import com.mysky.common.utils.poi.ExcelUtil;
import com.mysky.common.core.page.TableDataInfo;

/**
 * 旅游路线中间景点Controller
 * 
 * @author ll
 * @date 2024-12-30
 */
@RestController
@RequestMapping("/system/route_spots")
public class TouristRouteSpotsController extends BaseController
{
    @Autowired
    private ITouristRouteSpotsService touristRouteSpotsService;

    /**
     * 查询旅游路线中间景点列表
     */
    @PreAuthorize("@ss.hasPermi('system:route_spots:list')")
    @GetMapping("/list")
    public TableDataInfo list(TouristRouteSpots touristRouteSpots)
    {
        startPage();
        List<TouristRouteSpots> list = touristRouteSpotsService.selectTouristRouteSpotsList(touristRouteSpots);
        return getDataTable(list);
    }

    /**
     * 导出旅游路线中间景点列表
     */
    @PreAuthorize("@ss.hasPermi('system:route_spots:export')")
    @Log(title = "旅游路线中间景点", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    public void export(HttpServletResponse response, TouristRouteSpots touristRouteSpots)
    {
        List<TouristRouteSpots> list = touristRouteSpotsService.selectTouristRouteSpotsList(touristRouteSpots);
        ExcelUtil<TouristRouteSpots> util = new ExcelUtil<TouristRouteSpots>(TouristRouteSpots.class);
        util.exportExcel(response, list, "旅游路线中间景点数据");
    }

    /**
     * 获取旅游路线中间景点详细信息
     */
    @PreAuthorize("@ss.hasPermi('system:route_spots:query')")
    @GetMapping(value = "/{id}")
    public AjaxResult getInfo(@PathVariable("id") Long id)
    {
        return success(touristRouteSpotsService.selectTouristRouteSpotsById(id));
    }

    /**
     * 新增旅游路线中间景点
     */
    @PreAuthorize("@ss.hasPermi('system:route_spots:add')")
    @Log(title = "旅游路线中间景点", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult add(@RequestBody TouristRouteSpots touristRouteSpots)
    {
        return toAjax(touristRouteSpotsService.insertTouristRouteSpots(touristRouteSpots));
    }

    /**
     * 修改旅游路线中间景点
     */
    @PreAuthorize("@ss.hasPermi('system:route_spots:edit')")
    @Log(title = "旅游路线中间景点", businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult edit(@RequestBody TouristRouteSpots touristRouteSpots)
    {
        return toAjax(touristRouteSpotsService.updateTouristRouteSpots(touristRouteSpots));
    }

    /**
     * 删除旅游路线中间景点
     */
    @PreAuthorize("@ss.hasPermi('system:route_spots:remove')")
    @Log(title = "旅游路线中间景点", businessType = BusinessType.DELETE)
	@DeleteMapping("/{ids}")
    public AjaxResult remove(@PathVariable Long[] ids)
    {
        return toAjax(touristRouteSpotsService.deleteTouristRouteSpotsByIds(ids));
    }
}
