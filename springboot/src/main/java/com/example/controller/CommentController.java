package com.example.controller;

import com.example.common.Result;
import com.example.entity.Comment;
import com.example.service.CommentService;
import com.github.pagehelper.PageInfo;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.List;

@RestController
@RequestMapping("/comment")
public class CommentController {

    @Resource
    private CommentService commentService;

    /**
     * 新增
     */
    @PostMapping("/add")
    public Result add(@RequestBody Comment comment) {
        commentService.add(comment);
        return Result.success();
    }

    /**
     * 删除
     */
    @DeleteMapping("/delete/{id}")
    public Result deleteById(@PathVariable Integer id) {
        commentService.deleteById(id);
        return Result.success();
    }

    /**
     * 批量删除
     */
    @DeleteMapping("/delete/batch")
    public Result deleteBatch(@RequestBody List<Integer> ids) {
        commentService.deleteBatch(ids);
        return Result.success();
    }

    /**
     * 修改
     */
    @PutMapping("/update")
    public Result updateById(@RequestBody Comment comment) {
        commentService.updateById(comment);
        return Result.success();
    }

    /**
     * 根据ID查询
     */
    @GetMapping("/selectById/{id}")
    public Result selectById(@PathVariable Integer id) {
        Comment comment = commentService.selectById(id);
        return Result.success(comment);
    }

    /**
     * 查询所有
     */
    @GetMapping("/selectAll")
    public Result selectAll(Comment comment ) {
        List<Comment> list = commentService.selectAll(comment);
        return Result.success(list);
    }

    @GetMapping("/selectByTypeId")
    public Result selectByTypeId(@RequestParam Integer id) {
        List<Comment> list = commentService.selectByTypeId(id);
        return Result.success(list);
    }
    @GetMapping("/selectReplies")
    public Result getRepliesByCommentId(@RequestParam Integer commentId,
                                        @RequestParam(defaultValue = "1") Integer pageNum,
                                        @RequestParam(defaultValue = "4") Integer pageSize) {
        PageInfo<Comment> page = commentService.selectPageReplyComments(commentId, pageNum, pageSize);
        return Result.success(page);
    }

    /**
     * 分页查询
     */
    @GetMapping("/selectPage")
    public Result selectPage(Comment comment,
                             @RequestParam(defaultValue = "1") Integer pageNum,
                             @RequestParam(defaultValue = "10") Integer pageSize) {
        PageInfo<Comment> page = commentService.selectPage(comment, pageNum, pageSize);
        return Result.success(page);
    }

}