package org.hbrs.ooka.States;
import org.hbrs.ooka.Component;

public class StoppingState implements State {

    private Component context;
    private final static String name = "Stopping";

    public StoppingState(Component context) {
        this.context = context;
    }

    @Override
    public void handle() {
        context.setZustand(new ReadyState(context));
    }

    public String toString() {
        return name;
    }
}
