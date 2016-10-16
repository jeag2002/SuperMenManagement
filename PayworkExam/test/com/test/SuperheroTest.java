package com.test;

//http://stackoverflow.com/questions/33302369/jersey-test-framework-documentation-doesnt-work

import static org.junit.Assert.assertEquals;

 
import org.json.JSONException;
import org.json.JSONObject;
import org.junit.Test;
import javax.ws.rs.core.UriBuilder;

import com.sun.jersey.api.client.WebResource;
import com.sun.jersey.test.framework.JerseyTest;
import com.sun.jersey.test.framework.LowLevelAppDescriptor;
import com.sun.jersey.test.framework.spi.container.TestContainerFactory;
import com.sun.jersey.test.framework.spi.container.grizzly2.GrizzlyTestContainerFactory;

import junit.framework.Assert;
 
 
public class SuperheroTest extends JerseyTest {
	
   @Override
    protected TestContainerFactory getTestContainerFactory() {
        return new GrizzlyTestContainerFactory();
    }

	public SuperheroTest() {
        super(new LowLevelAppDescriptor.Builder(Superhero.class).
                contextPath("context").
                build());
    }

    @Test
    public void testSuperHero() {
        WebResource r = resource().path("/superhero");
        String data = r.get(String.class);
        Assert.assertEquals("@Produces(\"application/json\") Output: superherofound {\"superhero-data\":\"detect trufas\",\"superhero-name\":\"trufaman\"}", data);
    }

    @Test
    public void testSuperHeroByIndex() {
        WebResource r = resource().path("/superhero/1");
        String data = r.get(String.class);
        Assert.assertEquals("@Produces(\"application/json\") Output: superherofound by index [1]{\"superhero-data\":\"detect trufas\",\"superhero-name\":\"trufaman\"}", data);
    }

	
}