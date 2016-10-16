package com.test;

import java.net.URI;
import java.util.ArrayList;

import javax.ws.rs.core.MediaType;

import com.sun.jersey.api.client.Client;
import com.sun.jersey.api.client.ClientResponse;
import com.sun.jersey.api.client.WebResource;
import org.json.JSONException;
import org.json.JSONObject;




public class SuperHeroClient {
	
	
	Client client;
	WebResource webResource2;
	ClientResponse response;
	
	public SuperHeroClient(){
		client = new Client();
	}
	
	
	public void processSuperHero() throws Exception{
		//local
		//webResource2 = client.resource("http://localhost:8080/PayworkExam/rest/superhero");
		//remoto
		webResource2 = client.resource("http://payworks.wpz4fggi2c.eu-central-1.elasticbeanstalk.com/rest/superhero");
		response = webResource2.accept("application/json").get(ClientResponse.class);
		if (response.getStatus() != 200) {
			throw new RuntimeException("Failed : HTTP error code : " + response.getStatus());
		}

		String output2 = response.getEntity(String.class);
		System.out.println("\n============superhero============");
		System.out.println(output2);
		response.close();		
	}
	
	
	public void processSuperHeroByIndex(int index) throws Exception{
		//local
		//webResource2 = client.resource("http://localhost:8080/PayworkExam/rest/superhero/"+String.valueOf(index));
		//remoto
		webResource2 = client.resource("http://payworks.wpz4fggi2c.eu-central-1.elasticbeanstalk.com/rest/superhero/"+String.valueOf(index));
		response = webResource2.accept("application/json").get(ClientResponse.class);
		if (response.getStatus() != 200) {
			throw new RuntimeException("Failed : HTTP error code : " + response.getStatus());
		}

		String output2 = response.getEntity(String.class);
		System.out.println("\n============superherobyindex["+index+"]============");
		System.out.println(output2);
		
		response.close();
	}
	
	
	public void getSuperHeroByName(String name) throws Exception{
		//local
		//webResource2 = client.resource("http://localhost:8080/PayworkExam/rest/superhero/query/"+name);
		//remoto
		webResource2 = client.resource("http://payworks.wpz4fggi2c.eu-central-1.elasticbeanstalk.com/rest/superhero/query/"+name);
		response = webResource2.accept("application/json").get(ClientResponse.class);
		if (response.getStatus() != 200) {
			throw new RuntimeException("Failed : HTTP error code : " + response.getStatus());
		}

		String output2 = response.getEntity(String.class);
		System.out.println("\n============superherobyname["+name+"]============");
		System.out.println(output2);
		
		response.close();
	}
	
	
	public void getSuperHeroList() throws Exception{
		//local
		//webResource2 = client.resource("http://localhost:8080/PayworkExam/rest/superhero/list");
		//remoto
		webResource2 = client.resource("http://payworks.wpz4fggi2c.eu-central-1.elasticbeanstalk.com/rest/superhero/list");
		response = webResource2.accept("application/json").get(ClientResponse.class);
		if (response.getStatus() != 200) {
			throw new RuntimeException("Failed : HTTP error code : " + response.getStatus());
		}

		String output2 = response.getEntity(String.class);
		System.out.println("\n============superherolist============");
		System.out.println(output2);
		
		response.close();
	}
	
	
	public void insertSuperHeroJSON() throws Exception{
		
		SuperHeroBean user = new SuperHeroBean();
		user.setName("Batman");
		user.setPseudo("I'm the night,I'm the darkness");
		user.setPublisher("DC");
		user.setDateApp("1939-01-01");
		ArrayList<String> skillsData = new ArrayList();
		skillsData.add("Bat-Gatchets");
		user.setSkills(skillsData.toString());
		ArrayList<String> alliesData = new ArrayList();
		alliesData.add("Robin");
		user.setAllies(alliesData.toString());
		
		//local
		//webResource2  = client.resource("http://localhost:8080/PayworkExam/rest/superhero/insert");
		//remoto
		webResource2  = client.resource("http://payworks.wpz4fggi2c.eu-central-1.elasticbeanstalk.com/rest/superhero/insert");
		
		ClientResponse response = webResource2.accept("application/json").post(ClientResponse.class, user);
		if (response.getStatus() != 200){
			throw new RuntimeException("Failed : HTTP error code : " + response.getStatus());
		}

		String output2 = response.getEntity(String.class);
		System.out.println("\n============insertSuperHero(JSON)============");
		System.out.println("\ninserting SuperHero (" + user.toString() + ")");
		System.out.println(output2);
		
		response.close();
		
	}
	
	
	public static void main (String [ ] args) throws Exception {
		SuperHeroClient sHC = new SuperHeroClient();
		sHC.processSuperHero();
		sHC.processSuperHeroByIndex(1);
		sHC.insertSuperHeroJSON();
		sHC.getSuperHeroByName("Superman");
		sHC.getSuperHeroList();
	}
	

}
