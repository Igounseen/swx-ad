package com.swx.ad.service.impl;

import com.swx.ad.constant.CommonStatus;
import com.swx.ad.constant.Constants;
import com.swx.ad.dao.AdPlanRepo;
import com.swx.ad.dao.AdUserRepo;
import com.swx.ad.entity.AdPlan;
import com.swx.ad.entity.AdUser;
import com.swx.ad.exception.AdException;
import com.swx.ad.service.IAdPlanService;
import com.swx.ad.utils.CommonUtils;
import com.swx.ad.vo.AdPlanGetRequest;
import com.swx.ad.vo.AdPlanRequest;
import com.swx.ad.vo.AdPlanResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.List;
import java.util.Optional;

/**
 * Created by Qinyi.
 */
@Service
public class AdPlanServiceImpl implements IAdPlanService {

    @Autowired
    private AdUserRepo userRepo;
    @Autowired
    private AdPlanRepo planRepo;

    @Override
    @Transactional
    public AdPlanResponse createAdPlan(AdPlanRequest request) throws AdException {
        if (!request.createValidate()) {
            throw new AdException(Constants.ErrorMsg.REQUEST_PARAM_ERROR);
        }
        // 确保关联的 User 是存在的
        Optional<AdUser> adUser = userRepo.findById(request.getUserId());
        if (!adUser.isPresent()) { throw new AdException(Constants.ErrorMsg.CAN_NOT_FIND_RECORD); }

        AdPlan oldPlan = planRepo.findByUserIdAndPlanName(request.getUserId(), request.getPlanName());
        if (oldPlan != null) { throw new AdException(Constants.ErrorMsg.SAME_NAME_PLAN_ERROR); }

        AdPlan newAdPlan = planRepo.save(
                new AdPlan(request.getUserId(), request.getPlanName(),
                        CommonUtils.parseStringDate(request.getStartDate()),
                        CommonUtils.parseStringDate(request.getEndDate())
                )
        );

        return new AdPlanResponse(newAdPlan.getId(), newAdPlan.getPlanName());
    }

    @Override
    public List<AdPlan> getAdPlanByIds(AdPlanGetRequest request) throws AdException {
        if (!request.validate()) { throw new AdException(Constants.ErrorMsg.REQUEST_PARAM_ERROR); }
        return planRepo.findAllByIdInAndUserId(request.getIds(), request.getUserId());
    }

    @Override
    @Transactional
    public AdPlanResponse updateAdPlan(AdPlanRequest request) throws AdException {
        if (!request.updateValidate()) { throw new AdException(Constants.ErrorMsg.REQUEST_PARAM_ERROR); }

        AdPlan plan = planRepo.findByIdAndUserId(request.getId(), request.getUserId());
        if (plan == null) { throw new AdException(Constants.ErrorMsg.CAN_NOT_FIND_RECORD); }
        if (request.getPlanName() != null) { plan.setPlanName(request.getPlanName()); }
        if (request.getStartDate() != null) { plan.setStartDate(CommonUtils.parseStringDate(request.getStartDate())); }
        if (request.getEndDate() != null) { plan.setEndDate(CommonUtils.parseStringDate(request.getEndDate())); }
        plan.setUpdateTime(new Date());
        plan = planRepo.save(plan);

        return new AdPlanResponse(plan.getId(), plan.getPlanName());
    }

    @Override
    @Transactional
    public void deleteAdPlan(AdPlanRequest request) throws AdException {
        if (!request.deleteValidate()) { throw new AdException(Constants.ErrorMsg.REQUEST_PARAM_ERROR); }

        AdPlan plan = planRepo.findByIdAndUserId(request.getId(), request.getUserId());
        if (plan == null) { throw new AdException(Constants.ErrorMsg.CAN_NOT_FIND_RECORD); }
        plan.setPlanStatus(CommonStatus.INVALID.getStatus());
        plan.setUpdateTime(new Date());

        planRepo.save(plan);
    }
}
