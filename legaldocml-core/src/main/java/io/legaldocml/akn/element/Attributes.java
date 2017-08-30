package io.legaldocml.akn.element;

import io.legaldocml.akn.AknAttributes;
import io.legaldocml.akn.AknObject;
import io.legaldocml.akn.AkomaNtosoContext;
import io.legaldocml.akn.AttributeBiConsumer;
import io.legaldocml.akn.attribute.Core;
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
import io.legaldocml.io.Externalizable;
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
    public static final long ADDRESS_ID = Buffers.address(AknAttributes.ID);

    public static final long ADDRESS_EID = Buffers.address(AknAttributes.EID);

    public static final long ADDRESS_WID = Buffers.address(AknAttributes.WID);

    public static final long ADDRESS_GUID = Buffers.address(AknAttributes.GUID);

    public static final long ADDRESS_EVOLVING_ID = Buffers.address(AknAttributes.EVOLVING_ID);

    public static final long ADDRESS_AS = Buffers.address(AknAttributes.AS);

    public static final long ADDRESS_HREF = Buffers.address(AknAttributes.HREF);

    public static final long ADDRESS_DATE = Buffers.address(AknAttributes.DATE);

    public static final long ADDRESS_NAME = Buffers.address(AknAttributes.NAME);

    public static final long ADDRESS_VALUE = Buffers.address(AknAttributes.VALUE);

    public static final long ADDRESS_FROM_LANGUAGE = Buffers.address(AknAttributes.FROM_LANGUAGE);

    public static final long ADDRESS_AUTHORITATIVE = Buffers.address(AknAttributes.AUTHORITATIVE);

    public static final long ADDRESS_PIVOT = Buffers.address(AknAttributes.PIVOT);

    public static final long ADDRESS_BY = Buffers.address(AknAttributes.BY);

    public static final long ADDRESS_TO = Buffers.address(AknAttributes.TO);

    public static final long ADDRESS_LANGUAGE = Buffers.address(AknAttributes.LANGUAGE);

    public static final long ADDRESS_REFERS = Buffers.address(AknAttributes.REFERS_TO);

    public static final long ADDRESS_OUTCOME = Buffers.address(AknAttributes.OUTCOME);

    public static final long ADDRESS_SOURCE = Buffers.address(AknAttributes.SOURCE);

    public static final long ADDRESS_CONTAINS = Buffers.address(AknAttributes.CONTAINS);

    public static final long ADDRESS_SHOW_AS = Buffers.address(AknAttributes.SHOW_AS);

    public static final long ADDRESS_SHORT_FORM = Buffers.address(AknAttributes.SHORT_FORM);

    public static final long ADDRESS_NUMBER = Buffers.address(AknAttributes.NUMBER);

    public static final long ADDRESS_TYPE = Buffers.address(AknAttributes.TYPE);

    public static final long ADDRESS_ORIGINATING_EXPRESSION = Buffers.address(AknAttributes.ORIGINATING_EXPRESSION);

    public static final long ADDRESS_CLASS = Buffers.address(AknAttributes.CLASS);

    public static final long ADDRESS_STYLE = Buffers.address(AknAttributes.STYLE);

    public static final long ADDRESS_TITLE = Buffers.address(AknAttributes.TITLE);

    public static final long ADDRESS_DICTIONARY = Buffers.address(AknAttributes.DICTIONARY);

    public static final long ADDRESS_LEVEL = Buffers.address(AknAttributes.LEVEL);

    public static final long ADDRESS_TARGET = Buffers.address(AknAttributes.TARGET);

    public static final long ADDRESS_CELLSPACING = Buffers.address(AknAttributes.CELLSPACING);

    public static final long ADDRESS_CELLPADDING = Buffers.address(AknAttributes.CELLPADDING);

    public static final long ADDRESS_BORDER = Buffers.address(AknAttributes.BORDER);

    public static final long ADDRESS_WIDTH = Buffers.address(AknAttributes.WIDTH);

    public static final long ADDRESS_HEIGHT = Buffers.address(AknAttributes.HEIGHT);

    public static final long ADDRESS_STATUS = Buffers.address(AknAttributes.STATUS);

    public static final long ADDRESS_PERIOD = Buffers.address(AknAttributes.PERIOD);

    public static final long ADDRESS_ALTERNATIVE_TO = Buffers.address(AknAttributes.ALTERNATIVE_TO);

    public static final long ADDRESS_MARKER = Buffers.address(AknAttributes.MARKER);

    public static final long ADDRESS_PLACEMENT = Buffers.address(AknAttributes.PLACEMENT);

    public static final long ADDRESS_PLACEMENT_BASE = Buffers.address(AknAttributes.PLACEMENT_BASE);

    public static final long ADDRESS_COL_SPAN = Buffers.address(AknAttributes.COL_SPAN);

    public static final long ADDRESS_ROW_SPAN = Buffers.address(AknAttributes.ROW_SPAN);

    public static final long ADDRESS_SRC = Buffers.address(AknAttributes.SRC);

    public static final long ADDRESS_ALT = Buffers.address(AknAttributes.ALT);

    public static final long ADDRESS_UPTO = Buffers.address(AknAttributes.UP_TO);

    public static final long ADDRESS_START = Buffers.address(AknAttributes.START);

    public static final long ADDRESS_END = Buffers.address(AknAttributes.END);

    public static final long ADDRESS_DURATION = Buffers.address(AknAttributes.DURATION);

    public static final long ADDRESS_ACTOR = Buffers.address(AknAttributes.ACTOR);

    public static final long ADDRESS_ROLE = Buffers.address(AknAttributes.AS);

    public static final long ADDRESS_EXCLUSION = Buffers.address(AknAttributes.EXCLUSION);

    public static final long ADDRESS_INCOMPLETE = Buffers.address(AknAttributes.INCOMPLETE);

    public static final long ADDRESS_FOR = Buffers.address(AknAttributes.FOR);

    public static final long ADDRESS_FROM = Buffers.address(AknAttributes.FROM);

    public static final long ADDRESS_BREAKAT = Buffers.address(BREAKAT);

    public static final long ADDRESS_TIME = Buffers.address(AknAttributes.TIME);

    public static final long ADDRESS_NORMALIZED = Buffers.address(AknAttributes.NORMALIZED);

    public static final long ADDRESS_INCLUDED_IN = Buffers.address(AknAttributes.INCLUDED_IN);

    public static final long ADDRESS_EMPOWERED_BY = Buffers.address(AknAttributes.EMPOWERED_BY);

    public static final long ADDRESS_STARTQUOTE = Buffers.address(AknAttributes.START_QUOTE);

    public static final long ADDRESS_INLINEQUOTE = Buffers.address(AknAttributes.INLINE_QUOTE);

    public static final long ADDRESS_ENDQUOTE = Buffers.address(AknAttributes.END_QUOTE);

    public static final long ADDRESS_POS = Buffers.address(AknAttributes.POS);

    public static BiConsumer<Externalizable, CharArray> biConsumerInteger(long addr) {
        return (i, s) -> getUnsafe().putObject(i, addr, Integer.valueOf(s.toString()));
    }

    public static BiConsumer<Externalizable, CharArray> biConsumerString(long addr) {
        return (i, s) -> getUnsafe().putObject(i, addr, s.toString());
    }

    public static BiConsumer<Externalizable, CharArray> biConsumerNoWhiteSpace(long addr) {
        return (i, s) -> getUnsafe().putObject(i, addr, new NoWhiteSpace(s.raw()));
    }

    /**
     * This biConsumer will fill after the {@link io.legaldocml.akn.AkomaNtosoContext}}
     */
    public static BiConsumer<Externalizable, CharArray> biConsumerNoWhiteSpace(String name, long addr) {
        return new AttributeBiConsumer(name) {
            @Override
            public void accept(Externalizable object, CharArray charArray) {
                getUnsafe().putObject(object, addr, new NoWhiteSpace(charArray.raw()));
            }
        };
    }


    public static <T extends Enum<T>> BiConsumer<Externalizable, CharArray> biConsumerEnum(long addr, Class<T> enumClass) {
        return (i, s) -> getUnsafe().putObject(i, addr, Enum.valueOf(enumClass, s.toString()));
    }

