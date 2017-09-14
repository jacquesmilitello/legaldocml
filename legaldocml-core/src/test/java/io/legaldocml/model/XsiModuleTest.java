package io.legaldocml.model;

import io.legaldocml.io.Attribute;
import io.legaldocml.module.xsi.XsiModule;
import io.legaldocml.module.xsi.attribute.SchemaLocation;
import org.junit.Test;

import java.util.function.Supplier;

import static com.jayway.jsonpath.matchers.JsonPathMatchers.*;
import static org.hamcrest.Matchers.equalTo;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertThat;
import static org.junit.Assert.assertTrue;

public class XsiModuleTest {

    private XsiModule module = new XsiModule();

    @Test
    public void testAttribute() {
        Supplier<Attribute> supplier = module.attributes(SchemaLocation.ATTRIBUTE);
        assertNotNull(supplier);
        assertTrue(SchemaLocation.class.isAssignableFrom(supplier.get().getClass()));
    }

    @Test
    public void testAttributeFake() {
        assertNull(module.attributes("fake"));
    }

    @Test(expected = IllegalStateException.class)
    public void testElement() {
        module.getAknClass("Fake");
    }

    @Test
    public void testXsiToString() {
        String value = module.toString();
        assertThat(value, isJson());
        assertThat(value, hasJsonPath("$.prefix", equalTo(XsiModule.NAMESPACE_PREFIX_XSI)));
        assertThat(value, hasJsonPath("$.namespace", equalTo(XsiModule.NAMESPACE_SCHEMA_INSTANCE)));
    }

}
