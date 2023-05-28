<%@page import="java.sql.DriverManager"%>
<%@page import="java.sql.ResultSet"%>
<%@page import="java.sql.Statement"%>
<%@page import="java.sql.Connection"%>
<%

String driver = "com.mysql.jdbc.Driver";
String connectionUrl = "jdbc:mysql://localhost:3306/";
String database = "gar";
String userid = "root";
String password = "12345";
try {
Class.forName(driver);
} catch (ClassNotFoundException e) {
e.printStackTrace();
}
Connection connection = null;
Statement statement = null;
ResultSet resultSet = null;
%>
<!DOCTYPE html>
<html>
<body>
<head>
   <meta http-equiv="content-type" content="text/html; charset=utf-8" />
    	    <script src="https://code.jquery.com/jquery-3.2.1.min.js"></script>
    	<script type="text/javascript">
    	$.fn.infiniteScrollUp=function(){
    		var self=this,kids=self.children()
    		kids.slice(50).hide()
    		setInterval(function(){
    			kids.filter(':hidden').eq(0).fadeIn()
    			kids.eq(0).fadeOut(function(){
    				$(this).appendTo(self)
    				kids=self.children()
    			})
    		},1000)
    		return this
    	}
    	/*  $(function(){
    		$('tbody').infiniteScrollUp()
    	}) */ 
    	</script>
    	
     <title>Registration Page</title>
    <script src="https://kit.fontawesome.com/c9296ee6f9.js" crossorigin="anonymous"></script>
    <link href="afteradmin.css" rel="stylesheet" type="text/css">
<style>
	body{
	/* background: url( 'https://i.ytimg.com/vi/-ESweY0bvf8/maxresdefault.jpg');
  	background-repeat: no-repeat;
  	 background-size: 1400px 650px; */
	
	}
	table.center{
	   overflow-y:scroll;
	   height:350px;
	   display:block;
	   width:610px;
	    border-collapse: collapse;
  		   /* margin-right:auto;
  		   margin-left:auto; */
	}
	table.center th:nth-child(even){background-color: black;}

table.center td {background-color: #ddd;}
	
	td, th {
	padding-top: 50px;
  padding-bottom: 30px;
  text-align: left;
  background-color: #4CAF50;
  color: black;
	  border: 5px solid #060606;
	  text-align: left;
	  padding: 16px;


</style>
<title>infiniteScrollUp</title>


</head>




<header id="head">
        <nav>
            <ul id="nav-list">
               
                
                 <li><a href="firstpage.html">Home</a></li>
                <li><a href="aboutus.html">AboutUs</a></li>
                <li><a href="register.html">Register</a></li>
                <li><a href="admin.html">Admin login</a></li>
                <li><a href="contact.html">Contact Us</a></li>
                <li><a href="feedback.html">Feedback</a></li>  
            </ul>
        </nav>
        <div id="earth-outer">
            <i class="fa-sharp fa-solid fa-globe"></i>
        </div>
    </header>
    <aside>
        <div id="features">
            <ul id="fea">
                <!-- <li><button><a href="dashboard.html"> DASHBOARD</a></button></li> -->
                <li><button><a href="TotalComplaint.jsp">ALL COMPLAINS</a></button></li>
                <li><button><a href="Solved_Complaints.jsp">SOLVED QUERY</a></button></li>
                <!-- <li><button><a href="unsolved.html">UNSOLVED QUERY</a> </button></li>  -->
            </ul>
        </div>
    </aside>
    <div id="outer">
        <h2 style="position: relative; margin:20px; margin-left: 35rem; ">ALL COMPLAINTS DATA</h2><br>
        <div id="inner">
         <table class="center" >
			<tr>
			<td> Name  </td>
			<td> Email_Id </td>
			<td> Phone_No </td>
			<td> Date </td>
			<td> Time </td>
			
			
			</tr>
 
        </div>
       
    </div>



<!-- <h1 style="color:black;text-align:center;"><b>LIST OF VOTERS YOU CAN SEE YOUR NAME IN VOTERS LIST</b></h1>
 -->


<%
try{
connection = DriverManager.getConnection(connectionUrl+database, userid, password);
statement=connection.createStatement();
String sql ="select * from complaint";
resultSet = statement.executeQuery(sql);
while(resultSet.next()){
%>
<tr>
<td align="left"><%=resultSet.getString("Name") %></td> 
<td align="right"><%=resultSet.getString("Email_ID") %></td> 
<td align="right"><%=resultSet.getLong("Phone_Number") %></td> 
<td align="right"><%=resultSet.getString("Date") %></td> 
<td align="right"><%=resultSet.getString("time") %></td> 
<%-- 
<TR><TH><%=resultSet.getString(2)%></TH><TD><input type="text"></TD></TR>  --%>

</tr>
<%
}
connection.close();
} catch (Exception e) {
e.printStackTrace();
}
%>
</table>
