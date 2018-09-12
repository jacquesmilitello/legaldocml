package io.legaldocml.akn.type;

public interface Reference {

    boolean isRef(char[] value);
    char[] make(char[] value);

}
