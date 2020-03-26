package com.bny.esg.model;

public class User {

    private String id;
    private String userName;
    private String org;
    private String role;

    public User() {
        // empty to allow for bean access
    }

    public User(String id, String userName, String org, String role) {
        this.id = id;
        this.userName = userName;
        this.org = org;
        this.role = role;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getOrg() {
		return org;
	}

	public void setOrg(String org) {
		this.org = org;
	}

	public String getRole() {
		return role;
	}

	public void setRole(String role) {
		this.role = role;
	}

}
