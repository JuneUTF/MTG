package com.juneutf.mtg.controller;

import java.util.ArrayList;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.juneutf.mtg.config.service.PlanService;
import com.juneutf.mtg.model.PlanModel;

import lombok.extern.slf4j.Slf4j;
@Slf4j
@Controller
@RequestMapping("/kk")
public class PlanController {
	@Autowired
	private PlanService planService;
	@Autowired
    private SimpMessagingTemplate messagingTemplate;
	@GetMapping()
	public String getIndex(Model model) {
		//Websocket行動
		ArrayList<PlanModel> job = planService.selectPlan();
		model.addAttribute("job", job);
		return "plan/plan";
	}
	@GetMapping("/plan")
	public String getPlan(Model model) {
		//Websocket行動
		ArrayList<PlanModel> job = planService.selectPlan();
		model.addAttribute("job", job);
		return "plan/plan";
	}
	@PostMapping("/plan")
	public String plan(@Valid PlanModel planModel,BindingResult result,Model model) {
		try {
			if(result.hasErrors()) {
				model.addAttribute("msg",result.getAllErrors().get(0).getDefaultMessage());
				return "plan/plan";
			}
			//データベースに登録
			int insertPlan =planService.insertPlan(planModel);
			if(insertPlan == 0) {
				log.warn("予約内容をデータベースに登録できない！");
				return "error";
			}
			//Websocket行動
			ArrayList<PlanModel> job = planService.selectPlan();
			messagingTemplate.convertAndSend("/job/notification", job);
			int nowJob = planService.selectMaxIdPlan().get(0).getMAX();
			return "redirect:/kk/job?id="+nowJob; 
		} catch (Exception e) {
			log.warn("新規登録予約はエラーが発生しました。");
			return "error";
		}
	}
	
}
