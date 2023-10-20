package com.juneutf.mtg.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.juneutf.mtg.config.vender.CustomUser;
import com.juneutf.mtg.model.LoginModel;
import com.juneutf.mtg.service.LoginService;

/**
 * このクラスは、Spring Securityが提供するUserDetailsServiceインターフェースを実装しています。
 * 認証中にユーザーの詳細をロードする責任を持ちます。
 */
@Service
public class CustomerUserDetailsService implements UserDetailsService {

    @Autowired
    private LoginService loginService;

    /**
     * ユーザー名を使用してユーザー詳細をロードします。
     *
     * @param username ユーザーの詳細をロードするためのユーザー名。
     * @return ユーザーを表すUserDetailsオブジェクト。
     * @throws UsernameNotFoundException ユーザーが見つからない場合にスローされます。
     */
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        // LoginServiceからユーザー情報を取得します。
        LoginModel user = loginService.selectLoginByUsername(username);
        
        // ユーザーが見つからない場合は例外をスローします。
        if (user == null) {
            throw new UsernameNotFoundException(username+":ユーザーが見つかりませんでした。 ");
        }if(user.isEnabled()) {
        	throw new UsernameNotFoundException(user.getUsername() + "ロックされました。");
        }else {
        	// ユーザーのユーザー名、パスワード、および権限を持つUserDetailsオブジェクトを作成します。
        	return new CustomUser(user.getUsername(), user.getPassword(), user.getAuthorities(),user.getFullname(),user.getId());			
		}
        
    }
}
