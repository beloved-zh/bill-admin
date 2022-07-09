package com.beloved.system.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.beloved.common.model.entity.SysUser;
import com.beloved.system.mapper.SysUserMapper;
import com.beloved.system.service.SysUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * <p>
 * 用户信息表 服务实现类
 * </p>
 *
 * @author Beloved
 * @since 2022-07-09
 */
@Service
public class SysUserServiceImpl extends ServiceImpl<SysUserMapper, SysUser> implements SysUserService {

    @Autowired
    private SysUserMapper userMapper;

    @Override
    public List<SysUser> selectUserList() {
        return userMapper.selectUserList();
    }

    /**
     * 根据用户名查询用户
     * @param userName 用户名
     * @return 用户对象
     */
    @Override
    public SysUser queryUserByUserName(String userName) {
        return userMapper.queryUserByUserName(userName);
    }
}
