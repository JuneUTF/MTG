package com.juneutf.mtg.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.juneutf.mtg.model.ChangePasswordModel;
@Mapper
public interface ChangePasswordMapper {
	List<ChangePasswordModel> selectPasswordByUsername (ChangePasswordModel model);
	int setNewPassword (ChangePasswordModel model);
}
