package br.com.tcc.DAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import br.com.tcc.connection.Conexao;
import br.com.tcc.model.Restaurante;
import br.com.tcc.utils.Constantes;

/**
 * @author Wesley
 *
 */
public class RestauranteDAO extends Conexao {


    public int inserir(Restaurante restaurante) {

     Connection conn = null;
     conn = getConnection();
     int sucesso = 0;

         PreparedStatement stmt = null;
         try {
             stmt = conn.prepareStatement("INSERT INTO RESTAURANTE (CIDADE, DESCRI��O, ENDERE�O, ESPECIALIDADEGRASTRONICA, HORARIO, LATITUDE, LONGITUDE, NOME) VALUES(?,?,?,?,?,?,?,?)");

             stmt.setString(1, restaurante.getCidade());
             stmt.setString(2, restaurante.getDescricao());
             stmt.setString(3, restaurante.getEndereco());
             stmt.setString(4, restaurante.getEspecialidadeGastronomica());
             stmt.setString(5, restaurante.getHorario());
             stmt.setString(6, restaurante.getLatitude());
             stmt.setString(7, restaurante.getLongitude());
             stmt.setString(8, restaurante.getNome());
             sucesso = stmt.executeUpdate();

             if (sucesso > 0) {
                 System.out.println("RESTAURANTE INSERIDO!");
             }

         } catch (SQLException e) {
             e.printStackTrace();
             System.out.println("ERRO AO INSERIR RESTAURANTE!");
         } finally {
             closeConnection(conn, stmt);
         } 
     return sucesso;
    }

    /**
     * @param restaurante
     * @return
     */
    public int alterar(Restaurante restaurante) {

     Connection conn = null;
     conn = getConnection();
     PreparedStatement stmt = null;
     int sucesso = 0;
     try {
         stmt = conn
                 .prepareStatement("UPDATE RESTAURANTE SET CIDADE = ?, SET DESCRI��O = ?, SET ENDERE�O = ?," +
                 		"ESPECIALIDADEGASTRONOMICA = ?, HORARIO = ?, latitude = ?, longitude = ?, nome = ? WHERE ID = ?");

         stmt.setString(1, restaurante.getCidade());
         stmt.setString(2, restaurante.getDescricao());
         stmt.setString(3, restaurante.getEndereco());
         stmt.setString(4, restaurante.getEspecialidadeGastronomica());
         stmt.setString(5, restaurante.getHorario());
         stmt.setString(6, restaurante.getLatitude());
         stmt.setString(7, restaurante.getLongitude());
         stmt.setString(8, restaurante.getNome());
         sucesso = stmt.executeUpdate();
         
         if (sucesso > 0) {
             System.out.println("RESTAURANTE ALTERADO!");
         } else {
             System.out.println(Constantes.RESTAURANTE_NAO_EXISTE);
         }
     } catch (SQLException e) {
         e.printStackTrace();
         System.out.println("ERRO AO ALTERAR RESTAURANTE!");
     } finally {
         closeConnection(conn, stmt);
     }
     return sucesso;
     
    }

    /**
     * Objetivo: deletar restaurante
     * @param int
     * @return int excluidos
     */
    public int deletar(int id) {

     Connection conn = null;
     conn = getConnection();
     int excluidos = 0;
     PreparedStatement stmt = null;
     try {
         stmt = conn.prepareStatement("DELETE FROM RESTAURANTE WHERE IDRESTAURANTE = ?");
         stmt.setInt(1, id);
         excluidos = stmt.executeUpdate();
         
         if (excluidos > 0) {
             System.out.println("RESTAURANTE REMOVIDO!");
         } else {
             System.out.println("RESTAURANTE N�O EXISTE!");
         }

     } catch (SQLException e) {
         e.printStackTrace();
         System.out.println("ERRO AO DELETAR RESTAURANTE!");
     } finally {
         closeConnection(conn, stmt);
     }
     return excluidos;
    }

    /**
     * Objetivo: buscar um unico restaurante no banco
     * @param  int id
     * @return Restaurante
     */
    public Restaurante buscar(int id) {

		 Connection conn = null;
		 ResultSet resultSet = null;
		 PreparedStatement stmt = null;
		 conn = getConnection();
		 Restaurante restaurante = null;
		 try {
		     stmt = conn.prepareStatement("SELECT * FROM RESTAURANTE WHERE IDRESTAURANTE = ?");
			 stmt.setInt(1, id);
			 resultSet = stmt.executeQuery();
			 
			 while (resultSet.next()) {
			     restaurante = new Restaurante();
			     restaurante.setIdRestaurante(resultSet.getInt("idrestaurante"));
				 restaurante.setCidade(resultSet.getString("cidade"));
				 restaurante.setDescricao(resultSet.getString("descri��o"));
				 restaurante.setEndereco(resultSet.getString("endere�o"));
				 restaurante.setEspecialidadeGastronomica(resultSet.getString("especialidadegrastronica"));
				 restaurante.setHorario(resultSet.getString("horario"));
				 restaurante.setLatitude(resultSet.getString("latitude"));
				 restaurante.setLongitude(resultSet.getString("longitude"));
				 restaurante.setNome(resultSet.getString("nome"));
			 }
			 
		 } catch (SQLException e) {
		     e.printStackTrace();
		 } finally {
		     closeConnection(conn, stmt, resultSet);
		 }
		 return restaurante;
    }

    
    /**
     * Objetivo Buscar todos os restaurantes cadastrados no banco
     * @return ArrayList< Restaurante >
     */
    public ArrayList<Restaurante> buscarTodos() {

     Connection conn = null;
     ResultSet resultSet = null;
     PreparedStatement stmt = null;
     conn = getConnection();
     ArrayList<Restaurante> listaRestaurantes = null;

     try {

         stmt = conn.prepareStatement("SELECT * FROM RESTAURANTE");
         resultSet = stmt.executeQuery();
         listaRestaurantes = new ArrayList<Restaurante>();
         Restaurante restaurante = null;
         
         while (resultSet.next()) {
        	 restaurante = new Restaurante();
             restaurante.setIdRestaurante(resultSet.getInt("idrestaurante"));
             restaurante.setCidade(resultSet.getString("cidade"));
             restaurante.setDescricao(resultSet.getString("descri��o"));
             restaurante.setEndereco(resultSet.getString("endere�o"));
             restaurante.setEspecialidadeGastronomica(resultSet.getString("especialidadegrastronica"));
             restaurante.setHorario(resultSet.getString("horario"));
             restaurante.setLatitude(resultSet.getString("latitude"));
             restaurante.setLongitude(resultSet.getString("longitude"));
             restaurante.setNome(resultSet.getString("nome"));
             listaRestaurantes.add(restaurante);
         }

     } catch (SQLException e) {
         e.printStackTrace();
         listaRestaurantes = null;
     } finally {
         closeConnection(conn, stmt, resultSet);
     }
     return listaRestaurantes;
    }
}
 
