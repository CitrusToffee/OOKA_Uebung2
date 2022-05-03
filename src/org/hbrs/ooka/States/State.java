package org.hbrs.ooka.States;

import org.hbrs.ooka.Component;

public interface State {

    public void handle();

    public String toString();

    static State getState(String stateName, Component comp) {
        if (stateName.equals("Stopping")){
            return new StoppingState(comp);
        } else if (stateName.equals("Running")) {
            return new RunningState(comp);
        } else if (stateName.equals("Starting")) {
            return new StartState(comp);
        }else {
            return new ReadyState(comp);
        }

    }
}
