package org.hbrs.ooka.commands;

import org.hbrs.ooka.PersistentManager;

public class LoadCommand implements Command{

    public void execute(){
        PersistentManager.loadFromJson();
    }
}
