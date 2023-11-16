package com.juneutf.mtg.config.vender;

import java.util.Random;

import org.springframework.stereotype.Component;
@Component
public class CoverRegister {
		public String createPassword() {
			String pass ="";
	        for (int i=0;i<8;i++) {
	        	// 0から9までのランダムな数字を生成してキーに追加
	        	pass+=new Random().nextInt(10); 
	        }
			return pass;
		}
		public String getUsername(String email) {
			String username ="";
			int atIndex = email.indexOf('@');
			if (atIndex != -1) {
				username = email.substring(0, atIndex);
			}
			return username;
		}
}
