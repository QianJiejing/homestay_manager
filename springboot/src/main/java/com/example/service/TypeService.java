package com.example.service;

import cn.hutool.core.util.ObjectUtil;
import com.example.common.enums.ResultCodeEnum;
import com.example.common.enums.RoleEnum;
import com.example.entity.Account;
import com.example.entity.Collect;
import com.example.entity.Comment;
import com.example.entity.Orders;
import com.example.entity.dto.RelateDTO;
import com.example.entity.Type;
import com.example.entity.User;
import com.example.exception.CustomException;
import com.example.mapper.CollectMapper;
import com.example.mapper.CommentMapper;
import com.example.mapper.OrdersMapper;
import com.example.mapper.TypeMapper;
import com.example.mapper.UserMapper;
import com.example.utils.StatusCheckUtils;
import com.example.utils.TokenUtils;
import com.example.utils.UserCF;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Objects;
import java.util.Set;
import java.util.stream.Collectors;

/**
 * 房间类别表业务处理
 **/
@Service
public class TypeService {

    @Resource
    private TypeMapper typeMapper;
    @Resource
    private UserMapper userMapper;
    @Resource
    private CollectMapper collectMapper;
    @Resource
    private CommentMapper commentMapper;
    @Resource
    private OrdersMapper ordersMapper;


    /**
     * 新增
     */
    public void add(Type type) {
        if (StatusCheckUtils.isAudit()) {

            Account currentUser = TokenUtils.getCurrentUser();
            if (ObjectUtil.isNull(currentUser)) {
                throw new CustomException(ResultCodeEnum.TOKEN_CHECK_ERROR);
            }
            type.setHomestayId(currentUser.getId());
            typeMapper.insert(type);

        } else {
            throw new CustomException(ResultCodeEnum.STATUS_CHECK_ERROR);
        }

    }

    /**
     * 删除
     */
    public void deleteById(Integer id) {
        typeMapper.deleteById(id);
    }

    /**
     * 批量删除
     */
    public void deleteBatch(List<Integer> ids) {
        for (Integer id : ids) {
            typeMapper.deleteById(id);
        }
    }

    /**
     * 修改
     */
    public void updateById(Type type) {
        typeMapper.updateById(type);
    }

    /**
     * 根据ID查询
     */
    public Type selectById(Integer id) {
        return typeMapper.selectById(id);
    }

    /**
     * 查询所有
     */
    public List<Type> selectAll(Type type) {
        return typeMapper.selectAll(type);
    }

    /**
     * 分页查询
     */
    public PageInfo<Type> selectPage(Type type, Integer pageNum, Integer pageSize) {
        Account currentUser = TokenUtils.getCurrentUser();
        if (ObjectUtil.isNull(currentUser)) {
            throw new CustomException(ResultCodeEnum.TOKEN_CHECK_ERROR);
        }
        if (RoleEnum.HOMESTAY.name().equals(currentUser.getRole())) {
            type.setHomestayId(currentUser.getId());
        }

        PageHelper.startPage(pageNum, pageSize);
        List<Type> list = typeMapper.selectAll(type);
        return PageInfo.of(list);
    }

    public List<Type> selectByHomestayId(Integer homestayId) {

        return typeMapper.selectByHomestayId(homestayId);
    }

