package io.legaldocml;

import java.io.InputStream;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;

import org.w3c.dom.Document;

/**
 * @author <a href="mailto:jacques.militello@gmail.com">Jacques Militello</a>
 */
public final class ReaderHelper {

    private ReaderHelper() {
    }

    private static final DocumentBuilderFactory DOCUMENT_BUILDER_FACTORY = DocumentBuilderFactory.newInstance();

    static {
        DOCUMENT_BUILDER_FACTORY.setNamespaceAware(true);
    }

    public static Document load(String classpathResource) {
        return load(ReaderHelper.class.getResourceAsStream(classpathResource));
    }

    public static Document load(InputStream stream) {
        try {
            DocumentBuilder db = DOCUMENT_BUILDER_FACTORY.newDocumentBuilder();

            return db.parse(stream);
        } catch (Exception cause) {
            throw new RuntimeException(cause);
        }


    }


}
