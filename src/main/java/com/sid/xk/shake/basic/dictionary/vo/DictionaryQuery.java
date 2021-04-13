package com.sid.xk.shake.basic.dictionary.vo;

import com.sid.xk.shake.common.vo.QueryBean;

/**
 * @author wuxiaodong
 * @date 2021/04/12
 */
public class DictionaryQuery extends QueryBean {

    /** 类型 */
    private String dataType;
    /** 编码 */
    private String dataCode;
    /** 值 */
    private String dataValue;
    /** 备注 */
    private String remark;

    public String getDataType() {
        return dataType;
    }

    public void setDataType(String dataType) {
        this.dataType = dataType;
    }

    public String getDataCode() {
        return dataCode;
    }

    public void setDataCode(String dataCode) {
        this.dataCode = dataCode;
    }

    public String getDataValue() {
        return dataValue;
    }

    public void setDataValue(String dataValue) {
        this.dataValue = dataValue;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }
}
