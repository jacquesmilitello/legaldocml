package io.legaldocml.akn.group;

/**
 * The group inlineElements lists all the elements that are inline.
 *
 * <pre>
 *   &lt;xsd:group name="inlineElements"&gt;
 * 	   &lt;xsd:choice&gt;
 * 	     &lt;xsd:group ref="ANinline"/&gt;
 * 		 &lt;xsd:group ref="HTMLinline"/&gt;
 * 		 &lt;xsd:group ref="ANtitleInline"/&gt;
 * 		 &lt;xsd:group ref="ANsemanticInline"/&gt;
 * 		 &lt;xsd:group ref="ANheaderInline"/&gt;
 * 		 &lt;xsd:group ref="amendmentInline"/&gt;
 * 		 &lt;xsd:element ref="inline"/&gt;
 * 	   &lt;xsd:choice&gt;
 *   &lt;xsd:group&gt;
 * </pre>
 *
 * @author <a href="mailto:jacques.militello@gmail.com">Jacques Militello</a>
 */
public interface InlineElements extends InlineCM {

}