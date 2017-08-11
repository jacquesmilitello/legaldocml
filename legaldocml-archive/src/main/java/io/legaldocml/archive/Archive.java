package io.legaldocml.archive;

import io.legaldocml.akn.AkomaNtoso;
import io.legaldocml.akn.DocumentType;
import io.legaldocml.business.AknIdentifier;
import io.legaldocml.business.MediaType;

import java.nio.file.Path;

/**
 * @author <a href="mailto:jacques.militello@gmail.com">Jacques Militello</a>
 */
public interface Archive extends AutoCloseable {

    <T extends DocumentType> void put(AkomaNtoso<T> akn);

    void remove(AknIdentifier identifier);

    void put(AknIdentifier identifier, MediaType mediaType, Path path);

    <T extends DocumentType> AkomaNtoso<T> get(AknIdentifier identifier);

    Path raw(AknIdentifier identifier);

    Meta getMeta();

}
