package io.legaldocml.business.builder.support;

import io.legaldocml.akn.AknObject;
import io.legaldocml.akn.container.ComponentRefContainer;
import io.legaldocml.akn.element.ComponentRef;
import io.legaldocml.akn.type.ManifestationURI;
import io.legaldocml.business.builder.element.SrcTypeBuilder;

import java.util.function.Consumer;

/**
 * @author <a href="mailto:jacques.militello@gmail.com">Jacques Militello</a>
 */
public interface ComponentRefSupport<T extends ComponentRefContainer<E>, E extends AknObject> extends SupportBuilder<T> {

    default SrcTypeBuilder<ComponentRef> componentRef(ManifestationURI src, String showAs) {
        return componentRef(src, showAs,null);
    }

    default SrcTypeBuilder<ComponentRef> componentRef(ManifestationURI src, String showAs, Consumer<ComponentRef> consumer) {
        ComponentRef componentRef = new ComponentRef();
        componentRef.setShowAs(showAs);
        parent().add(componentRef);
        if (consumer != null) {
            consumer.accept(componentRef);
        }
        return new SrcTypeBuilder<>(businessBuilder(), componentRef);
    }

}
