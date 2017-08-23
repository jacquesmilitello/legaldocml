package io.legaldocml.akn.group;

/**
 * The group speechSection lists the structures that should contain speeches.
 *
 * <pre>
 *   <xsd:group name="speechSection">
 *     <xsd:choice>
 * 		 <xsd:element ref="administrationOfOath"/>
 * 		 <xsd:element ref="rollCall"/>
 * 		 <xsd:element ref="prayers"/>
 * 		 <xsd:element ref="oralStatements"/>
 * 		 <xsd:element ref="writtenStatements"/>
 * 		 <xsd:element ref="personalStatements"/>
 * 		 <xsd:element ref="ministerialStatements"/>
 * 		 <xsd:element ref="resolutions"/>
 * 		 <xsd:element ref="nationalInterest"/>
 * 		 <xsd:element ref="declarationOfVote"/>
 * 		 <xsd:element ref="communication"/>
 * 		 <xsd:element ref="petitions"/>
 * 		 <xsd:element ref="papers"/>
 * 		 <xsd:element ref="noticesOfMotion"/>
 * 		 <xsd:element ref="questions"/>
 * 		 <xsd:element ref="address"/>
 * 		 <xsd:element ref="proceduralMotions"/>
 * 		 <xsd:element ref="pointOfOrder"/>
 * 		 <xsd:element ref="adjournment"/>
 * 		 <xsd:element ref="debateSection"/>
 *     <xsd:choice>
 * 	 <xsd:group>
 * </pre>
 *
 * @author <a href="mailto:jacques.militello@gmail.com">Jacques Militello</a>
 */
public interface SpeechSection extends ContainerElements {

}