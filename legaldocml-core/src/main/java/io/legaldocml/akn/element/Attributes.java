package io.legaldocml.akn.element;

import io.legaldocml.akn.AknObject;
import io.legaldocml.akn.AttributeBiConsumer;
import io.legaldocml.akn.attribute.Actor;
import io.legaldocml.akn.attribute.Agent;
import io.legaldocml.akn.attribute.Alt;
import io.legaldocml.akn.attribute.Authoritative;
import io.legaldocml.akn.attribute.CellAttrs;
import io.legaldocml.akn.attribute.Contains;
import io.legaldocml.akn.attribute.Core;
import io.legaldocml.akn.attribute.Date;
import io.legaldocml.akn.attribute.Dictionary;
import io.legaldocml.akn.attribute.Duration;
import io.legaldocml.akn.attribute.Enactment;
import io.legaldocml.akn.attribute.For;
import io.legaldocml.akn.attribute.FromLanguage;
import io.legaldocml.akn.attribute.HTMLattrs;
import io.legaldocml.akn.attribute.Id;
import io.legaldocml.akn.attribute.ImgAtts;
import io.legaldocml.akn.attribute.Interval;
import io.legaldocml.akn.attribute.Language;
import io.legaldocml.akn.attribute.LawyerAtts;
import io.legaldocml.akn.attribute.Level;
import io.legaldocml.akn.attribute.Link;
import io.legaldocml.akn.attribute.Modifiers;
import io.legaldocml.akn.attribute.Name;
import io.legaldocml.akn.attribute.NormalizedAtt;
import io.legaldocml.akn.attribute.Notes;
import io.legaldocml.akn.attribute.Number;
import io.legaldocml.akn.attribute.Originating;
import io.legaldocml.akn.attribute.Outcome;
import io.legaldocml.akn.attribute.Period;
import io.legaldocml.akn.attribute.Pivot;
import io.legaldocml.akn.attribute.PortionAtt;
import io.legaldocml.akn.attribute.Pos;
import io.legaldocml.akn.attribute.Quote;
import io.legaldocml.akn.attribute.Range;
import io.legaldocml.akn.attribute.RefersOpt;
import io.legaldocml.akn.attribute.Role;
import io.legaldocml.akn.attribute.ShowReq;
import io.legaldocml.akn.attribute.Source;
import io.legaldocml.akn.attribute.SpeechAtts;
import io.legaldocml.akn.attribute.Src;
import io.legaldocml.akn.attribute.TableAtts;
import io.legaldocml.akn.attribute.Target;
import io.legaldocml.akn.attribute.Time;
import io.legaldocml.akn.attribute.Type;
import io.legaldocml.akn.attribute.UpTo;
import io.legaldocml.akn.attribute.ValueReq;
import io.legaldocml.akn.other.ExternalAttribute;
import io.legaldocml.akn.type.AgentRef;
import io.legaldocml.akn.type.ConceptRef;
import io.legaldocml.akn.type.EidRef;
import io.legaldocml.akn.type.EventRefRef;
import io.legaldocml.akn.type.ListReferenceRef;
import io.legaldocml.akn.type.ManifestationURI;
import io.legaldocml.akn.type.NoWhiteSpace;
import io.legaldocml.akn.type.RoleRef;
import io.legaldocml.akn.type.TemporalGroupRef;
import io.legaldocml.akn.type.VoteRef;
import io.legaldocml.akn.type.WidRef;
import io.legaldocml.io.Attribute;
import io.legaldocml.io.CharArray;
import io.legaldocml.io.XmlReader;
import io.legaldocml.io.impl.Buffers;
import io.legaldocml.module.Module;
import io.legaldocml.module.Modules;
import io.legaldocml.util.DateHelper;
import io.legaldocml.util.QnameUtil;
import io.legaldocml.util.ReferenceRef;
import io.legaldocml.util.Uri;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.OffsetDateTime;
import java.time.ZoneOffset;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.function.BiConsumer;

import static io.legaldocml.unsafe.UnsafeHelper.getUnsafe;

/**
 * @author <a href="mailto:jacques.militello@gmail.com">Jacques Militello</a>
 */
public final class Attributes {

    private Attributes() {
    }

    /**
     * Attribute 'breakat'.
     */
    public static final String BREAKAT = "breakat";

    /**
     * Memory address.
     */
    public static final long ADDRESS_ID = Buffers.address(Id.ATTRIBUTE_ID);

    public static final long ADDRESS_EID = Buffers.address(Id.ATTRIBUTE_EID);

    public static final long ADDRESS_WID = Buffers.address(Id.ATTRIBUTE_WID);

    public static final long ADDRESS_GUID = Buffers.address(Id.ATTRIBUTE_GUID);

    public static final long ADDRESS_EVOLVING_ID = Buffers.address(Id.ATTRIBUTE_EVOLVING_ID);

