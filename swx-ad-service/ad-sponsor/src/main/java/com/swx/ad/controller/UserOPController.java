package com.swx.ad.controller;

import com.alibaba.fastjson.JSON;
import com.swx.ad.exception.AdException;
import com.swx.ad.service.IUserService;
import com.swx.ad.vo.CreateUserRequest;
import com.swx.ad.vo.CreateUserResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
public class UserOPController {

    @Autowired
    private IUserService userService;

    @PostMapping("/create/user")
    public CreateUserResponse createUser(
            @RequestBody CreateUserRequest request) throws AdException {
        log.info("ad-sponsor: createUser -> {}", JSON.toJSONString(request));
        return userService.createUser(request);
    }
}
