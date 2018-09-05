package io.legaldocml.model;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.function.Supplier;

import io.legaldocml.io.CoreAttribute;
import org.junit.jupiter.api.Test;

import io.legaldocml.module.xsi.XsiModule;
import io.legaldocml.module.xsi.attribute.SchemaLocation;

public class XsiModuleTest {

    private XsiModule module = new XsiModule();

    @Test
    public void testAttribute() {
        Supplier<CoreAttribute> supplier = module.attribute(SchemaLocation.ATTRIBUTE);
        assertNotNull(supplier);
        assertTrue(SchemaLocation.class.isAssignableFrom(supplier.get().getClass()));
    }

    @Test
    public void testAttributeFake() {
    	assertNull(module.attribute("fake"));
    }

    /*@Test
    public void testXsiToString() {
        String value = module.toString();
        Assertions.assertThat(value, isJson());
        assertThat(value, hasJsonPath("$.prefix", equalTo(XsiModule.NAMESPACE_PREFIX_XSI)));
        assertThat(value, hasJsonPath("$.namespace", equalTo(XsiModule.NAMESPACE_SCHEMA_INSTANCE)));
    }
*/
}
