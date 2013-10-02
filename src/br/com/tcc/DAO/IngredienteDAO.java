
package br.com.tcc.DAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import br.com.tcc.connection.Conexao;
import br.com.tcc.model.Ingrediente;

public class IngredienteDAO extends Conexao {
	
	public int inserir(Ingrediente ingrediente) {

	     Connection conn = null;
	     conn = getConnection();
	     int sucesso = 0;

	         PreparedStatement stmt = null;
	         try {
	             stmt = conn.prepareStatement("INSERT INTO "
	                     + "INGREDIENTE (NOME) VALUES(?)");

	             stmt.setString(1, ingrediente.getNome());
	             sucesso = stmt.executeUpdate();

	             if (sucesso > 0) {
	                 System.out.println("INGREDIENTE INSERIDO!");
	             }

	         } catch (SQLException e) {
	             e.printStackTrace();
	             System.out.println("ERRO AO INSERIR INGREDIENTE!");
	         } finally {
	             closeConnection(conn, stmt);
	         } 
	     return sucesso;
	    }

	    public int alterar(Ingrediente ingrediente) {

	     Connection conn = null;
	     conn = getConnection();
	     PreparedStatement stmt = null;
	     int sucesso = 0;
	     try {
	         stmt = conn
	                 .prepareStatement("UPDATE PRATO SET NOME = ?, WHERE ID = ?");

	         stmt.setString(1, ingrediente.getNome());
	         sucesso = stmt.executeUpdate();
	         
	         if (sucesso > 0) {
	             System.out.println("INGREDIENTE ALTERADO!");
	         } else {
	             System.out.println("INGREDIENTE EXISTE!");
	         }
	     } catch (SQLException e) {
	         e.printStackTrace();
	         System.out.println("ERRO AO ALTERRAR INGREDIENTE!");
	     } finally {
	         closeConnection(conn, stmt);
	     }
	     return sucesso;
	    }

	    public int deletar(int id) {

	     Connection conn = null;
	     conn = getConnection();
	     int excluidos = 0;
	     PreparedStatement stmt = null;
	     try {
	         stmt = conn.prepareStatement("DELETE FROM INGREDIENTE WHERE IDRESTAURANTE = ?");
	         stmt.setInt(1, id);
	         excluidos = stmt.executeUpdate();
	         
	         if (excluidos > 0) {
	             System.out.println("INGREDIENTE REMOVIDO!");
	         } else {
	             System.out.println("INGREDIENTE NÃO EXISTE!");
	         }

	     } catch (SQLException e) {
	         e.printStackTrace();
	         System.out.println("ERRO AO DELETAR INGREDIENTE!");
	     } finally {
	         closeConnection(conn, stmt);
	     }
	     return excluidos;
	    }

	    public Ingrediente buscar(int id) {

	     Connection conn = null;
	     ResultSet resultSet = null;
	     PreparedStatement stmt = null;
	     conn = getConnection();
	     Ingrediente ingrediente = null;
	     try {
	         stmt = conn.prepareStatement("SELECT * FROM INGREDIENTE WHERE IDINGREDIENTES = ?");
	         stmt.setInt(1, id);
	         resultSet = stmt.executeQuery();
	         while (resultSet.next()) {
	             ingrediente = new Ingrediente();
	             ingrediente.setIdIngredientes(resultSet.getInt("idIngredientes"));
	             ingrediente.setNome(resultSet.getString("nome"));             
	         }
	     } catch (SQLException e) {
	         e.printStackTrace();
	     } finally {
	         closeConnection(conn, stmt, resultSet);
	     }
	     return ingrediente;
	    }

	    public ArrayList<Ingrediente> buscarTodos() {

	     Connection conn = null;
	     ResultSet resultSet = null;
	     PreparedStatement stmt = null;
	     conn = getConnection();
	     ArrayList<Ingrediente> listaIngredientes = null;

	     try {

	         stmt = conn.prepareStatement("SELECT * FROM IDINGREDIENTE ORDER BY ID");
	         resultSet = stmt.executeQuery();
	         listaIngredientes = new ArrayList<Ingrediente>();
	         Ingrediente ingrediente = null;
	         
	         while (resultSet.next()) {
	        	 ingrediente = new Ingrediente();
	             ingrediente.setNome(resultSet.getString("nome")); 
	             listaIngredientes.add(ingrediente);
	         }

	     } catch (SQLException e) {
	         e.printStackTrace();
	         listaIngredientes = null;
	     } finally {
	         closeConnection(conn, stmt, resultSet);
	     }
	     return listaIngredientes;
	    }
	}
	 

	


