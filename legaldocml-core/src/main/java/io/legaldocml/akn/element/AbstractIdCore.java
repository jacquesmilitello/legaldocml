package io.legaldocml.akn.element;

import io.legaldocml.akn.AknCloneContext;
import io.legaldocml.akn.attribute.Core;
import io.legaldocml.io.CoreAttribute;
import io.legaldocml.util.ToStringBuilder;

import java.util.ArrayList;
import java.util.List;

/**
 * @author <a href="mailto:jacques.militello@gmail.com">Jacques Militello</a>
 */
public abstract class AbstractIdCore extends AbstractId implements Core {

    private List<CoreAttribute> coreAttributes;

    /**
     * {@inheritDoc}
     */
    @Override
    public final void add(CoreAttribute attribute) {
        if (this.coreAttributes == null) {
            this.coreAttributes = new ArrayList<>();
        }
        this.coreAttributes.add(attribute);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public final Iterable<CoreAttribute> getAttributes() {
        return this.coreAttributes;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    protected void toString(ToStringBuilder builder) {
        if (this.coreAttributes == null) {
            return;
        }
        for (CoreAttribute attribute : coreAttributes) {
            builder.append(attribute.name(), attribute.value());
        }
    }

    protected final void clone(AbstractIdCore core, AknCloneContext context) {
        super.clone(core, context);
        // todo clone coreAttribute
    }

}