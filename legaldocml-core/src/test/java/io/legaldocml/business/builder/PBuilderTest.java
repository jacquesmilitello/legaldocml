package io.legaldocml.business.builder;

import io.legaldocml.akn.element.P;
import io.legaldocml.io.XmlProvider;
import io.legaldocml.test.SonarJUnit4ClassRunner;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.nio.channels.Channels;

@RunWith(SonarJUnit4ClassRunner.class)
public class PBuilderTest {


    @Test
    public void testAuthorialNote() throws IOException {
        P p = new P();
        PBuilder pBuilder = new PBuilder(p, null);
        pBuilder.authorialNote();
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        XmlProvider.writerFactory(3).write(Channels.newChannel(baos), p);
        Assert.assertEquals("<p><authorialNote><p/></authorialNote></p>",new String(baos.toByteArray()));
    }

    @Test
    public void testBold() throws IOException {
        P p = new P();
        PBuilder pBuilder = new PBuilder(p, null);
        pBuilder.b("hello");
        pBuilder.b().text("hello2");
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        XmlProvider.writerFactory(3).write(Channels.newChannel(baos), p);
        Assert.assertEquals("<p><b>hello</b><b>hello2</b></p>",new String(baos.toByteArray()));
    }

    @Test
    public void testItablic() throws IOException {
        P p = new P();
        PBuilder pBuilder = new PBuilder(p, null);
        pBuilder.i("hello");
        pBuilder.i().text("hello2");
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        XmlProvider.writerFactory(3).write(Channels.newChannel(baos), p);
        Assert.assertEquals("<p><i>hello</i><i>hello2</i></p>",new String(baos.toByteArray()));
    }
}
