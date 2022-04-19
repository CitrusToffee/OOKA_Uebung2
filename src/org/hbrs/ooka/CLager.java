package org.hbrs.ooka;

import java.util.HashMap;
import java.util.Map;

public class CLager {

    private static CLager Instance;
    private final Map<Integer, Component> ComponentMap = new HashMap<>();

    public static CLager getInstance() {
        if (Instance == null) {
            Instance = new CLager();
        }
        return Instance;
    }

    private CLager() {

    }

    public void addComponent(Component component) {
        ComponentMap.put(component.getId(),component);
    }

    public Component removeComponent(int id) {
        return ComponentMap.remove(id);
    }

    public String printComponents() {
        StringBuilder output = new StringBuilder();
        for (Component component: ComponentMap.values()) {
            output.append(component.toString()).append("\n");
        }
        return output.toString();
    }
    public Component getComponent(int id){
        return ComponentMap.get(id);
    }

}
