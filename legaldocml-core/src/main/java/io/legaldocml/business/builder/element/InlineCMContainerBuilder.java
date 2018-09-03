package io.legaldocml.business.builder.element;

import io.legaldocml.akn.DocumentType;
import io.legaldocml.akn.container.InlineCMContainer;
import io.legaldocml.akn.element.Inline;
import io.legaldocml.akn.element.StringInlineCM;
import io.legaldocml.akn.group.InlineCM;
import io.legaldocml.business.builder.AbstractBusinessPartBuilder;
import io.legaldocml.business.builder.BusinessBuilder;
import io.legaldocml.business.builder.BusinessBuilderAkomaNtosoContext;
import io.legaldocml.business.builder.BusinessPartBuilder;
import io.legaldocml.business.builder.group.ANinlineBuilder;
import io.legaldocml.business.builder.group.ANsemanticInlineBuilder;
import io.legaldocml.business.builder.group.ANtitleInlineBuilder;
import io.legaldocml.business.builder.group.HTMLInlineBuilder;
import io.legaldocml.business.builder.group.MarkerElementsBuilder;
import io.legaldocml.business.builder.group.SubFlowsElementsBuilder;
import io.legaldocml.business.builder.support.InlineSupport;
import io.legaldocml.business.builder.support.OrganizationSupport;
import io.legaldocml.business.util.AknReference;
import io.legaldocml.business.util.AknReferences;

/**
 * @author <a href="mailto:jacques.militello@gmail.com">Jacques Militello</a>
 */
public abstract class InlineCMContainerBuilder<T extends InlineCMContainer, U extends BusinessPartBuilder<T>> extends AbstractBusinessPartBuilder<T>
        implements ANtitleInlineBuilder<T, InlineCM>, ANinlineBuilder<T, InlineCM>, HTMLInlineBuilder<T, InlineCM>, OrganizationSupport<T>, MarkerElementsBuilder<T, InlineCM>,
        ANsemanticInlineBuilder<T, InlineCM>, SubFlowsElementsBuilder<T, InlineCM>, InlineSupport<T> {

    private final T container;

    public InlineCMContainerBuilder(BusinessBuilder<? extends DocumentType, ? extends BusinessBuilderAkomaNtosoContext> businessBuilder, T container) {
        super(businessBuilder, container);
        this.container = container;
    }

    public final T getContainer() {
        return this.container;
    }

    @SuppressWarnings("unchecked")
    public U text(String text) {
        this.container.add(new StringInlineCM(text));
        return (U) this;
    }

    /*
    @SuppressWarnings("unchecked")
    public U inline(String name, AknReference... refs) {
        Inline inline = new Inline();
        this.container.add(inline);
        inline.setName(name);
        AknReferences.apply(businessBuilder().getAkomaNtoso(), inline, refs);
        return (U) this;
    }
*/

}
