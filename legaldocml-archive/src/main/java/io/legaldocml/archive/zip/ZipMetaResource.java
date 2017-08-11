package io.legaldocml.archive.zip;

import io.legaldocml.archive.MetaResource;
import io.legaldocml.business.AknIdentifier;
import io.legaldocml.business.MediaType;
import io.legaldocml.util.ToStringBuilder;

/**
 * @author <a href="mailto:jacques.militello@gmail.com">Jacques Militello</a>
 */
final class ZipMetaResource implements MetaResource {

    private AknIdentifier aknIdentifier;
    private String name;
    private MediaType mediaType;

    public ZipMetaResource(AknIdentifier identifier, MediaType mediaType, String name) {
        this.aknIdentifier = identifier;
        this.mediaType = mediaType;
        this.name = name;
    }

    public AknIdentifier getAknIdentifier() {
        return aknIdentifier;
    }

    public String getName() {
        return name;
    }

    public MediaType getMediaType() {
        return mediaType;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public String toString() {
        return new ToStringBuilder(this)
                .append("aknIdentifier", aknIdentifier)
                .append("mediaType", mediaType)
                .append("name", name)
                .toString();
    }

}