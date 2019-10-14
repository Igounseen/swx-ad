package com.swx.ad.service.impl;

import com.swx.ad.constant.Constants;
import com.swx.ad.dao.AdPlanRepo;
import com.swx.ad.dao.AdUnitRepo;
import com.swx.ad.dao.CreativeRepo;
import com.swx.ad.dao.unit_condition.AdUnitDistrictRepo;
import com.swx.ad.dao.unit_condition.AdUnitItRepo;
import com.swx.ad.dao.unit_condition.AdUnitKeywordRepo;
import com.swx.ad.dao.unit_condition.CreativeUnitRepo;
import com.swx.ad.entity.AdPlan;
import com.swx.ad.entity.AdUnit;
import com.swx.ad.entity.unit_condition.AdUnitDistrict;
import com.swx.ad.entity.unit_condition.AdUnitIt;
import com.swx.ad.entity.unit_condition.AdUnitKeyword;
import com.swx.ad.entity.unit_condition.CreativeUnit;
import com.swx.ad.exception.AdException;
import com.swx.ad.service.IAdUnitService;
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
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

/**
 * Created by Qinyi.
 */
@Service
public class AdUnitServiceImpl implements IAdUnitService {

    @Autowired
    private AdPlanRepo planRepo;
    @Autowired
    private AdUnitRepo unitRepo;
    @Autowired
    private AdUnitKeywordRepo unitKeywordRepo;
    @Autowired
    private AdUnitItRepo unitItRepo;
    @Autowired
    private AdUnitDistrictRepo unitDistrictRepo;
    @Autowired
    private CreativeRepo creativeRepo;
    @Autowired
    private CreativeUnitRepo creativeUnitRepo;

    @Override
    public AdUnitResponse createUnit(AdUnitRequest request) throws AdException {

        if (!request.createValidate()) { throw new AdException(Constants.ErrorMsg.REQUEST_PARAM_ERROR); }

        Optional<AdPlan> adPlan = planRepo.findById(request.getPlanId());
        if (!adPlan.isPresent()) { throw new AdException(Constants.ErrorMsg.CAN_NOT_FIND_RECORD); }

        AdUnit oldAdUnit = unitRepo.findByPlanIdAndUnitName(request.getPlanId(), request.getUnitName());
        if (oldAdUnit != null) { throw new AdException(Constants.ErrorMsg.SAME_NAME_UNIT_ERROR); }

        AdUnit newAdUnit = unitRepo.save(new AdUnit(request.getPlanId(), request.getUnitName(), request.getPositionType(), request.getBudget()));

        return new AdUnitResponse(newAdUnit.getId(),
                newAdUnit.getUnitName());
    }

    @Override
    public AdUnitKeywordResponse createUnitKeyword(AdUnitKeywordRequest request) throws AdException {

        List<Long> unitIds = request.getUnitKeywords().stream()
                .map(AdUnitKeywordRequest.UnitKeyword::getUnitId)
                .collect(Collectors.toList());
        if (!isRelatedUnitExist(unitIds)) { throw new AdException(Constants.ErrorMsg.REQUEST_PARAM_ERROR); }

        List<Long> ids = Collections.emptyList();

        List<AdUnitKeyword> unitKeywords = new ArrayList<>();
        if (!CollectionUtils.isEmpty(request.getUnitKeywords())) {

            request.getUnitKeywords().forEach(i -> unitKeywords.add(
                    new AdUnitKeyword(i.getUnitId(), i.getKeyword())
            ));
            ids = unitKeywordRepo.saveAll(unitKeywords).stream()
                    .map(AdUnitKeyword::getId)
                    .collect(Collectors.toList());
        }

        return new AdUnitKeywordResponse(ids);
    }

    @Override
    public AdUnitItResponse createUnitIt(AdUnitItRequest request) throws AdException {

        List<Long> unitIds = request.getUnitIts().stream()
                .map(AdUnitItRequest.UnitIt::getUnitId)
                .collect(Collectors.toList());
        if (!isRelatedUnitExist(unitIds)) {
            throw new AdException(Constants.ErrorMsg.REQUEST_PARAM_ERROR);
        }

        List<AdUnitIt> unitIts = new ArrayList<>();
        request.getUnitIts().forEach(i -> unitIts.add(
                new AdUnitIt(i.getUnitId(), i.getItTag())
        ));
        List<Long> ids = unitItRepo.saveAll(unitIts).stream()
                .map(AdUnitIt::getId)
                .collect(Collectors.toList());

        return new AdUnitItResponse(ids);
    }

    @Override
    public AdUnitDistrictResponse createUnitDistrict(AdUnitDistrictRequest request) throws AdException {

        List<Long> unitIds = request.getUnitDistricts().stream()
                .map(AdUnitDistrictRequest.UnitDistrict::getUnitId)
                .collect(Collectors.toList());
        if (!isRelatedUnitExist(unitIds)) {
            throw new AdException(Constants.ErrorMsg.REQUEST_PARAM_ERROR);
        }

        List<AdUnitDistrict> unitDistricts = new ArrayList<>();
        request.getUnitDistricts().forEach(d -> unitDistricts.add(
                new AdUnitDistrict(d.getUnitId(), d.getProvince(),
                        d.getCity())
        ));
        List<Long> ids = unitDistrictRepo.saveAll(unitDistricts)
                .stream().map(AdUnitDistrict::getId)
                .collect(Collectors.toList());

        return new AdUnitDistrictResponse(ids);
    }

    @Override
    public CreativeUnitResponse createCreativeUnit(CreativeUnitRequest request) throws AdException {

        List<Long> unitIds = request.getUnitItems().stream()
                .map(CreativeUnitRequest.CreativeUnitItem::getUnitId)
                .collect(Collectors.toList());
        List<Long> creativeIds = request.getUnitItems().stream()
                .map(CreativeUnitRequest.CreativeUnitItem::getCreativeId)
                .collect(Collectors.toList());

        if (!(isRelatedUnitExist(unitIds) && isRelatedUnitExist(creativeIds))) { throw new AdException(Constants.ErrorMsg.REQUEST_PARAM_ERROR); }

        List<CreativeUnit> creativeUnits = new ArrayList<>();
        request.getUnitItems().forEach(i -> creativeUnits.add(
                new CreativeUnit(i.getCreativeId(), i.getUnitId())
        ));

        List<Long> ids = creativeUnitRepo.saveAll(creativeUnits)
                .stream()
                .map(CreativeUnit::getId)
                .collect(Collectors.toList());

        return new CreativeUnitResponse(ids);
    }

    private boolean isRelatedUnitExist(List<Long> unitIds) {
        if (CollectionUtils.isEmpty(unitIds)) { return false; }
        return unitRepo.findAllById(unitIds).size() == new HashSet<>(unitIds).size();
    }

    private boolean isRelatedCreativeExist(List<Long> creativeIds) {
        if (CollectionUtils.isEmpty(creativeIds)) { return false; }
        return creativeRepo.findAllById(creativeIds).size() == new HashSet<>(creativeIds).size();
    }
}
