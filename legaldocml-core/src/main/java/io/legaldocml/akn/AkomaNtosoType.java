package io.legaldocml.akn;

import io.legaldocml.akn.element.Components;
import io.legaldocml.akn.element.Groups;
import io.legaldocml.akn.visitor.AknVisitor;
import io.legaldocml.io.XmlReader;
import io.legaldocml.io.XmlWriter;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.xml.stream.XMLStreamConstants;
import java.io.IOException;
import java.util.function.Supplier;

/**
 * The complex type akomaNtosoType is the type for the root element in Akoma Ntoso.
 * <pre>
 *   &lt;xsd:complexType name="akomaNtosoType"&gt;
 *     &lt;xsd:sequence&gt;
 *       &lt;xsd:sequence&gt;
 *         &lt;xsd:group ref="documentType"/&gt;
 *       &lt;xsd:sequence&gt;
 *       &lt;xsd:element ref="components" minOccurs="0" maxOccurs="1"/&gt;
 *     &lt;xsd:sequence&gt;
 *   &lt;xsd:complexType&gt;
 * </pre>
 *
 * @author <a href="mailto:jacques.militello@gmail.com">Jacques Militello</a>
 */
public abstract class AkomaNtosoType<T extends DocumentType> implements AknObject {

    /**
     * SLF4J Logger.
     */
    private static final Logger LOGGER = LoggerFactory.getLogger(AkomaNtosoType.class);

    // Mandatory
    private T documentType;

    // Optional
    private Components components;

    public final T getDocumentType() {
        return this.documentType;
    }

    public final void setDocumentType(T documentType) {
        this.documentType = documentType;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void write(XmlWriter writer) throws IOException {
        this.documentType.write(writer);
        if (this.components != null) {
            this.components.write(writer);
        }
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void read(XmlReader reader) {

        Supplier<DocumentType> supplier = Groups.getDocumentTypes(reader).get(reader.getQName().getLocalName());

        if (supplier == null) {
            throw new RuntimeException("Document type not supported [" + reader.getQName().getLocalName() + "]");
        }

        if (LOGGER.isDebugEnabled()) {
            LOGGER.debug("supplier found : [{}]", supplier);
        }

        this.documentType = (T) supplier.get();
        this.documentType.read(reader);

        reader.nextStartOrEndElement();

        if (reader.getEventType() != XMLStreamConstants.END_DOCUMENT && reader.getQName().equalsLocalName(Components.ELEMENT)) {
            this.components = new Components();
            this.components.read(reader);
            reader.nextStartOrEndElement();
        }

    }

    /**
     * {@inheritDoc}
     */
    public void accept(AknVisitor visitor) {
        if (this.documentType != null) {
            this.documentType.accept(visitor);
        }

        if (this.components != null) {
            this.components.accept(visitor);
        }
    }
}
