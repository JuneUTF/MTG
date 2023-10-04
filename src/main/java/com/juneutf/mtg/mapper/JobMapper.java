package com.juneutf.mtg.mapper;

import java.util.ArrayList;

import org.apache.ibatis.annotations.Mapper;

import com.juneutf.mtg.model.JobModel;
@Mapper
public interface JobMapper {
	ArrayList<JobModel> selectJobById (int id);
	int updateJobById(JobModel jobModel);
	int deleteJobById(JobModel jobModel);
	int sestoreJobById(JobModel jobModel);
	ArrayList<JobModel> selectJob();
}
