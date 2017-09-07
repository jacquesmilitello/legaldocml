package io.legaldocml.xpath.impl;

/**
 * @author <a href="mailto:jacques.militello@gmail.com">Jacques Militello</a>
 */
final class XPathQnameBuilder {

    private final String[] values = new String[2];

    public void reset() {
        this.values[0] = null;
        this.values[1] = null;
    }

    public void append(String ncName) {
        if (this.values[0] == null) {
            this.values[0] = ncName;
        } else {
            this.values[1] = ncName;
        }
    }

    public XPathQname build() {
        if (this.values[1] == null) {
            return new XPathQname(null, this.values[0]);
        } else {
            return new XPathQname(this.values[0], this.values[1]);
        }
    }
}
