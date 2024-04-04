package com.example.common.enums;

public enum ResultCodeEnum {
    SUCCESS("200", "成功"),

    PARAM_ERROR("400", "参数异常"),
    TOKEN_INVALID_ERROR("401", "您还未登录"),
    TOKEN_CHECK_ERROR("401", "token验证失败，请重新登录"),
    PERMISSION_DENIED_ERROR("403", "您没有此权限"),
    NOTFOUND_ERROR("404","没有找到页面"),
    PARAM_LOST_ERROR("4001", "参数缺失"),
    GET_CURRENT_USER_ERROR("4002", "获取当前用户信息出错"),
    SYSTEM_ERROR("500", "系统异常"),
    USER_EXIST_ERROR("5001", "用户名已存在"),
    USER_NOT_LOGIN("5002", "用户未登录"),
    USER_ACCOUNT_ERROR("5003", "账号或密码错误"),
    USER_NOT_EXIST_ERROR("5004", "用户不存在"),
    PARAM_PASSWORD_ERROR("5005", "原密码输入错误"),
    COLLECTED_ALREADY_ERROR("5006", "您已经收藏过该房间"),
    TIME_CHECK_ERROR("5007", "您选择的时间不合理"),
    STATUS_CHECK_ERROR("5008", "民宿尚未通过审核，相关权限尚未开通"),
    STATUS_CHECK_NO_ERROR("5009", "该民宿还未通过审核，请选择其他民宿"),
    TIME_BEFORE_ERROR("5010", "还没到退房时间"),
    VALIDATE_CODE_ERROR("5011", "验证码错误"),

    ;

    public String code;
    public String msg;

    ResultCodeEnum(String code, String msg) {
        this.code = code;
        this.msg = msg;
    }
}
