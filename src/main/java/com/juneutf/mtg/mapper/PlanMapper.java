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
     * 指定された日付(予約中状態）の予約を取得します。
     * @param date_plan 日付
     * @return PlanModelのリスト
     */
    ArrayList<JobModel> selectPlan();

    /**
     * 最大IDの予約を取得します(新しいID)。
     * @return PlanModelのリスト
     */
    ArrayList<JobModel> selectMaxIdPlan();
    /**
     * 指定された日付の予約を取得します。
     * @param date_plan 日付
     * @return PlanModelのリスト
     */
	ArrayList<JobModel> selectPlanAPI();
	/**
     * 指定された日付(予約中状態のID編集以外）の予約を取得します。
     * @return PlanModelのリスト
     */
	ArrayList<JobModel> selectPlanEdit(int id);
	/**
     * 公開担当者 JobModel オブジェクトを取得します。
     *
     * @param id 取得する JobModel の ID
     * @return ID に対応する JobModel オブジェクトのリスト
     */
    ArrayList<JobModel> selectPublicId(int id);
}