    /**
     * 推荐房型业务
     */
    public List<Type> recommend() {

        Account currentUser = TokenUtils.getCurrentUser();

        //用户的什么行为可以认为他和喜欢的房间产生了关系？收藏、预订、评论

        //1. 获取所有的收藏信息
        List<Collect> allCollects = collectMapper.selectAll(null);
        //2. 获取所有的订单信息
        List<Orders> allOrders = ordersMapper.selectAll(null);
        //3. 获取所有的评论信息
        List<Comment> allComments = commentMapper.selectAll(null);
        //4. 获取所有的用户信息
        List<User> allUsers = userMapper.selectAll(null);
        //5. 获取所有的房型信息
        List<Type> allTypes = typeMapper.selectAll(null);

        // 定义每个房间类型和每个用户的关系List
        List<RelateDTO> data = new ArrayList<>();

        // 开始计算每个房间类型和每个用户的关系数据
        for (Type type : allTypes) {
            Integer typeId = type.getId();
            for (User user : allUsers) {
                Integer userId = user.getId();
                int index = 1;
                // 判断该用户是否收藏过、预订过、评论过该房型，分别给予不同权重
                //1. 判断该用户有没有收藏该房型，收藏过的权重给 1
                index += hasInteraction(allCollects, userId, typeId) ? 1 : 0;
                //2. 判断该用户有没有预订过该房型，预订过的权重给 3
                index += hasInteraction(allOrders, userId, typeId) ? 3 : 0;
                //3. 判断该用户有没有评论过该房型，评论过的权重给 2
                index += hasInteraction(allComments, userId, typeId) ? 2 : 0;

                // 当发现有关系时，添加到关系列表中
                if (index > 1) {
                    RelateDTO relateDTO = new RelateDTO(userId, typeId, index);
                    data.add(relateDTO);
                }
            }
        }

        // 使用推荐算法获取推荐的房型列表
        List<Integer> typeIds = UserCF.recommend(currentUser.getId(), data);
        List<Type> recommendedTypes = typeIds.stream()
                .map(typeId -> allTypes.stream().filter(type -> type.getId().equals(typeId)).findFirst().orElse(null))
                .filter(Objects::nonNull) // 过滤掉空值
                .collect(Collectors.toList());

        // 如果推荐数量已经有4或4以上，就按照推荐算法推荐4~8个房
        if (recommendedTypes.size() >= 4) {
            return recommendedTypes.stream().limit(8).collect(Collectors.toList());
        }

        // 如果不够4个就推荐的+随机推荐=4
        List<Type> randomTypes = getRandomTypes(4 - recommendedTypes.size(), allTypes);

        // 合并推荐房型和随机房型，保证不重复
        Set<Type> mergedTypes = new HashSet<>(recommendedTypes);
        mergedTypes.addAll(randomTypes);

        // 如果合并后的数量不够4个，继续随机推荐直到满足要求
        while (mergedTypes.size() < 4) {
            List<Type> moreRandomTypes = getRandomTypes(4 - mergedTypes.size(), allTypes);
            mergedTypes.addAll(moreRandomTypes);
        }

        return new ArrayList<>(mergedTypes);
    }

    // 检查用户是否与某房型有互动（收藏、预订、评论）
    private boolean hasInteraction(List<?> interactions, Integer userId, Integer typeId) {
        return interactions.stream()
                .anyMatch(interaction -> {
                    if (interaction instanceof Collect) {
                        return ((Collect) interaction).getUserId().equals(userId) &&
                                ((Collect) interaction).getTypeId().equals(typeId);
                    } else if (interaction instanceof Orders) {
                        return ((Orders) interaction).getUserId().equals(userId) &&
                                ((Orders) interaction).getTypeId().equals(typeId);
                    } else if (interaction instanceof Comment) {
                        return ((Comment) interaction).getUserId().equals(userId) &&
                                ((Comment) interaction).getTypeId().equals(typeId);
                    }
                    return false;
                });
    }


    // 从可用房型中随机选择需要推荐的房型数量
    private List<Type> getRandomTypes(int num, List<Type> availableTypes) {
        // 如果可用房型数量小于需要推荐的房型数量，则直接返回可用房型
        if (availableTypes.size() <= num) {
            return availableTypes;
        }
        // 使用Java 8的Collections.shuffle()方法来随机打乱列表
        Collections.shuffle(availableTypes);
        // 返回前N个房型
        return availableTypes.subList(0, num);
    }

    /**
     * 查询房型评论数
     */
    public Integer getCommentCountByTypeId(Integer typeId) {
        return commentMapper.getCountByTypeId(typeId,0);
    }



}