package io.legaldocml.business.builder;

import io.legaldocml.akn.element.Heading;
import io.legaldocml.akn.element.Hierarchy;
import io.legaldocml.akn.element.HierarchyElement;
import io.legaldocml.akn.element.Intro;
import io.legaldocml.akn.element.Num;
import io.legaldocml.akn.element.SubHeading;
import io.legaldocml.business.util.EidFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * @author <a href="mailto:jacques.militello@gmail.com">Jacques Militello</a>
 */
public final class HierarchyBuilder<T extends Hierarchy> {

    /**
     * SLF4J Logger.
     */
    private static final Logger LOGGER = LoggerFactory.getLogger(HierarchyBuilder.class);

    private final BusinessBuilder businessBuilder;

    private final HierarchyStrategy strategy;
    private final Hierarchy parent;
    private final T hierarchy;

    public HierarchyBuilder(BusinessBuilder businessBuilder, HierarchyStrategy strategy, T hierarchy) {
        this.businessBuilder = businessBuilder;
        this.strategy = strategy;
        this.parent = null;
        this.hierarchy = hierarchy;
    }

    public HierarchyBuilder(BusinessBuilder businessBuilder, HierarchyStrategy strategy, Hierarchy parent, T hierarchy) {
        this.businessBuilder = businessBuilder;
        this.strategy = strategy;
        this.parent = parent;
        this.hierarchy = hierarchy;
    }

    public final InlineTypeBuilder<Num> num() {
        if (hierarchy.getNum() != null) {
            throw new BusinessBuilderException("<num> is not null : [" + hierarchy.getNum() + "]");
        }
        Num num = new Num();
        this.hierarchy.setNum(num);
        return new InlineTypeBuilder<>(num);
    }

    public InlineReqTypeBuilder<Heading> heading() {
        if (hierarchy.getHeading() != null) {
            throw new BusinessBuilderException("<heading> is not null : [" + hierarchy.getHeading() + "]");
        }
        Heading heading = new Heading();
        this.hierarchy.setHeading(heading);
        return new InlineReqTypeBuilder<>(heading);
    }

    public InlineReqTypeBuilder<SubHeading> subHeading() {
        if (hierarchy.getSubheading() != null) {
            throw new BusinessBuilderException("<subHeading> is not null : [" + hierarchy.getSubheading() + "]");
        }
        SubHeading subHeading = new SubHeading();
        this.hierarchy.setSubheading(subHeading);
        return new InlineReqTypeBuilder<>(subHeading);
    }

    public BlocksreqBuilder<Intro> intro() {
        if (hierarchy.getIntro() != null) {
            throw new BusinessBuilderException("<intro> is not null : [" + hierarchy.getIntro() + "]");
        }
        Intro intro = new Intro();
        this.hierarchy.setIntro(intro);
        return new BlocksreqBuilder<>(this.hierarchy, intro);
    }

    public HierarchyBuilder<T> eId(String number) {
        EidFactory.makeAndFill(this.parent, this.hierarchy, number);
        return this;
    }

    public <T extends Hierarchy> HierarchyBuilder<T> next() {
        return new HierarchyBuilder<T>(this.businessBuilder, this.strategy, this.hierarchy, this.strategy.next(this.hierarchy));
    }

    public <T extends Hierarchy> HierarchyBuilder<T> newChild(String element) {
        HierarchyElement el = Hierarchy.ELEMS.get(element).get();
        this.hierarchy.add(el);
        return new HierarchyBuilder<T>(this.businessBuilder, this.strategy, this.hierarchy, (T) el);
    }
}
