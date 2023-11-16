package com.juneutf.mtg.model;

import javax.validation.constraints.Email;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

import lombok.Data;

/**
 * このモデルは、パスワードの設定およびリセットに関連する情報を表します。
 */
@Data 
public class SetPasswordModel {
	 /**
     * ユーザーのメールアドレスを表します。
     */
    @Email(message = "Emailが無効です")
    private String email;

    /**
     * 6桁のトークンを表します。
     */
    @Pattern(regexp = "^[0-9]{6}$", message = "トークンは6文字の数字のみです。")
    private String token;

    /**
     * パスワードを表します。
     */
    @Pattern(regexp = "^[a-zA-Z0-9]{8,18}$", message = "パスワードは8-18文字の英文字または数字のみです")
    private String password;

    /**
     * パスワードの再入力を表します。
     */
    @Size(min = 8, max = 16, message = "パスワードは8-18文字の英文字または数字のみです")
    private String rePassword;
}
