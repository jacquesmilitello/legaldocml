package io.legaldocml.akn.type;

/**
 * This is the list of allowed values for the status attribute. This is the list of possible reasons for a dscrepancy between the manifestation as it should be (e.g., a faithful representation of the content of an expression), and the manifestation as it actually is. Values should be interpreted as follows:
 *<ul>
 *<li> removed: the content of the element is present in the markup (manifestation) but is not present in the real content of the document (expression level) because it has been definitely removed (either ex tunc, as in annullments, or ex nunc, as in abrogations).</li>
 *<li> temporarily removed: the content of the element is present in the markup (manifestation) but is not present in the real content of the document (expression level) because it has been temporarily removed (e.g., for a temporary suspension or limitation of efficacy).</li>
 *<li> translated: the content of the element is present in the markup (manifestation) in a different form than in the real content of the document (expression level) because it has been translated into a different language (e.g., to match the rest of the document or because of other editorial decisions).</li>
 *<li> editorial: the content of the element is present in the markup (manifestation) but is not present in the real content of the document (expression level) because it has been inserted as an editorial process when creating the XML markup.</li>
 *<li> edited: the content of the element is different in the markup (manifestation) than in the real content of the document (expression level) because it has been amended (e.g., to remove scurrilous or offensive remarks).</li>
 *<li> verbatim: the content of the element is present in the markup (manifestation) is EXACTLY as it was in the real content of the document (expression level) because usual silent fixes and edits were NOT performed (e.g. to punctuation, grammatical errors or other usually non-debatable problems).</li>
 *<li> incomplete: the content of the element or the value of a required attribute is NOT present in the markup (manifestation), although it should, because the missing data is not known at the moment, but in the future it might become known. This is especially appropriate for documents in drafting phase (e.g., the publication date of the act while drafting the bill)</li>
 *<li> unknown: the content of the element or the value of a required attribute is NOT present in the markup (manifestation), although it should, because the author of the manifestation does not know it.</li>
 *<li>undefined: the content of the element or the value of a required attribute is NOT present in the markup (manifestation), because the information is not defined in the original document, or it doesn't exist in some legal tradition (e.g. an anonymous speech cannot specify the attribute by, or some publications do not record the numbering of the items, etc.)</li>
 *<li> ignored: the content of the element or the value of a required attribute is NOT present in the markup (manifestation) because the information exists but the author of the manifestation is not interested in reporting it (e.g., omitted parts of the document due to editorial reasons, etc.)</li>
 *</ul>
 *
 * <pre>
 *   &lt;xsd:simpleType name="statusType"&gt;
 * 	   &lt;xsd:restriction base="xsd:string"&gt;
 * 	     &lt;xsd:enumeration value="removed"/&gt;
 *       &lt;xsd:enumeration value="temporarilyRemoved"/&gt;
 *       &lt;xsd:enumeration value="translated"/&gt;
 *       &lt;xsd:enumeration value="editorial"/&gt;
 *       &lt;xsd:enumeration value="edited"/&gt;
 *       &lt;xsd:enumeration value="verbatim"/&gt;
 *       &lt;xsd:enumeration value="incomplete"/&gt;
 *       &lt;xsd:enumeration value="unknown"/&gt;
 *       &lt;xsd:enumeration value="undefined"/&gt;
 *       &lt;xsd:enumeration value="ignored"/&gt;
 * 	   &lt;xsd:restriction>
 *   &lt;xsd:simpleType>
 * </pre>
 *
 * @author <a href="mailto:jacques.militello@gmail.com">Jacques Militello</a>
 */
public enum StatusType {

    @Deprecated omissis, @Deprecated ignore, removed,temporarilyRemoved, translated, editorial, edited, verbatim, incomplete, unknown, undefined, ignored

}