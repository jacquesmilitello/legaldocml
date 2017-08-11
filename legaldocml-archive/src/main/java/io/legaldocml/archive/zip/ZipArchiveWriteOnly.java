package io.legaldocml.archive.zip;

import com.google.common.collect.ImmutableMap;
import com.google.common.collect.ImmutableSet;
import io.legaldocml.akn.AkomaNtoso;
import io.legaldocml.akn.DocumentType;
import io.legaldocml.archive.Archive;
import io.legaldocml.archive.ArchiveException;
import io.legaldocml.archive.Meta;
import io.legaldocml.business.AknIdentifier;
import io.legaldocml.business.BusinessProvider;
import io.legaldocml.business.MediaType;
import io.legaldocml.io.XmlProvider;
import io.legaldocml.io.XmlWriterFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.net.URI;
import java.nio.channels.FileChannel;
import java.nio.file.FileSystem;
import java.nio.file.FileSystems;
import java.nio.file.Files;
import java.nio.file.OpenOption;
import java.nio.file.Path;
import java.nio.file.StandardOpenOption;
import java.util.HashSet;

import static io.legaldocml.archive.ArchiveException.Type.WRITE_CREATE_DIRECTORIES;
import static io.legaldocml.archive.ArchiveException.Type.WRITE_ONLY_MODE;
import static io.legaldocml.archive.ArchiveException.Type.WRITE_PUT;
import static io.legaldocml.archive.ArchiveException.Type.WRITE_PUT_AKN;
import static io.legaldocml.business.MediaType.LEGALDOCML;

/**
 * @author <a href="mailto:jacques.militello@gmail.com">Jacques Militello</a>
 */
final class ZipArchiveWriteOnly implements Archive {

    private static final Logger LOGGER = LoggerFactory.getLogger(ZipArchiveWriteOnly.class);

    private static final ImmutableMap<String, String> ENV;

    private static final ImmutableSet<OpenOption> CREATE;

    static {
        // http://docs.oracle.com/javase/8/docs/technotes/guides/io/fsp/zipfilesystemproviderprops.html
        ENV = ImmutableMap.<String, String>builder()
                //The value should be of type java.lang.String. The default value is false. If the value is true,
                // the zip file system provider creates a new zip file if it does not exist.
                .put("create", "true")
                .build();

        CREATE = ImmutableSet.of(StandardOpenOption.CREATE_NEW, StandardOpenOption.WRITE);
    }

    private final FileSystem fs;

    private final ZipMeta meta = new ZipMeta();

    ZipArchiveWriteOnly(Path path) {
        try {
            fs = FileSystems.newFileSystem(new URI("jar:file:/" + path.toString().replace('\\', '/')), ENV);
        } catch (Exception cause) {
            throw new ArchiveException(ArchiveException.Type.WRITE_OPEN, cause.getMessage());
        }

        LOGGER.info("Create archive in [{}]", path);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public <T extends DocumentType> void put(AkomaNtoso<T> akn) {

        AknIdentifier identifier;
        if (AknIdentifier.isEmpty(akn)) {
            if (LOGGER.isDebugEnabled()) {
                LOGGER.debug("ZipArchiveWriteOnly.add() => akn with empty identifier => create a transient identifier");
            }
            identifier = BusinessProvider.newAknIdentifierTransient();
            identifier.apply(akn);
        } else {
            identifier = AknIdentifier.extract(akn);
        }

        String name = this.meta.add(LEGALDOCML, identifier);

        if (LOGGER.isDebugEnabled()) {
            LOGGER.debug("put AKN resource with aknIdentifier [{}] to [{}]", identifier, fs.getPath(name));
        }

        Path directory = fs.getPath(LEGALDOCML.getType(), LEGALDOCML.getSubType());
        checkAndCreateDir(directory);

        try (FileChannel fc = fs.provider().newFileChannel(directory.resolve(name), new HashSet<>(CREATE))) {
            XmlWriterFactory writerFactory = XmlProvider.writerFactory(akn.getContext().getAkoXmlModule().getVersion());
            writerFactory.write(fc, akn);
        } catch (IOException cause) {
            throw new ArchiveException(WRITE_PUT_AKN, "Failed to add akn with identifier [" + identifier + "]", cause);
        }

    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void put(AknIdentifier identifier, MediaType mediaType, Path path) {

        String name = this.meta.add(mediaType, identifier);

        if (LOGGER.isDebugEnabled()) {
            LOGGER.debug("put resource with aknIdentifier [{}] to [{}]", identifier, fs.getPath(name));
        }

        Path directory = fs.getPath(mediaType.getType(), mediaType.getSubType());
        checkAndCreateDir(directory);

        try {
            Files.copy(path, directory.resolve(name));
        } catch (IOException cause) {
            throw new ArchiveException(WRITE_PUT, "Failed to add akn with identifier [" + identifier + "]", cause);
        }

    }

    /**
     * {@inheritDoc}
     */
    @Override
    public <T extends DocumentType> AkomaNtoso<T> get(AknIdentifier identifier) {
        throw new ArchiveException(WRITE_ONLY_MODE, "get(AknIdentifier) in archive open in Write-Only Mode");
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Path raw(AknIdentifier identifier) {
        throw new ArchiveException(WRITE_ONLY_MODE, "raw(AknIdentifier) in archive open in Write-Only Mode");
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void remove(AknIdentifier identifier) {
        throw new ArchiveException(WRITE_ONLY_MODE, "remove(AknIdentifier) in archive open in Write-Only Mode");
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
        writeMeta();
        fs.close();
    }

    private void writeMeta() {
        checkAndCreateDir(fs.getPath("/META-INF"));
        try (FileChannel fc = fs.provider().newFileChannel(fs.getPath("/META-INF", "descriptor.xml"), new HashSet<>(CREATE))) {
            ZipMetaXml.write(fc, this.meta);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void checkAndCreateDir(Path directory) {
        if (!Files.exists(directory)) {
            try {
                Files.createDirectories(directory);
            } catch (IOException cause) {
                throw new ArchiveException(WRITE_CREATE_DIRECTORIES, "Failed to create directory [" + directory + "] in ZipArchiveWriteOnly [" + this + "]", cause);
            }
        }
    }

}
