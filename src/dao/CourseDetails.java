package dao;

import java.io.Serializable;

public class CourseDetails implements Serializable{
	
	private static final long serialVersionUID = 1L;
	private String coursename;
	private int capacity;
	private String year;
	private String delmode;
	private String day;
	private String times;
	private double duration;
	
	
	public CourseDetails(String cname,int cpaty,String year,String delmode,String day,String times,double dur){
		this.coursename = cname;
		this.capacity = cpaty;
		this.year = year;
		this.delmode = delmode;
		this.day = day;
		this.times = times;
		this.duration = dur;
	}
	
	public String getcoursename() {
		return this.coursename;
	}
	
	public int getcapacity() {
		return this.capacity;
	}
	
	public String getyear() {
		return this.year;
	}
	
	public String getdelmode() {
		return this.delmode;
	}
	
	public String getday() {
		return this.day;
	}
	
	public String gettimes() {
		return this.times;
	}
	
	public double getduration() {
		return this.duration;
	}
	
}