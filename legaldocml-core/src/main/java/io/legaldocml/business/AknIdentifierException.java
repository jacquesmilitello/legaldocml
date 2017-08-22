package io.legaldocml.business;

import io.legaldocml.akn.element.*;

/**
 * @author <a href="mailto:jacques.militello@gmail.com">Jacques Militello</a>
 */
public final class AknIdentifierException extends BusinessException {

    private final Type type;

    AknIdentifierException(Type type) {
        super(buildMsg(type));
        this.type = type;
    }

    private static String buildMsg(Type type) {
        StringBuilder builder = new StringBuilder();
        builder.append("Type=[").append(type).append("]");
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
        CONSISTENT, EXTRACT
    }


}
