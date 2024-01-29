package servlet;

import jakarta.servlet.*;
import jakarta.servlet.http.*;
import jakarta.servlet.annotation.*;
import model.Client;
import utils.DatabaseConnectionManager;
import utils.PasswordHashing;

import java.io.IOException;
import java.sql.*;

@WebServlet("/client-signup")
public class ClientSignup extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        try {
            // Retrieve client details from request parameters
            String name = request.getParameter("name");
            String password = request.getParameter("password");
            String username = request.getParameter("username");
            String address = request.getParameter("address");
            int bankNum = Integer.parseInt(request.getParameter("bankNum"));

            // Create the client
            Client newClient = createClient(name, password, username, address, bankNum);

            // Forward the created client to a JSP page for display or redirect to a success page
            if (newClient != null) {
                HttpSession session = request.getSession();
                session.setAttribute("user", newClient);
                response.sendRedirect("client-item");
            } else {
                // Handle the case where client creation fails
                response.sendRedirect("client-signup.jsp?error=1");
            }
        } catch (NumberFormatException e) {
            // Handle the case where bankNum parameter is not a valid integer
            response.sendRedirect("client-signup.jsp?error=1");
        } catch (SQLException e) {
            response.sendRedirect("client-signup.jsp?error=1");
        }
    }

    private Client createClient(String name, String password, String username, String address, int bankNum)
            throws SQLException {
        try (Connection connection = DatabaseConnectionManager.getConnection()) {
            // Prepare SQL statement to insert a new client
            String sql = "INSERT INTO clients (name, password, username, address, bank_num) VALUES (?, ?, ?, ?, ?)";
            String hashedPassword = PasswordHashing.hashPassword(password);
            try (PreparedStatement statement = connection.prepareStatement(sql, PreparedStatement.RETURN_GENERATED_KEYS)) {
                statement.setString(1, name);
                statement.setString(2, hashedPassword);
                statement.setString(3, username);
                statement.setString(4, address);
                statement.setInt(5, bankNum);

                // Execute the update
                int affectedRows = statement.executeUpdate();

                if (affectedRows == 1) {
                    // Retrieve the generated client ID
                    ResultSet generatedKeys = statement.getGeneratedKeys();

                    if (generatedKeys.next()) {
                        int clientId = generatedKeys.getInt(1);

                        // Create and return the Client object
                        return new Client(clientId, name, password, username, address, bankNum);
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