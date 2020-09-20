/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package servlets;

import database.databasecon;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 *
 * @author sachini
 */
public class loginservlet extends HttpServlet {

    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        try (PrintWriter out = response.getWriter()) {
            /* TODO output your page here. You may use following sample code. */
            out.println("<!DOCTYPE html>");
            out.println("<html>");
            out.println("<head>");
            out.println("<title>Servlet loginservlet</title>");
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet loginservlet at " + request.getContextPath() + "</h1>");
            out.println("</body>");
            out.println("</html>");
        }
    }

    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /**
     * Handles the HTTP <code>GET</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Handles the HTTP <code>POST</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        PrintWriter out = response.getWriter();

        try {

            String username = request.getParameter("newuser");
            String password = request.getParameter("password");
            String dbUsername = null;
            String dbPassword = null;
            String dbtype = null;
            String email = null;

            databasecon obj_DB_Connection = new databasecon();
            Connection connection = obj_DB_Connection.get_connection();
            PreparedStatement ps = null;
            ResultSet rs = null;

            String sql = "select username,password,type,email from form where username=? and password=?";
            Class.forName("com.mysql.jdbc.Driver");
            ps = connection.prepareStatement(sql);

            ps.setString(1, username);
            ps.setString(2, password);
            rs = ps.executeQuery();
            while (rs.next()) {
                dbUsername = rs.getString(1);
                dbPassword = rs.getString(2);
                dbtype = rs.getString(3);
                email = rs.getString(4);

            }
            if (username.equals(dbUsername) && password.equals(dbPassword)) {

                HttpSession session = request.getSession();
                session.setAttribute("LogedInUserName", username);

                session.setAttribute("LogedInUserType", dbtype);
                session.setAttribute("email", email);

                if (dbtype.equals("admin")) {
                    session.setAttribute("LogedInOwnerName", username);

                    out.println("<script type=\"text/javascript\">");
                    out.println("location='display.jsp';");
                    out.println("</script>");
                } else {
                    session.setAttribute("LogedInUserName", username);

                    out.println("<script type=\"text/javascript\">");
                    out.println("location='home.html';");
                    out.println("</script>");
                }

            } else {
                out.println("<script type=\"text/javascript\">");
                out.println("alert('Username Or Password is wrong !!');");
                out.println("location='login.jsp';");
                out.println("</script>");
            }

        } catch (SQLException ex) {
            out.println(ex);
        } catch (ClassNotFoundException ex) {
            out.println(ex);
        }
    }

    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
