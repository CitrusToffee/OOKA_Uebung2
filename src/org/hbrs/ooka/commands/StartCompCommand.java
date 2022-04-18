package org.hbrs.ooka.commands;

import java.util.List;

public class StartCompCommand implements Command {

    private List<String> componantNames;

    public StartCompCommand(List<String> componantNames){
        this.componantNames=componantNames;
    }




    public void execute(){
        String res = String.format("Starting Components: %s",componantNames);
        System.out.println(res);
        System.out.println("Needs to be implemented");
    }
}
