package io.legaldocml.business.builder.support;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.nullable;
import static org.mockito.Mockito.doCallRealMethod;

import java.util.function.Consumer;

import org.junit.jupiter.api.Test;

import io.legaldocml.akn.element.Alinea;
import io.legaldocml.akn.element.Article;
import io.legaldocml.akn.element.HierarchyElement;
import io.legaldocml.akn.element.Point;
import io.legaldocml.business.builder.element.HierarchyBuilder;

/**
 * @author <a href="mailto:mustapha.charboub@gmail.com">Mustapha CHARBOUB</a>
 */

@SuppressWarnings("unchecked")
class AlineaSupportTest extends SupportBuilderTestCase<AlineaSupport<Article, HierarchyElement>, Article> {

	@Test
	void testEmpty() {
		doCallRealMethod().when(mock).alinea();
		doCallRealMethod().when(mock).alinea(nullable(Consumer.class));
		mock.alinea();
		assertEquals("<article><alinea/></article>", write());
	}

	@Test
	void testWithNum() {
		doCallRealMethod().when(mock).alinea();
		doCallRealMethod().when(mock).alinea(nullable(Consumer.class));
		mock.alinea().num().text("1");
		assertEquals("<article><alinea><num>1</num></alinea></article>", write());
	}

	@Test
	void testWithHeading() {
		doCallRealMethod().when(mock).alinea();
		doCallRealMethod().when(mock).alinea(nullable(Consumer.class));
		HierarchyBuilder<Alinea> alineaHierarchyBuilder = mock.alinea();
		alineaHierarchyBuilder.heading().text("alinea heading");
		assertEquals("<article><alinea><heading>alinea heading</heading></alinea></article>", write());
	}

	@Test
	void testWithPoint() {
		doCallRealMethod().when(mock).alinea();
		doCallRealMethod().when(mock).alinea(nullable((Consumer.class)));
		HierarchyBuilder<Alinea> alineaHierarchyBuilder = mock.alinea();
		alineaHierarchyBuilder.num().text("1");
		alineaHierarchyBuilder.heading().text("heading alinea");
		HierarchyBuilder<Point> pointHierarchyBuilder = alineaHierarchyBuilder.point();
		pointHierarchyBuilder.num().text("1");
		pointHierarchyBuilder.heading().text("Point heading");
		assertEquals(
				"<article><alinea><num>1</num><heading>heading alinea</heading><point><num>1</num><heading>Point heading</heading></point></alinea></article>",
				write());
	}

}
