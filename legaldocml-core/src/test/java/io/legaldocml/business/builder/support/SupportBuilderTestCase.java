package io.legaldocml.business.builder.support;

import static org.mockito.Mockito.doReturn;
import static org.mockito.Mockito.mock;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.lang.reflect.ParameterizedType;
import java.nio.channels.Channels;
import java.nio.charset.StandardCharsets;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.extension.ExtendWith;

import io.legaldocml.akn.AknObject;
import io.legaldocml.akn.DocumentType;
import io.legaldocml.business.BusinessProvider;
import io.legaldocml.business.builder.BusinessBuilder;
import io.legaldocml.io.XmlProvider;
import io.legaldocml.test.LoggerInstancePostProcessor;

/**
 * @author <a href="mailto:jacques.militello@gmail.com">Jacques Militello</a>
 */
@ExtendWith(LoggerInstancePostProcessor.class)
public abstract class SupportBuilderTestCase<T extends SupportBuilder<E>, E extends AknObject>  {

    protected T mock;
    protected E parent;

    private final Class<T> mockClass;
    private final Class<E> parenteClass;

    @SuppressWarnings("all")
    protected SupportBuilderTestCase() {
        ParameterizedType p = (ParameterizedType) ((ParameterizedType) getClass().getGenericSuperclass()).getActualTypeArguments()[0];
        this.mockClass = (Class<T>) p.getRawType();
        this.parenteClass = (Class<E>) ((ParameterizedType) getClass().getGenericSuperclass()).getActualTypeArguments()[1];
    }


    @BeforeEach
    public void before() {
        mock = mock(mockClass);
        try {
            parent = parenteClass.newInstance();
        } catch (InstantiationException | IllegalAccessException e) {
           throw new RuntimeException(e);
        }
        BusinessBuilder<? extends DocumentType> businessBuilder = BusinessProvider.businessProvider("default").newBuilder("doc");
        doReturn(this.parent).when(mock).parent();
        doReturn(businessBuilder).when(mock).businessBuilder();
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

}
