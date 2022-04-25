import org.hbrs.ooka.Component;
import org.hbrs.ooka.Loader;
import org.hbrs.ooka.States.State;
import org.junit.jupiter.api.Test;


import java.io.IOException;
import java.lang.reflect.Method;
import java.util.Arrays;

import static org.junit.jupiter.api.Assertions.*;


public class TestLoader {
    @Test
    public void testLoader(){
        try {
            Loader.loadComponent("DummyKomponent-1.0-SNAPSHOT.jar");
        }catch (IOException | ClassNotFoundException e) {
            fail();
            System.out.println(Arrays.toString(e.getStackTrace()));
        }

    }
    @Test
    public void testIsClass(){
        Component component = loadTestJar();
        assertTrue(Loader.isClass(component.getKlasse()));
        assertFalse(Loader.isClass(State.class));
    }

    @Test
    public void testIsAnnotated(){
        Component component = loadTestJar();
        assertFalse(Loader.isAnnotated(component.getKlasse().getDeclaredMethods()[0]));
        assertTrue(Loader.isAnnotated(component.getKlasse().getDeclaredMethods()[1]));

    }

    @Test
    public void testIsEquals(){
        Component component = loadTestJar();
        Method method = component.getKlasse().getDeclaredMethods()[1];
        assertTrue(Loader.hasMethodThatAnnotation(method,"@Start"));
        assertFalse(Loader.hasMethodThatAnnotation(method,"@Annotation"));
    }



    private Component loadTestJar(){
        try {
            return Loader.loadComponent("DummyKomponent-1.0-SNAPSHOT.jar");
        }catch (IOException | ClassNotFoundException e) {
            System.out.println(Arrays.toString(e.getStackTrace()));
        }
        return null;
    }
}
