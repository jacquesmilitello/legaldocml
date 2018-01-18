package io.legaldocml.io.impl;

import io.legaldocml.akn.AkomaNtoso;
import io.legaldocml.akn.DocumentType;
import io.legaldocml.akn.util.XmlReaderHelper;
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

    XmlReaderFactoryImpl(int size) {
        pool = Pools.createPool(size, POOLABLE_OBJECT);
    }

    @Override
    public <T extends DocumentType> AkomaNtoso<T> read(MappedByteBuffer buffer) {
        PoolHolder<XmlChannelReader> holder = null;
        try {
            holder = this.pool.checkOut();

            XmlChannelReader reader = holder.get();
            reader.setBuffer(buffer);
            reader.nextStartOrEndElement();


            AkomaNtoso<T> akomaNtoso = XmlReaderHelper.<T>createAkomaNtoso(reader);
            akomaNtoso.read(reader);
            return akomaNtoso;
        } finally {
            if (holder != null) {
                this.pool.checkIn(holder);
            }
        }
    }
}
