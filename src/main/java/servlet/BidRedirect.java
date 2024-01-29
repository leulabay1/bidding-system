package servlet;

import jakarta.servlet.*;
import jakarta.servlet.http.*;
import jakarta.servlet.annotation.*;
import model.Item;
import utils.DatabaseConnectionManager;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

@WebServlet("/bid-redirect")
public class BidRedirect extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        try {
            int itemId = Integer.parseInt(request.getParameter("itemId"));
            Item item = getItemById(itemId);

            request.setAttribute("item", item);
            request.getRequestDispatcher("create-bid.jsp").forward(request, response);

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }


    }

    private Item getItemById(int itemId) throws SQLException {
        try (Connection connection = DatabaseConnectionManager.getConnection()) {
            // Prepare SQL statement to retrieve an item by ID
            String sql = "SELECT * FROM items WHERE id = ?";
            try (PreparedStatement statement = connection.prepareStatement(sql)) {
                statement.setInt(1, itemId);

                // Execute the query
                try (ResultSet resultSet = statement.executeQuery()) {
                    if (resultSet.next()) {
                        // Populate Item object from the result set
                        Item item = new Item();
                        item.setId(resultSet.getInt("id"));
                        item.setAuthorId(resultSet.getInt("authorId"));
                        item.setEndDate(resultSet.getDate("end_date"));
                        item.setDescription(resultSet.getString("description"));
                        item.setTitle(resultSet.getString("title"));
                        item.setClosed(resultSet.getBoolean("closed"));
                        item.setMaxAmount(resultSet.getFloat("max_amount"));
                        item.setTotalNo(resultSet.getInt("total_no"));
                        item.setImageUrl(resultSet.getString("image_url"));

                        return item;
                    }
                }
            }
        }

        // Return null if item with the specified ID is not found
        return null;
    }

}