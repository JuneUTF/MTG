package com.juneutf.mtg.config.service;

import java.util.ArrayList;

import com.juneutf.mtg.model.JobModel;
import com.juneutf.mtg.model.SearchModel;

public interface SearchService {
	ArrayList<JobModel> selectSearch(SearchModel model);
}
