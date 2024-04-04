package com.example.controller;

import com.example.common.Result;
import com.example.entity.Type;
import com.example.service.TypeService;
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
 * 房间类别表前端操作接口
 **/
@RestController
@RequestMapping("/type")
public class TypeController {

    @Resource
    private TypeService typeService;

    /**
     * 新增
     */
    @PostMapping("/add")
    public Result add(@RequestBody Type type) {
        typeService.add(type);
        return Result.success();
    }

    /**
     * 删除
     */
    @DeleteMapping("/delete/{id}")
    public Result deleteById(@PathVariable Integer id) {
        typeService.deleteById(id);
        return Result.success();
    }

    /**
     * 批量删除
     */
    @DeleteMapping("/delete/batch")
    public Result deleteBatch(@RequestBody List<Integer> ids) {
        typeService.deleteBatch(ids);
        return Result.success();
    }

    /**
     * 修改
     */
    @PutMapping("/update")
    public Result updateById(@RequestBody Type type) {
        typeService.updateById(type);
        return Result.success();
    }

    /**
     * 根据ID查询
     */
    @GetMapping("/selectById")
    public Result selectById(@RequestParam Integer id) {
        Type type = typeService.selectById(id);
        return Result.success(type);
    }

    @GetMapping("/selectByHomestayId")
    public Result selectByHomestayId(@RequestParam Integer id) {
        List<Type> list = typeService.selectByHomestayId(id);
        return Result.success(list);
    }

    /**
     * 查询所有
     */
    @GetMapping("/selectAll")
    public Result selectAll(Type type ) {
        List<Type> list = typeService.selectAll(type);
        return Result.success(list);
    }

    /**
     * 分页查询
     */
    @GetMapping("/selectPage")
    public Result selectPage(Type type,
                             @RequestParam(defaultValue = "1") Integer pageNum,
                             @RequestParam(defaultValue = "10") Integer pageSize) {
        PageInfo<Type> page = typeService.selectPage(type, pageNum, pageSize);
        return Result.success(page);
    }
    /**
    * 推荐房型
    */

    @GetMapping("/recommend")
    public Result recommend() {
        List<Type> list = typeService.recommend();
        return Result.success(list);
    }

    @GetMapping("/getCommentCount/{typeId}")
    public Result getCommentCount(@PathVariable Integer typeId) {
        Integer commentCount = typeService.getCommentCountByTypeId(typeId);
        return Result.success(commentCount);
    }

}