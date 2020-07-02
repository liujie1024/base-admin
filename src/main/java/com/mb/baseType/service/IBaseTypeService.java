package com.mb.baseType.service;

import com.mb.base.BaseService;
import com.mb.baseType.entity.BaseType;

import java.util.Map;

/**
 * 数据字典--service服务层
 */
public interface IBaseTypeService extends BaseService<BaseType, String> {

    /**
     * 按条件删除数据
     *
     * @param paramMap
     * @return
     */
    boolean deleteByParam(Map<String, Object> paramMap);

}
