package io.legaldocml.business.builder.support;

import io.legaldocml.akn.element.BaseHierarchy;
import io.legaldocml.akn.element.Heading;
import io.legaldocml.akn.element.Num;
import io.legaldocml.business.builder.BusinessBuilderException;
import io.legaldocml.business.builder.element.InlineReqTypeBuilder;
import io.legaldocml.business.builder.element.InlineTypeBuilder;

import java.util.function.Consumer;

/**
 * @author <a href="mailto:jacques.militello@gmail.com">Jacques Militello</a>
 */
public interface NumSupport<T extends BaseHierarchy> extends SupportBuilder<T> {

    default InlineTypeBuilder<Num> num() {
        return num(null);
    }

    default InlineTypeBuilder<Num> num(Consumer<Num> consumer) {
        if (parent().getNum() != null) {
            throw new BusinessBuilderException("<num> is not null : [" + parent().getNum() + "]");
        }
        Num num = new Num();
        this.parent().setNum(num);

        if (consumer != null) {
            consumer.accept(num);
        }

        return new InlineTypeBuilder<>(businessBuilder(), num);
    }

}
