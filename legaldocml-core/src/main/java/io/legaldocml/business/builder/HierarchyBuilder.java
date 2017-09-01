package io.legaldocml.business.builder;

import io.legaldocml.akn.element.Content;
import io.legaldocml.akn.element.Heading;
import io.legaldocml.akn.element.Hierarchy;
import io.legaldocml.akn.element.HierarchyElement;
import io.legaldocml.akn.element.Intro;
import io.legaldocml.akn.element.Num;
import io.legaldocml.akn.element.SubHeading;
import io.legaldocml.business.util.EidFactory;

/**
 * @author <a href="mailto:jacques.militello@gmail.com">Jacques Militello</a>
 */
public final class HierarchyBuilder<T extends Hierarchy> extends BusinessPartBuilder {

    private final Hierarchy parent;
    private final T hierarchy;

    public HierarchyBuilder(BusinessBuilder businessBuilder, T hierarchy) {
        this(businessBuilder, null, hierarchy);
    }

    public HierarchyBuilder(BusinessBuilder businessBuilder, Hierarchy parent, T hierarchy) {
        super(businessBuilder);
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
        if (hierarchy.getHeading() != null) {
            throw new BusinessBuilderException("<heading> is not null : [" + hierarchy.getHeading() + "]");
        }
        Heading heading = new Heading();
        this.hierarchy.setHeading(heading);
        return new InlineReqTypeBuilder<>(getBusinessBuilder(), heading);
    }

    public InlineReqTypeBuilder<SubHeading> subHeading() {
        if (hierarchy.getSubheading() != null) {
            throw new BusinessBuilderException("<subHeading> is not null : [" + hierarchy.getSubheading() + "]");
        }
        SubHeading subHeading = new SubHeading();
        this.hierarchy.setSubheading(subHeading);
        return new InlineReqTypeBuilder<>(getBusinessBuilder(), subHeading);
    }

    public HierarchyBuilder<T> eId(String number) {
        EidFactory.makeAndFill(this.parent, this.hierarchy, number);
        return this;
    }

    public <T extends Hierarchy & HierarchyElement> HierarchyBuilder<T> next() {
        T el = this.getBusinessBuilder().getStrategy().next(this.hierarchy);
        this.hierarchy.add(el);
        return new HierarchyBuilder<>(getBusinessBuilder(), this.hierarchy, el);
    }

    public <T extends Hierarchy> HierarchyBuilder<T> newChild(String element) {
        HierarchyElement el = Hierarchy.ELEMS.get(element).get();
        this.hierarchy.add(el);
        return new HierarchyBuilder<T>(getBusinessBuilder(), this.hierarchy, (T) el);
    }

    public BlocksBuilder<Intro> intro() {
        if (hierarchy.getIntro() != null) {
            throw new BusinessBuilderException("<intro> is not null : [" + hierarchy.getIntro() + "]");
        }
        Intro intro = new Intro();
        this.hierarchy.setIntro(intro);
        return new BlocksBuilder<>(getBusinessBuilder(), this.hierarchy, intro);
    }

    public BlocksBuilder<Content> content() {
        if (hierarchy.getContent() != null) {
            throw new BusinessBuilderException("<content> is not null : [" + hierarchy.getContent() + "]");
        }
        Content content = new Content();
        this.hierarchy.setContent(content);
        return new BlocksBuilder<>(getBusinessBuilder(), this.hierarchy, content);
    }
}
