package com.logistics.loginregister.DTO;

import com.logistics.domain.userinfo;

public class UserInfoSessionDTO {
	private userinfo userInfoSession;

	public userinfo getUserInfoSession() {
		return userInfoSession;
	}

	public void setUserInfoSession(userinfo userInfoSession) {
		this.userInfoSession = userInfoSession;
	}

	@Override
	public String toString() {
		return "UserInfoSessionDTO [userInfoSession=" + userInfoSession + "]";
	}

}
