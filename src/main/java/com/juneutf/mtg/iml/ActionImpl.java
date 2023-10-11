package com.juneutf.mtg.iml;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.juneutf.mtg.config.service.ActionService;
import com.juneutf.mtg.mapper.ActionMapper;

@Service
public class ActionImpl implements ActionService {
	@Autowired
	private ActionMapper mapper;

	@Override
	public int actionUpdateById(int id) {
		return mapper.actionUpdateById(id);
	}

}
