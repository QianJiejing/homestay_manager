package com.example.common;

public interface Constants {

    String TOKEN = "token";
    // Token 过期时间，单位为毫秒
    int TOKEN_EXPIRATION_TIME = 2 * 60; // 120分钟，即2小时
    //图片上传路径
    String imageUploadEndpoint =  "http://localhost:9090/HomestayAPI/files/";

    String USER_DEFAULT_PASSWORD = "123456";

    String USER_DEFAULT_AVATAR = "http://localhost:9090/HomestayAPI/files/1611365333853-default.png";

    String ADMIN_DEFAULT_AVATAR = "http://localhost:9090/HomestayAPI/files/1697438073596-avatar.png";

    String HOMESTAY_DEFAULT_AVATAR = "暂未上传图片";

    String USER_DEFAULT_NAME = "默认用户";

    String ADMIN_DEFAULT_NAME = "管理员";

    String HOMESTAY_DEFAULT_NAME = "默认民宿";

}
