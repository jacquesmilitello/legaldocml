package io.legaldocml.akn.group;


/**
 * The group subFlowElements lists all the elements that are subFlows.
 * <p/>
 * <pre>
 *   <xsd:group name="subFlowElements">
 * 	   <xsd:choice>
 * 	     <xsd:group ref="ANsubFlow"/>
 * 	     <xsd:element ref="subFlow"/>
 * 	   </xsd:choice>
 *   </xsd:group>
 * </pre>
 *
 * @author <a href="mailto:jacques.militello@gmail.com">Jacques Militello</a>
 */
public interface SubFlowElements extends InlineCM {

}