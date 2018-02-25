package io.legaldocml.io.impl;

import io.legaldocml.akn.element.Article;
import io.legaldocml.akn.element.Chapter;
import io.legaldocml.io.impl.XmlChannelReaderElement.XmlChannelReaderElementException;
import io.legaldocml.test.Tests;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class XmlChannelReaderElementTest {

    @Test
    void testConstructor() throws Exception {
        Tests.assertUtilClassIsWellDefined(XmlChannelReaderElement.class);
    }

    @Test
    void testArticle() {
        Article article = new Article();
        XmlChannelReaderElement.read(article, "<article eId=\"test\"/>".getBytes());
        assertEquals("test", article.getEid().toString());
    }

    @Test
    void testBadElement() {
        Chapter chapter = new Chapter();
        XmlChannelReaderElementException ex = Assertions.assertThrows(XmlChannelReaderElementException.class, () -> XmlChannelReaderElement.read(chapter, "<article eId=\"test\"/>".getBytes()));
        Assertions.assertTrue(ex.getLocalizedMessage().contains("Expected"));
    }

    @Test
    void testExternal() {
        Article article = new Article();
        Assertions.assertThrows(XmlChannelReaderElementException.class, () -> XmlChannelReaderElement.read(article, "<test:article eId=\"test\"/>".getBytes()));
    }

}
