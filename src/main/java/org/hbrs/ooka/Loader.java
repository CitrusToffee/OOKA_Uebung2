package org.hbrs.ooka;

import org.hbrs.ooka.Annotations.Inject;
import org.hbrs.ooka.Annotations.start;
import org.hbrs.ooka.Annotations.stop;

import java.io.File;
import java.io.IOException;
import java.lang.annotation.Annotation;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.net.URL;
import java.net.URLClassLoader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Enumeration;
import java.util.List;
import java.util.jar.JarEntry;
import java.util.jar.JarFile;

public class Loader {
    /*
     *Annotationen need to have the RetentionPolicy Runtime
     */

    public static Component loadComponent(String jarPath) throws ClassNotFoundException, IOException {
        String realJarPath =  "lib"+File.separator+jarPath;
        System.out.println(realJarPath);
        JarFile jarFile = new JarFile(realJarPath);
        Enumeration<JarEntry> e = jarFile.entries();

        URL[] urls = { new URL("jar:file:"+ realJarPath+"!/") };
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
                    if (m.isAnnotationPresent(start.class)){
                        containsStart = true;
                    } else if (m.isAnnotationPresent(stop.class)) {
                        containsStop = true;
                    }

                }

            }


            if (containsStart && containsStop){
                System.out.println("Class " + c.toString() + " ist die Startklasse.");

                List<Field> injectingFields = new ArrayList<>();
                for (Field f : c.getDeclaredFields()) {

                    if (f.isAnnotationPresent(Inject.class)) {
                        injectingFields.add(f);
                    }
                }

                return new Component(jarPath,c,cl,injectingFields);
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

    public static void injectObjects(Component comp, Object obj, List<Field> fields) {
        for(Field declaredField: fields) {

            try {
                boolean accessible = declaredField.canAccess(obj);
                declaredField.setAccessible(true);

                // getting @Inject Annotation Informations
                String prefix = declaredField.getAnnotation(Inject.class).Prefix();

                //create Logger
                ILogger LOG = LoggerFactory.createLogger(prefix);
                declaredField.set(obj, LOG);

                declaredField.setAccessible(accessible);
            } catch (IllegalAccessException e) {
                throw new RuntimeException(e);
            }
        }
    }

    public static boolean isAnnotated(Method method){
        return method.getDeclaredAnnotations().length > 0;
    }

    public static boolean hasMethodThatAnnotation(Method method, String anno){
        for (Annotation anon: method.getDeclaredAnnotations()){
            if (anon.toString().endsWith(anno)){
                return true;
            }
        }
        return false;
    }

    public static boolean hasFieldThatAnnotation(Field field, String anno){
        for (Annotation anon: field.getDeclaredAnnotations()){
            if (anon.toString().endsWith(anno)){
                return true;
            }
        }
        return false;

    }

    public static URLClassLoader getClassLoader(String pathToJar){
        try{
            URL[] urls = { new URL(pathToJar) };
            return new URLClassLoader(urls);
        }catch (IOException ex) {
            ex.printStackTrace();
        }
        return null;
    }

    public static Class getStartClass(URLClassLoader cl,String className){
        try{
            return cl.loadClass(className);
        }catch(ClassNotFoundException ex){
            ex.printStackTrace();
        }
        return null;
    }
}
