package com.mb.broadcast.entity;

import com.fasterxml.jackson.annotation.JsonFormat;
import org.springframework.format.annotation.DateTimeFormat;

import java.io.Serializable;
import java.util.Date;

/**
 * 直播录入数据
 */
public class BroadcastEntity implements Serializable {

    private static final long serialVersionUID = -4069461286988555402L;

    private String id;//主键

    private String progress;//进度

    private String region;//区域

    private String company;//单位

    private String broadcastPlatform;//直播平台

    private String broadcastOnlineshop;//网店名称

    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date broadcastDate;//直播日期

    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date broadcastBeginTime;//直播开始时间

    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date broadcastEndTime;//直播结束时间

    private String broadcastNickname;//主播昵称

    private String broadcastId;//主播ID

    private String broadcastNums;//主播粉丝数

    private String broadcastCount;//直播室人数

    private String estimateSales;//预计销售

    private String commission;//佣金

    private String mcnOrg;//MCN机构

    private String orderPlatform;//订单平台

    private String broadcastShopName;//直播门店名称

    private String broadcastShopId;//门店ID

    private String projectLeader;//项目负责人+招商

    private String projectOperation;//项目运营+策划+场控

    private String projectGoods;//项目商品

    private String projectPrice;//总部商品核款核价

    private String projectHead;//总部平台营运

    private String salesGross;//毛销售

    private String salesNet;//净销售

    private String returnRate;//退货率

    private String score;//综合评分

    private Date createDate;//创建日期

    private Date updateDate;//更新日期

    private String changePrice;//是否支持现场改价

    private String note1;//备注1

    private String note2;//备注2

    private String note3;//备注3

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getProgress() {
        return progress;
    }

    public void setProgress(String progress) {
        this.progress = progress;
    }

    public String getRegion() {
        return region;
    }

    public void setRegion(String region) {
        this.region = region;
    }

    public String getCompany() {
        return company;
    }

    public void setCompany(String company) {
        this.company = company;
    }

    public String getBroadcastPlatform() {
        return broadcastPlatform;
    }

    public void setBroadcastPlatform(String broadcastPlatform) {
        this.broadcastPlatform = broadcastPlatform;
    }

    @JsonFormat(pattern = "yyyy-MM-dd", timezone = "GMT+8")
    public Date getBroadcastDate() {
        return broadcastDate;
    }

    public void setBroadcastDate(Date broadcastDate) {
        this.broadcastDate = broadcastDate;
    }

    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    public Date getBroadcastBeginTime() {
        return broadcastBeginTime;
    }

    public void setBroadcastBeginTime(Date broadcastBeginTime) {
        this.broadcastBeginTime = broadcastBeginTime;
    }

    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    public Date getBroadcastEndTime() {
        return broadcastEndTime;
    }

    public void setBroadcastEndTime(Date broadcastEndTime) {
        this.broadcastEndTime = broadcastEndTime;
    }

    public Date getCreateDate() {
        return createDate;
    }

    public void setCreateDate(Date createDate) {
        this.createDate = createDate;
    }

    public Date getUpdateDate() {
        return updateDate;
    }

    public void setUpdateDate(Date updateDate) {
        this.updateDate = updateDate;
    }

    public String getBroadcastNickname() {
        return broadcastNickname;
    }

    public void setBroadcastNickname(String broadcastNickname) {
        this.broadcastNickname = broadcastNickname;
    }

    public String getBroadcastId() {
        return broadcastId;
    }

    public void setBroadcastId(String broadcastId) {
        this.broadcastId = broadcastId;
    }

    public String getBroadcastNums() {
        return broadcastNums;
    }

    public void setBroadcastNums(String broadcastNums) {
        this.broadcastNums = broadcastNums;
    }

    public String getEstimateSales() {
        return estimateSales;
    }

    public void setEstimateSales(String estimateSales) {
        this.estimateSales = estimateSales;
    }

    public String getCommission() {
        return commission;
    }

    public void setCommission(String commission) {
        this.commission = commission;
    }

