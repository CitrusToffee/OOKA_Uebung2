package org.hbrs.ooka.commands;

public class UnknownCommand implements Command {

    private String command;

    public UnknownCommand(String command){
        this.command=command;
    }


    @Override
    public void execute() {
        String res = String.format("Command \"%s\" is unknown",command);
        System.out.println(res);
    }
}
