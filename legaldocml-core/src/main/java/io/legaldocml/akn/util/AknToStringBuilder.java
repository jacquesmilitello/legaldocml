package io.legaldocml.akn.util;

import io.legaldocml.io.Attribute;
import io.legaldocml.util.ToStringBuilder;

import java.util.List;

/**
 * @author <a href="mailto:jacques.militello@gmail.com">Jacques Militello</a>
 */
public final class AknToStringBuilder extends ToStringBuilder {

    public AknToStringBuilder(Object object) {
        super(object);
    }

    public void append(List<Attribute> attributes) {
        if (attributes == null) {
            return;
        }
        //insert("attributes");
        for (Attribute attribute : attributes) {
            //insert(attribute).insert("=[").insert(value == null ? NULL : value).insert("] ");
           // insert(attribute.toString());
        }
    }

}
