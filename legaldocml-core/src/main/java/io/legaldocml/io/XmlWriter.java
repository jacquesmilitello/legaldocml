package io.legaldocml.io;

import io.legaldocml.LegalDocMlException;

import java.io.IOException;
import java.time.LocalDate;
import java.time.OffsetDateTime;

/**
 * @author <a href="mailto:jacques.militello@gmail.com">Jacques Militello</a>
 */
public interface XmlWriter {

    void writeStartDocument(long address, int len);

    void writeNamespace(long key, int keyLen, long value, int valueLen) throws IOException;

    void writeEndDocument(long address, int len) throws IOException;

	void writeStart(long address, int len) throws IOException;

	void writeEnd(long address, int len) throws IOException;

    void writeAttribute(long name, int nameLen, char[] value) throws IOException;

    void writeAttribute(long name, int nameLen, byte[] value) throws IOException;

    void writeAttribute(long name, int nameLen, LocalDate localDate) throws IOException;

    void writeAttribute(long name, int nameLen, OffsetDateTime offsetDateTime) throws IOException;

    void write(char[] chars) throws IOException;

    int getVersion();

    void setPermissive(boolean value);

    boolean isPermissive();

    void addExpcetion(LegalDocMlException exception);
}