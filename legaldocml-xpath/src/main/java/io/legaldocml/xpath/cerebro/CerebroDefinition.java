package io.legaldocml.xpath.cerebro;

import com.google.common.collect.ImmutableMap;
import io.legaldocml.akn.AknObject;

public final class CerebroDefinition<T extends AknObject> {

    private final ImmutableMap<Class<? extends AknObject>, CerebroLink> links;

    public CerebroDefinition(CerebroLink... cerebroLinks) {
        this(null, cerebroLinks);
    }
    public CerebroDefinition(CerebroDefinition parent, CerebroLink... cerebroLinks) {
        ImmutableMap.Builder builder =  ImmutableMap.<Class<? extends AknObject>, CerebroLink>builder();

        if (parent != null) {
            builder.putAll(parent.links);
        }

        for (CerebroLink link : cerebroLinks) {
            builder.put(link.getAknClass(), link);
        }
        this.links = builder.build();
    }

    public CerebroLink getLink(Class<? extends AknObject> child) {
        return  links.get(child);
    }
}
