package io.legaldocml.akn.group;

/**
 * The group HTMLinline lists the elements that are inline and were inherited from the HTML vocabulary.
 *
 * <pre>
 *   &lt;xsd:group name="HTMLinline"&gt;
 * 	   &lt;xsd:choice&gt;
 * 	     &lt;xsd:element ref="b"/&gt;
 * 		 &lt;xsd:element ref="i"/&gt;
 * 		 &lt;xsd:element ref="a"/&gt;
 * 		 &lt;xsd:element ref="u"/&gt;
 * 		 &lt;xsd:element ref="sub"/&gt;
 * 		 &lt;xsd:element ref="sup"/&gt;
 * 		 &lt;xsd:element ref="abbr"/&gt;
 * 		 &lt;xsd:element ref="span"/&gt;
 * 	   &lt;xsd:choice&gt;
 *   &lt;xsd:group&gt;
 * </pre>
 *
 * @author <a href="mailto:jacques.militello@gmail.com">Jacques Militello</a>
 */
public interface HTMLinline extends InlineElements {

}