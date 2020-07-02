package com.mb.broadcast.service.impl;

import com.mb.broadcast.dao.BroadcastDao;
import com.mb.broadcast.entity.BroadcastEntity;
import com.mb.broadcast.service.IBroadcastService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.UUID;

@Service
public class BroadcastServiceImpl implements IBroadcastService {

    @Resource
    private BroadcastDao broadcastDao;

    @Override
    public boolean save(BroadcastEntity entity) {
        boolean rtn = false;
        if (null != entity) {
            entity.setId(UUID.randomUUID().toString().replace("-", ""));
            entity.setCreateDate(new Date());
            Integer len = broadcastDao.save(entity);
            if (len > 0) {
                rtn = true;
            }
        }
        return rtn;
    }

    @Override
    public boolean update(BroadcastEntity entity) {
        boolean rtn = false;
        entity.setUpdateDate(new Date());
        Integer len = broadcastDao.update(entity);
        if (len > 0) {
            rtn = true;
        }
        return rtn;
    }

    @Override
    public boolean deleteById(String id) {
        boolean rtn = false;
        Integer isDel = broadcastDao.deleteById(id);
        if (isDel > 0) {
            rtn = true;
        }
        return rtn;
    }

    @Override
    public BroadcastEntity findById(String id) {
        return broadcastDao.findById(id);
    }

    @Override
    public List<BroadcastEntity> getListByParam(Map<String, Object> paramMap) {
        return broadcastDao.getListByParam(paramMap);
    }
}
