package io.legaldocml.business.builder.support;

import io.legaldocml.akn.element.Chapter;
import io.legaldocml.akn.element.HierarchyElement;
import io.legaldocml.akn.element.Part;
import io.legaldocml.akn.element.Title;
import io.legaldocml.business.builder.element.HierarchyBuilder;
import org.junit.jupiter.api.Test;

import java.util.function.Consumer;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.nullable;
import static org.mockito.Mockito.doCallRealMethod;

/**
 * @author <a href="mailto:mustapha.charboub@gmail.com">Mustapha CHARBPUB</a>
 */

@SuppressWarnings("unchecked")
class TitleSupportTest extends SupportBuilderTestCase<TitleSupport<Part, HierarchyElement>, Part> {

    @Test
    void testEmpty() {
        doCallRealMethod().when(mock).title();
        doCallRealMethod().when(mock).title(nullable(Consumer.class));
        mock.title();
        assertEquals("<part><title/></part>", write());
    }

    @Test
    void testWithSimpleContent() {
        doCallRealMethod().when(mock).title();
        doCallRealMethod().when(mock).title(nullable(Consumer.class));
        HierarchyBuilder<Title> titleHierarchyBuilder = mock.title();
        titleHierarchyBuilder.num().text("2");
        titleHierarchyBuilder.heading().text("Title Heading");
        titleHierarchyBuilder.intro().p().text("Simple introduction of Title 2");
        assertEquals(
                "<part><title><num>2</num><heading>Title Heading</heading><intro><p>Simple introduction of Title 2</p></intro></title></part>",
                write());
    }

    @Test
    void testWithChapter() {
        doCallRealMethod().when(mock).title();
        doCallRealMethod().when(mock).title(nullable(Consumer.class));
        HierarchyBuilder<Title> titleHierarchyBuilder = mock.title();
        titleHierarchyBuilder.num().text("1");
        titleHierarchyBuilder.heading().text("Title 1");
        titleHierarchyBuilder.intro().p().text("Introduction of title 2");
        HierarchyBuilder<Chapter> chapterHierarchyBuilder = titleHierarchyBuilder.chapter();
        chapterHierarchyBuilder.num().text("2");
        chapterHierarchyBuilder.heading().text("Chapter 1 2");
        chapterHierarchyBuilder.intro().p().text("Introduction of chapter 1 2");
        assertEquals(
                "<part><title><num>1</num><heading>Title 1</heading><intro><p>Introduction of title 2</p></intro><chapter><num>2</num><heading>Chapter 1 2</heading><intro><p>Introduction of chapter 1 2</p></intro></chapter></title></part>",
                write());
    }

}
