package com.mb.baseType.entity;

import java.io.Serializable;
import java.util.Date;

/**
 * 数据字典---组类型表
 */
public class BaseTypeGroup implements Serializable {

    private static final long serialVersionUID = 4033398248796873852L;

    private String id;// 主键

    private String typeGroupCode;// code

    private String typeGroupName;// 名称

    private Date createDate;// 创建时间

    private String createName;// 创建人

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getTypeGroupCode() {
        return typeGroupCode;
    }

    public void setTypeGroupCode(String typeGroupCode) {
        this.typeGroupCode = typeGroupCode;
    }

    public String getTypeGroupName() {
        return typeGroupName;
    }

    public void setTypeGroupName(String typeGroupName) {
        this.typeGroupName = typeGroupName;
    }

    public Date getCreateDate() {
        return createDate;
    }

    public void setCreateDate(Date createDate) {
        this.createDate = createDate;
    }

    public String getCreateName() {
        return createName;
    }

    public void setCreateName(String createName) {
        this.createName = createName;
    }
}
