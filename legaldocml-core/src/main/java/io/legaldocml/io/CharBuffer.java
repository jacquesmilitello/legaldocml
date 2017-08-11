package io.legaldocml.io;

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

    public char[] raw() {
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
        return CharArrays.constant(value);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public int hashCode() {
        int h = 0;
        if (pos > 0) {
            char val[] = hb;
            for (int i = 0; i < pos; i++) {
                h = 31 * h + val[i];
            }
        }
        return h;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }

        if (obj instanceof CharArrays.ConstantCharArray) {
            CharArrays.ConstantCharArray array = (CharArrays.ConstantCharArray) obj;
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