package com.juneutf.mtg.mapper;

import java.util.ArrayList;

import org.apache.ibatis.annotations.Mapper;

import com.juneutf.mtg.model.JobModel;
import com.juneutf.mtg.model.SearchModel;

@Mapper
public interface SearchMapper {
	ArrayList<JobModel> selectSearch(SearchModel model);
}
