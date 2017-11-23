package io.legaldocml.io.impl;

import io.legaldocml.io.XmlProvider;
import io.legaldocml.io.XmlReaderFactory;
import io.legaldocml.test.PathForTest;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.nio.MappedByteBuffer;
import java.nio.channels.FileChannel;
import java.nio.file.Path;
import java.nio.file.StandardOpenOption;

public class FactoryTest {

    @Test
    public void test() throws IOException {
       XmlReaderFactory factory = XmlProvider.readerFactory();

       Path path = PathForTest.path("/xml/v3/it_senato_ddl_2013.xml");
       for (int i = 0 ; i < 2 ; i++) {
           try (FileChannel channel = FileChannel.open(path, StandardOpenOption.READ)) {
               MappedByteBuffer buffer = channel.map(FileChannel.MapMode.READ_ONLY, 0, channel.size());
               try {
                   factory.read(buffer);
               } finally {
                   Buffers.releaseDirectByteBuffer(buffer);
               }

           }
       }

    }




}
