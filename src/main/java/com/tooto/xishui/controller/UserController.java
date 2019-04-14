package com.tooto.xishui.controller;

import com.tooto.xishui.config.exception.BusinessException;
import com.tooto.xishui.entity.common.ResponseData;
import io.swagger.annotations.Api;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

/**
 * @author Tooto
 * @since 2019/4/14 11:48
 */
@Controller
@RequestMapping("/user")
@Api("user-controller")
public class UserController {

    @RequestMapping("/get")
    public ResponseData get(@RequestParam Integer param) {
        if (param == 1) {
            throw new BusinessException("ceshi");
        }
        return new ResponseData();
    }
}
