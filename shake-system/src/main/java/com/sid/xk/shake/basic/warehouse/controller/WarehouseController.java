package com.sid.xk.shake.basic.warehouse.controller;


import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.sid.xk.shake.basic.warehouse.entity.BasicWarearea;
import com.sid.xk.shake.basic.warehouse.entity.BasicWarehouse;
import com.sid.xk.shake.basic.warehouse.service.IWarehouseService;
import com.sid.xk.shake.basic.warehouse.vo.WarehouseBean;
import com.sid.xk.shake.basic.warehouse.vo.WarehouseQuery;
import com.sid.xk.shake.constants.BaseConstants;
import com.sid.xk.shake.constants.StatusEnum;
import com.sid.xk.shake.controller.BaseController;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * <p>
 * 仓库表 前端控制器
 * </p>
 *
 * @author wuxiaodong
 * @since 2021-03-28
 */
@RestController
@RequestMapping("/basic/warehouse")
public class WarehouseController extends BaseController {

    @Autowired
    private IWarehouseService warehouseService;

    @PostMapping("/pages")
    public ModelMap query(@RequestBody WarehouseQuery form) {
        ModelMap modelMap = new ModelMap();
        try {
            Page<BasicWarehouse> data = warehouseService.queryPage(form);
            modelMap.addAttribute(BaseConstants.RES_RETURN_STATUS, StatusEnum.SUCCESS.getStatus());
            modelMap.addAttribute(BaseConstants.RES_RETURN_MESSAGE, StatusEnum.SUCCESS.getMsg());
            modelMap.addAttribute(BaseConstants.RES_RETURN_DATA, data);
        } catch (RuntimeException e) {
            e.printStackTrace();
            modelMap.addAttribute(BaseConstants.RES_RETURN_STATUS, StatusEnum.ERROR.getStatus());
            modelMap.addAttribute(BaseConstants.RES_RETURN_MESSAGE, e.getMessage());
        } catch (Exception e) {
            e.printStackTrace();
            modelMap.addAttribute(BaseConstants.RES_RETURN_STATUS, StatusEnum.ERROR.getStatus());
            modelMap.addAttribute(BaseConstants.RES_RETURN_MESSAGE, StatusEnum.ERROR.getMsg());
        }
        return modelMap;
    }

    @GetMapping("/detail/{warehouseCode}")
    public ModelMap queryDetail(@PathVariable String warehouseCode) {
        ModelMap modelMap = new ModelMap();
        try {
            List<BasicWarearea> data = warehouseService.queryDetail(warehouseCode);
            modelMap.addAttribute(BaseConstants.RES_RETURN_STATUS, StatusEnum.SUCCESS.getStatus());
            modelMap.addAttribute(BaseConstants.RES_RETURN_MESSAGE, StatusEnum.SUCCESS.getMsg());
            modelMap.addAttribute(BaseConstants.RES_RETURN_DATA, data);
        } catch (RuntimeException e) {
            e.printStackTrace();
            modelMap.addAttribute(BaseConstants.RES_RETURN_STATUS, StatusEnum.ERROR.getStatus());
            modelMap.addAttribute(BaseConstants.RES_RETURN_MESSAGE, e.getMessage());
        } catch (Exception e) {
            e.printStackTrace();
            modelMap.addAttribute(BaseConstants.RES_RETURN_STATUS, StatusEnum.ERROR.getStatus());
            modelMap.addAttribute(BaseConstants.RES_RETURN_MESSAGE, StatusEnum.ERROR.getMsg());
        }
        return modelMap;
    }

