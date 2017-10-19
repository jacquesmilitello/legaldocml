package io.legaldocml.business.builder.support;

import io.legaldocml.akn.container.ANhierContainer;
import io.legaldocml.akn.element.Paragraph;
import io.legaldocml.business.builder.element.HierarchyBuilder;

import java.util.function.Consumer;

/**
 * @author <a href="mailto:jacques.militello@gmail.com">Jacques Militello</a>
 */
public interface ParagraphSupport<T extends ANhierContainer> extends SupportBuilder<T> {

    default HierarchyBuilder<Paragraph> paragraph() {
        return paragraph(null);
    }

    default HierarchyBuilder<Paragraph> paragraph(Consumer<Paragraph> consumer) {
        Paragraph paragraph = new Paragraph();
        parent().add(paragraph);
        if (consumer != null) {
            consumer.accept(paragraph);
        }
        return new HierarchyBuilder<>(businessBuilder(), paragraph);
    }

}
