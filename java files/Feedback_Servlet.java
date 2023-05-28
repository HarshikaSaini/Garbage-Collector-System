/*package garbage;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

*//**
 * Servlet implementation class Feedback_Servlet
 *//*
@WebServlet("/")
public class Feedback_Servlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    *//**
     * @see HttpServlet#HttpServlet()
     *//*
    public Feedback_Servlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	*//**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 *//*
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		response.getWriter().append("Served at: ").append(request.getContextPath());
	}

	*//**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 *//*
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
*/






















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
@WebServlet("/Feedback_Servlet")
public class Feedback_Servlet extends HttpServlet {
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
			//Statement stmt=con.createStatement();
			//String query="Select * from stateelection where aadharnumber='"+aadharnumber+ "' ";
			String query="Select * from complaint where Email_ID='"+email +"'  and Name='"+name+ "'  and Phone_Number='"+phoneno+"' and Date='"+date+"' and Time='"+time+"' ";
			Statement stmt1 = con.createStatement();
		    ResultSet rs1 = stmt1.executeQuery(query);
			//ResultSet rs=stmt.executeQuery(query);
			
			
			
			
						
			
			
			
		    
		    Statement stmt=con.createStatement();
			String qury= "delete from complaint where Email_ID='"+email +"'  and Name='"+name+ "'  and Phone_Number='"+phoneno+"' and Date='"+date+"' and Time='"+time+"' "; 
				
			int rs=stmt.executeUpdate(qury);
			
		    
			if(rs1.next()!=true)
			{
				response.sendRedirect("complaint_Error1.html");
			}
			else
			{
				String qry="insert into feebacktable values (?,?,?,?,?,?)";		
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
				
				response.sendRedirect("feedback_Error.html");
			}
		} 
		catch (Exception e) {
			out.println(e);
		}
	}

}

