package com.example.service;

import cn.hutool.core.date.DateUtil;
import cn.hutool.core.util.ObjectUtil;
import com.example.common.enums.OrdersEnum;
import com.example.common.enums.ResultCodeEnum;
import com.example.common.enums.RoleEnum;
import com.example.common.enums.RoomEnum;
import com.example.entity.Account;
import com.example.entity.Checkin;
import com.example.entity.Orders;
import com.example.entity.Room;
import com.example.entity.Type;
import com.example.exception.CustomException;
import com.example.mapper.CheckinMapper;
import com.example.mapper.OrdersMapper;
import com.example.mapper.RoomMapper;
import com.example.mapper.TypeMapper;
import com.example.utils.StatusCheckUtils;
import com.example.utils.TokenUtils;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.Date;
import java.util.List;

@Service
public class CheckinService {

    @Resource
    private CheckinMapper checkinMapper;
    @Resource
    private OrdersMapper ordersMapper;
    @Resource
    private RoomMapper roomMapper;
    @Resource
    private TypeMapper typeMapper;

    /**
     * 新增
     */
    @Transactional
    public void add(Checkin checkin) {
        if(StatusCheckUtils.isAudit()){

            if(ObjectUtil.isEmpty(checkin.getInTime())){
                throw new CustomException(ResultCodeEnum.PARAM_LOST_ERROR);
            }

            String orderId = checkin.getOrderId();
            Orders orders = ordersMapper.selectByOrderId(orderId);

            checkin.setUserId(orders.getUserId());
            checkin.setHomestayId(orders.getHomestayId());
            checkin.setTypeId(orders.getTypeId());
            checkinMapper.insert(checkin);

            // 1. 对应的客房状态变成 占用
            Room room = roomMapper.selectById(checkin.getRoomId());
            room.setStatus(RoomEnum.STATUS_NO.status);
            roomMapper.updateById(room);

            // 2. 对应的客房分类剩余间数 要减 1
            Type type = typeMapper.selectById(room.getTypeId());
            type.setNum(type.getNum() - 1);
            typeMapper.updateById(type);

            // 3. 对应的订单状态 要变成 已入住
            orders.setStatus(OrdersEnum.STATUS_IN.status);
            ordersMapper.updateById(orders);

        }else {
            throw new CustomException(ResultCodeEnum.STATUS_CHECK_ERROR);
        }
/*        if(StatusCheckUtils.isAudit()){

        }else {
            throw new CustomException(ResultCodeEnum.STATUS_CHECK_ERROR);
        }*/

    }


    /**
     * 删除
     */
    public void deleteById(Integer id) {
        checkinMapper.deleteById(id);
    }

    /**
     * 批量删除
     */
    public void deleteBatch(List<Integer> ids) {
        for (Integer id : ids) {
            checkinMapper.deleteById(id);
        }
    }

    /**
     * 修改
     */
    public void updateById(Checkin checkin) {
        checkinMapper.updateById(checkin);
    }

    /**
     * 根据ID查询
     */
    public Checkin selectById(Integer id) {
        return checkinMapper.selectById(id);
    }

    /**
     * 查询所有
     */
    public List<Checkin> selectAll(Checkin checkin) {
        return checkinMapper.selectAll(checkin);
    }

    /**
     * 分页查询
     */
    public PageInfo<Checkin> selectPage(Checkin checkin, Integer pageNum, Integer pageSize) {
        Account currentUser = TokenUtils.getCurrentUser();
        if (RoleEnum.HOMESTAY.name().equals(currentUser.getRole())) {
            checkin.setHomestayId(currentUser.getId());
        }
        if (RoleEnum.USER.name().equals(currentUser.getRole())) {
            checkin.setUserId(currentUser.getId());
        }
        PageHelper.startPage(pageNum, pageSize);
        List<Checkin> list = checkinMapper.selectAll(checkin);
        return PageInfo.of(list);
    }

    public List<Checkin> selectByUserId(Integer id) {
        Checkin checkin = new Checkin();
        checkin.setUserId(id);
        return checkinMapper.selectAll(checkin);
    }
    @Transactional
    public void tuifang(Integer id) {

        Checkin checkin = checkinMapper.selectById(id);

        Orders order = ordersMapper.selectByOrderId(checkin.getOrderId());

         String predictedOutTime = order.getOutTime();
        // 获取当前时间
        Date now = new Date();

       // 将当前时间和预计离开时间进行比较
        if (now.before(DateUtil.parse(predictedOutTime))) {
            // 如果当前时间早于预计离开时间，抛出异常
            throw new CustomException(ResultCodeEnum.TIME_BEFORE_ERROR);
        }
        checkin.setOutTime(DateUtil.format(new Date(), "yyyy-MM-dd"));

        checkinMapper.updateById(checkin);

        // 1. 该客房状态需要还原为 空闲
        Room room = roomMapper.selectById(checkin.getRoomId());
        room.setStatus(RoomEnum.STATUS_OK.status);
        roomMapper.updateById(room);

        // 2. 该客房对应的房型剩余房间数需要还原（+1）
        Type type = typeMapper.selectById(checkin.getTypeId());
        type.setNum(type.getNum() + 1);
        typeMapper.updateById(type);

        // 3. 对应的订单状态 要变成 已退房
        Orders orders = ordersMapper.selectByOrderId(checkin.getOrderId());
        orders.setStatus(OrdersEnum.STATUS_OUT.status);
        ordersMapper.updateById(orders);

    }
}