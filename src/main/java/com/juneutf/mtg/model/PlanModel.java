package com.juneutf.mtg.model;

import lombok.Data;

/**
 * 予約情報を表すデータモデルクラスです。
 */
@Data
public class PlanModel {
    /**
     * 予約ID。
     */
    private int id;
    /**
     * 予約の曜日です。
     */
    private String date_day;
    /**
     * 予約の計画日です。
     */
    private String date_plan;

    /**
     * 予約の開始時間です。
     */
    private String time_start;

    /**
     * 予約の終了時間です。
     */
    private String time_end;

    /**
     * 予約の内容です。
     */
    private int purpose;
    /**
     * 予約の担当者です。
     */
    private int charge;

    /**
     * 予約されたのidです。
     */
    private int registerid;
    /**
     * 予約の状況です。
     */
    private String status;
}
