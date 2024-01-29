package servlet;

import model.User;
import jakarta.servlet.*;
import jakarta.servlet.http.*;
import jakarta.servlet.annotation.*;
import utils.DatabaseConnectionManager;

import java.io.IOException;
import java.sql.*;

@WebServlet("/user-edit-profile")
public class UserEditProfile extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession();

        // Assuming you have a User object stored in the session
        User user = (User) session.getAttribute("user");

        // Retrieve new profile information from the request parameters
        String newName = request.getParameter("name");
        String newPassword = request.getParameter("password");
        String newUsername = request.getParameter("username");
        String newAddress = request.getParameter("address");

        // Update the user's profile
        updateUserProfile(user.getId(), newName, newPassword, newUsername, newAddress);

        // Optionally, update the user object in the session with the new information
        user.setName(newName);
        user.setPassword(newPassword);
        user.setUsername(newUsername);
        user.setAddress(newAddress);
        session.setAttribute("user", user);

        // Redirect to a profile page or another appropriate page
        response.sendRedirect("items");
    }

    private void updateUserProfile(int userId, String newName, String newPassword, String newUsername,
                                   String newAddress) {
        // Perform database update
        try (Connection connection = DatabaseConnectionManager.getConnection()) {
            String sql = "UPDATE users SET name = ?, password = ?, username = ?, address = ? WHERE id = ?";
            try (PreparedStatement statement = connection.prepareStatement(sql)) {
                statement.setString(1, newName);
                statement.setString(2, newPassword);
                statement.setString(3, newUsername);
                statement.setString(4, newAddress);
                statement.setInt(5, userId);

                // Execute the update query
                statement.executeUpdate();
            }
        } catch (SQLException e) {
            e.printStackTrace(); // Handle the exception appropriately
        }
    }
}