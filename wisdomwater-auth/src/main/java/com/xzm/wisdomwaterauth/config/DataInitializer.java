package com.xzm.wisdomwaterauth.config;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.xzm.wisdomwaterauth.entity.SysUserDO;
import com.xzm.wisdomwaterauth.mapper.SysUserMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.CommandLineRunner;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.util.Date;

/**
 * 数据初始化：创建默认测试用户
 */
@Component
@RequiredArgsConstructor
public class DataInitializer implements CommandLineRunner {

    private final SysUserMapper sysUserMapper;
    private final PasswordEncoder passwordEncoder;

    @Override
    public void run(String... args) {
        // 检查 xzm 用户是否已存在
        SysUserDO exist = sysUserMapper.selectOne(
                new LambdaQueryWrapper<SysUserDO>().eq(SysUserDO::getUsername, "xzm")
        );
        if (exist == null) {
            SysUserDO user = SysUserDO.builder()
                    .username("xzm")
                    .password(passwordEncoder.encode("123456"))
                    .nickname("管理员")
                    .enabled(1)
                    .accountNonExpired(1)
                    .accountNonLocked(1)
                    .credentialsNonExpired(1)
                    .createTime(new Date())
                    .build();
            sysUserMapper.insert(user);
            System.out.println("默认用户创建成功: xzm / 123456");
        } else {
            System.out.println("用户 xzm 已存在，跳过创建");
        }
    }
}