package com.juneutf.mtg.iml;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.juneutf.mtg.mapper.SetPasswordMapper;
import com.juneutf.mtg.model.SetPasswordModel;
import com.juneutf.mtg.service.SetPasswordService;

/**
 * このクラスは、パスワードの設定およびリセットに関するサービスを提供します。
 */
@Service
public class SetPasswordImpl implements SetPasswordService {
    @Autowired
    private SetPasswordMapper mapper;
    
    /**
     * 指定された電子メールに関連するパスワード設定モデルを選択します。
     *
     * @param email 検索する電子メールアドレス
     * @return 関連するパスワード設定モデル、存在しない場合はnull
     */
    @Override
    public SetPasswordModel selectEmail(String email) {
        return mapper.selectEmail(email);
    }
    
    /**
     * 指定された電子メールに関連するトークンを使用して、パスワード設定モデルを更新します。
     *
     * @param setPasswordModel パスワード設定モデルを更新するための情報を含むオブジェクト
     * @return 更新が成功した場合は1、それ以外の場合は0
     */
    @Override
    public int updateTokenByEmail(SetPasswordModel setPasswordModel) {
        return mapper.updateTokenByEmail(setPasswordModel);
    }
    
    /**
     * 指定された電子メールに関連するトークンを使用して、パスワード設定モデルを選択します。
     *
     * @param email 検索する電子メールアドレス
     * @return 関連するパスワード設定モデル、存在しない場合はnull
     */
    @Override
    public SetPasswordModel selectTokenByEmail(String email) {
        return mapper.selectTokenByEmail(email);
    }
    
    /**
     * 指定された電子メールに関連するパスワードを更新します。
     *
     * @param setPasswordModel パスワードを更新するための情報を含むオブジェクト
     * @return 更新が成功した場合は1、それ以外の場合は0
     */
    @Override
    public int updatePasswordByEmail(SetPasswordModel setPasswordModel) {
        return mapper.updatePasswordByEmail(setPasswordModel);
    }
}
