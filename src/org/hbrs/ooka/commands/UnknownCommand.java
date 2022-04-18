package org.hbrs.ooka.commands;

public class UnknownCommand implements Command {

    private String command;

    public UnknownCommand(String command){
        this.command=command;
    }


    @Override
    public void execute() {
        String res = String.format("org.hbrs.ooka.commands.Command \"%s\" is unknown",command);
        System.out.println(res);
    }
}
