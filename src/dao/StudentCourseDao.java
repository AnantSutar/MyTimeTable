package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import model.Model;

public class StudentCourseDao {
	private final String TABLE_NAME = "SCdata";
	private final String TABLE_NAMECOURSE = "Coursedata";
	
	public void StudentCourseDB() {
		try (Connection connection = Database.getConnection();
			Statement stmt = connection.createStatement();) {
			String sql = "CREATE TABLE IF NOT EXISTS " + TABLE_NAME + "(Username varchar(20),"
					+"CourseName varchar(20) )"
					;
			stmt.executeUpdate(sql);
					System.out.println("Here");
			
		} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
	}
	
	public boolean CheckCapacityandmode(String course) {

		try (Connection connection = Database.getConnection();
				Statement stmt = connection.createStatement();) {
			
			String sql = "SELECT * FROM "+TABLE_NAMECOURSE+" WHERE CourseName= ?";
			PreparedStatement pstmt = connection.prepareStatement(sql);
			pstmt.setString(1, course);
			int capacity=0;
			String mode="";
			try (ResultSet rs = pstmt.executeQuery()){
				System.out.println(rs.getFetchSize());
				
			
					capacity =rs.getInt(2);
					mode = rs.getString(4);
				
			}catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
			
			

			if ((capacity == 0 && mode.equals("Online")) || (capacity !=0 && mode.equals("Face-to-face"))) {
				return true;
			}
			else {
				return false;
			}
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return true;
	}
	
	public void AddStudentCourse(Model mdel,String course) {
		Model model = mdel;
		try (Connection connection = Database.getConnection();
				Statement stmt = connection.createStatement();) {
			String sqlinsert = "INSERT INTO " + TABLE_NAME + " VALUES (?,?)";
			PreparedStatement pstmt = connection.prepareStatement(sqlinsert);
			pstmt.setString(1, model.getcurrentStudent().getUsername());
			pstmt.setString(2, course);
			pstmt.executeUpdate();
			
			String sqlupdate = "UPDATE "+ TABLE_NAMECOURSE+" SET Capacity = Capacity-1 WHERE CourseName=? and Mode=?";
			PreparedStatement statement = connection.prepareStatement(sqlupdate);
			statement.setString(1, course);
			statement.setString(2, "Face-to-face");
			statement.executeUpdate();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	public ArrayList Enrolledcourses(Model mdel,String username) {
		ArrayList<String> array = new ArrayList<String>();
		try (Connection connection = Database.getConnection();
				Statement stmt = connection.createStatement();){
			String sql = "SELECT * FROM "+TABLE_NAME+" WHERE Username= ?";
			PreparedStatement statement = connection.prepareStatement(sql);
			statement.setString(1, username);
			try (ResultSet rs = statement.executeQuery()){
				while(rs.next()) {
					array.add(rs.getString(2));
				}
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return array;
	}
	public void Withdrawcourses(Model mdel, String course) {
		Model model = mdel;
		try (Connection connection = Database.getConnection();
				Statement stmt = connection.createStatement();) {
			String sqldelete = "DELETE FROM " + TABLE_NAME + " WHERE Username=? AND CourseName=?";
			PreparedStatement pstmt = connection.prepareStatement(sqldelete);
			pstmt.setString(1, model.getcurrentStudent().getUsername());
			pstmt.setString(2, course);
			pstmt.executeUpdate();
			
			String sqlupdate = "UPDATE "+ TABLE_NAMECOURSE+" SET Capacity = Capacity+1 WHERE CourseName=? AND Mode=?";
			PreparedStatement statement = connection.prepareStatement(sqlupdate);
			statement.setString(1, course);
			statement.setString(2, "Face-to-face");
			statement.executeUpdate();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	public ArrayList Export(Model mdel) {
		ArrayList<String> array = new ArrayList<String>();
		Model model = mdel;
		try (Connection connection = Database.getConnection();
				Statement stmt = connection.createStatement();) {
			String sql = "SELECT * FROM "+TABLE_NAME+" WHERE Username= ?";
			PreparedStatement statement = connection.prepareStatement(sql);
			statement.setString(1, mdel.getcurrentStudent().getUsername());
			try (ResultSet rs = statement.executeQuery()){
				while(rs.next()) {
					array.add(rs.getString(2));
				}
			}
			
			
		} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
		return array;
	}
	
}
