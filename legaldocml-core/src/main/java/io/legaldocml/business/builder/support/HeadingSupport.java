package io.legaldocml.business.builder.support;

import io.legaldocml.akn.element.BaseHierarchy;
import io.legaldocml.akn.element.Heading;
import io.legaldocml.business.builder.BusinessBuilderException;
import io.legaldocml.business.builder.element.InlineReqTypeBuilder;

import java.util.function.Consumer;

/**
 * @author <a href="mailto:jacques.militello@gmail.com">Jacques Militello</a>
 */
public interface HeadingSupport<T extends BaseHierarchy> extends SupportBuilder<T> {

    default InlineReqTypeBuilder<Heading> heading() {
        return heading(null);
    }

    default InlineReqTypeBuilder<Heading> heading(Consumer<Heading> consumer) {
        if (parent().getHeading() != null) {
            throw new BusinessBuilderException("<heading> is not null : [" + parent().getHeading() + "]");
        }
        Heading heading = new Heading();
        parent().setHeading(heading);

        if (consumer != null) {
            consumer.accept(heading);
        }

        return new InlineReqTypeBuilder<>(businessBuilder(), heading);
    }

}
