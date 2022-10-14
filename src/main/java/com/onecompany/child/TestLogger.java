package com.onecompany.child;

import org.slf4j.LoggerFactory;
import org.slf4j.Logger;

import ch.qos.logback.classic.Level;
import ch.qos.logback.classic.LoggerContext;
import com.google.cloud.logging.logback.LoggingAppender;

public class TestLogger {
//    private static final Log logger = LogFactory.getLog(App.class);

    public static void main(String[] args) {
//        logger.error("Hello from logger!!!");

        Logger cloudLogger = createCloudLogger("foo", "foo.log");
        Logger barLogger = createCloudLogger("bar", "bar.log");

        cloudLogger.error( "Hello Clouder!" );
        barLogger.error("Hello bar logger");
    }

    private static Logger createCloudLogger(String string, String file) {
        LoggerContext lc = (LoggerContext) LoggerFactory.getILoggerFactory();

        LoggingAppender cloudAppender = new LoggingAppender();
        cloudAppender.setName(string);
        cloudAppender.setLog(file);
        cloudAppender.setContext(lc);
        cloudAppender.start();

        ch.qos.logback.classic.Logger logger = (ch.qos.logback.classic.Logger) LoggerFactory.getLogger(string);
        logger.addAppender(cloudAppender);
        logger.setLevel(Level.DEBUG);
        logger.setAdditive(true);

        return LoggerFactory.getLogger(string);
    }

}
