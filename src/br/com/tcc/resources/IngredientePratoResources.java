package br.com.tcc.resources;

import java.util.ArrayList;

import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;

import br.com.tcc.exception.NoContentException;
import br.com.tcc.model.IngredientePrato;
import br.com.tcc.model.IngredientePratoBusiness;
import br.com.tcc.model.PratoBusiness;
import br.com.tcc.utils.Constantes;

import com.google.gson.Gson;

@Path("/ingredientePrato")
public class IngredientePratoResources {
	
	
	/**
	 * Objetivo: apresentar o Json dos ingredientes do prato pelo seu ID
	 * @param  int id
	 * @return Prato prato
	 */
	@GET
    @Path("/{id}")
    @Produces("application/json")
    public ArrayList<IngredientePrato> buscar(@PathParam("id") int id){
		return new IngredientePratoBusiness().buscar(id);
     
    }
	

}
