import java.util.List;

public class StopCompCommand implements Command{

    private List<String> componantNames;

    public StopCompCommand(List<String> componantNames){
        this.componantNames=componantNames;
    }
    @Override
    public void execute() {

    }
}
