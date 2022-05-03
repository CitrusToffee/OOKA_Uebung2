import org.hbrs.ooka.Component;
import org.hbrs.ooka.Loader;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.net.URLClassLoader;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;

public class TestComponent {
    Component component;


    @Test
    public void testKonstructor(){
        try {
            component = Loader.loadComponent("DummyKomponent-1.0-SNAPSHOT.jar");
        } catch (ClassNotFoundException | IOException e) {
            e.printStackTrace();
        }
        assertNotEquals(component,null);
        System.out.println(component);
    }

    @Test
    public void testPathSplitten(){
        String s = "file:lib"+ File.separator+"DummyKomponent-2.0-SNAPSHOT.jar!"+ File.separator;
        String[] splittedString = s.split(File.separator);
        String jsonUrl = splittedString[splittedString.length-1];
        jsonUrl = jsonUrl.substring(0,jsonUrl.length()-1);
        System.out.println(jsonUrl);
    }



}
