package org.hbrs.ooka;

import org.hbrs.ooka.commands.*;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

public class CLI {
    private List<Command> actions;
    private Scanner in;
    public CLI(Scanner in){
        this.in=in;
        actions = new ArrayList<>();
    }

    public void readCL(){
        String input = in.nextLine();
        executeCommand(input);
    }

    public void executeCommand(String input){
        List<String> commandListe = splitCommand(input);
        String commandoString = commandListe.get(0).trim();
        List<String> commandArgs;
        if (commandListe.size()>=2){
            commandArgs = commandListe.subList(1,commandListe.size());
        }else {
            commandArgs = new ArrayList<>();
        }
        Command command;
        switch (commandoString){
            case "shutdown":
                command = new StopCommand();
                break;
            case "start":
                command = new StartCompCommand(commandArgs);
                break;
            case "add":
                command = new AddCompCommand(commandArgs);
                break;
            case "stop":
                command = new StopCompCommand(commandArgs);
                break;
            case "remove":
                command = new RemoveCompCommand(commandArgs);
                break;
            case "status":
                command = new StatusCommand();
                break;
            case "help":
                command= new HelpCommand();
                break;
            default:
                command = new UnknownCommand(input);
                break;
        }
        actions.add(command);
        command.execute();
    }

    public List<String> splitCommand(String input){
        List<String> stringList;

        if (input.contains(" ")){
            stringList = Arrays.asList(input.split(" "));
        }else {
            stringList = new ArrayList<>();
            stringList.add(input);
        }

        return stringList;

    }


}
