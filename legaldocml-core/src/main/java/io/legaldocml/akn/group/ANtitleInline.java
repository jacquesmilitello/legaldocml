package io.legaldocml.akn.group;

/**
 * The group ANtitleInline lists the elements that are inline, are specific to the Akoma Ntoso vocabulary, and should
 * only be used within the initial elements (preface, preamble and coverpage).
 *
 * <pre>
 *   &lt;xsd:group name="ANtitleInline"&gt;
 * 	   &lt;xsd:choice&gt;
 * 	     &lt;xsd:element ref="docType" /&gt;
 * 	  	 &lt;xsd:element ref="docTitle" /&gt;
 * 		 &lt;xsd:element ref="docNumber" /&gt;
 * 		 &lt;xsd:element ref="docProponent" /&gt;
 * 		 &lt;xsd:element ref="docDate" /&gt;
 * 	 	 &lt;xsd:element ref="legislature" /&gt;
 * 		 &lt;xsd:element ref="session" /&gt;
 * 		 &lt;xsd:element ref="shortTitle"/&gt;
 *       &lt;xsd:element ref="docAuthority" /&gt;
 * 	     &lt;xsd:element ref="docPurpose" /&gt;
 * 		 &lt;xsd:element ref="docCommittee" /&gt;
 * 		 &lt;xsd:element ref="docIntroducer" /&gt;
 * 		 &lt;xsd:element ref="docStage" /&gt;
 * 		 &lt;xsd:element ref="docStatus" /&gt;
 * 	     &lt;xsd:element ref="docJurisdiction" /&gt;
 * 		 &lt;xsd:element ref="docketNumber" /&gt;
 * 	   &lt;xsd:choice&gt;
 *   &lt;xsd:group&gt;
 * </pre>
 *
 * @author <a href="mailto:jacques.militello@gmail.com">Jacques Militello</a>
 */
public interface ANtitleInline extends InlineElements {

}