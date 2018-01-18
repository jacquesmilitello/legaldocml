package io.legaldocml.akn.exception;

import io.legaldocml.LegalDocMlException;
import io.legaldocml.akn.AknObject;

/**
 * @author <a href="mailto:jacques.militello@gmail.com">Jacques Militello</a>
 */
@SuppressWarnings("serial")
public final class MandatoryAttributeException extends LegalDocMlException {

    private final String attribute;
    private final AknObject aknObject;

    public MandatoryAttributeException(String message, String attribute, AknObject aknObject) {
        super(message);
        this.attribute = attribute;
        this.aknObject = aknObject;
    }

    public String getAttribute() {
        return attribute;
    }

    public AknObject getAknObject() {
        return aknObject;
    }

}
