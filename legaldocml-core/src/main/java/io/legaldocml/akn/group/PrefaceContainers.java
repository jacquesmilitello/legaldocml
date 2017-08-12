package io.legaldocml.akn.group;


import io.legaldocml.akn.element.PrefaceoptElement;

/**
 * The group prefaceContainers lists the elements that are containers and are specific to the Akoma Ntoso preface
 * vocabulary.
 *
 * <pre>
 *   &lt;xsd:group name="prefaceContainers"&gt;
 * 	   &lt;xsd:choice&gt;
 * 	     &lt;xsd:element ref="longTitle"/&gt;
 * 		 &lt;xsd:element ref="formula"/&gt;
 * 		 &lt;xsd:element ref="container"/&gt;
 * 	   &lt;xsd:choice&gt;
 *   &lt;xsd:group&gt;
 * </pre>
 *
 * @author <a href="mailto:jacques.militello@gmail.com">Jacques Militello</a>
 */
public interface PrefaceContainers extends PrefaceoptElement {

}