package com.juneutf.mtg.service;

import com.juneutf.mtg.model.LoginModel;

/**
 * このインターフェースは、ユーザー名によってログイン情報を選択するためのメソッドを提供します。
 */
public interface LoginService {

    /**
     * ユーザー名に基づいてログインモデルを選択します。
     *
     * @param username ログイン情報を検索するためのユーザー名またはメールアドレス
     * @return ユーザー名に一致するログインモデル、存在しない場合はnull
     */
    LoginModel selectLoginByUsername(String username);
}
