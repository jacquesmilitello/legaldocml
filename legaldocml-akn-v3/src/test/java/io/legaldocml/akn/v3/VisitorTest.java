package io.legaldocml.akn.v3;

import io.legaldocml.akn.AkomaNtoso;
import io.legaldocml.akn.ReaderHelper;
import io.legaldocml.akn.element.CoverPage;
import io.legaldocml.akn.element.Meta;
import io.legaldocml.akn.element.Preface;
import io.legaldocml.util.ToStringVisitor;
import org.junit.Test;

import java.nio.file.Paths;

public class VisitorTest {


    @Test
    public void simpleVisitor() throws Exception {

        AkomaNtoso ako = ReaderHelper.read(Paths.get(AknTest.class.getResource("/xml/cl_Sesion56_2.xml").toURI()));


        ToStringVisitor visitor = new ToStringVisitor() {
            public boolean visitEnter(Meta meta) {
                return false;
            }

            public boolean visitEnter(CoverPage coverPage) {
                return false;
            }

            public boolean visitEnter(Preface preface) {
                return false;
            }
        };


        ako.accept(visitor);

    }


    @Test
    public void testBillVisitor() throws Exception {

        AkomaNtoso ako = ReaderHelper.read(Paths.get(AknTest.class.getResource("/xml/it_senato_ddl_2013.xml").toURI()));


        ToStringVisitor visitor = new ToStringVisitor() {
            public boolean visitEnter(Meta meta) {
                return false;
            }

            public boolean visitEnter(CoverPage coverPage) {
                return false;
            }

            public boolean visitEnter(Preface preface) {
                return false;
            }
        };


        ako.accept(visitor);

    }

    @Test
    public void testDocumentCollectionVisitor() throws Exception {

        AkomaNtoso ako = ReaderHelper.read(Paths.get(AknTest.class.getResource("/xml/uy_bill_2010-09-27.xml").toURI()));


        ToStringVisitor visitor = new ToStringVisitor() {
            public boolean visitEnter(Meta meta) {
                return false;
            }

            public boolean visitEnter(CoverPage coverPage) {
                return false;
            }

            public boolean visitEnter(Preface preface) {
                return false;
            }
        };


        ako.accept(visitor);

    }




}
