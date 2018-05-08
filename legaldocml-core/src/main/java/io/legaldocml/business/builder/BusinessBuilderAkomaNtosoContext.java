package io.legaldocml.business.builder;

import io.legaldocml.akn.AknObject;
import io.legaldocml.akn.MarkupAkomaNtosoContext;
import io.legaldocml.business.ParentLink;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.IdentityHashMap;

/**
 * @author <a href="mailto:jacques.militello@gmail.com">Jacques Militello</a>
 */
public abstract class BusinessBuilderAkomaNtosoContext extends MarkupAkomaNtosoContext implements ParentLink {

    private static final Logger LOGGER = LoggerFactory.getLogger(BusinessBuilderAkomaNtosoContext.class);

    private final IdentityHashMap<AknObject, AknObject> all = new IdentityHashMap<>();

    public <P extends AknObject, C extends AknObject> void push(P parent, C child) {
        if (LOGGER.isDebugEnabled()) {
            LOGGER.debug("push({},{})", parent, child);
        }
        this.all.put(child, parent);
    }

    public <T extends AknObject> T getParent(AknObject child) {
        //noinspection unchecked
        return (T) this.all.get(child);
    }
}
