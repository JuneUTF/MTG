package com.juneutf.mtg.iml;

import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.juneutf.mtg.mapper.APIMapper;
import com.juneutf.mtg.model.APIChargeModel;
import com.juneutf.mtg.model.APIPurposeModel;
import com.juneutf.mtg.service.APIService;

/**
 * このクラスは、APIServiceを実装しており、APIに関連するデータを提供します。
 */
@Service
public class APIImpl implements APIService {
    @Autowired
    private APIMapper mapper;

    /**
     * APIの担当者モデルを選択して返します。
     *
     * @return APIの担当者モデルのリスト
     */
    @Override
    public ArrayList<APIChargeModel> selectAPICharge() {
        return mapper.selectAPICharge();
    }

    /**
     * APIの内容モデルを選択して返します。
     *
     * @return APIの内容モデルのリスト
     */
    @Override
    public ArrayList<APIPurposeModel> selectAPIPurpose() {
        return mapper.selectAPIPurpose();
    }
}
