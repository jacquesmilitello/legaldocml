package io.legaldocml.io;

import java.io.Serializable;

/**
 * @author <a href="mailto:jacques.militello@gmail.com">Jacques Militello</a>
 * @see <a href="http://en.wikipedia.org/wiki/Qname">QName</a>
 */
public interface QName extends Serializable {

    String getLocalName();

    String getPrefix();

    boolean equalsLocalName(String element);
}
