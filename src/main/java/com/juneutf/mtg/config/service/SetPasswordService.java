package com.juneutf.mtg.config.service;

import com.juneutf.mtg.model.SetPasswordModel;

/**
 * このインターフェースは、パスワードの設定とリセットに関連する操作を提供します。
 */
public interface SetPasswordService {

    /**
     * 指定された電子メールに関連するパスワード設定モデルを選択します。
     *
     * @param email メールアドレスを検索するための電子メール
     * @return 関連するパスワード設定モデル、存在しない場合はnull
     */
    SetPasswordModel selectEmail(String email);

    /**
     * 指定された電子メールに関連するトークンを使用して、パスワード設定モデルを更新します。
     *
     * @param setPasswordModel パスワード設定モデルを更新するための情報を含むオブジェクト
     * @return 更新が成功した場合は1、それ以外の場合は0
     */
    int updateTokenByEmail(SetPasswordModel setPasswordModel);

    /**
     * 指定された電子メールに関連するトークンを使用して、パスワード設定モデルを選択します。
     *
     * @param email メールアドレスを検索するための電子メール
     * @return 関連するパスワード設定モデル、存在しない場合はnull
     */
    SetPasswordModel selectTokenByEmail(String email);

    /**
     * 指定された電子メールに関連するパスワードを更新します。
     *
     * @param setPasswordModel パスワードを更新するための情報を含むオブジェクト
     * @return 更新が成功した場合は1、それ以外の場合は0
     */
    int updatePasswordByEmail(SetPasswordModel setPasswordModel);
}
