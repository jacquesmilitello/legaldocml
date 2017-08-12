package io.legaldocml.akn.v3;

import io.legaldocml.akn.AkomaNtoso;
import io.legaldocml.akn.ReaderHelper;
import io.legaldocml.akn.element.CoverPage;
import io.legaldocml.akn.element.Meta;
import io.legaldocml.akn.element.Preface;
import io.legaldocml.test.PathForTest;
import io.legaldocml.util.ToStringVisitor;
import org.junit.Test;

public class VisitorTest {


    @Test
    public void simpleVisitor() throws Exception {

        AkomaNtoso ako = ReaderHelper.read(PathForTest.path("/xml/v3/cl_Sesion56_2.xml"));


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

        AkomaNtoso ako = ReaderHelper.read(PathForTest.path("/xml/v3/it_senato_ddl_2013.xml"));


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

        AkomaNtoso ako = ReaderHelper.read(PathForTest.path("/xml/v3/uy_bill_2010-09-27.xml"));


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
