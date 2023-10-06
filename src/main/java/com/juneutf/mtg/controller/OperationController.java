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
			int updatecheck = operationService.deleteByIdAndNameID(operationModel);
			return "redirect:/kk/operation";
		} catch (Exception e) {
			log.warn("PostMapping operation/deleteに"+operationModel.getStatus()+"/"+operationModel.getId()+"エラー発生します。");
			return "error";
		}
	}
	@PostMapping("/operation/restore")
	public String postOperationRestore(OperationModel operationModel) {
		try {
			int updatecheck = operationService.restoreByIdAndNameID(operationModel);
			return "redirect:/kk/operation";
		} catch (Exception e) {
			log.warn("PostMapping operation/restoreに"+operationModel.getStatus()+"/"+operationModel.getId()+"エラー発生します。");
			return "error";
		}
	}
	@PostMapping("/operation/edit")
	public String postOperationEdit(OperationModel operationModel) {
		try {
			int updatecheck = operationService.editByIdAndNameID(operationModel);
			return "redirect:/kk/operation";
		} catch (Exception e) {
			log.warn("PostMapping operation/editに"+operationModel.getStatus()+"/"+operationModel.getId()+"エラー発生します。");
			return "error";
		}
	}
	@PostMapping("/operation/reg")
	public String postOperationReg(OperationModel operationModel) {
		System.out.println(operationModel);
		try {
			int updatecheck = operationService.regByIdAndNameID(operationModel);
			return "redirect:/kk/operation";
		} catch (Exception e) {
			log.warn("PostMapping operation/regに"+operationModel.getStatus()+"/"+operationModel.getId()+"エラー発生します。");
			return "error";
		}
	}

}
