package com.result;

import java.io.IOException;
import java.sql.*;

import javax.servlet.*;
import javax.servlet.http.*;

public class ResultServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        // 🔹 OTP ke baad session me jo enrollment save hua tha
        HttpSession session = request.getSession();
        String enroll = (String) session.getAttribute("enroll");

        // Agar session expire ho gaya ho
        if (enroll == null) {
            response.getWriter().println("Session Expired. Please try again.");
            return;
        }

        try {
            Class.forName("com.mysql.cj.jdbc.Driver");

            Connection con = DriverManager.getConnection(
                "jdbc:mysql://localhost:3306/student_result",
                "root",
                "devpathak"
            );

            PreparedStatement ps =
                con.prepareStatement("SELECT * FROM students WHERE enrollment_no=?");

            ps.setString(1, enroll);
            ResultSet rs = ps.executeQuery();

            if (rs.next()) {

                // Result JSP ko data bhejna
                request.setAttribute("rs", rs);
                RequestDispatcher rd =
                        request.getRequestDispatcher("result.jsp");
                rd.forward(request, response);

            } else {
                response.getWriter().println("Result Not Found");
            }

            rs.close();
            ps.close();
            con.close();

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
