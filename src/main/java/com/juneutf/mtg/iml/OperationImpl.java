package com.juneutf.mtg.iml;

import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.juneutf.mtg.config.service.OperationService;
import com.juneutf.mtg.mapper.OperationMapper;
import com.juneutf.mtg.model.OperationModel;

@Service
public class OperationImpl implements OperationService{
	@Autowired
	private OperationMapper mapper;
	@Override
	public ArrayList<OperationModel> selectCharge() {
		return mapper.selectCharge();
	}

	@Override
	public ArrayList<OperationModel> selectPurpose() {
		return mapper.selectPurpose();
	}

	@Override
	public int deleteByIdAndNameID(OperationModel model) {
		return mapper.deleteByIdAndNameID(model);
	}

	@Override
	public int restoreByIdAndNameID(OperationModel model) {
		return mapper.restoreByIdAndNameID(model);
	}

	@Override
	public int editByIdAndNameID(OperationModel model) {
		return mapper.editByIdAndNameID(model);
	}

	@Override
	public int regByIdAndNameID(OperationModel model) {
		return mapper.regByIdAndNameID(model);
	}

}
