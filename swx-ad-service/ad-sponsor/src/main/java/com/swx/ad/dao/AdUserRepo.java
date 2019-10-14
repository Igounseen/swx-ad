package com.swx.ad.dao;

import com.swx.ad.entity.AdUser;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AdUserRepo extends JpaRepository<AdUser, Long> {

    /**
     * <h2>根据用户名查询用户信息</h2>
     */
    AdUser findByUsername(String username);

}
