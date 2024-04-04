package com.example.entity;

import java.io.Serializable;

public class Orders implements Serializable {
    private static final long serialVersionUID = 1L;

    /** ID */
    private Integer id;
    /** 订单 */
    private String orderId;
    /** 订单状态 */
    private String status;
    /** 分类ID */
    private Integer typeId;
    /** 民宿ID */
    private Integer HomestayId;
    /** 用户ID */
    private Integer userId;
    /** 预订时间 */
    private String time;
    /** 入住时间 */
    private String inTime;
    /** 离开时间 */
    private String outTime;
    /** 入住天数 */
    private Long days;
    /** 订单价格 */
    private Double price;
    /** 房间类型 */
    private String typeName;
    /** 所属民宿 */
    private String homestayName;
    /** 用户昵称 */
    private String userName;
    /** 民宿图片 */
    private String typeImg;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getOrderId() {
        return orderId;
    }

    public void setOrderId(String orderId) {
        this.orderId = orderId;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public Integer getTypeId() {
        return typeId;
    }

    public void setTypeId(Integer typeId) {
        this.typeId = typeId;
    }

    public Integer getHomestayId() {
        return HomestayId;
    }

    public void setHomestayId(Integer homestayId) {
        HomestayId = homestayId;
    }

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public String getInTime() {
        return inTime;
    }

    public void setInTime(String inTime) {
        this.inTime = inTime;
    }

    public String getOutTime() {
        return outTime;
    }

    public void setOutTime(String outTime) {
        this.outTime = outTime;
    }

    public Long getDays() {
        return days;
    }

    public void setDays(Long days) {
        this.days = days;
    }

    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        this.price = price;
    }

    public String getTypeName() {
        return typeName;
    }

    public void setTypeName(String typeName) {
        this.typeName = typeName;
    }

    public String getHomestayName() {
        return homestayName;
    }

    public void setHomestayName(String homestayName) {
        this.homestayName = homestayName;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getTypeImg() {
        return typeImg;
    }

    public void setTypeImg(String typeImg) {
        this.typeImg = typeImg;
    }
}