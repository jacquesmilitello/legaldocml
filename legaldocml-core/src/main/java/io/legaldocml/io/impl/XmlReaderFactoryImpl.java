package io.legaldocml.io.impl;

import io.legaldocml.akn.AkomaNtoso;
import io.legaldocml.akn.AkomaNtosoContext;
import io.legaldocml.akn.DocumentType;
import io.legaldocml.akn.util.XmlReaderHelper;
import io.legaldocml.io.XmlReaderFactory;
import io.legaldocml.module.akn.DefaultAkomaNtosoContext;
import io.legaldocml.pool.Pool;
import io.legaldocml.pool.PoolHolder;
import io.legaldocml.pool.PoolableObject;
import io.legaldocml.pool.Pools;
import io.legaldocml.util.Buffers;

import java.io.IOException;
import java.nio.MappedByteBuffer;
import java.nio.channels.FileChannel;
import java.nio.file.Path;
import java.nio.file.StandardOpenOption;

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
	public <T extends DocumentType> AkomaNtoso<T> read(MappedByteBuffer buffer, AkomaNtosoContext context) {
		PoolHolder<XmlChannelReader> holder = null;
		try {
			holder = this.pool.checkOut();

			XmlChannelReader reader = holder.get();
			reader.setBuffer(buffer);
			reader.nextStartOrEndElement();

			AkomaNtoso<T> akomaNtoso = XmlReaderHelper.createAkomaNtoso(reader, context);
			akomaNtoso.read(reader);
			return akomaNtoso;
		} finally {
			if (holder != null) {
				this.pool.checkIn(holder);
			}
		}
	}

	@Override
	public <T extends DocumentType> AkomaNtoso<T> read(MappedByteBuffer buffer) {
		return read(buffer, new DefaultAkomaNtosoContext());
	}

	@Override
	public <T extends DocumentType> AkomaNtoso<T> read(Path path) throws IOException {
		MappedByteBuffer out = null;
		try (FileChannel channel = FileChannel.open(path, StandardOpenOption.READ)) {

			// Mapping a file into memory
			out = channel.map(FileChannel.MapMode.READ_ONLY, 0, channel.size());
			
			return read(out);
		} finally {
			if (out != null) {
				Buffers.releaseDirectByteBuffer(out);	
			}
			
		}
	}
}
