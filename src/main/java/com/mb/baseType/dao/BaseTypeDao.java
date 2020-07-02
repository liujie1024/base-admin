package com.mb.baseType.dao;

import com.mb.base.BaseDao;
import com.mb.baseType.entity.BaseType;
import org.mybatis.spring.annotation.MapperScan;

import java.util.Map;

@MapperScan
public interface BaseTypeDao extends BaseDao<BaseType, String> {

    /**
     * 按条件删除数据
     *
     * @param paramMap
     * @return
     */
    int deleteByParam(Map<String, Object> paramMap);

}