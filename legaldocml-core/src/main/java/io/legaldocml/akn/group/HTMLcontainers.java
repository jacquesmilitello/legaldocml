package io.legaldocml.akn.group;

/**
 * The group HTMLcontainers lists the elements that are containers and were inherited from the HTML vocabulary.
 *
 * <pre>
 *   &lt;xsd:group name="HTMLcontainers"&gt;
 * 	   &lt;xsd:choice&gt;
 * 	     &lt;xsd:element ref="div"/&gt;
 * 	   &lt;xsd:choice&gt;
 *   &lt;xsd:group&gt;
 * </pre>
 *
 * @author <a href="mailto:jacques.militello@gmail.com">Jacques Militello</a>
 */
public interface HTMLcontainers extends ContainerElements {

}