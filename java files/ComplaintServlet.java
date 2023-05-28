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
@WebServlet("/ComplaintServlet")
public class ComplaintServlet extends HttpServlet {
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		
		String name=request.getParameter("name");
		String email=request.getParameter("email");
		Long phoneno=Long.parseLong(request.getParameter("phoneno"));
		String date=request.getParameter("date");
		String time=request.getParameter("time");
		String status=request.getParameter("status");
		
		
		
		response.setContentType("text/html");
		PrintWriter out=response.getWriter();
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");  
			Connection con=DriverManager.getConnection("jdbc:mysql://localhost:3306/gar","root","12345");
			Statement stmt=con.createStatement();
			//String query="Select * from stateelection where aadharnumber='"+aadharnumber+ "' ";
			String query="Select * from kachra where Email_ID='"+email +"'  ";/*and Address='"+address+ "'*/
			ResultSet rs=stmt.executeQuery(query);
			if(rs.next()!=true)
			{
				response.sendRedirect("complaint_Error1.html");
			}
			else
			{
				String qry="insert into complaint values (?,?,?,?,?,?)";		
				PreparedStatement ps=con.prepareStatement(qry);
				
				ps.setString(1, name);
				ps.setString(2, email);
				ps.setLong(3, phoneno);
				
				
				ps.setString(4, date);
				ps.setString(5, time);
				ps.setObject(6,status );
//				
				ps.executeUpdate();
				ps.close();
				con.close();
				response.sendRedirect("complaint_Error.html");
			}
		} 
		catch (Exception e) {
			out.println(e);
		}
	}

}

