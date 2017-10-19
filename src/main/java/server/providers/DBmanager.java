package server.providers;

import server.config.Config;
import server.models.Student;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;


public class DBmanager {

    public Connection getConnection() {

        Connection connection = null;

        try {
            try {
                Class.forName("com.mysql.jdbc.Driver").newInstance();
            } catch (InstantiationException e) {
                e.printStackTrace();
            } catch (IllegalAccessException e) {
                e.printStackTrace();
            } catch (ClassNotFoundException e) {
                e.printStackTrace();
            }
            connection = DriverManager.getConnection(
                    "jdbc:mysql://"
                            + Config.getDbUrl() + ":"
                            + Config.getDbPort() + "/"
                            + Config.getDbName() + "?useSSL=false&serverTimezone=GMT",
                    Config.getDbUser(),
                    Config.getDbPassword());
        } catch (SQLException sqlException) {
            System.out.print(sqlException.getMessage());
            sqlException.printStackTrace();
        }
        return connection;
    }

    // lukke forbindelsen til databasen
    public  void close(){
        try {
            this.getConnection().close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

}