    public static final long ADDRESS_AS = Buffers.address(Role.ATTRIBUTE);

    public static final long ADDRESS_HREF = Buffers.address(Link.ATTRIBUTE);

    public static final long ADDRESS_DATE = Buffers.address(Date.ATTRIBUTE);

    public static final long ADDRESS_NAME = Buffers.address(Name.ATTRIBUTE);

    public static final long ADDRESS_VALUE = Buffers.address(ValueReq.ATTRIBUTE);

    public static final long ADDRESS_FROM_LANGUAGE = Buffers.address(FromLanguage.ATTRIBUTE);

    public static final long ADDRESS_AUTHORITATIVE = Buffers.address(Authoritative.ATTRIBUTE);

    public static final long ADDRESS_PIVOT = Buffers.address(Pivot.ATTRIBUTE);

    public static final long ADDRESS_BY = Buffers.address(Agent.ATTRIBUTE);

    public static final long ADDRESS_TO = Buffers.address(SpeechAtts.ATTRIBUTE_TO);

    public static final long ADDRESS_LANGUAGE = Buffers.address(Language.ATTRIBUTE);

    public static final long ADDRESS_REFERS = Buffers.address(RefersOpt.ATTRIBUTE);

    public static final long ADDRESS_OUTCOME = Buffers.address(Outcome.ATTRIBUTE);

    public static final long ADDRESS_SOURCE = Buffers.address(Source.ATTRIBUTE);

    public static final long ADDRESS_CONTAINS = Buffers.address(Contains.ATTRIBUTE);

    public static final long ADDRESS_SHOW_AS = Buffers.address(ShowReq.ATTRIBUTE_SHOW_AS);

    public static final long ADDRESS_SHORT_FORM = Buffers.address(ShowReq.ATTRIBUTE_SHORT_FORM);

    public static final long ADDRESS_NUMBER = Buffers.address(Number.ATTRIBUTE);

    public static final long ADDRESS_TYPE = Buffers.address(Type.ATTRIBUTE);

    public static final long ADDRESS_ORIGINATING_EXPRESSION = Buffers.address(Originating.ATTRIBUTE);

    public static final long ADDRESS_CLASS = Buffers.address(HTMLattrs.ATTRIBUTE_CLASS);

    public static final long ADDRESS_STYLE = Buffers.address(HTMLattrs.ATTRIBUTE_STYLE);

    public static final long ADDRESS_TITLE = Buffers.address(HTMLattrs.ATTRIBUTE_TITLE);

    public static final long ADDRESS_DICTIONARY = Buffers.address(Dictionary.ATTRIBUTE);

    public static final long ADDRESS_LEVEL = Buffers.address(Level.ATTRIBUTE);

    public static final long ADDRESS_TARGET = Buffers.address(Target.ATTRIBUTE);

    public static final long ADDRESS_CELLSPACING = Buffers.address(TableAtts.ATTRIBUTE_CELLSPACING);

    public static final long ADDRESS_CELLPADDING = Buffers.address(TableAtts.ATTRIBUTE_CELLPADDING);

    public static final long ADDRESS_BORDER = Buffers.address(TableAtts.ATTRIBUTE_BORDER);

    public static final long ADDRESS_WIDTH = Buffers.address(ImgAtts.ATTRIBUTE_WIDTH);

    public static final long ADDRESS_HEIGHT = Buffers.address(ImgAtts.ATTRIBUTE_HEIGHT);

    public static final long ADDRESS_STATUS = Buffers.address(Enactment.ATTRIBUTE);

    public static final long ADDRESS_PERIOD = Buffers.address(Period.ATTRIBUTE);

    public static final long ADDRESS_ALTERNATIVE_TO = Buffers.address(Alt.ATTRIBUTE);

    public static final long ADDRESS_MARKER = Buffers.address(Notes.ATTRIBUTE_MARKER);

    public static final long ADDRESS_PLACEMENT = Buffers.address(Notes.ATTRIBUTE_PLACEMENT);

    public static final long ADDRESS_PLACEMENT_BASE = Buffers.address(Notes.ATTRIBUTE_PLACEMENT_BASE);

    public static final long ADDRESS_COL_SPAN = Buffers.address(CellAttrs.ATTRIBUTE_COL_SPAN);

    public static final long ADDRESS_ROW_SPAN = Buffers.address(CellAttrs.ATTRIBUTE_ROW_SPAN);

    public static final long ADDRESS_SRC = Buffers.address(Src.ATTRIBUTE_SRC);

    public static final long ADDRESS_ALT = Buffers.address(Src.ATTRIBUTE_ALT);

    public static final long ADDRESS_UPTO = Buffers.address(UpTo.ATTRIBUTE);

