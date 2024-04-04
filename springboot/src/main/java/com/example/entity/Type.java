package com.example.entity;

import java.io.Serializable;

public class Type implements Serializable {
    private static final long serialVersionUID = 1L;

    /** ID */
    private Integer id;
    /** 分类名称 */
    private String name;
    /** 分类描述 */
    private String description;
    /** 房间价格 */
    private Double price;
    /** 剩余间数 */
    private Integer num;
    /** 民宿ID */
    private Integer homestayId;
    /** 房间图片 */
    private String img;
    /** 民宿名称 */
    private String homestayName;
    /**
     * 房型评论数
     */
    private Integer typeComNum;
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

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        this.price = price;
    }

    public Integer getNum() {
        return num;
    }

    public void setNum(Integer num) {
        this.num = num;
    }

    public Integer getHomestayId() {
        return homestayId;
    }

    public void setHomestayId(Integer homestayId) {
        this.homestayId = homestayId;
    }

    public String getImg() {
        return img;
    }

    public void setImg(String img) {
        this.img = img;
    }

    public String getHomestayName() {
        return homestayName;
    }

    public void setHomestayName(String homestayName) {
        this.homestayName = homestayName;
    }

    public Integer getTypeComNum() {
        return typeComNum;
    }

    public void setTypeComNum(Integer typeComNum) {
        this.typeComNum = typeComNum;
    }
}