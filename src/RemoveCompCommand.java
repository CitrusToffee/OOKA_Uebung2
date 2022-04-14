import java.util.List;

public class RemoveCompCommand implements Command{
    private List<String> componantNames;

    public RemoveCompCommand(List<String> componantNames){
        this.componantNames=componantNames;
    }




    public void execute(){
        String res = String.format("Removing Components: %s",componantNames);
        System.out.println(res);
        System.out.println("Needs to be implemented");
    }
}