    public static final long ADDRESS_START = Buffers.address(Interval.ATTRIBUTE_START);

    public static final long ADDRESS_END = Buffers.address(Interval.ATTRIBUTE_END);

    public static final long ADDRESS_DURATION = Buffers.address(Duration.ATTRIBUTE);

    public static final long ADDRESS_ACTOR = Buffers.address(Actor.ATTRIBUTE);

    public static final long ADDRESS_ROLE = Buffers.address(Role.ATTRIBUTE);

    public static final long ADDRESS_EXCLUSION = Buffers.address(Modifiers.ATTRIBUTE_EXCLUSION);

    public static final long ADDRESS_INCOMPLETE = Buffers.address(Modifiers.ATTRIBUTE_INCOMPLETE);

    public static final long ADDRESS_FOR = Buffers.address(For.ATTRIBUTE);

    public static final long ADDRESS_FROM = Buffers.address(Range.ATTRIBUTE);

    public static final long ADDRESS_BREAKAT = Buffers.address(BREAKAT);

    public static final long ADDRESS_TIME = Buffers.address(Time.ATTRIBUTE);

    public static final long ADDRESS_NORMALIZED = Buffers.address(NormalizedAtt.ATTRIBUTE);

    public static final long ADDRESS_INCLUDED_IN = Buffers.address(PortionAtt.ATTRIBUTE);

    public static final long ADDRESS_EMPOWERED_BY = Buffers.address(LawyerAtts.ATTRIBUTE_EMPOWERED_BY);

    public static final long ADDRESS_STARTQUOTE = Buffers.address(Quote.ATTRIBUTE_STARTQUOTE);

    public static final long ADDRESS_INLINEQUOTE = Buffers.address(Quote.ATTRIBUTE_INLINEQUOTE);

    public static final long ADDRESS_ENDQUOTE = Buffers.address(Quote.ATTRIBUTE_ENDQUOTE);

    public static final long ADDRESS_POS = Buffers.address(Pos.ATTRIBUTE);

    public static BiConsumer<AknObject, CharArray> biConsumerInteger(long addr) {
        return (i, s) -> getUnsafe().putObject(i, addr, Integer.valueOf(s.toString()));
    }

    public static BiConsumer<AknObject, CharArray> biConsumerString(long addr) {
        return (i, s) -> getUnsafe().putObject(i, addr, s.toString());
    }

    public static BiConsumer<AknObject, CharArray> biConsumerNoWhiteSpace(long addr) {
        return (i, s) -> getUnsafe().putObject(i, addr, new NoWhiteSpace(s.raw()));
    }

    /**
     * This biConsumer will fill after the {@link io.legaldocml.akn.AkomaNtosoContext}}
     *
     * @param name
     * @param addr
     * @return
     */
    public static BiConsumer<AknObject, CharArray> biConsumerNoWhiteSpace(String name, long addr) {
        return new AttributeBiConsumer(name) {
            @Override
            public void accept(AknObject aknObject, CharArray charArray) {
                getUnsafe().putObject(aknObject, addr, new NoWhiteSpace(charArray.raw()));
            }
        };
    }


    public static <T extends Enum<T>> BiConsumer<AknObject, CharArray> biConsumerEnum(long addr, Class<T> enumClass) {
        return (i, s) -> getUnsafe().putObject(i, addr, Enum.valueOf(enumClass, s.toString()));
    }

//    public static BiConsumer<AknObject, CharArray> biConsumerDate(long addr) {
//        return (i, s) -> {
//            UnsafeHelper.getUnsafe().putObject(i, addr, LocalDate.parse(s.toString()));
//        };
//    }

    public static BiConsumer<AknObject, CharArray> biConsumerLocalDateTime(long addr) {
        return (i, s) -> {

            LocalDateTime dateTime;
            try {
                dateTime = LocalDateTime.parse(s.toString(), DateTimeFormatter.ISO_LOCAL_DATE_TIME);
            } catch (DateTimeParseException cause) {
                throw new RuntimeException();
            }
            getUnsafe().putObject(i, addr, dateTime);
        };
    }


    public static BiConsumer<AknObject, CharArray> biConsumerDateTime(long addr) {
        return (i, s) -> {

            String val = s.toString();
            OffsetDateTime dateTime;
            if (val.length() == 10) {
                dateTime = OffsetDateTime.of(LocalDate.parse(s.toString(), DateTimeFormatter.ISO_DATE),
                        DateHelper.TIME_00_00_00, DateHelper.ZONE_OFFSET_0);
            } else {
                try {
                    dateTime = OffsetDateTime.of(LocalDateTime.parse(s.toString(), DateTimeFormatter.ISO_LOCAL_DATE_TIME), ZoneOffset.ofHours(0));
                } catch (DateTimeParseException cause) {
                    dateTime = OffsetDateTime.parse(s.toString(), DateTimeFormatter.ISO_OFFSET_DATE_TIME);
                }
            }
            getUnsafe().putObject(i, addr, dateTime);
        };
    }

