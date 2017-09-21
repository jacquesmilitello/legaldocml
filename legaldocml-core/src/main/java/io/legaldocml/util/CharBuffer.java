package io.legaldocml.util;

/**
 * @author <a href="mailto:jacques.militello@gmail.com">Jacques Militello</a>
 */
public final class CharBuffer implements CharArray {

    final char[] hb = new char[4096];

    private int pos = 0;

    /**
     * Writes the given char into this buffer at the current position, and then increments the position.
     *
     * @param c The char to be written
     */
    public void put(char c) {
        hb[pos++] = c;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public String toString() {
        return new String(hb, 0, pos);
    }

    public int pos() {
        return pos;
    }

    public void setPos(int pos) {
        this.pos = pos;
    }

    public void reset() {
        pos = 0;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public int length() {
        return pos;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public char charAt(int index) {
        return hb[index];
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public CharSequence subSequence(int start, int end) {
        return new String(hb, start, end - start);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public char[] value() {
        char[] value = new char[pos];
        System.arraycopy(hb, 0, value, 0, pos);
        return value;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public CharArray prefix(int prefixNS) {
        char[] value = new char[prefixNS];
        System.arraycopy(this.hb, 0, value, 0, prefixNS);
        return CharArrays.immutable(value);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public int hashCode() {
        return (int) Hashing.xx(this.pos, this.pos, this.hb);
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }

        if (obj instanceof ImmutableCharArray) {
            ImmutableCharArray array = (ImmutableCharArray) obj;
            if (array.length() != pos) {
                return false;
            }
            char[] hb = this.hb;
            for (int i = 0; i < pos; i++) {
                if (hb[i] != array.charAt(i)) {
                    return false;
                }
            }
            return true;
        }

        return false;
    }

    public char getBefore(int i) {
        return hb[pos - i];
    }


}