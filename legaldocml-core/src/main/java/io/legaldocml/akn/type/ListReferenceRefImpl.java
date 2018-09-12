package io.legaldocml.akn.type;

import io.legaldocml.akn.element.Attributes;
import io.legaldocml.io.XmlWriter;
import io.legaldocml.unsafe.UnsafeString;
import io.legaldocml.util.ToStringBuilder;

import java.io.IOException;
import java.util.List;

final class ListReferenceRefImpl implements ListReferenceRef {

    private final List<ReferenceRef> list;

    private char[] raw;

    private boolean isModified;

    ListReferenceRefImpl(List<ReferenceRef> list, char[] raw) {
        this.raw = raw;
        this.list = list;
        this.isModified = this.raw == null;
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

    public boolean contains(ReferenceRef referenceRef) {
        for (ReferenceRef ref : this.list) {
            if (ref.equals(referenceRef)) {
                return true;
            }
        }
        return false;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public String toString() {
        return new ToStringBuilder(this)
                .append("raw", raw)
                .append("list", list)
                .append("isModified", isModified)
                .toString();
    }

    @Override
    public void write(XmlWriter writer) throws IOException {
        if (isModified) {
            StringBuilder builder = new StringBuilder();
            for (ReferenceRef referenceRef : list) {
                builder.append(referenceRef.getChars());
                builder.append(' ');
            }
            builder.deleteCharAt(builder.length() - 1);
            this.raw = UnsafeString.getChars(builder.toString());
            this.isModified = false;
        }
        writer.writeAttribute(Attributes.ADDRESS_REFERS, 8, this.raw);
    }
}
