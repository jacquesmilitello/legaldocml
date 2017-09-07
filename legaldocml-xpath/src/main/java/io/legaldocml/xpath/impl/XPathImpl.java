package io.legaldocml.xpath.impl;

import io.legaldocml.module.Module;
import io.legaldocml.module.Modules;
import io.legaldocml.module.akn.v3.AkomaNtosoModuleV3;
import io.legaldocml.xpath.XPath;
import io.legaldocml.xpath.XPath2Lexer;
import io.legaldocml.xpath.XPath2Parser;
import io.legaldocml.xpath.XPathExpression;
import org.antlr.v4.runtime.CharStreams;
import org.antlr.v4.runtime.CommonTokenStream;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * @author <a href="mailto:jacques.militello@gmail.com">Jacques Militello</a>
 */
final class XPathImpl implements XPath {

    /**
     * SLF4J Logger.
     */
    private static final Logger LOGGER = LoggerFactory.getLogger(XPathImpl.class);

    @Override
    public XPathExpression compile(String expression) {

        if (LOGGER.isDebugEnabled()) {
            LOGGER.debug("compile({})", expression);
        }

        XPath2Parser parser = new XPath2Parser(new CommonTokenStream(new XPath2Lexer(CharStreams.fromString(expression))));
        XPathExpressionBuilder builder = new XPathExpressionBuilder(this, parser);
        parser.addParseListener(builder);
        parser.xPath();
        return builder.build();
    }

    @Override
    public Module getModule(String prefix) {
        return Modules.get(AkomaNtosoModuleV3.NAMESPACE);
    }

}