//    public static BiConsumer<AknObject, CharArray> biConsumerDate(long addr) {
//        return (i, s) -> {
//            UnsafeHelper.getUnsafe().putObject(i, addr, LocalDate.parse(s.toString()));
//        };
//    }

    public static BiConsumer<Externalizable, CharArray> biConsumerLocalDateTime(long addr) {
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


    public static BiConsumer<Externalizable, CharArray> biConsumerDateTime(long addr) {
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

    public static BiConsumer<Externalizable, CharArray> biConsumerBoolean(long addr) {
        return (i, s) -> getUnsafe().putObject(i, addr, Boolean.valueOf(s.toString()));
    }

    public static BiConsumer<Externalizable, CharArray> biConsumerUri(long addr) {
        return (i, s) -> getUnsafe().putObject(i, addr, new Uri(s.raw()));
    }


    public static BiConsumer<Externalizable, CharArray> biConsumerManifestationURI(String name, long addr) {
        return new AttributeBiConsumer(name) {
            @Override
            public void accept(Externalizable object, CharArray s) {
                getUnsafe().putObject(object, addr, new ManifestationURI(s.raw()));
            }
        };
    }

    public static BiConsumer<Externalizable, CharArray> biConsumerReferenceRef(long addr) {
        return (i, s) -> getUnsafe().putObject(i, addr, new ReferenceRef(s.raw()));
    }

    public static BiConsumer<Externalizable, CharArray> biConsumerEidRef(long addr) {
        return (i, s) -> getUnsafe().putObject(i, addr, new EidRef(s.raw()));
    }

    public static BiConsumer<Externalizable, CharArray> biConsumerWidRef(long addr) {
        return (i, s) -> getUnsafe().putObject(i, addr, new WidRef(s.raw()));
    }

    public static BiConsumer<Externalizable, CharArray> biConsumerAgentRef(long addr) {
        return (i, s) -> getUnsafe().putObject(i, addr, AgentRef.raw(s.raw()));
    }

    public static BiConsumer<Externalizable, CharArray> biConsumerRoleRef(long addr) {
        return (i, s) -> getUnsafe().putObject(i, addr, RoleRef.raw(s.raw()));
    }

    public static BiConsumer<Externalizable, CharArray> biConsumerVoteRef(long addr) {
        return (i, s) -> getUnsafe().putObject(i, addr, new VoteRef(s.raw()));
    }

    public static BiConsumer<Externalizable, CharArray> biConsumerConceptRef(long addr) {
        return (i, s) -> getUnsafe().putObject(i, addr, new ConceptRef(s.raw()));
    }


    public static BiConsumer<Externalizable, CharArray> biConsumerListReferenceRef(long addr) {
        return (i, s) -> getUnsafe().putObject(i, addr, new ListReferenceRef(s.raw()));
    }

    public static BiConsumer<Externalizable, CharArray> biConsumerEventRefRef(long addr) {
        return (i, s) -> getUnsafe().putObject(i, addr, new EventRefRef(s.raw()));
    }

    public static BiConsumer<Externalizable, CharArray> biConsumerTemporalGroupRef(long addr) {
        return (i, s) -> getUnsafe().putObject(i, addr, new TemporalGroupRef(s.raw()));
    }


    public static void read4Extension(XmlReader reader, Object akoObject) {
        reader.forEach(akoObject, (object, name, value, prefixNS) -> {

            if (prefixNS > 0) {
                if (name.toString().startsWith(reader.getQName().getPrefix())) {
                    BiConsumer<Externalizable, CharArray> cons = akoObject.attributes().get(QnameUtil.localName(name));
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

    public static void read(XmlReader reader, AknObject object) {
        reader.forEach(object, (akn, name, value, prefixNS) -> {
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
            BiConsumer<Externalizable, CharArray> cons = akn.attributes().get(name.toString());
            if (cons == null) {
                throw new RuntimeException("Missing [" + name + "] for [" + akn.getClass().getSimpleName() + "]");
            }

            cons.accept(akn, value);

            if (cons instanceof AttributeBiConsumer) {
                reader.<AkomaNtosoContext>getContext().update(((AttributeBiConsumer) cons).getName(), akn);
            }

        });
    }

}
