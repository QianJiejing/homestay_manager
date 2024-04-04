package com.example.controller;

import com.example.common.Result;
import com.example.entity.Homestay;
import com.example.service.HomestayService;
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
 * 民宿前端操作接口
 **/
@RestController
@RequestMapping("/homestay")
public class HomestayController {

    @Resource
    private HomestayService homestayService;

    /**
     * 新增
     */
    @PostMapping("/add")
    public Result add(@RequestBody Homestay homestay) {
        homestayService.add(homestay);
        return Result.success();
    }

    @PutMapping("/update")
    public Result update(@RequestBody Homestay homestay) {
        homestayService.update(homestay);
        return Result.success();
    }

    @DeleteMapping("/delete/{id}")
    public Result delete(@PathVariable Integer id) {
        homestayService.deleteById(id);
        return Result.success();
    }

    @GetMapping("/selectById")
    public Result selectById(@RequestParam Integer id) {
        Homestay homestay = homestayService.selectById(id);
        return Result.success(homestay);
    }

    @DeleteMapping("/delete/batch")
    public Result deleteBatch(@RequestBody List<Integer> ids) {
        homestayService.deleteBatch(ids);
        return Result.success();
    }

    @GetMapping("/selectAll")
    public Result selectAll() {
        List<Homestay> list = homestayService.selectAll();
        return Result.success(list);
    }

    @GetMapping("/selectAllOK")
    public Result selectAllOK() {
        List<Homestay> list = homestayService.selectAllOK();
        return Result.success(list);
    }

    @GetMapping("/selectByName")
    public Result selectByName(@RequestParam String name) {
        List<Homestay> list = homestayService.selectByName(name);
        return Result.success(list);
    }

    /**
     * 分页查询
     */
    @GetMapping("/selectPage")
    public Result selectPage(Homestay homestay,
                             @RequestParam(defaultValue = "1") Integer pageNum,
                             @RequestParam(defaultValue = "10") Integer pageSize) {
        PageInfo<Homestay> page = homestayService.selectPage(homestay, pageNum, pageSize);
        return Result.success(page);
    }

}