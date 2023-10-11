package com.juneutf.mtg.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.juneutf.mtg.config.service.OperationService;
import com.juneutf.mtg.model.OperationModel;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Controller
@RequestMapping("/kk")
public class OperationController {
	@Autowired
	private OperationService operationService;

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
	@PostMapping("/operation/delete")
	public String postOperationDelete(OperationModel operationModel) {
		try {
			operationService.deleteByIdAndNameID(operationModel);
			log.info("削除情報："+ operationModel.getNameID() +"テーブルの"+operationModel.getId());
			return "redirect:/kk/operation";
		} catch (Exception e) {
			log.warn("削除情報失敗："+ operationModel.getNameID() +"テーブルの"+operationModel.getId());
			return "error";
		}
	}
	@PostMapping("/operation/restore")
	public String postOperationRestore(OperationModel operationModel) {
		try {
			operationService.restoreByIdAndNameID(operationModel);
			log.info("復元情報："+ operationModel.getNameID() +"テーブルの"+operationModel.getId());
			return "redirect:/kk/operation";
		} catch (Exception e) {
			log.warn("復元情報失敗："+ operationModel.getNameID() +"テーブルの"+operationModel.getId());
			return "error";
		}
	}
	@PostMapping("/operation/edit")
	public String postOperationEdit(OperationModel operationModel) {
		try {
			operationService.editByIdAndNameID(operationModel);
			log.info("編集情報："+ operationModel.getNameID() +"テーブルの"+operationModel.getId());
			return "redirect:/kk/operation";
		} catch (Exception e) {
			log.warn("編集情報："+ operationModel.getNameID() +"テーブルの"+operationModel.getId());
			return "error";
		}
	}
	@PostMapping("/operation/reg")
	public String postOperationReg(OperationModel operationModel) {
		System.out.println(operationModel);
		try {
			operationService.regByIdAndNameID(operationModel);
			log.info("新規登録情報："+ operationModel.getNameID() +"テーブルの"+operationModel.getId());
			return "redirect:/kk/operation";
		} catch (Exception e) {
			log.warn("新規登録情報失敗："+ operationModel.getNameID() +"テーブルの"+operationModel.getId());
			return "error";
		}
	}

}
