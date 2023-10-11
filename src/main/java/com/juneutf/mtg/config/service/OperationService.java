package com.juneutf.mtg.config.service;

import java.util.ArrayList;

import com.juneutf.mtg.model.OperationModel;

/**
 * OperationService インターフェースは、操作に関連するサービスメソッドを提供します。
 */
public interface OperationService {
    
    /**
     * 担当者のリストを取得します。
     *
     * @return 担当者のリスト
     */
    ArrayList<OperationModel> selectCharge();
    
    /**
     * 内容のリストを取得します。
     *
     * @return 内容のリスト
     */
    ArrayList<OperationModel> selectPurpose();
    
    /**
     * 指定されたIDおよび名前IDに基づいて操作を削除します。
     *
     * @param model 削除する操作のモデル
     * @return 操作が正常に削除された場合は1、それ以外の場合は0を返します。
     */
    int deleteByIdAndNameID(OperationModel model);
    
    /**
     * 指定されたIDおよび名前IDに基づいて操作を復元します。
     *
     * @param model 復元する操作のモデル
     * @return 操作が正常に復元された場合は1、それ以外の場合は0を返します。
     */
    int restoreByIdAndNameID(OperationModel model);
    
    /**
     * 指定されたIDおよび名前IDに基づいて操作を編集します。
     *
     * @param model 編集する操作のモデル
     * @return 操作が正常に編集された場合は1、それ以外の場合は0を返します。
     */
    int editByIdAndNameID(OperationModel model);
    
    /**
     * 操作を登録します。
     *
     * @param model 登録する操作のモデル
     * @return 操作が正常に登録された場合は1、それ以外の場合は0を返します。
     */
    int regByIdAndNameID(OperationModel model);
}
