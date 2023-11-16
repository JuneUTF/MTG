package com.juneutf.mtg.model;

import lombok.Data;
/**
 * APIのメッセージ情報を表します。
 */
@Data
public class APIMessengerModel {
	private int id;
	/**
	 * 処理できる場合はtrue、処理エラー場合はfalse
	 */
	private String isData;
}
