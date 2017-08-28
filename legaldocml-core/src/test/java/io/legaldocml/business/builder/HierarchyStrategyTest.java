package io.legaldocml.business.builder;

import io.legaldocml.akn.element.Book;
import io.legaldocml.akn.element.Chapter;
import io.legaldocml.akn.element.SubSection;
import io.legaldocml.akn.element.Tome;
import org.junit.Assert;
import org.junit.Test;

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
        Assert.assertEquals(Book.class, strategy.next(strategy.next(new Tome())).getClass());
        Assert.assertEquals(Chapter.class, strategy.next(strategy.next(new Book())).getClass());
        Assert.assertEquals(SubSection.class, strategy.next(strategy.next(new Chapter())).getClass());


    }

}
