package com.sid.xk.shake.basic.dictionary.service;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.IService;
import com.sid.xk.shake.basic.dictionary.entity.BasicDictionary;
import com.sid.xk.shake.basic.dictionary.vo.DictionaryQuery;

import java.util.List;

/**
 * <p>
 * 数据字典表 服务类
 * </p>
 *
 * @author wuxiaodong
 * @since 2021-03-28
 */
public interface IDictionaryService extends IService<BasicDictionary> {

    /**
     * 查询类型
     * @param dataType 类型
     * @return Page<BasicDictionary>
     */
    List<BasicDictionary> queryTree(String dataType);

    /**
     * 分页查询
     * @param form 查询条件
     * @return Page<BasicDictionary>
     */
    Page<BasicDictionary> queryPage(DictionaryQuery form);

    /**
     * 查询详情
     * @param dataType 类型
     * @param dataCode 编码
     * @return BasicDictionary
     */
    BasicDictionary getBean(String dataType, String dataCode);

    /**
     * 新增
     * @param mod 保存bean
     */
    void insert(BasicDictionary mod);

    /**
     * 修改
     * @param mod 保存bean
     */
    void update(BasicDictionary mod);

    /**
     * 删除
     * @param dataType 类型
     * @param dataCode 编码
     */
    void delete(String dataType, String dataCode);

    /**
     * 加入缓存
     * key: BasicDictionary-data_type-{dataType}, value: List<BasicDictionary>
     */
    void loadCache();

}
