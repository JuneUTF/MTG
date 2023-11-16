package com.juneutf.mtg.iml;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.juneutf.mtg.mapper.LoginMapper;
import com.juneutf.mtg.model.LoginModel;
import com.juneutf.mtg.service.LoginService;

/**
 * このクラスは、ログイン関連のサービスを提供します。
 */
@Service
public class LoginImpl implements LoginService {

    @Autowired
    LoginMapper mapper;
    
    /**
     * ユーザー名に基づいてログインモデルを選択します。
     *
     * @param ログイン情報を検索するためのユーザー名またはメールアドレス
     * @return ユーザー名に一致するログインモデル、存在しない場合はnull
     */
    @Override
    public LoginModel selectLoginByUsername(String username){
        return mapper.selectLoginByUsername(username);
    }
}
