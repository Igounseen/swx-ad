package com.swx.ad.service;

import com.swx.ad.exception.AdException;
import com.swx.ad.vo.AdUnitDistrictRequest;
import com.swx.ad.vo.AdUnitDistrictResponse;
import com.swx.ad.vo.AdUnitItRequest;
import com.swx.ad.vo.AdUnitItResponse;
import com.swx.ad.vo.AdUnitKeywordRequest;
import com.swx.ad.vo.AdUnitKeywordResponse;
import com.swx.ad.vo.AdUnitRequest;
import com.swx.ad.vo.AdUnitResponse;
import com.swx.ad.vo.CreativeUnitRequest;
import com.swx.ad.vo.CreativeUnitResponse;

public interface IAdUnitService {

    AdUnitResponse createUnit(AdUnitRequest request) throws AdException;

    AdUnitKeywordResponse createUnitKeyword(AdUnitKeywordRequest request) throws AdException;

    AdUnitItResponse createUnitIt(AdUnitItRequest request) throws AdException;

    AdUnitDistrictResponse createUnitDistrict(AdUnitDistrictRequest request) throws AdException;

    CreativeUnitResponse createCreativeUnit(CreativeUnitRequest request) throws AdException;
}
