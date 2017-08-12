package io.legaldocml.akn.group;

/**
 * The group ANsemanticInline lists additional elements that are inline, and are specific to the Akoma Ntoso vocabulary.
 *
 * <pre>
 *   &lt;xsd:group name="ANsemanticInline"&gt;
 * 	   &lt;xsd:choice&gt;
 * 	     &lt;xsd:element ref="date" /&gt;
 * 		 &lt;xsd:element ref="time" /&gt;
 * 		 &lt;xsd:element ref="person" /&gt;
 * 		 &lt;xsd:element ref="organization" /&gt;
 * 		 &lt;xsd:element ref="concept" /&gt;
 * 		 &lt;xsd:element ref="object" /&gt;
 * 		 &lt;xsd:element ref="event" /&gt;
 * 		 &lt;xsd:element ref="location" /&gt;
 * 		 &lt;xsd:element ref="process" /&gt;
 * 		 &lt;xsd:element ref="role" /&gt;
 * 		 &lt;xsd:element ref="term" /&gt;
 * 		 &lt;xsd:element ref="quantity" /&gt;
 * 		 &lt;xsd:element ref="def" /&gt;
 * 		 &lt;xsd:element ref="entity" /&gt;
 * 	   &lt;xsd:choice&gt;
 *   &lt;xsd:group&gt;
 * </pre>
 *
 * @author <a href="mailto:jacques.militello@gmail.com">Jacques Militello</a>
 */
public interface ANsemanticInline extends InlineElements {

}