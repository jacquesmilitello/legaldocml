package io.legaldocml.akn.visitor.group;

import io.legaldocml.akn.element.Alinea;
import io.legaldocml.akn.element.Article;
import io.legaldocml.akn.element.Book;
import io.legaldocml.akn.element.Chapter;
import io.legaldocml.akn.element.Clause;
import io.legaldocml.akn.element.Division;
import io.legaldocml.akn.element.Indent;
import io.legaldocml.akn.element.Level;
import io.legaldocml.akn.element.List;
import io.legaldocml.akn.element.Paragraph;
import io.legaldocml.akn.element.Part;
import io.legaldocml.akn.element.Point;
import io.legaldocml.akn.element.Proviso;
import io.legaldocml.akn.element.Rule;
import io.legaldocml.akn.element.Section;
import io.legaldocml.akn.element.Subchapter;
import io.legaldocml.akn.element.Subclause;
import io.legaldocml.akn.element.Subdivision;
import io.legaldocml.akn.element.Sublist;
import io.legaldocml.akn.element.Subparagraph;
import io.legaldocml.akn.element.Subpart;
import io.legaldocml.akn.element.Subrule;
import io.legaldocml.akn.element.Subsection;
import io.legaldocml.akn.element.Subtitle;
import io.legaldocml.akn.element.Title;
import io.legaldocml.akn.element.Tome;
import io.legaldocml.akn.element.Transitional;

/**
 * Visitor for {@link io.legaldocml.akn.group.ANhier}
 *
 * @author <a href="mailto:jacques.militello@gmail.com">Jacques Militello</a>
 */
public interface ANhierVisitor  {

    default boolean visitEnter(Clause clause) { return true;}

    default void visitLeave(Clause clause) { }

    default boolean visitEnter(Section section) { return true;}

    default void visitLeave(Section section) { }

    default boolean visitEnter(Part part) { return true;}

    default void visitLeave(Part part) { }

    default boolean visitEnter(Paragraph paragraph) { return true;}

    default void visitLeave(Paragraph paragraph) { }

    default boolean visitEnter(Chapter chapter) { return true;}

    default void visitLeave(Chapter chapter) { }

    default boolean visitEnter(Title title) { return true;}

    default void visitLeave(Title title) { }

    default boolean visitEnter(Article article) { return true;}

    default void visitLeave(Article article) { }

    default boolean visitEnter(Book book) { return true;}

    default void visitLeave(Book book) { }

    default boolean visitEnter(Tome tome) { return true;}

    default void visitLeave(Tome tome) { }

    default boolean visitEnter(Division division) { return true;}

    default void visitLeave(Division division) { }

    default boolean visitEnter(List list) { return true;}

    default void visitLeave(List list) { }

    default boolean visitEnter(Point point) { return true;}

    default void visitLeave(Point point) { }

    default boolean visitEnter(Indent indent) { return true;}

    default void visitLeave(Indent indent) { }

    default boolean visitEnter(Alinea alinea) { return true;}

    default void visitLeave(Alinea alinea) { }

    default boolean visitEnter(Rule rule) { return true;}

    default void visitLeave(Rule rule) { }

    default boolean visitEnter(Subrule subrule) { return true;}

    default void visitLeave(Subrule subrule) { }

    default boolean visitEnter(Proviso proviso) { return true;}

    default void visitLeave(Proviso proviso) { }

    default boolean visitEnter(Subsection subsection) { return true;}

    default void visitLeave(Subsection subsection) { }

    default boolean visitEnter(Subpart subpart) { return true;}

    default void visitLeave(Subpart subpart) { }

    default boolean visitEnter(Subparagraph subparagraph) { return true;}

    default void visitLeave(Subparagraph subparagraph) { }

    default boolean visitEnter(Subchapter subchapter) { return true;}

    default void visitLeave(Subchapter subchapter) { }

    default boolean visitEnter(Subtitle subtitle) { return true;}

    default void visitLeave(Subtitle subtitle) { }

    default boolean visitEnter(Subdivision subdivision) { return true;}

    default void visitLeave(Subdivision subdivision) { }

    default boolean visitEnter(Subclause subclause) { return true;}

    default void visitLeave(Subclause subclause) { }

    default boolean visitEnter(Sublist sublist) { return true;}

    default void visitLeave(Sublist sublist) { }

    default boolean visitEnter(Level level) { return true;}

    default void visitLeave(Level level) { }

    default boolean visitEnter(Transitional transitional) { return true;}

    default void visitLeave(Transitional transitional) { }

}