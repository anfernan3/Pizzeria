package com.bootcamp.application.dtos;

import java.io.Serializable;

@SuppressWarnings("serial")
public class AuthToken implements Serializable {
	private boolean success = true;
    private String token;
    private String name;
    private String[] roles;
	public AuthToken(boolean success, String token, String name, String roles) {
		this.success = success;
		this.token = token;
		this.name = name;
		this.roles = roles.replace("ROLE_", "").split(",");
	}
	public boolean isSuccess() {
		return success;
	}
	public String getToken() {
		return token;
	}
	public String getName() {
		return name;
	}
	public String[] getRoles() {
		return roles;
	}
    
}
