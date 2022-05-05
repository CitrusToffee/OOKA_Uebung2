package org.hbrs.ooka.commands;

import org.hbrs.ooka.CLager;

public class StatusCommand implements Command {

    public void execute(){
        System.out.println(CLager.getInstance().printComponents());

    }

}
