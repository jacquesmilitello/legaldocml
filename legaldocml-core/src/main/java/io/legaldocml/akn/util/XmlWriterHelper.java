package io.legaldocml.akn.util;

import io.legaldocml.LegalDocMlException;
import io.legaldocml.akn.AknAttributes;
import io.legaldocml.akn.attribute.Actor;
import io.legaldocml.akn.attribute.Agent;
import io.legaldocml.akn.attribute.Alt;
import io.legaldocml.akn.attribute.Authoritative;
import io.legaldocml.akn.attribute.BooleanValue;
import io.legaldocml.akn.attribute.CellAttrs;
import io.legaldocml.akn.attribute.Contains;
import io.legaldocml.akn.attribute.Date;
import io.legaldocml.akn.attribute.Dictionary;
import io.legaldocml.akn.attribute.Duration;
import io.legaldocml.akn.attribute.EfficacyModType;
import io.legaldocml.akn.attribute.Enactment;
import io.legaldocml.akn.attribute.EventType;
import io.legaldocml.akn.attribute.FillInWidth;
import io.legaldocml.akn.attribute.For;
import io.legaldocml.akn.attribute.FromLanguage;
import io.legaldocml.akn.attribute.HTMLattrs;
import io.legaldocml.akn.attribute.IdOpt;
import io.legaldocml.akn.attribute.IdReq;
import io.legaldocml.akn.attribute.Interval;
import io.legaldocml.akn.attribute.LawyerAtts;
import io.legaldocml.akn.attribute.LegalSystemModType;
import io.legaldocml.akn.attribute.Level;
import io.legaldocml.akn.attribute.LinkOpt;
import io.legaldocml.akn.attribute.LinkReq;
import io.legaldocml.akn.attribute.MappingAtts;
import io.legaldocml.akn.attribute.MeaningModType;
import io.legaldocml.akn.attribute.Modifiers;
import io.legaldocml.akn.attribute.Name;
import io.legaldocml.akn.attribute.NormalizedAtt;
import io.legaldocml.akn.attribute.Notes;
import io.legaldocml.akn.attribute.Number;
import io.legaldocml.akn.attribute.OpinionType;
import io.legaldocml.akn.attribute.OriginalText;
import io.legaldocml.akn.attribute.Originating;
import io.legaldocml.akn.attribute.Outcome;
import io.legaldocml.akn.attribute.Period;
import io.legaldocml.akn.attribute.Pivot;
import io.legaldocml.akn.attribute.PortionAtt;
import io.legaldocml.akn.attribute.Pos;
import io.legaldocml.akn.attribute.Quote;
import io.legaldocml.akn.attribute.RangeOpt;
import io.legaldocml.akn.attribute.RangeReq;
import io.legaldocml.akn.attribute.RefersOpt;
import io.legaldocml.akn.attribute.RefersReq;
import io.legaldocml.akn.attribute.RestrictionType;
import io.legaldocml.akn.attribute.ResultType;
import io.legaldocml.akn.attribute.Role;
import io.legaldocml.akn.attribute.ScopeModType;
import io.legaldocml.akn.attribute.ShowOpt;
import io.legaldocml.akn.attribute.ShowReq;
import io.legaldocml.akn.attribute.Source;
import io.legaldocml.akn.attribute.SpeechAtts;
import io.legaldocml.akn.attribute.Src;
import io.legaldocml.akn.attribute.TableAtts;
import io.legaldocml.akn.attribute.Target;
import io.legaldocml.akn.attribute.TextualModType;
import io.legaldocml.akn.attribute.Time;
import io.legaldocml.akn.attribute.Type;
import io.legaldocml.akn.attribute.UpToOpt;
import io.legaldocml.akn.attribute.ValueOpt;
import io.legaldocml.akn.attribute.ValueReq;
import io.legaldocml.akn.attribute.VoteAtts;
import io.legaldocml.akn.element.Attributes;
import io.legaldocml.akn.exception.WriterMandatoryAttributeException;
import io.legaldocml.io.XmlWriter;

import java.io.IOException;
import java.time.OffsetDateTime;

