package org.hbrs.ooka.commands;

import org.hbrs.ooka.PersistentManager;

public class PersistenzCommand implements Command{

    public void execute(){
        PersistentManager.saveAsJson();
        System.out.println("Zustand der LZU in JSON persistiert");
    }
}
