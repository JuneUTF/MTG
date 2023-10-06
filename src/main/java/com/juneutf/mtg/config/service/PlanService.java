package com.juneutf.mtg.config.service;

import java.util.ArrayList;

import com.juneutf.mtg.model.JobModel;
import com.juneutf.mtg.model.PlanModel;

/**
 * プランデータへのアクセス操作を提供するサービスインターフェースです。
 */
public interface PlanService {
    /**
     * 新規予約を登録します。
     * @param planModel プランモデル
     * @return 挿入の結果
     */
    int insertPlan(PlanModel planModel);

    /**
     * 指定された日付のプランを取得します。
     * @param date_plan 日付
     * @return PlanModelのリスト
     */
    ArrayList<JobModel> selectPlan();

    /**
     * 最大IDのプランを取得します(新しいID)。
     * @return PlanModelのリスト
     */
    ArrayList<JobModel> selectMaxIdPlan();
}
