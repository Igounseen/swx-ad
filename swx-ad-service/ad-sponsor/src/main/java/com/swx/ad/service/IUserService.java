package com.swx.ad.service;

import com.swx.ad.exception.AdException;
import com.swx.ad.vo.CreateUserRequest;
import com.swx.ad.vo.CreateUserResponse;

public interface IUserService {

    /**
     * <h2>创建用户</h2>
     */
    CreateUserResponse createUser(CreateUserRequest request) throws AdException;
}
