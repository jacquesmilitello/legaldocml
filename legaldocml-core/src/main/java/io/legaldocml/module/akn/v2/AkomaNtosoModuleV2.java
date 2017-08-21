package io.legaldocml.module.akn.v2;


import io.legaldocml.akn.AkomaNtosoContext;
import io.legaldocml.io.Attribute;
import io.legaldocml.io.CharArray;
import io.legaldocml.io.CharArrays;
import io.legaldocml.io.XmlWriter;
import io.legaldocml.io.impl.Buffers;
import io.legaldocml.module.AknModule;

import java.io.IOException;
import java.util.function.Supplier;

/**
 * @author <a href="mailto:jacques.militello@gmail.com">Jacques Militello</a>
 */
public final class AkomaNtosoModuleV2 implements AknModule {

    public static final String NS_VALUE = "http://www.akomantoso.org/2.0";
    public static final String NS_PREFIX = "xmlns";

    private static final long NS_VALUE_ADDRESS = Buffers.address(NS_VALUE);
    private static final long NS_PREFIX_ADDRESS = Buffers.address(NS_PREFIX);

    static final CharArray NAMESPACE = CharArrays.constant(NS_VALUE);

    @Override
    public CharArray namespace() {
        return NAMESPACE;
    }

    @Override
    public void writeNamespace(XmlWriter writer) throws IOException {
        writer.writeNamespace(NS_PREFIX_ADDRESS,5, NS_VALUE_ADDRESS,29);
    }

    @Override
    public Supplier<Attribute> attributes(String name) {
        throw new IllegalStateException();
    }

    @Override
    public String toString() {
        return getClass().getName() + " for [" + NAMESPACE + "]";
    }

    @Override
    public int getVersion() {
        return 2;
    }

    @Override
    public AkomaNtosoContext newAkomaNtosoContext() {
        AkomaNtosoContext context = new AkomaNtosoContextV2();
        context.add(this);
        return context;
    }
}
