package model;

public class Student {
	private String username;
	private String password;
	private String fname;
	private String lname;
	private String sid;

	public Student() {
	}
	
	public Student(String username, String password,String fname,String lname,String sid) {
		this.username = username;
		this.password = password;
		this.fname = fname;
		this.lname = lname;
		this.sid = sid;
	}

	public String getUsername() {
		return username;
	}

	public String getPassword() {
		return password;
	}
	
	public String getfname() {
		return fname;
	}
	
	public String getlname() {
		return lname;
	}
	
	public String getsid() {
		return sid;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public void setPassword(String password) {
		this.password = password;
	}
	
	public void setdetails(String fname,String lname,String sid) {
		this.fname = fname;
		this.lname = lname;
		this.sid = sid;
	}
	public void setfname(String fname) {
		this.fname = fname;
	}
	public void setlname(String lname) {
		this.lname = lname;
	}
	public void setsid(String sid) {
		this.sid = sid;
	}
}
