package com.swx.ad.dao;

import com.swx.ad.entity.AdUnit;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface AdUnitRepo extends JpaRepository<AdUnit, Long> {

    AdUnit findByPlanIdAndUnitName(Long planId, String unitName);

    List<AdUnit> findAllByUnitStatus(Integer unitStatus);

}