import static io.legaldocml.akn.element.Attributes.ADDRESS_AUTHORITATIVE;
import static io.legaldocml.akn.element.Attributes.ADDRESS_DICTIONARY;
import static io.legaldocml.akn.element.Attributes.ADDRESS_FOR;
import static io.legaldocml.akn.element.Attributes.ADDRESS_FROM_LANGUAGE;
import static io.legaldocml.akn.element.Attributes.ADDRESS_ORIGINATING_EXPRESSION;
import static io.legaldocml.akn.element.Attributes.ADDRESS_PERIOD;
import static io.legaldocml.akn.element.Attributes.ADDRESS_PIVOT;
import static io.legaldocml.akn.element.Attributes.ADDRESS_POS;
import static io.legaldocml.akn.element.Attributes.ADDRESS_TYPE;
import static io.legaldocml.akn.element.Attributes.ADDRESS_UPTO;
import static io.legaldocml.unsafe.UnsafeString.getChars;

/**
 * @author <a href="mailto:jacques.militello@gmail.com">Jacques Militello</a>
 */
public final class XmlWriterHelper {

    private static final char[] TRUE = new char[]{'t', 'r', 'u', 'e'};

    private static final char[] FALSE = new char[]{'f', 'a', 'l', 's', 'e'};


    private XmlWriterHelper() {
    }

    public static void writeLinkReq(XmlWriter writer, LinkReq req) throws IOException {
        if (req.getHref() == null) {
            throw new RuntimeException();
        }
        writer.writeAttribute(Attributes.ADDRESS_HREF, 4, req.getHref().getChars());
    }

    public static void writeLinkOpt(XmlWriter writer, LinkOpt linkOpt) throws IOException {
        if (linkOpt.getHref() != null) {
            writer.writeAttribute(Attributes.ADDRESS_HREF, 4, linkOpt.getHref().getChars());
        }
    }

    public static void writeOutcome(XmlWriter writer, Outcome outcome) throws IOException {
        if (outcome.getOutcome() != null) {
            writer.writeAttribute(Attributes.ADDRESS_OUTCOME, 7, outcome.getOutcome().getChars());
        }
    }

    public static void writeSource(XmlWriter writer, Source source) throws IOException {
        if (source.getSource() == null) {
            throwException(writer, new WriterMandatoryAttributeException(source, AknAttributes.SOURCE, writer));
        } else {
            writer.writeAttribute(Attributes.ADDRESS_SOURCE, 6, source.getSource().getChars());
        }
    }

    public static void writeEnactment(XmlWriter writer, Enactment enactment) throws IOException {
        if (enactment.getStatus() != null) {
            writer.writeAttribute(Attributes.ADDRESS_STATUS, 6, getChars(enactment.getStatus().name()));
        }
        if (enactment.getPeriod() != null) {
            writer.writeAttribute(Attributes.ADDRESS_PERIOD, 6, enactment.getPeriod().getChars());
        }
    }

    public static void writeModifiers(XmlWriter writer, Modifiers modifiers) throws IOException {
        if (modifiers.getExclusion() != null) {
            if (modifiers.getExclusion().booleanValue()) {
                writer.writeAttribute(Attributes.ADDRESS_EXCLUSION, 9, TRUE);
            } else {
                writer.writeAttribute(Attributes.ADDRESS_EXCLUSION, 9, FALSE);
            }
        }

        if (modifiers.getIncomplete() != null) {
            if (modifiers.getIncomplete().booleanValue()) {
                writer.writeAttribute(Attributes.ADDRESS_INCOMPLETE, 10, TRUE);
            } else {
                writer.writeAttribute(Attributes.ADDRESS_INCOMPLETE, 10, FALSE);
            }

        }
    }

    public static void writeName(XmlWriter writer, Name name) throws IOException {
        if (name.getName() == null) {
            throwException(writer, new WriterMandatoryAttributeException(name, AknAttributes.NAME, writer));
        } else {
            writer.writeAttribute(Attributes.ADDRESS_NAME, 4, getChars(name.getName()));
        }

    }

    public static void writeValue(XmlWriter writer, ValueReq value) throws IOException {
        if (value.getValue() == null) {
            throwException(writer, new WriterMandatoryAttributeException(value, AknAttributes.VALUE, writer));
        } else {
            writer.writeAttribute(Attributes.ADDRESS_VALUE, 5, getChars(value.getValue()));
        }

    }

