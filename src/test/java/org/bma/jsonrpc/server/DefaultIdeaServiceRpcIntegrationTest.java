package org.bma.jsonrpc.server;

import java.net.MalformedURLException;
import java.net.URL;

import org.bma.jsonrpc.client.StandalonClient;
import org.hamcrest.CoreMatchers;
import org.hamcrest.number.OrderingComparison;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;

import com.googlecode.jsonrpc4j.JsonRpcHttpClient;
import com.googlecode.jsonrpc4j.ProxyUtil;

// To run this test server should running.
public class DefaultIdeaServiceRpcIntegrationTest {

	private IdeaService ideaService;

	@Rule
	public ExpectedException expectedException = ExpectedException.none(); 
	
	@Before
	public void setUp() throws MalformedURLException {
		JsonRpcHttpClient client = new JsonRpcHttpClient(new URL(
				"http://localhost:8080/IdeaService/IdeaService.json"));

		ideaService = ProxyUtil.createClientProxy(
				StandalonClient.class.getClassLoader(), IdeaService.class,
				client);
	}

	@Test
	public void givenIdeaNameWhenCreateNewThenIdShouldBeReturned() {
		int newIdeaId = ideaService.createNewIdea("Evrika");

		Assert.assertThat(newIdeaId, OrderingComparison.greaterThan(0));
	}

	@Test
	public void givenIdeaNameWhichAlreadyExistsWhenCreateNewThenExceptionIsThrown() {
		// Expect 
		expectedException.expect(IdeaAldeadyExistsException.class);
		
		// Given
		ideaService.createNewIdea("Existing Idea");

		// When
		ideaService.createNewIdea("Existing Idea");
	}

}
