package com.mb.baseType.service;

import com.mb.base.BaseService;
import com.mb.baseType.entity.BaseTypeGroup;

/**
 * 数据字典--service服务层
 */
public interface IBaseTypeGroupService extends BaseService<BaseTypeGroup, String> {

    /**
     * 初始化所有的数据字典数据
     */
    void initAllBaseTypeGroups();

    /**
     * 刷新数据字典数据
     */
    void refreshBaseTypeGroups();

}
