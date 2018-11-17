package io.legaldocml.diff;

import io.legaldocml.akn.AknAttributes;
import io.legaldocml.akn.AknObject;
import io.legaldocml.akn.attribute.Id;
import io.legaldocml.akn.attribute.Source;
import io.legaldocml.akn.util.AknList;
import io.legaldocml.io.CoreAttribute;
import io.legaldocml.io.AttributeGetterSetter;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.List;
import java.util.Objects;

/**
 * @author <a href="mailto:jacques.militello@gmail.com">Jacques Militello</a>
 */
public final class Diffs {

    /**
     * SLF4J
     */
    private static final Logger LOGGER = LoggerFactory.getLogger(Diffs.class);

    public static void compare(List<CoreAttribute> left, List<CoreAttribute> right, DiffContext context) {

        if (left == null && right == null) {
            return;
        }

        if (LOGGER.isTraceEnabled()) {
            LOGGER.trace("compare list of attributes : [] -> []", left, right);
        }
    }

    public static <T extends AknObject> void compare(T left, T right, DiffContext context) {

        if (left == null && right == null) {
            return;
        }

        Objects.requireNonNull(left);

        if (right == null) {
            context.missingElement(left);
            return;
        }

        if (!left.getClass().isAssignableFrom(right.getClass())) {
            context.mismatchElement(left, right);
            return;
        }

        compareAttributes(left, right, context);

        //left.nestedCompare(right, context);

    }

    public static <T extends Source> void compareSource(AknList<T> left, AknList<T> right, DiffContext context) {

    }

    public static <T extends Id> void compareNullable(AknList<T> left, AknList<T> right, DiffContext context) {

        if (left == null && right == null) {
            return;
        }

        if (left == null) {
            right.forEach(context::insertElement);
            return;
        }

        if (right == null) {
            left.forEach(context::deleteElement);
            return;
        }

        AknList<T> rigltClone = right.copy();

        int i = 0;
        for (T l : left.iterable()) {

            if (i < right.size()) {
                T r = right.get(i);

                if (LOGGER.isDebugEnabled()) {
                    LOGGER.debug("compare left [{}] - right [{}]", l, r);
                }

                if (l.getEid() != null && l.getEid().equals(r.getEid())) {
                    // ok
                    rigltClone.remove(i);
                }
            }

        }

        rigltClone.forEach(context::insertElement);


    }

    public static void compareAttributes(AknObject left, AknObject right, DiffContext context) {

        for (AttributeGetterSetter<AknObject> attribute : left.attributes().values()) {

            if (AknAttributes.ID.equals(attribute.name()) || AknAttributes.EVOLVING_ID.equals(attribute.name())) {
                // should be removed when remove AKN2 backward compatibility.
                continue;
            }

            Object valueLeft = attribute.apply(left);
            Object valueRight = attribute.apply(right);

            if (valueLeft == null && valueRight == null) {
                continue;
            }

            if (LOGGER.isDebugEnabled()) {
                LOGGER.debug("CoreAttribute [{}] -> Left [{}] - Right [{}]", attribute.name(), valueLeft, valueRight);
            }

            if (valueLeft == null) {
                context.attributeNew(attribute.name(), valueRight, left, right);
                continue;
            }

            if (valueRight == null) {
                context.attributeRemove(attribute.name(), valueLeft, left, right);
                continue;
            }

            if (!valueLeft.equals(valueRight)) {
                context.attributeValueDifferent(attribute.name(), valueLeft, valueRight, left, right);
            }

        }

    }
}
