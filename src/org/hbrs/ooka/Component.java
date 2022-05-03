package org.hbrs.ooka;

import com.google.gson.JsonObject;
import org.hbrs.ooka.States.ReadyState;
import org.hbrs.ooka.States.State;

import java.io.File;
import java.io.IOException;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.net.URLClassLoader;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Component {
    public static int IDTOTAL = 0;
    private int id;
    private String name;
    private Class<?> klasse; // Startklasse
    private URLClassLoader urlClassLoader;
    private State zustand;
    private List<Field> InjectingFields;

    private Executor thread;

    public Component(String name, Class<?> klasse, State zustand, URLClassLoader urlClassLoader, List<Field> injectingFields) {
        this.id = IDTOTAL;
        IDTOTAL += 1;
        this.name = name;
        this.klasse = klasse;
        this.zustand = zustand;
        this.urlClassLoader = urlClassLoader;
        this.InjectingFields = injectingFields;
    }

    public Component(String name, Class<?> klasse, URLClassLoader urlClassLoader, List<Field> injectingFields) {
        this(name, klasse, null, urlClassLoader, injectingFields);
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

    public List<Field> getInjectingFields() {
        return InjectingFields;
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

    public Map<String,Object> getConfAsMap(){
        Map<String,Object> comp = new HashMap<>();

        String[] splittedString = (urlClassLoader.getURLs()[0]).getPath().split(File.separator);
        String jsonUrl = splittedString[splittedString.length-1];
        comp.put("pathToComp",jsonUrl.substring(0,jsonUrl.length()-1));

        comp.put("zustand",zustand.toString());
        return comp;
    }

    public static Component getCompFromJson(JsonObject jsonComp){
        String jsonState = jsonComp.get("zustand").getAsString();

        String jsonUrl = jsonComp.get("pathToComp").getAsString();
        try{
            Component component = Loader.loadComponent(jsonUrl);
            component.setZustand(State.getState(jsonState,component));
            return component;
        }catch(ClassNotFoundException | IOException ex){
            ex.printStackTrace();
        }

        return null;
    }

}
