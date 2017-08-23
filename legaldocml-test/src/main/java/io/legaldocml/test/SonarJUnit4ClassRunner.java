package io.legaldocml.test;

import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.core.LoggerContext;
import org.apache.logging.log4j.core.config.Configuration;
import org.apache.logging.log4j.core.config.LoggerConfig;
import org.junit.runner.notification.RunNotifier;
import org.junit.runners.BlockJUnit4ClassRunner;
import org.junit.runners.model.InitializationError;

/**
 * @author <a href="mailto:jacques.militello@gmail.com">Jacques Militello</a>
 */
public class SonarJUnit4ClassRunner extends BlockJUnit4ClassRunner {

    private static final boolean ENABLED = Boolean.parseBoolean(System.getProperty("sonar","false"));

    /**
     * Creates a BlockJUnit4ClassRunner to run {@code klass}
     *
     * @throws InitializationError if the test class is malformed.
     */
    public SonarJUnit4ClassRunner(Class<?> klass) throws InitializationError {
        super(klass);
    }

    @Override
    public void run(RunNotifier notifier) {
        if (ENABLED) {
            configLoggers(Level.ALL);
            super.run(notifier);
            configLoggers(Level.OFF);
            super.run(notifier);
        } else {
            super.run(notifier);
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
}
