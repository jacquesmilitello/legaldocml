package io.legaldocml.akn.group;

/**
 * The group ANinline lists the elements that are inline and are specific to the Akoma Ntoso vocabulary.
 *
 * <pre>
 *   &lt;xsd:group name="ANinline"&gt;
 *     &lt;xsd:choice&gt;
 * 	     &lt;xsd:element ref="ref"/&gt;
 *       &lt;xsd:element ref="mref"/&gt;
 *       &lt;xsd:element ref="rref"/&gt;
 *       &lt;xsd:element ref="mod"/&gt;
 *       &lt;xsd:element ref="mmod"/&gt;
 *       &lt;xsd:element ref="rmod"/&gt;
 *       &lt;xsd:element ref="remark"/&gt;
 *       &lt;xsd:element ref="recordedTime"/&gt;
 *       &lt;xsd:element ref="vote"/&gt;
 *       &lt;xsd:element ref="outcome"/&gt;
 *       &lt;xsd:element ref="ins"/&gt;
 *       &lt;xsd:element ref="del"/&gt;
 *       &lt;xsd:element ref="omissis"/&gt;
 *       &lt;xsd:element ref="embeddedText"/&gt;
 *       &lt;xsd:element ref="embeddedStructure"/&gt;
 *       &lt;xsd:element ref="opinion"/&gt;
 *       &lt;xsd:element ref="placeholder"/&gt;
 *       &lt;xsd:element ref="fillIn"/&gt;
 *       &lt;xsd:element ref="decoration"/&gt;
 *     &lt;xsd:choice&gt;
 *   &lt;xsd:group&gt;
 * </pre>
 *
 * @author <a href="mailto:jacques.militello@gmail.com">Jacques Militello</a>
 */
public interface ANinline extends InlineElements {

}