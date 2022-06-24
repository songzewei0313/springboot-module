package com.song.springbootmybatis.controller;

import com.song.common.domain.vo.Result;
import com.song.common.utils.ResultUtil;
import com.song.springbootmybatis.service.MpTestService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author szw
 * @date 2022/6/23 14:17
 */
@RequiredArgsConstructor
@RestController
@RequestMapping("/mp")
public class MpTestController {
    private final MpTestService mpTestService;

    @GetMapping("/mpTest")
    public Result mpTest(){
        return ResultUtil.res(mpTestService.mpTest());
    }
}
