package io.legaldocml.util;

import io.legaldocml.akn.type.NoWhiteSpace;
import io.legaldocml.unsafe.UnsafeString;

import java.util.Arrays;

/**
 * <pre>
 *    foo://example.com:8042/over/there?name=ferret#nose
 *    \_/   \______________/\_________/ \_________/ \__/
 *     |           |            |            |        |
 *   scheme     authority      path        query   fragment
 *     |   _____________________|__
 *    / \ /                        \
 *    urn:example:animal:ferret:nose
 * </pre>
 *
 * @author <a href="mailto:jacques.militello@gmail.com">Jacques Militello</a>
 */
public abstract class AbstractUri {

    public static final char REF = '#';

    private final char[] value;

    /**
     * Cache the hash code for the NoWhiteSpace.
     */
    private int hash;

    private int scheme;
    private int specificPart;
    private int fragment;

    public AbstractUri(char[] value) {
        this.value = value;
        init(value);
    }

    private void init(char[] val) {
        int i = 0;
        for (; i < val.length; i++) {
            if (val[i] == ':') {
                break;
            }
        }
        if (i == val.length) {
            this.scheme = -1;
        } else {
            this.scheme = i;
        }
        for (; i < val.length; i++) {
            if (val[i] == '#') {
                break;
            }
        }
        this.specificPart = i;
    }

    public char[] getChars() {
        return this.value;
    }

    public String getScheme() {
        if (this.scheme == -1) {
            return null;
        }
        return new String(this.value, 0, this.scheme);
    }

    public String getSchemeSpecificPart() {
        return new String(this.value, this.scheme + 1, this.specificPart - this.scheme - 1);
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
        return this == o || o != null && getClass() == o.getClass() && Arrays.equals(value, ((AbstractUri) o).value);
    }

    protected static char[] makeRef(NoWhiteSpace noWhiteSpace) {
        StringBuilder builder = new StringBuilder(64);
        builder.append(REF);
        builder.append(noWhiteSpace.getChars());
        return UnsafeString.getChars(builder.toString());
    }
}
