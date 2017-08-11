package io.legaldocml.akn.group;

import io.legaldocml.akn.AknObject;

/**
 * The group judgmentBlock lists the structures that should be found in a judgment.
 * <p/>
 * <pre>
 *   <xsd:group name="amendmentBlock">
 * 	   <xsd:choice>
 * 	     <xsd:element ref="introduction"/>
 *       <xsd:element ref="background"/>
 *       <xsd:element ref="arguments"/>
 *       <xsd:element ref="remedies"/>
 *       <xsd:element ref="motivation"/>
 *       <xsd:element ref="decision"/>
 * 	   </xsd:choice>
 *   </xsd:group>
 * </pre>
 * @author <a href="mailto:jacques.militello@gmail.com">Jacques Militello</a>
 *
 */
public interface JudgmentBlock extends AknObject {

}