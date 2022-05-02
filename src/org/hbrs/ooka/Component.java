package org.hbrs.ooka;

import org.hbrs.ooka.States.ReadyState;
import org.hbrs.ooka.States.State;

import java.lang.reflect.Method;
import java.net.URLClassLoader;
import java.util.Arrays;

public class Component {
    public static int IDTOTAL = 0;
    private int id;
    private String name;
    private Class<?> klasse; // Startklasse
    private URLClassLoader urlClassLoader;
    private State zustand;

    private Executor thread;

    public Component(String name, Class<?> klasse, State zustand, URLClassLoader urlClassLoader) {
        this.id = IDTOTAL;
        IDTOTAL += 1;
        this.name = name;
        this.klasse = klasse;
        this.zustand = zustand;
        this.urlClassLoader = urlClassLoader;
    }

    public Component(String name, Class<?> klasse, URLClassLoader urlClassLoader) {
        this(name, klasse, null, urlClassLoader);
        this.zustand = new ReadyState(this);
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

    public State getZustand() {
        return zustand;
    }

    public void setZustand(State zustand) {
        this.zustand = zustand;
    }

    public String toString() {
        String resUrlCL ="URLs:"+ Arrays.toString(urlClassLoader.getURLs());
        StringBuilder result = new StringBuilder();
        result.append("id=").append(id).append(" ,name=").append(name).append(" ,Klasse=").append(klasse)
                .append(" ,Zustand=").append(zustand).append(" ,URLClassLoader=").append(resUrlCL);
        return result.toString();
    }

    public int getId(){
        return id;
    }

    public Method getStartMethod(){
        return getMethodWithAnnotation("@start()");
    }

    public Method getStopMethod(){
        return getMethodWithAnnotation("@stop()");
    }

    public Method getMethodWithAnnotation(String anno){
        for (Method method: klasse.getDeclaredMethods()) {
            if (Loader.isAnnotated(method)){
                if (Loader.hasMethodThatAnnotation(method,anno)){
                    return method;
                }
            }

        }
        return null;
    }

    public Executor getThread() {
        return thread;
    }

    public void setThread(Executor thread) {
        this.thread = thread;
    }

}
