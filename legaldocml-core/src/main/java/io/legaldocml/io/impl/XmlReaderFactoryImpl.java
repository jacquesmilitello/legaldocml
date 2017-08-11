package io.legaldocml.io.impl;

import io.legaldocml.akn.AkomaNtoso;
import io.legaldocml.akn.DocumentType;
import io.legaldocml.io.XmlReaderFactory;
import io.legaldocml.pool.Pool;
import io.legaldocml.pool.PoolHolder;
import io.legaldocml.pool.PoolableObject;
import io.legaldocml.pool.Pools;

import java.nio.MappedByteBuffer;

/**
 * @author <a href="mailto:jacques.militello@gmail.com">Jacques Militello</a>
 */
final class XmlReaderFactoryImpl implements XmlReaderFactory {

    private static final PoolableObject<XmlChannelReader> POOLABLE_OBJECT = new PoolableObject<XmlChannelReader>() {
        @Override
        public XmlChannelReader newInstance() {
            return new XmlChannelReader(false);
        }

        @Override
        public void passivate(XmlChannelReader xmlReader) {
            xmlReader.reset();
        }
    };

    private final Pool<PoolHolder<XmlChannelReader>> pool;

    public XmlReaderFactoryImpl(int size) {
        pool = Pools.createPool(size, POOLABLE_OBJECT);
    }

    @Override
    public <T extends DocumentType> AkomaNtoso<T> read(MappedByteBuffer buffer) {
        PoolHolder<XmlChannelReader> holder = null;
        try {
            holder = this.pool.checkOut();
            holder.get().setBuffer(buffer);
            AkomaNtoso<T> akomaNtoso = new AkomaNtoso<>();
            holder.get().nextStartOrEndElement();
            akomaNtoso.read(holder.get());
            return akomaNtoso;
        } finally {
            if (holder != null) {
                this.pool.checkIn(holder);
            }
        }
    }
}
