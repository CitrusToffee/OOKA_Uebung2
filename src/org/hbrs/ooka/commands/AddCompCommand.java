package org.hbrs.ooka.commands;

import java.util.List;

public class AddCompCommand implements Command {
    private List<String> componantNames;

    public AddCompCommand(List<String> componantNames){
        this.componantNames=componantNames;
    }




    public void execute(){
        String res = String.format("Adding Components: %s",componantNames);
        System.out.println(res);
        System.out.println("Needs to be implemented");
    }
}
