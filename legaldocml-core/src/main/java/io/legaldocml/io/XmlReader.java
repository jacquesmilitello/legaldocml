package io.legaldocml.io;

/**
 * @author <a href="mailto:jacques.militello@gmail.com">Jacques Militello</a>
 */
public interface XmlReader {

    /**
     * Get next parsing event - a processor may return all contiguous character data in a single chunk, or it may split
     * it into several chunks.
     * <p/>
     * By default entity references must be expanded and reported transparently to the application. An exception will be
     * thrown if an entity reference cannot be expanded. If element content is empty (i.e. content is "") then no
     * CHARACTERS event will be reported.
     * <p/>
     *
     * @return the integer code corresponding to the current parse event
     */
    int next();

    /**
     * Go to the next START_ELEMENT or END_ELEMENT.
     */
    void nextStartOrEndElement();

    /**
     * Returns an integer code that indicates the type of the event the cursor is pointing to.
     */
    int getEventType();

    /**
     * Returns an integer depth that indicates depth in the xml hierarchy.
     */
    int getDepth();

    /**
     * Returns the qualidied name of the current event.
     */
    QName getQName();

    /**
     * Returns the current value of the parse event as a string, this returns the string value of a CHARACTERS event.
     */
    CharArray getText();

    /**
     * Returns the current Processing Instruction of a PROCESSING_INSTRUCTION event.
     */
    ProcessingInstruction getPIData();

    /**
     * Returns the current Processing Instruction of the prolog.
     */
    ProcessingInstruction getProlog();

    Namespaces getNamespaces();

    <T> void forEach(T object, AttributeConsumer<T> consumer);

    void preserveSpace();

    <T extends XmlReaderContext> void setContext(T context);

    <T extends XmlReaderContext> T getContext();
}
