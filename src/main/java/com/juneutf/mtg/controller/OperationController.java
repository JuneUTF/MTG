package com.juneutf.mtg.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.juneutf.mtg.config.vender.CoverRegister;
import com.juneutf.mtg.config.vender.EmailService;
import com.juneutf.mtg.model.APIMessengerModel;
import com.juneutf.mtg.model.OperationModel;
import com.juneutf.mtg.model.TableModel;
import com.juneutf.mtg.service.OperationService;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Controller
@RequestMapping("/ad")
public class OperationController {
	@Autowired
	private OperationService operationService;
	@Autowired
	private CoverRegister coverRegister;
	@Autowired
	private PasswordEncoder encoder;
	@Autowired
	private EmailService emailService;
	/**
	 * '/charge'エンドポイントへのGETリクエストを処理し、（担当者項目）APIChargeModelオブジェクトを返します。
	 * @param APIMessengerModelオブジェクト
	 * @return ResponseEntity
	 */
	@GetMapping("/charge")
	public ResponseEntity<?> getCherge(APIMessengerModel apiMessengerModel){
		try {
			ArrayList<OperationModel> res = operationService.selectCharge();
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
			ArrayList<OperationModel> res = operationService.selectPurpose();
			return ResponseEntity.status(200).body(res);
		} catch (Exception e) {
			apiMessengerModel.setIsData("false");
			log.warn("内容のAPIを呼び出せない");
			return ResponseEntity.status(400).body(apiMessengerModel);
		}
	}
	
	/**
	 * '/chargeapi'エンドポイントへのGETリクエストを処理し、Email　チェック。
	 * @param APIMessengerModelオブジェクト
	 * @return ResponseEntity(ある場合true and false)
	 */
	@GetMapping("/chargeapi")
	public ResponseEntity<?> getChargeAPI(@RequestParam String email ,APIMessengerModel apiMessengerModel){
		try {
			//データセット
			TableModel tableModel =new TableModel();
			tableModel.setEmail(email);
			tableModel.setUsername(coverRegister.getUsername(email));
			List<TableModel> res = operationService.checkUserNameAndEmail(tableModel);
			//データベース内にある場合
			if(res.size()>0) {
				apiMessengerModel.setIsData("false");
				return ResponseEntity.status(200).body(apiMessengerModel);
			}else {
			//ない場合
				apiMessengerModel.setIsData("true");
				return ResponseEntity.status(200).body(apiMessengerModel);				
			}
		} catch (Exception e) {
			apiMessengerModel.setIsData("false");
			log.warn("内容のAPIを呼び出せない");
			return ResponseEntity.status(400).body(apiMessengerModel);
		}
	}
	
	/**
	 * '/purposeapi'エンドポイントへのGETリクエストを処理し、内容　チェック。
	 * @param APIMessengerModelオブジェクト
	 * @return ResponseEntity(ある場合true and false)
	 */
	@GetMapping("/purposeapi")
	public ResponseEntity<?> getPurposeAPI(@RequestParam String textEdit ,APIMessengerModel apiMessengerModel){
		try {
			//データセット
			TableModel tableModel =new TableModel();
			tableModel.setTextEdit(textEdit);
			List<TableModel> res = operationService.checkUserNameAndEmail(tableModel);
			//データベース内にある場合
			if(res.size()>0) {
				apiMessengerModel.setIsData("false");
				return ResponseEntity.status(200).body(apiMessengerModel);
			}else {
			//ない場合
				apiMessengerModel.setIsData("true");
				return ResponseEntity.status(200).body(apiMessengerModel);				
			}
		} catch (Exception e) {
			apiMessengerModel.setIsData("false");
			log.warn("内容のAPIを呼び出せない");
			return ResponseEntity.status(400).body(apiMessengerModel);
		}
	}
	/**
	 * 操作情報を取得するGETメソッド（担当者リスト・内容リストを渡す）。
	 * @param model モデル
	 * @return 操作情報の表示用テンプレート名
	 */
	@GetMapping("/operation")
	public String getOperation(Model model) {
		try {
			return "operation/index";
		} catch (Exception e) {
			log.warn("GetMapping operationにエラー発生します。");
			return "error";
		}
	}

	/**
	 * 操作情報を削除するPOSTメソッド。
	 * @param TableModel 操作モデル
	 * @return 操作情報の表示用テンプレート名
	 */
	@PostMapping("/operation/delete")
	public String postOperationDelete(TableModel tableModel) {
		try {
			//tableNameのIDとして削除
			if(tableModel.getId() == 1 &&  tableModel.getTableName().equals("user_infor")) {
				log.warn("スーパー管理者が削除出来ない。");
				return null;
			}
			operationService.deleteByIdAndtableName(tableModel);
			log.info("削除情報：" + tableModel);
			return "redirect:/ad/operation";
		} catch (Exception e) {
			log.warn("削除情報失敗：" +tableModel);
			return "error";
		}
	}

	/**
	 * 操作情報を復元するPOSTメソッド。
	 * @param TableModel 操作モデル
	 * @return 操作情報の表示用テンプレート名
	 */
	@PostMapping("/operation/restore")
	public String postOperationRestore(TableModel tableModel) {
		try {
			//tableNameのIDとして復元
			operationService.restoreByIdAndtableName(tableModel);
			log.info("復元情報：" + tableModel);
			return "redirect:/ad/operation";
		} catch (Exception e) {
			log.warn("復元情報失敗：" + tableModel);
			return "error";
		}
	}

	/**
	 * 操作情報を編集するPOSTメソッド。
	 * @param TableModel 操作モデル
	 * @return 操作情報の表示用テンプレート名
	 */
	@PostMapping("/operation/edit")
	public String postOperationEdit(TableModel tableModel) {
		try {
			//tableNameのIDとして編集
			if(tableModel.getId() == 1 &&  tableModel.getTableName().equals("user_infor")) {
				log.warn("スーパー管理者が削除出来ない。");
				return null;
			}
			operationService.editByIdAndtableName(tableModel);
			log.info("編集情報：" + tableModel);
			return "redirect:/ad/operation";
		} catch (Exception e) {
			log.warn("編集情報：" + tableModel);
			return "error";
		}
	}

	/**
	 * 新しい操作情報を登録するPOSTメソッド。
	 * @param TableModel 操作モデル
	 * @return 操作情報の表示用テンプレート名
	 */
	@PostMapping("/operation/reg")
	public String postOperationReg(TableModel tableModel) {
		System.out.println("ok"+ (operationService.checkPurpose(tableModel).size()== 0));
		try {
			//tableNameとして新規登録
			if(tableModel.getTableName().equals("user_infor") && operationService.checkUserNameAndEmail(tableModel).size()==0) {
				tableModel.setUsername(coverRegister.getUsername(tableModel.getEmail()));
				String pasString = coverRegister.createPassword();
				tableModel.setPassword(pasString);
				tableModel.setPassword(encoder.encode(pasString));
				operationService.regByIdAndtableName(tableModel);
				emailService.sendRegister(tableModel,pasString);
			}else if (tableModel.getTableName().equals("purpose") && operationService.checkPurpose(tableModel).size()== 0) {
				operationService.regByIdAndtableName(tableModel);				
			}else {
				return null;
			}
			log.info("新規登録情報：" + tableModel);
			return "redirect:/ad/operation";
		} catch (Exception e) {
			log.warn("新規登録情報失敗：" + tableModel);
			return "error";
		}
	}
}
