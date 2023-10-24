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
     *(予約中状態）
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

    /**
     * 予約を選択します。
     *
     * @return 選択された予約内容モデルのリスト
     */
    @Override
    public ArrayList<JobModel> selectPlanAPI() {
        return mapper.selectPlanAPI();
    }
    /**
     * 指定された日付(予約中状態のID編集以外）の予約を取得します。
     * @return PlanModelのリスト
     */
	@Override
	public ArrayList<JobModel> selectPlanEdit(int id) {
		return mapper.selectPlanEdit(id);
	}
	/**
     * 共有担当者 JobModel オブジェクトを取得します。
     *
     * @param id 取得する JobModel の ID
     * @return ID に対応する JobModel オブジェクトのリスト
     */
	@Override
	public ArrayList<JobModel> selectPublicId(int id) {
		return mapper.selectPublicId(id);
	}
}
