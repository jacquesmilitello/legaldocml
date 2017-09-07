package io.legaldocml.xpath;

import io.legaldocml.akn.AkomaNtoso;
import io.legaldocml.akn.element.Voting;
import io.legaldocml.test.PathForTest;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.io.IOException;
import java.util.Optional;

public class XpathTest {

    private AkomaNtoso<?> akomaNtoso;
    private XPath xpath;

    @Before
    public void before() throws IOException {
        XPathFactory xPathfactory = XPathFactory.newInstance();
        xpath = xPathfactory.newXPath();
        akomaNtoso = ReaderHelper.read(PathForTest.path("/xml/v3/cl_Sesion56_2.xml"));
    }


    @Test
    public void testAbsoluteSingleNode()  {
        XPathExpression expr = xpath.compile("/akomaNtoso/debate/meta/identification");
        Assert.assertSame(akomaNtoso.getDocumentType().getMeta().getIdentification(),expr.evaluate(akomaNtoso).get());
    }

    @Test
    public void testAbsoluteNull() {
        XPathExpression expr = xpath.compile("/akomaNtoso/bill/meta/identification");
        Assert.assertSame(Optional.empty(),expr.evaluate(akomaNtoso));
    }


    @Test
    public void testAbsoluteList()  {
        XPathExpression expr = xpath.compile("/akomaNtoso/debate/meta/analysis");
        System.out.println(expr.evaluate(akomaNtoso).get());

        expr = xpath.compile("/akomaNtoso/debate/meta/analysis/parliamentary");
        System.out.println(expr.evaluate(akomaNtoso).get());

        expr = xpath.compile("/akomaNtoso/debate/meta/analysis/parliamentary/voting");
        System.out.println(expr.evaluate(akomaNtoso).get());

        expr = xpath.compile("/akomaNtoso/debate/meta/analysis/parliamentary/voting[0]");
        Assert.assertEquals("v_1", ((Voting)expr.evaluate(akomaNtoso).get()).getEid().toString());
    }

}
