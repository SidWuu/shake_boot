package com.sid.xk.shake.basic.company.vo;

import com.sid.xk.shake.basic.company.entity.BasicCompany;
import com.sid.xk.shake.basic.company.entity.BasicCompanyLinkman;

import java.io.Serializable;
import java.util.List;

/**
 * @author wuxiaodong
 * @date 2021/04/03
 */
public class CompanyBean implements Serializable {

    private String code;

    private BasicCompany main;

    private List<BasicCompanyLinkman> details;

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public BasicCompany getMain() {
        return main;
    }

    public void setMain(BasicCompany main) {
        this.main = main;
    }

    public List<BasicCompanyLinkman> getDetails() {
        return details;
    }

    public void setDetails(List<BasicCompanyLinkman> details) {
        this.details = details;
    }
}
