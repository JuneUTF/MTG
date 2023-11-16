package com.juneutf.mtg.controller;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.juneutf.mtg.config.vender.CustomUser;
import com.juneutf.mtg.config.vender.GetIntoDay;
import com.juneutf.mtg.model.APIChargeModel;
import com.juneutf.mtg.model.APIMessengerModel;
import com.juneutf.mtg.model.APIPurposeModel;
import com.juneutf.mtg.model.JobModel;
import com.juneutf.mtg.model.SearchModel;
import com.juneutf.mtg.service.APIService;
import com.juneutf.mtg.service.JobService;
import com.juneutf.mtg.service.PlanService;
import com.juneutf.mtg.service.SearchService;

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
	@Autowired
	private APIService apiService;
	@Autowired
	private GetIntoDay getIntoDay;
	/**
	 * '/job'エンドポイントへのGETリクエストを処理し、(現在からの予約内容)PlanModelオブジェクトを返します。
	 * @param APIMessengerModelオブジェクト
	 * @return ResponseEntity
	 */
	@GetMapping("/jobAPI")
	public ResponseEntity<?> getJob(APIMessengerModel apiMessengerModel){
		try {
			ArrayList<JobModel> res = planService.selectPlanAPI();
			return ResponseEntity.status(200).body(res);
		} catch (Exception e) {
			apiMessengerModel.setIsData("false");
			log.warn("JOBのAPIを呼び出せない");
			return ResponseEntity.status(400).body(apiMessengerModel);
		}
	}
	/**
	 * '/getjobapi'エンドポイントへのGETリクエストを処理し、(現在から予約内容のID編集以外)PlanModelオブジェクトを返します。
	 * @param APIMessengerModelオブジェクト
	 * @return ResponseEntity
	 */
	@GetMapping("/getjobapi")
	public ResponseEntity<?> getJob(@RequestParam  int id,APIMessengerModel apiMessengerModel){
		try {
			ArrayList<JobModel> res = planService.selectPlanEdit(id);
			return ResponseEntity.status(200).body(res);
		} catch (Exception e) {
			apiMessengerModel.setIsData("false");
			log.warn("JOBのAPIを呼び出せない");
			return ResponseEntity.status(400).body(apiMessengerModel);
		}
	}
	/**
	 * 予約内容画面を取得するGETメソッド。
	 * 
	 * @return 予約内容画面のテンプレート名
	 */
	@GetMapping("/job")
	public String getJob(Model model) {
		UserDetails userDetails = (UserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        CustomUser customUser = (CustomUser) userDetails;
        model.addAttribute("fullName", customUser.getFullName()+"."+customUser.getAuthorities());
		return "job/job";
	}

	/**
	 * 予約内容編集画面を取得するGETメソッド。
	 *
	 * @param model    モデルオブジェクト
	 * @param jobModel 予約モデル
	 * @return 予約内容編集画面のテンプレート名
	 */
	@SuppressWarnings("null")
	@GetMapping("job/edit")
	public String getEditID(Model model, JobModel jobModel) {
		try {
			UserDetails userDetails = (UserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
	        CustomUser customUser = (CustomUser) userDetails;
	        Collection<? extends GrantedAuthority> authorities = userDetails.getAuthorities();
	     // 権限承認
	        String role = null;
	     for (GrantedAuthority authority : authorities) {
	         role = authority.getAuthority();
	     }
	     	model.addAttribute("role",role);
			// IDを検証
			// IDがない場合、予約内容に遷移
			if (jobModel.getId() == 0) {
				return "redirect:/kk/job";
			} else {
				// IDがある場合、IDで予約内容を取得
				ArrayList<JobModel> job = jobService.selectJobById(jobModel.getId());
				ArrayList<APIPurposeModel> purpose = apiService.selectAPIPurpose(job.get(0).getPurposeId());
		         model.addAttribute("purpose", purpose); 
		         if(customUser.getAuthorities().toString().equals("[ROLE_ADMIN]")) {
		         	ArrayList<APIChargeModel> jobadmin = apiService.selectAPICharge(job.get(0).getChargeId());
		         	model.addAttribute("charge", jobadmin);    
		         }else {
		         	ArrayList<JobModel> jobuser = planService.selectPublicId(customUser.getId());
		         	model.addAttribute("charge", jobuser);            	
		 		}
				// 取得した予約内容の比較
				// 予約内容がある
				if (job.size()== 1 && (customUser.getFullName().equals(job.get(0).getCharge()) || role.equals("ROLE_ADMIN")) || job.get(0).getRegisterid() == customUser.getId()) {
					// 内容を画面に渡す
					model.addAttribute("job", job);
					return "job/edit";
				} else {
					log.warn("編集予約内容機能で権限がない");
					return "error";
				}
			}
		} catch (Exception e) {
			log.warn("編集予約内容機能はエラーが発生します。");
			return "error";
		}
	}

	/**
	 * 予約内容をアップデートするPOSTメソッド。
	 *
	 * @param jobModel 予約モデル
	 * @param model    モデルオブジェクト
	 * @return リダイレクトまたはエラーページのテンプレート名
	 */
	@PostMapping("job")
	public String postEditID(JobModel jobModel, Model model) {
		try {
			//曜日を設定
			String toDayString = getIntoDay.setDay(jobModel.getDate_plan(), jobModel.getDate_day());
			jobModel.setDate_day(toDayString);
			// データベース内の予約内容が変更された結果
			int jobUpdate = jobService.updateJobById(jobModel);
			log.info("予約内容の編集情報：" + jobModel);
			// 予約内容が正常にアップデートされ、IDの上で画面に遷移
			if (jobUpdate == 1) {
				// Websocket行動
				ArrayList<JobModel> job = planService.selectPlan();
				messagingTemplate.convertAndSend("/job/notification", job);
				return "redirect:/kk/getjob?id=" + jobModel.getId();
			} else {
				log.warn("編集予約内容機能はエラーが発生します。");
				return "error";
			}
		} catch (Exception e) {
			log.warn("編集予約内容機能はエラーが発生します。");
			return "error";
		}
	}

	/**
	 * 予約内容画面を取得するGETメソッド。
	 *
	 * @param model    モデルオブジェクト
	 * @param jobModel 予約モデル
	 * @return 予約内容画面のテンプレート名
	 */
	@GetMapping("/getjob")
	public String getJobID(Model model, JobModel jobModel) {
		try {
			UserDetails userDetails = (UserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
	        CustomUser customUser = (CustomUser) userDetails;
	        model.addAttribute("fullName", customUser.getFullName()+"."+customUser.getAuthorities());
			// IDがある場合、IDで予約内容を取得
			ArrayList<JobModel> job = jobService.selectJobById(jobModel.getId());
			// 取得した予約内容の比較
			// 予約内容がある
			if (job.size() == 1) {
				SimpleDateFormat inputFormat = new SimpleDateFormat("yyyy-MM-dd");
				SimpleDateFormat outputFormat = new SimpleDateFormat("yyyy年MM月dd日");
				Date datecover = inputFormat.parse(job.get(0).getDate_plan());
				job.get(0).setDate_day(outputFormat.format(datecover) + " (" + job.get(0).getDate_day() + ")");
				// 内容を画面に渡す
				model.addAttribute("job", job);
				return "job/getjob";
			} else {
				// 予約内容がない場合はエラー画面を表示
				log.warn("ID:" + jobModel.getId() + "として予約内容が取得できません。");
				return "error";

			}
		} catch (Exception e) {
			log.warn("エラーが発生しました。");
			return "error";
		}
	}

	/**
	 * 予約内容を削除するPOSTメソッド。
	 *
	 * @param jobModel 予約モデル
	 * @param model    モデルオブジェクト
	 * @return リダイレクトまたはエラーページのテンプレート名
	 */
	@PostMapping("job/delete")
	public String postDeleteID(JobModel jobModel, Model model) {
		// データベース内の予約内容が変更された結果
		int jobUpdate = jobService.deleteJobById(jobModel);
		log.info("予約内容の削除情報：" + jobModel);
		// 予約内容が正常に削除され、IDの上で画面に遷移
		if (jobUpdate == 1) {
			// Websocket行動
			ArrayList<JobModel> job = planService.selectPlan();
			messagingTemplate.convertAndSend("/job/notification", job);
			return "redirect:/kk/job";
		} else {
			return "redirect:/kk/job?id=" + jobModel.getId();
		}
	}

	/**
	 * 予約内容を復元するPOSTメソッド。
	 *
	 * @param jobModel 予約モデル
	 * @param model    モデルオブジェクト
	 * @return リダイレクトまたはエラーページのテンプレート名
	 */
	@PostMapping("job/restore")
	public String postRestoreID(JobModel jobModel, Model model) {
		// データベース内の予約内容が変更された結果
		int jobUpdate = jobService.restoreJobById(jobModel);
		log.info("予約内容の復元情報：" + jobModel);
		// 予約内容が正常に復元され、IDの上で画面に遷移
		if (jobUpdate == 1) {
			// Websocket行動
			ArrayList<JobModel> job = planService.selectPlan();
			messagingTemplate.convertAndSend("/job/notification", job);
			return "redirect:/kk/job";
		} else {
			return "redirect:/kk/job?id=" + jobModel.getId();
		}
	}

	/**
	 * 予約内容を検索するPOSTメソッド。
	 *
	 * @param SearchModel       検索モデル
	 * @param APIMessengerModel メッセージモデル
	 * @return 予約内容リストまたはエラーメッセージ
	 */
	@PostMapping("job/searchAPI")
	public ResponseEntity<?> postSearchAPI(@RequestBody SearchModel searchModel, APIMessengerModel apiMessengerModel) {
		try {
			ArrayList<JobModel> job = searchService.selectSearch(searchModel);
			return ResponseEntity.status(200).body(job);
		} catch (Exception e) {
			apiMessengerModel.setIsData("false");
			log.warn("検索機能はエラーが発生します。");
			return ResponseEntity.status(400).body(apiMessengerModel);
		}
	}
}
