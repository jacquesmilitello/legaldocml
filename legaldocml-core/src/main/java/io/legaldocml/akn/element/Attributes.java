package io.legaldocml.akn.element;

import io.legaldocml.akn.AknObject;
import io.legaldocml.akn.attribute.Core;
import io.legaldocml.akn.other.ExternalAttribute;
import io.legaldocml.akn.type.AgentRef;
import io.legaldocml.akn.type.ConceptRef;
import io.legaldocml.akn.type.EidRef;
import io.legaldocml.akn.type.EventRefRef;
import io.legaldocml.akn.type.ListReferenceRefs;
import io.legaldocml.akn.type.ManifestationURI;
import io.legaldocml.akn.type.NoWhiteSpace;
import io.legaldocml.akn.type.ReferenceRef;
import io.legaldocml.akn.type.RoleRef;
import io.legaldocml.akn.type.TemporalGroupRef;
import io.legaldocml.akn.type.Uri;
import io.legaldocml.akn.type.VoteRef;
import io.legaldocml.akn.type.WidRef;
import io.legaldocml.io.AttributeConsumer;
import io.legaldocml.io.AttributeGetterSetter;
import io.legaldocml.io.CoreAttribute;
import io.legaldocml.io.XmlReader;
import io.legaldocml.module.Module;
import io.legaldocml.module.Modules;
import io.legaldocml.util.CharArray;
import io.legaldocml.util.Dates;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.OffsetDateTime;
import java.time.ZoneOffset;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.time.temporal.Temporal;
import java.util.function.Function;
import java.util.function.Supplier;

import static io.legaldocml.akn.AknAttributes.ACTOR;
import static io.legaldocml.akn.AknAttributes.ALT;
import static io.legaldocml.akn.AknAttributes.ALTERNATIVE_TO;
import static io.legaldocml.akn.AknAttributes.AS;
import static io.legaldocml.akn.AknAttributes.AUTHORITATIVE;
import static io.legaldocml.akn.AknAttributes.BORDER;
import static io.legaldocml.akn.AknAttributes.BY;
import static io.legaldocml.akn.AknAttributes.CELLPADDING;
import static io.legaldocml.akn.AknAttributes.CELLSPACING;
import static io.legaldocml.akn.AknAttributes.CLASS;
import static io.legaldocml.akn.AknAttributes.COL_SPAN;
import static io.legaldocml.akn.AknAttributes.CONTAINS;
import static io.legaldocml.akn.AknAttributes.DATE;
import static io.legaldocml.akn.AknAttributes.DICTIONARY;
import static io.legaldocml.akn.AknAttributes.DURATION;
import static io.legaldocml.akn.AknAttributes.EID;
import static io.legaldocml.akn.AknAttributes.EMPOWERED_BY;
import static io.legaldocml.akn.AknAttributes.END;
import static io.legaldocml.akn.AknAttributes.END_QUOTE;
import static io.legaldocml.akn.AknAttributes.EVOLVING_ID;
import static io.legaldocml.akn.AknAttributes.EXCLUSION;
import static io.legaldocml.akn.AknAttributes.FOR;
import static io.legaldocml.akn.AknAttributes.FROM;
import static io.legaldocml.akn.AknAttributes.FROM_LANGUAGE;
import static io.legaldocml.akn.AknAttributes.GUID;
import static io.legaldocml.akn.AknAttributes.HEIGHT;
import static io.legaldocml.akn.AknAttributes.HREF;
import static io.legaldocml.akn.AknAttributes.ID;
import static io.legaldocml.akn.AknAttributes.INCLUDED_IN;
import static io.legaldocml.akn.AknAttributes.INCOMPLETE;
import static io.legaldocml.akn.AknAttributes.INLINE_QUOTE;
import static io.legaldocml.akn.AknAttributes.LANGUAGE;
import static io.legaldocml.akn.AknAttributes.LEVEL;
import static io.legaldocml.akn.AknAttributes.MARKER;
import static io.legaldocml.akn.AknAttributes.NAME;
import static io.legaldocml.akn.AknAttributes.NORMALIZED;
import static io.legaldocml.akn.AknAttributes.NUMBER;
import static io.legaldocml.akn.AknAttributes.ORIGINAL_TEXT;
import static io.legaldocml.akn.AknAttributes.ORIGINATING_EXPRESSION;
import static io.legaldocml.akn.AknAttributes.OUTCOME;
import static io.legaldocml.akn.AknAttributes.PERIOD;
import static io.legaldocml.akn.AknAttributes.PIVOT;
import static io.legaldocml.akn.AknAttributes.PLACEMENT;
import static io.legaldocml.akn.AknAttributes.PLACEMENT_BASE;
import static io.legaldocml.akn.AknAttributes.POS;
import static io.legaldocml.akn.AknAttributes.REFERS_TO;
import static io.legaldocml.akn.AknAttributes.ROW_SPAN;
import static io.legaldocml.akn.AknAttributes.SHORT_FORM;
import static io.legaldocml.akn.AknAttributes.SHOW_AS;
import static io.legaldocml.akn.AknAttributes.SOURCE;
import static io.legaldocml.akn.AknAttributes.SRC;
import static io.legaldocml.akn.AknAttributes.START;
import static io.legaldocml.akn.AknAttributes.START_QUOTE;
import static io.legaldocml.akn.AknAttributes.STATUS;
import static io.legaldocml.akn.AknAttributes.STYLE;
import static io.legaldocml.akn.AknAttributes.TARGET;
import static io.legaldocml.akn.AknAttributes.TIME;
import static io.legaldocml.akn.AknAttributes.TITLE;
import static io.legaldocml.akn.AknAttributes.TO;
import static io.legaldocml.akn.AknAttributes.TYPE;
import static io.legaldocml.akn.AknAttributes.UP_TO;
import static io.legaldocml.akn.AknAttributes.VALUE;
import static io.legaldocml.akn.AknAttributes.WID;
import static io.legaldocml.akn.AknAttributes.WIDTH;
import static io.legaldocml.unsafe.UnsafeHelper.getUnsafe;
import static io.legaldocml.util.Buffers.address;
import static io.legaldocml.util.QnameUtil.localName;

