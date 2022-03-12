package com.beloved.system.service.impl;

import com.beloved.common.pojo.entity.SysUser;
import com.beloved.system.mapper.SysUserMapper;
import com.beloved.system.service.SysUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SysUserServiceImpl implements SysUserService {

    @Autowired
    private SysUserMapper sysUserMapper;

    @Override
    public List<SysUser> selectUserList() {
        return sysUserMapper.selectUserList();
    }
}
