import java.net.URLClassLoader;
import java.util.Arrays;

public class Component {
    public static int IDTOTAL = 0;
    private int id;
    private String name;
    private Class<?> klasse;
    private int zustand;
    private URLClassLoader urlClassLoader;

    public Component(String name, Class<?> klasse, int zustand, URLClassLoader urlClassLoader) {
        this.id = IDTOTAL;
        IDTOTAL += 1;
        this.name = name;
        this.klasse = klasse;
        this.zustand = zustand;
        this.urlClassLoader = urlClassLoader;
    }

    public Component(String name, Class<?> klasse, URLClassLoader urlClassLoader) {
        this(name, klasse, 0, urlClassLoader);
    }

    public String getName() {
        return name;
    }

    public Class<?> getKlasse() {
        return klasse;
    }

    public URLClassLoader getUrlClassLoader() {
        return urlClassLoader;
    }

    public int getZustand() {
        return zustand;
    }

    public void setZustand(int zustand) {
        this.zustand = zustand;
    }

    public String toString() {
        String resUrlCL ="URLs:"+ Arrays.toString(urlClassLoader.getURLs());
        StringBuilder result = new StringBuilder();
        result.append("name=").append(name).append(" ,klasse=").append(klasse).append(" ,zustand=")
                .append(zustand).append(" ,URLClassLoader=").append(resUrlCL);
        return result.toString();
    }

    public int getId(){
        return id;
    }


}
