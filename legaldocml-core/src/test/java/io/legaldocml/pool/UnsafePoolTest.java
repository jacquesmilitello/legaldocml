package io.legaldocml.pool;

import org.junit.Test;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.Executor;
import java.util.concurrent.Executors;

public class UnsafePoolTest {

    @Test
    public void test() throws InterruptedException {

        UnsafePool<Reader> pool = new UnsafePool<>(8, INSTANCE);

        Executor executor = Executors.newFixedThreadPool(16);

        CountDownLatch latch = new CountDownLatch(128);

        for (int j = 0 ; j < 128 ; j++) {
            executor.execute(() -> {
                for (int i = 0 ; i<16 ; i++) {
                    UnsafeHolder<Reader> holder = null;
                    try {
                        holder = pool.checkOut();
                    }
                    finally {
                        pool.checkIn(holder);
                    }
                }
                latch.countDown();
            });
        }


        latch.await();



    }

    public static final PoolableObject<Reader> INSTANCE = new PoolableObject<Reader>() {
        @Override
        public Reader newInstance() {
            return new Reader();
        }

        @Override
        public void passivate(Reader reader) {
            reader.reset();
        }
    };

    public static final class Reader {

        public void reset() {

        }
    }
}
