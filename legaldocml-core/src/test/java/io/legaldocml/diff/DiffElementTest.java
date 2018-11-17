package io.legaldocml.diff;

import io.legaldocml.akn.element.Chapter;
import io.legaldocml.akn.element.Section;
import io.legaldocml.akn.type.NoWhiteSpace;
import io.legaldocml.diff.impl.DiffBuilder;

import static org.junit.jupiter.api.Assertions.assertEquals;

class DiffElementTest {

    //@Test
    void testInserted() {

        Chapter left = new Chapter();
        left.setEid(NoWhiteSpace.valueOf("chp_001"));

        Chapter right = new Chapter();
        right.setEid(NoWhiteSpace.valueOf("chp_001"));

        Section section = new Section();
        section.setEid(NoWhiteSpace.valueOf("sec_001"));
        right.add(section);

        section = new Section();
        section.setEid(NoWhiteSpace.valueOf("sec_002"));
        right.add(section);

        DiffResult result = new DiffBuilder<Chapter>().left(left).right(right).build();

        assertEquals(2, result.count());

        Diff diff = result.stream().findFirst().get();

        assertEquals("/chapter", diff.getPath());
        assertEquals(DiffType.ELEMENT_INSERTED, diff.getType());
    }

   // @Test
    void testInsertedOneElement() {

        Chapter left = new Chapter();
        left.setEid(NoWhiteSpace.valueOf("chp_001"));

        Section section = new Section();
        section.setEid(NoWhiteSpace.valueOf("sec_001"));
        left.add(section);

        Chapter right = new Chapter();
        right.setEid(NoWhiteSpace.valueOf("chp_001"));

        section = new Section();
        section.setEid(NoWhiteSpace.valueOf("sec_001"));
        right.add(section);

        section = new Section();
        section.setEid(NoWhiteSpace.valueOf("sec_002"));
        right.add(section);

        DiffResult result = new DiffBuilder<Chapter>().left(left).right(right).build();

        assertEquals(1, result.count());

        Diff diff = result.stream().findFirst().get();

        assertEquals("/chapter", diff.getPath());
        assertEquals(DiffType.ELEMENT_INSERTED, diff.getType());
    }

   //@Test
    void testDeleted() {

        Chapter left = new Chapter();
        left.setEid(NoWhiteSpace.valueOf("chp_001"));

        Section section = new Section();
        section.setEid(NoWhiteSpace.valueOf("sec_001"));
        left.add(section);

        section = new Section();
        section.setEid(NoWhiteSpace.valueOf("sec_002"));
        left.add(section);

        Chapter right = new Chapter();
        right.setEid(NoWhiteSpace.valueOf("chp_001"));

        DiffResult result = new DiffBuilder<Chapter>().left(left).right(right).build();

        assertEquals(2, result.count());

        Diff diff = result.stream().findFirst().get();

        assertEquals("/chapter", diff.getPath());
        assertEquals(DiffType.ELEMENT_DELETED, diff.getType());
    }


}
