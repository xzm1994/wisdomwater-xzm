package com.xzm.wisdomwaterauth.controller;

import com.xzm.wisdomwaterauth.entity.CustomerUserDetails;
import com.xzm.wisdomwaterauth.service.TokenService;
import lombok.Data;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping
@RequiredArgsConstructor
public class AuthController {
    private final AuthenticationManager authenticationManager;
    private final TokenService tokenService;

    @Data
    static class LoginDTO {
        private String username;
        private String password;
    }

    @PostMapping("/login")
    public R login(@RequestBody LoginDTO dto) {
        // 账号密码认证（传明文，Spring Security 内部自动做密码比对）
        Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(dto.getUsername(), dto.getPassword())
        );
        CustomerUserDetails user = (CustomerUserDetails) authentication.getPrincipal();
        // 生成token存入redis
        String accessToken = tokenService.createToken(user);
        return R.ok().put("access_token", accessToken);
    }
}

@Data
class R {
    private Integer code;
    private String msg;
    private Object data;

    public static R ok() {
        R r = new R();
        r.code = 200;
        r.msg = "success";
        r.data = new java.util.HashMap<>();
        return r;
    }

    public R put(String key, Object val) {
        ((java.util.Map) this.data).put(key, val);
        return this;
    }
}
