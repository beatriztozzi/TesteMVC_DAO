package DAO;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DataBaseConnection {

    private String con_banco;
    private String usuario_mysql;
    private String senha_mysql;
    private Connection con;
    private static DataBaseConnection instance;

    public DataBaseConnection() {
        this.con_banco = "jdbc:mysql://localhost:3306/dbUsuario?useSSL=false";
        this.usuario_mysql = "root";
        this.senha_mysql = "";
        instance = null;

        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            con = DriverManager.getConnection(con_banco, usuario_mysql, senha_mysql);
        } catch (ClassNotFoundException ex) {
            ex.printStackTrace();
        } catch (SQLException ex) {
            ex.printStackTrace();
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    public Connection getConnection() {
        return this.con;
    }
}
