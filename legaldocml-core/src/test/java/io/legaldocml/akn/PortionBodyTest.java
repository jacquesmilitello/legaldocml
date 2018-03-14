package io.legaldocml.akn;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.io.IOException;
import java.util.concurrent.atomic.AtomicInteger;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import io.legaldocml.akn.element.Chapter;
import io.legaldocml.akn.element.Portion;
import io.legaldocml.akn.element.PortionBody;
import io.legaldocml.akn.element.Section;
import io.legaldocml.akn.type.NoWhiteSpace;
import io.legaldocml.io.XmlProvider;
import io.legaldocml.test.PathForTest;

class PortionBodyTest {


    @Test
    void testPortionBody() throws IOException {
        AkomaNtoso<Portion> akn = XmlProvider.readerFactory().read(PathForTest.path("/xml/v3/us_Title9-Chap3-eng.xml"));
        PortionBody body = akn.getDocumentType().getPortionBody();
        assertEquals(1, body.iterable().size());
        assertEquals("chp_3", body.iterable().stream().findFirst().map(Chapter.class::cast).map(Chapter::getEid).get().toString());
    }

    @Test
    void testChapterBody() throws IOException {
        AkomaNtoso<Portion> akn = XmlProvider.readerFactory().read(PathForTest.path("/xml/v3/us_Title9-Chap3-eng.xml"));
        PortionBody body = akn.getDocumentType().getPortionBody();

        Chapter chapter = body.iterable().stream().findFirst().map(Chapter.class::cast).get();
        assertEquals(7, chapter.iterable().stream().count());

        AtomicInteger i = new AtomicInteger(1);
        chapter.iterable().stream().map(Section.class::cast).forEach(
                t -> assertEquals("sec_30" + i.getAndIncrement(), t.getEid().toString())
        );

        Assertions.assertTrue(chapter.removeHierarchyElement(chapter.iterable().stream().findFirst().get()));
        assertEquals(6, chapter.iterable().stream().count());

        Section section = new Section();
        section.setEid(NoWhiteSpace.valueOf("sec_305"));
        section.setGUID(NoWhiteSpace.valueOf("idd1d32352-f639-11e2-8470-abc29ba29c4d"));
        Assertions.assertTrue(chapter.removeHierarchyElement(section));


        section.setEid(NoWhiteSpace.valueOf("sec_306"));
        section.setGUID(NoWhiteSpace.valueOf("idd1d32356-f639-11e2-8470-abc29ba29c4d"));
        Assertions.assertTrue(chapter.remove(section));

        section.setEid(NoWhiteSpace.valueOf("sec_307"));
        section.setGUID(NoWhiteSpace.valueOf("idd1d34a6a-f639-11e2-8470-abc29ba29c4d"));
        Assertions.assertTrue(chapter.removeHierarchyElement(section));

        section.setEid(NoWhiteSpace.valueOf("sec_308"));
        Assertions.assertFalse(chapter.remove(section));

        AtomicInteger j = new AtomicInteger(2);
        chapter.iterable().stream().map(Section.class::cast).forEach(
                t -> assertEquals("sec_30" + j.getAndIncrement(), t.getEid().toString())
        );

    }



}
