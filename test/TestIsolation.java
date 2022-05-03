import org.hbrs.ooka.Component;
import org.hbrs.ooka.Loader;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.util.Arrays;

import static org.junit.jupiter.api.Assertions.assertNotEquals;

public class TestIsolation {

    @Test
    public void testIso(){
        Component component1 = loadTestJar();
        Component component2 = loadTestJar();

        assertNotEquals(component1.getUrlClassLoader(),component2.getUrlClassLoader());
        assertNotEquals(component1.getKlasse(),component2.getKlasse());
        assertNotEquals(component1.getStartMethod(),component2.getStartMethod());

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
