package io.legaldocml.akn;

/**
 * All default attributes name.
 *
 * Based on the pattern :
 * According to Joshua Bloch, author of "Effective Java":
 * ```
 * The constant interface pattern is a poor use of interfaces.
 * That a class uses some constants internally is an implementation detail.
 * Implementing a constant interface causes this implementation detail to leak into the class's exported API.
 * It is of no consequence to the users of a class that the class implements a constant interface. In fact, it may even
 * confuse them. Worse, it represents a commitment: if in a future release the class is modified so that it no longer
 * needs to use the constants, it still must implement the interface to ensure binary compatibility.
 * If a nonfinal class implements a constant interface all of its subclasses will have their namespaces polluted by the
 * constants in the interface.
 * ```
 *
 * @author <a href="mailto:jacques.militello@gmail.com">Jacques Militello</a>
 */
public final class AknElements {

    private AknElements() {
    }

    /**
     * Element for {@link io.legaldocml.akn.element.AmendmentContent}
     */
    public static final String AMENDMENT_CONTENT = "amendmentContent";

    /**
     * Element for {@link io.legaldocml.akn.element.AmendmentHeading}
     */
    public static final String AMENDMENT_HEADING = "amendmentHeading";

}
