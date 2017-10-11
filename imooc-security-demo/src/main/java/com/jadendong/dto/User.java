package com.jadendong.dto;

import java.sql.Date;

import javax.validation.constraints.Past;

import org.hibernate.validator.constraints.NotBlank;

import com.fasterxml.jackson.annotation.JsonView;
import com.jadendong.validator.MyConstraint;

public class User {
	
	public interface UserSimpleView{};
	public interface UserDetailView extends UserSimpleView {};
	private int id;
	
	@MyConstraint(message="这是一个测试")
	private String username;
	
	@NotBlank
	private String password;
	
	@Past(message="生日必须为过去的时间")
	private Date birthday;
	
	@JsonView(UserSimpleView.class)
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	
	@JsonView(UserDetailView.class)
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	
	@JsonView(UserSimpleView.class)
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	
	@JsonView(UserSimpleView.class)
	public Date getBirthday() {
		return birthday;
	}
	public void setBirthday(Date birthday) {
		this.birthday = birthday;
	}
	
	
}
