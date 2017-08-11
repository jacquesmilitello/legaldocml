package io.legaldocml.io.impl;

import io.legaldocml.LegalDocMlException;
import io.legaldocml.akn.AkomaNtoso;
import io.legaldocml.akn.DocumentType;
import io.legaldocml.io.XmlWriterFactory;
import io.legaldocml.pool.Pool;
import io.legaldocml.pool.PoolHolder;
import io.legaldocml.pool.PoolableObject;
import io.legaldocml.pool.Pools;

import java.io.IOException;
import java.nio.channels.WritableByteChannel;
import java.util.List;

/**
 * @author <a href="mailto:jacques.militello@gmail.com">Jacques Militello</a>
 */
public abstract class DefaultXmlWriterFactory implements XmlWriterFactory {

    /**
     * Default Pool Size
     */
    public static final int DEFAULT_POOL_SIZE = 16;

    private final Pool<PoolHolder<XmlChannelWriter>> pool;

    public DefaultXmlWriterFactory(XmlChannelWriterPoolObject poolObject) {
        this(DEFAULT_POOL_SIZE, poolObject);
    }

    public DefaultXmlWriterFactory(int size, XmlChannelWriterPoolObject poolObject) {
        this.pool = Pools.createPool(size, poolObject);
    }

    @Override
    public <T extends DocumentType> void write(WritableByteChannel wbc, AkomaNtoso<T> akn) throws IOException {
        PoolHolder<XmlChannelWriter> holder = pool.checkOut();
        try {
            XmlChannelWriter writer = holder.get();
            writer.setChannel(wbc);
            akn.write(writer);
            writer.flush();
        } finally {
            pool.checkIn(holder);
        }
    }

    @Override
    public <T extends DocumentType> List<LegalDocMlException> writePermissive(WritableByteChannel wbc, AkomaNtoso<T> akn) throws IOException {
        PoolHolder<XmlChannelWriter> holder = pool.checkOut();
        List<LegalDocMlException> exceptions;
        try {
            XmlChannelWriter writer = holder.get();
            writer.setPermissive(true);
            writer.setChannel(wbc);
            akn.write(writer);
            writer.flush();
            exceptions = writer.getExceptions();
        } finally {
            pool.checkIn(holder);
        }
        return exceptions;
    }

    protected static abstract class XmlChannelWriterPoolObject implements PoolableObject<XmlChannelWriter> {
        @Override
        public final void passivate(XmlChannelWriter xmlChannelWriter) {
            xmlChannelWriter.reset();
        }
    }
}
