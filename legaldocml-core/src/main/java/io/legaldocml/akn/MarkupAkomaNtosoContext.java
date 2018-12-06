package io.legaldocml.akn;

import com.google.common.collect.ImmutableMap;
import io.legaldocml.akn.attribute.Id;
import io.legaldocml.akn.attribute.Link;
import io.legaldocml.akn.attribute.Src;
import io.legaldocml.akn.type.ManifestationURI;
import io.legaldocml.akn.type.NoWhiteSpace;
import io.legaldocml.akn.type.Uri;
import io.legaldocml.business.BusinessProvider;
import io.legaldocml.unsafe.UnsafeString;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.function.BiConsumer;

/**
 * @author <a href="mailto:jacques.militello@gmail.com">Jacques Militello</a>
 */
public abstract class MarkupAkomaNtosoContext extends AkomaNtosoContext {

    private static final ImmutableMap<String, BiConsumer<MarkupAkomaNtosoContext, AknObject>> REFS;

    static {
        REFS = ImmutableMap.<String, BiConsumer<MarkupAkomaNtosoContext, AknObject>>builder()
                .put(AknAttributes.EID, (context, aknObject) -> context.eids.put(((Id) aknObject).getEid(), (Id) aknObject))
                .put(AknAttributes.SRC, (context, aknObject) -> context.srcs.put(((Src) aknObject).getSrc(), (Src) aknObject))
                .put(AknAttributes.HREF, (context, aknObject) -> context.links.put(((Link) aknObject).getHref(), (Link) aknObject))
                .build();
    }

    private final Map<NoWhiteSpace, Id> eids = new HashMap<>();
    private final Map<ManifestationURI, Src> srcs = new HashMap<>();
    private final Map<Uri, Link> links = new HashMap<>();

    protected MarkupAkomaNtosoContext() {
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void update(String name, AknObject akn) {
        BiConsumer<MarkupAkomaNtosoContext, AknObject> consumer = REFS.get(name);
        if (consumer != null) {
            consumer.accept(this, akn);
        }
    }

    public Id getId(String id) {
        return this.eids.get(NoWhiteSpace.valueOf(UnsafeString.getChars(id)));
    }

    public Id getId(NoWhiteSpace id) {
        return this.eids.get(id);
    }

    public Iterator<Id> getIds() {
        return this.eids.values().iterator();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public abstract BusinessProvider businessProvider();
}