    public static void writeOptValue(XmlWriter writer, ValueOpt optValue) throws IOException {
        if (optValue.getValue() != null) {
            writer.writeAttribute(Attributes.ADDRESS_VALUE, 5, getChars(optValue.getValue()));
        }
    }

    public static void writeSrc(XmlWriter writer, Src src) throws IOException {
        if (src.getSrc() == null) {
            throw new RuntimeException();
        }
        writer.writeAttribute(Attributes.ADDRESS_SRC, 3, src.getSrc().getChars());

        if (src.getAlt() != null) {
            writer.writeAttribute(Attributes.ADDRESS_ALT, 3, getChars(src.getAlt()));
        }
    }

    public static void writeShow(XmlWriter writer, ShowReq show) throws IOException {
        if (show.getShowAs() == null) {
            throw new RuntimeException();
        }
        writer.writeAttribute(Attributes.ADDRESS_SHOW_AS, 6, getChars(show.getShowAs()));

        if (show.getShortForm() != null) {
            writer.writeAttribute(Attributes.ADDRESS_SHORT_FORM, 9, getChars(show.getShortForm()));
        }
    }

    public static void writeShow(XmlWriter writer, ShowOpt show) throws IOException {
        if (show.getShowAs() != null) {
            writer.writeAttribute(Attributes.ADDRESS_SHOW_AS, 6, getChars(show.getShowAs()));
        }
        if (show.getShortForm() != null) {
            writer.writeAttribute(Attributes.ADDRESS_SHORT_FORM, 9, getChars(show.getShortForm()));
        }
    }

    public static void writeDate(XmlWriter writer, Date date) throws IOException {
        if (date.getDate() == null) {
            throwException(writer, new WriterMandatoryAttributeException(date, AknAttributes.DATE, writer));
        } else {
            OffsetDateTime dt = date.getDate();
            if ((dt.getHour() == 0) && (dt.getMinute() == 0) && (dt.getSecond() == 0) && (dt.getNano() == 0)) {
                writer.writeAttribute(Attributes.ADDRESS_DATE, 4, date.getDate().toLocalDate());
            } else {
                writer.writeAttribute(Attributes.ADDRESS_DATE, 4, date.getDate());
            }
        }

    }

    public static void writeContains(XmlWriter writer, Contains contains) throws IOException {
        if (contains.getContains() != null) {
            writer.writeAttribute(Attributes.ADDRESS_CONTAINS, 8, getChars(contains.getContains().name()));
        }
    }

    public static void writeNumber(XmlWriter writer, Number number) throws IOException {
        if (number.getNumber() != null) {
            writer.writeAttribute(Attributes.ADDRESS_NUMBER, 6, getChars(number.getNumber()));
        }
    }

    public static void writeHTMLattrs(XmlWriter writer, HTMLattrs htmLattrs) throws IOException {
        if (htmLattrs.getClazz() != null) {
            writer.writeAttribute(Attributes.ADDRESS_CLASS, 5, getChars(htmLattrs.getClazz()));
        }
        if (htmLattrs.getStyle() != null) {
            writer.writeAttribute(Attributes.ADDRESS_STYLE, 5, getChars(htmLattrs.getStyle()));
        }
        if (htmLattrs.getTitle() != null) {
            writer.writeAttribute(Attributes.ADDRESS_TITLE, 5, getChars(htmLattrs.getTitle()));
        }

    }

    public static void writeAlt(XmlWriter writer, Alt alt) throws IOException {
        if (alt.getAlternativeTo() != null) {
            writer.writeAttribute(Attributes.ADDRESS_ALTERNATIVE_TO, 13, alt.getAlternativeTo().getChars());
        }
    }

    public static void writeNotes(XmlWriter writer, Notes notes) throws IOException {
        if (notes.getMarker() != null) {
            writer.writeAttribute(Attributes.ADDRESS_MARKER, 6, getChars(notes.getMarker()));
        }
        if (notes.getPlacement() != null) {
            writer.writeAttribute(Attributes.ADDRESS_PLACEMENT, 9, getChars(notes.getPlacement().name()));
        }
        if (notes.getPlacementBase() != null) {
            writer.writeAttribute(Attributes.ADDRESS_PLACEMENT_BASE, 13, notes.getPlacementBase().getChars());
        }
    }

