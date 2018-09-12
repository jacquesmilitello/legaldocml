package io.legaldocml.akn.type;

import java.util.ArrayList;
import java.util.List;

/**
 * @author <a href="mailto:jacques.militello@gmail.com">Jacques Militello</a>
 */
public final class ListReferenceRefs {

    private ListReferenceRefs() {
    }

    public static ListReferenceRef make(ReferenceRef referenceRef) {
        List<ReferenceRef> refs = new ArrayList<>();
        refs.add(referenceRef);
        return new ListReferenceRefImpl(refs, null);
    }

    public static ListReferenceRef parse(char[] value) {
        List<ReferenceRef>  list = new ArrayList<>(2);
        int i = 0;
        int p = 0;
        while (i < value.length) {
            if (value[i] == ' ') {
                add(value, p, i - p, list);
                p = i + 1;
            }
            i++;
        }
        if (p <= value.length) {
            add(value, p, i - p, list);
        }

        return new ListReferenceRefImpl(list, value);
    }

    private static void add(char[] value, int pos, int length, List<ReferenceRef>  list) {
        char[] tmp = new char[length];
        System.arraycopy(value, pos, tmp, 0, length);
        list.add(new ReferenceRef(tmp));
    }

}