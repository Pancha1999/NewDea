/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package servlets;

import java.io.IOException;
import java.io.PrintWriter;
import static java.lang.System.out;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author USER
 */
public class booking extends HttpServlet {

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
            out.println("<title>Servlet booking</title>");            
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet booking at " + request.getContextPath() + "</h1>");
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
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
        try {
            String fname = request.getParameter("fullname");
            String email = request.getParameter("email");
            String Destination = request.getParameter("destination");
            String datestart = request.getParameter("datestart");
            String dateend = request.getParameter("dateend");
            String hotel = request.getParameter("hotel");
            String packag = request.getParameter("package");
            
            
            

                databasecon obj_DB_Connection = new databasecon();
                Connection connection = obj_DB_Connection.get_connection();
                PreparedStatement ps = null;

                String sql = "insert into trip (fullname,email,firstd,lastd,hotel,destination,package) values(?,?,?,?,?,?,?)";
                Class.forName("com.mysql.jdbc.Driver");
                
            try {
                ps = connection.prepareStatement(sql);
            } catch (SQLException ex) {
                Logger.getLogger(booking.class.getName()).log(Level.SEVERE, null, ex);
            }
                
                
             
                
                ps.setString(1, fname);
                ps.setString(2, email);
                ps.setString(6, Destination);
                ps.setString(3, datestart);
                ps.setString(4,dateend);
                ps.setString(5,hotel);
                ps.setString(7,packag);
                
                
                ps.executeUpdate();
                out.println("<script type=\"text/javascript\">");
                out.println("alert('Successfully Booked !!');");
                out.println("location='home.html';");
                out.println("</script>");
            

        } catch (SQLException | ClassNotFoundException ex) {
            out.println(ex);
        }
	}
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

    }

    /**
     * Returns a short description of the servlet.
     *
     * @return a String containing servlet description
     */
  
    