    public static void writeCellAttrs(XmlWriter writer, CellAttrs cellAttrs) throws IOException {
        if (cellAttrs.getColSpan() != null) {
            writer.writeAttribute(Attributes.ADDRESS_COL_SPAN, 7, getChars(cellAttrs.getColSpan().toString()));
        }
        if (cellAttrs.getRowSpan() != null) {
            writer.writeAttribute(Attributes.ADDRESS_ROW_SPAN, 7, getChars(cellAttrs.getRowSpan().toString()));
        }
    }

    @SuppressWarnings("deprecation")
	public static void writeIdReq(XmlWriter writer, IdReq idReq) throws IOException {

        if (writer.getVersion() == 2) {

            if (idReq.getId() == null) {
                // for V2 => content blockopt and V3 content blockreq
                //throw new RuntimeException("-->" + idReq);
            } else {
                writer.writeAttribute(Attributes.ADDRESS_ID, 2, getChars(idReq.getId()));
            }


            if (idReq.getEvolvingId() != null) {
                writer.writeAttribute(Attributes.ADDRESS_EVOLVING_ID, 10, getChars(idReq.getEvolvingId()));
            }
        } else {
            if (idReq.getEid() != null) {
                writer.writeAttribute(Attributes.ADDRESS_EID, 3, idReq.getEid().getChars());
            }
            if (idReq.getWid() != null) {
                writer.writeAttribute(Attributes.ADDRESS_WID, 3, idReq.getWid().getChars());
            }
            if (idReq.getGUID() != null) {
                writer.writeAttribute(Attributes.ADDRESS_GUID, 4, idReq.getGUID().getChars());
            }

        }


    }

    @SuppressWarnings("deprecation")
	public static void writeIdOpt(XmlWriter writer, IdOpt idOpt) throws IOException {

        if (writer.getVersion() == 2) {
            if (idOpt.getId() != null) {
                writer.writeAttribute(Attributes.ADDRESS_ID, 2, getChars(idOpt.getId()));
            }

            if (idOpt.getEvolvingId() != null) {
                writer.writeAttribute(Attributes.ADDRESS_EVOLVING_ID, 10, getChars(idOpt.getEvolvingId()));
            }
        } else {
            if (idOpt.getEid() != null) {
                writer.writeAttribute(Attributes.ADDRESS_EID, 3, idOpt.getEid().getChars());
            }
            if (idOpt.getWid() != null) {
                writer.writeAttribute(Attributes.ADDRESS_WID, 3, idOpt.getWid().getChars());
            }

        }

    }


    public static void writeActor(XmlWriter writer, Actor actor) throws IOException {
        if (actor.getActor() != null) {
            writer.writeAttribute(Attributes.ADDRESS_ACTOR, 5, getChars(actor.getActor()));
        }
    }

    public static void writeRole(XmlWriter writer, Role role) throws IOException {
        if (role.getAs() != null) {
            writer.writeAttribute(Attributes.ADDRESS_ROLE, 2, role.getAs().getChars());
        }

    }

    public static void writeRefers(XmlWriter writer, RefersOpt refers) throws IOException {
        if (refers.getRefersTo() != null) {
            writer.writeAttribute(Attributes.ADDRESS_REFERS, 8, refers.getRefersTo().getChars());
        }
    }

    public static void writeRefersReq(XmlWriter writer, RefersReq refersReq) throws IOException {
        if (refersReq.getRefersTo() == null) {
            throwException(writer,  new WriterMandatoryAttributeException(refersReq, AknAttributes.REFERS_TO, writer));
        } else {
            writer.writeAttribute(Attributes.ADDRESS_REFERS, 8, refersReq.getRefersTo().getChars());
        }
    }

    public static void writeTime(XmlWriter writer, Time time) throws IOException {
        if (time == null) {
            throw new RuntimeException();
        }
        writer.writeAttribute(Attributes.ADDRESS_TIME, 4, time.getTime());
    }

