package io.legaldocml.xpath;

import io.legaldocml.akn.AkomaNtoso;
import io.legaldocml.akn.element.Voting;
import io.legaldocml.test.PathForTest;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.util.Optional;

public class XpathTest {

    private AkomaNtoso<?> akomaNtoso;
    private XPath xpath;

    @BeforeEach
    public void before() throws IOException {
        XPathFactory xPathfactory = XPathFactory.newInstance();
        xpath = xPathfactory.newXPath();
        akomaNtoso = ReaderHelper.read(PathForTest.path("/xml/v3/cl_Sesion56_2.xml"));
    }


    @Test
    public void testAbsoluteSingleNode()  {
        XPathExpression expr = xpath.compile("/akomaNtoso/debate/meta/identification");
        Assertions.assertSame(akomaNtoso.getDocumentType().getMeta().getIdentification(),expr.evaluate(akomaNtoso).get());
    }

    @Test
    public void testAbsoluteNull() {
        XPathExpression expr = xpath.compile("/akomaNtoso/bill/meta/identification");
        Assertions.assertSame(Optional.empty(),expr.evaluate(akomaNtoso));
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
        Assertions.assertEquals("v_1", ((Voting)expr.evaluate(akomaNtoso).get()).getEid().toString());
    }

}
