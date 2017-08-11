package io.legaldocml.akn.group;

/**
 * The group ANtitleInline lists the elements that are inline, are specific to the Akoma Ntoso vocabulary, and should
 * only be used within the initial elements (preface, preamble and coverpage).
 * <p>
 * <pre>
 *   <xsd:group name="ANtitleInline">
 * 	   <xsd:choice>
 * 	     <xsd:element ref="docType" />
 * 	  	 <xsd:element ref="docTitle" />
 * 		 <xsd:element ref="docNumber" />
 * 		 <xsd:element ref="docProponent" />
 * 		 <xsd:element ref="docDate" />
 * 	 	 <xsd:element ref="legislature" />
 * 		 <xsd:element ref="session" />
 * 		 <xsd:element ref="shortTitle"/>
 *       <xsd:element ref="docAuthority" />
 * 	     <xsd:element ref="docPurpose" />
 * 		 <xsd:element ref="docCommittee" />
 * 		 <xsd:element ref="docIntroducer" />
 * 		 <xsd:element ref="docStage" />
 * 		 <xsd:element ref="docStatus" />
 * 	     <xsd:element ref="docJurisdiction" />
 * 		 <xsd:element ref="docketNumber" />
 * 	   </xsd:choice>
 *   </xsd:group>
 * </pre>
 *
 * @author <a href="mailto:jacques.militello@gmail.com">Jacques Militello</a>
 */
public interface ANtitleInline extends InlineElements {

}