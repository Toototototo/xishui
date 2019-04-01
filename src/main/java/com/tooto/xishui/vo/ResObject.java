package com.tooto.xishui.vo;

import io.swagger.annotations.SwaggerDefinition;
import org.springframework.util.StringUtils;

public class ResObject {
    public ResObject(int statusCode, String message) {
        this.success = false;
        this.statusCode = statusCode;
        this.message = message;
    }

    public ResObject(String message) {
        this.message = message;
        this.success = false;
    }

    private int statusCode;

    /*
     * 请求是否成功
     * */
    private Boolean success;

    /*
     * 请求返回消息
     * */
    private String message;

    /*
     * 请求返回数据
     * */
    private Object data;

    public int getStatusCode() {
        return statusCode;
    }

    public void setStatusCode(int statusCode) {
        this.statusCode = statusCode;
    }

    public Boolean getSuccess() {
        return success;
    }

    public void setSuccess(Boolean success) {
        this.success = success;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        if (!StringUtils.isEmpty(message)) {
            this.success = false;
        }
        this.message = message;
    }

    public Object getData() {
        return data;
    }

    public void setData(Object data) {
        this.data = data;
    }
}
