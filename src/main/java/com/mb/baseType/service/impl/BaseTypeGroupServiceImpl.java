package com.mb.baseType.service.impl;

import com.mb.baseType.dao.BaseTypeGroupDao;
import com.mb.baseType.entity.BaseType;
import com.mb.baseType.entity.BaseTypeGroup;
import com.mb.baseType.service.IBaseTypeGroupService;
import com.mb.baseType.service.IBaseTypeService;
import com.mb.listener.ResourceUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.*;

@Service
public class BaseTypeGroupServiceImpl implements IBaseTypeGroupService {

    @Resource
    private BaseTypeGroupDao baseTypeGroupDao;

    @Autowired
    private IBaseTypeService baseTypeService;

    @Override
    public boolean save(BaseTypeGroup entity) {
        boolean rtn = false;
        entity.setId(UUID.randomUUID().toString().replace("-", ""));
        entity.setCreateDate(new Date());
        Integer len = baseTypeGroupDao.save(entity);
        if (len > 0) {
            rtn = true;
        }
        this.refreshBaseTypeGroups();//重新加载一遍数据
        return rtn;
    }

    @Override
    public boolean update(BaseTypeGroup entity) {
        boolean rtn = false;
        Integer len = baseTypeGroupDao.update(entity);
        if (len > 0) {
            rtn = true;
        }
        this.refreshBaseTypeGroups();//重新加载一遍数据
        return rtn;
    }

    @Override
    public boolean deleteById(String id) {
        boolean rtn = false;
        //删除数据字典数据
        Integer isDel = baseTypeGroupDao.deleteById(id);
        //删除字典分类数据
        Map<String, Object> paramMap = new HashMap<String, Object>();
        paramMap.put("typeGroupId", id);
        baseTypeService.deleteByParam(paramMap);
        //**********************************************************
        if (isDel > 0) {
            rtn = true;
        }
        this.refreshBaseTypeGroups();//重新加载一遍数据
        return rtn;
    }

    @Override
    public BaseTypeGroup findById(String id) {
        return baseTypeGroupDao.findById(id);
    }

    @Override
    public List<BaseTypeGroup> getListByParam(Map<String, Object> paramMap) {
        return baseTypeGroupDao.getListByParam(paramMap);
    }

    @Override
    public void initAllBaseTypeGroups() {
        Map<String, Object> paramMap = new HashMap<>();
        List<BaseTypeGroup> typeGroupList = baseTypeGroupDao.getListByParam(paramMap);//获取所有数据字典数据
        List<BaseType> typeList = baseTypeService.getListByParam(paramMap);//获取所有字典分类数据
        for (BaseTypeGroup typeGroup : typeGroupList) {
            //****数据字典**************************************************************************
            ResourceUtil.allTypeGroups.put(typeGroup.getTypeGroupCode(), typeGroup);
            //****字典分类**************************************************************************
            List<BaseType> tempTypeList = new ArrayList<>();
            for (BaseType type : typeList) {
                if (type.getTypeGroupId().equals(typeGroup.getId())) {
                    tempTypeList.add(type);
                }
            }
            ResourceUtil.allTypes.put(typeGroup.getTypeGroupCode(), tempTypeList);
        }
    }

    @Override
    public void refreshBaseTypeGroups() {
        ResourceUtil.allTypeGroups.clear();
        ResourceUtil.allTypes.clear();
        this.initAllBaseTypeGroups();
    }

}
