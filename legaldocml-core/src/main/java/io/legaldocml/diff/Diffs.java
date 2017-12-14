package io.legaldocml.diff;

import io.legaldocml.akn.AknObject;
import io.legaldocml.akn.DocumentType;
import io.legaldocml.akn.util.AknList;
import io.legaldocml.io.Attribute;
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

    public static final Diff EMPTY = new Diff() {
    };

    public static <T extends DocumentType> Diff missingElement(AknObject left) {
        return new DiffImpl(DiffType.MISSING_ELEMENT, left, null);
    }

    public static <T extends DocumentType> Diff mismatchElement(AknObject left, AknObject right) {
        return new DiffImpl(DiffType.MISMATCH_ELEMENT, left, null);
    }

    public static void compare(List<Attribute> left, List<Attribute> right, DiffContext context) {

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

        if (!left.getClass().isAssignableFrom(right.getClass())) {
            context.mismatchElement(left, right);
            return;
        }

        left.compare(right, context);
    }

    public static <T extends AknObject> void compare(AknList<T> left, AknList<T> right, DiffContext context) {
    }
}
