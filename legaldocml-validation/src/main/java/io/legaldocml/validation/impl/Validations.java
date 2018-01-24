package io.legaldocml.validation.impl;

import io.legaldocml.akn.AknObject;
import io.legaldocml.akn.AkomaNtoso;
import io.legaldocml.akn.DocumentType;
import io.legaldocml.validation.Rule;
import io.legaldocml.validation.ValidationContext;
import io.legaldocml.validation.ValidationError;

/**
 * @author <a href="mailto:jacques.militello@gmail.com">Jacques Militello</a>
 */
public final class Validations {

	private Validations() {
	}
	
	public static <T extends DocumentType> ValidationContext context(AkomaNtoso<T> akn) {
		return new ValidationsDefaultContext<T>(akn);
	}
	
	public static ValidationError error(Rule rule, AknObject node, String message) {
		return new ValidationErrorImpl(rule, node, message);
	}
}
