package com.example.entity;

import java.io.Serializable;

public class Checkin implements Serializable {
    private static final long serialVersionUID = 1L;

    /** ID */
    private Integer id;
    /** 订单ID */
    private String orderId;
    /** 分类ID */
    private Integer typeId;
    /** 民宿ID */
    private Integer HomestayId;
    /** 用户ID */
    private Integer userId;
    /** 房型ID */
    private Integer roomId;
    /** 入住时间 */
    private String inTime;
    /** 离开时间 */
    private String outTime;
    /*预退房时间*/
    private String predictedOutTime;
    /** 房间类型 */
    private String typeName;
    /** 所属民宿 */
    private String homestayName;
    /** 用户昵称 */
    private String userName;
    /** 客房编号 */
    private String roomName;

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

    public Integer getRoomId() {
        return roomId;
    }

    public void setRoomId(Integer roomId) {
        this.roomId = roomId;
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

    public String getRoomName() {
        return roomName;
    }

    public void setRoomName(String roomName) {
        this.roomName = roomName;
    }

    public String getPredictedOutTime() {
        return predictedOutTime;
    }

    public void setPredictedOutTime(String predictedOutTime) {
        this.predictedOutTime = predictedOutTime;
    }
}