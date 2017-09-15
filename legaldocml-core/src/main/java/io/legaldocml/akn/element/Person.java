package io.legaldocml.akn.element;

import com.google.common.collect.ImmutableMap;
import io.legaldocml.akn.AknAttributes;
import io.legaldocml.akn.attribute.Role;
import io.legaldocml.akn.group.ANsemanticInline;
import io.legaldocml.akn.type.RoleRef;
import io.legaldocml.akn.util.XmlWriterHelper;
import io.legaldocml.io.CharArray;
import io.legaldocml.io.Externalizable;
import io.legaldocml.io.XmlWriter;
import io.legaldocml.io.impl.Buffers;

import java.io.IOException;
import java.util.function.BiConsumer;

import static io.legaldocml.akn.AknElements.PERSON;
import static io.legaldocml.akn.element.Attributes.biConsumerRoleRef;
import static io.legaldocml.unsafe.UnsafeHelper.getFieldOffset;

/**
 * The element person is an inline element to identify a text fragment introducing or referring to a person in the
 * ontology. Attribute as allows to specify a TLCrole the person is holding in the context of the document's mention.
 *
 * <pre>
 *   <xsd:element name="person">
 * 	   <xsd:complexType mixed="true">
 * 	     <xsd:complexContent>
 * 		   <xsd:extension base="inlinereqreq">
 * 		     <xsd:attributeGroup ref="role"/>
 * 		   <xsd:extension>
 * 		 <xsd:complexContent>
 * 	   <xsd:complexType>
 *   <xsd:element>
 * </pre>
 *
 * @author <a href="mailto:jacques.militello@gmail.com">Jacques Militello</a>
 */
public final class Person extends InlineReqReqType implements Role, ANsemanticInline {

    /**
     * Memory address.
     */
    private static final long ADDRESS_PERSON = Buffers.address(PERSON);

    private static final ImmutableMap<String, BiConsumer<Externalizable, CharArray>> ATTRIBUTES;

    static {
        ATTRIBUTES = ImmutableMap.<String, BiConsumer<Externalizable, CharArray>>builder()
                .putAll(InlineReqReqType.ATTRIBUTES)
                .put(AknAttributes.AS, biConsumerRoleRef(getFieldOffset(Person.class, "as")))
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
        writer.writeStart(ADDRESS_PERSON, 6);
        XmlWriterHelper.writeRole(writer, this);
        super.write(writer);
        writer.writeEnd(ADDRESS_PERSON, 6);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public String name() {
        return PERSON;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public ImmutableMap<String, BiConsumer<Externalizable, CharArray>> attributes() {
        return ATTRIBUTES;
    }
}