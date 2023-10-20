package com.juneutf.mtg.config.vender;

import java.util.Collection;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.User;

public class CustomUser extends User {
    private static final long serialVersionUID = 1L;
	private String fullName;
	private int id;

    public CustomUser(String username, String password, Collection<? extends GrantedAuthority> authorities, String fullName,int id) {
        super(username, password, authorities);
        this.fullName = fullName;
        this.id = id;
    }

    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}
}

