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
     *目的IDです。
     */
    private int purposeId;

    /**
     * 担当者IDです。
     */
    private int chargeId;
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
    private String purpose;

    /**
     * プランの担当者です。
     */
    private String charge;
    /**
     * 目的IDです。
     */
    /**
     * プランの状況です。
     */
    private String status;
}
