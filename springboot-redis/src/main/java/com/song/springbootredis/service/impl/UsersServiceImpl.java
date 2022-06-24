package com.song.springbootredis.service.impl;

import cn.hutool.core.util.StrUtil;
import cn.hutool.json.JSONUtil;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.song.common.domain.entity.Users;
import com.song.springbootredis.dao.UsersMapper;
import com.song.springbootredis.service.UsersService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Service;

import static com.song.common.utils.Consts.USER_KEY;

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


    public StringRedisTemplate redisTemplate;

    /**
     * setter方法注入
     *
     * @param redisTemplate
     */
    @Autowired
    public void setRedisTemplate(StringRedisTemplate redisTemplate) {
        this.redisTemplate = redisTemplate;
    }
    /*@Autowired
    public UsersServiceImpl(StringRedisTemplate redisTemplate){
        this.redisTemplate = redisTemplate;
    }*/

    public boolean add(Users users) {
        boolean save = this.save(users);
        return save;
    }

    @Override
    public Users queryById(String id) {
        String key = USER_KEY + id;
        String entityJson = redisTemplate.opsForValue().get(key);
        if (StrUtil.isNotBlank(entityJson)) {
            Users users = JSONUtil.toBean(entityJson, Users.class);
            return users;
        }
        Users users = getById(id);
        if (null == users) {
            throw new RuntimeException("用户不存在！");
        }
        redisTemplate.opsForValue().set(key, JSONUtil.toJsonStr(users));
        return users;
    }
}
