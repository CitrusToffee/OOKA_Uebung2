import org.hbrs.ooka.Component;
import org.hbrs.ooka.Loader;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.net.URL;
import java.net.URLClassLoader;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;

public class TestComponent {
    Component component;

    @BeforeEach
    public void setup(){
        try {
            component = Loader.loadComponent("DummyKomponent-1.0-SNAPSHOT.jar");
        } catch (ClassNotFoundException | IOException e) {
            e.printStackTrace();
        }
    }

    @Test
    public void testKonstructor(){
        assertNotEquals(component,null);
        System.out.println(component);
    }



}
