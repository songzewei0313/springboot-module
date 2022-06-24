package com.song.springbootgraphics.service.impl;

import com.song.common.domain.entity.Users;
import com.song.springbootgraphics.dao.UsersMapper;
import com.song.springbootgraphics.service.UsersService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 用户管理  服务实现类
 * </p>
 *
 * @author songzw
 * @since 2022-05-05
 */
@Service
public class UsersServiceImpl extends ServiceImpl<UsersMapper, Users> implements UsersService {

}
