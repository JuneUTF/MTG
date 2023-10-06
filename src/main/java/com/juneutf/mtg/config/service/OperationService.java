package com.juneutf.mtg.config.service;

import java.util.ArrayList;

import com.juneutf.mtg.model.OperationModel;

public interface OperationService {
	ArrayList<OperationModel> selectCharge();
	ArrayList<OperationModel> selectPurpose();
	int deleteByIdAndNameID(OperationModel model);
	int restoreByIdAndNameID(OperationModel model);
	int editByIdAndNameID(OperationModel model);
	int regByIdAndNameID(OperationModel model);

}
