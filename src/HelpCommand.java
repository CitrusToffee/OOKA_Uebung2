public class HelpCommand implements Command{
    @Override
    public void execute() {
        StringBuilder builder = new StringBuilder();
        //quit
        builder.append("quit -> stoppt die LZU").append("\n");
        builder.append("add -> lädt Komponente(-n) in die LZU").append("\n");
        builder.append("remove -> entfernt Komponente(-n) aus der LZU").append("\n");
        builder.append("Annotations.start -> startet bereits geladene Komponente(-n)").append("\n");
        builder.append("Annotations.stop -> stoppt bereits laufende Komponente(-n)").append("\n");
        builder.append("status -> gibt den Status von geladenen Komponenten wieder").append("\n").append("\n");
        builder.append("Komponenten werden mit dem Kürzel -c lib\\Beispielsdatei.jar referenziert.").append("\n");
        builder.append("Das Kürzel kann bei den Befehlen add, remove verwendet werden");

        System.out.println(builder.toString());
    }
}
