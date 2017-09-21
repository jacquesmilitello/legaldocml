package io.legaldocml.util;

import io.legaldocml.unsafe.UnsafeString;

import java.util.Arrays;

/**
 * @author <a href="mailto:jacques.militello@gmail.com">Jacques Militello</a>
 */
final class ImmutableCharArray implements CharArray {

    /**
     * The char arrayBuffer.
     */
    private final char[] c;

    /**
     * Length of this char array.
     */
    private final int length;

    /**
     * Cache the hash code.
     */
    private int hash; // Default to 0

    ImmutableCharArray(char[] array) {
        this(array, array.length);
    }

    ImmutableCharArray(char[] array, int len) {
        this.c = array.clone();
        this.length = len;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public int length() {
        return this.length;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public char charAt(int index) {
        return this.c[index];
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public CharSequence subSequence(int start, int end) {
        if (start == 0) {
            return new ImmutableCharArray(this.c, end);
        } else {
            char[] val = new char[end - start];
            System.arraycopy(this.c, start, val, 0, val.length);
            return UnsafeString.valueOf(val);
        }
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public char[] value() {
        return Arrays.copyOf(this.c, length);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public CharArray prefix(int prefixNS) {
        throw new UnsupportedOperationException();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public String toString() {
        return UnsafeString.valueOf(this.c);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public boolean equals(Object obj) {

        if (obj == null) {
            return false;
        }

        if (obj == this) {
            return true;
        }

        if (obj instanceof ImmutableCharArray) {
            ImmutableCharArray s = (ImmutableCharArray) obj;
            if (this.length != s.length) {
                return false;
            }
            for (int i = 0; i < this.length; i++) {
                if (this.c[i] != s.c[i]) {
                    return false;
                }
            }
            return true;
        }

        if (obj instanceof CharSequence) {
            CharSequence s = (CharSequence) obj;
            if (this.length != s.length()) {
                return false;
            }
            for (int i = 0; i < this.length; i++) {
                if (this.c[i] != s.charAt(i)) {
                    return false;
                }
            }
            return true;
        }

        return super.equals(obj);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public int hashCode() {
        return (int) Hashing.xx(this.length, this.length, this.c);
    }

}
