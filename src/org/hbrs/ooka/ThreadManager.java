package org.hbrs.ooka;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

public class ThreadManager {

    public ThreadManager() {

    }

    public static void startComponent(int id) {
        Component component = CLager.getInstance().getComponent(id);
        component.getZustand().handle();
        Executor executor = new Executor(component);
        component.setThread(executor);
        executor.start();
        component.getZustand().handle();
    }


    public static void stopComponent(int id) {
        Component component = CLager.getInstance().getComponent(id);
        Executor executor = component.getThread();
        executor.interrupt();
        component.getZustand().handle();
        component.setThread(null);

    }

}
