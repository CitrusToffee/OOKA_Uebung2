package org.hbrs.ooka.commands;

import org.hbrs.ooka.CLager;
import org.hbrs.ooka.Component;
import org.hbrs.ooka.PersistentManager;
import org.hbrs.ooka.ThreadManager;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.List;

public class StopCompCommand implements Command {

    private List<Integer> componantIds;

    public StopCompCommand(List<String> componantIds){
        this.componantIds = new ArrayList<>();
        for (String name: componantIds) {
            this.componantIds.add(Integer.valueOf(name));
        }
    }
    @Override
    public void execute() {
        String res = String.format("Stopping Components: %s",componantIds);
        System.out.println(res);

        for (int cId:componantIds) {
            //retriving component and the main class
            Component component = CLager.getInstance().getComponent(cId);
            if (component.getThread()==null){
                System.out.println("Component can't be stopped, because it isn't running.");
                continue;
            }
            component.getZustand().handle();
            if (component.getThread().getState().equals(Thread.State.TERMINATED)){
                component.setThread(null);
                component.getZustand().handle();
            } else {
                ThreadManager.stopComponent(cId);
            }


        }
        PersistentManager.saveAsJson();
    }
}
