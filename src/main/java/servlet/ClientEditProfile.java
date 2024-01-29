package servlet;

import jakarta.servlet.*;
import jakarta.servlet.http.*;
import jakarta.servlet.annotation.*;
import model.Client;
import utils.DatabaseConnectionManager;

import java.io.IOException;
import java.sql.*;

@WebServlet("/client-edit-profile")
public class ClientEditProfile extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession();

        // Assuming you have a Client object stored in the session
        Client client = (Client) session.getAttribute("user");

        // Retrieve new profile information from the request parameters
        String newName = request.getParameter("name");
        String newPassword = request.getParameter("password");
        String newUsername = request.getParameter("username");
        String newAddress = request.getParameter("address");
        int newBankNum = Integer.parseInt(request.getParameter("bankNum"));

        // Update the client's profile
        updateClientProfile(client.getId(), newName, newPassword, newUsername, newAddress, newBankNum);

        // Optionally, update the client object in the session with the new information
        client.setName(newName);
        client.setPassword(newPassword);
        client.setUsername(newUsername);
        client.setAddress(newAddress);
        client.setBankNum(newBankNum);
        session.setAttribute("user", client);

        // Redirect to a profile page or another appropriate page
        response.sendRedirect("client-item");
    }


    private void updateClientProfile(int clientId, String newName, String newPassword, String newUsername,
                                     String newAddress, int newBankNum) {
        // Perform database update
        try (Connection connection = DatabaseConnectionManager.getConnection()) {
            String sql = "UPDATE clients SET name = ?, password = ?, username = ?, address = ?, bank_num = ? WHERE id = ?";
            try (PreparedStatement statement = connection.prepareStatement(sql)) {
                statement.setString(1, newName);
                statement.setString(2, newPassword);
                statement.setString(3, newUsername);
                statement.setString(4, newAddress);
                statement.setInt(5, newBankNum);
                statement.setInt(6, clientId);

                // Execute the update query
                statement.executeUpdate();
            }
        } catch (SQLException e) {
            e.printStackTrace(); // Handle the exception appropriately
        }
    }
}