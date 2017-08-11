package io.legaldocml.mod.xml.type;

public enum Space {

    DEFAULT("default"), PRESERVE("preserve");

    private String value;

    private Space(String value) {
        this.value = value;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public String toString() {
        return this.value;
    }
}
