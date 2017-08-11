package io.legaldocml.util;

/**
 * @author <a href="mailto:jacques.militello@gmail.com">Jacques Militello</a>
 */
public abstract class HashReader {

    public abstract long length();

    public abstract long getLong();

    public abstract int getInt();

    public abstract long getByte();

}