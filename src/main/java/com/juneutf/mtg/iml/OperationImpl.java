package com.juneutf.mtg.iml;

import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.juneutf.mtg.mapper.OperationMapper;
import com.juneutf.mtg.model.OperationModel;
import com.juneutf.mtg.model.TableModel;
import com.juneutf.mtg.service.OperationService;

@Service
public class OperationImpl implements OperationService{
    @Autowired
    private OperationMapper mapper;
    
    /**
     * 担当者に関連する操作モデルのリストを取得します。
     *
     * @return 担当者リスト
     */
    @Override
    public ArrayList<OperationModel> selectCharge() {
        return mapper.selectCharge();
    }

    /**
     * 内容に関連する操作モデルのリストを取得します。
     *
     * @return 内容リスト
     */
    @Override
    public ArrayList<OperationModel> selectPurpose() {
        return mapper.selectPurpose();
    }

    /**
     * 指定されたテーブルモデルのIDと名前IDに基づいてデータを削除します。
     *
     * @param TableModel テーブルモデル
     * @return 削除された行数
     */
    @Override
    public int deleteByIdAndtableName(TableModel model) {
        return mapper.deleteByIdAndtableName(model);
    }

    /**
     * 指定されたテーブルモデルのIDと名前IDに基づいてデータを復元します。
     *
     * @param TableModel テーブルモデル
     * @return 復元された行数
     */
    @Override
    public int restoreByIdAndtableName(TableModel model) {
        return mapper.restoreByIdAndtableName(model);
    }

    /**
     * 指定されたテーブルモデルのIDと名前IDに基づいてデータを編集します。
     *
     * @param TableModel テーブルモデル
     * @return 編集された行数
     */
    @Override
    public int editByIdAndtableName(TableModel model) {
        return mapper.editByIdAndtableName(model);
    }

    /**
     * 指定されたテーブルモデルのIDと名前IDに基づいてデータを登録します。
     *
     * @param TableModel テーブルモデル
     * @return 登録された行数
     */
    @Override
    public int regByIdAndtableName(TableModel model) {
        return mapper.regByIdAndtableName(model);
    }
}
