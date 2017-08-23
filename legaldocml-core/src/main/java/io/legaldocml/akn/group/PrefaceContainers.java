package io.legaldocml.akn.group;


import io.legaldocml.akn.element.PrefaceoptElement;

/**
 * The group prefaceContainers lists the elements that are containers and are specific to the Akoma Ntoso preface
 * vocabulary.
 *
 * <pre>
 *   <xsd:group name="prefaceContainers">
 * 	   <xsd:choice>
 * 	     <xsd:element ref="longTitle"/>
 * 		 <xsd:element ref="formula"/>
 * 		 <xsd:element ref="container"/>
 * 	   <xsd:choice>
 *   <xsd:group>
 * </pre>
 *
 * @author <a href="mailto:jacques.militello@gmail.com">Jacques Militello</a>
 */
public interface PrefaceContainers extends PrefaceoptElement {

}