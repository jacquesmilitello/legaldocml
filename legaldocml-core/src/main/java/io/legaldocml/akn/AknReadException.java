package io.legaldocml.akn;

import io.legaldocml.LegalDocMlException;
import io.legaldocml.io.XmlReader;

/**
 * @author <a href="mailto:jacques.militello@gmail.com">Jacques Militello</a>
 */
@SuppressWarnings("serial")
public final class AknReadException extends LegalDocMlException {

    public enum Type {

        MISSING_ELEMENT ("Missing Element [%s] in [%s]"),
        AKN_MODULE_NOT_FOUND ("AkomaNtoso namespace not found : Element [%s] in [%s]"),
        TWO_AKN_MODULES("The current XML has two akn modules [%s] - [%s]");

        private final String msg;

        Type(String msg) {
            this.msg = msg;
        }
    }

    public AknReadException(Type type, XmlReader reader, Object ... params) {
        super(buildMsg(type, reader,params));
    }

    private static String buildMsg(Type type, XmlReader reader, Object ... params) {
        StringBuilder builder = new StringBuilder();
        builder.append(String.format(type.msg, reader.getQName(),params));
        builder.append(" -> reader ").append(reader.toString());
        return builder.toString();
    }

}
