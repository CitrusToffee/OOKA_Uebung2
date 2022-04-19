package org.hbrs.ooka.commands;

import org.hbrs.ooka.CLager;
import org.hbrs.ooka.Component;
import org.hbrs.ooka.Loader;

import java.io.IOException;
import java.util.Arrays;
import java.util.List;

public class AddCompCommand implements Command {
    private List<String> componantNames;

    public AddCompCommand(List<String> componantNames){
        this.componantNames=componantNames;
    }




    public void execute(){
        CLager cLager  = CLager.getInstance();
        String res = String.format("Adding Components: %s",componantNames);
        System.out.println(res);
        for (String componantName: componantNames) {
            try {
                Component component = Loader.loadComponent(componantName);
                cLager.addComponent(component);
            } catch (ClassNotFoundException e) {
                System.out.println("Komponente "+ componantName+ " existiert nicht oder konnte nicht gefunden werden.");
            } catch (IOException e) {
                System.out.println(Arrays.toString(e.getStackTrace()));
            }
        }

    }
}
