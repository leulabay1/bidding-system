package servlet;

import jakarta.servlet.*;
import jakarta.servlet.http.*;
import jakarta.servlet.annotation.*;
import model.Client;
import utils.DatabaseConnectionManager;
import utils.PasswordHashing;

import java.io.IOException;
import java.sql.*;

@WebServlet("/client-login")
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

            Connection connection = DatabaseConnectionManager.getConnection();

            String sql = "SELECT * FROM clients WHERE username = ?";

            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setString(1, username);

            ResultSet resultSet = preparedStatement.executeQuery();

            if (resultSet.next()) {
                HttpSession session = request.getSession();
                Client client = createClientFromResultSet(resultSet);

                if (!PasswordHashing.verifyPassword(password, client.getPassword())) {
                    response.sendRedirect("unauthorized.jsp");
                    return;
                }

                session.setAttribute("user", client);

                response.sendRedirect("client-item");
            } else {
                response.sendRedirect("client-login.jsp?error=1");
            }
        }
        catch (SQLException e) {
            throw new RuntimeException(e);
        }

    }

    public static Client createClientFromResultSet(ResultSet resultSet) throws SQLException {
        int id = resultSet.getInt("id"); // Assuming 'id' is an integer column
        String name = resultSet.getString("name");
        String password = resultSet.getString("password");
        String username = resultSet.getString("username");
        String address = resultSet.getString("address");
        int bankNum = resultSet.getInt("bank_num");

        Client client = new Client(id, name, password, username, address, bankNum);

        return client;
    }
}