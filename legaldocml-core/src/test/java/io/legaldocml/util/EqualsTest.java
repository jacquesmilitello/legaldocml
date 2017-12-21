package io.legaldocml.util;

import io.legaldocml.akn.element.Chapter;
import io.legaldocml.akn.element.Section;
import io.legaldocml.akn.type.NoWhiteSpace;
import io.legaldocml.test.Tests;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

class EqualsTest {

    @Test
    void testConstructor() throws Exception {
        Tests.assertUtilClassIsWellDefined(Equals.class);
    }

    @Test
    void testDoEqualsMissingEid() {

        Chapter left = new Chapter();
        left.setEid(NoWhiteSpace.valueOf("12345"));

        Chapter right = new Chapter();
        Assertions.assertFalse(Equals.doEquals(left, right));
    }

    @Test
    void testDoEqualsDifferentEid() {

        Chapter left = new Chapter();
        left.setEid(NoWhiteSpace.valueOf("12345"));

        Chapter right = new Chapter();
        right.setEid(NoWhiteSpace.valueOf("123456"));

        Assertions.assertFalse(Equals.doEquals(left, right));
    }

    @Test
    void testDoEqualsSameEid() {

        Chapter left = new Chapter();
        left.setEid(NoWhiteSpace.valueOf("12345"));

        Chapter right = new Chapter();
        right.setEid(NoWhiteSpace.valueOf("12345"));

        Assertions.assertTrue(Equals.doEquals(left, right));
    }

    @Test
    void testDoEqualsDifferentObject() {

        Chapter left = new Chapter();
        left.setEid(NoWhiteSpace.valueOf("12345"));


        Assertions.assertFalse(Equals.doEquals(left, new Section()));
    }

}
