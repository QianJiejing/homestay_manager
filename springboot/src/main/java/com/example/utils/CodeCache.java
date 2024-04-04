package com.example.utils;

/**
 * 验证码缓存实体类
 */
public class CodeCache {
    /**
     * 前端传来的 uuid，唯一标识
     */
    private String key;
    /**
     * 验证码
     */
    private String code;
    /**
     * 时间戳
     */
    private Long timestamp;

    public String getKey() {
        return key;
    }

    public void setKey(String key) {
        this.key = key;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public Long getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(Long timestamp) {
        this.timestamp = timestamp;
    }

    @Override
    public String toString() {
        return "CodeCache{" +
                "key='" + key + '\'' +
                ", code='" + code + '\'' +
                ", timestamp=" + timestamp +
                '}';
    }
}
