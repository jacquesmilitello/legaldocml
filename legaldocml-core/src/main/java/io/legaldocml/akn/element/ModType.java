package io.legaldocml.akn.element;

import com.google.common.collect.ImmutableMap;
import io.legaldocml.akn.AknObject;
import io.legaldocml.akn.attribute.For;
import io.legaldocml.akn.type.EidRef;
import io.legaldocml.akn.util.AknList;
import io.legaldocml.akn.util.XmlReaderHelper;
import io.legaldocml.akn.util.XmlWriterHelper;
import io.legaldocml.io.CharArray;
import io.legaldocml.io.XmlReader;
import io.legaldocml.io.XmlWriter;

import java.io.IOException;
import java.util.function.BiConsumer;
import java.util.function.Supplier;

import static io.legaldocml.akn.element.Attributes.biConsumerEidRef;
import static io.legaldocml.akn.element.Groups.convertSuper;
import static io.legaldocml.akn.element.Groups.inlineCM;
import static io.legaldocml.unsafe.UnsafeHelper.getFieldOffset;

/**
 * the complex type modType specifies the content that is allowed within mod, mmod and rmod elements, i.e. it adds
 * quotedText and quotedStructure to the normal list of inline elements. Attribute for is used to point to the eId of
 * the corresponding ref element.
 *
 * <pre>
 *   &lt;xsd:complexType name="modType" mixed="true"&gt;
 *     &lt;xsd:choice minOccurs="0" maxOccurs="unbounded"&gt;
 * 	     &lt;xsd:group ref="inlineCM" /&gt;
 * 		 &lt;xsd:element ref="quotedText" /&gt;
 * 		 &lt;xsd:element ref="quotedStructure" /&gt;
 * 	   &lt;xsd:choice&gt;
 * 	   &lt;xsd:attributeGroup ref="corereq" /&gt;
 * 	   &lt;xsd:attributeGroup ref="for"/&gt;
 *   &lt;xsd:complexType&gt;
 * </pre>
 *
 * @author <a href="mailto:jacques.militello@gmail.com">Jacques Militello</a>
 */
public abstract class ModType extends CoreReqImpl implements For {

    private final AknList<ModTypeItem> elements = new AknList<>(new ModTypeItem[4]);

    private static final ImmutableMap<String, Supplier<ModTypeItem>> ELEMS_V2;
    private static final ImmutableMap<String, Supplier<ModTypeItem>> ELEMS;

    protected static final ImmutableMap<String, BiConsumer<AknObject, CharArray>> ATTRIBUTES;

    static {
        ATTRIBUTES = ImmutableMap.<String, BiConsumer<AknObject, CharArray>>builder()
                .putAll(CoreReqImpl.ATTRIBUTES)
                .put(For.ATTRIBUTE, biConsumerEidRef(getFieldOffset(ModType.class, "for_")))
                .build();

        ELEMS_V2 = ImmutableMap.<String, Supplier<ModTypeItem>>builder()
                .putAll(convertSuper(inlineCM()))
                .put(QuotedStructure.ELEMENT, QuotedStructureV2::new)
                .put(QuotedText.ELEMENT, QuotedText::new)
                .build();

        ELEMS = ImmutableMap.<String, Supplier<ModTypeItem>>builder()
                .putAll(convertSuper(inlineCM()))
                .put(QuotedStructure.ELEMENT, QuotedStructureV3::new)
                .put(QuotedText.ELEMENT, QuotedText::new)
                .build();
    }

    private EidRef for_;

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
        if (reader.getContext().getAkoXmlModule().getVersion() == 2) {
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
        super.write(writer);
        XmlWriterHelper.writeFor(writer, this);
        this.elements.write(writer);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public ImmutableMap<String, BiConsumer<AknObject, CharArray>> attributes() {
        return ATTRIBUTES;
    }
}