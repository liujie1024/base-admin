package com.mb.listener;

import com.mb.baseType.entity.BaseType;
import com.mb.baseType.entity.BaseTypeGroup;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 项目参数工具类
 */
public class ResourceUtil {

    /**
     * 缓存字段分组【缓存】
     */
    public static Map<String, BaseTypeGroup> allTypeGroups = new HashMap<String, BaseTypeGroup>();

    /**
     * 缓存字典【缓存】
     */
    public static Map<String, List<BaseType>> allTypes = new HashMap<String, List<BaseType>>();

}
