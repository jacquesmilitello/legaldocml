package io.legaldocml.akn.exception;

import io.legaldocml.LegalDocMlException;

/**
 * @author <a href="mailto:jacques.militello@gmail.com">Jacques Militello</a>
 */
@SuppressWarnings("serial")
public final class MetaException extends LegalDocMlException {

    public MetaException(String message) {
        super(message);
    }
}
