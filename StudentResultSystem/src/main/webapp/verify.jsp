<!DOCTYPE html>
<html>
<head>
    <title>Verify OTP</title>

    <style>
        * {
            margin: 0;
            padding: 0;
            box-sizing: border-box;
        }

        body {
            height: 100vh;
            background: #ffffff;
            font-family: Arial, sans-serif;
            display: flex;
            flex-direction: column;
            overflow: hidden;
        }

        .header {
            width:100%;
            background:#003366;
            color:white;
            text-align:center;
            padding:20px 0;
            border-bottom:3px solid #002244;
            margin-top: 10px;
        }

        .container {
            flex: 1;
            display: flex;
            justify-content: center;
            align-items: center;
        }

        .otp-box {
            width: 360px;
            background: #eeeeee;
            padding: 30px;
            border-radius: 10px;
            border: 2px solid black;
            box-shadow: 0 0 15px rgba(0,0,0,0.25);
            text-align: center;
        }

        .otp-box h2 {
            margin-bottom: 20px;
            color: #003366;
        }

        .otp-box input[type="text"] {
            width: 90%;
            padding: 12px;
            font-size: 16px;
            border: 1px solid #bbb;
            border-radius: 5px;
            margin-bottom: 20px;
        }

        .otp-box input[type="submit"] {
            background: #d32f2f;
            color: white;
            border: 2px solid black;
            padding: 12px 35px;
            font-size: 16px;
            border-radius: 5px;
            cursor: pointer;
        }

        .footer {
            width: 100%;
            background:#000000;
            color: #e5e7eb;
            text-align: center;
            font-size: 18px;
            border-top: 4px solid #c0c0c0;
            padding: 25px 0;
            margin-bottom: 10px;
        }
    </style>
</head>

<body>

<div class="header">
    <h2>Dev Institute of Technology & Engineering</h2>
    <p>Result Portal</p>
</div>

<%
    Boolean showMsg = (Boolean) session.getAttribute("showMsg");
    String msg = (String) session.getAttribute("msg");

    Long otpTime = (Long) session.getAttribute("otpTime");
    long remaining = 0;

    if (otpTime != null) {
        remaining = 30 - ((System.currentTimeMillis() - otpTime) / 1000);
        if (remaining < 0) remaining = 0;
    }

    if (showMsg != null && showMsg) {
%>
    <div style="
        background:#d4edda;
        color:#155724;
        padding:10px;
        margin:10px auto;
        width:360px;
        border:1px solid #c3e6cb;
        border-radius:5px;
        text-align:center;
        font-weight:bold;">
        <%= msg %>
    </div>
<%
        session.removeAttribute("showMsg");
        session.removeAttribute("msg");
    }
%>

<div class="container">
    <div class="otp-box">

        <h2>Verify OTP</h2>

        <form action="VerifyOTP" method="post">
            <input type="text" name="otp" placeholder="Enter OTP" required>
            <br>
            <input type="submit" value="Verify OTP">
        </form>

        <p style="margin-top:10px;color:red;font-weight:bold;">
            OTP valid for <span id="time"><%= remaining %></span> seconds
        </p>

        <form action="ResendOTP" method="post" style="margin-top:15px;">
            <input type="submit"
                   id="resendBtn"
                   value="Resend OTP"
                   <%= (remaining > 0 ? "disabled" : "") %>
                   style="
                   padding:10px 30px;
                   font-size:14px;
                   font-weight:bold;
                   background:<%= (remaining > 0 ? "#999" : "#333") %>;
                   color:white;
                   border:2px solid black;
                   border-radius:5px;
                   cursor:<%= (remaining > 0 ? "not-allowed" : "pointer") %>;
                   ">
        </form>

        <p style="margin-top:15px; font-size:13px; color:gray;">
            Secure Result Verification System
        </p>

    </div>
</div>

<div class="footer">
    Developed by <strong>Dev Deepak Pathak</strong> |
    &copy; <%= java.time.Year.now() %> Result Management System
</div>

<script>
    let time = <%= remaining %>;
    let timer = document.getElementById("time");
    let resendBtn = document.getElementById("resendBtn");

    let interval = setInterval(() => {
        if (time > 0) {
            time--;
            timer.innerText = time;
        } else {
            clearInterval(interval);
            timer.innerText = "Expired";
            resendBtn.disabled = false;
            resendBtn.style.background = "#333";
            resendBtn.style.cursor = "pointer";
        }
    }, 1000);
</script>

</body>
</html>
