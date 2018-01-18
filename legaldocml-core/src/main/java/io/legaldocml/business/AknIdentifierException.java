package io.legaldocml.business;

import io.legaldocml.LegalDocMlException;
import io.legaldocml.akn.element.CoreProperties;
import io.legaldocml.akn.element.FRBRExpression;
import io.legaldocml.akn.element.FRBRManifestation;
import io.legaldocml.akn.element.FRBRWork;

/**
 * @author <a href="mailto:jacques.militello@gmail.com">Jacques Militello</a>
 */
@SuppressWarnings("serial")
public final class AknIdentifierException extends LegalDocMlException {

    private final Type type;

    public AknIdentifierException(Type type) {
        super(buildMsg(type, null));
        this.type = type;
    }

    public AknIdentifierException(Type type, String message) {
        super(buildMsg(type, message));
        this.type = type;
    }

    private static String buildMsg(Type type, String message) {
        StringBuilder builder = new StringBuilder();
        builder.append("Type=[").append(type).append("]");
        if (message != null) {
            builder.append(" : ").append(message);
        }
        return builder.toString();
    }

    public static AknIdentifierException consistent(FRBRExpression frbrExpression, FRBRWork frbrWork) {
        return new AknIdentifierException(Type.CONSISTENT);
    }

    public static AknIdentifierException consistent(FRBRManifestation frbrManifestation, FRBRExpression frbrExpression) {
        return new AknIdentifierException(Type.CONSISTENT);
    }

    public static AknIdentifierException extract(String message, CoreProperties item) {
        return new AknIdentifierException(Type.EXTRACT);
    }

    public enum Type {
        CONSISTENT, EXTRACT, NEW
    }


}
