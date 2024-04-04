package com.example.utils;

import cn.hutool.core.date.DateUtil;
import cn.hutool.core.util.ObjectUtil;
import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.example.common.Constants;
import com.example.common.enums.RoleEnum;
import com.example.common.enums.StatusEnum;
import com.example.entity.Account;
import com.example.entity.Homestay;
import com.example.service.AdminService;
import com.example.service.HomestayService;
import com.example.service.UserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.annotation.PostConstruct;
import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.util.Date;

/**
 * StatusCheck工具类
 */
@Component
public class StatusCheckUtils {

    private static final Logger log = LoggerFactory.getLogger(StatusCheckUtils.class);

    private static HomestayService staticHomestayService;

    @Resource
    HomestayService homestayService;


    @PostConstruct
    public void setUserService() {
        staticHomestayService = homestayService;
    }

    /**
     * 校验民宿方是否审核通过
     */
    public static boolean isAudit(){
        Account currentUser = TokenUtils.getCurrentUser();
        if (RoleEnum.HOMESTAY.name().equals(currentUser.getRole())) {
            Homestay homestay = staticHomestayService.selectById(currentUser.getId());
            return StatusEnum.CHECKING_OK.status.equals(homestay.getStatus());
        }
        return false;
    }
    
}

