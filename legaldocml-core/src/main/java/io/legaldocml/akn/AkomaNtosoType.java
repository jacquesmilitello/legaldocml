package io.legaldocml.akn;

import io.legaldocml.akn.element.Components;
import io.legaldocml.akn.element.Groups;
import io.legaldocml.akn.visitor.AknVisitor;
import io.legaldocml.diff.DiffContext;
import io.legaldocml.diff.Diffs;
import io.legaldocml.io.XmlReader;
import io.legaldocml.io.XmlWriter;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.xml.stream.XMLStreamConstants;
import java.io.IOException;
import java.util.function.Supplier;

import static io.legaldocml.akn.AknElements.COMPONENTS;

/**
 * The complex type akomaNtosoType is the type for the root element in Akoma Ntoso.
 * <pre>
 *   <xsd:complexType name="akomaNtosoType">
 *     <xsd:sequence>
 *       <xsd:sequence>
 *         <xsd:group ref="documentType"/>
 *       <xsd:sequence>
 *       <xsd:element ref="components" minOccurs="0" maxOccurs="1"/>
 *     <xsd:sequence>
 *   <xsd:complexType>
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

    public final Components getComponents() {
        return components;
    }

    public final void setComponents(Components components) {
        this.components = components;
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
    @SuppressWarnings("unchecked")
    @Override
    public void read(XmlReader reader) {

        Supplier<T> supplier = (Supplier<T>) Groups.getDocumentTypes(reader).get(reader.getQName().getLocalName());

        if (supplier == null) {
            throw new RuntimeException("Document type not supported [" + reader.getQName().getLocalName() + "]");
        }

        if (LOGGER.isDebugEnabled()) {
            LOGGER.debug("supplier found : [{}]", supplier);
        }

        this.documentType = supplier.get();
        this.documentType.read(reader);

        reader.nextStartOrEndElement();

        if (reader.getEventType() != XMLStreamConstants.END_DOCUMENT && reader.getQName().equalsLocalName(COMPONENTS)) {
            this.components = new Components();
            this.components.read(reader);
            reader.nextStartOrEndElement();
        }

    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void accept(AknVisitor visitor) {
        if (this.documentType != null) {
            this.documentType.accept(visitor);
        }

        if (this.components != null) {
            this.components.accept(visitor);
        }
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void nestedCompare(AknObject object, DiffContext context) {
        Diffs.compare(documentType,((AkomaNtosoType)object).documentType, context);
        Diffs.compare(components,((AkomaNtosoType)object).components, context);
    }

}
