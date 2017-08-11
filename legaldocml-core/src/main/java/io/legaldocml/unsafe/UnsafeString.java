package io.legaldocml.unsafe;

import sun.misc.Unsafe;

/**
 * @author <a href="mailto:jacques.militello@gmail.com">Jacques Militello</a>
 */
public final class UnsafeString {


    private static final Unsafe UNSAFE = UnsafeHelper.getUnsafe();

    private static final long VALUE_OFFSET = UnsafeHelper.getFieldOffset(String.class, "value");

    @SuppressWarnings("all")
    private static final sun.misc.JavaLangAccess JAVA_LANG_ACCESS = sun.misc.SharedSecrets.getJavaLangAccess();

    private UnsafeString() {
    }

    public static String buildUnsafe(char[] chars) {
        String mutable = new String();// an empty string to hack
        UNSAFE.putObject(mutable, VALUE_OFFSET, chars);
        return mutable;
    }

    public static char[] getChars(String s) {
        return (char[]) UNSAFE.getObject(s, VALUE_OFFSET);
    }

    @SuppressWarnings("all")
    public static String valueOf(char[] buf) {
        return JAVA_LANG_ACCESS.newStringUnsafe(buf);
    }

}
