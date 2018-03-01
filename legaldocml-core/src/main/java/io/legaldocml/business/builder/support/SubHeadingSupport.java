package io.legaldocml.business.builder.support;

import io.legaldocml.akn.element.BaseHierarchy;
import io.legaldocml.akn.element.Heading;
import io.legaldocml.akn.element.Subheading;
import io.legaldocml.business.builder.BusinessBuilderException;
import io.legaldocml.business.builder.element.InlineReqTypeBuilder;

import java.util.function.Consumer;

/**
 * @author <a href="mailto:jacques.militello@gmail.com">Jacques Militello</a>
 */
public interface SubHeadingSupport<T extends BaseHierarchy> extends SupportBuilder<T> {

    default InlineReqTypeBuilder<Subheading> subHeading() {
        return subHeading(null);
    }

    default InlineReqTypeBuilder<Subheading> subHeading(Consumer<Subheading> consumer) {
        if (parent().getSubheading() != null) {
            throw new BusinessBuilderException("<subheading> is not null : [" + parent().getSubheading() + "]");
        }
        Subheading subheading = new Subheading();
        this.parent().setSubheading(subheading);

        if (consumer != null) {
            consumer.accept(subheading);
        }

        return new InlineReqTypeBuilder<>(businessBuilder(), subheading);
    }

}
