package io.legaldocml.akn.attribute;

import io.legaldocml.akn.type.VoteRef;

/**
 * The attributes in voteAtts are used in votes to identify agents and choices in votes. In particular, attribute 'by'
 * identifies the voter, optional attribute 'as' identifies the role under which the voter is acting, optional attribute
 * 'choice' specifies the vote that was casted.
 *
 * ```xml
 * <xsd:attributeGroup name="voteAtts">
 *   <xsd:attributeGroup ref="agent"/>
 *   <xsd:attributeGroup ref="role"/>
 *   <xsd:attribute name="choice" type="voteRef"/>
 * </xsd:attributeGroup>
 * ```
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
