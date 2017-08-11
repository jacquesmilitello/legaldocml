package io.legaldocml.akn.element;

import com.google.common.collect.ImmutableMap;
import io.legaldocml.akn.AknObject;
import io.legaldocml.akn.attribute.Period;
import io.legaldocml.akn.type.TemporalGroupRef;
import io.legaldocml.io.CharArray;
import io.legaldocml.io.XmlWriter;

import java.io.IOException;
import java.util.function.BiConsumer;

import static io.legaldocml.akn.element.Attributes.biConsumerTemporalGroupRef;
import static io.legaldocml.akn.util.XmlWriterHelper.writePeriod;
import static io.legaldocml.unsafe.UnsafeHelper.getFieldOffset;

/**
 * The complex type periodType defines the empty content model and the list of attributes for metadata elements in the
 * analysis section using periods.
 * <p/>
 * <pre>
 *   <xsd:complexType name="periodType">
 * 	   <xsd:complexContent>
 *       <xsd:extension base="anyOtherType">
 *         <xsd:attributeGroup ref="period"/>
 *       </xsd:extension>
 *     </xsd:complexContent>
 *   </xsd:complexType>
 * </pre>
 *
 * @author <a href="mailto:jacques.militello@gmail.com">Jacques Militello</a>
 */
public abstract class PeriodType extends AnyOtherType implements Period {

    protected static final ImmutableMap<String, BiConsumer<AknObject, CharArray>> ATTRIBUTES;

    static {
        ATTRIBUTES = ImmutableMap.<String, BiConsumer<AknObject, CharArray>>builder()
                .putAll(AnyOtherType.ATTRIBUTES)
                .put(Period.ATTRIBUTE, biConsumerTemporalGroupRef(getFieldOffset(PeriodType.class, "period")))
                .build();
    }

    private TemporalGroupRef period;

    /**
     * {@inheritDoc}
     */
    @Override
    public TemporalGroupRef getPeriod() {
        return this.period;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void setPeriod(TemporalGroupRef period) {
        this.period = period;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void write(XmlWriter writer) throws IOException {
        writePeriod(writer, this);
        super.write(writer);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public ImmutableMap<String, BiConsumer<AknObject, CharArray>> attributes() {
        return ATTRIBUTES;
    }
}