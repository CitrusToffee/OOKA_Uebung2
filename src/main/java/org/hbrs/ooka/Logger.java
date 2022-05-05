package org.hbrs.ooka;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class Logger implements ILogger {

    String prefix;

    public Logger(String name) {
        prefix = name;
    }

    public String buildPrefix(){
        DateTimeFormatter dtf = DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm:ss");
        LocalDateTime now = LocalDateTime.now();
        return "["+prefix +", "+ dtf.format(now) + "]: ";
    }

    public void sendLog(String toLog) {
        System.out.println(buildPrefix() + toLog);
    }

}