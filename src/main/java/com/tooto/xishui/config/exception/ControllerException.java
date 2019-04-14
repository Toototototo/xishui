package com.tooto.xishui.config.exception;

import com.tooto.xishui.config.HttpStatusEnum;
import com.tooto.xishui.entity.common.ResponseData;
import com.tooto.xishui.utils.log.LogUtil;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * @author Tooto
 * @className ControllerException
 * @Description 捕获controller抛出的异常
 * @since 2019/4/14 15:31
 */
@ControllerAdvice
public class ControllerException {
    /**
     * 异常捕捉处理，捕获全局@RequestMapping抛出的异常
     * 
     * @param ex
     * @return ResponseData
     */
    @ResponseBody
    @ExceptionHandler(value = Exception.class)
    public ResponseData errorHandler(Exception ex) {
        LogUtil.error(ex.getMessage(), ex);
        if (ex instanceof BusinessException) {
            BusinessException e = (BusinessException)ex;
            return new ResponseData().setSuccess(false).setMessage(e.getMessage()).setStatusCode(e.getStatus());
        }
        ex.printStackTrace();
        return new ResponseData().setSuccess(false).setMessage(ex.getMessage())
            .setStatusCode(HttpStatusEnum.SERVER_EXCEPTION.getStatus());
    }
}
