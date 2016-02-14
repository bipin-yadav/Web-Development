package org.tlp.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;



import org.tlp.bean.AuthorBean;

public class LoginDao {
	
	Connection con  = null;
	String password = "";

	public String validateUserLogin(AuthorBean ab) {
		System.out.println("in login dao");
		try {
			Class.forName("com.mysql.jdbc.Driver");
		} catch (ClassNotFoundException e) {
			System.out.println("Where is your MySQL JDBC Driver?");
			e.printStackTrace();		 
		}
		
		try {
			//con = DriverManager.getConnection("jdbc:mysql://tc.c4ebuphfnshg.us-west-2.rds.amazonaws.com:3306/tc","root", "root1234");
			con = DriverManager.getConnection("jdbc:mysql://localhost:3306/tc","root", "root");
		} catch (SQLException e1) {
			e1.printStackTrace();
		} 
		
		if (ab.getUserid().isEmpty() || ab.getPassword().isEmpty()) {
			return "FAILED";
		} else {
			String sql = "select password from tc.author where userid=?";
			try {
				 				
				PreparedStatement ps = con.prepareStatement(sql);
				ps.setString(1, ab.getUserid());
				ResultSet res = ps.executeQuery();
				while (res.next()) {
					password = res.getString(1);
				}
				if(password.equals(ab.getPassword())){					 
					return "SUCCESS";					 
				} else if (password == null) {
					return "INVALID";
				} else {
					return "FAILED";
				}

			} catch (SQLException sqle) {
				System.out.println(sqle);
				return "ERROR";
			} finally {
				if (con   != null) {
					try {
						con.close();
					} catch (SQLException e) {
					}
				}
			}

		}
		 
	}	 

	public boolean logout(String id) {
		Connection conn =null;
		try {
			Class.forName("com.mysql.jdbc.Driver");
		} catch (ClassNotFoundException e) {
			System.out.println("Where is your MySQL JDBC Driver?");
			e.printStackTrace();		 
		}
		
		try {
			con = DriverManager.getConnection("jdbc:mysql://localhost:3306/tc","root", "root1234");
		} catch (SQLException e1) {
			e1.printStackTrace();
		} 
		
		
		String query = "update tc.author set login_status=0 where userid =?";
		 
		try {		 
			 			 
		System.out.println("********>>" + query + "<<*****");
			PreparedStatement pst = con.prepareStatement(query);
			pst.setString(1, id);
			int i = pst.executeUpdate();
			if (i == 1) {
				return true;
			}
			 
		} catch (SQLException e) {
			System.out.println("Problem in saving logout status");
			e.printStackTrace();
		}finally{
			try {
				con.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}	
		 
		}
		return false;
		
	}	
	

}
