package com.mb.dingding.dao;

import org.mybatis.spring.annotation.MapperScan;

import com.mb.base.BaseDao;
import com.mb.dingding.entity.DdAccessToken;

@MapperScan
public interface DdAssessTokenDao extends BaseDao<DdAccessToken, java.lang.String> {

}