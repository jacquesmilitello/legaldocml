package io.legaldocml.io.impl;

import io.legaldocml.util.ToStringBuilder;

/**
 * @author <a href="mailto:jacques.militello@gmail.com">Jacques Militello</a>
 */
public final class XmlChannelReaderException extends RuntimeException {

    public enum Type {
        REPLACE_ENTITY, PREMATURE_END_OF_FILE, ILLEGAL_CHAR_0000, EXPECTED_QUOTES, EXPECTED_EQUALS, EXPECTED_GREATER, UNEXPECTED_TAG, STATE_UNKOWN,

        INVALID_EVENT_TYPE_TEXT, INVALID_EVENT_TYPE_PI, INVALID_EVENT_TYPE_QNAME
    }

    /**
     * Holds the textual representation for events.
     */
    private static final String[] NAMES_OF_EVENTS = new String[]{
            // event-type = 0
            "UNDEFINED",
            // event-type = 1
            "START_ELEMENT",
            // event-type = 2
            "END_ELEMENT",
            // event-type = 3
            "PROCESSING_INSTRUCTIONS",
            // event-type = 4
            "CHARACTERS",
            // event-type = 5
            "COMMENT",
            // event-type = 6
            "SPACE",
            // event-type = 7
            "START_DOCUMENT",
            // event-type = 8
            "END_DOCUMENT",
            // event-type = 9
            "ENTITY_REFERENCE",
            // event-type = 10
            "ATTRIBUTE",
            // event-type = 11
            "DTD",
            // event-type = 12
            "CDATA",
            // event-type = 13
            "NAMESPACE",
            // event-type = 14
            "NOTATION_DECLARATION",
            // event-type = 15
            "ENTITY_DECLARATION"
    };

    /**
     * Holds the textual representation for states.
     */
    private final static String[] NAMES_OF_STATES = new String[]{
            // state = 0
            "INVALID",
            // state = 1
            "STATE_CHARACTERS",
            // state = 2
            "STATE_MARKUP",
            // state = 3
            "STATE_COMMENT",
            // state = 4
            "STATE_PI",
            // state = 5
            "STATE_CDATA",
            // state = 6
            "STATE_OPEN_TAG_READ_ELEM_NAME",
            // state = 7
            "STATE_OPEN_TAG_ELEM_NAME_READ",
            // state = 8
            "STATE_OPEN_TAG_READ_ATTR_NAME",
            // state = 9
            "STATE_OPEN_TAG_ATTR_NAME_READ",
            // state = 10
            "STATE_OPEN_TAG_EQUAL_READ",
            // state = 11
            "STATE_OPEN_TAG_READ_ATTR_VALUE_SIMPLE_QUOTE",
            // state = 12
            "STATE_OPEN_TAG_READ_ATTR_VALUE_DOUBLE_QUOTE",
            // state = 13
            "STATE_OPEN_TAG_EMPTY_TAG",
            // state = 14
            "STATE_CLOSE_TAG_READ_ELEM_NAME",
            // state = 15
            "STATE_CLOSE_TAG_ELEM_NAME_READ",
            // state = 16
            "STATE_DTD",
            // state = 17
            "STATE_DTD_INTERNAL"
    };

    /**
     * Holds the textual representation for states.
     */
    private static final String[] NAMES_OF_EVENTS2 = new String[]{
            // event-type = 0
            "UNDEFINED",
            // event-type = 1
            "START_ELEMENT",
            // event-type = 2
            "END_ELEMENT",
            // event-type = 3
            "PROCESSING_INSTRUCTIONS",
            // event-type = 4
            "CHARACTERS",
            // event-type = 5
            "COMMENT",
            // event-type = 6
            "SPACE",
            // event-type = 7
            "START_DOCUMENT",
            // event-type = 8
            "END_DOCUMENT",
            // event-type = 9
            "ENTITY_REFERENCE",
            // event-type = 10
            "ATTRIBUTE",
            // event-type = 11
            "DTD",
            // event-type = 12
            "CDATA",
            // event-type = 13
            "NAMESPACE",
            // event-type = 14
            "NOTATION_DECLARATION",
            // event-type = 15
            "ENTITY_DECLARATION"
    };

    private final Type type;
//    private final int eventType;
//    private final int state;
//    private final int seqsIdx;
//    private final CharBuffer cb;
//    private final CharArray text;

    XmlChannelReaderException(Type type, XmlChannelReader reader) {
        super(buildMsg(type,reader));
        this.type = type;
//        this.eventType = eventType;
//        this.state = state;
//        this.seqsIdx = seqsIdx;
//        this.cb = cb;
//        this.text = text;
    }

    public Type getType() {
        return type;
    }


    private static String buildMsg(Type type, XmlChannelReader reader) {
        ToStringBuilder builder = new ToStringBuilder(true);
        builder.append("type",type);
        builder.append("eventType", reader.getEventType());
        builder.append("eventTypeName", NAMES_OF_EVENTS[reader.getEventType()]);
        builder.append("state", reader.getState());
        builder.append("stateName", NAMES_OF_STATES[reader.getState()]);
        builder.append("depth", reader.getDepth());
        builder.append("seqsIdx",reader.getSeqsIdx());
        builder.append("buffer",reader.getCb());
        builder.append("position",reader.getPosition());
        return builder.toString();
    }

    //	/**
//	 * Exception when we failed to read one byte or more in the XML.
//	 *
//	 * @param cause the IO Exception that produce.
//	 */
//	XmlChannelReaderException(IOException cause) {
//		super(cause);
//	}
//
//	/**
//	 * Invalid state in the XML.
//	 *
//	 * @param message the detail message. The detail message is saved for later retrieval by the {@link #getMessage()} method.
//	 * @param location the location of the source xml that produce this exception.
//	 */
//	XmlChannelReaderException(String message, Location location) {
//		super(new StringBuilder().append(message).append(", [").append(location).append("]").toString());
//	}

//
//    StringBuilder builder = new StringBuilder(256);
//            builder.append("Premature end of file -");
//            builder.append(" eventType=[").append(NAMES_OF_EVENTS[eventType]).append(']');
//            builder.append(" state=[").append(this.state).append(']');
//            builder.append(" depth=[").append(this.depth).append(']');
//            builder.append(" seqsIdx=[").append(this.seqsIdx).append(']');
//            builder.append(" buffer=[").append(this.cb).append(']');


}