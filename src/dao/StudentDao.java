package dao;

import java.sql.SQLException;

import model.Student;

/**
 * A data access object (DAO) is a pattern that provides an abstract interface 
 * to a database or other persistence mechanism. 
 * the DAO maps application calls to the persistence layer and provides some specific data operations 
 * without exposing details of the database. 
 */
public interface StudentDao {
	void setup() throws SQLException;
	Student getStudent(String username, String password) throws SQLException;
	Student createStudent(String username, String password, String lname,String fname,String sid) throws SQLException;
	void updateStudent(String username, String password, String lname, String fname);
}
