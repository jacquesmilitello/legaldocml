package io.legaldocml.business.builder.element;

import io.legaldocml.akn.DocumentType;
import io.legaldocml.akn.element.Content;
import io.legaldocml.akn.element.Hierarchy;
import io.legaldocml.akn.element.HierarchyElement;
import io.legaldocml.akn.element.Intro;
import io.legaldocml.business.builder.AbstractBusinessPartBuilder;
import io.legaldocml.business.builder.BusinessBuilder;
import io.legaldocml.business.builder.BusinessBuilderAkomaNtosoContext;
import io.legaldocml.business.builder.BusinessBuilderException;
import io.legaldocml.business.builder.attribute.EIdSupport;
import io.legaldocml.business.builder.group.HierElementsBuilder;
import io.legaldocml.business.builder.support.ComponentRefSupport;
import io.legaldocml.business.builder.support.HeadingSupport;
import io.legaldocml.business.builder.support.NumSupport;
import io.legaldocml.business.builder.support.SubHeadingSupport;

import java.util.function.Consumer;

/**
 * @author <a href="mailto:jacques.militello@gmail.com">Jacques Militello</a>
 */
public final class HierarchyBuilder<T extends Hierarchy> extends AbstractBusinessPartBuilder<T> implements HierElementsBuilder<T, HierarchyElement>, ComponentRefSupport<T, HierarchyElement>,
        HeadingSupport<T>, NumSupport<T>, SubHeadingSupport<T> , EIdSupport<T> {


    public HierarchyBuilder(BusinessBuilder<? extends DocumentType, ? extends BusinessBuilderAkomaNtosoContext> businessBuilder, T hierarchy) {
        super(businessBuilder, hierarchy);
    }

    public BlocksBuilder<Intro> intro() {
        return intro(null);
    }

    public BlocksBuilder<Intro> intro(Consumer<Intro> consumer) {
        if (parent().getIntro() != null) {
            throw new BusinessBuilderException("<intro> is not null : [" + parent().getIntro() + "]");
        }
        Intro intro = new Intro();
        this.parent().setIntro(intro);
        businessBuilder().getContext().push(parent(), intro);
        if (consumer != null) {
            consumer.accept(intro);
        }
        return new BlocksBuilder<>(businessBuilder(), intro);
    }

    public BlocksBuilder<Content> content() {
        return content(null);
    }

    public BlocksBuilder<Content> content(Consumer<Content> consumer) {
        if (parent().getContent() != null) {
            throw new BusinessBuilderException("<content> is not null : [" + parent().getContent() + "]");
        }
        Content content = new Content();
        this.parent().setContent(content);
        businessBuilder().getContext().push(parent(), content);

        if (consumer != null) {
            consumer.accept(content);
        }

        return new BlocksBuilder<>(businessBuilder(), content);
    }
}
