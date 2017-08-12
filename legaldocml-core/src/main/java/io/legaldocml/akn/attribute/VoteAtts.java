package io.legaldocml.akn.attribute;

import io.legaldocml.akn.type.VoteRef;

/**
 * The attributes in voteAtts are used in votes to identify agents and choices in votes. In particular, attribute 'by'
 * identifies the voter, optional attribute 'as' identifies the role under which the voter is acting, optional attribute
 * 'choice' specifies the vote that was casted.
 *
 * <pre>
 * 	 &lt;xsd:attributeGroup name="voteAtts"&gt;
 * 	   &lt;xsd:attributeGroup ref="agent"/&gt;
 *     &lt;xsd:attributeGroup ref="role"/&gt;
 *     &lt;xsd:attribute name="choice" type="voteRef"/&gt;
 * 	 &lt;xsd:attributeGroup&gt;
 * </pre>
 *
 * @author <a href="mailto:jacques.militello@gmail.com">Jacques Militello</a>
 */
public interface VoteAtts extends Role, Agent {

    /**
     * Attribute name "choice".
     */
    String ATTRIBUTE = "choice";

    VoteRef getChoice();

    void setChoice(VoteRef choice);

}
