package io.legaldocml.util;

import io.legaldocml.unsafe.UnsafeString;

/**
 * @author <a href="mailto:jacques.militello@gmail.com">Jacques Militello</a>
 */
public final class Uri extends AbstractUri {

    public Uri(char[] value) {
        super(value);
    }

    public static Uri valueOf(String value) {
        return new Uri(UnsafeString.getChars(value));
    }

}
