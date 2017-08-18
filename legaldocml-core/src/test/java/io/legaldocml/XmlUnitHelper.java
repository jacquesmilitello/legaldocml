package io.legaldocml;

import org.junit.Assert;
import org.w3c.dom.Node;
import org.xmlunit.builder.DiffBuilder;
import org.xmlunit.diff.ComparisonControllers;
import org.xmlunit.diff.Diff;
import org.xmlunit.diff.Difference;

import java.io.InputStream;
import java.util.Iterator;

/**
 * @author <a href="mailto:jacques.militello@gmail.com">Jacques Militello</a>
 */
public class XmlUnitHelper {

    public static void compare(InputStream expectedXml, InputStream testXml) {

        Diff myDiff = DiffBuilder
                .compare(expectedXml)
                .withTest(testXml)
                .withComparisonController(ComparisonControllers.StopWhenDifferent)
                .checkForIdentical()
                .ignoreComments()
                .ignoreWhitespace()
                .normalizeWhitespace()
                .checkForSimilar()
                .build();

        Iterator<Difference> iter = myDiff.getDifferences().iterator();
        int size = 0;
        while (iter.hasNext()) {
            System.out.println(iter.next().toString());
            size++;
        }

        Assert.assertEquals(0, size);

    }


    public static void compare(Node expectedXml, Node testXml) {

        Diff myDiff = DiffBuilder
                .compare(expectedXml)
                .withTest(testXml)
                .withComparisonController(ComparisonControllers.StopWhenDifferent)
                .checkForIdentical()
                .ignoreComments()
                .ignoreWhitespace()
                .normalizeWhitespace()
                .checkForSimilar()
                .build();

        Iterator<Difference> iter = myDiff.getDifferences().iterator();
        int size = 0;
        while (iter.hasNext()) {
            System.out.println(iter.next().toString());
            size++;
        }

        Assert.assertEquals(0, size);

    }



}
