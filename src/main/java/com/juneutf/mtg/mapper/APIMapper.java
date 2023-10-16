package com.juneutf.mtg.mapper;

import java.util.ArrayList;

import org.apache.ibatis.annotations.Mapper;

import com.juneutf.mtg.model.APIChargeModel;
import com.juneutf.mtg.model.APIPurposeModel;

/**
 * APIデータへのアクセス操作を提供するマッパーインターフェースです。
 */
@Mapper
public interface APIMapper {
    /**
     * APIの料金情報を取得します。
     * @return APIChargeModelのリスト
     */
    ArrayList<APIChargeModel> selectAPICharge();

    /**
     * APIの内容情報を取得します。
     * @return APIPurposeModelのリスト
     */
    ArrayList<APIPurposeModel> selectAPIPurpose();
}
