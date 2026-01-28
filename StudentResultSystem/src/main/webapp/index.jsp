<!DOCTYPE html>
<html>
<head>
    <title>Student Result</title>

    <style>
        body {
            margin: 0;
            padding: 0;
            overflow: hidden;
        }

        .page {
            width: 100%;
            display: flex;
            justify-content: center;
            margin-top: 80px;
        }

        .result-box {
            background: #eeeeee;
            padding: 25px;
            border-radius: 10px;
            box-shadow: 0 0 15px rgba(0,0,0,0.25);
            width: 420px;
            border: 2px solid black;
        }
    </style>
</head>

<body>

<!-- ===== HEADER START ===== -->
<div style="
    width:100%;
    background:#003366;
    color:white;
    text-align:center;
    padding:18px 0;
    border-top:5px solid #c0c0c0;
    border-bottom:3px solid #002244;
    font-family: Arial, sans-serif;
">
    <h2 style="margin:0;">Dev Institute of Technology & Engineering</h2>
    <p style="margin:5px 0 0 0;">Result Portal</p>
</div>
<!-- ===== HEADER END ===== -->

<div class="page">
    <div class="result-box">

        <form action="SendOTPServlet" method="post">

            <div style="text-align:center; margin-bottom:25px;">
                <label style="font-size:17px; font-weight:bold;">
                    Enter Enrollment Number
                </label><br><br>

                <input type="text" name="enroll"
                       placeholder="e.g. 122024091"
                       style="width:65%; padding:12px; font-size:16px;"
                       required>
            </div>

            <div style="text-align:center;">
                <input type="submit"
                       value="Send OTP"
                       style="
                       padding:12px 40px;
                       font-size:16px;
                       font-weight:bold;
                       cursor:pointer;
                       background:#d32f2f;
                       color:white;
                       border:none;
                       border-radius:5px;">
            </div>

        </form>

    </div>
</div>

<!-- ===== FOOTER ===== -->
<div style="
    width: 100%;
    padding: 25px 0;
    background:#000000;
    color: #e5e7eb;
    text-align: center;
    font-family: 'Segoe UI', Arial, sans-serif;
    font-size: 18px;
    font-weight: 500;
    border-top: 4px solid #c0c0c0;
    box-shadow: 0 -4px 15px rgba(0,0,0,0.25);
    letter-spacing: 0.5px;
    position: fixed;
    bottom: 10px;
    left: 0;
">
    Developed by <strong style="font-weight:700;">Dev Deepak Pathak</strong>  
    | &copy; <%= java.time.Year.now() %>  
    <span style="margin-left:8px;">Result Management System</span>
</div>

</body>
</html>
