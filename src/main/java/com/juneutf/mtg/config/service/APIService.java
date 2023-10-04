package com.juneutf.mtg.config.service;

import java.util.ArrayList;

import com.juneutf.mtg.model.APIChargeModel;
import com.juneutf.mtg.model.APIPurposeModel;

public interface APIService {
	ArrayList<APIChargeModel> selectAPICharge();
	ArrayList<APIPurposeModel> selectAPIPurpose();
}
