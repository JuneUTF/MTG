package com.juneutf.mtg.mapper;

import org.apache.ibatis.annotations.Mapper;

import com.juneutf.mtg.model.SetPasswordModel;

/**
 * パスワード設定データへのアクセス操作を提供するマッパーインターフェースです。
 */
@Mapper
public interface SetPasswordMapper {
    /**
     * 指定されたメールアドレスに関連付けられたデータを取得します。
     * @param email メールアドレス
     * @return SetPasswordModelオブジェクト
     */
    SetPasswordModel selectEmail(String email);

    /**
     * メールアドレスに関連付けられたトークンを更新します。
     * @param setPasswordModel SetPasswordModelオブジェクト
     * @return 更新の結果
     */
    int updateTokenByEmail(SetPasswordModel setPasswordModel);

    /**
     * メールアドレスに関連付けられたトークンを取得します。
     * @param email メールアドレス
     * @return SetPasswordModelオブジェクト
     */
    SetPasswordModel selectTokenByEmail(String email);

    /**
     * メールアドレスに関連付けられたパスワードを更新します。
     * @param setPasswordModel SetPasswordModelオブジェクト
     * @return 更新の結果
     */
    int updatePasswordByEmail(SetPasswordModel setPasswordModel);
}
