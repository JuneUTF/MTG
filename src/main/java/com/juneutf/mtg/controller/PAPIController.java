package com.juneutf.mtg.controller;

import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

import com.juneutf.mtg.model.APIChargeModel;
import com.juneutf.mtg.model.APIMessengerModel;
import com.juneutf.mtg.model.APIPurposeModel;
import com.juneutf.mtg.model.JobModel;
import com.juneutf.mtg.service.APIService;
import com.juneutf.mtg.service.PlanService;

import lombok.extern.slf4j.Slf4j;
/**
 * コントローラークラスです。
 */
@Slf4j
@Controller
public class PAPIController {
	@Autowired
	private APIService apiService;
	@Autowired
	private PlanService planService;
	/**
	 * '/charge'エンドポイントへのGETリクエストを処理し、（担当者項目）APIChargeModelオブジェクトを返します。
	 * @param APIMessengerModelオブジェクト
	 * @return ResponseEntity
	 */
	@GetMapping("/charge")
	public ResponseEntity<?> getCherge(APIMessengerModel apiModel){
		try {
			ArrayList<APIChargeModel> res = apiService.selectAPICharge();
			return ResponseEntity.status(200).body(res);
		} catch (Exception e) {
			apiModel.setIsData("false");
			log.warn("担当者のAPIを呼び出せない");
			return ResponseEntity.status(400).body(apiModel);
		}
	}
	/**
	 * '/purpose'エンドポイントへのGETリクエストを処理し、（内容項目）APIPurposeModelオブジェクトを返します。
	 * @param APIMessengerModelオブジェクト
	 * @return ResponseEntity
	 */
	@GetMapping("/purpose")
	public ResponseEntity<?> getPurpose(APIMessengerModel apiModel){
		try {
			ArrayList<APIPurposeModel> res = apiService.selectAPIPurpose();
			return ResponseEntity.status(200).body(res);
		} catch (Exception e) {
			apiModel.setIsData("false");
			log.warn("内容のAPIを呼び出せない");
			return ResponseEntity.status(400).body(apiModel);
		}
	}
	/**
	 * '/job'エンドポイントへのGETリクエストを処理し、(現在からの予約内容)PlanModelオブジェクトを返します。
	 * @param APIMessengerModelオブジェクト
	 * @return ResponseEntity
	 */
	@GetMapping("/job")
	public ResponseEntity<?> getJob(APIMessengerModel apiModel){
		try {
			ArrayList<JobModel> res = planService.selectPlan();
			return ResponseEntity.status(200).body(res);
		} catch (Exception e) {
			apiModel.setIsData("false");
			log.warn("JOBのAPIを呼び出せない");
			return ResponseEntity.status(400).body(apiModel);
		}
	}
}
