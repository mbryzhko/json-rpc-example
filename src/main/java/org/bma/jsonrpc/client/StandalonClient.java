package org.bma.jsonrpc.client;

import java.net.MalformedURLException;
import java.net.URL;

import org.bma.jsonrpc.server.IdeaService;

import com.googlecode.jsonrpc4j.JsonRpcHttpClient;
import com.googlecode.jsonrpc4j.ProxyUtil;

public class StandalonClient {

	/**
	 * @param args
	 * @throws MalformedURLException 
	 */
	public static void main(String[] args) throws MalformedURLException {
		JsonRpcHttpClient client = new JsonRpcHttpClient(new URL(
				"http://localhost:8080/IdeaService/IdeaService.json"));

		IdeaService ideaService = ProxyUtil.createClientProxy(StandalonClient.class.getClassLoader(),
				IdeaService.class, client);
		
		int ideaId = ideaService.createNewIdea("Evrika");

		System.out.println("Created new idea with id " + ideaId);
		
	}

}
