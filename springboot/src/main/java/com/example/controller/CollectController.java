package com.example.controller;

import com.example.common.Result;
import com.example.entity.Collect;
import com.example.entity.Type;
import com.example.service.CollectService;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.List;

/**
 * 收藏信息表前端操作接口
 **/
@RestController
@RequestMapping("/collect")
public class CollectController {

    @Resource
    private CollectService collectService;

    /**
     * 新增
     */
    @PostMapping("/add")
    public Result add(@RequestBody Collect collect) {
        collectService.add(collect);
        return Result.success();
    }

    /**
     * 取消收藏
     */
    @DeleteMapping("/deleteByTypeId")
    public Result deleteByTypeId(@RequestParam Integer id) {
        collectService.deleteByTypeId(id);
        return Result.success();
    }

    @GetMapping("/selectOwn")
    public Result selectOwn(@RequestParam Integer id) {
        List<Type> list = collectService.selectOwn(id);
        return Result.success(list);
    }
    @GetMapping("/getLatestThree")
    public Result getLatestThreeByUserId(@RequestParam Integer id) {
        List<Collect> list = collectService.selectLatestThreeByUserId(id);
        return Result.success(list);
    }
    @GetMapping("/getCollectCount/{UserId}")
    public Result getCollectCount(@PathVariable Integer UserId) {
        Integer collectCount = collectService.getCollectCountByUserId(UserId);
        return Result.success(collectCount);
    }

/*    @GetMapping("/selectOwn")
    public Result selectOwn(@RequestParam Integer id) {
        List<Collect> list = collectService.selectOwn(id);
        return Result.success(list);
    }*/

}