package io.legaldocml.business.impl;

import io.legaldocml.akn.AknObject;
import io.legaldocml.akn.attribute.Id;
import io.legaldocml.akn.type.NoWhiteSpace;
import io.legaldocml.business.EIdProvider;
import io.legaldocml.business.ParentLink;
import io.legaldocml.util.Strings;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import static io.legaldocml.akn.type.NoWhiteSpace.valueOf;

final class DefaultEIdProvider extends EIdProvider {

    /**
     * SLF4J Logger.
     */
    private static final Logger LOGGER = LoggerFactory.getLogger(DefaultEIdProvider.class);

    static final EIdProvider INSTANCE = new DefaultEIdProvider();

    /**
     * {@inheritDoc}
     */
    public <T extends Id> void fill(ParentLink parentLink, T child, String number) {
        if (LOGGER.isDebugEnabled()) {
            LOGGER.debug("fill({},{})", child, number);
        }
        child.setEid(make(parentLink.getParent(child),child, number));
    }

    /**
     * {@inheritDoc}
     */
    public <T extends Id> void fill(ParentLink parentLink, T child) {
        if (LOGGER.isDebugEnabled()) {
            LOGGER.debug("fill({})", child);
        }
        child.setEid(make(parentLink.getParent(child),child, Strings.EMPTY));
    }

    public String elementRef(Id element) {
        return ELEMENTS_REFS.get(element.getClass());
    }

    public NoWhiteSpace make(AknObject parent, Id object, String number) {
        StringBuilder builder = new StringBuilder();
        if (parent != null && parent instanceof Id && (((Id) parent).getEid()) != null) {
            builder.append((((Id) parent).getEid()).getChars()).append("__");
        }
        builder.append(elementRef(object));
        if (!Strings.isEmpty(number)) {
            builder.append("_").append(number);
        }

        return valueOf(builder.toString());
    }
}
