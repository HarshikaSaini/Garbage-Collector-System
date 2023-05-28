package garbage;

import java.sql.*;
import java.sql.Connection;
import java.sql.DriverManager;
import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@SuppressWarnings("serial")
//@WebServlet("/RegisterationInsertionServlet")
@WebServlet("/SignUpForm")
//public class RegisterationInsertionServlet extends HttpServlet
public class SignUpForm extends HttpServlet{
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		/*int id=Integer.parseInt(request.getParameter("id"));*/
		String name=request.getParameter("name");
		String email=request.getParameter("email");
		Long phoneno=Long.parseLong(request.getParameter("phoneno"));
		

		String address=request.getParameter("address");
		System.out.println(address);
		
		
		String city=request.getParameter("city");
		System.out.println(city);
		
		String landmark=request.getParameter("landmark");
		System.out.println(landmark);
		Long pincode=Long.parseLong(request.getParameter("pincode"));
		System.out.println(pincode);
		
		String pass=request.getParameter("pass");
		System.out.println(pass);
		
		
		
		response.setContentType("text/html");
		PrintWriter out=response.getWriter();
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");  
			Connection con=DriverManager.getConnection("jdbc:mysql://localhost:3306/gar","root","12345");
			Statement stmt=con.createStatement();
			String query="Select * from kachra where Email_Id='"+email +"' "; /*and Address='"+address+ "'*/
			ResultSet rs=stmt.executeQuery(query);
			if(rs.next()==true)
			{
				response.sendRedirect("already_Registered.html");
			}
			
			
			else
			{
				String qry="insert into kachra values (?,?,?,?,?,?,?,?)";		
				PreparedStatement ps=con.prepareStatement(qry);
				/*ps.setInt(1, id);*/
				ps.setString(1, name);
				ps.setString(2, email);
				ps.setLong(3, phoneno);
				ps.setString(4, address);
				ps.setString(5, city);
				ps.setString(6, landmark);
				
				ps.setLong(7, pincode);
				ps.setString(8, pass);
				ps.executeUpdate();
				ps.close();
				con.close();
				response.sendRedirect("register_Success.html");
			}
		} 
		catch (Exception e) {
			out.println(e);
		}
	}
}


