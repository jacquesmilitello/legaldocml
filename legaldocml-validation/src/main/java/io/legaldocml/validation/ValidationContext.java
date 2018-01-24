package io.legaldocml.validation;

import io.legaldocml.akn.AknObject;
import io.legaldocml.akn.AkomaNtoso;
import io.legaldocml.akn.DocumentType;

/**
 * @author <a href="mailto:jacques.militello@gmail.com">Jacques Militello</a>
 */
public interface ValidationContext {

	<T extends DocumentType> AkomaNtoso<T> getAkomaNtoso();

	void addError(Rule rule, AknObject node, String message);

	int getErrorCount();
	
}
