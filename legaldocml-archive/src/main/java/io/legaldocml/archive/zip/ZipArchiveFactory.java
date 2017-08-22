package io.legaldocml.archive.zip;

import io.legaldocml.archive.Archive;
import io.legaldocml.archive.ArchiveFactory;
import io.legaldocml.business.BusinessProvider;

import java.nio.file.Path;

/**
 * @author <a href="mailto:jacques.militello@gmail.com">Jacques Militello</a>
 */
public final class ZipArchiveFactory extends ArchiveFactory {

    /**
     * type for this ArchiveFactory.
     */
    public static final String TYPE = "zip";

    /**
     * {@inheritDoc}
     */
    @Override
    protected String type() {
        return TYPE;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    protected Archive writeOnly(BusinessProvider provider, Path path) {
        return new ZipArchiveWriteOnly(provider, path);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    protected Archive readOnly(Path path) {
        return new ZipArchiveReadOnly(path);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    protected Archive readWrite(BusinessProvider provider, Path source, Path target) {
        return new ZipArchiveReadWrite(provider, source, target);
    }

}
