package com.juneutf.mtg.mapper;

import java.util.ArrayList;

import org.apache.ibatis.annotations.Mapper;

import com.juneutf.mtg.model.OperationModel;

@Mapper
public interface OperationMapper {
	ArrayList<OperationModel> selectCharge();
	ArrayList<OperationModel> selectPurpose();
	int deleteByIdAndNameID(OperationModel model);
	int restoreByIdAndNameID(OperationModel model);
	int editByIdAndNameID(OperationModel model);
	int regByIdAndNameID(OperationModel model);

}
