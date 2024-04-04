package com.example.service;

import cn.hutool.core.date.DateUtil;
import com.example.common.Constants;
import com.example.common.enums.RoleEnum;
import com.example.entity.Account;
import com.example.entity.Comment;
import com.example.entity.Homestay;
import com.example.entity.User;
import com.example.mapper.CommentMapper;
import com.example.mapper.HomestayMapper;
import com.example.mapper.UserMapper;
import com.example.utils.TokenUtils;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.stereotype.Service;
import javax.annotation.Resource;
import java.util.Comparator;
import java.util.List;

@Service
public class CommentService {

    @Resource
    private CommentMapper commentMapper;
    @Resource
    private HomestayMapper homestayMapper;
    @Resource
    private UserMapper userMapper;

    /**
     * 新增
     */
    public void add(Comment comment) {
        comment.setTime(DateUtil.now());
        commentMapper.insert(comment);
        // 1. 对应的民宿评论数 要加 1
        Homestay homestay = homestayMapper.selectById(comment.getHomestayId());
        homestay.setComNum(homestay.getComNum() + 1);
        homestayMapper.updateById(homestay);
    }

    /**
     * 删除
     */
    public void deleteById(Integer id) {
        // 1. 对应的民宿评论数 要减 1
        Comment comment = commentMapper.selectById(id);
        Homestay homestay = homestayMapper.selectById(comment.getHomestayId());
        homestay.setComNum(homestay.getComNum() - 1);
        homestayMapper.updateById(homestay);
        commentMapper.deleteById(id);
    }

    /**
     * 批量删除
     */
    public void deleteBatch(List<Integer> ids) {
        for (Integer id : ids) {
            // 1. 对应的民宿评论数 要减 1
            Comment comment = commentMapper.selectById(id);
            Homestay homestay = homestayMapper.selectById(comment.getHomestayId());
            homestay.setComNum(homestay.getComNum() - 1);
            homestayMapper.updateById(homestay);
            commentMapper.deleteById(id);
        }
    }

    /**
     * 修改
     */
    public void updateById(Comment comment) {
        commentMapper.updateById(comment);
    }

    /**
     * 根据ID查询
     */
    public Comment selectById(Integer id) {
        return commentMapper.selectById(id);
    }

    /**
     * 查询所有
     */
    public List<Comment> selectAll(Comment comment) {
        return commentMapper.selectAll(comment);
    }

    /**
     * 分页查询
     */
    public PageInfo<Comment> selectPage(Comment comment, Integer pageNum, Integer pageSize) {
        Account currentUser = TokenUtils.getCurrentUser();
        if (RoleEnum.HOMESTAY.name().equals(currentUser.getRole())) {
            comment.setHomestayId(currentUser.getId());
        }
        PageHelper.startPage(pageNum, pageSize);
        List<Comment> list = commentMapper.selectAll(comment);
        return PageInfo.of(list);
    }

    public List<Comment> selectByTypeId(Integer id) {
        List<Comment> list = commentMapper.selectByTypeIdAndParentId(id, 0);
        for (Comment comment : list) {
            // 处理父评论的用户信息
            extracted(comment);
            //注释掉展示回复放到了分页业务里
            // 递归加载回复的回复
            List<Comment> commentList = commentMapper.selectByTypeIdAndParentId(id, comment.getId());
            comment.setChildren(selectReplyComments(id, commentList));

        }
        // 对评论列表按时间排序
        list.sort(Comparator.comparing(Comment::getTime));
        return list;
    }
    public PageInfo<Comment> selectPageReplyComments(Integer commentId, Integer pageNum, Integer pageSize) {
        PageHelper.startPage(pageNum, pageSize);
        List<Comment> replies = commentMapper.selectByParentId(commentId);

        for (Comment comment : replies) {
            // 处理父评论的用户信息
            extracted(comment);
            // 递归加载回复的回复
            List<Comment> commentList = commentMapper.selectByParentId( comment.getId());
            comment.setChildren(selectReplyComments(comment.getTypeId(), commentList));
        }
        // 对回复列表按时间排序
        replies.sort(Comparator.comparing(Comment::getTime));
        return PageInfo.of(replies);
    }


    /**
     * 递归查询回复的回复
     */
    private List<Comment> selectReplyComments(Integer typeId, List<Comment> comments) {
        for (Comment comment : comments) {
            // 处理子回复的用户信息
            extracted(comment);
            // 递归加载回复的回复
            List<Comment> replyComments = commentMapper.selectByTypeIdAndParentId(typeId, comment.getId());
            //当评论没有回复时，或者回复列表为空时，递归停止。
            if (!replyComments.isEmpty()) {
                // 递归加载回复的回复，并处理用户信息
                comment.setChildren(selectReplyComments(typeId, replyComments));
            }
        }
        comments.sort(Comparator.comparing(Comment::getTime));
        return comments;
    }

    //处理用户数据
    private void extracted(Comment comment) {
        if (RoleEnum.ADMIN.name().equals(comment.getRole())) {
            comment.setAvatar(Constants.ADMIN_DEFAULT_AVATAR);
            comment.setUserName(Constants.ADMIN_DEFAULT_NAME);
        }
        if (RoleEnum.HOMESTAY.name().equals(comment.getRole())) {
            Homestay homestay = homestayMapper.selectById(comment.getHomestayId());
            // 找到父评论
            findParentComment(comment, homestay.getName(), homestay.getAvatar());
        }

        if (RoleEnum.USER.name().equals(comment.getRole())) {
            User user = userMapper.selectById(comment.getUserId());
            // 找到父评论
            findParentComment(comment, user.getName(), user.getAvatar());
        }

    }
    // 找到父评论，设置评论的用户名信息
    private void findParentComment(Comment comment, String name, String avatar) {
        Comment parentComment = commentMapper.selectById(comment.getParentId());
        if (parentComment != null) {
            if (parentComment.getUserId() != 0) {
                // 获取父评论的用户信息
                User parentUser = userMapper.selectById(parentComment.getUserId());
                Homestay parentHomestay = homestayMapper.selectById(parentComment.getHomestayId());
                if (comment.getParentId() == 0) {
                    // 如果评论的 parentId 为 0，则直接显示评论的用户名
                    comment.setUserName(name);
                } else {
                    if (parentComment.getParentId() == 0) {
                        // 如果父评论的 parentId 为 0，则直接使用父评论的用户名作为回复人信息
                        comment.setUserName(name);
                    } else {
                        // 否则，格式化评论为 "回复 @" 的形式
                        if (RoleEnum.USER.name().equals(parentComment.getRole())){
                            comment.setUserName(name + " 回复 @" + parentUser.getName());
                        } else if (RoleEnum.HOMESTAY.name().equals(parentComment.getRole())){
                            comment.setUserName(name + " 回复 @" + parentHomestay.getName());
                        }
                    }
                }
            }else {
                // 如果找不到父评论，直接显示用户名
                comment.setUserName(name);
            }
        } else {
            // 如果找不到父评论，直接显示用户名
            comment.setUserName(name);
        }
        comment.setAvatar(avatar);
    }

/*    // 统计回复数
    private void countReplies(Comment comment) {
        // 统计所有的孩子回复数（递归）
        int allReplyCount = countAllReplies(comment.getChildren());
        comment.setAllReplyCount(allReplyCount);
    }

    // 递归统计所有的孩子回复数
    private int countAllReplies(List<Comment> comments) {
        int count = comments.size();
        for (Comment comment : comments) {
            if (!comment.getChildren().isEmpty()) {
                count += countAllReplies(comment.getChildren());
            }
        }
        return count;
    }*/


}