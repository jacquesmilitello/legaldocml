package io.legaldocml.business.builder;

import io.legaldocml.akn.element.Content;
import io.legaldocml.akn.element.Heading;
import io.legaldocml.akn.element.Hierarchy;
import io.legaldocml.akn.element.HierarchyElement;
import io.legaldocml.akn.element.Intro;
import io.legaldocml.akn.element.Num;
import io.legaldocml.akn.element.Subheading;
import io.legaldocml.business.builder.element.InlineReqTypeBuilder;
import io.legaldocml.business.builder.element.InlineTypeBuilder;
import io.legaldocml.business.builder.group.HierElementsBuilder;
import io.legaldocml.business.util.EidFactory;

import java.util.function.Consumer;

/**
 * @author <a href="mailto:jacques.militello@gmail.com">Jacques Militello</a>
 */
public final class HierarchyBuilder<T extends Hierarchy> extends AbstractBusinessPartBuilder<T> implements HierElementsBuilder<T> {

    private final Hierarchy parent;
    private final T hierarchy;

    public HierarchyBuilder(BusinessBuilder businessBuilder, T hierarchy) {
        this(businessBuilder, null, hierarchy);
    }

    public HierarchyBuilder(BusinessBuilder businessBuilder, Hierarchy parent, T hierarchy) {
        super(businessBuilder, hierarchy);
        this.parent = parent;
        this.hierarchy = hierarchy;
    }

    public final InlineTypeBuilder<Num> num() {
        if (hierarchy.getNum() != null) {
            throw new BusinessBuilderException("<num> is not null : [" + hierarchy.getNum() + "]");
        }
        Num num = new Num();
        this.hierarchy.setNum(num);
        return new InlineTypeBuilder<>(getBusinessBuilder(), num);
    }

    public InlineReqTypeBuilder<Heading> heading() {
       return heading(null);
    }

    public InlineReqTypeBuilder<Heading> heading(Consumer<Heading> consumer) {
        if (hierarchy.getHeading() != null) {
            throw new BusinessBuilderException("<heading> is not null : [" + hierarchy.getHeading() + "]");
        }
        Heading heading = new Heading();
        this.hierarchy.setHeading(heading);

        if (consumer != null) {
            consumer.accept(heading);
        }

        return new InlineReqTypeBuilder<>(getBusinessBuilder(), heading);
    }

    public InlineReqTypeBuilder<Subheading> subHeading() {
        if (hierarchy.getSubheading() != null) {
            throw new BusinessBuilderException("<subheading> is not null : [" + hierarchy.getSubheading() + "]");
        }
        Subheading subheading = new Subheading();
        this.hierarchy.setSubheading(subheading);
        return new InlineReqTypeBuilder<>(getBusinessBuilder(), subheading);
    }

    public HierarchyBuilder<T> eId(String number) {
        EidFactory.makeAndFill(this.parent, this.hierarchy, number);
        return this;
    }

    public <E extends Hierarchy & HierarchyElement> HierarchyBuilder<E> next() {
        E el = this.getBusinessBuilder().getStrategy().next(this.hierarchy);
        this.hierarchy.addHierarchyElement(el);
        return new HierarchyBuilder<>(getBusinessBuilder(), this.hierarchy, el);
    }

    public <E extends Hierarchy> HierarchyBuilder<E> newChild(String element) {
        HierarchyElement el = Hierarchy.ELEMS.get(element).get();
        this.hierarchy.addHierarchyElement(el);
        return new HierarchyBuilder<E>(getBusinessBuilder(), (E) el);
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
        if (consumer != null) {
            consumer.accept(intro);
        }
        return new BlocksBuilder<>(getBusinessBuilder(), this.hierarchy, intro);
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

        return new BlocksBuilder<>(getBusinessBuilder(), this.hierarchy, content);
    }
}
