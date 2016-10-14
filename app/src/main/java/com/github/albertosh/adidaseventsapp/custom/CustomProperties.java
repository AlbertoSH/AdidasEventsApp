package com.github.albertosh.adidaseventsapp.custom;

import android.view.View;
import android.widget.TextView;

import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

// TODO
public class CustomProperties {

    private final Map<String, Object> properties;

    public CustomProperties(Map<String, Object> properties) {
        this.properties = properties;
    }

    public String getDefaultImageUrl() {
        return (String) properties.get("defaultImageUrl");
    }

    public String getSelectedEventBackgroundColor() {
        return "#BDBDBDFF";
    }

    public String getUnselectedEventBackgroundColor() {
        return "#00000000";
    }

    public void applyCustomProperties(String id, View view) {
        try {
            switch (id) {
                case "txtTitle":
                    applyTextViewProperties(id, (TextView) view);
                    break;
            }
        } catch (ClassCastException e) {
            // Invalid id-view assignation
        }
    }

    private void applyTextViewProperties(String id, TextView view) {
        Map<String, Map<String, Map<String, Object>>> calls = (Map<String, Map<String, Map<String, Object>>>) properties.get("txtTitle");
        for (Map.Entry<String, Map<String, Map<String, Object>>> call : calls.entrySet()) {
            try {
                List<String> parameterClassNames = (List<String>) call.getValue().get("parameterTypes");
                List<Class> parameterClasses = new ArrayList<>();
                for (String className : parameterClassNames) {
                    switch (className) {
                        case "int":
                            parameterClasses.add(int.class);
                            break;
                        case "float":
                            parameterClasses.add(float.class);
                            break;
                        default:
                            parameterClasses.add(Class.forName(className));
                    }
                }
                Class[] parametersClassesAsArray = parameterClasses.toArray(new Class[parameterClasses.size()]);

                String methodName = call.getKey();
                Method method = null;
                Class currentClass = view.getClass();
                do {
                    try {
                        method = currentClass.getDeclaredMethod(methodName, parametersClassesAsArray);
                        break;
                    } catch (NoSuchMethodException e) {}
                    currentClass = currentClass.getSuperclass();
                } while (currentClass != null);
                if (method == null)
                    continue;

                List<String> parameterValuesNames = (List<String>) call.getValue().get("values");
                List<Object> parameterValues = new ArrayList<>();
                for (int i = 0; i < parameterClasses.size(); i++) {
                    Class usableClass = parameterClasses.get(i);
                    if (usableClass.isPrimitive())
                        usableClass = Class.forName("java.lang." +
                                Character.toUpperCase(usableClass.getName().charAt(0))
                                + usableClass.getName().substring(1));
                    Method[] methods = usableClass.getDeclaredMethods();
                    for (Method m : methods) {
                        if (m.getName().equals("valueOf") && (m.getParameterTypes()[0].equals(String.class))) {
                            parameterValues.add(m.invoke(parameterClasses.get(i), parameterValuesNames.get(i)));
                            break;
                        }
                    }
                }
                Object[] parametersValuesAsArray = parameterValues.toArray(new Object[parameterValues.size()]);

                method.invoke(view, parametersValuesAsArray);
            } catch (Exception e) {
                // Ignore
                // This is a highly experimental feature!!!
                e.printStackTrace();
            }
        }
    }
}
