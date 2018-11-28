package io.legaldocml.test;

import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.core.LoggerContext;
import org.apache.logging.log4j.core.config.Configuration;
import org.apache.logging.log4j.core.config.LoggerConfig;
import org.junit.jupiter.api.extension.ExtensionContext;
import org.junit.jupiter.api.extension.TestInstancePostProcessor;

/**
 * @author <a href="mailto:jacques.militello@gmail.com">Jacques Militello</a>
 */
public class LoggerInstancePostProcessor implements TestInstancePostProcessor {

    @Override
    public void postProcessTestInstance(Object o, ExtensionContext extensionContext) throws Exception {
        String level = System.getProperty("log4j2.level");
        if (level != null) {
            configLoggers(Level.valueOf(level));
        }

        // The job exceeded the maximum log length, and has been terminated.
        String travis = System.getProperty("travis");
        if (travis != null) {
            removeAllAppenders();
        }
    }

    private static void configLoggers(Level level) {
        LoggerContext ctx = (LoggerContext) LogManager.getContext(false);
        Configuration config = ctx.getConfiguration();
        LoggerConfig loggerConfig = config.getLoggerConfig(LogManager.ROOT_LOGGER_NAME);
        loggerConfig.setLevel(level);
        for (LoggerConfig lc: config.getLoggers().values()) {
            lc.setLevel(level);
        }
        ctx.updateLoggers();
    }

    private void removeAllAppenders() {
        LoggerContext ctx = (LoggerContext) LogManager.getContext(false);
        Configuration config = ctx.getConfiguration();
        LoggerConfig loggerConfig = config.getLoggerConfig(LogManager.ROOT_LOGGER_NAME);
        loggerConfig.getAppenders().keySet().forEach(loggerConfig::removeAppender);
        for (LoggerConfig lc: config.getLoggers().values()) {
            loggerConfig.getAppenders().keySet().forEach(loggerConfig::removeAppender);
        }
        ctx.updateLoggers();
    }

}
