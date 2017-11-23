package io.legaldocml.model;

import io.legaldocml.module.xsi.XsiModule;

public class XsiModuleTest {

    private XsiModule module = new XsiModule();

   /* @Test
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
        Assertions.assertThat(value, isJson());
        assertThat(value, hasJsonPath("$.prefix", equalTo(XsiModule.NAMESPACE_PREFIX_XSI)));
        assertThat(value, hasJsonPath("$.namespace", equalTo(XsiModule.NAMESPACE_SCHEMA_INSTANCE)));
    }
*/
}
