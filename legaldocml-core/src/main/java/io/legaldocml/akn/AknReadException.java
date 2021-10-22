package io.legaldocml.akn;

import io.legaldocml.LegalDocMlException;
import io.legaldocml.io.QName;
import io.legaldocml.io.XmlReader;

import java.util.Arrays;

/**
 * @author <a href="mailto:jacques.militello@gmail.com">Jacques Militello</a>
 */
@SuppressWarnings("serial")
public final class AknReadException extends LegalDocMlException {

    public enum Type {

        INVALID_ELEMENT ("Invalid Element [%s] in [%s]");

        private final String msg;

        Type(String msg) {
            this.msg = msg;
        }
    }

    public AknReadException(Type type, XmlReader reader, QName qname) {
        super(buildMsg(type, reader, qname));
    }

    private static String buildMsg(Type type, XmlReader reader, QName qName) {
        StringBuilder builder = new StringBuilder();
        builder.append(String.format(type.msg, reader.getQName(), qName));
        builder.append(" -> reader ").append(reader);
        return builder.toString();
    }

}