/**
 * @author <a href="mailto:jacques.militello@gmail.com">Jacques Militello</a>
 */
@SuppressWarnings("restriction")
public final class Attributes {

    private Attributes() {
    }

    /**
     * CoreAttribute 'breakat'.
     */
    public static final String BREAKAT = "breakat";

    /**
     * Memory address.
     */
    @SuppressWarnings("deprecation")
    public static final long ADDRESS_ID = address(ID);

    public static final long ADDRESS_EID = address(EID);

    public static final long ADDRESS_WID = address(WID);

    public static final long ADDRESS_GUID = address(GUID);

    @SuppressWarnings("deprecation")
    public static final long ADDRESS_EVOLVING_ID = address(EVOLVING_ID);

    public static final long ADDRESS_AS = address(AS);

    public static final long ADDRESS_HREF = address(HREF);

    public static final long ADDRESS_DATE = address(DATE);

    public static final long ADDRESS_NAME = address(NAME);

    public static final long ADDRESS_VALUE = address(VALUE);

    public static final long ADDRESS_FROM_LANGUAGE = address(FROM_LANGUAGE);

    public static final long ADDRESS_AUTHORITATIVE = address(AUTHORITATIVE);

    public static final long ADDRESS_PIVOT = address(PIVOT);

    public static final long ADDRESS_BY = address(BY);

    public static final long ADDRESS_TO = address(TO);

    public static final long ADDRESS_LANGUAGE = address(LANGUAGE);

    public static final long ADDRESS_REFERS = address(REFERS_TO);

    public static final long ADDRESS_OUTCOME = address(OUTCOME);

    public static final long ADDRESS_SOURCE = address(SOURCE);

    public static final long ADDRESS_CONTAINS = address(CONTAINS);

    public static final long ADDRESS_SHOW_AS = address(SHOW_AS);

    public static final long ADDRESS_SHORT_FORM = address(SHORT_FORM);

