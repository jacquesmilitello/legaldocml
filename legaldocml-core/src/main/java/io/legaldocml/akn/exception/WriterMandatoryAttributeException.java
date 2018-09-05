package io.legaldocml.akn.exception;

import io.legaldocml.LegalDocMlException;
import io.legaldocml.akn.AknObject;
import io.legaldocml.io.XmlWriter;

/**
 * @author <a href="mailto:jacques.militello@gmail.com">Jacques Militello</a>
 */
@SuppressWarnings("serial")
public final class WriterMandatoryAttributeException extends LegalDocMlException {

    public WriterMandatoryAttributeException(AknObject aknObject, String attributeName, XmlWriter writer) {
        super(buildMsg(aknObject, attributeName, writer));
    }

    private static String buildMsg(AknObject aknObject, String attributeName, XmlWriter writer) {
        StringBuilder builder = new StringBuilder();
        builder.append("CoreAttribute Mandatory [").append(attributeName).append("] ");
        builder.append("AknObject [").append(aknObject).append("]");
        return builder.toString();
    }
}
