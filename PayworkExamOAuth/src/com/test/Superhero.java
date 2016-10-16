package com.test;


import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Context; 
import javax.ws.rs.core.Response;
import org.json.JSONException;
import org.json.JSONObject;

import com.sun.jersey.api.core.HttpContext;
import com.sun.jersey.oauth.server.OAuthServerRequest;
import com.sun.jersey.oauth.signature.OAuthParameters;
import com.sun.jersey.oauth.signature.OAuthSecrets;
import com.sun.jersey.oauth.signature.OAuthSignature;
import com.sun.jersey.oauth.signature.OAuthSignatureException;


@Path("/superheroOAUTH")
public class Superhero {
	
	@GET
	@Produces("application/json")
	public Response getSuperHero(@Context HttpContext req) throws JSONException{
		 
		
		 OAuthServerRequest request = new OAuthServerRequest(req.getRequest());
		 OAuthParameters params = new OAuthParameters().readRequest(request);
		 OAuthSecrets secrets = new OAuthSecrets().consumerSecret("hoge").tokenSecret("testtest");
		 String timestamp = params.getTimestamp();
		 String result = "";
		 
		 /*Set dummy params for generating positive response of "verify" function */
		 System.out.println("OAuth SECRET ===================================");
		 System.out.println("CONSUMER_SECRET_: ("+secrets.getConsumerSecret()+")");
		 System.out.println("TOKEN_SECRET____: ("+secrets.getTokenSecret()+")");
		 System.out.println("OAuth SECRET ===================================");
		 
		 System.out.println("OAuth PARAMS ===================================");
		 System.out.println("CONSUMER_KEY____: ("+params.getConsumerKey()+")");
		 System.out.println("TOKEN___________: ("+params.getToken()+")");
		 System.out.println("SIGNATUREMETHOD_: ("+params.getSignatureMethod()+")");
		 System.out.println("TIMESTAMP_______: ("+params.getTimestamp()+")");
		 System.out.println("NONCE___________: ("+params.getNonce()+")");
		 System.out.println("VERSION_________: ("+params.getVersion()+")");
		 
		 System.out.println("OAuth PARAMS ===================================");
		 
		 secrets = new OAuthSecrets().consumerSecret("kd94hf93k423kf44").tokenSecret("pfkkdhi9sl3r4s00");
		 params.setConsumerKey("dpf43f3p2l4k3l03");
		 params.setToken("nnch734d00sl2jdk");
		 params.setSignatureMethod("HMAC-SHA1");
		 params.setTimestamp("1191242096");
		 params.setNonce("kllo9940pd9333jh");
		 params.setVersion("1.0");
		 	
		
		 
		 

		    try {
		        /* The error occurs here. */
		        if (OAuthSignature.verify(request, params, secrets)) {
		        	JSONObject json = new JSONObject();
		    		json.put("superhero-name", "trufaman");
		    		json.put("superhero-data", "detect trufas");
		    		result = "@Produces(\"application/json\") Output: superherofound " + json; 		
		        }else{
		        	result = "@Produces(\"application/json\") OAuthSignature not verified"; 
		        }
		    } catch (OAuthSignatureException e) {
		        result = "@Produces(\"application/json\") Output: fail [" + e.getMessage() + "]";
		    } catch (Exception e) {
		    	result = "@Produces(\"application/json\") Output: fail [" + e.getMessage() + "]";
		    }
		    
		    return Response.status(200).entity(result).build();
	}
	
}