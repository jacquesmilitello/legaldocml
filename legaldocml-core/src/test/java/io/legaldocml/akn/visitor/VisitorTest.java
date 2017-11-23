package io.legaldocml.akn.visitor;

import io.legaldocml.ReaderHelper;
import io.legaldocml.akn.AkomaNtoso;
import io.legaldocml.test.PathForTest;
import org.junit.jupiter.api.Test;

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

        akn = ReaderHelper.read(PathForTest.path("/xml/v3/us_Act_2011-11-29.xml"));
        akn.accept(new AknVisitor() {
        });

        akn = ReaderHelper.read(PathForTest.path("/xml/v3/za_Judgement_2008-11-26.xml"));
        akn.accept(new AknVisitor() {
        });

        akn = ReaderHelper.read(PathForTest.path("/xml/v3/us_Title9-Chap3-eng.xml"));
        akn.accept(new AknVisitor() {
        });

        akn = ReaderHelper.read(PathForTest.path("/xml/v3/uk_pga-2014-27-enacted-data.xml"));
        akn.accept(new AknVisitor() {
        });

        akn = ReaderHelper.read(PathForTest.path("/xml/v3/ke_Debate_Bungeni_2011-06-10.xml"));
        akn.accept(new AknVisitor() {
        });

        akn = ReaderHelper.read(PathForTest.path("/xml/v3/eu_COM(2013)0619_EN-8.xml"));
        akn.accept(new AknVisitor() {
        });

        akn = ReaderHelper.read(PathForTest.path("/xml/v2/Act_constitution_final-Kenya.xml"));
        akn.accept(new AknVisitor() {
        });

        akn = ReaderHelper.read(PathForTest.path("/xml/v2/Act_Instrument_Scotland_Subsidiarity_2010.xml"));
        akn.accept(new AknVisitor() {
        });

        akn = ReaderHelper.read(PathForTest.path("/xml/v2/Act_Panama_decreto_223_2010.xml"));
        akn.accept(new AknVisitor() {
        });

        akn = ReaderHelper.read(PathForTest.path("/xml/v2/Act_Salvador_Ley_604_1993.xml"));
        akn.accept(new AknVisitor() {
        });

        akn = ReaderHelper.read(PathForTest.path("/xml/v2/Act_USA_2011-11-29-short.xml"));
        akn.accept(new AknVisitor() {
        });
        
    }

    @Test
    public void testAmendment() throws IOException {
        AkomaNtoso akn = ReaderHelper.read(PathForTest.path("/xml/v2/Amendemet_Bungeni_2011-07-20.xml"));
        akn.accept(new AknVisitor() {
        });
    }

    @Test
    public void testAmendmentList() throws IOException {
        AkomaNtoso akn = ReaderHelper.read(PathForTest.path("/xml/v2/AmendmentList_Bungeni_2010-10-28v2.xml"));
        akn.accept(new AknVisitor() {
        });
    }

    @Test
    public void testBill() throws IOException {
        AkomaNtoso akn = ReaderHelper.read(PathForTest.path("/xml/v2/Bill_Bungeni_2009-01-27.xml"));
        akn.accept(new AknVisitor() {
        });

        akn = ReaderHelper.read(PathForTest.path("/xml/v2/Bill_Bungeni_2009-01-27-documentCollection.xml"));
        akn.accept(new AknVisitor() {
        });

        akn = ReaderHelper.read(PathForTest.path("/xml/v2/Bill_Bungeni_2010-07-10-memorandum.xml"));
        akn.accept(new AknVisitor() {
        });

        akn = ReaderHelper.read(PathForTest.path("/xml/v2/Bill_Bungeni_2010-10-10-memorandum.xml"));
        akn.accept(new AknVisitor() {
        });

        akn = ReaderHelper.read(PathForTest.path("/xml/v2/Bill_Bungeni_2010-11-19-coverpage.xml"));
        akn.accept(new AknVisitor() {
        });

        akn = ReaderHelper.read(PathForTest.path("/xml/v2/Bill_Bungeni_2011-06-30v1.xml"));
        akn.accept(new AknVisitor() {
        });

        akn = ReaderHelper.read(PathForTest.path("/xml/v2/Bill_Bungeni_2011-06-30v1-documentCollection.xml"));
        akn.accept(new AknVisitor() {
        });

        akn = ReaderHelper.read(PathForTest.path("/xml/v2/Bill_Bungeni_2011-12-19-collect.xml"));
        akn.accept(new AknVisitor() {
        });

    }

}
