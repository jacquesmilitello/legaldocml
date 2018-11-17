package io.legaldocml.xliff.attribute;

import io.legaldocml.io.CoreAttribute;
import io.legaldocml.io.XmlWriter;
import io.legaldocml.xliff.element.XliffObject;

import java.io.IOException;
import java.util.List;

/**
 * @author <a href="mailto:jacques.militello@gmail.com">Jacques Militello</a>
 */
public interface Core extends XliffObject {

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

}