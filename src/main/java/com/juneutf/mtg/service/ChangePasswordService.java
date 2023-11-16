package com.juneutf.mtg.service;

import java.util.List;

import com.juneutf.mtg.model.ChangePasswordModel;

public interface ChangePasswordService {
	List<ChangePasswordModel> selectPasswordByUsername (ChangePasswordModel model);
	int setNewPassword (ChangePasswordModel model);
}
