package com.sid.xk.shake.basic.dictionary.controller;


import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.sid.xk.shake.basic.dictionary.entity.BasicDictionary;
import com.sid.xk.shake.basic.dictionary.service.IDictionaryService;
import com.sid.xk.shake.basic.dictionary.vo.DictionaryQuery;
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
 * 数据字典表 前端控制器
 * </p>
 *
 * @author wuxiaodong
 * @since 2021-03-28
 */
@RestController
@RequestMapping("/basic/dictionary")
public class DictionaryController extends BaseController {

    @Autowired
    private IDictionaryService dictionaryService;

    @GetMapping("/tree")
    public ModelMap queryTree(@PathVariable String dataType) {
        ModelMap modelMap = new ModelMap();
        try {
            List<BasicDictionary> data =  dictionaryService.queryTree(dataType);
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

    @PostMapping("/query")
    public ModelMap query(@RequestBody DictionaryQuery form) {
        ModelMap modelMap = new ModelMap();
        try {
            Page<BasicDictionary> data = dictionaryService.queryPage(form);
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
    public ModelMap save(@RequestBody BasicDictionary mod) {
        ModelMap modelMap = new ModelMap();
        try {
            dictionaryService.insert(mod);
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

    @PutMapping("/update")
    public ModelMap update(@RequestBody BasicDictionary mod) {
        ModelMap modelMap = new ModelMap();
        try {
            dictionaryService.update(mod);
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

    @DeleteMapping("/delete/{dataType}/{dataCode}")
    public ModelMap delete(@PathVariable String dataType, @PathVariable String dataCode) {
        ModelMap modelMap = new ModelMap();
        try {
            dictionaryService.delete(dataType, dataCode);
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
