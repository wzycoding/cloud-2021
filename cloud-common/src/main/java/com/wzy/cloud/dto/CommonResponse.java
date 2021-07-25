package com.wzy.cloud.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * 统一响应结果
 *
 * @author wzy
 * @version 1.0
 * @date 2021/7/15 3:53 下午
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class CommonResponse<T> {

    private int code;

    private String msg;

    private T data;

    public CommonResponse(int code, String msg) {
        this.code = code;
        this.msg = msg;
    }

    public static <T> CommonResponse<T> badRequest(int code, String errorMsg) {
        return new CommonResponse<>(code, errorMsg);
    }

    public static <T> CommonResponse<T> success() {
        return new CommonResponse<>(200, "");
    }

    public static <T> CommonResponse<T> success(T data) {
        return new CommonResponse<>(200, "", data);
    }
}