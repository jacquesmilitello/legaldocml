package io.legaldocml.pool;

import io.legaldocml.test.Tests;
import org.junit.Test;

import java.util.concurrent.atomic.AtomicInteger;

import static org.junit.Assert.assertEquals;

/**
 * @author <a href="mailto:jacques.militello@gmail.com">Jacques Militello</a>
 */
public class PoolsTest {

    @Test
    public void testConstructor() throws Exception {
        Tests.assertUtilClassIsWellDefined(Pools.class);
    }

    @Test
    public void testSimplePool() {

        AtomicInteger integer = new AtomicInteger(0);

        try (Pool<PoolHolder<Object>> pool = Pools.createPool(2, new PoolableObject<Object>() {
            @Override
            public Object newInstance() {
                integer.incrementAndGet();
                return new Object();
            }
        })) {

            assertEquals(2, integer.get());

            // get 0
            PoolHolder<Object> p0 = pool.checkOut();
            assertEquals(PoolHolder.USED, p0.getState());
            // get 1
            PoolHolder<Object> p1 = pool.checkOut();
            assertEquals(PoolHolder.USED, p1.getState());

            assertEquals(2, integer.get());

            // get 2
            PoolHolder<Object> p2 = pool.checkOut();
            assertEquals(PoolHolder.SINGLE, p2.getState());

            pool.checkIn(p0);
            assertEquals(PoolHolder.FREE, p0.getState());
            assertEquals(PoolHolder.USED, p1.getState());
            assertEquals(PoolHolder.SINGLE, p2.getState());

            pool.checkIn(p1);
            assertEquals(PoolHolder.FREE, p0.getState());
            assertEquals(PoolHolder.FREE, p1.getState());
            assertEquals(PoolHolder.SINGLE, p2.getState());

            pool.checkIn(p2);

        }

    }

    @Test
    public void testFinalize() {


        try (Pool<PoolHolder<Object>> pool = Pools.createPool(2, new PoolableObject<Object>() {
            @Override
            public Object newInstance() {
                return null;
            }
        })) {

        }

        for (int i = 0; i < 2 ;i++) {
            // pool is closed -> try to force gc
            System.gc();
            System.runFinalization();

            try {
                Thread.sleep(100);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }

    }

}
