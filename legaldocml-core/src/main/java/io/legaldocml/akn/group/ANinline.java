package io.legaldocml.akn.group;

/**
 * The group ANinline lists the elements that are inline and are specific to the Akoma Ntoso vocabulary.
 * <p/>
 * <pre>
 *   <xsd:group name="ANinline">
 *     <xsd:choice>
 * 	     <xsd:element ref="ref"/>
 *       <xsd:element ref="mref"/>
 *       <xsd:element ref="rref"/>
 *       <xsd:element ref="mod"/>
 *       <xsd:element ref="mmod"/>
 *       <xsd:element ref="rmod"/>
 *       <xsd:element ref="remark"/>
 *       <xsd:element ref="recordedTime"/>
 *       <xsd:element ref="vote"/>
 *       <xsd:element ref="outcome"/>
 *       <xsd:element ref="ins"/>
 *       <xsd:element ref="del"/>
 *       <xsd:element ref="omissis"/>
 *       <xsd:element ref="embeddedText"/>
 *       <xsd:element ref="embeddedStructure"/>
 *       <xsd:element ref="opinion"/>
 *       <xsd:element ref="placeholder"/>
 *       <xsd:element ref="fillIn"/>
 *       <xsd:element ref="decoration"/>
 *     </xsd:choice>
 *   </xsd:group>
 * </pre>
 *
 * @author <a href="mailto:jacques.militello@gmail.com">Jacques Militello</a>
 */
public interface ANinline extends InlineElements {

}