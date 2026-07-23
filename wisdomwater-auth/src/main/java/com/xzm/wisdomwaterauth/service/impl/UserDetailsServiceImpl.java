package com.xzm.wisdomwaterauth.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.xzm.wisdomwaterauth.entity.CustomerUserDetails;
import com.xzm.wisdomwaterauth.entity.SysUserDO;
import com.xzm.wisdomwaterauth.mapper.SysUserMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Arrays;

@Service
@RequiredArgsConstructor
public class UserDetailsServiceImpl implements UserDetailsService {
    private final SysUserMapper sysUserMapper;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        SysUserDO sysUser = sysUserMapper.selectOne(
                new LambdaQueryWrapper<SysUserDO>().eq(SysUserDO::getUsername, username)
        );
        if (sysUser == null) {
            throw new UsernameNotFoundException("账号不存在");
        }
        // 实际项目从库查询角色、权限集合
        return CustomerUserDetails.builder()
                .userId(sysUser.getId())
                .username(sysUser.getUsername())
                .password(sysUser.getPassword())
                .roleList(Arrays.asList("ROLE_USER"))
                .permissionList(Arrays.asList("system:view"))
                .build();
    }
}
