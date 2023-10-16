package com.juneutf.mtg.service;

import java.util.ArrayList;

import com.juneutf.mtg.model.APIChargeModel;
import com.juneutf.mtg.model.APIPurposeModel;

/**
 * APIService インターフェースは、API関連のサービスメソッドを提供します。
 */
public interface APIService {
    
    /**
     * API料金モデルのリストを取得します。
     *
     * @return API担当者モデルのリスト
     */
    ArrayList<APIChargeModel> selectAPICharge();
    
    /**
     * API内容モデルのリストを取得します。
     *
     * @return API内容モデルのリスト
     */
    ArrayList<APIPurposeModel> selectAPIPurpose();
}
