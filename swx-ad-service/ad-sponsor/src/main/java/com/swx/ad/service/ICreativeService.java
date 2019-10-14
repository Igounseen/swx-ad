package com.swx.ad.service;

import com.swx.ad.vo.CreativeRequest;
import com.swx.ad.vo.CreativeResponse;

public interface ICreativeService {

    CreativeResponse createCreative(CreativeRequest request);
}