    public static final long ADDRESS_NUMBER = address(NUMBER);

    public static final long ADDRESS_TYPE = address(TYPE);

    public static final long ADDRESS_ORIGINATING_EXPRESSION = address(ORIGINATING_EXPRESSION);

    public static final long ADDRESS_CLASS = address(CLASS);

    public static final long ADDRESS_STYLE = address(STYLE);

    public static final long ADDRESS_TITLE = address(TITLE);

    public static final long ADDRESS_DICTIONARY = address(DICTIONARY);

    public static final long ADDRESS_LEVEL = address(LEVEL);

    public static final long ADDRESS_TARGET = address(TARGET);

    public static final long ADDRESS_CELLSPACING = address(CELLSPACING);

    public static final long ADDRESS_CELLPADDING = address(CELLPADDING);

    public static final long ADDRESS_BORDER = address(BORDER);

    public static final long ADDRESS_WIDTH = address(WIDTH);

    public static final long ADDRESS_HEIGHT = address(HEIGHT);

    public static final long ADDRESS_STATUS = address(STATUS);

    public static final long ADDRESS_PERIOD = address(PERIOD);

    public static final long ADDRESS_ALTERNATIVE_TO = address(ALTERNATIVE_TO);

    public static final long ADDRESS_MARKER = address(MARKER);

    public static final long ADDRESS_PLACEMENT = address(PLACEMENT);

    public static final long ADDRESS_PLACEMENT_BASE = address(PLACEMENT_BASE);

    public static final long ADDRESS_COL_SPAN = address(COL_SPAN);

    public static final long ADDRESS_ROW_SPAN = address(ROW_SPAN);

    public static final long ADDRESS_SRC = address(SRC);

    public static final long ADDRESS_ALT = address(ALT);

    public static final long ADDRESS_UPTO = address(UP_TO);

    public static final long ADDRESS_START = address(START);

    public static final long ADDRESS_END = address(END);

    public static final long ADDRESS_DURATION = address(DURATION);

    public static final long ADDRESS_ACTOR = address(ACTOR);

    public static final long ADDRESS_ROLE = address(AS);

    public static final long ADDRESS_EXCLUSION = address(EXCLUSION);

    public static final long ADDRESS_INCOMPLETE = address(INCOMPLETE);

    public static final long ADDRESS_FOR = address(FOR);

    public static final long ADDRESS_FROM = address(FROM);

    public static final long ADDRESS_BREAKAT = address(BREAKAT);

    public static final long ADDRESS_TIME = address(TIME);

    public static final long ADDRESS_NORMALIZED = address(NORMALIZED);

    public static final long ADDRESS_INCLUDED_IN = address(INCLUDED_IN);

    public static final long ADDRESS_EMPOWERED_BY = address(EMPOWERED_BY);

    public static final long ADDRESS_STARTQUOTE = address(START_QUOTE);

    public static final long ADDRESS_INLINEQUOTE = address(INLINE_QUOTE);

    public static final long ADDRESS_ENDQUOTE = address(END_QUOTE);

    public static final long ADDRESS_POS = address(POS);
    public static final long ADRESS_ORIGINALTEXT = address(ORIGINAL_TEXT);

    private static final sun.misc.Unsafe UNSAFE = getUnsafe();

    public static <T> AttributeGetterSetter<T> attributeGetterSetter4Integer(String name, long addr) {
        return new DefaultAknAttributeGetterSetter<T>(name, addr) {
            @Override
            public void accept(T object, CharArray charArray) {
                UNSAFE.putObject(object, addr, Integer.valueOf(charArray.toString()));
            }
        };
    }

    public static <T> AttributeGetterSetter<T> attributeRequireGetterSetter4String(String name, long addr) {
        return new MandatoryAknAttributeGetterSetter<T>(name, addr) {
            @Override
            public void accept(T object, CharArray charArray) {
                UNSAFE.putObject(object, addr, charArray.toString());
            }
        };
    }

    public static <T> AttributeGetterSetter<T> attributeGetterSetter4String(String name, long addr) {
        return new DefaultAknAttributeGetterSetter<T>(name, addr) {
            @Override
            public void accept(T object, CharArray charArray) {
                UNSAFE.putObject(object, addr, charArray.toString());
            }
        };
    }

