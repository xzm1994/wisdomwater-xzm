package com.xzm.wisdomwaterauth.controller;

import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @Description:
 * @ClassName: {@link TestController}
 * @Author: XZM
 * @Date: 2026/7/24 1:23
 * @Version: 1.0.0
 */
@RestController
public class TestController {

    @GetMapping("/test")
    @PreAuthorize("hasAnyAuthority('system:view')")
    public String test1() {
        return "test1";
    }

    @GetMapping("/test2")
    @PreAuthorize("hasAnyAuthority('system:view2')")
    public String test2() {
        return "test2";
    }
}

