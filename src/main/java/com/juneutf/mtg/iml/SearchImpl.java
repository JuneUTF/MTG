package com.juneutf.mtg.iml;

import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.juneutf.mtg.config.service.SearchService;
import com.juneutf.mtg.mapper.SearchMapper;
import com.juneutf.mtg.model.JobModel;
import com.juneutf.mtg.model.SearchModel;
@Service
public class SearchImpl implements SearchService {
	@Autowired
	private SearchMapper mapper;

	@Override
	public ArrayList<JobModel> selectSearch(SearchModel model) {
		return mapper.selectSearch(model);
	}

}
