package io.legaldocml.akn.element;

import com.google.common.collect.ImmutableMap;
import io.legaldocml.akn.AknObject;
import io.legaldocml.akn.attribute.Modifiers;
import io.legaldocml.akn.attribute.Pos;
import io.legaldocml.akn.attribute.UpToOpt;
import io.legaldocml.akn.type.EidRef;
import io.legaldocml.akn.type.PosType;
import io.legaldocml.io.AttributeGetterSetter;
import io.legaldocml.io.XmlWriter;

import java.io.IOException;

import static io.legaldocml.akn.AknAttributes.EXCLUSION;
import static io.legaldocml.akn.AknAttributes.INCOMPLETE;
import static io.legaldocml.akn.AknAttributes.POS;
import static io.legaldocml.akn.AknAttributes.UP_TO;
import static io.legaldocml.akn.element.Attributes.attributeGetterSetter4EidRef;
import static io.legaldocml.akn.element.Attributes.attributeGetterSetter4Enum;
import static io.legaldocml.akn.util.XmlWriterHelper.writeModifiers;
import static io.legaldocml.akn.util.XmlWriterHelper.writePos;
import static io.legaldocml.akn.util.XmlWriterHelper.writeUpTo;
import static io.legaldocml.unsafe.UnsafeHelper.getFieldOffset;

/**
 * The complex type argumentType defines the empty content model and the list of attributes for metadata elements in the
 * analysis section.
 *
 * <pre>
 *   <xsd:complexType name="argumentType">
 *     <xsd:complexContent>
 *       <xsd:extension base="anyOtherType">
 *         <xsd:attributeGroup ref="pos"/>
 *         <xsd:attributeGroup ref="modifiers"/>
 *         <xsd:attributeGroup ref="upToOpt"/>
 *       <xsd:extension>
 *     <xsd:complexContent>
 *   <xsd:complexType>
 * </pre>
 *
 * @author <a href="mailto:jacques.militello@gmail.com">Jacques Militello</a>
 */
public abstract class ArgumentType extends AnyOtherType implements Pos, Modifiers, UpToOpt {

    protected static final ImmutableMap<String, AttributeGetterSetter<AknObject>> ATTRIBUTES;

    static {

        ATTRIBUTES = ImmutableMap.<String, AttributeGetterSetter<AknObject>>builder()
                .putAll(AnyOtherType.ATTRIBUTES)
                .put(POS, attributeGetterSetter4Enum(POS, getFieldOffset(ArgumentType.class, "pos"), PosType.class))
                .put(UP_TO, attributeGetterSetter4EidRef(UP_TO, getFieldOffset(ArgumentType.class, "upTo")))
                .put(EXCLUSION, attributeGetterSetter4EidRef(EXCLUSION, getFieldOffset(ArgumentType.class, "exclusion")))
                .put(INCOMPLETE, attributeGetterSetter4EidRef(INCOMPLETE, getFieldOffset(ArgumentType.class, "incomplete")))
                .build();
    }

    // Pos
    private PosType pos;

    private EidRef upTo;

    private Boolean exclusion;

    private Boolean incomplete;

    private AknObject parent;

    /**
     * {@inheritDoc}
     */
    public final PosType getPos() {
        return this.pos;
    }

    /**
     * {@inheritDoc}
     */
    public final void setPos(PosType posType) {
        this.pos = posType;
    }

    public final EidRef getUpTo() {
        return this.upTo;
    }

    public final void setUpTo(EidRef upTo) {
        this.upTo = upTo;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Boolean getExclusion() {
        return this.exclusion;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void setExclusion(Boolean exclusion) {
        this.exclusion = exclusion;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Boolean getIncomplete() {
        return this.incomplete;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void setIncomplete(Boolean incomplete) {
        this.incomplete = incomplete;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void write(XmlWriter writer) throws IOException {
        super.write(writer);
        writeUpTo(writer, this);
        writePos(writer, this);
        writeModifiers(writer, this);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public ImmutableMap<String, AttributeGetterSetter<AknObject>> attributes() {
        return ATTRIBUTES;
    }

    @SuppressWarnings("unchecked")
    public <T extends AknObject> T getParent() {
        return (T)parent;
    }

    public <T extends AknObject> void setParent(T parent) {
        this.parent = parent;
    }

}