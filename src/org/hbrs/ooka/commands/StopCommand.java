package org.hbrs.ooka.commands;

import org.hbrs.ooka.PersistentManager;
import org.hbrs.ooka.StartDing;

public class StopCommand implements Command {


    public void execute(){
        PersistentManager.saveAsJson();
        StartDing.setRunning(false);
        System.out.println("LZU is shutting down");
    }

}
