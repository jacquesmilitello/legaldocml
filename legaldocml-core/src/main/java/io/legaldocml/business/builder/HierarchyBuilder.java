package io.legaldocml.business.builder;

import io.legaldocml.akn.element.Heading;
import io.legaldocml.akn.element.Hierarchy;
import io.legaldocml.akn.element.Intro;
import io.legaldocml.akn.element.List;
import io.legaldocml.akn.element.Num;
import io.legaldocml.akn.element.Paragraph;
import io.legaldocml.business.util.EidFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * @author <a href="mailto:jacques.militello@gmail.com">Jacques Militello</a>
 */
public class HierarchyBuilder<T extends Hierarchy> {

    /**
     * SLF4J Logger.
     */
    private static final Logger LOGGER = LoggerFactory.getLogger(HierarchyBuilder.class);

    private final BusinessBuilder businessBuilder;

    private final Hierarchy parent;
    private final T hierarchy;

    public HierarchyBuilder(BusinessBuilder businessBuilder, T hierarchy) {
        this.businessBuilder = businessBuilder;
        this.parent = null;
        this.hierarchy = hierarchy;
    }

    public HierarchyBuilder(BusinessBuilder businessBuilder, Hierarchy parent, T hierarchy) {
        this.businessBuilder = businessBuilder;
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

    public HierarchyBuilder<Paragraph> paragraph() {
        Paragraph paragraph = new Paragraph();
        this.hierarchy.add(paragraph);
        return new HierarchyBuilder<>(this.businessBuilder, this.hierarchy, paragraph);
    }

    public HierarchyBuilder<List> list() {
        List list = new List();
        this.hierarchy.add(list);
        return new HierarchyBuilder<>(this.businessBuilder, this.hierarchy, list);
    }

    public HierarchyBuilder<T> title(String title) {
        this.hierarchy.setTitle(title);
        return this;
    }

    public HierarchyBuilder<T> eId(String number) {
        EidFactory.makeAndFill(this.parent, this.hierarchy, number);
        return this;
    }


    public BlocksreqBuilder<Intro> intro() {
        Intro intro = new Intro();
        this.hierarchy.setIntro(intro);
        return new BlocksreqBuilder<>(this.hierarchy,intro);
    }
}
