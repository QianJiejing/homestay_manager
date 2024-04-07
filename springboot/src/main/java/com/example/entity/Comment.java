package com.example.entity;

import java.io.Serializable;
import java.util.List;

/**
 * 评论信息表
*/
public class Comment implements Serializable {
    private static final long serialVersionUID = 1L;

    /**
     * ID
     */
    private Integer id;
    /**
     * 评论内容
     */
    private String content;
    /**
     * 评论时间
     */
    private String time;
    /**
     * 分类ID
     */
    private Integer typeId;
    /**
     * 民宿ID
     */
    private Integer HomestayId;
    private Integer userId;
    private Integer parentId;
    private String role;
    /**
     * 所属分类
     */
    private String typeName;
    /**
     * 所属民宿
     */
    private String homestayName;
    /**
     * 用户昵称
     */
    private String userName;
    /**
     * 头像
     */
    private String avatar;
    // /**
    //  * 回复
    //  */
    // private String reply;
    /**
     * 子节点
     */
    private List<Comment> children;

    /**
     * 删除状态
     */
    private Integer deleted;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
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

    public Integer getParentId() {
        return parentId;
    }

    public void setParentId(Integer parentId) {
        this.parentId = parentId;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
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

    public String getAvatar() {
        return avatar;
    }

    public void setAvatar(String avatar) {
        this.avatar = avatar;
    }

    public List<Comment> getChildren() {
        return children;
    }

    public void setChildren(List<Comment> children) {
        this.children = children;
    }

    public Integer getDeleted() {
        return deleted;
    }

    public void setDeleted(Integer deleted) {
        this.deleted = deleted;
    }
}