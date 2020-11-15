package com.example.JasperReportProject.model;

import java.util.List;

public class GroupReport {
	private String subTitle;
	
	private List<Member> data;

	public GroupReport() {
		super();
	}

	public GroupReport(String subTitle, List<Member> data) {
		super();
		this.subTitle = subTitle;
		this.data = data;
	}

	public String getSubTitle() {
		return subTitle;
	}

	public void setSubTitle(String subTitle) {
		this.subTitle = subTitle;
	}

	public List<Member> getData() {
		return data;
	}

	public void setData(List<Member> data) {
		this.data = data;
	}
	

}
