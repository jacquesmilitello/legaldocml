package io.legaldocml.module;

import io.legaldocml.akn.AknObject;
import io.legaldocml.io.Attribute;
import io.legaldocml.io.XmlWriter;
import io.legaldocml.module.akn.v3.AkomaNtosoModuleV3;
import io.legaldocml.test.Tests;
import io.legaldocml.util.CharArray;
import io.legaldocml.util.CharArrays;
import org.junit.Assert;
import org.junit.Test;

import java.io.IOException;
import java.util.function.Supplier;

public class ModulesTest {

    @Test
    public void testConstructor() throws Exception {
        Tests.assertUtilClassIsWellDefined(Modules.class);
    }

    @Test
    public void testGet() {
        Assert.assertNotNull(Modules.get(AkomaNtosoModuleV3.NAMESPACE));
        Assert.assertNotNull(Modules.get(CharArrays.immutable("http://docs.oasis-open.org/legaldocml/ns/akn/3.0")));
        Assert.assertNull(Modules.get(CharArrays.immutable("hello")));
    }

    @Test(expected = UnsupportedOperationException.class)
    public void testElementOnModule() {
        new FakeModule().element(null,null);
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
        public Supplier<Attribute> attributes(String name) {
            return null;
        }
        @Override
        public Class<? extends AknObject> getAknClass(String localName) {
            return null;
        }
    }
}
