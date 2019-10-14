package com.swx.ad.controller;

import com.alibaba.fastjson.JSON;
import com.swx.ad.client.SponsorClient;
import com.swx.ad.client.vo.AdPlan;
import com.swx.ad.client.vo.AdPlanGetRequest;
import com.swx.ad.vo.CommonResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@Slf4j
@RestController
public class SearchController {

    @Qualifier("eureka-client-ad-sponsor")
    @Autowired
    SponsorClient sponsorClient;

    @PostMapping("/getAdPlans")
    public CommonResponse<List<AdPlan>> getAdPlans(@RequestBody AdPlanGetRequest request){
        log.info("ad-search: getAdPlans -> {}", JSON.toJSONString(request));
        return sponsorClient.getAdPlans(request);
    }

}
