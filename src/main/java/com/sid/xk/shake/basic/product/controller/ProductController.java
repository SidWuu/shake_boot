package com.sid.xk.shake.basic.product.controller;


import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.sid.xk.shake.basic.product.entity.BasicProduct;
import com.sid.xk.shake.basic.product.service.IProductService;
import com.sid.xk.shake.basic.product.vo.ProductQuery;
import com.sid.xk.shake.common.constants.StatusEnum;
import com.sid.xk.shake.common.controller.BaseController;
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

import static com.sid.xk.shake.common.constants.BaseConstants.RES_RETURN_DATA;
import static com.sid.xk.shake.common.constants.BaseConstants.RES_RETURN_MESSAGE;
import static com.sid.xk.shake.common.constants.BaseConstants.RES_RETURN_STATUS;

/**
 * <p>
 * 物资代码表 前端控制器
 * </p>
 *
 * @author wuxiaodong
 * @since 2021-03-28
 */
@RestController
@RequestMapping("/basic/product")
public class ProductController extends BaseController {

    @Autowired
    private IProductService productService;

    @PostMapping("/pages")
    public ModelMap query(@RequestBody ProductQuery form) {
        ModelMap modelMap = new ModelMap();
        try {
            Page<BasicProduct> data = productService.queryPage(form);
            modelMap.addAttribute(RES_RETURN_STATUS, StatusEnum.SUCCESS.getStatus());
            modelMap.addAttribute(RES_RETURN_MESSAGE, StatusEnum.SUCCESS.getMsg());
            modelMap.addAttribute(RES_RETURN_DATA, data);
        } catch (RuntimeException e) {
            e.printStackTrace();
            modelMap.addAttribute(RES_RETURN_STATUS, StatusEnum.ERROR.getStatus());
            modelMap.addAttribute(RES_RETURN_MESSAGE, e.getMessage());
        } catch (Exception e) {
            e.printStackTrace();
            modelMap.addAttribute(RES_RETURN_STATUS, StatusEnum.ERROR.getStatus());
            modelMap.addAttribute(RES_RETURN_MESSAGE, StatusEnum.ERROR.getMsg());
        }
        return modelMap;
    }

    @GetMapping("/edit/{productCode}")
    public ModelMap edit(@PathVariable String productCode) {
        ModelMap modelMap = new ModelMap();
        try {
            BasicProduct data = productService.getBean(productCode);
            modelMap.addAttribute(RES_RETURN_STATUS, StatusEnum.SUCCESS.getStatus());
            modelMap.addAttribute(RES_RETURN_MESSAGE, StatusEnum.SUCCESS.getMsg());
            modelMap.addAttribute(RES_RETURN_DATA, data);
        } catch (RuntimeException e) {
            e.printStackTrace();
            modelMap.addAttribute(RES_RETURN_STATUS, StatusEnum.ERROR.getStatus());
            modelMap.addAttribute(RES_RETURN_MESSAGE, e.getMessage());
        } catch (Exception e) {
            e.printStackTrace();
            modelMap.addAttribute(RES_RETURN_STATUS, StatusEnum.ERROR.getStatus());
            modelMap.addAttribute(RES_RETURN_MESSAGE, StatusEnum.ERROR.getMsg());
        }
        return modelMap;
    }

    @PostMapping("/save")
    public ModelMap save(@RequestBody BasicProduct mod) {
        ModelMap modelMap = new ModelMap();
        try {
            productService.insert(mod);
            modelMap.addAttribute(RES_RETURN_STATUS, StatusEnum.SUCCESS.getStatus());
            modelMap.addAttribute(RES_RETURN_MESSAGE, StatusEnum.SUCCESS.getMsg());
            modelMap.addAttribute(RES_RETURN_DATA, mod.getProductCode());
        } catch (RuntimeException e) {
            e.printStackTrace();
            modelMap.addAttribute(RES_RETURN_STATUS, StatusEnum.ERROR.getStatus());
            modelMap.addAttribute(RES_RETURN_MESSAGE, e.getMessage());
        } catch (Exception e) {
            e.printStackTrace();
            modelMap.addAttribute(RES_RETURN_STATUS, StatusEnum.ERROR.getStatus());
            modelMap.addAttribute(RES_RETURN_MESSAGE, StatusEnum.ERROR.getMsg());
        }
        return modelMap;
    }

    @PutMapping("/update")
    public ModelMap update(@RequestBody BasicProduct mod) {
        ModelMap modelMap = new ModelMap();
        try {
            productService.update(mod);
            modelMap.addAttribute(RES_RETURN_STATUS, StatusEnum.SUCCESS.getStatus());
            modelMap.addAttribute(RES_RETURN_MESSAGE, StatusEnum.SUCCESS.getMsg());
        } catch (RuntimeException e) {
            e.printStackTrace();
            modelMap.addAttribute(RES_RETURN_STATUS, StatusEnum.ERROR.getStatus());
            modelMap.addAttribute(RES_RETURN_MESSAGE, e.getMessage());
        } catch (Exception e) {
            e.printStackTrace();
            modelMap.addAttribute(RES_RETURN_STATUS, StatusEnum.ERROR.getStatus());
            modelMap.addAttribute(RES_RETURN_MESSAGE, StatusEnum.ERROR.getMsg());
        }
        return modelMap;
    }

    @DeleteMapping("/delete/{productCode}")
    public ModelMap delete(@PathVariable String productCode) {
        ModelMap modelMap = new ModelMap();
        try {
            productService.delete(productCode);
            modelMap.addAttribute(RES_RETURN_STATUS, StatusEnum.SUCCESS.getStatus());
            modelMap.addAttribute(RES_RETURN_MESSAGE, StatusEnum.SUCCESS.getMsg());
        } catch (RuntimeException e) {
            e.printStackTrace();
            modelMap.addAttribute(RES_RETURN_STATUS, StatusEnum.ERROR.getStatus());
            modelMap.addAttribute(RES_RETURN_MESSAGE, e.getMessage());
        } catch (Exception e) {
            e.printStackTrace();
            modelMap.addAttribute(RES_RETURN_STATUS, StatusEnum.ERROR.getStatus());
            modelMap.addAttribute(RES_RETURN_MESSAGE, StatusEnum.ERROR.getMsg());
        }
        return modelMap;
    }

}
