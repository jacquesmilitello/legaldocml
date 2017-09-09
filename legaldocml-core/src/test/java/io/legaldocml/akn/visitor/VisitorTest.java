package io.legaldocml.akn.visitor;

import io.legaldocml.ReaderHelper;
import io.legaldocml.akn.AkomaNtoso;
import io.legaldocml.test.PathForTest;
import org.junit.Test;

import java.io.IOException;

public class VisitorTest {

    @Test
    public void test() throws IOException {

        AkomaNtoso akn = ReaderHelper.read(PathForTest.path("/xml/v3/cl_Sesion56_2.xml"));
        akn.accept(new AknVisitor() {
        });

        akn = ReaderHelper.read(PathForTest.path("/xml/v3/it_senato_ddl_2013.xml"));
        akn.accept(new AknVisitor() {
        });

        akn = ReaderHelper.read(PathForTest.path("/xml/v3/uy_bill_2010-09-27.xml"));
        akn.accept(new AknVisitor() {
        });


        /*
        test("/xml/v3/us_Act_2011-11-29.xml");
        test("/xml/v3/za_Judgement_2008-11-26.xml");
        test("/xml/v3/us_Title9-Chap3-eng.xml");
        test("/xml/v3/uk_pga-2014-27-enacted-data.xml");
        test("/xml/v3/ke_Debate_Bungeni_2011-06-10.xml");
        test("/xml/v3/eu_COM(2013)0619_EN-8.xml");*/
    }
}