    public static <T> AttributeGetterSetter<T> attributeGetterSetter4NoWhiteSpace(String name, long addr) {
        return new DefaultAknAttributeGetterSetter<T>(name, addr) {
            @Override
            public void accept(T object, CharArray charArray) {
                UNSAFE.putObject(object, addr, NoWhiteSpace.valueOf(charArray.value()));
            }
        };
    }

    public static <T extends Enum<T>, E> AttributeGetterSetter<E> attributeGetterSetter4Enum(String name, long addr, Class<T> enumClass) {
        return new DefaultAknAttributeGetterSetter<E>(name, addr) {
            @Override
            public void accept(E object, CharArray charArray) {
                UNSAFE.putObject(object, addr, Enum.valueOf(enumClass, charArray.toString()));
            }
        };
    }

    public static <T extends Enum<T>, E> AttributeGetterSetter<E> attributeGetterSetter4Enum(String name, long addr, Function<String, T> function) {
        return new DefaultAknAttributeGetterSetter<E>(name, addr) {
            @Override
            public void accept(E object, CharArray charArray) {
                UNSAFE.putObject(object, addr, function.apply(charArray.toString()));
            }
        };
    }

    public static <T, E> AttributeGetterSetter<E> attributeGetterSetter4Object(String name, long addr, Function<String, T> function) {
        return new DefaultAknAttributeGetterSetter<E>(name, addr) {
            @Override
            public void accept(E object, CharArray charArray) {
                UNSAFE.putObject(object, addr, function.apply(charArray.toString()));
            }
        };
    }

    public static <T> AttributeGetterSetter<T> attributeGetterSetter4LocalDateTime(String name, long addr) {
        return new DefaultAknAttributeGetterSetter<T>(name, addr) {
            @Override
            public void accept(T i, CharArray s) {

                LocalDateTime dateTime;
                try {
                    dateTime = LocalDateTime.parse(s.toString(), DateTimeFormatter.ISO_LOCAL_DATE_TIME);
                } catch (DateTimeParseException cause) {
                    throw new RuntimeException();
                }
                getUnsafe().putObject(i, addr, dateTime);
            }
        };
    }

    public static <T> AttributeGetterSetter<T> attributeGetterSetter4DateTime(String name, long addr) {
        return new DefaultAknAttributeGetterSetter<T>(name, addr) {
            @Override
            public void accept(T i, CharArray s) {
                Temporal dateTime;
                if (s.length() == 10) {
                    dateTime = LocalDate.parse(s.toString(), DateTimeFormatter.ISO_DATE);
                } else if (s.length() == 11 && s.charAt(10) == 'Z') {
                    dateTime = OffsetDateTime.of(LocalDate.parse(s.subSequence(0, 10).toString(), DateTimeFormatter.ISO_DATE),
                            Dates.TIME_00_00_00, Dates.ZONE_OFFSET_0);
                } else {
                    try {
                        dateTime = OffsetDateTime.of(LocalDateTime.parse(s.toString(), DateTimeFormatter.ISO_LOCAL_DATE_TIME), ZoneOffset.ofHours(0));
                    } catch (DateTimeParseException cause) {
                        dateTime = OffsetDateTime.parse(s.toString(), DateTimeFormatter.ISO_OFFSET_DATE_TIME);
                    }
                }
                UNSAFE.putObject(i, addr, dateTime);
            }
        };
    }

    public static <T> AttributeGetterSetter<T> attributeGetterSetter4Boolean(String name, long addr) {
        return new DefaultAknAttributeGetterSetter<T>(name, addr) {
            @Override
            public void accept(T object, CharArray charArray) {
                UNSAFE.putObject(object, addr, Boolean.valueOf(charArray.toString()));
            }
        };
    }

    public static <T> AttributeGetterSetter<T> attributeGetterSetter4Uri(String name, long addr) {
        return new DefaultAknAttributeGetterSetter<T>(name, addr) {
            @Override
            public void accept(T object, CharArray charArray) {
                UNSAFE.putObject(object, addr, new Uri(charArray));
            }
        };
    }

