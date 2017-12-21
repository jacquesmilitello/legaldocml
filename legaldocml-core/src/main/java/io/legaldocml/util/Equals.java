package io.legaldocml.util;

import io.legaldocml.akn.attribute.CoreReq;

import java.util.Objects;

/**
 * @author <a href="mailto:jacques.militello@gmail.com">Jacques Militello</a>
 */
public final class Equals {

    private Equals() {
    }

    public static boolean doEquals(CoreReq left, Object right) {
        return left == right || (right != null) && left.getClass().isAssignableFrom(right.getClass())
                && left.getEid() != null && Objects.equals(left.getEid(), ((CoreReq) right).getEid());
    }
}
