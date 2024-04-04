package com.example.controller;

import com.example.common.Result;
import com.example.entity.Admin;
import com.example.entity.User;
import com.example.service.UserService;
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

/**
 * 用户前端操作接口
 **/
@RestController
@RequestMapping("/user")
public class UserController {

    @Resource
    private UserService userService;

    /**
     * 新增
     */
    @PostMapping("/add")
    public Result add(@RequestBody User user) {
        userService.add(user);
        return Result.success();
    }

    @PutMapping("/update")
    public Result update(@RequestBody User user) {
        userService.update(user);
        return Result.success();
    }

    @DeleteMapping("/delete/{id}")
    public Result delete(@PathVariable Integer id) {
        userService.deleteById(id);
        return Result.success();
    }

    @DeleteMapping("/delete/batch")
    public Result deleteBatch(@RequestBody List<Integer> ids) {
        userService.deleteBatch(ids);
        return Result.success();
    }

    /**
     * 查询所有
     */
    @GetMapping("/selectAll")
    public Result selectAll(User user ) {
        List<User> list = userService.selectAll(user);
        return Result.success(list);
    }


    /**
     * 分页查询
     */
    @GetMapping("/selectPage")
    public Result selectPage(User user,
                             @RequestParam(defaultValue = "1") Integer pageNum,
                             @RequestParam(defaultValue = "10") Integer pageSize) {
        PageInfo<User> page = userService.selectPage(user, pageNum, pageSize);
        return Result.success(page);
    }

}