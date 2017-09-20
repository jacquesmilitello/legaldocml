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
import io.legaldocml.test.SonarJUnit4ClassRunner;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;

@RunWith(SonarJUnit4ClassRunner.class)
public class EidFactoryTest {

    /**
     * Test : http://docs.oasis-open.org/legaldocml/akn-nc/v1.0/akn-nc-v1.0.html
     * part 5.4.2 part1
     */
    @Test
    public void testPart01() {
        Assert.assertEquals("list", EidFactory.getElementRef(List.class));
        Assert.assertEquals("list", EidFactory.getElementRef(BlockList.class));
        Assert.assertEquals("intro", EidFactory.getElementRef(Intro.class));
        Assert.assertEquals("intro", EidFactory.getElementRef(ListIntroduction.class));
        Assert.assertEquals("wrapup", EidFactory.getElementRef(WrapUp.class));
        // for the part 02 -> wrap
        // Assert.assertEquals("wrapup", EidFactory.getElementRef(ListWrapUp.class));
        Assert.assertEquals("body", EidFactory.getElementRef(Body.class));
        Assert.assertEquals("body", EidFactory.getElementRef(MainBody.class));
        Assert.assertEquals("body", EidFactory.getElementRef(AmendmentBody.class));
        Assert.assertEquals("body", EidFactory.getElementRef(DebateBody.class));
        Assert.assertEquals("body", EidFactory.getElementRef(JudgmentBody.class));
    }

    /**
     * Test : http://docs.oasis-open.org/legaldocml/akn-nc/v1.0/akn-nc-v1.0.html
     * part 5.4.2 part 2
     */
    @Test
    public void testPart02() {
        Assert.assertEquals("al", EidFactory.getElementRef(Alinea.class));
        Assert.assertEquals("body", EidFactory.getElementRef(Body.class));
    }

    @Test
    public void testEidBuilderWithoutParent() {
        Article article = new Article();
        NoWhiteSpace space = EidFactory.make(article, "1");
        Assert.assertEquals("art_1", space.toString());
        Assert.assertNull(article.getEid());

        EidFactory.makeAndFill(article, "1");
        Assert.assertEquals("art_1", article.getEid().toString());
        Assert.assertEquals(space, article.getEid());

        Assert.assertSame(article.getEid(),EidFactory.make(article, "1"));
    }


    @Test
    public void testEidBuilderWithParent() {
        Article article = new Article();
        EidFactory.makeAndFill(article, "1");
        Point point = new Point();
        EidFactory.makeAndFill(article, point, "a");
        Assert.assertEquals("art_1__point_a", point.getId());
    }

    @Test
    public void testEidBuilderWithParent2() {
        Article article = new Article();
        EidFactory.makeAndFill(article, "1");
        Point point = new Point();
        EidFactory.makeAndFill(article, point);
        Assert.assertEquals("art_1__point", point.getId());
    }

    @Test
    public void testEidBuilderWithParentWithoutNumber() {
        Article article = new Article();
        EidFactory.makeAndFill(article);
        Point point = new Point();
        EidFactory.makeAndFill(article, point);
        Assert.assertEquals("art__point", point.getId());
    }

}
