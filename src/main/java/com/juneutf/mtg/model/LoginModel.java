package com.juneutf.mtg.model;

import lombok.Data;

/**
 * ユーザーのログイン情報を表します.
 */
@Data
public class LoginModel {
	/**
	 * ユーザー名またはメールアドレス
	 */
    private String username; 
    /**
     * パスワード
     */
    private String password;
}
