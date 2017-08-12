package io.legaldocml.akn.group;

import io.legaldocml.akn.AknObject;

/**
 * The group judgmentBlock lists the structures that should be found in a judgment.
 *
 * <pre>
 *   &lt;xsd:group name="amendmentBlock"&gt;
 * 	   &lt;xsd:choice&gt;
 * 	     &lt;xsd:element ref="introduction"/&gt;
 *       &lt;xsd:element ref="background"/&gt;
 *       &lt;xsd:element ref="arguments"/&gt;
 *       &lt;xsd:element ref="remedies"/&gt;
 *       &lt;xsd:element ref="motivation"/&gt;
 *       &lt;xsd:element ref="decision"/&gt;
 * 	   &lt;xsd:choice&gt;
 *   &lt;xsd:group&gt;
 * </pre>
 * @author <a href="mailto:jacques.militello@gmail.com">Jacques Militello</a>
 *
 */
public interface JudgmentBlock extends AknObject {

}