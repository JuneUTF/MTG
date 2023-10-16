package com.juneutf.mtg.model;

/**
 * 操作モデルクラスはデータベースの操作情報を表します。
 */
import lombok.Data;

@Data
public class OperationModel {
    /**
     * 操作ID
     */
    private int id;

    /**
     * 操作が実行されたテーブルの名前です。
     */
    private String tableName;

    /**
     * 操作の実行状態を示す文字列です。
     */
    private String status;
}
