package org.hbrs.ooka;

public class Logger {

    String prefix;
    Component comp;

    Logger(Component comp) {
        this.comp = comp;
        prefix = "[" + comp.getId() + ", " + comp.getName() + "]";
    }

    public void sendLog(String toLog) {
        System.out.println(prefix + ": " + toLog);
    }

}
