package io.legaldocml.util;

import java.io.IOException;
import java.nio.ByteBuffer;
import java.nio.channels.ClosedChannelException;
import java.nio.channels.WritableByteChannel;

/**
 * @author <a href="mailto:jacques.militello@gmail.com">Jacques Militello</a>
 */
public final class WritableByteChannelImpl implements WritableByteChannel {

    public static final int DEFAULT_BUCKET = 64;

    /**
     * Same size from "XmlChannelWriter"
     */
    public static final int DEFAULT_BUCKET_SIZE = 32768;

    private final byte buf[][];
    private final int bucketSize;

    private boolean open = true;

    private int bucket;
    private int position;

    public WritableByteChannelImpl() {
        this(DEFAULT_BUCKET, DEFAULT_BUCKET_SIZE);
    }

    public WritableByteChannelImpl(int bucket, int bucketSize) {
        this.buf = new byte[bucket][];
        this.bucketSize = bucketSize;
        this.bucket = 0;
        this.position = 0;
        this.buf[0] = new byte[bucketSize];
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public int write(ByteBuffer src) throws IOException {

        if (!isOpen()) {
            throw new ClosedChannelException();
        }

        int len = src.remaining();
        int result = len;
        while (len > 0) {

            if (len > this.bucketSize - this.position) {
                src.get(this.buf[this.bucket],this.position, this.bucketSize - this.position);
                this.bucket++;
                this.buf[this.bucket] = new byte[bucketSize];
                this.position = 0;
                len -= this.bucketSize;
            } else {
                src.get(this.buf[this.bucket],this.position, len);
                this.position = len;
                len = 0;
            }
        }
        return result;
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
        byte[] bytes = new byte[position + (bucket * bucketSize)];
        int pos = 0;
        for (int i = 0 ; i < bucket ; i++, pos += bucketSize) {
            System.arraycopy(buf[i],0,bytes,pos, bucketSize);
        }
        if (position != 0) {
            System.arraycopy(buf[bucket],0,bytes,pos, position);
        }
        return bytes;
    }
}
