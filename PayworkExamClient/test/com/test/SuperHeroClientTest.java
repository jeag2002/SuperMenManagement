package com.test;

import org.mockserver.client.server.MockServerClient;
import org.mockserver.junit.MockServerRule;
import org.mockserver.mockserver.MockServer;
import org.mockserver.model.HttpRequest;
import org.mockserver.model.HttpResponse;
import org.mockserver.verify.VerificationTimes;

import com.sun.jersey.api.client.Client;
import com.sun.jersey.api.client.ClientResponse;
import com.sun.jersey.api.client.WebResource;

import junit.framework.Assert;
import junit.framework.TestCase;

public class SuperHeroClientTest extends TestCase{

	//public MockServerRule mockServerRule;
	private MockServerClient mockServerClient;
	private MockServer mockServer;
	
	public SuperHeroClientTest(){
		//mockServerRule = new MockServerRule(this, 9000);
	}
	
	/**
	 * TEST: CLIENT AGAINST MOCK SERVER
	 */
	public void testClientAgainMockHTTPServer(){
		// setting behaviour for test case
		mockServer = new MockServer(9002);
		mockServerClient = new MockServerClient("localhost", 9002);
		mockServerClient.when(HttpRequest.request("/PayworkExam/rest/superhero")).respond(HttpResponse.response().withStatusCode(200).withBody("{\"superhero-data\":\"detect trufas\",\"superhero-name\":\"trufaman\"}"));
		
		Assert.assertTrue(mockServerClient.isRunning());
 
		Client client = new Client();
		WebResource webResource2 = client.resource("http://localhost:9002/PayworkExam/rest/superhero");
		ClientResponse response = webResource2.accept("application/json").get(ClientResponse.class);
		Assert.assertEquals(200, response.getStatus());
		String data = response.getEntity(String.class);
		Assert.assertEquals("{\"superhero-data\":\"detect trufas\",\"superhero-name\":\"trufaman\"}",data);
		
		// verify server has received exactly one request
		mockServerClient.verify(HttpRequest.request("/PayworkExam/rest/superhero"), VerificationTimes.once());
		mockServerClient.stop();
		mockServer.stop();
	}
	
	
	
	
}