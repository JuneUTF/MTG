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
     * プランの目的です。
     */
    private String purpose;

    /**
     * プランの担当者です。
     */
    private String charge;
    /**
     * プランの状況です。
     */
    private String status;
}
