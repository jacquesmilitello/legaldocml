package io.legaldocml.business;

import io.legaldocml.akn.element.*;

/**
 * @author <a href="mailto:jacques.militello@gmail.com">Jacques Militello</a>
 */
public final class AknIdentifierException extends BusinessException {

    private final Type type;

    AknIdentifierException(Type type) {
        this.type = type;
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
