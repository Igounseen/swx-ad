package com.swx.ad.service.impl;

import com.swx.ad.dao.CreativeRepo;
import com.swx.ad.entity.Creative;
import com.swx.ad.service.ICreativeService;
import com.swx.ad.vo.CreativeRequest;
import com.swx.ad.vo.CreativeResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Created by Qinyi.
 */
@Service
public class CreativeServiceImpl implements ICreativeService {

    @Autowired
    private CreativeRepo creativeRepo;

    @Override
    public CreativeResponse createCreative(CreativeRequest request) {

        Creative creative = creativeRepo.save(request.convertToEntity());

        return new CreativeResponse(creative.getId(), creative.getName());
    }
}
