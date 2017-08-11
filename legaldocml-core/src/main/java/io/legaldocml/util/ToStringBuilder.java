package io.legaldocml.util;

import io.legaldocml.unsafe.UnsafeString;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.Arrays;

/**
 * @author <a href="mailto:jacques.militello@gmail.com">Jacques Militello</a>
 */
public class ToStringBuilder {

    /**
     * Default size for the buffer.
     */
    private static final int DEFAULT = 2048;

    /**
     * char buffer for a null object.
     */
    private static final char[] NULL = new char[]{'n', 'u', 'l', 'l'};

    /**
     * char buffer for a null object.
     */
    private static final char[] CLASS = new char[]{'c', 'l', 'a', 's', 's'};

    /**
     * char buffer for a null object.
     */
    private static final char[] IDENTITY_HASH_CODE = new char[]{'i', 'd', 'e', 'n', 't', 'i', 't', 'y', 'H', 'a', 's', 'h', 'C', 'o', 'd', 'e'};

    /**
     * The buffer for this builder.
     */
    private char[] value;

    /**
     * The index for this buffer (for char[] value).
     */
    private int idx;

    /**
     * boolean to test if we append null value or not.
     */
    private final boolean appendNull;

    /**
     * Instantiates a new to string builder.
     */
    public ToStringBuilder(boolean appendNull) {
        this.value = new char[DEFAULT];
        this.value[0] = '{';
        this.idx = 1;
        this.appendNull = appendNull;
    }

    public ToStringBuilder(Object object) {
        this(object,true);
    }

    public ToStringBuilder(Object object, boolean appendNull) {
        this(appendNull);
        // add "class":"...."
        insertKey(CLASS);
        insertValue(UnsafeString.getChars(object.getClass().getSimpleName()));
        // add "identityHashCode":"....."
        insertKey(IDENTITY_HASH_CODE);
        insertValue(UnsafeString.getChars(String.valueOf(System.identityHashCode(object))));
    }

    /**
     * Append.
     *
     * @param key   the key
     * @param value the value
     * @return the to string builder
     */
    public ToStringBuilder append(String key, String value) {
        if (!this.appendNull && value == null) {
            return this;
        }
        insertKey(UnsafeString.getChars(key));
        if (value == null) {
            insertNullValue();
        } else {
            insertValue(UnsafeString.getChars(value));
        }
        return this;
    }

    /**
     * Append.
     *
     * @param key   the key
     * @param value the value
     * @return the to string builder
     */
    public ToStringBuilder append(String key, char[] value) {
        if (!this.appendNull && value == null) {
            return this;
        }

        insertKey(UnsafeString.getChars(key));
        if (value == null) {
            insertNullValue();
        } else {
            insertValue(value);
        }
        return this;
    }

    /**
     * Append.
     *
     * @param key   the key
     * @param value the value
     * @return the to string builder
     */
    public ToStringBuilder append(String key, Object value) {
        if (!this.appendNull && value == null) {
            return this;
        }
        insertKey(UnsafeString.getChars(key));
        if (value == null) {
            insertNullValue();
        } else {
            insertValue(UnsafeString.getChars(value.toString()));
        }
        return this;
    }



//    /**
//     * Append.
//     *
//     * @param key    the key
//     * @param values the values
//     * @return the to string builder
//     */
//    public ToStringBuilder append(String key, Object[] values) {
//        if (!this.appendNull && (values == null || values.length == 0)) {
//            return this;
//        }
//        insert(key).insert("=").insert(Arrays.toString(values)).insert(" ");
//        return this;
//    }

    /**
     * Append a java.util.Calendar.
     *
     * @param key   the key
     * @param value the value
     * @return the to string builder
     */
    public ToStringBuilder append(String key, LocalDate value) {
        if (!this.appendNull && value == null) {
            return this;
        }
        insertKey(UnsafeString.getChars(key));
        if (value == null) {
            insertNullValue();
        } else {
            insertValue(value);
        }
        return this;
    }

    /**
     * Append a java.time.LocalTime.
     *
     * @param key   the key
     * @param value the value
     * @return the to string builder
     */
    public ToStringBuilder append(String key, LocalTime value) {
        if (!this.appendNull && value == null) {
            return this;
        }
       // insert(key).insert("=[").append(value == null ? NULL : DateTimeFormatter.ISO_LOCAL_TIME.format(value)).append("] ");
        return this;
    }

    /**
     * Append.
     *
     * @param key   the key
     * @param value the value
     * @return the to string builder
     */
    public ToStringBuilder append(String key, LocalDateTime value) {
        if (!this.appendNull && value == null) {
            return this;
        }
       // insert(key).insert("=[").insert(value == null ? NULL : DateTimeFormatter.ISO_LOCAL_DATE_TIME.format(value)).insert("] ");
        return this;
    }

    /**
     * Gets the string.
     *
     * @return the string
     */
    public String toString() {
        int idx = this.idx;
        if (this.value[this.idx - 1] != '{') {
            this.value[this.idx - 1] = '}';
        } else {
            this.value[this.idx] = '}';
        }

        char[] value = new char[idx];
        System.arraycopy(this.value,0,value, 0, idx);


        //String value = new String(this.value, 0, idx);
//        if (this.ptr > 1) {
//            this.value[this.ptr - 1] = ' ';
//        } else {
//            this.ptr--;
//        }
        return UnsafeString.buildUnsafe(value);
    }

    private void expandCapacity(int minimumCapacity) {
        int newCapacity = (this.value.length + 1) * 2;
        if (newCapacity < 0) {
            newCapacity = Integer.MAX_VALUE;
        } else if (minimumCapacity > newCapacity) {
            newCapacity = minimumCapacity;
        }
        this.value = Arrays.copyOf(this.value, newCapacity);
    }


    private void insertKey(char[] value) {
        int idx = this.idx;
        int len = value.length;
        int max = idx + len + 3;
        if (max > this.value.length) {
            expandCapacity(max);
        }
        char[] val = this.value;
        val[idx] = '"';
        System.arraycopy(value,0, this.value, idx + 1, len);
        val[max-2] = '"';
        val[max-1] = ':';
        this.idx = max;
    }

    private void insertValue(char[] value) {
        int len = value.length;
        int max = this.idx + len + 3;
        if (max > this.value.length) {
            expandCapacity(max);
        }
        char[] val = this.value;
        val[this.idx] = '"';
        System.arraycopy(value,0, this.value, this.idx + 1, len);
        val[max-2] = '"';
        val[max-1] = ',';
        this.idx = max;
    }

    private void insertValue(LocalDate date) {
        int ptr = this.idx;
        int max = ptr + 8 + 2;
        if (max > this.value.length) {
            expandCapacity(max);
        }
        char[] val = this.value;
        val[ptr++] = '[';
        val[ptr++] = ',';
        val[ptr++] = ',';
        val[ptr++] = ']';
        this.idx = max;
    }

    private void insertNullValue() {
        int max = this.idx + 4;
        if (max > this.value.length) {
            expandCapacity(max);
        }
        System.arraycopy(NULL, 0, this.value, this.idx, 4);
        this.idx = max;
    }

}