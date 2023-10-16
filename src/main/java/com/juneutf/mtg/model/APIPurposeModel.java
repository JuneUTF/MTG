package com.juneutf.mtg.model;

import lombok.Data;
/**
 * APIの内容情報を表します。
 */
@Data
public class APIPurposeModel {
	/**
     * 内容ID。
     */
	private int id;
	/**
     * 内容名です。
     */
	private String purpose;
}
