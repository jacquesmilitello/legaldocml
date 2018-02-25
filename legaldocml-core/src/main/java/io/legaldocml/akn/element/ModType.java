package io.legaldocml.akn.element;

import com.google.common.collect.ImmutableMap;
import io.legaldocml.akn.AknAttributes;
import io.legaldocml.akn.AknObject;
import io.legaldocml.akn.attribute.CoreReq;
import io.legaldocml.akn.attribute.For;
import io.legaldocml.akn.type.EidRef;
import io.legaldocml.akn.util.AknList;
import io.legaldocml.akn.util.XmlReaderHelper;
import io.legaldocml.akn.util.XmlWriterHelper;
import io.legaldocml.akn.visitor.AknVisitor;
import io.legaldocml.io.AttributeGetterSetter;
import io.legaldocml.io.XmlReader;
import io.legaldocml.io.XmlWriter;

import java.io.IOException;
import java.util.function.Supplier;

import static io.legaldocml.akn.AknElements.QUOTED_STRUCTURE;
import static io.legaldocml.akn.AknElements.QUOTED_TEXT;
import static io.legaldocml.akn.element.Attributes.attributeGetterSetter4EidRef;
import static io.legaldocml.akn.element.Groups.convertSuper;
import static io.legaldocml.akn.element.Groups.inlineCM;
import static io.legaldocml.unsafe.UnsafeHelper.getFieldOffset;
import static java.util.Objects.requireNonNull;

/**
 * the complex type modType specifies the content that is allowed within mod, mmod and rmod elements, i.e. it adds
 * quotedText and quotedStructure to the normal list of inline elements. Attribute for is used to point to the eId of
 * the corresponding ref element.
 * <p>
 * <pre>
 *   <xsd:complexType name="modType" mixed="true">
 *     <xsd:choice minOccurs="0" maxOccurs="unbounded">
 * 	     <xsd:group ref="inlineCM" />
 * 		 <xsd:element ref="quotedText" />
 * 		 <xsd:element ref="quotedStructure" />
 * 	   <xsd:choice>
 * 	   <xsd:attributeGroup ref="corereq" />
 * 	   <xsd:attributeGroup ref="for"/>
 *   <xsd:complexType>
 * </pre>
 *
 * @author <a href="mailto:jacques.militello@gmail.com">Jacques Militello</a>
 */
public abstract class ModType extends AbstractCore implements CoreReq, For {

    private static final ImmutableMap<String, Supplier<ModTypeItem>> ELEMS_V2;
    private static final ImmutableMap<String, Supplier<ModTypeItem>> ELEMS;

    protected static final ImmutableMap<String, AttributeGetterSetter<AknObject>> ATTRIBUTES;

    static {
        ATTRIBUTES = ImmutableMap.<String, AttributeGetterSetter<AknObject>>builder()
                .putAll(AbstractCore.ATTRIBUTES)
                .put(AknAttributes.FOR, attributeGetterSetter4EidRef(AknAttributes.FOR, getFieldOffset(ModType.class, "for_")))
                .build();

        ELEMS_V2 = ImmutableMap.<String, Supplier<ModTypeItem>>builder()
                .putAll(convertSuper(inlineCM()))
                .put(QUOTED_STRUCTURE, QuotedStructureV2::new)
                .put(QUOTED_TEXT, QuotedText::new)
                .build();

        ELEMS = ImmutableMap.<String, Supplier<ModTypeItem>>builder()
                .putAll(convertSuper(inlineCM()))
                .put(QUOTED_STRUCTURE, QuotedStructureV3::new)
                .put(QUOTED_TEXT, QuotedText::new)
                .build();
    }

    private final AknList<ModTypeItem> elements = new AknList<>(new ModTypeItem[4]);

    private EidRef for_;

    public final void add(ModTypeItem item) {
        this.elements.add(requireNonNull(item));
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public EidRef getFor() {
        return this.for_;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void setFor(EidRef for_) {
        this.for_ = for_;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void read(XmlReader reader) {
        super.read(reader);
        if (reader.getContext().getAknModule().getVersion() == 2) {
            XmlReaderHelper.readWithCharacters(reader, this.elements, ELEMS_V2);
        } else {
            XmlReaderHelper.readWithCharacters(reader, this.elements, ELEMS);
        }
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void write(XmlWriter writer) throws IOException {
        CoreReq.super.write(writer);
        XmlWriterHelper.writeFor(writer, this);
        this.elements.write(writer);
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
        this.elements.accept(visitor);
    }
}