package io.legaldocml.business.guid;

import io.legaldocml.akn.type.NoWhiteSpace;
import io.legaldocml.test.Tests;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.HashSet;
import java.util.Set;

class GuidGeneratorTest {

    @Test
    void testUtilClass() throws Exception {
        Tests.assertUtilClassIsWellDefined(GuidGenerators.class);
    }


    @Test
    void testUUID() {
        GuidGenerator generator = GuidGenerators.uuid();
        Assertions.assertNotNull(generator.generate());

        Set<NoWhiteSpace> set = new HashSet<>(1_000_000, 0.9f);
        for (int i = 0; i < 1_000_000; i++) {
            NoWhiteSpace p = generator.generate();
            if (set.contains(p)) {
                Assertions.fail("Already - generated [" + p + "]");
            } else {
                set.add(p);
            }
        }

    }

    @Test
    void testTimestamp() {
        GuidGenerator generator = GuidGenerators.timestamp();
        Assertions.assertNotNull(generator.generate());

        Set<NoWhiteSpace> set = new HashSet<>(1_000_000, 0.9f);
        for (int i = 0; i < 1_000_000; i++) {
            NoWhiteSpace p = generator.generate();
            if (set.contains(p)) {
                Assertions.fail("Already - generated [" + p + "]");
            } else {
                set.add(p);
            }
        }

    }
}
