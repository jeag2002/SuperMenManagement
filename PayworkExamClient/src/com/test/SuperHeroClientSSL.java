package com.test;

import com.sun.jersey.api.client.Client;
import com.sun.jersey.api.client.WebResource;
import com.sun.jersey.oauth.client.OAuthClientFilter;
import com.sun.jersey.oauth.signature.OAuthParameters;
import com.sun.jersey.oauth.signature.OAuthSecrets;


//http://www.programcreek.com/java-api-examples/index.php?api=com.sun.jersey.oauth.signature.OAuthParameters

public class SuperHeroClientSSL {
	
	private JerseySSLClient client;
	
	public SuperHeroClientSSL(){
		   client = new JerseySSLClient();
		   //user:admin; pass:admin
		   client.setUsernamePassword("admin", "admin");
	}
	
	public void processSuperHeroSSL() throws Exception{
		 String result = client.get_JSON(String.class);
		 System.out.println("\n============Client ProcessSuperHeroSSL (+Basic Auth)============");
		 System.out.println(result);
	     client.close();
	}
	
	
	public void processSuperHeroOAUTH() throws Exception{
		
    	OAuthParameters params = new OAuthParameters().consumerKey("hoge").signatureMethod("HMAC-SHA1").timestamp().nonce().version("1.1").token("sho1get");
        OAuthSecrets secrets = new OAuthSecrets().consumerSecret("testtest").tokenSecret("testtest");

        Client client = Client.create();
        WebResource resource = client.resource("http://localhost:8080/PayworkExamOAuth/rest/superheroOAUTH");

        OAuthClientFilter filter = new OAuthClientFilter(client.getProviders(), params, secrets);
        resource.addFilter(filter);
        
        System.out.println("==== Client ProcessSuperHero (+OAuth) =====");
        String data = resource.get(String.class);
        System.out.println(data);
		
		
	}
	
	public static void main (String [ ] args) throws Exception {
		
		 SuperHeroClientSSL sHCSSL = new SuperHeroClientSSL();
		 sHCSSL.processSuperHeroSSL();	
		 sHCSSL.processSuperHeroOAUTH();
	}
	
	

}
