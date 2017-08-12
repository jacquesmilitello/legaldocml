package io.legaldocml.akn.group;


/**
 * The group subFlowElements lists all the elements that are subFlows.
 *
 * <pre>
 *   &lt;xsd:group name="subFlowElements"&gt;
 * 	   &lt;xsd:choice&gt;
 * 	     &lt;xsd:group ref="ANsubFlow"/&gt;
 * 	     &lt;xsd:element ref="subFlow"/&gt;
 * 	   &lt;xsd:choice&gt;
 *   &lt;xsd:group&gt;
 * </pre>
 *
 * @author <a href="mailto:jacques.militello@gmail.com">Jacques Militello</a>
 */
public interface SubFlowElements extends InlineCM {

}