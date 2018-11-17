package io.legaldocml.xliff.element;

import com.google.common.collect.ImmutableMap;
import io.legaldocml.akn.AknObject;
import io.legaldocml.io.AttributeGetterSetter;
import io.legaldocml.io.XmlReader;
import io.legaldocml.io.XmlWriter;
import io.legaldocml.util.ToStringBuilder;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;

import static io.legaldocml.akn.element.Attributes.attributeGetterSetter4String;
import static io.legaldocml.unsafe.UnsafeHelper.getFieldOffset;
import static io.legaldocml.xliff.element.XliffAttributes.ATTRIBUTE_CONSUMER;
import static io.legaldocml.xliff.element.XliffAttributes.HREF;
import static io.legaldocml.xliff.element.XliffAttributes.ID;

/**
 * <pre>
 * <xs:element name="skeleton">
 *   <xs:complexType mixed="true">
 *     <xs:sequence>
 *       <xs:any minOccurs="0" maxOccurs="unbounded" namespace="##other" processContents="lax"/>
 *     </xs:sequence>
 *     <xs:attribute name="href" use="optional"/>
 *   </xs:complexType>
 * </xs:element>
 * </pre>
 */
public final class Skeleton implements XliffObject {

    /**
     * SLF4J logger.
     */
    private static final Logger LOGGER = LoggerFactory.getLogger(Skeleton.class);

    protected static final ImmutableMap<String, AttributeGetterSetter<AknObject>> ATTRIBUTES;

    static {
        ATTRIBUTES = ImmutableMap.<String, AttributeGetterSetter<AknObject>>builder()
                .put(ID, attributeGetterSetter4String(HREF, getFieldOffset(Skeleton.class, "href")))
                .build();
    }

    private String href;

    public String getHref() {
        return href;
    }

    public void setHref(String href) {
        this.href = href;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void write(XmlWriter writer) throws IOException {
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void read(XmlReader reader) {
        reader.forEach(this, ATTRIBUTE_CONSUMER);
        if (LOGGER.isDebugEnabled()) {
            LOGGER.debug("Skeleton ({})", this);
           reader.nextStartOrEndElement();
        }
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public final String toString() {
        ToStringBuilder builder = new ToStringBuilder(this, false);
        return builder.toString();
    }

}