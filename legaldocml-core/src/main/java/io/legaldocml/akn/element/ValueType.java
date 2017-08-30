package io.legaldocml.akn.element;

import com.google.common.collect.ImmutableMap;
import io.legaldocml.akn.AknAttributes;
import io.legaldocml.akn.attribute.RefersOpt;
import io.legaldocml.akn.attribute.ShowOpt;
import io.legaldocml.akn.attribute.ValueReq;
import io.legaldocml.akn.type.ListReferenceRef;
import io.legaldocml.io.CharArray;
import io.legaldocml.io.Externalizable;
import io.legaldocml.io.XmlWriter;
import io.legaldocml.util.ToStringBuilder;

import java.io.IOException;
import java.util.function.BiConsumer;

import static io.legaldocml.akn.element.Attributes.biConsumerListReferenceRef;
import static io.legaldocml.akn.element.Attributes.biConsumerString;
import static io.legaldocml.akn.util.XmlWriterHelper.writeRefers;
import static io.legaldocml.akn.util.XmlWriterHelper.writeShow;
import static io.legaldocml.akn.util.XmlWriterHelper.writeValue;
import static io.legaldocml.unsafe.UnsafeHelper.getFieldOffset;

/**
 * The type valueType specifies a text attribute to FRBR elements.
 *
 *
 * <pre>
 *   <xsd:complexType name="valueType">
 *     <xsd:complexContent>
 *       <xsd:extension base="metaopt">
 *         <xsd:attributeGroup ref="value"/>
 *         <xsd:attributeGroup ref="refers"/>
 *         <xsd:attributeGroup ref="showopt"/>
 *       <xsd:extension>
 *     <xsd:complexContent>
 *   <xsd:complexType>
 * </pre>
 *
 * @author <a href="mailto:jacques.militello@gmail.com">Jacques Militello</a>
 */
public abstract class ValueType extends MetaOpt implements ValueReq, RefersOpt, ShowOpt {

    protected static final ImmutableMap<String, BiConsumer<Externalizable, CharArray>> ATTRIBUTES;

    static {
        ATTRIBUTES = ImmutableMap.<String, BiConsumer<Externalizable, CharArray>>builder()
                .put(AknAttributes.VALUE, biConsumerString(getFieldOffset(ValueType.class, "value")))
                .put(AknAttributes.REFERS_TO, biConsumerListReferenceRef(getFieldOffset(ValueType.class, "refersTo")))
                .put(AknAttributes.SHOW_AS, biConsumerString(getFieldOffset(ValueType.class, "showAs")))
                .put(AknAttributes.SHORT_FORM, biConsumerString(getFieldOffset(ValueType.class, "shortForm")))
                .putAll(MetaOpt.ATTRIBUTES)
                .build();
    }

    private String value;

    private ListReferenceRef refersTo;
    private String showAs;
    private String shortForm;

    /**
     * {@inheritDoc}
     */
    @Override
    public String getValue() {
        return this.value;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void setValue(String value) {
        this.value = value;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public String getShowAs() {
        return this.showAs;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void setShowAs(String showAs) {
        this.showAs = showAs;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public String getShortForm() {
        return this.shortForm;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void setShortForm(String shortForm) {
        this.shortForm = shortForm;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public ListReferenceRef getRefersTo() {
        return this.refersTo;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void setRefersTo(ListReferenceRef refersTo) {
        this.refersTo = refersTo;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void write(XmlWriter writer) throws IOException {
        super.write(writer);
        writeValue(writer, this);
        writeShow(writer, this);
        writeRefers(writer, this);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public ImmutableMap<String, BiConsumer<Externalizable, CharArray>> attributes() {
        return ATTRIBUTES;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    protected void toString(ToStringBuilder builder) {
        builder.append(AknAttributes.VALUE, this.value);
        builder.append(AknAttributes.REFERS_TO, this.refersTo);
        builder.append(AknAttributes.SHOW_AS, this.showAs);
        builder.append(AknAttributes.SHORT_FORM, this.shortForm);
    }
}