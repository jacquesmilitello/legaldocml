package io.legaldocml.akn.element;

import java.util.ArrayList;
import java.util.List;

import io.legaldocml.akn.attribute.Core;
import io.legaldocml.io.Attribute;

/**
 * @author <a href="mailto:jacques.militello@gmail.com">Jacques Militello</a>
 */
public abstract class AbstractIdCore extends AbstractId implements Core {

    private List<Attribute> coreAttributes;

    /**
     * {@inheritDoc}
     */
    @Override
    public final void add(Attribute attribute) {
        if (this.coreAttributes == null) {
            this.coreAttributes = new ArrayList<>();
        }
        this.coreAttributes.add(attribute);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public final List<Attribute> getAttributes() {
        return this.coreAttributes;
    }

}