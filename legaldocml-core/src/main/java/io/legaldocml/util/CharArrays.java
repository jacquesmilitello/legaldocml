package io.legaldocml.util;

import io.legaldocml.unsafe.UnsafeString;

/**
 * @author <a href="mailto:jacques.militello@gmail.com">Jacques Militello</a>
 */
public final class CharArrays {

    /**
     * Hide constructor.
     */
    private CharArrays() {
    }

    public static CharArray immutable(CharBuffer buffer) {
        char[] chars = new char[buffer.length()];
        System.arraycopy(buffer.hb, 0, chars, 0, buffer.length());
        return new ImmutableCharArray(chars);
    }

    public static CharArray immutable(CharBuffer buffer, int off, int length) {
        char[] chars = new char[length];
        System.arraycopy(buffer.hb, off, chars, 0, length);
        return new ImmutableCharArray(chars);
    }

    public static CharArray immutable(char[] array) {
        return new ImmutableCharArray(array);
    }

    public static CharArray immutable(String string) {
        return new ImmutableCharArray(UnsafeString.getChars(string));
    }

}
