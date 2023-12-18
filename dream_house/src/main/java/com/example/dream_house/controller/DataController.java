package com.example.dream_house.controller;

import com.example.dream_house.model.Data;
import com.example.dream_house.model.getUser;
import com.example.dream_house.service.DataService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.v3.oas.annotations.parameters.RequestBody;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;

/**
 * @BelongsProject:dream_house
 * @BelongsPackage:com.example.dream_house.controller
 * @Author:Uestc_Xiye
 * @CreateTime:2023-12-17 17:17:36
 */

@Api(tags = "API接口")
@RestController
@CrossOrigin(origins = "*", maxAge = 3600)
public class DataController {

    @Autowired
    private DataService dataService;

    @ApiOperation("添加完整信息")
    @PostMapping("/insert")
    public String insert(@RequestBody Data data) {
        // @RequestBody注解用来绑定通过http请求中application/json类型上传的数据
        return dataService.insert(data.getIp(), data.getProvince(), data.getTime(), data.getStr(), data.getLikes());
    }

    @ApiOperation("查询id对应的信息")
    @GetMapping("/findById/{id}")
    public Data findById(@PathVariable int id) {
        return dataService.findById(id);
    }

    @ApiOperation("更新信息")
    @PutMapping("/update")
    public void update(@RequestBody Data data) {
        dataService.update(data);
    }

    @ApiOperation("删除指定id的信息")
    @DeleteMapping("/delete/{id}")
    public void deleteUser(@PathVariable int id) {
        dataService.delete(id);
    }

    @ApiOperation("上传信息接口")
    @PostMapping("/Add/{str}")
    public String Add(HttpServletRequest request, HttpServletResponse response, @PathVariable String str) {
        return dataService.Add(request, response, str);
    }

    @ApiOperation("查询点赞数前50名的信息")
    @GetMapping("/findByLikes")
    public List<getUser> findByLikes() {
        return dataService.findByLikes();
    }

    @ApiOperation("查询最新的50条信息")
    @GetMapping("/findByTime")
    public List<getUser> findByTime() {
        return dataService.findByTime();
    }

    @ApiOperation("查询随机的50条信息")
    @GetMapping("/findByRand")
    public List<getUser> findByRand() {
        return dataService.findByRand();
    }

    @ApiOperation("更新指定id对应的点赞数+1")
    @PutMapping("/increaseLikesById/{id}")
    public String increaseLikesById(@PathVariable int id) {
        return dataService.increaseLikesById(id);
    }

    @ApiOperation("更新指定id对应的点赞数-1")
    @PutMapping("/decreaseLikesById/{id}")
    public String decreaseLikesById(@PathVariable int id) {
        return dataService.decreaseLikesById(id);
    }
}
