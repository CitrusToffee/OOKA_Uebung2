package org.hbrs.ooka;

import java.io.IOException;
import java.lang.reflect.Method;
import java.net.URL;
import java.net.URLClassLoader;
import java.util.ArrayList;
import java.util.Enumeration;
import java.util.List;
import java.util.jar.JarEntry;
import java.util.jar.JarFile;

public class Loader {
    /*
    Annotationen need to have the RetentionPolicy Runtime
     */

    public static Component loadComponent(String jarPath) throws ClassNotFoundException, IOException {
        JarFile jarFile = new JarFile(jarPath);
        Enumeration<JarEntry> e = jarFile.entries();

        URL[] urls = { new URL("jar:file:" + jarPath+"!/") };
        URLClassLoader cl = new URLClassLoader(urls);
        // runs and finds all classes that are found
        // TODO: do something with the classes (use annotations to find the start class of this jar)
        List<Class> classes = new ArrayList<>();
        while (e.hasMoreElements()) {
            JarEntry je = e.nextElement();
            if(je.isDirectory() || !je.getName().endsWith(".class")){
                continue;
            }
            // -6 because of .class
            String className = je.getName().substring(0,je.getName().length()-6);
            className = className.replace('/', '.');
            Class c = cl.loadClass(className);
            if (isClass(c)){
                classes.add(c);
            }

        }

        for (Class c: classes) {
            System.out.println(c.getSimpleName());
            boolean containsStart = false, containsStop = false;
            for (Method m : c.getDeclaredMethods()){
                if (isAnnotated(m)){
                    if (isEquals(m,"@start()")){
                        containsStart = true;
                    } else if (isEquals(m,"@stop()")) {
                        containsStop = true;
                    }

                }

            }
            if (containsStart && containsStop){
                System.out.println("Class " + c.toString() + " ist die Startklasse.");
                return new Component(jarPath,c,cl);
            }

        }

        throw new ClassNotFoundException("Die gesuchte Startklasse wurde nicht in der Componente gefunden.");

    }

    public static boolean isClass(Class cls){
        if (cls.isEnum()){
            return false;
        } else if (cls.isAnnotation()) {
            return false;
        } else if (cls.isInterface()) {
            return false;
        } else {
            return true;
        }

    }

    public static boolean isAnnotated(Method method){
        return method.getDeclaredAnnotations().length > 0;
    }

    public static boolean isEquals(Method method, String anno){
        return (method.getDeclaredAnnotations()[0]).toString().equals(anno);
    }
}