    public static void writeSpeechAtts(XmlWriter writer, SpeechAtts speechAtts) throws IOException {
        if (speechAtts.getBy() == null) {
            throw new RuntimeException();
        }
        writer.writeAttribute(Attributes.ADDRESS_BY, 2, speechAtts.getBy().getChars());

        if (speechAtts.getTo() != null) {
            writer.writeAttribute(Attributes.ADDRESS_TO, 2, speechAtts.getTo().getChars());
        }

        if (speechAtts.getAs() != null) {
            writer.writeAttribute(Attributes.ADDRESS_AS, 2, speechAtts.getAs().getChars());
        }
    }

    public static void writeVoteAtts(XmlWriter writer, VoteAtts voteAtts) throws IOException {

        if (voteAtts.getBy() == null) {
            throw new RuntimeException();
        }

        writer.writeAttribute(Attributes.ADDRESS_BY, 2, voteAtts.getBy().getChars());

    }

    public static void writeAgent(XmlWriter writer, Agent agent) throws IOException {
        if (agent.getBy() == null) {
            throw new RuntimeException();
        }
        writer.writeAttribute(Attributes.ADDRESS_BY, 2, agent.getBy().getChars());
    }

    public static void writePortionAtt(XmlWriter writer, PortionAtt portionAtt) throws IOException {
        if (portionAtt.getIncludedIn() == null) {
            throwException(writer, new WriterMandatoryAttributeException(portionAtt, AknAttributes.INCLUDED_IN, writer));
        } else {
            writer.writeAttribute(Attributes.ADDRESS_INCLUDED_IN, 10, portionAtt.getIncludedIn().getChars());
        }
    }

    public static void writeBooleanValue(XmlWriter writer, BooleanValue booleanValue) throws IOException {
        if (booleanValue.getValue() == null) {
            throw new RuntimeException();
        }

        if (booleanValue.getValue().booleanValue()) {
            writer.writeAttribute(Attributes.ADDRESS_VALUE, 5, TRUE);
        } else {
            writer.writeAttribute(Attributes.ADDRESS_VALUE, 5, FALSE);
        }
    }

    public static void writeInterval(XmlWriter writer, Interval interval) throws IOException {
        if (interval.getStart() != null) {
            writer.writeAttribute(Attributes.ADDRESS_START, 5, interval.getStart().getChars());
        }
        if (interval.getEnd() != null) {
            writer.writeAttribute(Attributes.ADDRESS_END, 3, interval.getEnd().getChars());
        }
    }

    public static void writeFromLanguage(XmlWriter writer, FromLanguage fromLanguage) throws IOException {
        if (fromLanguage.getFromLanguage() == null) {
            throw new RuntimeException();
        }
        writer.writeAttribute(ADDRESS_FROM_LANGUAGE, 12, getChars(fromLanguage.getFromLanguage()));
    }


    public static void writeDuration(XmlWriter writer, Duration duration) {
    }

    public static void writeResultType(XmlWriter writer, ResultType resultType) throws IOException {
        if (resultType.getType() == null) {
            throwException(writer, new WriterMandatoryAttributeException(resultType, AknAttributes.TYPE, writer));
        } else {
            writer.writeAttribute(Attributes.ADDRESS_TYPE, 4, getChars(resultType.getType().name()));
        }
    }

    public static void writeRestrictionType(XmlWriter writer, RestrictionType restrictionType) throws IOException {
        if (restrictionType.getType() != null) {
            writer.writeAttribute(Attributes.ADDRESS_TYPE, 4, getChars(restrictionType.getType().name()));
        }
    }


    public static void writeAuthoritative(XmlWriter writer, Authoritative authoritative) throws IOException {
        if (authoritative.getAuthoritative() != null) {
            if (authoritative.getAuthoritative().booleanValue()) {
                writer.writeAttribute(ADDRESS_AUTHORITATIVE, 13, TRUE);
            } else {
                writer.writeAttribute(ADDRESS_AUTHORITATIVE, 13, FALSE);
            }
        }
    }

    public static void writePivot(XmlWriter writer, Pivot pivot) throws IOException {
        if (pivot.getPivot() != null) {
            writer.writeAttribute(ADDRESS_PIVOT, 5, getChars(pivot.getPivot()));
        }
    }

    public static void writeMappingAtts(XmlWriter writer, MappingAtts mapping) {

    }

    public static void writeFor(XmlWriter writer, For for_) throws IOException {
        if (for_.getFor() != null) {
            writer.writeAttribute(ADDRESS_FOR, 3, for_.getFor().getChars());
        }
    }

