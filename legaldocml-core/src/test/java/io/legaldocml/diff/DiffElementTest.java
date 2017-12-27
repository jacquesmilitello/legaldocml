package io.legaldocml.diff;

import io.legaldocml.akn.element.Chapter;
import io.legaldocml.akn.element.Section;
import io.legaldocml.akn.type.NoWhiteSpace;
import io.legaldocml.diff.impl.DiffBuilder;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class DiffElementTest {

    @Test
    void testInserted() {

        Chapter left = new Chapter();
        left.setEid(NoWhiteSpace.valueOf("chp_001"));

        Chapter right = new Chapter();
        right.setEid(NoWhiteSpace.valueOf("chp_001"));

        Section section = new Section();
        section.setEid(NoWhiteSpace.valueOf("sec_001"));
        right.add(section);

        DiffResult result = new DiffBuilder<Chapter>().left(left).right(right).build();

        Assertions.assertEquals(1, result.count());

        Diff diff = result.stream().findFirst().get();

      //  Assertions.assertEquals("/TLCPerson[@eId]", diff.getPath());
      //  Assertions.assertEquals(DiffType.ATTRIBUTE_VALUE, diff.getType());
    }
}
