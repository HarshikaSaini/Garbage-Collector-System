

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
@WebServlet("/Login_Form")
//public class RegisterationInsertionServlet extends HttpServlet
public class Login_Form extends HttpServlet{
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		/*int id=Integer.parseInt(request.getParameter("id"));*/
		
		String email=request.getParameter("email");
		
		
		String pass=request.getParameter("pass");
		System.out.println(pass);
		
		
		
		response.setContentType("text/html");
		PrintWriter out=response.getWriter();
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");  
			Connection con=DriverManager.getConnection("jdbc:mysql://localhost:3306/gar","root","12345");
			

			
			String qry="Select * from kachra where Email_Id='"+email +"' and Password='"+pass+ "' ";
			Statement stmt1 = con.createStatement();
		    ResultSet rs1 = stmt1.executeQuery(qry);
		    
		    
		    Statement stmt=con.createStatement();
			String query="Select * from login_table where Email_ID='"+email +"'  ";
			ResultSet rs=stmt.executeQuery(query);
		    
			if(rs1 .next()!=true && rs.next()!=true)
			{
				response.sendRedirect("registered_Error.html");
			}
			else if(rs1 .next()==true && rs.next()==true) {
				//System.out.println("cx");
				response.sendRedirect("complaint.html");
			}
			
			
			else
			{
				String qury="insert into login_table values (?,?)";		
				PreparedStatement ps=con.prepareStatement(qury);
				/*ps.setInt(1, id);*/
			
				ps.setString(1, email);
				
				ps.setString(2, pass);
				ps.executeUpdate();
				ps.close();
				con.close();
				response.sendRedirect("complaint.html");
			}
		} 
		catch (Exception e) {
			out.println(e);
		}
	}
}














