package org.hbrs.ooka.States;
import org.hbrs.ooka.Component;

public class RunningState implements State{

    private Component context;
    private final static String name = "Running";

    public RunningState(Component context) {
        this.context = context;
    }

    @Override
    public void handle() {
        context.setZustand(new StoppingState(context));
    }

    public String toString() {
        return name;
    }
}
