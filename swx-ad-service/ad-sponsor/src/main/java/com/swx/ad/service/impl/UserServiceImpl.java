package com.swx.ad.service.impl;

import com.swx.ad.constant.Constants;
import com.swx.ad.dao.AdUserRepo;
import com.swx.ad.entity.AdUser;
import com.swx.ad.exception.AdException;
import com.swx.ad.service.IUserService;
import com.swx.ad.utils.CommonUtils;
import com.swx.ad.vo.CreateUserRequest;
import com.swx.ad.vo.CreateUserResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * Created by Qinyi.
 */
@Slf4j
@Service
public class UserServiceImpl implements IUserService {

    @Autowired
    private AdUserRepo userRepo;

    @Override
    @Transactional
    public CreateUserResponse createUser(CreateUserRequest request)
            throws AdException {

        if (!request.validate()) {
            throw new AdException(Constants.ErrorMsg.REQUEST_PARAM_ERROR);
        }
        AdUser oldUser = userRepo.findByUsername(request.getUsername());
        if (oldUser != null) {
            throw new AdException(Constants.ErrorMsg.SAME_NAME_ERROR);
        }

        AdUser newUser = userRepo.save(new AdUser(request.getUsername(), CommonUtils.md5(request.getUsername())
        ));

        return new CreateUserResponse(
                newUser.getId(), newUser.getUsername(), newUser.getToken(),
                newUser.getCreateTime(), newUser.getUpdateTime()
        );
    }
}
