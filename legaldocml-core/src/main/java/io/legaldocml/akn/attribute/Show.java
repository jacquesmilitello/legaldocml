package io.legaldocml.akn.attribute;

import io.legaldocml.akn.AknObject;

/**
 * @author <a href="mailto:jacques.militello@gmail.com">Jacques Militello</a>
 */
public interface Show extends AknObject {

	/**
	 * Attribute name "showAs".
	 */
    String ATTRIBUTE_SHOW_AS = "showAs";
    /**
	 * Attribute name "shortForm".
	 */
    String ATTRIBUTE_SHORT_FORM = "shortForm";

    String getShowAs();

    void setShowAs(String showAs);

    String getShortForm();

    void setShortForm(String shortForm);

}