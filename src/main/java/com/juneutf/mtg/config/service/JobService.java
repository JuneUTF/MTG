package com.juneutf.mtg.config.service;

import java.util.ArrayList;

import com.juneutf.mtg.model.JobModel;

public interface JobService {
	ArrayList<JobModel> selectJobById (int id);
	int updateJobById(JobModel jobModel);
	int deleteJobById(JobModel jobModel);
	int sestoreJobById(JobModel jobModel);
	ArrayList<JobModel> selectJob();
	
}
