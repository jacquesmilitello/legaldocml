package io.legaldocml.akn.attribute;

import io.legaldocml.akn.AknObject;
import io.legaldocml.io.Attribute;
import io.legaldocml.io.XmlWriter;

import java.io.IOException;
import java.util.List;

/**
 * This attribute list are used to specify the fact that any attribute can be specified for this element if it belongs
 * to a different namespace.
 *
 * ```xml
 * <xsd:attributeGroup name="core">
 *   <xsd:anyAttribute namespace="##other" processContents="lax"/>
 * </xsd:attributeGroup>
 * ```
 *
 * @author <a href="mailto:jacques.militello@gmail.com">Jacques Militello</a>
 */
public interface Core extends AknObject {

    default void add(Attribute attribute) {
        throw new UnsupportedOperationException("for [" + getClass() + "]");
    }

    default List<Attribute> getAttributes() {
        return null;
    }

    @Override
    default void write(XmlWriter writer) throws IOException {
       if (getAttributes() != null) {
            for (Attribute attribute : getAttributes()) {
                attribute.write(writer);
            }
        }
    }
}