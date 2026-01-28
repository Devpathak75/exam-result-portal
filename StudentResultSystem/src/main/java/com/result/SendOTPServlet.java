package com.result;

import java.io.*;
import java.sql.*;
import java.util.*;
import javax.mail.*;
import javax.mail.internet.*;
import javax.servlet.*;
import javax.servlet.http.*;

public class SendOTPServlet extends HttpServlet {

    // Safety: GET bhi POST ko call kare
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        doPost(request, response);
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        response.setContentType("text/html");

        String enroll = request.getParameter("enroll");

        try {
            Class.forName("com.mysql.cj.jdbc.Driver");

            Connection con = DriverManager.getConnection(
                "jdbc:mysql://localhost:3306/student_result",
                "root", "devpathak");

            PreparedStatement ps =
                con.prepareStatement("SELECT email FROM students WHERE enrollment_no=?");

            ps.setString(1, enroll);
            ResultSet rs = ps.executeQuery();

            if (!rs.next()) {
                response.getWriter().println("<h3 style='color:red;'>Enrollment not found</h3>");
                return;
            }

            String email = rs.getString("email");

            if (email == null || email.trim().equals("")) {
                response.getWriter().println("<h3 style='color:red;'>Email not available for this student</h3>");
                return;
            }

            // ✅ Generate OTP
            int otp = new Random().nextInt(900000) + 100000;

            HttpSession session = request.getSession();

            // ✅ IMPORTANT — TIMER START HERE
            session.setAttribute("otp", otp);
            session.setAttribute("otpTime", System.currentTimeMillis());
            session.setAttribute("enroll", enroll);
            session.setAttribute("msg", "OTP sent successfully to " + email);
            session.setAttribute("showMsg", true);

            // ✅ Send mail
            sendMail(email, otp);

            // Redirect to verify page
            response.sendRedirect("verify.jsp");

            rs.close();
            ps.close();
            con.close();

        } catch (Exception e) {
            e.printStackTrace();
            response.getWriter().println(
                "<h3 style='color:red;'>Mail Error: " + e.getMessage() + "</h3>"
            );
        }
    }

    void sendMail(String to, int otp) throws Exception {

        String from = "devinstituteoftechnologyandeng@gmail.com";
        String pass = "bpcnqabjpkrqmghu";  // App password

        Properties props = new Properties();
        props.put("mail.smtp.host", "smtp.gmail.com");
        props.put("mail.smtp.port", "587");
        props.put("mail.smtp.auth", "true");
        props.put("mail.smtp.starttls.enable", "true");

        Session session = Session.getInstance(props,
            new Authenticator() {
                protected PasswordAuthentication getPasswordAuthentication() {
                    return new PasswordAuthentication(from, pass);
                }
            });

        Message msg = new MimeMessage(session);
        msg.setFrom(new InternetAddress(from));
        msg.setRecipients(Message.RecipientType.TO,
                InternetAddress.parse(to));
        msg.setSubject("OTP Verification");
        msg.setText("Your OTP is: " + otp);

        Transport.send(msg);
    }
}
