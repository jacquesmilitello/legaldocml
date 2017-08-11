package io.legaldocml.io.impl;

/**
 * @author <a href="mailto:jacques.militello@gmail.com">Jacques Militello</a>
 */
interface XmlChannelReaderStates {

    /**
     * Internal State : Characters.
     */
    int STATE_CHARACTERS = 1;

    /**
     * Internal State : Markup.
     */
    int STATE_MARKUP = 2;

    /**
     * Internal State : comment.
     */
    int STATE_COMMENT = 3;

    /**
     * Internal State : Processing instruction.
     */
    int STATE_PI = 4;

    /**
     * Internal State : Characters.
     */
    int STATE_CDATA = 5;

    /**
     * Internal State : Open Tag -> read the element name.
     */
    int STATE_OPEN_TAG_READ_ELEM_NAME = 6;

    /**
     * Internal State : Open Tag -> the name is readed.
     */
    int STATE_OPEN_TAG_ELEM_NAME_READ = 7;

    /**
     * Internal State : Open Tag -> read the attribute name.
     */
    int STATE_OPEN_TAG_READ_ATTR_NAME = 8;

    /**
     * Internal State : Open Tag -> the attribute name is reader.
     */
    int STATE_OPEN_TAG_ATTR_NAME_READ = 9;

    /**
     * Internal State : Open Tag -> read.
     */
    int STATE_OPEN_TAG_EQUAL_READ = 10;

    /**
     * Internal State : Open Tag -> Read attribute value with simple quote.
     */
    int STATE_OPEN_TAG_READ_ATTR_VALUE_SIMPLE_QUOTE = 11;

    /**
     * Internal State : Open Tag -> Read attribute value with double quote.
     */
    int STATE_OPEN_TAG_READ_ATTR_VALUE_DOUBLE_QUOTE = 12;

    /**
     * Internal State : Open Tag -> empty tag.
     */
    int STATE_OPEN_TAG_EMPTY_TAG = 13;

    /**
     * Internal State : Close Tag -> read element name.
     */
    int STATE_CLOSE_TAG_READ_ELEM_NAME = 14;

    /**
     * Internal State : Close Tag -> the element name is readed.
     */
    int STATE_CLOSE_TAG_ELEM_NAME_READ = 15;

    /**
     * Internal State : DTD.
     */
    int STATE_DTD = 16;

    /**
     * Internal State : Internal DTD.
     */
    int STATE_DTD_INTERNAL = 17;

    /**
     * Holds the textual representation for states.
     */
    String[] NAMES = new String[]{
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
}
