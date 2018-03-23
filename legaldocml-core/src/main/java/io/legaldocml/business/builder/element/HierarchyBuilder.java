package io.legaldocml.business.builder.element;

import io.legaldocml.akn.DocumentType;
import io.legaldocml.akn.element.Content;
import io.legaldocml.akn.element.Hierarchy;
import io.legaldocml.akn.element.HierarchyElement;
import io.legaldocml.akn.element.Intro;
import io.legaldocml.business.builder.AbstractBusinessPartBuilder;
import io.legaldocml.business.builder.BusinessBuilder;
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

    private final Hierarchy parent;
    private final T hierarchy;

    public HierarchyBuilder(BusinessBuilder<? extends DocumentType> businessBuilder, T hierarchy) {
        this(businessBuilder, null, hierarchy);
    }

    public HierarchyBuilder(BusinessBuilder<? extends DocumentType> businessBuilder, Hierarchy parent, T hierarchy) {
        super(businessBuilder, hierarchy);
        this.parent = parent;
        this.hierarchy = hierarchy;
    }

    public BlocksBuilder<Intro> intro() {
        return intro(null);
    }

    public BlocksBuilder<Intro> intro(Consumer<Intro> consumer) {
        if (hierarchy.getIntro() != null) {
            throw new BusinessBuilderException("<intro> is not null : [" + hierarchy.getIntro() + "]");
        }
        Intro intro = new Intro();
        this.hierarchy.setIntro(intro);
        businessBuilder().getContext().push(parent(), intro);
        if (consumer != null) {
            consumer.accept(intro);
        }
        return new BlocksBuilder<>(businessBuilder(), this.hierarchy, intro);
    }

    public BlocksBuilder<Content> content() {
        return content(null);
    }

    public BlocksBuilder<Content> content(Consumer<Content> consumer) {
        if (hierarchy.getContent() != null) {
            throw new BusinessBuilderException("<content> is not null : [" + hierarchy.getContent() + "]");
        }
        Content content = new Content();
        this.hierarchy.setContent(content);

        if (consumer != null) {
            consumer.accept(content);
        }

        return new BlocksBuilder<>(businessBuilder(), this.hierarchy, content);
    }
}
