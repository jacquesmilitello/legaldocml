package io.legaldocml.unsafe;

import io.legaldocml.util.Strings;
import sun.misc.Unsafe;

/**
 * @author <a href="mailto:jacques.militello@gmail.com">Jacques Militello</a>
 */
@SuppressWarnings("restriction")
public final class UnsafeString {

    /**
     * Unsafe special object.
     */
    private static final Unsafe UNSAFE = UnsafeHelper.getUnsafe();

    /**
     * Field offset of the inner value var in the String class.
     */
    private static final long VALUE_OFFSET = UnsafeHelper.getFieldOffset(String.class, "value");

    /**
     * Empty char.
     */
    private static final char[] EMPTY = new char[0];

    @SuppressWarnings("all")
    //private static final sun.misc.JavaLangAccess JAVA_LANG_ACCESS = sun.misc.SharedSecrets.getJavaLangAccess();

    private UnsafeString() {
    }

	public static char[] getChars(String s) {
        if (s == null) {
            return EMPTY;
        }
        if (Jvm.isJava8()) {
            return (char[]) UNSAFE.getObject(s, VALUE_OFFSET);
        } else {
            return s.toCharArray();
        }
    }

    @SuppressWarnings("all")
    public static String valueOf(char[] chars) {
        if (chars == null) {
            return "";
        }
        if (Jvm.isJava8()) {
            String mutable = new String();// an empty string to hack
            UNSAFE.putObject(mutable, VALUE_OFFSET, chars);
            return mutable;
        } else {
            return new String(chars);
        }
    }

}
