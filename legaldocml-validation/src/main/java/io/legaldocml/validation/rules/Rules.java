package io.legaldocml.validation.rules;

import io.legaldocml.validation.Rule;

/**
 * @author <a href="mailto:jacques.militello@gmail.com">Jacques Militello</a>
 */
public final class Rules {

	private Rules() {
	}
	
	public static Rule agentRefInReferences() {
		return AgentRefInReferencesRule.INSTANCE;
	}
}
