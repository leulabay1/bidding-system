package servlet;

import jakarta.servlet.*;
import jakarta.servlet.http.*;
import jakarta.servlet.annotation.*;
import model.User;
import utils.DatabaseConnectionManager;
import utils.PasswordHashing;

import java.io.IOException;
import java.sql.*;

@WebServlet("/user-login")
public class UserLogin extends HttpServlet {

    @Override
    public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String username = request.getParameter("username");
        String password = request.getParameter("password");

    }

    @Override
    public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        try {

            String username = request.getParameter("username");
            String password = request.getParameter("password");

            System.out.println(username + password);

            Connection connection = DatabaseConnectionManager.getConnection();

            String sql = "SELECT * FROM users WHERE username = ?";

            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setString(1, username);

            ResultSet resultSet = preparedStatement.executeQuery();

            if (resultSet.next()) {

                User user = new User();

                String hashedPassowrd = resultSet.getString("password");

                if (!PasswordHashing.verifyPassword(password, hashedPassowrd)) {
                    System.out.println("Password is incorrect");
                    response.sendRedirect("user-login.jsp?error=1");
                    return;
                }

                user.setId(resultSet.getInt("id"));
                user.setName(resultSet.getString("name"));
                user.setPassword(resultSet.getString("password"));
                user.setUsername(resultSet.getString("username"));
                user.setAddress(resultSet.getString("address"));

                HttpSession session = request.getSession();
                session.setAttribute("user", user);

                response.sendRedirect("items");
            } else {
                response.sendRedirect("user-login.jsp?error=1");
            }
        }
        catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

}