    public String getMcnOrg() {
        return mcnOrg;
    }

    public void setMcnOrg(String mcnOrg) {
        this.mcnOrg = mcnOrg;
    }

    public String getOrderPlatform() {
        return orderPlatform;
    }

    public void setOrderPlatform(String orderPlatform) {
        this.orderPlatform = orderPlatform;
    }

    public String getBroadcastShopName() {
        return broadcastShopName;
    }

    public void setBroadcastShopName(String broadcastShopName) {
        this.broadcastShopName = broadcastShopName;
    }

    public String getBroadcastShopId() {
        return broadcastShopId;
    }

    public void setBroadcastShopId(String broadcastShopId) {
        this.broadcastShopId = broadcastShopId;
    }

    public String getProjectLeader() {
        return projectLeader;
    }

    public void setProjectLeader(String projectLeader) {
        this.projectLeader = projectLeader;
    }

    public String getProjectOperation() {
        return projectOperation;
    }

    public void setProjectOperation(String projectOperation) {
        this.projectOperation = projectOperation;
    }

    public String getProjectGoods() {
        return projectGoods;
    }

    public void setProjectGoods(String projectGoods) {
        this.projectGoods = projectGoods;
    }

    public String getProjectPrice() {
        return projectPrice;
    }

    public void setProjectPrice(String projectPrice) {
        this.projectPrice = projectPrice;
    }

    public String getProjectHead() {
        return projectHead;
    }

    public void setProjectHead(String projectHead) {
        this.projectHead = projectHead;
    }

    public String getBroadcastCount() {
        return broadcastCount;
    }

    public void setBroadcastCount(String broadcastCount) {
        this.broadcastCount = broadcastCount;
    }

    public String getSalesGross() {
        return salesGross;
    }

    public void setSalesGross(String salesGross) {
        this.salesGross = salesGross;
    }

    public String getSalesNet() {
        return salesNet;
    }

    public void setSalesNet(String salesNet) {
        this.salesNet = salesNet;
    }

    public String getReturnRate() {
        return returnRate;
    }

    public void setReturnRate(String returnRate) {
        this.returnRate = returnRate;
    }

    public String getScore() {
        return score;
    }

    public void setScore(String score) {
        this.score = score;
    }

    public String getNote1() {
        return note1;
    }

    public void setNote1(String note1) {
        this.note1 = note1;
    }

    public String getNote2() {
        return note2;
    }

    public void setNote2(String note2) {
        this.note2 = note2;
    }

    public String getNote3() {
        return note3;
    }

    public void setNote3(String note3) {
        this.note3 = note3;
    }

    public String getBroadcastOnlineshop() {
        return broadcastOnlineshop;
    }

    public void setBroadcastOnlineshop(String broadcastOnlineshop) {
        this.broadcastOnlineshop = broadcastOnlineshop;
    }

    public String getChangePrice() {
        return changePrice;
    }

    public void setChangePrice(String changePrice) {
        this.changePrice = changePrice;
    }

    //****临时字段，不和数据库字段对应，开始********************************************************
    private String broadcastDateStr;//直播日期

    private String broadcastBeginTimeStr;//直播开始时间

    private String broadcastEndTimeStr;//直播结束时间

    public String getBroadcastDateStr() {
        return broadcastDateStr;
    }

    public void setBroadcastDateStr(String broadcastDateStr) {
        this.broadcastDateStr = broadcastDateStr;
    }

    public String getBroadcastBeginTimeStr() {
        return broadcastBeginTimeStr;
    }

    public void setBroadcastBeginTimeStr(String broadcastBeginTimeStr) {
        this.broadcastBeginTimeStr = broadcastBeginTimeStr;
    }

    public String getBroadcastEndTimeStr() {
        return broadcastEndTimeStr;
    }

    public void setBroadcastEndTimeStr(String broadcastEndTimeStr) {
        this.broadcastEndTimeStr = broadcastEndTimeStr;
    }
//****临时字段，不和数据库字段对应，结束********************************************************
}
