package io.legaldocml.akn.element;

import io.legaldocml.akn.attribute.CoreReq;
import io.legaldocml.io.XmlWriter;

import java.io.IOException;

import static io.legaldocml.akn.util.XmlWriterHelper.writeAlt;
import static io.legaldocml.akn.util.XmlWriterHelper.writeEnactment;
import static io.legaldocml.akn.util.XmlWriterHelper.writeHTMLattrs;
import static io.legaldocml.akn.util.XmlWriterHelper.writeIdReq;
import static io.legaldocml.akn.util.XmlWriterHelper.writeRefers;

/**
 * This is the list of the core attributes that all elements in the content part of the document must have. In elements
 * using this attribute definition both the refersTo attribute and the id attribute are optional.
 *
 * <pre>
 *   &lt;xsd:attributeGroup name="corereq"&gt;
 * 	   &lt;xsd:attributeGroup ref="core"/&gt;
 * 	   &lt;xsd:attributeGroup ref="HTMLattrs"/&gt;
 * 	   &lt;xsd:attributeGroup ref="enactment"/&gt;
 * 	   &lt;xsd:attributeGroup ref="idreq"/&gt;
 * 	   &lt;xsd:attributeGroup ref="refers"/&gt;
 * 	   &lt;xsd:attributeGroup ref="xmllang"/&gt;
 * 	   &lt;xsd:attributeGroup ref="alt"/&gt;
 *   &lt;xsd:attributeGroup&gt;
 * </pre>
 *
 * @author <a href="mailto:jacques.militello@gmail.com">Jacques Militello</a>
 */
public abstract class CoreReqImpl extends AbstractCore implements CoreReq {

    /**
     * {@inheritDoc}
     */
    @Override
    public void write(XmlWriter writer) throws IOException {
        super.write(writer);
        writeIdReq(writer, this);
        writeRefers(writer, this);
        writeHTMLattrs(writer, this);
        writeEnactment(writer, this);
        writeAlt(writer, this);
    }

}