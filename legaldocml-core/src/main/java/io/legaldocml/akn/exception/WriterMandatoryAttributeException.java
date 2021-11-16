package io.legaldocml.akn.exception;

import io.legaldocml.LegalDocMlException;
import io.legaldocml.akn.AknObject;
import io.legaldocml.io.CoreAttribute;
import io.legaldocml.io.XmlWriter;

/**
 * @author <a href="mailto:jacques.militello@gmail.com">Jacques Militello</a>
 */
public final class WriterMandatoryAttributeException extends LegalDocMlException {

    public WriterMandatoryAttributeException(AknObject aknObject, String attributeName, XmlWriter writer) {
        super("Attribute Mandatory [" + attributeName + "] " +
                "AknObject [" + aknObject + "]");
    }

    public WriterMandatoryAttributeException(CoreAttribute coreAttribute, String attributeName, XmlWriter writer) {
        super("Attribute Mandatory [" + attributeName + "] " +
                "CoreAttribute [" + coreAttribute + "]");
    }

}
