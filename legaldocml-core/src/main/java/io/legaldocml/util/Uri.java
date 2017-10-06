package io.legaldocml.util;

import io.legaldocml.akn.type.AbstractRef;
import io.legaldocml.unsafe.UnsafeString;

import java.util.function.Function;

/**
 * @author <a href="mailto:jacques.militello@gmail.com">Jacques Militello</a>
 */
public final class Uri extends AbstractRef {

    private static final Function<char[], Uri> INSTANTIATOR_URI = Uri::new;

    public static final Uri EMPTY = new Uri(new char[0]);

    private Uri(char[] value) {
        super(value);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public String toString() {
        return UnsafeString.valueOf(getChars());
    }

    public static Uri valueOf(String value) {
        return valueOf(UnsafeString.getChars(value));
    }

    public static Uri valueOf(char[] value) {
        return valueOf(value, INSTANTIATOR_URI);
    }

    public static Uri raw(char[] value) {
        return new Uri(value);
    }

    public static Uri raw(String value) {
        return new Uri(UnsafeString.getChars(value));
    }
}
