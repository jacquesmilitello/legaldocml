package io.legaldocml.akn.element;

import com.google.common.collect.ImmutableMap;
import io.legaldocml.akn.AknObject;
import io.legaldocml.akn.attribute.*;
import io.legaldocml.akn.type.EidRef;
import io.legaldocml.akn.type.ListReferenceRef;
import io.legaldocml.akn.type.StatusType;
import io.legaldocml.akn.type.TemporalGroupRef;
import io.legaldocml.io.Attribute;
import io.legaldocml.io.CharArray;
import io.legaldocml.io.XmlWriter;
import io.legaldocml.util.ToStringBuilder;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.function.BiConsumer;

import static io.legaldocml.akn.element.Attributes.*;
import static io.legaldocml.unsafe.UnsafeHelper.getFieldOffset;

/**
 * @author <a href="mailto:jacques.militello@gmail.com">Jacques Militello</a>
 */
public abstract class AbstractCore extends AbstractId implements Core {

    protected static final ImmutableMap<String, BiConsumer<AknObject, CharArray>> ATTRIBUTES;

    static {
        ATTRIBUTES = ImmutableMap.<String, BiConsumer<AknObject, CharArray>>builder()
                .putAll(AbstractId.ATTRIBUTES)
                .put(HTMLattrs.ATTRIBUTE_CLASS, biConsumerString(getFieldOffset(AbstractCore.class, "clazz")))
                .put(HTMLattrs.ATTRIBUTE_STYLE, biConsumerString(getFieldOffset(AbstractCore.class, "style")))
                .put(HTMLattrs.ATTRIBUTE_TITLE, biConsumerString(getFieldOffset(AbstractCore.class, "title")))
                .put(Enactment.ATTRIBUTE, biConsumerEnum(getFieldOffset(AbstractCore.class, "status"), StatusType.class))
                .put(Period.ATTRIBUTE, biConsumerTemporalGroupRef(getFieldOffset(AbstractCore.class, "period")))
                .put(RefersOpt.ATTRIBUTE, biConsumerListReferenceRef(getFieldOffset(AbstractCore.class, "refersTo")))
                .put(Alt.ATTRIBUTE, Attributes.biConsumerEidRef(getFieldOffset(AbstractCore.class, "alternativeTo")))
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
    public ImmutableMap<String, BiConsumer<AknObject, CharArray>> attributes() {
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
    public void write(XmlWriter writer) throws IOException {
        if (this.attributes != null) {
            for (int i = 0, n = this.attributes.size(); i < n; i++) {
                this.attributes.get(i).write(writer);
            }
        }
    }

    /**
     * {@inheritDoc}
     */
    @Override
    protected void toString(ToStringBuilder builder) {
        builder.append(HTMLattrs.ATTRIBUTE_CLASS, this.clazz);
        builder.append(HTMLattrs.ATTRIBUTE_STYLE, this.style);
        builder.append(HTMLattrs.ATTRIBUTE_TITLE, this.title);
        builder.append(Enactment.ATTRIBUTE, this.status);
        builder.append(Period.ATTRIBUTE, this.period);
        builder.append(RefersOpt.ATTRIBUTE, this.refersTo);
        builder.append(Alt.ATTRIBUTE, this.alternativeTo);
    }

}