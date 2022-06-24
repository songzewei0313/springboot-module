package com.song.springbootmybatis.service.impl;

import com.song.springbootmybatis.mapper.MpTestMapper;
import com.song.springbootmybatis.service.MpTestService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Map;

/**
 * @author szw
 * @date 2022/6/23 14:23
 */
@RequiredArgsConstructor
@Service
public class MpTestServiceImpl implements MpTestService {
    private final MpTestMapper mpTestMapper;

    @Override
    public Map<String, Object> mpTest(){
        return mpTestMapper.mpTest();
    }
}
