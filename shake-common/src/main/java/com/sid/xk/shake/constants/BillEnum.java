package com.sid.xk.shake.constants;

/**
 * 单据枚举
 * @author wuxiaodong
 * @date 2021/04/12
 */
public enum BillEnum {

    /* 往来企业[BasicCompany - basic_company] */
    BasicCompany("BasicCompany", "basic_company", "company_code"),
    /* 往来企业联系人[BasicCompanyLinkman - basic_company_linkman] */
    BasicCompanyLinkman("BasicCompanyLinkman", "basic_company_linkman", "linkman_code"),
    /* 数据字典[BasicDictionary - basic_dictionary] */
    BasicDictionary("BasicDictionary", "basic_dictionary", "data_type"),
    /* 物资代码[BasicProduct - basic_product] */
    BasicProduct("BasicProduct", "basic_product", "product_code"),
    /* 仓库[BasicWarehouse - basic_warehouse] */
    BasicWarehouse("BasicWarehouse", "basic_warehouse", "warehouse_code"),
    /* 库位[BasicWarearea - basic_warearea] */
    BasicWarearea("BasicWarearea", "basic_warearea", "warearea_code");

    public String getRedisKey() {
        return getEntity() + "-" + getColumn() + "-";
    }

    /** 实体名称 */
    private String entity;
    /** 表名称 */
    private String table;
    /** 表代码字段 */
    private String column;

    BillEnum(String entity, String table, String column) {
        this.entity = entity;
        this.table = table;
        this.column = column;
    }

    public String getEntity() {
        return entity;
    }

    public String getTable() {
        return table;
    }

    public String getColumn() {
        return column;
    }
}
