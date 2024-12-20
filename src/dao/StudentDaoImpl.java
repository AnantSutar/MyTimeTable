package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import model.Student;

public class StudentDaoImpl implements StudentDao {
	private final String TABLE_NAME = "Userdata";
	private static int id = 0;
	public StudentDaoImpl() {
	}

	@Override
	public void setup() throws SQLException {
		try (Connection connection = Database.getConnection();
				Statement stmt = connection.createStatement();) {
			String sql = "CREATE TABLE IF NOT EXISTS " + TABLE_NAME + " (username VARCHAR(10) NOT NULL,"
					+ "password VARCHAR(8) NOT NULL,fname VARCHAR(10) NOT NULL,lname VARCHAR(10) NOT NULL, "
					+ "StudentID VARCHAR(10) UNIQUE, " + "PRIMARY KEY (username))" ;
			stmt.executeUpdate(sql);
		} 
		CourseDao cd = new CourseDao();
		cd.readData("courses.csv");
		cd.CourseDB();
	}

	@Override
	public Student getStudent(String username, String password) throws SQLException {
		String hashpassword = Integer.toString(password.hashCode()); 
		String sql = "SELECT * FROM " + TABLE_NAME + " WHERE username = ? AND password = ?";
		try (Connection connection = Database.getConnection(); 
				PreparedStatement stmt = connection.prepareStatement(sql);) {
			stmt.setString(1, username);
			stmt.setString(2, hashpassword);
			
			try (ResultSet rs = stmt.executeQuery()) {
				if (rs.next()) {
					Student user = new Student();
					user.setUsername(rs.getString("username"));
					user.setPassword(password);
					user.setfname(rs.getString(3));
					user.setlname(rs.getString(4));
					user.setsid(rs.getString(5));
					return user;
				}
				return null;
			} 
		}
	}

	@Override
	public Student createStudent(String username, String password, String fname,String lname,String sid) throws SQLException {
		
		String hashpassword =Integer.toString(password.hashCode());
		id=id+1;
		System.out.println(id);
		String sql = "INSERT INTO " + TABLE_NAME + " VALUES (?, ?,?,?,?)";
		try (Connection connection = Database.getConnection();
				PreparedStatement stmt = connection.prepareStatement(sql);) {
			stmt.setString(1, username);
			stmt.setString(2, hashpassword);
			stmt.setString(3, fname);
			stmt.setString(4, lname);
			stmt.setString(5, sid);

			stmt.executeUpdate();
			return new Student(username, password,fname,lname,sid);
		} 
	}
	@Override
	public void updateStudent(String username, String fname, String lname, String password) {
		String hashpassword =Integer.toString(password.hashCode());
		String sql = "UPDATE "+TABLE_NAME+" SET password=?, fname=?,lname=? where username=?";
		try (Connection connection = Database.getConnection();
				PreparedStatement stmt = connection.prepareStatement(sql);) {
			stmt.setString(1, hashpassword);
			stmt.setString(2, fname);
			stmt.setString(3, lname);
			stmt.setString(4, username);
			
			stmt.executeUpdate();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
