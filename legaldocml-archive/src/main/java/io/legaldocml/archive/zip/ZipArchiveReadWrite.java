package io.legaldocml.archive.zip;

import io.legaldocml.akn.AkomaNtoso;
import io.legaldocml.akn.DocumentType;
import io.legaldocml.archive.Archive;
import io.legaldocml.archive.ArchiveException;
import io.legaldocml.archive.Meta;
import io.legaldocml.archive.MetaResource;
import io.legaldocml.business.AknIdentifier;
import io.legaldocml.business.BusinessProvider;
import io.legaldocml.business.MediaType;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.nio.file.Path;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.stream.Stream;

import static io.legaldocml.archive.ArchiveException.Type.RW_NOT_FOUND;

final class ZipArchiveReadWrite implements Archive {

    /**
     * SLF4J Logger.
     */
    private static final Logger LOGGER = LoggerFactory.getLogger(ZipArchiveReadWrite.class);

    private final BusinessProvider provider;
    private final ZipArchiveReadOnly read;
    private final ZipArchiveWriteOnly write;

    private final HashSet<AknIdentifier> doNotCopy = new HashSet<>();

    private final Map<AknIdentifier, AkomaNtoso<? extends DocumentType>> cache = new HashMap<>();

    private final ZipMetaAdapter meta;

    ZipArchiveReadWrite(BusinessProvider provider, Path source, Path target) {
        this.provider = provider;
        this.read = new ZipArchiveReadOnly(source);
        this.write = new ZipArchiveWriteOnly(provider, target);
        this.meta = new ZipMetaAdapter(this);
    }

    @Override
    public <T extends DocumentType> void put(AkomaNtoso<T> akn) {
        AknIdentifier identifier = AknIdentifier.extract(this.provider, akn);
        if (read.getMeta().exists(identifier)) {
            // source has this identifier => replace it
            doNotCopy.add(identifier);

            if (LOGGER.isDebugEnabled()) {
                LOGGER.debug("put() -> find aknIdentifier [{}] -> do not copy to target", identifier);
            }
        }

        this.write.put(akn);
        this.cache.put(identifier, akn);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void put(AknIdentifier identifier, MediaType mediaType, Path path) {
        if (read.getMeta().exists(identifier)) {
            // source has this identifier => replace it
            doNotCopy.add(identifier);

            if (LOGGER.isDebugEnabled()) {
                LOGGER.debug("put() -> find aknIdentifier [{}] and Mediatype [{}] -> do not copy to target", identifier, mediaType);
            }
        }
        this.write.put(identifier, mediaType, path);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void remove(AknIdentifier identifier) {
        if (this.read.getMeta().exists(identifier)) {
            // source has this identifier => remove it
            this.doNotCopy.add(identifier);

            if (LOGGER.isDebugEnabled()) {
                LOGGER.debug("remove() -> find aknIdentifier [{}] -> do not copy to target", identifier);
            }

            this.cache.remove(identifier);
        }
    }

    /**
     * {@inheritDoc}
     */
    @SuppressWarnings("unchecked")
    @Override
    public <T extends DocumentType> AkomaNtoso<T> get(AknIdentifier identifier) {

        AkomaNtoso<T> akn = (AkomaNtoso<T>) this.cache.get(identifier);

        if (akn != null) {
           return akn;
        }

        if (this.read.getMeta().exists(identifier)) {
            akn = this.read.get(identifier);
            this.cache.put(identifier, akn);
            return akn;
        }

        throw new ArchiveException(RW_NOT_FOUND, "AknIdentifier [" + identifier + "] not found");
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Path raw(AknIdentifier identifier) {

        if (this.read.getMeta().exists(identifier)) {
            return this.read.raw(identifier);
        }

        if (this.write.getMeta().exists(identifier)) {

        }

        return null;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Meta getMeta() {
        return this.meta;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void close() throws Exception {
        try {
            //1 -> copy all resources.
            this.read.getMeta().stream()
                    .filter(resource -> !this.doNotCopy.contains(resource.getAknIdentifier()))
                    .forEach(resource -> this.write.put(resource.getAknIdentifier(), resource.getMediaType(), this.read.raw(resource.getAknIdentifier())));
        } finally {
            try {
                // 2 -> close the ReadOnly
                this.read.close();
            } finally {
                // 3 -> close the WriteOnly
                this.write.close();
            }

        }
    }


    private static class ZipMetaAdapter implements Meta {

        private final ZipArchiveReadWrite rw;

        private ZipMetaAdapter(ZipArchiveReadWrite rw) {
            this.rw = rw;
        }

        /**
         * {@inheritDoc}
         */
        @Override
        public Stream<MetaResource> stream() {
            return Stream.concat(rw.read.getMeta().stream(), rw.write.getMeta().stream())
                    .distinct();
        }

        /**
         * {@inheritDoc}
         */
        @Override
        public boolean exists(AknIdentifier identifier) {
            if (rw.cache.containsKey(identifier)) {
                return true;
            }
            if (rw.read.getMeta().exists(identifier) && !rw.doNotCopy.contains(identifier)) {
                return true;
            }
            return rw.write.getMeta().exists(identifier);
        }
    }
}
