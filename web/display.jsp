<%-- 
    Document   : display
    Created on : Aug 22, 2020, 2:12:46 PM
    Author     : kavin
--%>

<%@page import="database.databasecon"%>

<%@page import="java.sql.ResultSet"%>
<%@page import="java.sql.Statement"%>
<%@page import="java.sql.DriverManager"%>
<%@page import="java.sql.Connection"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>


<html>
    <link rel="stylesheet" href="css/bootstrap.css">
<link rel="stylesheet" href="css/style.css">
<link rel="stylesheet" href="css/animate.css">
	
	<link rel="stylesheet" href="css/icomoon.css">
	<link rel="stylesheet" href="css/themify-icons.css">
        <link rel="stylesheet" href="css/magnific-popup.css">
	<link rel="stylesheet" href="css/bootstrap-datepicker.min.css">
	<link rel="stylesheet" href="css/owl.carousel.min.css">
	<link rel="stylesheet" href="css/owl.theme.default.min.css">
        <link rel="stylesheet" href="css/style.css">
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Admin(booking Details)</title>
    </head>
    <body><center><h1> BOOKING DETAILS</h1>

        <table border="1" class="table table-striped">
            <tr>
                <th>ID</th>
                <th>Full Name</th>
                <th>Email</th>
                <th>First Date</th>
                <th>Last Date</th>
                <th>Hotel</th>
                <th>Destination</th>
                <th>Package</th>
                <th></th>



            </tr>
            <%
                try {
                    Statement statement = null;
                    ResultSet resultSet = null;

                    databasecon obj_DB_Connection = new databasecon();
                    Connection connection = obj_DB_Connection.get_connection();
                    statement = connection.createStatement();
                    String sql = "SELECT * FROM trip ";

                    resultSet = statement.executeQuery(sql);
                    while (resultSet.next()) {
            %>

            <tr>

                <td><label>
                        
                        <%=resultSet.getString("id")%></label></td>

                <td><label>
                      
                        <%=resultSet.getString("fullname")%></label></td>
                <td><label>
                       
                        <%=resultSet.getString("email")%></label></td>
                <td><label>
                       
                        <%=resultSet.getString("firstd")%></label></td>
                <td><label>
                        
                        <%=resultSet.getString("lastd")%></label></td>
                <td><label>
                        
                        <%=resultSet.getString("hotel")%></label></td>
                <td><label>
                        
                        <%=resultSet.getString("destination")%></label></td>
                <td><label>
                        
                        <%=resultSet.getString("package")%></label></td>
                <td><form action="delete" method="post">
                                            <input style="display: none;" type="text" name="req_id"
                                                   value='<%=resultSet.getString("id")%>'>
                                            <button type="submit" class="btn danger" >Delete</button>
                                        </form></td>



            </tr>
            <%
                        }
                    %>

                    <%
                        } catch (Exception e) {
                            e.printStackTrace();
                        }
                    %>


        </table>  



    </center>
</body>
</html>
