package com.juneutf.mtg.controller;

import java.util.ArrayList;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import com.juneutf.mtg.config.vender.CreateToken;
import com.juneutf.mtg.config.vender.EmailService;
import com.juneutf.mtg.model.JobModel;
import com.juneutf.mtg.model.LoginModel;
import com.juneutf.mtg.model.SetPasswordModel;
import com.juneutf.mtg.service.ActionService;
import com.juneutf.mtg.service.PlanService;
import com.juneutf.mtg.service.SetPasswordService;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Controller
public class HomeController {
	@Autowired
	private SetPasswordService setPasswordService;
	@Autowired
	private CreateToken createToken;
	@Autowired
	private EmailService emailService;
	@Autowired
	private PasswordEncoder passwordEncoder;
	@Autowired
	private PlanService planService;
	@Autowired
    private ActionService actionService;
	@Autowired
    private SimpMessagingTemplate messagingTemplate;

	/**
	 * ホームページへのアクセスを処理します。
	 *
	 * @return "index" - ホームページへ
	 */
	@GetMapping("/")
	public String getIndex(Model model) {
		// ログイン状態の検証、ログイン済み場合はアクセス無効ー＞ｋｋページに遷移
		Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
		if (!authentication.getName().equals("anonymousUser")) {
			return "redirect:/kk/job";
		}
		try {
			// 予約の取得
			ArrayList<JobModel> job = planService.selectPlan();
			// タイムリーフに予約内容の渡す
			model.addAttribute("job", job);
			return "index";
		} catch (Exception e) {
			log.error("予約内容を取得出来ませんでした。");
			return "error";
		}
	}

	/**
	 * ログインページへのアクセスを処理します。
	 *
	 * @return "login" - ログインページへ
	 */
	@GetMapping("/login")
	public String getLogin() {
		// ログイン状態の検証、ログイン済み場合はアクセス無効ー＞ｋｋページに遷移
		Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
		if (!authentication.getName().equals("anonymousUser")) {
			return "redirect:/kk/job";
		}
		return "login";
	}

	/**
	 * パスワード変更ページへのアクセスを処理します。
	 *
	 * @param loginModel ログインモデルオブジェクト
	 * @return "getPassword" - パスワード変更ページへ
	 */
	@GetMapping("/password")
	public String getchangerPass(LoginModel loginModel) {
		// ログイン状態の検証、ログイン済み場合はアクセス無効ー＞ｋｋページに遷移
		Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
		if (!authentication.getName().equals("anonymousUser")) {
			return "redirect:/kk/job";
		}
		return "getPassword";
	}

	/**
	 * トークンを送信するためのページへのアクセスを処理します。
	 *
	 * @param setPasswordModel パスワード設定モデルオブジェクト
	 * @param model モデルオブジェクト
	 * @return "redirect:/setpassword" - トークン送信後のリダイレクト
	 */
	@GetMapping("/sendtoken")
	public String sendToken(SetPasswordModel setPasswordModel, Model model) {
		// ログイン状態の検証、ログイン済み場合はアクセス無効ー＞ｋｋページに遷移
		Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
		if (!authentication.getName().equals("anonymousUser")) {
			return "redirect:/kk/job";
		}
		try {
			// メールアドレスの検証
			String checkEmail = setPasswordService.selectEmail(setPasswordModel.getEmail()).getEmail();
			// トークン生成
			String token = createToken.creatKeyNumber();
			setPasswordModel.setToken(token);
			// メールアドレスにトークンの送信
			emailService.sendToken(checkEmail, token.substring(0, 6));
			// データベースにトークンの登録
			if (setPasswordService.updateTokenByEmail(setPasswordModel) == 0) {
				log.error("トークン生成したのに、トークンをデータベースに登録出来ません。");
				return "error";
			}
			return "redirect:/setpassword";
		} catch (Exception e) {
			model.addAttribute("msg", "メールアドレスが存在しません。");
			return "getPassword";
		}
	}

	/**
	 * パスワード設定ページへのアクセスを処理します。
	 *
	 * @return "setPassword" - パスワード設定ページ
	 */
	@GetMapping("/setpassword")
	public String showSetPassword() {
		// ログイン状態の検証、ログイン済み場合はアクセス無効ー＞ｋｋページに遷移
		Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
		if (!authentication.getName().equals("anonymousUser")) {
			return "redirect:/kk/job";
		}
		return "setPassword";
	}

	/**
	 * パスワードを設定するためのPOSTリクエストを処理します。
	 *
	 * @param setPasswordModel パスワード設定モデルオブジェクト
	 * @param model  モデルオブジェクト
	 * @return "redirect:/kk/job" - パスワード設定後のリダイレクト
	 */
	@PostMapping("/setpassword")
	public String setPassword(@Valid SetPasswordModel setPasswordModel, BindingResult result, Model model) {
		// ログイン状態の検証、ログイン済み場合はアクセス無効ー＞ｋｋページに遷移
		Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
		if (!authentication.getName().equals("anonymousUser")) {
			return "redirect:/kk/job";
		}

		try {
			// バリエーション検証
			if (result.hasErrors()) {
				model.addAttribute("msg", result.getAllErrors().get(0).getDefaultMessage());
				return "setPassword";
			}
			// メールアドレスでデータの取得
			String token = setPasswordService.selectTokenByEmail(setPasswordModel.getEmail()).getToken();
			// トークンの比較
			if (!setPasswordModel.getToken().equals(token.substring(0, 6))) {
				// トークン正しくない
				model.addAttribute("msg", "トークンが正しくない。");
				return "setPassword";
			}
			if (!createToken.checkTimeToken(token)) {
				// トークン無効期限
				model.addAttribute("msg", "トークンの無効期限です。");
				return "setPassword";
			}
			// パスワードの比較
			if (!setPasswordModel.getPassword().equals(setPasswordModel.getRePassword())) {
				// パスワード再入力エラー
				model.addAttribute("msg", "パスワード再入力が一致しません。");
				return "setPassword";
			}
			// パスワードのエンコード
			setPasswordModel.setPassword(passwordEncoder.encode(setPasswordModel.getPassword()));
			// データベースに新パスワードの登録
			if (setPasswordService.updatePasswordByEmail(setPasswordModel) == 0) {
				log.error("パスワード再設定をデータベースに登録出来ません。");
				return "error";
			}
			// ログインに遷移
			log.info(setPasswordModel.getEmail() + "はパスワード再設定しました。　新パスワード（" + setPasswordModel.getRePassword() + "）");
			return "redirect:/kk/job";
		} catch (Exception e) {
			return "error";
		}
	}
	/**
	 * 予約内容を完了するためのPOSTリクエストを処理します。
	 *
	 * @param 予約内容のID番号
	 * @return "redirect:/" - 完了後のリダイレクト
	 */
	@PostMapping("/kanryo")
	public String setKanryo(int id) {
		try {
			//予約内容のID番号として完了行動
			actionService.actionUpdateById(id);
			//Websocket行動
			ArrayList<JobModel> job = planService.selectPlan();
			messagingTemplate.convertAndSend("/job/notification", job);
			return "redirect:/";
		} catch (Exception e) {
			log.warn("完了機能ID"+id+"はエラーが発生します。");
			return "error";
		}
	}
}
