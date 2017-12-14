package io.legaldocml.akn.element;

import io.legaldocml.akn.AknAttributes;
import io.legaldocml.akn.AknObject;
import io.legaldocml.akn.AkomaNtosoContext;
import io.legaldocml.akn.attribute.Core;
import io.legaldocml.akn.other.ExternalAttribute;
import io.legaldocml.akn.type.AgentRef;
import io.legaldocml.akn.type.ConceptRef;
import io.legaldocml.akn.type.EidRef;
import io.legaldocml.akn.type.EventRefRef;
import io.legaldocml.akn.type.ListReferenceRef;
import io.legaldocml.akn.type.ManifestationURI;
import io.legaldocml.akn.type.NoWhiteSpace;
import io.legaldocml.akn.type.ReferenceRef;
import io.legaldocml.akn.type.RoleRef;
import io.legaldocml.akn.type.TemporalGroupRef;
import io.legaldocml.akn.type.Uri;
import io.legaldocml.akn.type.VoteRef;
import io.legaldocml.akn.type.WidRef;
import io.legaldocml.io.Attribute;
import io.legaldocml.io.AttributeGetterSetter;
import io.legaldocml.io.XmlReader;
import io.legaldocml.io.impl.Buffers;
import io.legaldocml.module.Module;
import io.legaldocml.module.Modules;
import io.legaldocml.util.CharArray;
import io.legaldocml.util.DateHelper;
import io.legaldocml.util.QnameUtil;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.OffsetDateTime;
import java.time.ZoneOffset;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

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

    private static final sun.misc.Unsafe UNSAFE = getUnsafe();

    public static <T> AttributeGetterSetter<T> biConsumerInteger(String name, long addr) {
        return new DefaultAknAttributeGetterSetter<T>(name, addr) {
            @Override
            public void accept(T object, CharArray charArray) {
                UNSAFE.putObject(object, addr, Integer.valueOf(charArray.toString()));
            }
        };
    }

    public static <T> AttributeGetterSetter<T> biConsumerString(String name, long addr) {
        return new DefaultAknAttributeGetterSetter<T>(name, addr) {
            @Override
            public void accept(T object, CharArray charArray) {
                UNSAFE.putObject(object, addr, charArray.toString());
            }
        };
    }

    public static <T> AttributeGetterSetter<T> biConsumerNoWhiteSpace(String name, long addr) {
        return new DefaultAknAttributeGetterSetter<T>(name, addr) {
            @Override
            public void accept(T object, CharArray charArray) {
                UNSAFE.putObject(object, addr, NoWhiteSpace.valueOf(charArray.value()));
            }
        };
    }

    /**
     * This biConsumer will fill after the {@link io.legaldocml.akn.AkomaNtosoContext}}
     * <p>
     * public static AknAttributeGetterSetter biConsumerNoWhiteSpace(String name, long addr) {
     * return new AttributeBiConsumer(name) {
     *
     * @Override public void accept(Externalizable object, CharArray charArray) {
     * getUnsafe().putObject(object, addr, NoWhiteSpace.valueOf(charArray.value()));
     * }
     * };
     * }
     */


    public static <T extends Enum<T>, E> AttributeGetterSetter<E> biConsumerEnum(String name, long addr, Class<T> enumClass) {
        return new DefaultAknAttributeGetterSetter<E>(name, addr) {
            @Override
            public void accept(E object, CharArray charArray) {
                UNSAFE.putObject(object, addr, Enum.valueOf(enumClass, charArray.toString()));
            }
        };
    }

    public static <T> AttributeGetterSetter<T> biConsumerLocalDateTime(String name, long addr) {
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


    public static <T> AttributeGetterSetter<T> biConsumerDateTime(String name, long addr) {
        return new DefaultAknAttributeGetterSetter<T>(name, addr) {
            @Override
            public void accept(T i, CharArray s) {
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
                UNSAFE.putObject(i, addr, dateTime);
            }
        };
    }

    public static <T> AttributeGetterSetter<T> biConsumerBoolean(String name, long addr) {
        return new DefaultAknAttributeGetterSetter<T>(name, addr) {
            @Override
            public void accept(T object, CharArray charArray) {
                UNSAFE.putObject(object, addr, Boolean.valueOf(charArray.toString()));
            }
        };
    }

    public static <T> AttributeGetterSetter<T> biConsumerUri(String name, long addr) {
        return new DefaultAknAttributeGetterSetter<T>(name, addr) {
            @Override
            public void accept(T object, CharArray charArray) {
                UNSAFE.putObject(object, addr, Uri.raw(charArray.value()));
            }
        };
    }


    public static <T> AttributeGetterSetter<T> biConsumerManifestationURI(String name, long addr) {
        return new DefaultAknAttributeGetterSetter<T>(name, addr) {
            @Override
            public void accept(T object, CharArray charArray) {
                UNSAFE.putObject(object, addr, new ManifestationURI(charArray.value()));
            }
        };
    }

    public static <T> AttributeGetterSetter<T> biConsumerReferenceRef(String name, long addr) {
        return new DefaultAknAttributeGetterSetter<T>(name, addr) {
            @Override
            public void accept(T object, CharArray charArray) {
                UNSAFE.putObject(object, addr, ReferenceRef.raw(charArray.value()));
            }
        };
    }

    public static <T> AttributeGetterSetter<T> biConsumerEidRef(String name, long addr) {
        return new DefaultAknAttributeGetterSetter<T>(name, addr) {
            @Override
            public void accept(T object, CharArray charArray) {
                UNSAFE.putObject(object, addr, new EidRef(charArray.value()));
            }
        };
    }

    public static <T> AttributeGetterSetter<T> biConsumerWidRef(String name, long addr) {
        return new DefaultAknAttributeGetterSetter<T>(name, addr) {
            @Override
            public void accept(T object, CharArray charArray) {
                UNSAFE.putObject(object, addr, new WidRef(charArray.value()));
            }
        };
    }

    public static <T> AttributeGetterSetter<T> biConsumerAgentRef(String name, long addr) {
        return new DefaultAknAttributeGetterSetter<T>(name, addr) {
            @Override
            public void accept(T object, CharArray charArray) {
                UNSAFE.putObject(object, addr, AgentRef.raw(charArray.value()));
            }
        };
    }

    public static <T> AttributeGetterSetter<T> biConsumerRoleRef(String name, long addr) {
        return new DefaultAknAttributeGetterSetter<T>(name, addr) {
            @Override
            public void accept(T object, CharArray charArray) {
                UNSAFE.putObject(object, addr, RoleRef.raw(charArray.value()));
            }
        };
    }

    public static <T> AttributeGetterSetter<T> biConsumerVoteRef(String name, long addr) {
        return new DefaultAknAttributeGetterSetter<T>(name, addr) {
            @Override
            public void accept(T object, CharArray charArray) {
                UNSAFE.putObject(object, addr, new VoteRef(charArray.value()));
            }
        };
    }

    public static <T> AttributeGetterSetter<T> biConsumerConceptRef(String name, long addr) {
        return new DefaultAknAttributeGetterSetter<T>(name, addr) {
            @Override
            public void accept(T object, CharArray charArray) {
                UNSAFE.putObject(object, addr, new ConceptRef(charArray.value()));
            }
        };
    }


    public static <T> AttributeGetterSetter<T> biConsumerListReferenceRef(String name, long addr) {
        return new DefaultAknAttributeGetterSetter<T>(name, addr) {
            @Override
            public void accept(T object, CharArray charArray) {
                UNSAFE.putObject(object, addr, new ListReferenceRef(charArray.value()));
            }
        };
    }

    public static <T> AttributeGetterSetter<T> biConsumerEventRefRef(String name, long addr) {
        return new DefaultAknAttributeGetterSetter<T>(name, addr) {
            @Override
            public void accept(T object, CharArray charArray) {
                UNSAFE.putObject(object, addr, new EventRefRef(charArray.value()));
            }
        };
    }

    public static <T> AttributeGetterSetter<T> biConsumerTemporalGroupRef(String name, long addr) {
        return new DefaultAknAttributeGetterSetter<T>(name, addr) {
            @Override
            public void accept(T object, CharArray charArray) {
                UNSAFE.putObject(object, addr, new TemporalGroupRef(charArray.value()));
            }
        };
    }


    public static void read4Extension(XmlReader reader, Object akoObject) {
        reader.forEach(akoObject, (object, name, value, prefixNS) -> {

            if (prefixNS > 0) {
                if (name.toString().startsWith(reader.getQName().getPrefix())) {
                    AttributeGetterSetter<AknObject> cons = akoObject.attributes().get(QnameUtil.localName(name));
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

                CharArray ns = reader.getNamespaces().get(name.subSequence(0, prefixNS));

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
            AttributeGetterSetter<AknObject> cons = akn.attributes().get(name.toString());
            if (cons == null) {
                throw new RuntimeException("Missing [" + name + "] for [" + akn.getClass().getSimpleName() + "]");
            }

            cons.accept(akn, value);

            reader.<AkomaNtosoContext>getContext().update(cons.name(), akn);

        });
    }


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
    }
}
