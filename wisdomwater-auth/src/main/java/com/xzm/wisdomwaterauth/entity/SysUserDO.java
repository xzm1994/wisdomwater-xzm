package com.xzm.wisdomwaterauth.entity;

import com.baomidou.mybatisplus.annotation.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.Date;

/**
 * OAuth2 用户表实体
 * @ClassName: {@link SysUserDO}
 * @Author: XZM
 * @Date: 2026/7/24 0:20
 * @Version: 1.0.0
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@TableName("sys_user")
public class SysUserDO {

    /**
     * 主键
     */
    @TableId(value = "id", type = IdType.AUTO)
    private Long id;

    /**
     * 用户名
     */
    @TableField(value = "username")
    private String username;

    /**
     * 加密密码
     */
    @TableField(value = "password")
    private String password;

    /**
     * 昵称
     */
    @TableField(value = "nickname")
    private String nickname;

    /**
     * 手机号
     */
    @TableField(value = "phone")
    private String phone;

    /**
     * 邮箱
     */
    @TableField(value = "email")
    private String email;

    /**
     * 头像URL
     */
    @TableField(value = "avatar")
    private String avatar;

    /**
     * 账号启用
     */
    @TableField(value = "enabled")
    private Integer enabled;

    /**
     * 未过期
     */
    @TableField(value = "account_non_expired")
    private Integer accountNonExpired;

    /**
     * 未锁定
     */
    @TableField(value = "account_non_locked")
    private Integer accountNonLocked;

    /**
     * 凭证未过期
     */
    @TableField(value = "credentials_non_expired")
    private Integer credentialsNonExpired;

    /**
     * 创建时间
     */
    @TableField(value = "create_time")
    private Date createTime;

    /**
     * 更新时间
     */
    @TableField(value = "update_time")
    private Date updateTime;

    /**
     * 逻辑删除
     */
    @TableField(value = "deleted")
    @TableLogic
    private Integer deleted;

}
