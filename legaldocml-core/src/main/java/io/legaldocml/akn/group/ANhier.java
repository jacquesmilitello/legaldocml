package io.legaldocml.akn.group;


/**
 * The group ANhier lists the elements that belong to the Akoma Ntoso legislative hierarchy.
 *
 * <pre>
 *   &lt;xsd:group name="ANhier"&gt;
 * 	   &lt;xsd:choice&gt;
 * 	     &lt;xsd:element ref="clause"/&gt;
 *       &lt;xsd:element ref="section"/&gt;
 *       &lt;xsd:element ref="part"/&gt;
 *       &lt;xsd:element ref="paragraph"/&gt;
 *       &lt;xsd:element ref="chapter"/&gt;
 *       &lt;xsd:element ref="title"/&gt;
 *       &lt;xsd:element ref="article"/&gt;
 *       &lt;xsd:element ref="book"/&gt;
 *       &lt;xsd:element ref="tome"/&gt;
 *       &lt;xsd:element ref="division"/&gt;
 *       &lt;xsd:element ref="list"/&gt;
 *       &lt;xsd:element ref="point"/&gt;
 *       &lt;xsd:element ref="indent"/&gt;
 *       &lt;xsd:element ref="alinea"/&gt;
 *       &lt;xsd:element ref="rule"/&gt;
 *       &lt;xsd:element ref="subrule"/&gt;
 *       &lt;xsd:element ref="proviso"/&gt;
 *       &lt;xsd:element ref="subsection"/&gt;
 *       &lt;xsd:element ref="subpart"/&gt;
 *       &lt;xsd:element ref="subparagraph"/&gt;
 *       &lt;xsd:element ref="subchapter"/&gt;
 *       &lt;xsd:element ref="subtitle"/&gt;
 *       &lt;xsd:element ref="subdivision"/&gt;
 *       &lt;xsd:element ref="subclause"/&gt;
 *       &lt;xsd:element ref="sublist"/&gt;
 *       &lt;xsd:element ref="level"/&gt;
 *       &lt;xsd:element ref="transitional"/&gt;
 * 	   &lt;xsd:choice&gt;
 *   &lt;xsd:group&gt;
 * </pre>
 *
 * @author <a href="mailto:jacques.militello@gmail.com">Jacques Militello</a>
 */
public interface ANhier extends HierElements {

}