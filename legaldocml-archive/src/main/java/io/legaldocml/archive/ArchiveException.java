package io.legaldocml.archive;

/**
 * @author <a href="mailto:jacques.militello@gmail.com">Jacques Militello</a>
 */
public final class ArchiveException extends RuntimeException {

    public enum Type {

        /**
         * for Read operations
         */
        READ_OPEN, READ_META, READ_NOT_FOUND, READ_EXTRACT, READ_ONLY_MODE,

        /**
         * for Write operations
         */
        WRITE_OPEN, WRITE_PUT_AKN, WRITE_PUT, WRITE_ONLY_MODE, WRITE_CREATE_DIRECTORIES, WRITE_ALREADY_EXISTS,

        /**
         * for ReadWrite operations
         */
        RW_NOT_FOUND,

        /**
         * for Meta operations
         */
        META_WRITE, META_READ
    }

    private final Type type;

    public ArchiveException(Type type, String msg) {
        super(msg);
        this.type = type;
    }

    public ArchiveException(Type type, String msg, Exception cause) {
        super(msg, cause);
        this.type = type;
    }

    public Type getType() {
        return this.type;
    }

}
