package com.result;

import javax.servlet.*;
import javax.servlet.http.*;
import java.io.*;

public class VerifyOTPServlet extends HttpServlet {

    protected void doPost(HttpServletRequest req, HttpServletResponse res)
            throws ServletException, IOException {

        int userOtp = Integer.parseInt(req.getParameter("otp"));
        HttpSession session = req.getSession();

        int realOtp = (int) session.getAttribute("otp");
        String enroll = (String) session.getAttribute("enroll");

        if (userOtp == realOtp) {
            res.sendRedirect("ResultServlet?enroll=" + enroll);
        } else {
            res.getWriter().println("Invalid OTP");
        }
    }
}
