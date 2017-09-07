package io.legaldocml.xpath.impl;

import io.legaldocml.util.ToStringBuilder;

/**
 * @author <a href="mailto:jacques.militello@gmail.com">Jacques Militello</a>
 */
public final class XPathQname {

    private final String prefix;
    private final String localname;

    XPathQname(String prefix, String localname) {
        this.prefix = prefix;
        this.localname = localname;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public String toString() {
        return new ToStringBuilder(true)
                .append("prefix", this.prefix)
                .append("localname", this.localname)
                .toString();
    }

    public String getPrefix() {
        return this.prefix;
    }

    public String getLocalname() {
        return this.localname;
    }
}
