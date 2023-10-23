package com.juneutf.mtg.model;

import lombok.Data;

/**
 * テーブルモデルクラスは、データベースのテーブル情報を表すためのクラスです。
 */
@Data
public class TableModel {
    /**
     * テーブルID。
     */
    private int id;

    /**
     * テーブルの名前です。
     */
    private String tableName;

    /**
     * テーブルに関連するテキスト情報です。
     */
    private String textEdit;
    /**
	 * ユーザー名
	 */
    private String username; 
    /**
	 * メールアドレス
	 */
    private String email; 
    /**
	 *パスワード
	 */
    private String password; 
    /**
	 * 名前
	 */
    private String fullname; 
    /**
     * 予約されたのidです。
     */
    private int publicid;
    /**
     * 権限です。
     */
    private String role;
    /**
     * 状況です。
     */
    private String status;
}
