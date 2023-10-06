package com.juneutf.mtg.iml;

import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.juneutf.mtg.config.service.PlanService;
import com.juneutf.mtg.mapper.PlanMapper;
import com.juneutf.mtg.model.JobModel;
import com.juneutf.mtg.model.PlanModel;

/**
 * {@link PlanService} インターフェースの実装を提供します。
 */
@Service
public class PlanImpl implements PlanService {
    @Autowired
    private PlanMapper mapper;
    @Override
    public int insertPlan(PlanModel planModel) {
        return mapper.insertPlan(planModel);
    }
    @Override
    public ArrayList<JobModel> selectPlan() {
        return mapper.selectPlan();
    }
    @Override
    public ArrayList<JobModel> selectMaxIdPlan() {
        return mapper.selectMaxIdPlan();
    }
}
