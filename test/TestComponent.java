import org.junit.jupiter.api.Test;

import java.net.URL;
import java.net.URLClassLoader;

public class TestComponent {

    @Test
    public void testKonstructor(){
        URLClassLoader.newInstance(new URL[]{});
        Component component1 = new Component("testComp",null,URLClassLoader.newInstance(new URL[]{}));
        System.out.println(component1);
    }

}
