package garbage;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.Date;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
* Servlet implementation class ComplaintServlet
*/
@WebServlet("/Admin1_Form_Servlet")
public class Admin1_Form_Servlet extends HttpServlet {
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		
		String name=request.getParameter("name");
		System.out.println(name);
		String email=request.getParameter("email");
		Long phoneno=Long.parseLong(request.getParameter("phoneno"));
		Long keyid=Long.parseLong(request.getParameter("keyid"));
		System.out.println(keyid);
		String pass=request.getParameter("pass");	
		
		
		
		response.setContentType("text/html");
		PrintWriter out=response.getWriter();
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");  
			Connection con=DriverManager.getConnection("jdbc:mysql://localhost:3306/gar","root","12345");
			Statement stmt=con.createStatement();
			//String query="Select * from stateelection where aadharnumber='"+aadharnumber+ "' ";
			String query="Select * from admin_table where Email='"+email +"'  and KeyID='"+keyid+ "' ";
			ResultSet rs=stmt.executeQuery(query);
			if(rs.next()==true)
			{
				response.sendRedirect("afteradmin.html");
			}
			else
			{
				String qry="insert into admin_table values (?,?,?,?,?)";		
				PreparedStatement ps=con.prepareStatement(qry);
				
				ps.setString(1, name);
				ps.setString(2, email);
				ps.setLong(3, phoneno);
				ps.setLong(4, keyid);
				ps.setString(5, pass);
				
				
				
				
				
				ps.executeUpdate();
				ps.close();
				con.close();
				response.sendRedirect("admin.html");
			}
		} 
		catch (Exception e) {
			out.println(e);
		}
	}

}
















