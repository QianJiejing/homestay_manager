package com.example.controller;

import com.example.common.Result;
import com.example.entity.Checkin;
import com.example.service.CheckinService;
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
@RequestMapping("/checkin")
public class CheckinController {

    @Resource
    private CheckinService checkinService;

    /**
     * 新增
     */
    @PostMapping("/add")
    public Result add(@RequestBody Checkin checkin) {
        checkinService.add(checkin);
        return Result.success();
    }

    /**
     * 删除
     */
    @DeleteMapping("/delete/{id}")
    public Result deleteById(@PathVariable Integer id) {
        checkinService.deleteById(id);
        return Result.success();
    }

    /**
     * 批量删除
     */
    @DeleteMapping("/delete/batch")
    public Result deleteBatch(@RequestBody List<Integer> ids) {
        checkinService.deleteBatch(ids);
        return Result.success();
    }

    /**
     * 修改
     */
    @PutMapping("/update")
    public Result updateById(@RequestBody Checkin checkin) {
        checkinService.updateById(checkin);
        return Result.success();
    }

    /**
     * 根据ID查询
     */
    @GetMapping("/selectById/{id}")
    public Result selectById(@PathVariable Integer id) {
        Checkin checkin = checkinService.selectById(id);
        return Result.success(checkin);
    }

    /** 退房 */
    @GetMapping("/tuifang/{id}")
    public Result tuifang(@PathVariable Integer id) {
        checkinService.tuifang(id);
        return Result.success();
    }

    @GetMapping("/selectByUserId")
    public Result selectByUserId(@RequestParam Integer id) {
        List<Checkin> checkin = checkinService.selectByUserId(id);
        return Result.success(checkin);
    }

    /**
     * 查询所有
     */
    @GetMapping("/selectAll")
    public Result selectAll(Checkin checkin ) {
        List<Checkin> list = checkinService.selectAll(checkin);
        return Result.success(list);
    }

    /**
     * 分页查询
     */
    @GetMapping("/selectPage")
    public Result selectPage(Checkin checkin,
                             @RequestParam(defaultValue = "1") Integer pageNum,
                             @RequestParam(defaultValue = "10") Integer pageSize) {
        PageInfo<Checkin> page = checkinService.selectPage(checkin, pageNum, pageSize);
        return Result.success(page);
    }

}