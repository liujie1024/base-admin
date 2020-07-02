package com.mb.baseType.entity;

import java.io.Serializable;
import java.util.Date;

/**
 * 数据字典---类型对应值表
 */
public class BaseType implements Serializable {

    private static final long serialVersionUID = -1268859724054274301L;

    private String id;// 主键

    private String typeCode;// code

    private String typeName;// 名称

    private String typeGroupId;// 组类型id

    private Date createDate;// 创建时间

    private String createName;// 创建人

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getTypeCode() {
        return typeCode;
    }

    public void setTypeCode(String typeCode) {
        this.typeCode = typeCode;
    }

    public String getTypeName() {
        return typeName;
    }

    public void setTypeName(String typeName) {
        this.typeName = typeName;
    }

    public String getTypeGroupId() {
        return typeGroupId;
    }

    public void setTypeGroupId(String typeGroupId) {
        this.typeGroupId = typeGroupId;
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
