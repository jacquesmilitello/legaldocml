package io.legaldocml.akn.type;

import io.legaldocml.unsafe.UnsafeString;

import java.util.Arrays;

/**
 * These values are those of ids such as eId, wId and GUID as a collection of any printable character except
 * whitespaces.
 *
 * <pre>
 *   <xsd:simpleType name="noWhiteSpace">
 *     <xsd:restriction base="xsd:string">
 *       <xsd:pattern value="[^\s]+"/>
 *     <xsd:restriction>
 *   <xsd:simpleType>
 * </pre>
 *
 * @author <a href="mailto:jacques.militello@gmail.com">Jacques Militello</a>
 */
public final class NoWhiteSpace {

    private char[] value;

    /**
     * Cache the hash code for the NoWhiteSpace.
     */
    private int hash;

    public NoWhiteSpace(char[] value) {
        this.value = value;
    }

    public NoWhiteSpace(String id) {
        this.value = UnsafeString.getChars(id);
    }

    public char[] getChars() {
        return value;
    }

    public char[] makeRef() {
        char[] ref = new char[this.value.length + 1];
        ref[0] = '#';
        System.arraycopy(this.value,0,ref,1,this.value.length);
        return ref;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public String toString() {
        return UnsafeString.valueOf(this.value);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public boolean equals(Object o) {
        return this == o || o != null && getClass() == o.getClass() && Arrays.equals(value, ((NoWhiteSpace) o).value);
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
}
