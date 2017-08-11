package io.legaldocml.akn.type;

/**
 * @author <a href="mailto:jacques.militello@gmail.com">Jacques Militello</a>
 */
public class ListReferenceRef {

    private char[] value;

    public ListReferenceRef(char[] value) {
        this.value = value;
    }

    public char[] getChars() {
        return this.value;
    }
}
