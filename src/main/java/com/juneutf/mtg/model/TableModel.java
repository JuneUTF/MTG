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
}
