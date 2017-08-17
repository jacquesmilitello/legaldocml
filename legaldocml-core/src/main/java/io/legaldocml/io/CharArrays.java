package io.legaldocml.io;

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

    public static CharArray constant(CharBuffer buffer) {
        char[] chars = new char[buffer.length()];
        System.arraycopy(buffer.hb, 0, chars, 0, buffer.length());
        return new ConstantCharArray(chars);
    }

    public static CharArray constant(CharBuffer buffer, int off, int length) {
        char[] chars = new char[length];
        System.arraycopy(buffer.hb, off, chars, 0, length);
        return new ConstantCharArray(chars);
    }

    public static CharArray constant(char[] array) {
        return new ConstantCharArray(array);
    }

    public static CharArray constant(String string) {
        return constant(UnsafeString.getChars(string));
    }

    static final class ConstantCharArray implements CharArray {

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

        private ConstantCharArray(char[] array) {
            this.c = array.clone();
            this.length = c.length;
        }

        private ConstantCharArray(char[] array, int len) {
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
                return new ConstantCharArray(this.c, end);
            } else {
                throw new UnsupportedOperationException();
            }
        }

        /**
         * {@inheritDoc}
         */
        @Override
        public char[] value() {
            return this.c;
        }

        /**
         * {@inheritDoc}
         */
        @Override
        public char[] raw() {
            throw new UnsupportedOperationException();
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

        @Override
        public boolean equals(Object obj) {

            if (obj == null) {
                return false;
            }

            if (obj == this) {
                return true;
            }

            if (obj instanceof ConstantCharArray) {
                ConstantCharArray s = (ConstantCharArray) obj;
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

        @Override
        public int hashCode() {
            int h = hash;
            if (h == 0 && this.c.length > 0) {
                char val[] = this.c;
                for (int i = 0; i < val.length; i++) {
                    h = 31 * h + val[i];
                }
                hash = h;
            }
            return h;
        }
    }
}
