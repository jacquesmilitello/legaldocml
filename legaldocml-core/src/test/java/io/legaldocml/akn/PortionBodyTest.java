package io.legaldocml.akn;

import io.legaldocml.ReaderHelper;
import io.legaldocml.akn.element.Chapter;
import io.legaldocml.akn.element.Portion;
import io.legaldocml.akn.element.PortionBody;
import io.legaldocml.akn.element.Section;
import io.legaldocml.akn.type.NoWhiteSpace;
import io.legaldocml.test.PathForTest;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.util.Optional;
import java.util.concurrent.atomic.AtomicInteger;

import static org.junit.jupiter.api.Assertions.assertEquals;

class PortionBodyTest {


    @Test
    void testPortionBody() throws IOException {
        AkomaNtoso<Portion> akn = ReaderHelper.read(PathForTest.path("/xml/v3/us_Title9-Chap3-eng.xml"));
        PortionBody body = akn.getDocumentType().getPortionBody();
        assertEquals(1, body.stream().count());
        assertEquals("chp_3", body.stream().findFirst().map(Chapter.class::cast).map(Chapter::getEid).get().toString());
    }

    @Test
    void testChapterBody() throws IOException {
        AkomaNtoso<Portion> akn = ReaderHelper.read(PathForTest.path("/xml/v3/us_Title9-Chap3-eng.xml"));
        PortionBody body = akn.getDocumentType().getPortionBody();

        Chapter chapter = body.stream().findFirst().map(Chapter.class::cast).get();
        assertEquals(7, chapter.stream().count());

        AtomicInteger i = new AtomicInteger(1);
        chapter.stream().map(Section.class::cast).forEach(
                t -> assertEquals("sec_30" + i.getAndIncrement(), t.getEid().toString())
        );

        Assertions.assertTrue(chapter.removeHierarchyElement(chapter.stream().findFirst().get()));
        assertEquals(6, chapter.stream().count());

        Section section = new Section();
        section.setEid(NoWhiteSpace.valueOf("sec_305"));
        Assertions.assertTrue(chapter.removeHierarchyElement(section));

        section.setEid(NoWhiteSpace.valueOf("sec_306"));
        Assertions.assertTrue(chapter.removeHierarchyElement(section));

        section.setEid(NoWhiteSpace.valueOf("sec_307"));
        Assertions.assertTrue(chapter.removeHierarchyElement(section));

        section.setEid(NoWhiteSpace.valueOf("sec_308"));
        Assertions.assertFalse(chapter.removeHierarchyElement(section));

        AtomicInteger j = new AtomicInteger(2);
        chapter.stream().map(Section.class::cast).forEach(
                t -> assertEquals("sec_30" + j.getAndIncrement(), t.getEid().toString())
        );

        section = Optional.of(chapter.remove(0)).map(Section.class::cast).get();

        Assertions.assertEquals("sec_302", section.getEid().toString());
    }



}