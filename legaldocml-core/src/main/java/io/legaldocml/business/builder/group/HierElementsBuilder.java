package io.legaldocml.business.builder.group;

import io.legaldocml.akn.container.HierElementsContainer;
import io.legaldocml.business.builder.support.ChapterSupport;
import io.legaldocml.business.builder.support.SectionSupport;
import io.legaldocml.business.builder.support.SubSectionSupport;

/**
 * @author <a href="mailto:jacques.militello@gmail.com">Jacques Militello</a>
 */
public interface HierElementsBuilder<T extends HierElementsContainer> extends ChapterSupport<T>, SectionSupport<T>, SubSectionSupport<T> {

}
