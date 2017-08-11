package io.legaldocml.xpath.grammar;

import io.legaldocml.akn.AknObject;
import io.legaldocml.akn.AkomaNtoso;
import io.legaldocml.akn.element.Debate;
import io.legaldocml.xpath.Xpath;
import io.legaldocml.xpath.XpathResult;
import io.legaldocml.xpath.XpathResultType;
import io.legaldocml.xpath.Xpaths;
import org.junit.Assert;
import org.junit.Test;

/**
 * Created by jacques.militello on 07/06/2017.
 */
public class XpathTest {

    private static final AkomaNtoso<Debate> AKN = ReaderHelper.read("/xml/cl_Sesion56_2.xml");

    @Test
    public void testAbsoluteGet() {

        Xpath xpath = Xpaths.compile("/akomaNtoso/debate");
        XpathResult<AknObject> result = xpath.execute(AKN);

        Assert.assertEquals(XpathResultType.NODE, result.type());
        Assert.assertEquals(Debate.ELEMENT, result.get().name());


    }
}
