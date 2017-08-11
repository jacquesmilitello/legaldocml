package io.legaldocml.akn;

import io.legaldocml.LegalDocMlException;
import io.legaldocml.io.XmlWriter;

/**
 * @author <a href="mailto:jacques.militello@gmail.com">Jacques Militello</a>
 */
public final class MandatoryAttributeException extends LegalDocMlException {

    public MandatoryAttributeException(AknObject aknObject, String attributeName, XmlWriter writer) {
        super(buildMsg(aknObject, attributeName, writer));
    }

    private static String buildMsg(AknObject aknObject, String attributeName, XmlWriter writer) {
        StringBuilder builder = new StringBuilder();
        builder.append("Attribute Mandatory [").append(attributeName).append("] ");
        builder.append("AknObject [").append(aknObject).append("]");
        return builder.toString();
    }
}
