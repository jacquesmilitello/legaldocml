package io.legaldocml.archive.zip;

import io.legaldocml.akn.AkomaNtoso;
import io.legaldocml.akn.DocumentType;
import io.legaldocml.archive.Archive;
import io.legaldocml.archive.ArchiveException;
import io.legaldocml.archive.Meta;
import io.legaldocml.archive.MetaResource;
import io.legaldocml.business.AknIdentifier;
import io.legaldocml.business.MediaType;
import io.legaldocml.io.impl.Buffers;
import io.legaldocml.io.XmlProvider;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.net.URI;
import java.nio.MappedByteBuffer;
import java.nio.channels.FileChannel;
import java.nio.file.FileSystem;
import java.nio.file.FileSystems;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;
import java.util.HashMap;
import java.util.HashSet;

import static io.legaldocml.archive.ArchiveException.Type.READ_EXTRACT;
import static io.legaldocml.archive.ArchiveException.Type.READ_META;
import static io.legaldocml.archive.ArchiveException.Type.READ_NOT_FOUND;
import static io.legaldocml.archive.ArchiveException.Type.READ_ONLY_MODE;
import static io.legaldocml.archive.ArchiveException.Type.READ_OPEN;

/**
 * @author <a href="mailto:jacques.militello@gmail.com">Jacques Militello</a>
 */
final class ZipArchiveReadOnly implements Archive {

    /**
     * SLF4J Logger.
     */
    private static final Logger LOGGER = LoggerFactory.getLogger(ZipArchiveReadOnly.class);

    private final FileSystem fs;

    private final ZipMeta meta;

    ZipArchiveReadOnly(Path path) {
        LOGGER.info("Open archive in Read-Only Mode [{}]", path);

        StringBuilder builder = new StringBuilder("jar:file:/");
        builder.append(path.toString().replace('\\', '/'));

        if (LOGGER.isDebugEnabled()) {
            LOGGER.debug("[{}]", builder);
        }

        try {
            fs = FileSystems.newFileSystem(new URI(builder.toString()), new HashMap<>());
        } catch (Exception cause) {
            throw new ArchiveException(READ_OPEN, cause.getMessage(), cause);
        }

        try {
            this.meta = ZipMetaXml.valueOf(fs.provider().newByteChannel(fs.getPath("/META-INF/descriptor.xml"), new HashSet<>()));
        } catch (IOException cause) {
            throw new ArchiveException(READ_META, "Failed to read Zip Meta Descriptor", cause);
        }
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public <T extends DocumentType> AkomaNtoso<T> get(AknIdentifier identifier) {

        Path source = raw(identifier);
        Path target;
        try {
            target = Paths.get(System.getProperty("java.io.tmpdir"), "zipExtract-" + System.nanoTime());
            Files.copy(source, target);
        } catch (IOException cause) {
            throw new ArchiveException(READ_EXTRACT, "Failed to get resource with AknIdentifier [" + identifier + "]", cause);
        }

        MappedByteBuffer mbb = null;
        try (FileChannel channel = FileChannel.open(target, StandardOpenOption.READ, StandardOpenOption.DELETE_ON_CLOSE)) {
            try {
                mbb = channel.map(FileChannel.MapMode.READ_ONLY, 0, channel.size());
                return XmlProvider.readerFactory().read(mbb);
            } finally {
                if (mbb != null) {
                    Buffers.releaseDirectByteBuffer(mbb);
                }
            }
        } catch (IOException cause) {
            throw new ArchiveException(READ_EXTRACT, "Failed to get resource with AknIdentifier [" + identifier + "]", cause);
        }
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Path raw(AknIdentifier identifier) {
        MetaResource resource = this.meta.get(identifier);

        if (resource == null) {
            throw new ArchiveException(READ_NOT_FOUND, "AknIdentifier [" + identifier + "] not found");
        }

        Path path = fs.getPath(resource.getMediaType().getType(), resource.getMediaType().getSubType(), resource.getName());

        if (LOGGER.isDebugEnabled()) {
            LOGGER.debug("Internal path in the Zip [{}]", path);
        }

        return path;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public <T extends DocumentType> void put(AkomaNtoso<T> akn) {
        throw new ArchiveException(READ_ONLY_MODE, "put(AkomaNtoso) in archive open in Read-Only Mode");
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void remove(AknIdentifier identifier) {
        throw new ArchiveException(READ_ONLY_MODE, "remove() in archive open in Read-Only Mode");
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void put(AknIdentifier identifier, MediaType mediaType, Path path) {
        throw new ArchiveException(READ_ONLY_MODE, "put(AknIdentifier,MediaType,ReadableByteChannel) in archive open in Read-Only Mode");
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
        fs.close();
    }


}
