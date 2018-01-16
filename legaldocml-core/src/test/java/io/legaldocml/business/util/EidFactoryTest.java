package io.legaldocml.business.util;

import io.legaldocml.akn.element.Alinea;
import io.legaldocml.akn.element.AmendmentBody;
import io.legaldocml.akn.element.Article;
import io.legaldocml.akn.element.BlockList;
import io.legaldocml.akn.element.Body;
import io.legaldocml.akn.element.DebateBody;
import io.legaldocml.akn.element.Intro;
import io.legaldocml.akn.element.JudgmentBody;
import io.legaldocml.akn.element.List;
import io.legaldocml.akn.element.ListIntroduction;
import io.legaldocml.akn.element.MainBody;
import io.legaldocml.akn.element.Point;
import io.legaldocml.akn.element.WrapUp;
import io.legaldocml.akn.type.NoWhiteSpace;
import io.legaldocml.test.LoggerInstancePostProcessor;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertSame;

@ExtendWith(LoggerInstancePostProcessor.class)
class EidFactoryTest {

    /**
     * Test : http://docs.oasis-open.org/legaldocml/akn-nc/v1.0/akn-nc-v1.0.html
     * part 5.4.2 part1
     */
    @Test
    void testPart01() {
        assertEquals("list", EidFactory.getElementRef(List.class));
        assertEquals("list", EidFactory.getElementRef(BlockList.class));
        assertEquals("intro", EidFactory.getElementRef(Intro.class));
        assertEquals("intro", EidFactory.getElementRef(ListIntroduction.class));
        assertEquals("wrapup", EidFactory.getElementRef(WrapUp.class));
        // for the part 02 -> wrap
        // Assert.assertEquals("wrapup", EidFactory.getElementRef(ListWrapUp.class));
        assertEquals("body", EidFactory.getElementRef(Body.class));
        assertEquals("body", EidFactory.getElementRef(MainBody.class));
        assertEquals("body", EidFactory.getElementRef(AmendmentBody.class));
        assertEquals("body", EidFactory.getElementRef(DebateBody.class));
        assertEquals("body", EidFactory.getElementRef(JudgmentBody.class));
    }

    /**
     * Test : http://docs.oasis-open.org/legaldocml/akn-nc/v1.0/akn-nc-v1.0.html
     * part 5.4.2 part 2
     */
    @Test
    void testPart02() {
        assertEquals("al", EidFactory.getElementRef(Alinea.class));
        assertEquals("body", EidFactory.getElementRef(Body.class));
    }

    @Test
    void testEidBuilderWithoutParent() {
        Article article = new Article();
        NoWhiteSpace space = EidFactory.make(article, "1");
        assertEquals("art_1", space.toString());
        assertNull(article.getEid());

        EidFactory.makeAndFill(article, "1");
        assertEquals("art_1", article.getEid().toString());
        assertEquals(space, article.getEid());

        assertSame(article.getEid(),EidFactory.make(article, "1"));
    }


    @Test
    void testEidBuilderWithParent() {
        Article article = new Article();
        EidFactory.makeAndFill(article, "1");
        Point point = new Point();
        EidFactory.makeAndFill(article, point, "a");
        assertEquals("art_1__point_a", point.getId());
    }

    @Test
    void testEidBuilderWithParent2() {
        Article article = new Article();
        EidFactory.makeAndFill(article, "1");
        Point point = new Point();
        EidFactory.makeAndFill(article, point);
        assertEquals("art_1__point", point.getId());
    }

    @Test
    void testEidBuilderWithParentWithoutNumber() {
        Article article = new Article();
        EidFactory.makeAndFill(article);
        Point point = new Point();
        EidFactory.makeAndFill(article, point);
        assertEquals("art__point", point.getId());
    }

}
