package com.test;

import java.util.ArrayList;
import java.util.List;

import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Response;
import javax.xml.bind.JAXBElement;

import org.json.JSONException;
import org.json.JSONObject;


@Path("/superhero")
public class Superhero {
	
	private static List<SuperHeroBean> superHeroArray = new ArrayList<SuperHeroBean>();
	
	@GET
	@Produces("application/json")
	public Response getSuperHero() throws JSONException{
		JSONObject json = new JSONObject();
		
		json.put("superhero-name", "trufaman");
		json.put("superhero-data", "detect trufas");
		
		String result = "@Produces(\"application/json\") Output: superherofound " + json;
		return Response.status(200).entity(result).build();
	}
	
	
	@GET
	@Path("/{index}")
	@Produces("application/json")
	public Response getSuperHeroByIndex(@PathParam("index") int index) throws JSONException{
		
		JSONObject json = new JSONObject();
		
		json.put("superhero-name", "trufaman");
		json.put("superhero-data", "detect trufas");
		
		String result = "@Produces(\"application/json\") Output: superherofound by index [" + index + "]" + json;
		return Response.status(200).entity(result).build();

		
	}
	
	@GET
	@Path("/list")
	@Produces("application/json")
	public Response getListOfSuperHeros(){
		
		String result = "@Produces(\"application/json\") Output: superheroList " + superHeroArray.toString();
		return Response.status(200).entity(result).build();
	}
	
	@GET
	@Path("/query/{name}")
	@Produces("application/json")
	public Response getSuperHeroData(@PathParam("name") String name){
		
		SuperHeroBean res = new SuperHeroBean();
		res.setName(name);
		
		int index = superHeroArray.indexOf(res);
		if (index != -1){
			res = superHeroArray.get(index);
		}
		
		String result = "@Produces(\"application/json\") Output: superheroList " + res.toString();
		return Response.status(200).entity(result).build();
		
	}
	
	@POST
	@Path("/insert")
	@Consumes({"application/json","application/xml"})
	@Produces("application/json")
	public Response setSuperHeroData(SuperHeroBean sHB){
		String res = "";
		System.out.println("Received SuperHeroBean: for insert" + sHB.toString());
		try{	
			int index = superHeroArray.indexOf(sHB);
			if (index != -1){
				SuperHeroBean sHBMain = superHeroArray.get(index);
				sHBMain.setSuperHeroBean(sHB);
				superHeroArray.set(index, sHBMain);
				res = "Superhero (" + sHB.getName() + ") Modified";
				System.out.println(res);
			}else{
				superHeroArray.add(sHB);
				res = "Superhero (" + sHB.getName() + ") Added";
				System.out.println(res);
			}
		}catch(Exception e){
			System.out.println(e.getMessage());
			res = "Cannot Insert (" + sHB.getName() + ")";
			System.out.println(res);
		}
		
		String result = "@Produces(\"application/json\") Output: result " + res;
		return Response.status(200).entity(result).build();
	}
	
	

}
