package io.legaldocml.util;

import java.io.IOException;
import java.nio.ByteBuffer;
import java.nio.channels.ClosedChannelException;
import java.nio.channels.WritableByteChannel;
import java.util.ArrayList;
import java.util.List;

/**
 * @author <a href="mailto:jacques.militello@gmail.com">Jacques Militello</a>
 */
public final class WritableByteChannelImpl implements WritableByteChannel {

    public static final int DEFAULT_BUCKET = 64;

    private final List<byte[]> buf;

    private boolean open = true;
    private int size = 0;

    public WritableByteChannelImpl() {
        this(DEFAULT_BUCKET);
    }

    public WritableByteChannelImpl(int bucket) {
        this.buf = new ArrayList<>(bucket);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public int write(ByteBuffer src) throws IOException {
        if (!isOpen()) {
            throw new ClosedChannelException();
        }
        byte[] buf = new byte[src.limit()];
        src.get(buf,0, buf.length);
        this.buf.add(buf);
        size += buf.length;
        return buf.length;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public boolean isOpen() {
        return this.open;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void close() {
        this.open = false;
    }

    public byte[] toByteArray() {
        byte[] bytes = new byte[size];
        int pos = 0;
        for (int i = 0 ; i < this.buf.size() ; i++) {
            byte[] buf = this.buf.get(i);
            System.arraycopy(buf,0,bytes,pos, buf.length);
            pos += buf.length;
        }
        return bytes;
    }
}
