package io.legaldocml.akn.type;

import io.legaldocml.akn.AttributeValueException;
import io.legaldocml.unsafe.UnsafeString;

import java.util.Arrays;

/**
 * Abstract class for all Ref.
 *
 * @author <a href="mailto:jacques.militello@gmail.com">Jacques Militello</a>
 */
public abstract class AbstractRef {

    static final Reference DEFAULT_REFERENCE = new Reference() {
        @Override
        public boolean isRef(char[] value) {
            return (value.length > 0)  && value[0] == '#';
        }
        @Override
        public char[] make(char[] value) {
            char[] ref = new char[value.length + 1];
            ref[0] = '#';
            System.arraycopy(value,0, ref, 1, value.length);
            return ref;
        }
    };

    /**
     * Cache the hash code.
     */
    private int hash;

    private final char[] value;
    private final Reference reference;


    protected AbstractRef(char[] value, Reference reference) {
        this.value = value;
        this.reference = reference;
    }

    protected AbstractRef(char[] value) {
        this(value, DEFAULT_REFERENCE);
    }

    public final boolean isRef() {
        return reference.isRef(this.value);
    }
    
    public final NoWhiteSpace toEID() {
    	char[] val = this.value;
    	char[] eid = new char[val.length-1];
    	System.arraycopy(val, 1, eid, 0, eid.length);
    	return NoWhiteSpace.valueOf(eid);
    }

    public final boolean isEmpty() {
        return this.value == null || this.value.length == 0;
    }

    public char[] getChars() {
        return this.value;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public final String toString() {
        return UnsafeString.valueOf(this.value);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public int hashCode() {
        int h = hash;
        if (h == 0 && this.value.length > 0) {
            char val[] = this.value;
            for (int i = 0; i < this.value.length; i++) {
                h = 31 * h + val[i];
            }
            hash = h;
        }
        return h;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public boolean equals(Object o) {
        return this == o || o != null && getClass() == o.getClass() && Arrays.equals(value, ((AbstractRef) o).value);
    }

    static String requireNonNull(String value) {
        if (value == null) {
            throw new AttributeValueException("NPE : valueOf(null)");
        }
        return value;
    }
}