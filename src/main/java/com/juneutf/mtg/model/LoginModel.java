package com.juneutf.mtg.model;

import lombok.Data;

/**
 * このモデルは、ユーザーのログイン情報を表します.
 */
@Data
public class LoginModel {
    private String username; // ユーザー名
    private String password; // パスワード
}
