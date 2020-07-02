package com.mb.baseType.dao;

import com.mb.base.BaseDao;
import com.mb.baseType.entity.BaseTypeGroup;
import org.mybatis.spring.annotation.MapperScan;

@MapperScan
public interface BaseTypeGroupDao extends BaseDao<BaseTypeGroup, String> {

}