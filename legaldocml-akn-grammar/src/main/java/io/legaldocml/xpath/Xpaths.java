package io.legaldocml.xpath;

import io.legaldocml.xpath.antlr.XpathLexer;
import io.legaldocml.xpath.antlr.XpathParser;
import org.antlr.v4.runtime.CharStreams;
import org.antlr.v4.runtime.CommonTokenStream;

public final class Xpaths {

    public static Xpath compile(String xpath) {
        XpathLexer lexer = new XpathLexer(CharStreams.fromString(xpath));
        XpathParser parser = new XpathParser(new CommonTokenStream(lexer));

        XpathPlanListener listener = new XpathPlanListener();
        parser.addParseListener(listener);
        parser.main();

        return listener.getBuilder().build();

    }


}
