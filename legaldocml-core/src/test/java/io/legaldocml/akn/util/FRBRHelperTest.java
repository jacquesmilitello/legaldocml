package io.legaldocml.akn.util;

import io.legaldocml.akn.element.FRBRauthor;
import io.legaldocml.akn.element.FRBRlanguage;
import io.legaldocml.akn.element.FRBRuri;
import io.legaldocml.io.Externalizable;
import io.legaldocml.iso.Iso639;
import io.legaldocml.model.Language;
import io.legaldocml.module.akn.v3.XmlChannelWriterV3;
import io.legaldocml.test.SonarJUnit4ClassRunner;
import io.legaldocml.test.Tests;
import io.legaldocml.akn.type.Uri;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.nio.channels.Channels;

@RunWith(SonarJUnit4ClassRunner.class)
public class FRBRHelperTest {

    @Test
    public void testConstructor() throws Exception {
        Tests.assertUtilClassIsWellDefined(FRBRHelper.class);
    }

    @Test
    public void testNewFRBRlanguage() {
        FRBRlanguage frbr = FRBRHelper.newFRBRlanguage(Iso639.ENGLISH);
        Assert.assertEquals("<FRBRlanguage language=\"eng\"/>", write(frbr));
    }

    @Test
    public void testNewFRBRlanguageWithMapper() {
        FRBRlanguage frbr = FRBRHelper.newFRBRlanguage(Iso639.ENGLISH, Language::getCode);
        Assert.assertEquals("<FRBRlanguage language=\"en\"/>", write(frbr));
    }

    @Test
    public void testNewFRBRauthor() {
        FRBRauthor frbr = FRBRHelper.newFRBRauthor(Uri.raw("Manon"));
        Assert.assertEquals("<FRBRauthor href=\"Manon\"/>", write(frbr));
    }

    @Test
    public void testNewFRBRuri() {
        FRBRuri frbr = FRBRHelper.newFRBRuri("Manon");
        Assert.assertEquals("<FRBRuri value=\"Manon\"/>", write(frbr));
    }

    private String write(Externalizable externalizable) {
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        XmlChannelWriterV3 writer = new XmlChannelWriterV3();
        writer.setChannel(Channels.newChannel(baos));
        try {
            externalizable.write(writer);
            writer.flush();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return new String(baos.toByteArray());
    }

}
