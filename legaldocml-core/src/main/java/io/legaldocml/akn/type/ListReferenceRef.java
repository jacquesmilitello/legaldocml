package io.legaldocml.akn.type;

import io.legaldocml.unsafe.UnsafeString;

import java.util.ArrayList;
import java.util.List;

/**
 * @author <a href="mailto:jacques.militello@gmail.com">Jacques Militello</a>
 */
public final class ListReferenceRef {

    private final List<ReferenceRef> list;

    private final char[] raw;

    private boolean isModified;

    public ListReferenceRef() {
        this.raw = null;
        this.list = new ArrayList<>(2);
        this.isModified = false;
    }

    public ListReferenceRef(char[] value) {
        this.raw = value;
        this.isModified = false;
        list = new ArrayList<>(2);
        int i = 0;
        int p = 0;
        while (i < value.length) {
            if (value[i] == ' ') {
                add(value, p, i - p);
                p = i + 1;
            }
            i++;
        }
        if (p <= value.length) {
            add(value, p, i - p);
        }
    }

    public int size() {
        return this.list.size();
    }

    public void add(ReferenceRef ref) {
        this.list.add(ref);
        this.isModified = true;
    }

    public ReferenceRef get(int index) {
        return this.list.get(index);
    }

    public char[] getChars() {
        if (!isModified) {
            return this.raw;
        }
        StringBuilder builder = new StringBuilder();
        for (ReferenceRef referenceRef : list) {
            builder.append(referenceRef.getChars());
            builder.append(' ');
        }
        builder.deleteCharAt(builder.length() - 1);
        return UnsafeString.getChars(builder.toString());

    }

    private void add(char[] value, int pos, int length) {
        char[] tmp = new char[length];
        System.arraycopy(value, pos, tmp, 0, length);
        list.add(ReferenceRef.raw(tmp));
    }
}
