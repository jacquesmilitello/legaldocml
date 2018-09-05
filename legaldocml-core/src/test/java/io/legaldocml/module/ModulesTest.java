package io.legaldocml.module;

import io.legaldocml.akn.AknObject;
import io.legaldocml.io.CoreAttribute;
import io.legaldocml.io.XmlWriter;
import io.legaldocml.module.akn.v3.AkomaNtosoModuleV3;
import io.legaldocml.test.Tests;
import io.legaldocml.util.CharArray;
import io.legaldocml.util.CharArrays;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.util.function.Supplier;

class ModulesTest {

    @Test
    void testConstructor() throws Exception {
        Tests.assertUtilClassIsWellDefined(Modules.class);
    }

    @Test
    void testGet() {
        Assertions.assertNotNull(Modules.get(AkomaNtosoModuleV3.NAMESPACE_LEGALDOCML));
        Assertions.assertNotNull(Modules.get(CharArrays.immutable("http://docs.oasis-open.org/legaldocml/ns/akn/3.0")));
        Assertions.assertNull(Modules.get(CharArrays.immutable("hello")));
    }

    @Test
    void testElementOnModule() {
        Assertions.assertThrows(UnsupportedOperationException.class, () -> new FakeModule().element(null));
    }


    public static class FakeModule implements Module {
        @Override
        public CharArray namespace() {
            return null;
        }
        @Override
        public void writeNamespace(XmlWriter writer) throws IOException {
        }
        @Override
        public Supplier<CoreAttribute> attribute(String name) {
            return null;
        }
        /**
         * {@inheritDoc}
         */
        @Override
        public <T extends AknObject> Supplier<T> element(String localName) {
            throw new UnsupportedOperationException();
        }
    }
}
