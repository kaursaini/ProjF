package model;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class StudentDAO {

	private static final String DB_URL = "jdbc:derby://localhost:64413/EECS;user=student;password=secret";
	String query;

	public List<StudentBean> retrieve(String name, String gpa, String sortBy) throws Exception {

		//System.out.println("inside student dai class");
		Class.forName("org.apache.derby.jdbc.ClientDriver").newInstance();
		Connection con = DriverManager.getConnection(DB_URL);
		Statement s = con.createStatement();
		s.executeUpdate("set schema roumani");

		double minGpa = Double.parseDouble(gpa);
		if (sortBy != null && !sortBy.isEmpty()) {
			query = "SELECT * FROM SIS WHERE SURNAME LIKE '" + name + "%' AND GPA >= " + minGpa + " order by " + sortBy;
		} else {
			query = "SELECT * FROM SIS WHERE SURNAME LIKE '" + name + "%' AND GPA >= " + minGpa;
		}

		//System.out.println(sortBy);
		//System.out.println(query);
		ResultSet r = s.executeQuery(query); 

		List<StudentBean> studentList = new ArrayList<>();

		while (r.next()) {
			//System.out.println("entering while loop");
			
			StudentBean student = new StudentBean();
			student.setCourses(r.getInt("COURSES"));
			student.setGpa(r.getDouble("GPA"));
			student.setMajor(r.getString("MAJOR"));
			student.setName(r.getString("SURNAME") + ", " + r.getString("GIVENNAME"));

			//System.out.println(student.toString());

			studentList.add(student);
		}

		return studentList;

	}
}
