package io.legaldocml.business.builder.group;

import io.legaldocml.akn.AknObject;
import io.legaldocml.akn.container.ANhierContainer;
import io.legaldocml.business.builder.support.*;

/**
 * @author <a href="mailto:jacques.militello@gmail.com">Jacques Militello</a>
 */
public interface ANhierBuilder<T extends ANhierContainer<E>, E extends AknObject> extends TitleSupport<T, E>, ChapterSupport<T, E>, SectionSupport<T, E>, ParagraphSupport<T, E>,
        ArticleSupport<T, E>, SubSectionSupport<T, E>, ListSupport<T, E>, AlineaSupport<T, E>, PointSupport<T, E> {

}
