package com.juneutf.mtg.controller;

import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import com.juneutf.mtg.model.APIChargeModel;
import com.juneutf.mtg.model.APIMessengerModel;
import com.juneutf.mtg.model.APIPurposeModel;
import com.juneutf.mtg.model.JobModel;
import com.juneutf.mtg.service.APIService;
import com.juneutf.mtg.service.ActionService;
import com.juneutf.mtg.service.PlanService;

import lombok.extern.slf4j.Slf4j;
/**
 * コントローラークラスです。
 */
@Slf4j
@Controller
public class APIController {
	@Autowired
	private APIService apiService;
	@Autowired
	private PlanService planService;
	@Autowired
	private ActionService actionService;
	@Autowired
    private SimpMessagingTemplate messagingTemplate;
	/**
	 * '/charge'エンドポイントへのGETリクエストを処理し、（担当者項目）APIChargeModelオブジェクトを返します。
	 * @param APIMessengerModelオブジェクト
	 * @return ResponseEntity
	 */
	@GetMapping("/charge")
	public ResponseEntity<?> getCherge(APIMessengerModel apiMessengerModel){
		try {
			ArrayList<APIChargeModel> res = apiService.selectAPICharge();
			return ResponseEntity.status(200).body(res);
		} catch (Exception e) {
			apiMessengerModel.setIsData("false");
			log.warn("担当者のAPIを呼び出せない");
			return ResponseEntity.status(400).body(apiMessengerModel);
		}
	}
	/**
	 * '/purpose'エンドポイントへのGETリクエストを処理し、（内容項目）APIPurposeModelオブジェクトを返します。
	 * @param APIMessengerModelオブジェクト
	 * @return ResponseEntity
	 */
	@GetMapping("/purpose")
	public ResponseEntity<?> getPurpose(APIMessengerModel apiMessengerModel){
		try {
			ArrayList<APIPurposeModel> res = apiService.selectAPIPurpose();
			return ResponseEntity.status(200).body(res);
		} catch (Exception e) {
			apiMessengerModel.setIsData("false");
			log.warn("内容のAPIを呼び出せない");
			return ResponseEntity.status(400).body(apiMessengerModel);
		}
	}
	/**
	 * '/job'エンドポイントへのGETリクエストを処理し、(現在からの予約内容)PlanModelオブジェクトを返します。
	 * @param APIMessengerModelオブジェクト
	 * @return ResponseEntity
	 */
	@GetMapping("/job")
	public ResponseEntity<?> getJob(APIMessengerModel apiMessengerModel){
		try {
			ArrayList<JobModel> res = planService.selectPlan();
			return ResponseEntity.status(200).body(res);
		} catch (Exception e) {
			apiMessengerModel.setIsData("false");
			log.warn("JOBのAPIを呼び出せない");
			return ResponseEntity.status(400).body(apiMessengerModel);
		}
	}
	/**
	 * 予約内容を完了するためのPOSTリクエストを処理します。
	 *
	 * @param 予約内容のID番号
	 * @return "redirect:/" - 完了後のリダイレクト
	 */
	@PostMapping("/kanryo")
	public ResponseEntity<?> setKanryo(@RequestBody APIMessengerModel apiMessengerModel) {
		try {
			//予約内容のID番号として完了行動
			int check = actionService.actionUpdateById(apiMessengerModel.getId());
			if(check == 1) {
				ArrayList<JobModel> job = planService.selectPlan();
				messagingTemplate.convertAndSend("/job/notification", job);				
			}
			//Websocket行動
			log.info("完了機能ID"+apiMessengerModel.getId()+"は完了しました。");
			apiMessengerModel.setIsData("true");
			return ResponseEntity.status(200).body("true");
		} catch (Exception e) {
			log.warn("完了機能ID"+apiMessengerModel.getId()+"はエラーが発生します。");
			apiMessengerModel.setIsData("false");
			return ResponseEntity.status(400).body(apiMessengerModel);
		}
	}
}
