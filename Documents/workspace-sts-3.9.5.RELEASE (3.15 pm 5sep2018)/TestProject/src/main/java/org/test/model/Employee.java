package org.test.model;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import org.apache.solr.client.solrj.beans.Field;

public class Employee {

	@Id
	@GeneratedValue
	@Field
	private String userId;
	@Field
	private String fname;
	@Field
	private String lname;
	@Field
	private String email;
	@Field
	private String mobile;
	
	
	
	
	public Employee(String userId, String fname, String lname, String email, String mobile) {
		super();
		this.userId = userId;
		this.fname = fname;
		this.lname = lname;
		this.email = email;
		this.mobile = mobile;
	}
	
	
	public String getUserId() {
		return userId;
	}
	public void setUserId(String userId) {
		this.userId = userId;
	}
	public String getFname() {
		return fname;
	}
	public void setFname(String fname) {
		this.fname = fname;
	}
	public String getLname() {
		return lname;
	}
	public void setLname(String lname) {
		this.lname = lname;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getMobile() {
		return mobile;
	}
	public void setMobile(String mobile) {
		this.mobile = mobile;
	}
	
	
}
 
