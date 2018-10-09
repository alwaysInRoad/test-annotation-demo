package com.example.testannotationdemo.controller;

import com.example.testannotationdemo.entity.User;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/users")
public class TestController {
    @ApiOperation("新增用户")
    @PutMapping(value = "/add")
    public void addUser(@RequestBody  User user) {

    }

    @ApiOperation("用户更新")
    @PutMapping(value = "/update")
    public void updateUser(@RequestBody  User user) {

    }

    @ApiOperation("根据id删除用户")
    @DeleteMapping(value = "/byId/{id}")
    public void deleteUser(@PathVariable("id") String id) {

    }

    @ApiOperation("根据id查找用户")
    @GetMapping(value = "/byId/{id}")
    public void getUserById(@PathVariable("id") String id) {
    }

}
