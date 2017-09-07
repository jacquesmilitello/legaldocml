package io.legaldocml.akn;

import io.legaldocml.akn.element.Alinea;
import io.legaldocml.akn.element.Amendment;
import io.legaldocml.test.SonarJUnit4ClassRunner;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;

@RunWith(SonarJUnit4ClassRunner.class)
public class AknElementsTest {

    @Test
    public void testExists() {
        Assert.assertTrue(AknElements.exists("alinea"));
        Assert.assertTrue(AknElements.exists(new Amendment().name()));
        Assert.assertFalse(AknElements.exists("toto"));
    }

    @Test
    public void testClass() {
        Assert.assertEquals(Alinea.class, AknElements.getAknClass("alinea"));
        Assert.assertEquals(Amendment.class, AknElements.getAknClass(new Amendment().name()));
    }
}
