package org.bma.jsonrpc.server;

import java.util.Collection;
import java.util.HashSet;
import java.util.Set;

import com.googlecode.jsonrpc4j.JsonRpcError;
import com.googlecode.jsonrpc4j.JsonRpcErrors;

public class DefaultIdeaService implements IdeaService {

	private Set<String> ideas = new HashSet<String>();

	private int i = 0;

	@JsonRpcErrors({ @JsonRpcError(exception = IdeaAldeadyExistsException.class, code = -5678) })
	public int createNewIdea(String name) throws IdeaAldeadyExistsException {
		if (ideas.contains(name)) {
			throw new IdeaAldeadyExistsException("Idea with name: " + name
					+ " already exists");
		}

		ideas.add(name);
		return ++i;
	}

	public Collection<String> getListOfIdeas() {
		return null;
	}

}
