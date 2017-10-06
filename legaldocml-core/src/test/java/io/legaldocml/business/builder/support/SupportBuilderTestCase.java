package io.legaldocml.business.builder.support;

import io.legaldocml.akn.AknObject;
import io.legaldocml.business.BusinessProvider;
import io.legaldocml.business.builder.BusinessBuilder;
import io.legaldocml.io.XmlProvider;
import io.legaldocml.test.SonarJUnit4ClassRunner;
import org.junit.Before;
import org.junit.runner.RunWith;
import org.mockito.Mockito;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.nio.channels.Channels;
import java.nio.charset.StandardCharsets;

/**
 * @author <a href="mailto:jacques.militello@gmail.com">Jacques Militello</a>
 */
@RunWith(SonarJUnit4ClassRunner.class)
public abstract class SupportBuilderTestCase<T extends SupportBuilder<E>, E extends AknObject>  {

    protected T mock;
    protected E parent;

    @Before
    public void before() {
        mock = Mockito.mock(getSupportBuilderClass());
        parent = getParent();
        BusinessBuilder businessBuilder = BusinessProvider.businessProvider("default").newBuilder("doc");
        Mockito.when(mock.parent()).thenReturn(this.parent);
        Mockito.when(mock.businessBuilder()).thenReturn(businessBuilder);
    }

    protected String write() {
        ByteArrayOutputStream baos = new ByteArrayOutputStream(1024);
        try {
            XmlProvider.writerFactory(3).write(Channels.newChannel(baos), parent);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return new String(baos.toByteArray(), StandardCharsets.UTF_8);
    }

    protected abstract Class<T> getSupportBuilderClass();

    protected abstract E getParent();

}
