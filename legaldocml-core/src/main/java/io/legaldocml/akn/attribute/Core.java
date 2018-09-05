package io.legaldocml.akn.attribute;

import java.io.IOException;
import java.util.List;

import io.legaldocml.akn.AknObject;
import io.legaldocml.diff.DiffContext;
import io.legaldocml.io.CoreAttribute;
import io.legaldocml.io.XmlWriter;

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

    default void add(CoreAttribute attribute) {
        throw new UnsupportedOperationException("for [" + getClass() + "]");
    }

    default List<CoreAttribute> getAttributes() {
    	return null;
    }

    default CoreAttribute getAttribute(String name) {
        List<CoreAttribute> attributes = getAttributes();
        if (attributes != null) {
            for (CoreAttribute attribute : attributes) {
                if (name.equals(attribute.name())) {
                    return attribute;
                }
            }
        }
        return null;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    default void write(XmlWriter writer) throws IOException {
        List<CoreAttribute> attributes = getAttributes();
        if (attributes != null) {
            for (CoreAttribute attribute : attributes) {
                attribute.write(writer);
            }
        }
    }

    /**
     * {@inheritDoc}
     */
    @Override
    default void nestedCompare(AknObject object, DiffContext context) {

    }
}