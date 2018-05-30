package io.legaldocml.business.builder.support;


import io.legaldocml.akn.element.P;
import org.junit.jupiter.api.Test;

import java.util.function.Consumer;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.nullable;
import static org.mockito.Mockito.doCallRealMethod;

/**
 * @author <a href="mailto:mustapha.charboub@gmail.com">Mustapha CHARBOUB</a>
 */
@SuppressWarnings("unchecked")
final class PlaceholderSupportTest extends SupportBuilderTestCase<PlaceholderSupport<P>, P> {

    @Test
    void testEmpty() {
        doCallRealMethod().when(mock).placeholder();
        doCallRealMethod().when(mock).placeholder(nullable(Consumer.class));
        mock.placeholder();
        assertEquals("<p><placeholder/></p>", write());
    }

    @Test
    void testWithContent() {
        doCallRealMethod().when(mock).placeholder();
        doCallRealMethod().when(mock).placeholder(nullable(Consumer.class));
        mock.placeholder(placeholder -> placeholder.setOriginalText("text"));
        assertEquals("<p><placeholder originalText=\"text\"/></p>", write());
    }
}