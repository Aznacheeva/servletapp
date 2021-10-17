package dbService;

import acccounts.UserProfile;
import dbService.dao.UsersDao;

import java.sql.Connection;
import java.sql.Driver;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DBService {
    private static Connection connection = null;

    static {
        createConnection();
        UsersDao dao = new UsersDao(connection);
        try {
            dao.createTable();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }

    private static void createConnection() {
        if (connection != null) return;
        try {
            DriverManager.registerDriver((Driver)Class.forName("com.mysql.jdbc.Driver").newInstance());

            StringBuilder url = new StringBuilder();

            url.
                    append("jdbc:mysql://").        //db type
                    append("localhost:").           //host name
                    append("3306/").                //port
                    append("java_servlets?").            //db name
                    append("user=root&").          //login
                    append("password=oracleshot");       //password
            connection = DriverManager.getConnection(url.toString());
        } catch (SQLException | InstantiationException | IllegalAccessException | ClassNotFoundException e) {
            e.printStackTrace();
        }
    }


    public static UserProfile getUser(String login){
        try {
            return (new UsersDao(connection).get(login));
        } catch (SQLException e) {
            return null;
        }
    }

    public static void addUser(UserProfile user){
        try {
            connection.setAutoCommit(false);
            UsersDao dao = new UsersDao(connection);
            dao.insertUser(user);
            connection.commit();
        } catch (SQLException e) {
            try {
                connection.rollback();
            } catch (SQLException ignore) {}
        } finally {
            try {
                connection.setAutoCommit(true);
            } catch (SQLException ignore) {
            }
        }
    }
}
