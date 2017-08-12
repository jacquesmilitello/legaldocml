package io.legaldocml.akn.group;

/**
 * The group speechSection lists the structures that should contain speeches.
 *
 * <pre>
 *   &lt;xsd:group name="speechSection"&gt;
 *     &lt;xsd:choice&gt;
 * 		 &lt;xsd:element ref="administrationOfOath"/&gt;
 * 		 &lt;xsd:element ref="rollCall"/&gt;
 * 		 &lt;xsd:element ref="prayers"/&gt;
 * 		 &lt;xsd:element ref="oralStatements"/&gt;
 * 		 &lt;xsd:element ref="writtenStatements"/&gt;
 * 		 &lt;xsd:element ref="personalStatements"/&gt;
 * 		 &lt;xsd:element ref="ministerialStatements"/&gt;
 * 		 &lt;xsd:element ref="resolutions"/&gt;
 * 		 &lt;xsd:element ref="nationalInterest"/&gt;
 * 		 &lt;xsd:element ref="declarationOfVote"/&gt;
 * 		 &lt;xsd:element ref="communication"/&gt;
 * 		 &lt;xsd:element ref="petitions"/&gt;
 * 		 &lt;xsd:element ref="papers"/&gt;
 * 		 &lt;xsd:element ref="noticesOfMotion"/&gt;
 * 		 &lt;xsd:element ref="questions"/&gt;
 * 		 &lt;xsd:element ref="address"/&gt;
 * 		 &lt;xsd:element ref="proceduralMotions"/&gt;
 * 		 &lt;xsd:element ref="pointOfOrder"/&gt;
 * 		 &lt;xsd:element ref="adjournment"/&gt;
 * 		 &lt;xsd:element ref="debateSection"/&gt;
 *     &lt;xsd:choice&gt;
 * 	 &lt;xsd:group&gt;
 * </pre>
 *
 * @author <a href="mailto:jacques.militello@gmail.com">Jacques Militello</a>
 */
public interface SpeechSection extends ContainerElements {

}