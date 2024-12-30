package com.mysky.system.controller;

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
import com.mysky.system.domain.TouristRoute;
import com.mysky.system.service.ITouristRouteService;
import com.mysky.common.utils.poi.ExcelUtil;
import com.mysky.common.core.page.TableDataInfo;

/**
 * 存储旅游者路线的基本信息Controller
 * 
 * @author ll
 * @date 2024-12-30
 */
@RestController
@RequestMapping("/system/route")
public class TouristRouteController extends BaseController
{
    @Autowired
    private ITouristRouteService touristRouteService;

    /**
     * 查询存储旅游者路线的基本信息列表
     */
    @PreAuthorize("@ss.hasPermi('system:route:list')")
    @GetMapping("/list")
    public TableDataInfo list(TouristRoute touristRoute)
    {
        startPage();
        List<TouristRoute> list = touristRouteService.selectTouristRouteList(touristRoute);
        return getDataTable(list);
    }

    /**
     * 导出存储旅游者路线的基本信息列表
     */
    @PreAuthorize("@ss.hasPermi('system:route:export')")
    @Log(title = "存储旅游者路线的基本信息", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    public void export(HttpServletResponse response, TouristRoute touristRoute)
    {
        List<TouristRoute> list = touristRouteService.selectTouristRouteList(touristRoute);
        ExcelUtil<TouristRoute> util = new ExcelUtil<TouristRoute>(TouristRoute.class);
        util.exportExcel(response, list, "存储旅游者路线的基本信息数据");
    }

    /**
     * 获取存储旅游者路线的基本信息详细信息
     */
    @PreAuthorize("@ss.hasPermi('system:route:query')")
    @GetMapping(value = "/{id}")
    public AjaxResult getInfo(@PathVariable("id") Long id)
    {
        return success(touristRouteService.selectTouristRouteById(id));
    }

    /**
     * 新增存储旅游者路线的基本信息
     */
    @PreAuthorize("@ss.hasPermi('system:route:add')")
    @Log(title = "存储旅游者路线的基本信息", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult add(@RequestBody TouristRoute touristRoute)
    {
        return toAjax(touristRouteService.insertTouristRoute(touristRoute));
    }

    /**
     * 修改存储旅游者路线的基本信息
     */
    @PreAuthorize("@ss.hasPermi('system:route:edit')")
    @Log(title = "存储旅游者路线的基本信息", businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult edit(@RequestBody TouristRoute touristRoute)
    {
        return toAjax(touristRouteService.updateTouristRoute(touristRoute));
    }

    /**
     * 删除存储旅游者路线的基本信息
     */
    @PreAuthorize("@ss.hasPermi('system:route:remove')")
    @Log(title = "存储旅游者路线的基本信息", businessType = BusinessType.DELETE)
	@DeleteMapping("/{ids}")
    public AjaxResult remove(@PathVariable Long[] ids)
    {
        return toAjax(touristRouteService.deleteTouristRouteByIds(ids));
    }
}
