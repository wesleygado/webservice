package br.com.tcc.DAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import br.com.tcc.connection.Conexao;
import br.com.tcc.model.IngredientePrato;

public class IngredientePratoDAO extends Conexao {
	
	public ArrayList<IngredientePrato> buscar(int id) {

	     Connection conn = null;
	     ResultSet resultSet = null;
	     PreparedStatement stmt = null;
	     conn = getConnection();
	     ArrayList<IngredientePrato> listaIngredientePrato = new ArrayList<IngredientePrato>();
	     
	     try {
	    	 
	         stmt = conn.prepareStatement("SELECT ingrediente.nome FROM ingrediente "
	       + "INNER JOIN ingredientes_prato ON ( ingrediente.idingredientes = ingredientes_prato.idingredientes ) "
	       + "INNER JOIN prato ON ( prato.idprato = ingredientes_prato.idprato ) "
	       + "WHERE ingredientes_prato.idprato =  ?");
	         stmt.setInt(1, id);
	         resultSet = stmt.executeQuery();
	         IngredientePrato ingredientePrato = null;
	         
	         while (resultSet.next()) {
	        	 
	             ingredientePrato = new IngredientePrato();
	             ingredientePrato.setNome(resultSet.getString("nome"));
	             listaIngredientePrato.add(ingredientePrato);
	             
	         }
	         
	     } catch (SQLException e) {
	    	 
	         e.printStackTrace();
	         
	     } finally {
	    	 
	         closeConnection(conn, stmt, resultSet);
	         
	     }
	     
	     return listaIngredientePrato;
	     
	}
	

}
