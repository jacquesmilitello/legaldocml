package io.legaldocml.io;

/**
 * @author <a href="mailto:jacques.militello@gmail.com">Jacques Militello</a>
 * @see <a href="http://en.wikipedia.org/wiki/Qname">QName</a>
 */
public interface QName {

    String getLocalName();

    String getPrefix();

    boolean equalsLocalName(String element);
}
