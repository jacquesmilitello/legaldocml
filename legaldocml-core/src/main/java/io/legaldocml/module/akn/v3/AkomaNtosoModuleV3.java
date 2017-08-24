package io.legaldocml.module.akn.v3;

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
public final class AkomaNtosoModuleV3 implements AknModule {

    private static final String NS_VALUE = "http://docs.oasis-open.org/legaldocml/ns/akn/3.0";
    private static final String NS_PREFIX = "xmlns";

    private static final long NS_VALUE_ADDRESS = Buffers.address(NS_VALUE);
    private static final long NS_PREFIX_ADDRESS = Buffers.address(NS_PREFIX);

    static final CharArray NAMESPACE = CharArrays.constant(NS_VALUE);

    /**
     * {@inheritDoc}
     */
    @Override
    public CharArray namespace() {
        return NAMESPACE;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void writeNamespace(XmlWriter writer) throws IOException {
        writer.writeNamespace(NS_PREFIX_ADDRESS,5, NS_VALUE_ADDRESS,48);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Supplier<Attribute> attributes(String name) {
        throw new IllegalStateException();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public String toString() {
        return getClass().getName() + " for [" + NAMESPACE + "]";
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public int getVersion() {
        return 3;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public AkomaNtosoContext newAkomaNtosoContext() {
        AkomaNtosoContext context = new AkomaNtosoContextV3();
        context.add(this);
        return context;
    }

}
