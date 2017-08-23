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

    public String ref() {
        return UnsafeString.valueOf(getChars());
    }

    static char[] makeRef(NoWhiteSpace noWhiteSpace) {
        StringBuilder builder = new StringBuilder(64);
        builder.append(REF);
        builder.append(noWhiteSpace.getChars());
        return UnsafeString.getChars(builder.toString());
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
