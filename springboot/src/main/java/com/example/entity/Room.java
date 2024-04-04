package com.example.entity;

import java.io.Serializable;

/**
 * 客房信息表
*/
public class Room implements Serializable {
    private static final long serialVersionUID = 1L;

    /** ID */
    private Integer id;
    /** 房间编号 */
    private String name;
    /** 房间状态 */
    private String status;
    /** 分类ID */
    private Integer typeId;
    /**民宿ID */
    private Integer HomestayId;
    /** 所属分类 */
    private String typeName;
    /** 所属民宿 */
    private String homestayName;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
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
}