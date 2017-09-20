package io.legaldocml.business.builder;

import com.google.common.collect.ImmutableList;
import io.legaldocml.akn.element.Alinea;
import io.legaldocml.akn.element.Article;
import io.legaldocml.akn.element.Book;
import io.legaldocml.akn.element.Chapter;
import io.legaldocml.akn.element.Clause;
import io.legaldocml.akn.element.Division;
import io.legaldocml.akn.element.Hierarchy;
import io.legaldocml.akn.element.Paragraph;
import io.legaldocml.akn.element.Part;
import io.legaldocml.akn.element.Point;
import io.legaldocml.akn.element.Proviso;
import io.legaldocml.akn.element.Rule;
import io.legaldocml.akn.element.Section;
import io.legaldocml.akn.element.Subparagraph;
import io.legaldocml.akn.element.Subsection;
import io.legaldocml.akn.element.Title;
import io.legaldocml.akn.element.Tome;

import java.util.function.Supplier;

/**
 * @author <a href="mailto:jacques.militello@gmail.com">Jacques Militello</a>
 */
public final class HierarchyStrategyBuilder {

    private final ImmutableList.Builder<Class<? extends Hierarchy>> keys;
    private final ImmutableList.Builder<Supplier<? extends Hierarchy>> values;

    public HierarchyStrategyBuilder() {
        this.keys = ImmutableList.builder();
        this.values = ImmutableList.builder();
    }

    public HierarchyStrategyBuilder tome() {
        this.keys.add(Tome.class);
        this.values.add(Tome::new);
        return this;
    }

    public HierarchyStrategyBuilder part() {
        this.keys.add(Part.class);
        this.values.add(Part::new);
        return this;
    }

    public HierarchyStrategyBuilder book() {
        this.keys.add(Book.class);
        this.values.add(Book::new);
        return this;
    }

    public HierarchyStrategyBuilder title() {
        this.keys.add(Title.class);
        this.values.add(Title::new);
        return this;
    }

    public HierarchyStrategyBuilder chapter() {
        this.keys.add(Chapter.class);
        this.values.add(Chapter::new);
        return this;
    }

    public HierarchyStrategyBuilder section() {
        this.keys.add(Section.class);
        this.values.add(Section::new);
        return this;
    }

    public HierarchyStrategyBuilder subSection() {
        this.keys.add(Subsection.class);
        this.values.add(Subsection::new);
        return this;
    }

    public HierarchyStrategyBuilder article() {
        this.keys.add(Article.class);
        this.values.add(Article::new);
        return this;
    }

    public HierarchyStrategyBuilder rule() {
        this.keys.add(Rule.class);
        this.values.add(Rule::new);
        return this;
    }

    public HierarchyStrategyBuilder alinea() {
        this.keys.add(Alinea.class);
        this.values.add(Alinea::new);
        return this;
    }

    public HierarchyStrategyBuilder clause() {
        this.keys.add(Clause.class);
        this.values.add(Clause::new);
        return this;
    }

    public HierarchyStrategyBuilder paragraph() {
        this.keys.add(Paragraph.class);
        this.values.add(Paragraph::new);
        return this;
    }

    public HierarchyStrategyBuilder provisio() {
        this.keys.add(Proviso.class);
        this.values.add(Proviso::new);
        return this;
    }

    public HierarchyStrategyBuilder subParagraph() {
        this.keys.add(Subparagraph.class);
        this.values.add(Subparagraph::new);
        return this;
    }

    public HierarchyStrategyBuilder division() {
        this.keys.add(Division.class);
        this.values.add(Division::new);
        return this;
    }

    public HierarchyStrategyBuilder point() {
        this.keys.add(Point.class);
        this.values.add(Point::new);
        return this;
    }

    public HierarchyStrategy build() {
        return new HierarchyStrategy(this.keys.build(), this.values.build());
    }



}
