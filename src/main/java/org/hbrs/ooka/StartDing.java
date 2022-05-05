package org.hbrs.ooka;

import java.util.Scanner;

public class StartDing {

    private static boolean running = true;

    public static void main(String[] args) {
        System.out.println("Run Java Run!!");
        Scanner in = new Scanner(System.in);
        CLI cli = new CLI(in);
        while(running){
            // listen for Command line
            if (in.hasNextLine()){
                cli.readCL();
            }

            if (!running){
                break;
            }
            // run components

        }

    }

    public static void setRunning(boolean bool){
        running=bool;
    }

}
