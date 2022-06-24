package com.song.springbootredis.controller;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.song.common.base.Log;
import com.song.common.domain.entity.Users;
import com.song.common.domain.vo.PageParam;
import com.song.common.domain.vo.Result;
import com.song.common.utils.ResultUtil;
import com.song.springbootredis.service.UsersService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.Arrays;
import java.util.List;
import java.util.Map;

/**
 * <p>
 * 用户管理   前端控制器
 * </p>
 *
 * @author songzw
 * @since 2022-05-05
 */
@RequiredArgsConstructor
@RestController
@RequestMapping("/users")
@Api(tags = "用户管理 ")
public class UsersController {
    public final UsersService usersService;

    @PostMapping("/insert")
    @ApiOperation(value = "增加", notes = "新增", httpMethod = "POST")
    @Log("增加")
    public Result insert(@RequestBody Users users) {
        return ResultUtil.res(usersService.save(users));
    }

    @PostMapping("/update")
    @ApiOperation(value = "修改", notes = "根据ID修改", httpMethod = "POST")
    @Log("修改")
    public Result updateById(@RequestBody Users users) {
        return ResultUtil.res(usersService.updateById(users));
    }

    @PostMapping("/delete")
    @ApiOperation(value = "删除", notes = "删除(多个ID逗号拼接如1,2,3)(参数示例{'ids':'1,2,3'})", httpMethod = "POST")
    @Log("删除")
    public Result delete(@RequestBody Map<String, String> param) {
        String ids = param.get("ids");
        String[] idItem = ids.split(",");
        List<String> idList = Arrays.asList(idItem);
        return ResultUtil.res(usersService.removeByIds(idList));
    }

    @GetMapping("/list")
    @ApiOperation(value = "查询", notes = "查询", httpMethod = "GET")
    public Result getList(Users users) {
        QueryWrapper<Users> wrapper = new QueryWrapper<>(users);
        List<Users> list = usersService.list(wrapper);
        return ResultUtil.res(list);
    }

    @GetMapping("/pageList")
    @ApiOperation(value = "分页查询", notes = "分页", httpMethod = "GET")
    public Result pageList(Users users, PageParam pageParam) {
        QueryWrapper<Users> wrapper = new QueryWrapper();
        IPage<Users> page = new Page<>(pageParam.getPageNum(), pageParam.getPageSize(), true);
        IPage<Users> rows = usersService.page(page, wrapper);
        return ResultUtil.res(rows);
    }

    @GetMapping("/queryById")
    @ApiOperation(value = "根据ID查询", notes = "根据ID查询", httpMethod = "GET")
    public Result queryById(@RequestParam String id) {
        return ResultUtil.res(usersService.queryById(id));
    }
}

