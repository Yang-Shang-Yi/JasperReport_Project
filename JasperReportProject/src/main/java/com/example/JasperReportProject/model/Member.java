package com.example.JasperReportProject.model;

public class Member {

	private String name;
	
	private String sex;
	
	private String dep;
	
	private String theme;
	
	public Member() {
		super();
	}

	public Member(String name, String sex, String dep, String theme) {
		super();
		this.name = name;
		this.sex = sex;
		this.dep = dep;
		this.theme = theme;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getSex() {
		return sex;
	}

	public void setSex(String sex) {
		this.sex = sex;
	}

	public String getDep() {
		return dep;
	}

	public void setDep(String dep) {
		this.dep = dep;
	}

	public String getTheme() {
		return theme;
	}

	public void setTheme(String theme) {
		this.theme = theme;
	}
	
	
}
