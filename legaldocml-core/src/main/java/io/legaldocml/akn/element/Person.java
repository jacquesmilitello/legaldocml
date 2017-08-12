package io.legaldocml.akn.element;

import com.google.common.collect.ImmutableMap;
import io.legaldocml.akn.AknObject;
import io.legaldocml.akn.attribute.Role;
import io.legaldocml.akn.group.ANsemanticInline;
import io.legaldocml.akn.type.RoleRef;
import io.legaldocml.akn.util.XmlWriterHelper;
import io.legaldocml.io.impl.Buffers;
import io.legaldocml.io.CharArray;
import io.legaldocml.io.XmlWriter;

import java.io.IOException;
import java.util.function.BiConsumer;

import static io.legaldocml.akn.element.Attributes.biConsumerRoleRef;
import static io.legaldocml.unsafe.UnsafeHelper.getFieldOffset;

/**
 * The element person is an inline element to identify a text fragment introducing or referring to a person in the
 * ontology. Attribute as allows to specify a TLCrole the person is holding in the context of the document's mention.
 *
 * <pre>
 *   &lt;xsd:element name="person"&gt;
 * 	   &lt;xsd:complexType mixed="true"&gt;
 * 	     &lt;xsd:complexContent&gt;
 * 		   &lt;xsd:extension base="inlinereqreq"&gt;
 * 		     &lt;xsd:attributeGroup ref="role"/&gt;
 * 		   &lt;xsd:extension&gt;
 * 		 &lt;xsd:complexContent&gt;
 * 	   &lt;xsd:complexType&gt;
 *   &lt;xsd:element&gt;
 * </pre>
 *
 * @author <a href="mailto:jacques.militello@gmail.com">Jacques Militello</a>
 */
public final class Person extends InlineReqReqType implements Role, ANsemanticInline {

    /**
     * XML tag element name.
     */
    public static final String ELEMENT = "person";

    /**
     * Memory address.
     */
    private static final long ADDRESS = Buffers.address(ELEMENT);

    private static final ImmutableMap<String, BiConsumer<AknObject, CharArray>> ATTRIBUTES;

    static {
        ATTRIBUTES = ImmutableMap.<String, BiConsumer<AknObject, CharArray>>builder()
                .putAll(InlineReqReqType.ATTRIBUTES)
                .put(Role.ATTRIBUTE, biConsumerRoleRef(getFieldOffset(Person.class, "as")))
                .build();
    }

    private RoleRef as;

    /**
     * {@inheritDoc}
     */
    @Override
    public RoleRef getAs() {
        return this.as;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void setAs(RoleRef as) {
        this.as = as;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void write(XmlWriter writer) throws IOException {
        writer.writeStart(ADDRESS, 6);
        XmlWriterHelper.writeRole(writer, this);
        super.write(writer);
        writer.writeEnd(ADDRESS, 6);
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
    public ImmutableMap<String, BiConsumer<AknObject, CharArray>> attributes() {
        return ATTRIBUTES;
    }
}