package com.example;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.*;

import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@SuppressWarnings("serial")
public class LoginRegisterServlet extends HttpServlet {

    protected void doPost(HttpServletRequest req, HttpServletResponse res) throws IOException {
        String action = req.getParameter("action");
        String username = req.getParameter("username");
        String password = req.getParameter("password");

        res.setContentType("text/html");
        PrintWriter out = res.getWriter();

        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            try (Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/college", "root", "nikita")) {

                if ("register".equals(action)) {
                    
                    try (PreparedStatement checkUser = conn.prepareStatement("SELECT * FROM users WHERE username=?")) {
                        checkUser.setString(1, username);
                        ResultSet rs = checkUser.executeQuery();

                        if (rs.next()) {
                            
                            out.println("<script>alert('❌ Username already exists!'); window.location='index.html';</script>");
                        } else {
                            // Register new user
                            try (PreparedStatement ps = conn.prepareStatement("INSERT INTO users(username, password) VALUES (?, ?)")) {
                                ps.setString(1, username);
                                ps.setString(2, password);
                                ps.executeUpdate();
                                out.println("<script>alert('✅ Registered Successfully!'); window.location='Logins.html';</script>");
                            }
                        }
                    }

                } else if ("login".equals(action)) {
                    try (PreparedStatement ps = conn.prepareStatement("SELECT * FROM users WHERE username=? AND password=?")) {
                        ps.setString(1, username);
                        ps.setString(2, password);
                        ResultSet rs = ps.executeQuery();

                        if (rs.next()) {
                            // Login successful → go to welcome page
                            out.println("<script>alert('✅ Login Successful!');</script>");
                        } else {
                            // Invalid login → back to login
                            out.println("<script>alert('❌ Invalid Credentials!'); window.location='index.html';</script>");
                        }
                    }
                }

            }
        } catch (Exception e) {
            out.println("<script>alert('❌ Error: " + e.getMessage() + "'); window.location='index.html';</script>");
            e.printStackTrace();
        }
    }
}
