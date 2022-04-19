package org.hbrs.ooka;

import javax.swing.*;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

public class Executor extends Thread{

    private Component component;

    public Executor(Component component){
        this.component=component;
    }

    public void run(){
        try {
            var obj = component.getKlasse().getConstructor().newInstance();
            Method startMethod = component.getStartMethod();
            startMethod.invoke(obj);

        } catch (IllegalAccessException e) {
            throw new RuntimeException(e);
        } catch (InvocationTargetException e) {
            throw new RuntimeException(e);
        } catch (InstantiationException e) {
            throw new RuntimeException(e);
        } catch (NoSuchMethodException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void interrupt() {
        try {
            var obj = component.getKlasse().getConstructor().newInstance();
            Method stopMethod = component.getStopMethod();
            stopMethod.invoke(obj);
        } catch (IllegalAccessException e) {
            throw new RuntimeException(e);
        } catch (InvocationTargetException e) {
            throw new RuntimeException(e);
        } catch (InstantiationException e) {
            throw new RuntimeException(e);
        } catch (NoSuchMethodException e) {
            throw new RuntimeException(e);
        }
        super.interrupt();
    }


}
