package io.legaldocml.business;

import io.legaldocml.LegalDocMlException;

/**
 * @author <a href="mailto:jacques.militello@gmail.com">Jacques Militello</a>
 */
@SuppressWarnings("serial")
public class BusinessException extends LegalDocMlException {

    public BusinessException(String message) {
        super(message);
    }

}