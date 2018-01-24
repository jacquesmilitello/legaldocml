package io.legaldocml.validation;

/**
 * @author <a href="mailto:jacques.militello@gmail.com">Jacques Militello</a>
 */
@FunctionalInterface
public interface Rule {

	void apply(ValidationContext context);
	
} 