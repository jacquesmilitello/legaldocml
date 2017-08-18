package io.legaldocml.business.util;

import io.legaldocml.akn.element.Alinea;
import io.legaldocml.akn.element.AmendmentBody;
import io.legaldocml.akn.element.BlockList;
import io.legaldocml.akn.element.Body;
import io.legaldocml.akn.element.DebateBody;
import io.legaldocml.akn.element.Intro;
import io.legaldocml.akn.element.JudgmentBody;
import io.legaldocml.akn.element.List;
import io.legaldocml.akn.element.ListIntroduction;
import io.legaldocml.akn.element.MainBody;
import io.legaldocml.akn.element.WrapUp;
import org.junit.Assert;
import org.junit.Test;

public class IdHelperTest {

    /**
     * Test : http://docs.oasis-open.org/legaldocml/akn-nc/v1.0/akn-nc-v1.0.html
     * part 5.4.2 part1
     */
    @Test
    public void testPart01() {
        Assert.assertEquals("list", IdHelper.getElementRef(List.class));
        Assert.assertEquals("list", IdHelper.getElementRef(BlockList.class));
        Assert.assertEquals("intro", IdHelper.getElementRef(Intro.class));
        Assert.assertEquals("intro", IdHelper.getElementRef(ListIntroduction.class));
        Assert.assertEquals("wrapup", IdHelper.getElementRef(WrapUp.class));
        // for the part 02 -> wrap
        // Assert.assertEquals("wrapup", IdHelper.getElementRef(ListWrapUp.class));
        Assert.assertEquals("body", IdHelper.getElementRef(Body.class));
        Assert.assertEquals("body", IdHelper.getElementRef(MainBody.class));
        Assert.assertEquals("body", IdHelper.getElementRef(AmendmentBody.class));
        Assert.assertEquals("body", IdHelper.getElementRef(DebateBody.class));
        Assert.assertEquals("body", IdHelper.getElementRef(JudgmentBody.class));
    }

    /**
     * Test : http://docs.oasis-open.org/legaldocml/akn-nc/v1.0/akn-nc-v1.0.html
     * part 5.4.2 part 2
     */
    @Test
    public void testPart02() {
        Assert.assertEquals("al", IdHelper.getElementRef(Alinea.class));
        Assert.assertEquals("body", IdHelper.getElementRef(Body.class));
    }
}
