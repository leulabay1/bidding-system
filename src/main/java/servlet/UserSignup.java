package servlet;

import jakarta.servlet.*;
import jakarta.servlet.http.*;
import jakarta.servlet.annotation.*;
import model.User;
import utils.DatabaseConnectionManager;
import utils.PasswordHashing;

import java.io.IOException;
import java.sql.*;

@WebServlet("/user-signup")
public class UserSignup extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        try {
            // Retrieve user details from request parameters
            String name = request.getParameter("name");
            String password = request.getParameter("password");
            String username = request.getParameter("username");
            String address = request.getParameter("address");

            // Create the user
            User newUser = createUser(name, password, username, address);

            // Forward the created user to a JSP page for display or redirect to a success page
            if (newUser != null) {
                HttpSession session = request.getSession();
                session.setAttribute("user", newUser);
                response.sendRedirect("items");
            } else {
                // Handle the case where user creation fails
                response.sendRedirect("user-signup.jsp?error=1");
                response.setStatus(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
            }
        } catch (SQLException e) {
            e.printStackTrace(); // Handle the exception appropriately in your application
            response.setStatus(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
        }

    }

    private User createUser(String name, String password, String username, String address) throws SQLException {
        try (Connection connection = DatabaseConnectionManager.getConnection()) {
            // Prepare SQL statement to insert a new user
            String sql = "INSERT INTO users (name, password, username, address) VALUES (?, ?, ?, ?)";
            String hashedPassword = PasswordHashing.hashPassword(password);
            try (PreparedStatement statement = connection.prepareStatement(sql, PreparedStatement.RETURN_GENERATED_KEYS)) {
                statement.setString(1, name);
                statement.setString(2, hashedPassword);
                statement.setString(3, username);
                statement.setString(4, address);

                // Execute the update

                int affectedRows = statement.executeUpdate();

                if (affectedRows == 1) {
                    // Retrieve the generated client ID
                    ResultSet generatedKeys = statement.getGeneratedKeys();

                    if (generatedKeys.next()) {
                        int clientId = generatedKeys.getInt(1);

                        // Create and return the Client object
                        return new User(clientId, name, password, username, address);
                    } else {
                        // Handle the case where no keys were generated
                        throw new SQLException("No generated keys available");
                    }
                } else {
                    // Handle the case where the insert did not affect any rows
                    throw new SQLException("Failed to insert client or no rows affected");
                }
            }
        }

    }
}