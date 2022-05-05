package org.hbrs.ooka.commands;

import org.hbrs.ooka.CLager;
import org.hbrs.ooka.Component;
import org.hbrs.ooka.PersistentManager;
import org.hbrs.ooka.ThreadManager;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.List;

public class StartCompCommand implements Command {

    private List<Integer> componantIds;

    public StartCompCommand(List<String> componantIds){
        this.componantIds = new ArrayList<>();
        for (String name: componantIds) {
            this.componantIds.add(Integer.valueOf(name));
        }
    }




    public void execute(){
        String res = String.format("Starting Components: %s",componantIds);
        System.out.println(res);
        for (int cId:componantIds) {
            //retriving component and the main class
            ThreadManager.startComponent(cId);
        }
        PersistentManager.saveAsJson();

    }
}
