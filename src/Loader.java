import java.io.IOException;
import java.net.URL;
import java.net.URLClassLoader;
import java.util.Enumeration;
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
        while (e.hasMoreElements()) {
            JarEntry je = e.nextElement();
            if(je.isDirectory() || !je.getName().endsWith(".class")){
                continue;
            }
            // -6 because of .class
            String className = je.getName().substring(0,je.getName().length()-6);
            className = className.replace('/', '.');
            Class c = cl.loadClass(className);

        }

        Component component = new Component(jarPath,null,cl);

    }

}
