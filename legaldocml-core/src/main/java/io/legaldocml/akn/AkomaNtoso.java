package io.legaldocml.akn;

import io.legaldocml.akn.attribute.Core;
import io.legaldocml.akn.util.AknToStringBuilder;
import io.legaldocml.akn.visitor.AknVisitor;
import io.legaldocml.io.Attribute;
import io.legaldocml.io.XmlReader;
import io.legaldocml.io.XmlWriter;
import io.legaldocml.io.impl.Buffers;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * The element akomaNtoso is the root element of all document types in Akoma Ntoso. It follows the pattern Universal
 * Root (http://www.xmlpatterns.com/UniversalRootMain.shtml).
 *
 * <pre>
 *   &lt;xsd:element name="akomaNtoso" type="akomaNtosoType"&gt;
 * </pre>
 *
 * @author <a href="mailto:jacques.militello@gmail.com">Jacques Militello</a>
 */
public final class AkomaNtoso<T extends DocumentType> extends AkomaNtosoType<T> implements Core {

    /**
     * SLF4J Logger.
     */
    private static final Logger LOGGER = LoggerFactory.getLogger(AkomaNtoso.class);

    /**
     * Main Element name.
     */
    public static final String ELEMENT = "akomaNtoso";

    /**
     * Memory address.
     */
    private static final long ADDRESS = Buffers.address(ELEMENT);

    /**
     * Context for this instance of akomaNtoso instance.
     */
    private final AkomaNtosoContext context;

    private List<Attribute> attributes;

    public AkomaNtoso(AkomaNtosoContext context) {
        this.context = context;
    }

    public AkomaNtosoContext getContext() {
        return context;
    }

    /**
     * {@inheritDoc}
     */
    public void add(Attribute attribute) {
        if (this.attributes == null) {
            this.attributes = new ArrayList<>();
        }
        this.attributes.add(attribute);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void write(XmlWriter writer) throws IOException {
        writer.writeStartDocument(ADDRESS, 10);
        // write namespaces
        this.context.writeModules(writer);
        // write external attributes
        if (this.attributes != null) {
            for (Attribute attribute : this.attributes) {
                attribute.write(writer);
            }
        }
        super.write(writer);
        writer.writeEndDocument(ADDRESS, 10);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void read(XmlReader reader) {
        //Attributes.read(reader, this);
        //reader.nextStartOrEndElement();
        super.read(reader);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public String name() {
        return ELEMENT;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void accept(AknVisitor visitor) {
        if (visitor.visitEnter(this)) {
            super.accept(visitor);
            visitor.visitLeave(this);
        }
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public String toString() {
        AknToStringBuilder builder = new AknToStringBuilder(this);
        builder.append(this.attributes);
        return builder.toString();
    }
}
