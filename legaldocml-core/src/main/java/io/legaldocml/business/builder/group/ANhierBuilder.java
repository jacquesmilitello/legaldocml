package io.legaldocml.business.builder.group;

import io.legaldocml.akn.AknObject;
import io.legaldocml.akn.container.ANhierContainer;
import io.legaldocml.business.builder.support.ArticleSupport;
import io.legaldocml.business.builder.support.ChapterSupport;
import io.legaldocml.business.builder.support.ListSupport;
import io.legaldocml.business.builder.support.ParagraphSupport;
import io.legaldocml.business.builder.support.PointSupport;
import io.legaldocml.business.builder.support.SectionSupport;
import io.legaldocml.business.builder.support.SubSectionSupport;

/**
 * @author <a href="mailto:jacques.militello@gmail.com">Jacques Militello</a>
 */
public interface ANhierBuilder<T extends ANhierContainer<E>, E extends AknObject> extends ChapterSupport<T,E>, SectionSupport<T,E>, ParagraphSupport<T,E>,
        ArticleSupport<T,E>, SubSectionSupport<T,E>, ListSupport<T,E>, PointSupport<T,E> {

}
