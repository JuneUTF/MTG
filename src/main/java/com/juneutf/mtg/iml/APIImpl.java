package com.juneutf.mtg.iml;

import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.juneutf.mtg.config.service.APIService;
import com.juneutf.mtg.mapper.APIMapper;
import com.juneutf.mtg.model.APIChargeModel;
import com.juneutf.mtg.model.APIPurposeModel;

/**
 * APIサービスの実装クラスです。
 */
/**
 * {@link APIService} インターフェースの実装を提供します。
 */
@Service
public class APIImpl implements APIService {
	@Autowired
	private APIMapper mapper;

	@Override
	public ArrayList<APIChargeModel> selectAPICharge() {
		return mapper.selectAPICharge();
	}

	@Override
	public ArrayList<APIPurposeModel> selectAPIPurpose() {
		return mapper.selectAPIPurpose();
	}
}
