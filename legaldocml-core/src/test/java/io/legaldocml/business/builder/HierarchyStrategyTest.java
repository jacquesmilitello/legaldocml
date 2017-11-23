package io.legaldocml.business.builder;

import io.legaldocml.akn.element.Book;
import io.legaldocml.akn.element.Chapter;
import io.legaldocml.akn.element.Subsection;
import io.legaldocml.akn.element.Tome;
import io.legaldocml.test.LoggerInstancePostProcessor;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;

import static org.junit.jupiter.api.Assertions.assertEquals;

/**
 * @author <a href="mailto:jacques.militello@gmail.com">Jacques Militello</a>
 */
@ExtendWith(LoggerInstancePostProcessor.class)
public class HierarchyStrategyTest {

    @Test
    public void testBuild() {
        HierarchyStrategyBuilder hsb = new HierarchyStrategyBuilder();
        hsb.tome();
        hsb.part();
        hsb.book();
        hsb.title();
        hsb.chapter();
        hsb.section();
        hsb.subSection();
        HierarchyStrategy strategy = hsb.build();
        assertEquals(Book.class, strategy.next(strategy.next(new Tome())).getClass());
        assertEquals(Chapter.class, strategy.next(strategy.next(new Book())).getClass());
        assertEquals(Subsection.class, strategy.next(strategy.next(new Chapter())).getClass());


    }

}
