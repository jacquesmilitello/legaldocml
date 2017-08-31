<sch:schema xmlns:sch="http://purl.oclc.org/dsdl/schematron" id="test">

   <sch:let name="let01" value="hello"/>
   <sch:let name="let02" value="world"/>
   <!--
     Set of rules applying to the entire document
   -->
   <sch:pattern id="Document">

      <sch:rule context="/document">

         <!-- Too many sections -->
         <sch:assert test="count(section) le 20">Your document contains over 20
            sections. You should consider splitting your document into multiple
            documents.
         </sch:assert>

         <!-- No Doc ID -->
         <sch:report test="not(documentinfo/uri/@docid)">Your document
            does not have a Doc ID.
         </sch:report>

         <!-- No Label -->
         <sch:report test="not(documentinfo/uri/@labels)">Your document
            has no label.
         </sch:report>

      </sch:rule>

   </sch:pattern>

   <!--
     Set of rules applying to the document fragments
   -->
   <sch:pattern id="Fragments">

      <sch:rule context="section/fragment">

         <!-- Fragment has no heading -->
         <sch:assert test="name(*[1]) = 'heading'">Fragment
            '<sch:value-of select="@id"/>' has no title.
         </sch:assert>

         <!-- Fragment is too long (over 2000 chars)-->
         <sch:assert test="string-length(string-join(.//text(), '')) le 2000">
            Fragment '<sch:value-of select="@id"/>' has over 2000 characters.
         </sch:assert>

      </sch:rule>

   </sch:pattern>

</sch:schema>