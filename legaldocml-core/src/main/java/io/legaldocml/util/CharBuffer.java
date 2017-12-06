package io.legaldocml.util;

/**
 * @author <a href="mailto:jacques.militello@gmail.com">Jacques Militello</a>
 */
public final class CharBuffer implements CharArray {

    public static final int DEFAULT_BUFFER_SIZE = 2048;

    protected char[] hb = new char[DEFAULT_BUFFER_SIZE];

    private int pos = 0;

    /**
     * Writes the given char into this buffer at the current position, and then increments the position.
     *
     * @param c The char to be written
     */
    public void put(char c) {
        if (pos == hb.length) {
            grow();
        }
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
    public CharArray subSequence(int start, int end) {
        char[] value = new char[end- start];
        System.arraycopy(this.hb, start, value, 0, value.length);
        return CharArrays.immutable(value);
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

    private void grow() {
        char[] newHb = new char[hb.length << 1];
        System.arraycopy(this.hb,0,newHb, 0, this.pos);
        this.hb = newHb;
    }

    public void clean() {
        reset();
        if (this.hb.length > DEFAULT_BUFFER_SIZE) {
            this.hb = new char[DEFAULT_BUFFER_SIZE];
        }
    }
}