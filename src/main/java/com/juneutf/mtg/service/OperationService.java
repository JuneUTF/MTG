package com.juneutf.mtg.service;

import java.util.ArrayList;
import java.util.List;

import com.juneutf.mtg.model.OperationModel;
import com.juneutf.mtg.model.TableModel;

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
    int deleteByIdAndtableName(TableModel model);
    
    /**
     * 指定されたIDおよび名前IDに基づいて操作を復元します。
     *
     * @param model 復元する操作のモデル
     * @return 操作が正常に復元された場合は1、それ以外の場合は0を返します。
     */
    int restoreByIdAndtableName(TableModel model);
    
    /**
     * 指定されたIDおよび名前IDに基づいて操作を編集します。
     *
     * @param model 編集する操作のモデル
     * @return 操作が正常に編集された場合は1、それ以外の場合は0を返します。
     */
    int editByIdAndtableName(TableModel model);
    
    /**
     * 操作を登録します。
     *
     * @param model 登録する操作のモデル
     * @return 操作が正常に登録された場合は1、それ以外の場合は0を返します。
     */
    int regByIdAndtableName(TableModel model);
    
    /**
     * 内容新規登録の業務検証
     * @param TableModel 登録対象のデータを指定する TableModel オブジェクト
     * @return 存在場合はTrue、存在ない場合はFalse.
     */
     List<TableModel> checkUserNameAndEmail(TableModel model);
     /**
      * 内容新規登録の業務検証
      * @param TableModel 登録対象のデータを指定する TableModel オブジェクト
      * @return 存在場合はTrue、存在ない場合はFalse.
      */
      List<TableModel> checkPurpose(TableModel model);
}
