public class StatusCommand implements Command{

    public void execute(){
        CLager.getInstance().printComponents();
    }

}
