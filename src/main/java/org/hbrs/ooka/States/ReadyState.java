package org.hbrs.ooka.States;
import org.hbrs.ooka.Component;

public class ReadyState implements State{

    private Component context;
    private final static String name = "Ready";

    public ReadyState(Component context) {
        this.context = context;
    }

    @Override
    public void handle() {
        context.setZustand(new StartState(context));
    }

    public String toString() {
        return name;
    }

}
