package com.juneutf.mtg.mapper;

import java.util.ArrayList;
import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.juneutf.mtg.model.OperationModel;
import com.juneutf.mtg.model.TableModel;

/**
 * このマッパーは、OperationModel オブジェクトに関するデータベース操作を提供します。
 */
@Mapper
public interface OperationMapper {

    /**
     * チャージ関連の OperationModel オブジェクトを取得します。
     * @return チャージ関連の OperationModel オブジェクトのリスト
     */
    ArrayList<OperationModel> selectCharge();

    /**
     * 目的関連の OperationModel オブジェクトを取得します。
     * @return 目的関連の OperationModel オブジェクトのリスト
     */
    ArrayList<OperationModel> selectPurpose();

    /**
     * 指定されたモデルの ID および tableName に基づいてデータを削除します。
     * @param TableModel 削除対象のデータを指定する TableModel オブジェクト
     * @return 削除が成功した場合は 1、失敗した場合は 0
     */
    int deleteByIdAndtableName(TableModel model);

    /**
     * 指定されたモデルの ID および tableName に基づいてデータを復元します。
     * @param TableModel 復元対象のデータを指定する TableModel オブジェクト
     * @return 復元が成功した場合は 1、失敗した場合は 0
     */
    int restoreByIdAndtableName(TableModel model);

    /**
     * 指定されたモデルの ID および tableName に基づいてデータを編集します。
     * @param TableModel 編集対象のデータを指定する TableModel オブジェクト
     * @return 編集が成功した場合は 1、失敗した場合は 0
     */
    int editByIdAndtableName(TableModel model);

    /**
     * 指定されたモデルの ID および tableName に新しいデータを登録します。
     * @param TableModel 登録対象のデータを指定する TableModel オブジェクト
     * @return 登録が成功した場合は 1、失敗した場合は 0
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
