package org.unbrokendome.jackson.beanvalidation;

import java.util.Map;

public class ValidationContextHolder {

    static ThreadLocal<Map<String, Object>> hints = new ThreadLocal<>();

    public static Map<String, Object> getHints() {
        return hints.get();
    }

}