    @GetMapping("/edit/{warehouseCode}")
    public ModelMap edit(@PathVariable String warehouseCode) {
        ModelMap modelMap = new ModelMap();
        try {
            WarehouseBean data = warehouseService.getBean(warehouseCode);
            modelMap.addAttribute(BaseConstants.RES_RETURN_STATUS, StatusEnum.SUCCESS.getStatus());
            modelMap.addAttribute(BaseConstants.RES_RETURN_MESSAGE, StatusEnum.SUCCESS.getMsg());
            modelMap.addAttribute(BaseConstants.RES_RETURN_DATA, data);
        } catch (RuntimeException e) {
            e.printStackTrace();
            modelMap.addAttribute(BaseConstants.RES_RETURN_STATUS, StatusEnum.ERROR.getStatus());
            modelMap.addAttribute(BaseConstants.RES_RETURN_MESSAGE, e.getMessage());
        } catch (Exception e) {
            e.printStackTrace();
            modelMap.addAttribute(BaseConstants.RES_RETURN_STATUS, StatusEnum.ERROR.getStatus());
            modelMap.addAttribute(BaseConstants.RES_RETURN_MESSAGE, StatusEnum.ERROR.getMsg());
        }
        return modelMap;
    }

    @PostMapping("/save")
    public ModelMap save(@RequestBody WarehouseBean bean) {
        ModelMap modelMap = new ModelMap();
        try {
            warehouseService.insert(bean);
            modelMap.addAttribute(BaseConstants.RES_RETURN_STATUS, StatusEnum.SUCCESS.getStatus());
            modelMap.addAttribute(BaseConstants.RES_RETURN_MESSAGE, StatusEnum.SUCCESS.getMsg());
            modelMap.addAttribute(BaseConstants.RES_RETURN_DATA, bean.getMain().getWarehouseCode());
        } catch (RuntimeException e) {
            e.printStackTrace();
            modelMap.addAttribute(BaseConstants.RES_RETURN_STATUS, StatusEnum.ERROR.getStatus());
            modelMap.addAttribute(BaseConstants.RES_RETURN_MESSAGE, e.getMessage());
        } catch (Exception e) {
            e.printStackTrace();
            modelMap.addAttribute(BaseConstants.RES_RETURN_STATUS, StatusEnum.ERROR.getStatus());
            modelMap.addAttribute(BaseConstants.RES_RETURN_MESSAGE, StatusEnum.ERROR.getMsg());
        }
        return modelMap;
    }

    @PutMapping("/update")
    public ModelMap update(@RequestBody WarehouseBean bean) {
        ModelMap modelMap = new ModelMap();
        try {
            warehouseService.update(bean);
            modelMap.addAttribute(BaseConstants.RES_RETURN_STATUS, StatusEnum.SUCCESS.getStatus());
            modelMap.addAttribute(BaseConstants.RES_RETURN_MESSAGE, StatusEnum.SUCCESS.getMsg());
        } catch (RuntimeException e) {
            e.printStackTrace();
            modelMap.addAttribute(BaseConstants.RES_RETURN_STATUS, StatusEnum.ERROR.getStatus());
            modelMap.addAttribute(BaseConstants.RES_RETURN_MESSAGE, e.getMessage());
        } catch (Exception e) {
            e.printStackTrace();
            modelMap.addAttribute(BaseConstants.RES_RETURN_STATUS, StatusEnum.ERROR.getStatus());
            modelMap.addAttribute(BaseConstants.RES_RETURN_MESSAGE, StatusEnum.ERROR.getMsg());
        }
        return modelMap;
    }

    @DeleteMapping("/delete/{warehouseCode}")
    public ModelMap delete(@PathVariable String warehouseCode) {
        ModelMap modelMap = new ModelMap();
        try {
            warehouseService.delete(warehouseCode);
            modelMap.addAttribute(BaseConstants.RES_RETURN_STATUS, StatusEnum.SUCCESS.getStatus());
            modelMap.addAttribute(BaseConstants.RES_RETURN_MESSAGE, StatusEnum.SUCCESS.getMsg());
        } catch (RuntimeException e) {
            e.printStackTrace();
            modelMap.addAttribute(BaseConstants.RES_RETURN_STATUS, StatusEnum.ERROR.getStatus());
            modelMap.addAttribute(BaseConstants.RES_RETURN_MESSAGE, e.getMessage());
        } catch (Exception e) {
            e.printStackTrace();
            modelMap.addAttribute(BaseConstants.RES_RETURN_STATUS, StatusEnum.ERROR.getStatus());
            modelMap.addAttribute(BaseConstants.RES_RETURN_MESSAGE, StatusEnum.ERROR.getMsg());
        }
        return modelMap;
    }

}
