package com.juneutf.mtg.iml;

import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.juneutf.mtg.mapper.PlanMapper;
import com.juneutf.mtg.model.JobModel;
import com.juneutf.mtg.model.PlanModel;
import com.juneutf.mtg.service.PlanService;

/**
 * PlanServiceの実装クラスです。
 */
@Service
public class PlanImpl implements PlanService {
    @Autowired
    private PlanMapper mapper;

    /**
     * 予約モデルを挿入します。
     *
     * @param planModel 挿入する予約モデル
     * @return 挿入結果を表す整数
     */
    @Override
    public int insertPlan(PlanModel planModel) {
        return mapper.insertPlan(planModel);
    }

    /**
     * 予約を選択します。
     *
     * @return 選択された予約内容モデルのリスト
     */
    @Override
    public ArrayList<JobModel> selectPlan() {
        return mapper.selectPlan();
    }

    /**
     * 最大IDの予約を選択します。
     *
     * @return 選択された最大IDの予約内容モデルのリスト
     */
    @Override
    public ArrayList<JobModel> selectMaxIdPlan() {
        return mapper.selectMaxIdPlan();
    }
}
