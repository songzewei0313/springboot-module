package com.song.springbootmybatis.mapper;

import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import java.util.Map;

/**
 * @author szw
 * @date 2022/6/23 14:28
 */
@Mapper
public interface MpTestMapper {
    Map<String, Object> mpTest();
}
