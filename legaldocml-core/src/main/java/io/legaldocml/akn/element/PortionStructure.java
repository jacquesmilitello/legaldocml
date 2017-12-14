package io.legaldocml.akn.element;

import com.google.common.collect.ImmutableMap;
import io.legaldocml.akn.AknObject;
import io.legaldocml.akn.attribute.PortionAtt;
import io.legaldocml.akn.type.ReferenceRef;
import io.legaldocml.akn.visitor.AknVisitor;
import io.legaldocml.diff.DiffContext;
import io.legaldocml.diff.Diffs;
import io.legaldocml.io.AttributeGetterSetter;
import io.legaldocml.io.XmlReader;
import io.legaldocml.io.XmlWriter;

import java.io.IOException;

import static io.legaldocml.akn.AknAttributes.INCLUDED_IN;
import static io.legaldocml.akn.element.Attributes.attributeGetterSetter4ReferenceRef;
import static io.legaldocml.akn.util.XmlWriterHelper.writePortionAtt;
import static io.legaldocml.unsafe.UnsafeHelper.getFieldOffset;

/**
 * The type portionStructure specifies the overall content model of the document type that is a portion of another
 * document.
 * <p>
 * <pre>
 *   <xsd:complexType name="portionStructure">
 *     <xsd:sequence>
 * 	     <xsd:element ref="meta"/>
 * 		 <xsd:element ref="portionBody"/>
 *     </xsd:sequence>
 * 	   <xsd:attributeGroup ref="portionAtt"/>
 *   </xsd:complexType>
 * </pre>
 *
 * @author <a href="mailto:jacques.militello@gmail.com">Jacques Militello</a>
 */
public abstract class PortionStructure implements PortionAtt {


    protected static final ImmutableMap<String, AttributeGetterSetter<AknObject>> ATTRIBUTES;

    static {
        ATTRIBUTES = ImmutableMap.<String, AttributeGetterSetter<AknObject>>builder()
                .put(INCLUDED_IN, attributeGetterSetter4ReferenceRef(INCLUDED_IN, getFieldOffset(PortionStructure.class, "referenceRef")))
                .build();
    }

    // Mandatory
    private final Meta meta = new Meta();

    // Mandatory
    private final PortionBody portionBody = new PortionBody();

    private ReferenceRef referenceRef;

    public final Meta getMeta() {
        return this.meta;
    }

    public final PortionBody getPortionBody() {
        return this.portionBody;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public final ReferenceRef getIncludedIn() {
        return this.referenceRef;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public final void setIncludedIn(ReferenceRef referenceRef) {
        this.referenceRef = referenceRef;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void write(XmlWriter writer) throws IOException {
        writePortionAtt(writer, this);
        this.meta.write(writer);
        this.portionBody.write(writer);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void read(XmlReader reader) {
        Attributes.read(reader, this);
        reader.nextStartOrEndElement();
        this.meta.read(reader);
        this.portionBody.read(reader);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public ImmutableMap<String, AttributeGetterSetter<AknObject>> attributes() {
        return ATTRIBUTES;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void accept(AknVisitor visitor) {
        this.meta.accept(visitor);
        this.portionBody.accept(visitor);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void nestedCompare(AknObject object, DiffContext context) {
        Diffs.compare(meta, ((PortionStructure) object).meta, context);
        Diffs.compare(portionBody, ((PortionStructure) object).portionBody, context);

        //Diffs.compareAttribute(referenceRef,)

    }

}