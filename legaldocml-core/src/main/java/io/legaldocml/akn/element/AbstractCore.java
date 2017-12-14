package io.legaldocml.akn.element;

import com.google.common.collect.ImmutableMap;
import io.legaldocml.akn.AknObject;
import io.legaldocml.akn.attribute.Core;
import io.legaldocml.akn.type.EidRef;
import io.legaldocml.akn.type.ListReferenceRef;
import io.legaldocml.akn.type.StatusType;
import io.legaldocml.akn.type.TemporalGroupRef;
import io.legaldocml.io.Attribute;
import io.legaldocml.io.AttributeGetterSetter;
import io.legaldocml.util.ToStringBuilder;

import java.util.ArrayList;
import java.util.List;

import static io.legaldocml.akn.AknAttributes.ALTERNATIVE_TO;
import static io.legaldocml.akn.AknAttributes.CLASS;
import static io.legaldocml.akn.AknAttributes.PERIOD;
import static io.legaldocml.akn.AknAttributes.REFERS_TO;
import static io.legaldocml.akn.AknAttributes.STATUS;
import static io.legaldocml.akn.AknAttributes.STYLE;
import static io.legaldocml.akn.AknAttributes.TITLE;
import static io.legaldocml.akn.element.Attributes.attributeGetterSetter4Enum;
import static io.legaldocml.akn.element.Attributes.attributeGetterSetter4ListReferenceRef;
import static io.legaldocml.akn.element.Attributes.attributeGetterSetter4String;
import static io.legaldocml.akn.element.Attributes.attributeGetterSetter4TemporalGroupRef;
import static io.legaldocml.unsafe.UnsafeHelper.getFieldOffset;

/**
 * @author <a href="mailto:jacques.militello@gmail.com">Jacques Militello</a>
 */
public abstract class AbstractCore extends AbstractId implements Core {

    protected static final ImmutableMap<String, AttributeGetterSetter<AknObject>> ATTRIBUTES;

    static {
        ATTRIBUTES = ImmutableMap.<String, AttributeGetterSetter<AknObject>>builder()
                .putAll(AbstractId.ATTRIBUTES)
                .put(CLASS, attributeGetterSetter4String(CLASS, getFieldOffset(AbstractCore.class, "clazz")))
                .put(STYLE, attributeGetterSetter4String(STYLE, getFieldOffset(AbstractCore.class, "style")))
                .put(TITLE, attributeGetterSetter4String(TITLE, getFieldOffset(AbstractCore.class, "title")))
                .put(STATUS, attributeGetterSetter4Enum(STATUS, getFieldOffset(AbstractCore.class, "status"), StatusType.class))
                .put(PERIOD, attributeGetterSetter4TemporalGroupRef(PERIOD, getFieldOffset(AbstractCore.class, "period")))
                .put(REFERS_TO, attributeGetterSetter4ListReferenceRef(REFERS_TO, getFieldOffset(AbstractCore.class, "refersTo")))
                .put(ALTERNATIVE_TO, Attributes.attributeGetterSetter4EidRef(ALTERNATIVE_TO, getFieldOffset(AbstractCore.class, "alternativeTo")))
                .build();
    }

    private String clazz;
    private String style;
    private String title;
    private StatusType status;
    private TemporalGroupRef period;
    private ListReferenceRef refersTo;
    private EidRef alternativeTo;
    private List<Attribute> attributes;

    public final String getClazz() {
        return this.clazz;
    }

    public final void setClazz(String clazz) {
        this.clazz = clazz;
    }

    public final String getStyle() {
        return this.style;
    }

    public final void setStyle(String style) {
        this.style = style;
    }

    public final String getTitle() {
        return this.title;
    }

    public final void setTitle(String title) {
        this.title = title;
    }

    public final StatusType getStatus() {
        return this.status;
    }

    public final void setStatus(StatusType status) {
        this.status = status;
    }

    public final TemporalGroupRef getPeriod() {
        return this.period;
    }

    public final void setPeriod(TemporalGroupRef period) {
        this.period = period;
    }

    public final ListReferenceRef getRefersTo() {
        return this.refersTo;
    }

    public final void setRefersTo(ListReferenceRef refersTo) {
        this.refersTo = refersTo;
    }

    public final EidRef getAlternativeTo() {
        return this.alternativeTo;
    }

    public final void setAlternativeTo(EidRef alternativeTo) {
        this.alternativeTo = alternativeTo;
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
    public final List<Attribute> getAttributes() {
        return this.attributes;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    protected void toString(ToStringBuilder builder) {
        builder.append(CLASS, this.clazz);
        builder.append(STYLE, this.style);
        builder.append(TITLE, this.title);
        builder.append(STATUS, this.status);
        builder.append(PERIOD, this.period);
        builder.append(REFERS_TO, this.refersTo);
        builder.append(ALTERNATIVE_TO, this.alternativeTo);
    }

}