    public static void writeUpTo(XmlWriter writer, UpToOpt upTo) throws IOException {
        if (upTo.getUpTo() != null) {
            writer.writeAttribute(ADDRESS_UPTO, 4, upTo.getUpTo().getChars());
        }
    }

    public static void writePos(XmlWriter writer, Pos pos) throws IOException {
        if (pos.getPos() != null) {
            writer.writeAttribute(ADDRESS_POS, 3, getChars(pos.getPos().name()));
        }
    }

    public static void writeOriginating(XmlWriter writer, Originating originating) throws IOException {
        if (originating.getOriginatingExpression() != null) {
            if (originating.getOriginatingExpression().booleanValue()) {
                writer.writeAttribute(ADDRESS_ORIGINATING_EXPRESSION, 21, TRUE);
            } else {
                writer.writeAttribute(ADDRESS_ORIGINATING_EXPRESSION, 21, FALSE);
            }
        }
    }

    public static void writeEventType(XmlWriter writer, EventType eventType) throws IOException {
        if (eventType.getType() != null) {
            writer.writeAttribute(ADDRESS_TYPE, 4, getChars(eventType.getType().name()));
        }
    }

    public static void writePeriod(XmlWriter writer, Period period) throws IOException {
        if (period.getPeriod() != null) {
            writer.writeAttribute(ADDRESS_PERIOD, 6, period.getPeriod().getChars());
        }
    }

    public static void writeTextualModType(XmlWriter writer, TextualModType textualModType) throws IOException {
        if (textualModType.getType() == null) {
            throw new RuntimeException();
        }
        writer.writeAttribute(ADDRESS_TYPE, 4, getChars(textualModType.getType().name()));
    }

    public static void writeMeaningModType(XmlWriter writer, MeaningModType meaningModType) throws IOException {
        if (meaningModType.getType() == null) {
            throw new RuntimeException();
        }
        writer.writeAttribute(ADDRESS_TYPE, 4, getChars(meaningModType.getType().name()));

    }

    public static void writeScopeModType(XmlWriter writer, ScopeModType scopeModType) throws IOException {
        if (scopeModType.getType() == null) {
            throw new RuntimeException();
        }
        writer.writeAttribute(ADDRESS_TYPE, 4, getChars(scopeModType.getType().name()));

    }

    public static void writeDictionary(XmlWriter writer, Dictionary dictionary) throws IOException {
        if (dictionary.getDictionary() != null) {
            writer.writeAttribute(ADDRESS_DICTIONARY, 10, dictionary.getDictionary().getChars());
        }
    }

    public static void writeLevel(XmlWriter writer, Level level) throws IOException {
        if (level.getLevel() == null) {
            throw new RuntimeException();
        } else {
            writer.writeAttribute(Attributes.ADDRESS_LEVEL, 5, getChars(level.getLevel()));
        }
    }

    public static void writeEfficacyMods(XmlWriter writer, EfficacyModType efficacyModType) throws IOException {
        if (efficacyModType.getType() == null) {
            throw new RuntimeException();
        }
        writer.writeAttribute(ADDRESS_TYPE, 4, getChars(efficacyModType.getType().name()));
    }

    public static void writeLegalSystemMods(XmlWriter writer, LegalSystemModType legalSystemModType) throws IOException {
        if (legalSystemModType.getType() == null) {
            throw new RuntimeException();
        }
        writer.writeAttribute(ADDRESS_TYPE, 4, getChars(legalSystemModType.getType().name()));
    }

    public static void writeTableAtts(XmlWriter writer, TableAtts tableAtts) throws IOException {
        if (tableAtts.getWidth() != null) {
            writer.writeAttribute(Attributes.ADDRESS_WIDTH, 5, getChars(tableAtts.getWidth().toString()));
        }
        if (tableAtts.getBorder() != null) {
            writer.writeAttribute(Attributes.ADDRESS_BORDER, 6, getChars(tableAtts.getBorder().toString()));
        }
        if (tableAtts.getCellspacing() != null) {
            writer.writeAttribute(Attributes.ADDRESS_CELLSPACING, 11, getChars(tableAtts.getCellspacing().toString()));
        }
        if (tableAtts.getCellpadding() != null) {
            writer.writeAttribute(Attributes.ADDRESS_CELLPADDING, 11, getChars(tableAtts.getCellpadding().toString()));
        }
    }

