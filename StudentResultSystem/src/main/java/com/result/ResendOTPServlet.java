package com.result;

import java.io.*;
import java.sql.*;
import java.util.*;
import javax.mail.*;
import javax.mail.internet.*;
import javax.servlet.*;
import javax.servlet.http.*;

public class ResendOTPServlet extends HttpServlet {

    // GET bhi POST ko call kare (safety)
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        doPost(request, response);
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        HttpSession session = request.getSession();
        String enroll = (String) session.getAttribute("enroll");

        if (enroll == null) {
            response.getWriter().println("Session expired. Please try again.");
            return;
        }

        try {
            Class.forName("com.mysql.cj.jdbc.Driver");

            Connection con = DriverManager.getConnection(
                "jdbc:mysql://localhost:3306/student_result",
                "root", "devpathak");

            PreparedStatement ps =
                con.prepareStatement("SELECT email FROM students WHERE enrollment_no=?");

            ps.setString(1, enroll);
            ResultSet rs = ps.executeQuery();

            if (rs.next()) {

                String email = rs.getString("email");

                // 🔥 Generate new OTP
                int otp = new Random().nextInt(900000) + 100000;

                // ✅ IMPORTANT: Reset OTP + TIMER
                session.setAttribute("otp", otp);
                session.setAttribute("otpTime", System.currentTimeMillis());
                session.setAttribute("msg", "OTP resent successfully");
                session.setAttribute("showMsg", true);

                // Send mail
                sendMail(email, otp);

                response.sendRedirect("verify.jsp");

            } else {
                response.getWriter().println("User not found");
            }

            rs.close();
            ps.close();
            con.close();

        } catch (Exception e) {
            e.printStackTrace();
            response.getWriter().println("Error while resending OTP");
        }
    }

    private void sendMail(String to, int otp) throws Exception {

        String from = "devinstituteoftechnologyandeng@gmail.com";
        String pass = "bpcnqabjpkrqmghu";

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
        msg.setSubject("Resent OTP - Result Portal");
        msg.setText("Your new OTP is: " + otp);

        Transport.send(msg);
    }
}
