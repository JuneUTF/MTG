package com.juneutf.mtg.mapper;

import org.apache.ibatis.annotations.Mapper;

import com.juneutf.mtg.model.LoginModel;

/**
 * ユーザー名に基づいてログイン情報をデータベースから選択するためのメソッドを提供します。
 */
@Mapper
public interface LoginMapper {
    
    /**
     * ユーザー名に基づいてログインモデルを選択します。
     *
     * @param ログイン情報を検索するためのユーザー名またはメールアドレス
     * @return ユーザー名に一致するログインモデル、存在しない場合はnull
     */
    LoginModel selectLoginByUsername(String username);
}
