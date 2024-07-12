package com.example.controller;

import cn.hutool.core.util.ObjectUtil;
import cn.hutool.core.util.StrUtil;
import com.example.common.Result;
import com.example.common.enums.ResultCodeEnum;
import com.example.common.enums.RoleEnum;
import com.example.entity.Account;
import com.example.exception.CustomException;
import com.example.service.AdminService;
import com.example.service.HomestayService;
import com.example.service.UserService;
import com.example.utils.ValidateCodeCache;
import com.example.utils.ValidateCodeProperties;
import com.wf.captcha.GifCaptcha;
import com.wf.captcha.SpecCaptcha;
import com.wf.captcha.base.Captcha;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * 基础前端接口
 */
@RestController
public class WebController {

    @Resource
    private AdminService adminService;

    @Resource
    private HomestayService homestayService;

    @Resource
    private UserService userService;

    @GetMapping("/")
    public Result hello() {
        return Result.success("访问成功");
    }

    @GetMapping("/getValidateCode")
    public void getValidateCode(HttpServletRequest request, HttpServletResponse response){
        // 生成验证码
        ValidateCodeProperties code = new ValidateCodeProperties();
        setHeader(response,code.getType());
        Captcha captcha = createCaptcha(code);
        // 存储验证码到缓存
       ValidateCodeCache.setCache(request.getParameter("key"),captcha.text().toLowerCase());
        try {
            ServletOutputStream outputStream = response.getOutputStream();
            captcha.out(outputStream);
            outputStream.flush();
            outputStream.close();
        } catch (IOException e){
            e.printStackTrace();
            throw new CustomException(ResultCodeEnum.VALIDATE_CODE_ERROR);
        }
    }
    
    /**
     * 设置验证码返回头
     */
    private void setHeader(HttpServletResponse response,String type){
        if ("gif".equalsIgnoreCase(type)){
            response.setContentType(MediaType.IMAGE_GIF_VALUE);
        }else {
            response.setContentType(MediaType.IMAGE_PNG_VALUE);
        }
        response.setHeader(HttpHeaders.PRAGMA,"No-cache");
        response.setHeader(HttpHeaders.CACHE_CONTROL,"no-cache");
        response.setDateHeader(HttpHeaders.EXPIRES,0L);
    }

    /**
     * 创建验证码
     */
    private Captcha createCaptcha(ValidateCodeProperties code) {
        Captcha captcha;
        if ("gif".equalsIgnoreCase(code.getType())) {
            captcha = new GifCaptcha(code.getWidth(), code.getHeight(), code.getLength());
        } else {
            captcha = new SpecCaptcha(code.getWidth(), code.getHeight(), code.getLength());
        }
        captcha.setCharType(code.getCharType());
        return captcha;

    }
    /**
     * 登录
     */
    @PostMapping("/login")
    public Result login(@RequestBody Account account) {
        if (ObjectUtil.isEmpty(account.getUsername()) || ObjectUtil.isEmpty(account.getPassword())
                || ObjectUtil.isEmpty(account.getRole()) || ObjectUtil.isEmpty(account.getKey())
                || ObjectUtil.isEmpty(account.getCode())) {
            return Result.error(ResultCodeEnum.PARAM_LOST_ERROR);
        }

        // 校验验证码
        boolean result = ValidateCodeCache.validateCode(account.getKey(), account.getCode());
        //code不存在
        if(!result){
            return Result.error(ResultCodeEnum.VALIDATE_CODE_ERROR);
        }

        if (RoleEnum.ADMIN.name().equals(account.getRole())) {
            account = adminService.login(account);
        }
        else if (RoleEnum.HOMESTAY.name().equals(account.getRole())) {
            account = homestayService.login(account);
        }
        else if (RoleEnum.USER.name().equals(account.getRole())){
            account = userService.login(account);
        } else {
            return Result.error(ResultCodeEnum.USER_NOT_EXIST_ERROR);
        }
        return Result.success(account);
    }

    /**
     * 注册
     */
    @PostMapping("/register")
    public Result register(@RequestBody Account account) {
        if (StrUtil.isBlank(account.getUsername()) || StrUtil.isBlank(account.getPassword())
                || ObjectUtil.isEmpty(account.getRole())) {
            return Result.error(ResultCodeEnum.PARAM_LOST_ERROR);
        }
        if (RoleEnum.ADMIN.name().equals(account.getRole())) {
            adminService.register(account);
        }
        else if (RoleEnum.HOMESTAY.name().equals(account.getRole())) {
            homestayService.register(account);
        }
        else if (RoleEnum.USER.name().equals(account.getRole())) {
            userService.register(account);
        }
        return Result.success();
    }

    /**
     * 修改密码
     */
    @PutMapping("/updatePassword")
    public Result updatePassword(@RequestBody Account account) {
        if (StrUtil.isBlank(account.getUsername()) || StrUtil.isBlank(account.getPassword())
                || ObjectUtil.isEmpty(account.getNewPassword())) {
            return Result.error(ResultCodeEnum.PARAM_LOST_ERROR);
        }
        if (RoleEnum.ADMIN.name().equals(account.getRole())) {
            adminService.updatePassword(account);
        }
        else if (RoleEnum.HOMESTAY.name().equals(account.getRole())) {
            homestayService.updatePassword(account);
        }
        else if (RoleEnum.USER.name().equals(account.getRole())) {
            userService.updatePassword(account);
        }else {
            return Result.error(ResultCodeEnum.USER_NOT_EXIST_ERROR);
        }
        return Result.success();
    }
}
