package model;



import java.sql.SQLException;

import dao.StudentDao;
import dao.StudentDaoImpl;

public class Model {
	private StudentDao studentDao;
	private Student currentStudent; 
	
	public Model() {
		studentDao = new StudentDaoImpl();
	}
	
	public void setup() throws SQLException {
		studentDao.setup();
	}
	public StudentDao getStudentDao() {
		return studentDao;
	}
	
	public Student getcurrentStudent() {
		return this.currentStudent;
	}
	
	public void setcurrentStudent(Student student) {
		currentStudent = student;
	}
}
