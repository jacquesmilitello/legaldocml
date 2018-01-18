package io.legaldocml.business.builder.support;

import io.legaldocml.akn.AknObject;
import io.legaldocml.akn.container.ANhierContainer;
import io.legaldocml.akn.element.Point;
import io.legaldocml.business.builder.element.HierarchyBuilder;

import java.util.function.Consumer;

/**
 * @author <a href="mailto:jacques.militello@gmail.com">Jacques Militello</a>
 */
public interface PointSupport<T extends ANhierContainer<E>, E extends AknObject> extends SupportBuilder<T> {

    default HierarchyBuilder<Point> point() {
        return point(null);
    }

    default HierarchyBuilder<Point> point(Consumer<Point> consumer) {
        Point point = new Point();
        parent().add(point);
        if (consumer != null) {
            consumer.accept(point);
        }
        return new HierarchyBuilder<>(businessBuilder(), point);
    }

}
