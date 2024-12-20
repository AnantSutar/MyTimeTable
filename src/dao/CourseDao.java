package dao;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.Serializable;
import java.sql.Connection;
import java.util.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Scanner;





public class CourseDao {
	private final String TABLE_NAME = "Coursedata";
private HashMap<String, CourseDetails> cdets = new LinkedHashMap<String, CourseDetails>();
	
	private Scanner scan = new Scanner(System.in);
	
	public void readData(String filename){
		Scanner scanM=null;
		try {
			scanM = new Scanner(new File(filename));
		} catch (FileNotFoundException e) {
			System.out.println("File does not exist");
		}
		catch (NullPointerException e) {
        	System.err.println("File is null.");    
        }
		String tt = scanM.nextLine();
		System.out.println(tt);
		while (scanM.hasNext()) {
			String temp = scanM.nextLine();
			String[] arrOfStr = temp.split(",");
			String cn = arrOfStr[0];
			int cap;
			if(arrOfStr[1].equals("N/A")) {
				cap=0;
			}else {
				cap = Integer.parseInt(arrOfStr[1]);
			}
			String yr = arrOfStr[2];
			String dm = arrOfStr[3];
			String day = arrOfStr[4];
			String times = arrOfStr[5];
			float duration = Float.parseFloat(arrOfStr[6]);
			String modifiedTime="";
			SimpleDateFormat sdf = new SimpleDateFormat("hh:mm");
			Date date = null;
			try {
	            date = sdf.parse(times);
	            Calendar calendar = Calendar.getInstance();
	            calendar.setTime(date);
	            
	            float hoursToAdd = duration; // Example: adding 2 hours // Adding 1.5 hours
	            int minutesToAdd = (int) (hoursToAdd * 60);
	            calendar.add(Calendar.MINUTE, minutesToAdd);

	            Date modifiedDate = calendar.getTime();
	            modifiedTime = sdf.format(modifiedDate);
	           
	        } catch (ParseException e) {
	            e.printStackTrace();
	        }
			
			String finaltime = times+"-"+modifiedTime;
			cdets.put(cn, new CourseDetails(cn, cap, yr,dm,day,finaltime,duration));
		}
	}
	
	public void CourseDB() {
		int i=0;
		try (Connection connection = Database.getConnection();
			Statement stmt = connection.createStatement();) {
			String sqlcheck = "SELECT COUNT(*) FROM " + TABLE_NAME;
			
			
				
				String sql = "CREATE TABLE IF NOT EXISTS " + TABLE_NAME + "(CourseName varchar(20)"
						+ ",Capacity integer"
						+ ",Year varchar(20)"
						+ ",Mode varchar(20)"
						+ ",day varchar(20)"
						+ ",Time varchar(20)"
						+ ",duration real)";
				stmt.executeUpdate(sql);
				try(ResultSet rs = stmt.executeQuery(sqlcheck)){
					if(rs.next()) {
						String count = rs.getString(1);
						i = Integer.parseInt(count);
					}
				}
				if (i==0) {
				for (String s : cdets.keySet()) {
					CourseDetails b = cdets.get(s);
					
				String sqlinsert = "INSERT INTO " + TABLE_NAME + " VALUES (?,?,?,?,?,?,?)";
				PreparedStatement pstmt = connection.prepareStatement(sqlinsert);
				pstmt.setString(1, b.getcoursename());
				pstmt.setInt(2, b.getcapacity());
				pstmt.setString(3, b.getyear());
				pstmt.setString(4, b.getdelmode());
				pstmt.setString(5, b.getday());
				pstmt.setString(6, b.gettimes());
				pstmt.setDouble(7, b.getduration());
				pstmt.executeUpdate();
				}}
			
				
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public HashMap getCourseDBinfo() {
		HashMap<String, CourseDetails> cdets = new LinkedHashMap<String, CourseDetails>();
		try (Connection connection = Database.getConnection();
				Statement stmt = connection.createStatement();){
			String sql = "SELECT * FROM " + TABLE_NAME;
			try(ResultSet rs = stmt.executeQuery(sql)){
				System.out.println(rs.getFetchSize());
				while(rs.next()) {
					String cn = rs.getString(1);
					int cap = rs.getInt(2);
					String year = rs.getString(3);
					String dm = rs.getString(4);
					String day = rs.getString(5);
					String times = rs.getString(6);
					Double duration = rs.getDouble(7);
					cdets.put(cn, new CourseDetails(cn, cap, year,dm,day,times,duration));
				}
			}
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return cdets;
	}
}
