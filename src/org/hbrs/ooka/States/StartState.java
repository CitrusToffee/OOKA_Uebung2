package org.hbrs.ooka.States;
import org.hbrs.ooka.Component;

public class StartState implements State{

    private Component context;
    private final static String name = "Starting";

    public StartState(Component context) {
        this.context = context;
    }

    @Override
    public void handle() {
        context.setZustand(new RunningState(context));
    }

    public String toString() {
        return name;
    }
}
