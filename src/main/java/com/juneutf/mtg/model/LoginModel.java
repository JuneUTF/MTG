package com.juneutf.mtg.model;

import java.util.Collection;
import java.util.HashSet;
import java.util.Set;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import lombok.Data;

/**
 * ユーザーのログイン情報を表します.
 */
@Data
public class LoginModel implements UserDetails{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private int id;
	/**
	 * ユーザー名またはメールアドレス
	 */
    private String username; 
    /**
     * パスワード
     */
    private String password;
    /**
	 * 名前
	 */
    private String fullname;
    /**
	 * 状態
	 */
    private String status;
    /**
     * 権限
     */
    private String role;
	@Override
	public Collection<? extends GrantedAuthority> getAuthorities() {
		Set<SimpleGrantedAuthority> authorities = new HashSet<>();
        authorities.add(new SimpleGrantedAuthority("ROLE_"+role));
        return authorities;
	}
	@Override
	public boolean isEnabled() {
		if(status.equals("削除")) {
			return true;
		}else {
			return false;		
		}
	}
	@Override
	public boolean isAccountNonExpired() {
		// TODO 自動生成されたメソッド・スタブ
		return false;
	}
	@Override
	public boolean isAccountNonLocked() {
		// TODO 自動生成されたメソッド・スタブ
		return false;
	}
	@Override
	public boolean isCredentialsNonExpired() {
		// TODO 自動生成されたメソッド・スタブ
		return false;
	}
	
}
