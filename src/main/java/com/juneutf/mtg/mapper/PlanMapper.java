package com.juneutf.mtg.mapper;

import java.util.ArrayList;

import org.apache.ibatis.annotations.Mapper;

import com.juneutf.mtg.model.JobModel;
import com.juneutf.mtg.model.PlanModel;

/**
 * 予約データへのアクセス操作を提供するマッパーインターフェースです。
 */
@Mapper
public interface PlanMapper {
    /**
     *新規予約を登録します。
     * @param planModel 予約モデル
     * @return 挿入の結果
     */
    int insertPlan(PlanModel planModel);

    /**
     * 指定された日付の予約を取得します。
     * @param date_plan 日付
     * @return PlanModelのリスト
     */
    ArrayList<JobModel> selectPlan();

    /**
     * 最大IDの予約を取得します(新しいID)。
     * @return PlanModelのリスト
     */
    ArrayList<JobModel> selectMaxIdPlan();
}
