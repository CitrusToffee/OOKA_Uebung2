import java.io.IOException;
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

    public static void loadComponent(String jarPath) throws ClassNotFoundException, IOException {
        JarFile jarFile = new JarFile(jarPath);
        Enumeration<JarEntry> e = jarFile.entries();

        URL[] urls = { new URL("jar:file:" + jarPath+"!/") };
        URLClassLoader cl = URLClassLoader.newInstance(urls);

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
            System.out.println(Arrays.toString(c.getDeclaredMethods()));
            System.out.println(isClass(c));
            System.out.println(Arrays.toString(c.getDeclaredAnnotations()));

            for (Method m : c.getDeclaredMethods()){
                System.out.println("--------------------");
                System.out.println(m.getName());
                System.out.println(m.getAnnotation(start.class));
                System.out.println(Arrays.toString(m.getDeclaredAnnotations()));

                System.out.println(m.isAnnotationPresent(start.class));
            }

        }

        Component component = new Component(jarPath,null,cl);

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

}
