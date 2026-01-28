🎓 Student Result Management System with OTP Verification
Java | JSP | Servlet | MySQL | SMTP | Maven
📌 Project Overview

This project is a Student Result Management System developed using Java, JSP, Servlet, MySQL, and Maven.

The system allows students to:

Enter their Enrollment Number

Receive OTP on registered email

Verify OTP with 60 seconds expiry

View their complete academic result

Download result

Secure authentication using OTP

This project is designed for college/university result portals and works on real email authentication.

🏫 Project Scenario

Dummy University: Dev Pathak Personal University

Dummy College: Dev Institute of Technology & Engineering

Students are registered with:

Enrollment Number

Email ID

Personal Details

Subject Marks

OTP is sent to registered email only

Result is shown only after OTP verification

🧠 Features

✅ Real OTP generation
✅ OTP sent to registered email
✅ OTP expiry (60 seconds)
✅ Resend OTP feature
✅ Secure OTP verification
✅ Session handling
✅ Result display with grades
✅ Result download option
✅ Header & footer UI
✅ MySQL database integration
✅ Maven based project
✅ Email authentication using Gmail SMTP

🗂️ Project Structure
StudentResultSystem
│
├── src/main/java
│   └── com.result
│       ├── SendOTPServlet.java
│       ├── VerifyOTPServlet.java
│       ├── ResendOTPServlet.java
│       └── ResultServlet.java
│
├── src/main/webapp
│   ├── index.jsp
│   ├── verify.jsp
│   ├── result.jsp
│   ├── images/
│   │   ├── college.png
│   │   └── university.png
│   └── WEB-INF
│       └── web.xml
│
├── pom.xml
└── database.sql

🧾 Database Details

Database Name: student_result

Student Table Fields:

id

enrollment_no

name

mother_name

abc_id

branch

email

subject1 to subject8

🔐 OTP Flow

Student enters Enrollment Number

OTP sent to registered email

OTP valid for 60 seconds

OTP verified

Result displayed

OTP expires automatically

Resend OTP option available

⚙️ Technologies Used
Technology	Usage
Java	Backend Logic
JSP	Frontend
Servlet	Controller
MySQL	Database
Maven	Dependency Management
SMTP	Email OTP
HTML/CSS	UI
Apache Tomcat	Server
📧 Email Configuration

OTP is sent using:

Gmail SMTP

App Password

Secure TLS connection

📸 Screens Included

Enrollment Page

OTP Verification Page

Result Page

College Header & Footer

Real OTP Mail

🏆 Project Highlights

✔ Real-world project
✔ College-level system
✔ Secure authentication
✔ Professional UI
✔ Perfect for Resume & Final Year Project

👨‍💻 Developed By

Dev Deepak Pathak
📍 Nashik, Maharashtra
🎓 Diploma Engineer
💻 Java | JSP | MySQL | Web Developer
