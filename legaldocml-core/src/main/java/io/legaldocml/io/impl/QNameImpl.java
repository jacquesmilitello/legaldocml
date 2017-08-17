package io.legaldocml.io.impl;


import io.legaldocml.io.QName;
import io.legaldocml.unsafe.UnsafeString;

/**
 * @author <a href="mailto:jacques.militello@gmail.com">Jacques Militello</a>
 * @see <a href="http://en.wikipedia.org/wiki/Qname">Wikipedia: QName</a>
 */
public final class QNameImpl implements QName {

    private final char[] full;

    private String cache;

    private final String localname;

    private final String prefix;

    QNameImpl(char[] hb, int len, int prefixLength) {
        full = new char[len];
        System.arraycopy(hb, 0, full, 0, len);
        if (prefixLength < 1) {
            this.prefix = null;
            this.localname = UnsafeString.valueOf(full);
        } else {
            this.prefix = new String(full, 0, prefixLength);
            this.localname = new String(full, prefixLength + 1, full.length - prefixLength - 1).intern();
        }
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public String toString() {
        if (cache == null) {
            cache = UnsafeString.valueOf(full);
        }
        return cache;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public boolean equals(Object obj) {
        return obj instanceof QNameImpl && doEquals((QNameImpl) obj);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public int hashCode() {
        if (this.cache == null) {
            this.cache = UnsafeString.valueOf(full);
        }
        return this.cache.hashCode();
    }

    boolean doEquals(QNameImpl qName) {

        if (qName == this) {
            return true;
        }

        if (qName == null) {
            return false;
        }

        if (this.full.length != qName.full.length) {
            return false;
        }

        for (int i = 0; i < this.full.length; i++) {
            if (this.full[i] != qName.full[i]) {
                return false;
            }
        }

        return true;

    }

    /**
     * {@inheritDoc}
     */
    @Override
    public String getLocalName() {
        return localname;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public String getPrefix() {
        return prefix;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public boolean equalsLocalName(String element) {
        return this.localname.equals(element);
    }

}
