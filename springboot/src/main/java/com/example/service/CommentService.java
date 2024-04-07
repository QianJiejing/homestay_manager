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
     * 删除评论及其所有子回复
     */
    private void deleteCommentAndReplies(Integer id) {
        // 删除评论
        commentMapper.deleteById(id);

        // 查询子回复
        List<Comment> replies = commentMapper.selectRepliesByParentId(id,0);

        // 递归删除子回复
        for (Comment reply : replies) {
            deleteCommentAndReplies(reply.getId());
        }
    }

    /**
     * 删除
     */
    public void deleteById(Integer id) {
        Comment comment = commentMapper.selectById(id);
        if (comment.getParentId() == 0) {
            // 如果是父评论，删除所有子回复
            deleteCommentAndReplies(id);
        } else {
            // 如果是子回复，只删除自身
            commentMapper.deleteById(id);
        }
    }

    /**
     * 批量删除
     */
    public void deleteBatch(List<Integer> ids) {
        for (Integer id : ids) {
            deleteById(id);
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
/*            // 递归加载回复的回复
            List<Comment> commentList = commentMapper.selectByTypeIdAndParentId(id, comment.getId());
            comment.setChildren(selectReplyComments(id, commentList));*/

        }
        return list;
    }

    /**
     ** 分页查出该评论的回复
     */
    public PageInfo<Comment> selectPageReplyComments(Integer commentId, Integer pageNum, Integer pageSize) {
        PageHelper.startPage(pageNum, pageSize);
        //获取当前评论的回复
        List<Comment> pageReplies = commentMapper.selectRepliesByParentId(commentId,0);
        // 对回复信息进行处理
        for (Comment reply : pageReplies) {
            extracted(reply);
        }

        return PageInfo.of(pageReplies);
    }

/*    private List<Comment> getPageReplies(Integer commentId, Integer pageSize) {
        List<Comment> pageReplies = new ArrayList<>();
        Map<Integer, Comment> replyMap = new HashMap<>();
        Queue<Integer> queue = new LinkedList<>();

        // 初始化队列,将根评论的 ID 入队
        queue.offer(commentId);

        // 广度优先搜索获取当前页的回复
        while (!queue.isEmpty() && pageReplies.size() < pageSize) {
            Integer parentId = queue.poll();
            List<Comment> replies = commentMapper.selectByParentId(parentId);

            for (Comment reply : replies) {
                extracted(reply);
                replyMap.put(reply.getId(), reply);
                queue.offer(reply.getId());

                if (pageReplies.size() < pageSize) {
                    pageReplies.add(reply);
                }
            }
        }

        // 根据回复的 ID 顺序对当前页的回复列表进行排序
        pageReplies.sort(Comparator.comparingInt(Comment::getId));

        return pageReplies;
    }

    *//**
     *获取当前评论的回复数
     *//*
    private int countAllReplies(Integer commentId) {
        int count = 0;
        Queue<Integer> queue = new LinkedList<>();

        // 初始化队列,将根评论的 ID 入队
        queue.offer(commentId);

        // 广度优先搜索统计所有回复数量
        while (!queue.isEmpty()) {
            Integer parentId = queue.poll();
            List<Comment> replies = commentMapper.selectByParentId(parentId);
            count += replies.size();

            for (Comment reply : replies) {
                queue.offer(reply.getId());
            }
        }

        return count;
    }*/
/*    *//**
     *分页查出该评论的回复
     *//*
    public PageInfo<Comment> selectPageReplyComments(Integer commentId, Integer pageNum, Integer pageSize) {
        PageHelper.startPage(pageNum, pageSize);
        List<Comment> replies = commentMapper.selectByParentId(commentId);
        // 对回复列表按时间排序
        replies.sort(Comparator.comparing(Comment::getTime));

        for (Comment reply : replies) {
            // 处理父评论的用户信息
            extracted(reply);
            // 递归加载回复的回复
            reply.setChildren(selectReplyCommentsWithPaging(reply.getTypeId(), reply.getId(), 1, pageSize));
        }

        // 获取总记录数
        int total = countAllReplies(commentId);
        PageInfo<Comment> pageInfo = new PageInfo<>(replies);
        pageInfo.setTotal(total);
        return pageInfo;
    }

    *//**
     * 递归查询回复的回复,并分页
     *//*
    private List<Comment> selectReplyCommentsWithPaging(Integer typeId, Integer parentId, Integer pageNum, Integer pageSize) {
        PageHelper.startPage(pageNum, pageSize);
        // 查询指定父评论的回复
        List<Comment> replyComments = commentMapper.selectByTypeIdAndParentId(typeId, parentId);

        for (Comment reply : replyComments) {
            extracted(reply);
            // 递归加载回复的回复并处理用户信息
            reply.setChildren(selectReplyCommentsWithPaging(typeId, reply.getId(), 1, pageSize));
        }
        // 对回复列表按时间排序
        replyComments.sort(Comparator.comparing(Comment::getTime));
        return replyComments;
    }*/

/*    *//**
     * 递归查询回复的回复
     *//*
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
    }*/

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


}