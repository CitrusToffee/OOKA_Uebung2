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
    private Class<?> klasse;
    private int zustand;
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
