package com.swx.ad.controller;

import com.alibaba.fastjson.JSON;
import com.swx.ad.service.ICreativeService;
import com.swx.ad.vo.CreativeRequest;
import com.swx.ad.vo.CreativeResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
public class CreativeOPController {

    @Autowired
    private ICreativeService creativeService;

    @PostMapping("/create/creative")
    public CreativeResponse createCreative(@RequestBody CreativeRequest request) {
        log.info("ad-sponsor: createCreative -> {}", JSON.toJSONString(request));
        return creativeService.createCreative(request);
    }
}
