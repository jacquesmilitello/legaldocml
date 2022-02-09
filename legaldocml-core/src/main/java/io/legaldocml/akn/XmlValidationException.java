package io.legaldocml.akn;

import io.legaldocml.LegalDocMlException;

public final class XmlValidationException extends LegalDocMlException {

    public enum Type {

        DUPLICATE_XML_ID("Duplicate xml:id attribute [%s]");

        private final String msg;

        Type(String msg) {
            this.msg = msg;
        }
    }

    private final Type type;

    public XmlValidationException(XmlValidationException.Type type, String value) {
        super(buildMsg(type, value));
        this.type = type;
    }

    public Type getType() {
        return type;
    }

    private static String buildMsg(XmlValidationException.Type type, String value) {
        StringBuilder builder = new StringBuilder();
        builder.append(String.format(type.msg, value));
        return builder.toString();
    }

}
