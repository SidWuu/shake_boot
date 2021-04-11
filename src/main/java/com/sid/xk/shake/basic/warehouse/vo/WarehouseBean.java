package com.sid.xk.shake.basic.warehouse.vo;

import com.sid.xk.shake.basic.warehouse.entity.BasicWarearea;
import com.sid.xk.shake.basic.warehouse.entity.BasicWarehouse;

import java.io.Serializable;
import java.util.List;

/**
 * @author wuxiaodong
 * @date 2021/04/11
 */
public class WarehouseBean implements Serializable {

    private String code;

    private BasicWarehouse main;

    private List<BasicWarearea> details;

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public BasicWarehouse getMain() {
        return main;
    }

    public void setMain(BasicWarehouse main) {
        this.main = main;
    }

    public List<BasicWarearea> getDetails() {
        return details;
    }

    public void setDetails(List<BasicWarearea> details) {
        this.details = details;
    }
}
