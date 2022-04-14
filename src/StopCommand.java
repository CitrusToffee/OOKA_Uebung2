public class StopCommand implements Command {


    public void execute(){
        StartDing.setRunning(false);
        System.out.println("LZU is shutting down");
    }
}
