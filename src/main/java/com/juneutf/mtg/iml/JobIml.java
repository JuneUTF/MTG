package com.juneutf.mtg.iml;

import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.juneutf.mtg.config.service.JobService;
import com.juneutf.mtg.mapper.JobMapper;
import com.juneutf.mtg.model.JobModel;
@Service
public class JobIml implements JobService{
	@Autowired
	private JobMapper mapper;
	@Override
	public ArrayList<JobModel> selectJobById(int id) {
		return mapper.selectJobById(id);
	}
	@Override
	public int updateJobById(JobModel jobModel) {
		return mapper.updateJobById(jobModel);
	}
	@Override
	public int deleteJobById(JobModel jobModel) {
		return mapper.deleteJobById(jobModel);
	}
	@Override
	public ArrayList<JobModel> selectJob() {
		return mapper.selectJob();
	}
	@Override
	public int restoreJobById(JobModel jobModel) {
		return mapper.restoreJobById(jobModel);
	}
	
}
