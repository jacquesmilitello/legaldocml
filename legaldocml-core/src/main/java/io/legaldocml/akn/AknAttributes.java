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
     * CoreAttribute for {@link io.legaldocml.akn.attribute.Actor}
     */
    public static final String ACTOR = "actor";

    /**
     * CoreAttribute for {@link io.legaldocml.akn.attribute.Agent}
     */
    public static final String BY = "by";

    /**
     * CoreAttribute for {@link io.legaldocml.akn.attribute.Alt}
     */
    public static final String ALTERNATIVE_TO = "alternativeTo";

    /**
     * CoreAttribute for {@link io.legaldocml.akn.attribute.Authoritative}
     */
    public static final String AUTHORITATIVE = "authoritative";

    /**
     * CoreAttribute for {@link io.legaldocml.akn.attribute.BooleanValue}
     * CoreAttribute for {@link io.legaldocml.akn.attribute.Value}
     */
    public static final String VALUE = "value";

    /**
     * CoreAttribute for {@link io.legaldocml.akn.attribute.CellAttrs}
     */
    public static final String ROW_SPAN = "rowspan";

    /**
     * CoreAttribute for {@link io.legaldocml.akn.attribute.CellAttrs}
     */
    public static final String COL_SPAN = "colspan";

    /**
     * CoreAttribute for {@link io.legaldocml.akn.attribute.Contains}
     */
    public static final String CONTAINS = "contains";

    /**
     * CoreAttribute for {@link io.legaldocml.akn.attribute.Date}
     */
    public static final String DATE = "date";

    /**
     * CoreAttribute for {@link io.legaldocml.akn.attribute.Date}
     */
    public static final String DICTIONARY = "dictionary";

    /**
     * CoreAttribute for {@link io.legaldocml.akn.attribute.Duration}
     */
    public static final String DURATION = "duration";

    /**
     * CoreAttribute for :
     * : * {@link io.legaldocml.akn.attribute.EfficacyModType}
     * : * {@link io.legaldocml.akn.attribute.EventType}
     * : * {@link io.legaldocml.akn.attribute.ForceModType}
     * : * {@link io.legaldocml.akn.attribute.LegalSystemModType}
     * : * {@link io.legaldocml.akn.attribute.MeaningModType}
     * : * {@link io.legaldocml.akn.attribute.OpinionType}
     * : * {@link io.legaldocml.akn.attribute.RecordedTimeType}
     * : * {@link io.legaldocml.akn.attribute.RemarkType}
     * : * {@link io.legaldocml.akn.attribute.RestrictionType}
     * : * {@link io.legaldocml.akn.attribute.ResultType}
     * : * {@link io.legaldocml.akn.attribute.ScopeModType}
     * : * {@link io.legaldocml.akn.attribute.TextualModType}
     * : * {@link io.legaldocml.akn.attribute.Type}
     */
    public static final String TYPE = "type";

    /**
     * CoreAttribute for {@link io.legaldocml.akn.attribute.Enactment}
     */
    public static final String STATUS = "status";

    /**
     * CoreAttribute for :
     * : * {@link io.legaldocml.akn.attribute.FillInWidth}
     * : * {@link io.legaldocml.akn.attribute.ImgAtts}
     */
    public static final String WIDTH = "width";

    /**
     * CoreAttribute for {@link io.legaldocml.akn.attribute.Period}
     */
    public static final String PERIOD = "period";

    /**
     * CoreAttribute for :
     * : * {@link io.legaldocml.akn.attribute.For}
     * : * {@link io.legaldocml.akn.attribute.LawyerAtts}
     */
    public static final String FOR = "for";

    /**
     * CoreAttribute for {@link io.legaldocml.akn.attribute.FromLanguage}
     */
    public static final String FROM_LANGUAGE = "fromLanguage";

    /**
     * CoreAttribute for {@link io.legaldocml.akn.attribute.Froozen}
     */
    public static final String FROZEN = "frozen";

    /**
     * CoreAttribute for {@link io.legaldocml.akn.attribute.HTMLattrs}
     */
    public static final String CLASS = "class";

    /**
     * CoreAttribute for {@link io.legaldocml.akn.attribute.HTMLattrs}
     */
    public static final String STYLE = "style";

    /**
     * CoreAttribute for {@link io.legaldocml.akn.attribute.HTMLattrs}
     */
    public static final String TITLE = "title";

    /**
     * CoreAttribute for {@link io.legaldocml.akn.attribute.Id}
     *
     * @deprecated
     */
    @Deprecated
    public static final String ID = "id";

    /**
     * CoreAttribute for {@link io.legaldocml.akn.attribute.Id}
     *
     * @deprecated
     */
    @Deprecated
    public static final String EVOLVING_ID = "evolvingId";

    /**
     * CoreAttribute for {@link io.legaldocml.akn.attribute.Id}
     */
    public static final String EID = "eId";

    /**
     * CoreAttribute for {@link io.legaldocml.akn.attribute.Id}
     */
    public static final String WID = "wId";

    /**
     * CoreAttribute for {@link io.legaldocml.akn.attribute.Id}
     */
    public static final String GUID = "GUID";

    /**
     * CoreAttribute for {@link io.legaldocml.akn.attribute.ImgAtts}
     */
    public static final String HEIGHT = "height";

    /**
     * CoreAttribute for :
     * : * {@link io.legaldocml.akn.attribute.Interval}
     * : * {@link io.legaldocml.akn.attribute.MappingAtts}
     */
    public static final String START = "start";

    /**
     * CoreAttribute for :
     * : * {@link io.legaldocml.akn.attribute.Interval}
     * : * {@link io.legaldocml.akn.attribute.MappingAtts}
     */
    public static final String END = "end";

    /**
     * CoreAttribute for {@link io.legaldocml.akn.attribute.Language}
     */
    public static final String LANGUAGE = "language";

    /**
     * CoreAttribute for {@link io.legaldocml.akn.attribute.LawyerAtts}
     */
    public static final String EMPOWERED_BY = "empoweredBy";

    /**
     * CoreAttribute for {@link io.legaldocml.akn.attribute.Level}
     */
    public static final String LEVEL = "level";

    /**
     * CoreAttribute for {@link io.legaldocml.akn.attribute.Link}
     */
    public static final String HREF = "href";

    /**
     * CoreAttribute for {@link io.legaldocml.akn.attribute.MappingAtts}
     */
    public static final String ORIGINAL = "original";

    /**
     * CoreAttribute for {@link io.legaldocml.akn.attribute.MappingAtts}
     */
    public static final String CURRENT = "current";

    /**
     * CoreAttribute for {@link io.legaldocml.akn.attribute.Modifiers}
     */
    public static final String EXCLUSION = "exclusion";

    /**
     * CoreAttribute for {@link io.legaldocml.akn.attribute.Modifiers}
     */
    public static final String INCOMPLETE = "incomplete";

    /**
     * CoreAttribute for {@link io.legaldocml.akn.attribute.Name}
     */
    public static final String NAME = "name";

    /**
     * CoreAttribute for {@link io.legaldocml.akn.attribute.NormalizedAtt}
     */
    public static final String NORMALIZED = "normalized";

    /**
     * CoreAttribute for {@link io.legaldocml.akn.attribute.Notes}
     */
    public static final String MARKER = "marker";

    /**
     * CoreAttribute for {@link io.legaldocml.akn.attribute.Notes}
     */
    public static final String PLACEMENT = "placement";

    /**
     * CoreAttribute for {@link io.legaldocml.akn.attribute.Notes}
     */
    public static final String PLACEMENT_BASE = "placementBase";

    /**
     * CoreAttribute for {@link io.legaldocml.akn.attribute.Number}
     */
    public static final String NUMBER = "number";

    /**
     * CoreAttribute for {@link io.legaldocml.akn.attribute.OriginalText}
     */
    public static final String ORIGINAL_TEXT = "originalText";

    /**
     * CoreAttribute for {@link io.legaldocml.akn.attribute.Originating}
     */
    public static final String ORIGINATING_EXPRESSION = "originatingExpression";

    /**
     * CoreAttribute for {@link io.legaldocml.akn.attribute.Outcome}
     */
    public static final String OUTCOME = "outcome";

    /**
     * CoreAttribute for {@link io.legaldocml.akn.attribute.Pivot}
     */
    public static final String PIVOT = "pivot";

    /**
     * CoreAttribute for {@link io.legaldocml.akn.attribute.PortionAtt}
     */
    public static final String INCLUDED_IN = "includedIn";

    /**
     * CoreAttribute for {@link io.legaldocml.akn.attribute.Pos}
     */
    public static final String POS = "pos";

    /**
     * CoreAttribute for {@link io.legaldocml.akn.attribute.Quote}
     */
    public static final String START_QUOTE = "startQuote";

    /**
     * CoreAttribute for {@link io.legaldocml.akn.attribute.Quote}
     */
    public static final String END_QUOTE = "endQuote";

    /**
     * CoreAttribute for {@link io.legaldocml.akn.attribute.Quote}
     */
    public static final String INLINE_QUOTE = "inlineQuote";

    /**
     * CoreAttribute for {@link io.legaldocml.akn.attribute.Range}
     */
    public static final String FROM = "from";

    /**
     * CoreAttribute for {@link io.legaldocml.akn.attribute.Refers}
     */
    public static final String REFERS_TO = "refersTo";

    /**
     * CoreAttribute for {@link io.legaldocml.akn.attribute.Role}
     */
    public static final String AS = "as";

    /**
     * CoreAttribute for {@link io.legaldocml.akn.attribute.Show}
     */
    public static final String SHOW_AS = "showAs";

    /**
     * CoreAttribute for {@link io.legaldocml.akn.attribute.Show}
     */
    public static final String SHORT_FORM = "shortForm";

    /**
     * CoreAttribute for {@link io.legaldocml.akn.attribute.Source}
     */
    public static final String SOURCE = "source";

    /**
     * CoreAttribute for {@link io.legaldocml.akn.attribute.Source}
     */
    public static final String TO = "to";

    /**
     * CoreAttribute for {@link io.legaldocml.akn.attribute.Source}
     */
    public static final String START_TIME = "startTime";

    /**
     * CoreAttribute for {@link io.legaldocml.akn.attribute.Source}
     */
    public static final String END_TIME = "endTime";

    /**
     * CoreAttribute for {@link io.legaldocml.akn.attribute.Src}
     */
    public static final String SRC = "src";

    /**
     * CoreAttribute for {@link io.legaldocml.akn.attribute.Src}
     */
    public static final String ALT = "alt";

    /**
     * CoreAttribute for {@link io.legaldocml.akn.attribute.TableAtts}
     */
    public static final String BORDER = "border";

    /**
     * CoreAttribute for {@link io.legaldocml.akn.attribute.TableAtts}
     */

    public static final String CELLSPACING = "cellspacing";

    /**
     * CoreAttribute for {@link io.legaldocml.akn.attribute.TableAtts}
     */
    public static final String CELLPADDING = "cellpadding";

    /**
     * CoreAttribute for {@link io.legaldocml.akn.attribute.Target}
     */
    public static final String TARGET = "target";

    /**
     * CoreAttribute for {@link io.legaldocml.akn.attribute.Time}
     */
    public static final String TIME = "time";

    /**
     * CoreAttribute for {@link io.legaldocml.akn.attribute.UpTo}
     */
    public static final String UP_TO = "upTo";

    /**
     * CoreAttribute for {@link io.legaldocml.akn.attribute.VoteAtts}
     */
    public static final String CHOICE = "choice";

}