    public static <T> AttributeGetterSetter<T> attributeRequireGetterSetter4Uri(String name, long addr) {
        return new DefaultAknAttributeGetterSetter<T>(name, addr) {
            @Override
            public void accept(T object, CharArray charArray) {
                UNSAFE.putObject(object, addr, new Uri(charArray));
            }
        };
    }


    public static <T> AttributeGetterSetter<T> attributeGetterSetter4ManifestationURI(String name, long addr) {
        return new DefaultAknAttributeGetterSetter<T>(name, addr) {
            @Override
            public void accept(T object, CharArray charArray) {
                UNSAFE.putObject(object, addr, new ManifestationURI(charArray.value()));
            }
        };
    }

    public static <T> AttributeGetterSetter<T> attributeGetterSetter4ReferenceRef(String name, long addr) {
        return new DefaultAknAttributeGetterSetter<T>(name, addr) {
            @Override
            public void accept(T object, CharArray charArray) {
                UNSAFE.putObject(object, addr, new ReferenceRef(charArray));
            }
        };
    }

    public static <T> AttributeGetterSetter<T> attributeGetterSetter4EidRef(String name, long addr) {
        return new DefaultAknAttributeGetterSetter<T>(name, addr) {
            @Override
            public void accept(T object, CharArray charArray) {
                UNSAFE.putObject(object, addr, new EidRef(charArray));
            }
        };
    }

    public static <T> AttributeGetterSetter<T> attributeGetterSetter4WidRef(String name, long addr) {
        return new DefaultAknAttributeGetterSetter<T>(name, addr) {
            @Override
            public void accept(T object, CharArray charArray) {
                UNSAFE.putObject(object, addr, new WidRef(charArray.value()));
            }
        };
    }

    public static <T> AttributeGetterSetter<T> attributeGetterSetter4AgentRef(String name, long addr) {
        return new DefaultAknAttributeGetterSetter<T>(name, addr) {
            @Override
            public void accept(T object, CharArray charArray) {
                UNSAFE.putObject(object, addr, new AgentRef(charArray));
            }
        };
    }

    public static <T> AttributeGetterSetter<T> attributeGetterSetter4RoleRef(String name, long addr) {
        return new DefaultAknAttributeGetterSetter<T>(name, addr) {
            @Override
            public void accept(T object, CharArray charArray) {
                UNSAFE.putObject(object, addr, new RoleRef(charArray));
            }
        };
    }

    public static <T> AttributeGetterSetter<T> attributeGetterSetter4VoteRef(String name, long addr) {
        return new DefaultAknAttributeGetterSetter<T>(name, addr) {
            @Override
            public void accept(T object, CharArray charArray) {
                UNSAFE.putObject(object, addr, new VoteRef(charArray.value()));
            }
        };
    }

    public static <T> AttributeGetterSetter<T> attributeGetterSetter4ConceptRef(String name, long addr) {
        return new DefaultAknAttributeGetterSetter<T>(name, addr) {
            @Override
            public void accept(T object, CharArray charArray) {
                UNSAFE.putObject(object, addr, new ConceptRef(charArray.value()));
            }
        };
    }


    public static <T> AttributeGetterSetter<T> attributeGetterSetter4ListReferenceRef(String name, long addr) {
        return new DefaultAknAttributeGetterSetter<T>(name, addr) {
            @Override
            public void accept(T object, CharArray charArray) {
                UNSAFE.putObject(object, addr, ListReferenceRefs.parse(charArray.value()));
            }
        };
    }

    public static <T> AttributeGetterSetter<T> attributeRequireGetterSetter4ListReferenceRef(String name, long addr) {
        return new DefaultAknAttributeGetterSetter<T>(name, addr) {
            @Override
            public void accept(T object, CharArray charArray) {
                UNSAFE.putObject(object, addr, ListReferenceRefs.parse(charArray.value()));
            }
        };
    }

