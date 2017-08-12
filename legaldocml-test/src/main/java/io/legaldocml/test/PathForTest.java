package io.legaldocml.test;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.io.InputStream;
import java.net.URI;
import java.net.URISyntaxException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;

public class PathForTest {

    private static final Logger LOGGER = LoggerFactory.getLogger(PathForTest.class);

    private PathForTest(){}

    public static Path path(String resource) throws IOException {

        LOGGER.info("Read File [{}]", resource);

        URI uri;
        try {
            uri = PathForTest.class.getResource(resource).toURI();
        } catch (URISyntaxException e) {
            throw new RuntimeException();
        }

        Path path;
        if (uri.toString().startsWith("jar:")) {
            path = Paths.get(System.getProperty("java.io.tmpdir"), "test.xml");
            try (InputStream is = PathForTest.class.getResourceAsStream(resource)) {
                Files.copy(is, path, StandardCopyOption.REPLACE_EXISTING);
            }
        } else {
            path = Paths.get(uri);
        }

        return path;

    }
}
