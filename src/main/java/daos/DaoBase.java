package daos;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DaoBase {

    protected String user = "root";
    protected String pass = "12345678";
    protected String url = "jdbc:mysql://localhost:3306/Lab9_Biblioteca";

    public Connection getConnection() throws SQLException{
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
        } catch (ClassNotFoundException e){
            throw new SQLException(e);

        }
        return DriverManager.getConnection(url, user, pass);

    }
}
