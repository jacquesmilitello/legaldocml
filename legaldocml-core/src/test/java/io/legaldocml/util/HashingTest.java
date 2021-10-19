package io.legaldocml.util;

import io.legaldocml.test.Tests;
import io.legaldocml.unsafe.UnsafeHelper;
import io.legaldocml.unsafe.UnsafeString;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class HashingTest {

    @Test
    public void testUtilConstructor() throws Exception {
        Tests.assertUtilClassIsWellDefined(Hashing.class);
    }

    @Test
    public void test() {
        String value = "Hello World !";
        Assertions.assertEquals(-7739591154577084421L,Hashing.xx(123456, value));

    }

    @Test
    public void testReader() {
        String value = "Hello World !";
       // Assertions.assertEquals(3221264181048580479L, Hashing.xx(123456, new HashReader4String(UnsafeString.getChars(value))));
    }

    @Test
    public void testReaderLong() {
        String value = "Hello World !Hello World !Hello World !Hello World !Hello World !Hello World !Hello World !";
        //Assertions.assertEquals(6133476324641084178L, Hashing.xx(123456, new HashReader4String(UnsafeString.getChars(value))));
    }

    @Test
    public void testHashCode() {
        Assertions.assertEquals(0,Hashing.hashCode(null));
        Assertions.assertEquals(0,Hashing.hashCode(new char[0]));
        Assertions.assertEquals("HelloWorld".hashCode(), Hashing.hashCode("HelloWorld".toCharArray()));
    }

    @SuppressWarnings("restriction")
    private static class HashReader4String extends HashReader {

        private final char[] val;
        private long adr;

        public HashReader4String(char[] val) {
            this.val = val;
            this.adr = 16;
        }

        @Override
        public long length() {
            return val.length * 2;
        }

        @Override
        public long getLong() {
			long value = UnsafeHelper.getUnsafe().getLong(val, adr);
            adr += 8;
            return value;
        }

        @Override
        public int getInt() {
            int value = UnsafeHelper.getUnsafe().getInt(val, adr);
            adr += 4;
            return value;
        }

        @Override
        public long getByte() {
            return UnsafeHelper.getUnsafe().getByte(val, adr++);
        }
    }
}
