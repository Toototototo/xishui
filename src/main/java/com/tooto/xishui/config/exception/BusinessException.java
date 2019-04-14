package com.tooto.xishui.config.exception;

import com.tooto.xishui.config.HttpStatusEnum;
import org.jetbrains.annotations.NotNull;

/**
 * @author Tooto
 * @className BusinessException
 * @Description 自定义业务异常类
 * @since 2019/4/14 15:22
 */
public class BusinessException extends RuntimeException {

    /**
     * 状态码
     */
    private Integer status;

    /**
     * 异常信息
     */
    private String message;

    public BusinessException(String message) {
        super(message);
        this.status = HttpStatusEnum.REQUEST_EXCEPTION.getStatus();
        this.message = message;
    }

    public BusinessException(@NotNull HttpStatusEnum status, String message) {
        super(message);
        this.status = status.getStatus();
        this.message = message;
    }

    public BusinessException(@NotNull HttpStatusEnum status, String message, Throwable e) {
        super(message, e);
        this.message = message;
        this.status = status.getStatus();
    }

    public Integer getStatus() {
        return status;
    }

    public BusinessException setStatus(Integer status) {
        this.status = status;
        return this;
    }

    @Override
    public String getMessage() {
        return message;
    }

    public BusinessException setMessage(String message) {
        this.message = message;
        return this;
    }
}
