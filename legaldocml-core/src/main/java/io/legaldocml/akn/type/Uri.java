package io.legaldocml.akn.type;

import io.legaldocml.unsafe.UnsafeString;
import io.legaldocml.util.CharArray;

import static java.util.Objects.requireNonNull;

/**
 * @author <a href="mailto:jacques.militello@gmail.com">Jacques Militello</a>
 */
public final class Uri extends AbstractRef {

    public static final Uri EMPTY = new Uri(new char[0]);

    Uri(char[] value) {
        super(value);
    }

    public Uri(CharArray charArray) {
        super(charArray.value());
    }

    public static Uri valueOf(String value) {
        return new Uri(UnsafeString.getChars(requireNonNull(value)));
    }

}
