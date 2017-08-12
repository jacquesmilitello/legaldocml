package io.legaldocml.akn.attribute;

/**
 * This is the list of the core attributes that all elements in the content part of the document must have. In elements
 * using this attribute definition both the refersTo attribute and the eId attribute are required.
 *
 * <pre>
 *   &lt;xsd:attributeGroup name="corereqreq"&gt;
 * 	   &lt;xsd:attributeGroup ref="core"/&gt;
 * 	   &lt;xsd:attributeGroup ref="HTMLattrs" /&gt;
 * 	   &lt;xsd:attributeGroup ref="enactment" /&gt;
 * 	   &lt;xsd:attributeGroup ref="idreq" /&gt;
 * 	   &lt;xsd:attributeGroup ref="refersreq" /&gt;
 * 	   &lt;xsd:attributeGroup ref="xmllang" /&gt;
 * 	   &lt;xsd:attributeGroup ref="alt" /&gt;
 *   &lt;xsd:attributeGroup&gt;
 * </pre>
 *
 * @author <a href="mailto:jacques.militello@gmail.com">Jacques Militello</a>
 */
public interface CoreReqReq extends Core, HTMLattrs, Enactment, IdReq, RefersReq, Alt {

}