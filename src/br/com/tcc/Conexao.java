package br.com.tcc;

import java.sql.*;

public class Conexao {
	
	private static final String DATABASE_URL = "jdbc:mysql://localhost/mydb";
    private static final String USERNAME = "wesleygado";
    private static final String PASSWORD = "199130";

    public Connection getConnection() {
     Connection con = null;
     try {
         con = DriverManager.getConnection(DATABASE_URL, USERNAME,
                 PASSWORD);
     } catch (Exception e) {
         System.out.println("Erro ao criar conexao.");
         e.printStackTrace();
     }
     return con;
    }

    public void closeConnection(Connection conn, Statement stmt, ResultSet rs) {
     try {
         close(conn, stmt, rs);
     } catch (Exception e) {
         System.out.println("Erro ao fechar conexao.");
         e.printStackTrace();
     }
    }

    public void closeConnection(Connection conn, Statement stmt) {
     try {
         close(conn, stmt, null);
     } catch (Exception e) {
         System.out.println("Erro ao fechar conexao.");
         e.printStackTrace();
     }
    }

    public void closeConnection(Connection conn) {
     try {
         close(conn, null, null);
     } catch (Exception e) {
         System.out.println("Erro ao fechar conexao.");
         e.printStackTrace();
     }
    }

    private void close(Connection conn, Statement stmt, ResultSet rs) {

     try {
         if (rs != null) {
             rs.close();
         }
         if (stmt != null) {
             stmt.close();
         }
         if (conn != null) {
             conn.close();
         }
     } catch (Exception e) {
         System.out.println("Erro ao fechar conexao.");
         e.printStackTrace();
     }
    }
}
 