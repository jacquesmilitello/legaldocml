package io.legaldocml.akn.group;


/**
 * The group ANhier lists the elements that belong to the Akoma Ntoso legislative hierarchy.
 * <p>
 * <pre>
 *   <xsd:group name="ANhier">
 * 	   <xsd:choice>
 * 	     <xsd:element ref="clause"/>
 *       <xsd:element ref="section"/>
 *       <xsd:element ref="part"/>
 *       <xsd:element ref="paragraph"/>
 *       <xsd:element ref="chapter"/>
 *       <xsd:element ref="title"/>
 *       <xsd:element ref="article"/>
 *       <xsd:element ref="book"/>
 *       <xsd:element ref="tome"/>
 *       <xsd:element ref="division"/>
 *       <xsd:element ref="list"/>
 *       <xsd:element ref="point"/>
 *       <xsd:element ref="indent"/>
 *       <xsd:element ref="alinea"/>
 *       <xsd:element ref="rule"/>
 *       <xsd:element ref="subrule"/>
 *       <xsd:element ref="proviso"/>
 *       <xsd:element ref="subsection"/>
 *       <xsd:element ref="subpart"/>
 *       <xsd:element ref="subparagraph"/>
 *       <xsd:element ref="subchapter"/>
 *       <xsd:element ref="subtitle"/>
 *       <xsd:element ref="subdivision"/>
 *       <xsd:element ref="subclause"/>
 *       <xsd:element ref="sublist"/>
 *       <xsd:element ref="level"/>
 *       <xsd:element ref="transitional"/>
 * 	   </xsd:choice>
 *   </xsd:group>
 * </pre>
 *
 * @author <a href="mailto:jacques.militello@gmail.com">Jacques Militello</a>
 */
public interface ANhier extends HierElements {

}