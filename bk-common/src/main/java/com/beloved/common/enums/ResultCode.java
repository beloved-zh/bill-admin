package com.beloved.common.enums;

/**
 * 状态码枚举类
 *
 * @author beloved
 */
public enum ResultCode {
    SUCCESS(200, "操作成功"),

    // =============== 4xx ===============
    BAD_REQUEST(400, "请求错误"),
    UNAUTHORIZED(401, "认证失败，无法访问系统资源"),
    FORBIDDEN(403, "服务器拒绝请求"),
    NOT_FOUND(404, "请求资源不存在"),
    METHOD_NOT_ALLOWED(405, "请求方法不合法"),
    REQUEST_TIMEOUT(408, "请求超时"),
    UNSUPPORTED_MEDIA_TYPE(415, "不支持此媒体类型"),

    // =============== 5xx ===============
    ERROR(500, "服务器内部错误"),


    TEST(600, "测试");

    private Integer code;
    private String message;


    ResultCode(Integer code, String message) {
        this.code = code;
        this.message = message;
    }

    public Integer getCode() {
        return code;
    }

    public String getMessage() {
        return message;
    }
}
