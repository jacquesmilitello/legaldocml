package io.legaldocml.io.impl;

import io.legaldocml.module.akn.v3.XmlChannelWriterV3;
import io.legaldocml.test.SonarJUnit4ClassRunner;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.nio.channels.Channels;
import java.time.LocalDate;
import java.time.OffsetDateTime;
import java.time.ZoneOffset;

@RunWith(SonarJUnit4ClassRunner.class)
public class XmlChannelWriterTest {

    private static final String TOTO = "toto";
    private static final long ADR = Buffers.address(TOTO);

    private ByteArrayOutputStream baos;
    private XmlChannelWriter writer;

    @Before
    public void before() {
        baos = new ByteArrayOutputStream();
        writer = new XmlChannelWriterV3();
        writer.setChannel(Channels.newChannel(baos));
    }

    @Test
    public void writeByteArray() throws IOException {
        writer.writeStart(ADR, 4);
        writer.writeAttribute(ADR, 4, "hello".getBytes());
        writer.writeEnd(ADR, 4);
        writer.flush();

        Assert.assertEquals("<toto toto=\"hello\"/>", baos.toString());
    }

    @Test
    public void writeAttributeCharArray() throws IOException {
        writer.writeStart(ADR, 4);
        writer.writeAttribute(ADR, 4, "hello".toCharArray());
        writer.writeEnd(ADR, 4);
        writer.flush();

        Assert.assertEquals("<toto toto=\"hello\"/>", baos.toString());
    }

    @Test
    public void writeAttributeLocalDate() throws IOException {
        writer.writeStart(ADR, 4);
        writer.writeAttribute(ADR, 4, LocalDate.of(2011,3,9));
        writer.writeEnd(ADR, 4);
        writer.flush();

        Assert.assertEquals("<toto toto=\"2011-03-09\"/>", baos.toString());
    }

    @Test
    public void writeAttributeOffsetDateTime() throws IOException {
        writer.writeStart(ADR, 4);
        writer.writeAttribute(ADR, 4, OffsetDateTime.of(2011,3,9, 18,36, 5,0, ZoneOffset.UTC ));
        writer.writeEnd(ADR, 4);
        writer.flush();

        Assert.assertEquals("<toto toto=\"2011-03-09T18:36:05\"/>", baos.toString());
    }



}
