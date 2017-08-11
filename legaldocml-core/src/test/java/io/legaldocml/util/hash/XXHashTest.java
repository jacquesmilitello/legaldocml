package io.legaldocml.util.hash;

import io.legaldocml.unsafe.UnsafeHelper;
import io.legaldocml.unsafe.UnsafeString;
import io.legaldocml.util.HashReader;
import io.legaldocml.util.Hashing;
import org.junit.Test;

public class XXHashTest {

    @Test
    public void test() {

        String value = "Hello World !";
        System.out.println("---> [" + Hashing.xx(123456, value) + "]");

    }

    @Test
    public void tests() {

        String value = "Hello World !";
        System.out.println("2---> [" + Hashing.xx(123456, new HashReader4String(UnsafeString.getChars(value))) + "]");
    }

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
