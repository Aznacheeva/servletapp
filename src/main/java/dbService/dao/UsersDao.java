package dbService.dao;

import acccounts.UserProfile;
import dbService.executor.Executor;

import java.sql.Connection;
import java.sql.SQLException;

public class UsersDao {
    private Executor executor;

    public UsersDao(Connection connection) {
        executor = new Executor(connection);
    }

    public UserProfile get(String login) throws SQLException {
        return executor.execQuery("select * from java_servlets.profiles where login='" + login + "'", result -> {
            result.next();
            return new UserProfile(result.getString(1),
                    result.getString(2),
                    result.getString(3));
        });
    }

    public void insertUser(UserProfile user) throws SQLException {
        executor.execUpdate("insert into java_servlets.profiles (login, password, email)" +
                " values ('" + user.getLogin() + "', '" + user.getPassword() + "', '" + user.getEmail() + "')");
    }

    public void createTable() throws SQLException {
        executor.execUpdate("create table if not exists java_servlets.profiles (login varchar(15), password varchar(20), email varchar(40))");
    }

    public void dropTable() throws SQLException {
        executor.execUpdate("drop table java_servlets.profiles");
    }

    public String getLogin(String email) throws SQLException {
        return executor.execQuery("select * from java_servlets.profiles where email='" + email + "'", result -> {
            result.next();
            return result.getString(1);
        });
    }
}
