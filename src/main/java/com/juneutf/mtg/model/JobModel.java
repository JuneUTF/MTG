package com.juneutf.mtg.model;

import lombok.Data;

/**
 * 予約情報を表すデータモデルクラスです。
 */
@Data
public class JobModel {
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
    private String purpose;

    /**
     * 予約の担当者です。
     */
    private String charge;
    /**
     * 内容IDです。
     */
    private int purposeId;

    /**
     * 担当者IDです。
     */
    private int chargeId;
    /**
     * 予約の状況です。
     */
    private String status;
    /**
     * ID最大です。
     */
    private int MAX;
}
