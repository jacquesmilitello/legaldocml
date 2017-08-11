package io.legaldocml.io;

import io.legaldocml.LegalDocMlException;
import io.legaldocml.akn.AkomaNtoso;
import io.legaldocml.akn.DocumentType;

import java.io.IOException;
import java.nio.channels.WritableByteChannel;
import java.util.List;

/**
 * @author <a href="mailto:jacques.militello@gmail.com">Jacques Militello</a>
 */
public interface XmlWriterFactory {

    <T extends DocumentType> void write(WritableByteChannel wbc, AkomaNtoso<T> akn) throws IOException;

    <T extends DocumentType> List<LegalDocMlException> writePermissive(WritableByteChannel wbc, AkomaNtoso<T> akn) throws IOException;

}