    public static <T> AttributeGetterSetter<T> attributeGetterSetter4EventRefRef(String name, long addr) {
        return new DefaultAknAttributeGetterSetter<T>(name, addr) {
            @Override
            public void accept(T object, CharArray charArray) {
                UNSAFE.putObject(object, addr, new EventRefRef(charArray.value()));
            }
        };
    }

    public static <T> AttributeGetterSetter<T> attributeGetterSetter4TemporalGroupRef(String name, long addr) {
        return new DefaultAknAttributeGetterSetter<T>(name, addr) {
            @Override
            public void accept(T object, CharArray charArray) {
                UNSAFE.putObject(object, addr, new TemporalGroupRef(charArray.value()));
            }
        };
    }


    public static void read4Extension(XmlReader reader, Object akoObject) {
        reader.forEach(akoObject, (channelReader, object, name, value, prefixNS) -> {

            if (prefixNS > 0) {
                if (name.toString().startsWith(reader.getQName().getPrefix())) {
                    AttributeGetterSetter<AknObject> cons = akoObject.attributes().get(localName(name));
                    if (cons == null) {
                        throw new RuntimeException("Missing [" + localName(name) + "] for [" + akoObject.getClass().getSimpleName() + "]");
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

    public static final AttributeConsumer<AknObject> ATTRIBUTE_CONSUMER = (channelReader, akn, name, value, prefixNS) -> {

        String localName;

        if (prefixNS > 0) {
            CharArray prefix = name.subSequence(0, prefixNS);

            // same prefix from the element prefix and attribute prefix
            if (!prefix.toString().equals(channelReader.getQName().getPrefix())) {

                CharArray ns = channelReader.getNamespaces().get(prefix);
                Module module = Modules.get(ns);

                if (!(akn instanceof Core)) {
                    throw new RuntimeException("Should instance of Core");
                }

                CoreAttribute attr;

                if (module == null) {
                    // use external attributes.
                    attr = new ExternalAttribute(name, value);
                } else {
                    Supplier<CoreAttribute> supplier = module.attribute(name.toString().substring(prefixNS + 1));
                    if (supplier == null) {
                        throw new UnsupportedAttributeException(module, name);
                    }
                    attr = supplier.get();
                }

                attr.read(channelReader, value);
                ((Core) akn).add(attr);
                channelReader.getContext().update(attr.name(), akn);
                return;
            } else {
                localName = name.subSequence(prefixNS + 1, name.length()).toString();
            }
        } else {
            localName = name.toString();
        }
        AttributeGetterSetter<AknObject> cons = akn.attributes().get(localName);
        if (cons == null) {
            throw new InvalidAttributeException(name, value, akn);
        }

        cons.accept(akn, value);

        channelReader.getContext().update(cons.name(), akn);
    };

    private static abstract class DefaultAknAttributeGetterSetter<T> implements AttributeGetterSetter<T> {
        final long addr;
        private final String name;

        protected DefaultAknAttributeGetterSetter(String name, long addr) {
            this.addr = addr;
            this.name = name;
        }

        @Override
        public java.lang.Object apply(T object) {
            return UNSAFE.getObject(object, addr);
        }

        @Override
        public String name() {
            return this.name;
        }

        @Override
        public boolean isRequired() {
            return false;
        }
    }

    private static abstract class MandatoryAknAttributeGetterSetter<T> extends DefaultAknAttributeGetterSetter<T> {

        protected MandatoryAknAttributeGetterSetter(String name, long addr) {
            super(name, addr);
        }

        @Override
        public boolean isRequired() {
            return true;
        }
    }


    public static class InvalidAttributeException extends RuntimeException {

        InvalidAttributeException(CharArray name, CharArray value,  AknObject akn) {
            super("Invalid attribute [" + name + "]-["+ value + "] for [" + akn.name() + "] : attributes allowed " + akn.attributes().keySet().asList());
        }
    }
    public static class UnsupportedAttributeException extends RuntimeException {

        UnsupportedAttributeException(Module module, CharArray name) {
            super("Unsupported attribute [" + name + "] in module [" + module.namespace() + "]");
        }
    }


}
