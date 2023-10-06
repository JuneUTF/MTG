package com.juneutf.mtg.controller;

import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.juneutf.mtg.config.service.JobService;
import com.juneutf.mtg.config.service.PlanService;
import com.juneutf.mtg.config.service.SearchService;
import com.juneutf.mtg.model.JobModel;
import com.juneutf.mtg.model.PlanModel;
import com.juneutf.mtg.model.SearchModel;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Controller
@RequestMapping("/kk")
public class JobController {
	@Autowired
	private SearchService searchService;
	@Autowired 
	private PlanService planService;
	@Autowired 
	private JobService jobService;
	@Autowired
    private SimpMessagingTemplate messagingTemplate;
	@GetMapping("/job")
	public String getJob(Model model,JobModel jobModel) {
		try {
			//idを検証
			//ID がない場合今日より予約内容を取得
			if(jobModel.getId() ==0) {
				ArrayList<JobModel> job = jobService.selectJob();
				model.addAttribute("job", job);
				return "job/job";
			}else {
				//IDがある場合IDで予約内容を取得
				ArrayList<JobModel> job = jobService.selectJobById(jobModel.getId());
				//取得した予約内容の比較
				//予約内容がある
				if(job.size()!=0) {
					//ジョブを画面に渡す
				model.addAttribute("job", job);
				return "job/job";
				}else {
					//予約内容がない場合はエラー画面を表示されます。
					return "error";
				}
			}
		} catch (Exception e) {
			return "error";
		}
	}
	@GetMapping("job/edit")
	public String getEditID(Model model,JobModel jobModel) {
		try {
			//idを検証
			//ID がない場合予約内容に遷移
			if(jobModel.getId() ==0) {
				return "redirect:/kk/job";
			}else {
				//IDがある場合IDで予約内容を取得
				ArrayList<JobModel> job = jobService.selectJobById(jobModel.getId());
				//取得した予約内容の比較
				//予約内容がある
				if(job.size()!=0) {
					//ジョブを画面に渡す
				model.addAttribute("job", job);
				return "job/edit";
				}else {
					//予約内容がない場合はエラー画面を表示されます。
					return "error";
				}
			}
		} catch (Exception e) {
			// TODO: handle exception
		}
		return "job/edit";
	}
	/**
	 * 予約内容がアップデート*/
	@PostMapping("job")
	public String postEditID(JobModel jobModel,Model model) {
		//データベース内に予約内容が変更の結果
		int jobUpdate = jobService.updateJobById(jobModel);
		//予約内容が完成してIDの上で画面に遷移
		if(jobUpdate==1) {
			//Websocket行動
			ArrayList<JobModel> job = planService.selectPlan();
			messagingTemplate.convertAndSend("/job/notification", job);
			return "redirect:/kk/job?id="+jobModel.getId();
		}else {
			return "redirect:/kk/job/edit?id="+jobModel.getId();
		}
		
	}
	/**
	 * 予約内容が削除*/
	@PostMapping("job/delete")
	public String postDeleteID(JobModel jobModel,Model model) {
		//データベース内に予約内容が変更の結果
		int jobUpdate = jobService.deleteJobById(jobModel);
		//予約内容が完成してIDの上で画面に遷移
		if(jobUpdate==1) {
			//Websocket行動
			ArrayList<JobModel> job = planService.selectPlan();
			messagingTemplate.convertAndSend("/job/notification", job);
			return "redirect:/kk/job";
		}else {
			return "redirect:/kk/job?id="+jobModel.getId();
		}
	}
	/**
	 * 予約内容が復元*/
	@PostMapping("job/restore")
	public String postRestoreID(JobModel jobModel,Model model) {
		//データベース内に予約内容が変更の結果
		int jobUpdate = jobService.sestoreJobById(jobModel);
		//予約内容が完成してIDの上で画面に遷移
		if(jobUpdate==1) {
			//Websocket行動
			ArrayList<JobModel> job = planService.selectPlan();
			messagingTemplate.convertAndSend("/job/notification", job);
			return "redirect:/kk/job";
		}else {
			return "redirect:/kk/job?id="+jobModel.getId();
		}
	}

	/**
	 * 予約内容の検索*/
	@PostMapping("job/search")
	public String postSearch(SearchModel searchModel,Model model) {
		System.out.println(searchModel);
		ArrayList<JobModel> job = searchService.selectSearch(searchModel);
		System.out.println(job);
		model.addAttribute("job", job);
		return "job/search";
	}
}