    public static void writeQuote(XmlWriter writer, Quote quote) throws IOException {
        if (quote.getStartQuote() != null) {
            writer.writeAttribute(Attributes.ADDRESS_STARTQUOTE, 10, getChars(quote.getStartQuote()));
        }
        if (quote.getEndQuote() != null) {
            writer.writeAttribute(Attributes.ADDRESS_ENDQUOTE, 8, getChars(quote.getEndQuote()));
        }
        if (quote.getInlineQuote() != null) {
            writer.writeAttribute(Attributes.ADDRESS_INLINEQUOTE, 12, getChars(quote.getInlineQuote()));
        }
    }

    public static void writeFillInWidth(XmlWriter writer, FillInWidth fillInWidth) throws IOException {
        if (fillInWidth.getWidth() != null) {
            writer.writeAttribute(Attributes.ADDRESS_WIDTH, 5, getChars(fillInWidth.getWidth()));
        }
    }

    public static void writeOriginalText(XmlWriter writer, OriginalText originalText) {

    }

    public static void writeRange(XmlWriter writer, RangeReq rangeReq) throws IOException {
        if (rangeReq.getFrom() == null) {
            throwException(writer, new WriterMandatoryAttributeException(rangeReq, AknAttributes.FROM, writer));
        } else {
            writer.writeAttribute(Attributes.ADDRESS_FROM, 4, rangeReq.getFrom().getChars());
        }
        if (rangeReq.getUpTo() == null) {
            throwException(writer, new WriterMandatoryAttributeException(rangeReq, AknAttributes.UP_TO, writer));
        } else {
            writer.writeAttribute(Attributes.ADDRESS_UPTO, 4, rangeReq.getUpTo().getChars());
        }
    }

    public static void writeRange(XmlWriter writer, RangeOpt range) throws IOException {
        if (range.getFrom() == null) {
            throwException(writer, new WriterMandatoryAttributeException(range, AknAttributes.FROM_LANGUAGE, writer));
        } else {
            writer.writeAttribute(Attributes.ADDRESS_FROM, 4, range.getFrom().getChars());
        }
        if (range.getUpTo() != null) {
            writer.writeAttribute(Attributes.ADDRESS_UPTO, 4, range.getUpTo().getChars());
        }
    }

    public static void writeNormalizedAtt(XmlWriter writer, NormalizedAtt normalizedAtt) throws IOException {
        if (normalizedAtt.getNormalized() != null) {
            writer.writeAttribute(Attributes.ADDRESS_NORMALIZED, 10, getChars(normalizedAtt.getNormalized()));
        }
    }

    public static void writeOpinionType(XmlWriter writer, OpinionType opinionType) throws IOException {
        writeAgent(writer, opinionType);
        if (opinionType.getType() != null) {
            writer.writeAttribute(Attributes.ADDRESS_TYPE, 4, getChars(opinionType.getType().name()));
        }
    }

    public static void writeLawyerAtts(XmlWriter writer, LawyerAtts lawyerAtts) throws IOException {
        writeRole(writer, lawyerAtts);
        if (lawyerAtts.getFor() != null) {
            writer.writeAttribute(Attributes.ADDRESS_FOR, 3, lawyerAtts.getFor().getChars());
        }
        if (lawyerAtts.getEmpoweredBy() != null) {
            writer.writeAttribute(Attributes.ADDRESS_EMPOWERED_BY, 11, lawyerAtts.getEmpoweredBy().getChars());
        }

    }

    public static void writeTarget(XmlWriter writer, Target target) throws IOException {
        if (target.getTarget() != null) {
            writer.writeAttribute(Attributes.ADDRESS_TARGET, 6, getChars(target.getTarget()));
        }
    }

    public static void writeType(XmlWriter writer, Type type) throws IOException {
        if (type.getType() != null) {
            writer.writeAttribute(Attributes.ADDRESS_TYPE, 4, getChars(type.getType()));
        }
    }

    public static void throwException(XmlWriter writer, LegalDocMlException exception) {
        if (writer.isPermissive()) {
            writer.addExpcetion(exception);
        } else {
            throw exception;
        }
    }



}

