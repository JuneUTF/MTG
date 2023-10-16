package com.juneutf.mtg.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.juneutf.mtg.model.TableModel;
import com.juneutf.mtg.service.OperationService;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Controller
@RequestMapping("/kk")
public class OperationController {
	@Autowired
	private OperationService operationService;

	/**
	 * 操作情報を取得するGETメソッド（担当者リスト・内容リストを渡す）。
	 * @param model モデル
	 * @return 操作情報の表示用テンプレート名
	 */
	@GetMapping("/operation")
	public String getOperation(Model model) {
		try {
			model.addAttribute("charge", operationService.selectCharge());
			model.addAttribute("purpose", operationService.selectPurpose());
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
			operationService.deleteByIdAndtableName(tableModel);
			log.info("削除情報：" + tableModel);
			return "redirect:/kk/operation";
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
			return "redirect:/kk/operation";
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
			operationService.editByIdAndtableName(tableModel);
			log.info("編集情報：" + tableModel);
			return "redirect:/kk/operation";
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
		try {
			//tableNameとして新規登録
			operationService.regByIdAndtableName(tableModel);
			log.info("新規登録情報：" + tableModel);
			return "redirect:/kk/operation";
		} catch (Exception e) {
			log.warn("新規登録情報失敗：" + tableModel);
			return "error";
		}
	}
}
