package com.guserinterfaces.config;

import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Connection;

public class Config {
	private String url;
	private String user;
	private String pw;
	
	public Config(String url, String user, String pw) {
		this.url = url;
		this.user = user;
		this.pw = pw;
	}
	
	public Config() {
		
	}
	public Connection connected() throws SQLException{
		Connection connected = DriverManager.getConnection(this.url, this.user, this.pw);
		return connected;
	}
	public String getUrl() {
		return url;
	}
	public void setUrl(String url) {
		this.url = url;
	}
	public String getUser() {
		return user;
	}
	public void setUser(String user) {
		this.user = user;
	}
	public String getPw() {
		return pw;
	}
	public void setPw(String pw) {
		this.pw = pw;
	}
	
}
