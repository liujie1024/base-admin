package com.mb.baseType.service.impl;

import com.mb.baseType.dao.BaseTypeDao;
import com.mb.baseType.entity.BaseType;
import com.mb.baseType.service.IBaseTypeGroupService;
import com.mb.baseType.service.IBaseTypeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.UUID;

@Service
public class BaseTypeServiceImpl implements IBaseTypeService {

    @Resource
    private BaseTypeDao baseTypeDao;

    @Autowired
    private IBaseTypeGroupService baseTypeGroupService;

    @Override
    public boolean save(BaseType entity) {
        boolean rtn = false;
        entity.setId(UUID.randomUUID().toString().replace("-", ""));
        entity.setCreateDate(new Date());
        Integer len = baseTypeDao.save(entity);
        if (len > 0) {
            rtn = true;
            baseTypeGroupService.refreshBaseTypeGroups();//重新加载一遍数据
        }
        return rtn;
    }

    @Override
    public boolean update(BaseType entity) {
        boolean rtn = false;
        Integer len = baseTypeDao.update(entity);
        if (len > 0) {
            rtn = true;
            baseTypeGroupService.refreshBaseTypeGroups();//重新加载一遍数据
        }
        return rtn;
    }

    @Override
    public boolean deleteById(String id) {
        boolean rtn = false;
        Integer isDel = baseTypeDao.deleteById(id);
        if (isDel > 0) {
            rtn = true;
            baseTypeGroupService.refreshBaseTypeGroups();//重新加载一遍数据
        }
        return rtn;
    }

    @Override
    public BaseType findById(String id) {
        return baseTypeDao.findById(id);
    }

    @Override
    public List<BaseType> getListByParam(Map<String, Object> paramMap) {
        return baseTypeDao.getListByParam(paramMap);
    }

    @Override
    public boolean deleteByParam(Map<String, Object> paramMap) {
        boolean rtn = false;
        Integer isDel = baseTypeDao.deleteByParam(paramMap);
        if (isDel > 0) {
            rtn = true;
        }
        return rtn;
    }
    
}
