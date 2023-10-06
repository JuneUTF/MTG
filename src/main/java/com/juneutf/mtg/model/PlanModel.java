package com.juneutf.mtg.model;

import lombok.Data;

/**
 * プラン情報を表すデータモデルクラスです。
 */
@Data
public class PlanModel {
    /**
     * プランの一意の識別子です。
     */
    private int id;
    /**
     * プランの曜日です。
     */
    private String date_day;
    /**
     * プランの計画日です。
     */
    private String date_plan;

    /**
     * プランの開始時間です。
     */
    private String time_start;

    /**
     * プランの終了時間です。
     */
    private String time_end;

    /**
     * プランの目的です。
     */
    private int purpose;

    /**
     * プランの担当者です。
     */
    private int charge;
    /**
     * プランの状況です。
     */
    private String status;
}
