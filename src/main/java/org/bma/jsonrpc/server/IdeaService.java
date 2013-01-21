package org.bma.jsonrpc.server;

import java.util.Collection;

import com.googlecode.jsonrpc4j.JsonRpcParam;

public interface IdeaService {
	int createNewIdea(@JsonRpcParam("ideaName") String name)
			throws IdeaAldeadyExistsException;

	Collection<String> getListOfIdeas();
}
