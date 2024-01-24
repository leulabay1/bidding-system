package project;

import jakarta.servlet.*;
import jakarta.servlet.http.*;
import jakarta.servlet.annotation.*;
import java.io.IOException;
import java.sql.*;

@WebServlet("/login")
public class ClientLogin extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        try{
            Connection connection = DatabaseConnectionManager.getConnection();
        }catch(SQLException e){
            throw new RuntimeException(e);
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        try {

            String username = request.getParameter("username");
            String password = request.getParameter("password");

            System.out.println(username + password);

            Connection connection = DatabaseConnectionManager.getConnection();

            String sql = "SELECT * FROM admins WHERE username = ? AND password = ?";

            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setString(1, username);
            preparedStatement.setString(2, password);

            ResultSet resultSet = preparedStatement.executeQuery();

            if (resultSet.next()) {

                HttpSession session = request.getSession();
                session.setAttribute("username", username);

                System.out.println("hello");

                response.sendRedirect("welcome.jsp");
            } else {
                response.sendRedirect("unauthorized.jsp");
            }
        }
        catch (SQLException e) {
            throw new RuntimeException(e);
        }

    }
}