package io.legaldocml.akn.type;

import io.legaldocml.akn.AttributeValueException;
import io.legaldocml.test.SonarJUnit4ClassRunner;
import org.junit.Assert;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;
import org.junit.runner.RunWith;

@RunWith(SonarJUnit4ClassRunner.class)
public class AgentRefTest {

    @Rule
    public ExpectedException thrown = ExpectedException.none();

    @Test
    public void testWithRef() {
        Assert.assertEquals("#toto", AgentRef.valueOf("#toto").ref());
        Assert.assertEquals("#toto", AgentRef.valueOf("#toto".toCharArray()).ref());
        Assert.assertEquals("#toto", AgentRef.raw("#toto".toCharArray()).ref());
    }

    @Test
    public void testWithOutRef() {
        Assert.assertEquals("#toto", AgentRef.valueOf("toto").ref());
        Assert.assertEquals("#toto", AgentRef.valueOf("toto".toCharArray()).ref());
        Assert.assertEquals("toto", AgentRef.raw("toto".toCharArray()).ref());
    }

    @Test
    public void testBadValueOnValueOf01() {
        thrown.expect(AttributeValueException.class);
        thrown.expectMessage("null");
        AgentRef.valueOf((String)null);
    }

    @Test
    public void testBadValueOnValueOf02() {
        thrown.expect(AttributeValueException.class);
        thrown.expectMessage("null");
        AgentRef.valueOf((char[]) null);
    }

    @Test
    public void testBadValueOnValueOf03() {
        thrown.expect(AttributeValueException.class);
        thrown.expectMessage("[]");
        AgentRef.valueOf("");
    }

    @Test
    public void testBadValueOnValueOf04() {
        thrown.expect(AttributeValueException.class);
        thrown.expectMessage("[]");
        AgentRef.valueOf("".toCharArray());
    }
}
