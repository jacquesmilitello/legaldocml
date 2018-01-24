package io.legaldocml.akn.type;

import io.legaldocml.akn.AttributeValueException;
import io.legaldocml.unsafe.UnsafeString;
import io.legaldocml.util.AbstractUri;

import java.util.Arrays;
import java.util.function.Function;

import static java.lang.System.arraycopy;

/**
 * Abstract class for all Ref.
 *
 * @author <a href="mailto:jacques.militello@gmail.com">Jacques Militello</a>
 */
public abstract class AbstractRef extends AbstractUri {

    public static final char REF = '#';

    protected AbstractRef(char[] value) {
        super(value);
    }

    public final boolean isRef() {
    	char[] val = getChars();
    	return val.length > 0 && val[0] == '#';
    }
    
    public final NoWhiteSpace toEID() {
    	char[] val = getChars();
    	char[] eid = new char[val.length-1];
    	System.arraycopy(val, 1, eid, 0, eid.length);
    	return NoWhiteSpace.valueOf(eid);
    }
    
    /**
     * {@inheritDoc}
     */
    @Override
    public String toString() {
        return UnsafeString.valueOf(getChars());
    }

    static <T extends AbstractRef> T valueOf(char[] value, Function<char[], T> instantiator) {
        if (value == null || value.length == 0) {
            throw new AttributeValueException("Value is null or empty : " + Arrays.toString(value));
        }
        if (value[0] == AbstractRef.REF) {
            return instantiator.apply(value);
        }
        char[] ref = new char[value.length + 1];
        ref[0] = AbstractRef.REF;
        arraycopy(value, 0, ref, 1, value.length);
        return instantiator.apply(ref);
    }

}