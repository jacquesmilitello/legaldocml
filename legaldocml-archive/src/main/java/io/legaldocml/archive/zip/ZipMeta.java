package io.legaldocml.archive.zip;

import io.legaldocml.archive.ArchiveException;
import io.legaldocml.archive.Meta;
import io.legaldocml.archive.MetaResource;
import io.legaldocml.business.AknIdentifier;
import io.legaldocml.business.MediaType;
import io.legaldocml.util.Hashing;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.LinkedHashMap;
import java.util.Map;
import java.util.stream.Stream;

import static io.legaldocml.archive.ArchiveException.Type.WRITE_ALREADY_EXISTS;

/**
 * @author <a href="mailto:jacques.militello@gmail.com">Jacques Militello</a>
 */
final class ZipMeta implements Meta {

    /**
     * SLF4J Logger.
     */
    private static final Logger LOGGER = LoggerFactory.getLogger(ZipMeta.class);

    /**
     * To hold all resources.
     */
    private final Map<AknIdentifier, MetaResource> resources = new LinkedHashMap<>();


    String add(MediaType mediaType, AknIdentifier identifier) {

        MetaResource resource = resources.get(identifier);

        if (resource != null) {
            throw new ArchiveException(WRITE_ALREADY_EXISTS, "Resource with AknIdenfier [" + identifier + "] already exists in the ZipArchiveWriteOnly");
        }

        StringBuilder builder = new StringBuilder(128);
        builder.append(Hashing.xx(0, identifier.toString()));

        if (LOGGER.isDebugEnabled()) {
            LOGGER.debug("add([{}]->[{}])", identifier, builder);
        }

        this.resources.put(identifier, new ZipMetaResource(identifier, mediaType, builder.toString()));

        return builder.toString();
    }


    MetaResource get(AknIdentifier identifier) {
        return this.resources.get(identifier);
    }

    Map<AknIdentifier, MetaResource> getResources() {
        return resources;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Stream<MetaResource> stream() {
        return resources.values().stream();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public boolean exists(AknIdentifier identifier) {
        return this.resources.containsKey(identifier);
    }
}
