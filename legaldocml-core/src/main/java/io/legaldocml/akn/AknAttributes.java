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
public final class AknAttributes {

    private AknAttributes() {
    }

    /**
     * Attribute for {@link io.legaldocml.akn.attribute.Actor}
     */
    public static final String ACTOR = "actor";

    /**
     * Attribute for {@link io.legaldocml.akn.attribute.Agent}
     */
    public static final String BY = "by";

    /**
     * Attribute for {@link io.legaldocml.akn.attribute.Alt}
     */
    public static final String ALTERNATIVE_TO = "alternativeTo";

    /**
     * Attribute for {@link io.legaldocml.akn.attribute.Authoritative}
     */
    public static final String AUTHORITATIVE = "authoritative";

    /**
     * Attribute for {@link io.legaldocml.akn.attribute.BooleanValue}
     */
    public static final String VALUE = "value";

    /**
     * Attribute for {@link io.legaldocml.akn.attribute.CellAttrs}
     */
    public static final String ROW_SPAN = "rowspan";

    /**
     * Attribute for {@link io.legaldocml.akn.attribute.CellAttrs}
     */
    public static final String COL_SPAN = "colspan";

    /**
     * Attribute for {@link io.legaldocml.akn.attribute.Contains}
     */
    public static final String CONTAINS = "contains";

    /**
     * Attribute for {@link io.legaldocml.akn.attribute.Date}
     */
    public static final String DATE = "date";

    /**
     * Attribute for {@link io.legaldocml.akn.attribute.Date}
     */
    public static final String DICTIONARY = "dictionary";

    /**
     * Attribute for {@link io.legaldocml.akn.attribute.Duration}
     */
    public static final String DURATION = "duration";

    /**
     * Attribute for :
     * : * {@link io.legaldocml.akn.attribute.EfficacyModType}
     * : * {@link io.legaldocml.akn.attribute.EventType}
     * : * {@link io.legaldocml.akn.attribute.ForceModType}
     * : * {@link io.legaldocml.akn.attribute.LegalSystemModType}
     */
    public static final String TYPE = "type";

    /**
     * Attribute for {@link io.legaldocml.akn.attribute.Enactment}
     */
    public static final String STATUS = "status";

    /**
     * Attribute for :
     * : * {@link io.legaldocml.akn.attribute.FillInWidth}
     * : * {@link io.legaldocml.akn.attribute.ImgAtts}
     */
    public static final String WIDTH = "width";

    /**
     * Attribute for {@link io.legaldocml.akn.attribute.Period}
     */
    public static final String PERIOD = "period";

    /**
     * Attribute for :
     * : * {@link io.legaldocml.akn.attribute.For}
     * : * {@link io.legaldocml.akn.attribute.LawyerAtts}
     */
    public static final String FOR = "for";

    /**
     * Attribute for {@link io.legaldocml.akn.attribute.FromLanguage}
     */
    public static final String FROM_LANGUAGE = "fromLanguage";

    /**
     * Attribute for {@link io.legaldocml.akn.attribute.Froozen}
     */
    public static final String FROZEN = "frozen";

    /**
     * Attribute for {@link io.legaldocml.akn.attribute.HTMLattrs}
     */
    public static final String CLASS = "class";

    /**
     * Attribute for {@link io.legaldocml.akn.attribute.HTMLattrs}
     */
    public static final String STYLE = "style";

    /**
     * Attribute for {@link io.legaldocml.akn.attribute.HTMLattrs}
     */
    public static final String TITLE = "title";

    /**
     * Attribute for {@link io.legaldocml.akn.attribute.Id}
     */
    @Deprecated
    public static final String ID = "id";

    /**
     * Attribute for {@link io.legaldocml.akn.attribute.Id}
     */
    @Deprecated
    public static final String EVOLVING_ID = "evolvingId";

    /**
     * Attribute for {@link io.legaldocml.akn.attribute.Id}
     */
    public static final String EID = "eId";

    /**
     * Attribute for {@link io.legaldocml.akn.attribute.Id}
     */
    public static final String WID = "wId";

    /**
     * Attribute for {@link io.legaldocml.akn.attribute.Id}
     */
    public static final String GUID = "GUID";

    /**
     * Attribute for {@link io.legaldocml.akn.attribute.ImgAtts}
     */
    public static final String HEIGHT = "height";

    /**
     * Attribute for {@link io.legaldocml.akn.attribute.Interval}
     */
    public static final String START = "start";

    /**
     * Attribute for {@link io.legaldocml.akn.attribute.Interval}
     */
    public static final String END = "end";

    /**
     * Attribute for {@link io.legaldocml.akn.attribute.Language}
     */
    public static final String LANGUAGE = "language";

    /**
     * Attribute for {@link io.legaldocml.akn.attribute.LawyerAtts}
     */
    public static final String EMPOWERED_BY = "empoweredBy";

    /**
     * Attribute for {@link io.legaldocml.akn.attribute.Level}
     */
    public static final String LEVEL = "level";


}
