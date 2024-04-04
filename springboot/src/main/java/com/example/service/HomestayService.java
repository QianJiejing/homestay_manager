package com.example.service;

import cn.hutool.core.bean.BeanUtil;
import cn.hutool.core.util.ObjectUtil;
import com.example.common.Constants;
import com.example.common.enums.ResultCodeEnum;
import com.example.common.enums.RoleEnum;
import com.example.common.enums.StatusEnum;
import com.example.entity.Account;
import com.example.entity.Homestay;
import com.example.exception.CustomException;
import com.example.mapper.HomestayMapper;
import com.example.utils.TokenUtils;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;


/**
 * 民宿房主业务处理
 **/
@Service
public class HomestayService {
    @Resource
    private HomestayMapper homestayMapper;


    public void add(Homestay homestay) {
        // 1. 做一下重复性校验
        Homestay homestayUser = homestayMapper.selectByUsername(homestay.getUsername());
        if (ObjectUtil.isNotEmpty(homestayUser)) {
            throw new CustomException(ResultCodeEnum.USER_EXIST_ERROR);
        }
        if (ObjectUtil.isEmpty(homestay.getPassword())) {
            homestay.setPassword(Constants.USER_DEFAULT_PASSWORD);
        }
        if (ObjectUtil.isEmpty(homestay.getRole())) {
            homestay.setRole(RoleEnum.HOMESTAY.name());
        }
        if (ObjectUtil.isEmpty(homestay.getAvatar())) {
            homestay.setAvatar(Constants.HOMESTAY_DEFAULT_AVATAR);
        }
        if (ObjectUtil.isEmpty(homestay.getName())) {
            homestay.setName(Constants.HOMESTAY_DEFAULT_NAME);
        }
        homestay.setStatus(StatusEnum.CHECKING.status);
        homestayMapper.insert(homestay);
    }

    public PageInfo<Homestay> selectPage(Homestay homestay, Integer pageNum, Integer pageSize) {
        PageHelper.startPage(pageNum, pageSize);
        List<Homestay> homestays = homestayMapper.selectAll(homestay);
        return PageInfo.of(homestays);
    }

    public void update(Homestay homestay) {
        homestayMapper.updateById(homestay);
    }

    public void deleteById(Integer id) {
        homestayMapper.deleteById(id);
    }

    public void deleteBatch(List<Integer> list) {
        for (Integer id : list) {
            deleteById(id);
        }
    }

    public Account login(Account account) {
        Account dbHomestay = homestayMapper.selectByUsername(account.getUsername());
        if (ObjectUtil.isNull(dbHomestay)) {
            throw new CustomException(ResultCodeEnum.USER_NOT_EXIST_ERROR);
        }
        if (!account.getPassword().equals(dbHomestay.getPassword())) {
            throw new CustomException(ResultCodeEnum.USER_ACCOUNT_ERROR);
        }

        // 生成token
        String tokenData = dbHomestay.getId() + "-" + RoleEnum.HOMESTAY.name();
        String token = TokenUtils.createToken(tokenData, dbHomestay.getPassword());
        dbHomestay.setToken(token);
        return dbHomestay;
    }

    public void register(Account account) {
        Homestay homestay = new Homestay();
        BeanUtil.copyProperties(account,homestay);
        add(homestay);
    }

    public Homestay selectById(Integer id) {
        return homestayMapper.selectById(id);

    }


    /**
     * 修改密码
     */
    public void updatePassword(Account account) {
        Homestay dbHomestay = homestayMapper.selectByUsername(account.getUsername());
        if (ObjectUtil.isNull(dbHomestay)) {
            throw new CustomException(ResultCodeEnum.USER_NOT_EXIST_ERROR);
        }
        if (!account.getPassword().equals(dbHomestay.getPassword())) {
            throw new CustomException(ResultCodeEnum.PARAM_PASSWORD_ERROR);
        }
        dbHomestay.setPassword(account.getNewPassword());
        homestayMapper.updateById(dbHomestay);
    }

    public List<Homestay> selectAll() {
/*        if(StatusCheckUtils.isAudit()){

        }else {
            throw new CustomException(ResultCodeEnum.STATUS_CHECK_ERROR);
        }*/
        return homestayMapper.selectAll(new Homestay());
    }


    public List<Homestay> selectByName(String name) {
        // 创建一个 Homestay 对象用于设置查询条件
        Homestay homestay = new Homestay();
        // 如果 name 不是 null
        if (!"null".equals(name)) {
            // 设置名称模糊查询条件
            if (ObjectUtil.isNotEmpty(name)) {
                homestay.setName(name);
            }
        } else {
            // 当 name 为 null 时，设置状态为审核通过
            homestay.setStatus(StatusEnum.CHECKING_OK.status);
        }

        // 执行查询
        List<Homestay> homestays = homestayMapper.selectAll(homestay);

        // 如果查询结果为空，直接返回空列表
        if (homestays.isEmpty()) {
            return homestays;
        }

        // 对查询结果进行进一步处理，检查状态是否符合要求
        for (Homestay dbhomestay : homestays) {
            if (!dbhomestay.getStatus().equals(StatusEnum.CHECKING_OK.status)) {
                // 如果有状态不符合要求的数据，则抛出异常
                throw new CustomException(ResultCodeEnum.STATUS_CHECK_NO_ERROR);
            }
        }

        // 返回查询结果
        return homestays;
    }



    public List<Homestay> selectAllOK() {
        return homestayMapper.selectAllOK();
    }

}