package dao;

import java.sql.*;

public class Conecta {
    public static Connection getConexao() throws Exception {
        Connection con = null;

        String serverName = "localhost";
        String myDataBase = "alunos";

        String userName = "root";
        String password = "200467lol";

        String driverName = "com.mysql.cj.jdbc.Driver";
        Class.forName(driverName);

        String url = "jdbc:mysql://" + serverName + "/" + myDataBase;
        con = DriverManager.getConnection(url, userName, password);

        return con;
    }
}
