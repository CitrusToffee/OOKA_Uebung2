package org.hbrs.ooka.commands;

import org.hbrs.ooka.CLager;
import org.hbrs.ooka.Component;
import org.hbrs.ooka.PersistentManager;
import org.hbrs.ooka.ThreadManager;

import java.util.ArrayList;
import java.util.List;

public class RemoveCompCommand implements Command {
    private List<Integer> componantIds;

    public RemoveCompCommand(List<String> componantIds){
        this.componantIds = new ArrayList<>();
        for (String name: componantIds) {
            this.componantIds.add(Integer.valueOf(name));
        }
    }




    public void execute(){
        String res = String.format("Removing Components: %s",componantIds);
        System.out.println(res);
        CLager clager = CLager.getInstance();
        for (int cId:componantIds) {
            //retriving component and the main class
            Component component = clager.getComponent(cId);
            if (component.getZustand().toString().equals("Ready")){
                clager.removeComponent(cId);
            } else {
                System.out.println("Component isn't in state ready and therefor cannot be removed.");
            }
        }
        PersistentManager.saveAsJson();
    }
}
