package io.legaldocml.business.builder.support;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.nullable;
import static org.mockito.Mockito.doCallRealMethod;

import java.util.function.Consumer;

import org.junit.jupiter.api.Test;

import io.legaldocml.akn.element.Article;
import io.legaldocml.akn.element.HierarchyElement;
import io.legaldocml.akn.element.Part;
import io.legaldocml.akn.element.Title;
import io.legaldocml.business.builder.element.HierarchyBuilder;

/**
 * @author <a href="mailto:mustapha.charboub@gmail.com">Mustapha CHARBOUB</a>
 */
@SuppressWarnings("unchecked")

class PartSupportTest extends SupportBuilderTestCase<PartSupport<Title, HierarchyElement>, Title> {

	@Test
	void testEmpty() {
		doCallRealMethod().when(mock).part();
		doCallRealMethod().when(mock).part(nullable(Consumer.class));
		mock.part();
		assertEquals("<title><part/></title>", write());
	}

	@Test
	void testSimpleContent() {
		doCallRealMethod().when(mock).part();
		doCallRealMethod().when(mock).part(nullable(Consumer.class));
		HierarchyBuilder<Part> partHierarchyBuilder = mock.part();
		partHierarchyBuilder.num().text("1");
		partHierarchyBuilder.heading().text("Part 1");
		partHierarchyBuilder.intro().p().text("part introduction");
		assertEquals(
				"<title><part><num>1</num><heading>Part 1</heading><intro><p>part introduction</p></intro></part></title>",
				write());
	}

	@Test
	void testWithArticle() {
		doCallRealMethod().when(mock).part();
		doCallRealMethod().when(mock).part(nullable(Consumer.class));
		HierarchyBuilder<Part> partHierarchyBuilder = mock.part();
		partHierarchyBuilder.num().text("2");
		partHierarchyBuilder.heading().text("part 2");
		HierarchyBuilder<Article> articleHierarchyBuilder = partHierarchyBuilder.article();
		articleHierarchyBuilder.num().text("1");
		articleHierarchyBuilder.heading().text("Article 1");
		articleHierarchyBuilder.intro().p().text("article introduction");
		assertEquals(
				"<title><part><num>2</num><heading>part 2</heading><article><num>1</num><heading>Article 1</heading><intro><p>article introduction</p></intro></article></part></title>",
				write());
	}
}
