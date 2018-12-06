package io.legaldocml.io.impl;

import io.legaldocml.LegalDocMlException;
import io.legaldocml.io.Externalizable;

/**
 * @author <a href="mailto:jacques.militello@gmail.com">Jacques Militello</a>
 */
@SuppressWarnings("serial")
public final class MandatoryAttributeException extends LegalDocMlException {

    private final String attribute;
    private final Externalizable externalizable;

    public MandatoryAttributeException(String message, String attribute, Externalizable externalizable) {
        super(message);
        this.attribute = attribute;
        this.externalizable = externalizable;
    }

    public String getAttribute() {
        return attribute;
    }

    public Externalizable getExternalizable() {
        return externalizable;
    }

}
