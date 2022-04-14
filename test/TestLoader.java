import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.util.Arrays;

public class TestLoader {
    @Test
    public void testLoader(){
        try {
            Loader.loadComponent("lib\\DummyKomponent-1.0-SNAPSHOT.jar");
        }catch (IOException | ClassNotFoundException e) {
            System.out.println(Arrays.toString(e.getStackTrace()));
        }

    }
}
