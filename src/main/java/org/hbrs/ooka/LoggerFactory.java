package org.hbrs.ooka;

public class LoggerFactory {

    public static ILogger createLogger(String loggerName){
        return new Logger(loggerName);
    }
}
