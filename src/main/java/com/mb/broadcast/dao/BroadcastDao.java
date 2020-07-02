package com.mb.broadcast.dao;

import com.mb.broadcast.entity.BroadcastEntity;
import com.mb.base.BaseDao;
import org.mybatis.spring.annotation.MapperScan;

@MapperScan
public interface BroadcastDao extends BaseDao<BroadcastEntity, String> {

}