    public static BiConsumer<AknObject, CharArray> biConsumerBoolean(long addr) {
        return (i, s) -> getUnsafe().putObject(i, addr, Boolean.valueOf(s.toString()));
    }

    public static BiConsumer<AknObject, CharArray> biConsumerUri(long addr) {
        return (i, s) -> getUnsafe().putObject(i, addr, new Uri(s.raw()));
    }


    public static BiConsumer<AknObject, CharArray> biConsumerManifestationURI(String name, long addr) {
        return new AttributeBiConsumer(name) {
            @Override
            public void accept(AknObject aknObject, CharArray s) {
                getUnsafe().putObject(aknObject, addr, new ManifestationURI(s.raw()));
            }
        };
    }

    public static BiConsumer<AknObject, CharArray> biConsumerReferenceRef(long addr) {
        return (i, s) -> getUnsafe().putObject(i, addr, new ReferenceRef(s.raw()));
    }

    public static BiConsumer<AknObject, CharArray> biConsumerEidRef(long addr) {
        return (i, s) -> getUnsafe().putObject(i, addr, new EidRef(s.raw()));
    }

    public static BiConsumer<AknObject, CharArray> biConsumerWidRef(long addr) {
        return (i, s) -> getUnsafe().putObject(i, addr, new WidRef(s.raw()));
    }

    public static BiConsumer<AknObject, CharArray> biConsumerAgentRef(long addr) {
        return (i, s) -> getUnsafe().putObject(i, addr, AgentRef.raw(s.raw()));
    }

    public static BiConsumer<AknObject, CharArray> biConsumerRoleRef(long addr) {
        return (i, s) -> getUnsafe().putObject(i, addr, RoleRef.raw(s.raw()));
    }

    public static BiConsumer<AknObject, CharArray> biConsumerVoteRef(long addr) {
        return (i, s) -> getUnsafe().putObject(i, addr, new VoteRef(s.raw()));
    }

    public static BiConsumer<AknObject, CharArray> biConsumerConceptRef(long addr) {
        return (i, s) -> getUnsafe().putObject(i, addr, new ConceptRef(s.raw()));
    }


    public static BiConsumer<AknObject, CharArray> biConsumerListReferenceRef(long addr) {
        return (i, s) -> getUnsafe().putObject(i, addr, new ListReferenceRef(s.raw()));
    }

    public static BiConsumer<AknObject, CharArray> biConsumerEventRefRef(long addr) {
        return (i, s) -> getUnsafe().putObject(i, addr, new EventRefRef(s.raw()));
    }

    public static BiConsumer<AknObject, CharArray> biConsumerTemporalGroupRef(long addr) {
        return (i, s) -> getUnsafe().putObject(i, addr, new TemporalGroupRef(s.raw()));
    }


    public static void read4Extension(XmlReader reader, AknObject akoObject) {
        reader.forEach(akoObject, (object, name, value, prefixNS) -> {

            if (prefixNS > 0) {
                if (name.toString().startsWith(reader.getQName().getPrefix())) {
                    BiConsumer<AknObject, CharArray> cons = akoObject.attributes().get(QnameUtil.localName(name));
                    if (cons == null) {
                        throw new RuntimeException("Missing [" + QnameUtil.localName(name) + "] for [" + akoObject.getClass().getSimpleName() + "]");
                    }
                    cons.accept(akoObject, value);
                } else {
                    throw new UnsupportedOperationException();
                }
            } else {
                throw new UnsupportedOperationException();
            }
        });
    }

    public static void read(XmlReader reader, AknObject akoObject) {
        reader.forEach(akoObject, (akn, name, value, prefixNS) -> {
            if (prefixNS > 0) {

                CharArray ns = reader.getNamespaces().get(name.prefix(prefixNS));

                Module module = Modules.get(ns);

                if (!(akn instanceof Core)) {
                    throw new RuntimeException("Should instance of Core");
                }

                Attribute attr;

                if (module == null) {
                    // use external attribures.
                    attr = new ExternalAttribute(name, value);
                } else {
                    attr = module.attributes(name.toString().substring(prefixNS + 1)).get();
                }

                attr.read(reader, value);
                ((Core) akn).add(attr);

                return;


            }
            BiConsumer<AknObject, CharArray> cons = akn.attributes().get(name.toString());
            if (cons == null) {
                throw new RuntimeException("Missing [" + name + "] for [" + akn.getClass().getSimpleName() + "]");
            }

            cons.accept(akn, value);

            if (cons instanceof AttributeBiConsumer) {
                reader.getContext().update(((AttributeBiConsumer) cons).getName(), akoObject);
            }

        });
    }

}
