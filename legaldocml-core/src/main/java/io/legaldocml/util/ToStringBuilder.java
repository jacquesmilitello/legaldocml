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
        this(object, true);
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

    /**
     * Append a java.time.LocalDate
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
    public ToStringBuilder append(String key, LocalDateTime value) {
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
     * Gets the string.
     *
     * @return the string
     */
    public String toString() {
        int i = this.idx;
        if (this.value[i - 1] == ',') {
            this.value[i - 1] = '}';
        } else {
            this.value[i++] = '}';
        }

        char[] v = new char[i];
        System.arraycopy(this.value, 0, v, 0, i);
        return UnsafeString.buildUnsafe(v);
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
        System.arraycopy(value, 0, this.value, idx + 1, len);
        val[max - 2] = '"';
        val[max - 1] = ':';
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
        System.arraycopy(value, 0, this.value, this.idx + 1, len);
        val[max - 2] = '"';
        val[max - 1] = ',';
        this.idx = max;
    }

    private void insertValue(LocalDateTime dateTime) {
        int ptr = this.idx;
        int max = ptr + 24;
        if (max > this.value.length) {
            expandCapacity(max);
        }
        this.value[ptr++] = '[';
        ptr = appendValue(this.value, ptr, dateTime.toLocalDate());
        this.value[ptr++] = ',';
        ptr = appendValue(this.value, ptr, dateTime.toLocalTime());
        this.value[ptr++] = ']';
        this.idx = ptr;
    }

    private void insertValue(LocalDate date) {
        int ptr = this.idx;
        if (ptr + 12 > this.value.length) {
            expandCapacity(ptr + 12);
        }
        this.value[ptr++] = '[';
        ptr = appendValue(this.value, ptr, date);
        this.value[ptr++] = ']';
        this.idx = ptr;
    }

    private void insertValue(LocalTime time) {
        int ptr = this.idx;
        if (ptr + 10 > this.value.length) {
            expandCapacity(ptr + 10);
        }
        this.value[ptr++] = '[';
        ptr = appendValue(this.value, ptr, time);
        this.value[ptr++] =  ']';
        this.idx = ptr;
    }

    private void insertNullValue() {
        int max = this.idx + 5;
        if (max > this.value.length) {
            expandCapacity(max);
        }
        System.arraycopy(NULL, 0, this.value, this.idx, 4);
        this.value[max - 1] = ',';
        this.idx = max;
    }

    private static int appendValue(char[] val, int ptr, LocalDate date) {
        int year = date.getYear();
        val[ptr + 3] = (char) (48 + year % 10);
        year = Maths.unsignedDiv10(year);
        val[ptr + 2] = (char) (48 + year % 10);
        year = Maths.unsignedDiv10(year);
        val[ptr + 1] = (char) (48 + year % 10);
        val[ptr] = (char) (48 + (Maths.unsignedDiv1000(date.getYear())));
        ptr += 4;
        val[ptr++] = ',';
        val[ptr++] = (char) (48 + Maths.unsignedDiv10(date.getMonthValue()));
        val[ptr++] = (char) (48 + date.getMonthValue() % 10);
        val[ptr++] = ',';
        val[ptr++] = (char) (48 + Maths.unsignedDiv10(date.getDayOfMonth()));
        val[ptr++] = (char) (48 + date.getDayOfMonth() % 10);
        return ptr;
    }

    private static int appendValue(char[] val, int ptr, LocalTime time) {
        val[ptr++] = (char) (48 + Maths.unsignedDiv10(time.getHour()));
        val[ptr++] = (char) (48 + time.getHour() % 10);
        val[ptr++] = ',';
        val[ptr++] = (char) (48 + Maths.unsignedDiv10(time.getMinute()));
        val[ptr++] = (char) (48 + time.getMinute() % 10);
        val[ptr++] = ',';
        val[ptr++] = (char) (48 + Maths.unsignedDiv10(time.getSecond()));
        val[ptr++] = (char) (48 + time.getSecond() % 10);
        return ptr;
    }

}