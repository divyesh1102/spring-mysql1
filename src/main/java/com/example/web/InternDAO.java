package com.example.web;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class InternDAO {

	private Connection con = null;
	private PreparedStatement ps = null;
	private ResultSet rs = null;

	public InternDAO() {
		try {
			Class.forName("com.mysql.jdbc.Driver");
			String dbURL = "jdbc:mysql://localhost:3306/intern?useSSL=false";
			con = DriverManager.getConnection(dbURL,"root","root");
		} catch (ClassNotFoundException | SQLException e) {
			e.printStackTrace();
		}
	}
	
	public void closeConn() {
		try {
			if (rs != null) {
				rs.close();rs=null;
			}
			if (ps != null) {
				ps.close();ps=null;
			}
			if (con != null) {
				con.close();con=null;
			}
		} catch (SQLException e) {
			e.printStackTrace();;
		}
	}
	
	public boolean save(Intern intern){
		boolean flag = false;
		try {
			String sql = "INSERT INTO interndata(id,name,tech) VALUES(?,?,?)";
			ps = con.prepareStatement(sql);
			ps.setInt(1, intern.getId());
			ps.setString(2, intern.getName());
			ps.setString(3, intern.getTech());
			
			if(ps.executeUpdate() > 0) {
				flag = true;
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			if(con!=null)closeConn();
		}
		return flag;
	}
	
	
	public Intern findById(Integer id) {
		Intern intern = null;
		try {
			String sql = "SELECT * FROM interndata where id = ?";
			ps = con.prepareStatement(sql);
			ps.setInt(1, id);
			rs = ps.executeQuery();
			while(rs.next()) {
				intern = new Intern();
				intern.setId(rs.getInt("id"));
				intern.setName(rs.getString("name"));
				intern.setTech(rs.getString("tech"));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			if(con!=null)closeConn();
		}
		return intern;
	}
	
	public List<Intern> findAll(){
		List<Intern> data = new ArrayList<Intern>();
		
		try {
			String query = "select * from interndata";
			ps = con.prepareStatement(query);
			rs = ps.executeQuery();
			
			while(rs.next()) {
				Intern intern = new Intern();
				intern.setId(rs.getInt("id"));
				intern.setName(rs.getString("name"));
				intern.setTech(rs.getString("tech"));
				data.add(intern);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		finally {
			if(con!=null)closeConn();
		}
		
		return data;
	}
}
