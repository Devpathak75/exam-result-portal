package com.result;

import javax.servlet.*;
import javax.servlet.http.*;
import java.io.*;

public class VerifyOTPServlet extends HttpServlet {

    protected void doPost(HttpServletRequest req, HttpServletResponse res)
            throws ServletException, IOException {

        HttpSession session = req.getSession();

        // ✅ OTP & time get karo
        Integer realOtp = (Integer) session.getAttribute("otp");
        Long otpTime = (Long) session.getAttribute("otpTime");
        String enroll = (String) session.getAttribute("enroll");

        // 🔴 Session expired
        if (realOtp == null || otpTime == null || enroll == null) {
            res.getWriter().println("<h3 style='color:red;'>Session expired. Please resend OTP.</h3>");
            return;
        }

        // ✅ Safe OTP read
        String otpInput = req.getParameter("otp");

        if (otpInput == null || otpInput.trim().isEmpty()) {
            res.getWriter().println("<h3 style='color:red;'>Please enter OTP</h3>");
            return;
        }

        // 🔥 Remove spaces / symbols
        otpInput = otpInput.trim();
        otpInput = otpInput.replaceAll("[^0-9]", "");

        int userOtp;
        try {
            userOtp = Integer.parseInt(otpInput);
        } catch (Exception e) {
            res.getWriter().println("<h3 style='color:red;'>Invalid OTP format</h3>");
            return;
        }

        // ⏱ OTP expiry (60 sec)
        long currentTime = System.currentTimeMillis();
        if ((currentTime - otpTime) > 60000) {
            session.removeAttribute("otp");
            session.removeAttribute("otpTime");

            res.getWriter().println("<h3 style='color:red;'>OTP expired. Please resend OTP.</h3>");
            return;
        }

        // ✅ OTP MATCH
        if (userOtp == realOtp) {

            // clear otp after success
            session.removeAttribute("otp");
            session.removeAttribute("otpTime");

            res.sendRedirect("ResultServlet?enroll=" + enroll);

        } else {
            res.getWriter().println("<h3 style='color:red;'>Invalid OTP</h3>");
        }
    }
}
