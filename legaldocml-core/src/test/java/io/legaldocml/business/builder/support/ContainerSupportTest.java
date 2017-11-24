package io.legaldocml.business.builder.support;

import io.legaldocml.akn.element.Container;
import io.legaldocml.akn.element.Preface;
import io.legaldocml.business.builder.element.ContainerTypeBuilder;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.util.function.Consumer;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.ArgumentMatchers.nullable;
import static org.mockito.Mockito.doCallRealMethod;

/**
 * @author <a href="mailto:jacques.militello@gmail.com">Jacques Militello</a>
 */
public class ContainerSupportTest extends SupportBuilderTestCase<ContainerSupport<Preface>,Preface> {

    @Test
    public void testEmpty() throws IOException {
        doCallRealMethod().when(mock).container(anyString());
        doCallRealMethod().when(mock).container(anyString(), nullable(Consumer.class));
        ContainerTypeBuilder<Container> builder = mock.container("mandatoryName");
        assertEquals("<preface><container name=\"mandatoryName\"/></preface>", write());
    }

    @Test
    public void testAddElement() throws IOException {
        doCallRealMethod().when(mock).container(anyString());
        doCallRealMethod().when(mock).container(anyString(), nullable(Consumer.class));
        ContainerTypeBuilder<Container> builder = mock.container("mandatoryName");
        builder.p();
        assertEquals("<preface><container name=\"mandatoryName\"><p/></container></preface>", write());
    }

}
