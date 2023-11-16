package com.juneutf.mtg.model;

import lombok.Data;

@Data
public class SearchModel {
	/**
     * 検索の開始日です。
     */
	private String date_start;
	/**
     * 検索の終了日です。
     */
	private String date_end;
    /**
     *内容IDです。
     */
    private int purposeId;

    /**
     * 担当者IDです。
     */
    private int chargeId;
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
    /**
     * 予約の状況です。
     */
    private String status;
}
