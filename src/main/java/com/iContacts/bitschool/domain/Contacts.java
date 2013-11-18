package com.iContacts.bitschool.domain;

public class Contacts {
	
	private int id;
	private int userId;
	private String name;
	private String phoneNum;
	private String email;
	private String work;
	private String homeAddr;
	private String webPage;
	private String memo;

	public Contacts() {
		System.out.println("Contacts 생성");
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public int getUserId() {
		return userId;
	}

	public void setUserId(int userId) {
		this.userId = userId;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getPhoneNum() {
		return phoneNum;
	}

	public void setPhoneNum(String phoneNum) {
		this.phoneNum = phoneNum;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getWork() {
		return work;
	}

	public void setWork(String work) {
		this.work = work;
	}

	public String getHomeAddr() {
		return homeAddr;
	}

	public void setHomeAddr(String homeAddr) {
		this.homeAddr = homeAddr;
	}

	public String getWebPage() {
		return webPage;
	}

	public void setWebPage(String webPage) {
		this.webPage = webPage;
	}

	public String getMemo() {
		return memo;
	}

	public void setMemo(String memo) {
		this.memo = memo;
	}

	@Override
	public String toString() {
		return "Contacts [id=" + id + ", userId=" + userId + ", name=" + name
				+ ", phoneNum=" + phoneNum + ", email=" + email + ", work="
				+ work + ", homeAddr=" + homeAddr + ", webPage=" + webPage
				+ ", memo=" + memo + "]";
	}

}
