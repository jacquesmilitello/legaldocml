package io.legaldocml.diff;

import io.legaldocml.ReaderHelper;
import io.legaldocml.akn.AkomaNtoso;
import io.legaldocml.akn.DocumentType;
import io.legaldocml.test.PathForTest;
import org.junit.jupiter.api.Test;

import java.io.IOException;

public class DiffTest {

    @Test
    public void test() throws IOException {
        AkomaNtoso<DocumentType> akn = ReaderHelper.read(PathForTest.path("/xml/v3/us_Title9-Chap3-eng.xml"));

        akn.compare(akn, new DiffContext());

    }
}
