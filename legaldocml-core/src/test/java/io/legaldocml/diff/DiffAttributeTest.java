package io.legaldocml.diff;

import io.legaldocml.ReaderHelper;
import io.legaldocml.akn.AkomaNtoso;
import io.legaldocml.akn.DocumentType;
import io.legaldocml.akn.element.TLCPerson;
import io.legaldocml.akn.type.Uri;
import io.legaldocml.diff.impl.DiffBuilder;
import io.legaldocml.test.PathForTest;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.util.List;
import java.util.stream.Collectors;

import static io.legaldocml.akn.type.NoWhiteSpace.valueOf;
import static io.legaldocml.akn.util.TLCFactory.newTLCPerson;

class DiffAttributeTest {

    @Test
    void testSame() throws IOException {
        AkomaNtoso<DocumentType> akn = ReaderHelper.read(PathForTest.path("/xml/v3/us_Title9-Chap3-eng.xml"));
        DiffResult result = new DiffBuilder<AkomaNtoso<DocumentType>>().left(akn).right(akn).build();
        Assertions.assertEquals(0,result.count());
    }


    @Test
    void testOneAttributeHasDifferentValue() {
        TLCPerson left = newTLCPerson(valueOf("vergottini"), Uri.valueOf("/akn/us/ontology/person/somebody"), "Grant Vergottini");
        TLCPerson right = newTLCPerson(valueOf("vergottini2"), Uri.valueOf("/akn/us/ontology/person/somebody"), "Grant Vergottini");

        DiffResult result = new DiffBuilder<TLCPerson>().left(left).right(right).build();

        Assertions.assertEquals(1, result.count());

        Diff diff = result.stream().findFirst().get();

        Assertions.assertEquals("/TLCPerson[@eId]", diff.getPath());
        Assertions.assertEquals(DiffType.ATTRIBUTE_VALUE, diff.getType());

        AttributeDiff ad = (AttributeDiff) diff;
        Assertions.assertEquals(left, ad.getLeft());
        Assertions.assertEquals(right, ad.getRight());
        Assertions.assertEquals(valueOf("vergottini"), ad.getLeftValue());
        Assertions.assertEquals(valueOf("vergottini2"), ad.getRightValue());


    }

    @Test
    void test3diffrentValue() {
        TLCPerson left = newTLCPerson(valueOf("vergottini"), Uri.valueOf("/akn/us/ontology/person/somebody"), "Grant Vergottini");
        TLCPerson right = new TLCPerson();
        right.setShortForm("shortForm");

        DiffResult result = new DiffBuilder<TLCPerson>().left(left).right(right).build();

        Assertions.assertEquals(4, result.count());

        List<Diff> diffs = result.stream().collect(Collectors.toList());

        Diff diff = diffs.get(0);
        Assertions.assertEquals("/TLCPerson[@eId]", diff.getPath());
        Assertions.assertEquals(DiffType.ATTRIBUTE_REMOVED, diff.getType());
        AttributeDiff ad = (AttributeDiff) diff;
        Assertions.assertEquals(left, ad.getLeft());
        Assertions.assertEquals(right, ad.getRight());
        Assertions.assertEquals(valueOf("vergottini"), ad.getLeftValue());
        Assertions.assertNull(ad.getRightValue());

        diff = diffs.get(1);
        Assertions.assertEquals("/TLCPerson[@showAs]", diff.getPath());
        Assertions.assertEquals(DiffType.ATTRIBUTE_REMOVED, diff.getType());
        ad = (AttributeDiff) diff;
        Assertions.assertEquals(left, ad.getLeft());
        Assertions.assertEquals(right, ad.getRight());
        Assertions.assertEquals("Grant Vergottini", ad.getLeftValue());
        Assertions.assertNull(ad.getRightValue());

        diff = diffs.get(2);
        Assertions.assertEquals("/TLCPerson[@shortForm]", diff.getPath());
        Assertions.assertEquals(DiffType.ATTRIBUTE_INSERTED, diff.getType());
        ad = (AttributeDiff) diff;
        Assertions.assertEquals(left, ad.getLeft());
        Assertions.assertEquals(right, ad.getRight());
        Assertions.assertEquals("shortForm",ad.getRightValue());
        Assertions.assertNull(ad.getLeftValue());


        diff = diffs.get(3);
        Assertions.assertEquals("/TLCPerson[@href]", diff.getPath());
        Assertions.assertEquals(DiffType.ATTRIBUTE_REMOVED, diff.getType());
        ad = (AttributeDiff) diff;
        Assertions.assertEquals(left, ad.getLeft());
        Assertions.assertEquals(right, ad.getRight());
        Assertions.assertEquals(Uri.valueOf("/akn/us/ontology/person/somebody"), ad.getLeftValue());
        Assertions.assertNull(ad.getRightValue());

    }
}
