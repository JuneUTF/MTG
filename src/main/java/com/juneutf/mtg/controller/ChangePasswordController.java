package com.juneutf.mtg.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.juneutf.mtg.config.vender.CustomUser;
import com.juneutf.mtg.model.ChangePasswordModel;
import com.juneutf.mtg.service.ChangePasswordService;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Controller
@RequestMapping("/kk")
public class ChangePasswordController {
	@Autowired
	private ChangePasswordService changePasswordService;
	@Autowired
	private PasswordEncoder passwordEncoder;
	/**
	 * 
	 */
	@GetMapping("password")
	public String getPassword() {
		return "password/index";
	}
	@PostMapping("password")
	public String setPassword(ChangePasswordModel changePasswordModel,Model model) {
		UserDetails userDetails = (UserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		CustomUser customUser = (CustomUser) userDetails;
		try {
	        changePasswordModel.setUsername(customUser.getUsername());
			String password =  changePasswordService.selectPasswordByUsername(changePasswordModel).get(0).getPassword();
			if(!passwordEncoder.matches(changePasswordModel.getPassword(), password)) {
				model.addAttribute("error","現在パスワードが正しくありません。");
				return "password/index";
			}
			if(!changePasswordModel.getNewpassword().equals(changePasswordModel.getRepassword())) {
				model.addAttribute("error","再入力パスワードが一致しません。");
				return "password/index";
			}
			changePasswordModel.setNewpassword(passwordEncoder.encode(changePasswordModel.getNewpassword()));
			int check = changePasswordService.setNewPassword(changePasswordModel);
			if(check!=1) { 
				log.warn("パスワード変更機能のSQLがエラー発生しました。");
				return null;}
			model.addAttribute("msg","パスワードが変更されました。");
			return "password/index";			
		} catch (Exception e) {
			log.warn(customUser.getUsername()+"：パスワード変更際はエラー発生しました。");
			return "error";
		}
	}

}
