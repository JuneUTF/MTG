package com.juneutf.mtg.model;

import lombok.Data;

@Data
public class ChangePasswordModel {
	private String username;
	private String password;
	private String newpassword;
	private String repassword;
}
