package io.legaldocml;

/**
 * Main Exception.
 *
 * @author <a href="mailto:jacques.militello@gmail.com">Jacques Militello</a>
 */
@SuppressWarnings("serial")
public abstract class LegalDocMlException extends RuntimeException {

    protected LegalDocMlException(String message) {
        super(message);
    }

    protected LegalDocMlException(String message, Throwable cause) {
        super(message, cause);
    }

}